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
import fr.kazejiyu.stoneandsaber.behaviors.weapon.impl.WeaponFactory;

/**
 * A human that has a {@link Weapon} and which can fight with others.
 * 
 * @author <a href="mailto:emmanuel.chebbi@outlook.fr">Emmanuel Chebbi</a>
 */
public abstract class Duelist extends SimpleHuman
{
    /**
     * The weapon used by the dueslist in order to fight
     */
	private Weapon weapon;
	
	/**
	 * Creates a new Duelist, specifiying its name, its money and its favorite beverage.
	 * <br><br>
	 * Its weapon is set to {@link WeaponFactory#createNoWeapon()}.
	 * 
	 * @param name
	 *             The name of the duelist.
	 * @param money
	 *             The starting amount of money.
	 * @param beverage
	 *             The favorite beverage.
	 */
	protected Duelist(String name, int money, String beverage)
	{
		this(name, money, beverage, WeaponFactory.createNoWeapon());
	}
	
	/**
	 * Creates a new Duelist with a given {@link Weapon}.
	 * 
	 * @param name
	 *             The name of the duelist.
	 * @param money
	 *             The starting amount of money.
	 * @param beverage
	 *             The favorite beverage.
	 * @param weapon
	 *             The weapon used. Cannot be <code>null</code>.
	 */
	protected Duelist(String name, int money, String beverage, Weapon weapon)
	{
		super(name, money, beverage);
		this.weapon = Objects.requireNonNull(weapon, "The weapon cannot be null. Use WeaponFactory.createNoWeapon() in order to set no weapon");
	}
	
	/**
	 * Returns the weapon used by the duelist.
	 * @return the weapon used by the duelist.
	 */
	public Weapon weapon()
	{
		return weapon;
	}
	
	/**
	 * Uses the weapon on the given {@link Duelist}.
	 * 
	 * @param target
	 * 			the GameCharacter on which use the Weapon
	 */
	public void useWeaponOn(Duelist target) {
		weapon.useOn(target);
	}
	
	/**
	 * Change weapon.
	 * <br>
	 * Caution : you must not pass null as a parameter to this function.
	 * If you want to give no weapon to the Character, prefer use
	 * {@link #unsetWeapon()} function.
	 *  
	 * @param weapon
	 * 			the weapon to use
	 * 
	 * @see #unsetWeapon()
	 * @see #getWeapon()
	 */
	public void setWeapon(Weapon weaponBehavior) {
		this.weapon = weaponBehavior;
	}

	/**
	 * Unsets the weapon.
	 * <br><br>
	 * Equivalent to 
	 * <pre>setWeapon( WeaponFactory.createNoWeapon() );</pre>
	 * 
	 * @see #setWeapon(Weapon)
	 * @see #getWeapon()
	 */
	public void unsetWeapon() {
		setWeapon(WeaponFactory.createNoWeapon());
	}

	/**
	 * Called when the duelist wins a duel.
	 * 
	 * @param amount
	 *             The amount of money earn.
	 */
	abstract void winDuel(int amount);
	
	/**
	 * Called when the duelist loses a duel.
	 * 
	 * @param amount
	 *             The amount of money losed.
	 */
	abstract void loseDuel(int amount);
	
	/**
	 * Returns the money earned when a duel is won against <code>loser</code>.
	 * 
	 * @param loser
	 *             The duelist that has been defeated.
	 *             
	 * @return the amount of money earned.
	 */
	protected int moneyWonDefeating(Duelist loser)
	{
		return loser.money();
	}
	
	/**
	 * Starts a battle against <code>opponent</code>.
	 * <br><br>
	 * The duelists use their weapon on each other while one of
	 * them is still alive.
	 * <br><br>
	 * When the duel ends, the winner's method {@link #winDuel(int)}
	 * is called, while loser's method {@link #loseDuel(int)} is called.
	 * <br><br>
	 * The called of this method is the first one to strike.
	 * 
	 * @param opponent
	 *             The duelist to battle.
	 *             
	 * @return whether the duel has been won.
	 */
	public boolean challenge(Duelist opponent)
	{
		while( this.isAlive() && opponent.isAlive() )
		{
			this.useWeaponOn(opponent);
			
			if( opponent.isAlive() )
				opponent.useWeaponOn(this);
			
			this.undergoTroubles();
			opponent.undergoTroubles();
		}
		
		Duelist winner = isAlive() ? this : opponent;
		Duelist loser = isAlive() ? opponent : this;

		int amount = winner.moneyWonDefeating(loser);
					
		loser.loseDuel(amount);
		winner.winDuel(amount);
		
		return winner == this;
	}
}
