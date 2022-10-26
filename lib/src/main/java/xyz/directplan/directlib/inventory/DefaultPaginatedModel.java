package xyz.directplan.directlib.inventory;

/**
 * @author DirectPlan
 */
public class DefaultPaginatedModel extends PaginatedModel {


    public DefaultPaginatedModel() {
        super(18, 8, 0);
    }

    @Override
    public int modelSlot(int slot) {
        return (9 + slot);
    }
}
