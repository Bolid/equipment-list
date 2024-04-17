package ru.export.testapp.details.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create
import ru.export.testapp.details.data.EquipmentDetailsApi
import ru.export.testapp.details.domain.EquipmentDetailsInteractor
import ru.export.testapp.details.domain.EquipmentDetailsInteractorImpl
import ru.export.testapp.details.presentation.EquipmentDetailsViewModel
import ru.export.testapp.details.repository.EquipmentDetailsRepository
import ru.export.testapp.details.repository.EquipmentDetailsRepositoryImpl

val equipmentDetailsModule = module {
    viewModel {
        EquipmentDetailsViewModel(
            interactor = get(),
            resourceProvider = get(),
            onEquipmentItemListener = get()
        )
    }

    single<EquipmentDetailsInteractor> {
        EquipmentDetailsInteractorImpl(repository = get())
    }

    single<EquipmentDetailsRepository> {
        EquipmentDetailsRepositoryImpl(api = get())
    }
    single<EquipmentDetailsApi> {
        val retrofit: Retrofit = get()
        retrofit.create()
    }
}