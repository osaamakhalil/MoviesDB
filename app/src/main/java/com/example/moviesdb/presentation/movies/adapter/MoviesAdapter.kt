package com.example.moviesdb.presentation.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesdb.R
import com.example.moviesdb.databinding.AdapterMovieBinding
import com.example.moviesdb.presentation.movies.model.MovieUiModel

class MoviesAdapter(
    private val onMovieClicked: (id: Long) -> Unit,
    private val onFavClicked: (id: Long) -> Unit
) : ListAdapter<MovieUiModel, MoviesAdapter.MoviesViewHolder>(MoviesDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            binding = AdapterMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MoviesViewHolder(
        private val binding: AdapterMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieUiModel) {

            binding.movieNameTextView.text = movie.title
            binding.movieReleaseDateTextView.text = movie.releaseDate
            binding.movieDescriptionTextView.text = movie.overview

            Glide.with(binding.root.context)
                .load(movie.poster)
                .error(R.drawable.default_image)
                .into(binding.moviePosterImageView)

            binding.root.setOnClickListener { onMovieClicked(movie.id) }

            binding.favoriteButton.apply {
                setOnClickListener { onFavClicked(movie.id) }
                if (movie.isLiked) {
                    setImageDrawable(
                        binding.root.context.getDrawable(R.drawable.ic_remove_from_favorites)
                    )
                } else {
                    setImageDrawable(
                        binding.root.context.getDrawable(R.drawable.ic_add_to_favorites)
                    )
                }
            }
        }
    }

}