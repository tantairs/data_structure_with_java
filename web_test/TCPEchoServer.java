package com.lianxi.web_test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by tantairs on 10/13/16.
 */
public class TCPEchoServer {

    private static final int BUFSIZE = 32;
    public static void main(String[] args) throws IOException{
        if(args.length != 1){
            throw new IllegalArgumentException("Parmeter(s): <Port>");
        }
        int servPort = Integer.parseInt(args[0]);
        ServerSocket servSock = new ServerSocket(servPort);

        int recvMsgSize;
        byte[] receiveBuf = new byte[BUFSIZE];

        while (true){
            Socket clntSock = servSock.accept();
            SocketAddress clinetAddress = clntSock.getRemoteSocketAddress();
            System.out.println("Handling client at " + clinetAddress);

            InputStream in = clntSock.getInputStream();
            OutputStream out = clntSock.getOutputStream();

            while ((recvMsgSize = in.read(receiveBuf)) != -1){
                out.write(receiveBuf,0,recvMsgSize);
            }
            clntSock.close();
        }

    }

}
