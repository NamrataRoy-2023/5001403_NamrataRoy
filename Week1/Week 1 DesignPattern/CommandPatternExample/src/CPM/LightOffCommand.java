package CPM;

public class LightOffCommand implements Command {
	private Light l;
	
	public LightOffCommand(Light l) {
		this.l = l;
	}
	
	@Override
	public void execute() {
		l.turnoff();
	}

}
