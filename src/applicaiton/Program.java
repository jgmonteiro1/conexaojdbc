package applicaiton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {
		//Conecta ao banco
		Connection conn = null;
		//Prepara uma consulta SQL
		Statement st = null;
		//Resultado da consulta é armazedo em rs
		ResultSet rs = null;
		
		
		try {
			//O método foi chamado direto porque é um método estático, logo não precisa ser instanciado. Chamada a classe + método;
			conn = DB.getConnection();
			//Para instanciar um objeto do tipo statement, chama a variável de conexão que já está instanciada e o método createStatement;
			st = conn.createStatement();
			//Seleciona a tabela "departamento" do BD, o resultado é atribuido na variável ResultSet
			rs = st.executeQuery("select * from department");
			
			//Enquanto existir um próximo, faça;
			while(rs.next()) {
				//Pega um número inteiro que está na tabela "Id" e pega uma String que está na tabela Name"
				System.out.println(rs.getInt("Id")+ ", " + rs.getString("Name"));
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		finally {
			//Fecha as conexões para não haver vazamento de memória
			DB.closeResultSet(rs);
			DB.closeStatement(st);;
			DB.closeConnection();
		}
		
		
	}

}
