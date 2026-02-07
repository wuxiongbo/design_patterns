package big_talk.chapter22.bridge0;

abstract class Abstraction{
    protected Implementor implementor;

    public void setImplementor(Implementor implementor){
        this.implementor = implementor;
    }

    public abstract void operation();
}

