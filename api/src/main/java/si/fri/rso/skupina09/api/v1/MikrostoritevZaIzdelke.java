package si.fri.rso.skupina09.api.v1;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@OpenAPIDefinition(
    info = @Info(
        title = "Mikrostoritev za izdelke",
        version = "v1",
        contact = @Contact(email = "zk0821@student.uni-lj.si"),
        license = @License(name = "dev"),
        description = "API za izdelke"
    ),
    servers = @Server(url = "http://localhost:8080/")
)
@ApplicationPath("/v1")
public class MikrostoritevZaIzdelke extends Application {
}
