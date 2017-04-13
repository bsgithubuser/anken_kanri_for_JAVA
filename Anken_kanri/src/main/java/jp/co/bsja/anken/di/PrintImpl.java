package jp.co.bsja.anken.di;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.co.bsja.anken.common.CommonFunction;
import jp.co.bsja.anken.dto.SessionDto;
import jp.co.bsja.anken.form.PrintForm;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.seasar.framework.beans.util.BeanMap;
import org.seasar.struts.util.ServletContextUtil;

public class PrintImpl implements PrintInterface {

   /**
     * 担当者マスタの担当者名全てを取得します。 .
     *
     * @return nameList 担当者名一覧
     */
  @Override
  public void createPrintList(PrintForm printForm, SessionDto sessionDto) {
    //保持した検索結果
    List<BeanMap> searchResult = sessionDto.searchResultList;
    List<BeanMap> printList = new ArrayList<>();

    //個別印刷
    if (!sessionDto.bulkFlag) {
      //印刷する案件情報IDリスト
      String[] prjIdList = sessionDto.printAnknIds.split(",");

      for (BeanMap map : searchResult) {
        for (String checkedId : prjIdList) {
          int prjId = (int) map.get("prjId");
          int printId = Integer.parseInt(checkedId);
          if (CommonFunction.eq(prjId, printId)) {
            //TODO 期間作成処理
            //TODO 案件名 改行/省略処理
            //TODO 概要 改行/省略処理
            //TODO スキル名の改行はずす
            printList.add(map);
          }
        }
      }
    } else {
      //一括印刷
      for (BeanMap map : searchResult) {
        //TODO 期間作成処理
        //TODO 案件名 改行/省略処理
        //TODO 概要 改行/省略処理
        //TODO スキル名の改行はずす
        printList.add(map);
      }
    }

    printForm.printPrjList = printList;
  }

  //POIテスト用
  @Override
  public void poiTest() throws IOException {
    String SAMPLE_FILE = "/WEB-INF/excel/outPutTemp5.xlsx";
    String outputFilePath = ServletContextUtil.getServletContext().getRealPath(SAMPLE_FILE);
    Workbook book = null;
    FileOutputStream fout = null;

    try {
      book = new SXSSFWorkbook();

      Font font = book.createFont();
      font.setFontName("ＭＳ ゴシック");
      font.setFontHeightInPoints((short) 9);

      DataFormat format = book.createDataFormat();

      //ヘッダ文字列用のスタイル
      CellStyle styleHeader = book.createCellStyle();
      styleHeader.setBorderBottom(BorderStyle.THIN);
      PrintImpl.setBorder(styleHeader, BorderStyle.THIN);
      styleHeader.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
      styleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
      styleHeader.setVerticalAlignment(VerticalAlignment.TOP);
      styleHeader.setFont(font);

      //整数用のスタイル
      CellStyle styleInt = book.createCellStyle();
      PrintImpl.setBorder(styleInt, BorderStyle.THIN);
      styleInt.setDataFormat(format.getFormat("#,##0;-#,##0"));
      styleInt.setVerticalAlignment(VerticalAlignment.TOP);
      styleInt.setFont(font);

      Row row;
      int rowNumber;
      Cell cell;
      int colNumber;
      Sheet sheet;

      sheet = book.createSheet();

      if (sheet instanceof SXSSFSheet) {
        ((SXSSFSheet) sheet).trackAllColumnsForAutoSizing();
      }

      //シート名称の設定
      book.setSheetName(0, "シート1");

      //ヘッダ行の作成
      rowNumber = 0;
      colNumber = 0;
      row = sheet.createRow(rowNumber);
      cell = row.createCell(colNumber++);
      cell.setCellStyle(styleHeader);
      cell.setCellType(CellType.STRING);
      cell.setCellValue("No.");

      //ウィンドウ枠の固定
      sheet.createFreezePane(1, 1);

      //データ行の生成
      rowNumber++;
      row = sheet.createRow(rowNumber);
      cell = row.createCell(colNumber++);
      cell.setCellStyle(styleInt);
      cell.setCellType(CellType.NUMERIC);
      cell.setCellValue(1);

      //ファイル出力
      fout = new FileOutputStream(outputFilePath);
      book.write(fout);
      fout.close();

    }catch(Exception e){
        e.printStackTrace();
        System.out.println("処理が失敗しました");
    }
  }

  private static void setBorder(CellStyle style, BorderStyle border) {
    style.setBorderBottom(border);
    style.setBorderTop(border);
    style.setBorderLeft(border);
    style.setBorderRight(border);
  }

  private static final String[] LIST_ALPHA = {
    "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
    "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
  };

  private static String getExcelColumnString(int column) {
    String result = "";

    if (column >= 0) {
      if (column / PrintImpl.LIST_ALPHA.length > 0) {
        result += getExcelColumnString(column / PrintImpl.LIST_ALPHA.length - 1);
      }
      result += PrintImpl.LIST_ALPHA[column % PrintImpl.LIST_ALPHA.length];
    }

    return result;
  }
}
