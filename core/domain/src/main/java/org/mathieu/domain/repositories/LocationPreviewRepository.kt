package org.mathieu.domain.repositories

import kotlinx.coroutines.flow.Flow
import org.mathieu.domain.models.location.Location
import org.mathieu.domain.models.locationPreview.LocationPreview

interface LocationPreviewRepository {

    /**
     * Retrieves a list of all locations.
     * as a [Flow] of [List] of [Location] objects.
     * @return A flow emitting a list of locations.
     */
    suspend fun getLocations(): Flow<List<Location>>

    /**
     * Retrieves the location with the specified [id].
     * @param id The unique identifier of the location to be fetched.
     */
    suspend fun getLocation(id: Int): LocationPreview
}