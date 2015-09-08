import java.awt.Component;

import javax.swing.CellRendererPane;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalComboBoxButton;


public class ComboBox extends JComboBox{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ComboBox(){
		/*for(Component comp : this.getComponents()){
			if(comp instanceof AbstractButton){
				//comp.setVisible(false);
				comp.setPreferredSize(new Dimension(0,0));
			}
		}*/
		DefaultListCellRenderer dlcr = new DefaultListCellRenderer(); 
		dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		setRenderer(dlcr);
	}
	
	@Override
    public void updateUI() {
        super.updateUI();
        hideArrowButton();
    }
	
	private void hideArrowButton() {
        for(Component comp : this.getComponents()){
        	if(comp instanceof CellRendererPane)continue;
        	( (JComponent) comp ).setBorder(new EmptyBorder(0,0,0,0));
            if(comp instanceof MetalComboBoxButton){
                comp.setVisible(false);
                System.out.println(comp.getWidth());
                //comp.setSize(5, 5);
            }
        }
    }
	
	public void disable(){
		this.setPopupVisible(false);
	}
	
}
