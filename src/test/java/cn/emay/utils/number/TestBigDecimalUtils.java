package cn.emay.utils.number;

import java.math.BigDecimal;

import org.junit.Test;

import cn.emay.utils.number.BigDecimalUtils;

public class TestBigDecimalUtils {

	@Test
	public void testD() {

		int scale = 2;

		double dou = 123546.123456;
		double dou2 = 123546.99999;

		int in1 = 123345;
		int in2 = -2595;

		System.out.println(BigDecimalUtils.add(new BigDecimal(in1), new BigDecimal(in2), scale).intValue() + "\t" + (in2 + in1));
		System.out.println(BigDecimalUtils.add(new BigDecimal(dou), new BigDecimal(dou2), scale).doubleValue() + "\t" + (dou + dou2));

		System.out.println(BigDecimalUtils.sub(new BigDecimal(in1), new BigDecimal(in2), scale).intValue() + "\t" + (in1 - in2));
		System.out.println(BigDecimalUtils.sub(new BigDecimal(dou), new BigDecimal(dou2), scale).doubleValue() + "\t" + (dou - dou2));

		System.out.println(BigDecimalUtils.div(new BigDecimal(in1), new BigDecimal(in2), scale).intValue() + "\t" + (in1 / in2));
		System.out.println(BigDecimalUtils.div(new BigDecimal(dou), new BigDecimal(dou2), scale).doubleValue() + "\t" + (dou / dou2));

		System.out.println(BigDecimalUtils.mul(new BigDecimal(in1), new BigDecimal(in2), scale).intValue() + "\t" + (in1 * in2));
		System.out.println(BigDecimalUtils.mul(new BigDecimal(dou), new BigDecimal(2), scale).doubleValue() + "\t" + (dou * 2));

		System.out.println(BigDecimalUtils.compare(new BigDecimal(dou), new BigDecimal(dou2)));
		
	}

}
