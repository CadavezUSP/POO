public class Dragon extends Enemy{

    public Dragon (){
        setLife(300);
    }
    @Override
    public String[] getAttack() {
        String[] ataques = new String[maxAttack];
        ataques[0] = attack[0].getConta(1);
        ataques[1] = attack[1].getConta(2);
        ataques[2] = attack[2].getConta(2);
        return ataques;
    }


}
