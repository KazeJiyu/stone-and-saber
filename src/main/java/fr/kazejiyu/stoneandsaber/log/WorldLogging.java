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
package fr.kazejiyu.stoneandsaber.log;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import fr.kazejiyu.stoneandsaber.Story;
import fr.kazejiyu.stoneandsaber.world.World;

/**
 * This aspect logs information about {@link World}s, especially after their creation.
 * <br><br>
 *  It can be deactivated by merely setting {@link #LOGGING_IS_ACTIVATED} to false.
 * 
 * @author <a href="mailto:emmanuel.chebbi@outlook.fr">Emmanuel Chebbi</a>
 */
@Aspect
public final class WorldLogging
{
	/**
	 * Whether the aspect is currently activated.
	 * Set this value to false in order to disable human speaking.
	 */
	public static final boolean LOGGING_IS_ACTIVATED = true;
	
	/**
	 * The debug mode provides more details than classic logging mode.
	 * If this boolean is set to true, this aspect will be activated,
	 * even if {@link #LOGGING_IS_ACTIVATED} is set to false;
	 */
	public static final boolean DEBUG_MODE = true;

    @SuppressWarnings("unused")
	@Pointcut("if()")
    public static final boolean LOGGING_IS_ACTIVATED() {
        return DEBUG_MODE || LOGGING_IS_ACTIVATED;
    }

    @Pointcut("if()")
    public static final boolean DEBUG_MODE() {
        return DEBUG_MODE;
    }


	/* -------------------------------------------------------------------------------- * 
	 * **************************** HUMANS ******************************************** *
	 * -------------------------------------------------------------------------------- */
    
    @Before("LOGGING_IS_ACTIVATED() && call(* *World.randomized(..)) && withincode(* *Story.main(..))")
    public void logWorldIsAboutToBeGenerated()
    {
        System.out.printf(" ----- CREATING THE WORLD AND ITS INHABITANTS:%n%n");
    }
	
	
	@AfterReturning(
	        pointcut="LOGGING_IS_ACTIVATED() && call(static * *World.randomized(..)) "
	                + "&& withincode(* *Story.main(..))",
	        returning="world")
	public void logWorldHasBeenGenerated(World world)
	{
	    System.out.printf("%n ----- THE POWER IS OWNED BY:%n");
        System.out.printf("           le seigneur " + world.lord().name()+",%n");
        System.out.printf("           le clan " + world.clan().name()+".%n%n");
        
        System.out.printf(" ----- EVENTS THROUGHOUT %d GENERATIONS:%n%n", Story.NBR_GENERATIONS);
	}
}
