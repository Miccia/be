package main.java.businessProcessListener;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.kie.api.event.process.*;
import org.kie.api.runtime.process.*;
import org.kie.api.runtime.*;
import org.drools.persistence.TransactionManager;
import org.drools.persistence.TransactionSynchronization;



//import main.java.loglib.AMQProducer;

public class BPListener implements org.kie.api.event.process.ProcessEventListener{
	
	//ActiveMQ Connection params
	public String brokerUrl;
	public String queueName;

	private ThreadLocal<Map<String,ProcessEventContext>> processContexts = new ThreadLocal();


	public BPListener(String brokerUrl,String queueName){
		this.brokerUrl=brokerUrl;
		this.queueName=queueName;
	}

	public BPListener(){
		this("tcp://192.198.1.214:61616","businessEvents");
	}
/*
*
*			PROCESS LISTENERS
*
*/
public void beforeProcessCompleted(ProcessCompletedEvent event){
	ProcessInstance pi = event.getProcessInstance();
	String msg = "[BEFORE_PROCESS_COMPLETED]";

	//TO-DO append objects to msg...

	System.out.println(msg);

	//try{new AMQProducer(brokerUrl,queueName,msg).start();}
	//catch(Exception e){e.printStackTrace();}

}
public void beforeProcessStarted(ProcessStartedEvent event){
	ProcessInstance pi = event.getProcessInstance();
	String msg = "[BEFORE_PROCESS_START]";

			//TO-DO append objects to msg...
		ProcessEventContext pec = getProcessContext(event);
		pec.setIndexingProcessState(ProcessEventContext.ProcessState.STARTING);
		pec.setProcessState(getProcessState(event));

	System.out.println(msg);
//    sendToAMQ(msg);
///	try{new AMQProducer(brokerUrl,queueName,msg).start();}
	//catch(Exception e){
	//	System.out.println("\n\n\n \t\tOMG \n");
	//	e.printStackTrace();
	//	System.out.println("\n\n\n");}

} 

public void afterProcessCompleted(ProcessCompletedEvent event){
	ProcessInstance pi = event.getProcessInstance();
	String msg = "[AFTER_PROCESS_COMPLETED]";

	//TO-DO append objects to msg...


	System.out.println(msg);

	//try{new AMQProducer(brokerUrl,queueName,msg).start();}
	//catch(Exception e){e.printStackTrace();}

}
public void afterProcessStarted(ProcessStartedEvent event){
	ProcessInstance pi = event.getProcessInstance();
	String msg = "[AFTER_PROCESS_STARTED]";

	//TO-DO append objects to msg...


	System.out.println(msg);

	//try{new AMQProducer(brokerUrl,queueName,msg).start();}
	//catch(Exception e){e.printStackTrace();}

}

/*
*
*			NODE LISTENERS
*
*/

public void beforeNodeLeft(ProcessNodeLeftEvent event){
	NodeInstance ni = event.getNodeInstance();
	long pid 		= event.getProcessInstance().getId();
	String msg = "[BEFORE_NODE_LEFT]";


	System.out.println(msg);

	//try{new AMQProducer(brokerUrl,queueName,msg).start();}
	//catch(Exception e){e.printStackTrace();}


}

public void beforeNodeTriggered(ProcessNodeTriggeredEvent event){
	NodeInstance ni = event.getNodeInstance();
	long pid 		= event.getProcessInstance().getId();
	String msg = "[BEFORE_NODE_TRIGGERED]";


	System.out.println(msg);

	//try{new AMQProducer(brokerUrl,queueName,msg).start();}
	//catch(Exception e){e.printStackTrace();}

}

public void afterNodeTriggered(ProcessNodeTriggeredEvent event){
	NodeInstance ni = event.getNodeInstance();
	long pid 		= event.getProcessInstance().getId();
	String msg = "[AFTER_NODE_TRIGGERED]";


	System.out.println(msg);

	//try{new AMQProducer(brokerUrl,queueName,msg).start();}
	//catch(Exception e){e.printStackTrace();}

}

public void afterNodeLeft(ProcessNodeLeftEvent event){
	NodeInstance ni = event.getNodeInstance();
	long pid 		= event.getProcessInstance().getId();
	String msg = "[AFTER_NODE_LEFT]";


	System.out.println(msg);

	//try{new AMQProducer(brokerUrl,queueName,msg).start();}
	//catch(Exception e){e.printStackTrace();}

}


/*
*
*			PROCESS VARIABLE LISTENERS
*
*/

public void beforeVariableChanged(ProcessVariableChangedEvent event){

	String msg = "[BEFORE_VARIABLE_CHANGED]";
	
	System.out.println(msg);
}
public void afterVariableChanged(ProcessVariableChangedEvent event){

		String msg ="[AFTER_VARIABLE_CHANGED]";


	System.out.println(msg);
}




//Utils
    public boolean sendToAMQ(String message){

    try{

            new PAMQProducer(queueName,message).start();
    }catch(Exception e){
        e.printStackTrace();

        return false;
    }
    return true;
    }

