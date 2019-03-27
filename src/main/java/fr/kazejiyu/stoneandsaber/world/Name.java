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
package fr.kazejiyu.stoneandsaber.world;

import java.util.Random;

/**
 * As of now, this class has a rather bad design (it should be like the util.Random one).
 * <br><br>
 * This is a utility class intended to generate random names for world's humans.
 * <br><br>
 * It is only used within the {@link World} class.
 * 
 * @author Emmanuel Chebbi
 */
public class Name
{
	private static final String[] DEFAULT_SYLLABLES = new String[] 
	        { "a", "ab", "bo", "bu", "to", "li", "pa", "mi", "fu", "tu", "ri", "so", "zu", "si", "il", "ly", "an" };
	
	
	private final String[] syllables;
	
	private final Random random = new Random();
	
	public Name()
	{
		this(DEFAULT_SYLLABLES);
	}
	
	public Name(String[] syllables)
	{
		this.syllables = syllables.clone();
	}
	
	/**
	 * Generates a pseudo-random name, that has between 2 and 4 syllables.
	 * 
	 * @return the generated name
	 */
	public String generate()
	{
		return generate(random.nextInt((4 - 2) + 1) + 2);
	}
	
	/**
	 * Generates a pseudo-random name, that has the specified
	 * number of syllables.
	 * 
	 * @param nbrSyllables
	 * 			The number of syllables of the name
	 * 
	 * @return the generated name
	 */
	public String generate(int nbrSyllables)
	{
		StringBuilder sb = new StringBuilder();
		
		for( int i = 0 ; i < nbrSyllables ; i++ )
			sb.append( syllables[new Random().nextInt(syllables.length)] );
		
		String name = sb.toString();
		
		return name.substring(0,1).toUpperCase() + name.substring(1);
	}
}
