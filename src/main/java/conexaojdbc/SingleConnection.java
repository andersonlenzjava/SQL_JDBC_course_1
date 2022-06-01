package conexaojdbc;

import java.sql.Connection;
import java.sql.DriverManager;

//classe que realiza a conexão com o banco 

public class SingleConnection {

	private static String url = "jdbc:postgresql://localhost:5433/posjava";
	private static String password = "admin";
	private static String user = "postgres";
	private static Connection connection = null;

	// construtor  
	public SingleConnection() {
		
	}

	// método de conexão //que vai executar toda a conexão, 
	private static void conectar() {
		try {

			// se a conexão for nula ele vai tentar conectar 
			if (connection == null) {
				Class.forName("org.postgresql.Driver");//drive para conexão
				connection = DriverManager.getConnection(url, user, password);//objeto connection
				connection.setAutoCommit(false);//desabilita o autoenvio de dados 
				System.out.println("Conectou com sucesso");//verificação 

			}

		} catch (Exception e) {
			e.printStackTrace();// retorna o erro que aconteceu caso não consiga a conexão
		}

	}

	// conction do pacote java.sql //méetodos staticos nao precisam instanciar a classe para acessar 
	public static Connection getConnection() {
		conectar();// ja aciona o método na hora de instânicar
		return connection;//retorna se é verdadeira ou falsa 
	}

}
