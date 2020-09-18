package cn.emay.utils.network;

/**
 * 网卡参数
 *
 * @author Frank
 */
public class NetworkCardInfo {

    /**
     * 硬件名称
     */
    private String displayName;

    /**
     * 网卡名称
     */
    private String name;

    /**
     * MAC地址
     */
    private String mac;

    /**
     * IPV4地址
     */
    private String ipv4;

    /**
     * IPV6地址
     */
    private String ipv6;

    public NetworkCardInfo() {

    }

    public NetworkCardInfo(String displayName, String name, String mac, String ipv4, String ipv6) {
        super();
        this.displayName = displayName;
        this.name = name;
        this.mac = mac;
        this.ipv4 = ipv4;
        this.ipv6 = ipv6;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    public String getIpv6() {
        return ipv6;
    }

    public void setIpv6(String ipv6) {
        this.ipv6 = ipv6;
    }
}
