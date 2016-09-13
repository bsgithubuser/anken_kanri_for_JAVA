package jp.co.bsja.anken.entity;

import java.sql.Date;
import java.sql.Timestamp;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link TProjInfo}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2016/09/12 17:12:05")
public class TProjInfoNames {

    /**
     * prjIdのプロパティ名を返します。
     * 
     * @return prjIdのプロパティ名
     */
    public static PropertyName<Integer> prjId() {
        return new PropertyName<Integer>("prjId");
    }

    /**
     * prjNameのプロパティ名を返します。
     * 
     * @return prjNameのプロパティ名
     */
    public static PropertyName<String> prjName() {
        return new PropertyName<String>("prjName");
    }

    /**
     * userIdのプロパティ名を返します。
     * 
     * @return userIdのプロパティ名
     */
    public static PropertyName<Integer> userId() {
        return new PropertyName<Integer>("userId");
    }

    /**
     * cmpnIdのプロパティ名を返します。
     * 
     * @return cmpnIdのプロパティ名
     */
    public static PropertyName<Integer> cmpnId() {
        return new PropertyName<Integer>("cmpnId");
    }

    /**
     * prjSklIdのプロパティ名を返します。
     * 
     * @return prjSklIdのプロパティ名
     */
    public static PropertyName<Integer> prjSklId() {
        return new PropertyName<Integer>("prjSklId");
    }

    /**
     * genDateのプロパティ名を返します。
     * 
     * @return genDateのプロパティ名
     */
    public static PropertyName<Date> genDate() {
        return new PropertyName<Date>("genDate");
    }

    /**
     * periFromのプロパティ名を返します。
     * 
     * @return periFromのプロパティ名
     */
    public static PropertyName<Date> periFrom() {
        return new PropertyName<Date>("periFrom");
    }

    /**
     * periToのプロパティ名を返します。
     * 
     * @return periToのプロパティ名
     */
    public static PropertyName<Date> periTo() {
        return new PropertyName<Date>("periTo");
    }

    /**
     * longTermFlgのプロパティ名を返します。
     * 
     * @return longTermFlgのプロパティ名
     */
    public static PropertyName<Boolean> longTermFlg() {
        return new PropertyName<Boolean>("longTermFlg");
    }

    /**
     * sameDayFlgのプロパティ名を返します。
     * 
     * @return sameDayFlgのプロパティ名
     */
    public static PropertyName<Boolean> sameDayFlg() {
        return new PropertyName<Boolean>("sameDayFlg");
    }

    /**
     * anyTimeFlgのプロパティ名を返します。
     * 
     * @return anyTimeFlgのプロパティ名
     */
    public static PropertyName<Boolean> anyTimeFlg() {
        return new PropertyName<Boolean>("anyTimeFlg");
    }

    /**
     * extentionFlgのプロパティ名を返します。
     * 
     * @return extentionFlgのプロパティ名
     */
    public static PropertyName<Boolean> extentionFlg() {
        return new PropertyName<Boolean>("extentionFlg");
    }

    /**
     * orverviewのプロパティ名を返します。
     * 
     * @return orverviewのプロパティ名
     */
    public static PropertyName<String> orverview() {
        return new PropertyName<String>("orverview");
    }

    /**
     * otherのプロパティ名を返します。
     * 
     * @return otherのプロパティ名
     */
    public static PropertyName<String> other() {
        return new PropertyName<String>("other");
    }

    /**
     * delFlgのプロパティ名を返します。
     * 
     * @return delFlgのプロパティ名
     */
    public static PropertyName<Boolean> delFlg() {
        return new PropertyName<Boolean>("delFlg");
    }

    /**
     * createDateのプロパティ名を返します。
     * 
     * @return createDateのプロパティ名
     */
    public static PropertyName<Timestamp> createDate() {
        return new PropertyName<Timestamp>("createDate");
    }

