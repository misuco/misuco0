/*
 * MisuFrameScale.java
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
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiDevice.Info;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import model.MisuScale;
import control.CMisuWindow;
import control.CMisuWindowV;
import control.MisuMidi;
import control.MisuProg;

public class MisuFrameScale extends JFrame implements ActionListener {
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

	int onnote;
	int c;			// midi channel

	MisuScale scale;

	public MisuFrameScale(MisuScale scale) {
		super();
		
		Toolkit toolkit = java . awt . Toolkit . getDefaultToolkit ();
		Dimension ss = toolkit . getScreenSize ();
		int h=ss.height;
		int w=ss.width;
		
		int y1=h/10;
		int y2=h*6/10;
		int y3=h-y1-y2;

		int wx=w/5;

		onnote = -1;
		c = 1;
		
		this.scale = scale;
		mm = new MisuMidi(new MisuProg(w1));

		mosel = new JComboBox(MidiSystem.getMidiDeviceInfo());
		mosel.addActionListener(this);
//		mosel.addKeyListener(new CMisuAlphaKeys(this,scale));
		this.add(mosel);
		
//		this.addKeyListener(new cMisuAlphaKeys());

		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
		gd = gs[0];
		setVisible(true);
		setSize(300, 100);
		setTitle("midisurface");
		
		try {
			w1 = new MisuWindow(scale);
			w1.setSize(w, y2);
			w1.setLocation(0, y1);
			w1.setVisible(true);
			CMisuWindow cmw=new CMisuWindow(c,mm,scale,w1);
			w1.addMouseMotionListener(cmw);
			w1.addMouseListener(cmw);
			
			String[] cap={"1","2","3","4","5","6","7","8","9","10"};
			w2 = new MisuWindowV(this, scale, 10, cap);
			w2.setSize(wx, y3);
			w2.setLocation(0, y1+y2);
			w2.setVisible(true);
			w2.addMouseMotionListener(new CMisuWindowV(c,mm,scale,w2,this,CMisuWindowV.PROG));
			
			String[] cap2={"-4","-3","2","1","0","1","2","3","4","5"};
			w3 = new MisuWindowV(this, scale, 10, cap2);
			w3.setSize(wx, y3);
			w3.setLocation(wx, y1+y2);
			w3.setVisible(true);
			w3.addMouseMotionListener(new CMisuWindowV(c,mm,scale,w3,this,CMisuWindowV.OCT));
			
			String[] cap3={"c","c#","d","d#","e","f","f#","g","g#","a","a#","h","c"};
			w4 = new MisuWindowV(this, scale, 12, cap3);
			w4.setSize(wx, y3);
			w4.setLocation(wx*2, y1+y2);
			w4.setVisible(true);
			w4.addMouseMotionListener(new CMisuWindowV(c,mm,scale,w4,this,CMisuWindowV.PITCH));
			
			String[] cap4={"DUR", "MOLL", "PENTA", "HEXA", "HEPTA",
			"PENTA_DUR", "PENTA_MOL", "PENTA_KUOMI", "PENTA_IWATO", "PENTA_PELOG",
			"PENTA_HYOJO", "PENTA_CHINA", "PENTA_EGYPT"};
			w5 = new MisuWindowV(this, scale, 13, cap4);
			w5.setSize(wx, y3);
			w5.setLocation(wx*3, y1+y2);
			w5.setVisible(true);
			w5.addMouseMotionListener(new CMisuWindowV(c,mm,scale,w5,this,CMisuWindowV.SCALE));
			
			w6 = new MisuWindowV(this, scale, 10, cap);
			w6.setSize(wx, y3);
			w6.setLocation(wx*4, y1+y2);
			w6.setVisible(true);
			w6.addMouseMotionListener(new CMisuWindowV(c,mm,scale,w6,this,CMisuWindowV.RANGE));
			
//			gd.setFullScreenWindow(w);
		} finally {
//			gd.setFullScreenWindow(null);
		}


	}


	void saySomething(String eventDescription, MouseEvent e) {
		System.out
				.println(eventDescription + " (" + e.getX() + "," + e.getY()
						+ ")" + " detected on "
						+ e.getComponent().getClass().getName());
	}

	public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox) e.getSource();
		Info mo = (Info) cb.getSelectedItem();
		mm.initMidiOut(mo);
	}
	
	public void repaint() {
		super.repaint();
		w1.repaint();
		w2.repaint();
		w3.repaint();
		w4.repaint();
		w5.repaint();
		w6.repaint();
	}


}
