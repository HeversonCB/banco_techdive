import java.util.Calendar;

public class Transacao {
    private int contaOrigem;
    private int contaDestino;
    private double valor;
    private Calendar data;
    private String tipo;

    public Transacao(int contaOrigem, int contaDestino, double valor, Calendar data) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
        this.data = data;
        this.tipo = "Transferencia";
    }

    public Transacao(int contaOrigem, double valor, Calendar data) {
        this.contaOrigem = contaOrigem;
        this.valor = valor;
        this.data = data;
        this.tipo = "Saque";
    }

    public Transacao(double valor, int contaDestino, Calendar data) {
        this.contaDestino = contaDestino;
        this.valor = valor;
        this.data = data;
        this.tipo = "Deposito";
    }

    @Override
    public String toString() {
        switch (tipo) {
            case "Saque":
                return  "Operação = Saque        " +
                        " ||| Valor = R$" + valor +
                        " ||| Data = " + data.getTime() + "\n";
            case "Deposito":
                return  "Operação = Depósito     " +
                        " ||| Valor = R$" + valor +
                        " ||| Data = " + data.getTime() + "\n";
            case "Transferencia":
                return  "Operação = Transferência" +
                        " ||| Valor = R$" + valor +
                        " ||| Conta Origem = " + contaOrigem +
                        " ||| Conta Destino = " + contaDestino +
                        " ||| Data = " + data.getTime() + "\n";
            default:
                break;
        }
        return "";
    }
}
