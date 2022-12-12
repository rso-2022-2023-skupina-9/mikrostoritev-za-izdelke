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
}
