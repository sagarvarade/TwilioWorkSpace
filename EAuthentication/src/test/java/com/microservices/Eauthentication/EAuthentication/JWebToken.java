package com.microservices.Eauthentication.EAuthentication;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JWebToken {

    private static final String SECRET_KEY = "ZmQ0ZGI5NjQ0MDQwY2I4MjMxY2Y3ZmI3MjdhN2ZmMjNhODViOTg1ZGE0NTBjMGM4NDA5NzYxMjdjOWMwYWRmZTBlZjlhNGY3ZTg4Y2U3YTE1ODVkZDU5Y2Y3OGYwZWE1NzUzNWQ2YjFjZDc0NGMxZWU2MmQ3MjY1NzJmNTE0MzI="; //@TODO Add Signature here
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    private static final String ISSUER = "mason.metamug.net";
    private static final String JWT_HEADER = "{\"alg\":\"HS512\",\"typ\":\"JWT\"}";
    private JSONObject payload = new JSONObject();
    private String signature;
    private String encodedHeader;

    private JWebToken() {
        try {
			encodedHeader = encode(new JSONObject(JWT_HEADER));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public JWebToken(JSONObject payload) throws JSONException {
        this(payload.getString("sub"), payload.getJSONArray("aud"), payload.getLong("exp"));
    }

    public JWebToken(String sub, JSONArray aud, long expires) {
        this();
        try {
			payload.put("sub", sub);
			payload.put("aud", aud);
	        payload.put("exp", expires);
	        payload.put("iat", LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
	        payload.put("iss", ISSUER);
	        payload.put("jti", UUID.randomUUID().toString()); //how do we use this?
	        signature = hmacSha256(encodedHeader + "." + encode(payload), SECRET_KEY);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

    /**
     * For verification
     *
     * @param token
     * @throws java.security.NoSuchAlgorithmException
     */
    
    public static void main(String[] args) {
    	try {
			JWebToken j=new JWebToken("eyJhbGciOiJIUzUxsMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTY1ODY0OTUzM30.pTMryXzrxviBhtm4asI-R0O3QHzMI8e7apB_0UfVqCxzkGyWrpDYijKyY9lPw1yl7fFzgd7ypNk5p5m2A4NfvQ");
			System.out.println("  "+j.encodedHeader);
			System.out.println("  "+j.signature);
			System.out.println("  "+j.payload);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
	}
    
    
    public JWebToken(String token) throws NoSuchAlgorithmException {
        this();
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid Token format");
        }
        if (encodedHeader.equals(parts[0])) {
            encodedHeader = parts[0];
        } else {
            throw new NoSuchAlgorithmException("JWT Header is Incorrect: " + parts[0]);
        }

        try {
			payload = new JSONObject(decode(parts[1]));
			if (((CharSequence) payload).isEmpty()) {
	            throw new JSONException("Payload is Empty: ");
	        }
	        if (!payload.has("exp")) {
	            throw new JSONException("Payload doesn't contain expiry " + payload);
	        }
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        signature = parts[2];
    }

    @Override
    public String toString() {
        return encodedHeader + "." + encode(payload) + "." + signature;
    }

    public boolean isValid() throws JSONException {
        return payload.getLong("exp") > (LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)) //token not expired
                && signature.equals(hmacSha256(encodedHeader + "." + encode(payload), SECRET_KEY)); //signature matched
    }

    public String getSubject() throws JSONException {
        return payload.getString("sub");
    }

    public List<String> getAudience() throws JSONException {
        JSONArray arr = payload.getJSONArray("aud");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            list.add(arr.getString(i));
        }
        return list;
    }

    private static String encode(JSONObject obj) {
        return encode(obj.toString().getBytes(StandardCharsets.UTF_8));
    }

    private static String encode(byte[] bytes) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    private static String decode(String encodedString) {
        return new String(Base64.getUrlDecoder().decode(encodedString));
    }

    /**
     * Sign with HMAC SHA256 (HS256)
     *
     * @param data
     * @return
     * @throws Exception
     */
    private String hmacSha256(String data, String secret) {
        try {

            //MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = secret.getBytes(StandardCharsets.UTF_8);//digest.digest(secret.getBytes(StandardCharsets.UTF_8));

            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(hash, "HmacSHA256");
            sha256Hmac.init(secretKey);

            byte[] signedBytes = sha256Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return encode(signedBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException ex) {
            Logger.getLogger(JWebToken.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

}