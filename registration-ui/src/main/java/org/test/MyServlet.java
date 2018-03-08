/*
 * #%L
 * Wildfly Camel
 * %%
 * Copyright (C) 2013 - 2015 RedHat
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.test;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

@WebServlet(name = "CamelServiceServlet", urlPatterns = { "/skoregistration/*" }, loadOnStartup = 1)
public class MyServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name = "java:jboss/camel/context/spring-context")
	private CamelContext camelContext;

    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String candidatename = req.getParameter("candidatename");
        String email = req.getParameter("email");
        String seatname = req.getParameter("seatname");
        String seatno = req.getParameter("seatno");
        
        Registration registration = new Registration(candidatename,email,seatname,seatno);
    	ProducerTemplate producer = camelContext.createProducerTemplate();
        producer.requestBody("direct:start", registration);
    	
    	
        
		RequestDispatcher rd = req.getRequestDispatcher("/done.html");
		rd.forward(req, res);
    }
}
