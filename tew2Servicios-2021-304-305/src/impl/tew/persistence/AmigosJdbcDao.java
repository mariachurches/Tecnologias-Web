package impl.tew.persistence;

import java.sql.*;
import java.util.*;

import com.tew.model.Amigos;
import com.tew.model.Usuario;
import com.tew.persistence.AmigosDao;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;
import com.tew.persistence.exception.PersistenceException;

public class AmigosJdbcDao implements AmigosDao {

	@Override
	public List<Amigos> getAmigos() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		
		List<Amigos> listaAmigos = new ArrayList<Amigos>();

		try {
			// En una implemenntaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from amigo");
			rs = ps.executeQuery();

			while (rs.next()) {
				Amigos amigos = new Amigos();
				
				amigos.setEmail_usuario(rs.getString("EMAIL_USUARIO"));
				amigos.setEmail_amigo(rs.getString("EMAIL_AMIGO"));
				amigos.setAceptada(rs.getBoolean("ACEPTADA"));
				
				listaAmigos.add(amigos);
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
		
		return listaAmigos;
	}
	
	
	@Override
	public List<Amigos> getInvitacionesAmistad(String email) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		
		List<Amigos> listaInvitaciones = new ArrayList<Amigos>();

		try {
			// En una implemenntaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from amigo where aceptada = false and email_usuario = ?");
			
			ps.setString(1, email);
			
			
			rs = ps.executeQuery();

			while (rs.next()) {
				Amigos amigos = new Amigos();
				
				amigos.setEmail_usuario(rs.getString("EMAIL_USUARIO"));
				amigos.setEmail_amigo(rs.getString("EMAIL_AMIGO"));
				amigos.setAceptada(rs.getBoolean("ACEPTADA"));
				
				listaInvitaciones.add(amigos);
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
		
		return listaInvitaciones;
	}
	
	
	
	
	

	@Override
	public void envio(String emailUsuario, String emailAmigo) throws AlreadyPersistedException {
		PreparedStatement ps = null;
		Connection con = null;
		
		
		System.out.println(emailAmigo);
		System.out.println(emailUsuario);
		
		System.out.println("--------------------------------------------------------");
		
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
					"insert into AMIGO (email_usuario, email_amigo, aceptada) " +
					"values (?, ?, ?)");
			
			ps.setString(2, emailUsuario);
			ps.setString(1, emailAmigo);
			ps.setBoolean(3, false);

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
	public void update(Amigos amigos) throws NotPersistedException {
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
					"update AMIGO " +
					"set aceptada = ?" +
					"where email_usuario = ? and email_amigo = ?");
			
			ps.setBoolean(1, amigos.isAceptada());
			ps.setString(2, amigos.getEmail_usuario());
			ps.setString(3, amigos.getEmail_amigo());
			

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Amigos " + amigos + " not found");
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
		System.out.println("LLEGUEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE " + email);
		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");

			ps = con.prepareStatement("delete from AMIGO where email_usuario = ? or email_amigo = ?");

			ps.setString(1, email);
			ps.setString(2, email);
			
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
	public Amigos findByEmail(String emailUsuario, String emailAmigo) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Amigos amigos = null;
		
		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from AMIGO where email_usuario = ? and email_amigo = ?");
			
			ps.setString(1, emailUsuario);
			ps.setString(2, emailAmigo);
			
			
			rs = ps.executeQuery();
			while (rs.next()) {
				amigos = new Amigos();
				
				amigos.setEmail_usuario(rs.getString("EMAIL_USUARIO"));
				amigos.setEmail_amigo(rs.getString("EMAIL_AMIGO"));
				amigos.setAceptada(rs.getBoolean("ACEPTADA"));

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
	public void aceptar(String emailUsuario, String emailAmigo) throws AlreadyPersistedException {
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
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
					"update AMIGO " +
					"set aceptada = ?" +
					"where email_usuario = ? and email_amigo = ?");
			
			ps.setBoolean(1, true);
			ps.setString(2, emailUsuario);
			ps.setString(3, emailAmigo);

			rows = ps.executeUpdate();
			
			if (rows != 1) {
				throw new AlreadyPersistedException("Amigos already persisted");
			} 
			
			
			
			ps2 = con.prepareStatement(
					"update AMIGO " +
					"set aceptada = ?" +
					"where email_usuario = ? and email_amigo = ?");
			
			ps2.setBoolean(1, true);
			ps2.setString(3, emailUsuario);
			ps2.setString(2, emailAmigo);
			
			rows = ps2.executeUpdate();
			if(rows!=1) {
				rows=0;
				ps3 = con.prepareStatement(
					"insert into AMIGO (email_usuario, email_amigo, aceptada) " +
					"values (?, ?, ?)");
				
				ps3.setString(1, emailAmigo);
				ps3.setString(2, emailUsuario);
				ps3.setBoolean(3, true);

				rows = ps3.executeUpdate();
				
				if (rows != 1) {
					throw new AlreadyPersistedException("Amigos already persisted");
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
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
	}


	@Override
	public void save(Amigos amigos) throws AlreadyPersistedException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mutuo(String emailUsuario, String emailAmigo) throws AlreadyPersistedException {
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		Connection con = null;
		
		System.out.println("--------------------------------------------------------");
		
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
					"insert into AMIGO (email_usuario, email_amigo, aceptada) " +
					"values (?, ?, ?)");
			
			ps.setString(2, emailUsuario);
			ps.setString(1, emailAmigo);
			ps.setBoolean(3, true);

			ps.executeUpdate();
			
			ps2 = con.prepareStatement(
					"insert into AMIGO (email_usuario, email_amigo, aceptada) " +
					"values (?, ?, ?)");
			
			ps2.setString(1, emailUsuario);
			ps2.setString(2, emailAmigo);
			ps2.setBoolean(3, true);

			ps2.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (ps2 != null) {try{ ps2.close(); } catch (Exception ex){}};
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

			ps = con.prepareStatement("delete from AMIGO");

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
	public List<Usuario> getNoAmigos(String email) {
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		ResultSet rs = null;
		Connection con = null;
		
		List<String> listaAmigos = new ArrayList<String>();
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		List<Usuario> lista = new ArrayList<Usuario>();
		
		try {
			// En una implemenntaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from amigo where email_usuario=?");
			ps2 = con.prepareStatement("select * from usuarios where rol='usuario' and email!=?");
			ps3 = con.prepareStatement("select * from amigo where email_amigo=? and aceptada=false");
			
			ps.setString(1, email);
			rs = ps.executeQuery();
			

			while (rs.next()) {
				
				listaAmigos.add(rs.getString("EMAIL_AMIGO"));

			}
			
			ps3.setString(1,email);
			rs = ps3.executeQuery();
			
			System.out.println("----");
			while (rs.next()) {
				
				listaAmigos.add(rs.getString("EMAIL_USUARIO"));

			}
			
			
			ps2.setString(1, email);
			rs = ps2.executeQuery();
			

			while (rs.next()) {
				Usuario us = new Usuario();
				
				us.setEmail(rs.getString("EMAIL"));
				us.setNombre(rs.getString("NOMBRE"));
				us.setRol(rs.getString("ROL"));
				
				listaUsuarios.add(us);
			}
			
			
			//COMPARACION
			for(int i=0; i<listaUsuarios.size();i++) {
				if(!(listaAmigos.contains(listaUsuarios.get(i).getEmail()))) {
					lista.add(listaUsuarios.get(i));
				}
				
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
		
		System.out.print(lista.size());
		return lista;
	}


	@Override
	public void deleteAmistad(String email, String emailamigo) {
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		Connection con = null;
		System.out.println("LLEÑEEEEEEEEEEEEEEEEEÑEÑEÑEÑEÑEÑEÑEEEEE " + email + " " + emailamigo);
		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");

			ps = con.prepareStatement("delete from AMIGO where email_usuario = ? and email_amigo = ? ");
			ps2 = con.prepareStatement("delete from AMIGO where email_usuario = ? and email_amigo = ? ");

			ps.setString(1, email);
			ps.setString(2, emailamigo);
			
			ps2.setString(1, emailamigo);
			ps2.setString(2, email);
			
			ps.executeUpdate(); 
			ps2.executeUpdate(); 

			
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