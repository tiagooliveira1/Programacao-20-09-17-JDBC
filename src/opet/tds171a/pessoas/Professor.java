package opet.tds171a.pessoas;

public class Professor extends Funcionario {
	
	private final double BONUS_PERCENT = 0.2;

	public Professor(String nome, Double salario) {
		super(nome, salario);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double pagarBonus() {
		// TODO Auto-generated method stub
		return getSalario() * BONUS_PERCENT;
	}

}
