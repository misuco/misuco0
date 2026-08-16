/*
 * CMisuWindowV.java
 *
 * Copyright (C) 2009-2010 Claudio Zopfi
 * 
 * Licensed under CC Attribution-Noncommercial-Share Alike 3.0 Germany
 * 
 * See the file license.txt which came with this distribution
 * or http://creativecommons.org/licenses/by-nc-sa/3.0/de/deed.en
 * or http://c1audio.com/by-nc-sa/
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 * 
 */
package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import model.MisuScale;
import view.MisuFrameScale;
import view.MisuWindowV;

public class CMisuWindowV implements MouseMotionListener {

	public static final int PITCH = 1;
	public static final int OCT = 2;
	public static final int PROG = 3;
	public static final int SCALE = 4;
	public static final int RANGE = 5;

	private MisuScale scale;
	private MisuWindowV w;
	private MisuFrameScale mf;

	private int val;
	private int ctlType;

	private int c = 1;
	private MisuMidi mm;

	public CMisuWindowV(int c, MisuMidi mm, MisuScale scale, MisuWindowV w,
			MisuFrameScale mf, int ctlType) {
		super();
		this.scale = scale;
		this.w = w;
		this.mf = mf;
		this.ctlType = ctlType;
		this.c = c;
		this.mm = mm;
		
		this.val=1;
		
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {

		int val = w.getN() * e.getY() / w.getHeight();

		if (val != this.val) {
			if (this.ctlType == PITCH) {
				scale.setBase(scale.getBase()+ (val-this.val));
				this.val = val;
				System.out.println("pitch " + val);
			} else if (this.ctlType == OCT) {
				scale.setBase(scale.getBase()+ (val-this.val) * 12);
				System.out.println("oct " + val);
				this.val = val;
			} else if (this.ctlType == PROG) {
				System.out.println("pc " + val);
				mm.pc(c, val);
				this.val = val;
			} else if (this.ctlType == SCALE) {
				System.out.println("scale " + val);
				scale.setScale(val);
				this.val = val;
			} else if (this.ctlType == RANGE) {
				System.out.println("range " + val);
				scale.setRange(val+1);
				this.val = val;
			}
		}
		w.setPix(e.getX(), e.getY());
		mf.repaint();
	}
}
