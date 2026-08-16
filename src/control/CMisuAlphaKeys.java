/*
 * CMisuAlphaKeys.java
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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import model.MisuScale;

public class CMisuAlphaKeys implements KeyListener {

	private MisuScale scale;
	private JFrame mf;
	private MisuMidi mm;
	private MisuProg mp;

	public CMisuAlphaKeys(JFrame f, MisuScale s, MisuMidi m, MisuProg p) {
		super();
		mf = f;
		scale = s;
		mm=m;
		mp=p;
	}

	public CMisuAlphaKeys() {
		super();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int kc=e.getKeyCode();
		int kch=e.getKeyChar();
//		System.out.println("Keycode:"+kch);
		int initBase=24;
		/*
		Keycode:97
		Keycode:115
		Keycode:100
		Keycode:102
		Keycode:103
		Keycode:104
		Keycode:106
		Keycode:107
		Keycode:108
		Keycode:246
		Keycode:228
		Keycode:36*/
		switch (kch) {
		case 97:
			scale.setBase(initBase);
			mp.setBase(initBase);
			mf.repaint();
			break;
		case 115:
			initBase+=1;
			scale.setBase(initBase);
			mp.setBase(initBase);
			mf.repaint();
			break;
		case 100:
			initBase+=2;
			scale.setBase(initBase);
			mp.setBase(initBase);
			mf.repaint();
			break;
		case 102:
			initBase+=3;
			scale.setBase(initBase);
			mp.setBase(initBase);
			mf.repaint();
			break;
		case 103:
			initBase+=4;
			scale.setBase(initBase);
			mp.setBase(initBase);
			mf.repaint();
			break;
		case 104:
			initBase+=5;
			scale.setBase(initBase);
			mp.setBase(initBase);
			mf.repaint();
			break;
		case 106:
			initBase+=6;
			scale.setBase(initBase);
			mp.setBase(initBase);
			mf.repaint();
			break;
		case 107:
			initBase+=7;
			scale.setBase(initBase);
			mp.setBase(initBase);
			mf.repaint();
			break;
		case 108:
			initBase+=8;
			scale.setBase(initBase);
			mp.setBase(initBase);
			mf.repaint();
			break;
		case 246:
			initBase+=9;
			scale.setBase(initBase);
			mp.setBase(initBase);
			mf.repaint();
			break;
		case 228:
			initBase+=10;
			scale.setBase(initBase);
			mp.setBase(initBase);
			mf.repaint();
			break;
		case 36:
			initBase+=11;
			scale.setBase(initBase);
			mp.setBase(initBase);
			mf.repaint();
			break;
			
		}
		switch (kc) {
		
		case 37:	// cursor left
			break;
			
		case 38:	// cursor up
			mp.inc();
			break;
			
		case 39:	// cursor right
			break;
			
		case 40:	// cursor down
			mp.dec();
			break;
			
		case 112:	// F-Keys
		case 113:
		case 114:
		case 115:
		case 116:
		case 117:
		case 118:
		case 119:
		case 120:
		case 121:
		case 122:
		case 123:
			mm.pc(1, kc-112);
			mf.repaint();
			break;
		case 27:	// ESC-Key
			System.exit(0);
			break;
			
		case 49:	// Numbers
		case 50:
		case 51:
		case 52:
		case 53:
		case 54:
		case 55:
		case 56:
		case 57:
		case 58:
			scale.setScale(kc-48);
			mp.setScale(kc-48);
			mf.repaint();
			break;

			/*			qwertzu
			 * Keycode:81
			Keycode:87
			Keycode:69
			Keycode:82
			Keycode:84
			Keycode:90
			Keycode:85 */
		case 81:
			scale.setRange(1);
			mp.setRange(1);
			mf.repaint();
			break;
		case 87:
			scale.setRange(2);
			mp.setRange(2);
			mf.repaint();
			break;
		case 69:
			scale.setRange(3);
			mp.setRange(3);
			mf.repaint();
			break;
		case 82:
			scale.setRange(4);
			mp.setRange(4);
			mf.repaint();
			break;
		case 84:
			scale.setRange(5);
			mp.setRange(5);
			mf.repaint();
			break;
		case 90:
			scale.setRange(6);
			mp.setRange(6);
			mf.repaint();
			break;
		case 85:
			scale.setRange(7);
			mp.setRange(7);
			mf.repaint();
			break;
			/* iop
			Keycode:73
			Keycode:79
			Keycode:80
			Keycode:0
			Keycode:135
			Keycode:27*/
		case 73:
			scale.setRange(8);
			mp.setRange(8);
			mf.repaint();
			break;
		case 79:
			scale.setRange(9);
			mp.setRange(9);
			mf.repaint();
			break;
		case 80:
			scale.setRange(10);
			mp.setRange(10);
			mf.repaint();
			break;
		case 135:
			scale.setRange(11);
			mp.setRange(11);
			mf.repaint();
			break;
			
/*			
 *  		asdfghjkl
 *  
 * 			Keycode:65
			Keycode:83
			Keycode:68
			Keycode:70
			Keycode:71
			Keycode:72
			Keycode:74
			Keycode:75
			Keycode:76
			Keycode:515
			Keycode:10 
		case 65:
			scale.setBase(0);
			mf.repaint();
			break;
		case 83:
			scale.setBase(1);
			mf.repaint();
			break;
		case 68:
			scale.setBase(2);
			mf.repaint();
			break;
		case 70:
			scale.setBase(3);
			mf.repaint();
			break;
		case 71:
			scale.setBase(4);
			mf.repaint();
			break;
		case 72:
			scale.setBase(5);
			mf.repaint();
			break;
		case 74:
			scale.setBase(6);
			mf.repaint();
			break;
		case 75:
			scale.setBase(7);
			mf.repaint();
			break;
		case 515:
			scale.setBase(8);
			mf.repaint();
			break;
		case 10:
			scale.setBase(9);
			mf.repaint();
			break;
 * 		
 * yxcvbnm,.-
 * 
Keycode:89
Keycode:88
Keycode:67
Keycode:86
Keycode:66
Keycode:78
Keycode:77
Keycode:44
Keycode:46
Keycode:45
Keycode:27	
 */
		case 89:
			mp.updRiff(0);
			mf.repaint();
			break;
		case 88:
			mp.updRiff(1);
			mf.repaint();
			break;
		case 67:
			mp.updRiff(2);
			mf.repaint();
			break;
		case 86:
			mp.updRiff(3);
			mf.repaint();
			break;
		case 66:
			mp.updRiff(4);
			mf.repaint();
			break;
		case 78:
			mp.updRiff(5);
			mf.repaint();
			break;
		case 77:
			mp.updRiff(6);
			mf.repaint();
			break;
		case 44:
			mp.updRiff(7);
			mf.repaint();
			break;
		case 46:
			mp.updRiff(8);
			mf.repaint();
			break;
		case 45:
			mp.updRiff(9);
			mf.repaint();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
