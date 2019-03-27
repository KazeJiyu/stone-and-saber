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

import java.util.Random;

import fr.kazejiyu.stoneandsaber.behaviors.weapon.Weapon;

public class Ronin extends Duelist
{
	private int honnor = 1;
	
	Ronin(String name, int money, String beverage, Weapon weapon)
	{
		super(name, money, beverage, weapon);
	}

	public int honnor()
	{
		return honnor;
	}
	
	public int donate(Merchant merchant)
	{
		return donate(merchant, new Random().nextInt(money()+1));
	}
	
	public int donate(Merchant merchant, int amount)
	{
		this.loseMoney(amount);
		
		merchant.earnMoney(amount);
		honnor++;
		
		return amount;
	}
	
	@Override
	public String greetMessage()
	{
		return name()+". Ronin. "+money()+" sous.";
	}
	
	@Override
	public void winDuel(int amount)
	{
		honnor++;
		earnMoney(amount);
	}
	
	@Override
	public void loseDuel(int amount)
	{
		honnor--;
		loseMoney(amount);
	}
}
