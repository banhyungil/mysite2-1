package kr.co.itcen.mysite.action.board;

import kr.co.itcen.web.mvc.ActionFactory;
import kr.co.itcen.web.mvc.Actionable;

public class BoardActionFactory extends ActionFactory{

	@Override
	public Actionable getAction(String actionName) {
		Actionable action = null;
		
		if("writeform".equals(actionName)) {
			action = new WriteFormAction();
		} else if("insert".equals(actionName)) {
			action = new InsertAction();
		} else if("contentsform".equals(actionName)) {
			action = new ContentsFormAction();
		} else if("modifyform".equals(actionName)) {
			action = new ModifyFormAction();
		} else if("update".equals(actionName)) {
			action = new UpdateAction();
		} else if("delete".equals(actionName)) {
			action = new DeleteAction();
		} else {
			/* default(list) */
			action = new ListAction();
		}
		return action;
	}

}
