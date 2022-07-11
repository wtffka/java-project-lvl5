package hexlet.code.app.config.rollbar;

import com.rollbar.notifier.Rollbar;
import com.rollbar.notifier.config.Config;
import com.rollbar.spring.webmvc.RollbarSpringConfigBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration()
@ComponentScan({
        "hexlet.code.app"
})
public class RollbarConfig {

    @Value("${rollbar_token:}")
    private String rollbarToken;

    @Value("${spring.profiles.active:}")
    private String activeProfile;

    /**
     * Register a Rollbar bean to configure App with Rollbar.
     * @return config
     */
    @Bean
    public Rollbar rollbar() {

        return new Rollbar(getRollbarConfigs(rollbarToken));
    }

    private Config getRollbarConfigs(String accessToken) {
        // Reference ConfigBuilder.java for all the properties you can set for Rollbar
        return RollbarSpringConfigBuilder.withAccessToken(accessToken)
                .environment("production")
                .enabled(activeProfile.equals("prod"))
                .build();
    }
}
