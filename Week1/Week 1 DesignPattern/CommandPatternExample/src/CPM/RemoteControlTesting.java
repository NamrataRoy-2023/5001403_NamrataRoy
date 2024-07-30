package CPM;

public class RemoteControlTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Create the receiver
        Light roomLight = new Light();

        // Create commands
        Command lightOn = new LightOnCommand(roomLight);
        Command lightOff = new LightOffCommand(roomLight);

        // Create invoker
        RemoteControl remote = new RemoteControl();

        // Turn the light on
        remote.setCommand(lightOn);
        remote.button();

        // Turn the light off
        remote.setCommand(lightOff);
        remote.button();

	}

}
