package com.fatiny.util;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.slf4j.Logger;

/**
 * 之前的处理方式,前后端依赖性太强,并且维护性差.所以要对其进行整改.具体方案如下:
 * 首先,
 * @author Jeremy
 */
public class MediaUtil {
	private static Logger log = LogContext.LOG_MODULE_UTILS;
	
	private static String REG_VEDIO = "\\<video.*?><\\/video>";
	private static String REG_AUDIO = "\\<audio.*?><\\/audio>";
	private static String REG_IMAGE = "<img[^>]*/>";
	private static String REG_P = "<p></p>";
	
	private static String TAG_VEDIO = "<video id=\"home_video\" class=\"entry-video video-js vjs-default-skin\"  data-aspect-ratio='2.41' data-setup=\"{}\" controls> <source src='?' type='video/mp4' ></source></video>";
	private static String TAG_AUDIO = "<audio class=\"entry-audio AudioPlayerV1\"  controls=\"controls\"><source src='?' type=\"audio/mpeg\"></source></audio>";
	private static String TAG_IMAGE = "<a class=\"single-image picture-icon\" href=\"?\"><img class=\"entry-image\" src=\"?\" alt=\"\"></img></a>";
	private static String TAG_IMAGES_LI="<li>?</li>";
	private static String TAG_IMAGES = "<div class=\"image-gallery-slider\"><ul>?</ul></div>";
	
	/**
	 * 获取指定属性的src路径
	 * @param source
	 * @param element
	 * @return
	 */
	private static List<String> getUrls(String source, String element){
		Document doc = Jsoup.parse(source);
		Elements eles=doc.select(element);
		ArrayList<String> result = new ArrayList<String>();
		for (Element ele : eles) {
			if(ele == null) continue;
			result.add(ele.attr("src"));
		}
		return result;
	}
	
	/**
	 * 获取指定属性的src路径
	 * @param source
	 * @param element
	 * @return
	 */
	private static String getUrl(String source, String element){
		Document doc = Jsoup.parse(source);
		Element ele=doc.select(element).first();
		if(ele == null) return "";
		return ele.attr("src");
	}
	
	/**
	 * 获取指定属性的src路径
	 * @param source
	 * @param element
	 * @return
	 */
	private static String getElement(String source, String element){
		Document doc = Jsoup.parse(source);
		Elements ele=doc.select(element);
		return ele.toString();
	}
	
	/**
	 * 获取一个视频,的url
	 * @param source
	 * @return
	 */
	private static String getAudioUrl(String source){
		String element = "audio";
		source = getElement(source,element);
		if (StringUtil.isBlank(source)) return "";
		
		element = "source";
		return getUrl(source,element);
	}
	
	/**
	 * 获取一个视频,的url
	 * @param source
	 * @return
	 */
	private static String getVideoUrl(String source){
		String element = "video";
		source = getElement(source,element);
		if (StringUtil.isBlank(source)) return "";
		
		element = "source";
		return getUrl(source,element);
	}
	
	/**
	 * 获取单图
	 * @param source
	 * @return
	 */
	private static String getPictureUrl(String source){
		String element = "img";
		return getUrl(source,element);
	}
	
	/**
	 * 获取多图
	 * @param source
	 * @return
	 */
	private static List<String> getImagesUrl(String source){
		String element = "img";
		return getUrls(source,element);
	}
	
	private static String removeTag(String content){
		//去掉所有html标签
		//content = content.replaceAll("\\<.*?>", ""); 
		//去掉img标签
		content = content.replaceAll("<img[^>]*/>", ""); 
		//去掉vedio标签
		content = content.replaceAll("\\<video.*?><\\/video>", "");
		//去掉audio标签
		content = content.replaceAll("\\<audio.*?><\\/audio>", "");
		//去掉p标签
		content = content.replaceAll("<p></p>", "");
		return content;
	}
	
	public static String getMedia(String source) {
		log.info("just to backup:"+source);
		//获取到url
		String vedio = getVideoUrl(source);
		String result = "";
		if (!StringUtil.isBlank(vedio)){
			result = UtilTools.format(TAG_VEDIO, vedio);
		}
		if (StringUtil.isBlank(result)){
			String audio = getAudioUrl(source);
			if (!StringUtil.isBlank(audio)){
				result = UtilTools.format(TAG_AUDIO, audio);
			}
		}
		if (StringUtil.isBlank(result)){
			//获取到所有路径
			List<String> Urls = getImagesUrl(source);
			//过滤掉不合要求的图片路径,留下合乎要求的
			List<String> imageUrls = ImageUtil.getTrueUrl(Urls);
			if (imageUrls.size() == 1) {
				String imgPath = imageUrls.get(0);
				String newSmallPath = ImageUtil.cutBlit(imgPath);
				result = UtilTools.format(TAG_IMAGE, imgPath, newSmallPath);
			}else if(imageUrls.size() > 1){
				StringBuilder builder = new StringBuilder();
				for (String url : imageUrls) {
					String newSmallPath = ImageUtil.cutBlit(url);
					result = UtilTools.format(TAG_IMAGE, url, newSmallPath);
					result = UtilTools.format(TAG_IMAGES_LI, result);
					builder.append("\n").append(result);
				}
				result = UtilTools.format(TAG_IMAGES, builder.toString());
			}
		}
		log.info("Media info:"+result);
		return result;
	}
	
	
	
