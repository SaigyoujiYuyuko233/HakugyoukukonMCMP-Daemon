package hgk.saigyoujiyuyuko.mcmp.daemon.tools;

import hgk.saigyoujiyuyuko.mcmp.daemon.Var.Var;

public class GcThread implements Runnable{

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
				System.gc();
				//Var.logger.info("线程GC", Var.ERROR);
			} catch (InterruptedException e) {
				Var.logger.info("线程中断异常", Var.ERROR);
				e.printStackTrace();
			}
		}
	}

}
