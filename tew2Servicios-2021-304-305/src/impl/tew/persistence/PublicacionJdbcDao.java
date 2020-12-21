package impl.tew.persistence;

import java.sql.*;
import java.util.*;

import com.tew.model.Publicacion;
import com.tew.persistence.PublicacionDao;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;
import com.tew.persistence.exception.PersistenceException;

public class PublicacionJdbcDao implements PublicacionDao {

	@Override
	public List<Publicacion> getPublicaciones() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		
		List<Publicacion> publicaciones = new ArrayList<Publicacion>();

		try {
			// En una implemenntaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from PUBLICACIONES");
			rs = ps.executeQuery();

			while (rs.next()) {
				Publicacion publicacion = new Publicacion();
				
				publicacion.setId(rs.getInt("ID"));
				publicacion.setEmail(rs.getString("EMAIL"));
				publicacion.setTitulo(rs.getString("TITULO"));
				publicacion.setTexto(rs.getString("TEXTO"));
				publicacion.setFecha(rs.getLong("FECHA"));
				
				publicaciones.add(publicacion);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
		return publicaciones;
	}

	@Override
	public void save(Publicacion publicacion) throws AlreadyPersistedException {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		
		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(
					"insert into PUBLICACIONES (email, titulo, texto, fecha) " +
					"values (?, ?, ?, ?)");

			ps.setString(1, publicacion.getEmail());
			ps.setString(2, publicacion.getTitulo());
			ps.setString(3, publicacion.getTexto());
			ps.setLong(4, System.currentTimeMillis());

			rows = ps.executeUpdate();
			
			if (rows != 1) {
				throw new AlreadyPersistedException("Publicacion " + publicacion + " already persisted");
			} 

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		

	}
	
	@Override
	public void saveBBDD(Publicacion publicacion) throws AlreadyPersistedException {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		
		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(
					"insert into PUBLICACIONES (email, titulo, texto, fecha) " +
					"values (?, ?, ?, ?)");

			ps.setString(1, publicacion.getEmail());
			ps.setString(2, publicacion.getTitulo());
			ps.setString(3, publicacion.getTexto());
			ps.setLong(4, publicacion.getFecha());
			
			

			rows = ps.executeUpdate();
			
			if (rows != 1) {
				throw new AlreadyPersistedException("Publicacion " + publicacion + " already persisted");
			} 

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		

	}

	@Override
	public void update(Publicacion publicacion) throws NotPersistedException {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		
		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(
					"update PUBLICACION " +
					"set titulo = ?, texto = ?, fecha = ?" +
					"where id = ? and email = ?");
			
			ps.setString(1, publicacion.getTitulo());
			ps.setString(2, publicacion.getTexto());
			
			//Fecha actual de actualización en milisegundos
			java.util.Date utilDate = new java.util.Date();
			long lnMilisegundos = utilDate.getTime();
			ps.setLong(3, lnMilisegundos); 
			
			ps.setLong(4, publicacion.getId());
			ps.setString(5, publicacion.getEmail());

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Publicacion " + publicacion + " not found");
			} 
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}

	}

	@Override
	public void delete(int id) throws NotPersistedException {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		
		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("delete from PUBLICACIONES where id = ?");
			
			ps.setInt(1, id);

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Publicacion " + id + " not found");
			} 
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		

	}
	
	@Override
	public void deleteAll() throws NotPersistedException {
		PreparedStatement ps = null;
		Connection con = null;
		
		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("delete from PUBLICACIONES");
			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		

	}
	
	@Override
	public void deleteP(String email) throws NotPersistedException {
		PreparedStatement ps = null;
		Connection con = null;

		
		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("delete from PUBLICACIONES where EMAIL = ?");
			
			ps.setString(1, email);

			ps.executeUpdate();

			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		

	}


	@Override
	public Publicacion findById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Publicacion publicacion = null;
		
		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from PUBLICACION where id = ?");
			ps.setLong(1, id);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				publicacion = new Publicacion();
				
				publicacion.setId(rs.getInt("ID"));
				publicacion.setEmail(rs.getString("EMAIL"));
				publicacion.setTitulo(rs.getString("TITULO"));
				publicacion.setTexto(rs.getString("TEXTO"));
				publicacion.setFecha(rs.getLong("FECHA"));

			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
		return publicacion;
	}

	@Override
	public List<Publicacion> findByEmail(String email) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Publicacion publicacion = null;
		
		List<Publicacion> publicaciones = new ArrayList<Publicacion>();
		
		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from PUBLICACIONES where EMAIL = ?");
			ps.setString(1, email);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				publicacion = new Publicacion();
				
				publicacion.setId(rs.getInt("ID"));
				publicacion.setEmail(rs.getString("EMAIL"));
				publicacion.setTitulo(rs.getString("TITULO"));
				publicacion.setTexto(rs.getString("TEXTO"));
				publicacion.setFecha(rs.getLong("FECHA"));
				publicacion.setFechaCadena(rs.getLong("FECHA"));
				
				publicaciones.add(publicacion);

			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
		return publicaciones;
	}

	@Override
	public List<Publicacion> ordenByFechas(String email) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Publicacion publicacion = null;
		
		List<Publicacion> publicaciones = new ArrayList<Publicacion>();
		
		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from PUBLICACIONES where EMAIL = ? ORDER BY FECHA");
			ps.setString(1, email);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				publicacion = new Publicacion();
				
				publicacion.setId(rs.getInt("ID"));
				publicacion.setEmail(rs.getString("EMAIL"));
				publicacion.setTitulo(rs.getString("TITULO"));
				publicacion.setTexto(rs.getString("TEXTO"));
				publicacion.setFecha(rs.getLong("FECHA"));
				publicacion.setFechaCadena(rs.getLong("FECHA"));
				
				publicaciones.add(publicacion);

			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
		return publicaciones;
	}

	@Override
	public List<Publicacion> ordenByTitulo(String email) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Publicacion publicacion = null;
		
		List<Publicacion> publicaciones = new ArrayList<Publicacion>();
		
		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from PUBLICACIONES where EMAIL = ? ORDER BY UPPER(TITULO)");
			ps.setString(1, email);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				publicacion = new Publicacion();
				
				publicacion.setId(rs.getInt("ID"));
				publicacion.setEmail(rs.getString("EMAIL"));
				publicacion.setTitulo(rs.getString("TITULO"));
				publicacion.setTexto(rs.getString("TEXTO"));
				publicacion.setFecha(rs.getLong("FECHA"));
				publicacion.setFechaCadena(rs.getLong("FECHA"));
				
				publicaciones.add(publicacion);

			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
		return publicaciones;
	}

	@Override
	public List<String> getAmigos(String email) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		
		List<String> amigos = new ArrayList<String>();
		
		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select EMAIL_AMIGO from AMIGO where EMAIL_USUARIO LIKE ? and ACEPTADA=?");
			
			ps.setString(1, email);
			ps.setBoolean(2, true);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				String amigo = rs.getString("EMAIL_AMIGO");
				amigos.add(amigo);

			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
		return amigos;
	}

	@Override
	public List<Publicacion> listadoPubliAmigos(List<String> amigos) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Publicacion publicacion = null;
		
		List<Publicacion> publicacionesAmigos = new ArrayList<Publicacion>();
		
		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from PUBLICACIONES where EMAIL = ?");
			
			@SuppressWarnings("rawtypes")
			Iterator it = amigos.iterator();
			while(it.hasNext()) {
				ps.setString(1, it.next().toString());
			
				rs = ps.executeQuery();
				while (rs.next()) {
					publicacion = new Publicacion();
				
					publicacion.setId(rs.getInt("ID"));
					publicacion.setEmail(rs.getString("EMAIL"));
					publicacion.setTitulo(rs.getString("TITULO"));
					publicacion.setTexto(rs.getString("TEXTO"));
					publicacion.setFecha(rs.getLong("FECHA"));
					publicacion.setFechaCadena(rs.getLong("FECHA"));
				
					publicacionesAmigos.add(publicacion);

				}
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
		return publicacionesAmigos;

	
	}
}
