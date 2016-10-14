package com.lianxi.web_test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by tantairs on 10/12/16.
 */
public class HttpClient {

    public static void main(String[] args){
        String uri = "index.html";
        if(args.length != 0) uri = args[0];
        doGet("localhost",8080,uri);

    }

    public static void doGet(String host,int port, String uri){
        Socket socket = null;

        try {
            socket = new Socket(host,port);
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            StringBuffer sb = new StringBuffer("GET " + uri + " HTTP/1.1\r\n");
            sb.append("Accept: */*\r\n");
            sb.append("Accept-Language: zh-cn\r\n");
            sb.append("Accept-Encoding: gzip, deflate\r\n");
            sb.append("User-Agent: HttpClient\r\n");
            sb.append("Host: localhost:8080\r\n");
            sb.append("Connection: Keep-Alive\r\n\r\n");

            OutputStream socketOut = socket.getOutputStream();
            socketOut.write(sb.toString().getBytes());
            Thread.sleep(2000);

            InputStream socketIn = socket.getInputStream();
            int size = socketIn.available();
            byte[] buffer = new byte[size];
            socketIn.read(buffer);
            System.out.println(new String(buffer));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
