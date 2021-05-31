package com.gracielo.core.domain.model

data class Users(

    var id: Int? = null,
    var login: String? = null, //Username
    var updatedAt: String? = null,
    var company: String? = null,
    var publicRepos: Int? = null, // Total Repository
    var followers: Int? = null,// Followers Count
    var avatarUrl: String? = null,
    var htmlUrl: String? = null,
    var following: Int? = null,// Following Count
    var name: String? = null,
    var location: String? = null,
    var isFavorite: Boolean = false,
)