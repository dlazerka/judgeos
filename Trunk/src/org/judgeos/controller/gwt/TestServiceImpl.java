package org.judgeos.controller.gwt;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.judgeos.gwt.client.TestService;

public class TestServiceImpl extends RemoteServiceServlet implements TestService {
	public String getTest(int param) {
		return "Test" + param;
	}
}
