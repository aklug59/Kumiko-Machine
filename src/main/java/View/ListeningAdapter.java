package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**This class serves to encapsulate ActionListener and KeyListner, which is used by GUI. Unused methods
 * stored here instead of in GUI.
 *
 */
public class ListeningAdapter implements ActionListener, KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void actionPerformed (ActionEvent e) {}

}
