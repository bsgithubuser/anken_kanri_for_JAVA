package jp.co.bsja.anken.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * MUsersエンティティクラス
 *
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2016/09/12 14:42:12")
public class MUsers implements Serializable {

    private static final long serialVersionUID = 1L;

    /** userNameプロパティ */
    @Column(length = 30, nullable = false, unique = false)
    public String userName;

    /** passwordプロパティ */
    @Column(length = 32, nullable = false, unique = false)
    public String password;

    /** adminプロパティ */
    @Column(length = 2, nullable = false, unique = false)
    public String admin;

    /** loginStateプロパティ */
    @Column(length = 1, nullable = false, unique = false)
    public Boolean loginState;

    /** createDateプロパティ */
    @Column(nullable = false, unique = false)
    public Timestamp createDate;

    /** updateDateプロパティ */
    @Column(nullable = false, unique = false)
    public Timestamp updateDate;

    /** userIdプロパティ */
    @Id

    @Column(precision = 10, nullable = false, unique = true)
    public Integer userId;
}