package br.com.devmonkeys.tela;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.devmonkeys.classe.Mysql;

public class TelaPrincipalFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton iniciarPararMysql;
	private Mysql mysql;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					TelaPrincipalFrame frame = new TelaPrincipalFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public TelaPrincipalFrame() throws IOException {

		this.mysql = new Mysql();

		boolean status = mysql.verificarStatus();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		this.contentPane = new JPanel();

		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout());

		this.label = new JLabel();

		this.label.setSize(200, 50);
		this.label.setVisible(true);

		this.iniciarPararMysql = new JButton();

		this.iniciarPararMysql.setSize(60, 60);
		this.iniciarPararMysql.setVisible(true);

		this.iniciarPararMysql.addActionListener(this);

		if (status) {

			this.label.setText("Mysql está rodando");
			this.iniciarPararMysql.setText("Parar");
		} else {

			this.label.setText("Mysql não está rodando");
			this.iniciarPararMysql.setText("Iniciar");
		}

		this.contentPane.add(label, BorderLayout.NORTH);
		this.contentPane.add(this.iniciarPararMysql, BorderLayout.CENTER);
		setContentPane(this.contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();
		
		try {
			
			if (obj == this.iniciarPararMysql) {

				if (this.mysql.isStatus()) {

					if (this.mysql.parar()) {

						this.label.setText("Mysql não está rodando");
						this.iniciarPararMysql.setText("Iniciar");
					}
				}else {
					
					if (this.mysql.iniciar()) {

						this.label.setText("Mysql está rodando");
						this.iniciarPararMysql.setText("Parar");
					}
				}
			}
		} catch (IOException e1) {

			e1.printStackTrace();
		}
	}

}
