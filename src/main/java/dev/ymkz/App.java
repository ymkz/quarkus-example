package dev.ymkz;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@OpenAPIDefinition(
    tags = {@Tag(name = "book")},
    servers = {@Server(url = "http://localhost:8080", description = "ローカル環境")},
    info = @Info(title = "API仕様書", version = "1.0.0"))
@ApplicationScoped
@ApplicationPath("api/v1")
public class App extends Application {}
