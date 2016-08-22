package com.ibm.microservices.wfd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.ibm.microservices.wfd.model.MealEntree;
import com.ibm.microservices.wfd.model.MealAppetizer;

@Component
@RestController
@ResponseBody
public class MealOptionsService {

  @Autowired
  private RestTemplate restTemplate;

  @RequestMapping("/meal-options")
  public Menu getMealOptions() {

    Menu newMenu = new Menu();

    MealAppetizer apps =
        this.restTemplate.getForObject("http://appetizer-service/menu", MealAppetizer.class);
    newMenu.setAppetizers(apps);

    MealEntree entrees =
        this.restTemplate.getForObject("http://entree-service/menu", MealEntree.class);
    newMenu.setEntrees(entrees);

    return newMenu;
  }

}

class Menu {

  private MealEntree entrees;
  private MealAppetizer apps;

  Menu(){
    this.entrees = new MealEntree();
    this.apps = new MealAppetizer();
  }

  public MealEntree getEntrees(){
    return this.entrees;
  }

  public void setEntrees(MealEntree entrees){
    this.entrees = entrees;
  }

  public MealAppetizer getAppetizers(){
    return this.apps;
  }

  public void setAppetizers(MealAppetizer apps){
    this.apps = apps;
  }

}
