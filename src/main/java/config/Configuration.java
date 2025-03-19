package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:api.properties"
})
public interface Configuration extends Config {

    @Config.Key("base.url")
    String getBaseUrl();

    @Config.Key("connection.timeout")
    int getConnectionTimeout();

}
