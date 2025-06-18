package sm.mobile.androidnavigation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoViewModel(context: Context) : ViewModel() { //() sekalian import constructor

    private val _todos =
        MutableStateFlow<List<Todo>>(emptyList()) //bersifat private agar UI gak bisa manipulasi. Mutable ini bisa setter & getter
    val todos: StateFlow<List<Todo>> = _todos //variabel todos sifatnya read only. getter aja
    val repo = TodoRepo(context)

    fun fetchTodos() {
        viewModelScope.launch { //diproses pararel bersama UI
//            RetrofitClient.apiService.getTodos()
//                .enqueue(object :
//                    Callback<List<Todo>> { //antrian req. Jika req berhasil, method onResponse jalan. Kl gagal, onFailure jalan
//                    override fun onResponse(
//                        call: Call<List<Todo>>,
//                        response: Response<List<Todo>>
//                    ) {
//                        if (response.isSuccessful) //if(response?.isSuccessful!!) pake kode itu merah di . ilang
//                            _todos.value = response.body()
//                                ?: emptyList() //variabel didepan tanda tanya (response) ada kemungkinan nilai variabel null || _todos = mutable
//                    }
//
//                    override fun onFailure(call: Call<List<Todo>>, t: Throwable?) {
//                        // kalau gagal ke sini
//                    }
//                })
        }
    }

    fun addTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) { //Dispatchers: agar dijalankan di thread yg berbeda
//            RetrofitClient.apiService.addTodo(todo)
//                .enqueue(object : Callback<Todo> {
//                    override fun onResponse(
//                        call: Call<Todo>,
//                        response: Response<Todo>
//                    ) {
//                        if (response.isSuccessful) fetchTodos()
//                    }
//
//                    override fun onFailure(
//                        call: Call<Todo>,
//                        t: Throwable?
//                    ) {
//
//
//                    }
//                })
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
//            RetrofitClient.apiService.updateTodo(todo.id, todo)
//                .enqueue(object : Callback<Void> {
//                    override fun onResponse(
//                        call: Call<Void>,
//                        response: Response<Void>
//                    ) {
//                        if (response.isSuccessful)
//                            fetchTodos()
//                    }
//
//
//                    override fun onFailure(call: Call<Void?>?, t: Throwable?) {
//                        TODO("Not yet implemented")
//                    }
//                })
        }
    }

    fun deleteTodo(id: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
//            RetrofitClient.apiService.deleteTodo(id)
//                .enqueue(object : Callback<Void> {
//                    override fun onResponse(
//                        call: Call<Void>,
//                        response: Response<Void>
//                    ) {
//                        if (response.isSuccessful)
//                            fetchTodos()
//                    }
//
//                    override fun onFailure(call: Call<Void?>?, t: Throwable?) {
//                        TODO("Not yet implemented")
//                    }
//                })
        }
    }

    fun addLocalTodo(context: Context, todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            val db = TodoDb.getInstance(context)
            db.getDao().insertAll(todo)
            getAllLocalTodos(context)
        }
    }

    fun getAllLocalTodos(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val db = TodoDb.getInstance(context)
            _todos.value = db.getDao().getAll()
        }
    }

    fun updateLocalTodo(context: Context, todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            val db = TodoDb.getInstance(context)
            db.getDao().update(todo)
            getAllLocalTodos(context) //hbs update, data-nya di refresh
        }
    }

    fun deleteLocalTodo(context: Context, todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            val db = TodoDb.getInstance(context)
            db.getDao().delete(todo)
            getAllLocalTodos(context)
        }
    }

    fun fetchTodosRepo() {
        viewModelScope.launch(Dispatchers.IO) {
            _todos.value = repo.getAll()
        }
    }
    fun addTodoRepo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            if (repo.add(todo)) fetchTodosRepo()
        }
    }
    fun updateTodoRepo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            if (repo.update(todo)) fetchTodosRepo()
        }
    }
    fun deleteTodoRepo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            if (repo.delete(todo)) fetchTodosRepo()
        }
    }
}