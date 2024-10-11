package di

import org.koin.dsl.module
import presentation.DiaryScreenModel

val diaryModule = module {
    factory { DiaryScreenModel() }
}