package com.ultimismc.skywars.core.user.setting;

import com.ultimismc.skywars.core.user.UserCacheHandler;

import java.util.Collection;

/**
 * @author DirectPlan
 */
public class UserSettingHandler extends UserCacheHandler<String, UserSetting> {

    public <T> void setSetting(String name, T value) {
        UserSetting userSetting = userCache.computeIfAbsent(name, s -> new UserSetting(name, value));
        userSetting.setValue(value);
    }

    public boolean isActivated(String name) {
        UserSetting userSetting = getCache(name);
        return userSetting.asBoolean();
    }

    public int getSettingInt(String name) {
        UserSetting userSetting = getCache(name);
        return userSetting.asInt();
    }

    public String getSettingValue(String name) {
        UserSetting userSetting = getCache(name);
        return userSetting.asString();
    }


    public Collection<UserSetting> getSettings() {
        return getCacheCollection();
    }
}
