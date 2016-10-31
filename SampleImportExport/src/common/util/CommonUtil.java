package common.util;

import common.SIEBeanFactory;				
import common.SIEContext;
import dataaccess.DataAccessApi;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.*;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class CommonUtil {
	private static Logger logger = null;

	public static List convertStrArray2List(String strArray[]) {
		List list = ((List) (new Vector()));
		for (int i = 0; i < strArray.length; i++)
			list.add(((Object) (strArray[i])));
		return list;
	}

	public static List convertIdsStr2StringList(String strsStr, String seperator) {
		List strList = new ArrayList();
		String strArray[] = strsStr.split(seperator);
		strList.add(strArray);
		return strList;
	}

	public static List convertIdsStr2LongList(String idsStr, String seperator) {
		List idList = ((List) (new Vector()));
		if (idsStr != null && seperator != null) {
			String idArray[] = idsStr.split(seperator);
			for (int i = 0; i < idArray.length; i++) {
				String id = "0";
				try {
					id = idArray[i];
				} catch (Exception exception) {
				}
				if (!"0".equals(id))
					idList.add(idArray[i].trim());
			}
		}
		Collections.sort(idList);
		return idList;
	}

	public static List convertStr2IntList(String idsStr, String seperator) {
		List idList = ((List) (new Vector()));
		if (idsStr != null && seperator != null) {
			String idArray[] = idsStr.split(seperator);
			for (int i = 0; i < idArray.length; i++)
				idList
						.add(((Object) (Integer
								.valueOf(getIntValue(idArray[i])))));
		}
		Collections.sort(idList);
		return idList;
	}

	public static String getWarHome() {
		return SIEContext.getServletContext().getRealPath("/");
	}
	
	public static void main(String args[]){
		String url = getWarHome();
		System.out.println(url);
	}

	public static String buildActionId(String handlerName, String methodName) {
		return (new StringBuilder(String.valueOf(((Object) (handlerName)))))
				.append(".").append(methodName).toString();
	}

	public static String convertIntList2Str(List intValues, String seperator) {
		StringBuffer sb = new StringBuffer();
		int size = intValues.size();
		for (int i = 0; i < size - 2; i++)
			sb.append(intValues.get(i)).append(seperator);
		sb.append(intValues.get(size - 1));
		return sb.toString();
	}

	public static boolean isEmptyStr(String s) {
		return s == null || s.trim().length() == 0;
	}

	public static boolean isNotEmptyStr(String s) {
		return !isEmptyStr(s);
	}

	public static List convertSnsStr2StringList(String snsStr, String seperator) {
		List snList = ((List) (new Vector()));
		if (snsStr != null && seperator != null) {
			String snArray[] = snsStr.split(seperator);
			for (int i = 0; i < snArray.length; i++)
				snList.add(((Object) (snArray[i])));
		}
		return snList;
	}

	public static List convertSnsStr2StringContaintsNullList(String snsStr,
			String seperator) {
		List snList = ((List) (new Vector()));
		if (snsStr != null && seperator != null) {
			// String snArray[] = snsStr.split(seperator);
			String temStr = snsStr;
			while (temStr.indexOf(seperator) != -1) {
				String ary = temStr.substring(0, temStr.indexOf(seperator) + 1);
				temStr = temStr.substring(temStr.indexOf(seperator) + 1, temStr
						.length());
				snList.add(new String(ary.replace(seperator, "")));
			}
		}
		return snList;
	}

	public static String convertStringList2Str(List stringList, String seperator) {
		StringBuffer sb = new StringBuffer();
		int size = stringList.size();
		for (int i = 0; i < size - 2; i++)
			sb.append((String) stringList.get(i)).append(seperator);

		sb.append((String) stringList.get(size - 1));
		return sb.toString();
	}

	public static String getNlenNumberStr(int value, int n) {
		if (value < 0 || n <= 0)
			return null;
		String strValue = (new StringBuilder()).append(value).toString();
		if (strValue.length() >= n)
			return strValue.substring(0, n);
		String zeros = "";
		for (int i = 0; i < n - strValue.length(); i++)
			zeros = (new StringBuilder(String.valueOf(((Object) (zeros)))))
					.append("0").toString();
		return (new StringBuilder(String.valueOf(((Object) (zeros))))).append(
				strValue).toString();
	}

	public static String getNoLessThanNlenStr(String s, int n) {
		if (s == null || n < 0)
			return null;
		if (s.length() >= n)
			return s;
		String zeros = "";
		for (int i = 0; i < n - s.length(); i++)
			zeros = (new StringBuilder(String.valueOf(((Object) (zeros)))))
					.append("0").toString();
		return (new StringBuilder(String.valueOf(((Object) (zeros)))))
				.append(s).toString();
	}

	public static String linkSnAndName(String sn, String name) {
		return (new StringBuilder(String.valueOf(((Object) (sn)))))
				.append("=>").append(name).toString();
	}

	public static float getFormatFloat(float value) {
		return Float.parseFloat((new DecimalFormat("0.00")).format(value));
	}

	public static String getFileSuffix(String fileName) {
		String s = "";
		if (isEmptyStr(fileName))
			return s;
		int lastPoint = fileName.lastIndexOf(".");
		if (lastPoint >= 0)
			s = fileName.substring(lastPoint + 1);
		logger.info(((Object) ((new StringBuilder("FileName="))
				.append(fileName).append(", suffix=").append(s).toString())));
		return s;
	}

	public static boolean strEqual(String str1, String str2) {
		boolean flag = false;
		if (isEmptyStr(str1) && isEmptyStr(str2)) {
			flag = true;
		} else {
			if (isEmptyStr(str1) && !isEmptyStr(str2))
				return false;
			if (!isEmptyStr(str1) && isEmptyStr(str2))
				return false;
			flag = str1.equalsIgnoreCase(str2);
		}
		return flag;
	}

	public static boolean strNotEqual(String str1, String str2) {
		return !strEqual(str1, str2);
	}

	public static String getRightNLenStr(String sn, int n) {
		if (sn == null || n <= 0)
			return null;
		if (sn.length() >= n)
			return sn.substring(sn.length() - n, sn.length());
		String zeros = "";
		for (int i = 0; i < n - sn.length(); i++)
			zeros = (new StringBuilder(String.valueOf(((Object) (zeros)))))
					.append("0").toString();
		return (new StringBuilder(String.valueOf(((Object) (zeros))))).append(
				sn).toString();
	}

	public static String getListId(List IDs) {
		String str = "";
		int i;
		for (i = 0; i < IDs.size() - 1; i++) {
			Long ID = (Long) IDs.get(i);
			str = (new StringBuilder(String.valueOf(((Object) (str))))).append(
					ID.longValue()).append(",").toString();
		}
		str = (new StringBuilder(String.valueOf(((Object) (str))))).append(
				((Long) IDs.get(i)).longValue()).toString();
		return str;
	}

	public static String getUserName(HttpServletRequest request) {
		return (String) request.getSession().getAttribute("userName");
	}


	public static Logger getLogger() {
		if (logger == null) {
			//PropertyConfigurator.configure("D:\\log4j.properties");
			PropertyConfigurator.configureAndWatch("log4j.properties");
			logger = Logger.getLogger("");
		}
		return logger;
	}

	public static boolean isLocalIp(String ip) {
		boolean flag = false;
		if (isEmptyStr(ip))
			return flag;
		if (ip.equalsIgnoreCase("localhsot")
				|| ip.equalsIgnoreCase("127.0.0.1"))
			flag = true;
		return false;
	}

	public static String getfenbuTrack(String departmentTrack) {
		if (isEmptyStr(departmentTrack))
			return "";
		String idStrs[] = departmentTrack.split(",");
		if (idStrs.length <= 1)
			return "";
		else
			return (new StringBuilder(String.valueOf(((Object) (idStrs[0])))))
					.append(",").append(idStrs[1]).append(",").toString();
	}

	public static int getIntValue(String s) {
		int v = 0;
		try {
			v = (new Integer(s)).intValue();
		} catch (Exception exception) {
		}
		return v;
	}

	public static long getLongValue(String s) {
		long v = 0L;
		try {
			v = (new Integer(s)).intValue();
		} catch (Exception exception) {
		}
		return v;
	}

	public static String getDateParam(String time, int param) {
		int index0 = 0;
		int index1 = time.indexOf("-", index0);
		int index2 = time.indexOf("-", index1 + 1);
		int index3 = time.indexOf(" ", index2 + 1);
		int index4 = time.indexOf(":", index3 + 1);
		int index5 = time.indexOf(":", index4 + 1);
		if (param == 0)
			return time.substring(0, index1);
		if (param == 1)
			return time.substring(index1 + 1, index2);
		if (param == 2)
			return time.substring(index2 + 1, index3);
		if (param == 3)
			return time.substring(index3 + 1, index4);
		if (param == 4)
			return time.substring(index4 + 1, index5);
		if (param == 5)
			return time.substring(index5 + 1);
		else
			return "";
	}

	public static String getGbEncode(String str) {
		try {
			if (str == null)
				return null;
			return new String(str.getBytes("iso8859_1"), "gb2312");
		} catch (UnsupportedEncodingException unsupportedencodingexception) {
			return str;
		}
	}
	
	public static String getUtfEncode(String str) {
		try {
			if (str == null)
				return null;
			return new String(str.getBytes("utf-8"), "gb2312");
		} catch (UnsupportedEncodingException unsupportedencodingexception) {
			return str;
		}
	}

	public static String getISOEncode(String str) {
		try {
			if (str == null)
				return null;
			return new String(str.getBytes("gb2312"), "iso8859_1");
		} catch (UnsupportedEncodingException unsupportedencodingexception) {
			return str;
		}
	}

	public static String buildTrackWithIdStr(String deptId1, String deptId2) {
		return (new StringBuilder(String.valueOf(((Object) (deptId1)))))
				.append(",").append(deptId2).append(",").toString();
	}

	public static String getFolderFromFile(String filePath) {
		String folderPath = null;
		if (isNotEmptyStr(filePath)) {
			File file = new File(filePath);
			folderPath = file.getParent();
		}
		return folderPath;
	}

	public static String getFileNameFromHttpUrl(String httpUrl) {
		String fileName = null;
		if (isNotEmptyStr(httpUrl) && httpUrl.indexOf("/") != -1) {
			int index = httpUrl.lastIndexOf("/");
			fileName = httpUrl.substring(index + 1);
		}
		return fileName;
	}
	
	public static String getUTFtoISOEncode(String str){
		try {
			if (str == null)
				return null;
			return new String(str.getBytes("iso8859_1"), "UTF-8");
		} catch (UnsupportedEncodingException unsupportedencodingexception) {
			return str;
		}
	}
	
	public static  String renameFileName(String newName, String fileName) {
		String suffix = CommonUtil.getFileSuffix(fileName);
		String newFileName = null;
		newFileName = newName+(".")+suffix;
		return newFileName;
	}
}