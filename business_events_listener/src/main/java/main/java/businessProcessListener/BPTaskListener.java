package main.java.businessProcessListener;

//import org.jbpm.services.task.events.DefaultTaskEventListener;
import org.kie.api.task.TaskEvent;


public class BPTaskListener  extends  org.jbpm.services.task.events.DefaultTaskEventListener{

    @Override
    public void beforeTaskActivatedEvent(TaskEvent event) {
        String msg = "";
       
        System.out.println("\n\t\t"+msg+"\n");

    }

    @Override
    public void beforeTaskClaimedEvent(TaskEvent event) {
        String msg = "";
        System.out.println("\n\t\t"+msg+"\n");

    }

    @Override
    public void beforeTaskSkippedEvent(TaskEvent event) {
        String msg = "";
        System.out.println("\n\t\t"+msg+"\n");

    }

    @Override
    public void beforeTaskStartedEvent(TaskEvent event) {
        String msg = "";
        System.out.println("\n\t\t"+msg+"\n");

    }

    @Override
    public void beforeTaskStoppedEvent(TaskEvent event) {
        String msg = "";
        System.out.println("\n\t\t"+msg+"\n");

    }

    @Override
    public void beforeTaskCompletedEvent(TaskEvent event) {
        String msg = "";
        System.out.println("\n\t\t"+msg+"\n");

    }

    @Override
    public void beforeTaskFailedEvent(TaskEvent event) {
        String msg = "";
        System.out.println("\n\t\t"+msg+"\n");

    }

    @Override
    public void beforeTaskAddedEvent(TaskEvent event) {
        String msg = "";
        System.out.println("\n\t\t"+msg+"\n");

    }

    @Override
    public void beforeTaskExitedEvent(TaskEvent event) {
        String msg = "";
        System.out.println("\n\t\t"+msg+"\n");

    }

    @Override
    public void beforeTaskReleasedEvent(TaskEvent event) {
        String msg = "";
        System.out.println("\n\t\t"+msg+"\n");

    }

    @Override
    public void beforeTaskResumedEvent(TaskEvent event) {
        String msg = "";
        System.out.println("\n\t\t"+msg+"\n");

    }

    @Override
    public void beforeTaskSuspendedEvent(TaskEvent event) {
        String msg = "";
        System.out.println("\n\t\t"+msg+"\n");

    }

    @Override
    public void beforeTaskForwardedEvent(TaskEvent event) {
        String msg = "";
        System.out.println("\n\t\t"+msg+"\n");

    }

    @Override
    public void beforeTaskDelegatedEvent(TaskEvent event) {
        String msg = "";
        System.out.println("\n\t\t"+msg+"\n");

    }

    @Override
    public void beforeTaskNominatedEvent(TaskEvent event) {
        String msg = "";
        System.out.println("\n\t\t"+msg+"\n");

    }

    @Override
    public void afterTaskActivatedEvent(TaskEvent event) {
        String msg = "";
        System.out.println("\n\t\t"+msg+"\n");

    }

    @Override
    public void afterTaskClaimedEvent(TaskEvent event) {
        String msg = "";
        System.out.println("\n\t\t"+msg+"\n");

    }

    @Override
    public void afterTaskSkippedEvent(TaskEvent event) {
        String msg = "";
        System.out.println("\n\t\t"+msg+"\n");

    }

    @Override
    public void afterTaskStartedEvent(TaskEvent event) {
        String msg = "";
        System.out.println("\n\t\t"+msg+"\n");

    }

    @Override
    public void afterTaskStoppedEvent(TaskEvent event) {
        String msg = "";
        System.out.println("\n\t\t"+msg+"\n");

    }

    @Override
    public void afterTaskCompletedEvent(TaskEvent event) {
        String msg = "";
        System.out.println("\n\t\t"+msg+"\n");

    }

    @Override
    public void afterTaskFailedEvent(TaskEvent event) {
        String msg = "";
        System.out.println("\n\t\t"+msg+"\n");

    }

    @Override
    public void afterTaskAddedEvent(TaskEvent event) {
        String msg = "";
        System.out.println("\n\t\t"+msg+"\n");

    }

    @Override
    public void afterTaskExitedEvent(TaskEvent event) {
        String msg = "";
        System.out.println("\n\t\t"+msg+"\n");

    }

    @Override
    public void afterTaskReleasedEvent(TaskEvent event) {
        String msg = "";
        System.out.println("\n\t\t"+msg+"\n");

    }

    @Override
    public void afterTaskResumedEvent(TaskEvent event) {
        String msg = "";
        System.out.println("\n\t\t"+msg+"\n");

    }

    @Override
    public void afterTaskSuspendedEvent(TaskEvent event) {
        String msg = "";
        System.out.println("\n\t\t"+msg+"\n");

    }

    @Override
    public void afterTaskForwardedEvent(TaskEvent event) {
        String msg = "";
        System.out.println("\n\t\t"+msg+"\n");

    }

    @Override
    public void afterTaskDelegatedEvent(TaskEvent event) {
        String msg = "";
        System.out.println("\n\t\t"+msg+"\n");

    }

    @Override
    public void afterTaskNominatedEvent(TaskEvent event) {
        String msg = "";
        System.out.println("\n\t\t"+msg+"\n");

    }


    public ProcessEventContext getProcessContext(){
        
    }



}



