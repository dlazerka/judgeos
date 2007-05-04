package org.judgeos.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

public class ControlPanel implements EntryPoint {
	public ControlPanelMessages messages;

	public void onModuleLoad() {
		Command voidCommand = null;
		messages = (ControlPanelMessages) GWT.create(ControlPanelMessages.class);
		//GWT.create();
//		final Button buttonTest = new Button(messages.getTest());
//		final Button buttonTest2 = new Button(messages.getTest()+ "2");
		final TabPanel tabPanel = new TabPanel();

		final Widget scoreTable = createScoreTable();

		tabPanel.add(scoreTable, messages.scoreTable());
//		final MenuItem itemScoreTable = new MenuItem("kuku", voidCommand);
//		Command kuku = new Command() {
//			private int i = 1;
//			public void execute() {
//				itemScoreTable.setText("kuku" + i++);
//			}
//		};
//		itemScoreTable.setCommand(kuku);

//		tabPanel.add(buttonTest, "asds   ");
//		tabPanel.add(buttonTest2, "   asds32");
		HorizontalPanel centralizer = new HorizontalPanel();
		centralizer.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		tabPanel.setWidth("100pt");
		centralizer.add(tabPanel);
		tabPanel.selectTab(0);
		RootPanel.get("body").add(centralizer);
	}

	private Widget createScoreTable() {
		final Grid scoreTable = new Grid(1,1);

		TestServiceAsync testServiceAsync = (TestServiceAsync) GWT.create(TestService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) testServiceAsync;
		endpoint.setServiceEntryPoint("http://localhost:8080/judgeos/gwt/TestService");
		AsyncCallback callback = new AsyncCallback() {
			public void onFailure(Throwable caught) {
				scoreTable.setText(0, 0, "Failure");
			}

			public void onSuccess(Object result) {
				scoreTable.setText(0, 0, "Success");
			}
		};
		testServiceAsync.getTest(123, callback);

		scoreTable.setText(0, 0, "Test3");

		return scoreTable;
	}
}
