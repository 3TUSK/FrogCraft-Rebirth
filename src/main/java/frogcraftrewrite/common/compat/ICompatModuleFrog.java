package frogcraftrewrite.common.compat;

import java.util.ArrayList;
import java.util.List;

public interface ICompatModuleFrog {
	
	void init();
	
	void postInit();
	
	public static List<Class<? extends ICompatModuleFrog>> compatList = new ArrayList<Class<? extends ICompatModuleFrog>>();

}
