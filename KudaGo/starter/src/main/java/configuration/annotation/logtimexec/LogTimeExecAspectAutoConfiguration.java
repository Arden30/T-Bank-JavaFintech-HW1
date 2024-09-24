package configuration.annotation.logtimexec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTimeExecAspectAutoConfiguration {
    @Bean
    public LogTimeExecAspect logTimeExecAspect() {
        return new LogTimeExecAspect();
    }
}
