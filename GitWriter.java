import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrarList;
import java.util.List;
import org.springframework.util.StringUtils;

public class GitWriter implements Writer{

    String gitPath="C:\\Users\\wbhhc\\git\\项目名\\";//git仓库路径
    String key="git提交备注关键字";

    public GitWriter(String gitPath,String key){
        this.gitPath=gitPath;
        this.key=key;
    }

    StringBuilder builder=new StringBuilder();//内容缓存
    List<String> list=new ArrayList<String>();

    int commitCount=0;
    int fileCount=0;
    boolean enable=true;//启用标记，无内容时关闭，不输出

    @Orverride
    public List<String> getSourcePaths() throws Exception{
        List<String> commits=getCommits();
        for(String commitId:commits){
            fromCommitId(commitId);
        }
        return list;
    }

    private List<String> getCommits() throws IOException,InterruptedException,Exception{
        String[] cmd={"git","log","--grep=".concat(key)};
        Process p;
        p=Runtime.getRuntime().exec(cmd,null,new File(gitPath));
        BufferedReader br=new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line=null;
        List<String> commits=new ArrayList<String>();
        while((line=br.readLine()!=null)){
            if(line.indexOf("commit")==0){
                commitCount++;
                commits.add(line.replace("commit",""));
            }
        }

        int wf=p.waitFor();
        if(wf!=0) throw new Exception("读取git提交记录失败！");
        if(commits.size()==0){
            enable=false;
        }
        return commits;
    }

    private List<String> fromCommitId(String commitId) throws IOException,InterruptedException,Exception{
        String[] cmd={"git","show","--name-only","--format=online",commitId};
        Process p;
        p=Runtime.getRuntime().exec(cmd,null,new File(gitPath));
        BufferedReader br=new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line=null;
        while((line=br.readLine()!=null)){
            //只保留文件路径，排除包含commitid的merge记录
            if(line.indexOf(".")>=0 && line.lastIndexOf(".")==line.indexOf(".")){
                fileCount++;
                list.add(line);
            }
        }
        int wf=p.waitFor();
        if(wf!=0) throw new Exception("读取git提交记录失败！");

        return list;
    }

    @Orverride
    public void drawBody(List<String> finalList){
        for(String str:finalList){
            if(StringUtils.isNotEmpty(str)){
                builder.append(str).append("\n");
            }
        }
    }

    @Orverride
    public void drawHead(String head){
        builder.append(head).append("\n");
    }

    @Orverride
    public void drawFooter(String footer){
        builder.append(footer).append("\n");
    }

    @Orverride
    public void appear() throws Exception{
        if(enable){
            System.out.printLn(builder.toString());
        }
    }
}
