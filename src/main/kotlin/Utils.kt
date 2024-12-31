fun <T> List<List<T>>.mapChange(x: Int, y: Int, value: T): List<List<T>> {
    return mapIndexed { i, row ->
        row.mapIndexed { j, v ->
            if(i == x && j == y) {
                value
            } else {
                v
            }
        }
    }
}
