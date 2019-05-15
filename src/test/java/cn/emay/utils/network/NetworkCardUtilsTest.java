package cn.emay.utils.network;

import java.net.SocketException;

/**
* 
* @author Frank
*
*/
public class NetworkCardUtilsTest {

	public static void main(String args[]) throws SocketException {
		System.out.println(NetworkCardUtils.getLocalFirstIPv4Address());
		System.out.println(NetworkCardUtils.getLocalFirstIPv6Address());
	}

}
