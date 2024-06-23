package xyz.directplan.directlib.inventory;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.inventory.decoration.InventoryDecoration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author DirectPlan
 */
@Getter
public abstract class PaginatedMenu<T> extends InventoryUI {

    private int page = 1;
    private final int pageSize;
    private final PaginatedModel paginatedModel;
    private List<T> contents;

    public PaginatedMenu(String title, int rows, PaginatedModel paginatedModel, InventoryDecoration decoration) {
        super(title, (rows != -1 ? rows : computeRowsBasedOnSlots(paginatedModel.getPageSize())), decoration);

        this.pageSize = paginatedModel.getPageSize();
        this.paginatedModel = paginatedModel;
    }

    public PaginatedMenu(String title, int rows, PaginatedModel paginatedModel) {
        this(title, rows, paginatedModel, null);
    }

    public PaginatedMenu(String title, int rows) {
        this(title, rows, PaginatedModel.DEFAULT_MODEL);
    }

    public void nextPage(Player player){
        this.page += 1;
        this.clearItems();
        player.playSound(player.getLocation(), Sound.CLICK, 1L, 1L);
        lock();
        this.open(player);
        unlock();
    }

    public void previousPage(Player player){
        this.page -= 1;
        this.clearItems();
        player.playSound(player.getLocation(), Sound.CLICK, 1L, 1L);
        lock();
        this.open(player);
        unlock();
    }

    public int getTotalPages(){
        int size = contents.size();
        return (int) Math.ceil(((double)size / pageSize));
    }

    @Override
    public void build(Player player) {
        if(contents == null) {
            Collection<T> list = getList();

            contents = list != null ? new ArrayList<>(list) : new ArrayList<>();
            contents.removeIf(content -> !accept(content));
        }

        int nextPageSlot = paginatedModel.getNextPageSlot();
        int previousPageSlot = paginatedModel.getPreviousPageSlot();
        int totalPages = this.getTotalPages();

        // Building the page here before
        List<T> currentPageContents = this.getCurrentPageList(totalPages);

        if(paginatedModel.isHeader()) buildHeader();

        this.setTitle(getTitle().replace("current_page", String.valueOf(page)));

        if(this.page < totalPages){
            int currentPage = (page + 1);
            String nextPageDisplayName = "&aNext Page &7("+currentPage+"/" + totalPages + ")";

            MenuItem nextPageItem = new MenuItem(Material.ARROW, nextPageDisplayName, (item, clicker, clickedBlock, clickType) -> this.nextPage(clicker));
            this.setSlot(nextPageSlot, nextPageItem);
        }
        if(this.page > 1) {
            int previousPage = (page - 1);
            String previousPageDisplayName = "&aPrevious page &7(" + previousPage + "/"+ totalPages +")";

            MenuItem previousPageItem = new MenuItem(Material.ARROW, previousPageDisplayName, (item, clicker, clickedBlock, clickType) -> this.previousPage(clicker));
            this.setSlot(previousPageSlot, previousPageItem);
        }

        int startSlotIndex = 0;
        for(T pageContent : currentPageContents) {
            int modelSlot = paginatedModel.modelSlot(startSlotIndex);

            MenuItem menuItem = buildContent(player, pageContent);
            if(menuItem == null) continue;
            setSlot(modelSlot, menuItem);
            startSlotIndex++;
        }
    }

    private List<T> getCurrentPageList(int totalPages) {
        List<T> paginatedObjects = new ArrayList<>();

        int page = (this.page - 1);

        if(page < 0 || page >= totalPages) return contents;

        int min = page * pageSize;
        int max = (min + pageSize);
        if(max > contents.size()) {
            max = contents.size();
        }
        for(int i = min; i < max; i++){
            paginatedObjects.add(contents.get(i));
        }
        return paginatedObjects;
    }

    public static int computeRowsBasedOnSlots(int slots) {
        double divided = 9.0;
        double rows = (slots / divided);
        if(slots % divided != 0.0) return (int) rows + 1;
        return (int) rows;
    }


    public abstract Collection<T> getList();

    public abstract MenuItem buildContent(Player player, T content);

    public boolean accept(T content) {
        return true;
    }
}
