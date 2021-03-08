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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "planets", schema = "app")
public class Planet {
    @Id
    @Column(length=5)
    private String planetNode;
    @Column(length=50)
    private String name;

    // One planet - many Nodes
    @OneToMany(mappedBy = "planetNode")
    private List<Node> nodes = new ArrayList<Node>();

    public Planet(String planetNode, String name) {
        this.planetNode = planetNode;
        this.name = name;
    }


}
