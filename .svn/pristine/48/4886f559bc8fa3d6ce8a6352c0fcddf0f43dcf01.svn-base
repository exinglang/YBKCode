package com.puxtech.ybk.qidong;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences辅助类
 * @author fanshuo
 */
public class SharedPreferenceManager {
	public static final String FILE_NAME_QIDONG = "QiDong_SharedPreferences";//文件名，只用于启动模块的设置信息
	public static final String KEY_CONTENT_SERVER_REFRESH_TIME = "key_content_server_refresh_time";//目录服务器数据更新时间
	//link
	public static final String LINK = "LINK";
	public static final String LINK_HANGQIN = "LINK_HANGQIN";
	public static final String LINK_JIAOYI = "LINK_JIAOYI";

	private SharedPreferences pre;
	public SharedPreferenceManager(Context context, String fileName) {
		super();
		this.pre = context.getSharedPreferences(fileName, 0);
	}
	
	/**
	 * 检查是否包含某个key
	 * @param key
	 * @return
	 */
	public boolean contains(String key){
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

	public int getInt(final Context pContext, final String pKey,
			final int pDefaultValue) {
		return this.pre.getInt(pKey, pDefaultValue);
	}

	public boolean putInt(final Context pContext, final String pKey,
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

	public long getLong(final Context pContext, final String pKey,
			final long pDefaultValue) {
		return this.pre.getLong(pKey, pDefaultValue);
	}

	public boolean putLong(final Context pContext, final String pKey,
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

	public boolean getBoolean(final Context pContext, final String pKey,
			final boolean pDefaultValue) {
		return this.pre.getBoolean(pKey, pDefaultValue);
	}

	public boolean putBoolean(final Context pContext, final String pKey,
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

	public String getString(final Context pContext, final String pKey,
			final String pDefaultValue) {
		return this.pre.getString(pKey, pDefaultValue);
	}

	public boolean putString(final Context pContext, final String pKey,
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