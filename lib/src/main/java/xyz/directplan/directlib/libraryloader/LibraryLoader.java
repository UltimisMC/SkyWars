package xyz.directplan.directlib.libraryloader;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import lombok.Data;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author DirectPlan
 */
public class LibraryLoader {

    @SuppressWarnings("Guava")
    private static final Supplier<URLClassLoaderAccess> URL_INJECTOR = Suppliers.memoize(() -> URLClassLoaderAccess.create((URLClassLoader) LibraryLoader.class.getClassLoader()));

    private final File DEPENDENCY_DIRECTORY;
    private final Deque<CachedDependency> cachedDependencies = new ArrayDeque<>();

    private final Logger logger;

    public LibraryLoader(JavaPlugin plugin) {
        logger = plugin.getLogger();

        DEPENDENCY_DIRECTORY = new File(plugin.getDataFolder(), "dependencies/");
        if(DEPENDENCY_DIRECTORY.mkdirs()) {
            logger.log(Level.INFO, "Created dependency directory '" + DEPENDENCY_DIRECTORY.getPath() + "'...");
        }
    }

    public void loadDependency(Dependency dependency, boolean load) {

        File file = downloadDependency(dependency);
        if(file == null) { // Could be caused by server connectivity or unbound file
            return;
        }
        cachedDependencies.addFirst(new CachedDependency(file));

        if(load) {
            loadDependencyToRuntime(file);
        }
    }

    public void loadDependency(Dependency dependency) {
        loadDependency(dependency, true);
    }

    public void loadDependencyToRuntime(File file) {
        // SoftwareLabLib-1.0-SNAPSHOT-jar-with-dependencies
        logger.log(Level.INFO, "Loading dependency '" + file.getName() + "' to runtime...");
        try {
            URL url = file.toURI().toURL();
            URL_INJECTOR.get().addURL(url);
        } catch (Exception ex) {
            throw new RuntimeException("Cannot load dependency from jar file '" + file.getAbsolutePath() + "'. Reason: " + ex.getMessage());
        }
    }

    public void loadDependenciesFromFolder(File dependencyDirectory) {

        File[] files = dependencyDirectory.listFiles();
        if(files == null || files.length == 0) {
            logger.log(Level.SEVERE, "The specified directory '" + dependencyDirectory.getPath()+" does not exist");
            return;
        }
        int trackedAmount = 0;
        for (File file : files) {
            delay();
            loadDependencyToRuntime(file);
            trackedAmount++;
        }
        logger.log(Level.INFO, "Loaded " + trackedAmount + " dependencies!");
    }
    public void loadDependencies() {
        logger.log(Level.INFO, "Loading libraries...");
        loadDependenciesFromFolder(DEPENDENCY_DIRECTORY);
    }

    public void loadDependencies(Class<?> clazz) {
        logger.log(Level.INFO, "Loading libraries...");

        MavenDependency[] dependencies =  clazz.getAnnotationsByType(MavenDependency.class);
        for (MavenDependency dependency : dependencies) {
            String value = dependency.value();
            String groupId = dependency.groupId();
            String artifactId = dependency.artifactId();
            String version = dependency.version();
            if (!value.isEmpty()) {
                String[] args = value.split("\\|");
                if (args.length == 3) {
                    groupId = args[0];
                    artifactId = args[1];
                    version = args[2];
                }
            }
            loadDependency(groupId, artifactId, version, false);
        }

        for(CachedDependency cachedDependency : cachedDependencies) {
            loadDependencyToRuntime(cachedDependency.getDependencyFile());
            delay();
        }
        logger.log(Level.INFO, "Loaded " + cachedDependencies.size() + " dependencies!");
        cachedDependencies.clear();
    }

    private void delay() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void loadDependency(String groupId, String artifactId, String version, boolean load) {
        loadDependency(new Dependency(groupId, artifactId, version, "https://repo1.maven.org/maven2"), load);
    }

    public void loadDependency(String groupId, String artifactId, String version) {
        loadDependency(groupId, artifactId, version, true);
    }

    private File downloadDependency(Dependency dependency) {

        String displayName = dependency.getArtifactId() + "-" + dependency.getVersion();
        File file = new File(DEPENDENCY_DIRECTORY, displayName + ".jar");
        boolean existed = true;
        if(!file.exists()) {
            existed = false;
            try {
                logger.log(Level.INFO, "Downloading dependency '" + displayName + "'...");
                URL url = dependency.getUrl();
                try (InputStream is = url.openStream()) {
                    Files.copy(is, file.toPath());
                }

                logger.log(Level.INFO, "Download for dependency '" + displayName + "' has complete.");
                return file;
            } catch (Exception ignored) {}
        }
        if(!existed) { // This means that it went through the download process and failed
            logger.log(Level.INFO, "Could not download dependency: " + displayName);
            return null;
        }
        return file;
    }

    @Data
    public static class Dependency {

        private final String groupId;
        private final String artifactId;
        private final String version;
        private final String repoUrl;

        public URL getUrl() throws MalformedURLException {
            String repo = this.repoUrl;
            if (!repo.endsWith("/")) {
                repo += "/";
            }
            repo += "%s/%s/%s/%s-%s.jar";

            String url = String.format(repo, this.groupId.replace(".", "/"), this.artifactId, this.version, this.artifactId, this.version);
            return new URL(url);
        }
    }

    @Data
    public static class CachedDependency {

        private final File dependencyFile;
    }
}
