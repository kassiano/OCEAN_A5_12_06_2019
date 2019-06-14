package br.com.ocean_android.view

import br.com.ocean_android.model.Repository

interface MainView {

    fun onButtonClick()

    fun showList(repositories:List<Repository> )

}