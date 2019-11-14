package me.okawa.transformations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _repository = UserRepository()

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text
    val textUpperCase: LiveData<String> = Transformations.map(_text) { value ->
        value.toUpperCase()
    }
    val textDistinct: LiveData<String> = Transformations.distinctUntilChanged(_text)
    val users: LiveData<List<String>> = Transformations.switchMap(_text) { value ->
        _repository.getFilteredUsers(value)
    }

    fun setText(text: String) {
        _text.postValue(text)
    }

}