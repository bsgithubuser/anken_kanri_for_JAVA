package jp.co.bsja.anken.di;


import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.co.bsja.anken.common.CommonFunction;
import jp.co.bsja.anken.dao.AnkenRegisterDao;
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
  public String initEntry(AnkenRegisterForm ankenRegisterForm) {

    //スキルと担当者と案件IDを画面へ渡す
    List<BeanMap> skillList = dao.selectAll("M_SKILL");
    List<BeanMap> usersList = dao.selectAll("M_USERS");
    int idSeq = dao.getPrjIdSeq();
    String id = String.valueOf(idSeq);
    String fiscalYear = CommonFunction.fiscalYear();
    String stId = fiscalYear + id;
    int prjId = Integer.parseInt(stId);

    ankenRegisterForm.skillList = skillList;
    ankenRegisterForm.usersList = usersList;
    ankenRegisterForm.id = prjId;
    ankenRegisterForm.editFlg = 0;

    return "ankenRegister.jsp";
  }

  /**
   * 案件情報編集初期表示を行います。.
   *
   * @param  ankenRegisterForm 案件情報登録フォーム
   * @return JSPファイル名
   */
  @Override
  public String initEdit(AnkenRegisterForm ankenRegisterForm) {

    //スキルと担当者と案件情報を画面へ渡す
    List<BeanMap> skillList;
    skillList = dao.selectAll("M_SKILL");
    List<BeanMap> userList;
    userList = dao.selectAll("M_USERS");
    TProjInfo ankenList = dao.initEdit(ankenRegisterForm.id);
    String cmpn;
    cmpn = dao.cmpm(ankenList.cmpnId);
    String update;

    //案件情報がない場合エラーとする
    if (CommonFunction.empty(ankenList)) {
      //案件情報なし
      errors.add(ActionMessages.GLOBAL_MESSAGE,
          new ActionMessage("MSG_E00006", ankenRegisterForm.id));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);

      return "一覧画面";
    }
    update = ankenList.updateDate.toString();
    SimpleDateFormat sd;
    sd = new SimpleDateFormat("yyyy/MM/dd");

    ankenRegisterForm.skillList = skillList;
    ankenRegisterForm.usersList = userList;
    ankenRegisterForm.ankenList = ankenList;
    ankenRegisterForm.updateDate = update;
    ankenRegisterForm.prjName = ankenList.prjName;
    ankenRegisterForm.genDate = sd.format(ankenList.genDate.getTime()).toString();
    ankenRegisterForm.orverview = ankenList.orverview;
    ankenRegisterForm.prjOther = ankenList.other;
    ankenRegisterForm.editFlg = 1;
    ankenRegisterForm.cmpnName = cmpn;
    if (!CommonFunction.empty(ankenList.periFrom)) {
      ankenRegisterForm.periFrom = sd.format(ankenList.periFrom.getTime()).toString();
    }
    if (!CommonFunction.empty(ankenList.periTo)) {
      ankenRegisterForm.periTo = sd.format(ankenList.periTo.getTime()).toString();
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
          new ActionMessage("MSG_E0006","選択した担当者"));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);

      return "ankenRegister.jsp";
    }

    List<BeanMap> skillList = dao.selectAll("M_SKILL");

    if (!CommonFunction.empty(ankenRegisterForm.skillId)) {
      int skillCount = 0;
      for (int i = 0;ankenRegisterForm.skillId.length > i;i++) {
        for (int j = 0;skillList.size() > j;j++) {
          String skill = String.valueOf(skillList.get(j).get("skillId"));
          if (skill.equals(ankenRegisterForm.skillId[i])) {
            //スキルあり
            skillCount++;
          }
        }
        if (skillCount == 0) {
          errors.add(ActionMessages.GLOBAL_MESSAGE,
              new ActionMessage("MSG_E0006","選択したスキル"));
          ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);

          return "ankenRegister.jsp";
        }
      }
    }

    String cmpnId = dao.cmpmCheck(ankenRegisterForm.cmpnName);
    if (CommonFunction.empty(cmpnId)) {
      //会社なし
      errors.add(ActionMessages.GLOBAL_MESSAGE,
          new ActionMessage("MSG_E0006","入力した会社名"));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);

      return "ankenRegister.jsp";
    }

    //案件スキル登録
    Timestamp timestamp = CommonFunction.getBaseDt();

    int prjSkillId = dao.getPrjSkillIdSeq();
    for (int i = 0;ankenRegisterForm.skillId.length > i;i++) {
      int skillId = Integer.parseInt(ankenRegisterForm.skillId[i]);
      //スキルのその他入力がある場合
      if (skillId == -1 && !CommonFunction.empty(ankenRegisterForm.skillOther)) {
        projSkill.other = ankenRegisterForm.skillOther;
      }

      projSkill.prjSklId = prjSkillId;
      projSkill.sklId = skillId;
      projSkill.createDate = timestamp ;
      projSkill.updateDate = timestamp ;
      dao.prjSkillEntry(projSkill);
    }

    boolean longTermFlg = false;
    boolean sameDayFlg = false;
    boolean anyTimeFlg = false;
    boolean extentionflg = false;

    if (ankenRegisterForm.longTermFlg.equals("1")) {
      longTermFlg = true;
    }
    if (ankenRegisterForm.sameDayFlg.equals("1")) {
      sameDayFlg = true;
    }
    if (ankenRegisterForm.anyTimeFlg.equals("1")) {
      anyTimeFlg = true;
    }
    if (ankenRegisterForm.extentionFlg.equals("1")) {
      extentionflg = true;
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    Date genDate = null;
    Date from = null;
    Date to = null;
    try {
      genDate = (Date) sdf.parse(ankenRegisterForm.genDate);
      from = (Date) sdf.parse(ankenRegisterForm.periFrom);
      to = (Date) sdf.parse(ankenRegisterForm.periTo);
    } catch (ParseException e) {
      // TODO 自動生成された catch ブロック
      e.printStackTrace();
    }


    //案件情報登録
    projInfo.prjId = ankenRegisterForm.id;
    projInfo.prjName = ankenRegisterForm.prjName;
    projInfo.userId = ankenRegisterForm.userId;
    projInfo.cmpnId = Integer.parseInt(cmpnId);
    projInfo.prjSklId = prjSkillId;
    projInfo.genDate = genDate;
    projInfo.periFrom = from;
    projInfo.periTo = to;
    projInfo.longTermFlg = longTermFlg;
    projInfo.sameDayFlg = sameDayFlg;
    projInfo.anyTimeFlg = anyTimeFlg;
    projInfo.extentionFlg = extentionflg;
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

    //更新前チェック
    TProjInfo ankenList = dao.initEdit(ankenRegisterForm.id);

    if (CommonFunction.empty(ankenList)) {
      //論理削除データあり
      errors.add(ActionMessages.GLOBAL_MESSAGE,
          new ActionMessage("MSG_E0006", "案件ID" + ankenRegisterForm.id));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);

      return "ankenRegister.jsp";
    }

    int hasLocked = dao.hasLocked("T_PROJ_INFO", "PRJ_ID",
        String.valueOf(ankenRegisterForm.id),
        Timestamp.valueOf(ankenRegisterForm.updateDate));

    if (hasLocked == 1) {
      //更新されている
      errors.add(ActionMessages.GLOBAL_MESSAGE,
          new ActionMessage("MSG_E0006", "案件ID" + ankenRegisterForm.id));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);

      return "ankenRegister.jsp";
    }

    //担当者とスキルと会社名が登録されていることを確認する
    int tantoCount = dao.existCheck("M_USERS", "USER_ID", ankenRegisterForm.userId);

    if (tantoCount == 0) {
      //担当者なし
      errors.add(ActionMessages.GLOBAL_MESSAGE,
          new ActionMessage("MSG_E0006", "選択した担当者"));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);

      return "ankenRegister.jsp";
    }

    List<BeanMap> skillList = dao.selectAll("M_SKILL");

    if (!CommonFunction.empty(ankenRegisterForm.skillId)) {
      int skillCount = 0;
      for (int i = 0;ankenRegisterForm.skillId.length > i;i++) {
        for (int j = 0;skillList.size() > j;j++) {
          String skill = String.valueOf(skillList.get(j).get("skillId"));
          if (skill.equals(ankenRegisterForm.skillId[i])) {
            //スキルあり
            skillCount++;
          }
        }
        if (skillCount == 0) {
          errors.add(ActionMessages.GLOBAL_MESSAGE,
              new ActionMessage("MSG_E0006","選択したスキル"));
          ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);

          return "ankenRegister.jsp";
        }
      }
    }

    String cmpnId = dao.cmpmCheck(ankenRegisterForm.cmpnName);

    if (CommonFunction.empty(cmpnId)) {
      //会社なし
      errors.add(ActionMessages.GLOBAL_MESSAGE,
          new ActionMessage("MSG_E0006", "入力した会社名"));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);
      return "ankenRegister.jsp";
    }

    //案件スキルを削除
    TProjInfo ankenSkillList =
        dao.initEdit(ankenList.prjId);

    projSkill.prjSklId = ankenSkillList.prjSklId;
    dao.delete(projSkill.prjSklId);

    //案件スキルを編集
    Timestamp timestamp = CommonFunction.getBaseDt();

    for (int i = 0; ankenRegisterForm.skillId.length > i; i++) {
      int skillId = Integer.parseInt(ankenRegisterForm.skillId[i]);
      //スキルのその他入力がある場合
      if (skillId == -1 && !CommonFunction.empty(ankenRegisterForm.skillOther)) {
        projSkill.other = ankenRegisterForm.skillOther;
      }

      projSkill.prjSklId = projSkill.prjSklId;
      projSkill.sklId = skillId;
      projSkill.createDate = timestamp;
      projSkill.updateDate = timestamp;
      dao.prjSkillEntry(projSkill);
    }
    boolean longTermFlg = false;
    boolean sameDayFlg = false;
    boolean anyTimeFlg = false;
    boolean extentionflg = false;
    if (ankenRegisterForm.longTermFlg.equals("1")) {
      longTermFlg = true;
    }
    if (ankenRegisterForm.sameDayFlg.equals("1")) {
      sameDayFlg = true;
    }
    if (ankenRegisterForm.anyTimeFlg.equals("1")) {
      anyTimeFlg = true;
    }
    if (ankenRegisterForm.extentionFlg.equals("1")) {
      extentionflg = true;
    }

    DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    Date genDate = null;
    Date from = null;
    Date to = null;
    try {
      java.util.Date genSdf = sdf.parse(ankenRegisterForm.genDate);
      genDate = new Date(genSdf.getTime());
      if (!CommonFunction.empty(ankenRegisterForm.periFrom)) {
        java.util.Date fromSdf = sdf.parse(ankenRegisterForm.periFrom);
        from = new Date(fromSdf.getTime());
      }
      if (!CommonFunction.empty(ankenRegisterForm.periTo)) {
        java.util.Date toSdf = sdf.parse(ankenRegisterForm.periTo);
        to = new Date(toSdf.getTime());
      }
    } catch (ParseException e) {
      // TODO 自動生成された catch ブロック
      e.printStackTrace();
    }

    //案件情報更新
    projInfo.prjId = ankenRegisterForm.id;
    projInfo.prjName = ankenRegisterForm.prjName;
    projInfo.userId = ankenRegisterForm.userId;
    projInfo.prjSklId = projSkill.prjSklId;
    projInfo.cmpnId = Integer.parseInt(cmpnId);;
    projInfo.genDate = genDate;
    projInfo.periFrom = from;
    projInfo.periTo = to;
    projInfo.longTermFlg = longTermFlg;
    projInfo.sameDayFlg = sameDayFlg;
    projInfo.anyTimeFlg = anyTimeFlg;
    projInfo.extentionFlg = extentionflg;
    projInfo.orverview = ankenRegisterForm.orverview;
    projInfo.other = ankenRegisterForm.prjOther;
    projInfo.createDate = ankenList.createDate;
    projInfo.updateDate = timestamp;
    projInfo.delFlg = false;
    dao.uptate(projInfo);

    return "Completion.jsp";
  }

  /**
   * 画面入力クリア処理を行います。.
   * @ankenRegisterForm 案件情報登録フォーム
   */
  @Override
  public void clear(AnkenRegisterForm ankenRegisterForm) {
    List<BeanMap> skillList = dao.selectAll("M_SKILL");
    List<BeanMap> usersList = dao.selectAll("M_USERS");
    TProjInfo ankenList = dao.initEdit(ankenRegisterForm.id);

    ankenRegisterForm.skillList = skillList;
    ankenRegisterForm.usersList = usersList;

    if (!CommonFunction.empty(ankenList)) {
      //案件情報あり
      String update;
      update = ankenList.updateDate.toString();
      ankenRegisterForm.ankenList = ankenList;
      ankenRegisterForm.updateDate = update;
      ankenRegisterForm.editFlg = 1;
    } else {
      ankenRegisterForm.editFlg = 0;
    }
  }
}
