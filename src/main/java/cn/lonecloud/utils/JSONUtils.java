package cn.lonecloud.utils;

import cn.lonecloud.bean.CrmBean;
import com.alibaba.fastjson.JSON;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by lonecloud on 2017/8/9.
 */
public class JSONUtils {

    public static <T> List<T> getJSONOBJList(File file,Class<T> clazz) throws FileNotFoundException {
        String fileString = FileUtils.getFileString(file);
        List<T> list = JSON.parseArray(fileString, clazz);
        return list;
    }

}
