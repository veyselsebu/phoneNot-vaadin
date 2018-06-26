package com.example.phoneNot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.VerticalLayout;


@SpringComponent
public class pnLayout extends VerticalLayout {

	@Autowired
	pnRepository repo;
	
	
  void init() {
	update();  
  }
  
  private void update() {
	  datas();
  }
  
  public List<PhoneBook> datas() {
	  List <PhoneBook>datas=new ArrayList();
	  removeAllComponents();
	  repo.findAll().forEach(datas::add);
	  return datas;
  }
  public void add(PhoneBook pb) {
	  repo.save(pb);
	  update();
  }

  public void delet(PhoneBook pb) {
	  repo.delete(pb);
	  update();
  }
	
	
	
	
	
	
}
