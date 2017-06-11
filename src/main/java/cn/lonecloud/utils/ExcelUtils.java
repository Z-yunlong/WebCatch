package cn.lonecloud.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by lonecloud on 17/6/11.
 * @author lonecloud
 * Excel的工具类
 */
public class ExcelUtils {

    public static String generatorExcel(List datas,String[] titleDatas,String filePath) throws IOException, IllegalAccessException {
        //创建HSSFWorkbook对象
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建HSSFSheet对象
        HSSFSheet sheet = wb.createSheet("sheet0");
        setTitle(sheet.createRow(0),titleDatas);
        for (int i = 1; i < datas.size(); i++) {
            //创建HSSFRow对象
            HSSFRow row = sheet.createRow(i);
            covernData(row,datas.get(i));
        }
        //输出Excel文件
        FileOutputStream output = new FileOutputStream(filePath);
        wb.write(output);
        output.flush();
        return filePath;
    }

    /**
     * 设置头部信息
     * @param row
     * @param titleDatas
     */
    private static void setTitle(HSSFRow row,String[] titleDatas){
        for (int i = 0; i < titleDatas.length; i++) {
            row.createCell(i).setCellValue(titleDatas[i]);
        }
    }
    private static void covernData(HSSFRow row,Object obj) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        int i=0;
        HSSFCell cell=null;
        for (Field field:fields) {
            field.setAccessible(true);
            if (field.get(obj)!=null){
                cell= row.createCell(i++);
                cell.setCellValue(field.get(obj).toString());
            }
        }
    }
}
