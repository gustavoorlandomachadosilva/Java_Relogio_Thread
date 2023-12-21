package com.java.Thread;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
									//Extendemos a classe JDialog para usar Telas
public class TelaTimeThread extends JDialog {
	private Thread thread1Time;
	private Thread thread2Time;
	
	//Construtor da classe
	public TelaTimeThread() {
		/*1.O construtor inicializa pela primeira vez o objeto
		 * Logo, nossa tela deve está sendo chamada nele.	 
		 */
		//Modificando titulo
				setTitle("Tela de Tempo com Thread");
				//Modificando tamanho da tela
				setSize(new Dimension(250,250));
				//Não é possível redimensionar a tela
				setResizable(false);
				//Centralizando tela
				setLocationRelativeTo(null);
				
		//Criando um panel para receber os elementos da tela
		JPanel panel = new JPanel(new GridBagLayout());
		JLabel descricaoHora = new JLabel();
		JTextField mostraTempo = new JTextField();
		JLabel descricaoHora2 = new JLabel();
		JTextField mostraTempo2 = new JTextField();	
		JButton jbutton = new JButton("Start");
		JButton jbutton2 = new JButton("Stop");
		
		//Criando Thread
		Runnable thread1 = new Runnable() {			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true) {
					mostraTempo.setText(new SimpleDateFormat("dd/MM/YY hh:mm.ss").format(Calendar.getInstance().getTime()));
					try {
	                    Thread.sleep(1000);
	                } catch (InterruptedException e) {
	                    // A thread foi interrompida durante o sono, tratando a exceção
	                    Thread.currentThread().interrupt(); // Restaurando a flag de interrupção
	                    break; // Saindo do loop para encerrar a thread
	                }
				}
				
			}
		};		
		
		Runnable thread2 = new Runnable() {			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true) {
					mostraTempo2.setText(new SimpleDateFormat("dd-MM-YY hh:mm.ss").format(Calendar.getInstance().getTime()));
					try {
	                    Thread.sleep(1000);
	                } catch (InterruptedException e) {
	                    // A thread foi interrompida durante o sono, tratando a exceção
	                    Thread.currentThread().interrupt(); // Restaurando a flag de interrupção
	                    break; // Saindo do loop para encerrar a thread
	                }
				}
				
			}
		};			
		
		//Criando um gerenciador de layout
		GridBagConstraints bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = 0;
		bagConstraints.gridy = 0;
		bagConstraints.gridwidth = 2;
		bagConstraints.insets = new Insets(5,10,5,5);
		bagConstraints.anchor = GridBagConstraints.WEST;
		
		//Adicionando Componentes
		descricaoHora.setText("Time Thread 1");
		descricaoHora.setPreferredSize(new Dimension(200,25));
		panel.add(descricaoHora,bagConstraints);
		
		mostraTempo.setPreferredSize(new Dimension(200,25));
		mostraTempo.setEditable(false);
		bagConstraints.gridy++;
		panel.add(mostraTempo,bagConstraints);
		
		descricaoHora2.setText("Time Thread 2");
		descricaoHora2.setPreferredSize(new Dimension(200,25));
		bagConstraints.gridy++;
		panel.add(descricaoHora2,bagConstraints);
		
		mostraTempo2.setPreferredSize(new Dimension(200,25));
		mostraTempo2.setEditable(false);
		bagConstraints.gridy++;
		panel.add(mostraTempo2,bagConstraints);
		
		bagConstraints.gridwidth = 1;
		
		jbutton.setPreferredSize(new Dimension(92,25));
		bagConstraints.gridy++;
		panel.add(jbutton,bagConstraints);
		
		jbutton2.setPreferredSize(new Dimension(92,25));
		bagConstraints.gridx++;
		panel.add(jbutton2,bagConstraints);
		
		jbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Inicie a thread apenas se ela ainda não estiver em execução
                if (thread1Time == null || !thread1Time.isAlive()) {
                    thread1Time = new Thread(thread1);
                    thread1Time.start();
                    jbutton.setEnabled(false);
                    jbutton2.setEnabled(true);
                }
                if (thread2Time == null || !thread2Time.isAlive()) {
                    thread2Time = new Thread(thread2);
                    thread2Time.start();
                    
                }
            }
        });

        // Criando um ActionListener para o botão "Stop"
        jbutton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pare a thread apenas se ela estiver em execução
                if (thread1Time != null && thread1Time.isAlive()) {
                    thread1Time.interrupt();
                    jbutton2.setEnabled(false);
                    jbutton.setEnabled(true);
                }
                if (thread2Time != null && thread2Time.isAlive()) {
                    thread2Time.interrupt();
                }
            }
        });		
        
        jbutton2.setEnabled(false);
		add(panel,BorderLayout.WEST);
		//Tem que ser a última coisa a ser chamada
		 setVisible(true);
		
		
	}
	
}
