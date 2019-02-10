package br.com.fiap.trabalhofinalapplication.view

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.fiap.trabalhofinalapplication.APIClient

import br.com.fiap.trabalhofinalapplication.R
import br.com.fiap.trabalhofinalapplication.evaluation.api.v1.CustomerApi
import br.com.fiap.trabalhofinalapplication.evaluation.api.v1.OAuthApi
import br.com.fiap.trabalhofinalapplication.evaluation.contracts.customers.v1.CustomersReponse
import br.com.fiap.trabalhofinalapplication.evaluation.dao.DataBaseInstance
import kotlinx.android.synthetic.main.user_add_fragment.*
import kotlinx.android.synthetic.main.user_add_fragment.view.*
import kotlinx.android.synthetic.main.user_list_fragment.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserListFragment: Fragment() {

    var customerApi: CustomerApi? = null

    var customerResponse: CustomersReponse? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        customerApi = APIClient.client?.create(CustomerApi::class.java)

        var inflater = LayoutInflater.from(container?.context).inflate(R.layout.user_list_fragment, container, false)

        val sharedPreferences = inflater.context.getSharedPreferences("appconfig",
            Context.MODE_PRIVATE)

        inflater.user_list_recyclerview.layoutManager = LinearLayoutManager(container?.context)

        println("token: " + sharedPreferences.getString("JWT_TOKEN", null))

        this.customerApi!!.list(sharedPreferences.getString("JWT_TOKEN", null))
            .enqueue(object : Callback<CustomersReponse> {

            override fun onFailure(call: Call<CustomersReponse>?, t: Throwable?) {

                Toast.makeText(
                    inflater.context,
                    t!!.message,
                    Toast.LENGTH_LONG)
                    .show()

            }

            override fun onResponse(call: Call<CustomersReponse>?, response: Response<CustomersReponse>?) {

                if(response?.isSuccessful == true){

                    if(response.code() == 200){

                        customerResponse = response.body()

                        Toast.makeText(
                            inflater.context,
                            "Dados carregados com sucesso",
                            Toast.LENGTH_LONG)
                            .show()


                    }else{

                        Toast.makeText(
                            inflater.context,
                            "Falha ao carregar usuários",
                            Toast.LENGTH_LONG)
                            .show()
                    }

                }else{

                    Toast.makeText(
                        inflater.context,
                        "Falha ao carregar usuários",
                        Toast.LENGTH_LONG)
                        .show()
                }

                inflater.user_list_recyclerview.adapter = UserAdapter(customerResponse)
            }

        })

        return inflater
    }

}
