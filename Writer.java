import java.util.List;

//输出 写者
public interface Writer{
    //原始路径
    List<String> getSourcePaths() throws Exception;

    //画成body
    void drawBody(List<String> finalList);

    //画头
    void drawHead(String head);

    //画尾
    void drawFooter(String footer);

    //呈现结果
    void appear() throws Exception;
}