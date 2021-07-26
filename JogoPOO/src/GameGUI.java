import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.Iterator;
import java.util.Objects;
import java.util.Vector;
import java.util.concurrent.*;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class GameGUI extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField resposta, resposta1, resposta2;
    private JButton button;
    String contas[];
    Enemy monstro;

    JLabel integrantes;
    JPanel painel, jp3;

    public GameGUI() {

        super("EducaMonstro");
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
        button.setToolTipText(legenda);
        button.setMnemonic(m);
        this.add(button);
    }

    //configura a caixa de texto e retorna o painel
    void set_resposta(String textoBox, String botaoBox) {

        jTextField("resposta",115, 380, 100, 25);
        jTextField1("resposta1",115, 380, 100, 25);
        jTextField2("resposta2",115, 380, 100, 25);
        jButton("Confirmar Resposta", 115, 480, 100, 25, botaoBox, 'c');

        setVisible(true);
    }

    //organiza os argumentos da conta em um painel e o retorna
    JPanel mostra_conta(String operador) {
        //argumentos da conta e o operador
        JLabel op = new JLabel("operador");
        op.setText(String.valueOf(operador)); op.setHorizontalAlignment(JLabel.CENTER); op.setVerticalAlignment(JLabel.CENTER);

        //define a fonte a ser mostrada
        op.setFont(new Font("Verdana",1,20));

        JPanel conta = new JPanel(new FlowLayout());
        conta.setLayout(new GridLayout(1,3));

        //organiza a conta para ser mostrada
        conta.add(op);

        return conta;

    }

    public void cria_conta(String[] operador) {

        //limpa o painel
        getContentPane().removeAll();

        JPanel dif = new JPanel(new FlowLayout());
        dif.setLayout(new GridLayout(3,3));

        JPanel conta1 = new JPanel(new FlowLayout());

        conta1 = mostra_conta(operador[0]);
        painel.add(conta1);

        String botaoBox = "confirma";
        jTextField("resposta",115, 380, 100, 25);

        conta1 = mostra_conta(operador[1]);
        painel.add(conta1);

        jTextField1("resposta1",115, 380, 100, 25);

        conta1 = mostra_conta(operador[2]);
        painel.add(conta1);
        jTextField2("resposta2",115, 380, 100, 25);


        jButton("confirma", 115, 480, 100, 25, botaoBox, 'c');
        setVisible(true);

        revalidate();
        repaint();
        return;
    }

    public void catch_resposta(){
        double[] respDouble = new double[3];
        try{

            String resp = resposta.getText();
            respDouble[0] = Double.parseDouble(resp);

            String resp1 = resposta1.getText();
            respDouble[1] = Double.parseDouble(resp);

            String resp2 = resposta2.getText();
            respDouble[2] = Double.parseDouble(resp);

        } catch(Exception ex){
            System.out.println("Não encontrou a resposta");
            ex.printStackTrace();
        }


        return;
    }

    public void lets_game(int opt, String[] operador) {
        int rodadas = 0;

        if(opt == 0) { //facil
            rodadas = 2;

            while(rodadas > 0){
                cria_conta(operador);
                rodadas --;
            }

        }else if(opt == 1) { //normal

        }else if(opt == 2) { //dificil

        }else if(opt ==3) { //extremo

        }
    }
    public void setDificuldade(){

        JPanel dif = new JPanel(new FlowLayout());
        painel.setLayout(new GridLayout(4, 1));

        JButton facil  = new JButton("Facil");
        JButton normal  = new JButton("Normal");
        JButton dificil  = new JButton("Dificil");
        JButton extremo  = new JButton("Extremo");

        facil.addActionListener(this); facil.setActionCommand("facil");
        normal.addActionListener(this); normal.setActionCommand("normal");
        dificil.addActionListener(this); dificil.setActionCommand("dificil");
        extremo.addActionListener(this); extremo.setActionCommand("extremo");

        dif.add(facil);
        dif.add(normal);
        dif.add(dificil);
        dif.add(extremo);

        painel.add(dif);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if("jogar".equals(e.getActionCommand())) {

            getContentPane().removeAll();
            getContentPane().add(jp3);
            setDificuldade();
            revalidate();
            repaint();

        }else if ("instrucoes".equals(e.getActionCommand())) {

            JFrame janelainst = new JFrame("Instruções");
            JPanel painel = (JPanel) janelainst.getContentPane();
            JLabel l1 = new JLabel("O jogo consiste em problemas matemáticos para crianças."); l1.setHorizontalAlignment(JLabel.CENTER); l1.setVerticalAlignment(JLabel.CENTER);
            JLabel l2 = new JLabel("São elaboradas questões baseadas nas operações básicas com nível de"); l2.setHorizontalAlignment(JLabel.CENTER); l2.setVerticalAlignment(JLabel.CENTER);
            JLabel l3 = new JLabel("dificuldade escolhido pelo usuário. O objetivo é vencer os monstros a partir"); l3.setHorizontalAlignment(JLabel.CENTER); l3.setVerticalAlignment(JLabel.CENTER);
            JLabel l4 = new JLabel("de respostas corretas para as perguntas que eles fazem."); l4.setHorizontalAlignment(JLabel.CENTER); l4.setVerticalAlignment(JLabel.CENTER);

            painel.setLayout(new FlowLayout());
            painel.add(l1);
            painel.add(l2);
            painel.add(l3);
            painel.add(l4);

            janelainst.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            janelainst.setSize(500, 150);
            janelainst.setVisible(true);

        }else if("facil".equals(e.getActionCommand())){

            lets_game(0,contas);
            monstro = new Slime();

        }else if("normal".equals(e.getActionCommand())){
            getContentPane().removeAll();
            getContentPane().add(jp3);
            revalidate();
            repaint();
            monstro = new Dragon();
        }else if("dificil".equals(e.getActionCommand())){
            getContentPane().removeAll();
            getContentPane().add(jp3);
            revalidate();
            repaint();
            monstro = new Demon();
        }else if("extremo".equals(e.getActionCommand())){
            getContentPane().removeAll();
            getContentPane().add(jp3);
            revalidate();
            repaint();
        }

        Object obj = e.getSource();
        if(obj instanceof JButton){
            button = (JButton)obj;
            if(button.getText().equals("confirma")){
                catch_resposta();
            }

        }
    }
    public void contaToGUI(String[] contas){
        this.contas = contas;
    }

}