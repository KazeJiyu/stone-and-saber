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

/**
 * Data class that stores every data required in order to generate a new random {@link World}.
 * <br><br>
 * See {@link World#randomized(WorldSeed)}.
 * <br><br>
 * The class {@link WorldSeedParameters} is a builder that makes creation of a seed easier.
 * 
 * @author <a href="mailto:emmanuel.chebbi@outlook.fr">Emmanuel Chebbi</a>
 */
public class WorldSeed
{
	/**
	 * The number of samurais to generate in the new world.
	 */
	public final int samurais;
	
	/**
	 * The number of yakuzas to generate in the new world.
	 */
	public final int yakuzas;

	/**
	 * The number of merchants to generate in the new world.
	 */
	public final int merchants;
	
	/**
	 * The number of traitors to generate in the new world.
	 */
	public final int traitors;

	/**
	 * Creates a new world seed with specified parameters.
	 * 
	 * @param samurais
	 * 			The number of samurais to generate in the new world.
	 * @param yakuzas
	 * 			The number of yakuzas to generate in the new world.
	 * @param merchants
	 * 			The number of merchants to generate in the new world.
	 * @param traitors
	 * 			The number of traitors to generate in the new world.
	 */
	public WorldSeed(int samurais, int yakuzas, int merchants, int traitors)
	{
		this.samurais = samurais;
		this.yakuzas = yakuzas;
		this.merchants = merchants;
		this.traitors = traitors;
	}
}
