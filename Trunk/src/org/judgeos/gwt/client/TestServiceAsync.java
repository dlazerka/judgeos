package org.judgeos.gwt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TestServiceAsync {
  void getTest(int param, AsyncCallback callback);
}

