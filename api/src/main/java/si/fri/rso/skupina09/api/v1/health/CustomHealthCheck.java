package si.fri.rso.skupina09.api.v1.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import si.fri.rso.skupina09.services.config.ConfigProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Liveness
@ApplicationScoped
public class CustomHealthCheck implements HealthCheck {

    @Inject
    private ConfigProperties configProperties;

    @Override
    public HealthCheckResponse call() {
        if (configProperties.getBroken()) {
            return HealthCheckResponse.down(CustomHealthCheck.class.getSimpleName());
        } else {
            return HealthCheckResponse.up(CustomHealthCheck.class.getSimpleName());
        }
    }
}
