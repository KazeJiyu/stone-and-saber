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

import fr.kazejiyu.stoneandsaber.behaviors.weapon.impl.WeaponFactory;
import fr.kazejiyu.stoneandsaber.event.EventBus;
import fr.kazejiyu.stoneandsaber.human.Human;
import fr.kazejiyu.stoneandsaber.world.Name;
import rx.Subscription;
import rx.functions.Action1;

public final class HumanFactory
{
	private final int MIN_MONEY;
	private final int MAX_MONEY;
	
	private final Name name;
	private final Random random;
	
//	private final PublishSubject <Object> humanFlux = PublishSubject.create();
	
	private final EventBus <Human> humanFlow = new EventBus<>();
	
	public HumanFactory()
	{
		this(0, 100);
	}
	
	public HumanFactory(int minMoney, int maxMoney)
	{
		if( minMoney < 0 )
			throw new IllegalArgumentException("A human cannot have less than 0 bling.");
		
		name = new Name();
		random = new Random();
		
		MIN_MONEY = minMoney;
		MAX_MONEY = maxMoney;
	}
	
	public Merchant createMerchant()
	{
		Merchant merchant = new Merchant(name.generate(), randomMoney());
		humanFlow.post(merchant);
		
		return merchant;
	}
	
	public Yakuza createYakuza(Clan clan)
	{
		Yakuza yakuza = new Yakuza(name.generate(), randomMoney(), "rhum", clan, WeaponFactory.any());
		humanFlow.post(yakuza);
		
		return yakuza;
	}
	
	public Samurai createSamurai(Lord lord)
	{
		Samurai samurai = new Samurai(name.generate(), randomMoney(), "saké", lord, WeaponFactory.any());
		humanFlow.post(samurai);
		
		return samurai;
	}
	
	public Traitor createTraitor(Lord lord)
	{
		Traitor traitor = new Traitor(name.generate(), randomMoney(), "thé noir", lord);
		humanFlow.post(traitor);
		
		return traitor;
	}
	
	/**
	 * Registers a <code>callback</code> that will be called each time an instance 
	 * of the specified class is created.
	 * 
	 * @param eventClassToListenFor
	 * 			The class to listen for.
	 * @param callback
	 * 			The callback to call each time an instance of the specified class is created.
	 * 
	 * @return the corresponding subscription
	 */
	public Subscription onCreate(final Class <? extends Human> eventClassToListenFor, Action1 <Human> callback)
	{
		return humanFlow.register(eventClassToListenFor, callback);
	}
	
	/**
	 * Returns a random amount of money.
	 */
	private int randomMoney()
	{
		return random.nextInt((MAX_MONEY - MIN_MONEY) + 1) + MIN_MONEY;
	}
}
