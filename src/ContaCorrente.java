import java.util.Calendar;

public class ContaCorrente extends Conta{

    //O limite especial é o mesmo valor da renda mensal.
    private double limiteChequeEspecial;

    public ContaCorrente(String nome, String cpf, double rendaMensal, int conta, String agencia) {
        super(nome, cpf, rendaMensal, conta, agencia);
        this.tipo = "corrente";
        this.limiteChequeEspecial = rendaMensal;
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
            } else if (saldo + limiteChequeEspecial < valor) {
                System.out.println("Você não possui saldo suficiente!!!");
            } else {
                saldo -= valor;
                Transacao transacao = new Transacao(this.conta, valor, hoje);
                transacoes.add(transacao);
                System.out.printf("Operação realizada!! Você agora está usando o cheque especial.\nNovo saldo: R$%.2f", saldo);
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
            }  else if (saldo + limiteChequeEspecial < valor) {
                System.out.println("Você não possui saldo suficiente!!!");
            } else {
                saldo -= valor;
                contaDestino.saldo += valor;
                Transacao transacao = new Transacao(this.conta, contaDestino.getConta(), valor, hoje);
                contaDestino.transacoes.add(transacao);
                transacao = new Transacao(contaDestino.getConta(), this.conta, valor, hoje);
                transacoes.add(transacao);
                System.out.printf("Operação realizada!! Você agora está usando o cheque especial.\nNovo saldo: R$%.2f", saldo);
            }
        } catch (Exception er) {
            System.out.println("Erro " + er);
        }
    }

}
