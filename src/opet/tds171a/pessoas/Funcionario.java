package opet.tds171a.pessoas;

import opet.tds171a.pessoas.interfaces.IFuncionario;

public abstract class Funcionario extends Pessoa implements IFuncionario {

	private String nome;
	private double salario;

	public Funcionario(String nome, Double salario) {
		this.nome = nome;
		this.salario = salario;
	}

	public abstract double pagarBonus();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}
	
	/**
	 * Implementação da classe taltal
	 */
	public void baterPonto()
	{
		System.out.println("Mizerávi, bateu o ponto!");
	}

}
