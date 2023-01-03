package com.ultimismc.skywars.core.user.setting;

import com.ultimismc.skywars.core.user.UserCacheHandler;

import java.util.*;

/**
 * @author DirectPlan
 */
public class UserSettingHandler extends UserCacheHandler<String, UserSetting> {

    public <T> void addSetting(String key, T value) {
        UserSetting userSetting = userCache.get(key);
        if(userSetting != null) {
            Object settingValue = userSetting.getValue();
            Set<T> values;
            if(!(settingValue instanceof Set)) {
                values = new HashSet<>();
            }else {
                values = (Set<T>) settingValue;
            }
            values.add(value);
            return;
        }
        setSetting(key, value);
    }

    public <T> boolean contains(String key, T value) {
        UserSetting userSetting = userCache.get(key);
        if(userSetting == null) return false;
        Object settingValue = userSetting.getValue();
        if(!(settingValue instanceof Set)) {
            return settingValue.equals(value);
        }
        Set<T> values = (Set<T>) settingValue;
        return values.contains(value);
    }

    public <T> void removeSetting(String key, T value) {
        UserSetting userSetting = userCache.get(key);
        if(userSetting == null) return;
        Object settingValue = userSetting.getValue();
        if(!(settingValue instanceof Set)) {
            userCache.remove(key);
            return;
        }
        Set<T> values = (Set<T>) settingValue;
        values.remove(value);
    }

    public <T> void setSetting(String key, T value) {
        UserSetting userSetting = userCache.computeIfAbsent(key, s -> new UserSetting(key, value));
        userSetting.setValue(value);
    }

    public <T> T getSetting(Class<T> castClass, String key) {
        UserSetting userSetting = userCache.get(key);
        if(userSetting == null) {
            return null;
        }
        Object value = userSetting.getValue();
        return castClass.cast(value);
    }

    public <T> Collection<T> getListSetting(Class<T> castClass, String key) {
        Set<T> values = new HashSet<>();
        UserSetting userSetting = userCache.get(key);
        if(userSetting == null) return values;

        Object value = userSetting.getValue();
        if(!(value instanceof Set)) {
            values.add(castClass.cast(value));
            return values;
        }
        return (Set<T>) value;
    }

    public boolean isEmpty() {
        return userCache.isEmpty();
    }

    public boolean isActivated(String key) {
        Boolean activated = getSetting(Boolean.class, key);
        return activated != null ? activated : false;
    }

    public int getSettingInt(String key) {
        Integer integer = getSetting(Integer.class, key);
        return integer != null ? integer : 0;
    }

    public String getSettingString(String key) {
        return getSetting(String.class, key);
    }

    public Collection<UserSetting> getSettings() {
        return getCacheCollection();
    }
}
