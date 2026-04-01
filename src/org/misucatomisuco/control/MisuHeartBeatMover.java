package org.misucatomisuco.control;

import java.awt.event.MouseEvent;

import org.misucatomisuco.view.MisuFrame;
import org.misucatomisuco.view.MisuPanel;

public class MisuHeartBeatMover extends MisuHeartBeat {
	
	boolean mouseDown=false;
	int eventid=0;
	double vx=0;
	double vy=0;
	int x;
	int y;

	public MisuHeartBeatMover(MisuSeqPlayer[] p, MisuFrame f) {
		super(p, f);
	}
	
	public void advance() {
		if(pans!=null) {
			for(MisuPanel p:pans) {
				if(p.getWbar()>0) {
					double action=Math.random();
					if(action>0.9) {
						vx=vx*-1;
						vy=vy*-1;
					} else {
						vx+=Math.random();
						vy+=Math.random();
					}
					if(vx>20) {
						vx=0;
					}
					if(vy>20) {
						vy=0;
					}
					x+=vx;
					y+=vy;
					if(x>p.getWidth()) {
						x=p.getWidth();
					}
					if(x<0) {
						x=0;
					}
					if(y>p.getHeight()) {
						y=p.getHeight();
					}
					if(y<0) {
						y=0;
					}
					
					action=Math.random();
					MouseEvent e=new MouseEvent(p, eventid++, System.currentTimeMillis(), 0, x, y, 0, false);
					if(action>0.9) { 
						x=(int) (Math.random()*p.getWidth());
						y=(int) (Math.random()*p.getHeight());
					}
					else if(action>0.2) {
						if(mouseDown) {
							if(action>0.55) {
								mouseDown=false;
								p.getControl().mouseReleased(e);
							} else {
								p.getControl().mouseMoved(e);
							}
						} else {
							if(action>0.55) {
								mouseDown=true;
								p.getControl().mousePressed(e);
							}
						}
					}
				}
			}
		}
		for(MisuSeqPlayer p:player) {
			p.setResolution(300);
			p.advance();
		}
		frame.repaint();
	}
}
