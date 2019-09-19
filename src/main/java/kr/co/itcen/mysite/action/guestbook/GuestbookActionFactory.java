package kr.co.itcen.mysite.action.guestbook;

import kr.co.itcen.web.mvc.ActionFactory;
import kr.co.itcen.web.mvc.Actionable;
import kr.co.itcen.mysite.action.guestbook.*;

public class GuestbookActionFactory extends ActionFactory{

	@Override
	public Actionable getAction(String actionName) {
		// TODO Auto-generated method stub
		Actionable action = null;
		if("insert".equals(actionName)) {	//방명록 남기기
			action = new AddAction();
		} else if("deleteform".equals(actionName)) {
			action = new DeleteFormAction();
		} else if("delete".equals(actionName)) {
			action = new DeleteAction();
		} else {
			action = new ListAction();
		}
		return action;
	}

}
