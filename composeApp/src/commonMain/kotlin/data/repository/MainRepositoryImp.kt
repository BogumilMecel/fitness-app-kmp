package data.repository

import data.BaseRepository
import data.api.MainApiClient
import domain.repository.MainRepository

@Suppress("unused")
class MainRepositoryImp(private val mainApiClient: MainApiClient) : MainRepository, BaseRepository()