package cn.emay.utils.number;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * BigDecimal精准计算工具
 * 
 * @author Frank
 *
 */
public class BigDecimalUtils {

	/**
	 * 转换
	 * 
	 * @param number
	 * @return
	 */
	public static BigDecimal parse(int number) {
		return new BigDecimal(number);
	}

	/**
	 * 转换
	 * 
	 * @param number
	 * @return
	 */
	public static BigDecimal parse(short number) {
		return new BigDecimal(number);
	}

	/**
	 * 转换
	 * 
	 * @param number
	 * @return
	 */
	public static BigDecimal parse(long number) {
		return new BigDecimal(number);
	}

	/**
	 * 转换
	 * 
	 * @param number
	 * @param scale
	 * @return
	 */
	public static BigDecimal parse(float number, int scale) {
		return new BigDecimal(number).setScale(scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 转换
	 * 
	 * @param number
	 * @param scale
	 * @return
	 */
	public static BigDecimal parse(double number, int scale) {
		return new BigDecimal(number).setScale(scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 转换
	 * 
	 * @param number
	 * @return
	 */
	public static BigDecimal parse(BigInteger number) {
		return new BigDecimal(number);
	}

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            加数
	 * @param v2
	 *            被加数
	 * @return 两个参数的和
	 */
	public static BigDecimal add(BigDecimal v1, BigDecimal v2) {
		return v1.add(v2);
	}

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            加数
	 * @param v2
	 *            被加数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的和
	 */
	public static BigDecimal add(BigDecimal v1, BigDecimal v2, int scale) {
		return v1.add(v2).setScale(scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            减数
	 * @param v2
	 *            被减数
	 * @return 两个参数的差
	 **/
	public static BigDecimal sub(BigDecimal v1, BigDecimal v2) {
		return v1.subtract(v2);
	}

	/**
	 * 
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            减数
	 * @param v2
	 *            被减数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的差
	 **/
	public static BigDecimal sub(BigDecimal v1, BigDecimal v2, int scale) {
		return v1.subtract(v2).setScale(scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            乘数
	 * @param v2
	 *            被乘数
	 * @return 两个参数的积
	 */
	public static BigDecimal mul(BigDecimal v1, BigDecimal v2) {
		return v1.multiply(v2);
	}

	/**
	 * * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            乘数
	 * @param v2
	 *            被乘数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的积
	 */
	public static BigDecimal mul(BigDecimal v1, BigDecimal v2, int scale) {
		return v1.multiply(v2).setScale(scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 * 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            除数
	 * 
	 * @param v2
	 *            被除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static BigDecimal div(BigDecimal v1, BigDecimal v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		return v1.divide(v2, scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 对比大小
	 * 
	 * @param v1
	 * @param v2
	 * @return true -> v1>v2; false -> v1<v2;
	 */
	public static boolean compare(BigDecimal v1, BigDecimal v2) {
		return v1.compareTo(v2) > 0;
	}

}