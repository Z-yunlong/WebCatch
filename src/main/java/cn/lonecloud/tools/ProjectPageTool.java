package cn.lonecloud.tools;

import cn.lonecloud.bean.Project;
import cn.lonecloud.tools.itf.IWebReptile;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * http://www.cdgdc.edu.cn/xwyyjsjyxx/sy/glmd/264462.shtml
 * 该网址学科数据抓取Demo
 * Created by lonecloud on 2017/8/8.
 *
 * @author lonecloud
 */
public class ProjectPageTool implements IWebReptile<Project> {

    @Override
    public List<Project> catchWebByUrl(String url) {
        return analyzeHTMLByString(url);
    }

    protected List<Project> analyzeHTMLByString(String url) {
        List<Project> projects = new ArrayList<>();
        Project p = null;
        Document document = null;
        try {
            document = Jsoup.parse(new URL(url).openStream(), "gb2312", url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements tbodys = document.getElementsByTag("tbody");//获取数据表
        if (tbodys.isEmpty()) {
            throw new RuntimeException("网址错误");
        }
        Elements title = document.getElementsByTag("title");
        Element tbody = tbodys.get(0);
        Elements trs = tbody.getElementsByTag("tr");
        String fid = "", sid = "";
        for (int i = 1; i < trs.size(); i++) {
            Element tr = trs.get(i);
            Elements tds = tr.getElementsByTag("td");
            int index = 0;
            if (tds.size() == 4) {
                //一级
                Elements firstE = tds.get(0).getElementsByTag("span");
                fid = firstE.get(0).text().trim();
                buildProject(tds, 0, null, projects);
                //二级目录
                Elements secondE = tds.get(1).getElementsByTag("span");
                sid = secondE.get(0).text().trim();
                buildProject(tds, 1, fid, projects);
                index = 2;
            }
            //一个一级下面有多个二级分目录特殊处理
            if (tds.size() == 3) {
                //二级目录
                Elements secondE = tds.get(0).getElementsByTag("span");
                sid = secondE.get(0).text().trim();
                buildProject(tds, 0, fid, projects);
                index = 1;
            }
            //学科
            Elements numProject = tds.get(index++).getElementsByTag("span");
            p = new Project();
            p.setId(numProject.get(0).text().trim());
            p.setName(tds.get(index).getElementsByTag("span").get(0).text());
            p.setPid(sid);
            projects.add(p);
        }
        return projects;
    }

    /**
     * 构建Project对象
     *
     * @param tds
     * @param index
     * @param pid
     * @param projects
     */
    private void buildProject(Elements tds, int index, String pid, List<Project> projects) {
        Project p;
        Elements span = tds.get(index).getElementsByTag("span");
        p = new Project();
        p.setId(span.get(0).text());
        p.setName(span.get(1).text());
        p.setPid(pid);
        projects.add(p);
    }

}
