public class Demon extends Enemy{
    public Demon (){
        for (int i = 0; i < attack.length; i++) {
            attack[i] = new Contas();
        }
        setLife(500);
    }
    @Override
    public String[] getAttack() {
        String[] ataques = new String[maxAttack];
        ataques[0] = attack[0].getConta(2);
        ataques[1] = attack[1].getConta(3);
        ataques[2] = attack[2].getConta(4);
        return ataques;
    }
}