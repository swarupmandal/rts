package org.appsquad.utility;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

public class PasswordEncryption {
	
	public static void main(String[] args) {
		easeyEncrypt("r0xvydzm");
	}
	
    public static String easeyEncrypt(String ip) {
    	String b64encoded = Base64.getEncoder().encodeToString(ip.getBytes());
    	// Reverse the string
    	String reverse = new StringBuffer(b64encoded).reverse().toString();
    	StringBuilder tmp = new StringBuilder();
    	final int OFFSET = 4;
    	for (int i = 0; i < reverse.length(); i++) {
    		tmp.append(reverse.charAt(i) + OFFSET);
    	}
    	return tmp.toString();
    }


    //To decrypt procede backwards:
    public static String easeyDecrypt(String secret) {
    	StringBuilder tmp = new StringBuilder();
    	final int OFFSET = 4;
    	for (int i = 0; i < secret.length(); i++) {
    		tmp.append(secret.charAt(i) - OFFSET);
    	}

    	String reversed = new StringBuffer(tmp.toString()).reverse().toString();
    	return Base64.getDecoder().decode(reversed).toString();
    }
}
