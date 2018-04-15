import com.sun.xml.internal.fastinfoset.util.StringArray

/**
 * Created by David on 4/7/2018.
 */

object Main{

    @JvmStatic fun main(args: Array<String>) {
        val tsp = TravelingSalesmanProblem.generateRandomTSP(5)
        tsp.print()
        println()

        val bruteForceCircuit = BruteForceAlgorithm(tsp).solve()
        println("Brute Force Solution: ")
        println("$bruteForceCircuit of size ${tsp.sizeOf(bruteForceCircuit)}")
        println()

        //Nearest Neighbor
        val nearestNeighborCircuit = NearestNeighbor(tsp).solve()
        println("Nearest Neighbor solution")
        println("$nearestNeighborCircuit of size ${tsp.sizeOf(nearestNeighborCircuit)}")
        println()

    }
}
