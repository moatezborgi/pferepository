package tn.ey.healthsystem.eureka.ccmapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableDiscoveryClient
@EnableScheduling
 @ConfigurationPropertiesScan
@EnableCaching

@SpringBootApplication
 public class CcmApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcmApplication.class, args);
    }

}
