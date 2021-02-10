package factory;


import controllers.PageType;
import loaders.Loader;
import loaders.MainPageLoader;
import sample.MainSystem;

/**
 * The type Loader factory.
 */
public class LoaderFactory {
    /**
     * The System.
     */
    MainSystem system;

    /**
     * Instantiates a new Loader factory.
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
    public Loader getLoader(PageType pageType){
        return  switch (pageType){
            case MAIN -> new MainPageLoader(system);
        };
    }
}
