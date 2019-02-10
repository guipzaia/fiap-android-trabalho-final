package br.com.fiap.trabalhofinalapplication.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.fiap.trabalhofinalapplication.APIClient
import br.com.fiap.trabalhofinalapplication.R
import br.com.fiap.trabalhofinalapplication.evaluation.api.v1.UserAPI
import br.com.fiap.trabalhofinalapplication.evaluation.model.users.v1.UserRequest
import kotlinx.android.synthetic.main.admin_add_fragment.*
import kotlinx.android.synthetic.main.admin_add_fragment.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class AdminAddFragment : Fragment() {

    var userApi: UserAPI? = null
    var token: String? = null

    @SuppressLint("NewApi")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        userApi = APIClient.client?.create(UserAPI::class.java)

        var inflater = LayoutInflater.from(container?.context).inflate(R.layout.admin_add_fragment, container, false)

        val sharedPreferences = inflater.context.getSharedPreferences("appconfig",
            Context.MODE_PRIVATE)

        token = sharedPreferences.getString("JWT_TOKEN", null)

        inflater.btnAddAdmin.setOnClickListener {

            if (validarSenha()) {
                var dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")).toString()

                this.userApi!!.create(
                    token!!,
                    UserRequest(
                        accountNonLocked = true,
                        createdAt =  dataHora,
                        credentialsNonExpired = true,
                        email =  edtEmail.text.toString(),
                        enable = true,
                        id = edtUsername.text.toString(),
                        password = edtPassword02.text.toString(),
                        updatedAt = dataHora,
                        username = edtUsername.text.toString()
                    )
                ).enqueue(object: Callback<Any> {

                    override fun onFailure(call: Call<Any>, t: Throwable) {

                        println("Falha ao cadastrar usuario")
                        println(t.message)

                        Toast.makeText(
                            inflater.context,
                            t.message,
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }

                    override fun onResponse(call: Call<Any>?, response: Response<Any>?) {

                        if (response?.isSuccessful == true) {

                            if (response.code() == 200) {

                                Toast.makeText(
                                    inflater.context,
                                    "Usuario cadastrado com sucesso",
                                    Toast.LENGTH_LONG
                                )
                                    .show()

                                /*
                                var handle = Handler()

                                handle.postDelayed({
                                    finish()
                                }, 2000)
                                */

                            } else {
                                Toast.makeText(
                                    inflater.context,
                                    "Falha ao cadastrar usuario",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            }

                        } else {

                            Toast.makeText(
                                inflater.context,
                                "Falha na requisição: " + response?.message() + ": " + response?.code().toString(),
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    }
                })

            } else {
                Toast.makeText(
                    inflater.context,
                    "Senhas não conferem",
                    Toast.LENGTH_LONG)
                    .show()
            }

        }


        return inflater
    }

    private fun validarSenha(): Boolean {

        var password01 = edtPassword01.text.toString()
        var password02 = edtPassword02.text.toString()

        return password01.equals(password02)
    }
}
