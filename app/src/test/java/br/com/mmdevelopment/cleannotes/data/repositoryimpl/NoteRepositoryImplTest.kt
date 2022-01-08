package br.com.mmdevelopment.cleannotes.data.repositoryimpl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.mmdevelopment.cleannotes.data.local.AppDatabase
import br.com.mmdevelopment.cleannotes.data.local.dao.NoteDao
import br.com.mmdevelopment.cleannotes.data.mapper.NoteEntityMapper
import br.com.mmdevelopment.cleannotes.util.TestData.NOTE
import br.com.mmdevelopment.cleannotes.util.TestData.initMockkAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NoteRepositoryImplTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var repository: NoteRepositoryImpl

    @RelaxedMockK
    private lateinit var db: AppDatabase

    private lateinit var dao: NoteDao

    @RelaxedMockK
    private lateinit var mapper: NoteEntityMapper

    @Before
    fun setUp() {
        initMockkAnnotations()
        repository = NoteRepositoryImpl(db, mapper)
        dao = db.noteDao()

        // Override AndroidSchedulers.mainThread() which doesn't work in unit tests
        RxAndroidPlugins.setInitMainThreadSchedulerHandler {
            Schedulers.trampoline()
        }
    }

    @Test
    fun getNotes() {
        repository.getNotes()
            .test()
            .assertResult(listOf())

        verify(exactly = 1) { dao.getAll() }
    }

    @Test
    fun findById() {
        repository.findById(NOTE.id)
            .test()
            .assertResult(NOTE)

        verify(exactly = 1) { dao.findById(any()) }
    }

    @Test
    fun search() {
        repository.search("Title")
            .test()
            .assertResult(listOf(NOTE))

        verify(exactly = 1) { dao.searchDatabase(any()) }
    }

    @Test
    fun insert() {
        repository.insert(NOTE)
            .test()
            .assertComplete()

        verify(exactly = 1) { dao.insert(any()) }
    }

    @Test
    fun delete() {
        repository.delete(NOTE)
            .test()
            .assertComplete()

        verify(exactly = 1) { dao.delete(any()) }
    }

    @Test
    fun update() {
        repository.update(NOTE)
            .test()
            .assertComplete()

        verify(exactly = 1) { dao.update(any()) }
    }
}