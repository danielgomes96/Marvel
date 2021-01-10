package com.lucasdias.feature_comic.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.lucasdias.base.presentation.BaseFragment
import com.lucasdias.domain.model.ComicDetail
import com.lucasdias.feature_comic.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComicDetailFragment : BaseFragment<ComicDetail>(
    successViewId = R.id.success_view,
    loadingViewId = R.id.loading_layout,
    errorViewId = R.id.error_view,
    fragmentLayoutId = R.layout.fragment_comic_detail
) {

    override val viewModel by viewModel<ComicDetailViewModel>()
    private val args: ComicDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_comic_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.setArgumentForRequest(args.comicId)
        super.onViewCreated(view, savedInstanceState)
    }
}
