package dropshape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ArrayList;

public class DropShapeCanvas extends BufferedCanvas{
	private ArrayList<ShapeWrapper> shapes;

	public DropShapeCanvas(int width, int height,
			int imageType) {
		super(width, height, imageType);
		
		shapes = new ArrayList<ShapeWrapper>();
	}
	
	
	public ArrayList<ShapeWrapper> getShapes(){
		return shapes;
	}
	
	@Override
	public void singleThreadRender(){
		Graphics2D g2d = (Graphics2D) getGraphics();
		
		g2d.setBackground(Color.WHITE);
		
		g2d.clearRect(0, 0, getWidth(), getHeight());
		
		g2d.setColor(Color.black);
		
		for(ShapeWrapper s : shapes){
			g2d.drawImage(s.getImage(), (int) s.getPosition().getX(), (int) s.getPosition().getY(), null);
		}
		
		g2d.dispose();
	}
}
