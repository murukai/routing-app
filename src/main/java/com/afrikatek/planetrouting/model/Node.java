package com.afrikatek.planetrouting.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "nodes", schema = "app")
public class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int routeId;
    @Transient
    private String name;
    private String planetOrig;
    @Column(length=5)
    private String planetNode;
    private double speedDelay;
    private double distance;

    public Node(String planetOrig, String planetNode, double speedDelay, double distance) {
        this.planetOrig = planetOrig;
        this.planetNode = planetNode;
        this.speedDelay = speedDelay;
        this.distance = distance;
    }

}