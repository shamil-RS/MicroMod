import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
object ArtistScreenNav : NavKey

@Serializable
data class DetailCardArtist(
    val id: Int = 0,
    val name: String
) : NavKey