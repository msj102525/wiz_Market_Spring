package com.jyes.www.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

/**
 * String Utility Class
 * 
 * @author ZeroOne
 * @version 1.0, 2009.06.25
 * 
 */

public class ImageUtil {

	protected ImageUtil() {

	}

	public static String getBase64String(String imgUrl) throws Exception {
		String fileExtension = imgUrl.substring(imgUrl.lastIndexOf(".") + 1);
		InputStream is = null;
		try {
			URL url = new URL(imgUrl);
			is = url.openStream();
			byte[] imageData = IOUtils.toByteArray(is);
			imgUrl = getImageData(imageData, fileExtension);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				is.close();
			}
		}
		return imgUrl;
	}

	public static String getImageData(byte[] imageData, String fileExtName) {
		String data = "";
		String ecodeStr = new String(Base64.encodeBase64(imageData));
		data = "data:image/" + fileExtName + ";base64," + ecodeStr;
		return data;
	}
	
	public static final int RATIO = 0;
	public static final int SAME = -1;

	public static byte[] resize(byte[] src, int width, int height) throws IOException {
		byte[] returnValue = null;
		Image srcImg = null;
		InputStream in = new ByteArrayInputStream(src);
		srcImg = ImageIO.read(in);

		int srcWidth = srcImg.getWidth(null);
		int srcHeight = srcImg.getHeight(null);

		//추가
		long L = src.length;
		int LIMIT_SIZE = 500;
		if (width == SAME) {
			System.out.println((L/1024) + " KB ");
			if((L/1024)>LIMIT_SIZE) {//App 요청사항 500KB 이하로 이미지 용량 지정 요청
				double ratio = ((double) srcWidth * (1 - 10 / 100f)) / ((double) srcWidth);
				srcWidth = (int)(srcWidth * (1 - 10 / 100f));
				srcHeight = (int) ((double) srcHeight * ratio);
			}
		}

		int destWidth = -1, destHeight = -1;

		if (width == SAME) {
			destWidth = srcWidth;
		} else if (width > 0) {
			destWidth = width;
		}

		if (height == SAME) {
			destHeight = srcHeight;
		} else if (height > 0) {
			destHeight = height;
		}

		Image imgTarget = srcImg.getScaledInstance(destWidth, destHeight, Image.SCALE_SMOOTH);
		int pixels[] = new int[destWidth * destHeight];
		PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, destWidth, destHeight, pixels, 0, destWidth);
		try {
			pg.grabPixels();
		} catch (InterruptedException e) {
			throw new IOException(e.getMessage());
		}
		BufferedImage destImg = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
		destImg.setRGB(0, 0, destWidth, destHeight, pixels, 0, destWidth);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		ImageIO.write(destImg, "png", baos);
		
		returnValue = baos.toByteArray();
		
		L = baos.size();
		if((L/1024)>LIMIT_SIZE) {
			//재귀
			returnValue = resize(baos.toByteArray(), -1, -1);
		}
		
		//완료
		return returnValue;
		
	}
	
	public static byte[] crop(byte[] src, int x, int y, int cropWidth, int cropHeight) throws IOException {
		byte[] returnValue = null;
		BufferedImage srcImg = null;
		InputStream in = new ByteArrayInputStream(src);
		srcImg = ImageIO.read(in);
				
		BufferedImage out = srcImg.getSubimage(x,y,cropWidth,cropHeight);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		ImageIO.write(out, "png", baos);
		
		returnValue = baos.toByteArray();
		
		//완료
		return returnValue;
		
	}

}