public class PathProcessFactory{
    public static PathProcess create(GitRepositoryEnum repository){
        PathProcess process;
        switch(repository){
            case gci:
                process=new GciPathProcess();
                break;
            default:
                process=null;
                break;
        }
        return process;
    }
}