package jp.co.bsja.anken.di;


import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.co.bsja.anken.common.CommonFunction;
import jp.co.bsja.anken.dao.AnkenRegisterDao;
import jp.co.bsja.anken.dto.SessionDto;
import jp.co.bsja.anken.entity.TProjInfo;
import jp.co.bsja.anken.entity.TProjSkill;
import jp.co.bsja.anken.form.AnkenRegisterForm;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.beans.util.BeanMap;
import org.seasar.struts.util.ActionMessagesUtil;
import org.seasar.struts.util.RequestUtil;

public class AnkenRegisterImpl implements AnkenRegisterInterface {

  AnkenRegisterDao dao = new AnkenRegisterDao();
  TProjInfo projInfo = new TProjInfo();
  TProjSkill projSkill = new TProjSkill();
  ActionMessages errors = new ActionMessages();

  /**
   * 案件情報登録初期表示を行います。.
   *
   * @param  ankenRegisterForm 案件情報登録フォーム
   * @return JSPファイル名
   */
  @Override
  public String initEntry(AnkenRegisterForm ankenRegisterForm, SessionDto sessionDto) {

    //スキルと担当者と案件IDを画面へ渡す
    List<BeanMap> skillList = dao.selectAll("M_SKILL");
    List<BeanMap> usersList = dao.selectAll("M_USERS");
    int idSeq = dao.getPrjIdSeq();
    String id = String.valueOf(idSeq);
    String fiscalYear = CommonFunction.fiscalYear();
    String stId = fiscalYear + id;
    int prjId = Integer.parseInt(stId);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    int userId = 0;
    for (int i = 0; i < usersList.size(); i++) {
      if (CommonFunction.eq(usersList.get(i).get("userName").toString(), "admin")) {
        userId = CommonFunction.asInt(usersList.get(i).get("userId").toString(), userId);
        break;
      }
    }
    List<BeanMap> skillView =  new ArrayList<BeanMap>();
    for (int i = 0; skillList.size() > i; i++) {
      BeanMap setSkill = new BeanMap();
      setSkill.put("skillId", skillList.get(i).get("skillId"));
      setSkill.put("skillName", skillList.get(i).get("skillName"));
      setSkill.put("checked", "0");
      skillView.add(setSkill);
    }
    sessionDto.skillList = skillView;
    sessionDto.usersList = usersList;
    ankenRegisterForm.skillList = skillView;
    ankenRegisterForm.usersList = usersList;
    ankenRegisterForm.id = prjId;
    ankenRegisterForm.genDate = sdf.format(new java.util.Date().getTime());
    ankenRegisterForm.userId = userId;
//    ankenRegisterForm.skillOtherFlg = "0";
//    ankenRegisterForm.disabledFlg = "disabled";
    sessionDto.editFlg = 0;
    ankenRegisterForm.editFlg = 0;
    ankenRegisterForm.extentionFlg = "0";

    return "ankenRegister.jsp";
  }

  /**
   * 案件情報編集初期表示を行います。.
   *
   * @param  ankenRegisterForm 案件情報登録フォーム
   * @return JSPファイル名
   */
  @Override
  public String initEdit(AnkenRegisterForm ankenRegisterForm, SessionDto sessionDto) {

    //スキルと担当者と案件情報を画面へ渡す
    List<BeanMap> skillList = dao.selectAll("M_SKILL");
    List<BeanMap> usersList = dao.selectAll("M_USERS");
    TProjInfo ankenList = dao.initEdit(ankenRegisterForm.id);


    //案件情報がない場合エラーとする
    if (CommonFunction.empty(ankenList)) {
      //案件情報なし
      errors.add(ActionMessages.GLOBAL_MESSAGE,
          new ActionMessage("MSG_E00006", ankenRegisterForm.id));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);

      return "一覧画面";
    }
    String cmpn = dao.cmpm(ankenList.cmpnId);
    List<BeanMap> dbSkill = dao.ankenSkill(ankenList.prjSklId);
    String update = ankenList.updateDate.toString();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    sessionDto.usersList = usersList;
    ankenRegisterForm.usersList = usersList;
    ankenRegisterForm.updateDate = update;
    ankenRegisterForm.prjName = ankenList.prjName;
    ankenRegisterForm.genDate = sdf.format(ankenList.genDate.getTime());
    ankenRegisterForm.orverview = ankenList.orverview;
    ankenRegisterForm.prjOther = ankenList.other;
    sessionDto.editFlg = 1;
    ankenRegisterForm.editFlg = 1;
    ankenRegisterForm.cmpnName = cmpn;
//    ankenRegisterForm.disabledFlg = "disabled";
    ankenRegisterForm.userId = ankenList.userId;

