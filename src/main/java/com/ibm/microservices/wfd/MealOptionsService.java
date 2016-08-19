package com.ibm.microservices.wfd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.ibm.microservices.wfd.model.MealEntree;

@Component
@RestController
@ResponseBody
public class MealOptionsService {

  @Autowired
  private RestTemplate restTemplate;

  @RequestMapping("/meal-options")
  public Menu getMealOptions() {

    Menu newMenu = new Menu();

    MealEntree entrees =
        this.restTemplate.getForObject("http://entree-service/menu", MealEntree.class);
    newMenu.setEntrees(entrees);

    return newMenu;
  }

}

class Menu {

  private MealEntree entrees;

  Menu(){
    this.entrees = new MealEntree();
  }

  public MealEntree getEntrees(){
    return this.entrees;
  }

  public void setEntrees(MealEntree entrees){
    this.entrees = entrees;
  }

}
