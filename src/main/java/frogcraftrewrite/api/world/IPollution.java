package frogcraftrewrite.api.world;

import net.minecraft.world.World;

/**
 * @author 3TUSK
 * Created at 10:56:14 PM Aug 31, 2015 EST 2015
 */
public interface IPollution {
	
	/**
	 * @return Debug information. Will be used?
	 */
	String[] info();
	
	/**
	 * @return The range which will be polluted, measured in radius.
	 */
	int radius();
	
	/**
	 * @return The core method, which will affect the nearby environment.
	 */
	void pollute(World world);

}
