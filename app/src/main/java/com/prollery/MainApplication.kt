package com.prollery

import android.app.Application
import com.prollery.notetakerapp.repo.NoteDao
import com.prollery.notetakerapp.repo.NotesRepo
import com.prollery.notetakerapp.repo.PrimaryDatabase
import com.prollery.notetakerapp.ui.viewmodels.NotesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.koinApplication
import org.koin.dsl.module

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MainApplication)
            modules(listOf(viewModelModule, repoModule, roomDaoModule, utilModule))
        }
    }

    val viewModelModule = module {
        viewModel {
            NotesViewModel(get())
        }
    }

    val repoModule = module {
        single {
            NotesRepo(get())
        }
    }

    val roomDaoModule = module {
        single {
            PrimaryDatabase.getDatabase(androidContext())
        }
        single {
            get<PrimaryDatabase>().noteDao()
        }
    }

    val utilModule = module {

    }
}