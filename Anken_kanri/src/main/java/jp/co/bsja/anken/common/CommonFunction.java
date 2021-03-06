package jp.co.bsja.anken.common;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 案件情報管理システムにおける共通処理を持つクラス .
 *
 * @author Akira Murakami
 */
public class CommonFunction {
  public static final String FORMAT_YMD_SLASH = "yyyy/MM/dd";
  public static final String FORMAT_YMD_HYPHEN = "yyyy-MM-dd";
  public static final String FORMAT_YMDHMS_SLASH = "yyyy/MM/dd HH:mm:ss";
  public static final String FORMAT_YMDHMS_HYPEN = "yyyy-MM-dd HH:mm:ss";
  public static final String FORMAT_YMDHMSS_SLASH = "yyyy/MM/dd HH:mm:ss.SSS";
  public static final String FORMAT_YMDHMSS_HYPEN = "yyyy-MM-dd HH:mm:ss.SSS";

  /**
   * オブジェクト単位で比較を行う共通メソッドです .
   *
   * @param src 比較元
   * @param dest 比較対象
   * @return 比較結果
   */
  public static boolean eq(Object src, Object dest) {
    if (src == null && dest == null) {
      return true;
    }
    if (src == null || dest == null) {
      return false;
    }
    return src.equals(dest);
  }

  /**
   * srcに指定したオブジェクトと等価のtargsがあればtrueを返却します .
   *
   * @param src 比較元
   * @param targs 比較対象
   * @return 比較結果
   */
  public static boolean eqAny(Object src, Object... targs) {
    for (Object o : targs) {
      if (eq(src, o)) {
        return true;
      }
    }
    return false;
  }

  /**
   * srcに指定したオブジェクトと等価のtargsがあればtrueを返却します .
   *
   * @param src 比較元
   * @param targs 比較対象
   * @return 比較結果
   */
  public static boolean eqAnyFromList(Object src, List<?> targs) {
    if (targs == null) {
      if (src == null) {
        return true;
      } else {
        return false;
      }
    }
    for (Object o : targs) {
      if (eq(src, o)) {
        return true;
      }
    }
    return false;
  }

  /**
   * オブジェクトの内容がnull, 空の場合にtrueを返却します .
   *
   * @param src 対象
   * @return 空判定結果
   */
  public static boolean empty(Object src) {
    if (src == null) {
      return true;
    }
    if (src instanceof List) {
      return ((List<?>) src).isEmpty();
    }
    if (src instanceof Map<?, ?>) {
      return ((Map<?, ?>) src).isEmpty();
    }
    if (src instanceof String) {
      return ((String) src).isEmpty();
    }
    return false;
  }

  /**
   * 指定した可変オブジェクトが全てnullまたは空の場合にtrueを返却します .
   *
   * @param srcs 対象
   * @return 空判定結果
   */
  public static boolean emptyAll(Object... srcs) {
    if (srcs == null) {
      return true;
    }
    for (Object o : srcs) {
      if (!empty(o)) {
        return false;
      }
    }
    return true;
  }

  /**
   * targがnullの場合、デフォルト値defを返却します .
   *
   * @param targ nvlの対象
   * @param def デフォルト値
   * @return targまたはdef
   */
  public static Object nvl(Object targ, Object def) {
    if (targ == null) {
      return def;
    }
    return targ;
  }

  /**
   * <pre>
   * 各オブジェクトをStringに変換します .
   * 想定以外のオブジェクトの場合、nullの場合は空文字を返却します。
   * </pre>
   *
   * @param targ 変換対象
   * @return 文字列
   */
  public static String asString(Object targ) {
    if (empty(targ)) {
      return "";
    }
    if (targ instanceof String) {
      return (String) targ;
    }
    if (targ instanceof Integer) {
      return ((Integer) targ).toString();
    }
    if (targ instanceof Long) {
      return ((Long) targ).toString();
    }
    if (targ instanceof Double) {
      return ((Double) targ).toString();
    }
    if (targ instanceof BigDecimal) {
      return ((BigDecimal) targ).toString();
    }
    return "";
  }

