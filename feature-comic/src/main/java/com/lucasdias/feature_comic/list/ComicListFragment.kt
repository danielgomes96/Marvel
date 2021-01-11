package com.lucasdias.feature_comic.list

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucasdias.base.presentation.BaseFragment
import com.lucasdias.core.resource.observe
import com.lucasdias.domain.model.ComicSummary
import com.lucasdias.extensions.animateVisibleToGone
import com.lucasdias.extensions.findNavController
import com.lucasdias.extensions.scrollSetup
import com.lucasdias.feature_comic.R
import com.lucasdias.feature_comic.databinding.FragmentComicListBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

@Suppress("UNCHECKED_CAST")
class ComicListFragment : BaseFragment<List<ComicSummary>>(
    successViewId = R.id.recycler_view,
    loadingViewId = R.id.loading_layout,
    errorViewId = R.id.error_view,
    fragmentLayoutId = R.layout.fragment_comic_list
) {

    override val viewModel by viewModel<ComicListViewModel>()
    private val adapter by inject<ComicListAdapter> {
        parametersOf({ comicId: Int -> navigateToComicDetail(comicId) })
    }
    private lateinit var binding: FragmentComicListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingSetup(view)
        observerSetup()
        recyclerViewSetup()
        backButtonSetup { binding.recyclerView.smoothScrollToPosition(0) }
    }

    private fun bindingSetup(view: View) {
        binding = FragmentComicListBinding.bind(view)
    }

    private fun observerSetup() {
        viewModel.responseLiveData.observe(viewLifecycleOwner, ::onLoading, ::onSuccess, ::onError)
    }

    private fun recyclerViewSetup() = with(binding.recyclerView) {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            setHasFixedSize(true)
            this.layoutManager = layoutManager
            this.adapter = this@ComicListFragment.adapter
            scrollSetup(layoutManager) { viewModel.requestNextPage() }
    }

    override fun onLoading() {
        super.onLoading()
        showProgressbarForPaginationRequest(
            viewModel.isNotInitialRequest(),
            errorView.visibility,
            binding.progressBar
        )
    }

    override fun onSuccess(model: Any?) {
        super.onSuccess(model)
        binding.progressBar.animateVisibleToGone()
        adapter.updateComicList(model as List<ComicSummary>? ?: emptyList())
    }

    override fun onError(throwable: Throwable?) {
        super.onError(throwable)
        showErrorSnackbarForPaginationRequest(viewModel.isNotInitialRequest())
        binding.progressBar.animateVisibleToGone()
    }

    private fun navigateToComicDetail(comicId: Int) {
        val directions = ComicListFragmentDirections.navigateToComicDetail(comicId)
        viewModel.blockNextRequest()
        findNavController().navigate(directions)
    }
}
