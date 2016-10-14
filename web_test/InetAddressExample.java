package com.lianxi.web_test;

import java.net.*;
import java.util.Enumeration;

/**
 * Created by tantairs on 10/13/16.
 */
public class InetAddressExample {

    public static void main(String[] args){

        try{
            Enumeration<NetworkInterface> interfaceList = NetworkInterface.getNetworkInterfaces();
            if(interfaceList == null){
                System.out.println("--No interfaces found--");
            }else {
                while (interfaceList.hasMoreElements()){
                    NetworkInterface iface = interfaceList.nextElement();
                    System.out.println("Interface " + iface.getName() + ":");
                    Enumeration<InetAddress> addrList = iface.getInetAddresses();
                    if(!addrList.hasMoreElements()){
                        System.out.println("\t(No addresses for this interface)");
                    }
                    while (addrList.hasMoreElements()){
                        InetAddress address = addrList.nextElement();
                        System.out.print("\tAddress" + (address instanceof Inet4Address ? "(v4)" : (address instanceof Inet6Address ? "(v6)" : "(?)")));
                        System.out.println(": " + address.getHostAddress());
                    }
                }
            }
        }catch (SocketException se){
            se.printStackTrace();
        }

        for(String host : args){
            try {
                System.out.println(host + ":");
                InetAddress[] addressList = InetAddress.getAllByName(host);
                for(InetAddress address : addressList){
                    System.out.println("\t" + address.getHostName() + "/" + address.getHostAddress());
                }
            }catch (UnknownHostException e){
                System.out.println("\t Unable to find address for " + host);
            }
        }

    }

}
