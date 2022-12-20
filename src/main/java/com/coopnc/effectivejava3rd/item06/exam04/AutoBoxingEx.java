package main.java.com.coopnc.effectivejava3rd.item06.exam04;

public class AutoBoxingEx {

	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		sum();
		long end = System.currentTimeMillis();

		System.out.println("걸린 시간 : " + (end-start));
	}

	private static long sum(){
		long sum = 0L; // Long, long
		for(long i=0; i<=Integer.MAX_VALUE; i++){
			sum += i;
		}
		return sum;
	}
}
