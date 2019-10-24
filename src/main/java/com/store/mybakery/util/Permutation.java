package com.store.mybakery.util;

import java.util.List;

/**
 * This class is created for calculating multiple available permutations of pack size.
 * @author yogesh
 *
 */
public class Permutation {
	
	/**
	 * This function is responsible for creating all the available permutations of pack size to populate 
	 * best match product for customer with lowest price. 
	 * @param a	
	 * @param k
	 * @param combination
	 * @return
	 */
	public static List<Integer[]> permute(Integer[] a, int k, List<Integer[]> combination) {
		if(k == a.length) {
			Integer packQtyArray[] = new Integer[a.length];
			for (int i = 0; i < a.length; i++) {
				packQtyArray[i] = a[i];
			}
			combination.add(packQtyArray);
		}else{
			for (int i = k; i < a.length; i++){
				int temp = a[k];
				a[k] = a[i];
				a[i] = temp;
				permute(a, k + 1, combination);
				temp = a[k];
				a[k] = a[i];
				a[i] = temp;
			}
		}
		return combination;
	}
}