package com.fatiny.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;


/**
 * 图片处理工具类：<br>
 * 功能：缩放图像、切割图像、图像类型转换、彩色转黑白、文字水印、图片水印等
 * @author Administrator
 */
public class ImageUtil {
    /**
     * 几种常见的图片格式
     */
    public static String IMAGE_TYPE_GIF = "gif";// 图形交换格式
    public static String IMAGE_TYPE_JPG = "jpg";// 联合照片专家组
    public static String IMAGE_TYPE_JPEG = "jpeg";// 联合照片专家组
    public static String IMAGE_TYPE_BMP = "bmp";// 英文Bitmap（位图）的简写，它是Windows操作系统中的标准图像文件格式
    public static String IMAGE_TYPE_PNG = "png";// 可移植网络图形
    public static String IMAGE_TYPE_PSD = "psd";// Photoshop的专用格式Photoshop
    
    /**
     * 要把旧的地址改成新的地址.并且还不能用绝对路径,改成相对路径
     * @param f
     * @return
     */
    private static String getSmallImagePath(File f){
    	final String sign = "sm_";
    	String newPath = f.getParent()+"/"+sign+f.getName();
    	return newPath;
    }
    
    /**
     * @param 检查能否多图
     * @return
     */
    public static boolean checkUrl(List<String> srcImageFile){
    	List<String> result = new ArrayList<String>();
    	BufferedImage bi;
    	File file;
    	try {
	    	for (String src : srcImageFile) {
	    		file = new File(SystemConfig.REAL_PATH + src);
	    		//图片太小,直接忽略
    			bi = ImageIO.read(file);
    			if (bi.getWidth() > 650 && bi.getHeight() > 250) 
    	    		result.add(src);
	    	}
	    	if (result.size() > 1) {
				return true;
			}
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	return false;
    }
    
    /**
     * @param 返回多图集合
     * @return
     */
    public static List<String> getTrueUrl(List<String> srcImageFile){
    	List<String> result = new ArrayList<String>();
    	BufferedImage bi;
    	File file;
    	try {
	    	for (String src : srcImageFile) {
	    		file = new File(SystemConfig.REAL_PATH + src);
	    		//图片太小,直接忽略
    			bi = ImageIO.read(file);
    			if (bi.getWidth() > 650 && bi.getHeight() > 250) 
    	    		result.add(src);
	    	}
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	return result;
    }
    
    /**
     * 
     * @param 通过源地址,生成新图片,返回新图片
     * @return
     */
    public static String cutBlit(String srcImageFile){
    	//String newPath = scale(srcImageFile, 0, 400, true);
    	String newPath = cutPic(srcImageFile, 0, 0, 700, 280);
    	return newPath;
    }
    
    /**
     * 图像切割(按指定起点坐标和宽高切割)
     * @param srcImageFile 源图像地址
     * @param result 切片后的图像地址
     * @param x 目标切片起点坐标X
     * @param y 目标切片起点坐标Y
     * @param width 目标切片宽度
     * @param height 目标切片高度
     */
    public final static String cut(String srcImageFile, int x, int y, int width, int height) {
    	//如果程序异常或者不符合剪切要求,则直接返回原图像
    	String result = srcImageFile;
        try {
            // 读取源图像
        	File file = new File(SystemConfig.REAL_PATH + srcImageFile);
			result = getSmallImagePath(file);
            BufferedImage bi = ImageIO.read(file);
            
            int srcWidth = bi.getHeight(); // 源图宽度
            int srcHeight = bi.getWidth(); // 源图高度
            if (srcWidth > 0 && srcHeight > 0) {
                Image image = bi.getScaledInstance(srcWidth, srcHeight,
                        Image.SCALE_DEFAULT);
                // 四个参数分别为图像起点坐标和宽高
                // 即: CropImageFilter(int x,int y,int width,int height)
                ImageFilter cropFilter = new CropImageFilter(x, y, width, height);
                Image img = Toolkit.getDefaultToolkit().createImage(
                        new FilteredImageSource(image.getSource(),
                                cropFilter));
                BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                Graphics g = tag.getGraphics();
                g.drawImage(img, 0, 0, width, height, null); // 绘制切割后的图
                g.dispose();
                //保存使用绝对路径
                ImageIO.write(tag, IMAGE_TYPE_PNG, new File(result));
                //显示需要相对路径
                result = "/"+ result.replaceFirst(SystemConfig.REAL_PATH, "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
    /**
     * 图片切割2
     * @param srcImageFile
     * @param outFile
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
    public static String cutPic(String srcImageFile, int x, int y, int width, int height) {
		FileInputStream is = null;
		ImageInputStream iis = null;
		String result = "";
		try {
			 // 读取源图像
        	File file = new File(SystemConfig.REAL_PATH + srcImageFile);
        	result = getSmallImagePath(file);
			// 如果源图片不存在
        	/*if (!new File(srcImageFile).exists()) {
				return false;
			}*/
			
        	//图片太小,直接忽略
        	//BufferedImage bi = ImageIO.read(file);
        	//if (bi.getWidth() < 650 && bi.getHeight() < 250)  return result;
        	
        	// 读取图片文件
			is = new FileInputStream(file);
			// 获取文件格式
			String ext = srcImageFile.substring(srcImageFile.lastIndexOf(".") + 1);
			// ImageReader声称能够解码指定格式
			Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(ext);
			ImageReader reader = it.next();
			// 获取图片流
			iis = ImageIO.createImageInputStream(is);
			// 输入源中的图像将只按顺序读取
			reader.setInput(iis, true);
			// 描述如何对流进行解码
			ImageReadParam param = reader.getDefaultReadParam();
			// 图片裁剪区域
			Rectangle rect = new Rectangle(x, y, width, height);
			// 提供一个 BufferedImage，将其用作解码像素数据的目标
			param.setSourceRegion(rect);
			// 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象
			BufferedImage bi = reader.read(0, param);
			// 保存新图片
			ImageIO.write(bi, ext, new File(result));
			//显示需要相对路径
            result = "\\"+ result.replace(SystemConfig.REAL_PATH, "");
            result = result.replace("\\", "/");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (iis != null) {
					iis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				return result;
			}
		}
	}
    
	/**
     * 缩放图像（按高度和宽度缩放）
     * @param srcImageFile 源图像文件地址
     * @param result 缩放后的图像地址
     * @param width 缩放后的宽度
     * @param height 缩放后的高度
     * @param bb 比例不对时是否需要补白：true为补白; false为不补白;
     */
    public static String scale(String srcImageFile, int width, int height, boolean bb) {
        try {
            double ratio = 0.0; // 缩放比例
            File f = new File(SystemConfig.REAL_PATH + srcImageFile);
            String result = getSmallImagePath(f);
            BufferedImage bi = ImageIO.read(f);
            
            width = width == 0 ? bi.getWidth() : width;
            height = height == 0 ? bi.getHeight() : height;
            
            Image itemp = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            // 计算比例
            if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
                if (bi.getHeight() > bi.getWidth()) {
                    ratio = (new Integer(height)).doubleValue() / bi.getHeight();
                } else {
                    ratio = (new Integer(width)).doubleValue() / bi.getWidth();
                }
                AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
                itemp = op.filter(bi, null);
            }
            if (bb) {//补白
            	BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image.createGraphics();
                g.setColor(Color.white);
                g.fillRect(0, 0, width, height);
                if (width == itemp.getWidth(null))
                	g.drawImage(itemp, 0, 0,itemp.getWidth(null), itemp.getHeight(null),Color.white, null);
                else
                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,itemp.getWidth(null), itemp.getHeight(null),Color.white, null);
                
                g.dispose();
                itemp = image;
            }
            //保存使用绝对路径
            ImageIO.write((BufferedImage) itemp, IMAGE_TYPE_PNG, new File(result));
            //显示需要相对路径
            result = "/"+ result.replaceFirst(SystemConfig.REAL_PATH, "");
            return result;
        } catch (IOException e) {
            e.printStackTrace();	
        }
        return null;
    }
}