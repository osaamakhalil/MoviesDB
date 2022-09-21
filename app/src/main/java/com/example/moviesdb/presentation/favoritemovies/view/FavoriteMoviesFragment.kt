package com.example.moviesdb.presentation.favoritemovies.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.moviesdb.MyApplication
import com.example.moviesdb.databinding.FragmentFavoriteMoviesBinding
import com.example.moviesdb.di.ViewModelProviderFactory
import com.example.moviesdb.presentation.favoritemovies.viewmodel.FavoriteMoviesViewModel
import com.example.moviesdb.presentation.favoritemovies.viewstate.FavoriteMoviesViewAction
import com.example.moviesdb.presentation.movies.adapter.MoviesAdapter
import javax.inject.Inject

class FavoriteMoviesFragment : Fragment() {

    private var _binding: FragmentFavoriteMoviesBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MoviesAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory
    private val viewModel: FavoriteMoviesViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MyApplication).provideAppComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.postAction(FavoriteMoviesViewAction.GetMovies)

        handleViewState()
        setUpRecyclerView()
    }

    private fun handleViewState() {
        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            adapter.submitList(viewState.movies)
        }
    }

    private fun setUpRecyclerView() {
        adapter = MoviesAdapter({ movieId ->
            val action =
                FavoriteMoviesFragmentDirections.toMovieDetailsAction(movieId)
            binding.root.findNavController().navigate(action)
        }, { movieId ->
            viewModel.postAction(FavoriteMoviesViewAction.RemoveFromFavorites(id = movieId))
        })
        binding.moviesRV.adapter = adapter
    }
}