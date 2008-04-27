package org.judgeos.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;


public class ControlPanel implements EntryPoint {
	protected final static ControlPanelMessages messages = (ControlPanelMessages) GWT.create(ControlPanelMessages.class);
	private static Message message;
	private static JudgeosServiceAsync service;

	private final static String ROOT_SLOT_ID = "body";

	public void onModuleLoad() {
		RootPanel slot = RootPanel.get(ROOT_SLOT_ID);
		if (slot == null) {
			RootPanel.get().add(new Label(messages.errorsRootSlotNotFound(ROOT_SLOT_ID)));
			return;
		}

		service = (JudgeosServiceAsync) GWT.create(JudgeosService.class);

		// todo: different servlet paths for shell and production modes
		((ServiceDefTarget) service).setServiceEntryPoint("http://localhost:8080/judgeos/gwt/JudgeosService");
//		((ServiceDefTarget) service).setServiceEntryPoint(GWT.getModuleBaseURL() + "../../gwt/JudgeosService");

		message = new Message();
		slot.add(message);

		//slot.add(new ControlPanelWidget());
		PopupPanel popup = new PopupPanel(true);
		HorizontalPanel w = new HorizontalPanel();
		w.add();
		popup.setWidget(w);
		showMessage("Fetching data...");
	}

	/**
	 * Shows global message (greetings, tips etc).
	 * @param text to show
	 */
	public static void showMessage(String text) {
		message.setText(text);
	}

	/**
	 * Service to interact with.
	 * @return service
	 */
	public static JudgeosServiceAsync getService() {
		return service;
	}

	//	private Widget createScoreTablePanel() {
//		//final Grid results = new Grid(3,3);
//		//		results.setText(0, 0, "Commands");
//		//		results.setText(0, 1, "Problem A");
//		//		results.setText(0, 2, "Problem B");
//		//		results.setText(1, 0, "The First command");
//		//		results.setText(1, 1, "+1");
//		//		results.setText(1, 2, "-2");
//		//		results.setText(2, 0, "The Second command");
//		//		results.setText(2, 1, "-20");
//		//		results.setText(2, 2, "-");
//
//		Label label = new Label("kuku");
//
//		TestServiceAsync testServiceAsync = (TestServiceAsync) GWT.create(JudgeosService.class);
//		((ServiceDefTarget) testServiceAsync).setServiceEntryPoint("http://localhost:8080/judgeos/gwt/TessxtService");
//
//		testServiceAsync.getResults(23455, new MyAsyncCallback(label));
//
//		//results.setText(0, 0, "Test3");
//
//		return label;
//	}

//	private Widget createProblemsPanel() {
//		TabPanel tabPanel = new TabPanel();
//		HTML problem1 = new HTML("Problem A Task");
//		HTML problem2 = new HTML("Problem B Task");
//		tabPanel.add(problem1, "Problem A");
//		tabPanel.add(problem2, "Problem B");
//		return tabPanel;
//	}

//	static class MyAsyncCallback implements AsyncCallback {
//		Label label;
//
//		MyAsyncCallback(Label label) {
//			this.label = label;
//		}
//
//		public void onFailure(Throwable caught) {
//
////			String s = myPrintStackTrace(caught);
////			for (int i = 0; i < caught.getStackTrace().length; i++) {
////				StackTraceElement e = caught.getStackTrace()[i];
////			}
////			label.setText("Failed in ");
////			throw new JavaScriptException(caught.getMessage(), "Failed while connecting to server");
//
//			setStatusText("Error");
//			if (errorDialog == null) {
//			  errorDialog = new ErrorDialog();
//			}
//			if (caught instanceof InvocationException) {
//			  errorDialog.setText("An RPC server could not be reached");
//			  errorDialog.setBody(NO_CONNECTION_MESSAGE);
//			} else {
//			  errorDialog.setText("Unexcepted Error processing remote call");
//			  errorDialog.setBody(caught.getMessage());
//			}
//			errorDialog.center();
//		  }
//		}

//		private String myPrintStackTrace(Throwable caught) {
//			Throwable ourCause = caught.getCause();
//			if (ourCause != null) {
//				return myPrintStackTrace(ourCause);
//			}
//			String s = caught.toString() + "\n";
//			StackTraceElement[] trace = caught.getStackTrace();
//			for (int i = 0; i < trace.length; i++) {
//				s = s + "\tat " + trace[i] + "\n";
//			}
//			return s;
//		}

//		public void onSuccess(Object result) {
//			label.setText("Success");
//		}
//	}

	public static void showFetchingData() {
		message.setText(messages.fetchingData());
	}

	public static void hideFetchingData() {
		message.setText("");
	}
}
