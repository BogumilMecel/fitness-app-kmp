package di

import data.database.FitnessAppDatabase
import org.koin.core.module.Module
import org.koin.core.scope.Scope
import org.koin.dsl.module

typealias NativeInjectionFactory<T> = Scope.() -> T

fun createSharedNativeModule(fitnessAppDatabase: NativeInjectionFactory<FitnessAppDatabase>): Module {
    return module {
        single { fitnessAppDatabase() }
    }
}