import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class ImagePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7218720593394235437L;
	private String imageFileName;
	private String type;
	private boolean init;

	public ImagePanel(String type, String imageFile) {
		this.imageFileName = imageFile;
		this.type = type;
		this.init = true;
	}

	
	
	@Override
	protected void paintComponent(Graphics g) {
		if (init) {
			BufferedImage image = createImage(this);
			try {
				ImageIO.write(image, type, new File(imageFileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			init = false;
		}
		
		super.paintComponent(g);
	}

	
	
	public BufferedImage createImage(JPanel panel) {
	    int w = panel.getWidth();
	    int h = panel.getHeight();
	    BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g = bi.createGraphics();
	    panel.paint(g);
	    return bi;
	}

}