    public ProcessEventContext getProcessContext(ProcessEvent event){

    	Map<String,ProcessEventContext> contexts = processContexts.get();
    	if(contexts == null){
    		registerTransactionSynchronization(event);
    		contexts = new HashMap<>();
    		processContexts.set(contexts);
    	}
    	ProcessEventContext pec = contexts.get(getProcessEventContextKey(event));
    	if(contexts==null){
    		long pid = event.getProcessInstance().getId();
    		String deploymentUnit = (String) event.getKieRuntime().getEnvironment().get("deploymentId");
    		String processId = event.getProcessInstance().getProcessId();
    		contexts.put(
    			getProcessEventContextKey(),
    			new ProcessEventContext(deploymentUnit,processId,pid)
    			);
    	}return contexts;


    }

    public String getProcessEventContextKey(ProcessEvent event){
    	return new StringBuilder().append(event.getProcessInstance().getProcessId())
    				.append("-").append(event.getProcessInstance().getId()).toString();
    }

    public String getProcessState(ProcessEvent event){
    	String state="";
    	int s = event.getProcessInstance().getState();
    	switch(s){
    		case ProcessInstance.STATE_ABORTED:
    			return "ABORTED";
    			break;
    		case ProcessInstance.STATE_ACTIVE:
    			return "ACTIVE";
    			break;
    		case ProcessInstance.STATE_COMPLETE:
    			return "COMPLETE";
    			break;
    		case ProcessInstance.STATE_PENDING:
    			return "PENDING";
    			break;
    		case ProcessInstance.STATE_SUSPENDED:
    			return "SUSPENDED";
    			break;
    	}

    	return state;
    }


    private void registerTransactionSynchronization(ProcessEvent event){
    	TransactionManager tm = (TransactionManager) processEvent.getKieRuntime().getEnvironment()
    		.get(EnvironmentName.TRANSACTION_MANAGER);
    	if(tm == null){
    		throw new IllegalStateException("this listener requires access to a transaction manager");
    	}

    	if(tm.getStatus() == TransactionManager.STATUS_NO_TRANSACTION){
    		throw new IllegalStateException("no transaction found");
    	}

    	TSAdapter tsa = new TSAdapter();
    	tm.registerTransactionSynchronization(tsa);
    }

    private class TSAdapter implements TransactionSynchronization{

    	@Override
    	public void beforeCompletion(){}


    	@Override
    	public void afterCompletion(int status){
    		try{
    			switch(status){

    				case TransactionManager.STATUS_COMMITTED:

    					Map<String,ProcessEventContext> contexts =
    						BPListener.this.processContexts.get();
    					if(contexts != null){
    						Collection<ProcessEventContext> pec = contexts.values();

    						for(ProcessEventContext pe : pec){
    						if(pe != null){

    						PamProcessDocument pd = new PamProcessDocument(pe);
    						String JsonProcessDocument = processDocument.toJsonString();
    						String processDocumentId  = pd.getDeploymentUnit()+"-"+
    													pd.getProcessId()+"-"+
    													pd.getProcessInstanceId();


    						switch(pe.getIndexingProcessState()){
    						case STARTING :
    							sendToAMQ(JsonProcessDocument);
    							break;
    						case ACTIVE:
    							sendToAMQ(JsonProcessDocument);
    							break;
    						case COMPLETE:
    							sendToAMQ(JsonProcessDocument);
    							break;
    						default:
    							break;
    						}//switch 1   						
    						}
    						}//for each context found write to amq

    					}
    					break;
    			case TransactionManager.STATUS_ROLLEDBACK:
    				throw new IllegalStateException("rolled back transaction, discarding info.");
    				break;
    			default:
    				throw new IllegalStateException("unexpected transaction manager state");
    				break;
    			}//switch_0


    		}catch(Exception e){e.printStackTrace();}
    		finally{
    			processContexts.set(null);
    		}



    	}//afterCompletion



    }
}