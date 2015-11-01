package tritusk.trichemistry.equation;

import tritusk.trichemistry.matter.Molecule;

public interface Reaction {
	
	Molecule[] reactants();
	
	Molecule[] products();
	
	ReactionCondition[] conditions();

}
