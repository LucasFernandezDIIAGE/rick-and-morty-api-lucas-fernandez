package org.mathieu.characters.details

import android.app.Application
import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import org.koin.core.component.inject
import org.mathieu.characters.list.CharactersAction
import org.mathieu.domain.models.location.Location
import org.mathieu.domain.models.locationPreview.LocationPreview
import org.mathieu.domain.repositories.CharacterRepository
import org.mathieu.ui.Destination
import org.mathieu.ui.ViewModel


sealed interface CharacterDetailsAction {
    data class SelectedLocationCard(val location: org.mathieu.domain.models.locationPreview.LocationPreview):
        CharacterDetailsAction
}

class CharacterDetailsViewModel(application: Application) : org.mathieu.ui.ViewModel<CharacterDetailsState>(
    CharacterDetailsState(), application) {

    private val characterRepository: org.mathieu.domain.repositories.CharacterRepository by inject()

    fun init(characterId: Int) {
        fetchData(
            source = { characterRepository.getCharacter(id = characterId) }
        ) {

            onSuccess {
                updateState { copy(avatarUrl = it.avatarUrl, name = it.name, locationPreview = it.locationPreview, error = null) }
            }

            onFailure {
                updateState { copy(error = it.toString()) }
            }

            updateState { copy(isLoading = false) }
        }

    }

    /**
     * Handles the user actions for the character details screen.
     * Used to navigate to the location details screen when a location card is selected.
     * @param action The action to handle.
     */
    fun handleAction(action: CharacterDetailsAction){
        when(action) {
            is CharacterDetailsAction.SelectedLocationCard -> selectedLocation(action.location)
        }
    }

    private fun selectedLocation(location: LocationPreview)=
        sendEvent(Destination.LocationDetails(location.id.toString()))


}

/**
 * Vibrates the phone for a given duration.
 * Used to vibrate the phone when a location card is selected.
 * @param context The context to use for the vibration.
 * @param duration The duration in milliseconds for the vibration.
 */
fun vibratePhone(context: Context, duration: Long = 500) {
    // Obtenir le service de vibration
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    // Vérifiez si le téléphone peut vibrer
    if (vibrator.hasVibrator()) {
        // Pour les versions Android 26 et ultérieures
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val effect = VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE)
            vibrator.vibrate(effect)
        } else {
            // Pour les versions antérieures
            vibrator.vibrate(duration)
        }
    }
}



data class CharacterDetailsState(
    val isLoading: Boolean = true,
    val avatarUrl: String = "",
    val name: String = "",
    val location: Location? = null,
    val locationPreview: LocationPreview? = null,
    val error: String? = null
)