	/**
	 * 视频,音乐,照片,要替换成新的样式.
	 * @param source
	 * @return
	 */
	public static String assembleTag(String source) {
		log.info("before process Content info:"+source);
		//获取到url
		String vedio = getVideoUrl(source);
		String audio = getAudioUrl(source);
		List<String> images = getImagesUrl(source);
		//List<String> result = new ArrayList<String>();
		String temp = null;
		if (!StringUtil.isBlank(vedio)){
			temp = UtilTools.format(TAG_VEDIO, vedio);
			source.replace(REG_VEDIO, vedio);
		}
		if (!StringUtil.isBlank(audio)){
			temp =  UtilTools.format(TAG_AUDIO, audio);
			source.replace(REG_AUDIO, audio);
		}
		if (!images.isEmpty()) {
			for (String img : images) {
				temp = UtilTools.formatForBlog(TAG_IMAGE, img);
				source = source.replaceFirst(REG_IMAGE, temp);
			}
		}
		source.replaceAll(REG_P, "");
		log.info("after process Content info:"+source);
		return source;
	}
	
	@Test
	public void test(){
		String content  = "<p>nothing in the world.</p><p><img src=\"/blog/uploads/image/blog/201510/1443946238458-1.jpg\" title=\"1443946238458-1.jpg\" alt=\"1.jpg\"/></p><img src=\"/blog/uploads/image/blog/201510/1443859333325-2.jpg\" title=\"1443859333325-2.jpg\" alt=\"2.jpg\"/><p><audio class=\"entry-audio AudioPlayerV1\" controls=\"controls\"><source src=\"/blog/uploads/file/blog/201510/1443946335168-Sad Angel.mp3\" type=\"audio/mpeg\"/></audio></p><p>ok, 就是这些都东西!</p>";
		String info = getMedia(content);
		System.out.println("before process:"+content+"\ninfo:"+info);
		content = assembleTag(content);
		System.out.println("after process:"+content);
	}
	
	public static void main(String[] args) {
		//String content = "<p>just for test upload pictrues.</p><p>ok, let&#39;s do it.</p><p><img class=\"entry-image\" src=\"/blog/uploads/image/blog/201510/1443859304670-2.jpg\" alt=\"1443859304670-2.jpg\"/><img src=\"/blog/uploads/image/blog/201510/1443859333325-2.jpg\" title=\"1443859333325-2.jpg\" alt=\"2.jpg\"/></p><p><media class=\"\"><source src=\"aa.mp3\"></source></media></p><p>ok. let&#39; debug the code.";
		String content  = "<p>nothing in the world.</p><p><img src=\"/blog/uploads/image/blog/201510/1443946238458-1.jpg\" title=\"1443946238458-1.jpg\" alt=\"1.jpg\"/></p><p><video id=\"home_video\" class=\"entry-video video-js vjs-default-skin\" data-aspect-ratio=\"2.41\" data-setup=\"{}\" controls=\"\"><source src=\"/blog/uploads/vedio/blog/201510/1443946311118-VID_20130223_001230.mp4\" type=\"video/mp4\"/></video></p><img src=\"/blog/uploads/image/blog/201510/1443859333325-2.jpg\" title=\"1443859333325-2.jpg\" alt=\"2.jpg\"/><p><audio class=\"entry-audio AudioPlayerV1\" controls=\"controls\"><source src=\"/blog/uploads/file/blog/201510/1443946335168-Sad Angel.mp3\" type=\"audio/mpeg\"/></audio></p><p>ok, 就是这些都东西!</p>";
		System.out.println("获取到Audio URL:"+getAudioUrl(content));
		System.out.println("获取到Vedio URL:"+getVideoUrl(content));
		System.out.println("获取到单图 URL:"+getPictureUrl(content));
		System.out.println("获取到多图 URL:"+getImagesUrl(content));
		System.out.println();
		System.out.println("resource:"+content);
		content = removeTag(content);
		System.out.println("resource:"+content);
		//String source  = "<p>nothing in the world.</p><p><img src=\"/blog/uploads/image/blog/201510/1443946238458-1.jpg\" title=\"1443946238458-1.jpg\" alt=\"1.jpg\"/></p><p><video id=\"home_video\" class=\"entry-video video-js vjs-default-skin\" data-aspect-ratio=\"2.41\" data-setup=\"{}\" controls=\"\"><source src=\"/blog/uploads/vedio/blog/201510/1443946311118-VID_20130223_001230.mp4\" type=\"video/mp4\"/></video></p><img src=\"/blog/uploads/image/blog/201510/1443859333325-2.jpg\" title=\"1443859333325-2.jpg\" alt=\"2.jpg\"/><p><audio class=\"entry-audio AudioPlayerV1\" controls=\"controls\"><source src=\"/blog/uploads/file/blog/201510/1443946335168-Sad Angel.mp3\" type=\"audio/mpeg\"/></audio></p><p>ok, 就是这些都东西!</p>";
		String source  = "<p>nothing in the world.</p><p><img src=\"/blog/uploads/image/blog/201510/1443946238458-1.jpg\" title=\"1443946238458-1.jpg\" alt=\"1.jpg\"/></p><p></p><img src=\"/blog/uploads/image/blog/201510/1443859333325-2.jpg\" title=\"1443859333325-2.jpg\" alt=\"2.jpg\"/><p></p><p>ok, 就是这些都东西!</p>";
		System.out.println(assembleTag(source));
	}
	
}
