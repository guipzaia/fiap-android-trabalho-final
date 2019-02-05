package br.com.fiap.trabalhofinalapplication.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import br.com.fiap.trabalhofinalapplication.APIClient
import br.com.fiap.trabalhofinalapplication.R
import br.com.fiap.trabalhofinalapplication.evaluation.api.v1.OAuthApi
import br.com.fiap.trabalhofinalapplication.evaluation.contracts.oauth.v1.LoginRequest
import br.com.fiap.trabalhofinalapplication.evaluation.contracts.oauth.v1.LoginResponse
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    var oAuthApi: OAuthApi? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        oAuthApi = APIClient.client?.create(OAuthApi::class.java)

        loginButton.setOnClickListener {

            login()

        }


    }

    private fun login(){

        this.oAuthApi!!.login(
            LoginRequest(
                username = loginText.text.toString(),
                password = passwordText.text.toString()
            )
        ).enqueue(object : Callback<LoginResponse>{

            override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {

                println("falha de login")
                println(t?.message)

                Toast.makeText(
                    this@LoginActivity,
                    t?.message,
                    Toast.LENGTH_LONG)
                    .show()

            }

            override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {

                    if(response?.isSuccessful == true){

                        var message = response.body()?.message
                        var token = response.body()?.token

                        if(response.code() == 200){

                            Toast.makeText(
                                this@LoginActivity,
                                message,
                                Toast.LENGTH_LONG)
                                .show()

                            var handle =  Handler()

                            handle.postDelayed({
                                openMain()
                            }, 2000)

                        }else{
                            Toast.makeText(
                                this@LoginActivity,
                                message,
                                Toast.LENGTH_LONG)
                                .show()
                        }

                }else{
                    var message = response?.body()?.message

                    if(message == null)
                        message = "Dados não conferem"

                    Toast.makeText(
                        this@LoginActivity,
                        message,
                        Toast.LENGTH_LONG)
                        .show()
                }
            }

        })

    }

    fun openMain() {
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}
