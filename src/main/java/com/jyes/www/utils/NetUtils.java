package com.jyes.www.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class NetUtils {

	public static String getServerIp() {
		String hostAddr = "";
		try {
			Enumeration<NetworkInterface> nienum = NetworkInterface.getNetworkInterfaces();
			while (nienum.hasMoreElements()) {
				NetworkInterface ni = nienum.nextElement();
				Enumeration<InetAddress> kk = ni.getInetAddresses();
				while (kk.hasMoreElements()) {
					InetAddress inetAddress = kk.nextElement();
					if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress()) {
						hostAddr = inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return hostAddr;
	}

}
