package client;

import java.util.ArrayList;
import java.util.List;

import commands.Command;

public class TaskQueue {
	
	private List<Command> taskList;
	
	public TaskQueue(){
		taskList = new ArrayList <Command>();	
	}

	public synchronized void addTask(Command task){	
		
		taskList.add(task);		
		notify();
	}
	
	public  synchronized Command getNextTask(){
		if(taskList.isEmpty()){
			return null;
		}else{
			return taskList.remove(0);
		}
	}
	
	public boolean isEmpty(){
		if(taskList.size() == 0){
			return true;
		}else{
			return false;
		}
	}
}