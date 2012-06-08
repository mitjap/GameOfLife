package org.puzi.gameoflife.utils;

public interface Transformer<I,O> {
	public O transform(I in); 

}
