import java.util.ArrayList;

public abstract class Conta {

    protected String nome;
    protected String cpf;
    protected double rendaMensal;
    protected int conta;
    protected String agencia;
    protected double saldo = 0;
    protected String tipo = "";
    protected ArrayList<Transacao> transacoes;

    public Conta(String nome, String cpf, double rendaMensal, int conta, String agencia) {
        this.nome = nome;
        this.cpf = cpf;
        this.rendaMensal = rendaMensal;
        this.conta = conta;
        this.agencia = agencia;
        this.transacoes = new ArrayList<>();
    }

    public void saque(double valor) {

    }

    public void deposito (double valor) {

    }

    public void transferencia (double valor, Conta contaOrigem, Conta contaDestino) {

    }

    public double getSaldo() {
        return saldo;
    }

    public String getNome() {
        return this.nome;
    }

    public void calcularRendimento(double valor, int meses) {

    }

    public void alterarCadastro(String nome, double rendaMensal) {
        this.nome = nome;
        this.rendaMensal = rendaMensal;
    }

    public String getAgencia() {
        return this.agencia;
    }

    public int getConta() {
        return this.conta;
    }

    public ArrayList<Transacao> getTransacoes() {
        return transacoes;
    }

    public String getCpf() {
        return this.cpf;
    }

    public String getTipo() {
        return this.tipo;
    }

    public double getRendaMensal() {
        return rendaMensal;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", rendaMensal=" + rendaMensal +
                ", conta=" + conta +
                ", agencia='" + agencia + '\'' +
                ", saldo=" + saldo +
                ", transacoes=" + transacoes +
                '}';
    }
}
