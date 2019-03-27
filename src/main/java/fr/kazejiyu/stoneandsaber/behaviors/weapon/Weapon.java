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
package fr.kazejiyu.stoneandsaber.behaviors.weapon;

import java.util.HashSet;
import java.util.Set;

import fr.kazejiyu.stoneandsaber.behaviors.effect.AttackEffect;
import fr.kazejiyu.stoneandsaber.behaviors.usable.UsableOnObject;
import fr.kazejiyu.stoneandsaber.human.HumanEntity;

/**
 * A simple Weapon.
 * 
 * A Weapon has : 
 * <ul>
 * 	<li>a name</li>
 * 	<li>a set of {@link AttackEffect}</li>
 * </ul>
 * 
 * @navassoc - - 0..* AttackEffect
 * 
 * @author Emmanuel Chebbi
 */
public class Weapon implements Cloneable, UsableOnObject <HumanEntity> {

	/**
	 * The name of the Weapon
	 */
	private String name;
	
	/**
	 * The {@link AttackEffect} of the Weapon.
	 */
	private Set <AttackEffect> attackEffects;
	
	/**
	 * Creates a new Weapon with given name
	 * @param name
	 * 			the name of the Weapon
	 */
	public Weapon(String name) {
		this.name = name;
		this.attackEffects = new HashSet<>();
	}
	
	/**
	 * @return the name of the Weapon
	 */
	public String name() {
		return name;
	}
	
	/**
	 * Returns the {@link AttackEffect} of the Weapon.
	 * @return the AttackEffect of the Weapon
	 */
	public Set <AttackEffect> effects() {
		return attackEffects;
	}

	/**
	 * @param name the name to set
	 */
	void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Adds an {@link AttackEffect} to the Weapon.
	 * @param effect
	 * 			the AttackEffect to add
	 */
	public void addEffect(AttackEffect effect) {
		attackEffects.add(effect);
	}
	
	/**
	 * Removes an {@link AttackEffect} from the Weapon.
	 * @param effect
	 * 			the AttackEffect to remove
	 */
	public void removeEffect(AttackEffect effect) {
		attackEffects.remove(effect);
	}

	/**
	 * Uses the Weapon on a {@link HumanEntity}.
	 * <br>
	 * The instantaneous effects are immediately apply
	 * on the target, whereas the lasting effects are
	 * only added to the target's list of troubles.
	 * 
	 *  @param target
	 *  		the HumanEntity on whom use the Weapon
	 */
	@Override
	public void useOn(HumanEntity target) {
		for( AttackEffect effect : attackEffects ) { 
			if( effect.isInstantaneous() )
				effect.applyOn(target);
			else
				target.addTrouble(effect);	// if the effect last in time, the target has to keep it in mind
		}
	}
	
	/**
	 * Creates a new Weapon with the same effects.
	 */
	@Override
	public Weapon clone() {
		Weapon clone = new Weapon(this.name);
		clone.attackEffects = new HashSet<>();
		
		for( AttackEffect effect : this.attackEffects )
			clone.attackEffects.add(effect);	// TODO : check if effects are really copied this way...
			
		return clone;
	}

	/**
	 * Returns a String containing the name of the Weapon and 
	 * its {@link AttackEffect}s' description.
	 */
	@Override
	public String toString() {
		StringBuilder out = new StringBuilder(name);
		out.append("(");
		
		for( AttackEffect effect : attackEffects ) {
			out.append(effect);
			out.append(", ");
		}
		
		return  attackEffects.isEmpty() ? out.substring(0, out.length()-1) 
										: out.substring(0, out.length()-2) + ")";
	}
}
