import java.util.Arrays;
import java.util.Collection;

public class TreeRectUnion {
	private final Node root;
	private final int[] xary, yary;
    private final TIntIntHashMap x2idx = new TIntIntHashMap();
    private final TIntIntHashMap y2idx = new TIntIntHashMap();

    public TreeRectUnion(Collection<Rect> rects) {
        TIntHashSet xset = new TIntHashSet();
        TIntHashSet yset = new TIntHashSet();

        xset.add(0);
        yset.add(0);

        for (Rect rect : rects) {
            xset.add(rect.x);
            xset.add(rect.x + rect.width);
            yset.add(rect.y);
            yset.add(rect.y + rect.height);
        }

        xary = xset.toArray();
        yary = yset.toArray();

        Arrays.sort(xary);
        Arrays.sort(yary);

        for (int i = 0; i < xary.length; i++) {
            x2idx.put(xary[i], i);
        }
        for (int i = 0; i < yary.length; i++) {
            y2idx.put(yary[i], i);
        }

        Zone2D area = new Zone2D(0, 0, xary.length + 1, yary.length + 1, 0);
        root = new Node(area);
    }

    /** コンストラクタの引数の中の長方形を渡すこと */
    public void add(Rect rect) {
        int x = x2idx.get(rect.x);
        int y = y2idx.get(rect.y);
        int w = x2idx.get(rect.width + rect.x) - x;
        int h = y2idx.get(rect.height + rect.y) - y;
        Zone2D zone = new Zone2D(x, y, w, h, 0);
        TreeRectUnionUtils.add(root, zone);
	}

    /** コンストラクタの引数の中の長方形を渡すこと */
    public void remove(Rect rect) {
        int x = x2idx.get(rect.x);
        int y = y2idx.get(rect.y);
        int w = x2idx.get(rect.width + rect.x) - x;
        int h = y2idx.get(rect.height + rect.y) - y;
        Zone2D zone = new Zone2D(x, y, w, h, 0);
        TreeRectUnionUtils.remove(root, zone);
	}

	public boolean contains(Rect rect) {
		int x = findXidx(rect.x, true);
		int y = findYidx(rect.y, true);
		int w = findXidx(rect.right, false) - x;
		int h = findYidx(rect.bottom, false) - y;

//		System.out.println("yary = " + ArrayUtils.toString(yary));
//
//		System.out.println("x = " + x +
//				", y = " + y +
//				", w = " + w +
//				", h = " + h);

		// 幅０以下は含まれるとする
		if (w <= 0 || h <= 0) return true;

		Zone2D zone = new Zone2D(x, y, w, h, 0);
		return TreeRectUnionUtils.contains(root, zone);
	}

	private int findXidx(int x, boolean smaller) {
		int idx = Arrays.binarySearch(xary, x);
		if (idx >= 0) return idx;
		return Math.max(0, -(idx + 1) + (smaller ? -1 : 0));
	}

	private int findYidx(int y, boolean smaller) {
		int idx = Arrays.binarySearch(yary, y);
		if (idx >= 0) return idx;
		return Math.max(0, -(idx + 1) + (smaller ? -1 : 0));
	}
}
