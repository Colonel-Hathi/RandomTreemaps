import net.bouthier.treemapSwing.TMComputeSize;
import net.bouthier.treemapSwing.TMExceptionBadTMNodeKind;
import net.bouthier.treemapSwing.TMNode;


public class RandomTreeNodeSize implements TMComputeSize {

	@Override
	public boolean isCompatibleWith(TMNode node) {
		if (node instanceof RandomTreeMapNode) {
			return true;
		}
		return false;
	}

	@Override
	public float getSize(TMNode node) throws TMExceptionBadTMNodeKind {
		return ((RandomTreeMapNode)node).getSize();
	}

}
