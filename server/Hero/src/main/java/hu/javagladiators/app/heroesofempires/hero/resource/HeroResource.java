package hu.javagladiators.app.heroesofempires.hero.resource;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import hu.javagladiators.app.heroesofempires.hero.model.Hero;
import hu.javagladiators.app.heroesofempires.hero.model.Items;
import java.util.ArrayList;
import java.util.List;


@Path("hero")
@Produces({ MediaType.APPLICATION_JSON+";charset=UTF-8"})
public class HeroResource {
    private final List<Hero> heroes = new ArrayList<>();

    public HeroResource() {
        for(int i=0;i<500;i++)
            heroes.add(new Hero("Hero:"+i, "desc:"+i, true));
    }
    
/*
    @GET
    public List all(){
        return heroes;
    }
  */
    
    @GET
    public Response get(@QueryParam("name") String name){
        for(Hero h:heroes)
            if(h.getName().equals(name))
                return Response.ok(name).build();        
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    
    
    @GET
    @Path("{offset}/{limit}")
    public Items query(@PathParam("offset") int pStart, @PathParam("limit") int pCount ){
        Items res = new Items(pCount, pStart, heroes.size());
        for(int i=0; (i+pStart)<heroes.size() && i<pCount; i++)
            res.addItems(heroes.get(+pStart));
        return res;
    }

    @DELETE
    public Response delete(@QueryParam("name") String name ){
        for(Hero h:heroes)
            if(h.getName().equals(name))
                return Response.ok().build();
        return Response.status(Response.Status.NOT_FOUND).build();
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
