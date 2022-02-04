package ch.hftm.vsblog.services;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;

import ch.hftm.vsblog.model.Entry;
import io.quarkus.runtime.StartupEvent;

@Dependent
public class ServiceInitialization {

    @Inject
    KeyPairGenerator keyGenerator;

    @Transactional
    public void intializeData(@Observes StartupEvent event) {
        // Ensure that there is a Keypair around
        keyGenerator.generateKeys();
        
        if (Entry.listAll().size() < 1) {
            var e1 = new Entry();
            e1.title = "Modern Java Backend with Quarkus";
            e1.author = "Simeon";
            e1.content = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, ...";
            e1.persist();

            var e2 = new Entry();
            e2.title = "Deploying Java-REST-API on Azure";
            e2.author = "Simeon";
            e2.content = "At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam ...";
            e2.persist();
        }
    }
}