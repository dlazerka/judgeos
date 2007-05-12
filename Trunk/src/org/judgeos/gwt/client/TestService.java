package org.judgeos.gwt.client;

import com.google.gwt.user.client.rpc.RemoteService;

public interface TestService extends RemoteService {
  String getTest(int param);
}
