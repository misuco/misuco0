/*
 * MisuWindow.java
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
package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import model.MisuScale;

public class MisuWindow extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8995482059907456773L;
	int x1, y1, wbar;
	MisuScale scale;

	public MisuWindow(MisuScale s) {
		super();
		scale = s;
		System.out.println("Misu Window created");
	}

	public void paint(Graphics g1) {
		Image offscreen = createImage(getWidth(), getHeight());
		Graphics g = offscreen.getGraphics();

		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());

		wbar = getWidth() / scale.getLenth();
		int t=0;
		Color ct=Color.WHITE;
		for (int i = 0; i < scale.getLenth(); i++) {

			float sat = 0.5f;
			if (this.x1 >= i * wbar && this.x1 <= i * wbar + wbar) {
				sat = 1f;
				t=scale.getNote(i);
				ct=Color.getHSBColor(
						((float) (scale.getNote(i) % 12) / 11), 1f, sat);
			}
			g.setColor(Color.getHSBColor(
					((float) (scale.getNote(i) % 12) / 11), 1f, sat));
			g.fillRoundRect(i * wbar, 0, wbar+15, getHeight(), 15, 15);
		}
		g.drawOval(x1 - 3, y1 - 3, 6, 6);
		
		g.setColor(Color.WHITE);
		
		float p=getHeight()/8;
		int s=getHeight()/2;
		for(float i=0;i<32;i+=0.25) {
			int h=(int)(Math.sqrt(i)*p);
			g.drawLine(0, s+h, getWidth(), s+h);
			g.drawLine(0, s-h, getWidth(), s-h);
		}
		g.setColor(ct);
		double t1=Math.pow(2, (double)(128-t)/12);
		long n=Math.round(getWidth()/t1);
		long x,x1=0;
		int a1=0;
		for(long i=0;i<n;i++) {
			int a=0;
			switch ((int)i%4) {
			case 1:
				a=1;
				break;
			case 3:
				a=-1;
				break;
			}
			x=Math.round(i*t1);
			g.drawLine((int)x, s+s*a, (int)x1, s+s*a1);
			x1=x;
			a1=a;
		}
		g1.drawImage(offscreen, 0, 0, this);
	}

	public void setPix(int x, int y) {
		x1 = x;
		y1 = y;
	}

	public int getWbar() {
		return wbar;
	}

	public MisuScale getScale() {
		return scale;
	}
}