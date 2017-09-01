package cn.lonecloud.bean;

/**
 *  学科门类
    代码及名称
 * Created by lonecloud on 2017/8/8.
 * @author lonecloud
 */
public class Project {

    //id代码
    private String id;
    //name名称
    private String name;
    //父id
    private String pid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pid='" + pid + '\'' +
                '}'+'\n';
    }
}
