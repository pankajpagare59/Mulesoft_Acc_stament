package com.statement.utils;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

public class CustomComponent implements Callable {
//	  public Object onCall(MuleEventContext eventContext) throws Exception {
//	    //Get at the payload by eventContext.getMessage().getPayload()
//	    return eventContext.getMessage();
//	  }

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		// TODO Auto-generated method stub
		return  eventContext.getMessage();
	}
	}