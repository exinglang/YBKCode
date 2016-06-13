package com.puxtech.ybk.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * SharedPreferences辅助类
 *
 * @author fanshuo
 */
public class SharedPreferenceManager {


    //登录记录
    public static final String LOGIN = "LOGIN";
    public static final String LOGIN_ACCOUNT_ARRAY = "LOGIN_ACCOUNT_ARRAY";
    public static final String LOGIN_LAST_ACCOUNT = "LOGIN_LAST_ACCOUNT";
    //设置
    public static final String SHE_ZHI = "SHE_ZHI";
    public static final String LIU_LIANG_SYSTEM_SHUT_DOWN = "LIU_LIANG_SYSTEM_SHUT_DOWN";
    public static final String LIU_LIANG_APP_START = "LIU_LIANG_APP_START";
    public static final String LIU_LIANG_CLEAN_TIME = "LIU_LIANG_CLEAN_TIME";

    //心跳信号
    public static final String TRADE_CNT = "TRADE_CNT";
    public static final String CLEAR_DATE = "CLEAR_DATE";
    public static final String LASTNEW_ID = "LASTNEW_ID";
    public static final String PROMPT = "PROMPT";

    private SharedPreferences pre;

    public SharedPreferenceManager(Context context, String fileName) {
        super();
        this.pre = context.getSharedPreferences(fileName, 0);
    }

    /**
     * 检查是否包含某个key
     *
     * @param key
     * @return
     */
    public boolean contains(String key) {
        return pre.contains(key);
    }


    public float getFloat(final Context pContext, final String pKey,
                          final float pDefaultValue) {
        return this.pre.getFloat(pKey, pDefaultValue);
    }

    public boolean putFloat(final Context pContext, final String pKey,
                            final float pValue) {
        try {
            final SharedPreferences.Editor editor = this.pre.edit();
            editor.putFloat(pKey, pValue);
            return editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getInt(final String pKey,
                      final int pDefaultValue) {
        return this.pre.getInt(pKey, pDefaultValue);
    }

    public boolean putInt(final String pKey,
                          final int pValue) {
        try {
            final SharedPreferences.Editor editor = this.pre.edit();
            editor.putInt(pKey, pValue);
            return editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public long getLong(final String pKey,
                        final long pDefaultValue) {
        return this.pre.getLong(pKey, pDefaultValue);
    }

    public boolean putLong(final String pKey,
                           final long pValue) {
        try {
            final SharedPreferences.Editor editor = this.pre.edit();
            editor.putLong(pKey, pValue);
            return editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean getBoolean(final String pKey,
                              final boolean pDefaultValue) {
        return this.pre.getBoolean(pKey, pDefaultValue);
    }

    public boolean putBoolean(final String pKey,
                              final boolean pValue) {
        try {
            final SharedPreferences.Editor editor = this.pre.edit();
            editor.putBoolean(pKey, pValue);
            return editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getString(final String pKey,
                            final String pDefaultValue) {
        return this.pre.getString(pKey, pDefaultValue);
    }

    public Set<String> getStringSet(final String pKey) {
        return this.pre.getStringSet(pKey, new HashSet<String>());
    }


    public boolean putStringSet(final String pKey,
                                final String pValue) {
        try {
            final SharedPreferences.Editor editor = this.pre.edit();

            Set<String> s = getStringSet(pKey);
            s.add(pValue);
            editor.putStringSet(pKey, s);
            return editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean putString(final String pKey,
                             final String pValue) {
        try {
            final SharedPreferences.Editor editor = this.pre.edit();
            editor.putString(pKey, pValue);
            return editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean remove(final Context context, final String key) {
        try {
            final SharedPreferences.Editor editor = this.pre.edit();
            editor.remove(key);
            return editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}