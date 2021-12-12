import java.util.Objects;
import java.util.Scanner;

public class Menu {

    private final static Banco banco = Main.banco;

    public static void MenuPrincipal() {

        LimparTela();

        Scanner scanner = new Scanner(System.in);

        System.out.println("-------------------MENU PRINCIPAL-------------------");
        System.out.println("\n            Selecione a opção desejada: ");
        System.out.println("             ===========================");
        System.out.println("             |  1 - Criar Conta        |");
        System.out.println("             |  2 - Acessar Conta      |");
        System.out.println("             |  3 - Listar Contas      |");
        System.out.println("             |  4 - Contas Negativadas |");
        System.out.println("             ===========================\n\n");
        System.out.print("Opção: ");
        int op = scanner.nextInt();

        switch (op) {
            case 1 -> MenuCriarConta();
            case 2 -> {
                System.out.println("----------CONTAS----------");

                for (Conta conta: banco.getContas()) {
                    System.out.printf("Nome: %s ||| Conta: %d ||| Tipo: Conta %s\n\n", conta.getNome(), conta.getConta(), conta.getTipo());
                }

                System.out.print("Digite a conta desejada: ");
                int numeroConta = scanner.nextInt() - 1;

                MenuAcessarConta(numeroConta);
                try {
                    if (Objects.equals(banco.getContas().get(numeroConta).getTipo(), "corrente")) {
                        MenuContaCorrente(numeroConta);
                    } else if (Objects.equals(banco.getContas().get(numeroConta).getTipo(), "poupanca")) {
                        MenuContaPoupanca(numeroConta);
                    } else if (Objects.equals(banco.getContas().get(numeroConta).getTipo(), "investimento")) {
                        MenuContaInvestimento(numeroConta);
                    } else {
                        System.out.println("Conta não encontrada!!");
                    }
                } catch (Exception er) {
                    System.out.println("Erro " + er);
                }
            }
            case 3 -> {
                System.out.println("----------CONTAS----------");

                for (Conta conta: banco.getContas()) {
                    System.out.printf("Nome: %s ||| Conta: %d ||| Tipo: Conta %s\n\n", conta.getNome(), conta.getConta(), conta.getTipo());
                }
                MenuVoltar();
            }
            case 4 -> {
                for (Conta conta: banco.getContas()) {
                    if (conta.getSaldo() < 0) {
                        System.out.printf("Nome: %s ||| Conta: %d ||| Saldo: R$%.2f ||| Tipo: Conta %s\n\n", conta.getNome(), conta.getConta(),conta.getSaldo(), conta.getTipo());
                    }
                }
            }
            default -> {
            }
        }
        LimparTela();
    }

