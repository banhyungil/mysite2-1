package kr.co.itcen.web.mvc;

public abstract class ActionFactory {
	public abstract Actionable getAction(String actionName);
}
