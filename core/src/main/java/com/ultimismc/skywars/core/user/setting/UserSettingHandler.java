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
            List<T> values;
            if(!(settingValue instanceof List)) {
                values = new ArrayList<>();
            }else {
                values = (List<T>) settingValue;
            }
            values.add(value);
            return;
        }
        addCache(key, new UserSetting(key, value));
    }

    public <T> T getSetting(Class<T> castClass, String key) {
        UserSetting userSetting = userCache.get(key);
        if(userSetting != null) {
            Object value = userSetting.getValue();
            return castClass.cast(value);
        }
        return null;
    }

    public boolean isEmpty() {
        return userCache.isEmpty();
    }

    public boolean isActivated(String key) {
        return getSetting(Boolean.class, key);
    }

    public int getSettingInt(String key) {
        return getSetting(Integer.class, key);
    }

    public String getSettingString(String key) {
        return getSetting(String.class, key);
    }

    public Collection<UserSetting> getSettings() {
        return getCacheCollection();
    }
}
