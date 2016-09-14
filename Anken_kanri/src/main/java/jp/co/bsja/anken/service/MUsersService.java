package jp.co.bsja.anken.service;

import java.util.List;
import javax.annotation.Generated;
import jp.co.bsja.anken.entity.MUsers;

import static jp.co.bsja.anken.entity.MUsersNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link MUsers}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2016/09/12 14:42:25")
public class MUsersService extends AbstractService<MUsers> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param userId
     *            識別子
     * @return エンティティ
     */
    public MUsers findById(Integer userId) {
        return select().id(userId).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<MUsers> findAllOrderById() {
        return select().orderBy(asc(userId())).getResultList();
    }
}