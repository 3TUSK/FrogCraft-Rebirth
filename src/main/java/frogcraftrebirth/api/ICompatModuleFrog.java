package frogcraftrebirth.api;

import java.util.HashMap;
import java.util.Map;

public interface ICompatModuleFrog {
	
	void init();
	
	public static Map<String, ICompatModuleFrog> compats = new HashMap<String, ICompatModuleFrog>();

}
