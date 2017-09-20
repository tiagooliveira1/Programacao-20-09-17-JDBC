package opet.tds171a.pessoas;

public class Diretor extends Funcionario {

	private final double BONUS_PERCENT = 0.1;

	public Diretor(String nome, Double salario) {

		super(nome, salario);
		// TODO Auto-generated constructor stub
	}

	private Double bonus;

	public double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

	public double getBONUS_PERCENT() {
		return BONUS_PERCENT;
	}

	@Override
	public double pagarBonus() {
		return getSalario() * BONUS_PERCENT;
	}

}
