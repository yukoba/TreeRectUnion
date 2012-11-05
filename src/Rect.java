import java.io.Serializable;

/**
 * {@link java.awt.Rectangle} の高速版。
 * <p/>
 * 制約条件
 * <ol>
 *     <li>width > 0 かつ height > 0 であること (x < 0, y < 0 は OK)</li>
 *     <li>不変オブジェクト</li>
 *     <li>x + width, y + width が int で表現できること</li>
 * </ol>
 * <p/>
 * 面積0の長方形を表現できなくなるので、その時は null を使います。
 */
public class Rect implements Serializable {
	private static final long serialVersionUID = 1965716073245580816L;
	//java.awt.Rectangle rect;
	public final int x, y, width, height, right, bottom;
	private final int hashCode;

	public Rect() {
		this(0, 0, 0, 0);
	}

	public Rect(int width, int height) {
		this(0, 0, width, height);
	}

	public Rect(int x, int y, int width, int height) {
		if (width <= 0)
			throw new IllegalArgumentException("width <= 0");
		if (height <= 0)
			throw new IllegalArgumentException("height <= 0");

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.right = x + width;
		this.bottom = y + height;

		int hashCode = x;
		hashCode = 31 * hashCode + y;
		hashCode = 31 * hashCode + width;
		hashCode = 31 * hashCode + height;
		this.hashCode = hashCode;
	}

	public Rect(Rect r) {
		this(r.x, r.y, r.width, r.height);
	}

	public boolean contains(Rect r) {
		return r != null && contains(r.x, r.y, r.right, r.bottom);
	}

	public boolean contains(int X, int Y, int RIGHT, int BOTTOM) {
		return X >= x && Y >= y && RIGHT <= right && BOTTOM <= bottom;
	}

	public boolean intersects(Rect r) {
		return r != null && r.right > x && r.bottom > y && right > r.x && bottom > r.y;
	}

	public Rect intersection(Rect r) {
		int x2 = Math.max(this.x, r.x);
		int y2 = Math.max(this.y, r.y);
		int r2 = Math.min(this.right, r.right);
		int b2 = Math.min(this.bottom, r.bottom);

		int w2 = r2 - x2;
		int h2 = b2 - y2;

		if (w2 <= 0 || h2 <= 0) return null;

		return new Rect(x2, y2, w2, h2);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		// 高速化のため null チェック、クラスチェックの省略
//		if (o == null || getClass() != o.getClass()) return false;

		Rect that = (Rect) o;

		return hashCode == that.hashCode &&
				x == that.x && y == that.y &&
				height == that.height && width == that.width;
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	@Override public String toString() {
		return "{" +
				"x=" + x +
				", y=" + y +
				", width=" + width +
				", height=" + height +
				'}';
	}
}
