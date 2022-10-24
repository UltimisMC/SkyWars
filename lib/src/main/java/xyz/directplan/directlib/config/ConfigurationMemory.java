package xyz.directplan.directlib.config;

import java.util.List;
import java.util.Set;

/**
 * @author DirectPlan
 */
public interface ConfigurationMemory<K> {

    void set(K key, Object value);

    String getString(K key);

    boolean getBoolean(K key);

    int getInteger(K key);

    List<String> getStringList(K key);

    boolean containsKey(K key);

    Object get(K key);

    Set<String> getSectionKeys(K key, boolean b);
}
