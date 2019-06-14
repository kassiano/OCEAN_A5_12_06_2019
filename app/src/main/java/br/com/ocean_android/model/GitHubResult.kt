package br.com.ocean_android.model

class GitHubResult(
    val items: List<Repository>
)

class Repository(
    val id:Long?,
    val name:String?,
    val full_name:String?,
    val owner: Owner
)

class Owner(
    val id :Long?,
    val login: String?,
    val avatar_url: String?
)