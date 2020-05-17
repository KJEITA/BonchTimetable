package com.example.bonchapp.domain.repository

import com.example.bonchapp.domain.entities.Event

interface IEventRepository {

    fun getAllEvents(callback: (data: List<Event>?, error: String?) -> Unit)
    fun getFavoriteEvent(callback: (data: List<Event>?, error: String?) -> Unit)
    fun getMyEvents(callback: (data: List<Event>?, error: String?) -> Unit)
    fun addFavoriteEvent(eventId: Int, callback: (error: String?) -> Unit)
    fun deleteFavoriteEvent(eventId: Int, callback: (error: String?) -> Unit)


}