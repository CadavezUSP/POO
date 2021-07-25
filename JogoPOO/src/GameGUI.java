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
    private Vector<JTextField> tfLista = new Vector<JTextField>();
    private JTextField textfield;
    private JButton button;
    
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
    
    private JTextField buscarJTextField(String nome){
        for (Iterator<JTextField> i = tfLista.iterator(); i.hasNext();) {
            JTextField jTextField = i.next();
            if(jTextField.getName().equals(nome)) return jTextField;
        }
        return null;
        //Se não encontrar o textfield retorna null
    }
    
    public void jTextField(String nome,int x, int y, int w, int h){
        textfield = new JTextField();
        textfield.setBounds(x, y, w, h);
        textfield.setName(nome);
        this.add(textfield);
        tfLista.add(textfield);
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
        jTextField(textoBox,115, 380, 100, 25);
        jButton(botaoBox, 115, 480, 100, 25, "Confirmar Resposta", 'c');
        setVisible(true);
    }
    
    //organiza os argumentos da conta em um painel e o retorna
    JPanel mostra_conta(int arg1, int arg2, char operador) {
    	//argumentos da conta e o operador
    	JLabel ar1 = new JLabel("done talk");
	    JLabel ar2 = new JLabel("done talk");
	    JLabel op = new JLabel("done talk");
	    
	    //inicializa as labels com os valores a serem mostrados //centraliza
	    ar1.setText(String.valueOf(arg1)); ar1.setHorizontalAlignment(JLabel.CENTER); ar1.setVerticalAlignment(JLabel.CENTER);
	    ar2.setText(String.valueOf(arg2)); ar2.setHorizontalAlignment(JLabel.CENTER); ar2.setVerticalAlignment(JLabel.CENTER);
	    op.setText(String.valueOf(operador)); op.setHorizontalAlignment(JLabel.CENTER); op.setVerticalAlignment(JLabel.CENTER);
	    
	    
	    //define a fonte a ser mostrada
	    ar1.setFont(new Font("Verdana",1,20));
	    ar2.setFont(new Font("Verdana",1,20));
	    op.setFont(new Font("Verdana",1,20));
	    
	    JPanel conta = new JPanel(new FlowLayout());
	    conta.setLayout(new GridLayout(1,3));
	    
	    //organiza a conta para ser mostrada
	    conta.add(ar1);
	    conta.add(op);
	    conta.add(ar2);
	    
	    return conta;
	    
    }
    
    public void facil(int arg1, int arg2, char operador) {
    	
    	//limpa o painel
    	getContentPane().removeAll();
    		
    	JPanel conta1 = new JPanel(new FlowLayout());
    	conta1 = mostra_conta(arg1,arg2,operador);    
	    painel.add(conta1);
	    
	    painel.setBorder(new LineBorder(Color.BLACK)); // make it easy to see
	    set_resposta("Resposta","confirma"); //parametros passados confirmam a resposta
	    
        revalidate();
        repaint();
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
    
        if ("jogar".equals(e.getActionCommand())) {
            
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
        	 
        	facil(1,2,'+'); 
             
        }else if("normal".equals(e.getActionCommand())){
	       	 getContentPane().removeAll();
	         getContentPane().add(jp3);
	         revalidate();
	         repaint();
        }else if("dificil".equals(e.getActionCommand())){
	       	 getContentPane().removeAll();
	         getContentPane().add(jp3);
	         revalidate();
	         repaint();
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
                try{
                    String resposta  = buscarJTextField("Resposta").getText();
                    System.out.println("Usuário: "+ resposta);
                } catch(Exception ex){
                    System.out.println("Não encontrou a resposta");
                    ex.printStackTrace();
                }
            }
        
        }
    
    }
}
