package com.example.moviesdb.presentation.moviedetails.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.moviesdb.MyApplication
import com.example.moviesdb.R
import com.example.moviesdb.databinding.FragmentMovieDetailsBinding
import com.example.moviesdb.di.ViewModelProviderFactory
import com.example.moviesdb.presentation.moviedetails.viewmodel.MovieDetailsViewModel
import com.example.moviesdb.presentation.moviedetails.viewstate.MovieDetailsViewAction
import com.example.moviesdb.presentation.moviedetails.viewstate.MovieDetailsViewState
import javax.inject.Inject

class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<MovieDetailsFragmentArgs>()

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory
    private val viewModel: MovieDetailsViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MyApplication).provideAppComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.postAction(MovieDetailsViewAction.GetMovieDetails(id = args.id))
        binding.tryAgainButton.setOnClickListener {
            viewModel.postAction(MovieDetailsViewAction.GetMovieDetails(id = args.id))
        }
        handleViewState()
    }

    private fun handleViewState() {
        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            binding.loadingProgressBar.isVisible = viewState is MovieDetailsViewState.Loading
            binding.detailsViewsGroup.isVisible = viewState is MovieDetailsViewState.Success
            binding.errorGroup.isVisible = viewState is MovieDetailsViewState.Error
            when (viewState) {
                MovieDetailsViewState.Error -> {}
                MovieDetailsViewState.Loading -> {}
                is MovieDetailsViewState.Success -> {
                    Glide.with(binding.root.context)
                        .load(viewState.movie.backImage)
                        .error(R.drawable.default_image)
                        .into(binding.movieImageView)
                    binding.movieNameTextView.text = viewState.movie.title
                    binding.movieReleaseDateTextView.text = viewState.movie.releaseDate
                    binding.movieDescriptionTextView.text = viewState.movie.overview
                    binding.movieTagline.text = viewState.movie.tagline
                }
            }
        }
    }
}