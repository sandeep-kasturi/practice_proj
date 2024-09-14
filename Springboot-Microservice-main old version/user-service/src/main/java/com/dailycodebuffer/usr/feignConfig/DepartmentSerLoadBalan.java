package com.dailycodebuffer.usr.feignConfig;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;

import feign.Feign;

@LoadBalancerClient(name="DEPARTMENT-SERVICE",configuration = CustomLoadBalancerConfiguration.class)
public class DepartmentSerLoadBalan {

	@LoadBalanced
	@Bean
	public Feign.Builder feBuilder(){
		return Feign.builder();
	}
}
