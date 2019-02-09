package br.com.fiap.trabalhofinalapplication.evaluation.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import br.com.fiap.trabalhofinalapplication.evaluation.models.Login

@Dao
interface LoginDao {

    @Insert
    fun insert(login: Login)

    @Delete
    fun delete(login: Login)

    @Query("DELETE FROM login")
    fun deleteAll()

    @Update
    fun update(login: Login)

    @Query("SELECT * FROM login")
    fun list(): LiveData<List<Login>>

    @Query("SELECT * FROM login WHERE id = :id")
    fun findById(id: Int): Login

    @Query("SELECT * FROM login LIMIT 1")
    fun first(): Login
}