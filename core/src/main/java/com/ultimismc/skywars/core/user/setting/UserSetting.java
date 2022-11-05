package com.ultimismc.skywars.core.user.setting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author DirectPlan
 */
@AllArgsConstructor
@Getter
@Setter
public class UserSetting {

    private final String name;
    private Object value;

    public boolean asBoolean() {
        return (boolean) value;
    }

    public String asString() {
        return (String) value;
    }

    public int asInt() {
        return (int) value;
    }
}
