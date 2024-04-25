package di

import domain.model.Country
import domain.services.SettingsService
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.datetime.TimeZone
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import org.lighthousegames.logging.logging
import utils.Constants

private val sharedModule = module {
    single<HttpClient> {
        val settingsService: SettingsService = get()
        HttpClient {
            expectSuccess = true
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
                logger = object: Logger {
                    override fun log(message: String) {
                        logging(tag = "KtorClient").debug {
                            message
                        }
                    }
                }
            }

            defaultRequest {
                header(
                    key = "Content-Type",
                    value = "application/json"
                )
                settingsService.getAccessToken()?.let {
                    header(
                        key = Constants.Headers.AUTHORIZATION,
                        value = "Bearer $it"
                    )
                }
                header(
                    key = Constants.Headers.COUNTRY,
                    value = Country.POLAND.shortName
                )
                header(
                    key = Constants.Headers.TIMEZONE,
                    value = TimeZone.currentSystemDefault().id
                )
                url(urlString = "http://192.168.0.132/")
                port = 8080
            }

            install(ContentNegotiation) {
                json(
                    json = Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    },
                    contentType = ContentType.Any
                )
            }
        }
    }
}

val sharedModules = listOf(
    sharedModule,
    authModule,
    splashModule
)