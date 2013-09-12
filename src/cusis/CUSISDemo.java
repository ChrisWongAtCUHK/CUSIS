package cusis;

import cusis.platform.*;

public class CUSISDemo {
	public static void main(String[] args){
		IPlatform cusis = new CmdPlatform();
		cusis.show("Kill us!!");
	}
}