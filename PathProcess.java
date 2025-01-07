public abstract class PathProcess{
    Writer Writer;

    public void setWriter(Writer writer){
        this.Writer=writer;
    }

    public void run() throws Exception{
        List<String> list=new ArrayList<String>();
        list=writer.getSourcePaths();
        list=replaceAll(list);

        List<String> finalList=arrangement(list);

        writer.drawHead(getHead());
        writer.drawBody(finalList);
        writer.drawFooter(getFooter());
        writer.appear();
    }

    private List<String> replaceAll(List<String> list){
        //替换
        List<String> newList=new ArrayList<String>();
        for(String string:list){
            newList.add(replaceLinePath(string));
        }
        return newList;
    }

    //整理
    protected List<String> arrangement(List<String> list){
        //去重
        Set<String> set=new HashSet<String>();
        set.addAll(list);
        //排序
        List<String> fullList=new ArrarList<String>();
        fullList.addAll(set);
        return fullList;
    }

    protected abstract String getHead();

    protected abstract String getFooter();

    protected abstract String replaceLinePath(String line);
}