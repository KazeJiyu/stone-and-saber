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

import fr.kazejiyu.stoneandsaber.behaviors.Status;

/**
 * An quantified action realized on a {@link Status}.
 * <br><br>
 * The word <em>quantified</em> means that the action will either {@code INCREASE} 
 * or {@code DECREASE} a {@link Status}' attribute by a defined amount.
 * <br><br>
 * The type of the action is defined by an instance of {@link AttackAction.Type}.
 * <br>
 * The concrete process realized by the action is defined by overriding the 
 * {@link #alterStatus(Status)} method. * 
 * 
 * 
 * @author Emmanuel Chebbi.
 */
public abstract class AttackAction {
	
	/**
	 * The value of the action
	 */
	private int amount;
	
	/**
	 * The type of the action
	 */
	private Type action;
	
	/**
	 * Whether the {@link AttackAction} increases or decreases a {@link Status}'s attribute. 
	 * @author Emmanuel Chebbi
	 */
	public enum Type {
		INCREASE,
		DECREASE
	}
	
	/**
	 * Creates a new {@code AttackAction}.
	 * 
	 * @param action
	 * 			If the action will {@code INCREASE} or {@code DECREASE} a {@link Status}' attribute.
	 * @param amount
	 * 			The amount to {@code INCREASE} / {@code DECREASE} by.
	 */
	public AttackAction(Type action, int amount) {
		this.action = action;
		this.amount = amount;
	}

	/**
	 * @return if the action will {@code INCREASE} or {@code DECREASE} a {@link Status}' attribute.
	 */
	public Type getAction() {
		return action;
	}
	
	/**
	 * @return the amount to {@code INCREASE} / {@code DECREASE} by.
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Alter one or more {@link Status}'s attribute.
	 * 
	 * @param status
	 * 			The {@link Status} to alter.
	 */
	public abstract void alterStatus(Status status);
}
