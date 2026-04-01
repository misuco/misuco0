package org.misucatomisuco.control;

import java.awt.event.MouseEvent;

import org.misucatomisuco.view.MisuFrame;
import org.misucatomisuco.view.MisuPanel;

// creates random touch events...
public class MisuHeartBeatCreator extends MisuHeartBeat {
	
	boolean mouseDown=false;
	int eventid=0;

	public MisuHeartBeatCreator(MisuSeqPlayer[] p, MisuFrame f) {
		super(p, f);
	}
	
	public void advance() {
		if(pans!=null) {
			for(MisuPanel p:pans) {
				if(p.getWbar()>0) {
					int x=(int) (Math.random()*p.getWidth());
					int y=(int) (Math.random()*p.getHeight());
					double action=Math.random();
					MouseEvent e=new MouseEvent(p, eventid++, System.currentTimeMillis(), 0, x, y, 0, false);
					if(action>0.5) {
						if(mouseDown) {
							if(action>0.75) {
								mouseDown=false;
								p.getControl().mouseReleased(e);
							} else {
								p.getControl().mouseMoved(e);
							}
						} else {
							if(action>0.75) {
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
