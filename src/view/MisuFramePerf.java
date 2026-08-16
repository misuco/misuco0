/*
 * MisuFramePerf.java
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

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import model.MisuScale;
import control.CMisuAlphaKeys;
import control.CMisuWindow;
import control.MisuMidi;
import control.MisuProg;

public class MisuFramePerf extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3158210219035758030L;
	GraphicsDevice gd;
	MisuWindow w1;
	MisuWindowV w2;
	MisuWindowV w3;
	MisuWindowV w4;
	MisuWindowV w5;
	MisuWindowV w6;
	JComboBox mosel;

	MisuMidi mm;
	MisuProg mp;

	int onnote;
	int c; // midi channel

	MisuScale scale;

	public MisuFramePerf() {

		Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
		Dimension ss = toolkit.getScreenSize();
		int h = ss.height;
		int w = ss.width;

		// int y1=h/10;
		// int y2=h*6/10;
		// int y3=h-y1-y2;
		//
		// int wx=w/5;

		onnote = -1;
		c = 1;

		scale = new MisuScale(40, 2, MisuScale.MOLL);
		w1 = new MisuWindow(scale);
		mp = new MisuProg(w1);
		mm = new MisuMidi(mp);
		mp.setMm(mm);

		addKeyListener(new CMisuAlphaKeys(this, scale, mm, mp));

		CMisuWindow cmw = new CMisuWindow(c, mm, scale, w1);
		w1.addMouseMotionListener(cmw);
		w1.addMouseListener(cmw);
		w1.setSize(w, h);
		w1.setLocation(0, 0);
		w1.setVisible(true);

		add(w1);
		setSize(w, h);
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(true);
		setVisible(true);

	}

	// public void actionPerformed(ActionEvent e) {
	// JComboBox cb = (JComboBox) e.getSource();
	// Info mo = (Info) cb.getSelectedItem();
	// mm.initMidiOut(mo);
	// }

	public void repaint() {
		w1.repaint();
	}
}
