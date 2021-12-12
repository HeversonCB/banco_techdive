public class Main {

    static Banco banco = new Banco();
    static Menu menu = new Menu();
    //Salva a conta que estará sendo acessada pelo usuário.
    static int contaAtual = 0;

    public static void main(String[] args) {


        try {
            Menu.MenuPrincipal();
        } catch (Exception er) {
            System.out.println("Erro " + er);
        }


    }
}