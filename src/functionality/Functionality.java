package functionality;

/**
 * This abstract class is used to abstract the method of CreateDevice DeleteDevice, ChangeState, ModeDevice's sendCommand() 
 */
public abstract class Functionality {
	abstract public void sendCommand();
	/**
	 * get user command directly from gui
	 * @param cmd user command
	 */
	abstract public void sendCommand(String cmd);
}
 