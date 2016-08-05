package frogcraftrebirth.common.lib.util;

public class FrogMath {
	
	/**
	 * This has been annoying me for a very long time. Now it's time to end it.
	 * @param base
	 * @param exp
	 * @return log<sub>base</sub>(exp)
	 */
	public static double logBase(double base, double exp) {
		return (double)(Math.log(exp)/Math.log(base));
	}
	
	public static double cot(double radians) {
		return (double)(1/Math.tan(radians));
	}
	
	public static double sec(double radians) {
		return (double)(1/Math.cos(radians));
	}
	
	public static double csc(double radians) {
		return (double)(1/Math.sin(radians));
	}
	
	public static String toFancyString(int value) {
		try {
			return (String) Class.forName("ic2.core.util.Util").getDeclaredMethod("toSiString", double.class, int.class).invoke(null, value, 2);
		} catch (Exception e) {
			e.printStackTrace();
			return Integer.toString(value);
		}
	}

}
