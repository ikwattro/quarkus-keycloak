package dev.ikwattro;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/")
public class IndexResource {

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance index();
        public static native TemplateInstance names(List<String> names);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance index() {
        return Templates.index();
    }

    @GET
    @Path("login")
    public Response login(@QueryParam("redirect") String redirect) {
        return Response.temporaryRedirect(URI.create(redirect)).build();
    }

    @GET
    @RolesAllowed("OPS")
    @Path("names")
    public TemplateInstance names() {
        return Templates.names(List.of("john", "alice", "bob"));
    }

}
