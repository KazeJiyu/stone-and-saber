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

import java.util.Objects;

import fr.kazejiyu.stoneandsaber.behaviors.weapon.Weapon;

public class Samurai extends Ronin
{
	/**
	 * A Samurai swear fidelity to one and only one lord.
	 */
	private final Lord lord;

	/**
	 * Creates a new <code>Samurai</code>, with specified <code>name</code>,
	 * <code>beverage</code> and <code>lord</code>.
	 * 
	 * @param name
	 * 			The name of the samurai. Must not be <code>null</code>.
	 * @param beverage
	 * 			The favorite beverage. Must not be <code>null</code>.
	 * @param lord
	 * 			The lord of the samurai. Must not be <code>null</code>.
	 */
	Samurai(String name, int money, String beverage, Lord lord, Weapon weapon)
	{
		super(name, money, beverage, weapon);
		
		this.lord = Objects.requireNonNull(lord, "A samurai needs to know its Lord. Otherwise, it is a Ronin !");
		this.lord.engage(this);
	}

	/**
	 * Returns the lord of the samurai.
	 * @return the lord of the samurai
	 */
	public Lord lord()
	{
		return lord;
	}

	public void drink(String beverage)
	{
		say("A samurai must stay focused! I do not have time to drink.");
	}
	
	@Override
	public String greetMessage()
	{
		return "My name is "+name()+". I am a fidel samurai serving Lord "+lord.name()+".";
	}
}
