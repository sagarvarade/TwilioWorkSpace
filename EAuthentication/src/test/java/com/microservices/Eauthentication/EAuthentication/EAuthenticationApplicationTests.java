package com.microservices.Eauthentication.EAuthentication;

import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.Test;

import net.minidev.json.JSONObject;

class EAuthenticationApplicationTests {

	//@Test
    public void testDecodeJWT(){
        String jwtToken = "eyJhbGciOiJIUzUxsMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTY1ODY0OTUzM30.pTMryXzrxviBhtm4asI-R0O3QHzMI8e7apB_0UfVqCxzkGyWrpDYijKyY9lPw1yl7fFzgd7ypNk5p5m2A4NfvQ";
        System.out.println("------------ Decode JWT ------------");
        String[] split_string = jwtToken.split("\\.");
        String base64EncodedHeader = split_string[0];
        String base64EncodedBody = split_string[1];
        String base64EncodedSignature = split_string[2];

        System.out.println("~~~~~~~~~ JWT Header ~~~~~~~");
        Base64 base64Url = new Base64(true);
        String header = new String(base64Url.decode(base64EncodedHeader));
        System.out.println("JWT Header : " + header);
        
        System.out.println("~~~~~~~~~ JWT Body ~~~~~~~");
        String body = new String(base64Url.decode(base64EncodedBody));
        System.out.println("JWT Body : "+body);        
    }
	@Test
	public void testJson() {
		JSONObject userDetailsObject=new JSONObject();
	      userDetailsObject.put("ID", "sds");
	      userDetailsObject.put("EMAIL", "sdsffe");
	    System.out.println(userDetailsObject.toJSONString());
	    System.out.println(userDetailsObject.toString());
	}
}
