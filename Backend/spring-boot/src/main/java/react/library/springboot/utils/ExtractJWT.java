package react.library.springboot.utils;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class ExtractJWT {
    public static  String JWTExtraction(String token , String extraction){
        token.replace("Bearer","");
        String[] chunks= token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String payload = new String(decoder.decode(chunks[1]));

        String  [] entries = payload.split(",");
        Map<String,String> keyValMap = new HashMap<String,String>();

        for(String entry:entries){
            String[] keyvalue = entry.split(":");
            if(keyvalue[0].equals(extraction)){
                int remove=1;
                if(keyvalue[1].endsWith("}")){
                    remove=2;
                }
                //Processing to remove email from sub
                keyvalue[1]=keyvalue[1].substring(0,keyvalue[1].length()-remove);
                keyvalue[1]=keyvalue[1].substring(1);
                keyValMap.put(keyvalue[0],keyvalue[1]);
            }
        }

        if (keyValMap.containsKey(extraction)) {
            return keyValMap.get(extraction);
        }
        return null;
    }
}
