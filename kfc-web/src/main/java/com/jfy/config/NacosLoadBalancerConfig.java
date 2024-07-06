package com.jfy.config;


import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.loadbalancer.NacosLoadBalancer;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
public class NacosLoadBalancerConfig {
    @Resource //这里只能用@Resource注入，不能用@Autowired注入
    private NacosDiscoveryProperties nacosDiscoveryProperties;
    @Bean
    public ReactorLoadBalancer<ServiceInstance> getBalancer(Environment env,
                                                            LoadBalancerClientFactory factory){
        System.out.println("nacosDiscoveryProperties-------- "+nacosDiscoveryProperties);
//获取付提供者的服务名称
                String name=env.getProperty(factory.PROPERTY_NAME);
//获取ObjectProvider对象
        ObjectProvider<ServiceInstanceListSupplier> provider =
                factory.getLazyProvider(name,ServiceInstanceListSupplier.class);
//返回NacosLoadBalancer对象
        return new NacosLoadBalancer(provider,name,
                nacosDiscoveryProperties);
    }
}