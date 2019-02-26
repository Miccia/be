package main.java.businessProcessListener;

import javax.jms.*;
import javax.naming.InitialContext;


public class PAMQProducer implements javax.jms.ExceptionListener{

    private String brokerUrl;
    private String queueName;
    private String message;

    private ConnectionFactory connectionFactory;
    private Connection 	 connection;
    private Session 	 session;

    public PAMQProducer() {
        //Use for local tests only
        this("tcp://127.0.0.1:61616","testQueue","A nice message");
    }
    ///etc/hosts  activemqBroker !!!!!!!!!
    public  PAMQProducer(String brokerUrl,
                        String queueName,
                        String message){

        this.brokerUrl=brokerUrl;
        this.queueName=queueName;
        this.message=message;
    }

    public PAMQProducer(String queueName,String message){
            this.queueName = queueName;
            this.message = message;
    }


    public  void start() throws JMSException, javax.naming.NamingException{

        InitialContext ctx = new InitialContext();
        if (this.connectionFactory == null) {
            //java:/ActiveMQConnectionFactory"

            queueName="java:/jms/queue/PAM.POSTE.AUDIT.AGG";
            this.connectionFactory = (ConnectionFactory) ctx.lookup("ConnectionFactoryBridge"); }
        Connection queueConnection = connectionFactory.createConnection();
        Session  queueSession = queueConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer  = queueSession.createProducer(queueSession.createQueue(queueName));
        producer.send(queueSession.createTextMessage(message));

        ctx.close();
        queueSession.close();
        queueConnection.close();

       // System.out.println("Sent  message: \n\n"+msg+"\n\t"+ message.hashCode());

    }


    //jms onException
    public void onException(JMSException jmse){

        System.out.println("\n\n\n\t\t\t"+jmse+"\n\n\n");
        jmse.printStackTrace();
    }

}