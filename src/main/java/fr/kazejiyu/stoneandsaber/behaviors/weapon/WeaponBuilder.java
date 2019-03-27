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

import fr.kazejiyu.stoneandsaber.behaviors.effect.AttackAction;
import fr.kazejiyu.stoneandsaber.behaviors.effect.AttackEffect;
import fr.kazejiyu.stoneandsaber.behaviors.effect.ElementalType;
import fr.kazejiyu.stoneandsaber.behaviors.effect.impl.AlterLife;
import fr.kazejiyu.stoneandsaber.behaviors.effect.impl.InstantaneousAttackEffect;
import fr.kazejiyu.stoneandsaber.behaviors.effect.impl.LastingAttackEffect;

/**
 * Makes easier to create a {@link Weapon} with many {@link AttackEffect}s.
 * <br><br>
 * Notice that each method return a reference to the {@link WeaponBuilder}
 * to make you able to link statement.
 * <br><br>
 * Thus, following statement is valid :
 * <br>
 * <pre>{@code   new WeaponBuilder()
 * 	.named("MasterSword")
 * 	.injure(9999);}</pre>
 * 
 * @author Emmanuel Chebbi
 */
public class WeaponBuilder {
	
	/**
	 * The {@link Weapon} built;
	 */
	private Weapon build;
	
	/**
	 * Initializes a new {@link Weapon} called ""
	 */
	public WeaponBuilder() {
		this("");
	}
	
	/**
	 * Initializes a new {@link Weapon} with a given name.
	 * 
	 * @param name
	 * 			the name of the weapon
	 */
	public WeaponBuilder(String name) {
		build = new Weapon(name);
	}
	
	/**
	 * Sets {@link Weapon}'s name.
	 * 
	 * @param name
	 * 			the name of the Weapon
	 * 
	 * @return the current WeaponMaterials
	 */
	public WeaponBuilder named(String name) {
		build.setName(name);
		return this;
	}
	
	/**
	 * Sets the {@link Weapon} enable to injure.
	 * 
	 * @param amount
	 * 			the amount of life to take
	 * 
	 * @return the current WeaponMaterials
	 */
	public WeaponBuilder injuring(int amount) {
		AttackEffect effect = new InstantaneousAttackEffect(
				new AlterLife(AttackAction.Type.DECREASE, amount), 
				ElementalType.PHYSICAL );
		
		build.addEffect( effect );
		return this;
	}
	
	/**
	 * Sets the {@link Weapon} enable to cure.
	 * 
	 * @param amount
	 * 			the amount of life to cure
	 * 
	 * @return the current WeaponMaterials
	 */
	public WeaponBuilder curing(int amount) {
		
		AttackEffect effect = new InstantaneousAttackEffect( 
				new AlterLife(AttackAction.Type.INCREASE, amount), 
				ElementalType.FAIRY );
		
		build.addEffect( effect );
		return this;
	}
	
	/**
	 * Sets the {@link Weapon} enable to burn.
	 * 
	 * @param amount
	 * 			the amount of damages to make
	 * @param timeDuration
	 * 			the duration of the effect
	 * 
	 * @return the current WeaponMaterials
	 */
	public WeaponBuilder burning(int amount, int timeDuration) {
		
		AttackEffect effect = new LastingAttackEffect(
				new AlterLife(AttackAction.Type.DECREASE, amount), 
				ElementalType.FIRE, timeDuration );
		
		build.addEffect( effect );
		return this;
	}

	/**
	 * Builds a new {@link Weapon}.
	 */
	public Weapon build() {
		return build.clone();
	}
}
