package br.com.mmdevelopment.cleannotes.presentation.home

import br.com.mmdevelopment.cleannotes.domain.model.Note
import br.com.mmdevelopment.cleannotes.domain.repository.NoteRepository
import br.com.mmdevelopment.cleannotes.util.TestData.NOTE
import br.com.mmdevelopment.cleannotes.util.TestData.initMockkAnnotations
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class HomePresenterTest {

    private lateinit var homePresenter: HomePresenter

    @RelaxedMockK
    private lateinit var view: HomeContract.View

    @MockK
    private lateinit var repository: NoteRepository

    @Before
    fun setUp() {
        initMockkAnnotations()
        homePresenter = HomePresenter(view, repository)

        RxAndroidPlugins.setInitMainThreadSchedulerHandler {
            Schedulers.trampoline()
        }
    }

    @Test
    fun `should return a list of notes when searching for a text`() {
        val expectedList = listOf<Note>()
        every { repository.search(any()) } returns Flowable.just(expectedList)

        homePresenter.onSearch(anyString())

        verify { view.showNotesOnRecycleView(expectedList) }
        verify { repository.search("%${anyString()}%") }
    }

    @Test
    fun `should not update RV list when search returns an error`() {
        val expectedList = listOf<Note>()
        every { repository.search(any()) } returns Flowable.error(Throwable())

        homePresenter.onSearch(anyString())

        verify(inverse = true) { view.showNotesOnRecycleView(expectedList) }
    }

    @Test
    fun `should return all notes from database`() {
        val expectedList = listOf<Note>()
        every { repository.getNotes() } returns Flowable.just(expectedList)

        homePresenter.getAllNotes()

        verify { view.showNotesOnRecycleView(expectedList) }
        verify { repository.getNotes() }
    }

    @Test
    fun `should not update RV list when database fetch returns an error`() {
        val expectedList = listOf<Note>()
        every { repository.getNotes() } returns Flowable.error(Throwable())

        homePresenter.getAllNotes()

        verify(inverse = true) { view.showNotesOnRecycleView(expectedList) }
    }

    @Test
    fun `should delete note passed in`() {
        every { repository.delete(any()) } returns Completable.complete()

        homePresenter.delete(NOTE)

        verify { repository.delete(NOTE) }
    }

    @Test
    fun `should insert note passed in`() {
        every { repository.insert(any()) } returns Completable.complete()

        homePresenter.insertNote(NOTE)

        verify { repository.insert(NOTE) }
    }

    @Test
    fun setView() {
        homePresenter.setView(view)
        assertThat(view).isNotNull()
    }
}