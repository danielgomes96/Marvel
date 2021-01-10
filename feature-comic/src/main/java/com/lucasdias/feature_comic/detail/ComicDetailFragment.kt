package com.lucasdias.feature_comic.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.lucasdias.base.presentation.BaseFragment
import com.lucasdias.core.resource.observe
import com.lucasdias.domain.model.ComicDetail
import com.lucasdias.feature_comic.R
import com.lucasdias.feature_comic.databinding.FragmentComicDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComicDetailFragment : BaseFragment<ComicDetail>(
    successViewId = R.id.success_view,
    loadingViewId = R.id.loading_layout,
    errorViewId = R.id.error_view,
    fragmentLayoutId = R.layout.fragment_comic_detail
) {

    override val viewModel by viewModel<ComicDetailViewModel>()
    private val args: ComicDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentComicDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.setArgumentForRequest(args.comicId)
        super.onViewCreated(view, savedInstanceState)
        bindingSetup(view)
        observerSetup()
    }

    private fun bindingSetup(view: View) {
        binding = FragmentComicDetailBinding.bind(view)
    }

    private fun observerSetup() {
        viewModel.responseLiveData.observe(viewLifecycleOwner, ::onLoading, ::onSuccess, ::onError)
    }

    override fun onLoading() {
        super.onLoading()
    }

    override fun onSuccess(model: Any?) {
        super.onSuccess(model)
    }

    override fun onError(throwable: Throwable?) {
        super.onError(throwable)
    }
}
