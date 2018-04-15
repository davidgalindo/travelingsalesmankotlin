import TravelingSalesmanProblem.Edge

/**
 * Created by David on 4/8/2018.
 * Code for the branch and bound
 * An incomplete implementation. Look forward to it soon.
 */
class BranchAndBound(val tsp:TravelingSalesmanProblem) {
    var circuitWeight:Int = 0
    val distanceArray: ArrayList<ArrayList<Int>> = ArrayList()

    init {
        //Convert the matrix into a distance array
        for (edges in tsp.cityMatrix){
            val row = ArrayList<Int>()
            row.addAll(edges.map { return@map it.weight })
            distanceArray.add(row)
        }
    }

    /*
    @Synchronized fun solve(){
        val distanceSet: HashSet<Int> = HashSet(distanceArray.size)
        for (i in 0 until distanceArray.size){
            distanceSet.add(i)
        }

        //Find optimal greedy cost based on a start at zero
        val cost = findCost(0, distanceSet, distanceArray)

        //Create new set that represents the cities we need to visit
        val visitedCitiesList = IntArray(distanceArray.size, {
            return@IntArray it
        })





    }


    private fun findCost(i: Int, citySet: HashSet<Int>, distances: ArrayList<ArrayList<Int>>): Int {
        //Base case: empty distance array
        if (distances.isEmpty()) {
            return distances[0][i]
        }
        //Remove the current city from the location set to not count it
        citySet.remove(i)

        //Find the lowest value among the location set
        var lowestCost: Int = Int.MAX_VALUE
        var lowestIndex: Int = 0
        citySet.forEach { cityItem ->
            val cost = distances[i][cityItem]
            if (cost < lowestCost) {
                lowestCost = cost
                lowestIndex = i
            }
        }
        return lowestCost + findCost(lowestIndex, citySet, distances)
    }
    */

    }

    /*
    @Synchronized fun solve(): ArrayList<Int> {
        val resultArray = ArrayList<Int>()
        val tspMatrix = ArrayList(tsp.cityMatrix)
        return recursiveStep(tspMatrix,resultArray)
    }

    //Does most of the work provided a matrix.
    private fun recursiveStep(matrix: ArrayList<ArrayList<Edge>>, resultArray:ArrayList<Int>):ArrayList<Int>{
        //Base case: Matrix size is 1, return the last edge
        if(matrix.size == 1){
            val value = matrix[0][0]
            println("(${value.startCity} ${value.endCity}) of size $circuitWeight")
            return resultArray
        }

        //Find the row minimum and modify the matrix accordingly
        for (rowArray in matrix){
            //Find the row minimum
            var rowMin = Int.MAX_VALUE //Initialize at max value

            for (col in 0 until rowArray.size){
                val row = rowArray[col]
                if(row.weight == TravelingSalesmanProblem.BLANK) continue //Ignore self-visiting
                if(rowMin > row.weight) rowMin = row.weight
            }
            //Once the minimum is found, modify every value in the row
            for (i in 0 until rowArray.size){
                if(rowArray[i].weight > 0)
                    rowArray[i].weight -= rowMin
            }
            //Add the row minimum to the circuitWeight
            circuitWeight += rowMin
        }

        //Find the column minimum and modify the matrix accordingly
        for(col in 0 until matrix.size){
            var colMin = matrix[0][col]

            for (row in 0 until matrix.size){
                val currentValue = matrix[col][row]
                if(currentValue.weight == TravelingSalesmanProblem.BLANK) continue //Ignore self-visiting
                if(colMin.weight > currentValue.weight){
                    colMin = currentValue
                }
            }

            //Once we have the column minimum, modify every value in the column
            val columnArray = ArrayList<Edge>()
            for (row in 0 until matrix.size) {
                if (col == row) {
                    columnArray.add(Edge(row, col, TravelingSalesmanProblem.BLANK));continue
                }
                if(matrix[row][col].weight > 0) {
                    matrix[row][col].weight -= colMin.weight
                }
                columnArray.add(matrix[row][col])
            }
            //Then add the column minimum to the circuit weight
            circuitWeight += colMin.weight
        }
        println(matrix)

        val minColArray = ArrayList<Int>()
        //Calculate each column's minimum value
        for(col in 0 until matrix.size){
            var minimum = 101
            for (row in 0 until matrix.size){
                minimum = if (matrix[row][col].weight in 0 until minimum) matrix[row][col].weight else minimum
            }
            minColArray.add(minimum)
        }


        //Now that we've reduced the matrix by the row and column minimum, it's time to create a penalty matrix.
        val penaltyMatrix = ArrayList<ArrayList<Edge>>()

        for(rowIndex in 0 until matrix.size){
            val rowArray = matrix[rowIndex]
            val penaltyRow = ArrayList<Edge>()
            for (col in 0 until matrix.size){
                //Add the rows and columns together, and then subtract the resulting values by the current value
                val originalEntry = matrix[rowIndex][col]
                //If it's a negative entry, add it as is
                if(originalEntry.weight < 0){penaltyRow.add(originalEntry);continue}

                //Get the minimum
                val minimum = minColArray[col] + getMinEdge(rowArray).weight
                //Add it to the penalty array
                penaltyRow.add(Edge(rowIndex,col,minimum))

            }
            //Add the penalty row onto the penalty matrix
            penaltyMatrix.add(penaltyRow)
        }
        println("Penalty: $penaltyMatrix")
        //Now we find the largest value in the penalty matrix.
        var maxRowIndex = 0; var maxColIndex = 0; var maxEdge: Edge = Edge(0,0,0)
        for(row in 0 until penaltyMatrix.size){
            for(col in 0 until penaltyMatrix[row].size){
                val currentValue = penaltyMatrix[row][col]
                if(currentValue.weight <= 0) continue
                if(currentValue.weight > maxEdge.weight){
                    maxEdge = currentValue
                    maxRowIndex = row
                    maxColIndex = col
                }
            }
        }


        //Finally, remove the max row and max column from the matrix.
        matrix.removeAt(maxRowIndex)
        for(row in 0 until matrix.size){
            //Remove the corresponding value from the column, one at a time
            matrix[row].removeAt(maxColIndex)
        }
        //(maxRowIndex, maxColIndex) is the edge that's part of our circuit
        print("(${maxEdge.startCity} ${maxEdge.endCity})")
        //Continue recursion
        return recursiveStep(matrix,resultArray)
    }

    private fun addRowOrCol(array: ArrayList<Int>):Int{
        var sum = 0
        for (value in array){
            sum += value
        }
        return sum
    }

    companion object {
        private fun getMinEdge(array: ArrayList<Edge>): Edge {
            var min = array.first()
            for (edge in array){
                min = if(edge.weight < min.weight || min.weight < 0) edge else min
            }
            return min
        }
    }*/
