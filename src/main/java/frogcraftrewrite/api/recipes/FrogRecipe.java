package frogcraftrewrite.api.recipes;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FrogRecipe {
	
	/**
	 * @return The corresponding name of machine
	 */
	String machine() default "";
	
	ReactionType reactionType() default ReactionType.CHEMICAL;
	
	public enum ReactionType {
		/**
		 * Represents that this reaction does not yield new substance.
		 */
		PHYSICAL,
		/**
		 * Represents that this reaction does yield new substance.
		 */
		CHEMICAL;
	}
	
	
}
