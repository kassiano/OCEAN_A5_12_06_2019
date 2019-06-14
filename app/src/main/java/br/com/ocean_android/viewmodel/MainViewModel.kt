package br.com.ocean_android.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.ocean_android.model.GitHubResult
import br.com.ocean_android.model.Repository
import br.com.ocean_android.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {

    val repositories = MutableLiveData<List<Repository>>()
    val loading = MutableLiveData<Boolean>()

    fun fetchRepositories(language:String){

        loading.value = true

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
                    repositories.value = result.items
                    loading.value = false
                }

            }

        } )

    }

}