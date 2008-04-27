package org.judgeos.gwt.client;

import com.google.gwt.user.client.rpc.RemoteService;
import org.judgeos.gwt.client.model.Results;

public interface JudgeosService extends RemoteService {
  Results getResults();
}
