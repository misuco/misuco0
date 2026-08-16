/*
 * MisuWindowV.java
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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JWindow;

import model.MisuScale;

public class MisuWindowV extends JWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8995482059907456773L;
	ArrayList<Integer> x = new ArrayList<Integer>();
	ArrayList<Integer> y = new ArrayList<Integer>();

	private int x1;
	private int y1;

	private MisuScale scale;
	private int n;

	private String[] cap;

	public MisuWindowV(JFrame f, MisuScale s, int n) {
		super(f);
		this.n = n;
		this.cap = new String[0];
		scale = s;
	}

	public MisuWindowV(JFrame f, MisuScale s, int n, String[] c) {
		super(f);
		this.n = n;
		this.cap = c;
		scale = s;
	}

	public void paint(Graphics g1) {

		Image offscreen = createImage(getWidth(), getHeight());
		Graphics g = offscreen.getGraphics();

		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());

		for (int i = 0; i < n; i++) {
			int wbar = getHeight() / n;

			float sat = 0.5f;
			if (this.y1 >= i * wbar && this.y1 <= i * wbar + wbar) {
				sat = 1f;
			}
			g.setColor(Color.getHSBColor(((float) (i % 12) / 11), 1f, sat));
			g.fillRoundRect(0, i * wbar, getWidth(), wbar + 5, 5, 5);
			if (cap.length > 0) {
				Font big = new Font("Xirod", Font.PLAIN, 20);
				g.setFont(big);
				g.setColor(Color.black);
				g.drawString(cap[i], 1, 20 + i * wbar);
			}
		}

		g1.drawImage(offscreen, 0, 0, this);

	}

	public void setPix(int x, int y) {
		this.x1 = x;
		this.y1 = y;
	}

	public int getN() {
		return n;
	}

}
