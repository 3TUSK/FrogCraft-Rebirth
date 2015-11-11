package info.tritusk.tritchemlab.equation;

import info.tritusk.tritchemlab.matter.Molecule;

public interface Reaction {
	
	Molecule[] reactants();
	
	Molecule[] products();
	
	ReactionCondition[] conditions();

}