    List<BeanMap> skillView =  new ArrayList<BeanMap>();

    if (!CommonFunction.empty(skillList)) {
      for (int i = 0; skillList.size() > i; i++) {
        boolean countFlg = false;
        if (!CommonFunction.empty(dbSkill)) {
          for (int j = 0; dbSkill.size() > j; j++) {
            if (skillList.get(i).get("skillId").equals(dbSkill.get(j).get("sklId"))) {
              BeanMap setSkill = new BeanMap();
              setSkill.put("skillId", skillList.get(i).get("skillId"));
              setSkill.put("skillName", skillList.get(i).get("skillName"));
              setSkill.put("checked", "checked");
              skillView.add(setSkill);
              countFlg = true;
//            } else if (dbSkill.get(j).get("sklId").equals(-1)) {
//              ankenRegisterForm.skillOtherFlg = "checked";
//              ankenRegisterForm.skillOther = String.valueOf(dbSkill.get(j).get("other"));
//              ankenRegisterForm.disabledFlg = "";
            }
          }
        }

        if (!countFlg) {
          BeanMap setSkill = new BeanMap();
          setSkill.put("skillId", skillList.get(i).get("skillId"));
          setSkill.put("skillName", skillList.get(i).get("skillName"));
          setSkill.put("checked", "0");
          skillView.add(setSkill);
        }
      }
    }

    sessionDto.skillList = skillView;
    ankenRegisterForm.skillList = skillView;
    if (!CommonFunction.empty(ankenList.periFrom)) {
      ankenRegisterForm.periFrom = sdf.format(ankenList.periFrom.getTime());
    }
    if (!CommonFunction.empty(ankenList.periTo)) {
      ankenRegisterForm.periTo = sdf.format(ankenList.periTo.getTime());
    }
    if (ankenList.longTermFlg == true) {
      ankenRegisterForm.longTermFlg = "checked";
    }
    if (ankenList.sameDayFlg == true) {
      ankenRegisterForm.sameDayFlg = "checked";
    }
    if (ankenList.anyTimeFlg == true) {
      ankenRegisterForm.anyTimeFlg = "checked";
    }
    if (ankenList.extentionFlg == true) {
      ankenRegisterForm.extentionFlg = "1";
    } else {
      ankenRegisterForm.extentionFlg = "0";
    }

