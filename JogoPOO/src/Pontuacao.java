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
                System.out.println(timeBonus);
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
        int periodo = nivel*3;
        timer = new Timer();
        timer.schedule(new decreaseBonus(), 0, (periodo* 1000L));
    }

    public void marcarPontoRodada (boolean[] acertos){
        int counter=0;
        for (boolean acerto : acertos) {
            System.out.println(acerto);
            if (acerto) counter++;
        }
        pontosRodada = counter*nivel*combo* + timeBonus;
        pontos += pontosRodada;
        timeBonus = 100*nivel;
        timer.cancel();
    }

    private void increaseCombo() {
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

    private void breakCombo (){
        combo =1;
    }

    public void correctCombo(boolean[] acertos){
        int counter =0;
        for (boolean acerto : acertos) {
            if (acerto) counter++;
        }
        if (counter==acertos.length){
            increaseCombo();
        }
        else {
            breakCombo();
        }
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        String string = "Sua pontuação atual é: " + pontos;
        return string;
    }
}
