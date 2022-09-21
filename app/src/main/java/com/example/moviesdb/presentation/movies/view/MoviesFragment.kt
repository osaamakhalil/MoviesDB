package com.example.moviesdb.presentation.movies.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesdb.MyApplication
import com.example.moviesdb.R
import com.example.moviesdb.databinding.FragmentMoviesBinding
import com.example.moviesdb.di.ViewModelProviderFactory
import com.example.moviesdb.presentation.movies.adapter.MoviesAdapter
import com.example.moviesdb.presentation.movies.viewmodel.MoviesViewModel
import com.example.moviesdb.presentation.movies.viewstate.MoviesViewAction
import com.example.moviesdb.presentation.movies.viewstate.MoviesViewState
import javax.inject.Inject

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MoviesAdapter
    private var isScrolling = false

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory
    private val viewModel: MoviesViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MyApplication).provideAppComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.postAction(MoviesViewAction.GetMovies)

        setUpRecyclerView()
        handleViewState()

        binding.likesHeaderTextView.setOnClickListener {
            val action = MoviesFragmentDirections.toFavoriteMoviesAction()
            binding.root.findNavController().navigate(action)
        }
    }

    private fun handleViewState() {
        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            binding.loadingProgressBar.isVisible = viewState is MoviesViewState.Loading
            binding.moviesRV.isVisible = viewState is MoviesViewState.Success
            binding.likesHeaderTextView.isVisible = viewState is MoviesViewState.Success
            when (viewState) {
                MoviesViewState.Error -> {}
                MoviesViewState.Loading -> {}
                is MoviesViewState.Success -> {
                    adapter.submitList(viewState.movies)
                    binding.loadMoreProgressBar.isVisible = viewState.loadingMore

                    binding.likesHeaderTextView.text = getString(
                        R.string.likes_number,
                        viewState.likesNumber
                    )
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        adapter = MoviesAdapter({ movieId ->
            val action =
                MoviesFragmentDirections.toMovieDetailsAction(movieId)
            binding.root.findNavController().navigate(action)
        }, { movieId ->
            viewModel.postAction(MoviesViewAction.AddToFavorites(id = movieId))
        })
        binding.apply {
            moviesRV.adapter = adapter
            moviesRV.addOnScrollListener(this@MoviesFragment.scrollListener)
        }
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val shouldPaginate = isAtLastItem && isNotAtBeginning && isScrolling

            if (shouldPaginate) {
                viewModel.postAction(MoviesViewAction.GetMoreMovies)
                isScrolling = false
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }
    }
}