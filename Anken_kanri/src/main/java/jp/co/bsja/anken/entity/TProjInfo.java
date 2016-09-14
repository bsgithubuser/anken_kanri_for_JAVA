package jp.co.bsja.anken.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * TProjInfoエンティティクラス
 *
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2016/09/12 14:42:12")
public class TProjInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** prjIdプロパティ */
    @Id

    @Column(precision = 10, nullable = false, unique = true)
    public Integer prjId;

    /** prjNameプロパティ */
    @Column(length = 200, nullable = false, unique = false)
    public String prjName;

    /** userIdプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer userId;

    /** cmpnIdプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer cmpnId;

    /** prjSklIdプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer prjSklId;

    /** genDateプロパティ */
    @Column(nullable = false, unique = false)
    public Date genDate;

    /** periFromプロパティ */
    @Column(nullable = true, unique = false)
    public Date periFrom;

    /** periToプロパティ */
    @Column(nullable = true, unique = false)
    public Date periTo;

    /** longTermFlgプロパティ */
    @Column(length = 1, nullable = false, unique = false)
    public Boolean longTermFlg;

    /** sameDayFlgプロパティ */
    @Column(length = 1, nullable = false, unique = false)
    public Boolean sameDayFlg;

    /** anyTimeFlgプロパティ */
    @Column(length = 1, nullable = false, unique = false)
    public Boolean anyTimeFlg;

    /** extentionFlgプロパティ */
    @Column(length = 1, nullable = false, unique = false)
    public Boolean extentionFlg;

    /** orverviewプロパティ */
    @Column(length = 2000, nullable = false, unique = false)
    public String orverview;

    /** otherプロパティ */
    @Column(length = 1000, nullable = false, unique = false)
    public String other;

    /** delFlgプロパティ */
    @Column(length = 1, nullable = false, unique = false)
    public Boolean delFlg;

    /** createDateプロパティ */
    @Column(nullable = false, unique = false)
    public Timestamp createDate;

    /** updateDateプロパティ */
    @Column(nullable = false, unique = false)
    public Timestamp updateDate;
}