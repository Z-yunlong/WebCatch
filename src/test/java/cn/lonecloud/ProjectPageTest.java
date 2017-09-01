package cn.lonecloud;

import cn.lonecloud.bean.Project;
import cn.lonecloud.tools.ProjectPageTool;
import cn.lonecloud.tools.itf.IWebReptile;
import cn.lonecloud.utils.DBUtils;
import org.junit.Test;

import java.util.List;

/**
 * Created by lonecloud on 2017/8/8.
 */
public class ProjectPageTest {

    @Test
    public void getProjectTool(){
        IWebReptile<Project> catchTool=new ProjectPageTool();
        List<Project> projects = catchTool.catchWebByUrl("http://www.cdgdc.edu.cn/xwyyjsjyxx/sy/glmd/264462.shtml");
//        DBUtils.bashInsert("project",projects);
        System.out.println(projects);
    }
}
