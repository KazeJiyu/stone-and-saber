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
package fr.kazejiyu.stoneandsaber.behaviors.weapon.impl;

import java.util.Random;

import fr.kazejiyu.stoneandsaber.behaviors.effect.ElementalType;
import fr.kazejiyu.stoneandsaber.behaviors.effect.impl.LastingAttackEffect;
import fr.kazejiyu.stoneandsaber.behaviors.weapon.Weapon;
import fr.kazejiyu.stoneandsaber.behaviors.weapon.WeaponBuilder;
import fr.kazejiyu.stoneandsaber.exceptions.WeaponNotExist;
import fr.kazejiyu.stoneandsaber.human.HumanEntity;

/**
 * A Factory which creates {@link Weapon}s.
 * 
 * This class implements the Factory Pattern.
 * 
 * @navassoc - - * Weapon
 * @navassoc - uses - * WeaponBuilder
 * 
 * @author Emmanuel Chebbi
 */
public enum WeaponFactory {
	
	INSTANCE;

	/**
	 * Creates a Sword.
	 * <br><br>
	 * A Sword is called "Sword" and makes 200 physical damages.
	 * 
	 * @return a Sword.
	 */
	public static Weapon createSword() {
		return new WeaponBuilder()
				.named("Sword")
				.injuring(200)
				.build();
	}
	
	/**
	 * Creates a Burning Sword.
	 * <br><br>
	 * A Burning Sword is called "Burning Sword" and makes 250 physical damages.
	 * <br>
	 * Moreover, it adds it a {@link LastingAttackEffect} which {@link ElementalType}
	 * is {@link ElementalType#FIRE} and which makes 20 damage each turn during 3 turns. 
	 * 
	 * @return a Burning Sword.
	 */
	public static Weapon createBurningSword() {
		return new WeaponBuilder()
				.named("Burning Sword")
				.injuring(200)
				.burning(50, 3)
				.build();
	}
	
	/**
	 * Creates a Dagger.
	 * <br><br>
	 * A Dagger is called "Dagger" and makes 100 physical damages.
	 * 
	 * @return a Dagger.
	 */
	public static Weapon createDagger() {
		return new WeaponBuilder()
				.named("Dagger")
				.injuring(100)
				.build();
	}
	
	/**
	 * Creates a Bow.
	 * <br><br>
	 * A Bow is called "Bow" and make 150 physical damages.
	 * 
	 * @return a Bow.
	 */
	public static Weapon createBow() {
		return new WeaponBuilder()
				.named("Bow")
				.injuring(150)
				.build();
	}
	
	/**
	 * Creates a Cure.
	 * <br><br>
	 * A Cure is called "Sword". It makes no damage to the opponent. 
	 * However, it can heal a {@link HumanEntity} and thus increasing
	 * his life by 100.
	 * 
	 * @return a Cure.
	 */
	public static Weapon createCure() {
		return new WeaponBuilder()
				.named("Cure")
				.curing(100)
				.build();
	}
	
	/**
	 * Creates a NoWeapon.
	 * <br><br>
	 * This weapon defines the absence of any weapon.
	 * 
	 * @return a Sword.
	 */
	public static NoWeapon createNoWeapon() {
		return new NoWeapon();
	}
	
	/**
	 * Creates a new {@link Weapon} according to given name.
	 * 
	 * The method {@link #getAvailableObjects()} provides an array
	 * containing acceptable names.
	 * 
	 * @param weapon
	 * 			the name of the Weapon to create
	 * 
	 * @return the Weapon created
	 * 
	 * @throws RuntimeException if the name given doesn't match any Weapon
	 * 
	 * @see #getAvailableObjects()
	 */
	public static Weapon create(String weapon) {
		
		switch(weapon) {
		
		case "Sword" : 
			return createSword();
			
		case "Burning Sword" :
			return createBurningSword();
		
		case "Dagger" : 
			return createDagger();
		
		case "Bow" : 
			return createBow();
			
		case "Cure" :
			return createCure();
			
		case "No Weapon" :
			return createNoWeapon();
		
		default :
			throw new WeaponNotExist(weapon + " does not match any weapon. See WeaponFactory#getAvailableObjects for available ones.");
		}
	}
	
	public static Weapon any()
	{
		return create( getAvailableObjects() [new Random().nextInt(4)] );
	}
	
	/**
	 * Returns the names which can be used to create a {@link Weapon}
	 * using the {@link #create(String)} function.
	 * 
	 * @return the name which can be used to create a Weapon
	 */
	public static String[] getAvailableObjects() {
		return new String[] { "Sword", "Burning Sword", "Bow", "Dagger", "Cure", "No Weapon" };
	}
}
