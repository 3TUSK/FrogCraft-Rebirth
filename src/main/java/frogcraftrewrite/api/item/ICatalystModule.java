package frogcraftrewrite.api.item;

import tritusk.trichemistry.matter.Catalyst;

/**
 * Modulized cataylst provides a flexible way to extends your advanced chemical reactor.
 * Implement this in a TileEntity.
 * @since 12:39 PM Aug 20th (EST) 2015
 * @author 3TUSK
 */
public interface ICatalystModule {

	/**
	 * @return The catalyst represented by this module.
	 */
	Catalyst getCatalyst();

	/**
	 * @return True if this catalyst can influence this reaction.
	 */
	boolean match();

	/**
	 * @return A double which represents the acceleration rate target on the reaction. Return a negative number to deaccelerate the reation.
	 */
	double accelerate();

}