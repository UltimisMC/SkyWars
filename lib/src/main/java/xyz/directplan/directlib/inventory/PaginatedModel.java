package xyz.directplan.directlib.inventory;

import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public abstract class PaginatedModel {

    public static final PaginatedModel HYPIXEL_MODEL = new HypixelPaginatedModel();
    public static final PaginatedModel DEFAULT_MODEL = new DefaultPaginatedModel();

    private final int pageSize, nextPageSlot, previousPageSlot;
    private final int[] modelMatrix;

    public PaginatedModel(int pageSize, int nextPageSlot, int previousPageSlot, int[] modelMatrix) {
        this.pageSize = pageSize;
        this.nextPageSlot = nextPageSlot;
        this.previousPageSlot = previousPageSlot;
        this.modelMatrix = modelMatrix;
    }

    public PaginatedModel(int pageSize, int nextPageSlot, int previousPageSlot) {
        this(pageSize, nextPageSlot, previousPageSlot, null);
    }

    public int modelSlot(int slot) {
        return modelMatrix[slot];
    }
}
