package org.mathieu.data.local.objects

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mathieu.domain.models.character.Character
import org.mathieu.domain.models.location.Location
import org.mathieu.domain.models.locationPreview.LocationPreview

internal class LocationPreviewObject: RealmObject {
    @PrimaryKey
    var id: Int = -1
    var name: String = ""
    var type: String = ""
    var dimension: String = ""
}

internal fun LocationPreview.toRealmObject() = LocationPreviewObject().also { obj ->
    obj.id = id
    obj.name = name
    obj.type = type
    obj.dimension = dimension
}

internal fun LocationPreviewObject.toModel() = LocationPreview(
    id = id,
    name = name,
    type = type,
    dimension = dimension,
)