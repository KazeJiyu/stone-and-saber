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
package fr.kazejiyu.stoneandsaber.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import fr.kazejiyu.stoneandsaber.human.Human;
import fr.kazejiyu.stoneandsaber.human.characters.Duelist;
import fr.kazejiyu.stoneandsaber.human.characters.Merchant;
import fr.kazejiyu.stoneandsaber.human.characters.Ronin;
import fr.kazejiyu.stoneandsaber.human.characters.Traitor;
import fr.kazejiyu.stoneandsaber.human.characters.Yakuza;

/**
 * This aspect makes {@link Human}s talk when they process an action.
 * <br><br>
 *  It can be disactivated by merely setting {@link #TALKING_IS_ALLOWED} to false.
 * 
 * @author <a href="mailto:emmanuel.chebbi@outlook.fr">Emmanuel Chebbi</a>
 */
@Aspect
public final class HumanTalking
{
	/**
	 * Whether the aspect is currently activated.
	 * Set this value to false in order to disable human speaking.
	 */
	public static final boolean TALKING_IS_ALLOWED = true;
    
    /**
     * The debug mode provides more details than classic logging mode.
     * If this boolean is set to true, this aspect will be activated,
     * even if {@link #TALKING_IS_ALLOWED} is set to false;
     */
    public static final boolean DEBUG_MODE = true;

	@SuppressWarnings("unused") // remove dead code warning
	@Pointcut("if()")
    public static final boolean TALKING_IS_ALLOWED() {
        return DEBUG_MODE || TALKING_IS_ALLOWED;
    }

    @Pointcut("if()")
    public static final boolean DEBUG_MODE() {
        return DEBUG_MODE;
	}


	/* -------------------------------------------------------------------------------- * 
	 * **************************** HUMANS ******************************************** *
	 * -------------------------------------------------------------------------------- */
	
	
	@After("TALKING_IS_ALLOWED() && execution(* Human.drink(..)) && target(human)")
	public void logHumanDrunk(Human human)
	{
		human.say("Ahhh, a good glass of "+human.beverage()+ "! Gasp!");
	}
	
	@After("TALKING_IS_ALLOWED() && call(* fr.kazejiyu.stoneandsaber.world.World.bury(..)) && args(human) "
        + "&& withincode(* fr.kazejiyu.stoneandsaber.world.World.makePeopleLive(..))")
	public void logHumanDeath(Human human)
	{
	    System.out.println("  *"+human.name()+" is dead.");
	}
	
	/* -------------------------------------------------------------------------------- * 
	 * **************************** YAKUZAS ******************************************* *
	 * -------------------------------------------------------------------------------- */
	
	
	@After("TALKING_IS_ALLOWED() && execution(* Yakuza.winDuel(..)) && target(yakuza)")
	public void logYakuzaWonDuel(Yakuza yakuza)
	{
		yakuza.say("Huhu, you fool! Did you really think you could defeat me?");
	}
	
	@After("TALKING_IS_ALLOWED() && execution(* Yakuza.loseDuel(..)) && target(yakuza)")
	public void logYakuzaLooseDuel(Yakuza yakuza)
	{
		yakuza.say("Nooo, how on earth have I lost?");
	}
	
	@AfterReturning(
			pointcut="TALKING_IS_ALLOWED() && execution(int Yakuza.extort(Merchant)) && target(yakuza) && args(victim)",
			returning="amount")
	public void logYakuzaExtortMerchant(Yakuza yakuza, Merchant victim, int amount)
	{
		victim.say("O rage! O despair! I am as poor as a church mouse!");
		
		yakuza.say("Hehe, I am so vilain. I stole "+amount+" blings from "+victim.name()+"."
				+ " I now have "+yakuza.money()+" blings.");
	}


	/* -------------------------------------------------------------------------------- * 
	 * **************************** RONINS ******************************************** *
	 * -------------------------------------------------------------------------------- */
	
	
	@After("TALKING_IS_ALLOWED() && execution(* Ronin.winDuel(..)) && target(ronin)")
	public void logRoninWonDuel(Ronin ronin)
	{
		ronin.say("Victory is mine!");
	}
	
	@After("TALKING_IS_ALLOWED() && execution(* Ronin.loseDuel(..)) && target(ronin)")
	public void logRoninLooseDuel(Ronin ronin)
	{
		ronin.say("Argg, I didn't keep up...");
	}
	
	@AfterReturning(
			pointcut="TALKING_IS_ALLOWED() "
				+ "&& call(* Ronin.donate(Merchant,int)) && target(ronin) && args(merchant,amount)",
			returning="amount")
	public void logRoninDonateMerchant(Ronin ronin, Merchant merchant, int amount)
	{
		ronin.say("Hey, merchant, here's "+amount+" blings.");
		merchant.say("Oh, thank you for this donation, your Highness. Now, I have "+merchant.money()+" blings.");
	}


	/* -------------------------------------------------------------------------------- * 
	 * **************************** DUELISTS ****************************************** *
	 * -------------------------------------------------------------------------------- */
	

	@Before("TALKING_IS_ALLOWED() && execution(* Duelist.useWeaponOn(Duelist)) && target(assaillant) && args(opponent)")
	public void logDuelistUseWeaponOn(Duelist assaillant, Duelist opponent)
	{
		assaillant.say("I use " + assaillant.weapon().name() + " against " + opponent.name()+"!");
		
		if( DEBUG_MODE ) {
		    System.out.println("    ["+opponent.name()+".status="+opponent.status()+"]\n");
		}
	}
	
	@Before("TALKING_IS_ALLOWED() && execution(* Duelist.challenge(Duelist)) && target(assaillant) && args(opponent)")
	public void logDuelistChallenge(Duelist assaillant, Duelist opponent)
	{
		assaillant.say("I challenge you, " +opponent.name()+"!");
		
		if( DEBUG_MODE ) {
            System.out.println("    ["+assaillant.name()+".weapon="+assaillant.weapon()+"]");
            System.out.println("    ["+opponent.name()+".weapon="+opponent.weapon()+"]\n");
		}
	}
	
	@Around("call(boolean Traitor.canExtort(Merchant)) "
		+ "&& target(traitor)"
		+ "&& args(merchant)")
	public boolean logTraitorCanExtort(ProceedingJoinPoint pjp, Traitor traitor, Merchant merchant) throws Throwable
	{
		boolean canExtort = (boolean) pjp.proceed();
		
		if( ! canExtort )
			traitor.say("Sigh, cannot extort "+merchant.name() + ".");
		
		return canExtort;
	
	}
	
	@Around("execution(int Traitor.extort(Merchant)) "
		+ "&& target(traitor)"
		+ "&& args(victim)")
	public int logTraitorExtort(ProceedingJoinPoint pjp, Traitor traitor, Merchant victim) throws Throwable
	{
		int stealed = (int) pjp.proceed();
		
		traitor.say("Fool "+victim.name()+", you didn't see me coming! I stole you "+stealed+" blings.");
		
		return stealed;
	}
	
	@After("call(* Traitor.makeFriend(Human,int)) "
		+ "&& target(traitor)"
		+ "&& args(gullible,amount)")
	public void logTraitorMakeFriend(Traitor traitor, Human gullible, int amount) throws Throwable
	{
		traitor.say(gullible.name()+", you seem very friendly. Here you are, take these "+amount+" blings.");
		gullible.say("Thank you! You're trully generous.");
		traitor.say("Thanks to this gullible my betrayal level is now ");
	}

}
