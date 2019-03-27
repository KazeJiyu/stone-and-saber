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
package fr.kazejiyu.stoneandsaber.human.characters;

import static java.lang.Math.min;

import fr.kazejiyu.stoneandsaber.human.Human;

/**
 * A human that remembers the last {@link #MAX_HUMANS_IN_MEMORY} people she met.
 * <br><br>
 * A grandma is able to recognized traitors, and can gossip about them.
 * 
 * @author <a href="mailto:emmanuel.chebbi@outlook.fr">Emmanuel Chebbi</a>
 */
public class Grandma extends SimpleHuman
{
	private static final int MAX_HUMANS_IN_MEMORY = 30;
	
	private Human[] memorizedHumans = new Human[MAX_HUMANS_IN_MEMORY];
	private int humansInMemory = -1;
	
	Grandma(String name, int money)
	{
		super(name, money, "tisane");
	}

	public void meet(Human human)
	{
		humansInMemory = min(humansInMemory+1, MAX_HUMANS_IN_MEMORY);
		memorizedHumans[humansInMemory % MAX_HUMANS_IN_MEMORY] = human;
		
		say("Heureuse de te rencontrer, " + human.name());
	}
	
	public void gossip()
	{
		for( int i = 0 ; i <= humansInMemory ; i++ )
		{
			if( memorizedHumans[i] == null )
				break;
			
			if( memorizedHumans[i] instanceof Traitor )
				say("Je sais que "+memorizedHumans[i].name()+" est un traitre !");
			else
				say("Je crois que "+memorizedHumans[i].name()+" est ...");
		}
	}
}
