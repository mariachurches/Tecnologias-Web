package impl.tew.business.classes;

import com.tew.infrastructure.Factories;
import com.tew.persistence.PublicacionDao;

public class PublicacionesBaja {
	public void deleteP(String email) throws Exception {

		PublicacionDao dao = Factories.persistence.createPublicacionDao();
		try {
			dao.deleteP(email);
		}
		catch (Exception ex) {
			throw new Exception("Publicacion no borrada " + email, ex);
		}
	}

	public void deleteAll() throws Exception {

		PublicacionDao dao = Factories.persistence.createPublicacionDao();
		try {
			dao.deleteAll();
		}
		catch (Exception ex) {
			throw new Exception("No habia publicaciones");
		}
	}

}
