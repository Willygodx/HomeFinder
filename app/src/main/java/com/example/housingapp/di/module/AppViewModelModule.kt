package com.example.housingapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.housingapp.di.ViewModelFactory
import com.example.housingapp.di.ViewModelKey
import com.example.housingapp.presentation.viewmodel.AddNoteViewModel
import com.example.housingapp.presentation.viewmodel.DetailsViewModel
import com.example.housingapp.presentation.viewmodel.HomeViewModel
import com.example.housingapp.presentation.viewmodel.MainViewModel
import com.example.housingapp.presentation.viewmodel.SignInViewModel
import com.example.housingapp.presentation.viewmodel.SignUpViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AppViewModelModule {

    // Метод для связывания MainViewModel с ViewModel.
    // Аннотация @Binds указывает, что метод связывает интерфейс ViewModel с конкретной реализацией MainViewModel.
    // @IntoMap используется для добавления ViewModel в карту, где ключом будет класс ViewModel.
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun mainViewModel(viewModel: MainViewModel): ViewModel

    // Метод для связывания HomeViewModel с ViewModel.
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun homeViewModel(viewModel: HomeViewModel): ViewModel

    // Метод для связывания SignInViewModel с ViewModel.
    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    fun signInViewModel(viewModel: SignInViewModel): ViewModel

    // Метод для связывания SignUpViewModel с ViewModel.
    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    fun signUpViewModel(viewModel: SignUpViewModel): ViewModel

    // Метод для связывания DetailsViewModel с ViewModel.
    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    fun detailsViewModel(viewModel: DetailsViewModel): ViewModel

    // Метод для связывания AddNoteViewModel с ViewModel.
    @Binds
    @IntoMap
    @ViewModelKey(AddNoteViewModel::class)
    fun addNoteViewModel(viewModel: AddNoteViewModel): ViewModel

    // Метод для связывания ViewModelFactory с ViewModelProvider.Factory.
    // ViewModelFactory используется для создания экземпляров ViewModel с параметрами.
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}