package org.example;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PluginManager {
    private final String pluginRootDirectory;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }

    public Plugin load(String pluginName, String pluginClassName) throws ClassNotFoundException, IllegalAccessException, InstantiationException, MalformedURLException, NoSuchMethodException {
        URL pluginURL = new URL("file:/" + pluginRootDirectory.replace("\\", "/") + "/" + pluginName);
        URLClassLoader classLoader = new URLClassLoader(new URL[]{pluginURL});
        Class<?> pluginClass = classLoader.loadClass(pluginClassName);
        return (Plugin) pluginClass.newInstance();
    }


}
