/*******************************************************************************
 * Copyright (C) 2017 Emmanuel Chebbi
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package fr.kazejiyu.stoneandsaber.world;

import java.util.Random;
import java.util.function.Consumer;

import rx.Observable;
import rx.observables.ConnectableObservable;

/**
 * Processes random events on a {@link World}.
 * 
 * @author <a href="mailto:emmanuel.chebbi@outlook.fr">Emmanuel Chebbi</a>
 */
public class Fate
{
	/**
	 * 
	 */
	private ConnectableObservable <Double> obs;
	
	/**
	 * The world on which the events occur.
	 */
	private final World world;
	
	/**
	 * Initialize the fate of the <code>word</code>.
	 * <br><br>
	 * This world will undergo events for the specified
	 * number of <code>generations</code>.
	 * 
	 * @param world
	 * 			The world to be affected by the fate.
	 * @param generations
	 * 			The number of generations.
	 */
	public Fate(World world, int generations)
	{
		this.world = world;
		
		Random rdm = new Random();
		
		obs =
			Observable
				.fromCallable(rdm::nextDouble)
				.repeat(generations)
				.share()
				.publish();
		
		applyAtTheEnd();
	}
	
	/**
	 * Registers a new event that can occur on the world
.	 * associated to this fate.
	 * 
	 * @param probability
	 * 			The probability of event's occurrence.
	 * @param event
	 * 			
	 * @return the current instance. May be used to chain method calls.
	 */
	public final Fate addEvent(double probability, String name, Consumer <World> event)
	{
		obs
			.flatMap(Observable::just)
			.filter(p -> p <= probability)
			.subscribe( p -> event.accept(world),
						e -> System.err.println("\nSORRY, AN ERROR OCCURED WHILE PROCESSING :\n"
								+ "   event was : " + name + "\n"
								+ "   error message is : " + e.getMessage() + "\n")
			);
		
		return this;
	}
	
	
	private final Fate applyAtTheEnd()
	{
		obs
			.flatMap(p -> Observable.empty())
			.subscribe( p -> {},
						Throwable::printStackTrace,
						() -> System.out.printf("%n ----- GENERATION IS OVER ! Hope you enjoyed it.%n"));
						
		return this;
	}
	
	/**
	 * Applies events on the world; 
	 */
	public void descendOnWorld()
	{
		obs.connect();
	}
	
}
