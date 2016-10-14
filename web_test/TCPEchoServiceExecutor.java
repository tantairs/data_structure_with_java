package com.lianxi.web_test;

import sun.jvm.hotspot.tools.ObjectHistogram;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * Created by tantairs on 10/13/16.
 */
public class TCPEchoServiceExecutor {

    public static void main(String[] args) throws IOException{
        if(args.length != 1){
            throw new IllegalArgumentException("Parameter(s): <Port>");
        }
        int echoServPort = Integer.parseInt(args[0]);

        ServerSocket servSock = new ServerSocket(echoServPort);
        Logger logger = Logger.getLogger("practical");
        Executor service = Executors.newCachedThreadPool();
        while (true){
            Socket clntSock = servSock.accept();
//            service.execute(new Object());
        }
    }

}
