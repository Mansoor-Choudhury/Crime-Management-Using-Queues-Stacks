import java.util.LinkedList;

public class Main {
	public static void main(String[] args){
		//Run for a single iteration : enqueue/dequeue and push/pop operations
		countCrimeIncidentTypes(1);
		
		//Run for a multiple iterations : enqueue/dequeue and push/pop operations for experimental analysis
		countCrimeIncidentTypes(5);
	}
	/**
	 * This method is responsible for running the queue and stack operations on different set of data 
	 * based on incident type.
	 * 
	 * First we read the records from the crime-incidents.csv file into a list and put them into the stack and queue data structures
	 * 
	 * We then remove the data from the data structure and check if it matches the incident type and increment the corresponding count
	 * @param numOfIterations
	 * @throws InterruptedException 
	 */
	public static void countCrimeIncidentTypes(int numOfIterations) throws InterruptedException{
		
		String[] incidentType = new String[] {"ASSAULT","LARCENY","MURDER","ROBBERY","SEXUAL"};
		FileManager file = new FileManager();
		LinkedList<CrimeIncidents> crimeIncidentsList = file.getCrimeData();
		ArrayQueue arrayQueue = new ArrayQueue(crimeIncidentsList.size());
		ArrayStack arrayStack = new ArrayStack(crimeIncidentsList.size());
		LinkedQueue linkedQueue = new LinkedQueue();
		LinkedStack linkedStack = new LinkedStack();
				
		int countIncidentTypeArrayQ = 0;
		int countIncidentTypeArrayStack = 0;
		int countLinkedQueue = 0;
		int countLinkedStack = 0;
		
		System.out.println("\n"+"****************************************************************************************************************************************************************************************************************************************");
		if(numOfIterations == 1) 
			System.out.println("\n"+"\t\t\t\t\t\t\t\t\t\tTime taken to count crime incident types\t\t\t\t\t\t\t\t\t\t");
		else
			System.out.println("\n"+"\t\t\t\t\t\t\t\t\t\tEXPERIMENTAL ANALYSIS - Average Time taken to count crime incident types\t\t\t\t\t\t\t\t\t\t");

		System.out.println("\n"+"****************************************************************************************************************************************************************************************************************************************");

		System.out.println("Crime Incident Type\t|\tRecord Count\t|\tQueue(Array)\t|\tStack(Array)\t|\tQueue(Linked List)\t|\tStack(Linked List)\t");

		System.out.println("\n"+"****************************************************************************************************************************************************************************************************************************************");

		for(int i=0; i< incidentType.length;i++) {
			
			long arrayQueueDequeueTime = 0;
		
			// Processing data in queue (array implementation)
			for(int j = 0;j< numOfIterations; j++) {
				countIncidentTypeArrayQ = 0;
				
				//Insert into the queue the csv data
				for(CrimeIncidents crimeIncident : crimeIncidentsList) {
					arrayQueue.enqueue(crimeIncident);
				}
				
				long arrayQueueDequeueStartTime =  System.currentTimeMillis();
				
				while(arrayQueue.size() > 0){
					
					//Dequeue data from the queue and increment counter for the incident type
					if(((CrimeIncidents)arrayQueue.dequeue()).getIncidentType().trim().equals(incidentType[i])){
						countIncidentTypeArrayQ++;
					}
				}
				
				long arrayQueueDequeueEndTime =  System.currentTimeMillis();
				arrayQueueDequeueTime += arrayQueueDequeueEndTime - arrayQueueDequeueStartTime;
				
			}
			
			long arrayStackPopTime = 0;
			
			// Processing data in stack (array implementation)
			for(int k = 0;k< numOfIterations; k++) {
				countIncidentTypeArrayStack = 0;
				
				//Insert into the stack the csv data
				for(CrimeIncidents crimeIncident : crimeIncidentsList) {
					arrayStack.push(crimeIncident);
				}
				
				long arrayStackPopStartTime =  System.currentTimeMillis();
				
				while(arrayStack.size() > 0){
					
					//Pop data from the stack and increment counter for the incident type
					if(((CrimeIncidents)arrayStack.pop()).getIncidentType().trim().equals(incidentType[i])){
						countIncidentTypeArrayStack++;
					}
				}
	
				long arrayStackPopEndTime =  System.currentTimeMillis();
				arrayStackPopTime += arrayStackPopEndTime - arrayStackPopStartTime;
			}
			
			long linkedQueueDequeueTime = 0;
			
			// Processing data in queue (linked list implementation)
			for(int l = 0;l< numOfIterations; l++) {
				countLinkedQueue = 0;
				
				//Insert into the queue the csv data
				for(CrimeIncidents crimeIncident : crimeIncidentsList) {
					linkedQueue.enqueue(crimeIncident);
				}
				
				long linkedQueueDequeueStartTime = System.currentTimeMillis();
				
				while(linkedQueue.size() > 0){
					//Dequeue data from the queue and increment counter for the incident type
					if(((CrimeIncidents)linkedQueue.dequeue()).getIncidentType().trim().equals(incidentType[i])){
						countLinkedQueue++;
					}
				}
				long linkedQueueDequeueEndTime = System.currentTimeMillis();
				linkedQueueDequeueTime += linkedQueueDequeueEndTime - linkedQueueDequeueStartTime;
			}
			
			long linkedStackPopTime = 0;
			
			// Processing data in stack (linked list implementation)
			for(int m = 0;m< numOfIterations; m++) {
				countLinkedStack = 0;
				
				//Insert into the stack the csv data
				for(CrimeIncidents crimeIncident : crimeIncidentsList) {
					linkedStack.push(crimeIncident);
				}
				
				long linkedStackPopStartTime = System.currentTimeMillis();
				
				while(linkedStack.size() > 0){
					
					//Pop data from the stack and increment counter for the incident type
					if(((CrimeIncidents)linkedStack.pop()).getIncidentType().trim().equals(incidentType[i])){
						countLinkedStack++;
					}
				}
				long linkedStackPopEndTime = System.currentTimeMillis();
				linkedStackPopTime += linkedStackPopEndTime - linkedStackPopStartTime;
			}
			
			System.out.println(incidentType[i]+"\t\t\t|\t\t"+countIncidentTypeArrayQ+"\t|\t\t"+arrayQueueDequeueTime/numOfIterations+"\t|\t\t"+arrayStackPopTime/numOfIterations+"\t|\t\t"+linkedQueueDequeueTime/numOfIterations+"\t\t|\t\t"+linkedStackPopTime/numOfIterations);

		}
		
		System.out.println("\n"+"****************************************************************************************************************************************************************************************************************************************");

	}
	

}

