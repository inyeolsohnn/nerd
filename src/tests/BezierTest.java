package tests;

public class BezierTest {
	public static void main(String[] args) {
		float t = 0.000f;
		int i = 0;
		for (; t <= 1; i++) {
			System.out.println(i);
			t += 0.001f;
		}
	}
}
