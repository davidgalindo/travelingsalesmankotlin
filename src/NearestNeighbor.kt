import java.util.*

/**
 * Created by David on 4/8/2018.
 * Nearest Neighbor: Check every possible edge and find the shortest one. Again, we start and end at 0.
 */
class NearestNeighbor(tsp:TravelingSalesmanProblem){
    val tsp:TravelingSalesmanProblem
    val circuit: ArrayList<Int>
    //Constructor
    init{
        this.tsp = tsp
        circuit = ArrayList<Int>()
        circuit.add(0) //Add the origin city
    }
    @Synchronized fun solve(): ArrayList<Int>{
        val orderedCircuit = TravelingSalesmanProblem.generateStraightHamCircuit(tsp.size)
        //Remove last city since we know where we have to go
        orderedCircuit.removeAt(orderedCircuit.lastIndex)
        val result = "0 ${recursiveStep(orderedCircuit)}0"
        val scanner = Scanner(result)
        val resultingArray = ArrayList<Int>()
        while(scanner.hasNext()){
            val current = scanner.nextInt()
            resultingArray.add(current)
        }
        return resultingArray
    }

    private fun recursiveStep(circuit: ArrayList<Int>): String{
        if(circuit.size == 1){
            return ""
        }
        val start = circuit[0]
        var bestCity = -1
        var bestEdge = -1
        var bestIndex = 0
        for(i in 1 until circuit.size){
            if(bestCity == -1){
                bestCity = circuit[i]
                bestEdge = tsp.getEdgeWeight(start,bestCity)
                bestIndex = i
                continue
            }
            val currentEdge = tsp.getEdgeWeight(start,circuit[i])
            if (currentEdge < bestEdge && bestEdge > -1){
                bestCity = circuit[i]
                bestEdge = currentEdge
                bestIndex = i
            }
        }
        //Swap the best city and the new first city.
        //But only perform the swap if we have more than one value
        if(circuit.size > 1) {
            val holding = circuit[1]
            circuit[1] = circuit[bestIndex]
            circuit[bestIndex] = holding
        }
        //Remove the first city, as we no longer need to travel there. T
        circuit.removeAt(0)
        return "$bestCity ${recursiveStep(circuit)}"
    }

}
