import java.util.*;

public class AmazonTest {

	public static void main(String[] args) {
		AmazonTest a = new AmazonTest();
		//int[] A = {0,1,2,2,3,5};
		//String A = "wawaglknagagwunagkwkwagl";
		String A = "abcabcb";
		List<String> result = a.subStringsLessKDist(A,3);
		List<String> result1 = a.subStringsLessKDist1(A,4);
		for (String str : result) {
			System.out.println(str);	
		}
		System.out.println("----------- 1 solution -----------");
		for (String str : result1) {
			System.out.println(str);	
		}
		
		
	}
	
	/*
	 * Question: Write a function which returns distinct substrings of length k from the given input string.
	 * The substring should have one or more characters repeated in itself.
	 * 
	 * eg : Input string= abcabcb and K=4
	 * then the function  should return 4 substrings as "abca", "bcab", "cabc" and "abcb"
	 * 
	 * eg : Input string= abcabcb and K=3
	 * then the function  should return 1 substring as "bcb"
	 * 
	 * --submitted Solution 
	 * */
	public List<String> subStringsLessKDist1(String inputString, int num) 
	{
        // WRITE YOUR CODE HERE
        List<String> resultList = new ArrayList<String>();
        if(inputString.length() >= num) {
            for(int i=0; i<=inputString.length()-num; i++) {
                String sub = inputString.substring(i,num+i);
                for(int j=0; j< sub.length(); j++) {

                	char temp = sub.charAt(j);
                    for(int k=0;k<sub.length()-1;k++) {
                    	if(k!=j) {
                    		if(sub.charAt(k) == temp) {
                    			if(!resultList.contains(sub)) {
                    				resultList.add(sub);
                    				break;
                    			}
                    		}
                    	}
                    }
                }
            }
        } else {
        	System.out.println("No substring less than k distinct");
        }
        return resultList;
    }
	
		 // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
		public List<String> subStringsLessKDist(String inputString, int num)
		{
	        // WRITE YOUR CODE HERE
	        List<String> resultList = new ArrayList<String>();
	        if(inputString.length() >= num) {
	            for(int i=0; i<=inputString.length()-num; i++) {
	            	int count=0;
	                String sub = inputString.substring(i,num+i);
	                for(int j=0; j< sub.length(); j++) {

	                	char temp = sub.charAt(j);
	                    for(int k=0;k<sub.length()-1;k++) {
	                    	if(k!=j) {
	                    		if(sub.charAt(k) == temp) {
	                    			if(!resultList.contains(sub)) {
	                    				resultList.add(sub);
	                    				break;
	                    			}
	                    		}
	                    	}
	                    }
	                	
	                	for(int k=0;k<sub.length();k++) {
	                    	if(k!=j) {
	                    		if(sub.charAt(k) == temp) {
	                    		   count++; 
	                    		}
	                    	}
	                    }
	                	if(count>1) break;
	                }
	                if(!resultList.contains(sub) && count==2) {
                        resultList.add(sub);
                        break;
	                }
	               
	            }
	        }
	        return resultList;
	    }

}
