package org.apache.storm.jms.example;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JmsMsgProducerApp {
	  public static void main(String[] args) {
		    // init spring context
		    ApplicationContext ctx = new ClassPathXmlApplicationContext("jms-activemq.xml");
		         
		    // get bean from context
		    JmsMessageSender jmsMessageSender = (JmsMessageSender)ctx.getBean("jmsMessageSender");
		         
		    // send to default destination 
		    jmsMessageSender.send("hello JMS");
		         
		    // send to a code specified destination
		    Queue queue = new ActiveMQQueue("GroupingTest");
		    String fixMsg = new FixMsg("fixorder1").getMessage();
		    for(int i=0; i < 50; i++)
		    	jmsMessageSender.send(queue, fixMsg);
		   
		    // close spring application context
		    ((ClassPathXmlApplicationContext)ctx).close();
	}
	  
}
