package com.imooc.ad.service;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.DeleteRowsEventData;
import com.github.shyiko.mysql.binlog.event.EventData;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;

import java.io.IOException;

/**
 * 测试binlog
 * @author chenqiang
 * @create 2020-06-17 10:51
 */
public class BinlogServiceTest {

    public static void main(String[] args) throws IOException {
        BinaryLogClient client = new BinaryLogClient("123.57.166.10", 3306, "root", "123456");
        //client.setBinlogFilename("mysql-bin.0000xx");
        //client.setBinlogPosition();
        client.registerEventListener(event -> {
            EventData data = event.getData();
            if(data instanceof UpdateRowsEventData){
                System.out.println("update------------");
                System.out.println(data.toString());
            }else if(data instanceof WriteRowsEventData){
                System.out.println("write--------------");
                System.out.println(data.toString());
            }else if(data instanceof DeleteRowsEventData){
                System.out.println("delete-------------");
                System.out.println(data.toString());
            }
        });
        client.connect();
    }
}
