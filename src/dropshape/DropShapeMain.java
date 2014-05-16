package dropshape;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

public class DropShapeMain {
	private Playplace p;
	private BufferedImage image;
	private ArrayList<ShapeWrapper> shapes;
	private boolean running;

	public DropShapeMain(Playplace p){
		this.p = p;
		image = p.getCanvasPanel().getBufferedImage();

		shapes = ((DropShapeCanvas) p.getCanvasPanel().getBufferedCanvas()).getShapes();
		running = false;
	}

	public Playplace getPlayplace(){
		return p;
	}

	public BufferedImage getImage(){
		return image;
	}

	public ArrayList<ShapeWrapper> getShapes(){
		return shapes;
	}

	public void shiftShapes(){
		Rectangle2D bounds = null;

		for(ShapeWrapper s : shapes){
			s.setPosition(new Point((int) (s.getPosition().getX()), (int) (s.getPosition().getY() + 1)));
		}
	}

	public void startGameLoop(){
		ShapeWrapper tester = new ShapeWrapper(new Rectangle2D.Double(0,0,50,60));

		tester.setPosition(new Point(50,50));

		addShape(tester);
		running = true;
		(new Thread(){
			public void run(){
				Point absolutePos;

				Point pos;

				ShapeWrapper offender = null;

				ShapeWrapper oldOffender = null;

				boolean insideShape = false;

				boolean somethingChanged = false;

				while(running){
					//TODO set absolutePos to mouse coordinates
					
					absolutePos = null;

					pos = new Point((int) (absolutePos.getX() - p.getCanvasPanel().getLocationOnScreen().getX()), 
							(int) (absolutePos.getY() - p.getCanvasPanel().getLocationOnScreen().getY()));


					oldOffender = offender;

					offender = intersections(pos);

					if(offender != null && !insideShape){
						insideShape = true;

						offender.setColor(Color.red);

						offender.drawCrap();
						somethingChanged = true;
					}

					if(offender == null){
						insideShape = false;
					}

					if(offender == null && oldOffender != null){
						oldOffender.setColor(Color.black);

						oldOffender.drawCrap();
						somethingChanged = true;
					}

					//somethingChanged = true;
					//TODO change logic to draw every ten seconds or something


					if(System.currentTimeMillis() % 100 == 0){
						shiftShapes();
						somethingChanged = true;
					}
					

					if(somethingChanged){
						redrawEverything();
					}
					
					somethingChanged = false;
				}
			}
		}).start();
	}

	public void addShape(ShapeWrapper s){
		shapes.add(s);

		redrawEverything();
	}

	public void redrawEverything(){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				((DropShapeCanvas) image).threadedRender();
				p.getCanvasPanel().rerender();
			}
		});
	}

	public ShapeWrapper intersections(Point pos){
		ShapeWrapper offender = null;
		Point temppos = null;

		if(pos == null){
			return null;
		}

		for(ShapeWrapper s : shapes){
			temppos = new Point((int) (pos.getX() - s.getPosition().getX()), (int) (pos.getY() - s.getPosition().getY()));

			//System.out.println(temppos);

			if(s.getShape().contains(temppos)){			
				offender = s;
				return offender;
			}
		}

		return null;
	}

	public void stopGameLoop(){
		running = false;
	}

	public static void main(String[] args){
		DropShapeCanvas canvas = new DropShapeCanvas(600,600, BufferedImage.TYPE_INT_ARGB);
		CanvasPanel panel = new CanvasPanel(600,600, canvas);

		DropShapeMain avoid = new DropShapeMain(new Playplace(panel));

		avoid.startGameLoop();

		avoid.redrawEverything();
	}
}
