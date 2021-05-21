package sunghs.shorturl.core.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import sunghs.shorturl.api.model.ShortUrlComponent;

@Configuration
@EnableConfigurationProperties
public class ShortUrlComponentConfig implements InitializingBean {

    @Bean
    @ConfigurationProperties(prefix = "short")
    public ShortUrlComponent shortUrlComponent() {
        return new ShortUrlComponent();
    }

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(shortUrlComponent(), "ShortUrlComponent must not be null");
    }
}
