import java.net.URL;

import javax.swing.ImageIcon;


public class Images {
	
	private ImageIcon up,down,left,right;
	
	public Images(){
		URL imgURL = getClass().getResource("resource/up.png");
		up = new ImageIcon(imgURL);
		imgURL = getClass().getResource("resource/down.png");
		down = new ImageIcon(imgURL);
		imgURL = getClass().getResource("resource/left.png");
		left = new ImageIcon(imgURL);
		imgURL = getClass().getResource("resource/right.png");
		right = new ImageIcon(imgURL);
	}
	
	public ImageIcon getUpImage(){
		return up;
	}
	
	public ImageIcon getDownImage(){
		return down;
	}
	
	public ImageIcon getLeftImage(){
		return left;
	}
	
	public ImageIcon getRightImage(){
		return right;
	}
	
}
