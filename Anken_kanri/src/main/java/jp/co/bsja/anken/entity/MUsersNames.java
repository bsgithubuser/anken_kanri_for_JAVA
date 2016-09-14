package jp.co.bsja.anken.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link MUsers}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2016/09/12 17:12:05")
public class MUsersNames {

    /**
     * userNameのプロパティ名を返します。
     * 
     * @return userNameのプロパティ名
     */
    public static PropertyName<String> userName() {
        return new PropertyName<String>("userName");
    }

    /**
     * passwordのプロパティ名を返します。
     * 
     * @return passwordのプロパティ名
     */
    public static PropertyName<String> password() {
        return new PropertyName<String>("password");
    }

    /**
     * adminのプロパティ名を返します。
     * 
     * @return adminのプロパティ名
     */
    public static PropertyName<String> admin() {
        return new PropertyName<String>("admin");
    }

    /**
     * loginStateのプロパティ名を返します。
     * 
     * @return loginStateのプロパティ名
     */
    public static PropertyName<Boolean> loginState() {
        return new PropertyName<Boolean>("loginState");
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
     * userIdのプロパティ名を返します。
     * 
     * @return userIdのプロパティ名
     */
    public static PropertyName<Integer> userId() {
        return new PropertyName<Integer>("userId");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _MUsersNames extends PropertyName<MUsers> {

        /**
         * インスタンスを構築します。
         */
        public _MUsersNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _MUsersNames(final String name) {
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
        public _MUsersNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * userNameのプロパティ名を返します。
         *
         * @return userNameのプロパティ名
         */
        public PropertyName<String> userName() {
            return new PropertyName<String>(this, "userName");
        }

        /**
         * passwordのプロパティ名を返します。
         *
         * @return passwordのプロパティ名
         */
        public PropertyName<String> password() {
            return new PropertyName<String>(this, "password");
        }

        /**
         * adminのプロパティ名を返します。
         *
         * @return adminのプロパティ名
         */
        public PropertyName<String> admin() {
            return new PropertyName<String>(this, "admin");
        }

        /**
         * loginStateのプロパティ名を返します。
         *
         * @return loginStateのプロパティ名
         */
        public PropertyName<Boolean> loginState() {
            return new PropertyName<Boolean>(this, "loginState");
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

        /**
         * userIdのプロパティ名を返します。
         *
         * @return userIdのプロパティ名
         */
        public PropertyName<Integer> userId() {
            return new PropertyName<Integer>(this, "userId");
        }
    }
}