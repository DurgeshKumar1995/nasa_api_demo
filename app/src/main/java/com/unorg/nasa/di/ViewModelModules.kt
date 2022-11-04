package com.unorg.nasa.di



import com.unorg.nasa.impl.AppImpl
import com.unorg.nasa.repo.APIRepository
import com.unorg.nasa.ui.main.view_model.MainViewModel
import com.unorg.nasa.ui.welcome.viewModel.WelcomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module


object ViewModelModules {

    val viewModels: Module = module {

        single<APIRepository> { AppImpl(get(),get()) }
        viewModel { MainViewModel(get()) }
        viewModel { WelcomeViewModel() }

    }


}

