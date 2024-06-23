package xyz.directplan.directlib.placeholders;

/**
 * @author DirectPlan
 */
public interface PlaceholderProcessor<T> {

    Object process(T player, String value);
}
