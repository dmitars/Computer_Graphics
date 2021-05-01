package factory;


import controllers.PageType;
import loaders.Loader;
import loaders.MainPageLoader;
import sample.FXSystem;

/**
 * The type Loader main.java.factory.
 */
public class LoaderFactory {
    /**
     * The System.
     */
    FXSystem system;

    /**
     * Instantiates a new Loader main.java.factory.
     *
     * @param system the system
     */
    public LoaderFactory(FXSystem system){
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
