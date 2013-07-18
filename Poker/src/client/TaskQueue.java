package client;

import java.util.ArrayList;
import java.util.List;

import commands.Command;

/**
 * Storage for commands received from server
 * @author Aleksey
 *
 */

public class TaskQueue {
	
	/**
	 * taskList: data structure used for storing commands
	 */
	private List<Command> taskList;
	
	/**
	 * Constructs and empty data structure
	 */
	public TaskQueue(){
		taskList = new ArrayList <Command>();	
	}
	
	/**
	 * Synchronously adds a command to the taskList
	 * and notifies {@link ClientGame}
	 * @param task
	 *  Command to add
	 */

	public synchronized void addTask(Command task){	
		
		taskList.add(task);		
		notify();
	}
	
	/**
	 * Synchronously get a command from the taskList
	 * @return
	 * last command sent by the server
	 */
	
	public  synchronized Command getNextTask(){
		if(taskList.isEmpty()){
			return null;
		}else{
			return taskList.remove(0);
		}
	}
	
	/**
	 * Check's if the taskList is empty, 
	 * wARNING this method is not synchronized
	 * 
	 * @return true if it's empty, false otherwise
	 */
	
	public boolean isEmpty(){
		if(taskList.size() == 0){
			return true;
		}else{
			return false;
		}
	}
}