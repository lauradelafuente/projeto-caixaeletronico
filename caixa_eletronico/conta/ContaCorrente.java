package caixa_eletronico.conta;

public class ContaCorrente extends Conta {

	private double limite;

	public ContaCorrente(String nomeCliente, String banco, String numeroConta, double limite, double saldoInicial) {
		super(nomeCliente, banco, numeroConta, saldoInicial);
		this.limite = limite;

	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	public boolean validaSaqueComLimite(double valorSaque) {
		return valorSaque > getSaldo() && valorSaque <= saldoComLimite();
	}

	public boolean validaSaqueComum(double valorSaque) {
		return valorSaque <= getSaldo();
	}

	@Override
	public void saque(double valorSaque) {
		double saldoTotal = saldoComLimite();
		double saldo = getSaldo();

		if (validaSaqueComum(valorSaque)) {
			setSaldo(saldo - valorSaque);
			System.out.println(" Saque Aprovado !!!");
		} else if (validaSaqueComLimite(valorSaque)) {
			setSaldo(saldo - valorSaque);
			limite = saldoTotal - valorSaque;
			System.out.println(" Saque Aprovado !!!");
		} else {
			System.out.println(" Saque Recusado !!!");
		}

	}

	@Override
	public void efetuarDeposito(double valorDeposito) {
		double saldo = getSaldo();
		double valorUsadoDoLimite = saldo * -1;
		if (saldo < 0 && valorDeposito >= valorUsadoDoLimite) {
			setLimite(valorUsadoDoLimite);
			setSaldo(valorDeposito - valorUsadoDoLimite);
		} else {
			super.efetuarDeposito(valorDeposito);
		}

	}

	public double saldoComLimite() {
		return getSaldo() + limite;
	}
}
