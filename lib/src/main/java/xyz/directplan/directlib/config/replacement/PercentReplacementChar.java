package xyz.directplan.directlib.config.replacement;

/**
 * @author DirectPlan
 */
public class PercentReplacementChar implements ReplacementChar {

    @Override
    public char start() {
        return '%';
    }

    @Override
    public char end() {
        return '%';
    }
}
