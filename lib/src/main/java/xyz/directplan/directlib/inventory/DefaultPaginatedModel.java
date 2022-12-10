package xyz.directplan.directlib.inventory;

/**
 * @author DirectPlan
 */
public class DefaultPaginatedModel extends PaginatedModel {

    public DefaultPaginatedModel(int pageSize) {
        super(pageSize, 8, 0, true);
    }

    public DefaultPaginatedModel() {
        this(18);
    }

    @Override
    public int modelSlot(int slot) {
        return (9 + slot);
    }
}
