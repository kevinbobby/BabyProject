package com.zhubibo.baby.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by kevin on 2017/6/19.
 */

public class PreferenceUtil {

    public static final String DEFAULT_FILE_NAME = "preferences";

    public static Context mContext;

    // 业务key开始
    public static final String BIRTHDAY = "birthday";
    public static final String BABY_NAME = "baby_name";
    public static final String SHOW_VOLUME_SETTING = "show_volume_setting";
    // 业务key结束

    public static void init(Context context) {
        mContext = context;
    }

    /**
     * 保存键值对
     * @param key
     * @param value
     */
    public static void saveValue(String key, Object value) {
        saveValue(DEFAULT_FILE_NAME, key, value);
    }

    /**
     * 保存键值对
     * @param key
     * @param value
     */
    public static void saveValue(String fileName, String key, Object value) {
        if (mContext == null) {
            throw new NullPointerException("mContext为空，请先在Application中调用init方法进行初始化.");
        }
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(fileName, Context.MODE_APPEND);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Set) {
            editor.putStringSet(key, (Set<String>) value);
        } else {
            throw new IllegalArgumentException("value 参数格式错误，支持参数格式为：Boolean/Integer/Long/Float/String/Set<String>.");
        }
        editor.commit();

    }

    /**
     * 获取指定key的value
     * @param key
     * @param defaultValue
     * @return
     */
    public static Object getValue(String key, Object defaultValue) {
        return getValue(DEFAULT_FILE_NAME, key, defaultValue);
    }

    /**
     * 获取指定key的value
     * @param fileName
     * @param key
     * @param defaultValue
     * @return
     */
    public static Object getValue(String fileName, String key, Object defaultValue) {
        if (mContext == null) {
            throw new NullPointerException("mContext为空，请先在Application中调用init方法进行初始化.");
        }
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(fileName, Context.MODE_APPEND);

        if (defaultValue instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) defaultValue);
        } else if (defaultValue instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defaultValue);
        } else if (defaultValue instanceof Long) {
            return sharedPreferences.getLong(key, (Long) defaultValue);
        } else if (defaultValue instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) defaultValue);
        } else if (defaultValue instanceof String) {
            return sharedPreferences.getString(key, (String) defaultValue);
        } else if (defaultValue instanceof Set) {
            return sharedPreferences.getStringSet(key, (Set<String>) defaultValue);
        } else {
            throw new IllegalArgumentException("value 参数格式错误，支持参数格式为：Boolean/Integer/Long/Float/String/Set<String>.");
        }
    }

    /**
     * 移除指定的field
     * @param key
     */
    public static void removeField(String key) {
        removeField(DEFAULT_FILE_NAME, key);
    }

    /**
     * 移除指定的field
     * @param fileName
     * @param key
     */
    public static void removeField(String fileName, String key) {
        if (mContext == null) {
            throw new NullPointerException("mContext为空，请先在Application中调用init方法进行初始化.");
        }
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(fileName, Context.MODE_APPEND);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }
}
