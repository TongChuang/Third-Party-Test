package dataaccess.help;

import common.SIEBeanFactory;		
import common.datamodel.*;
import common.exception.DataAccessRuntimeException;

import common.util.CommonUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;


public class DataAccessUtil {
	private static Logger logger = CommonUtil.getLogger();

	public static void addObject(Object obj, String tableName,
			HibernateTemplate hbTemplate) {
		try {
			hbTemplate.save(obj);
		} catch (DataAccessException e) {
			throw new DataAccessRuntimeException((new StringBuilder("add"))
					.append(tableName).toString(), ((Throwable) (e)));
		}
	}

	public static void addObjects(List objects, String tableName,
			HibernateTemplate hbTemplate) {
		saveOrUpdateAll(((Collection) (objects)), tableName, hbTemplate,
				"update");
	}

	public static void updateObject(Object obj, String tableName,
			HibernateTemplate hbTemplate) {
		try {
			hbTemplate.update(obj);
		} catch (DataAccessException e) {
			throw new DataAccessRuntimeException((new StringBuilder(
					"updateObject error, where tableName = "))
					.append(tableName).toString(), ((Throwable) (e)));
		}
	}

	public static void updateObjectAll(Set objs, String tableName,
			HibernateTemplate hbTemplate) {
		saveOrUpdateAll(((Collection) (objs)), tableName, hbTemplate, "update");
	}

	public static void saveOrUpdate(Object obj, String tableName,
			HibernateTemplate hbTemplate) {
		saveOrUpdate(obj, tableName, hbTemplate, "saveOrUpdate");

	}

	private static void saveOrUpdate(Object obj, String tableName,
			HibernateTemplate hbTemplate, String methodName) {
		try {
			hbTemplate.saveOrUpdate(obj);
		} catch (DataAccessException e) {
			throw new DataAccessRuntimeException((new StringBuilder(String
					.valueOf(((Object) (methodName))))).append(tableName)
					.toString(), ((Throwable) (e)));
		}
	}

	public static void saveOrUpdateAll(Collection objs, String tableName,
			HibernateTemplate hbTemplate, String method) {
		try {
			hbTemplate.saveOrUpdateAll(objs);
		} catch (DataAccessException e) {
			throw new DataAccessRuntimeException((new StringBuilder(String
					.valueOf(((Object) (method))))).append(tableName)
					.toString(), ((Throwable) (e)));
		}
	}

	public static List getAllObjects(String tableName,
			HibernateTemplate hbTemplate) {
		try {
			List res = hbTemplate.find((new StringBuilder("from ")).append(
					tableName).toString());
			return res;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return ((List) (new Vector()));
		}
	}

	public static List getAllObjectsWithColumnAsc(String colName,
			boolean isAsc, String tableName, HibernateTemplate hbTemplate) {
		String asc = isAsc ? "asc" : "desc";
		try {
			List res = hbTemplate.find((new StringBuilder("from ")).append(
					tableName).append(" order by ").append(colName).append(" ")
					.append(asc).toString());
			return res;
		} catch (DataAccessException e) {
			return ((List) (new Vector()));
		}
	}

	public static List getAllObjectsWithColumnAscAndBetweenNow(
			String BeginTimecolName, String EndTimecolName, String colName,
			boolean isAsc, String tableName, HibernateTemplate hbTemplate) {
		String asc = isAsc ? "asc" : "desc";
		try {
			String s = "from "+tableName +" where "+colName +" between '"+BeginTimecolName+"' and '"+EndTimecolName+"' order by "+colName+" "+asc;
			List res = hbTemplate.find(s);
			return res;
		} catch (DataAccessException e) {
			return ((List) (new Vector()));
		}
	}



	public static Object getObjectById(long id, String tableName,
			HibernateTemplate hibernateTemplate) {
		List result = ((List) (new Vector()));
		try {
			result = hibernateTemplate.find((new StringBuilder("from "))
					.append(tableName).append(" where id=?").toString(),
					((Object) (id)));
			if (result != null && result.size() > 0)
				return result.iterator().next();
			else
				return ((Object) (null));
		} catch (DataAccessException e) {
			throw new DataAccessRuntimeException((new StringBuilder("get"))
					.append(tableName).append("ById").toString(),
					((Throwable) (e)));
		}
	}
	
	public static Object getObjectById(BigDecimal id, String tableName,
			HibernateTemplate hibernateTemplate) {
		List result = ((List) (new Vector()));
		try {
			result = hibernateTemplate.find((new StringBuilder("from "))
					.append(tableName).append(" where id=?").toString(),
					((Object) (id)));
			if (result != null && result.size() > 0)
				return result.iterator().next();
			else
				return ((Object) (null));
		} catch (DataAccessException e) {
			throw new DataAccessRuntimeException((new StringBuilder("get"))
					.append(tableName).append("ById").toString(),
					((Throwable) (e)));
		}
	}

