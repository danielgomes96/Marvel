package com.lucasdias.feature_comic.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucasdias.base.presentation.BaseFragment
import com.lucasdias.core.resource.observe
import com.lucasdias.domain.model.Comic
import com.lucasdias.extensions.animateVisibleToGone
import com.lucasdias.extensions.scrollSetup
import com.lucasdias.feature_comic.R
import com.lucasdias.feature_comic.databinding.FragmentComicListBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

@Suppress("UNCHECKED_CAST")
class ComicListFragment : BaseFragment<List<Comic>>(
    successViewId = R.id.recycler_view,
    loadingViewId = R.id.loading_layout,
    errorViewId = R.id.error_view,
    fragmentLayoutId = R.layout.fragment_comic_list
) {

    override val viewModel by viewModel<ComicListViewModel>()
    private val adapter by inject<ComicListAdapter> { parametersOf({ comicId: Int? -> navigateToComicDetail(comicId) }) }
    private lateinit var binding: FragmentComicListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingSetup(view)
        observerSetup()
        recyclerViewSetup()
        backButtonSetup()
    }

    private fun backButtonSetup() {
        val backButtonCallback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    binding.recyclerView.smoothScrollToPosition(0)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), backButtonCallback)
    }

    private fun bindingSetup(view: View) {
        binding = FragmentComicListBinding.bind(view)
    }

    private fun observerSetup() {
        viewModel.responseLiveData.observe(viewLifecycleOwner, ::onLoading, ::onSuccess, ::onError)
    }

    private fun recyclerViewSetup() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        with(binding) {
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
            recyclerView.scrollSetup(layoutManager) { viewModel.requestNextPage() }
        }
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
        adapter.updateComicList(model as List<Comic>? ?: emptyList())
    }

    override fun onError(throwable: Throwable?) {
        super.onError(throwable)
        showErrorSnackbarForPaginationRequest(viewModel.isNotInitialRequest())
        binding.progressBar.animateVisibleToGone()
    }

    private fun navigateToComicDetail(comicId: Int?) {
        Toast.makeText(requireContext(), comicId?.toString(), Toast.LENGTH_LONG).show()
    }
}
