package jp.co.bsja.anken.service;

import java.util.List;
import javax.annotation.Generated;
import jp.co.bsja.anken.entity.TProjInfo;

import static jp.co.bsja.anken.entity.TProjInfoNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link TProjInfo}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2016/09/12 14:42:25")
public class TProjInfoService extends AbstractService<TProjInfo> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param prjId
     *            識別子
     * @return エンティティ
     */
    public TProjInfo findById(Integer prjId) {
        return select().id(prjId).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<TProjInfo> findAllOrderById() {
        return select().orderBy(asc(prjId())).getResultList();
    }
}