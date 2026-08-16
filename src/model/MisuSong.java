package model;


public class MisuSong {
	MisuState[] ST;
	int n;
	public MisuSong(int n) {
		super();
		this.n = n;
		ST=new MisuState[n];
		for(int i=0;i<n;i++) {
			ST[i]=new MisuState((int)(Math.random()*10),24+(int)(Math.random()*48),1+(int)(Math.random()*6));
		}
	}
	public MisuState getST(int i) {
		return ST[i];
	}
	public int getN() {
		return n;
	}
}
