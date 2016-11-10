package jp.co.bsja.anken.csv;

import java.io.Serializable;

import org.seasar.s2csv.csv.annotation.column.CSVColumn;
import org.seasar.s2csv.csv.annotation.column.CSVMaxLength;
import org.seasar.s2csv.csv.annotation.column.CSVRequired;
import org.seasar.s2csv.csv.annotation.entity.CSVEntity;

@CSVEntity(header = false, columnCountCheck = true)
public class AnkenKanriCsv implements Serializable{

  /** 番号 .*/
  @CSVColumn(columnIndex = 0, columnName = "No")
  public String no;

  /** 担当者名 .*/
  @CSVRequired
  @CSVMaxLength(maxlength = 30)
  @CSVColumn(columnIndex = 1, columnName = "担当者名")
  public String userName;

  /** 発生日 .*/
  @CSVRequired
  @CSVMaxLength(maxlength = 10)
  @CSVColumn(columnIndex = 2, columnName = "発生日")
  public String genDate;

  /** 会社名 .*/
  @CSVRequired
  @CSVMaxLength(maxlength = 60)
  @CSVColumn(columnIndex = 3, columnName = "会社名")
  public String cmpnName;

  /** 案件名 .*/
  @CSVRequired
  @CSVMaxLength(maxlength = 200)
  @CSVColumn(columnIndex = 4, columnName = "案件名")
  public String prjName;

  /** 概要 .*/
  @CSVRequired
  @CSVMaxLength(maxlength = 2000)
  @CSVColumn(columnIndex = 5, columnName = "概要")
  public String overview;

  /** 期間(開始日) .*/
  @CSVMaxLength(maxlength = 10)
  @CSVColumn(columnIndex = 6, columnName = "期間開始日")
  public String periodFrom;

  /** 期間(終了日) .*/
  @CSVMaxLength(maxlength = 10)
  @CSVColumn(columnIndex = 7, columnName = "期間終了日")
  public String periodTo;

  /** 随時フラグ .*/
  @CSVColumn(columnIndex = 8, columnName = "随時フラグ")
  public String anyTimeFlg;

  /** 即日フラグ .*/
  @CSVColumn(columnIndex = 9, columnName = "即日フラグ")
  public String sameDayFlg;

  /** 長期フラグ .*/
  @CSVColumn(columnIndex = 10, columnName = "長期フラグ")
  public String longTermFlg;

  /** 延長フラグ .*/
  @CSVColumn(columnIndex = 11, columnName = "延長フラグ")
  public String extentionFlg ;

  /** 言語 .*/
  @CSVRequired
  @CSVColumn(columnIndex = 12, columnName = "言語")
  public String skillName;

  /** その他 .*/
  @CSVMaxLength(maxlength = 1000)
  @CSVColumn(columnIndex = 13, columnName = "その他")
  public String other;
}
