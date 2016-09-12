package com.ibm.microservices.wfd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.ibm.microservices.wfd.model.MealEntree;
import com.ibm.microservices.wfd.model.MealAppetizer;
import com.ibm.microservices.wfd.model.MealDessert;

@Component
@RestController
@ResponseBody
public class MenuService {

  @Autowired
  private RestTemplate restTemplate;

  @RequestMapping("/menu")
  public Menu getMenu() {

    Menu newMenu = new Menu();

    MealAppetizer apps =
        this.restTemplate.getForObject("http://appetizer-service/menu", MealAppetizer.class);
    newMenu.setAppetizers(apps);

    MealEntree entrees =
        this.restTemplate.getForObject("http://entree-service/menu", MealEntree.class);
    newMenu.setEntrees(entrees);

    MealDessert desserts =
      this.restTemplate.getForObject("http://dessert-service/menu", MealDessert.class);
    newMenu.setDesserts(desserts);

    return newMenu;
  }

}

class Menu {

  private MealEntree entrees;
  private MealAppetizer apps;
  private MealDessert desserts;

  Menu(){
    this.entrees = new MealEntree();
    this.apps = new MealAppetizer();
    this.desserts = new MealDessert();
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

  public MealDessert getDesserts(){
    return this.desserts;
  }

  public void setDesserts(MealDessert desserts){
    this.desserts = desserts;
  }

}
