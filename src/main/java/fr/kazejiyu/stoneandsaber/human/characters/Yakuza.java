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

public class Yakuza extends Duelist implements Bully
{
	private int prestige = 0;
	
	private final Clan clan;

	Yakuza(String name, int money, String beverage, Clan clan, Weapon weapon)
	{
		super(name, money, beverage, weapon);
		
		this.clan = Objects.requireNonNull(clan, "Le clan du yakuza doit être spécifié.");
		this.clan.engage(this);
	}
	
	@Override
	public String greetMessage()
	{
		return "J'suis "+name()+", un yakuza du clan "+clan.name()+" ! J'ai "+money()+" bling"
			+ (money() > 1 ? "s" : "") + ".";
	}
	
	public int prestige()
	{
		return prestige;
	}
	
	public Clan clan()
	{
		return clan;
	}
	
	@Override
	public boolean canExtort(Merchant victim)
	{
		return victim.money() > 0;
	}
	
	@Override
	public int extort(Merchant victim)
	{
		int amount = victim.money();
		
		earnMoney( victim.loseEverything() );
		prestige++;
		
		return amount;
	}
	
	@Override
	public void winDuel(int amount)
	{
		prestige++;
	}
	
	@Override
	public void loseDuel(int amount)
	{
		prestige--;
		loseMoney(amount);
	}
}
