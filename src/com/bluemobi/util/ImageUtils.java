package com.bluemobi.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.appcore.util.FileUtil;
import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 图片相关操作工具类
 * 
 * @author zhangzheng
 * @date 2015-11-30
 * 
 */
public class ImageUtils {
	
	
	/**
	 * 裁剪并等比缩放图片
	 * 此方法按照最大可视范围将原图片裁剪为需要的尺寸（裁宽或者裁高），并可将才将后的图片等比缩放
	 * @author haoj 309444359@qq.com
	 * @date 2016-10-21 下午5:10:08
	 * @param oImage 原图
	 * @param w 要裁剪的图宽
	 * @param h 要裁剪的图高
	 * @param image
	 * @throws Exception
	 */
	public static void cutAndReduceImg(String oImage, int w, int h, String image) throws Exception {
		
		//获取原图宽高
    	BufferedImage bufferedImage = ImageIO.read(new File(oImage));
        int ow = bufferedImage.getWidth();
        int oh = bufferedImage.getHeight();

        
    	int cutW = 0;//要裁剪的宽
    	int cutH = 0;//要裁剪的高
    	
    	//原图太宽
    	if(ow/(double)oh>w/(double)h){
    		//(ow - x) / oh = w/h, ow-x=(w/h)*oh, ow - (w/h)*oh = x
    		cutW = (int)(ow - (w/(double)h)*oh);
    	}
    	
    	//原图太高
    	if(ow/(double)oh<w/(double)h){
    		// (oh-x)/ow = h/w, (oh-x) = (h/w)*ow, oh - (h/w)*ow = x
    		cutH = (int)(oh - (h/(double)w)*ow);
    	}
    	
    	int sx1 = cutW/2;
    	int sy1 = cutH/2;
    	
    	int sx2 = ow - cutW/2;
    	int sy2 = oh - cutH/2;
    	// (ow - cutW)  (oh - cutH)
    	ImageUtils.cutImg(oImage, sx1, sy1, sx2, sy2, w, h, image);
		
	}
	
	

    /**
     * 生成缩略图
     * 
     * @auther zhangzheng
     * @date 2015-12-2 下午5:30:31
     * @param image
     *            源图片
     * @param width
     *            图片宽
     * @param height
     *            图片高
     * @param imgUrl
     *            生成图片的硬盘保存路径，如："F:\\img1.jpg"
     * @throws Exception
     */
    public static void reduceImg(String imagePath, int width, int height, String imgUrl) throws Exception {
//        File imageFile = new File(imagePath);
//        Image image = ImageIO.read(imageFile);
//        Integer canvasWidth = image.getWidth(null);
//        Integer canvasHeight = image.getHeight(null);
        reduceImage(imagePath, 0, 0, width, height, null, width, height, BufferedImage.TYPE_INT_RGB, imgUrl);
    }

    /**
     * 
     * @auther zhangzheng
     * @date 2015-12-3 下午5:35:56
     * @param imagePath
     *            源图片路径
     * @param sx1
     *            裁剪区域图片的起点x坐标
     * @param sy1
     *            裁剪区域图片的起点y坐标
     * @param sx2
     *            裁剪区域图片的终点x坐标
     * @param sy2
     *            裁剪区域图片的终点y坐标
     * @param canvasWidth
     *            绘制画布的宽
     * @param canvasHeight
     *            绘制画布的高
     * @param imgUrl
     *            缩略图保存路径
     * @throws Exception
     */
    public static void cutImg(String imagePath, int sx1, int sy1, int sx2, int sy2, int canvasWidth, int canvasHeight, String imgUrl) throws Exception {
        cutImage(imagePath, 0, 0, canvasWidth, canvasHeight, sx1, sy1, sx2, sy2, null, canvasWidth, canvasHeight, BufferedImage.TYPE_INT_RGB, imgUrl);
    }

    /**
     * 
     * @auther zhangzheng
     * @date 2015-12-3 下午5:37:49
     * @param imagePath
     *            源图片路径
     * @param imgIconPath
     *            水印图片路径
     * @param x
     *            水印绘制点x坐标
     * @param y
     *            水印绘制点y坐标
     * @param width
     *            水印绘制宽
     * @param height
     *            水印绘制高
     * @param imgUrl
     *            结果图保存路径
     * @throws ImageFormatException
     * @throws IOException
     */
    public static void addImageWatermark(String imagePath, String imgIconPath, Integer x, Integer y, Integer width, Integer height, String imgUrl) throws ImageFormatException,
            IOException {
        addWatermark(imagePath, imgIconPath, x, y, width, height, null, null, null, null, null, null, null, null, imgUrl);
    }

    /**
     * 
     * @auther zhangzheng
     * @date 2015-12-3 下午5:38:28
     * @param imagePath
     *            源图片路径
     * @param fontSize
     *            字体大小，如：20
     * @param fontContent
     *            文字水印内容
     * @param fontX
     *            文字水印起点x坐标
     * @param fontY
     *            文字水印起点y坐标
     * @param imgUrl
     *            结果图保存路径
     * @throws ImageFormatException
     * @throws IOException
     */
    public static void addStringWatermark(String imagePath, Integer fontSize, String fontContent, Integer fontX, Integer fontY, String imgUrl) throws ImageFormatException,
            IOException {
        addWatermark(imagePath, null, null, null, null, null, null, Color.BLACK, "宋体", Font.BOLD, fontSize, fontContent, fontX, fontY, imgUrl);
    }

