package opet.tds171a.pessoas;

public class Secretario extends Funcionario {

	private final double BONUS_PERCENT = 0.25;

	public Secretario(String nome, Double salario) {
		super(nome, salario);
		// TODO Auto-generated constructor stub
	}

	public double getBONUS_PERCENT() {
		return BONUS_PERCENT;
	}

	public double pagarBonus() {

		return getSalario() * BONUS_PERCENT;
		

	}

}
