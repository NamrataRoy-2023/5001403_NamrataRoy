package CPM;

public class RemoteControl {
	private Command c;
	
	public void setCommand(Command c) {
		this.c = c;
	}
	
	public void button() {
		c.execute();
	}
}
