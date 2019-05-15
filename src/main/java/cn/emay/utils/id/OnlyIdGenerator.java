package cn.emay.utils.id;

import java.util.Date;
import java.util.UUID;

import cn.emay.utils.date.DateUtils;

/**
 * 唯一ID生成器
 * 
 * @author Frank
 *
 */
public class OnlyIdGenerator {

	/**
	 * 补全字段
	 */
	private static String ZERO10 = "0000000000";

	/**
	 * ID 序列
	 */
	private static int ID_INDEX = 0;

	/**
	 * ID 序列计算时间
	 */
	private static long ID_END_MILLIS = 0L;

	/**
	 * 生成18位的唯一数字ID<br/>
	 * 当前毫秒数(13) + 序列(2) + 服务标识(3)<br/>
	 * 等待机制：如果当前毫秒数的序列超过100，则等待累加到下一毫秒。即每毫秒最多生成100个
	 *
	 * @return
	 */
	public synchronized static String genOnlyId(String sign) {
		long millis = System.currentTimeMillis();
		if (millis == ID_END_MILLIS) {
			ID_INDEX++;
		} else {
			ID_INDEX = 0;
			ID_END_MILLIS = millis;
		}
		if (ID_INDEX > 99) {
			boolean wait = true;
			while (wait) {
				millis = System.currentTimeMillis();
				if (millis != ID_END_MILLIS) {
					wait = false;
					ID_END_MILLIS = millis;
					ID_INDEX = 0;
				}
			}
		}
		String indexStr = ZERO10 + ID_INDEX;
		indexStr = indexStr.substring(indexStr.length() - 2, indexStr.length());
		String smsId = millis + indexStr + sign;
		return smsId;
	}

	/**
	 * BID 序列
	 */
	private static int BID_INDEX = 0;

	/**
	 * BID 序列计算时间
	 */
	private static String BID_END_MILLIS = "";

	/**
	 * 生成唯一BID编码<br/>
	 * yyyyMMddHHmmss(14) + 序列(7) + 服务标识(3)<br/>
	 * 每秒最多1000万个
	 */
	public synchronized static String genOnlyBId(String sign) {
		String time = DateUtils.toString(new Date(), "yyyyMMddHHmmss");
		if (BID_END_MILLIS.equals(time)) {
			BID_INDEX++;
		} else {
			BID_INDEX = 0;
			BID_END_MILLIS = time;
		}
		String indexStr = ZERO10 + BID_INDEX;
		indexStr = indexStr.substring(indexStr.length() - 7, indexStr.length());
		String no = time + indexStr + sign;
		return no;
	}

	public synchronized static String genUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}
