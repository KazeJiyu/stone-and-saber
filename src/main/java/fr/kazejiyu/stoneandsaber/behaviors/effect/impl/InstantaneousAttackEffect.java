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
package fr.kazejiyu.stoneandsaber.behaviors.effect.impl;


import fr.kazejiyu.stoneandsaber.behaviors.effect.AttackAction;
import fr.kazejiyu.stoneandsaber.behaviors.effect.AttackDuration;
import fr.kazejiyu.stoneandsaber.behaviors.effect.AttackEffect;
import fr.kazejiyu.stoneandsaber.behaviors.effect.ElementalType;
import fr.kazejiyu.stoneandsaber.human.Entity;

/**
 * An AttackEffect which is Instantaneous.
 * 
 * @author Emmanuel Chebbi
 */
public class InstantaneousAttackEffect extends AttackEffect {
	
	/**
	 * Creates a new InstantaneousAttackEffect with a given {@link AttackAction}.
	 * <br>
	 * The {@link AttackDuration} is set to INSTANTANEOUS and the {@link ElementalType} is set to NONE.
	 * 
	 * @param action
	 * 			the action to do when the effect occurs
	 */
	public InstantaneousAttackEffect(AttackAction action) {
		super(action, AttackDuration.INSTANTANEOUS);
	}
	
	/**
	 * Creates a new InstantaneousAttackEffect with given {@link AttackAction} and {@link ElementalType}.
	 * <br>
	 * The {@link AttackDuration} is set to INSTANTANEOUS.
	 * 
	 * @param action
	 * 			the action to do when the effect occurs
	 * @param type
	 * 			the type of the effect
	 */
	public InstantaneousAttackEffect(AttackAction action, ElementalType type) {
		super(action, AttackDuration.INSTANTANEOUS, type);
	}
	
	@Override
	public boolean isOver() {
		return false;
	}

	@Override
	public boolean isInstantaneous() {
		return true;
	}
	
	@Override 
	public boolean isLasting() {
		return false;
	}

	@Override
	public void applyOn(Entity target) {
		getAction().alterStatus( target.status() );
	}
}
