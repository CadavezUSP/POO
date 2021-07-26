public class main {
    public static void main(String[] args) throws Exception{
//        GameGUI frame = new GameGUI();
//
//        frame.setDefaultCloseOperation(GameGUI.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//        frame.setSize(500, 500);
        Dragon dragao = new Dragon();
        String[] ataques = dragao.getAttack();
        for (int i =0;i<ataques.length;i++){
            System.out.println(ataques[i]);
        }

    }
}