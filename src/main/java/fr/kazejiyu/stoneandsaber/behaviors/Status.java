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
package fr.kazejiyu.stoneandsaber.behaviors;

import static java.lang.Math.max;

import java.util.Set;
import java.util.TreeSet;

import fr.kazejiyu.stoneandsaber.behaviors.effect.AttackEffect;
import fr.kazejiyu.stoneandsaber.behaviors.effect.ElementalType;

/**
 * A set of stats.
 * 
 * This class should be used to describe the status of an Entity.
 * 
 * @author Emmanuel Chebbi
 */
public class Status {
	
	/**
	 * Default value for life.
	 */
	public static final int DEFAULT_LIFE = 500;
	/** 
	 * Default value for armor.
	 */
	public static final int DEFAULT_ARMOR = 100;
	/**
	 * Default value for strength.
	 */
	public static final int DEFAULT_STRENGTH = 100;
	
	/**
	 * The life points
	 */
	private int life;
	
	/**
	 * The maximum life points
	 */
	private int lifeMax;
	
	/**
	 * The strength
	 */
	private int strength;
	
	/**
	 * The armor
	 */
	private int armor;
	
	/**
	 * The weaknesses 
	 */
	private Set <ElementalType> weaknesses;	// TODO weaknesses aren't currently used
	
	/**
	 * The weaknesses 
	 */
	private Set <ElementalType> resistances; // TODO resistances aren't currently used
	
	/**
	 * Creates a new Status with DEFAULT values.
	 */
	public Status() {
		this(DEFAULT_LIFE, DEFAULT_STRENGTH, DEFAULT_ARMOR);
	}
	
	/**
	 * Creates a new Status with given values.
	 */
	public Status( int life, int strength, int armor ) {
		this.life = life;
		this.armor = armor;
		this.lifeMax = life;
		this.strength = strength;
		this.weaknesses = new TreeSet<>();
		this.resistances = new TreeSet<>();
	}

	/**
	 * @return the life
	 */
	public int life() {
		return life;
	}

	/**
	 * @return the strength
	 */
	public int strength() {
		return strength;
	}

	/**
	 * @return the armor
	 */
	public int armor() {
		return armor;
	}
	
	/**
	 * @return the weaknesses
	 */
	public Set<ElementalType> weaknesses() {
		return weaknesses;
	}
	
	/**
	 * @return the resistances
	 */
	public Set<ElementalType> resistances() {
		return resistances;
	}
	
	/**
	 * Returns whether the entity is alive (i.e. if life > 0)
	 * @return whether the life is greater than 0
	 */
	public boolean isAlive() {
		return life > 0;
	}
	
	/**
	 * Returns whether a {@link ElementalType} is a weakness.
	 * 
	 * @param possibleWeakness
	 * 			the Type to check
	 * 
	 * @return whether a Type is a weakness
	 */
	public boolean isAWeakness(ElementalType possibleWeakness) {
		return weaknesses.contains(possibleWeakness);
	}
	
	/**
	 * Returns whether a {@link ElementalType} is a resistance.
	 * 
	 * @param possibleResistance
	 * 			the Type to check
	 * 
	 * @return whether a Type is a resistance
	 */
	public boolean isAResistance(ElementalType possibleResistance) {
		return resistances.contains(possibleResistance);
	}

	/**
	 * @param life the life to set
	 */
	public void setLife(int life) {
		this.life = max(0,life);
	}

	/**
	 * @param strength the strength to set
	 */
	public void setStrength(int strength) {
		this.strength = strength;
	}

	/**
	 * @param armor the armor to set
	 */
	public void setArmor(int armor) {
		this.armor = armor;
	}
	
	/**
	 * Adds a new weakness.
	 * <br><br>
	 * Notice that if the weakness is already set as a resistance,
	 * it will be removed from the list of resistances.
	 * 
	 * @param weakness
	 * 			the weakness to add
	 * 
	 * @return false is the weakness was already set, true otherwise
	 */
	public boolean addWeakness(ElementalType weakness) {
		if( resistances.contains(weakness) )
			resistances.remove(weakness);
			
		return weaknesses.add(weakness);
	}
	
	/**
	 * Removes a weakness.
	 * 
	 * @param weakness
	 * 			the weakness to remove 
	 * 
	 * @return true is the weakness was set, false otherwise
	 */
	public boolean removeWeakness(ElementalType weakness) {
		return weaknesses.remove(weakness);
	}
	
	/**
	 * Adds a new resistance.
	 * <br><br>
	 * Notice that if the resistance is already set as a weakness,
	 * it will be removed from the list of weaknesses.
	 * 
	 * @param resistance
	 * 			the resistance to add 
	 * 
	 * @return false is the resistance was already set, true otherwise
	 */
	public boolean addResistance(ElementalType resistance) {
		if( weaknesses.contains(resistance) )
			weaknesses.remove(resistance);
			
		return resistances.add(resistance);
	}
	
	/**
	 * Removes a resistance.
	 * 
	 * @param resistance
	 * 			the resistance to remove 
	 * 
	 * @return true is the resistance was set, false otherwise
	 */
	public boolean removeResistance(ElementalType resistance) {
		return resistances.remove(resistance);
	}
	
	/**
	 * The action called when damages are received.
	 * @param damage
	 * 			the amount of damages
	 */
	public void onDamage(int damage) {
		life -= damage;
		
		if( life < 0 )
			life = 0;
	}
	
	/**
	 * The function called when the entity is heal
	 * @param ease
	 */
	public void onHeal(int ease) {
		life += ease;
		
		if( life > lifeMax )
			life = lifeMax;
	}
	
	public void onFire(int damage) {
		onDamage(damage);
	}
	
	@Override
	public String toString() {
		return "HP:"+life+" STR:"+strength+" ARM:"+armor;
	}
	
	/**
	 * Changes the state according to the effect received.
	 * 
	 * @param effect
	 * 			the effect received
	 */
	public void undergo(AttackEffect effect) {
		/*switch( effect.getType() ) {
		case CURE: onHeal(effect.getAmount());
			break;
		case FIRE: onFire(effect.getAmount());
			break;
		case GEL:
			break;
		case PHYSICAL: onDamage(effect.getAmount());
			break;
		case POISON:
			break;
		case THUNDER:
			break;
		default:
			break;
		
		}*/
	}
}
