package impl.tew.business;

import java.util.ArrayList;

import com.tew.business.SettingsService;

import impl.tew.business.classes.SettingsLogin;
import impl.tew.business.classes.SettingsReinicio;

public class SimpleSettingsService implements SettingsService{

	@Override
	public void reinicioBBDD(String url) throws Exception {
		new SettingsReinicio().reinicioBBDD(url) ;
	}

	@Override
	public void añadirSesion(String email) throws Exception {
		new SettingsLogin().añadirUsuario(email);
		
	}

	@Override
	public void deleteSesion(String email) throws Exception {
		new SettingsLogin().borrarUsuario(email);
		
	}

	@Override
	public ArrayList<String> getUsuarios() throws Exception {
		return new SettingsLogin().vectorUsuarios();
	}



}
