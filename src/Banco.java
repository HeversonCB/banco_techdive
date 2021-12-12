import java.util.ArrayList;

public class Banco{

    private final String nome = "TECHDive";
    private ArrayList<Conta> contas = new ArrayList<>();
    private int qtdContas = 1;

    public int getQtdContas() {
        return qtdContas;
    }

    public void addConta(Conta conta) {
        this.contas.add(conta);
    }

    public ArrayList<Conta> getContas() {
        return this.contas;
    }

    public void somarQtdContas() {
        qtdContas++;
    }
}
