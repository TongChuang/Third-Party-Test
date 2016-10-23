package common.util;

import java.io.*;
import org.apache.log4j.Logger;

public class FileOperHelper {
	private static Logger logger = CommonUtil.getLogger();

	public FileOperHelper() {
		/* super(); */
		/* return; */
	}

	public static boolean deleteFileAndFolder(String filePath) {
		File file = new File(filePath);
		file.delete();
		logger.info(((Object) ((new StringBuilder("删除文件")).append(file.getName()).toString())));
		File folder = file.getParentFile();
		folder.delete();
		logger.info(((Object) ((new StringBuilder("删除文件夹")).append(folder.getName()).toString())));
		return true;
	}

	public static boolean copyFile(File filefrom, File fileto, boolean rewrite) {
		logger.info(((Object) ((new StringBuilder("把文件从")).append(((Object) (filefrom))).append("复制到").append(((Object) (fileto))).toString())));
		if (!filefrom.exists()) {
			logger.info("文件不存在");
			return false;
		}
		if (!filefrom.isFile()) {
			logger.info("不能够复制文件夹");
			return false;
		}
		if (!filefrom.canRead()) {
			logger.info("不能够读取需要复制的文件");
			return false;
		}
		if (!fileto.getParentFile().exists())
			fileto.getParentFile().mkdirs();
		if (fileto.exists() && rewrite)
			fileto.delete();
		try {
			FileInputStream fosfrom = new FileInputStream(filefrom);
			FileOutputStream fosto = new FileOutputStream(fileto);
			byte bt[] = new byte[1024];
			int c;
			while ((c = fosfrom.read(bt)) > 0) 
				fosto.write(bt, 0, c);
			fosfrom.close();
			fosto.close();
		}
		catch (Exception ex) {
			logger.error(((Object) (ex.getMessage())), ((Throwable) (ex)));
			return false;
		}
		return true;
	}

	public static boolean copyFile(String from, String to) {
		File filefrom = new File(from);
		File fileto = new File(to);
		return copyFile(filefrom, fileto, true);
	}

	public static void main(String args[]) {
		deleteFileAndFolder("E:\\a\\b\\c\\d\\e");
	}

	public static void delFolder(String folderPath) {
		logger.info(((Object) ((new StringBuilder("删除文件夹")).append(folderPath).toString())));
		try {
			delAllFile(folderPath);
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			myFilePath.delete();
		}
		catch (Exception e) {
			logger.error("删除文件夹操作出错", ((Throwable) (e)));
		}
	}

	public static boolean delAllFile(String path) {
		boolean bea = false;
		File file = new File(path);
		if (!file.exists())
			return bea;
		if (!file.isDirectory())
			return bea;
		String tempList[] = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator))
				temp = new File((new StringBuilder(String.valueOf(((Object) (path))))).append(tempList[i]).toString());
			else
				temp = new File((new StringBuilder(String.valueOf(((Object) (path))))).append(File.separator).append(tempList[i]).toString());
			if (temp.isFile())
				temp.delete();
			if (temp.isDirectory()) {
				delAllFile((new StringBuilder(String.valueOf(((Object) (path))))).append("/").append(tempList[i]).toString());
				delFolder((new StringBuilder(String.valueOf(((Object) (path))))).append("/").append(tempList[i]).toString());
				bea = true;
			}
		}

		return bea;
	}

	public static void moveFile(String fromFilePath, String toFilePath) {
		File fromFile = new File(fromFilePath);
		File toFile = new File(toFilePath);
		File toFolder = toFile.getParentFile();
		if (!toFolder.exists())
			toFolder.mkdirs();
		fromFile.renameTo(toFile);
		File fromFolder = fromFile.getParentFile();
		fromFolder.delete();
	}

	public static boolean saveFile(String filePath, byte bytes[]) {
		File fileto = new File(filePath);
		if (!fileto.getParentFile().exists())
			fileto.getParentFile().mkdirs();
		if (fileto.exists())
			fileto.delete();
		try {
			FileOutputStream fosto = new FileOutputStream(fileto);
			fosto.write(bytes, 0, bytes.length);
			fosto.close();
			if (bytes==null||bytes.equals("")||(bytes.length<=10240))
				fileto.delete();
		}
		catch (Exception ex) {
			logger.error(((Object) (ex.getMessage())), ((Throwable) (ex)));
			return false;
		}
		return true;
	}
}