    public static void MenuContaCorrente(int conta) {
        int contaAcessada = conta + 1;
        LimparTela();

        String nome = banco.getContas().get(conta).getNome();

        System.out.println("-------------------CONTA CORRENTE (" + contaAcessada + "-" + nome + ")-------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n                  Escolha uma opção: ");
        System.out.println("                 ===================================");
        System.out.println("                 |   1 - Minha Conta               |");
        System.out.println("                 |   2 - Saque                     |");
        System.out.println("                 |   3 - Depósito                  |");
        System.out.println("                 |   4 - Saldo                     |");
        System.out.println("                 |   5 - Extrato                   |");
        System.out.println("                 |   6 - Transferir                |");
        System.out.println("                 |   7 - Alterar dados             |");
        System.out.println("                 |   8 - Voltar ao menu principal  |");
        System.out.println("                 ===================================\n\n");
        System.out.print("Opção: ");
        int op = scanner.nextInt();
        scanner.nextLine();

        switch (op) {
            case 1 -> {
                System.out.printf("\nNome: %s", banco.getContas().get(conta).getNome());
                System.out.printf("\nCPF: %s", banco.getContas().get(conta).getCpf());
                System.out.printf("\nRenda Mensal: %.2f", banco.getContas().get(conta).getRendaMensal());
                System.out.printf("\nConta: %d", banco.getContas().get(conta).getConta());
                System.out.printf("\nAgencia: %s\n\n", banco.getContas().get(conta).getAgencia());
            }
            case 2 -> {
                System.out.print("Digite o valor que deseja sacar: ");
                double valorSaque = scanner.nextDouble();
                banco.getContas().get(conta).saque(valorSaque);
            }
            case 3 -> {
                System.out.print("Digite o valor que deseja depositar: ");
                double valorDeposito = scanner.nextDouble();
                banco.getContas().get(conta).deposito(valorDeposito);
            }
            case 4 -> System.out.printf("Seu saldo atual é de R$%.2f", banco.getContas().get(conta).getSaldo());
            case 5 -> {
                int cont = 1;
                for (Transacao transacao : banco.getContas().get(conta).getTransacoes()) {
                    System.out.printf("%dª transação: %s", cont, transacao);
                    cont++;
                }
            }
            case 6 -> {
                System.out.print("Informe o número da conta que deseja enviar: ");
                int contaRecebedor = scanner.nextInt();
                System.out.print("\nInforme o valor da transferência: ");
                double valorTransferencia = scanner.nextDouble();
                banco.getContas().get(conta).transferencia(valorTransferencia, banco.getContas().get(conta), banco.getContas().get(contaRecebedor-1));
            }
            case 7 -> {
                System.out.printf("Nome atual: %s", banco.getContas().get(conta).getNome());
                System.out.print("\nNovo nome: ");
                String novoNome = scanner.nextLine();
                System.out.printf("Renda mensal atual: %.2f", banco.getContas().get(conta).getRendaMensal());
                System.out.print("\nNova renda: ");
                double novaRenda = scanner.nextDouble();
                banco.getContas().get(conta).alterarCadastro(novoNome, novaRenda);
            }
            case 8 -> MenuPrincipal();
            default -> {
            }
        }

        MenuVoltar();
    }

    public static void MenuContaPoupanca(int conta) {
        int contaAcessada = conta + 1;
        LimparTela();

        String nome = banco.getContas().get(conta).getNome();

        System.out.println("-------------------CONTA POUPANÇA (" + contaAcessada + "-" + nome + ")-------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n                  Escolha uma opção: ");
        System.out.println("                 ===================================");
        System.out.println("                 |   1 - Minha Conta               |");
        System.out.println("                 |   2 - Saque                     |");
        System.out.println("                 |   3 - Depósito                  |");
        System.out.println("                 |   4 - Saldo                     |");
        System.out.println("                 |   5 - Extrato                   |");
        System.out.println("                 |   6 - Transferir                |");
        System.out.println("                 |   7 - Simular rendimento        |");
        System.out.println("                 |   8 - Alterar dados             |");
        System.out.println("                 |   9 - Voltar ao menu principal  |");
        System.out.println("                 ===================================\n\n");
        System.out.print("Opção: ");
        int op = scanner.nextInt();
        scanner.nextLine();

        switch (op) {
            case 1 -> {
                System.out.printf("\nNome: %s", banco.getContas().get(conta).getNome());
                System.out.printf("\nCPF: %s", banco.getContas().get(conta).getCpf());
                System.out.printf("\nRenda Mensal: %.2f", banco.getContas().get(conta).getRendaMensal());
                System.out.printf("\nConta: %d", banco.getContas().get(conta).getConta());
                System.out.printf("\nAgencia: %s\n\n", banco.getContas().get(conta).getAgencia());
            }
            case 2 -> {
                System.out.print("Digite o valor que deseja sacar: ");
                double valorSaque = scanner.nextDouble();
                banco.getContas().get(conta).saque(valorSaque);
            }
            case 3 -> {
                System.out.print("Digite o valor que deseja depositar: ");
                double valorDeposito = scanner.nextDouble();
                banco.getContas().get(conta).deposito(valorDeposito);
            }
            case 4 -> System.out.printf("Seu saldo atual é de R$%.2f", banco.getContas().get(conta).getSaldo());
            case 5 -> {
                int cont = 1;
                for (Transacao transacao : banco.getContas().get(conta).getTransacoes()) {
                    System.out.printf("%dª transação: %s", cont, transacao);
                    cont++;
                }
            }
            case 6 -> {
                System.out.print("Informe o número da conta que deseja enviar: ");
                int contaRecebedor = scanner.nextInt();
                System.out.print("\nInforme o valor da transferência: ");
                double valorTransferencia = scanner.nextDouble();
                banco.getContas().get(conta).transferencia(valorTransferencia, banco.getContas().get(conta), banco.getContas().get(contaRecebedor-1));
            }
            case 7 -> {
                System.out.print("Digite o valor a ser investido: ");
                double valor = scanner.nextDouble();
                System.out.print("\nMeses: ");
                int meses = scanner.nextInt();
                banco.getContas().get(conta).calcularRendimento(valor, meses);
            }
            case 8 -> {
                System.out.printf("Nome atual: %s", banco.getContas().get(conta).getNome());
                System.out.print("\nNovo nome: ");
                String novoNome = scanner.nextLine();
                System.out.printf("Renda mensal atual: %.2f", banco.getContas().get(conta).getRendaMensal());
                System.out.print("\nNova renda: ");
                double novaRenda = scanner.nextDouble();
                banco.getContas().get(conta).alterarCadastro(novoNome, novaRenda);
            }
            case 9 -> MenuPrincipal();
            default -> {
            }
        }

        MenuVoltar();
    }

