package cs2321;

import net.datastructures.*;

/**
 * @author Ruihong Zhang Reference textbook R14.16 P14.81
 *
 */
public class Travel {
	static AdjListGraph<String, Integer> graph;
	/**
	 * @param routes: Array of routes between cities. routes[i][0] and routes[i][1]
	 *                represent the city names on both ends of the route.
	 *                routes[i][2] represents the cost in string type. Hint: In
	 *                Java, use Integer.valueOf to convert string to integer.
	 */
	public Travel(String[][] routes) {
		graph = new AdjListGraph<String, Integer>(false);
		for (int i = 0; i < routes.length; i++) {
		graph.insertEdge(graph.insertVertex(routes[i][0]),
				graph.insertVertex(routes[i][1]),Integer.valueOf(routes[i][2]));
		}
	}   
 
	/**  
	 * @param departure :   the departure city name
	 * @param destination: the destination city name
	 * @return Return the path from departure city to destination using Depth First
	 *         Search algorithm. The path should be represented as ArrayList or
	 *         DoublylinkedList of city names. The order of city names in the list
	 *         should match order of the city names in the path.
	 * 
	 * @IMPORTANT_NOTE: The outgoing edges should be traversed by the order of the
	 *                  city names stored in the opposite vertices. For example, if
	 *                  V has 3 outgoing edges as in the picture below, V / | \ / |
	 *                  \ B A F your algorithm below should visit the outgoing edges
	 *                  of V in the order of A,B,F. This means you will need to
	 *                  create a helper function to sort the outgoing edges by the
	 *                  opposite city names.
	 * 
	 *                  See the method sortedOutgoingEdges below.
	 */
	public Iterable<String> DFSRoute(String departure, String destination) {
		Map<Vertex<String>,Edge<Integer>> forest = new HashMap();
		set<Vertex<String>> known = new set();
		 AdjListGraph<String, Integer> Newgraph = new AdjListGraph(true);
		DFS(graph, graph.insertVertex(departure), known, forest,Newgraph);
		set<String> newSt = new set();
		return Reconstruct(graph.insertVertex(destination), newSt , Newgraph);
	}
	public set<String> Reconstruct(Vertex<String> destination, set<String> sem, AdjListGraph<String, Integer>Newgraph) {
		Vertex<String> des = Newgraph.insertVertex(destination.getElement());
		sem.addFirst(des.getElement());
        for(Edge<Integer> e : Newgraph.incomingEdges(des)) {
            Vertex<String> v = Newgraph.opposite(des, e); 
            if(Newgraph.incomingEdges(v) != null) {
                sem.addFirst(v.getElement());
                return Reconstruct(v, sem,Newgraph);
            } 
        }
        return sem;  
    } 
	public static void DFS(Graph<String,Integer> g, Vertex<String> u,
			set<Vertex<String>> known, Map<Vertex<String>,Edge<Integer>> forest,Graph<String,Integer> newg) {
		known.addLast(u); // u has been discovered 
		newg.insertVertex(u.getElement());
		for (Edge<Integer> e : sortedOutgoingEdges(u)) { // for every outgoing edge from u 
			Vertex<String> v = g.opposite(u, e); 
			if (!known.contains(v)) { 
				forest.put(v, e); // e is the tree edge that discovered v 
				newg.insertEdge(newg.insertVertex(u.getElement()), newg.insertVertex(v.getElement()), e.getElement());
				DFS(g, v, known, forest,newg); // recursively explore from v 
				} 
			}  
		}   
	/**
	 * @param departure:   the departure city name
	 * @param destination: the destination city name
	 * @return Return the path from departure city to destination using Breadth
	 *         First Search algorithm. The path should be represented as ArrayList
	 *         or DoublylinkedList of city names. The order of city names in the
	 *         list should match order of the city names in the path.
	 * 
	 * @IMPORTANT_NOTE: The outgoing edges should be traversed by the order of the
	 *                  city names stored in the opposite vertices. For example, if
	 *                  V has 3 outgoing edges as in the picture below, V / | \ / |
	 *                  \ B A F your algorithm below should visit the outgoing edges
	 *                  of V in the order of A,B,F. This means you will need to
	 *                  create a helper function to sort the outgoing edges by the
	 *                  opposite city names.
	 * 
	 *                  See the method sortedOutgoingEdges below.
	 */

	public Iterable<String> BFSRoute(String departure, String destination) {
		Map<Vertex<String>,Edge<Integer>> forest = new HashMap();
		set<Vertex<String>> known = new set();
		 AdjListGraph<String, Integer> Newgraph = new AdjListGraph(true);
		BFS(graph, graph.insertVertex(departure), known, forest,Newgraph);
		set<String> newSt = new set();
		return Reconstruct(graph.insertVertex(destination), newSt , Newgraph);
	}  
	 public static void BFS(Graph<String,Integer> g, Vertex<String> s, 
			 set<Vertex<String>> known, Map<Vertex<String>,Edge<Integer>> forest, AdjListGraph<String, Integer> newgraph) { 
		 PositionalList<Vertex<String>> level = new DoublyLinkedList<>(); 
		 known.addLast(s);
		 newgraph.insertVertex(s.getElement());
		 level.addLast(s); // First level includes only s 
		 while (!level.isEmpty()) { 
			 PositionalList<Vertex<String>> nextLevel = new DoublyLinkedList<>(); 
			 for (Vertex<String> u : level) 
				 for (Edge<Integer> e : sortedOutgoingEdges(u)) { 
					 Vertex<String> v = g.opposite(u, e); 
					 if (!known.contains(v)) { 
						 known.addFirst(v);
						 newgraph.insertVertex(v.getElement());
						 forest.put(v, e); // e is the tree edge that discovered v 
						 newgraph.insertEdge(newgraph.insertVertex(u.getElement()), 
								 newgraph.insertVertex(v.getElement()), e.getElement());
						 nextLevel.addLast(v); // v will be further considered in next pass 
						 } 
					 } 
			 level = nextLevel; // relabel next level to become the current 
			 } 
		 }    
				

