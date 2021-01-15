package com.lucasdias.feature_comic.list

import com.lucasdias.core.resource.Resource
import com.lucasdias.domain.model.ComicSummary
import com.lucasdias.domain.model.Image
import com.lucasdias.domain.usecase.FetchComicList
import com.lucasdias.feature_comic.util.CoroutineTestRule
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class ComicListViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    private val fetchComicList: FetchComicList = mockk()
    private val viewModel =
        spyk(ComicListViewModel(fetchComicList, coroutinesTestRule.testDispatcher))

    @Test
    fun `IF the application calls the next page while not loading another request THEN calls the executeRequest`() {
        every { viewModel.isNotLoading() } returns true
        every { viewModel.executeRequest() } just Runs

        viewModel.requestNextPage()

        verify(exactly = 1) { viewModel.executeRequest() }
    }

    @Test
    fun `IF the app calls the next page while not loading another request THEN it doesn't call the executeRequest`() {
        every { viewModel.isNotLoading() } returns false
        every { viewModel.executeRequest() } just Runs

        viewModel.requestNextPage()

        verify(exactly = 0) { viewModel.executeRequest() }
    }

    @Test
    fun `IF the application does not request that the next request be blocked THEN the value of requestIsBlocked must be false`() {
        val expected = false
        val actual = viewModel.requestIsBlocked

        assertEquals(expected, actual)
    }

    @Test
    fun `IF the application request that the next request be blocked THEN the value of requestIsBlocked must be true`() {
        val expected = true
        viewModel.blockNextRequest()

        val actual = viewModel.requestIsBlocked

        assertEquals(expected, actual)
    }

    @Test
    fun `IF when making the request and the request is not blocked THEN call use case fetchComicList`() = runBlockingTest {
            viewModel.requestIsBlocked = false
            coEvery { fetchComicList() } returns comicSummaryListMocked

            viewModel.request()

            coVerify(exactly = 1) { fetchComicList() }
        }

    @Test
    fun `IF when making request the request is blocked THEN returns an empty list with success status`() =
        runBlockingTest {
            viewModel.blockNextRequest()

            val expected = emptyComicSummaryListMocked
            val actual = viewModel.request()

            assertEquals(expected, actual)
        }

    private companion object {
        val emptyComicSummaryListMocked = Resource.Success<List<ComicSummary>>(emptyList())
        val comicSummaryListMocked = Resource.Success<List<ComicSummary>>(
            mutableListOf(
                ComicSummary(1, "ABC", Image("DEF", "GHI"))
            )
        )
    }
}
