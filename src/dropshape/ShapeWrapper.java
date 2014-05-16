package dropshape;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.image.BufferedImage;

public class ShapeWrapper{
	private Shape s;
	private Color c;
	private ShapeImage image;
	
	private Point pos;
	
	public ShapeWrapper(Shape s, Color c){
		this.s = s;
		this.c = c;
		
		image = new ShapeImage((int) s.getBounds().getMaxX(), (int) s.getBounds().getMaxY(),BufferedImage.TYPE_INT_ARGB);
		
		drawCrap();
	}
	
	public void setPosition(Point pos){
		this.pos = pos;
	}
	
	public Point getPosition(){
		return pos;
	}
	
	public ShapeWrapper(Shape s){
		this(s, Color.black);
	}
	
	public Shape getShape(){
		return s;
	}
	
	public Color getColor(){
		return c;
	}
	
	public void setColor(Color c){
		this.c = c;
	}
	
	public void drawCrap(){
		image.drawCrap(this);
	}
	
	public BufferedImage getImage(){
		return image;
	}
}
