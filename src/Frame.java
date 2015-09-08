import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Panel panel;
	private int size;
	// private JButton[][] buttons;
	private ComboBox[][] comboBox;
	private GridBagConstraints gbc;
	private JLabel[][] horsigns, versigns;
	private boolean[][][] verSigns;
	private boolean[][][] horSigns;
	private int[][] numbers;
	private int[][] randNumbers;
	private boolean filled;
	private boolean ok;
	public Menu MenuBar;

	public Frame(int n, boolean[][][] _verSigns, boolean[][][] _horSigns,
			int[][] numbers, Menu menu) {
		super("Futoshiki");
		this.size = n;
		verSigns = _verSigns;
		horSigns = _horSigns;
		gbc = new GridBagConstraints();
		this.numbers = numbers;
		horsigns = new JLabel[size - 1][size - 1];
		versigns = new JLabel[size - 1][size - 1];
		randNumbers = new int[size][size];
		comboBox = new ComboBox[n][n];
		filled = false;
		setSize(900, 700);
		setLayout(new BorderLayout());
		MenuBar = menu;
		add(MenuBar, BorderLayout.NORTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initialize();
		createPanel();
		createButtons();
		createLabels();
	}

	private void initialize() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i < size - 1 && j < size - 1) {
					horsigns[i][j] = new JLabel();
					versigns[i][j] = new JLabel();
				}
				comboBox[i][j] = new ComboBox();
			}
		}
	}

	private void createLabels() {
		Images img = new Images();
		for (int i = 0, gy = 0; i < size - 1; i++, gy += 2) {
			for (int j = 0, gx = 1; j < size - 1; j++, gx += 2) {
				if (horSigns[i][j][j + 1] == true) {
					// u ovom ifu samo ubaci slicice umjesto glupih znakova ;)
					if (numbers[i][j] > numbers[i][j + 1]) {
						horsigns[i][j].setIcon(img.getRightImage());
						// System.out.println("horizontalni znak: ("+i+","+j+") > ("+i+","+(int)(j+1)+")");
					} else {
						horsigns[i][j].setIcon(img.getLeftImage());
					}
				}
				horsigns[i][j].setHorizontalAlignment(JLabel.CENTER);
				horsigns[i][j].setPreferredSize(new Dimension(40, 40));
				gbc.gridx = gx;
				gbc.gridy = gy;
				panel.add(horsigns[i][j], gbc);
			}
		}
		for (int i = 0, gx = 0; i < size - 1; i++, gx += 2) {
			for (int j = 0, gy = 1; j < size - 1; j++, gy += 2) {
				if (verSigns[i][j][j + 1] == true) {
					if (numbers[j][i] > numbers[j + 1][i]) {
						versigns[i][j].setIcon(img.getDownImage());
					} else {
						versigns[i][j].setIcon(img.getUpImage());
					}
				}
				versigns[i][j].setHorizontalAlignment(JLabel.CENTER);
				versigns[i][j].setPreferredSize(new Dimension(40, 40));
				gbc.gridx = gx;
				gbc.gridy = gy;
				panel.add(versigns[i][j], gbc);
			}
		}
	}

	public void Run() {
		this.setVisible(true);
	}

	private void createButtons() {
		// TODO Auto-generated method stub
		
		Random rn = new Random();
		int p = rn.nextInt(5) + 4;
		while (p-- > 0) {
			int tmp1 = rn.nextInt(size);
			int tmp2 = rn.nextInt(size);
			randNumbers[tmp1][tmp2] = numbers[tmp1][tmp2];
		}
		int prefSize = (700 - (size + 3) * 40) / size + 10;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				gbc.gridx = 2 * j;
				gbc.gridy = 2 * i;
				comboBox[i][j].setPreferredSize(new Dimension(prefSize,
						prefSize));
				//comboBox[i][j].setRenderer(dlcr);
				comboBox[i][j].setFont(new Font("Courier New", Font.ITALIC,
						prefSize / 3));
				if (randNumbers[i][j] != 0) {
					// System.out.println("na indeksu ("+i+","+j+") je: "+randNumbers[i][j]);
					comboBox[i][j].addItem(randNumbers[i][j]);
					//comboBox[i][j].disable();
					comboBox[i][j].setFont(new Font("Courier New", Font.BOLD,
							prefSize / 3));
				} else {
					comboBox[i][j].addItem("\n");
					for (int k = 1; k <= size; k++) {
						comboBox[i][j].addItem(k);
					}
				}
				//comboBox[i][j].setBorder(BorderFactory.createLineBorder(Color.blue,1));
				/*((JLabel) comboBox[i][j].getRenderer())
				.setHorizontalAlignment(JLabel.CENTER);*/
				//comboBox[i][j].setUI((ComboBoxUI) MyComboBoxUI.createUI(comboBox[i][j]));
				comboBox[i][j].setBorder(null);
				comboBox[i][j].setSelectedIndex(0);
				panel.add(comboBox[i][j], gbc);
				//spanel.revalidate();
			}
		}
	}

	private void createPanel() {
		panel = new Panel();
		this.add(panel, BorderLayout.CENTER);
	}

	public void Restart() {
		Clear();
	}

	private void Clear() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (randNumbers[i][j] != 0) {
					comboBox[i][j].setSelectedIndex(randNumbers[i][j]);
				} else {
					comboBox[i][j].setSelectedIndex(0);
				}
			}
		}
	}

	public boolean isOk(){
		ok = true;
		boolean is = false;
		boolean[][] isThereSame = new boolean[size][size];
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				comboBox[i][j].revalidate();
				if(randNumbers[i][j]!=0)continue;
				if("\n".equals(comboBox[i][j].getSelectedItem().toString())){
					is = true;
					comboBox[i][j].setBorder(null);
					continue;
				}
				boolean found = false;
				for(int k=0;k<size;k++){
					if(j==k)continue;
					try{
						if(Integer.parseInt(comboBox[i][j].getSelectedItem().toString()) == Integer.parseInt(comboBox[i][k].getSelectedItem().toString()) ){
							found = true;
							break;
						}
					}catch (Exception e){
					}
				}
				if(found){
					isThereSame[i][j]=true;
					ok = false;
					comboBox[i][j].setBorder(BorderFactory.createLineBorder(Color.RED,2));
				}
			}
		}
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				if(randNumbers[j][i]!=0)continue;
				if("".equals(comboBox[j][i].getSelectedItem().toString())){
					comboBox[j][i].setBorder(null);
					continue;
				}
				boolean found = false;
				for(int k=0;k<size;k++){
					if(j==k)continue;
					try{
						if(Integer.parseInt(comboBox[j][i].getSelectedItem().toString()) == Integer.parseInt(comboBox[k][i].getSelectedItem().toString()) ){
							found = true;
							break;
						}
					}catch (Exception e){
					}
				}
				if(found){
					ok = false;
					comboBox[j][i].setBorder(BorderFactory.createLineBorder(Color.RED,2));
				}else {
					if(comboBox[j][i].getBorder()!=null && isThereSame[j][i]==false){
						comboBox[j][i].setBorder(null);
					}
				}
			}
		}
		if(!is)filled = true;
		return ok;
	}
	public boolean isFilled(){
		return filled;
	}
	public void clear(){
		ok = false;
		filled = false;
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				if(randNumbers[i][j]==0){
					comboBox[i][j].setSelectedIndex(0);
				}
			}
		}
	}
	public void showSolution(){
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				if(randNumbers[i][j]==0){
					comboBox[i][j].setSelectedIndex(numbers[i][j]);
				}
			}
		}
	}
	public void revalidate(){
		panel.revalidate();
	}
}
