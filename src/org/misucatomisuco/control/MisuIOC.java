package org.misucatomisuco.control;

import java.util.ArrayList;

import org.misucatomisuco.model.MisuSeqGrid;
import org.misucatomisuco.view.IMisuPanelDrawer;
import org.misucatomisuco.view.MisuFrame;
import org.misucatomisuco.view.MisuPanel;
import org.misucatomisuco.view.MisuPanelDrawSeqGridBottom;
import org.misucatomisuco.view.MisuPanelString;

public class MisuIOC {

	public MisuIOC() {
		super();
		/**
		 * 
		 * 	 */
			
			int npans=1;
			MisuMidi out=new MisuMidi();
			MisuOutMulti outm= new MisuOutMulti();
			outm.add(out);
			
			MisuSeqGrid seqgrid[]=new MisuSeqGrid[npans];
			
			MisuFrame f=new MisuFrame();
			
				MisuProg mp=new MisuProg();
				
				int[] progCh=new int[npans];
				MisuPanel[] pans=new MisuPanel[npans];
				CMisuPanelString[] control=new CMisuPanelString[npans];
				for(int i=0;i<npans;i++) {
					progCh[i]=i;
					mp.setProgCh(progCh);
							seqgrid[i]=new MisuSeqGrid(2000);
							pans[i]=new MisuPanelString();
								control[i]=new CMisuPanelString(out,i);
								control[i].setSeqgrid(seqgrid[i]);
							pans[i].setControl(control[i]);
								ArrayList<IMisuPanelDrawer> drawers=new ArrayList<IMisuPanelDrawer>();
//								drawers.add(new MisuPanelDrawSeqGridOver());
								drawers.add(new MisuPanelDrawSeqGridBottom());
							pans[i].setDrawers(drawers);
							pans[i].setSeqgrid(seqgrid[i]);
				}
				mp.setPans(pans);
					
				mp.setMm(outm);
				
				MisuSeqPlayer[] seqplayers=new MisuSeqPlayer[npans];
				for(int i=0;i<npans;i++) {
					seqplayers[i]=new MisuSeqPlayer();
					seqplayers[i].setChannel(i);
					seqplayers[i].setGrid(seqgrid[i]);
						MisuSignalPlayer player=new MisuSignalPlayer();
						player.setOut(out);
						seqplayers[i].setPlayer(player);
				}
						
//				MisuHeartBeat h=new MisuHeartBeat(seqplayers, f);
//				MisuHeartBeat h=new MisuHeartBeatCreator(seqplayers, f);
				MisuHeartBeat h=new MisuHeartBeatMover(seqplayers, f);
					
					h.setHeartbeat(50);
					h.pans=pans;
					
					
				mp.setHeartbeat(h);
			
			f.setMp(mp);
			f.setPanels(pans);
	}

}
