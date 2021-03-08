package com.afrikatek.planetrouting;


import com.afrikatek.planetrouting.model.Node;
import com.afrikatek.planetrouting.rest.GraphController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(GraphController.class)
public class GraphControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private GraphController graphController;
    @Test
    public void getShortestRoute() throws Exception {

        Node node= new Node();
        node.setName("Gliese 436");
        node.setDistance(5.25);
        node.setSpeedDelay(4);
        node.setPlanetOrig("A");

        List<Node>lst= new ArrayList<>();
        lst.add(node);
        ResponseEntity<List<Node>> route = ResponseEntity.ok().body(lst);

        given(graphController.shortestRoute("Earth","Gliese 436")).willReturn(route);

        mvc.perform(get( "http://localhost:8888/api/route/Earth/Gliese 436")

                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
                //.andExpect(jsonPath("$[0].city", is(arrival.getCity())));
    }

}
