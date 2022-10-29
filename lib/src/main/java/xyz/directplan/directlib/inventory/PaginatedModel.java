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
    private final boolean header;

    public PaginatedModel(int pageSize, int nextPageSlot, int previousPageSlot, boolean header, int[] modelMatrix) {
        this.pageSize = pageSize;
        this.nextPageSlot = nextPageSlot;
        this.previousPageSlot = previousPageSlot;
        this.header = header;
        this.modelMatrix = modelMatrix;
    }

    public PaginatedModel(int pageSize, int nextPageSlot, int previousPageSlot, boolean header) {
        this(pageSize, nextPageSlot, previousPageSlot, header, null);
    }

    public int modelSlot(int slot) {
        return modelMatrix[slot];
    }
}
