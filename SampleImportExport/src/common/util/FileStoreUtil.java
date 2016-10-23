package common.util;

import java.io.File;

public class FileStoreUtil {
	
	private static final String Excel = "Excel";
	
	public FileStoreUtil() {
		/* super(); */
		/* return; */
	}
	//获取外景图片路径
	public static String getSamplePic(boolean isFtp,String customerId, boolean isFtpUp,boolean isDownLocalPath) {
		if(isFtp){
			String ftpPath="";
			if(isFtpUp){
				ftpPath = "/"+customerId+"/";
			}else{
				ftpPath = customerId+"/";
			}
			return ftpPath;
		}
		return null;
	}
	
	
	//获取excel路径
	public static String getimportExcelPath(String userName) {
		return (new StringBuilder(String.valueOf(((Object) (CommonUtil
				.getWarHome()))))).append("uploadTemp").append(File.separator).append(userName).append(Excel).append(File.separator).toString();
	}


}