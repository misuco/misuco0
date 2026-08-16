/*
 * CMisuWindow.java
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
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import model.MisuScale;
import view.MisuWindow;

public class CMisuWindow implements MouseMotionListener, MouseListener {
	static long lastevent;
	int onnote;		//current note
	int c=1;		//channel
	int vsens=20;	//speed sensitivity
	MisuMidi mm;
	MisuScale scale;
	MisuWindow w;
	int px,py,vx,vy=0;
	
	public CMisuWindow(int c, MisuMidi mm, MisuScale scale, MisuWindow w) {
		super();
		this.c = c;
		this.mm = mm;
		this.scale = scale;
		this.w = w;
		this.onnote=-1;
		CMisuWindow.lastevent=System.nanoTime();
	}

	public void mouseMoved(MouseEvent e) {
//		ts();
		if (onnote >= 0) {
			mm.noteOff(c, onnote, 0);
			onnote = -1;
		}
		px=py=0;
	}
	
	public void mouseDragged(MouseEvent e) {
//		ts();
		
//		if(px>0) {
//			int vyn=e.getY()-py;
//			if(vyn*vy<0) {
//				System.out.println("y-dir-change new note");
//				mm.noteOff(c, onnote, 0);
//				
//				mm.cc(c, 1, Math.abs(vyn));
//				
//				mm.noteOn(c, onnote, 127);
//			}
//			vx=e.getX()-px;
//			vy=vyn;
//			System.out.println("V:"+vx+" "+vy);
//		}
		
		if(py>0) {
			int vxn=e.getX()-px;
			if(vxn*vx<0) {
				mm.noteOff(c, onnote, 0);
				mm.noteOn(c, onnote, 127);
//				System.out.println("x-dir-change new note");
			}
			int cv=vsens*Math.abs(vxn);
			cv=cv>127?127:cv;
			cv=127-cv;
			mm.cc(c, 1, cv);
//			System.out.println("send cv:"+cv);
			
			vy=e.getY()-py;
			vx=vxn;
//			System.out.println("V:"+vx+" "+vy);
		}
		
		px=e.getX();
		py=e.getY();
		
		int newnotei=e.getX() / w.getWbar();
		int newnote = onnote;
		if(newnotei<scale.getLenth()) {
			newnote=scale.getNote(newnotei);
		}
		if(newnote!=onnote) {
			if (onnote >= 0) {
				mm.noteOff(c, onnote, 0);
			}
			onnote = newnote;
			mm.noteOn(c, onnote, 127);
		}
//		int m = e.getY() / 2;
//		int p = 0;
//		int framewidth = w.getWidth()/scale.getLenth();
//		int p = (e.getX() % framewidth - framewidth/2) * 127 / framewidth + 64;
		int frameheight = w.getHeight();
		int p = (e.getY() % frameheight - frameheight/2) * 127 / frameheight + 64;
		mm.pitch(c, p);
//		mm.mod(c, m);

		w.setPix(e.getX(), e.getY());
		w.repaint();

	//	System.out.println("note " + onnote + " pitch " + p + " mod " + m);
	}

//	private void ts() {
//		long nt=System.nanoTime();
////		System.out.println("T:"+((nt-CMisuWindow.lastevent)/1000000));
//		CMisuWindow.lastevent=nt;
//	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseMoved(e);
		
	}

}
