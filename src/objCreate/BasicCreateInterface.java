package objCreate;

import objects.BasicAbstract;

@FunctionalInterface
public interface BasicCreateInterface {
    public abstract BasicAbstract createBasic(int x, int y, int depth);    
} 
