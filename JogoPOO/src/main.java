public class main {
    public static void main(String[] args) throws Exception{
        GameGUI frame = new GameGUI();

        frame.setDefaultCloseOperation(GameGUI.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(500, 500);
        Demon dragao = new Demon();
        String[] ataques = dragao.getAttack();
        frame.contaToGUI(ataques);

    }
}