package caixa_eletronico.banco_dados;

import java.util.List;

import caixa_eletronico.conta.Conta;
import caixa_eletronico.conta.ContaCorrente;
import caixa_eletronico.conta.ContaPoupanca;

public class BancoDeDados {

	private static List<Conta> contas = List.of(new ContaCorrente("Veronica", "Itau", "123456", 50.00, 50.00),
			new ContaPoupanca("Veronica", "Itau", "654321", 1500.00),
			new ContaPoupanca("Laura", "Bradesco", "528720", 30000.00),
			new ContaCorrente("Laura", "Bradesco", "5287", 12000.00, 530.00),
			new ContaCorrente("Pedro","Santander","76543",15000.00,3000.00),
			new ContaCorrente("José", "Banco do Brasil", "121212", 50.00, 15.00));
			

	public static Conta getConta(String numeroConta) {
		for (Conta conta : contas) {
			if (conta.getNumeroConta().equals(numeroConta)) {
				return conta;
			}
		}
		throw new RuntimeException("caixa_eletronico.Conta Não Encontrada!");
	}

}
