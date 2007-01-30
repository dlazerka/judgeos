package org.judgeos.controller;

import org.apache.struts.action.ActionForward;

import javax.servlet.http.HttpSession;
import java.util.Stack;

public class MustReturnStackUtil {
	private static final String MUST_RETURN_STACK_ATTRIBUTE_NAME = MustReturnStackUtil.class.getName() + ".mustReturnStack";

	public static ActionForward push(ActionForward actionForward, HttpSession httpSession) {
		Stack<ActionForward> stack = (Stack<ActionForward>) httpSession.getAttribute(MUST_RETURN_STACK_ATTRIBUTE_NAME);
		if (stack == null) {
			stack = new Stack<ActionForward>();
			httpSession.setAttribute(MUST_RETURN_STACK_ATTRIBUTE_NAME, stack);
		}

		return stack.push(actionForward);
	}

	public static ActionForward pop(HttpSession httpSession) {
		Stack<ActionForward> stack = (Stack<ActionForward>) httpSession.getAttribute(MUST_RETURN_STACK_ATTRIBUTE_NAME);
		if (stack == null) {
			return null;
		}

		return stack.pop();
	}

	public static boolean empty(HttpSession httpSession) {
		Stack<ActionForward> stack = (Stack<ActionForward>) httpSession.getAttribute(MUST_RETURN_STACK_ATTRIBUTE_NAME);
		return stack == null || stack.empty();
	}
}
