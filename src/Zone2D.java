import jp.accelart.xshare.server.util.rectangleunion.Rect;

public class Zone2D extends Rect {
	private static final long serialVersionUID = -5642793542786042137L;
	private int depth;

	public Zone2D(int x, int y, int width, int height, int depth) {
		super(x, y, width, height);

		if (x < 0)
			throw new IllegalArgumentException("x = " + x);
		if (y < 0)
			throw new IllegalArgumentException("y = " + y);
		if (width <= 0)
			throw new IllegalArgumentException("width = " + width);
		if (height <= 0)
			throw new IllegalArgumentException("height = " + height);
		if (depth < 0)
			throw new IllegalArgumentException("depth = " + depth);

		this.depth = depth;
	}

	@Override
	public String toString() {
		return "Zone2D{" +
				"x=" + x +
				", y=" + y +
				", width=" + width +
				", height=" + height +
				", depth=" + depth +
				'}';
	}

	public Zone2D getLeftHalf() {
		if (width <= 1 && height <= 1)
			return null;

		boolean splitX = depth2splitX();

		if (splitX) {
			return new Zone2D(x, y, width / 2, height, depth + 1);
		} else {
			return new Zone2D(x, y, width, height / 2, depth + 1);
		}
	}

	public Zone2D getRightHalf() {
		if (width <= 1 && height <= 1)
			return null;

		boolean splitX = depth2splitX();

		if (splitX) {
			return new Zone2D(x + width / 2, y, width - width / 2, height, depth + 1);
		} else {
			return new Zone2D(x, y + height / 2, width, height - height / 2, depth + 1);
		}
	}

	private boolean depth2splitX() {
		boolean splitX = (depth % 2) == 0;
		if (splitX) {
			if (width == 1) splitX = false;
		} else {
			if (height == 1) splitX = true;
		}
		return splitX;
	}

	public boolean isSplitable() {
		return width >= 2 || height >= 2;
	}
}
