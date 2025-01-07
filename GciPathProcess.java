public class GciPathProcess extends PathProcess{
    @Orverride
    protected String getHead(){
        return "--gci begin";
    }

    @Orverride
    protected String getFooter(){
        return "--gci end";
    }

    @Orverride
    protected String replaceLinePath(String line){
        line=line.replace("src/main/java","classes/");
        line="/gci/".concat(line);
        line=line.replace(".java",".class");
        return line;
    }
}