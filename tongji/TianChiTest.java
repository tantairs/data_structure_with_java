package com.lianxi.tongji;

import com.lianxi.util.DBUtil;

import java.sql.*;

/**
 * Created by tantairs on 16/5/6.
 */
public class TianChiTest {

    public static void main(String[] args){


        /**
         * 需要做的任务: 几月几号 - 每个艺人 - 每个艺人一天总共被播放的歌曲 - 每个艺人那天被播放的总的歌曲数 - (下载的歌曲数) - (收藏数)
         *
         */

        ResultSet rs=null;
        try {
            Connection conn = DBUtil.connectToDB();
            Statement stmt=conn.createStatement();
            rs=stmt.executeQuery("select song_id,artist_id from mars_tianchi_songs");
            while(rs.next())
            {
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            if(rs!=null)
            {
                try {
                    rs.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
