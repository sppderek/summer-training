package cn.cerc.example.forms;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jdb.core.Record;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;
import cn.cerc.jpage.core.UrlRecord;

public class FrmPartInfo extends AbstractForm {

    @Override
    public IPage execute() {
        JspPage jspPage = new JspPage(this, "common/FrmPartInfo.jsp");

        String message = getRequest().getParameter("message");
        if (message != null) {
            jspPage.setMessage(message);
        }

        LocalService svr = new LocalService(this, "SvrPartInfo.search");
        Record headIn = svr.getDataIn().getHead();
        headIn.setField("searchText_", getRequest().getParameter("searchText"));
        if (!svr.exec()) {
            jspPage.setMessage(svr.getMessage());
            return jspPage;
        }
        DataSet dataSet = svr.getDataOut();

        jspPage.add("dataSet", dataSet);
        return jspPage;
    }

    public IPage append() {
        JspPage jspPage = new JspPage(this, "common/FrmPartInfo_append.jsp");
        String submit = getRequest().getParameter("submit");
        if (submit == null || "".equals(submit)) {
            return jspPage;
        }

        String code = jspPage.getRequest().getParameter("code");
        if (code == null || "".equals(code)) {
            jspPage.setMessage("物料编码不允许为空");
            return jspPage;
        }

        String name = jspPage.getRequest().getParameter("name");
        if (name == null || "".equals(name)) {
            jspPage.setMessage("商品名称不允许为空");
            return jspPage;
        }

        String unit = jspPage.getRequest().getParameter("unit");
        if (unit == null || "".equals(unit)) {
            jspPage.setMessage("单位不允许为空");
            return jspPage;
        }

        LocalService svr = new LocalService(this, "SvrPartInfo.append");
        Record headIn = svr.getDataIn().getHead();
        headIn.setField("code_", code);
        headIn.setField("name_", name);
        headIn.setField("unit_", unit);
        if (!svr.exec()) {
            jspPage.setMessage(svr.getMessage());
            return jspPage;
        }

        UrlRecord url = new UrlRecord();
        url.setSite("FrmPartInfo");
        url.addParam("message", "添加成功");
        return new RedirectPage(this, url.getUrl());
    }

    public IPage modify() {
        JspPage jspPage = new JspPage(this, "common/FrmPartInfo_modify.jsp");
        String uid = getRequest().getParameter("uid");
        if (uid == null || "".equals(uid)) {
            jspPage.setMessage("uid 不允许为空");
            return jspPage;
        }

        String message = getRequest().getParameter("message");
        if (message != null) {
            jspPage.setMessage(message);
        }

        LocalService svr1 = new LocalService(this, "SvrPartInfo.download");
        Record headIn1 = svr1.getDataIn().getHead();
        headIn1.setField("UID_", uid);
        if (!svr1.exec()) {
            jspPage.setMessage(svr1.getMessage());
            return jspPage;
        }
        jspPage.add("record", svr1.getDataOut().getHead());

        String submit = getRequest().getParameter("submit");
        if (submit != null && !"".equals(submit)) {
            LocalService svr2 = new LocalService(this, "SvrPartInfo.modify");
            Record headIn2 = svr2.getDataIn().getHead();
            headIn2.setField("UID_", uid);
            headIn2.setField("code_", jspPage.getRequest().getParameter("code"));
            headIn2.setField("name_", jspPage.getRequest().getParameter("name"));
            headIn2.setField("unit_", jspPage.getRequest().getParameter("unit"));
            if (!svr2.exec()) {
                jspPage.setMessage(svr2.getMessage());
                return jspPage;
            }
            return new RedirectPage(this, "FrmPartInfo");
        }
        return jspPage;
    }

    public IPage delete() {
        UrlRecord url = new UrlRecord();

        String uid = getRequest().getParameter("uid");
        LocalService svr = new LocalService(this, "SvrPartInfo.delete");
        Record headIn2 = svr.getDataIn().getHead();
        headIn2.setField("UID_", uid);

        if (!svr.exec()) {
            url.setSite("FrmPartInfo.modify");
            url.addParam("uid", uid);
            url.addParam("message", svr.getMessage());
            return new RedirectPage(this, url.getUrl());
        }

        url.setSite("FrmPartInfo");
        url.addParam("message", "删除成功");
        return new RedirectPage(this, url.getUrl());
    }

    @Override
    public boolean logon() {
        return true;
    }
}
