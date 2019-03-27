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
package fr.kazejiyu.stoneandsaber.behaviors.effect;

import fr.kazejiyu.stoneandsaber.behaviors.weapon.Weapon;
import fr.kazejiyu.stoneandsaber.human.Entity;
import fr.kazejiyu.stoneandsaber.human.HumanEntity;

/**
 * @brief Describes the effect of a Weapon 
 * 
 * Such effects may be link to a {@link Weapon} and thus
 * describes what happens to the target touched by the {@link Weapon}.
 * <br><br>
 * Indeed, an AttackEffect is made of three things : 
 * <ul>
 * 	<li>an {@link AttackAction}, which describes what to do when the Weapon strikes a {@link HumanEntity}.</li>
 *  <li>an {@link ElementalType}, which describes the type of the AttackEffect (used to manage weaknesses/resistances).</li>
 *  <li>an {@link AttackDuration}, which indicates whether the AttackEffect last in time</li>
 * </ul>
 * 
 * @author Emmanuel Chebbi
 */
public abstract class AttackEffect { // implements Cloneable {

	/**
	 * The type of the Effect
	 */
	private ElementalType type;
	/**
	 * Whether the Effect last in time
	 */
	private AttackDuration duration;
	/**
	 * The action to do
	 */
	private AttackAction action;
	
	/**
	 * Creates a new AttackEffect with a given {@link AttackDuration}.
	 * <br>
	 * The {@link ElementalType} is set to {@link ElementalType}.NONE and the {@link AttackDuration} to {@link AttackDuration}.INSTANTANEOUS.
	 * 
	 * @param action
	 * 			the action to do when the effect occurs
	 */
	public AttackEffect(AttackAction action) {
		this(action, AttackDuration.INSTANTANEOUS);
	}
	
	/**
	 * Creates a new AttackEffect with given {@link AttackAction} and {@link AttackDuration}.
	 * <br>
	 * The {@link ElementalType} is set to {@link ElementalType}.NONE.
	 * 
	 * @param action
	 * 			the action to do when the effect occurs
	 * @param duration
	 * 			the Duration of the effect
	 */
	public AttackEffect(AttackAction action, AttackDuration duration) {
		this(action, duration, ElementalType.NONE);
	}
	
	/**
	 * Creates a new AttackEffect with given {@link AttackAction}, {@link AttackDuration} and {@link ElementalType}.
	 * 
	 * @param action
	 * 			the action to do when the effect occurs
	 * @param duration
	 * 			the duration of the effect
	 * @param type
	 * 			the type of the effect
	 */
	public AttackEffect(AttackAction action, AttackDuration duration, ElementalType type) {
		this.action = action;
		this.duration = duration;
		this.type = type;
	}
	
	/**
	 * Returns the {@link AttackDuration} of the effect.
	 * @return the AttackDuration of the effect
	 */
	public AttackDuration getDuration() {
		return duration;
	}
	
	/**
	 * Returns the {@link ElementalType} of the effect.
	 * @return the Type of the effect
	 */
	public ElementalType getType() {
		return type;
	}
	
	/**
	 * Returns the {@link AttackAction} of the effect
	 * @return the AttackAction of the effect
	 */
	public AttackAction getAction() {
		return action;
	}
	
	/**
	 * Returns whether the effect is lasting.
	 * @return whether the effect is lasting
	 */
	public boolean isLasting() {
		return duration == AttackDuration.LASTING;
	}
	
	/**
	 * Returns whether the effect is instantaneous.
	 * @return whether the effect is instantaneous
	 */
	public boolean isInstantaneous() {
		return duration == AttackDuration.INSTANTANEOUS;
	}
	
	/**
	 * Returns whether the effect is over.
	 * @return whether the effect is over
	 */
	public abstract boolean isOver();
	
	/**
	 * Sets the {@link AttackAction} of the effect
	 * @param action the new AttackAction
	 */
	public void setAction( AttackAction action ) {
		this.action = action;
	}
	
	@Override
	public String toString() {
		return /*(duration == AttackDuration.LASTING ? "L_" : "I_") +*/ type + ":" + action.getAmount();
	}
	
//	@Override
//	public abstract AttackEffect clone();
	
	/**
	 * Applies the effect on a {@link HumanEntity}
	 * 
	 * @param target
	 * 			the HumanEntity who will be affected by the effect
	 */
	public abstract void applyOn(Entity target);
}
