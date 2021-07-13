import java.util.Calendar;

/**
 * gerador simples de n�meros aleat�rios.
 * @author delamaro
 *
 */
public class Random {
    private long p = 2147483648l;
    private long m = 843314861;
    private long a = 453816693;


    private long xi;

    /**
     * Retorna um n�mero aleat�rio no intervalo (0,1[
     * @return o n�mero gerado.
     */
    public double getRand() {
        xi = (a + m * xi) % p;
        double d = xi;
        return d / p;
    }

    /**
     * Retorna um valor inteiro no intervalo (0,max[
     * @param max O valor limite para a gera��o do n�mero inteiro
     * @return o n�mero gerado
     */
    public int getIntRand(int max)
    {
        double d = getRand() * max;
        return (int) d;
    }

    /**
     * Permite alterar a semente de gera��o de n�meros aleat�rios. Supostamente deve ser chamada
     * antes de iniciar a gera��o, mas se for chamado a qualquer instante, reseta o valor
     * da semante
     * @param semente o valor da nova semente de gera��o
     */
    public void setSemente(int semente) {
        xi = semente;
    }

    /**
     * Construtor que permite criar o gerador, especificando o valor inicial da semente.
     * @param k O valor inicial da semente.
     */
    public Random(int k)
    {
        xi = k;
    }

    /**
     * Construtor que usa uma semente aleat�ria, adquerida usando o m�todo Calendar.getTimeInMillis().
     */
    public Random() {
        xi = Calendar.getInstance().getTimeInMillis() % p;
    }

}