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
 * An {@link AttackEffect} which is Lasting.
 * 
 * @author Emmanuel Chebbi
 *
 */
public class LastingAttackEffect extends AttackEffect {

	/**
	 * The default value for time duration
	 */
	public static final int DEFAULT_TIME_DURATION = 3;
	/**
	 * The duration in time of the effect
	 */
	private int timeDuration;
	/**
	 * The time remaining for the effect
	 */
	private int timeRemaining;
	
	/**
	 * Creates a new LastingAttackEffect with a given {@link AttackAction}.
	 * <br>
	 * The {@link AttackDuration} is set to LASTING.
	 * The time duration is set to {@link #DEFAULT_TIME_DURATION}.
	 * 
	 * @param action
	 * 			the action to do when the effect occurs
	 */
	public LastingAttackEffect(AttackAction action) {
		this(action, DEFAULT_TIME_DURATION);
	}

	/**
	 * Creates a new LastingAttackEffect with given {@link AttackAction} and time duration.
	 * <br>
	 * The {@link AttackDuration} is set to LASTING.
	 * 
	 * @param action
	 * 			the action to do when an error occurs
	 * @param timeDuration
	 * 			the duration in time of the effect
	 */
	public LastingAttackEffect(AttackAction action, int timeDuration) {
		super(action, AttackDuration.LASTING);
		this.timeDuration = timeDuration;
		this.timeRemaining = timeDuration;
	}
	
	/**
	 * @param action
	 * @param duration
	 * @param type
	 */
	public LastingAttackEffect(AttackAction action, ElementalType type, int timeDuration) {
		super(action, AttackDuration.LASTING, type);
		this.timeDuration = timeDuration;
		this.timeRemaining = timeDuration;
	}

	/**
	 * @return the timeDuration
	 */
	public int getTimeDuration() {
		return timeDuration;
	}

	/**
	 * @param timeDuration the timeDuration to set
	 */
	public void setTimeDuration(int timeDuration) {
		this.timeDuration = timeDuration;
	}
	
	protected int decreaseRemainingTime() {
		return --timeRemaining;
	}
	
	public int getTimeRemaining() {
		return timeRemaining;
	}
	
	@Override
	public final boolean isOver() {
		return timeRemaining <= 0;
	}

	@Override
	public void applyOn(Entity target) {
		getAction().alterStatus( target.status() );
		timeRemaining--;
	}
}
