package com.ou.learn.base.io;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by weouyang on 24/11/2017.
 */
public class ReadFile {

    public void testMetadataMD5() {
        try {
            BufferedReader fileReader = new BufferedReader(
                new InputStreamReader(ClassLoader.getSystemResourceAsStream("metadata/variable/metadata.info")));
            Map<String, String> infoMap = new HashMap<String, String>();
            String line;
            do {
                line = fileReader.readLine();
                if (StringUtils.isEmpty(line))
                    continue;
                String[] values = line.split("=");
                infoMap.put(values[0],values[1]);
            } while (!StringUtils.isEmpty(line));
            System.out.println("info md5:"+infoMap.get("checkSum"));


            InputStream in = ClassLoader.getSystemResource("metadata/variable/metadata.json").openStream();
            //            int total = 0;
            //            List<byte[]> list = new ArrayList<>();
            //            int  ch = 0;
            //            do {
            //                byte[] bytes = new byte[2048];
            //                total += ch;
            //                ch = in.read(bytes,0,2048);
            //                if (ch > 0 )
            //                    list.add(bytes);
            //            } while (ch != -1);
            //            System.out.println(total+" list.size:"+list.size()+" last length:");
            //            byte[] totalBytes = new byte[total];
            //            int i = 0;
            //            for (byte[] bytes1 : list) {
            //                for (byte b : bytes1) {
            //                    totalBytes[i] = b;
            //                    ++i;
            //                    if (i > (total -1))
            //                        break;
            //                }
            //                if (i > (total -1))
            //                    break;
            //            }
            //            BufferedReader jsonReader = new BufferedReader(new InputStreamReader(in));
            //            StringBuilder jsonBuilder = new StringBuilder();
            //            String json_line = jsonReader.readLine();
            //            while (json_line != null){
            //                jsonBuilder.append(json_line);
            //            }
            //            byte[] totalBytes = jsonBuilder.toString().getBytes();
            //            MessageDigest md5 = MessageDigest.getInstance("MD5");
            //            md5.update(ByteBuffer.wrap(totalBytes));
            //            BigInteger bi = new BigInteger(1, md5.digest());
            //            String value = bi.toString(16);
            //            System.out.println("md5:"+value);

//            String json_md5 = DigestUtils.md5Hex(IOUtils.toByteArray(in));
//            IOUtils.closeQuietly(in);
//            System.out.println("MD5:"+json_md5);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void readerJarfile() {

    }
}
