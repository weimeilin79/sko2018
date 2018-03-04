package org.mycompany;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component("AnalyticProcessor")
public class AnalyticProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		
		String sestno = (String) exchange.getIn().getBody();
		
		
		Integer row = Integer.valueOf( sestno.substring(0, sestno.indexOf("_")));
		Integer colno = Integer.valueOf(sestno.substring(((sestno.indexOf("_")+1)),sestno.length()));
		
		exchange.getIn().setHeader("row", row);
		exchange.getIn().setHeader("colno", colno);
		
		if(row < 5)
			exchange.getIn().setHeader("class", "LOGE");
		else 
			exchange.getIn().setHeader("class", "CLUB");
	}	

	
	
}
