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

import static java.lang.Math.max;

import java.io.PrintStream;

import fr.kazejiyu.stoneandsaber.human.Human;
import fr.kazejiyu.stoneandsaber.human.HumanEntity;

/**
 * Implementation of {@link Human} interface.
 * 
 * @author <a href="mailto:emmanuel.chebbi@outlook.fr">Emmanuel Chebbi</a>
 */
abstract class SimpleHuman extends HumanEntity
{
    /**
     * The name of the human.
     */
	private final String name;
	
	/**
	 * The favorite beverage of the human.
	 */
	private final String beverage;
	
	/**
	 * The current amount of money of the human.
	 */
	private int money;
	
	/**
	 * A unique id identify each entity. It is used to distinguish them within equals() method.
	 */
	private int id;
	
	/**
	 * A static counter to automatically generate entities' id.
	 * Should not be modified outside of the constructor.
	 */
	private static int nextInstanceId = 0;
	
	SimpleHuman(String name, int money, String beverage)
	{
		super(); 
		
		this.name = name;
		this.money = money;
		this.beverage = beverage;
		
		this.id = nextInstanceId++;
	}
	
	@Override
	public String name()
	{
		return name;
	}

	@Override
	public String beverage()
	{
		return beverage;
	}

	@Override
	public int money()
	{
		return money;
	}

	@Override
	public void drink()
	{
//		say("Ahhh, un bon verre de "+beverage+ " ! GLOUPS !");
	}
	
	@Override
	public final void greet()
	{
		greet(System.out);
	}
	
	@Override
	public final void greet(PrintStream out)
	{
		say(greetMessage());
	}
	
	/**
	 * Returns the message to be said by the entity when {@link #greet()} is called.
	 * <br><br>
	 * This method may be overriden by subclasses in order to change the greet message.
	 * 
	 * @return the message to be said when <code>greet()</code> is called
	 */
	protected String greetMessage()
	{
		return "Hello ! My name is "+name+" and I love drinking "+beverage+".";
	}
	
	@Override
	public final void say(String text) 
	{
		say(text, System.out);
	}
	
	@Override
	public final void say(String text, PrintStream out)
	{
		out.println("("+this+") - " + text);
	}
	
	@Override
	public int loseMoney(int amount)
	{
		if( amount < 0 )
			throw new IllegalArgumentException("Cannot lose "+amount+" while having only"+money());
		
		int oldMoney = money;
		
		money -= amount;
		money = max(0, money);
		
		return oldMoney;
	}
	
	@Override
	public int loseEverything()
	{
		return loseMoney(money);
	}
	
	@Override
	public int earnMoney(int amount)
	{
		money += amount;
		return money;
	}
	
	@Override
	public String toString()
	{
		return "["+this.getClass().getSimpleName().charAt(0)+"] "+name;
	}

	@Override
	public final int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public final boolean equals(Object obj)
	{
		if( this == obj )
			return true;

		if( !(obj instanceof SimpleHuman) )
			return false;
		
		return id == ((SimpleHuman) obj).id;
	}
}
