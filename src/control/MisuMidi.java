/*
 * MisuMidi.java
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

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Transmitter;
import javax.sound.midi.MidiDevice.Info;

public class MisuMidi {
	Transmitter trans;
	Receiver rcvr;
	Synthesizer synth;
	MidiDevice mout;
	MidiDevice minp;
	MisuMidiReceiver mire;
	int ch;
	
	boolean midiOutInit = false;
	boolean midiInInit = false;

	public MisuMidi(MisuProg m) {		
		Info mdi[] = MidiSystem.getMidiDeviceInfo();
		int in=0;
		int out=0;
		for(int i=0;i<mdi.length;i++) {
			System.out.println(mdi[i].getName());
			if(mdi[i].getName().contains("LoopBe Internal MIDI")) {
				System.out.println("****************");
				if(in==-1) {
					in=i;
				}else {
					out=i;
				}
			}
		}
		initMidiOut(mdi[out]);
		//initMidiIn(mdi[in],m);
	}
	
	public void noteOn(int c, int f, int v) {
		ch=c;
		ShortMessage m;
		f=f>127?127:f;
		f=f<0?0:f;
		m = new ShortMessage();
		try {
			m.setMessage(ShortMessage.NOTE_ON, c, f, v);
		} catch (InvalidMidiDataException e1) {
			e1.printStackTrace();
		}
		rcvr.send(m, -1);
	}

	public void noteOff(int c, int f, int v) {
		ShortMessage m;
		ch=c;
		f=f>127?127:f;
		f=f<0?0:f;
		m = new ShortMessage();
		try {
			m.setMessage(ShortMessage.NOTE_OFF, c, f, v);
		} catch (InvalidMidiDataException e1) {
			e1.printStackTrace();
		}
		rcvr.send(m, -1);
	}

	public void pitch(int c, int f) {
		ShortMessage m;
		ch=c;
		m = new ShortMessage();
		try {
			m.setMessage(ShortMessage.PITCH_BEND, c, f, f);
		} catch (InvalidMidiDataException e1) {
			e1.printStackTrace();
		}
		rcvr.send(m, -1);
	}

	public void pc(int c, int p) {
		ShortMessage m;
		ch=c;
		m = new ShortMessage();
		try {
			m.setMessage(ShortMessage.PROGRAM_CHANGE, c, p, 0);
		} catch (InvalidMidiDataException e1) {
			e1.printStackTrace();
		}
		rcvr.send(m, -1);
	}

	public void pc(int p) {
		pc(ch,p);
	}

	public void mod(int c, int p) {
		ShortMessage m;
		ch=c;
		m = new ShortMessage();
		p=p>127?127:p;
		p=p<0?0:p;
		try {
			m.setMessage(ShortMessage.CONTROL_CHANGE, c, 1, p);
		} catch (InvalidMidiDataException e1) {
			e1.printStackTrace();
		}
		rcvr.send(m, -1);
	}

	public void cc(int c, int cc, int p) {
		ShortMessage m;
		ch=c;
		m = new ShortMessage();
		p=p>127?127:p;
		p=p<0?0:p;
		try {
			m.setMessage(ShortMessage.CONTROL_CHANGE, c, cc, p);
		} catch (InvalidMidiDataException e1) {
			e1.printStackTrace();
		}
		rcvr.send(m, -1);
	}

	public void initMidiOut(Info mo) {
		try {
			if (midiOutInit) {
				// trans.close();
				// rcvr.close();
				mout.close();
			} else {
				midiOutInit = true;
			}
			mout = MidiSystem.getMidiDevice(mo);
			mout.open();
			rcvr = mout.getReceiver();

		} catch (MidiUnavailableException e1) {
			e1.printStackTrace();
		}
//		System.out.println("new midi out");

	}

	public void initMidiIn(Info mi, MisuProg mp) {
		try {
			if (midiInInit) {
				// trans.close();
				// rcvr.close();
				minp.close();
			} else {
				midiInInit = true;
			}
			minp = MidiSystem.getMidiDevice(mi);
			minp.open();
			trans = minp.getTransmitter();
			mire=new MisuMidiReceiver(mp);
			trans.setReceiver(mire);
		} catch (MidiUnavailableException e1) {
			e1.printStackTrace();
		}
//		System.out.println("new midi out");
	}
}
