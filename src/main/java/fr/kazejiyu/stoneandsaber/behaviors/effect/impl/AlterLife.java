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

import fr.kazejiyu.stoneandsaber.behaviors.Status;
import fr.kazejiyu.stoneandsaber.behaviors.effect.AttackAction;

/**
 * An {@link AttackAction} which modify {@link Status}'s life.
 * @author Emmanuel Chebbi
 */
public class AlterLife extends AttackAction {

	/**
	 * @param action
	 * 			Whether the life will be increased or decreased
	 * @param amount
	 * 			The quantity of live to take/give
	 */
	public AlterLife(Type action, int amount) {
		super(action, amount);
	}

	@Override
	public void alterStatus(Status status) {
		int life = status.life();
		
		if( getAction() == Type.INCREASE )
			life += getAmount();
		else
			life -= getAmount();
				
		status.setLife(life);
	}

}
