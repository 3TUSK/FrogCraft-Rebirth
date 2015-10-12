package frogcraftrewrite.common.lib.util;

public class FrogMath {
	
	/**
	 * This has been annoying me for a very long time. Now it's time to end it.
	 * @param base
	 * @param exp
	 * @return
	 */
	public static double log(double base, double exp) {
		return (double)(Math.log(exp)/Math.log(base));
	}
	
	public static double cot(double radius) {
		return (double)(1/Math.tan(radius));
	}
	
	public static double sec(double radius) {
		return (double)(1/Math.cos(radius));
	}
	
	public static double csc(double radius) {
		return (double)(1/Math.sin(radius));
	}

}
