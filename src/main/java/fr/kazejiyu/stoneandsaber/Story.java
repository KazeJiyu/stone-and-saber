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
package fr.kazejiyu.stoneandsaber;

import java.util.Random;

import fr.kazejiyu.stoneandsaber.human.Human;
import fr.kazejiyu.stoneandsaber.human.characters.Merchant;
import fr.kazejiyu.stoneandsaber.human.characters.Ronin;
import fr.kazejiyu.stoneandsaber.human.characters.Traitor;
import fr.kazejiyu.stoneandsaber.human.characters.Yakuza;
import fr.kazejiyu.stoneandsaber.world.Fate;
import fr.kazejiyu.stoneandsaber.world.World;
import fr.kazejiyu.stoneandsaber.world.WorldSeed;
import fr.kazejiyu.stoneandsaber.world.WorldSeedParameters;

/**
 * The evolution of a {@link World} according to a certain {@link Fate}.
 * 
 * @author Emmanuel Chebbi
 */
public class Story
{
	public static final int NBR_GENERATIONS = 10;
	
	public static void main(String[] args)
	{
		WorldSeed seed = 
			new WorldSeedParameters()
				.samurais(randInRange(3, 10))
				.yakuzas(randInRange(3, 10))
				.merchants(randInRange(3, 5))
				.traitors(randInRange(0, 2))
				.toSeed();
		
		World world = World.randomized(seed);
		
		Fate fate = new Fate(world, NBR_GENERATIONS);
		addPossibleEvents(fate);
		
		fate.descendOnWorld();
	}
	
	
	private static void addPossibleEvents(Fate fate)
	{
		// 1 chance sur 10 qu'un humain se mette à boire
		
		fate.addEvent(.1, "Human drink", world ->
			world.anyone().ifPresent(Human::drink)
		);
		
		// 0.5 chance sur 10 qu'un ronin défie un yakuza  
		
		fate.addEvent(.05d, "Ronin challenge Yakuza", world -> 
			world.any(Ronin.class).ifPresent(ronin ->
				world.any(Yakuza.class).ifPresent(ronin::challenge)
			)
		);
		
		// 0.5 chance sur 10 qu'un yakuza défie un ronin  
		
		fate.addEvent(.05d, "Yakuza challenge Ronin", world -> 
			world.any(Yakuza.class).ifPresent(yakuza ->
				world.any(Ronin.class).ifPresent(yakuza::challenge)
			)
		);
		
		// 2 chances sur 10 qu'un yakuza extorque un marchand
		
		fate.addEvent(.2d, "Yakuza extort Merchant", world ->
			world.any(Yakuza.class).ifPresent(yakuza ->
				world.any(Merchant.class).ifPresent(yakuza::extort)
			)
		);
		
		// 2 chances sur 10 qu'un traître extorque un marchand
		
		fate.addEvent(.2d, "Traitor extort Merchant", world ->
			world.any(Traitor.class).ifPresent(traitor ->
				world.any(Merchant.class).ifPresent(traitor::extort)
			)
		);
		
		// 2 chances sur 10 qu'un samurai fasse un don à un marchand 
		
		fate.addEvent(.2d, "Ronin donate to Merchant", world -> 
			world.any(Ronin.class).ifPresent(ronin ->
				world.any(Merchant.class).ifPresent(ronin::donate)
			)
		);
		
		// update the world at each tick
		
		fate.addEvent(1d, "Make people live", World::makePeopleLive);
		
		// Try error handling
		
//		fate.addEvent(.4d, "Try error handling", world -> { willThrowAnException(); } );
	}
	
//	private static void willThrowAnException()
//	{
//		throw new IllegalArgumentException("Just STOP it !");
//	}
    
    private static final Random rand = new Random();
    
    private static int randInRange(int min, int max)
    {
        return rand.nextInt((max - min) + 1) + min;
    }
}
