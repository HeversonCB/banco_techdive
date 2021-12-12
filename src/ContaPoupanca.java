import java.util.Calendar;

public class ContaPoupanca extends Conta{

    private double rentabilidadeMensal = 0.0005;

    public ContaPoupanca(String nome, String cpf, double rendaMensal, int conta, String agencia) {
        super(nome, cpf, rendaMensal, conta, agencia);
        this.tipo = "poupanca";
    }
    public double getRentabilidadeMensal() {
        return rentabilidadeMensal;
    }

    @Override
    public void saque(double valor) {
        Calendar hoje = Calendar.getInstance();

        try {
            if (saldo >= valor) {
                saldo -= valor;
                Transacao transacao = new Transacao(this.conta, valor, hoje);
                transacoes.add(transacao);
                System.out.printf("Operação realizada!\nNovo saldo: R$%.2f", this.getSaldo());
            } else {
                System.out.println("Você não possui saldo suficiente!!!");
            }
        } catch (Exception er) {
            System.out.println("Erro " + er);
        }
    }

    @Override
    public void transferencia (double valor, Conta contaOrigem, Conta contaDestino) {
        Calendar hoje = Calendar.getInstance();

        try {
            if (saldo >= valor) {
                saldo -= valor;
                contaDestino.saldo += valor;
                Transacao transacao = new Transacao(this.conta, contaDestino.getConta(), valor, hoje);
                contaDestino.transacoes.add(transacao);
                transacao = new Transacao(contaDestino.getConta(), this.conta, valor, hoje);
                transacoes.add(transacao);
                System.out.printf("Operação realizada!\nNovo saldo: R$%.2f", this.getSaldo());
            }  else {
                System.out.println("Você não possui saldo suficiente!!!");
            }
        } catch (Exception er) {
            System.out.println("Erro " + er);
        }
    }

    @Override
    public void deposito (double valor) {
        Calendar hoje = Calendar.getInstance();

        try {
            if (valor > 0) {
                saldo += valor;
                Transacao transacao = new Transacao(valor, this.conta, hoje);
                transacoes.add(transacao);
                System.out.printf("Operação realizada!\nNovo saldo: R$%.2f\n", this.getSaldo());
            } else {
                System.out.println("Por favor digite um valor maior que 0");
            }
        } catch (Exception er) {
            System.out.println("Erro " + er);
        }
    }

    @Override
    public void calcularRendimento(double valor, int meses) {
        for ( int i = 0; i < meses; i++) {
            valor += valor*rentabilidadeMensal;
        }
        System.out.printf("Rentabilidade mensal atual: %.2f%% \n" , rentabilidadeMensal * 100);
        System.out.printf("Saldo total em " + meses + " meses: %.2f", valor);
    }
}