	/** 
	 * @param departure:   the departure city name
	 * @param destination: the destination city name
	 * @param itinerary:   an empty DoublylinkedList object will be passed in to the
	 *                     method. When a shorted path is found, the city names in
	 *                     the path should be added to the list in the order.
	 * @return return the cost of the shortest path from departure to destination.
	 * 
	 * @IMPORTANT_NOTE: The outgoing edges should be traversed by the order of the
	 *                  city names stored in the opposite vertices. For example, if
	 *                  V has 3 outgoing edges as in the picture below, V / | \ / |
	 *                  \ B A F your algorithm below should visit the outgoing edges
	 *                  of V in the order of A,B,F. This means you will need to
	 *                  create a helper function to sort the outgoing edges by the
	 *                  opposite city names.
	 * 
	 *                  See the method sortedOutgoingEdges below.
	 */

	public int DijkstraRoute(String departure, String destination, DoublyLinkedList<String> itinerary) {
		Vertex<String> de = graph.insertVertex(departure);
        Vertex<String> des = graph.insertVertex(destination);
 
        Map<Vertex<String>, Integer> shortest_path = shortestPathLengths(graph, de, des, itinerary);
        return shortest_path.get(des);
	}

	public static <V> Map<Vertex<String>, Integer> shortestPathLengths(Graph<String, Integer> g, 
			Vertex<String> src, Vertex<String> des, DoublyLinkedList<String> itinerary) {
		Map<Vertex<String>, Integer> d = new HashMap<>();
		// map reachable v to its d value
		Map<Vertex<String>, Integer> cloud = new HashMap<>();
		// pq will have vertices as elements, with d.get(v) as key
		AdaptablePriorityQueue<Integer, Vertex<String>> pq;
		pq = new HeapPq<>();
		// maps from vertex to its pq locator
		Map<Vertex<String>, Entry<Integer, Vertex<String>>> pqTokens;
		pqTokens = new HashMap<>();
		// for each vertex v of the graph, add an entry to the priority queue, with
		// the source having distance 0 and all others having infinite distance
		 for (Vertex<String> v : g.vertices()) {
	            if (v == src) {
	                d.put(v, 0); 
	            } else
	                d.put(v, Integer.MAX_VALUE);
	            pqTokens.put(v, pq.insert(d.get( v), v));
	        }
		// now begin adding reachable vertices to the cloud
		 while (!pq.isEmpty()) {
	            Entry<Integer, Vertex<String>> entry = pq.removeMin();
	            int key = entry.getKey();
	            Vertex<String> u = entry.getValue();
	            cloud.put(u, key);
	            itinerary.addLast(u.getElement());
	            if(u.getElement().equals(des.getElement())) {
	                return cloud;
	            }
	            pqTokens.remove( u);
	            for (Edge<Integer> e : sortedOutgoingEdges(u)) {
	                Vertex<String> v = g.opposite(u, e);
	                if (cloud.get( v) == null) {
	                    int wgt = e.getElement();
	                    if (d.get(u) + wgt < d.get(v)) {
	                        d.put(v, d.get( u) + wgt);
	                        pq.replaceKey(pqTokens.get(v), d.get(v));
	                    }
	                } 
	            } 
	        }
	        return cloud;
	}  

	public static <V> Map<Vertex<V>, Edge<Integer>> spTree(Graph<V, Integer> g, Vertex<V> s,
			Map<Vertex<V>, Integer> d) {
		Map<Vertex<V>, Edge<Integer>> tree = new HashMap<>();
		for (Vertex<V> v : d.keySet())
			if (v != s)
				for (Edge<Integer> e : g.incomingEdges(v)) { // consider INCOMING edges
					Vertex<V> u = g.opposite(v, e);
					int wgt = e.getElement();
					if (d.get(v) == d.get(u) + wgt)
						tree.put(v, e); // edge is is used to reach v
				}
		return tree;
	}

	/**
	 * I strongly recommend you to implement this method to return sorted outgoing
	 * edges for vertex V You may use any sorting algorithms, such as insert sort,
	 * selection sort, etc.
	 * 
	 * @param v: vertex v
	 * @return a list of edges ordered by edge's name
	 */
	public static Iterable<Edge<Integer>> sortedOutgoingEdges(Vertex<String> v) {
        QuickSort sort = new QuickSort();
        int j = 0;
        for (Edge<Integer> ed : graph.outgoingEdges(v)) {
            j++;
        }
        Edge<Integer>[] edges = new Edge[j];
        int i = 0;
        for (Edge<Integer> ed : graph.outgoingEdges(v)) {
            edges[i] = ed;
            i++;
        }
        sort.sort(edges, graph, v);
        ArrayList<Edge<Integer>> Edge = new ArrayList<>();
        for(Edge<Integer> e : edges) {
            Edge.addLast(e);
        }
        return Edge;
    }
} 