    return "ankenRegister.jsp";
  }

  /**
   * 案件情報登録処理を行います。.
   *
   * @param  ankenRegisterForm 案件情報登録フォーム
   * @return JSPファイル名
   */
  @Override
  public String entry(AnkenRegisterForm ankenRegisterForm) {

    //エラーメッセージが残っている場合はクリアする
    if (!errors.isEmpty()) {
      errors.clear();
    }

    //必須入力チェック
    if (CommonFunction.empty(ankenRegisterForm.prjName)) {
      errors.add(ActionMessages.GLOBAL_MESSAGE,
          new ActionMessage("MSG_E00001","案件名"));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);

      return "ankenRegister.jsp";
    }
    if (CommonFunction.empty(ankenRegisterForm.userId)) {
      errors.add(ActionMessages.GLOBAL_MESSAGE,
          new ActionMessage("MSG_E00001","担当者"));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);

      return "ankenRegister.jsp";
    }
    if (CommonFunction.empty(ankenRegisterForm.genDate)) {
      errors.add(ActionMessages.GLOBAL_MESSAGE,
          new ActionMessage("MSG_E00001","発生日"));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);

      return "ankenRegister.jsp";
    }
    if (CommonFunction.empty(ankenRegisterForm.cmpnName)) {
      errors.add(ActionMessages.GLOBAL_MESSAGE,
          new ActionMessage("MSG_E00001","会社名"));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);

      return "ankenRegister.jsp";
    }

    //日付フォーマットチェック
    Pattern fomat = Pattern.compile("^(\\d{4})/(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])$");
    Matcher genDateFomat = fomat.matcher(ankenRegisterForm.genDate);

    if (genDateFomat.find() == false) {
      errors.add(ActionMessages.GLOBAL_MESSAGE,
          new ActionMessage("MSG_E00010"));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);

      return "ankenRegister.jsp";
    }

    if (!CommonFunction.empty(ankenRegisterForm.periFrom)) {
      genDateFomat = fomat.matcher(ankenRegisterForm.periFrom);
      if (genDateFomat.find() == false) {
        errors.add(ActionMessages.GLOBAL_MESSAGE,
            new ActionMessage("MSG_E00010"));
        ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);

        return "ankenRegister.jsp";
      }
    }

    if (!CommonFunction.empty(ankenRegisterForm.periTo)) {
      genDateFomat = fomat.matcher(ankenRegisterForm.periTo);
      if (genDateFomat.find() == false) {
        errors.add(ActionMessages.GLOBAL_MESSAGE,
            new ActionMessage("MSG_E00010"));
        ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);

        return "ankenRegister.jsp";
      }
    }

    //担当者とスキルと会社名が登録されていることを確認する
    int tantoCount = dao.existCheck("M_USERS","USER_ID",ankenRegisterForm.userId);

    if (tantoCount == 0) {
      //担当者なし
      errors.add(ActionMessages.GLOBAL_MESSAGE,
          new ActionMessage("MSG_E00006","選択した担当者"));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);

      return "ankenRegister.jsp";
    }

    List<BeanMap> skillList = dao.selectAll("M_SKILL");

    if (!CommonFunction.empty(ankenRegisterForm.skillId)) {
      int skillCount = 0;
      for (int i = 0;ankenRegisterForm.skillId.length > i;i++) {
        for (int j = 0;skillList.size() > j;j++) {
          String skill = String.valueOf(skillList.get(j).get("skillId"));

          if (CommonFunction.eq(ankenRegisterForm.skillId[i], skill)
              || CommonFunction.eq(ankenRegisterForm.skillId[i], "-1")) {
            //スキルあり
            skillCount++;
          }
        }

        if (skillCount == 0) {
          errors.add(ActionMessages.GLOBAL_MESSAGE,
              new ActionMessage("MSG_E00006","選択したスキル"));
          ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);

          return "ankenRegister.jsp";
        }
      }
    }

    String cmpnId = dao.cmpmCheck(ankenRegisterForm.cmpnName);
    if (CommonFunction.empty(cmpnId)) {
      //会社なし
      errors.add(ActionMessages.GLOBAL_MESSAGE,
          new ActionMessage("MSG_E00006","入力した会社名"));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);

      return "ankenRegister.jsp";
    }

    //案件スキル登録
    Timestamp timestamp = CommonFunction.getBaseDt();

    int prjSkillId = dao.getPrjSkillIdSeq();

    if (!CommonFunction.empty(ankenRegisterForm.skillId)) {
      for (int i = 0;ankenRegisterForm.skillId.length > i;i++) {
        int skillId = Integer.parseInt(ankenRegisterForm.skillId[i]);
        //スキルのその他入力がある場合
//        if (CommonFunction.eq(skillId, -1) && !CommonFunction.empty(ankenRegisterForm.skillOther)) {
//          projSkill.other = ankenRegisterForm.skillOther;
//
//        } else if (CommonFunction.eq(skillId, -1)
//            && CommonFunction.empty(ankenRegisterForm.skillOther)) {
//          //スキルのその他がチェック付いて未入力の場合
//          errors.add(ActionMessages.GLOBAL_MESSAGE,
//              new ActionMessage("MSG_E00001", "チェックが付いているスキルのその他"));
//
//          ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);
//          return "ankenRegister.jsp";
//        }

        projSkill.prjSklId = prjSkillId;
        projSkill.sklId = skillId;
        projSkill.createDate = timestamp ;
        projSkill.updateDate = timestamp ;
        dao.prjSkillEntry(projSkill);
      }
    }

    // 長期・即日・随時のチェックボックスの値が「on」である場合は、 true にする
    boolean longTermFlg = CommonFunction.eq(ankenRegisterForm.longTermFlg, "on");
    boolean sameDayFlg = CommonFunction.eq(ankenRegisterForm.sameDayFlg, "on");
    boolean anyTimeFlg = CommonFunction.eq(ankenRegisterForm.anyTimeFlg, "on");
    // 延長のラジオボタンの値が「1」である場合は、 true にする
    boolean extentionFlg = CommonFunction.eq(ankenRegisterForm.extentionFlg, "1");

    Date periFrom = null;
    Date periTo = null;

    // 発生日・期間開始日・期間終了日のテキストボックスの値を「yyyy/MM/dd」の形式で Date型 にする
    Date genDate = new Date(CommonFunction.parseDate(ankenRegisterForm.genDate,
        CommonFunction.FORMAT_YMD_SLASH).getTime());
    java.util.Date from = CommonFunction.parseDate(ankenRegisterForm.periFrom, CommonFunction.FORMAT_YMD_SLASH);
    if (!CommonFunction.empty(from)) {
      periFrom = new Date(from.getTime());
    }
    java.util.Date to = CommonFunction.parseDate(ankenRegisterForm.periTo, CommonFunction.FORMAT_YMD_SLASH);
    if (!CommonFunction.empty(to)) {
      periTo = new Date(to.getTime());
    }


    //案件情報登録
    projInfo.prjId = ankenRegisterForm.id;
    projInfo.prjName = ankenRegisterForm.prjName;
    projInfo.userId = ankenRegisterForm.userId;
    projInfo.cmpnId = Integer.parseInt(cmpnId);
    projInfo.prjSklId = prjSkillId;
    projInfo.genDate = genDate;
    projInfo.periFrom = periFrom;
    projInfo.periTo = periTo;
    projInfo.longTermFlg = longTermFlg;
    projInfo.sameDayFlg = sameDayFlg;
    projInfo.anyTimeFlg = anyTimeFlg;
    projInfo.extentionFlg = extentionFlg;
    projInfo.orverview = ankenRegisterForm.orverview;
    projInfo.other = ankenRegisterForm.prjOther;
    projInfo.delFlg = false;
    projInfo.createDate = timestamp;
    projInfo.updateDate = timestamp;
    dao.prjEntry(projInfo);

    return "Completion.jsp";
  }

  /**
   * 案件情報編集処理を行います。.
   * @ankenRegisterForm 案件情報登録フォーム
   * @return JSPファイル名
   */
  @Override
  public String edit(AnkenRegisterForm ankenRegisterForm) {

    //エラーメッセージが残っている場合はクリアする
    if (!errors.isEmpty()) {
      errors.clear();
    }

    //必須入力チェック
    if (CommonFunction.empty(ankenRegisterForm.prjName)) {
      errors.add(ActionMessages.GLOBAL_MESSAGE,
          new ActionMessage("MSG_E00001","案件名"));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);

      return "ankenRegister.jsp";
    }
    if (CommonFunction.empty(ankenRegisterForm.userId)) {
      errors.add(ActionMessages.GLOBAL_MESSAGE,
          new ActionMessage("MSG_E00001","担当者"));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);

      return "ankenRegister.jsp";
    }
    if (CommonFunction.empty(ankenRegisterForm.genDate)) {
      errors.add(ActionMessages.GLOBAL_MESSAGE,
          new ActionMessage("MSG_E00001","発生日"));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);

      return "ankenRegister.jsp";
    }
    if (CommonFunction.empty(ankenRegisterForm.cmpnName)) {
      errors.add(ActionMessages.GLOBAL_MESSAGE,
          new ActionMessage("MSG_E00001","会社名"));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);

      return "ankenRegister.jsp";
    }

    //日付フォーマットチェック
    Pattern fomat = Pattern.compile("^(\\d{4})/(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])$");
    Matcher genDateFomat = fomat.matcher(ankenRegisterForm.genDate);

    if (genDateFomat.find() == false) {
      errors.add(ActionMessages.GLOBAL_MESSAGE,
          new ActionMessage("MSG_E00010"));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);

      return "ankenRegister.jsp";
    }

    if (!CommonFunction.empty(ankenRegisterForm.periFrom)) {
      genDateFomat = fomat.matcher(ankenRegisterForm.periFrom);
      if (genDateFomat.find() == false) {
        errors.add(ActionMessages.GLOBAL_MESSAGE,
            new ActionMessage("MSG_E00010"));
        ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);

        return "ankenRegister.jsp";
      }
    }

    if (!CommonFunction.empty(ankenRegisterForm.periTo)) {
      genDateFomat = fomat.matcher(ankenRegisterForm.periTo);
      if (genDateFomat.find() == false) {
        errors.add(ActionMessages.GLOBAL_MESSAGE,
            new ActionMessage("MSG_E00010"));
        ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);

        return "ankenRegister.jsp";
      }
    }

    //更新前チェック
    TProjInfo ankenList = dao.initEdit(ankenRegisterForm.id);

    if (CommonFunction.empty(ankenList)) {
      //論理削除データあり
      errors.add(ActionMessages.GLOBAL_MESSAGE,
          new ActionMessage("MSG_E00006", "案件ID" + ankenRegisterForm.id));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);

      return "ankenRegister.jsp";
    }

    int hasLocked = dao.hasLocked("T_PROJ_INFO", "PRJ_ID",
        String.valueOf(ankenRegisterForm.id),
        Timestamp.valueOf(ankenRegisterForm.updateDate));

    if (hasLocked == 1) {
      //更新されている
      errors.add(ActionMessages.GLOBAL_MESSAGE,
          new ActionMessage("MSG_E00006", "案件ID" + ankenRegisterForm.id));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);

      return "ankenRegister.jsp";
    }

    //担当者とスキルと会社名が登録されていることを確認する
    int tantoCount = dao.existCheck("M_USERS", "USER_ID", ankenRegisterForm.userId);

    if (tantoCount == 0) {
      //担当者なし
      errors.add(ActionMessages.GLOBAL_MESSAGE,
          new ActionMessage("MSG_E00006", "選択した担当者"));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);

      return "ankenRegister.jsp";
    }

    List<BeanMap> skillList = dao.selectAll("M_SKILL");

    if (!CommonFunction.empty(ankenRegisterForm.skillId)) {
      int skillCount = 0;
      for (int i = 0;ankenRegisterForm.skillId.length > i;i++) {
        for (int j = 0;skillList.size() > j;j++) {
          String skill = String.valueOf(skillList.get(j).get("skillId"));

          if (CommonFunction.eq(ankenRegisterForm.skillId[i], skill)
              || CommonFunction.eq(ankenRegisterForm.skillId[i], "-1")) {
            //スキルあり
            skillCount++;
          }
        }

        if (skillCount == 0) {
          errors.add(ActionMessages.GLOBAL_MESSAGE,
              new ActionMessage("MSG_E00006","選択したスキル"));
          ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);

          return "ankenRegister.jsp";
        }
      }
    }

    String cmpnId = dao.cmpmCheck(ankenRegisterForm.cmpnName);

    if (CommonFunction.empty(cmpnId)) {
      //会社なし
      errors.add(ActionMessages.GLOBAL_MESSAGE,
          new ActionMessage("MSG_E00006", "入力した会社名"));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);
      return "ankenRegister.jsp";
    }

    //案件スキルを削除
    TProjInfo ankenSkillList = dao.initEdit(ankenList.prjId);

    projSkill.prjSklId = ankenSkillList.prjSklId;
    dao.delete(projSkill.prjSklId);

    //案件スキルを編集
    Timestamp timestamp = CommonFunction.getBaseDt();

    if (!CommonFunction.empty(ankenRegisterForm.skillId)) {
      for (int i = 0; ankenRegisterForm.skillId.length > i; i++) {
        int skillId = Integer.parseInt(ankenRegisterForm.skillId[i]);

        //スキルのその他入力がある場合
//        if (CommonFunction.eq(skillId, -1) && !CommonFunction.empty(ankenRegisterForm.skillOther)) {
//          projSkill.other = ankenRegisterForm.skillOther;
//
//        } else if (CommonFunction.eq(skillId, -1)
//            && CommonFunction.empty(ankenRegisterForm.skillOther)) {
//          //スキルのその他がチェック付いて未入力の場合
//          errors.add(ActionMessages.GLOBAL_MESSAGE,
//              new ActionMessage("MSG_E00001", "チェックが付いているスキルのその他"));
//
//          ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);
//          return "ankenRegister.jsp";
//        }

        projSkill.prjSklId = projSkill.prjSklId;
        projSkill.sklId = skillId;
        projSkill.createDate = timestamp;
        projSkill.updateDate = timestamp;
        dao.prjSkillEntry(projSkill);
      }
    }

    // 長期・即日・随時のチェックボックスの値が「on」である場合は、 true にする
    boolean longTermFlg = CommonFunction.eq(ankenRegisterForm.longTermFlg, "on");
    boolean sameDayFlg = CommonFunction.eq(ankenRegisterForm.sameDayFlg, "on");
    boolean anyTimeFlg = CommonFunction.eq(ankenRegisterForm.anyTimeFlg, "on");
    // 延長のラジオボタンの値が「1」である場合は、 true にする
    boolean extentionFlg = CommonFunction.eq(ankenRegisterForm.extentionFlg, "1");

    Date periFrom = null;
    Date periTo = null;

    // 発生日・期間開始日・期間終了日のテキストボックスの値を「yyyy/MM/dd」の形式で Date型 にする
    Date genDate = new Date(CommonFunction.parseDate(ankenRegisterForm.genDate,
        CommonFunction.FORMAT_YMD_SLASH).getTime());
    java.util.Date from = CommonFunction.parseDate(ankenRegisterForm.periFrom, CommonFunction.FORMAT_YMD_SLASH);
    if (!CommonFunction.empty(from)) {
      periFrom = new Date(from.getTime());
    }
    java.util.Date to = CommonFunction.parseDate(ankenRegisterForm.periTo, CommonFunction.FORMAT_YMD_SLASH);
    if (!CommonFunction.empty(to)) {
      periTo = new Date(to.getTime());
    }

    //案件情報更新
    projInfo.prjId = ankenRegisterForm.id;
    projInfo.prjName = ankenRegisterForm.prjName;
    projInfo.userId = ankenRegisterForm.userId;
    projInfo.prjSklId = projSkill.prjSklId;
    projInfo.cmpnId = Integer.parseInt(cmpnId);;
    projInfo.genDate = genDate;
    projInfo.periFrom = periFrom;
    projInfo.periTo = periTo;
    projInfo.longTermFlg = longTermFlg;
    projInfo.sameDayFlg = sameDayFlg;
    projInfo.anyTimeFlg = anyTimeFlg;
    projInfo.extentionFlg = extentionFlg;
    projInfo.orverview = ankenRegisterForm.orverview;
    projInfo.other = ankenRegisterForm.prjOther;
    projInfo.createDate = ankenList.createDate;
    projInfo.updateDate = timestamp;
    projInfo.delFlg = false;
    dao.uptate(projInfo);

    return "Completion.jsp";
  }

  /**
   * 画面入力値保持処理を行います。.
   * @ankenRegisterForm 案件情報登録フォーム
   */
  @Override
  public void preserve(AnkenRegisterForm ankenRegisterForm, SessionDto sessionDto) {
    // セッションから処理する内容が登録なのか更新なのか示す値を取得する
    int editFlg = sessionDto.editFlg;
    // セッションから初期表示時のスキルリストと担当者リストを取得する
    List<BeanMap> skillList = sessionDto.skillList;
    List<BeanMap> usersList = sessionDto.usersList;
    // フォームから長期・即日・随時の各チェックボックスの値を取得する
    String longTermFlg = ankenRegisterForm.longTermFlg;
    String sameDayFlg = ankenRegisterForm.sameDayFlg;
    String anyTimeFlg = ankenRegisterForm.anyTimeFlg;
    // フォームから各スキルのチェックボックスの値を取得し、String型 のリストにした変数を作成する
    List<String> skillId;
    if (ankenRegisterForm.skillId != null) {
      skillId = Arrays.asList(ankenRegisterForm.skillId);
    } else {
      skillId = new ArrayList<String>();
    }
    // 各スキルのチェックボックスのチェック状態を保持したリストを作成する
    List<BeanMap> skillView =  new ArrayList<BeanMap>();
    for (int i = 0; skillList.size() > i; i++) {
      BeanMap setSkill = skillList.get(i);
      if (skillId.contains(skillList.get(i).get("skillId").toString())) {
        setSkill.replace("checked", "checked");
      } else {
        setSkill.replace("checked", "");
      }
      skillView.add(setSkill);
    }

    // 処理する内容が登録なのか更新なのか示す値をフォームに反映させる
    ankenRegisterForm.editFlg = editFlg;
    // 長期・即日・随時のチェックボックスのチェック状態をフォームに反映させる
    if (CommonFunction.eq(longTermFlg, "on")) {
      ankenRegisterForm.longTermFlg = "checked";
    }
    if (CommonFunction.eq(sameDayFlg, "on")) {
      ankenRegisterForm.sameDayFlg = "checked";
    }
    if (CommonFunction.eq(anyTimeFlg, "on")) {
      ankenRegisterForm.anyTimeFlg = "checked";
    }
    // チェック状態を保持したスキルリストをフォームに反映させる
    ankenRegisterForm.skillList = skillView;
    // 初期表示時の担当者リストをフォームに反映させる
    ankenRegisterForm.usersList = usersList;
  }
}
