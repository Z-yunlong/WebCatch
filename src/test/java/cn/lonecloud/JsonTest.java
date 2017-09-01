package cn.lonecloud;

import cn.lonecloud.bean.CrmBean;
import cn.lonecloud.bean.CrmBean2;
import cn.lonecloud.utils.ExcelUtils;
import cn.lonecloud.utils.JSONUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by lonecloud on 2017/8/10.
 */
public class JsonTest {
    @Test
    public void json() throws IOException, IllegalAccessException {
        List<CrmBean2> jsonobjList = JSONUtils.getJSONOBJList(new File("/Users/lonecloud/Desktop/data.json"), CrmBean2.class);
        System.out.println(jsonobjList);
        ExcelUtils.generatorExcel(jsonobjList, new String[]{"公司名称", "行业", "职位","联系人","手机", "公司电话","邮箱","所在省","所在城市","地址","描述","网站"}, "/Users/lonecloud/Desktop/1.xls","url","webSite");
    }
}
