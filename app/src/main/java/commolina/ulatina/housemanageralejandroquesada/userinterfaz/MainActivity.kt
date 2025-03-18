package commolina.ulatina.housemanageralejandroquesada.userinterfaz



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import commolina.ulatina.housemanageralejandroquesada.ui.theme.HouseManagerTheme
import commolina.ulatina.housemanageralejandroquesada.userinterfaz.navigation.AppNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HouseManagerTheme {
                AppNavGraph()
            }
        }
    }
}
