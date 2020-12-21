package impl.tew.business.classes;

import java.util.List;

import com.tew.infrastructure.Factories;
import com.tew.model.Amigos;
import com.tew.model.Usuario;
import com.tew.persistence.AmigosDao;

/**
 * Esta clase pertenece a la capa de persistencia y ejecuta un proceso 
 * de negocio.
 * 
 * Si el problema a resolver fuese m??????s complejo habr?????? otras muchas clases de 
 * este estilo en esta capa. 
 * 
 * Las clases que forman la capa de negocio pueden necesitar acceder a la capa
 * de persistencia para resolver su cometido. Esta oculta los detalles de la 
 * tecnolog??????a de persistencia ofreciendo m??????todos del estilo: crear, borrer, 
 * actualizar y diversas consultas.
 * 
 */
public class GetAmigos {

	public List<Amigos> getAmigos() throws Exception {
		AmigosDao dao = Factories.persistence.createAmigosDao();
		return  dao.getAmigos();

	}
	
	public List<Usuario> getNoAmigos(String email) throws Exception {
		AmigosDao dao = Factories.persistence.createAmigosDao();
		return  dao.getNoAmigos(email);

	}
}
