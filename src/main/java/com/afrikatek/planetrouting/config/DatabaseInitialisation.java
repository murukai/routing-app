package com.afrikatek.planetrouting.config;

import com.afrikatek.planetrouting.model.Node;
import com.afrikatek.planetrouting.model.Planet;
import com.afrikatek.planetrouting.repository.NodeRepository;
import com.afrikatek.planetrouting.repository.PlanetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Component
public class DatabaseInitialisation implements ApplicationListener<ApplicationReadyEvent> {

        private final Environment env;

        private final NodeRepository nodeRepository;

        private final PlanetRepository planetRepository;

    public DatabaseInitialisation(Environment env, NodeRepository nodeRepository, PlanetRepository planetRepository) {
        this.env = env;
        this.nodeRepository = nodeRepository;
        this.planetRepository = planetRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(DatabaseInitialisation.class);

        @Override
        public void onApplicationEvent(ApplicationReadyEvent event) {
            // String path= env.getRequiredProperty("Sys.init_path");
            // String planetPath= env.getRequiredProperty("Sys.init_planet_path");

            String path = "src/main/resources/routes.csv";
            String planetPath = "src/main/resources/planets.csv";
            // delete first
            planetRepository.deleteAll();
            nodeRepository.deleteAll();
            // initialize data
            try (BufferedReader br = new BufferedReader(new FileReader(new File(planetPath)))) {
                String line = br.readLine();
                while ((line = br.readLine()) != null) {
                    String[] arr = line.split(",");
                    planetRepository.save(new Planet(arr[0].trim(), arr[1].trim()));
                    logger.info(line);
                }
            } catch (IOException iox) {
                System.out.println("Pakaipa!" + iox.toString());
            }
            try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))) {
                String line = br.readLine();
                while ((line = br.readLine()) != null) {
                    String[] arr = line.split(",");
                    nodeRepository.save(
                            new Node(arr[1].trim(), arr[2].trim(), Double.parseDouble(arr[3]), Double.parseDouble(arr[4])));
                    logger.info(line);
                }
            } catch (IOException iox) {
                System.out.println("Pakaipa!" + iox.toString());
            }
            logger.info("Still waiting!");
        }

}
