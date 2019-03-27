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

import java.io.PrintStream;

/**
 * Representation of a human having:
 * <ul>
 *  <li>a name,</li>
 *  <li>a favorite beverage,</li>
 *  <li>an amount of money</li>
 * </ul>
 * and that is able to:
 * <ul>
 *  <li><code>say</code> things,</li>
 *  <li><code>greets</code>,</li>
 *  <li><code>drink</code></li>
 * </ul>
 * 
 * @author <a href="mailto:emmanuel.chebbi@outlook.fr">Emmanuel Chebbi</a>
 */
public interface Human extends Entity
{
    /**
     * @return the name of the human.
     */
	String name();
	
	/**
	 * @return the favorite beverage of the human.
	 */
	String beverage();
	
	/**
	 * @return the amount of money held by the human.
	 */
	int money();

	/**
	 * Says <code>text</code>.
	 * <br><br>
	 * This method is a shortcut for:
	 * <pre>say(text, System.out);</pre>
	 * 
	 * @param text
	 *             The text to be said.
	 *             
	 * @see #say(String, PrintStream)
	 */
	void say(String text);

    /**
     * Prints the <code>text</code> into <code>out</code>, with a formatted line 
     * displaying the name of the human.
     * 
     * @param text
     *             The text to be said.
     *             
     * @see #say(String)
     */
	void say(String text, PrintStream out);
	
	/**
	 * This method is a shortcut for:
	 * <pre>greet(System.out);</pre>
	 */
	void greet();
	
	/**
	 * Prints a greeting into <code>out</code>.
	 * 
	 * @param out
	 *             The stream in which print the greeting.     
	 */
	void greet(PrintStream out);
	
	/**
	 * Decreases the money by <code>amount<code>.
	 * <code><code>
	 * If <pre>amount <
	 * 
	 * @param amount
	 *             The amount of money to lose.
	 *             
	 * @return the old amount of money.
	 */
	int loseMoney(int amount);
	
	int earnMoney(int amount);
	
	int loseEverything();
	
	void drink();
}
