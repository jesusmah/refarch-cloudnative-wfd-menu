package com.ibm.microservices.wfd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ibm.microservices.wfd.model.MealEntree;
import com.ibm.microservices.wfd.model.MealAppetizer;
import com.ibm.microservices.wfd.model.MealDessert;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Component
@EnableConfigurationProperties
public class MenuAggregate {

  @Autowired
  private MenuConfiguration config;

  @Autowired
  private RestTemplate restTemplate;

  @HystrixCommand(fallbackMethod="getDefaultAppetizers", groupKey="MenuServiceAggregator", commandKey="GetAppetizers")
  public MealAppetizer getCurrentAppetizers(){
    return this.restTemplate.getForObject(this.config.getAppetizers(), MealAppetizer.class);
  }

  public MealAppetizer getDefaultAppetizers(){
    return new MealAppetizer();
  }

  @HystrixCommand(fallbackMethod="getDefaultEntrees", groupKey="MenuServiceAggregator", commandKey="GetEntrees")
  public MealEntree getCurrentEntrees(){
    return this.restTemplate.getForObject(this.config.getEntrees(), MealEntree.class);
  }

  public MealEntree getDefaultEntrees(){
    return new MealEntree();
  }

  @HystrixCommand(fallbackMethod="getDefaultDesserts", groupKey="MenuServiceAggregator", commandKey="GetDesserts")
  public MealDessert getCurrentDesserts(){
    return this.restTemplate.getForObject(this.config.getDesserts(), MealDessert.class);
  }

  public MealDessert getDefaultDesserts(){
    return new MealDessert();
  }

}
