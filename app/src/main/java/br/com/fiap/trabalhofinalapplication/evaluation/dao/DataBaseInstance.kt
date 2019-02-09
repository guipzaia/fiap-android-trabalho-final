package br.com.fiap.trabalhofinalapplication.evaluation.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import br.com.fiap.trabalhofinalapplication.evaluation.models.Login


@Database(entities = arrayOf(Login::class), version = 21)
abstract class DataBaseInstance: RoomDatabase() {

    abstract fun loginDao(): LoginDao

    companion object {

        var INSTANCE: DataBaseInstance? = null

        fun getDatabase(context: Context): DataBaseInstance? {
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    DataBaseInstance::class.java,
                    "trabalho")
                    .build()
            }

            return INSTANCE
        }

    }

}