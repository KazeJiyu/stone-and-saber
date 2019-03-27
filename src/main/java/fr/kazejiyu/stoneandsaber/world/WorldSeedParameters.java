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
 * A builder to make {@link WorldSeed} creation easier.
 * <br><br>
 * By default, every parameter is set to 0.
 * 
 * @author <a href="mailto:emmanuel.chebbi@outlook.fr">Emmanuel Chebbi</a>
 */
public class WorldSeedParameters
{
	private int samurais = 0;
	private int yakuzas = 0;
	private int merchants = 0;
	private int traitors = 0;

	public WorldSeedParameters samurais(int i)
	{
		samurais = i;
		return this;
	}

	public WorldSeedParameters yakuzas(int i)
	{
		yakuzas = i;
		return this;
	}
	
	public WorldSeedParameters merchants(int i)
	{
		merchants = i;
		return this;
	}
	
	public WorldSeedParameters traitors(int i)
	{
		traitors = i;
		return this;
	}
	
	public WorldSeed toSeed()
	{
		return new WorldSeed(samurais, yakuzas, merchants, traitors);
	}
}
