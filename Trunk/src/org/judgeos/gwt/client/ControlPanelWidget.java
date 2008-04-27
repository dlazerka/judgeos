package org.judgeos.gwt.client;

import com.google.gwt.user.client.ui.TabPanel;

public class ControlPanelWidget extends TabPanel {
	public ControlPanelWidget() {
		add(new ResultsWidget(), ControlPanel.messages.results());
	}
}
