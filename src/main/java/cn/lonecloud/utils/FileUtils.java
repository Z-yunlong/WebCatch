package cn.lonecloud.utils;

import java.io.*;

/**
 * Created by lonecloud on 2017/8/10.
 */
public class FileUtils {

    public static String getFileString(File file) throws FileNotFoundException {
        BufferedReader bw=new BufferedReader(new FileReader(file));
        StringBuilder sb=new StringBuilder();
        try {
            String line =bw.readLine();
            while (line!=null){
                sb.append(line);
                line=bw.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
