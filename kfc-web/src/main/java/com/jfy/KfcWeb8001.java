package com.jfy;

import com.jfy.config.NacosLoadBalancerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@LoadBalancerClients(defaultConfiguration = NacosLoadBalancerConfig.class)
@ServletComponentScan("com.jfy.filter")
public class KfcWeb8001 {
    public static void main(String[] args) {
        SpringApplication.run(KfcWeb8001.class,args);
    }
}