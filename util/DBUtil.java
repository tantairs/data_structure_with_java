package com.lianxi.util;

import java.sql.*;

/**
 * Created by tantairs on 16/5/6.
 */
public class DBUtil {
    public static Connection connectToDB(){

        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");//驱动的类名
            conn= DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/tianchi", //服务器的IP地址和端口号，数据库的名字
                    "root",//用户名
                    "123456"//密码
            );

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return conn;
    }
}
