import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class Menu extends JMenuBar{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenu game,about;
	public JMenuItem newG, restart, back, exit, solution;
	
	public Menu(){
		game = new JMenu("Game");
		createMenuGame();
		this.add(game);
		about = new JMenu("About");
		this.add(about);
	}

	private void createMenuGame() {
		// TODO Auto-generated method stub
		newG = new JMenuItem("New Game");
		restart = new JMenuItem("Restart");
		solution = new JMenuItem("Display solution");
		game.add(newG);
		game.add(restart);
		game.add(solution);
		game.addSeparator();
		back = new JMenuItem("Back to main menu");
		game.add(back);
		game.addSeparator();
		exit = new JMenuItem("Exit");
		game.add(exit);	
	}
	
	
}
