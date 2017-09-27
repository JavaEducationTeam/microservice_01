package hu.javagladiators.app.heroesofempires.resources;

import com.codahale.metrics.annotation.Timed;
import hu.javagladiators.app.heroesofempires.core.Location;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author hallgato
 */
@Path("/location")
@Produces(MediaType.APPLICATION_JSON)
public class LocationsResource {


    @GET
    public Location get() {
        Location res =new Location();
        res.setName("ssssss");
        return res;
    }    
}
