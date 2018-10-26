package com.statement.aggregater;

import java.util.ArrayList;

import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.routing.AggregationContext;
import org.mule.routing.AggregationStrategy;
import org.mule.transport.NullPayload;



public class CustomAggregater implements AggregationStrategy
{

	@Override
	public MuleEvent aggregate(AggregationContext context) throws MuleException {
		 ArrayList<Object> temp = new ArrayList<>();

         System.out.println(" array size : " + context.collectEventsWithoutExceptions().size());

         for(MuleEvent event: context.collectEventsWithoutExceptions())

         {

                         Object payload = event.getMessage().getPayload();

                         if(!(payload instanceof NullPayload))

                         {

                                         System.out.println(payload.toString());

                                         temp.add(event.getMessage().getPayload());

                                         //temp.add(payload.toString());

                         }

        

}

         MuleEvent result = null;

         result = context.getOriginalEvent();

         result.getMessage().setPayload(temp.get(0));

         return result;

} 
	}


