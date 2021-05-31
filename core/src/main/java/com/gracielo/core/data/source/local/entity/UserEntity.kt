package com.gracielo.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "login")
    var login: String? = null, //Username

    @ColumnInfo(name = "updated_at")
    var updatedAt: String? = null,

    @ColumnInfo(name = "company")
    var company: String? = null,

    @ColumnInfo(name = "public_repos")
    var publicRepos: Int? = null, // Total Repository

    @ColumnInfo(name = "followers")
    var followers: Int? = null,// Followers Count

    @ColumnInfo(name = "avatar_url")
    var avatarUrl: String? = null,

    @ColumnInfo(name = "htmlUrl_url")
    var htmlUrl: String? = null,

    @ColumnInfo(name = "following")
    var following: Int? = null,// Following Count

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "location")
    var location: String? = null,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false


)