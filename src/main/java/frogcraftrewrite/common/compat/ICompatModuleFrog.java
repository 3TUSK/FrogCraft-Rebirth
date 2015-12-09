package frogcraftrewrite.common.compat;

import java.util.HashMap;
import java.util.Map;

public interface ICompatModuleFrog {
	
	void preInit();
	
	void init();
	
	void postInit();
	
	public static Map<String, ICompatModuleFrog> compats = new HashMap<String, ICompatModuleFrog>();

}
