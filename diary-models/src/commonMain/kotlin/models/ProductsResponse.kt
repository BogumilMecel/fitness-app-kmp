package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductsResponse(
    @SerialName("results")
    val results: List<Product>,

    @SerialName("page")
    val page: Int,

    @SerialName("has_next_page")
    val hasNextPage: Boolean,
)
