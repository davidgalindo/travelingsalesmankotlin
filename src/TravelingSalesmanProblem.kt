import java.util.*

/**
 * Created by David on 4/7/2018.
 */

class TravelingSalesmanProblem(var cityMatrix: ArrayList<ArrayList<Edge>>, var size:Int = cityMatrix.size){

    //Get specific edge weight
    fun getEdgeWeight(startCity: Int, endCity: Int): Int {
        return cityMatrix[startCity][endCity].weight
    }

    //Gets the size of a Hamiltonian circuit.
    fun sizeOf(hamCircuit: ArrayList<Int>): Int{
        //Check if we have a circuit in the first place/
        /*if(!isHamiltonianCircuit(hamCircuit)){
            return 0
        }*/
        var sum = 0
        //Then find its sum.
        for (i in 0 until hamCircuit.size -1){
            val startCity = hamCircuit[i]
            val endCity = hamCircuit[i+1]
            val edge = getEdgeWeight(startCity,endCity)
            sum += edge
        }
        return sum
    }

    fun print(){
        for (array in cityMatrix){
            for (i in array){
                print("$i ")
            }
            println()
        }
    }
    //Data classes
    data class Edge(val startCity: Int, val endCity: Int, var weight: Int){override fun toString(): String { return "$weight" }}



    //Static object that will create our TSP Array.
    companion object{
        //Generate Random TSPs of size n. Gives each edge a length between 1 and 100.
        val MIN = 1
        val MAX = 100
        val BLANK = -1 //fills square with a "skip me" value

        fun generateRandomTSP(ofSize:Int): TravelingSalesmanProblem{
            val resultMatrix = newInitialArrayList(ofSize)
            /*We must follow our assumptions for the TSP. They are:
                    1. Graph must be symmetric (eg. AB = BA)
                    2. Graph is simple (no self-connected points)
                    3. Graph is connected (every vertex is connected to every other vertex
                    4. Every edge has an integer weight
              The following nested for loop will help us generate the matrix.
                    */
            for (row in 0 until ofSize){
                for (col in row until ofSize){
                    //Case I: Same row/column, set a "pass" value and continue filling other squares
                    if (row == col){
                        resultMatrix[row][col] = Edge(row,col,BLANK)
                        continue //Continue with the iteration
                    }else{//Case II: Row not equal to column.
                        //Math.random() produces a random decimal from 0 to 1, exclusive. Adding the min guarantees the value will
                        //be from 1 to 101, exclusive. We then cast to to an Integer.
                        val randomValue:Int =  (Math.random()*MAX + MIN).toInt()
                        //To make things symmetric, we fill in (row, column) and (column, row) with the same value.
                        //We can do this safely since our matrix is a square.
                        resultMatrix[row][col] = Edge(row,col,randomValue)
                        resultMatrix[col][row] = Edge(col,row,randomValue)
                    }
                }//End column loop
            }//End row loop
            //Then we return our resulting TSP
            return TravelingSalesmanProblem(resultMatrix)
        }//End generateRandomTSP()

        fun generateStraightHamCircuit(ofSize: Int):ArrayList<Int>{
            val result = newIntArrayList(ofSize+1)
            for (i in 0 until ofSize){
                result[i] = i
            }
            result[ofSize] = 0
            return result
        }


        fun newIntArrayList(objectCount: Int): ArrayList<Int>{
            val list = ArrayList<Int>()
            for (i in 0 until objectCount){
                list.add(0)
            }
            return list
        }

        private fun newEdgeArrayList(objectCount: Int): ArrayList<Edge>{
            val list = ArrayList<Edge>()
            //Annoying thing about Kotlin: it doesn't automatically fill ArrayLists. We gotta do that ourselves.
            for(i in 0 until objectCount){
                list.add(Edge(0,0,0))
            }
            return list
        }
        private fun newInitialArrayList(objectCount: Int): ArrayList<ArrayList<Edge>>{
            val list = ArrayList<ArrayList<Edge>>()
            for (i in 0 until objectCount){
                val currentObject = newEdgeArrayList(objectCount)
                list.add(currentObject)
            }
            return list
        }

    }//End companion object
}
