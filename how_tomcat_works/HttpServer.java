package com.lianxi.how_tomcat_works;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by tantairs on 10/12/16.
 */
public class HttpServer {



    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN_COMMAND";
    private boolean shutdown = false;

    public static void main(String[] args){

        HttpServer server = new HttpServer();
        server.await();
    }

    public void await(){
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port,1, InetAddress.getByName("127.0.0.1"));
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }

        while (!shutdown){
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;

            try {
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();
                Request request = new Request(input);
                request.parse();

                Response response = new Response(output);
                response.setRequest(request);

                //check if this is a request for a servlet or
                //a static resource
                //a request for a servlet begins with "/servlet"
                if(request.getUri().startsWith("/servlet/")){
                    ServletProcessor1 processor = new ServletProcessor1();
                    processor.process(request,response);
                }else {
                    StaticResourceProcessor processor = new StaticResourceProcessor();
                    processor.process(request,response);
                }

//                response.sendStaticResource();
                socket.close();

                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }


        }
    }

}
