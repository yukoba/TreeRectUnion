public class TreeRectUnionUtils {
	public static void add(Node node, Zone2D zone) {
		if (node.isLeaf() && node.isCovered)
			return;
        if (!zone.intersects(node.zone))
            return;
		if (zone.contains(node.zone)) {
			node.isCovered = true;
            node.left = node.right = null;
		} else {
			if (node.zone.isSplitable()) {
				add(node.createLeft(), zone);
				add(node.createRight(), zone);
			}
		}
	}

    public static void remove(Node node, Zone2D zone) {
        if (node.isLeaf() && !node.isCovered)
            return;
        if (!zone.intersects(node.zone))
            return;
        if (zone.contains(node.zone)) {
            node.isCovered = false;
            node.left = node.right = null;
        } else {
            if (node.zone.isSplitable()) {
                remove(node.createLeft(), zone);
                remove(node.createRight(), zone);
            }
        }
    }

    /** zoneを完全に含んでいたらtrue。一部でも含まなかったらfalse */
	public static boolean contains(Node node, Zone2D zone) {
        if (node.isLeaf()) {
            return node.isCovered && zone.intersects(node.zone);
        } else {
            return (!zone.intersects(node.left.zone) || contains(node.left, zone)) &&
                    (!zone.intersects(node.right.zone) || contains(node.right, zone));
        }
	}
}
