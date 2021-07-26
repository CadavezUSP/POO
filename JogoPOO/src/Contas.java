import java.util.Random;

public class Contas {
    private final int maxValueFacil = 20;
    private final int maxValueMedio = 50;
    private final int maxValueDificl = 100;
    private final int maxArgumentsMedio = 2;
    private final int maxArgumentsHard = 3;
    private final int maxValueMultDiv = 15;
    private double respostaAtual;
    private String conta;

    private int getRand (int maxValue){
        Random rand = new Random();
        int saida = rand.nextInt() % (maxValue);
        return saida +1;
    }

    private int getRand (int maxValue, int minValue){
        Random rand = new Random();
        int saida = 0;
        while (saida < minValue){
            saida = rand.nextInt() % (maxValue);
        }
        return saida;
    }

    private String generateOperator(boolean podeMultDiv){
        try {
            Thread.sleep(15);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        int operador;
        if (podeMultDiv) operador = getRand(4, 1);
        else operador = getRand(2,1);
        switch (operador){
            case 1:
                return "+";
            case 2:
                return "-";
            case 3:
                return "×";
            case 4:
                return "÷";
            default:
                return "";
        }
    }

    private double executarConta (double arg1, double arg2, String operator){
        switch (operator){
            case "+":
                return arg1 + arg2;
            case "-":
                return arg1 - arg2;
            case "×":
                return arg1 * arg2;
            case  "÷":
                return arg1/arg2;
            default:
                return 0;
        }
    }

    private void criaConta(int arg1, int arg2, String operator){
        if (arg1 <0 && arg2 <0){
            conta = conta + "[" + "(" + arg1 + ")" + " " + operator + " " + "(" + arg2 + ")" + "]     ";
        }
        else if(arg1<0){
            conta = conta + "[" + "(" + arg1 + ")" + " " + operator + " " +  arg2  + "]     ";
        }
        else if (arg2 <0){
            conta = conta + "[" + arg1 + " " + operator + " " + "(" + arg2 + ")" + "]     ";
        }
        else conta = conta + "(" + arg1 + " " + operator + " " + arg2 + ")     ";
    }

    private double calculaResposta(double[] respostas, String[] operador, int maxArguments){
        int i;
        for (i =0; i<maxArguments-1;i++){
            respostas[i+1] = executarConta(respostas[i], respostas[i+1], operador[i]);
        }
        return respostas[i];
    }


    public String getConta (int nivel){
        conta = "";
        int arg1;
        int arg2;
        boolean podeMultDiv;
        double[] respostas = new double[maxArgumentsHard];
        String[] operadores = new String[maxArgumentsHard];
        String operator;
        switch (nivel){
            case 0:
                arg1 = getRand(maxValueFacil, 1);
                arg2 = getRand(maxValueFacil, 1);
                operator = generateOperator(false);
                respostaAtual = executarConta(arg1, arg2, operator);
                conta = "" + arg1 + " " + operator + " " + arg2 + " =";
                break;
            case 1:
                arg1 = getRand(maxValueFacil, 1);
                arg2 = getRand(maxValueFacil, 1);
                operator = generateOperator(true);
                if (operator.equals("×") || operator.equals("÷")){
                    arg1 = arg1 %10;
                    arg2 = (arg2 %10) +1;

                }
                respostaAtual = executarConta(arg1, arg2, operator);
                criaConta(arg1, arg2, operator);
                conta = conta + "=";
                break;
            case 2:
                for (int i =0; i<maxArgumentsMedio; i++){
                    arg1 = getRand(maxValueFacil);
                    arg2 = getRand(maxValueFacil);
                    operator = generateOperator(false);
                    if (operator.equals("×") || operator.equals("÷")){
                        arg1 = arg1 %maxValueMultDiv;
                        arg2 = (arg2 %maxValueMultDiv) + 1;
                    }
                    criaConta(arg1, arg2, operator);
                    respostas[i] = executarConta(arg1, arg2, operator);
                    if (i != maxArgumentsMedio-1) {
                        operadores[i] = generateOperator(false);
                        conta = conta + operadores[i] + "     ";
                    }
                }
                conta = conta + "=";
                break;
            case 3:
                for (int i =0; i<maxArgumentsMedio; i++){
                    arg1 = getRand(maxValueMedio);
                    arg2 = getRand(maxValueMedio);
                    operator = generateOperator(true);
                    if (operator.equals("×") || operator.equals("÷")){
                        arg1 = arg1 %maxValueMultDiv;
                        arg2 = (arg2 %maxValueMultDiv) + 1;
                    }
                    criaConta(arg1, arg2, operator);
                    respostas[i] = executarConta(arg1, arg2, operator);
                    podeMultDiv = !(respostas[i] > 15);
                    if (i != maxArgumentsMedio-1) {
                        operadores[i] = generateOperator(podeMultDiv);
                        conta = conta + operadores[i] + "      ";
                    }
                }
                conta = conta + "=";
                respostaAtual = calculaResposta(respostas, operadores, maxArgumentsMedio);
                break;
            case 4:
                for (int i =0; i<maxArgumentsHard; i++){
                    arg1 = getRand(maxValueDificl);
                    arg2 = getRand(maxValueDificl);
                    operator = generateOperator(true);
                    if (operator.equals("×") || operator.equals("÷")){
                        arg1 = arg1 %maxValueMultDiv;
                        arg2 = (arg2 %maxValueMultDiv)+ 1;
                    }
                    criaConta(arg1, arg2, operator);
                    respostas[i] = executarConta(arg1, arg2, operator);
                    podeMultDiv = ((respostas[i] < 20) && (respostas[i] >-20));
                    if (i != maxArgumentsHard-1) {
                        operadores[i] = generateOperator(podeMultDiv);
                        conta = conta + operadores[i] + "      ";
                    }
                }
                conta = conta + " =";
                respostaAtual = calculaResposta(respostas, operadores, maxArgumentsHard);
                break;
        }
        return conta;
    }

    public double getRespostaAtual() {
        return respostaAtual;
    }

    public boolean ComparaRespostas(double resposta){
        String respostaEsperada = String.format("%.1f", respostaAtual);
        String respostaInserida = String.format("%.1f", resposta);
        return respostaEsperada.equals(respostaInserida);
    }

    @Override public String toString(){
        return conta;
    }
}
