/*
 */
package hu.javagladiators.app.hero.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author krisztian
 */
public class Items<T> extends ResourceSupport{
    
    private int limit;
    private int page;
    private int offset;
    private int size;
    private List<T> items = new ArrayList();    
    
    @JsonCreator
    public Items(
            @JsonProperty("offset") int offset,
            @JsonProperty("limit") int limit,
            @JsonProperty("page") int page,
            @JsonProperty("size") int size,
            @JsonProperty("items") List<T> items
    ) {
        this.offset = offset;
        this.limit = limit;
        this.page = page;
        this.size = size;
        this.items = items;
    }    

    public int getLimit() {
        return limit;
    }

    public int getPage() {
        return page;
    }

    public int getOffset() {
        return offset;
    }

    public int getSize() {
        return size;
    }

    public List<T> getItems() {
        return items;
    }
    
    
    
}
