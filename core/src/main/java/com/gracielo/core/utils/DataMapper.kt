package com.gracielo.core.utils

import com.gracielo.core.data.source.remote.response.MoviesResponse
import com.gracielo.core.data.source.remote.response.TVResponse
import com.gracielo.core.domain.model.Movie
import com.gracielo.core.domain.model.TV

object DataMapper {

    fun mapResponsesToEntitiesMovies(input: List<MoviesResponse>): List<com.gracielo.core.data.source.local.entity.MovieEntity> {
        val movieList = ArrayList<com.gracielo.core.data.source.local.entity.MovieEntity>()
        input.map {
            var film_genre = ""
            var ctr = 0
            while (ctr < it.genreIds.size) {
                if (film_genre != "") {
                    film_genre = "$film_genre, "
                }
                val idgenre = it.genreIds[ctr]
                film_genre = "$film_genre " + GenreMapper.genres[idgenre]
                ctr++
            }
            if (film_genre == "") film_genre = "-"
            val movie = com.gracielo.core.data.source.local.entity.MovieEntity(
                it.id,
                it.overview,
                it.originalLanguage,
                it.originalTitle,
                it.video,
                it.title,
                film_genre,
                it.posterPath,
                it.backdropPath,
                it.releaseDate,
                it.popularity,
                it.voteAverage,
                it.adult,
                it.voteCount,
                false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapResponsesToEntitiesTV(input: List<TVResponse>): List<com.gracielo.core.data.source.local.entity.TVEntity> {
        val movieList = ArrayList<com.gracielo.core.data.source.local.entity.TVEntity>()
        input.map {
            var film_genre = ""
            var ctr = 0
            while (ctr < it.genreIds.size) {
                if (film_genre != "") {
                    film_genre = "$film_genre, "
                }
                val idgenre = it.genreIds[ctr]
                film_genre = "$film_genre " + GenreMapper.genres[idgenre]
                ctr++
            }
            if (film_genre == "") film_genre = "-"
            var origin: String
            if (it.originCountry!!.isNotEmpty()) origin = it.originCountry[0]
            else origin = "-"
            val movie = com.gracielo.core.data.source.local.entity.TVEntity(
                it.id,
                it.firstAirDate,
                it.overview,
                it.originalLanguage,
                film_genre,
                it.posterPath,
                origin,
                it.backdropPath,
                it.originalName,
                it.popularity,
                it.voteAverage,
                it.name,
                it.voteCount,
                false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomainMovie(input: List<com.gracielo.core.data.source.local.entity.MovieEntity>): List<Movie> =
        input.map {
            Movie(
                it.id,
                it.overview,
                it.originalLanguage,
                it.originalTitle,
                it.video,
                it.title,
                it.genreIds,
                it.posterPath,
                it.backdropPath,
                it.releaseDate,
                it.popularity,
                it.voteAverage,
                it.adult,
                it.voteCount,
                it.isFavorite
            )
        }

    fun mapEntitiesToDomainTV(input: List<com.gracielo.core.data.source.local.entity.TVEntity>): List<TV> =
        input.map {
            TV(
                it.id,
                it.firstAirDate,
                it.overview,
                it.originalLanguage,
                it.genreIds,
                it.posterPath,
                it.originCountry,
                it.backdropPath,
                it.originalName,
                it.popularity,
                it.voteAverage,
                it.name,
                it.voteCount,
                it.isFavorite
            )
        }

    fun mapDomainToEntityMovie(input: Movie) =
        com.gracielo.core.data.source.local.entity.MovieEntity(
            input.id,
            input.overview,
            input.originalLanguage,
            input.originalTitle,
            input.video,
            input.title,
            input.genreIds,
            input.posterPath,
            input.backdropPath,
            input.releaseDate,
            input.popularity,
            input.voteAverage,
            input.adult,
            input.voteCount,
            input.isFavorite
        )

    fun mapDomainToEntityTV(input: TV) = com.gracielo.core.data.source.local.entity.TVEntity(
        input.id,
        input.firstAirDate,
        input.overview,
        input.originalLanguage,
        input.genreIds,
        input.posterPath,
        input.originCountry,
        input.backdropPath,
        input.originalName,
        input.popularity,
        input.voteAverage,
        input.name,
        input.voteCount,
        input.isFavorite
    )
}