import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

public class Run {

	private static Menu MenuBar;
	private static Frame frame;
	private static Game game;
	private static int n;
	private static boolean solved = false;
	private static MainScreen startScreen;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		createStartScreen();
	}

	private static void createStartScreen() {
		// TODO Auto-generated method stub
		startScreen = new MainScreen("Futoshiki");
		startScreen.newGame.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int p = startScreen.newGame.getSelectedIndex();
				if(p>0){
					initialize(p+2);
					startScreen.newGame.setSelectedIndex(0);
					startScreen.dispose();
				}
			}
			
		});
		startScreen.Run();
	}

	private static void check() {
		// TODO Auto-generated method stub
		SwingWorker<Void, Void> work = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				while (true) {
					//frame.revalidate();
					if (frame.isOk()) {
						// check if it is filled
						if (frame.isFilled() && !solved) {
							JOptionPane.showMessageDialog(null,
									"You win!!! Congratulations");
							break;
						}
					}
				}
				return null;
			}

		};
		work.execute();
	}
	
	private static void initialize(int p){
		n = p;
		MenuBar = new Menu();
		newGame();
		MenuBar.restart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.clear();
				check();
			}

		});
		MenuBar.newG.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				newGame();
				check();
			}

		});
		MenuBar.exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}

		});
		MenuBar.solution
				.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						solved = true;
						frame.showSolution();
					}

				});
		MenuBar.back.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				createStartScreen();
				frame.dispose();
			}
			
		});
		check();
	}

	private static void newGame() {
		game = new Game(n);
		frame = new Frame(n, game.getVerSigns(), game.getHorSigns(),
				game.getFilledArray(), MenuBar);
		frame.Run();
	}

}
