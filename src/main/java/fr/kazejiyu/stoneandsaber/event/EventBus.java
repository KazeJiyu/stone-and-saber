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
package fr.kazejiyu.stoneandsaber.event;

import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

/**
 * Manages the event launched through the game.
 * 
 * @author <a href="mailto:emmanuel.chebbi@outlook.fr">Emmanuel Chebbi</a>
 */
public class EventBus <T>
{
	private final PublishSubject <Object> bus = PublishSubject.create();
	
	
	
	/**
	 * Registers a <code>callback</code> that will be called each time an
	 * instance of <code>eventClassToListenFor</code> is <code>post</code>ed.
	 * 
	 * @param eventClassToListenFor
	 * 			The class to listen for.
	 * @param callback
	 * 			The callback function to call when a specific event is posted.
	 * 
	 * @return the {@link Subscription} corresponding to the registration.
	 * 
	 * @see #post(Object)
	 */
	public Subscription register(final Class <? extends T> eventClassToListenFor, Action1 <T> callback)
	{
		return bus
				.filter(eventClassToListenFor::isInstance)
				.cast(eventClassToListenFor)
				.subscribe(callback);
	}
	
	/**
	 * Sends an event through the event bus.
	 * 
	 * @param event
	 * 			The event to send.
	 * 
	 * @see #register(Class, Action1)
	 */
	public void post(Object event)
	{
		bus.onNext(event);
	}

}
