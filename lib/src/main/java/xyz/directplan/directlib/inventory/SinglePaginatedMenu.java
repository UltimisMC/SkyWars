package xyz.directplan.directlib.inventory;

import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author DirectPlan
 */
public abstract class SinglePaginatedMenu<T> extends PaginatedMenu<T> {

    public SinglePaginatedMenu(String title, int rows, int pageSize) {
        super(title, rows, pageSize);
    }

    @Override
    public void buildPage(Player player, List<T> pageContents) {

        int index = 9;
        for(T content : pageContents) {
            MenuItem menuItem = buildContent(player, content);
            if(menuItem == null) continue;
            setSlot(index, menuItem);
            index++;
        }
    }

    public abstract MenuItem buildContent(Player player, T content);
}
