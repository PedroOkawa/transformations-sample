package me.okawa.transformations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

private val DEFAULT_USERS = listOf("Rick Sanchez", "Jerry Smith", "Morty Smith", "Mr. Meeseeks", "Squanchy", "Gazorpian")

class UserRepository {

    fun getFilteredUsers(filter: String): LiveData<List<String>> {
        return MutableLiveData(DEFAULT_USERS.filter { filter.isNotEmpty() && it.contains(filter, true) })
    }

}