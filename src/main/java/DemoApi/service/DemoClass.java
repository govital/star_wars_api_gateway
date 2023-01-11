package DemoApi.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DemoClass {


    public boolean demo(String s1, String s2) {
        if(s1 == null || s1.isEmpty() ||
            s2 == null || s2.isEmpty()){
            return false;
        }
        if(s1.length()!=s2.length()){
            return false;
        }
        Map<Character, Integer> instances = new HashMap<>();

        for(char c: s1.toCharArray()) {
            if (!instances.containsKey(c)) {
                instances.put(c, 0);
            }
            instances.put(c, instances.get(c)+1);
        }

        for(char c: s2.toCharArray()) {
            if (!instances.containsKey(c)) {
                return false;
            }
            instances.put(c, instances.get(c)-1);
            if(instances.get(c)==0){
                instances.remove(c);
            }
        }
        if(!instances.isEmpty()){
            return false;
        }
        return true;

    }


}
