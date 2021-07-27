import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GameGUI extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField resposta, resposta1, resposta2;
    private JButton button;
    private String contas[];
    private Enemy monstro;
    private int nRodadas, dificuldade;
    JLabel integrantes;
    JPanel painel, jp3;
    Pontuacao pontuacao = new Pontuacao(1);

    private void criaMenu(){
        //Garante que não vai ter nada no painel
        nRodadas=0;
        getContentPane().removeAll();

        painel = (JPanel) this.getContentPane();
        JPanel jp1 = new JPanel(new FlowLayout());
        JPanel jp2 = new JPanel(new FlowLayout());
        jp3 = new JPanel(new FlowLayout());
        JButton jogar = new JButton("Jogar");
        JButton instrucoes = new JButton("Instruções");
        this.integrantes = new JLabel("Integrantes: V, C, B, D");

        painel.setLayout(new GridLayout(3, 1));
        jogar.addActionListener(this); jogar.setActionCommand("jogar");
        instrucoes.addActionListener(this); instrucoes.setActionCommand("instrucoes");

        jp1.add(jogar);
        jp2.add(instrucoes);
        jp3.add(integrantes);
        painel.add(jp1); painel.add(jp2); painel.add(jp3);

        revalidate();
        repaint();
    }

    public GameGUI() {
        super("EducaMonstro");
        criaMenu();
    }

    public void jTextField(String nome,int x, int y, int w, int h){
        resposta = new JTextField();
        resposta.setBounds(x, y, w, h);
        resposta.setName(nome);
        this.add(resposta);
    }

    public void jTextField1(String nome,int x, int y, int w, int h){
        resposta1 = new JTextField();
        resposta1.setBounds(x, y, w, h);
        resposta1.setName(nome);
        this.add(resposta1);
    }

    public void jTextField2(String nome,int x, int y, int w, int h){
        resposta2 = new JTextField();
        resposta2.setBounds(x, y, w, h);
        resposta2.setName(nome);
        this.add(resposta2);
    }

    public void jButton(String text, int x, int y, int w, int h, String legenda, char m){
        button = new JButton(text);
        button.setBounds(x, y, w, h);
        button.addActionListener(this);
        button.setActionCommand(legenda);
        button.setMnemonic(m);
        this.add(button);
    }

    //organiza os argumentos da conta em um painel e o retorna
    JPanel mostra_conta(String conta) {

        // Cria o texto da conta que será mostrada
        JLabel label_conta = new JLabel("conta");
        label_conta.setText(String.valueOf(conta)); label_conta.setHorizontalAlignment(JLabel.CENTER); label_conta.setVerticalAlignment(JLabel.CENTER);

        // Define a fonte a ser mostrada
        label_conta.setFont(new Font("Verdana", 1, 20));

        // Define o painel onde a conta será mostrada
        JPanel painel_conta = new JPanel(new FlowLayout());
        painel_conta.setLayout(new GridLayout(1,3));

        // Insere a conta na tela
        painel_conta.add(label_conta);

        return painel_conta;

    }

    public void cria_conta(String[] operador) {

        //limpa o painel
        getContentPane().removeAll();

        JPanel dif = new JPanel(new FlowLayout());
        dif.setLayout(new GridLayout(3,3));

        JPanel conta1 = new JPanel(new FlowLayout());

        conta1 = mostra_conta(operador[0]);
        painel.add(conta1);

        jTextField("resposta",115, 380, 100, 25);

        conta1 = mostra_conta(operador[1]);
        painel.add(conta1);

        jTextField1("resposta1",115, 380, 100, 25);

        conta1 = mostra_conta(operador[2]);
        painel.add(conta1);
        jTextField2("resposta2",115, 380, 100, 25);

        jButton("Menu", 115, 480, 100, 25, "menu", 'c');
        jButton("confirma", 115, 480, 100, 25, "confirma", 'c');
        setVisible(true);

        revalidate();
        repaint();
        return;
    }

    public boolean[] catch_resposta(){
        double[] respDouble = new double[3];
        try{

            String resp = resposta.getText();
            respDouble[0] = Double.parseDouble(resp);
            //System.out.printf("RESP " + resp);

            String resp1 = resposta1.getText();
            respDouble[1] = Double.parseDouble(resp1);
            //System.out.printf("RESP1 " + resp1);

            String resp2 = resposta2.getText();
            respDouble[2] = Double.parseDouble(resp2);
            //System.out.printf("RESP2 " + resp2);
            System.out.println(respDouble[0]);
            System.out.println(respDouble[1]);
            System.out.println(respDouble[2]);
            return monstro.comparaRespostas(respDouble);

        } catch(Exception ex){
            System.out.println("Não encontrou a resposta");
            ex.printStackTrace();
        }
        return null;

    }

    public String[] selecionarFala(boolean[] respostas){
        int nacertos = 0;
        for (boolean acerto : respostas){
            if (acerto) nacertos++;
        }
        monstro.apllyDamage(nacertos*dificuldade*5);
        String[] fala = new String[4];
        int contador = 0;
        if (pontuacao.getCombo() >1){
            fala[contador++] = "Nossa voce esta indo muito bem parece que voce desbloqueou algum bonus ou algo por ai";
        }
        if (nRodadas ==1 && nacertos <3){
            fala[contador++] = "Seus erros nao me divertem humano, preste atencao";
        }
        else if(nRodadas ==1 && nacertos ==3){
            fala[contador++]="Muito bem, nunca imaginei que humanos pudessem ser tao uteis";
        }
        else if(nRodadas ==2 && nacertos==3){
            fala[contador++]="Voce esta se provando muito util continue assim";
        }
        else if(monstro.getLife()<20 && pontuacao.getPontos() <800){
            fala[contador++]="Arrg voce esta tentando me enganar essa e a sua ultima chance";
        }
        else if(nacertos==0){
            fala[contador++] = "Esperava mais de você humano!!";
        }
        else if(nacertos == 1){
            fala[contador++] = "com esses resultados VOCÊ acha que vai salvar a Terra";
        }
        else if(nacertos==2){
            fala[contador++] = "";
        }
        else{
            fala[contador++] = "Quase lá...";
        }
        String[] retorno = new String[contador-1];
        for (int i =0; i<retorno.length;i++){
            retorno[i] = fala[i];
        }
        return retorno;

    }

    public JLabel texto(String str, int tam) {
        JLabel op = new JLabel("str");
        op.setText(String.valueOf(str));
        op.setHorizontalAlignment(JLabel.CENTER);
        op.setVerticalAlignment(JLabel.CENTER);

        //define a fonte a ser mostrada
        op.setFont(new Font("Verdana",1,tam));

        return op;
    }

    public void cutscene(String []dialogo){
        int pontuacaoRodada= pontuacao.getPontosRodada(), pontuacaoTotal = pontuacao.getPontos();

        //limpa o painel
        getContentPane().removeAll();

        //JPanel painel = new JPanel(new FlowLayout());
        painel.setLayout(new GridLayout(5,1));

        JLabel empty = new JLabel();

        //primeira linha do painel
        JPanel linha1 = new JPanel(new FlowLayout());
        linha1.setLayout(new GridLayout(1,3));

        JLabel gameNome = texto("EducaMonstro", 40);
        JLabel pRtexto = texto("Pontuacao da Rodada", 25);
        JLabel pTtexto = texto("Pontuacao Total", 25);

        String str = "" + pontuacaoRodada;
        JLabel pR = texto(str.toString(), 20);
        str = "" + pontuacaoTotal;
        JLabel pT = texto(str.toString(), 20);

        JPanel linhaLinha = new JPanel(new FlowLayout());
        linhaLinha.setLayout(new GridLayout(2,2));
        linhaLinha.add(pRtexto);
        linhaLinha.add(pTtexto);
        linhaLinha.add(pR);
        linhaLinha.add(pT);

        linha1.add(gameNome);
        linha1.add(empty);
        linha1.add(linhaLinha);

        //segunda linha
        JPanel linha2 = new JPanel(new FlowLayout());
        int i = dialogo.length;
        linha2.setLayout(new GridLayout(i,1));

        JLabel dialogos;

        for(String fala: dialogo){
            dialogos = texto(fala,20);
            linha2.add(dialogos);
        }



        //linha 4
        JPanel linha4 = new JPanel(new FlowLayout());
        linha4.setLayout(new GridLayout(1,4));

        JButton menu = new JButton("Menu");
        menu.addActionListener(this); menu.setActionCommand("menu");
        linha4.add(menu);

        linha4.add(empty);
        linha4.add(empty);
        if(monstro.isAlive()) {
            JButton prox = new JButton("Proximo");
            prox.addActionListener(this);
            prox.setActionCommand("Proximo");
            linha4.add(prox);
        }

        painel.add(linha1);
        painel.add(linha2);

        ImageIcon image = new ImageIcon("dragao.jpg");
        JLabel labelSecreta = new JLabel();
        labelSecreta.setIcon(image);
        painel.add(labelSecreta);

        painel.add(linha4);


        revalidate();
        repaint();
        return;
    }

    public void lets_game(int opt) {
        pontuacao.setNivel(opt+1);
        if(opt == 0) { //facil
            monstro = new Slime();

        }else if(opt == 1) { //normal
            monstro = new Dragon();

        }else if(opt == 2) { //dificil
            monstro = new Demon();

        }else if(opt ==3) { //extremo
            monstro = new God();

        }
        // Seta o painel do frame principal para organizar o jogo
        painel.setLayout(new GridLayout(4, 1));

        contas = monstro.getAttack();
        cria_conta(contas);
        pontuacao.startRound();
        nRodadas++;
        dificuldade = opt;
    }
    public void setDificuldade(){

        // Cria o painel de dificuldades
        JPanel dif = new JPanel(new FlowLayout());

        // Cria os botões para as opções de dificulade
        JButton facil  = new JButton("Facil");
        JButton normal  = new JButton("Normal");
        JButton dificil  = new JButton("Dificil");
        JButton extremo  = new JButton("Extremo");

        // Seta os listeners para os botões de escolha da dificulade
        facil.addActionListener(this); facil.setActionCommand("Facil");
        normal.addActionListener(this); normal.setActionCommand("Normal");
        dificil.addActionListener(this); dificil.setActionCommand("Dificil");
        extremo.addActionListener(this); extremo.setActionCommand("Extremo");

        // Adiciona os botões no painel de dificuldades
        dif.add(facil);
        dif.add(normal);
        dif.add(dificil);
        dif.add(extremo);

        // Adiciona o painel de dificuldades no frame
        painel.add(dif);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Se o usuário apertou no botão de jogar
        if("jogar".equals(e.getActionCommand())) {

            // Limpa a tela
            getContentPane().removeAll();

            // Adiciona os integrantes do grupo no frame
            painel.add(jp3);

            // Adiciona as opções de dificuldade no frame
            setDificuldade();

            // Revalida o frame e mostra as informações
            revalidate();
            repaint();

        }else if ("instrucoes".equals(e.getActionCommand())) {

            JFrame janelainst = new JFrame("Instruções");
            JPanel painel = (JPanel) janelainst.getContentPane();
            JLabel l1 = new JLabel("O jogo consiste em problemas matemáticos para crianças."); l1.setHorizontalAlignment(JLabel.CENTER); l1.setVerticalAlignment(JLabel.CENTER);
            JLabel l2 = new JLabel("São elaboradas questões baseadas nas operações básicas com nível de"); l2.setHorizontalAlignment(JLabel.CENTER); l2.setVerticalAlignment(JLabel.CENTER);
            JLabel l3 = new JLabel("dificuldade escolhido pelo usuário. O objetivo é vencer os monstros a partir"); l3.setHorizontalAlignment(JLabel.CENTER); l3.setVerticalAlignment(JLabel.CENTER);
            JLabel l4 = new JLabel("de respostas corretas para as perguntas que eles fazem."); l4.setHorizontalAlignment(JLabel.CENTER); l4.setVerticalAlignment(JLabel.CENTER);
            JLabel l5 = new JLabel("Recomendasse jogar em tela cheia para melhor visualização das contas."); l4.setHorizontalAlignment(JLabel.CENTER); l5.setVerticalAlignment(JLabel.CENTER);

            painel.setLayout(new FlowLayout());
            painel.add(l1);
            painel.add(l2);
            painel.add(l3);
            painel.add(l4);
            painel.add(l5);

            janelainst.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            janelainst.setSize(500, 150);
            janelainst.setVisible(true);

        }else if("Facil".equals(e.getActionCommand())){
            pontuacao = new Pontuacao(1);
            monstro = new Slime();
            lets_game(0);
        }else if("Normal".equals(e.getActionCommand())){
            pontuacao = new Pontuacao(2);
            monstro = new Dragon();
            lets_game(1);
        }else if("Dificil".equals(e.getActionCommand())){
            pontuacao = new Pontuacao(3);
            monstro = new Demon();
            lets_game(2);
        }else if("Extremo".equals(e.getActionCommand())){
            pontuacao = new Pontuacao(4);
            monstro = new God();
            lets_game(3);
        }else if("Proximo".equals(e.getActionCommand())){
            lets_game(dificuldade);
        }
        else if("menu".equals(e.getActionCommand())){
            criaMenu();
            boolean[] respostas = catch_resposta();
            pontuacao.marcarPontoRodada(respostas);
        }
        else if("confirma".equals(e.getActionCommand())){
            boolean[] respostas = catch_resposta();
            pontuacao.marcarPontoRodada(respostas);
            String[] fala = selecionarFala(catch_resposta());
            System.out.println(fala[0]);
            cutscene(fala);
        }
    }

}