    public static void MenuContaInvestimento(int conta) {
        int contaAcessada = conta + 1;
        LimparTela();

        String nome = banco.getContas().get(conta).getNome();

        System.out.println("-------------------CONTA CORRENTE (" + contaAcessada + "-" + nome + ")-------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n                  Escolha uma opção: ");
        System.out.println("                 ===================================");
        System.out.println("                 |   1 - Minha Conta               |");
        System.out.println("                 |   2 - Saque                     |");
        System.out.println("                 |   3 - Depósito                  |");
        System.out.println("                 |   4 - Saldo                     |");
        System.out.println("                 |   5 - Extrato                   |");
        System.out.println("                 |   6 - Alterar dados             |");
        System.out.println("                 |   7 - Voltar ao menu principal  |");
        System.out.println("                 ===================================\n\n");
        System.out.print("Opção: ");
        int op = scanner.nextInt();
        scanner.nextLine();

        switch (op) {
            case 1 -> {
                System.out.printf("\nNome: %s", banco.getContas().get(conta).getNome());
                System.out.printf("\nCPF: %s", banco.getContas().get(conta).getCpf());
                System.out.printf("\nRenda Mensal: %.2f", banco.getContas().get(conta).getRendaMensal());
                System.out.printf("\nConta: %d", banco.getContas().get(conta).getConta());
                System.out.printf("\nAgencia: %s\n\n", banco.getContas().get(conta).getAgencia());
            }
            case 2 -> {
                System.out.print("Digite o valor que deseja sacar: ");
                double valorSaque = scanner.nextDouble();
                banco.getContas().get(conta).saque(valorSaque);
            }
            case 3 -> {
                System.out.print("Digite o valor que deseja depositar: ");
                double valorDeposito = scanner.nextDouble();
                banco.getContas().get(conta).deposito(valorDeposito);
            }
            case 4 -> System.out.printf("Seu saldo atual é de R$%.2f", banco.getContas().get(conta).getSaldo());
            case 5 -> {
                int cont = 1;
                for (Transacao transacao : banco.getContas().get(conta).getTransacoes()) {
                    System.out.printf("%dª transação: %s", cont, transacao);
                    cont++;
                }
            }
            case 6 -> {
                System.out.printf("Nome atual: %s", banco.getContas().get(conta).getNome());
                System.out.print("\nNovo nome: ");
                String novoNome = scanner.nextLine();
                System.out.printf("Renda mensal atual: %.2f", banco.getContas().get(conta).getRendaMensal());
                System.out.print("\nNova renda: ");
                double novaRenda = scanner.nextDouble();
                banco.getContas().get(conta).alterarCadastro(novoNome, novaRenda);
            }
            case 7 -> MenuPrincipal();
            default -> {
            }
        }

        MenuVoltar();
    }

