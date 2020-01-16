import java.awt.Color;
import java.awt.Paint;


public class ColorUtil {

	public static Paint getNodeColor(RandomTreeMapNode node) {
		switch (node.getCategory()) {
		case 1:
			return getColor(Color.BLUE, node.getSize()/node.getParentSize());
		case 2:
			return getColor(Color.ORANGE, node.getSize()/node.getParentSize());
		case 3:
			return getColor(Color.RED, node.getSize()/node.getParentSize());
		case 4:
			return getColor(Color.GREEN, node.getSize()/node.getParentSize());
		case 5:
			return getColor(Color.CYAN, node.getSize()/node.getParentSize());
		case 6:
			return getColor(Color.LIGHT_GRAY, node.getSize()/node.getParentSize());
		case 7:
			return getColor(Color.MAGENTA, node.getSize()/node.getParentSize());
		case 8:
			return getColor(Color.YELLOW, node.getSize()/node.getParentSize());
		case 9:
			return getColor(Color.PINK, node.getSize()/node.getParentSize());

		default:
//			System.err.println("ColorUtil: Default case error: " + node.getCategory());
			return Color.WHITE;
		}

	}

	private static Paint getColor(Color color, float fraction) {
		/* This call initializes an array of length 3 based on the existing color's properties.
		 * The values in the array are floats between 0 and 1. They are in the order HSB:
		 * The first cell is the Hue, the second cell is the Saturation, and the thid
		 * cell is the Brightness. 
		 */
		float[] hsb=Color.RGBtoHSB(color.getRed(),color.getGreen(),color.getBlue(),null);

		hsb[1] = fraction;
		// The Saturation lies in the second cell of the array, and here we increment it.

		hsb[1]=in_range(hsb[1]);
		//Must make sure this number is between 0 and 1

		// And return the newly created color.
		return Color.getHSBColor(hsb[0],hsb[1],hsb[2]);
	}



	/**
	 * Makes sure the integer passed in is between 0 and 255 and returns it.
	 * If the integer is outside the range, the returned value
	 * is either 0 or 255, whichever is closest. This is useful because
	 * it is common to represent an individual color component
	 * as an int with a value 0 to 255.
	 * This is especially useful when dealing with Color obects because
	 * if they are initialized with values outside that range, they throw
	 * an exception, and dealing with those takes some effort. In this case
	 * it is much easier to make sure the component values conform.
	 **/
	protected static int in_range(int i) {
		if(i<0)i=0;
		if(i>255)i=255;
		return i;
	}

	/**
	 * Returns a float between 0 and 1.
	 * Similar to in_range(int), but acts on a float and uses
	 * a different range. It is also common to describe color
	 * components as a float between 0 and 1.
	 **/
	protected static float in_range(float f) {
		if(f<0)f=0;
		if(f>1)f=1;
		return f;
	}
}
