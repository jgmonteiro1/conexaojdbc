package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
	
	
	//Objeto de conex�o do banco de dados do jdbc;
	private static Connection conn = null;
	
	
	//M�todo para conectar ao banco de dados.
	public static Connection getConnection() {
		//Caso ainda seja nulo
		if(conn == null) {
			try{
			//Pegar as propriedades do BD
			Properties props = loadingProperties();
			//Pega a URL do Banco de dados
			String url = props.getProperty("dburl");
			//Para obter uma conex�o com o banco de dados � necess�rio chamar:
			conn = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	
	//M�todo para fechar a conex�o com o banco de dados
	public static void closeConnection() {
		//Se a conex�o estiver instanciada
		if(conn != null) {
			try {
			conn.close();
			} catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	// M�todo auxiliar para carregar as propriedades do arquivo db.properties;
	
	private static Properties loadingProperties() {
			//Para ler o arquivo na url "db.properties";
			try(FileInputStream fs = new FileInputStream("db.properties")) {
				
				Properties props = new Properties();
				//.load faz a leitura do arquivo  apontado pelo FS e guarda os dados no objeto props
				props.load(fs);
				return props;
			} catch(IOException e) {
				//Se der um erro IOException
				//Vai lan�ar a exce��o personalizada
				throw new DbException(e.getMessage());
			}
		}

}
