package jp.co.bsja.anken.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link MCmpn}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2016/09/12 17:12:05")
public class MCmpnNames {

    /**
     * cmpnIdのプロパティ名を返します。
     * 
     * @return cmpnIdのプロパティ名
     */
    public static PropertyName<Integer> cmpnId() {
        return new PropertyName<Integer>("cmpnId");
    }

    /**
     * cmpnNameのプロパティ名を返します。
     * 
     * @return cmpnNameのプロパティ名
     */
    public static PropertyName<String> cmpnName() {
        return new PropertyName<String>("cmpnName");
    }

    /**
     * cmpnNameFuriのプロパティ名を返します。
     * 
     * @return cmpnNameFuriのプロパティ名
     */
    public static PropertyName<String> cmpnNameFuri() {
        return new PropertyName<String>("cmpnNameFuri");
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
    public static class _MCmpnNames extends PropertyName<MCmpn> {

        /**
         * インスタンスを構築します。
         */
        public _MCmpnNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _MCmpnNames(final String name) {
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
        public _MCmpnNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
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
         * cmpnNameのプロパティ名を返します。
         *
         * @return cmpnNameのプロパティ名
         */
        public PropertyName<String> cmpnName() {
            return new PropertyName<String>(this, "cmpnName");
        }

        /**
         * cmpnNameFuriのプロパティ名を返します。
         *
         * @return cmpnNameFuriのプロパティ名
         */
        public PropertyName<String> cmpnNameFuri() {
            return new PropertyName<String>(this, "cmpnNameFuri");
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