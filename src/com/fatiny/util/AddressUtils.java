package com.fatiny.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

import com.fatiny.pojo.Visitor;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;
import com.useragentutils.tools.Browser;
import com.useragentutils.tools.OperatingSystem;
import com.useragentutils.tools.UserAgent;


/**
 * 根据IP地址获取详细的地域信息
 */
public class AddressUtils {
	
	private static final String localhost = "本地地址";
	private static final String localip = "192.168.1.";
	private static final String localip2 = "0:0:0:0:0:0:0:1";
	private static final String errorResult = "无效IP或未解析成功";
	
	/** 获取IP */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	/**
	 * @Description  通过IP获取地址
	 * @author Jeremy
	 * @date 2016年1月19日 下午5:40:10 
	 * @version V1.0
	 * @param request
	 * @return
	 */
	public static Visitor createVisitorByIp(HttpServletRequest request, String ip) {
		//如果缓存没有当前访问者,则生成一个新的visitor
		String address = AddressUtils.getGeoAddress(ip);
		String dizhi = AddressUtils.getIpInfo(ip);
		
		/*获取用户的浏览器信息*/
		String agent = request.getHeader("user-agent");
		UserAgent uAgent = new UserAgent(agent);
		OperatingSystem os = uAgent.getOperatingSystem();
		Browser browser = uAgent.getBrowser();
		String osName = os.getName();
		String browserName =browser.getName();
		Visitor visitor = new Visitor(ip, address, dizhi, osName, browserName);
		return visitor;
	}
	
	/**
	    * 方法名：getIpInfo</br>
	    * 详述：通过IP获取地址(需要联网，调用淘宝的IP库)</br>
	    * 开发人员：souvc </br>
	    * 创建时间：2015-11-10  </br>
	    * @param ip
	    * @return
	    * @throws
	     */
	    public static String getIpInfo(String ip) {
	    	//本地
			if (ip.contains(localip) || ip.contains(localip2)) {
				return localhost;
			}
	        String info = "";
	        try {
	            URL url = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
	            HttpURLConnection htpcon = (HttpURLConnection) url.openConnection();
	            htpcon.setRequestMethod("GET");
	            htpcon.setDoOutput(true);
	            htpcon.setDoInput(true);
	            htpcon.setUseCaches(false);
	 
	            InputStream in = htpcon.getInputStream();
	            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
	            StringBuffer returnStr = new StringBuffer();
	            String line = bufferedReader.readLine();
	            while (line != null) {
	            	returnStr.append(line).append("\r\n");
	                line = bufferedReader.readLine();
	            }
	            bufferedReader.close();
	            
	            if (returnStr != null) {
	    			// 处理返回的省市区信息
	    			//System.out.println(returnStr);
	    			String[] temp = returnStr.toString().split(",");
	    			if (temp.length < 3) {
	    				return "0";// 无效IP，局域网测试
	    			}
	    			String contry = (temp[2].split(":"))[1].replaceAll("\"", "");
	    			String area = (temp[3].split(":"))[1].replaceAll("\"", "");
	    			String region = (temp[5].split(":"))[1].replaceAll("\"", "");
	    			String city = (temp[7].split(":"))[1].replaceAll("\"", "");
	    			String isp = (temp[11].split(":"))[1].replaceAll("\"", "");
	    			area = decodeUnicode(area); //地区
	    			contry = decodeUnicode(contry); //国家
	    			region = decodeUnicode(region);// 省份
	    			city = decodeUnicode(city);// 省份
	    			isp = decodeUnicode(isp);// 公司
	    			
	    			if (region == "" || region.equals(""))
						return contry;
	    			
	    			return area + region + city + isp;
	    		}
	            
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (ProtocolException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return info;
	    }
	
	/**
	 * unicode 转换成 中文
	 * @author fanhui 2007-3-15
	 * @param theString
	 * @return
	 */
	private static String decodeUnicode(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException(
									"Malformed      encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't') {
						aChar = '\t';
					} else if (aChar == 'r') {
						aChar = '\r';
					} else if (aChar == 'n') {
						aChar = '\n';
					} else if (aChar == 'f') {
						aChar = '\f';
					}
					outBuffer.append(aChar);
				}
			} else {
				outBuffer.append(aChar);
			}
		}
		return outBuffer.toString();
	}
	
	public static String getGeoAddress(String ip){
		//本地
		if (ip.contains(localip) || ip.contains(localip2)) {
			return localhost;
		}
		//如果是非本地地址的IP
		try {
//			File dbfile = new File("/root/Jeremy/GeoLiteCity.dat");
			File dbfile = new File("D://GeoLiteCity.dat");
			LookupService lookupService = new LookupService(dbfile, LookupService.GEOIP_MEMORY_CACHE);
			Location location = lookupService.getLocation(ip);
			// Populate region. Note that regionName is a MaxMind class, not an instance variable
			if (location != null) {
			    //location.region = regionName.regionNameByCode(location.countryCode, location.region);
				String contry = location.countryName;
				String region = regionName.regionNameByCode(location.countryCode, location.region);
				String city = location.city;
				String result = "";
				if (city != null && !city.isEmpty()) 
					result += city + ", ";
				if (region != null && !region.isEmpty())
					result += region + ", ";
				if (contry != null && !contry.isEmpty())
					result += contry;
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return errorResult;
	}
	
	@Test
	public void splitips(){
		List<String> list = new ArrayList<String>(Arrays.asList("219.136.134.157", "112.74.85.106", "119.81.157.66", "24.143.198.188"));
		for (String str : list) {
			String address = getIpInfo(str);
			System.out.println("==>"+address);
		}
	}
	
	@Test
	public void splitGeoip(){
//		 测试ip 219.136.134.157 中国=华南=广东省=广州市=越秀区=电信
//		 测试ip 125.70.11.136 中国=西南=四川省=成都市==电信
		String ip = "117.150.35.41";
//		String ip="112.74.85.106";	//广东深圳
//		ip = "103.9.116.204";	//台湾省
//		ip = "119.81.157.66";	//HK
//		ip = "5.39.51.137";		//FR
//		ip = "24.143.198.188";	//US
		String address = getGeoAddress(ip);
		System.out.println("==>"+address);
	}
	
	@Test
	public void splitGeoips(){
//		List<String> list = new ArrayList<String>(Arrays.asList("103.9.116.204", "24.143.198.188"));
		List<String> ipList = new ArrayList<String>();
		for (int i = 1; i <= 2000; i++) {
			ipList.add(("119.81.157."+i));
		}
		ipList.add("219.136.134.157");
		ipList.add("103.9.116.204");
//		Arrays.asList("103.9.116.204", "24.143.198.188")
		long beginTime = System.currentTimeMillis();
		for (String str : ipList) {
			String address = getGeoAddress(str);
			System.out.println("==>"+address);
		}
		System.out.println("use time: "+(System.currentTimeMillis() - beginTime));
	}
	
	
}