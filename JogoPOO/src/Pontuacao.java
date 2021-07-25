import java.util.Timer;
import java.util.TimerTask;

public class Pontuacao {
    private int nivel;
    private int pontos =0;
    private int combo =1;
    private int timeBonus;
    private Timer timer;
    private int pontosRodada;

    class decreaseBonus extends TimerTask {
        public void run() {
            if (timeBonus >-100) {
                timeBonus -= nivel*10;
            }
            else {
                timer.cancel();
            }
        }
    }

    public Pontuacao(int nivel){
        this.nivel = nivel;
        timeBonus = 100*nivel;
    }

    public void startRound(){
        int periodo = nivel*5;
        System.out.println(periodo);
        timer = new Timer();
        timer.schedule(new decreaseBonus(), 0, (periodo* 1000L));
    }

    public void marcarPontoRodada (){
        pontosRodada = nivel*combo*100 + timeBonus;
        pontos += pontosRodada;
        timeBonus = 100;
        timer.cancel();
    }

    public void increaseCombo() {
        combo++;
    }

    public int getCombo() {
        return combo;
    }

    public int getPontos() {
        return pontos;
    }

    public int getPontosRodada() {
        return pontosRodada;
    }

    public int getTimeBonus() {
        return timeBonus;
    }

    public void breakCombo (){
        combo =1;
    }

    @Override
    public String toString() {
        String string = "Sua pontuação atual é: " + pontos;
        return string;
    }
}