package org.mathieu.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.InternalSerializationApi
import org.mathieu.data.local.objects.LocationPreviewObject
import org.mathieu.data.remote.responses.LocationResponse
import org.mathieu.data.remote.responses.PaginatedResponse
import org.mathieu.domain.models.locationPreview.LocationPreview


internal class LocationPreviewAPI(private val client: HttpClient){

    /**
     * Fetches a list of locations from the API.
     *
     * If the page parameter is not provided, it defaults to fetching the first page.
     *
     * @param page The page number to fetch. If null, the first page is fetched by default.
     * @return A paginated response containing a list of [LocationResponse] for the specified page.
     * @throws HttpException if the request fails or if the status code is not [HttpStatusCode.OK].
     */
    @OptIn(InternalSerializationApi::class)
    suspend fun getLocations(page: Int?): PaginatedResponse<LocationResponse> = client
        .get("location/") {
            if (page != null)
                url {
                    parameter("page", page)
                }
        }
        .accept(HttpStatusCode.OK)
        .body()


    /**
     * Fetches the details of a location with the given ID from the service.
     *
     * @param id The unique identifier of the location to retrieve.
     * @return The [LocationResponse] representing the details of the location.
     * @throws HttpException if the request fails or if the status code is not [HttpStatusCode.OK].
     */
    @OptIn(InternalSerializationApi::class)
    suspend fun getLocation(id: Int): LocationPreviewObject? = client
        .get("location/$id")
        .accept(HttpStatusCode.OK)
        .body()
}