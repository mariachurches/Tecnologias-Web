package com.tew.presentation;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.tew.model.User;

@ManagedBean(name="aplicacion",eager=true)
@ApplicationScoped
public class BeanAplicacion implements Serializable  {

	private static final long serialVersionUID = -4995765962699086087L;
	private ArrayList<User> user_en = null;


	public BeanAplicacion() {}


	public ArrayList<User> getUser_en() {
		return user_en;
	}


	public void setUser_en(ArrayList<User> user_en) {
		this.user_en = user_en;
	}
	
	public void addUser(User u) {
		System.out.println("Nevo usuario en el vector");
		user_en.add(u);
		System.out.println("Nevo usuario en el vector quedan " + user_en.size());

	}
	
	public void removeUser(User u) {
		System.out.println("Borrado usuario en el vector");
		for(int i=0; i< user_en.size(); i++) {
			if(u.getLogin()==user_en.get(i).getLogin()) user_en.remove(i);
		}
		System.out.println("Borrado usuario en el vector"+ user_en.size());

	}
	

	@PostConstruct
	public void init() {
		System.out.println("BeanSettings - PostConstruct");
		user_en = new ArrayList<User>();

		
	}




}
