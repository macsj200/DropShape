package dropshape;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CanvasPanel extends JPanel{
	private BufferedImage bufferedCanvas = null;
	//Use a buffered image to render stuff
	
	public static final int defaultWidth = 500;
	public static final int defaultHeight = 500;
	
	private int width;
	private int height;

	CanvasPanel(){
		this(defaultWidth, defaultHeight);
	}
	
	CanvasPanel(int width, int height){
		this.width = width;
		this.height = height;
		
		setPreferredSize(new Dimension(width, height));
		
		bufferedCanvas = new BufferedCanvas(width, 
				height, BufferedImage.TYPE_INT_ARGB);
		//Make a new BufferedImage for to render stuff
	}
	
	CanvasPanel(int width, int height, BufferedImage image){
		this.width = width;
		this.height = height;
		
		setPreferredSize(new Dimension(width, height));
		
		bufferedCanvas = image;
		//Make a new BufferedImage for to render stuff
	}
	
	public void rerender(){
		//Redraw everything
		revalidate();
		repaint();
	}

	public void paintComponent(Graphics g){
		//Override paintComponent() method to paint BufferedCanvas
		
		super.paintComponent(g);
		//Let the normal paintComponent() method do its thing
		
		Graphics2D g2d = (Graphics2D) g.create();
		//Make a new context
		
		g2d.drawImage(bufferedCanvas, 0, 0, null);
		//Write the image
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.dispose();
		//We're all done with that
	}
	
	public BufferedImage getBufferedImage(){
		//Get a reference to the BufferedCanvas object behind this panel
		
		return bufferedCanvas;
	}
	
	public BufferedCanvas getBufferedCanvas(){
		return (BufferedCanvas) bufferedCanvas;
	}
}