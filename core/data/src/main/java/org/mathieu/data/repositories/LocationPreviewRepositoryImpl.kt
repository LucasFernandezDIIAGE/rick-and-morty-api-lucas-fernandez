package org.mathieu.data.repositories

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.InternalSerializationApi
import org.mathieu.data.local.LocationPreviewLocal
import org.mathieu.domain.repositories.LocationPreviewRepository
import org.mathieu.data.local.objects.toRealmObject
import org.mathieu.data.local.objects.toModel
import org.mathieu.data.remote.LocationPreviewAPI
import org.mathieu.domain.models.location.Location
import org.mathieu.domain.models.locationPreview.LocationPreview

internal class LocationPreviewRepositoryImpl(
    private val context: Context,
    private val locationPreviewLocal: LocationPreviewLocal,
    private val locationPreviewAPI: LocationPreviewAPI
): LocationPreviewRepository {

    /**
     * Retrieves a list of all locations.
     * @return a list of [Location]
     */
    override suspend fun getLocations(): Flow<List<Location>> {
        TODO()
    }
    /**
     * Retrieves the location with the specified id.
     * 1. Tries to fetch the location from the local storage.
     * 2. If not found locally, fetches the location from the API.
     * 3. Upon successful API retrival, saves the location to local storage.
     *
     * @param id The unique identifier of the location to be fetched.
     * @return The [Location] with the specified [id].
     * @throws Exception If the location cannot be found both locally and via the API.
     */
    @OptIn(ExperimentalStdlibApi::class, InternalSerializationApi::class)
    override suspend fun getLocation(id: Int): LocationPreview{
        TODO()
    }

}

inline fun <T, R> Flow<List<T>>.mapLocationElement(crossinline transform: suspend (value: T) -> R): Flow<List<R>> =
    this.map { list ->
        list.map { transform(it) }
    }