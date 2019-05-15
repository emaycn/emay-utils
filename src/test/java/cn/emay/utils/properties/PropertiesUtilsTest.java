package cn.emay.utils.properties;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;

/**
* 
* @author Frank
*
*/
public class PropertiesUtilsTest {

	@Test
	public void test() {

		String propertiesClassPath = "test.properties";

		String intkey = "test.int";
		String stringkey = "test.string";
		String floatkey = "test.float";
		String booleankey = "test.boolean";
		String datekey = "test.date";

		System.out.println("PropertiesUtils.getBigDecimalProperty");
		System.out.println(PropertiesUtils.getBigDecimalProperty(intkey, propertiesClassPath, new BigDecimal(0)));
		System.out.println("PropertiesUtils.getBooleanProperty");
		System.out.println(PropertiesUtils.getBooleanProperty(booleankey, propertiesClassPath, false));
		System.out.println("PropertiesUtils.getDateProperty");
		System.out.println(PropertiesUtils.getDateProperty(datekey, propertiesClassPath, "yyyy-MM-dd HH:mm:ss SSS", new Date()));
		System.out.println("PropertiesUtils.getFloatProperty");
		System.out.println(PropertiesUtils.getFloatProperty(floatkey, propertiesClassPath, 1.0f));
		System.out.println("PropertiesUtils.getDoubleProperty");
		System.out.println(PropertiesUtils.getDoubleProperty(floatkey, propertiesClassPath, 1.1d));
		System.out.println("PropertiesUtils.getIntProperty");
		System.out.println(PropertiesUtils.getIntProperty(intkey, propertiesClassPath, 1));
		System.out.println("PropertiesUtils.getLongProperty");
		System.out.println(PropertiesUtils.getLongProperty(intkey, propertiesClassPath, 1L));
		System.out.println("PropertiesUtils.getProperties");
		System.out.println(PropertiesUtils.getProperties(propertiesClassPath));
		System.out.println("PropertiesUtils.getProperty");
		System.out.println(PropertiesUtils.getProperty(stringkey, propertiesClassPath));
		System.out.println("PropertiesUtils.getPropertys");
		System.out.println(PropertiesUtils.getPropertys(propertiesClassPath));

		// PropertiesUtils.getPropertiesByFile(file);
		// PropertiesUtils.getPropertiesByFile(propertiesFilePath);
		// PropertiesUtils.getPropertyByFile(key, propertiesFilePath);
		// PropertiesUtils.getPropertysByFile(propertiesFilePath);
	}

}
