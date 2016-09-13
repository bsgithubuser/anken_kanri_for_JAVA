package jp.co.bsja.anken.service;

import java.util.List;
import javax.annotation.Generated;
import jp.co.bsja.anken.entity.MSkill;

import static jp.co.bsja.anken.entity.MSkillNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link MSkill}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2016/09/12 14:42:24")
public class MSkillService extends AbstractService<MSkill> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param skillId
     *            識別子
     * @return エンティティ
     */
    public MSkill findById(Integer skillId) {
        return select().id(skillId).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<MSkill> findAllOrderById() {
        return select().orderBy(asc(skillId())).getResultList();
    }
}