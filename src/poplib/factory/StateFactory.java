package poplib.factory;

import poplib.State;

public interface StateFactory {
	
	public State nextState(State state);
}
