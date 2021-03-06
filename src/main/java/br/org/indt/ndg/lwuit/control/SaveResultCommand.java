package br.org.indt.ndg.lwuit.control;

import br.org.indt.ndg.lwuit.model.ImageData;
import com.nokia.mid.appl.cmd.Local;
import com.sun.lwuit.Command;
import java.util.Vector;

/**
 *
 * @author mluz
 */
public class SaveResultCommand extends CommandControl {

    private static SaveResultCommand instance;
    private SaveResultsObserver m_observer = null;

    protected Command createCommand() {
        return new Command(Local.getText(Local.QTJ_CMD_SAVE));
    }

    public void setObserver( SaveResultsObserver observer ) {
        m_observer = observer;
    }

    protected void doAction(Object parameter) {
        PersistenceManager.getInstance().saveNdgResult( m_observer );
        m_observer = null;
    }

    public static SaveResultCommand getInstance() {
        if (instance == null)
            instance = new SaveResultCommand();
        return instance;
    }

}

