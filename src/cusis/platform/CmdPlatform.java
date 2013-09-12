package cusis.platform;

// command prompt platform
public class CmdPlatform implements IPlatform {
	// Constructor
	public CmdPlatform(){
	}
	
	@Override
	public void show(String msg){
		System.out.println(msg);
	}
}