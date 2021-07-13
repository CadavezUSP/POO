public class main {
    public static void main(String[] args) throws Exception{
        Contas contas = new Contas();
        Pontuacao pontos = new Pontuacao(2);
        pontos.startRound();
        for (int i =0;i<15;i++) {
            System.out.println(contas.getConta(4));
        }
    }
}
