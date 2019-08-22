package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
	
	
	//Objeto de conexão do banco de dados do jdbc;
	private static Connection conn = null;
	
	
	//Método para conectar ao banco de dados.
	public static Connection getConnection() {
		//Caso ainda seja nulo
		if(conn == null) {
			try{
			//Pegar as propriedades do BD
			Properties props = loadingProperties();
			//Pega a URL do Banco de dados
			String url = props.getProperty("dburl");
			//Para obter uma conexão com o banco de dados é necessário chamar:
			conn = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	
	//Método para fechar a conexão com o banco de dados
	public static void closeConnection() {
		//Se a conexão estiver instanciada
		if(conn != null) {
			try {
			conn.close();
			} catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	// Método auxiliar para carregar as propriedades do arquivo db.properties;
	
	private static Properties loadingProperties() {
			//Para ler o arquivo na url "db.properties";
			try(FileInputStream fs = new FileInputStream("db.properties")) {
				
				Properties props = new Properties();
				//.load faz a leitura do arquivo  apontado pelo FS e guarda os dados no objeto props
				props.load(fs);
				return props;
			} catch(IOException e) {
				//Se der um erro IOException
				//Vai lançar a exceção personalizada
				throw new DbException(e.getMessage());
			}
		}

}
