package si.fri.rso.samples.comments.api.v1.resources;

import com.kumuluz.ee.common.runtime.EeRuntime;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import si.fri.rso.samples.comments.api.v1.configuration.RestProperties;

@Path("demo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class DemoResource {

    @Inject
    private RestProperties restProperties;

    @GET
    @Path("instanceid")
    public Response getInstanceId() {

        String instanceId =
                "{\"instanceId\" : \"" + EeRuntime.getInstance().getInstanceId() + "\"}";

        return Response.ok(instanceId).build();
    }

    @POST
    @Path("healthy")
    public Response setHealth(Boolean healthy) {
        restProperties.setHealthy(healthy);
        return Response.ok().build();
    }

    @POST
    @Path("healthy")
    public Response setHealthFalse() {
        restProperties.setHealthy(false);
        return Response.ok().build();
    }

    @POST
    @Path("healthy")
    public Response setHealthTrue() {
        restProperties.setHealthy(true);
        return Response.ok().build();
    }

    @GET
    @Path("healthy")
    public Boolean getHealth() {
        return restProperties.isHealthy();
    }

}