	public static Object getObjectByName(String name, String colName,
			String tableName, HibernateTemplate hibernateTemplate) {
		List result = ((List) (new Vector()));
		try {
			result = hibernateTemplate.find((new StringBuilder("from "))
					.append(tableName).append(" where ").append(colName)
					.append("=?").toString(), ((Object) (name)));
			if (result != null && result.size() > 0)
				return result.iterator().next();
			else
				return ((Object) (null));
		} catch (DataAccessException e) {
			throw new DataAccessRuntimeException((new StringBuilder("get"))
					.append(tableName).append("ByName").toString(),
					((Throwable) (e)));
		}
	}

	public static Object getObjectByColum(Object value, String colName,
			String tableName, HibernateTemplate hibernateTemplate) {
		List result = ((List) (new Vector()));
		try {
			result = hibernateTemplate.find((new StringBuilder("from "))
					.append(tableName).append(" where ").append(colName)
					.append("=?").toString(), value);
			if (result != null && result.size() > 0)
				return result.iterator().next();
			else
				return ((Object) (null));
		} catch (DataAccessException e) {
			throw new DataAccessRuntimeException((new StringBuilder("get"))
					.append(tableName).append("By").append(colName).toString(),
					((Throwable) (e)));
		}
	}

	public static List getObjectsByColum(Object value, String colName,
			String tableName, HibernateTemplate hibernateTemplate) {
		List result = ((List) (new Vector()));
		try {
			result = hibernateTemplate.find((new StringBuilder("from "))
					.append(tableName).append(" where ").append(colName)
					.append("=?").toString(), value);
			if (result == null)
				result = ((List) (new Vector()));
		} catch (DataAccessException e) {
			throw new DataAccessRuntimeException(
					(new StringBuilder("get")).append(tableName).append("sBy")
							.append(colName).toString(), ((Throwable) (e)));
		}
		return result;
	}

	public static List getObjectsByColumWithIn(List colValues, String colName,
			String tableName, Session session) {
		List l = ((List) (new Vector()));
		if (!colValues.isEmpty()) {
			int n = colValues.size() / 500;
			for (int i = 0; i <= n; i++) {
				String hql = (new StringBuilder("select t from ")).append(
						tableName).append(" t where ").toString();
				hql = (new StringBuilder(String.valueOf(((Object) (hql)))))
						.append(colName).append(" in (:idd) ").toString();
				logger.info(((Object) (hql)));
				Query query = session.createQuery(hql);
				List tempValues = colValues.subList(i * 500, Math.min(
						i * 500 + 500, colValues.size()));
				query.setParameterList("idd", ((Collection) (tempValues)));
				l.addAll(((Collection) (query.list())));
			}
		}
		return l;
	}

	public static List getObjectsByColumWithAsc(Object value, String colName,
			String orderColumnName, boolean isAsc, String tableName,
			HibernateTemplate hibernateTemplate) {
		List result = ((List) (new Vector()));
		String asc = isAsc ? "asc" : "desc";
		try {
			result = hibernateTemplate.find((new StringBuilder("from "))
					.append(tableName).append(" where ").append(colName)
					.append("=?").append(" order by ").append(orderColumnName)
					.append(" ").append(asc).toString(), value);
			if (result == null)
				result = ((List) (new Vector()));
		} catch (DataAccessException e) {
			throw new DataAccessRuntimeException(
					(new StringBuilder("get")).append(tableName).append("sBy")
							.append(colName).toString(), ((Throwable) (e)));
		}
		return result;
	}

	public static Object getObjectByColums(Object values[], String colNames[],
			String tableName, HibernateTemplate hibernateTemplate) {
		List result;
		StringBuffer sb;
		result = ((List) (new Vector()));
		try {
			sb = new StringBuffer();
			for (int i = 0; i < colNames.length; i++)
				if (i == 0)
					sb.append(colNames[i]).append("=?");
				else
					sb.append(" and ").append(colNames[i]).append("=?");

			result = hibernateTemplate.find((new StringBuilder("from "))
					.append(tableName).append(" where ").append(sb.toString())
					.toString(), values);
			if (result != null && result.size() > 0)
				return result.iterator().next();
			else
				return ((Object) (null));
		} catch (DataAccessException e) {
			throw new DataAccessRuntimeException((new StringBuilder("get"))
					.append(tableName).append("By ").append(
							((Object) (colNames))).toString(),
					((Throwable) (e)));
		}
	}

