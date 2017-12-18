package com.netcracker.avizhen.persistence;

import com.netcracker.avizhen.persistence.config.PersistenceConfig;
import com.netcracker.avizhen.persistence.ui.MainFrame;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Александр on 18.12.2017.
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(PersistenceConfig.class);
        new MainFrame("Orcle final app", context);
    }

}
