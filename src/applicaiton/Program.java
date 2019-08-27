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
		//Resultado da consulta � armazedo em rs
		ResultSet rs = null;
		
		
		try {
			//O m�todo foi chamado direto porque � um m�todo est�tico, logo n�o precisa ser instanciado. Chamada a classe + m�todo;
			conn = DB.getConnection();
			//Para instanciar um objeto do tipo statement, chama a vari�vel de conex�o que j� est� instanciada e o m�todo createStatement;
			st = conn.createStatement();
			//Seleciona a tabela "departamento" do BD, o resultado � atribuido na vari�vel ResultSet
			rs = st.executeQuery("select * from department");
			
			//Enquanto existir um pr�ximo, fa�a;
			while(rs.next()) {
				//Pega um n�mero inteiro que est� na tabela "Id" e pega uma String que est� na tabela Name"
				System.out.println(rs.getInt("Id")+ ", " + rs.getString("Name"));
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		finally {
			//Fecha as conex�es para n�o haver vazamento de mem�ria
			DB.closeResultSet(rs);
			DB.closeStatement(st);;
			DB.closeConnection();
		}
		
		
	}

}
