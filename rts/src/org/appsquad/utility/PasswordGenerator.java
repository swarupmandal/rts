package org.appsquad.utility;

public class PasswordGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		generate();
	}

	public static String generate(){
		String yourPassword = null;
		char[] symbols = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n',
				'o','p','q','r','s','t','u','v','w','x','y','z',
				'0','1','2','3','4','5','6','7','8','9'};
		int passwordLength = 8;
		char[] letters = new char[passwordLength];
		
		for(int i=0;i<passwordLength;i++){
			int randValue = (int)(Math.random()*36);
			letters[i] = symbols[randValue];
		}
		yourPassword = new String(letters);
		return yourPassword;
	}
}
