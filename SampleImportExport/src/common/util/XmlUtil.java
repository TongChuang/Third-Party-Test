package common.util;

import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jdom.input.DOMBuilder;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import sysconf.SysConfApi;

/**
 * xml相关的工具类
 * 
 * @author zjn
 */
@SuppressWarnings("unchecked")
public class XmlUtil {
	private SysConfApi sysConfApi = null;

	public void setSysConfApi(SysConfApi sysConfApi) {
		this.sysConfApi = sysConfApi;
	}

	/**
	 * 将Object类型的值，转换成bean对象属性里对应的类型值
	 * 
	 * @param value
	 *            Object对象值
	 * @param fieldTypeClass
	 *            属性的类型
	 * @return 转换后的值
	 */
	private static Object convertValType(Object value, Class fieldTypeClass) {
		Object retVal = null;
		if (Long.class.getName().equals(fieldTypeClass.getName())
				|| long.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Long.parseLong(value.toString());
		} else if (Integer.class.getName().equals(fieldTypeClass.getName())
				|| int.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Integer.parseInt(value.toString());
		} else if (Float.class.getName().equals(fieldTypeClass.getName())
				|| float.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Float.parseFloat(value.toString());
		} else if (Double.class.getName().equals(fieldTypeClass.getName())
				|| double.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Double.parseDouble(value.toString());
		} else {
			retVal = value;
		}
		return retVal;
	}

	/**
	 * 获取指定字段名称查找在class中的对应的Field对象(包括查找父类)
	 * 
	 * @param clazz
	 *            指定的class
	 * @param fieldName
	 *            字段名称
	 * @return Field对象
	 */
	private static Field getClassField(Class clazz, String fieldName) {
		if (Object.class.getName().equals(clazz.getName())) {
			return null;
		}
		Field[] declaredFields = clazz.getDeclaredFields();
		for (Field field : declaredFields) {
			// System.out.println(field.getName()+"|"+fieldName);
			if (field.getName().equals(fieldName)) {
				return field;
			}
		}

		Class superClass = clazz.getSuperclass();
		if (superClass != null) {// 简单的递归一下
			return getClassField(superClass, fieldName);
		}
		return null;
	}

	/**
	 * 测试XML结果转对象
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// String xmlStr =
		// "<InXml><OrgID>20000</OrgID><DistributionNumber>8888888888</DistributionNumber></InXml>";
		// "<OutXml><HospitalTelephone>057188888888</HospitalTelephone><Memo>测试备注</Memo><OrderDistributionDate>2016-04-28 11:54:01</OrderDistributionDate><OrderNumber>8888888</OrderNumber><OrderSubmitDate>2016-04-28 11:54:01</OrderSubmitDate><OrderType>0</OrderType><OrgID>200000000</OrgID><SubscriberName>王医生</SubscriberName><SubscriberTelephone>057188888888</SubscriberTelephone><OrderList><BLOODPROINFO><ABO>1</ABO><Irradiated>0</Irradiated><MeasureUnit>U</MeasureUnit><ProductCode>11</ProductCode><ProductName>悬浮红细胞</ProductName><ProductVolume>2</ProductVolume>10<RhD>1</RhD><WashingType>0</WashingType></BLOODPROINFO><BLOODPROINFO><ABO>2</ABO><Irradiated>0</Irradiated><MeasureUnit>U</MeasureUnit><ProductCode>11</ProductCode><ProductName>悬浮红细胞</ProductName><ProductVolume>1.5</ProductVolume><RhD>1</RhD><WashingType>0</WashingType></BLOODPROINFO></OrderList></OutXml>";

		// GRI_OrderList gri_outxml = (GRI_OrderList) xmlStrToBean(xmlStr,
		// GRI_OrderList.class);
	}

	/**
	 * JavaBean转换成xml
	 * 
	 * @param obj
	 * @param encoding
	 * @return
	 */
	public static String convertToXml(Object obj, String encoding) {
		String result = null;
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);

			StringWriter writer = new StringWriter();
			marshaller.marshal(obj, writer);
			result = writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * xml转换成JavaBean
	 * 
	 * @param xml
	 * @param c
	 * @return
	 */
	public static <T> T converyToJavaBean(String xml, Class<T> c) {
		T t = null;
		try {
			JAXBContext context = JAXBContext.newInstance(c);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			t = (T) unmarshaller.unmarshal(new StringReader(xml));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	public static org.jdom.Document string2Doc(String xml) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document doc = null;
		InputSource source = null;
		StringReader reader = null;
		try {
			builder = factory.newDocumentBuilder();
			reader = new StringReader(xml);
			source = new InputSource(reader);// 使用字符流创建新的输入源
			doc = builder.parse(source);
			return dom2Jdom(doc);
		} catch (Exception e) {
			return null;
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}

	private static org.jdom.Document dom2Jdom(org.w3c.dom.Document doc) {
		DOMBuilder builder = new DOMBuilder();
		org.jdom.Document jdomDoc = builder.build(doc);
		return jdomDoc;
	}
}
