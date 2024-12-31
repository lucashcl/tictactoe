import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class Game {
    private val state: MutableState<List<List<Cell?>>> = mutableStateOf(List(3) { List(3) { null } })
    private val turn: MutableState<Cell> = mutableStateOf(Cell.Circle)
    private val winner: MutableState<Cell?> = mutableStateOf(null)

    fun play(x: Int, y: Int) {
        state.value = state.value.mapChange(x, y, turn.value)
        turn.value = if(turn.value == Cell.Cross) Cell.Circle else Cell.Cross
        checkForWinner()
    }

    fun cpuPlay() {
        val row = (0..2).random()
        val col = (0..2).random()
        if(gameWinner != null) {
            return
        }

        if(state.value.all { it.all { it != null } }) {
            return
        }

        if(getCell(row, col) == null) {
            play(row, col)
        } else {
            cpuPlay()
        }
    }

    val cells: List<List<Cell?>>
        get() = state.value

    val currentTurn: Cell
        get() = turn.value

    val gameWinner: Cell?
        get() = winner.value

    fun getCell(x: Int, y: Int): Cell? {
        return state.value[x][y]
    }

    fun checkForWinner() {
        val cells = state.value
        for(i in 0..2) {
            if(cells[i][0] == cells[i][1] && cells[i][1] == cells[i][2] && cells[i][0] != null) {
                winner.value = cells[i][0]
                return
            }
            if(cells[0][i] == cells[1][i] && cells[1][i] == cells[2][i] && cells[0][i] != null) {
                winner.value = cells[0][i]
                return
            }
        }
        if(cells[0][0] == cells[1][1] && cells[1][1] == cells[2][2] && cells[0][0] != null) {
            winner.value = cells[0][0]
            return
        }
        if(cells[0][2] == cells[1][1] && cells[1][1] == cells[2][0] && cells[0][2] != null) {
            winner.value = cells[0][2]
            return
        }

    }
}