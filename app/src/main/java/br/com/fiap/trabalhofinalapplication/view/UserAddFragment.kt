package br.com.fiap.trabalhofinalapplication.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.fiap.trabalhofinalapplication.APIClient
import br.com.fiap.trabalhofinalapplication.R
import br.com.fiap.trabalhofinalapplication.evaluation.api.v1.CustomerApi
import br.com.fiap.trabalhofinalapplication.evaluation.api.v1.UserAPI
import br.com.fiap.trabalhofinalapplication.evaluation.contracts.Customer
import br.com.fiap.trabalhofinalapplication.evaluation.model.users.v1.UserRequest
import kotlinx.android.synthetic.main.admin_add_fragment.*
import kotlinx.android.synthetic.main.admin_add_fragment.view.*
import kotlinx.android.synthetic.main.user_add_fragment.*
import kotlinx.android.synthetic.main.user_add_fragment.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UserAddFragment: Fragment() {

    var customerApi: CustomerApi? = null
    var token: String? = null

    @SuppressLint("NewApi")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var inflater = LayoutInflater.from(container?.context).inflate(R.layout.user_add_fragment, container, false)

        customerApi = APIClient.client?.create(CustomerApi::class.java)

        val sharedPreferences = inflater.context.getSharedPreferences("appconfig",
            Context.MODE_PRIVATE)

        token = sharedPreferences.getString("JWT_TOKEN", null)

        inflater.addUserButton.setOnClickListener {

            var dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")).toString()

            Toast.makeText(
                inflater.context,
                dateOfBirthEditText.text.toString(),
                Toast.LENGTH_LONG
            ).show()

            var dataNasc = LocalDate.parse(dateOfBirthEditText.text.toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString()

            this.customerApi!!.create(
                token!!,
                Customer(
                    documentNumber = documentNumberEditText.text.toString(),
                    firstName = firstNameEditText.text.toString(),
                    lastName = lastNameEditText.text.toString(),
                    dateOfBirth = dataNasc,
                    createdAt =  dataHora,
                    updatedAt = dataHora
                )
            ).enqueue(object: Callback<Customer> {

                override fun onFailure(call: Call<Customer>, t: Throwable) {

                    println("Falha ao cadastrar usuario")
                    println(t.message)

                    Toast.makeText(
                        inflater.context,
                        t.message,
                        Toast.LENGTH_LONG
                    )
                        .show()
                }

                override fun onResponse(call: Call<Customer>?, response: Response<Customer>?) {

                    if (response?.isSuccessful == true) {

                        if (response.code() == 200) {

                            Toast.makeText(
                                inflater.context,
                                "Usuario cadastrado com sucesso",
                                Toast.LENGTH_LONG
                            )
                                .show()

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

        }

        return inflater
    }

}