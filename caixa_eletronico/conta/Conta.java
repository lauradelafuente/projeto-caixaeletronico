package caixa_eletronico.conta;

public abstract class Conta {

	private String nomeCliente;
	private String numeroConta;
	private String banco;
	private double saldo;

	public Conta(String nomeCliente, String banco, String numeroConta) {
		this.nomeCliente = nomeCliente;
		this.numeroConta = numeroConta;
		this.banco = banco;

	}

	public Conta(String nomeCliente, String banco, String numeroConta, double saldo) {
		this.nomeCliente = nomeCliente;
		this.numeroConta = numeroConta;
		this.saldo = saldo;
		this.banco = banco;
	}

	public void saque(double valorSaque) {
		if (valorSaque <= saldo) {
			System.out.println(" Saque Aprovado !!!");
			saldo -= valorSaque;
		} else {
			System.out.println(" Saque Recusado !!!");
		}

	}

	public void efetuarDeposito(double valorDeposito) {
		saldo += valorDeposito;
		System.out.println(" Depósito efetuado: " + valorDeposito);
	}

	public Conta() {
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public String getBanco() {
		return banco;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

}
