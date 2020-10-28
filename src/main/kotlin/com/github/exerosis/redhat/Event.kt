package com.github.exerosis.redhat

//Leaks theoretically
typealias Event<Listener> = (Listener) -> (Unit)

class ArrayEvent<Listener> : Event<Listener> {
    internal val listeners = ArrayList<Listener>()
    override fun invoke(listener: Listener) {
        listeners.add(listener)
    }
}

operator fun <First> ArrayEvent<(First) -> (Unit)>.invoke(first: First)
    = listeners.forEach { it(first) }
operator fun <First, Second> ArrayEvent<(First, Second) -> (Unit)>.invoke(first: First, second: Second)
    = listeners.forEach { it(first, second) }

//Additional specializations as required.

typealias Observable<Type> = Event<(Type) -> (Unit)>
typealias Observer<Type> = (Type) -> (Unit)