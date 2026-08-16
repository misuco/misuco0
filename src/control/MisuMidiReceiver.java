package control;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

public class MisuMidiReceiver implements Receiver {
	
	MisuProg mp;

	public MisuMidiReceiver(MisuProg mp) {
		super();
		this.mp = mp;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void send(MidiMessage message, long timeStamp) {
		byte[] m=message.getMessage();
//		System.out.print("received ts: "+timeStamp+" msg: ");
		for(int i=0;i<m.length;i++) {
			System.out.print(String.valueOf(m[i])+" ");
		}
//		System.out.println(" ");
		if(m[0]==-80 && m[1]==1) {
//		if(m[1]==57) {
//			System.out.println("----------------");
			mp.cc(m[2]);
		}
	}
}
