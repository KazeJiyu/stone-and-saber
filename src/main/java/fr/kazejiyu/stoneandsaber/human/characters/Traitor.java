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

import java.util.Random;

import fr.kazejiyu.stoneandsaber.behaviors.weapon.impl.WeaponFactory;
import fr.kazejiyu.stoneandsaber.human.Human;

public class Traitor extends Samurai implements Bully
{
	private double betrayalLevel = 0d;
	
	public Traitor(String name, int money, String beverage, Lord lord)
	{
		super(name, money, beverage, lord, WeaponFactory.createDagger());
	}

	@Override
	public boolean canExtort(Merchant victim)
	{
		return betrayalLevel < 3;
	}
	
	@Override
	public int extort(Merchant victim)
	{
		if( ! canExtort(victim) ) {
			return 0;
		}
		
		int amount = victim.money();
		earnMoney( victim.loseEverything() );
		
		betrayalLevel++;
		
		return amount;
	}
	
	public void makeFriend(Human gullible)
	{
		makeFriend(gullible, new Random().nextInt(money()+1));
	}
	
	public void makeFriend(Human gullible, int donation)
	{
		if( donation < 0 || money() < donation )
			throw new IllegalArgumentException("Cannot donate "+donation+" while having only"+money());
		
		loseMoney(donation);
		gullible.earnMoney(donation);
		
		betrayalLevel -= donation / 10d;
		betrayalLevel = max(0, betrayalLevel);
	}
}
