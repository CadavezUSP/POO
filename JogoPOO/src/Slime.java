public class Slime extends Enemy{

    public Slime(){
        for (int i = 0; i < attack.length; i++) {
            attack[i] = new Contas();
        }
        setLife(150);
    }
    @Override
    public String[] getAttack() {
        String[] ataques = new String[maxAttack];
        ataques[0] = attack[0].getConta(0);
        ataques[1] = attack[1].getConta(1);
        ataques[2] = attack[2].getConta(1);
        return ataques;
    }
}
