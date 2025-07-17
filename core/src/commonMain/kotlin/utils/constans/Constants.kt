package utils.constans

object Constants {
    object Headers {
        const val COUNTRY = "country"
        const val TIMEZONE = "time_zone"
        const val AUTHORIZATION = "Authorization"
    }
    object Query {
        const val LATEST_DATE_TIME = "latest_date_time"
        const val PAGE = "page"
        const val SEARCH_TEXT = "search_text"
    }
    object Paging {
        const val API_PAGE_SIZE = 20L
        const val OFFLINE_PAGE_SIZE = 100L
    }
}