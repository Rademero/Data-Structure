package cs2321;

import net.datastructures.*;

/**
 * @author Ruihong Zhang
 * Reference: Text book: R14.17 on page 678
 *
 */
public class Course {
	AdjListGraph<String, Integer> graph;
	/**
	 * @param courses: An array of course information. Each element in the array is also array:
	 * 				starts with the course name, followed by a list (0 or more) of prerequisite course names.
	 * 
	 */
	public Course(String courses[][]) {
		graph = new AdjListGraph(true);
		for(String[] s : courses) {
            Vertex<String> v = graph.insertVertex(s[0]);
            if(s.length > 1) {
                for(int i = s.length - 1; i > 0; i--) {
                	graph.insertEdge(graph.insertVertex(s[i]), v, 1);
                }
            } 
        }
	} 
	 
	/**  
	 * @param course
	 * @return find the earliest semester that the given course could be taken by a students after taking all the prerequisites. 
	 */
	public int whichSemester(String course) {
		 Vertex<String> u = null;
	        for(Vertex<String> v : graph.vertices()) {
	            if(v.getElement().equals(course)) u = v;
	        }
	        return Toporder(u, 0);
	} 
	public int Toporder(Vertex<String> course, int semCount) {
        for(Edge<Integer> e : graph.incomingEdges(course)) {
            Vertex<String> v = graph.opposite(course, e); 
            if(graph.incomingEdges(v) != null) {
            	semCount += 1;
                return Toporder(v, semCount); 
            }
        }
        return semCount + 1; 
    } 
}  
