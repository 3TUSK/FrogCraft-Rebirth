package frogcraftrewrite.common.tile;

public interface ICanProcess {
	
	boolean canConsume();
	
	void consume();
	
	void process();
	
	boolean canOutput();
	
	void output();

}
