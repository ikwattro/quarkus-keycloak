package dev.ikwattro;

import io.quarkus.security.User;
import io.quarkus.security.identity.SecurityIdentity;
import org.jboss.resteasy.reactive.NoCache;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/api/users")
public class UsersResource {

    @Inject
    SecurityIdentity securityIdentity;

    @GET
    @Path("/me")
    @RolesAllowed("user")
    @NoCache
    public User me() {
        return new User(securityIdentity);
    }

    @GET
    @Path("/all")
    @RolesAllowed("admin")
    @NoCache
    public List<String> allUsers() {
        return List.of("Mark", "John", "Suzy");
    }

    @GET
    @Path("/manage")
    @RolesAllowed("OPS")
    @NoCache
    public List<String> manageUsers() {
        return List.of();
    }

    public static class User {
        private final String username;

        User(SecurityIdentity identity) {
            this.username = identity.getPrincipal().getName();
        }

        public String getUsername() {
            return username;
        }
    }
}
