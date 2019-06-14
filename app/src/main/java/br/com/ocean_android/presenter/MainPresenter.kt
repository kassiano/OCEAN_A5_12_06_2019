package br.com.ocean_android.presenter

import br.com.ocean_android.model.GitHubResult
import br.com.ocean_android.service.ApiService
import br.com.ocean_android.view.MainView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainPresenter(val view: MainView)  {

    fun fetchRepositories(language:String){

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)

        val call = service.buscarRepositorios("language:${language}")

        call.enqueue( object: Callback<GitHubResult> {
            override fun onFailure(call: Call<GitHubResult>, t: Throwable) {

            }

            override fun onResponse(call: Call<GitHubResult>, response: Response<GitHubResult>) {

                val result = response.body()

                if(result != null){
                   view.showList(result.items)
                }

            }

        } )

    }

}