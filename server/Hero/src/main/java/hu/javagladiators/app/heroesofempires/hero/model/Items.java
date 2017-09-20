package hu.javagladiators.app.heroesofempires.hero.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.javagladiators.app.heroesofempires.hero.ListLinkSerializer;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLink.Style;
import org.glassfish.jersey.linking.InjectLinks;

/**
 * @author krisztian
 */
public class Items<T> {
    
    private int pageitemnumber;
    private int page;
    private int startitemnumber;
    private int fullitemnumber;
    private List<T> items = new ArrayList();

           @InjectLinks({
            @InjectLink(
                    resource = HeroResource.class,
                    style = InjectLink.Style.ABSOLUTE,
                    method = "query",
                    condition = "${instance.offset >0 && instance.modelLimit>instance.limit}",
                    bindings = {
                            @Binding(name = "offset", value = "${instance.offset - instance.limit}"),
                            @Binding(name = "limit", value = "${instance.limit}")
                    },
                    rel = "prev"
            ),
            
            @InjectLink(
                    resource = HeroResource.class,
                    style = InjectLink.Style.ABSOLUTE,
                    method = "query",
                    condition = "${instance.offset >0 && instance.modelLimit>instance.limit}",
                    bindings = {
                            @Binding(name = "offset", value = "0"),
                            @Binding(name = "limit", value = "${instance.limit}")
                    },
                    rel = "first"
            ),

            @InjectLink(
                    resource = HeroResource.class,
                    style = InjectLink.Style.ABSOLUTE,
                    method = "query",
                    condition = "${(instance.offset - instance.limit) >0}",
                    bindings = {
                            @Binding(name = "offset", value = "${instance.offset - 2*instance.limit}"),
                            @Binding(name = "limit", value = "${instance.limit}")
                    },
                    rel = "-2"
            ),            
            @InjectLink(
                    resource = HeroResource.class,
                    style = InjectLink.Style.ABSOLUTE,
                    method = "query",
                    condition = "${instance.offset>0 }",
                    bindings = {
                            @Binding(name = "offset", value = "${instance.offset - instance.limit}"),
                            @Binding(name = "limit", value = "${instance.limit}")
                    },
                    rel = "-1"
            ),            
            @InjectLink(
                    resource = HeroResource.class,
                    style = InjectLink.Style.ABSOLUTE,
                    method = "query",
                    bindings = {
                            @Binding(name = "offset", value = "${instance.offset}"),
                            @Binding(name = "limit", value = "${instance.limit}")
                    },
                    rel = "self"
            ),
            
            @InjectLink(
                    resource = HeroResource.class,
                    style = InjectLink.Style.ABSOLUTE,
                    method = "query",
                    condition = "${instance.offset + instance.limit <instance.modelLimit }",
                    bindings = {
                            @Binding(name = "offset", value = "${instance.offset + instance.limit}"),
                            @Binding(name = "limit", value = "${instance.limit}")
                    },
                    rel = "+1"
            ),            
            @InjectLink(
                    resource = HeroResource.class,
                    style = InjectLink.Style.ABSOLUTE,
                    method = "query",
                    condition = "${instance.offset + 2*instance.limit <instance.modelLimit }",
                    bindings = {
                            @Binding(name = "offset", value = "${instance.offset + 2*instance.limit}"),
                            @Binding(name = "limit", value = "${instance.limit}")
                    },
                    rel = "+2"
            ),            
            
            @InjectLink(
                    resource = HeroResource.class,
                    style = InjectLink.Style.ABSOLUTE,
                    method = "query",
                    condition = "${instance.offset + 2*instance.limit <instance.modelLimit }",
                    bindings = {
                            @Binding(name = "offset", value = "${instance.offset + 3*instance.limit}"),
                            @Binding(name = "limit", value = "${instance.limit}")
                    },
                    rel = "+3"
            ),            
            
            @InjectLink(
                    resource = HeroResource.class,
                    style = InjectLink.Style.ABSOLUTE,
                    method = "query",
                    condition = "${instance.offset + instance.limit < instance.modelLimit }",
                    bindings = {
                            @Binding(name = "offset", value = "${instance.modelLimit - instance.limit}"),
                            @Binding(name = "limit", value = "${instance.limit}")
                    },
                    rel = "last"
            ),
            @InjectLink(
                    resource = HeroResource.class,
                    style = InjectLink.Style.ABSOLUTE,
                    method = "query",
                    condition = "${instance.offset + instance.limit < instance.modelLimit}",
                    bindings = {
                            @Binding(name = "offset", value = "${instance.offset + instance.limit}"),
                            @Binding(name = "limit", value = "${instance.limit}")
                    },
                    rel = "next"
            )
})
    @JsonSerialize(using = ListLinkSerializer.class)  
    List<Link> navigations;
    public Items() {
    }

    public Items(int pageitemnumber, int startitemnumber, int fullitemnumber) {
        this.pageitemnumber = pageitemnumber;
        this.startitemnumber = startitemnumber;
        this.fullitemnumber = fullitemnumber;
        this.page = fullitemnumber/pageitemnumber +1;
    }

    public int getPageitemnumber() {
        return pageitemnumber;
    }

    public void setPageitemnumber(int pageitemnumber) {
        this.pageitemnumber = pageitemnumber;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getStartitemnumber() {
        return startitemnumber;
    }

    public void setStartitemnumber(int startitemnumber) {
        this.startitemnumber = startitemnumber;
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
