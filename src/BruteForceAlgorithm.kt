import java.util.*

/**
 * Created by David on 4/7/2018.
 * Calculates the optimal circuit using branch and bound.
 */
class BruteForceAlgorithm(tsp: TravelingSalesmanProblem){
    var smallestCircuit: ArrayList<Int>? = null
    var smallestCircuitSize: Int = 0
    var tsp: TravelingSalesmanProblem
    //Attempt to solve - Make sure it's thread safe. We start at the first city
    @Synchronized fun solve(): ArrayList<Int>{
        //Create an ordered circuit first and establish properties.
        val orderedCircuit = TravelingSalesmanProblem.generateStraightHamCircuit(tsp.size)
        this.tsp = tsp
        //Set the initial circuit as the smallest
        smallestCircuit = orderedCircuit
        smallestCircuitSize = tsp.sizeOf(smallestCircuit as ArrayList<Int>)
        //Removes the start and end points
        orderedCircuit.removeIf { it == 0 }
        recursiveStep(orderedCircuit,0)
        //Edge case: If the ordered circuit is the shortest circuit
        if(orderedCircuit.size < tsp.size +1){
            orderedCircuit.add(0)
            orderedCircuit.add(0,0)
        }

        return smallestCircuit as ArrayList<Int>
    }

    init{
        this.tsp = tsp
    }

    private fun recursiveStep(circuit: ArrayList<Int>,index: Int){
        //Nothing left to permute, we can find the circuit's length here
        if(index >= circuit.size -1){
            //Add first and last city
            circuit.add(0)
            circuit.add(0,0)
            val circuitLength = tsp.sizeOf(circuit)
            if(smallestCircuitSize > circuitLength){
                @Suppress("UNCHECKED_CAST")
                smallestCircuit = circuit.clone() as ArrayList<Int>
                smallestCircuitSize = circuitLength
            }
            //After we get the length, remove the last indexes
            circuit.removeIf { it == 0 }
            return
        }


        //Right here we are running a loop to generate every possible circuit. Note
        //this has a time complexity of O(n!), so it hurts!
        var i = index
        while(i < circuit.size){
            //Perform a swap
            var holding = circuit[index]
            circuit[index] = circuit[i]
            circuit[i] = holding

            //Recursive step
            recursiveStep(circuit,index+1)

            //Then swap them back
            holding = circuit[index]
            circuit[index] = circuit[i]
            circuit[i] = holding
            ++i
        }
    }

}