  /**
   * <pre>
   * 文字列targをint値に変換して返却します .
   * 変換出来ない場合はデフォルト値defを返却します
   * </pre>
   *
   * @param targ 変換対象文字列
   * @param def デフォルト値
   * @return int変換した値またはデフォルト値
   */
  public static int asInt(String targ, int def) {
    if (targ == null) {
      return def;
    }
    int parsed = 0;
    try {
      parsed = Integer.parseInt(targ);
    } catch (Exception e) {
      return def;
    }
    return parsed;
  }

  /**
   * <pre>
   * 文字列日付をDateに変換して返却します .
   * 変換に失敗した場合は例外をthrowします
   * </pre>
   *
   * @param targ 変換対象オブジェクト
   * @param fmt 日付フォーマッタ
   * @return 変換結果
   */
  public static Date formatDate(String targ, String fmt) throws ParseException {
    if (empty(targ)) {
      return null;
    }
    SimpleDateFormat sdf = new SimpleDateFormat(fmt);
    return sdf.parse((String) targ);
  }

  /**
   * 現在時刻をTimestampで取得します .
   *
   * @return Timestamp型の現在時刻
   */
  public static Timestamp getBaseDt() {
    return new Timestamp(System.currentTimeMillis());
  }

  /**
   * パスワードハッシュ化処理を行います。 .
   *
   * @param algorithmName ハッシュ化アルゴリズム .
   * @param value  パスワード
   * @return ハッシュ化により出来た64byte文字列
   */
  public static String toEncryptedHashValue(String algorithmName, String value) {
    MessageDigest md = null;
    StringBuilder sb = null;
    try {
      md = MessageDigest.getInstance(algorithmName);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    md.update(value.getBytes());
    sb = new StringBuilder();
    for (byte b : md.digest()) {
      String hex = String.format("%02x", b);
      sb.append(hex);
    }
    return sb.toString();
  }

  /**
   * 年度取得.
   * @return 年度
   */
  public static String fiscalYear() {
    //現在日時を取得する
    Calendar time = Calendar.getInstance();
    String fiscalYear = "";
    int year = 0;
    //フォーマットパターンを指定して表示する
    SimpleDateFormat yyyyTime = new SimpleDateFormat("yyyy");
    SimpleDateFormat mmTime = new SimpleDateFormat("MM");
    int month = Integer.parseInt(mmTime.format(time.getTime()));

    if (month < 4) {
      year = Integer.parseInt(yyyyTime.format(time.getTime()));
      year = year - 1;
    } else {
      year = Integer.parseInt(yyyyTime.format(time.getTime()));
    }
    fiscalYear = String.valueOf(year);
    return fiscalYear;
  }

  /**
   * 文字列を指定された形式で日付に変換して返します。.<br>
   * 変換に失敗した場合は null を返します。.<br>
   * 日付フォーマッタは日付の形式になっていなかった場合、「yyyy/MM/dd」を指定します。.
   *
   * @param targ 日付にする文字列
   * @param fmt 日付フォーマッタ
   * @return 変換した日付または null
   */
  public static Date parseDate(String targ, String fmt) {
    // 引数の日付にする文字列が空の場合は null を返す
    if (empty(targ)) {
      return null;
    }

    // 引数の日付フォーマッタでフォーマットを指定する
    // 日付フォーマッタが日付の形式ではなかった場合は「yyyy/MM/dd」にする
    SimpleDateFormat sdf;
    if (!empty(fmt)) {
      if (eqAny(fmt, FORMAT_YMD_SLASH, FORMAT_YMD_HYPHEN, FORMAT_YMDHMS_SLASH,
          FORMAT_YMDHMS_HYPEN, FORMAT_YMDHMSS_SLASH, FORMAT_YMDHMSS_HYPEN)) {
        sdf = new SimpleDateFormat(fmt);
      } else {
        sdf = new SimpleDateFormat(FORMAT_YMD_SLASH);
      }
    } else {
      sdf = new SimpleDateFormat(FORMAT_YMD_SLASH);
    }

    // 指定されたフォーマットに従って文字列を日付に変換して返す
    // 変換に失敗した場合は null を返す
    try {
      return sdf.parse(targ);
    } catch (ParseException e) {
      return null;
    }
  }
}
