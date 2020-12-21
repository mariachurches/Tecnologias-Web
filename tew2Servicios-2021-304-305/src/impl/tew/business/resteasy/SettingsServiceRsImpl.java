package impl.tew.business.resteasy;

import java.util.ArrayList;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.business.resteasy.SettingsServiceRs;
import impl.tew.business.classes.SettingsLogin;
import impl.tew.business.classes.SettingsReinicio;

public class SettingsServiceRsImpl implements SettingsServiceRs {


	@Override
	public void reinicioBBDD(String url) throws Exception {
		new SettingsReinicio().reinicioBBDD(url);	
	}

	@Override
	public void anadirSesion(String email) throws Exception {
		new SettingsLogin().añadirUsuario(email);
	}

	@Override
	public void deleteSesion(String email) throws EntityNotFoundException {
		new SettingsLogin().borrarUsuario(email);
	}

	@Override
	public ArrayList<String> getUsuarios() throws Exception {
		return new SettingsLogin().vectorUsuarios();
	}

	@Override
	public void añadirSesion(String email) throws Exception {
		new SettingsLogin().añadirUsuario(email);		
	}





}
