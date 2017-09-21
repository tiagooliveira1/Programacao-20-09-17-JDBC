package opet.tds171a.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import opet.tds171a.pessoas.Diretor;
import opet.tds171a.pessoas.Funcionario;
import opet.tds171a.pessoas.Professor;
import opet.tds171a.pessoas.Secretario;

public class MainPessoas {

	private static ArrayList<Funcionario> arrFuncionarios;

	public static final String DB_USUARIO = "system";
	public static final String DB_PASS = "oracle";
	public static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";

	public static void main(String[] args) {

		arrFuncionarios = new ArrayList<Funcionario>();
		
		
		Funcionario func = new Professor("João Andrei", 2580.22);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.print("Conectando ao banco...");
			Connection connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);
			System.out.println("Conectado!");

			PreparedStatement pstmt = connection
					.prepareStatement("INSERT INTO FUNCIONARIO (IDFUNCIONARIO, NOME, SALARIO, TIPO) values ("
							+ "coalesce((select max(idfuncionario)+1 from funcionario),1), " + "?,?,?)");
			pstmt.setString(1, func.getNome());
			pstmt.setDouble(2, func.getSalario());
			if (func instanceof Professor)
				pstmt.setInt(3, 3);
			else if (func instanceof Secretario)
				pstmt.setInt(3, 2);
			else
				pstmt.setInt(3, 1);
			// pstmt.executeUpdate();
			pstmt.execute(); // precisou colocar execute pq uso select para
								// achar novo id

			if (pstmt != null)
				pstmt.close();

			if (connection != null)
				connection.close();

			// implementando o delete
			pstmt = null;
			connection = null;

			System.out.print("Conectando ao banco novamente...");
			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);
			System.out.println("OK");

			pstmt = connection.prepareStatement("DELETE FROM FUNCIONARIO WHERE IDFUNCIONARIO = ? ");
			pstmt.setInt(1, 12);

			pstmt.executeUpdate();

			if (pstmt != null)
				pstmt.close();

			System.out.print("Desconectando...");
			if (connection != null)
				connection.close();
			System.out.println("OK");

			// implementando o delete
			pstmt = null;
			connection = null;

			System.out.print("Conectando ao banco novamente...");
			connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);
			System.out.println("OK");

			pstmt = connection
					.prepareStatement("UPDATE FUNCIONARIO SET NOME = ?, SALARIO = ? WHERE IDFUNCIONARIO = ? ");
			pstmt.setString(1, "Novo nome do cidadão");
			pstmt.setDouble(2, 1455.99);
			pstmt.setInt(3, 11);

			pstmt.executeUpdate();

			if (pstmt != null)
				pstmt.close();

			
			// implementando Select 
			recuperarFuncionarios(connection);
			if (connection != null)
				connection.close();
			pstmt = null;
			connection = null;
			
			
			

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		//popularFuncionario();

		//listarFuncionarios();

	}

	/**
	 *  Recupera via Select no banco, cria os objetos das classes corretas e já insere no arrayList
	 * @param conn
	 */
	public static void recuperarFuncionarios(Connection conn) {
		PreparedStatement pstmt;
		try {
			pstmt = conn
					.prepareStatement("SELECT * FROM FUNCIONARIO");
			ResultSet rs = pstmt.executeQuery(); 
			while( rs.next() ) {
				
				System.out.println(rs.getString("nome"));
				Funcionario func;
				// verifica qual o tipo do funcionário retornado, e cria o objeto correspondete
				if(rs.getInt("tipo") == 3) {
					func = new Professor(rs.getString("nome"), rs.getDouble("salario") );
				} else if(rs.getInt("tipo") == 2) {
					func = new Secretario(rs.getString("nome"), rs.getDouble("salario") );
				} else {
					func = new Diretor(rs.getString("nome"), rs.getDouble("salario") );
				}
				arrFuncionarios.add(func);
			}
			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static void popularFuncionario() {

		Diretor diretor = new Diretor("Paulo", 12500.00);
		arrFuncionarios.add(diretor);

		Secretario secretario = new Secretario("Bruno", 1590.23);
		arrFuncionarios.add(secretario);

	}

	public static void listarFuncionarios() {
		for (Funcionario func : arrFuncionarios) {

			// .pagarBonus neste caso é um exemplo clássico de polimorfismo
			System.out.println(func.getNome() + " " + func.pagarBonus());
			if (func instanceof Diretor) {
				System.out.println("[E um diretor!");
			}

			// utiliza o método declarado em interfaces
			func.baterPonto();
		}
	}

}
