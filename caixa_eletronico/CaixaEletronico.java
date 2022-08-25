package caixa_eletronico;

import java.io.IOException;
import java.util.Scanner;

import caixa_eletronico.banco_dados.BancoDeDados;
import caixa_eletronico.conta.Conta;
import caixa_eletronico.conta.ContaCorrente;

public class CaixaEletronico {

	private static Conta validarContaNoBancoDados(String numeroConta) {

		try {
			return BancoDeDados.getConta(numeroConta);

		} catch (RuntimeException ex) {
			System.out.println("Erro caixa_eletronico : " + ex.getMessage());
			numeroConta = solicitaNumeroConta();
			// recursão
			return validarContaNoBancoDados(numeroConta);
		}

	}

	public static void iniciar() {
		System.out.println("               ____            ____              _                      \r\n"
				+ "              / ___| ___ _ __ | __ )  __ _ _ __ | | __                  \r\n"
				+ "             | |  _ / _ | '_ \\|  _ \\ / _` | '_ \\| |/ /                  \r\n"
				+ "             | |_| |  __| | | | |_) | (_| | | | |   <                   \r\n"
				+ "              \\____|\\___|_| |_|____/ \\__,_|_| |_|_|\\_\\                  \r\n"
				+ "   ____      _             _____ _      _                   _           \r\n"
				+ "  / ___|__ _(___  ____ _  | ____| | ___| |_ _ __ ___  _ __ (_) ___ ___  \r\n"
				+ " | |   / _` | \\ \\/ / _` | |  _| | |/ _ | __| '__/ _ \\| '_ \\| |/ __/ _ \\ \r\n"
				+ " | |__| (_| | |>  | (_| | | |___| |  __| |_| | | (_) | | | | | (_| (_) |\r\n"
				+ "  \\____\\__,_|_/_/\\_\\__,_| |_____|_|\\___|\\__|_|  \\___/|_| |_|_|\\___\\___/ \r\n"
				+ "                                                                        ");
		String numeroConta = CaixaEletronico.solicitaNumeroConta();
		Conta conta = validarContaNoBancoDados(numeroConta);
		apresentarBoasVindas(conta);
		menuDoCaixa(conta);

	}

	private static String solicitaNumeroConta() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("  _____________________________________________________________________  \r\n"
				+ " 								       							   \r\n"
				+ "                 DIGITE O NUMERO DA CONTA PARA INICIAR                 \r\n"
				+ "  _____________________________________________________________________ \n");

		String numeroConta = scanner.next();
		return numeroConta;
	}

	private static void apresentarBoasVindas(Conta conta) {
		System.out.println("  _____________________________________________________________________  ");
		System.out.println("                                                                         ");
		System.out.println("  			          " + conta.getBanco() + " 							  ");
		System.out.println("  _____________________________________________________________________  ");
		System.out.println("                      Olá,  " + conta.getNomeCliente() + "  Seja Bem vindo(a)");
		System.out.println("                            Conta : " + conta.getNumeroConta());
	}

	public static void menuDoCaixa(Conta conta) {
		System.out.println(" _____________________________________________________________________  ");

		System.out.println("  Selecione uma das opções abaixo\n");
		System.out.println("  (0) SAIR");
		System.out.println("  (1) SAQUE");
		System.out.println("  (2) SALDO");
		System.out.println("  (3) DEPOSITO");
		Scanner scanner = new Scanner(System.in);
		switch (scanner.nextInt()) {
		case 1 -> {
			saque(conta);
			menuDoCaixa(conta);

		}
		case 2 -> {
			saldo(conta);
			menuDoCaixa(conta);
		}
		case 3 -> {
			deposito(conta);
			menuDoCaixa(conta);
		}
		case 0 -> {
			System.out.println(" Caixa Finalizado!");
			break;
		}
		}
	}

	private static void deposito(Conta conta) {
		System.out.println(" _____________________________________________________________________  ");
		System.out.println("  Selecione uma das opções abaixo para o depósito");
		System.out.println(" (1) MESMA TITULARIDADE");
		System.out.println(" (2) OUTRA TITULARIDADE");
		System.out.println(" (0) VOLTAR");

		Scanner scan = new Scanner(System.in);
		int operacao = scan.nextInt();
		switch (operacao) {
		case 1 -> {
			System.out.println(" Digite o valor do deposito:  ");
			double valorDeposito = scan.nextDouble();
			System.out.println(" A conta que irá receber o Depósito: " + conta.getNumeroConta());
			if (valorDeposito > 0) {
				conta.efetuarDeposito(valorDeposito);
			} else {
				System.out.println(" Operação Inválida!");
			}

		}
		case 2 -> {
			System.out.println(" A conta que irá receber o Depósito: ");
			int contaTitularidadeDiferente = scan.nextInt();
			System.out.println(" Digite o valor do deposito: ");
			double valorDeposito = scan.nextDouble();
			System.out.println(" Deposito efetuado!");
		}
		case 0 -> menuDoCaixa(conta);
		default -> throw new IllegalStateException(" Valor inesperado: " + operacao);
		}
		;

	}

	private static void saldo(Conta conta) {
		Scanner scanner = new Scanner(System.in);
		System.out.println(" _____________________________________________________________________  ");
		System.out.println(" Extrato selecionado\n");
		if (conta instanceof ContaCorrente) {
			System.out.println(" Saldo da conta Corrente R$ " + conta.getSaldo());
			System.out.println(" Limite total  R$ " + ((ContaCorrente) conta).getLimite());
		} else {
			System.out.println(" Saldo da conta Poupanca R$ " + conta.getSaldo());
		}
	}

	private static void saque(Conta conta) {
		System.out.println(" _____________________________________________________________________  ");
		System.out.println(" Saque selecionado, informe o valor desejado\n");
		System.out.println(" (1) R$20.00");
		System.out.println(" (2) R$50.00");
		System.out.println(" (3) R$100.00");
		System.out.println(" (4) Digite o valor desejado");

		Scanner scanner = new Scanner(System.in);

		int operacao = scanner.nextInt();

		double valorSaque = switch (operacao) {

		case 1 -> 20.00;
		case 2 -> 50.00;
		case 3 -> 100.00;
		case 4 -> scanner.nextDouble();
		default -> {
			throw new IllegalStateException(" Valor inesperado: " + operacao);
		}
		};

		conta.saque(valorSaque);

	}
}
