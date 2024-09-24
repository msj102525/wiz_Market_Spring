package com.jyes.www.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

/**
 * String Utility Class
 * 
 * @author ZeroOne
 * @version 1.0, 2009.06.25
 * 
 */

public class FileUtil {

	protected FileUtil() {

	}
	
	public static boolean saveImgFile(String dir_path, String file_path, byte[] byte_file) {
		boolean returnValue = true;
		try {
			File storageDir = new File(dir_path);
			if (!storageDir.exists()) {
				storageDir.mkdirs();
			}
			FileOutputStream stream = new FileOutputStream(file_path);
			stream.write(byte_file);
			stream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnValue = false;
		}
		return returnValue;
	}
	
	/*public static byte[] getFileByte(MultipartFile multipartFile) {
		String fileName = multipartFile.getOriginalFilename();
		String fileExtension = fileName.toLowerCase().substring(fileName.lastIndexOf(".") + 1, fileName.length());
		fileExtension = Config.EXTENSION_CONTENTS_IMAGE;//png로 통일
		BufferedImage image = null;
		ByteArrayOutputStream baos = null;
		byte[] file_byte = null;
		try {
			image = ImageIO.read(multipartFile.getInputStream());
			baos = new ByteArrayOutputStream();
			ImageIO.write(image, fileExtension, baos);
			file_byte = baos.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(baos!=null) {
					baos.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return file_byte;
	}*/
	
	public static File multipartToFile(Object object) throws IllegalStateException, IOException {
		MultipartFile multipartFile = null;
		InputStream inputStream = null;
		File convFile = null;
		if(object instanceof MultipartFile) {
			multipartFile = (MultipartFile)object;
			convFile = new File(multipartFile.getOriginalFilename());
			multipartFile.transferTo(convFile);
		} else if(object instanceof InputStream) {
			inputStream = (InputStream)object;
			/**
			 * 고민중
			 */
		}
		return convFile;
	}
	
	public static byte[] getFileByte(MultipartFile multipartFile) {
		String fileName = multipartFile.getOriginalFilename();
		String fileExtension = fileName.toLowerCase().substring(fileName.lastIndexOf(".") + 1, fileName.length());
//		fileExtension = Config.EXTENSION_CONTENTS_IMAGE;//png로 통일
		BufferedImage image = null;
		ByteArrayOutputStream baos = null;
		byte[] file_byte = null;
		try {
			System.out.println("["+LogUtils.getCurrentTime()+"]"+" "+"name : "+multipartFile.getName());
			System.out.println("["+LogUtils.getCurrentTime()+"]"+" "+"filename : "+multipartFile.getOriginalFilename());
			System.out.println("["+LogUtils.getCurrentTime()+"]"+" "+"size : "+multipartFile.getSize());
			System.out.println("["+LogUtils.getCurrentTime()+"]"+" "+"파일 변환:"+multipartFile.getOriginalFilename());
			image = ImageIO.read(multipartFile.getInputStream());
			baos = new ByteArrayOutputStream();
			ImageIO.write(image, fileExtension, baos);
			file_byte = baos.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("["+LogUtils.getCurrentTime()+"]"+" "+"파일 변환 실패 재시도");
			try {
				image = CMYKConverter.readImage(multipartToFile(multipartFile));
				baos = new ByteArrayOutputStream();
				ImageIO.write(image, fileExtension, baos);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("["+LogUtils.getCurrentTime()+"]"+" "+"파일 변환 재시도 실패");
			}
			file_byte = baos.toByteArray();
		} finally {
			try {
				if(baos!=null) {
					baos.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return file_byte;
	}
	
	public static byte[] getFileByte(URL url) {
		String fileExtension = url.toString().toLowerCase().substring(url.toString().lastIndexOf(".") + 1, url.toString().length());
//		fileExtension = Config.EXTENSION_CONTENTS_IMAGE;//png로 통일
		BufferedImage image = null;
		ByteArrayOutputStream baos = null;
		byte[] file_byte = null;
		try {
			image = ImageIO.read(url);
			baos = new ByteArrayOutputStream();
			ImageIO.write(image, fileExtension, baos);
			file_byte = baos.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(baos!=null) {
					baos.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return file_byte;
	}
	
	public static void copy(File sourceF, File targetF) {
		File[] target_file = sourceF.listFiles();
		if (!targetF.exists()) {
			targetF.mkdirs();
		}
		for (File file : target_file) {
			File temp = new File(targetF.getAbsolutePath() + File.separator + file.getName());
			System.out.println(file.getName());
			if (file.isDirectory()) {
				temp.mkdir();
				copy(file, temp);
			} else {
				FileInputStream fis = null;
				FileOutputStream fos = null;
				try {
					fis = new FileInputStream(file);
					fos = new FileOutputStream(temp);
					byte[] b = new byte[4096];
					int cnt = 0;
					while ((cnt = fis.read(b)) != -1) {
						fos.write(b, 0, cnt);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						fis.close();
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static void delete(String path) {
		File folder = new File(path);
		try {
			if (folder.exists()) {
				File[] folder_list = folder.listFiles();

				for (int i = 0; i < folder_list.length; i++) {
					if (folder_list[i].isFile()) {
						folder_list[i].delete();
					} else {
						delete(folder_list[i].getPath());
					}
					folder_list[i].delete();
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	public static String generateUniqueFilename(String originalFilename) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid + "_" + originalFilename;
    }
	
}