package cn.lonecloud;

import cn.lonecloud.bean.CompanyInfo;
import cn.lonecloud.tools.CatchPageTool;
import cn.lonecloud.utils.ExcelUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lonecloud on 17/6/17.
 */
public class MainRun {
    public static void main(String[] args) throws IOException, IllegalAccessException {
        if (args != null && args.length == 3) {
            int length = Integer.parseInt(args[2]);
            List<CompanyInfo> companyInfos = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                CatchPageTool catchPageTool = new CatchPageTool();
                try {
                    System.out.println("开始-------------------第" + i + "页");
                    companyInfos.addAll(catchPageTool.catchWebByUrl(args[0] + i));
                    System.out.println("结束-------------------第" + i + "页");
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
            ExcelUtils.generatorExcel(companyInfos, new String[]{"公司名称", "联系人名称", "电话号码", "主营"}, args[1], "url", "webSite");
        }
    }

}