    /**
     * 生成缩略图
     * 
     * @auther zhangzheng
     * @date 2015-12-2 下午2:22:51
     * @param imagePath
     *            源图片路径
     * @param x
     *            起始绘制点x坐标
     * @param y
     *            起始绘制点y坐标
     * @param width
     *            缩略图宽
     * @param height
     *            缩略图高
     * @param observer
     *            用于在构造 Image 时，接收有关 Image 信息通知的异步更新接口，可以为null
     * @param canvasWidth
     *            绘制画布的宽
     * @param canvasHeight
     *            绘制画布的高
     * @param bufferedImage
     *            图像缓冲区，传入BufferedImage，如：BufferedImage.TYPE_INT_RGB
     * @param imgUrl
     *            缩略图保存路径
     * @throws Exception
     */
    private static void reduceImage(String imagePath, int x, int y, int width, int height, ImageObserver observer, int canvasWidth, int canvasHeight, int bufferedImage,
            String imgUrl) throws Exception {
        File imageFile = new File(imagePath);
        Image image = ImageIO.read(imageFile);
        // 设置画布大小
        BufferedImage tag = new BufferedImage(canvasWidth, canvasHeight, bufferedImage);
        // 在画布上绘制缩小后的图
        tag.getGraphics().drawImage(image, x, y, width, height, observer);
        // 输出到文件流
        FileOutputStream out = new FileOutputStream(imgUrl);
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        // 近JPEG编码
        encoder.encode(tag);
        out.close();
    }

    /**
     * 裁剪图片
     * 
     * @auther zhangzheng
     * @date 2015-12-2 下午2:33:45
     * @param imagePath
     *            源图片路径
     * @param dx1
     *            绘制结果图片的起点x坐标
     * @param dy1
     *            绘制结果图片的起点y坐标
     * @param dx2
     *            绘制结果图片的终点x坐标
     * @param dy2
     *            绘制结果图片的终点y坐标
     * @param sx1
     *            裁剪区域图片的起点x坐标
     * @param sy1
     *            裁剪区域图片的起点y坐标
     * @param sx2
     *            裁剪区域图片的终点x坐标
     * @param sy2
     *            裁剪区域图片的终点y坐标
     * @param observer
     *            用于在构造 Image 时，接收有关 Image 信息通知的异步更新接口，可以为null
     * @param canvasWidth
     *            绘制画布的宽
     * @param canvasHeight
     *            绘制画布的高
     * @param bufferedImage
     *            图像缓冲区，传入BufferedImage，如：BufferedImage.TYPE_INT_RGB
     * @param imgUrl
     *            缩略图保存路径
     * @throws Exception
     */
    private static void cutImage(String imagePath, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer, int canvasWidth,
            int canvasHeight, int bufferedImage, String imgUrl) throws Exception {
        File imageFile = new File(imagePath);
        Image image = ImageIO.read(imageFile);
        // 设置画布大小
        BufferedImage tag = new BufferedImage(canvasWidth, canvasHeight, bufferedImage);
        // 裁剪图片
        tag.getGraphics().drawImage(image, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
        // 检验文件夹是否存在，不存在 就创建
        FileUtil.makeDir(imgUrl.substring(0, imgUrl.lastIndexOf("/")));
        // 输出到文件流
        FileOutputStream out = new FileOutputStream(imgUrl);
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        // 近JPEG编码
        encoder.encode(tag);
        out.close();
    }

    /**
     * 添加水印，可添加图片和文字
     * 
     * @auther zhangzheng
     * @date 2015-12-2 下午4:03:28
     * @param imagePath
     *            源图片路径
     * @param imgIconPath
     *            水印图片路径
     * @param x
     *            水印绘制点x坐标
     * @param y
     *            水印绘制点y坐标
     * @param width
     *            水印绘制宽
     * @param height
     *            水印绘制高
     * @param observer
     *            用于在构造 Image 时，接收有关 Image 信息通知的异步更新接口，可以为null
     * @param fontColor
     *            字体颜色，如：Color.BLACK
     * @param font
     *            字体，如："黑体"
     * @param style
     *            字体风格，如：Font.BOLD
     * @param fontSize
     *            字体大小，如：20
     * @param fontContent
     *            文字水印内容
     * @param fontX
     *            文字水印起点x坐标
     * @param fontY
     *            文字水印起点y坐标
     * @param imgUrl
     *            结果图保存路径
     * @throws ImageFormatException
     * @throws IOException
     */
    private static void addWatermark(String imagePath, String imgIconPath, Integer x, Integer y, Integer width, Integer height, ImageObserver observer, Color fontColor,
            String font, Integer style, Integer fontSize, String fontContent, Integer fontX, Integer fontY, String imgUrl) throws ImageFormatException, IOException {
        File imageFile = new File(imagePath);
        BufferedImage image = ImageIO.read(imageFile);
        File iconFile = new File(imgIconPath);
        Image imgIcon = ImageIO.read(iconFile);
        // 得到画笔对象
        Graphics g = image.getGraphics();
        if (imgIcon != null) {
            // 在大图上绘制水印图片
            g.drawImage(imgIcon, x, y, width, height, observer);
        }
        if (fontContent != null && !"".equals(fontContent)) {
            // 设置字体
            g.setColor(fontColor);
            Font f = new Font(font, style, fontSize);
            g.setFont(f);
            // 绘制字体水印
            g.drawString(fontContent, fontX, fontY);
        }
        g.dispose();
        OutputStream os = new FileOutputStream(imgUrl);
        // 创键编码器，用于编码内存中的图象数据。
        JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
        en.encode(image);
        os.close();
    }
    
    
    public static void main(String[] args) throws Exception {
    	ImageUtils.cutAndReduceImg("d:/a.jpg", 900, 400, "d:/a_cut.jpg");
	}

}
