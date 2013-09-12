package cusis.platform;

import javax.swing.*;

// UI platform
public class UIPlatform implements IPlatform {

	@Override
	public void show(String msg){
		JOptionPane.showMessageDialog(null, msg);
	}
}