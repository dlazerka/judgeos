package org.judgeos.gwt.client;

import com.google.gwt.i18n.client.Messages;


public interface ControlPanelMessages extends Messages {
	String results();

	String problems();
	/**
	 * @gwt.key errors.rootSlotNotFound
	 * @param rootSlotId
	 */
	String errorsRootSlotNotFound(String rootSlotId);

	/**
	 * @gwt.key errors.failedReachRPC
	 */
	String errorsFailedReachRPC();

	/**
	 * @gwt.key errors.failedExecRPC
	 */
	String errorsFailedExecRPC();

	String fetchingData();
}
