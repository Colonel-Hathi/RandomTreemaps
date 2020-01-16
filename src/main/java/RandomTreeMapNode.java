import java.util.Enumeration;
import java.util.Random;
import java.util.Vector;

import net.bouthier.treemapSwing.TMNode;
import net.bouthier.treemapSwing.TMUpdater;


public class RandomTreeMapNode implements TMNode {
	private static final int MAX_CHILDS = 4;
	private Vector<RandomTreeMapNode> children;
	private float size;
	private float parentSize;
	private int category;
	private int level;

	public RandomTreeMapNode(Random rnd, float parentSize, float initialSize, int category, int level) {
		children = new Vector<RandomTreeMapNode>();

		this.size = initialSize;
		this.parentSize = parentSize;
		this.level = level;
		this.category = category;
		
		if (level <= MAX_CHILDS) {
			int childNr = rnd.nextInt(MAX_CHILDS);
			if (level == 0 && childNr == 0) {
				while (childNr == 0) {
					childNr = rnd.nextInt(MAX_CHILDS);
				}
			}

			for (int i = 1; i <= childNr; i++) {
				if (i == childNr) {
					if (level == 0) {
						children.add(new RandomTreeMapNode(rnd, size, initialSize, i, level+1));
					} else {
						children.add(new RandomTreeMapNode(rnd, size, initialSize, category, level+1));
					}
					return;
				}
				float childSize = initialSize * rnd.nextFloat();
				
				if (level == 0) {
					children.add(new RandomTreeMapNode(rnd, size, initialSize, i, level+1));
				} else {
					children.add(new RandomTreeMapNode(rnd, size, childSize, category, level+1));
				}
				initialSize -= childSize;
			}
		}
	}
	
	@Override
	public Enumeration children() {
		return children.elements();
	}

	@Override
	public boolean isLeaf() {
		if (children.size() == 0) {
			return true;
		} else{
			return false;
		}
	}

	@Override
	public void setUpdater(TMUpdater updater) {
		//nothing.
	}

	public float getSize() {
		return size;
	}

	
	public float getParentSize() {
		return parentSize;
	}
	
	public int getCategory() {
		return category;
	}
	
	
	public String getTitle() {
		return "";
//		return "Level: " + level + ", Cat: " + getCategory();// + ", Size: " +getSize();
	}

}
