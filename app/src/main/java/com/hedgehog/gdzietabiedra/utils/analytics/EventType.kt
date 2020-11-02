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

    class Event(private val eventName: EventName) : EventType() {
        override fun name() = eventName.name

        override fun getBundle(): Bundle = Bundle.EMPTY

        enum class EventName {
            SENDING_EMAIL, GIVE_STARS, LOCATION_ON, LOCATION_OFF, SELECT_SHOP_ON_MAP, MAP_MOVE,
            SEARCH_SHOP_LIST, NAVIGATION_START, SHOW_LOCATION_WARNING, LIST_ITEM_CLICKED
        }
    }
}