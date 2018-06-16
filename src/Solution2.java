
/**
 * @author kalbhor
 *	Que: Find multiplicative pairs, 
 *Question not noted at the time of test so not clearly known.
 */
public class Solution2 {

	public static void main(String[] args) {
		Solution2 solution = new Solution2();
		int[] A = {0,1,2,2,3,5};
		int[] B = {500000, 500000, 0,0,0,20000};
		/*int[] A = {0,2,1,2,3,5};
		int[] B = {500000, 0, 500000, 0,0,20000};*/
		
		System.out.println(solution.solution(A,B));
	}
	
	public int solution(int[] A, int[] B) {
		float[] C = new float[B.length];
		int pairs =0;
		
		//populate C
		for(int i=0; i< A.length; i++) {
			C[i]= ((float)A[i] + ((float)B[i]/1000000));
		}
		
		//find multiplicative pairs;
		for(int j=0; j<C.length; j++) {
			for(int k=j+1; k< C.length; k++) {
				if(C[j] <= C[k]) {
					float mul = (float)(C[j] * C[k]);
					float add = (float) (C[j] + C[k]);
				
					if(mul >= add ){
						pairs++;
					}
				}
			}
		}
		
		//returning result;
		if(pairs > 1000000000) {
			return 1000000000;
		} else {
			return pairs;	
		}
    }

}