    public static void MenuCriarConta () {
        LimparTela();

        System.out.println("-------------------CRIAÇÃO DE CONTA-------------------");

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n              Selecione o tipo de conta: ");
        System.out.println("             =============================");
        System.out.println("             |   1 - Conta Corrente      |");
        System.out.println("             |   2 - Conta Poupança      |");
        System.out.println("             |   3 - Conta Investimento  |");
        System.out.println("             =============================\n\n");
        System.out.print("Opção: ");
        try {
            int op = scanner.nextInt();

            switch (op) {
                case 1 -> MenuCriarContaCorrente();
                case 2 -> MenuCriarContaPoupanca();
                case 3 -> MenuCriarContaInvestimento();
                default -> {
                }
            }
        } catch (Exception er) {
            System.out.println("Erro " + er);
        }
        LimparTela();
    }

    public static void MenuCriarContaCorrente() {
        LimparTela();

        Scanner scanner = new Scanner(System.in);

        System.out.println("-------------------CRIAÇÃO DE CONTA CORRENTE-------------------");

        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();

        boolean val = false;
        String cpf = "";
        while (!val) {
            System.out.print("\nDigite seu CPF (SEM PONTUAÇÃO): ");
            cpf = scanner.nextLine();
            if (valida(cpf)) {
                val = true;
            }
        }

        System.out.print("\nDigite sua renda (EX: 1000.50): ");
        double renda = scanner.nextDouble();
        scanner.nextLine();

        String op = "0";
        String agencia = "0";
        while (!Objects.equals(op, "1") && !Objects.equals(op, "2")) {
            System.out.print("\nEscolha a agência mais próxima de você: ");
            System.out.print("\n1- Florianópolis\n2- São José\n");
            agencia = scanner.nextLine();
            op = agencia;
            if (Objects.equals(agencia, "1") || Objects.equals(agencia, "2")) {
                break;
            } else {
                System.out.println("Opção inválida!");
            }
        }

        int conta = banco.getQtdContas();
        banco.somarQtdContas();

        ContaCorrente novaContaCorrente = new ContaCorrente(nome, cpf, renda, conta, agencia);
        banco.addConta(novaContaCorrente);

        System.out.println("\nConta criada!!!");
        System.out.printf("\n\nSeu limite de cheque especial é R$%.2f!!\n", renda);
        System.out.println("O número da sua conta é " + conta + ", não perca!!!");

        MenuVoltar();
    }

    public static void MenuCriarContaPoupanca() {
        LimparTela();

        Scanner scanner = new Scanner(System.in);

        System.out.print("-------------------CRIAÇÃO DE CONTA POUPANÇA-------------------");

        System.out.print("\nDigite seu nome: ");
        String nome = scanner.nextLine();

        boolean val = false;
        String cpf = "";
        while (!val) {
            System.out.print("\nDigite seu CPF (SEM PONTUAÇÃO): ");
            cpf = scanner.nextLine();
            if (valida(cpf)) {
                val = true;
            }
        }

        System.out.print("\nDigite sua renda (EX: 1000.50): ");
        double renda = scanner.nextDouble();
        scanner.nextLine();

        String op = "0";
        String agencia = "0";
        while (!Objects.equals(op, "1") && !Objects.equals(op, "2")) {
            System.out.print("\nEscolha a agência mais próxima de você: ");
            System.out.print("\n1- Florianópolis\n2- São José\n");
            agencia = scanner.nextLine();
            op = agencia;
            if (Objects.equals(agencia, "1") || Objects.equals(agencia, "2")) {
                break;
            } else {
                System.out.println("Opção inválida!");
            }
        }

        int conta = banco.getQtdContas();
        banco.somarQtdContas();

        ContaPoupanca novaContaPoupanca = new ContaPoupanca(nome, cpf, renda, conta, agencia);
        banco.addConta(novaContaPoupanca);

        System.out.printf("Rendimento mensal atual: %.2f%%", novaContaPoupanca.getRentabilidadeMensal()*100);

        MenuVoltar();
    }

