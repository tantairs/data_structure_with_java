package com.lianxi.web_test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by tantairs on 10/13/16.
 */
public class TCPEchoClient {

    public static void main(String[] args) throws IOException{
        if((args.length < 2) || (args.length > 3))
            throw new IllegalArgumentException("Paramerer(s) <Server> <Word> [<Port>]");

        String server = args[0];
        byte[] data = args[1].getBytes();

        int servPort = (args.length == 3) ? Integer.parseInt(args[2]) : 7;

        Socket socket = new Socket(server,servPort);

        System.out.println("Connected to server ... sending echo string");

        InputStream in = socket.getInputStream();

        OutputStream out = socket.getOutputStream();

        out.write(data);

        int toltalBytesRcvd = 0;
        int bytesRcvd;
        while (toltalBytesRcvd < data.length){
            if((bytesRcvd = in.read(data,toltalBytesRcvd,data.length - toltalBytesRcvd)) == -1){
                throw new SocketException("Connnection closed prematurely");
            }
            toltalBytesRcvd += bytesRcvd;

        }
        System.out.println("Received: " + new String(data));
        socket.close();
    }

}
