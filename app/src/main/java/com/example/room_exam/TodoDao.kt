package com.example.room_exam

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {
    @get:Query("SELECT * FROM Todo")
    val getAll: LiveData<List<Todo>>

    @Insert
    fun insert(todo: Todo)

    @Update
    fun update(todo: Todo)

    @Delete
    fun delete(todo: Todo)
}