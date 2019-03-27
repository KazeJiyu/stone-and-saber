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
package fr.kazejiyu.stoneandsaber.human;

import java.util.HashSet;
import java.util.Set;

import fr.kazejiyu.stoneandsaber.behaviors.Status;
import fr.kazejiyu.stoneandsaber.behaviors.effect.AttackEffect;

/**
 * A human that has a {@link Status}.
 */
public abstract class HumanEntity implements Human
{
	/**
	 * The status of the Character
	 */
	private final Status status = new Status();

	/**
	 * {@link AttackEffect}s which trouble the HumanEntity
	 */
	private Set <AttackEffect> troubles = new HashSet<>();
	
	@Override
	public int life()
	{
		return status.life();
	}

	@Override
	public Status status()
	{
		return status;
	}

	@Override
	public boolean isAlive()
	{
		return status.isAlive();
	}

	@Override
	public void addTrouble(AttackEffect trouble)
	{
		troubles.add(trouble);
	}

	@Override
	public void undergoTroubles()
	{
		for( AttackEffect trouble : troubles )
		{
			trouble.applyOn(this);

			if( trouble.isOver() )
				troubles.remove(trouble);
		}
	}
}
