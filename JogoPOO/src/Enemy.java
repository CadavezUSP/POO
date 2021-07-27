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
        setLife(life-damage);
    }
    public int calculateDamage(boolean[] acertos){
        return acertos.length*10;
    }
    public double[] getRespostas() {
        double[] respostas = new double[maxAttack];
        for (int i =0; i <maxAttack;i++){
            respostas[i] = attack[i].getRespostaAtual();
        }
        return respostas;
    }

    public boolean[] comparaRespostas(double[] respostas){
        boolean[] respostasCertas = new boolean[maxAttack];
        for (int i=0;i<maxAttack;i++){
           respostasCertas[i] = attack[i].ComparaRespostas(respostas[i]);
        }
        return respostasCertas;
    }
    public boolean isAlive(){
        return life >=0;
    }

}