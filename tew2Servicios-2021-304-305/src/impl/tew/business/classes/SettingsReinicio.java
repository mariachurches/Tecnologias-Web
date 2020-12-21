package impl.tew.business.classes;

import com.tew.infrastructure.Factories;
import com.tew.persistence.ConfigBBDDDao;

public class SettingsReinicio {

	public void reinicioBBDD(String url) throws Exception {
		ConfigBBDDDao dao = Factories.persistence.createConfigBBDDDao();
		try {
			dao.reiniciarBBDD(url);
		}
		catch (Exception ex) {
			System.err.print("Error al reiniciar la BBDD");
		}
	}
}
