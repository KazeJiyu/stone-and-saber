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

import static java.util.Collections.shuffle;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import fr.kazejiyu.stoneandsaber.human.Human;
import fr.kazejiyu.stoneandsaber.human.characters.Clan;
import fr.kazejiyu.stoneandsaber.human.characters.HumanFactory;
import fr.kazejiyu.stoneandsaber.human.characters.Lord;
import fr.kazejiyu.stoneandsaber.human.kingdom.Kingdom;

/**
 * The world where humans live.
 * <br><br>
 * Instances of this class are aimed to store {@link Human} instances.
 * 
 * @author Emmanuel Chebbi
 */
public class World
{
	private Lord lord;
	
	private Clan clan;
	
	private Kingdom kingdom;
	
	private List <Human> people = new LinkedList<>();

	/**
	 * Makes human creation easier
	 */
	private HumanFactory factory;
	
	/**
	 * Creates a new <code>World</code> that will be populated thanks
	 * to specified <code>factory</code>
	 * 
	 * @param factory
	 * 			Used to create the new humans. Must not be <code>null</code>.
	 */
	private World(HumanFactory factory) 
	{
		this.factory = requireNonNull(factory);
	}
	
	/**
	 * Creates a new <code>World</code> randomized from given <code>seed</code>.
	 * 
	 * @param seed
	 * 			Specifies the parameters of the new world.
	 * 
	 * @return the world created.
	 */
	public static World randomized(WorldSeed seed)
	{		
		Name name = new Name();
		HumanFactory factory = new HumanFactory();
		
		World world = new World(factory);
		
		String lordName = name.generate();
		world.lord = new Lord(lordName, new Kingdom());
		
		String clanName = name.generate();
		world.clan = new Clan(clanName);
		
		// Populate the world
		
		for(int i = 0 ; i < seed.samurais ; i++)
			world.people.add( factory.createSamurai(world.lord) );
		
		for(int i = 0 ; i < seed.yakuzas ; i++)
			world.people.add( factory.createYakuza(world.clan) );
		
		for(int i = 0 ; i < seed.merchants ; i++)
			world.people.add( factory.createMerchant() );
		
		for(int i = 0 ; i < seed.traitors ; i++)
			world.people.add( factory.createTraitor(world.lord) );
		
		// Each time a human is created, he will be added to the population
		factory.onCreate(Human.class, world.people::add);
		
		return world;
	}
	
	public Lord lord()
	{
		return lord;
	}
	
	public Clan clan() 
	{
		return clan;
	}
	
	public Kingdom kingdom()
	{
		return kingdom;
	}
	
	public HumanFactory factory()
	{
		return factory;
	}

	/**
	 * Returns a random <code>Human</code> of any class.
	 * @return a random <code>Human</code> of any class.
	 */
	public Optional <Human> anyone()
	{
		return any(Human.class);
	}
	
	/**
	 * Returns a random human of class <code>human</code>, if any.
	 * 
	 * @param human
	 * 			The class of the instance to look for.
	 * 
	 * @return a random human of class <code>human</code>, if any.
	 */
	public <T extends Human> Optional <T> any(Class <T> human)
	{
		return people.stream()
					 .filter(human::isInstance)
					 .filter(Human::isAlive)
					 .map(human::cast) 
 					 // shuffle the collection ...
					 .collect(collectingAndThen(toList(), collected -> {
						 shuffle(collected);
						 return collected.stream();
					 }))
					 // ... in order to pick a random human
					 .findAny();
	}
	
	public void makePeopleLive()
	{
		Iterator <Human> itP = people.iterator();
		
		while( itP.hasNext() )
		{
			Human p = itP.next();
			p.undergoTroubles();
			
			if( ! p.isAlive() ) {
				itP.remove();
				bury(p);
			}
		}
	}
	
	/**
	 * Called each time a {@code Human} dies.
	 * 
	 * At this time, this method has no effect. It's aimed at making the code
	 * more "aspect aware" by providing a join point ...
	 * 
	 * @param dead
	 * 			A human that has just died. This method should not be called more 
	 * 			than once with the same {@code Human}.
	 */
	private void bury(Human dead) {
		// nothing here, just an available joinpoint
	}
	
	@Override
	public String toString()
	{
		return "Lord=["+lord.name()+"] Clan=["+clan.name()+"]";
	}
}
