package com.ou.learn.work.tool;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by weouyang on 26/07/2017.
 */
public class ExelParser {

    public static void main(String[] args) {
        System.out.println(" tests ");
        List<String[]> list = readVars("/Users/weouyang/Documents/doc/toEveVars.xlsx",0);
        System.out.println(list.size());
        for (String[] s : list) {
            System.out.println(s);
        }
    }

    public String file_url ;
    public int varTypeIndex;

    public ExelParser(String file_url,int varTypeIndex){
        this.file_url = file_url;
        this.varTypeIndex = varTypeIndex;
    }

    public List<String[]> readVars(){
        return readVars(file_url,varTypeIndex);
    }

    /**
     * 从excel获取jira请求中的radd variable enum
     *
     * @param file_url
     * @return
     */
    public static List<String[]> readVars(String file_url,int varTypeIndex) {
        List<String[]> list = new ArrayList<String[]>();
        Workbook readwb = null;
        InputStream instream = null;
        try {
            if (StringUtils.isEmpty(file_url)) {
                return null;
            }
            //构建Workbook对象, 只读Workbook对象
            //直接从本地文件创建Workbook
            instream = new FileInputStream(file_url);
            readwb = new XSSFWorkbook(instream);

            //Sheet的下标是从0开始
            Sheet readRaddsheet = readwb.getSheetAt(varTypeIndex);
            if (readRaddsheet == null)
                return list;

            //获取Sheet表中所包含的最后一行的index（第一行为0）
            int rsRows = readRaddsheet.getLastRowNum();
            if (rsRows < 0)
                return list;

            Row headRow = readRaddsheet.getRow(0);
            int varCount = headRow.getLastCellNum() - headRow.getFirstCellNum() + 1;
            if (varCount < 1)
                return list;

            //获取指定单元格的对象引用
            for (int i = 1; i < rsRows + 1; i++) {
                Row row = readRaddsheet.getRow(i);
                String[] varValues = new String[varCount];
                for (int j = 0;j<varCount;j++) {
                    Cell cell = row.getCell(j);
                    if (cell == null)
                        varValues[j] = null;
                    else
                        varValues[j] = cell.getStringCellValue();
                }
                list.add(varValues);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (readwb != null)
                    readwb.close();
                if (instream != null)
                    instream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(ExelParser.class+":list size : " + list.size());
        return list;
    }
}
