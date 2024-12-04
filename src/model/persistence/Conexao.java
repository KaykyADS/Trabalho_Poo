package model.persistence;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class Conexao {
 
	private Connection c;
 
	public Connection getConnection() throws ClassNotFoundException, SQLException {
 
		String hostName = "localhost";
		String dbName = "CatalogoRPG";
		String user = "root";
		String senha = "alunofatec";
		Class.forName("org.mariadb.jdbc.Driver");
		c = DriverManager.getConnection(String.format(
				"jdbc:mariadb://localhost:3307/CatalogoRPG?user=root&password=alunofatec"));

		return c;
	}
 
	//driver jdbc:mariadb://localhost:3307/agendadb?allowPublicKeyRetrieval=true
	//org.mariadb.jdbc.Driver
	
	
	
}