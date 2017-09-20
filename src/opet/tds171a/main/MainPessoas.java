package opet.tds171a.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.scenario.effect.AbstractShadow.ShadowMode;

import opet.tds171a.pessoas.Diretor;
import opet.tds171a.pessoas.Funcionario;
import opet.tds171a.pessoas.Secretario;

public class MainPessoas {

	private static ArrayList<Funcionario> arrFuncionarios ;
	
	public static final String DB_USUARIO = "system";
	public static final String DB_PASS = "oracle";
	public static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
	
	public static void main(String[] args) {
		
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.print("Conectando ao banco...");
			Connection connection = DriverManager.getConnection(DB_URL, DB_USUARIO, DB_PASS);
			System.out.println("Conectado!");
			
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		arrFuncionarios = new ArrayList<Funcionario>();
		
		popularFuncionario();
		
		listarFuncionarios();
		

	}
	
	public static void popularFuncionario() {
		
		Diretor diretor = new Diretor("Paulo", 12500.00);
		arrFuncionarios.add(diretor);
		
		Secretario secretario = new Secretario("Bruno", 1590.23);
		arrFuncionarios.add(secretario);
			
	}
	
	public static void listarFuncionarios() {
		for(Funcionario func : arrFuncionarios) {
			
			// .pagarBonus neste caso é um exemplo clássico de polimorfismo
			System.out.println(func.getNome() +" "+  func.pagarBonus() );
			if(func instanceof Diretor) {
				System.out.println("[E um diretor!");
			}
			
			// utiliza o método declarado em interfaces
			func.baterPonto();
		}
	}
	


}
