package hu.javagladiators.app.heroesofempires.hero.resource;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import hu.javagladiators.app.heroesofempires.data.ObjectStore;
import hu.javagladiators.app.heroesofempires.hero.model.Hero;
import hu.javagladiators.app.heroesofempires.hero.model.Items;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.HEAD;
import org.glassfish.jersey.examples.linking.representation.HeroRepresentation;
import org.glassfish.jersey.examples.linking.representation.ItemsRepresentation;


@Path("hero")
@Produces({ MediaType.APPLICATION_JSON+";charset=UTF-8"})
public class HeroResource {
    private List<Hero> heroes = new ArrayList<>();

    public HeroResource() {
        for(int i=0;i<500;i++)
            heroes.add(new Hero("Hero:"+i, "desc:"+i, true));
    }
    

    @GET
    public List all(){
        return heroes;
    }
    
    @GET
    public Response get(@QueryParam("name") String name){
        for(Hero h:heroes)
            if(h.getName().equals(name))
                return Response.ok(name).build();        
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    
    
    @GET
    @Path("{start}/{count}")
    public Items get(@PathParam("start") int pStart, @PathParam("count") int pCount ){
        Items res = new Items(pCount, pStart, heroes.size());
        return res;
    }

    @DELETE
    public Response delete(@QueryParam("name") String name ){
        for(Hero h:heroes)
            if(h.getName().equals(name))
                return Response.ok().build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    

    @GET
    public ItemsRepresentation query(
            @Context javax.ws.rs.core.UriInfo info,
            @QueryParam("offset") @DefaultValue("-1") int offset, @DefaultValue("-1") @QueryParam("limit") int limit) {

        if (offset == -1 || limit == -1) {
            offset = offset == -1 ? 0 : offset;
            limit = limit == -1 ? 10 : limit;

            throw new WebApplicationException(
                    Response.seeOther(info.getRequestUriBuilder().queryParam("offset", offset)
                            .queryParam("limit", limit).build())
                            .build()
            );
        }

        ItemsRepresentation model = new ItemsRepresentation(offset, limit);
        model.setModelLimit(ObjectStore.heroes.size());
        
        for (int i = offset; i < (offset + limit) && i < ObjectStore.heroes.size(); i++) {
            model.getItems().add(new HeroRepresentation(ObjectStore.heroes.get(i)));
        }
  
        return model;
    }

    
    @POST
    public Response add(
            @FormParam("name") String name,
            @FormParam("description") String description
    ){
        for(Hero h:heroes)
            if(h.getName().equals(name))
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        Hero h = new Hero(name, description, true);
        return Response.ok(h).build();
    }
    
}
