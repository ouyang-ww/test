package com.ou.learn.base.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by weouyang on 01/11/2017.
 */
public class ReadTxt {
    public static void main(String[] args) {
        try {
            readFile("/Users/weouyang/Desktop/model_result/driver_1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void readFile(String fileUrl) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileUrl));
        String readLine = null;
        do {
            readLine = bufferedReader.readLine();
            if (readLine != null) {
                String[] ss = readLine.split("\\|");
                if (ss.length <3)
                    continue;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = sdf.parse(ss[2]);
                System.out.println(date);

            }
        }while(readLine != null);

    }
}
