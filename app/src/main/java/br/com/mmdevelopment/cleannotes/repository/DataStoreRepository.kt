package br.com.mmdevelopment.cleannotes.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

const val LAYOUT_PREF = "layout_pref"

class DataStoreRepository(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = LAYOUT_PREF)


    suspend fun saveToDataStore(layout: Boolean) {
        context.dataStore.edit {
            it[LAYOUT] = layout
        }
    }

    fun getLayout() = context.dataStore.data.map {
        it[LAYOUT] ?: true
    }

    companion object {
        val LAYOUT = booleanPreferencesKey("LAYOUT")
    }
}