import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@ExperimentalFoundationApi
@Composable
fun App() {
    val game = remember { Game() }
    Column {
        Text("Player: ${game.currentTurn}")
        Text("Winner: ${game.gameWinner}")
        for(i in 0..2) {
            Row(modifier = Modifier.weight(1f)) {
                for(j in 0..2) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .onClick {
                                if(game.getCell(i, j) == null && game.gameWinner == null) {
                                    game.play(i, j)
                                    game.cpuPlay()
                                }
                            }
                            .padding(8.dp)
                            .border(1.dp, Color.Gray)
                            .fillMaxSize()
                            .weight(1f)
                    ) {
                        when(game.getCell(i, j)) {
                            Cell.Cross -> {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = null,
                                    tint = Color.Red
                                )
                            }
                            Cell.Circle -> {
                                Box(
                                    Modifier
                                        .border(2.dp, Color.Blue, CircleShape)
                                        .size(24.dp)
                                )
                            }
                            null -> {}
                        }
                    }
                }
            }
        }
    }

}