package main.java.businessProcessListener;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.ObjectMapper;
import com.fasterxml.jackson.annotate.JsonSerialize.Inclusion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Java representation of the JSON Process data.
 * 
 * @author <a href="mailto:duncan.doyle@redhat.com">Duncan Doyle</a>
 */
public class PamProcessDocument {
	
	//private static final Logger LOGGER = LoggerFactory.getLogger(JbpmProcessDocument.class);
	
	private String deploymentUnit;
	
	private String processId;

	private long processInstanceId;
	
	private String processState;
	
	private Map<String, Object> processVariables;
	
	private ObjectMapper jsonObjectMapper = new ObjectMapper().setSerializationInclusion(Inclusion.NON_EMPTY);
	
	public PamProcessDocument(ProcessEventContext context) {
		this.deploymentUnit = context.getDeploymentUnit();
		this.processId = context.getProcessId();
		this.processInstanceId = context.getProcessInstanceId();
		this.processState = context.getProcessState();
		//TODO: Shouldn't we make a copy?
		this.processVariables = context.getChangedVariables();
	}

	public long getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
	public String getDeploymentUnit() {
		return deploymentUnit;
	}

	public void setDeploymentUnit(String deploymentUnit) {
		this.deploymentUnit = deploymentUnit;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}
	
	public String getProcessState() {
		return processState;
	}

	public void setProcessState(String processState) {
		this.processState = processState;
	}

	public Map<String, Object> getProcessVariables() {
		return processVariables;
	}

	public void setProcessVariables(Map<String, Object> processVariables) {
		this.processVariables = processVariables;
	}
	
	
	public String toJsonString() {
		String jsonString;
		try {
			jsonString = jsonObjectMapper.writeValueAsString(this);
		} catch (IOException e) {
			String message = "Unable to marshall object to JSON string.";
			//LOGGER.error(message, e);
			//This will probably end up rolling back the DB transaction and thus the process.
			throw new RuntimeException(message, e);
		}
		return jsonString;
	}
	
}