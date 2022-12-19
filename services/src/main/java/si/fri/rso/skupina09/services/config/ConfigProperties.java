package si.fri.rso.skupina09.services.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ConfigBundle("config-properties")
@ApplicationScoped
public class ConfigProperties {

    @ConfigValue(watch=true)
    private Boolean maintenanceMode;

    private Boolean broken;

    @ConfigValue(value = "rapid-api.x-rapidapi-key", watch = true)
    private String xRapidAPIKey;

    @ConfigValue(value = "rapid-api.x-rapidapi-host", watch = true)
    private String xRapidAPIHost;

    public Boolean getMaintenanceMode() {
        return this.maintenanceMode;
    }

    public void setMaintenanceMode(final Boolean maintenanceMode) {
        this.maintenanceMode = maintenanceMode;
    }

    public Boolean getBroken() {
        return this.broken;
    }

    public void setBroken(final Boolean broken) {
        this.broken = broken;
    }

    public String getxRapidAPIKey() {
        return xRapidAPIKey;
    }

    public void setxRapidAPIKey(String xRapidAPIKey) {
        this.xRapidAPIKey = xRapidAPIKey;
    }

    public String getxRapidAPIHost() {
        return xRapidAPIHost;
    }

    public void setxRapidAPIHost(String xRapidAPIHost) {
        this.xRapidAPIHost = xRapidAPIHost;
    }
}
