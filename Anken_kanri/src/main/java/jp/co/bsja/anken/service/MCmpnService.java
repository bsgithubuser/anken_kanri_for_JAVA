package jp.co.bsja.anken.service;

import static jp.co.bsja.anken.entity.MCmpnNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import jp.co.bsja.anken.entity.MCmpn;

/**
 * {@link MCmpn}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2016/09/12 14:42:24")
public class MCmpnService extends AbstractService<MCmpn> {

  public List<MCmpn> makeUserList() {
    List<MCmpn> ret = new ArrayList<MCmpn>();
    ret.add(findById(new Integer(10)));
    return ret;
  }
    /**
     * 識別子でエンティティを検索します。
     * 
     * @param cmpnId
     *            識別子
     * @return エンティティ
     */
    private MCmpn findById(Integer cmpnId) {
        return select().id(cmpnId).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<MCmpn> findAllOrderById() {
        return select().orderBy(asc(cmpnId())).getResultList();
    }
}