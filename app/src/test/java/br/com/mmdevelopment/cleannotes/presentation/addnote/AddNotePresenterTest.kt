package br.com.mmdevelopment.cleannotes.presentation.addnote

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
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

class AddNotePresenterTest {

    private lateinit var addNotePresenter: AddNotePresenter

    @RelaxedMockK
    private lateinit var view: AddNoteContract.View

    @MockK
    private lateinit var repository: NoteRepository

    @Before
    fun setUp() {
        initMockkAnnotations()
        addNotePresenter = AddNotePresenter(view, repository)

        RxAndroidPlugins.setInitMainThreadSchedulerHandler {
            Schedulers.trampoline()
        }
    }

    @Test
    fun setView() {
        addNotePresenter.setView(view)
        assertThat(view).isNotNull()
    }

    @Test
    fun `should return a completable when successfully inserts a note`() {
        every { repository.insert(any()) } returns Completable.complete()

        addNotePresenter.insertNote(NOTE)

        verify(exactly = 1) { repository.insert(NOTE) }
    }

    @Test
    fun `should return a Note if note is found by ID`() {
        every { repository.findById(any()) } returns Maybe.just(NOTE)

        addNotePresenter.getNoteById(NOTE.id)

        verify(exactly = 1) { repository.findById(NOTE.id) }
    }
}