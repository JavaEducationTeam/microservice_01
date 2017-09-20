package hu.javagladiators.app.heroesofempires.hero.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.javagladiators.app.heroesofempires.hero.ListLinkSerializer;
import hu.javagladiators.app.heroesofempires.hero.resource.HeroResource;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Link;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;

/**
 * @author krisztian
 * @param <T>
 */
public class Items<T> {
    
    private int limit;
    private int page;
    private int offset;
    private int fullitemnumber;
    private List<T> items = new ArrayList();

    @InjectLinks({
            @InjectLink(
                    resource = HeroResource.class,
                    style = InjectLink.Style.ABSOLUTE,
                    rel = "self"
            ),
    })
    @JsonSerialize(using = ListLinkSerializer.class)  
    private List<Link> links;
           
    
           
    public Items() {
    }

    public Items(int pageitemnumber, int startitemnumber, int fullitemnumber) {
        this.limit = pageitemnumber;
        this.offset = startitemnumber;
        this.fullitemnumber = fullitemnumber;
        this.page = this.offset/pageitemnumber +1;
    }

    public int getPageitemnumber() {
        return limit;
    }

    public void setPageitemnumber(int pageitemnumber) {
        this.limit = pageitemnumber;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getStartitemnumber() {
        return offset;
    }

    public void setStartitemnumber(int startitemnumber) {
        this.offset = startitemnumber;
    }

    public int getFullitemnumber() {
        return fullitemnumber;
    }

    public void setFullitemnumber(int fullitemnumber) {
        this.fullitemnumber = fullitemnumber;
    }

    public List getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public void addItems(T item) {
        this.items.add(item);
    }
    
    
    
}
