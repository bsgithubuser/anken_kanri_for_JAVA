package jp.co.bsja.anken.service;

import java.util.List;
import javax.annotation.Generated;
import jp.co.bsja.anken.entity.TProjSkill;

import static jp.co.bsja.anken.entity.TProjSkillNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link TProjSkill}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2016/09/12 14:42:25")
public class TProjSkillService extends AbstractService<TProjSkill> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param prjSklId
     *            識別子
     * @param sklId
     *            識別子
     * @return エンティティ
     */
    public TProjSkill findById(Integer prjSklId, Integer sklId) {
        return select().id(prjSklId, sklId).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TProjSkill> findAllOrderById() {
        return select().orderBy(asc(prjSklId()), asc(sklId())).getResultList();
    }
}