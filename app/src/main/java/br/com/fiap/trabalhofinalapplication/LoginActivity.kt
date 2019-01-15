package br.com.fiap.trabalhofinalapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var handle =  Handler()

        handle.postDelayed({
            openMain()
        }, 2000)
    }

    fun openMain() {
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}
