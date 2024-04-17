package ru.export.testapp.list.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import ru.export.testapp.list.data.EquipmentListApi
import ru.export.testapp.list.domain.EquipmentListInteractor
import ru.export.testapp.list.domain.EquipmentListInteractorImpl
import ru.export.testapp.list.presentation.EquipmentListViewModel
import ru.export.testapp.list.repository.EquipmentListRepository
import ru.export.testapp.list.repository.EquipmentListRepositoryImpl

val equipmentListModule = module {
    viewModel {
        EquipmentListViewModel(
            interactor = get(),
            onEquipmentItemListener = get(),
            resourceProvider = get()
        )
    }

    single<EquipmentListInteractor> {
        EquipmentListInteractorImpl(repository = get())
    }

    single<EquipmentListRepository> {
        EquipmentListRepositoryImpl(api = get())
    }

    single {
        val retrofit: Retrofit = get()
        retrofit.create(EquipmentListApi::class.java)
    }
}