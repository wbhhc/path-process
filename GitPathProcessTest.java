public class GitPathProcessTest{
    private String key;
    private String gitPath;

    @Before
    public void setup(){
        gitPath="C\\Users\\wbhhc\\git\\gci";
        key="RCSxxxxx";
    }

    @Test
    public void gci_test() throws Exception{
        Writer writer4=new GitWriter(gitPath,key);
        PathProcess p= PathProcessFactory.create(GitRepositoryEnum.gci,writer4);
        p.run();
    }
}