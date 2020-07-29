package com.haenandong.util.excel;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Slf4j
public class ApachePOI {
    /*
    Apache POI 하위 컴포넌트
        POIFS : MS 오피스의 OLE2 Compound Document 파일 포맷을 읽고 쓰는 컴포넌트입니다. 모든 오피스 파일 포맷은 OLE2 방식이므로 하위 모든 컴포넌트의 기반이 됩니다.
        HSSF : 엑셀 파일포맷을 읽고 쓰는 컴포넌트로서 엑셀 97버전부터 현재까지 지원합니다.
        XSSF : 엑셀 2007부터 지원하는 OOXML 파일 포맷인 *.xlsx 파일을 읽고 쓰는 컴포넌트입니다.
        HSLF : 파워포인트 파일을 읽고 쓰는데 사용하는 컴포넌트입니다.
        HDGF : 비지오 파일을 읽는 데에 사용하는 컴포넌트입니다.
        HPBF : 퍼블리셔 파일을 다루는데 사용되는 컴포넌트입니다.
        HPSF : 오피스 파일의 문서요약 정보를 읽는 데에 사용되는 컴포넌트입니다.
    */

    private static final String FILE_PATH = "/Users/ran/temp/excel.xlsx";

    public static void main(String[] args) {
        read();

    }

    public static void read() {
        try {
            //엑셀 파일 읽는 순서
            //XSSFWorkbook -> XSSFSheet -> XSSFRow -> XSSFCell
            File file = new File(FILE_PATH);
            // 엑셀파일 전체 내용을 담고 있는 객체
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(file));

            // 탐색에 사용할 sheet 객체
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);

            for(Row row : xssfSheet) {
                for(Cell cell : row) {
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            log.info(cell.getStringCellValue());
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            log.info(String.valueOf(cell.getNumericCellValue()));
                            break;
                    }
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public static void read(int rowIndex, int cellIndex) {

    }




}
