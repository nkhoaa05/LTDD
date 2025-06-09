package com.example.bttuan_4.data

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.bttuan_4.data.pojo.Book
import com.example.bttuan_4.data.pojo.User
import com.example.bttuan_4.data.repository.LibraryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: LibraryRepository) : ViewModel() {

    private val _borrowedBooks = MutableStateFlow<List<String>>(emptyList())
    val borrowedBooks: StateFlow<List<String>> get() = _borrowedBooks

    init {
        viewModelScope.launch {
            if (repository.getAllUsers().isEmpty()) {
                repository.insertSampleData()
            }

            loadAllUsers()
            loadAllBooks()
        }
    }

    fun loadBooksForUser(userName: String) {
        viewModelScope.launch {
            try {
                val userWithBooks = repository.getUserWithBooksByName(userName)
                _borrowedBooks.value = userWithBooks?.books?.map { it.name } ?: emptyList()
            } catch (e: Exception) {
                _borrowedBooks.value = emptyList()
                e.printStackTrace()
            }
        }
    }

    // User
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> get() = _users

    fun loadAllUsers() {
        viewModelScope.launch {
            _users.value = repository.getAllUsers()
        }
    }

    // Book
    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> get() = _books

    fun loadAllBooks() {
        viewModelScope.launch {
            _books.value = repository.getAllBooks()
        }
    }


}

class MainViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val db = LibraryDatabase.getDatabase(context)
        val repository = LibraryRepository(db)
        return MainViewModel(repository) as T
    }
}
