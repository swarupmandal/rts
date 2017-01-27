package org.appsquad.utility;

public class WrapperUtility {
  public static void m1(Integer x){
	  System.out.println("1ST METHOD");
  }
  
  public static void m1(long x){
	  System.out.println("2ND METHOD");
  }
  public static void main(String[] args) {
	  int z = 10;
	  m1(z);
  } 
}
