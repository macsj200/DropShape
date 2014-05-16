package dropshape;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.SwingUtilities;

public class BufferedCanvas extends BufferedImage{
	//BufferedCanvas

	private int[] pixels;
	//ArrayList of ShapeWrapper objects
	//Holds everything on the screen

	Graphics2D g2 = null;
	//Graphics2D object used for drawing primitives
	//This is the primary object, don't use directly for drawing
	//Instead clone and dispose of clone

	private Color background = null;
	//Background of canvas

	public BufferedCanvas(int width, int height, int imageType) {
		//Primary constructor
		//IDK what imageType is, it's required for BufferedImage

		super(width, height, imageType);
		//Call BufferedImage constructor

		pixels = ((DataBufferInt) getRaster().getDataBuffer()).getData();
		//Create the ArrayList to hold ShapeWrapper objects

		

		threadedRender();
		//Do initial rendering work
	}

	public void threadedRender(){
		//Wrapper, just invokes singleThreadRender with invokeLater

		SwingUtilities.invokeLater(new Runnable(){
			//Necessary framework for Swing concurrency
			public void run(){
				singleThreadRender();
				//Primary rendering logic
			}
		});
	}
	
	public int[] getPixels(){
		return pixels;
	}

	public void singleThreadRender(){
		//nothing, to be overriden
	}
	
	public void redrawSelf(){
		//do nothing
	}
}
