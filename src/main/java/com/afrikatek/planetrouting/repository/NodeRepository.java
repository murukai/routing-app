package com.afrikatek.planetrouting.repository;

import com.afrikatek.planetrouting.model.Node;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NodeRepository extends CrudRepository<Node, Long> {
    // Gets all Todos of the given user
    @Query("SELECT n FROM Node n WHERE n.planetNode = ?1")
    Node getNodeByNode(String planetNode);

    // Gets Todos related to given user in the desc order of id so that most recent comes first
    @Query("SELECT n FROM Node n WHERE n.planetNode = ?1 AND  n.planetOrig =?2")
    List<Node> getNodeInbetween(String destNode, String originNode);
    @Query("SELECT n FROM Node n")
    List<Node> getAllRoutes();
    @Query("SELECT n FROM Node n WHERE n.planetNode = ?1")
    List<Node> getPrimeNodes(String destNode);
}