    /**
     * updateDateのプロパティ名を返します。
     * 
     * @return updateDateのプロパティ名
     */
    public static PropertyName<Timestamp> updateDate() {
        return new PropertyName<Timestamp>("updateDate");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _TProjInfoNames extends PropertyName<TProjInfo> {

        /**
         * インスタンスを構築します。
         */
        public _TProjInfoNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TProjInfoNames(final String name) {
            super(name);
        }

        /**
         * インスタンスを構築します。
         * 
         * @param parent
         *            親
         * @param name
         *            名前
         */
        public _TProjInfoNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * prjIdのプロパティ名を返します。
         *
         * @return prjIdのプロパティ名
         */
        public PropertyName<Integer> prjId() {
            return new PropertyName<Integer>(this, "prjId");
        }

        /**
         * prjNameのプロパティ名を返します。
         *
         * @return prjNameのプロパティ名
         */
        public PropertyName<String> prjName() {
            return new PropertyName<String>(this, "prjName");
        }

        /**
         * userIdのプロパティ名を返します。
         *
         * @return userIdのプロパティ名
         */
        public PropertyName<Integer> userId() {
            return new PropertyName<Integer>(this, "userId");
        }

        /**
         * cmpnIdのプロパティ名を返します。
         *
         * @return cmpnIdのプロパティ名
         */
        public PropertyName<Integer> cmpnId() {
            return new PropertyName<Integer>(this, "cmpnId");
        }

        /**
         * prjSklIdのプロパティ名を返します。
         *
         * @return prjSklIdのプロパティ名
         */
        public PropertyName<Integer> prjSklId() {
            return new PropertyName<Integer>(this, "prjSklId");
        }

        /**
         * genDateのプロパティ名を返します。
         *
         * @return genDateのプロパティ名
         */
        public PropertyName<Date> genDate() {
            return new PropertyName<Date>(this, "genDate");
        }

        /**
         * periFromのプロパティ名を返します。
         *
         * @return periFromのプロパティ名
         */
        public PropertyName<Date> periFrom() {
            return new PropertyName<Date>(this, "periFrom");
        }

        /**
         * periToのプロパティ名を返します。
         *
         * @return periToのプロパティ名
         */
        public PropertyName<Date> periTo() {
            return new PropertyName<Date>(this, "periTo");
        }

        /**
         * longTermFlgのプロパティ名を返します。
         *
         * @return longTermFlgのプロパティ名
         */
        public PropertyName<Boolean> longTermFlg() {
            return new PropertyName<Boolean>(this, "longTermFlg");
        }

        /**
         * sameDayFlgのプロパティ名を返します。
         *
         * @return sameDayFlgのプロパティ名
         */
        public PropertyName<Boolean> sameDayFlg() {
            return new PropertyName<Boolean>(this, "sameDayFlg");
        }

        /**
         * anyTimeFlgのプロパティ名を返します。
         *
         * @return anyTimeFlgのプロパティ名
         */
        public PropertyName<Boolean> anyTimeFlg() {
            return new PropertyName<Boolean>(this, "anyTimeFlg");
        }

        /**
         * extentionFlgのプロパティ名を返します。
         *
         * @return extentionFlgのプロパティ名
         */
        public PropertyName<Boolean> extentionFlg() {
            return new PropertyName<Boolean>(this, "extentionFlg");
        }

        /**
         * orverviewのプロパティ名を返します。
         *
         * @return orverviewのプロパティ名
         */
        public PropertyName<String> orverview() {
            return new PropertyName<String>(this, "orverview");
        }

        /**
         * otherのプロパティ名を返します。
         *
         * @return otherのプロパティ名
         */
        public PropertyName<String> other() {
            return new PropertyName<String>(this, "other");
        }

        /**
         * delFlgのプロパティ名を返します。
         *
         * @return delFlgのプロパティ名
         */
        public PropertyName<Boolean> delFlg() {
            return new PropertyName<Boolean>(this, "delFlg");
        }

        /**
         * createDateのプロパティ名を返します。
         *
         * @return createDateのプロパティ名
         */
        public PropertyName<Timestamp> createDate() {
            return new PropertyName<Timestamp>(this, "createDate");
        }

        /**
         * updateDateのプロパティ名を返します。
         *
         * @return updateDateのプロパティ名
         */
        public PropertyName<Timestamp> updateDate() {
            return new PropertyName<Timestamp>(this, "updateDate");
        }
    }
}