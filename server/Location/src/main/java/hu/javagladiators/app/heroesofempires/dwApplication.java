package hu.javagladiators.app.heroesofempires;

import hu.javagladiators.app.heroesofempires.resources.LocationsResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class dwApplication extends Application<dwConfiguration> {

    public static void main(final String[] args) throws Exception {
        new dwApplication().run(args);
    }

    @Override
    public String getName() {
        return "dw";
    }

    @Override
    public void initialize(final Bootstrap<dwConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final dwConfiguration configuration,
                    final Environment environment) {
     
    final LocationsResource resource = new LocationsResource();
    environment.jersey().register(resource);        
        // TODO: implement application
    }

}
