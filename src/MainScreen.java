import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;


public class MainScreen extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Panel container;
	public JComboBox newGame;
	private JButton instructions, exit;
	private GridBagConstraints gbc;
	
	public MainScreen(String caption){
		super(caption);
		setSize(700,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		createPanel();
		createButtons();
		getRootPane().setDefaultButton(instructions);
	}
	
	private void createButtons() {
		DefaultListCellRenderer dlcr = new DefaultListCellRenderer(); 
		dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER); 
		gbc = new GridBagConstraints();
		newGame = new JComboBox();
		newGame.setFont(new Font("Courier New", Font.ITALIC,20));
		newGame.setRenderer(dlcr);
		//((JLabel)newGame.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		newGame.addItem("New Game");
		for(int i=3;i<=8;i++){
			newGame.addItem(i+"x"+i);
		}
		newGame.setPreferredSize(new Dimension(200,100));
		gbc.gridx=0;
		gbc.gridy=0;
		container.add(newGame,gbc);
		instructions = new JButton("Instructions");
		instructions.setPreferredSize(new Dimension(200,100));
		gbc.gridy=1;
		gbc.gridx=0;
		gbc.insets = new Insets(20, 0, 0, 0);
		instructions.setFont(new Font("Courier New", Font.ITALIC,20));
		container.add(instructions,gbc);
		exit = new JButton("Exit :(");
		exit.setPreferredSize(new Dimension(200,100));
		exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
		gbc.gridx=0;
		gbc.gridy=2;
		exit.setFont(new Font("Courier New", Font.ITALIC,20));
		gbc.insets = new Insets(20, 0, 0, 0);
		container.add(exit,gbc);
	}

	private void createPanel() {
		container = new Panel();
		this.add(container,BorderLayout.CENTER);
	}

	public void Run(){
		this.setVisible(true);
	}
	public void Hide(){
		this.setVisible(false);
	}
	
}
