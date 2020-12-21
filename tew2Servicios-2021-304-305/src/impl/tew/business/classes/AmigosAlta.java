package impl.tew.business.classes;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.infrastructure.Factories;
import com.tew.persistence.AmigosDao;
import com.tew.persistence.exception.AlreadyPersistedException;

public class AmigosAlta {
	
	public void mutuo(String email, String emailAmigo) throws EntityNotFoundException {

		AmigosDao dao = Factories.persistence.createAmigosDao();
		try {
			dao.mutuo(email, emailAmigo); 
		}
		catch (AlreadyPersistedException ex) {
			throw new EntityNotFoundException("Amigo no guardado " + email, ex);
		}

	}


}
