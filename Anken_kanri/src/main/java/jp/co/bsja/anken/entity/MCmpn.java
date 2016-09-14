package jp.co.bsja.anken.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * MCmpnエンティティクラス
 *
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2016/09/12 14:42:12")
public class MCmpn implements Serializable {

    private static final long serialVersionUID = 1L;

    /** cmpnIdプロパティ */
    @Id

    @Column(precision = 10, nullable = false, unique = true)
    public Integer cmpnId;

    /** cmpnNameプロパティ */
    @Column(length = 60, nullable = false, unique = false)
    public String cmpnName;

    /** cmpnNameFuriプロパティ */
    @Column(length = 60, nullable = false, unique = false)
    public String cmpnNameFuri;

    /** createDateプロパティ */
    @Column(nullable = false, unique = false)
    public Timestamp createDate;

    /** updateDateプロパティ */
    @Column(nullable = false, unique = false)
    public Timestamp updateDate;
}