	public static List getObjectsByColums(Object values[], String colNames[],
			String tableName, HibernateTemplate hibernateTemplate) {
		List result = ((List) (new Vector()));
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < colNames.length; i++)
			if (i == 0)
				sb.append(colNames[i]).append("=?");
			else
				sb.append(" and ").append(colNames[i]).append("=?");

		try {
			result = hibernateTemplate.find((new StringBuilder("from "))
					.append(tableName).append(" where ").append(sb.toString())
					.toString(), values);
			if (result == null)
				result = ((List) (new Vector()));
		} catch (DataAccessException e) {
			/*
			 * throw new DataAccessRuntimeException((new StringBuilder("get"))
			 * .append(tableName).append("By ").append( ((Object)
			 * (colNames))).toString(), ((Throwable) (e)));
			 */
			result = ((List) (new Vector()));
			return result;
		}
		return result;
	}

	public static Object getObjectByColumsWithAsc(Object values[],
			String colNames[], String orderColumnNames[], boolean isAsc,
			String tableName, HibernateTemplate hibernateTemplate) {
		Object result = null;
		List list = ((List) (new Vector()));
		String asc = isAsc ? "asc" : "desc";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < colNames.length; i++)
			if (i == 0)
				sb.append(colNames[i]).append("=?");
			else
				sb.append(" and ").append(colNames[i]).append("=?");

		sb.append(" order by ");
		for (int j = 0; j < orderColumnNames.length; j++)
			if (j == 0)
				sb.append(orderColumnNames[j]);
			else
				sb.append((new StringBuilder(",")).append(orderColumnNames[j])
						.toString());

		sb.append((new StringBuilder(" ")).append(asc).toString());
		try {
			list = hibernateTemplate.find((new StringBuilder("from ")).append(
					tableName).append(" where ").append(sb.toString())
					.toString());
			if (list != null && !list.isEmpty())
				result = list.get(0);
		} catch (DataAccessException e) {
			throw new DataAccessRuntimeException("getObjectByColumsWithAsc",
					((Throwable) (e)));
		}
		return result;
	}

	public static List getColum1s(String col1Name, String tableName,
			HibernateTemplate hibernateTemplate) {
		List result = ((List) (new Vector()));
		try {
			String sql = (new StringBuilder("select distinct ")).append(
					col1Name).append(" from ").append(tableName).toString();
			result = hibernateTemplate.find(sql);
		} catch (DataAccessException e) {
			throw new DataAccessRuntimeException(
					(new StringBuilder("excption in get")).append(col1Name)
							.append("s").toString(), ((Throwable) (e)));
		}
		return result;
	}

	public static List getColum1sByColum2(String col1Name, String col2Name,
			Object col2Value, String tableName,
			HibernateTemplate hibernateTemplate) {
		List results = ((List) (new Vector()));
		List result = ((List) (new Vector()));
		try {
			result = hibernateTemplate.find((new StringBuilder(
					"select distinct ")).append(col1Name).append(" from ")
					.append(tableName).append(" where ").append(col2Name)
					.append("=?").toString(), col2Value);
			for (Iterator it = result.iterator(); it.hasNext(); results.add(it
					.next()))
				;
		} catch (DataAccessException e) {
			throw new DataAccessRuntimeException((new StringBuilder(
					"excption in get")).append(col1Name).append("sBy").append(
					col2Name).toString(), ((Throwable) (e)));
		}
		return results;
	}

	public static String getColum1ByColum2(String col1Name, String col2Name,
			Object col2Value, String tableName,
			HibernateTemplate hibernateTemplate) {
		String strValue = null;
		List result = ((List) (new Vector()));
		try {
			result = hibernateTemplate.find((new StringBuilder(
					"select distinct ")).append(col1Name).append(" from ")
					.append(tableName).append(" where ").append(col2Name)
					.append("=?").toString(), col2Value);
			if (!result.isEmpty())
				strValue = (String) result.get(0);
		} catch (DataAccessException e) {
			throw new DataAccessRuntimeException((new StringBuilder(
					"excption in get")).append(col1Name).append("sBy").append(
					col2Name).toString(), ((Throwable) (e)));
		}
		return strValue;
	}

	public static List excuteQuery(String sql, Session session) {
		List clumValues = ((List) (new Vector()));
		try {
			List result = ((List) (new Vector()));
			Query query = session.createQuery(sql);
			result = query.list();
			Object row[];
			for (Iterator i = result.iterator(); i.hasNext(); clumValues
					.add(((Object) (row))))
				row = (Object[]) i.next();

		} catch (DataAccessException e) {
			throw new DataAccessRuntimeException("excption in sql",
					((Throwable) (e)));
		}
		return clumValues;
	}

	public static String getNextSequence(String seqName, Session session) {
		String seqString = "";
		String sql = (new StringBuilder("select ")).append(seqName).append(
				".nextval from dual").toString();
		try {
			//String sql = "select Sequence_user.nextval nextvalue from dual";
			seqString = (String)(session.createSQLQuery(sql).addScalar("nextval", Hibernate.STRING) ).uniqueResult();
			System.out.println(seqString);
		} catch (Exception e) {
			return seqString;
		}
		return seqString;
	}

	public static void deleteObjectByColValues(List values, String colName,
			String tableName, Session session) {
		if (!values.isEmpty()) {
			int n = values.size();
			String hql = "";
			String val = "";
			if (n == 1) {
				val = values.get(0).toString();
				hql = "delete from " + tableName + " where " + colName + "='"
						+ val + "'";
			} else {
				for (int i = 0; i <= n; i++) {
					val = values.get(i).toString();
				}
				hql = "";
			}
			logger.info(((Object) (hql)));
			if(!hql.isEmpty()&&!val.isEmpty()){
				Query query = session.createQuery(hql);
				query.executeUpdate();
			}
		}
	}

	public static void deleteObjectByColum(Object value, String colName,
			String tableName, Session session) {
		String hql = (new StringBuilder("delete from ")).append(tableName)
				.append(" where ").append(colName).append("='").append(value)
				.append("'").toString();
		session.createQuery(hql).executeUpdate();
	}

	public static void deleteObjectByColums(Object values[], String colNames[],
			String tableName, Session session) {
		StringBuffer hql = new StringBuffer((new StringBuilder("delete from "))
				.append(tableName).append(" where ").toString());
		for (int i = 0; i < colNames.length; i++)
			if (i == 0)
				hql.append((new StringBuilder(String
						.valueOf(((Object) (colNames[i]))))).append("='")
						.append(values[i]).append("' ").toString());
			else
				hql.append((new StringBuilder("and ")).append(colNames[i])
								.append("='").append(values[i]).append("' ")
								.toString());

		session.createQuery(hql.toString()).executeUpdate();
	}

	public static void deleteObjectById(long id, String tableName,
			Session session) {
		String hql = (new StringBuilder("delete from ")).append(tableName)
				.append(" where id").append("=").append(id).toString();
		session.createQuery(hql).executeUpdate();
	}

	public static void deleteObjectsByStrColum(Object value, String colName,
			String tableName, Session session) {
		String hql = (new StringBuilder("delete from ")).append(tableName)
				.append(" where ").append(colName).append("='").append(value)
				.append("'").toString();
		session.createQuery(hql).executeUpdate();
	}
	
	public static int deleteObjectsByStrColums(String value1,String value2, String colName1,String colName2,
			String tableName, Session session) {
		String hql ="delete from "+tableName+" where "+colName1+"='"+value1+"' and "+colName2+"='"+value2+"'";
		return session.createQuery(hql).executeUpdate();
	}


	public static void deleteAll(String tableName, Session session) {
		String hql = (new StringBuilder("delete from ")).append(tableName)
				.toString();
		session.createQuery(hql).executeUpdate();
	}

	public static void updateStrColsByStrCol1(String targetValues[],
			String targetColNames[], String srcValue, String srcColName,
			String tableName, Session session) {
		StringBuffer hql = new StringBuffer();
		hql.append((new StringBuilder("update ")).append(tableName).append(
				" set ").toString());
		for (int i = 0; i < targetColNames.length; i++)
			if (i == 0)
				hql.append((new StringBuilder(String
						.valueOf(((Object) (targetColNames[i]))))).append("='")
						.append(targetValues[i]).append("' ").toString());
			else
				hql.append((new StringBuilder(", ")).append(targetColNames[i])
						.append("='").append(targetValues[i]).append("' ")
						.toString());

		hql.append((new StringBuilder("where ")).append(srcColName)
				.append("='").append(srcValue).append("'").toString());
		session.createQuery(hql.toString()).executeUpdate();
	}

	public static int cntByStrCols(String tableName, String colNames[],
			String colValues[], Session session) {
		int cnt = 0;
		StringBuffer sql = new StringBuffer((new StringBuilder(
				"select count(*) from ")).append(tableName).append(" where ")
				.toString());
		for (int i = 0; i < colNames.length; i++)
			if (i == 0)
				sql.append((new StringBuilder(String
						.valueOf(((Object) (colNames[i]))))).append("='")
						.append(colValues[i]).append("' ").toString());
			else
				sql.append((new StringBuilder("and ")).append(colNames[i])
						.append("='").append(colValues[i]).append("' ")
						.toString());

		Connection conn = session.connection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			if (rs.next())
				cnt = rs.getInt(1);
			rs.close();
			stmt.close();
		} catch (Exception e) {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e1) {
				logger.error(((Object) (e1.getMessage())), ((Throwable) (e1)));
			}
		}
		return cnt;
	}



	
}