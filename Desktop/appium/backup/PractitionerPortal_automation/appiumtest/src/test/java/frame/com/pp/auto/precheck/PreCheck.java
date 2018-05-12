package frame.com.pp.auto.precheck;


public class PreCheck {

	private static PreCheck instance = null;

	public static synchronized PreCheck getInstance() {
		if (instance == null) {
			instance = new PreCheck();
		}
		return instance;
	}
	
	public void preCheck(){
		new CheckConfig().check();
		new CheckNetwork().check();
	}
}
