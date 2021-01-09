package com.lucasdias.feature_comic.list

import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComicListFragment : Fragment() {

    private val viewModel by viewModel<ComicListViewModel>()

    override fun onResume() {
        super.onResume()

        viewModel.fetch()
    }
}
