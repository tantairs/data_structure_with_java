package com.lianxi.how_tomcat_works;

import java.io.IOException;

/**
 * Created by tantairs on 10/13/16.
 */
public class StaticResourceProcessor {

    public void process(Request request, Response response){
        try {
            response.sendStaticResource();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
