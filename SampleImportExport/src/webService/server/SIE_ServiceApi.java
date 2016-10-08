/**
 * 
 */
package webService.server;

import common.StartAPI;

/**
 * @author zjn
 * @createTime 2016-9-13
 */
public interface SIE_ServiceApi extends StartAPI {
	/**
	 * 获取检验结果
	 * @param customerid 客户ID
	 * @param customerKey 接口验证KEY
	 * @param testItems 检验项目ID
	 * @param dsfbarcode 条码号
	 * @return
	 */
	String getTestResults(String customerid,String customerKey,String testItems,String dsfbarcode);
	/**
	 * 上传样本信息
	 * @param xmlData 上传样本信息的XML文件，格式参考附件
	 * @param customerid 客户ID
	 * @param customerKey 接口验证KEY
	 * @return
	 */
	String uploadTestItem(String xmlData,String customerid,String customerKey);
	
	/**
	 * 上传基础信息
	 * @param xmlData 上传基础样本信息
	 * @param customerid 客户ID
	 * @param customerKey 接口验证KEY
	 * @return
	 */
	String uploadBaseDataInfo(String xmlData,String customerid,String customerKey);
}
