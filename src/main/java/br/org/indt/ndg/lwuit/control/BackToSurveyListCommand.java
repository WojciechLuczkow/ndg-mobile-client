/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.indt.ndg.lwuit.control;

import br.org.indt.ndg.lwuit.ui.SurveyList;
import br.org.indt.ndg.mobile.AppMIDlet;
import com.nokia.mid.appl.cmd.Local;
import com.sun.lwuit.Command;

/**
 *
 * @author damian.janicki
 */
public class BackToSurveyListCommand extends BackCommand{

    private static BackToSurveyListCommand instance = null;

    public static BackToSurveyListCommand getInstance(){
        if(instance == null){
            instance = new BackToSurveyListCommand();
        }
        return instance;
    }
    protected Command createCommand() {
        return new Command(Local.getText(Local.QTJ_CMD_BACK));
    }

    protected void doAction(Object parameter) {
        AppMIDlet.getInstance().setDisplayable( SurveyList.class );
    }

}
