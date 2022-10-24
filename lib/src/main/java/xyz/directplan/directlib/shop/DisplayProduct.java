package xyz.directplan.directlib.shop;

/**
 * @author DirectPlan
 */
public abstract class DisplayProduct<U> extends AbstractProduct<U> {


    public DisplayProduct(String name, int itemSlot) {
        super(name, itemSlot);
    }

    @Override
    public boolean isDisplay() {
        return true;
    }
}
