package model;

public class MisuState {
	int scale;
	int base;
	int range;
	public MisuState(int scale, int base, int range) {
		super();
		this.scale = scale;
		this.base = base;
		this.range = range;
	}
	public int getScale() {
		return scale;
	}
	public int getBase() {
		return base;
	}
	public int getRange() {
		return range;
	}
	public void setBase(int b) {
		base=b;
	}
	public void setRange(int r) {
		range=r;
	}
	public void setScale(int s) {
		scale=s;
	}
}
