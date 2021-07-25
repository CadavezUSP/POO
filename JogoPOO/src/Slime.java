public class Slime extends Enemy{

    public Slime(){
        setLife(150);
    }
    @Override
    public String[] getAttack() {
        String[] ataques = new String[maxAttack];
        ataques[0] = attack[0].getConta(0);
        ataques[1] = attack[1].getConta(0);
        ataques[2] = attack[2].getConta(1);
        return ataques;
    }

}
