package kr.co.itcen.mysite.action.main;

import kr.co.itcen.web.mvc.ActionFactory;
import kr.co.itcen.web.mvc.Actionable;

public class MainActionFactory extends ActionFactory{

	@Override
	public Actionable getAction(String actionName) {
		// TODO Auto-generated method stub
		return new MainAction();
	}

}
