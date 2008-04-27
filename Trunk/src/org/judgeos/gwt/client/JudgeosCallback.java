package org.judgeos.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;

public abstract class JudgeosCallback implements AsyncCallback {
	public void onFailure(Throwable caught) {
		String msg;
		if (caught instanceof InvocationException) {
			msg = ControlPanel.messages.errorsFailedReachRPC();
		} else {
			msg = ControlPanel.messages.errorsFailedExecRPC();
		}
		msg += ": \n" + GWT.getTypeName(caught);
		ControlPanel.showMessage(msg);

		PopupPanel popup = new PopupPanel(true);
		popup.setWidget(new HTML(caught.getMessage()));
		popup.center();
	}


	public void onSuccess(Object result) {
		ControlPanel.hideFetchingData();
	}
}
