package configuration;

import com.microstudy.users.controller.NacosWeight;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonConfiguration {

    @Bean
    public IRule ribbonRule(){
//        return new RoundRobinRule();
        return new NacosWeight();
    }
}
