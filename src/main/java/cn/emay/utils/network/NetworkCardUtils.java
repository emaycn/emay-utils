package cn.emay.utils.network;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * 网卡工具
 * 
 * @author Frank
 *
 */
public class NetworkCardUtils {

	/**
	 * 所有的网卡信息
	 */
	private static NetworkCardInfo[] INFOS = null;

	/**
	 * 获取本机所有网卡信息
	 * 
	 * @return
	 */
	public static NetworkCardInfo[] getLocalNetworkCards() {
		if (INFOS != null) {
			return INFOS;
		}
		NetworkCardInfo[] infos = null;
		try {
			Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			List<NetworkInterface> list = Collections.list(allNetInterfaces);
			int size = list.size();
			infos = new NetworkCardInfo[size];
			for (int i = 0; i < list.size(); i++) {
				NetworkInterface netInterface = (NetworkInterface) list.get(i);
				String displayName = netInterface.getDisplayName();
				String name = netInterface.getName();
				byte[] macBytes = netInterface.getHardwareAddress();
				String mac = getLocalMac(macBytes);
				String ipv4 = null;
				String ipv6 = null;
				Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
				while (addresses.hasMoreElements()) {
					InetAddress ia = (InetAddress) addresses.nextElement();
					if (ia == null) {
						continue;
					}
					if (ia instanceof Inet4Address) {
						ipv4 = ia.getHostAddress();
					}
					if (ia instanceof Inet6Address) {
						if (ia.getHostAddress() != null) {
							if (ia.getHostAddress().indexOf("%") > 0) {
								ipv6 = ia.getHostAddress().substring(0, ia.getHostAddress().indexOf("%"));
							} else {
								ipv6 = ia.getHostAddress();
							}
						}
					}
				}
				infos[i] = new NetworkCardInfo(displayName, name, mac, ipv4, ipv6);
			}
		} catch (SocketException e) {
			throw new IllegalArgumentException(e);
		}
		INFOS = infos;
		return INFOS;
	}

	/**
	 * 获取MAC
	 * 
	 * @param macBytes
	 * @return
	 */
	private static String getLocalMac(byte[] macBytes) {
		if (macBytes == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < macBytes.length; i++) {
			String str = Integer.toHexString(macBytes[i] & 0xff);
			sb.append(str.length() == 1 ? "0" + str : str).append("-");
		}
		return sb.substring(0, sb.length() - 1);
	}

	/**
	 * 获取本机第一个有效IPV4地址
	 * 
	 * @return
	 */
	public static String getLocalFirstIPv4Address() {
		String ipv4 = null;
		NetworkCardInfo[] infos = getLocalNetworkCards();
		for (NetworkCardInfo info : infos) {
			if (info.getIpv4() != null && !"127.0.0.1".equals(info.getIpv4())) {
				ipv4 = info.getIpv4();
				break;
			}
		}
		return ipv4;
	}

	/**
	 * 获取本机第一个有效IPV6地址
	 * 
	 * @return
	 */
	public static String getLocalFirstIPv6Address() {
		String ipv6 = null;
		NetworkCardInfo[] infos = getLocalNetworkCards();
		for (NetworkCardInfo info : infos) {
			if (info.getIpv6() != null && !"0:0:0:0:0:0:0:1".equals(info.getIpv6())) {
				ipv6 = info.getIpv6();
				break;
			}
		}
		return ipv6;
	}

}
