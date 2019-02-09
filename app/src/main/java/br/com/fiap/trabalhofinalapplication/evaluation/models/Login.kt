package br.com.fiap.trabalhofinalapplication.evaluation.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Login {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var login: String? = null
    var jwtToken: String? = null

}