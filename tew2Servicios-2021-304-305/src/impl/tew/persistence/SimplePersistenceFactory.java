package impl.tew.persistence;


import com.tew.persistence.AmigosDao;
import com.tew.persistence.ConfigBBDDDao;
import com.tew.persistence.PersistenceFactory;
import com.tew.persistence.PublicacionDao;
import com.tew.persistence.UsuarioDao;

/**
 * Implementaci??????n de la factoria que devuelve implementaci??????n de la capa
 * de persistencia con Jdbc 
 * 
 * @author Enrique
 *
 */
public class SimplePersistenceFactory implements PersistenceFactory {

	@Override
	public UsuarioDao createUsuarioDao() {
		return new UsuarioJdbcDao();
	}

	@Override
	public PublicacionDao createPublicacionDao() {
		return new PublicacionJdbcDao();
	}

	@Override
	public AmigosDao createAmigosDao() {
		return new AmigosJdbcDao();
	}

	@Override
	public ConfigBBDDDao createConfigBBDDDao() {
		return new ConfigBBDDJdbcDao();
	}


}
