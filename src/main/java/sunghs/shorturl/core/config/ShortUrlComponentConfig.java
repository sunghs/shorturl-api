package sunghs.shorturl.core.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.RegexValidator;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import sunghs.shorturl.api.model.ShortUrlComponent;

@Slf4j
@Configuration
@EnableConfigurationProperties
public class ShortUrlComponentConfig implements InitializingBean {

    @Bean
    @ConfigurationProperties(prefix = "short")
    public ShortUrlComponent shortUrlComponent() {
        return new ShortUrlComponent();
    }

    @Bean
    public UrlValidator urlValidator() {
        RegexValidator regex = new RegexValidator(new String[] {"http", "https","((localhost)(:[0-9]+))"});
        return new UrlValidator(regex, 0);
    }

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(shortUrlComponent(), "ShortUrlComponent must not be null");
    }
}
