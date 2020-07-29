package com.haenandong.util.excel;

import lombok.extern.slf4j.Slf4j;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class MakeEncryptionExcel {

    public static void main(String[] args) {
        final String password = "1234";
        final String savePath = "/Users/ran/temp/encExcel.xlsx";

        List<String> tmpData = Arrays.asList("Java", "JavaScript", "Python", "GoLang");

        try {
            // apache-poi 3.17 까지 정상동작
            Workbook workbook = new XSSFWorkbook();
            //XSSFWorkbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(savePath);

            Sheet sheet = workbook.createSheet("SHEET1");

            // Write Excel File
            for (int i = 0; i < tmpData.size(); i++) {
                String data = tmpData.get(i);

                Row row = sheet.createRow(i);
                Cell cell = row.createCell(0);

                cell.setCellValue(data);
            }

            workbook.write(byteArrayOutputStream);

            InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            OPCPackage opcPackage = OPCPackage.open(inputStream);

            POIFSFileSystem poifsFileSystem = new POIFSFileSystem();

            EncryptionInfo encryptionInfo = new EncryptionInfo(EncryptionMode.agile);
            Encryptor encryptor = encryptionInfo.getEncryptor();
            encryptor.confirmPassword(password);

            opcPackage.save(encryptor.getDataStream(poifsFileSystem));
            poifsFileSystem.writeFilesystem(fileOutputStream);

            log.info("Create Excel File!!");

        } catch(IOException e) {
            log.error(e.getMessage(), e);
        } catch(InvalidFormatException e) {
            log.error(e.getMessage(), e);
        } catch(GeneralSecurityException e) {
            log.error(e.getMessage(), e);
        }

    }


}
