package dropshape;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ShapeImage extends BufferedImage{

	public ShapeImage(int width, int height, int imageType) {
		super(width, height, imageType);
	}
	
	public void drawCrap(ShapeWrapper s){
		Graphics2D g2d = (Graphics2D) getGraphics();
		
		g2d.clearRect(0, 0, getWidth(), getHeight());
		
		g2d.setColor(s.getColor());
		g2d.fill(s.getShape());
		
		g2d.dispose();
	}

}
