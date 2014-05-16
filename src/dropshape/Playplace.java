package dropshape;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class Playplace extends JFrame{
	private final CanvasPanel panel;
	
	public Playplace(CanvasPanel panel){
		this.panel = panel;
		
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initAndAddComponents();
		
		pack();
		
		setVisible(true);
	}
	
	public void initAndAddComponents(){
		getContentPane().add(panel);
		
	}
	
	public CanvasPanel getCanvasPanel(){
		return panel;
	}
}
