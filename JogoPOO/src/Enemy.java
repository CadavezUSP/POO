public abstract class Enemy {
    private int life;
    protected final int maxAttack= 3;
    protected Contas[] attack = new Contas[maxAttack];
    protected void setLife(int life){
        this.life = life;
    }

    public abstract String[] getAttack();
    public int getLife(){
        return life;
    }
    public void apllyDamage(int damage){
        int currentLife = getLife();
        setLife(currentLife-damage);
    }
    public int calculateDamage(int pontos){
        return pontos/20;
    }
    public double[] getRespostas() {
        double[] respostas = new double[maxAttack];
        for (int i =0; i <maxAttack;i++){
            respostas[i] = attack[i].getRespostaAtual();
        }
        return respostas;
    }
    public boolean isAlive(){
        return life <=0;
    }
}
