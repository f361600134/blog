package com.fatiny.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;

/**
 * 根据IP地址获取详细的地域信息
 */
public class AddressUtils {
	
	private static final String localhost = "Local or LAN";
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
	 * 获取指定IP的区域位置
	 * @param content
	 *            请求的参数 格式为：name=xxx&pwd=xxx
	 * @param encoding
	 *            服务器端请求编码。如GBK,UTF-8等
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getAddresses(String ip) {
		// 这里调用pconline的接口
		String urlStr = "http://ip.taobao.com/service/getIpInfo.php";
		String content = "ip="+ip;
		// 从http://whois.pconline.com.cn取得IP所在的省市区信息
		String returnStr = getResult(urlStr, content);
		if (returnStr != null) {
			// 处理返回的省市区信息
			//System.out.println(returnStr);
			String[] temp = returnStr.split(",");
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
			return region + city + isp;
		}
		return errorResult;
	}

	/**
	 * @param urlStr
	 *            请求的地址
	 * @param content
	 *            请求的参数 格式为：name=xxx&pwd=xxx
	 * @param encoding
	 *            服务器端请求编码。如GBK,UTF-8等
	 * @return
	 */
	private static String getResult(String urlStr, String content) {
		URL url = null;
		HttpURLConnection connection = null;
		try {
			url = new URL(urlStr);
			connection = (HttpURLConnection) url.openConnection();// 新建连接实例
			connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
			connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒
			connection.setDoOutput(true);// 是否打开输出流 true|false
			connection.setDoInput(true);// 是否打开输入流true|false
			connection.setRequestMethod("POST");// 提交方法POST|GET
			connection.setUseCaches(false);// 是否缓存true|false
			connection.connect();// 打开连接端口
			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());// 打开输出流往对端服务器写数据
			out.writeBytes(content);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
			out.flush();// 刷新
			out.close();// 关闭输出流
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));// 往对端写完数据对端服务器返回数据
			// ,以BufferedReader流来读取
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();// 关闭连接
			}
		}
		return null;
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
			File dbfile = new File("/root/Jeremy/GeoLiteCity.dat");
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
	
	
	// 测试
	public static void main(String[] args) {
		// 测试ip 219.136.134.157 中国=华南=广东省=广州市=越秀区=电信
		// 测试ip 125.70.11.136 中国=西南=四川省=成都市==电信
		//String ip = "218.192.3.42";
		//String ip="112.74.85.106";	//广东深圳
		//ip = "103.9.116.204";	//台湾省
		//ip = "119.81.157.66";	//HK
		//ip = "5.39.51.137";		//FR
		//ip = "24.143.198.188";	//US
		List<String> list = new ArrayList<String>(Arrays.asList("103.9.116.204", "112.74.85.106"));
		for (String str : list) {
			String address = getAddresses(str);
			System.out.println("==>"+address);
		}
	}
}