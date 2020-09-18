package cn.emay.utils.network;

/**
 * @author Frank
 */
public class NetworkCardUtilsTest {

    public static void main(String[] args) {
        System.out.println(NetworkCardUtils.getLocalFirstIPv4Address());
        System.out.println(NetworkCardUtils.getLocalFirstIPv6Address());
    }

}
