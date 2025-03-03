import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class jfBloqueioPalavra extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jfBloqueioPalavra frame = new jfBloqueioPalavra();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public jfBloqueioPalavra() {
		setTitle("Lista de Palavras");
		setBounds(100, 100, 900, 600);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(0, 0, 872, 543);
		textArea.setEditable(true);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(0, 0, 872, 543);
		panel.add(scrollPane);
		
		try {
			File file = new File("bloqueioPalavra.conf");
			
			if (!file.exists()) {
				file.createNewFile();
			}
			
			BufferedReader arquivo = new BufferedReader(new FileReader(file));
			//System.out.println("Arquivo aberto");
			String str, txt="";
			while((str = arquivo.readLine()) != null){
				txt += str + "\n"; //Ele pega a linha coloca uma quebra de linha no final e "concatena" com o que j� tem na variavel txt
			}
			textArea.setText(txt);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			//scrollPane.add(textArea);
			
			JMenuBar menuBar = new JMenuBar();
			scrollPane.setColumnHeaderView(menuBar);
			
			JButton btnCancelar = new JButton("Fechar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			
			JButton btnSalvar = new JButton("Salvar");
			btnSalvar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						BufferedWriter fileOut = new BufferedWriter(new FileWriter(file));
						fileOut.append(textArea.getText());
						JOptionPane.showMessageDialog(textArea, "Arquivo salvo com sucesso!");
						fileOut.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			menuBar.add(btnSalvar);
			menuBar.add(btnCancelar);
			//scrollPane.add(textArea);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
