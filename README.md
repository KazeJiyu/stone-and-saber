# Stone and Saber <!-- omit in toc -->

## A cool architecture <!-- omit in toc -->

Stone and Saber is a school project I made in 2017 during my final year of bachelor's degree. It was origininally aimed at making us practice polymorphism, but I ended up achieving way more and really enjoyed the process.

I share the source code because the architecture relies on several design patterns and it may be valuable for others (and future me) to take a look.

> **Disclaimer**: I did not update the code since then, and I am aware that it could be improved in many ways.

## Table of Contents <!-- omit in toc -->

- [What does the code do?](#what-does-the-code-do)
- [Run the code](#run-the-code)
- [Possible output](#possible-output)
- [Code's entry point](#codes-entry-point)
- [The Factory pattern for creating different implementations of a same interface](#the-factory-pattern-for-creating-different-implementations-of-a-same-interface)
- [The Builder pattern for instantiating configurable objects](#the-builder-pattern-for-instantiating-configurable-objects)
- [Aspect-Oriented Programming for logging](#aspect-oriented-programming-for-logging)
- [Null Object Pattern for preventing NPE](#null-object-pattern-for-preventing-npe)

## What does the code do?

When executed, the code:

1. Generates a _kind-of-medieval_ world randomly populated with some people
2. Simulates the passage of time for 30 generations
3. Produces random events at each generation that may affect any people, leading humans to:
   - Drink
   - Fight

More details in the following sections.

## Run the code

#### Clone the repository <!-- omit in toc -->

```
git clone https://github.com/echebbi/stone-and-saber.git
```

#### Build a JAR <!-- omit in toc -->

```
mvn compile assembly:single
```

#### Run the JAR <!-- omit in toc -->

```
java -jar target/stoneandsaber-1.0.0-jar-with-dependencies.jar
```

## Possible output

Here is an example of what can be printed by the program:

```
----- CREATING THE WORLD AND ITS INHABITANTS:


 ----- THE POWER IS OWNED BY:
           le seigneur Sototusi,
           le clan Lysobua.

 ----- EVENTS THROUGHOUT 10 GENERATIONS:

([M] Lysoliil) - O rage! O despair! I am as poor as a church mouse!
([Y] Siilan) - Hehe, I am so vilain. I stole 96 blings from Lysoliil. I now have 140 blings.
([S] Absofu) - Hey, merchant, here's 1 blings.
([M] Tupafu) - Oh, thank you for this donation, your Highness. Now, I have 90 blings.
([M] Lysoliil) - Ahhh, a good glass of thé! Gasp!
([M] Lymiriri) - O rage! O despair! I am as poor as a church mouse!
([Y] Zulipabo) - Hehe, I am so vilain. I stole 7 blings from Lymiriri. I now have 66 blings.
([S] Siabmibu) - Hey, merchant, here's 12 blings.
([M] Lymiriri) - Oh, thank you for this donation, your Highness. Now, I have 12 blings.
([Y] Soto) - Ahhh, a good glass of rhum! Gasp!
([Y] Ilsibuzu) - I challenge you, Rito!
([Y] Ilsibuzu) - I use Burning Sword against Rito!
([S] Rito) - I use Dagger against Ilsibuzu!
([Y] Ilsibuzu) - I use Burning Sword against Rito!
([S] Rito) - I use Dagger against Ilsibuzu!
([S] Rito) - Argg, I didn't keep up...
([Y] Ilsibuzu) - Huhu, you fool! Did you really think you could defeat me?
  *Rito is dead.
([M] Lymiriri) - O rage! O despair! I am as poor as a church mouse!
([Y] Soto) - Hehe, I am so vilain. I stole 12 blings from Lymiriri. I now have 93 blings.
([S] Riasi) - Hey, merchant, here's 3 blings.
([M] Tupafu) - Oh, thank you for this donation, your Highness. Now, I have 93 blings.

 ----- GENERATION IS OVER ! Hope you enjoyed it.
 ```

 In order to understand what is going on several things can be noticed.

 First of all, each line starting by `(..)` corresponds to something that has been said by someone. The letter surrounded by `[]` indicates the category to which the person belongs:

 - **M**erchant
 - **S**amuraï
 - **Y**akuza
 - **T**raitor

The category defines what the person can say and which events can happen to him. For instance, samuraïs may challenge yakuzas and yakuzas may extort merchants.

People talk when an event happen to them. For example, Lymiriri says `"O rage! O despair! I am as poor as a church mouse!`" because he has been robbed.

## Code's entry point

The main class is `Story`. It is used to tell a new story or, in other words, to:

1. Create a new random `World`
2. Predict the `Fate` of the World, with specific events
3. Let the World evolve according to its Fate

Concretelly, a Story initialize the simulation and then runs it.

## The Factory pattern for creating different implementations of a same interface

I use factories to:

- Create new humans (see [HumanFactory](src/main/java/fr/kazejiyu/stoneandsaber/human/characters/HumanFactory.java))
- Create new weapons (see [WeaponFactory](src/main/java/fr/kazejiyu/stoneandsaber/behaviors/weapon/impl/WeaponFactory.java))

## The Builder pattern for instantiating configurable objects

I use builders to:

- Create a new world (see [WorldSeedParameters](src/main/java/fr/kazejiyu/stoneandsaber/world/WorldSeedParameters.java))
- Create weapons with different characteristics (see [WeaponBuilder](src/main/java/fr/kazejiyu/stoneandsaber/behaviors/weapon/WeaponBuilder.java))

## Aspect-Oriented Programming for logging

Aspect-Oriented Programming (AOP) is a powerful way to handle **cross-cutting concerns**, which are, roughly speaking, everything that is not related to the software's business value (logging, security, etc).

> See [this article](https://medium.com/@mithunsasidharan/aspect-oriented-programming-overview-6c03235e666a) for more details.

In this project, I used AOP for two purposes:

1. Printing debug logs
2. Letting people speak

My main goal was to experiment with AspectJ, which I had discovered a few days before, but I actually feel that centralizing at the same place all the code that deals with logging makes the code cleaner and easier to maintain.

I defined two aspects:

- [HumanLogging](src/main/java/fr/kazejiyu/stoneandsaber/log/HumanLogging.java)
- [WorldLogging](src/main/java/fr/kazejiyu/stoneandsaber/log/WorldLogging.java)

Both of them expose the `LOGGING_IS_ACTIVATED` and `DEBUG_MODE` constants. Setting `LOGGING_IS_ACTIVATED` to false deactivates any log; it's that simple. `DEBUG_MODE` produces more detailed logs. For instance, with this option the output shown above would have been:

```
 ----- CREATING THE WORLD AND ITS INHABITANTS:


 ----- THE POWER IS OWNED BY:
           le seigneur Sototusi,
           le clan Lysobua.

 ----- EVENTS THROUGHOUT 10 GENERATIONS:

([M] Lysoliil) - O rage! O despair! I am as poor as a church mouse!
([Y] Siilan) - Hehe, I am so vilain. I stole 96 blings from Lysoliil. I now have 140 blings.
([S] Absofu) - Hey, merchant, here's 1 blings.
([M] Tupafu) - Oh, thank you for this donation, your Highness. Now, I have 90 blings.
([M] Lysoliil) - Ahhh, a good glass of thé! Gasp!
([M] Lymiriri) - O rage! O despair! I am as poor as a church mouse!
([Y] Zulipabo) - Hehe, I am so vilain. I stole 7 blings from Lymiriri. I now have 66 blings.
([S] Siabmibu) - Hey, merchant, here's 12 blings.
([M] Lymiriri) - Oh, thank you for this donation, your Highness. Now, I have 12 blings.
([Y] Soto) - Ahhh, a good glass of rhum! Gasp!
([Y] Ilsibuzu) - I challenge you, Rito!
    [Ilsibuzu.weapon=Burning Sword(PHYSICAL:200, FIRE:50)]
    [Rito.weapon=Dagger(PHYSICAL:100)]

([Y] Ilsibuzu) - I use Burning Sword against Rito!
    [Rito.status=HP:500 STR:100 ARM:100]

([S] Rito) - I use Dagger against Ilsibuzu!
    [Ilsibuzu.status=HP:500 STR:100 ARM:100]

([Y] Ilsibuzu) - I use Burning Sword against Rito!
    [Rito.status=HP:250 STR:100 ARM:100]

([S] Rito) - I use Dagger against Ilsibuzu!
    [Ilsibuzu.status=HP:400 STR:100 ARM:100]

([S] Rito) - Argg, I didn't keep up...
([Y] Ilsibuzu) - Huhu, you fool! Did you really think you could defeat me?
  *Rito is dead.
([M] Lymiriri) - O rage! O despair! I am as poor as a church mouse!
([Y] Soto) - Hehe, I am so vilain. I stole 12 blings from Lymiriri. I now have 93 blings.
([S] Riasi) - Hey, merchant, here's 3 blings.
([M] Tupafu) - Oh, thank you for this donation, your Highness. Now, I have 93 blings.

 ----- GENERATION IS OVER ! Hope you enjoyed it.
 ```

 ## Reactive programming to handle events

 I also took the opportunity to experiment with reactive programming thanks to the RxJava library.

 I used it to:

 1. Broadcast an event each time a new generation begins
 2. React to such event by letting world's inhabitant live their lives (drinking, fighting, etc.)

The corresponding code can be found within the  [Fate](src/main/java/fr/kazejiyu/stoneandsaber/world/Fate.java) class. I also implemented an [EventBus](src/main/java/fr/kazejiyu/stoneandsaber/event/EventBus.java) that helps me to support an Observer pattern in [HumanFactory](src/main/java/fr/kazejiyu/stoneandsaber/human/characters/HumanFactory.java) but, to be honest, it is used poorly.

## Null Object Pattern for preventing NPE

The Null Object Pattern allows to get rid of `null` values by providing a concrete class that does nothing. It's kind of a placeholder that prevents `NullPointerException` without hurting the logic of the code.

I hence created the [NoWeapon](src/main/java/fr/kazejiyu/stoneandsaber/weapon/impl/NoWeapon.java) which can be used transparently by [Duelists](src/main/java/fr/kazejyu/stoneandsaber/human/characters/Duelist.java) (i.e. humans owning weapons and able to fight).

Indeed, instead of writing:

```java
if (this.weapon != null) {
    this.weapon.useOn(opponent);
}
```

the `NoWeapon` class ensures that the following is enough:

```java
this.weapon.useOn(opponent);
```