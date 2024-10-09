package org.mathieu.data.remote.responses

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@InternalSerializationApi
/**
 * Represents detailed information about a location, typically received from an API response.
 *
 * @property id The unique identifier for the location.
 * @property name The name of the location.
 * @property type The type or category of the location.
 * @property dimension The specific dimension in which the location exists.
 * @property created The timestamp indicating when the location was added to the database.
 */

@Serializable
internal data class LocationPreviewResponse(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val created: String,
)