    public static void MenuCriarContaInvestimento() {
        LimparTela();

        Scanner scanner = new Scanner(System.in);

        System.out.print("-------------------CRIAÇÃO DE CONTA INVESTIMENTO-------------------");

        System.out.print("\nDigite seu nome: ");
        String nome = scanner.nextLine();

        boolean val = false;
        String cpf = "";
        while (!val) {
            System.out.print("\nDigite seu CPF (SEM PONTUAÇÃO): ");
            cpf = scanner.nextLine();
            if (valida(cpf)) {
                val = true;
            }
        }

        System.out.print("\nDigite sua renda (EX: 1000.50): ");
        double renda = scanner.nextDouble();
        scanner.nextLine();

        String op = "0";
        String agencia = "0";
        while (!Objects.equals(op, "1") && !Objects.equals(op, "2")) {
            System.out.print("\nEscolha a agência mais próxima de você: ");
            System.out.print("\n1- Florianópolis\n2- São José\n");
            agencia = scanner.nextLine();
            op = agencia;
            if (Objects.equals(agencia, "1") || Objects.equals(agencia, "2")) {
                break;
            } else {
                System.out.println("Opção inválida!");
            }
        }

        System.out.println("Escolha um investimento abaixo: ");
        int cont = 0;
        for (double rendimento : ContaInvestimento.getRentabilidade()) {
            System.out.printf("Rendimento %d = %.2f%% ao mês.\n", cont, ContaInvestimento.getRentabilidade()[cont]*100);
            cont++;
        }
        double rendimento = scanner.nextDouble();

        int conta = banco.getQtdContas();
        banco.somarQtdContas();

        ContaInvestimento novaContaInvestimento = new ContaInvestimento(nome, cpf, renda, conta, agencia, rendimento);
        banco.addConta(novaContaInvestimento);

        MenuVoltar();
    }

    public static void MenuAcessarConta(int conta) {
        Main.contaAtual = conta;
    }

    public static boolean valida(String cpf) {
        try {
            if (Objects.equals(cpf, "00000000000") ||
                Objects.equals(cpf, "11111111111") ||
                Objects.equals(cpf, "22222222222") ||
                Objects.equals(cpf, "33333333333") ||
                Objects.equals(cpf, "44444444444") ||
                Objects.equals(cpf, "55555555555") ||
                Objects.equals(cpf, "66666666666") ||
                Objects.equals(cpf, "77777777777") ||
                Objects.equals(cpf, "88888888888") ||
                Objects.equals(cpf, "99999999999")) {
                return false;
            }

            if (cpf.length() != 11) {
                return false;
            } else {
                int valor = 0;
                int cont = 0;
                for (int i = 9; i > 0; i--) {
                    valor += Character.getNumericValue(cpf.charAt(cont)) * (i + 1);
                    cont++;
                }

                if ((valor * 10) % 11 == Character.getNumericValue((cpf.charAt(9)))) {
                    valor = 0;
                    cont = 0;
                    for (int i = 10; i > 0; i--) {
                        valor += Character.getNumericValue(cpf.charAt(cont)) * (i + 1);
                        cont++;
                    }
                    if ((valor * 10) % 11 == Character.getNumericValue((cpf.charAt(10)))) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } catch (Exception er) {
            System.out.println("Erro " + er);
        }
        return false;
    }

    public static void LimparTela() {
        for (int i = 0; i < 15; i++) {
            System.out.println("\n\n");
        }
    }

    public static void MenuVoltar() {
        char op;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\n\nDeseja voltar ao menu principal? S = sim / N = não");
            op = scanner.next().charAt(0);
            if (op == 'S' || op == 's') {
                LimparTela();
                MenuPrincipal();
            } else if (op == 'N' || op == 'n') {
                System.exit(0);
            } else {
                System.out.println("Por favor digite \"S\" ou \"N\"");
            }
        } while (true);
    }
}
