package com.afrikatek.planetrouting.rest;

import com.afrikatek.planetrouting.model.Node;
import com.afrikatek.planetrouting.model.Planet;
import com.afrikatek.planetrouting.repository.NodeRepository;
import com.afrikatek.planetrouting.repository.PlanetRepository;
import com.afrikatek.planetrouting.service.RoutesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class GraphController {
    private final NodeRepository nodeRepository;
    private final PlanetRepository planetRepository;
    private final RoutesService routesService;

    public GraphController(NodeRepository nodeRepository, PlanetRepository planetRepository, RoutesService routesService) {
        this.nodeRepository = nodeRepository;
        this.planetRepository = planetRepository;
        this.routesService = routesService;
    }

    @DeleteMapping("/graphs/{id}")
    public void deleteById(@PathVariable Long id) {

    }

    @GetMapping("/graphs")
    public ResponseEntity<List<Node>> findAll() {
        List<Node> lst = nodeRepository.getAllRoutes();
        return ResponseEntity.ok().body(lst);
    }

    @GetMapping("/planets")
    public ResponseEntity<Iterable<Planet>> findAllPlanets() {
        Iterable<Planet> lst = planetRepository.findAll();
        return ResponseEntity.ok().body(lst);
    }

    @GetMapping("/graphs/{id}")
    public ResponseEntity<Node> findById(@PathVariable Long id) {

        return ResponseEntity.ok().body(new Node());
    }

    @GetMapping("/route/{originPlanet}/{destPlanet}")
    public ResponseEntity<List<Node>> shortestRoute(@PathVariable String originPlanet,
                                                    @PathVariable String destPlanet) {
        List<Node> lst = routesService.getShortest(originPlanet, destPlanet);
        return ResponseEntity.ok().body(lst);
    }

    @GetMapping("/planets/{id}")
    public ResponseEntity<Planet> findByName(@PathVariable Long id) {
        Optional<Planet> p = planetRepository.findById(id);
        return ResponseEntity.ok().body(p.get());
    }

    @PostMapping("/routes/create")
    public ResponseEntity<Node> create(@RequestBody Node node) {
        planetRepository.save(new Planet(node.getPlanetNode(), node.getName()));
        return ResponseEntity.ok(nodeRepository.save(node));
    }
}
