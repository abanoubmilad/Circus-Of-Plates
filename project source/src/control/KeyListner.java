/*
 created on : 27/12/2014
 modified on: 27/12/2014
 
 Implemented by : Abanoub Milad Nassif
 e-mail: abanoubcs@gmail.com 
  
 */
package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class KeyListner implements KeyListener {
	private Controller controller = Controller.getInstance();
	private Set<Integer> list = new HashSet<Integer>();

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		list.add(e.getKeyCode());
		controller.movePlayers(list);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		list.remove(e.getKeyCode());

	}
}
