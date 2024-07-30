package CPM;

public class LightOnCommand implements Command {
	private Light l;
	
	public LightOnCommand(Light l) {
		this.l = l;
	}
	@Override
	public void execute() {
		l.turnon();
	}
}
