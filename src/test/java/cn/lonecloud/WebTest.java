package cn.lonecloud;

import cn.lonecloud.bean.CompanyInfo;
import cn.lonecloud.utils.ExcelUtils;
import cn.lonecloud.utils.WaperUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lonecloud on 17/6/11.
 */
public class WebTest {

    @Test
    public void myTestWeb() throws IOException, IllegalAccessException {
        List<CompanyInfo> companyInfos = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            try {
                System.out.println("开始-------------------第"+i+"页");
                companyInfos.addAll(WaperUtils.catchWebByUrl("http://b2b.huangye88.com/beijing/shenghuo/pn" + i));
                System.out.println("结束-------------------第"+i+"页");
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        ExcelUtils.generatorExcel(companyInfos, new String[]{"公司名称", "联系人名称", "电话号码", "主营"}, "/Users/lonecloud/Desktop/1.xls");
    }

}
