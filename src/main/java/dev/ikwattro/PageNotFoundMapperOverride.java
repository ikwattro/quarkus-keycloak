package dev.ikwattro;

import io.quarkus.qute.Template;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

//@Provider
public class PageNotFoundMapperOverride implements ExceptionMapper<NotFoundException> {

    @Inject
    Template error404;

    @Override
    @Produces(MediaType.TEXT_HTML)
    public Response toResponse(NotFoundException e) {
        return Response.ok(error404.render()).build();
    }
}
