package factory;


import loaders.Loader;
import loaders.MainPageLoader;
import sample.MainSystem;

/**
 * The type Loader main.java.factory.
 */
public class LoaderFactory {
    /**
     * The System.
     */
    MainSystem system;

    /**
     * Instantiates a new Loader main.java.factory.
     *
     * @param system the system
     */
    public LoaderFactory(MainSystem system){
        this.system = system;
    }

    /**
     * Get loader loader.
     *
     * @param pageType the page type
     * @return the loader
     */
    public Loader getLoader(controllers.PageType pageType){
        return  switch (pageType){
            case MAIN -> new MainPageLoader(system);
        };
    }
}
