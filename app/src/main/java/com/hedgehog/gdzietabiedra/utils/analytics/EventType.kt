package com.hedgehog.gdzietabiedra.utils.analytics

import android.os.Bundle

private const val SCREEN = "SCREN"
private const val EVENT = "EVENT"
private const val NAME = "name"

sealed class EventType {

    abstract fun name(): String
    abstract fun getBundle(): Bundle

    class Screen(private val screenName: String) : EventType() {
        override fun name() = SCREEN

        override fun getBundle() = Bundle().also { it.putString(NAME, screenName) }
    }

    class Event(private val eventName: String) : EventType() {
        override fun name() = eventName

        override fun getBundle(): Bundle = Bundle.EMPTY
    }
}