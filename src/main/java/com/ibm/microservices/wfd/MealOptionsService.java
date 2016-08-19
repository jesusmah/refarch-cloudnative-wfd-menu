package com.ibm.microservices.wfd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.ArrayList;

@Component
@RestController
@ResponseBody
public class MealOptionsService {

  @Autowired
  private RestTemplate restTemplate;

  @RequestMapping("/meal-options")
  public Menu getMealOptions() {

    Menu newMenu = new Menu();
    
    MealOptions entrees =
        this.restTemplate.getForObject("http://entree-service/menu", MealOptions.class);
    newMenu.setEntrees(entrees);

    return newMenu;
  }

}

class MealOptions {

    private List<String> options;

    MealOptions(){
      this.options = new ArrayList<String>();
    }

    public List<String> getOptions(){
      return this.options;
    }

    public void setOptions(List<String> options){
      this.options = options;
    }
}

class Menu {

  private MealOptions entrees;

  Menu(){
    this.entrees = new MealOptions();
  }

  public MealOptions getEntrees(){
    return this.entrees;
  }

  public void setEntrees(MealOptions entrees){
    this.entrees = entrees;
  }

}
