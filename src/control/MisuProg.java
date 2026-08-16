package control;

import model.MisuScale;
import model.MisuSong;
import view.MisuWindow;

public class MisuProg {
	MisuMidi mm;
	MisuSong[] S;
	MisuScale[] SC;
	MisuWindow[] W;
	int cp = 0;		// current program
	int cr = 0;		// current riff
	int np = 10;

	public MisuProg(MisuWindow w) {
		super();
		SC = new MisuScale[1];
		S = new MisuSong[np];
		W=new MisuWindow[1];
		W[0]=w;
		SC[0]=W[0].getScale();
		for(int i=0;i<np;i++) {
			S[i]=new MisuSong(10);
		}
	}
	
	public void setMm(MisuMidi m) {
		mm=m;
	}

	public void cc(int v) {
		cr = v * (S[cp].getN()-1) / 127;
		updRiff();
	}
	public void updRiff(int i) {
		cr=i;
		updRiff();
	}
	
	public void updRiff() {
		for (int i = 0; i < SC.length; i++) {
			SC[i].setBase(S[cp].getST(cr).getBase());
			SC[i].setRange(S[cp].getST(cr).getRange());
			SC[i].setScale(S[cp].getST(cr).getScale());
			W[i].repaint();
		}
	}
	
	public void setBase(int b) {
		S[cp].getST(cr).setBase(b);
	}
	public void setRange(int r) {
		S[cp].getST(cr).setRange(r);
	}
	public void setScale(int s) {
		S[cp].getST(cr).setScale(s);
	}
	
	public void inc() {
		if(cp<np-1) {
			cp++;
			updRiff();
			mm.pc(cp);
		}
	}
	
	public void dec() {
		if(cp>0) {
			cp--;
			updRiff();
			mm.pc(cp);
		}
	}
}
