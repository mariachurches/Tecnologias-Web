package impl.tew.business.classes;

import java.util.ArrayList;
import com.tew.model.Usuario;
import com.tew.model.UsuariosSesion;
import impl.tew.persistence.UsuarioJdbcDao;

public class SettingsLogin {
	
	private UsuariosSesion us = new UsuariosSesion();
	private UsuarioJdbcDao udao= new UsuarioJdbcDao();
	private ArrayList<Usuario> array =null;
	private ArrayList<String> list =null;

	@SuppressWarnings("static-access")
	public void añadirUsuario(String email) {
		Usuario user = udao.findByEmail(email);
		us.addUser(user);
		ArrayList<Usuario> usuarios = us.getSusuarios();
		for(Usuario u : usuarios) {
			System.out.println(u.getEmail());
		}
	}
	
	public void borrarUsuario(String email) {
		us.removeUser(email);}
	
	/**
	public JSONArray vectorUsuarios() {
		array = us.getSusuarios();
		JSONArray jsArray = new JSONArray();
		for(Usuario u : array) jsArray.add(u.getEmail());
		return jsArray;
	}
	*/
	
	@SuppressWarnings("static-access")
	public ArrayList<String> vectorUsuarios() {
		list = new ArrayList<String>();
		array = us.getSusuarios();
		for(Usuario u : array) list.add(u.getEmail());
		return list;
	}

}
