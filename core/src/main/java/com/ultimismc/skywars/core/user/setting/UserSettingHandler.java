package com.ultimismc.skywars.core.user.setting;

import com.ultimismc.skywars.core.user.UserCacheHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author DirectPlan
 */
public class UserSettingHandler extends UserCacheHandler<String, UserSetting> {

    public <T> void addSetting(String key, T value) {
        UserSetting userSetting = userCache.get(key);
        if(userSetting != null) {
            Object settingValue = userSetting.getValue();
            List<T> values;
            if(!(settingValue instanceof List)) {
                values = new ArrayList<>();
            }else {
                values = (List<T>) settingValue;
            }
            values.add(value);
            return;
        }
        setSetting(key, value);
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
