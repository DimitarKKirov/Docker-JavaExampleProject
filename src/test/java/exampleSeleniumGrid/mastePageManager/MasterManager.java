package exampleSeleniumGrid.mastePageManager;


import exampleSeleniumGrid.lillyPOM.LillyPageManager;

/**
 * Singleton initialization of classes
 * MasterManager class is static class that can be called freely
 * used for instantiating and accessing classes in the project
 * https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples
 */
public class MasterManager {

    // Singleton initialization of classes
    // https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples

    private static MasterManager masterManager= new MasterManager();
    private LillyPageManager lillyPageManager;


    public static MasterManager getMasterManager() {
        return masterManager;
    }

    /**
     * if there is no instance of class LillyPageManager
     * the method is creating one and returning it
     * otherwise is returning the instance of the class
     * @return LillyPageManager instance
     */
    public LillyPageManager lillyPageManager(){
        if (lillyPageManager==null){
            lillyPageManager = new LillyPageManager();
            return lillyPageManager;
        }return lillyPageManager;
    }
}
