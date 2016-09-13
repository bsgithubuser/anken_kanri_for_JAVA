package jp.co.bsja.anken.entity;

import javax.annotation.Generated;
import jp.co.bsja.anken.entity.MCmpnNames._MCmpnNames;
import jp.co.bsja.anken.entity.MSkillNames._MSkillNames;
import jp.co.bsja.anken.entity.MUsersNames._MUsersNames;
import jp.co.bsja.anken.entity.TProjInfoNames._TProjInfoNames;
import jp.co.bsja.anken.entity.TProjSkillNames._TProjSkillNames;

/**
 * 名前クラスの集約です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesAggregateModelFactoryImpl"}, date = "2016/09/12 17:12:05")
public class Names {

    /**
     * {@link MCmpn}の名前クラスを返します。
     * 
     * @return MCmpnの名前クラス
     */
    public static _MCmpnNames mCmpn() {
        return new _MCmpnNames();
    }

    /**
     * {@link MSkill}の名前クラスを返します。
     * 
     * @return MSkillの名前クラス
     */
    public static _MSkillNames mSkill() {
        return new _MSkillNames();
    }

    /**
     * {@link MUsers}の名前クラスを返します。
     * 
     * @return MUsersの名前クラス
     */
    public static _MUsersNames mUsers() {
        return new _MUsersNames();
    }

    /**
     * {@link TProjInfo}の名前クラスを返します。
     * 
     * @return TProjInfoの名前クラス
     */
    public static _TProjInfoNames tProjInfo() {
        return new _TProjInfoNames();
    }

    /**
     * {@link TProjSkill}の名前クラスを返します。
     * 
     * @return TProjSkillの名前クラス
     */
    public static _TProjSkillNames tProjSkill() {
        return new _TProjSkillNames();
    }
}