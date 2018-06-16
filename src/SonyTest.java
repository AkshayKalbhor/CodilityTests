import java.util.*;

/**
 * @author kalbhor
 * Question: Write a program which accepts a string and an offset by which the characters in the string should be shifted.
 * eg: string "DEF" and an offset of 3 should return "GHI"
 * Spaces and other symbols should remain the same. 
 */
public class SonyTest {

	public static void main(String[] a) {
		/*System.out.println(encodeDecode("DEF ghi", 3, true));*/
		System.out.println(encodeDecode("ZAB cde@#$@#$", 40, true));
		
	}
	
	/**
	 * Encode function to encode the string
	 * @param input : Input string to be encoded
	 * @param offset : Offset to shift the characters within the string.
	 * @return @String : Encoded string.
	 */
	/*public static String encode(String input, int offset) {
		String result = new String();
		
		List<String> alpList = new ArrayList<String>();
		List<String> capAlpList = new ArrayList<String>();

		for(char c='a'; c <= 'z'; c++) {
			alpList.add(String.valueOf(c));
		}
		for(char c='A'; c <= 'Z'; c++) {
			capAlpList.add(String.valueOf(c));
		}
		
		for(int i =0; i<input.length(); i++) {
			
			//check unicode to find if it is small or caps
			int aChar=input.charAt(i); 

			if(aChar>=97 && aChar<=123)        
			{
				//if Small
				int index = alpList.indexOf(Character.toString(input.charAt(i)));
				if(index + offset > alpList.size()) {
					int remainder = alpList.size() % index;
					offset -= remainder;
				}
				index += offset;
				result = result.concat(String.valueOf(alpList.get(index)));
			} else if(aChar>=65 && aChar<=96) {
				//if CAPS
				int indexCap = capAlpList.indexOf(Character.toString(input.charAt(i)));
				if(indexCap + offset > capAlpList.size()) {
					int remainder = capAlpList.size() % indexCap;
					offset -= remainder;
				}
				indexCap += offset;
				result = result.concat(String.valueOf(capAlpList.get(indexCap)));
			} else {
				if(aChar ==32) {
					result = result.concat(" ");
				}
			}
		}
		
		return result;
	}*/
	
	/**
	 * Method to return lists containing master List containing a-z A-Z
	 * @return
	 */
	public static List<String> getMasterData() {
		List<String> alpList = new ArrayList<String>();

		for(char c='a'; c <= 'z'; c++) {
			alpList.add(String.valueOf(c));
		}
		for(char c='A'; c <= 'Z'; c++) {
			alpList.add(String.valueOf(c));
		}
		return alpList;
	} 
	
	/**
	 * 
	 * @param input
	 * @param offset
	 * @param encodeDecodeFlag True: Encode , False: Decode
	 * @return Encoded or decoded String.
	 */
	public static String encodeDecode(String input, int offset, boolean encodeDecodeFlag) {
		String result = new String();
		
		List<String> alpList = getMasterData();
		
		if(offset > 26) {
			offset = offset % 26;
		}
		
		for(int i =0; i<input.length(); i++) {
			//check unicode to find if it is small or caps
			int aChar=input.charAt(i); 

			/*
			 * processing of small case alphabets
			 */
			if(aChar>=97 && aChar<=122) {
				int index = alpList.indexOf(Character.toString(input.charAt(i)));
				
				if(encodeDecodeFlag) {
					//Encoding of small characters
					if(index + offset > 25) {
						int remainder = 25 % index;
						int tempOffset = offset- remainder;
						index = tempOffset;
						//index starts from 0 so have to reduce 1
						index--;
					} else {
						index += offset;
					}
					result = result.concat(String.valueOf(alpList.get(index)));
					
				} else {
					//Decoding of small characters
					//starts at 0
					if(index - offset < 0) {
						//last char of small case letters
						int remainder = offset-index-1;
						index = 25-remainder;
					} else {
						index -= offset;
					}
					
					result = result.concat(String.valueOf(alpList.get(index)));
				}
				
			} 
			
			/*
			 * processing of CAPITAL alphabets
			 */
			else if(aChar>=65 && aChar<=90) {
				int index = alpList.indexOf(Character.toString(input.charAt(i)));
				
				if(encodeDecodeFlag) {
					//Encoding of CAPS
					if(index + offset > 51) {
						int remainder =26;
						remainder += 51 % index;
						index = remainder+offset;
						//index starts from 0 so have to reduce 1
						index--;
					} else {
						index += offset;
					}
					result = result.concat(String.valueOf(alpList.get(index)));
				} else {
					//Decoding of CAPS
					//starts at 0
					if(index - offset < 26) {
						//First char of Upper case letters is at 26
						int remainder = index-26;
						int newoffset = offset - remainder; 
						index = 52-newoffset;
					} else {
						index -= offset;
					}
					
					result = result.concat(String.valueOf(alpList.get(index)));
				}
			} else {
				//in case of space or other symbols.
					result = result.concat(String.valueOf(input.charAt(i)));
			}
		}
		
		return result;		
	}
	
	
	/*public int solution(int[] A) {
        // write your code in Java SE 8
        ArrayList<Integer> pits = new ArrayList<Integer>();
        
        int i=0;
        int j=0;
        int k =0;
        for(i=0; i<A.length; i++) {
        	int downFall = 0;
            int highRise = 0;
            for(j=i+1; j<A.length-1; j++) {
                while (A[i] > A[j] ) {
                    int downFallTemp = A[i]-A[j];
                    if(downFallTemp > downFall) {
                        downFall = downFallTemp;
                        j++;
                    } else if(downFallTemp <= downFall) {
                    	j--;
                    	break;
                    }
                } break;
            }
            
            if(downFall > 0) {
	            for(k=j+1; k < A.length-2; k++) {
	                while (A[j] < A[k]) {
	                    int highRiseTemp = giveDiff(A[k],A[j]);
	                    if(highRiseTemp > highRise) {
	                        highRise = highRiseTemp;
	                        k++;
	                    } else if(highRiseTemp <= highRise) {
	                    	k--;
	                    	break;
	                    }
	                } break;
	            }
            }
            
            if(downFall > 0 && highRise > 0) {
            	pits.add(givemin(downFall, highRise));
            	i = j;
            }
        }
            
        int result =0;
        for (Integer a: pits) {
        	if(a > result)	{
        		result = a;
        	}
        }
        return result;
    }
    
    private int givemin(int a, int b) {
    	 if(a > b) {
             return b;
         } else return a;
	}
    
    private int giveDiff(int a, int b) {
    	if(a - b > 0) {
    		return a-b;
    	} else {
    		return b-a;
    	}
    }*/

}
