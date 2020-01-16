import java.awt.Color;
import java.awt.Paint;

import net.bouthier.treemapSwing.TMComputeDraw;
import net.bouthier.treemapSwing.TMExceptionBadTMNodeKind;
import net.bouthier.treemapSwing.TMNode;
import net.bouthier.treemapSwing.TMNodeAdapter;



public class RandomTreeDraw implements TMComputeDraw {

	@Override
	public boolean isCompatibleWith(TMNode node) {
		if (node instanceof RandomTreeMapNode) {
			return true;
		}
		return false;
	}

	@Override
	public Paint getFilling(TMNodeAdapter nodeAdapter)
			throws TMExceptionBadTMNodeKind {
		RandomTreeMapNode node = (RandomTreeMapNode) nodeAdapter.getNode();
		return ColorUtil.getNodeColor(node);
	}

	@Override
	public String getTooltip(TMNodeAdapter nodeAdapter)
			throws TMExceptionBadTMNodeKind {
		RandomTreeMapNode node = (RandomTreeMapNode) nodeAdapter.getNode();
		return node.getTitle();
	}

	@Override
	public String getTitle(TMNodeAdapter nodeAdapter)
			throws TMExceptionBadTMNodeKind {
		RandomTreeMapNode node = (RandomTreeMapNode) nodeAdapter.getNode();
		return node.getTitle();
	}

	@Override
	public Paint getTitleColor(TMNodeAdapter nodeAdapter)
			throws TMExceptionBadTMNodeKind {
		return Color.BLACK;
	}

}
