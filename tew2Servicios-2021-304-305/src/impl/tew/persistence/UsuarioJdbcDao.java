package impl.tew.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.tew.model.Usuario;
import com.tew.persistence.UsuarioDao;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;
import com.tew.persistence.exception.PersistenceException;

public class UsuarioJdbcDao  implements UsuarioDao {

	@Override
	public List<Usuario> getUsuarios(String rol) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		
		List<Usuario> usuarios = new ArrayList<Usuario>();

		try {
			// En una implemenntaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			System.out.println(rol);
			if(rol.contains("todos")) ps = con.prepareStatement("select * from USUARIOS");
			else ps = con.prepareStatement("select * from USUARIOS where rol !='admin'");
			
			rs = ps.executeQuery();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setEmail(rs.getString("EMAIL"));
				usuario.setPasswd(rs.getString("PASSWD"));
				usuario.setRol(rs.getString("ROL"));
				usuario.setNombre(rs.getString("NOMBRE"));
				usuarios.add(usuario);
			}
			
						
			return usuarios;
			
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
		
	}
	
	
	@Override
	public void save(Usuario a) throws AlreadyPersistedException {
		System.out.println("ASIOOOOOOOOOOOS JOBA");
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		
		try {
			// En una implementaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(
					"insert into USUARIOS (EMAIL, PASSWD, ROL, NOMBRE) " +
					"values (?, ?, ?, ?)");
			
			ps.setString(1, a.getEmail());
			ps.setString(2, a.getPasswd());
			ps.setString(3, a.getRol());
			ps.setString(4, a.getNombre());


			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("Usuario " + a + " already persisted");
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
	public void update(Usuario a) throws NotPersistedException {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		
		try {
			// En una implementaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(
					"update USUARIOS " +
					"set PASSWD = ?, ROL = ?, NOMBRE = ?" +
					"where EMAIL = ?");
			
			ps.setString(1, a.getPasswd());
			ps.setString(2, a.getRol());
			ps.setString(3, a.getNombre());
			ps.setString(4, a.getEmail());

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("UsuarioF " + a + " not found");
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
	public void delete(String email) throws NotPersistedException {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		
		try {
			// En una implementaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("delete from USUARIOS where email = ?");
			
			ps.setString(1, email);

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Usuario " + email + " not found");
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
	public Usuario findByEmail(String email) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Usuario usuario = null;
		
		try {
			// En una implementaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from USUARIOS where EMAIL = ?");
			ps.setString(1, email);

			rs = ps.executeQuery();
			while (rs.next()) {
				usuario = new Usuario();
				usuario.setEmail(rs.getString("EMAIL"));
				usuario.setPasswd(rs.getString("PASSWD"));
				usuario.setRol(rs.getString("ROL"));
				usuario.setNombre(rs.getString("NOMBRE"));

			}
			
			return usuario;
			
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
		
		
	}

	
	@Override
	public Usuario findByName(String name) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Usuario usuario = null;
		
		try {
			// En una implementaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from USUARIOS where NOMBRE = ?");
			ps.setString(1, name);

			
			rs = ps.executeQuery();
			while (rs.next()) {
				usuario = new Usuario();
				usuario.setEmail(rs.getString("EMAIL"));
				usuario.setPasswd(rs.getString("PASSWD"));
				usuario.setRol(rs.getString("ROL"));
				usuario.setNombre(rs.getString("NOMBRE"));

			}
			
			return usuario;
			
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
		
		
	}
	
	@Override
	public List<Usuario> findByCadena(String cadena, String emailUsuario) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Usuario usuario = null;
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		try {
			// En una implementaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			//String sql = "select * from USUARIOS WHERE EMAIL LIKE '%"+cadena+"%'";
			//ps = con.prepareStatement("select * from USUARIOS WHERE (EMAIL LIKE ? OR NOMBRE LIKE ?) AND (ROL NOT ? AND EMAIL NOT LIKE ?)");
			ps = con.prepareStatement("select * from USUARIOS WHERE (UPPER(NOMBRE) LIKE UPPER('%"+cadena+"%') OR UPPER(EMAIL) LIKE UPPER('%"+cadena+"%')) AND (ROL != 'admin' AND NOT EMAIL LIKE ?)");
			ps.setString(1, emailUsuario);
			
			//String cadena2 = "%"+cadena+"%";
			//ps.setString(1, cadena2);
			//ps.setString(2, cadena2);
			//ps.setString(3, "administrador");
			//ps.setString(4, emailUsuario);
			
			rs = ps.executeQuery();
			
			
			while (rs.next()) {

				
				System.out.println("----------------------------------");
				
				usuario = new Usuario();
				
				usuario.setEmail(rs.getString("EMAIL"));
				System.out.println(usuario.getEmail());
				
				usuario.setPasswd(rs.getString("PASSWD"));
				usuario.setRol(rs.getString("ROL"));
				usuario.setNombre(rs.getString("NOMBRE"));

				usuarios.add(usuario);

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
		
		return usuarios;
	}


	@Override
	public List<Usuario> getUsuariosSinUsLogin(String email) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		
		List<Usuario> usuarios = new ArrayList<Usuario>();

		try {
			// En una implemenntaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from USUARIOS WHERE EMAIL NOT LIKE ? AND EMAIL NOT LIKE ?");
			
			ps.setString(1, email);
			ps.setString(2, "admin@dominio.com");
			//ps.setString(2, "admin@email.com");
			
			rs = ps.executeQuery();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setEmail(rs.getString("EMAIL"));
				usuario.setPasswd(rs.getString("PASSWD"));
				usuario.setRol(rs.getString("ROL"));
				usuario.setNombre(rs.getString("NOMBRE"));
				usuarios.add(usuario);
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
		
		return usuarios;
	}


	@Override
	public void deleteAll() throws NotPersistedException {
		PreparedStatement ps = null;
		Connection con = null;
		
		try {
			// En una implementaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("delete from USUARIOS where rol != 'admin'");

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

	
	
}

