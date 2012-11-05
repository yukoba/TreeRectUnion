public class Node {
    // かならず left も right も両方設定すること。null にするなら両方null。
	public Node left, right;
	public boolean isCovered;
	public final Zone2D zone;

	public Node(Zone2D zone) {
//		if (zone == null)
//			throw new NullPointerException("zone = null");
		this.zone = zone;
	}

	public Node createLeft() {
		if (left == null) {
			left = new Node(zone.getLeftHalf());
		}
		return left;
	}

	public Node createRight() {
		if (right == null) {
			right = new Node(zone.getRightHalf());
		}
		return right;
	}

    public boolean isLeaf() {
        return left == null;
//        return left == null && right == null;
    }

	@Override
	public String toString() {
		return "Tree{" +
				"isCovered=" + isCovered +
				", zone=" + zone +
				", left=" + left +
				", right=" + right +
				'}';
	}
}
