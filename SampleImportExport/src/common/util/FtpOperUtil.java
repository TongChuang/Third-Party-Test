package common.util;	

import java.io.*;	
import java.util.List;
import java.util.Vector;
import org.apache.commons.net.SocketClient;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FtpOperUtil {
	private static FTPClient ftpClient = new FTPClient();

	public static void uploadFile(File file, String ftpRoot,
			String ftpRelatedFolderPath) throws IOException {
		connectServer(ftpRoot);
		mkdir2(getIsoCodeStr(ftpRelatedFolderPath));
		uploadFile2(file, getIsoCodeStr(ftpRelatedFolderPath));
		closeServer();
	}

	public static void downloadFile(String localFilePath, String ftpRoot,
			String ftpRelatedFilePath) throws IOException {
		connectServer(ftpRoot);
		mkLocalDirs(localFilePath);
		downloadFile2(localFilePath, getIsoCodeStr(ftpRelatedFilePath));
		closeServer();
	}

	public static boolean downloadFile2(String localFilePath, String ftpRoot,
			String ftpRelatedFilePath) throws IOException {
		boolean result = false;
		connectServer(ftpRoot);
		mkLocalDirs(localFilePath);
		result = downloadFile3(localFilePath, getIsoCodeStr(ftpRelatedFilePath));
		closeServer();
		return result;
	}

	public static byte[] getFileBytes(String ftpRoot, String ftpRelatedFilePath) throws IOException{
		connectServer(ftpRoot);
		ftpClient.setControlEncoding("GBK");
		ByteArrayOutputStream baos = null;
			FTPFile[] ff = ftpClient
					.listFiles(getIsoCodeStr(getIsoCodeStr(ftpRelatedFilePath)));
			if (ff.length == 1) {
				baos = new ByteArrayOutputStream(Integer
						.parseInt(String.valueOf(ff[0].getSize())));
				ftpClient.retrieveFile(getIsoCodeStr(ftpRelatedFilePath), baos);
			}
			baos.close();
			closeServer();
			return baos.toByteArray();
	}

	/**
	 * 新添的moveFile方法用于从FTP上移动文件（在不同的目录间移动）
	 * 
	 * @param ftpRoot
	 *            ：ftp的IP和用户名及密码
	 * @param fromRoot
	 *            ：文件所在路径
	 * @param toRoot
	 *            ：文件目标路径
	 * @throws IOException
	 */
	public static void moveFile(String ftpRoot, String fromRoot, String toRoot,
			String fileRoot) throws IOException {
		System.out.println("ftpRoot--->" + ftpRoot);
		System.out.println("fromRoot--->" + fromRoot);
		System.out.println("toRoot--->" + toRoot);
		System.out.println("fileRoot--->" + fileRoot);
		connectServer(ftpRoot);
		mkdir2(getIsoCodeStr(toRoot));
		ftpClient.rename(fromRoot, fileRoot);
		closeServer();
	}

	private static void mkLocalDirs(String localFilePath) {
		String folderPath = CommonUtil.getFolderFromFile(localFilePath);
		(new File(folderPath)).mkdirs();
	}

	public static void mkdir(String ftpRoot, String ftpRelatedPath)
			throws IOException {
		connectServer(ftpRoot);
		mkdir2(getIsoCodeStr(ftpRelatedPath));
		closeServer();
	}

	private static void connectServer(String ftpRoot) throws IOException {
		List params = getConnParams(ftpRoot);
		((SocketClient) (ftpClient)).connect((String) params.get(2),
				(new Integer((String) params.get(3))).intValue());
		ftpClient.login((String) params.get(0), (String) params.get(1));
		ftpClient.setFileType(2);
	}
	
	public static List getConnParams(String ftpRoot) {
		String a = ftpRoot.substring(6);
		String userName;
		String password;
		String ip;
		String port;
		if (ftpRoot.indexOf("@") != -1) {
			String bs[] = a.split(":");
			userName = bs[0];
			String cs[] = bs[1].split("@");
			password = cs[0];
			ip = cs[1];
			if (bs.length == 3)
				port = bs[2];
			else
				port = "21";
		} else {
			String bs[] = a.split(":");
			userName = "";
			password = "";
			ip = bs[0];
			if (bs.length == 2)
				port = bs[1];
			else
				port = "21";
		}
		List params = ((List) (new Vector()));
		params.add(((Object) (userName)));
		params.add(((Object) (password)));
		params.add(((Object) (ip)));
		params.add(((Object) (port)));
		return params;
	}

	private static void closeServer() {
		try {
			ftpClient.logout();
			ftpClient.disconnect();
		} catch (IOException e) {
			System.out.println("关闭服务器出错！");
			e.printStackTrace();
		}
	}

	private static void uploadFile2(File file, String ftpFolderPath)
			throws IOException {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			String tempName = (new StringBuilder(String
					.valueOf(((Object) (ftpFolderPath))))).append("/").append(
					file.getName()).toString();

			boolean f = ftpClient.storeFile(tempName,
					((java.io.InputStream) (fis)));
			fis.close();
			fis = null;
			if (!f)
				throw new IOException((new StringBuilder("上传文件\"")).append(
						file.getPath()).append("\"").append("失败!请检查ftp目录是否存在。")
						.toString());
		} catch (IOException e) {
			if (fis != null)
				try {
					fis.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			throw e;
		}
	}

	private static void downloadFile2(String localFilePath, String sqlFilePath)
			throws IOException {

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(localFilePath));
			ftpClient.retrieveFile(sqlFilePath, ((java.io.OutputStream) (fos)));
			if (fos != null)
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		} catch (IOException e) {
			throw e;
		}
		return;
	}

	private static boolean downloadFile3(String localFilePath,
			String sqlFilePath) throws IOException {
		boolean result = false;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(localFilePath));
			result = ftpClient.retrieveFile(sqlFilePath,
					((java.io.OutputStream) (fos)));
		} catch (IOException e) {
			throw e;
		} finally {
			if (fos != null)
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			return result;
		}
	}

	private static void mkdir2(String ftpPath) throws IOException {
		String cwd = ftpClient.printWorkingDirectory();
		System.out.println("cwd------>" + cwd);
		String dirs[] = ftpPath.split("/");
		StringBuffer tempDir = new StringBuffer("");
		for (int i = 0; i < dirs.length; i++) {
			tempDir.append((new StringBuilder(String
					.valueOf(((Object) (dirs[i]))))).append("/").toString());
			ftpClient.makeDirectory(getIsoCodeStr(tempDir.toString()));
		}
	}

	private static String getIsoCodeStr(String ftpFolderPath)
			throws UnsupportedEncodingException {
		return new String(ftpFolderPath.getBytes("gb2312"), "ISO-8859-1");
	}

	public static void main(String args[]) {
		 try {
		 byte [] b = getFileBytes("ftp://test:test@127.0.0.1", "test.jpg");
		 System.out.println(b.length+"---"+"ftp://test:test@127.0.0.1".length());
		 String s = "ftp://test:test@127.0.0.1/a.jpg";
		 String k =s.substring(0, 32);
		 String kk = s.substring(33);
		 System.out.println(k+"---"+kk);
		 }
		 catch (IOException e) {
		 e.printStackTrace();
		 }
		// try {
		// moveFile("ftp://fxc5q:fxc5q@10.118.141.217",
		// " /01/Photo/20091102/0911021140502010188757_1.JPG"
		// ,"/01/official/Photo/20091102"
		// ,"/01/official/Photo/20091102/0911021140502010188757_1.JPG");
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// try {
		// mkdir("ftp://test:test@127.0.0.1:21", "a");
		// }
		// catch (IOException e) {
		// System.out.println("mkdir('a') failed");
		// }
		// try {
		// downloadFile("c:/1", ((String) (null)),
		// "01/Photo/20080616/0808211926202000002410_1.jpg");
		// }
		// catch (IOException e) {
		// System.out.println("downloadFile() failed");
		// }
	}

	public static String parseFtpRootFromFtpPath(String url) {
		String a[] = url.split("//");
		String b[] = a[1].split("/");
		return (new StringBuilder("ftp://")).append(b[0]).toString();
	}
}