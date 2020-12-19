package org.lkg.algorithms;

import java.util.HashMap;

public class LoggerRateLimiter {
	static HashMap<String,Integer> msgs = new HashMap<String,Integer>();
	
	static class Logger{
		int timestamp;
		
		
		public Logger(){
			timestamp =0;
		}
		
		public boolean shouldPrintMessage(int timestamp, String msg){
			if(!msgs.containsKey(msg)){
				msgs.put(msg, timestamp++);
				return true;
			}else{
				if(msgs.get(msg)>10 || timestamp > 10){
					msgs.put(msg, timestamp++);
					return true;
				}
			}
			return false;
			
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logger logger = new Logger();

		// logging string "foo" at timestamp 1

		System.out.println(logger.shouldPrintMessage(1, "foo")); //returns true;

		// logging string "bar" at timestamp 2

		System.out.println(logger.shouldPrintMessage(2,"bar")); //returns true;

		// logging string "foo" at timestamp 3

		System.out.println(logger.shouldPrintMessage(3,"foo")); //returns false;

		// logging string "bar" at timestamp 8

		System.out.println(logger.shouldPrintMessage(8,"bar")); //returns false;

		// logging string "foo" at timestamp 10

		System.out.println(logger.shouldPrintMessage(10,"foo")); //returns false;

		// logging string "foo" at timestamp 11

		System.out.println(logger.shouldPrintMessage(11,"foo")); //returns true;
	}

}
