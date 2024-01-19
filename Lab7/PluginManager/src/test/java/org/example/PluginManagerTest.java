package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PluginManagerTest {
    @Test
    public void testPluginLoading() throws Exception {
        PluginManager manager = new PluginManager("C:\\Users\\astaf\\IdeaProjects\\JavaTasks\\Lab7\\plugins");

        Plugin myPlugin = manager.load("first.jar", "org.example.HelloPlugin");
        assertNotNull(myPlugin);
        myPlugin.doUsefull();

        Plugin anotherPlugin = manager.load("second.jar", "org.example.HelloPlugin");
        assertNotNull(anotherPlugin);
        anotherPlugin.doUsefull();
    }

}