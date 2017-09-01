package cn.lonecloud;

import cn.lonecloud.bean.CompanyInfo;
import cn.lonecloud.tools.CatchPageTool;
import cn.lonecloud.utils.ExcelUtils;
import cn.lonecloud.utils.WaperUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
                companyInfos.addAll(WaperUtils.catchWebByUrl("http://b2b.huangye88.com/shanghai/jiancai/pn" + i));
                System.out.println("结束-------------------第"+i+"页");
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        ExcelUtils.generatorExcel(companyInfos, new String[]{"公司名称", "联系人名称", "电话号码", "主营"}, "/Users/lonecloud/Desktop/2.xls");
    }
    @Test
    public void myTestWeb2() throws IOException, IllegalAccessException {
    	List<CompanyInfo> companyInfos = new ArrayList<>();
    	for (int i = 0; i < 20; i++) {
            CatchPageTool catchPageTool=new CatchPageTool();
    		try {
    			System.out.println("开始-------------------第"+i+"页");
    			companyInfos.addAll(catchPageTool.catchWebByUrl("http://b2b.huangye88.com/shanghai/yundongxiuxian/pn" + i));
    			System.out.println("结束-------------------第"+i+"页");
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		
    		
    	}
    	ExcelUtils.generatorExcel(companyInfos, new String[]{"公司名称", "联系人名称", "电话号码", "主营"}, "/Users/lonecloud/Desktop/1.xls","url","webSite");
    }
    @Test
    public void Str(){
        String s="学科、专业目录_各类名单_中国学位与研究生教育信息网";
        try {
            System.out.println(new String(s.getBytes("utf-8"),"gb2312"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
