package com.lucasdias.feature_comic.detail

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lucasdias.base.presentation.BaseFragment
import com.lucasdias.core.resource.observe
import com.lucasdias.domain.model.ComicDetail
import com.lucasdias.extensions.findNavController
import com.lucasdias.extensions.gone
import com.lucasdias.feature_comic.R
import com.lucasdias.feature_comic.databinding.ComicDetailSectionWithListBinding
import com.lucasdias.feature_comic.databinding.ComicDetailSectionWithTextBinding
import com.lucasdias.feature_comic.databinding.FragmentComicDetailBinding
import com.lucasdias.feature_comic.detail.adapter.ComicDetailAdapter
import com.lucasdias.feature_comic.di.CHARACTER_ADAPTER
import com.lucasdias.feature_comic.di.CREATOR_ADAPTER
import com.lucasdias.feature_comic.di.PRICE_ADAPTER
import com.lucasdias.feature_comic.di.STORY_ADAPTER
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class ComicDetailFragment : BaseFragment<ComicDetail>(
    successViewId = R.id.success_view,
    loadingViewId = R.id.loading_layout,
    errorViewId = R.id.error_view,
    fragmentLayoutId = R.layout.fragment_comic_detail
) {

    override val viewModel by viewModel<ComicDetailViewModel>()
    private val args: ComicDetailFragmentArgs by navArgs()
    private val characterAdapter by inject<ComicDetailAdapter>(named(CHARACTER_ADAPTER))
    private val priceAdapter by inject<ComicDetailAdapter>(named(PRICE_ADAPTER))
    private val storyAdapter by inject<ComicDetailAdapter>(named(STORY_ADAPTER))
    private val creatorAdapter by inject<ComicDetailAdapter>(named(CREATOR_ADAPTER))
    private lateinit var binding: FragmentComicDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.setArgumentForRequest(args.comicId)
        super.onViewCreated(view, savedInstanceState)
        bindingSetup(view)
        observerSetup()
        sectionsSetup()
        backButtonSetup { findNavController().popBackStack() }
    }

    private fun bindingSetup(view: View) {
        binding = FragmentComicDetailBinding.bind(view)
    }

    private fun observerSetup() {
        viewModel.responseLiveData.observe(viewLifecycleOwner, ::onLoading, ::onSuccess, ::onError)
    }

    private fun recyclerViewSetup(
        recyclerView: RecyclerView,
        adapter: ComicDetailAdapter
    ) = with(recyclerView) {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        setHasFixedSize(true)
        this.layoutManager = layoutManager
        this.adapter = adapter
        isNestedScrollingEnabled = false
    }

    private fun sectionsSetup() {
        sectionSetup(
            binding.titleComicDetail.titleComicSectionWithText,
            R.string.title_title_section_comic_detail_fragment
        )

        sectionSetup(
            binding.descriptionComicDetail.titleComicSectionWithText,
            R.string.title_description_section_comic_detail_fragment
        )

        sectionSetup(
            binding.pageCountComicDetail.titleComicSectionWithText,
            R.string.title_page_quantity_section_comic_detail_fragment
        )

        sectionSetup(
            binding.characterRecyclerViewComicDetail.titleComicSectionWithList,
            R.string.title_character_section_comic_detail_fragment,
            binding.characterRecyclerViewComicDetail.recyclerViewComicDetailSectionWithList,
            characterAdapter
        )

        sectionSetup(
            binding.priceRecyclerViewComicDetail.titleComicSectionWithList,
            R.string.title_price_section_comic_detail_fragment,
            binding.priceRecyclerViewComicDetail.recyclerViewComicDetailSectionWithList,
            priceAdapter
        )

        sectionSetup(
            binding.storyRecyclerViewComicDetail.titleComicSectionWithList,
            R.string.title_story_section_comic_detail_fragment,
            binding.storyRecyclerViewComicDetail.recyclerViewComicDetailSectionWithList,
            storyAdapter
        )

        sectionSetup(
            binding.creatorRecyclerViewComicDetail.titleComicSectionWithList,
            R.string.title_creator_section_comic_detail_fragment,
            binding.creatorRecyclerViewComicDetail.recyclerViewComicDetailSectionWithList,
            creatorAdapter
        )
    }

    private fun sectionSetup(
        textView: TextView,
        @StringRes stringIdRes: Int,
        recyclerView: RecyclerView,
        adapter: ComicDetailAdapter
    ) {
        textView.text = getString(stringIdRes)
        recyclerViewSetup(recyclerView, adapter)
    }

    private fun sectionSetup(
        textView: TextView,
        @StringRes stringIdRes: Int
    ) {
        textView.text = getString(stringIdRes)
    }

    override fun onSuccess(model: Any?) {
        super.onSuccess(model)
        updateViewContent(model as ComicDetail)
    }

    private fun updateViewContent(comicDetail: ComicDetail) = with(binding) {
        handleWithSectionContent(characterRecyclerViewComicDetail, characterAdapter, comicDetail.characters)
        handleWithSectionContent(priceRecyclerViewComicDetail, priceAdapter, comicDetail.prices)
        handleWithSectionContent(storyRecyclerViewComicDetail, storyAdapter, comicDetail.stories)
        handleWithSectionContent(creatorRecyclerViewComicDetail, creatorAdapter, comicDetail.creators)
        handleWithSectionContent(titleComicDetail, comicDetail.title)
        handleWithSectionContent(descriptionComicDetail, comicDetail.description)
        handleWithSectionContent(pageCountComicDetail, comicDetail.pageCount.toString())

        viewPagerComicDetail.updateImageUrls(comicDetail.images?.map { it.getUrl() }.orEmpty())
    }

    private fun handleWithSectionContent(
        binding: ComicDetailSectionWithListBinding,
        adapter: ComicDetailAdapter,
        model: List<String>?
    ) {
        if (model.isNullOrEmpty()) binding.layoutComicSectionWithList.gone()
        else adapter.updateList(model)
    }

    private fun handleWithSectionContent(
        binding: ComicDetailSectionWithTextBinding,
        model: String?
    ) {
        if (model.isNullOrEmpty()) binding.layoutComicSectionWithText.gone()
        else binding.textComicDetailSectionWithText.text = model
    }
}
