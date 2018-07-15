package cn.cerc.example.service;

import cn.cerc.example.common.AppDB;
import cn.cerc.jbean.core.CustomService;
import cn.cerc.jbean.core.DataValidateException;
import cn.cerc.jdb.core.Record;
import cn.cerc.jdb.mysql.BuildQuery;
import cn.cerc.jdb.mysql.SqlQuery;

public class SvrPartInfo extends CustomService {

    public boolean search() {
        Record headIn = getDataIn().getHead();
        BuildQuery f = new BuildQuery(this);
        f.add("select * from %s where corpNo_='%s'", AppDB.Table_PartInfo, AppDB.DerekCorp);

        if (headIn.hasValue("searchText_")) {
            f.byLink(new String[] { "code_", "name_" }, headIn.getString("searchText_"));
        }

        getDataOut().appendDataSet(f.open());
        return true;
    }

    public boolean append() {
        Record headIn = getDataIn().getHead();
        SqlQuery cdsTmp = new SqlQuery(this);
        cdsTmp.add("select * from %s where corpNo_='%s'", AppDB.Table_PartInfo, AppDB.DerekCorp);
        cdsTmp.setMaximum(0);
        cdsTmp.open();

        cdsTmp.append();
        cdsTmp.setField("corpNo_", AppDB.DerekCorp);
        cdsTmp.setField("name_", headIn.getString("name_"));
        cdsTmp.setField("unit_", headIn.getString("unit_"));
        cdsTmp.setField("code_", headIn.getString("code_"));
        cdsTmp.setField("stock_", 0);
        cdsTmp.post();

        return true;
    }

    public boolean download() throws DataValidateException {
        Record headIn = getDataIn().getHead();
        DataValidateException.stopRun("UID_不允许为空", !headIn.hasValue("UID_"));
        String uid = headIn.getString("UID_");

        SqlQuery cdsTmp = new SqlQuery(this);
        cdsTmp.add("select * from %s where corpNo_='%s'", AppDB.Table_PartInfo, AppDB.DerekCorp);
        cdsTmp.add(" and UID_=%s", uid);
        cdsTmp.open();
        DataValidateException.stopRun("记录不存在", cdsTmp.eof());

        getDataOut().getHead().copyValues(cdsTmp.getCurrent());
        return true;
    }

    public boolean modify() throws DataValidateException {
        Record headIn = getDataIn().getHead();
        DataValidateException.stopRun("UID_不允许为空", !headIn.hasValue("UID_"));
        String uid = headIn.getString("UID_");
        String code = headIn.getString("code_");
        String name = headIn.getString("name_");
        String unit = headIn.getString("unit_");
        SqlQuery cdsTmp = new SqlQuery(this);
        cdsTmp.add("select * from %s where corpNo_='%s'", AppDB.Table_PartInfo, AppDB.DerekCorp);
        cdsTmp.add(" and UID_=%s", uid);
        cdsTmp.open();
        DataValidateException.stopRun("记录不存在", cdsTmp.eof());

        cdsTmp.edit();
        cdsTmp.setField("code_", code);
        cdsTmp.setField("name_", name);
        cdsTmp.setField("unit_", unit);
        cdsTmp.post();
        return true;
    }

    public boolean delete() throws DataValidateException {
        Record headIn = getDataIn().getHead();
        DataValidateException.stopRun("UID_不允许为空", !headIn.hasValue("UID_"));
        String uid = headIn.getString("UID_");

        SqlQuery cdsTmp = new SqlQuery(this);
        cdsTmp.add("select * from %s where corpNo_='%s'", AppDB.Table_PartInfo, AppDB.DerekCorp);
        cdsTmp.add(" and UID_=%s", uid);
        cdsTmp.open();
        DataValidateException.stopRun("记录不存在", cdsTmp.eof());

        cdsTmp.delete();
        return true;
    }

}
