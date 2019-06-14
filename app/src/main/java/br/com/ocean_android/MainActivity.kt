package br.com.ocean_android

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import br.com.ocean_android.model.Repository
import br.com.ocean_android.presenter.MainPresenter
import br.com.ocean_android.view.MainView
import br.com.ocean_android.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {


    lateinit var presenter : MainPresenter

    val viewmodel by lazy {
         ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btBuscar.setOnClickListener {
            val language = editBusca.text.toString()
            viewmodel.fetchRepositories(language)
        }

        progressBar.visibility = View.GONE
        viewmodel.repositories.observe(this, Observer{

            it?.let{
                var nomes = ""
                it.forEach {
                    nomes += "Nome: ${it.name} \tlogin:${it.owner.login}\n"
                }

                tvRepositorios.text = nomes
            }
        })

        viewmodel.loading.observe(this, Observer {
            it?.let {

                progressBar.visibility = if(it) View.VISIBLE else View.GONE

            }
        })


        //Código relacionod ao mpdelo MVP
        /*
        presenter = MainPresenter(this)

        btBuscar.setOnClickListener {
            onButtonClick()
        }
        */
    }

    override fun onButtonClick() {
        val language = editBusca.text.toString()
        presenter.fetchRepositories(language)
    }

    override fun showList(repositories: List<Repository>) {

        var nomes = ""
        repositories.forEach {
            nomes += "Nome: ${it.name} \tlogin:${it.owner.login}\n"
        }

        tvRepositorios.text = nomes
    }


}
