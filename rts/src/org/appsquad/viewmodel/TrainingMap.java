package org.appsquad.viewmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
public class TrainingMap {
	
	
	
  public static void main(String[] args) {
	  Map<Integer, HashMap<String, Integer>> map = new HashMap<Integer, HashMap<String,Integer>>();
	  HashMap<String, Integer> map1 = new HashMap<String, Integer>();
	  map1.put("a", 1);
	  map1.put("a", 1);
	  map1.put("b", 1);
	  map1.put("a", 1);
	  map.put(1, map1);
	  /**************/
	  map1.put("c", 1);
	  map1.put("c", 1);
	  map1.put("c", 1);
	  map1.put("d", 1);
	  map.put(2, map1);
	  
	  System.out.println("ENTRY SET IS :"+map.entrySet());
	  for (Entry<Integer, HashMap<String, Integer>> entry : map.entrySet()){
		  System.out.println("key is:"+entry.getKey()+"-----"+"values is :"+entry.getValue());
		  
	  }
  }
}
