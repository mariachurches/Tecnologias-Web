package impl.tew.business;

import javax.faces.context.FacesContext;

import com.tew.business.LoginService;
import com.tew.model.User;
import com.tew.presentation.BeanUsuario;

public class SimpleLoginService implements LoginService {

	private BeanUsuario usuario  =  (BeanUsuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("usuario"));
	private String[] p;
	public User verify(String login, String password) {
		if (!validLogin(login, password)) return null;
	//	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "email", p[0]);
		//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "nombre", p[2]);
	//	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "rol", p[3]);
		return new User(p[0],p[2],p[3]);

	}

	private boolean validLogin(String login, String password) {
		p = usuario.CompruebaLogin(login);
		if(p[0]=="no-usuario") return false;
		if (p[1].equals(password)) return true;
		return false ;
	}
}