package org.judgeos.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.*;

public class ControlPanel implements EntryPoint {
	public ControlPanelMessages messages;
	private final Command voidCommand = new Command() {public void execute() {}}; 

	public void onModuleLoad() {
		messages = (ControlPanelMessages) GWT.create(ControlPanelMessages.class);
		//GWT.create();
		final TabPanel tabPanel = new TabPanel();

		final Widget scoreTablePanel = createScoreTablePanel();
		final Widget problemsPanel = createProblemsPanel();

		//tabPanel.add(scoreTablePanel, messages.scoreTablePanel());
//		final MenuItem itemScoreTable = new MenuItem("ScoreTable", voidCommand);
//		Command kuku = new Command() {
//			private int i = 1;
//			public void execute() {
//				itemScoreTable.setText("kuku" + i++);
//			}
//		};
//		itemScoreTable.setCommand(kuku);
		tabPanel.add(scoreTablePanel, messages.scoreTable());
		//tabPanel.add(scoreTablePanel, messages.problems());
		HorizontalPanel centralizer = new HorizontalPanel();
//		centralizer.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
//		centralizer.add(tabPanel);
//		tabPanel.selectTab(0);
		RootPanel.get("body").add(tabPanel);
//		RootPanel.get("body").add(centralizer);
	}

	private Widget createScoreTablePanel() {
		final Grid scoreTable = new Grid(3,2);
		scoreTable.setText(0, 0, "Commands");
		scoreTable.setText(0, 1, "Problem A");
		scoreTable.setText(0, 2, "Problem B");
		scoreTable.setText(1, 0, "The First command");
		scoreTable.setText(1, 1, "+1");
		scoreTable.setText(1, 2, "-2");
		scoreTable.setText(2, 1, "The Second command");
		scoreTable.setText(2, 2, "-20");
		scoreTable.setText(2, 3, "-");

//		TestServiceAsync testServiceAsync = (TestServiceAsync) GWT.create(TestService.class);
//		((ServiceDefTarget) testServiceAsync).setServiceEntryPoint("http://localhost:8080/judgeos/gwt/TestService");
//		AsyncCallback callback = new AsyncCallback() {
//			public void onFailure(Throwable caught) {
//				scoreTable.setText(0, 0, "Failure");
//			}
//
//			public void onSuccess(Object result) {
//				scoreTable.setText(0, 0, result.toString());
//			}
//		};
//		testServiceAsync.getTest(123, callback);
//
//		scoreTable.setText(0, 0, "Test3");

		return scoreTable;
	}

	private Widget createProblemsPanel() {
		TabPanel tabPanel = new TabPanel();
		HTML problem1 = new HTML("Problem A Task");
		HTML problem2 = new HTML("Problem B Task");
		tabPanel.add(problem1, "Problem A");
		tabPanel.add(problem2, "Problem B");
		return tabPanel;
	}
}
