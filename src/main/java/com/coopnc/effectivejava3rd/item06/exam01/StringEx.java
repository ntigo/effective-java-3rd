package main.java.com.coopnc.effectivejava3rd.item06.exam01;

public class StringEx {
	public static void main(String[] args) {
		String s1 = new String("a");
		String s2 = s1;
		System.out.println(s1==s2); // true

		String s = new String("abc");
		for(int i=0; i<10; i++){
			String old = s;
			s = new String("abc");
			System.out.println(old==s); // false
		}
	}
}
