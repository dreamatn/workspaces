package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTBLD {
    int JIBS_tmp_int;
    DBParm BPTTBLD_RD;
    String K_PGM_NAME = "BPZRTBLD";
    int WS_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTBLD BPRTBLD = new BPRTBLD();
    SCCGWA SCCGWA;
    BPCRTBLD BPCRTBLD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    String LK_DATA = " ";
    public void MP(SCCGWA SCCGWA, BPCRTBLD BPCRTBLD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTBLD = BPCRTBLD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZRTBLD return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LK_DATA = IBS.CLS2CPY(SCCGWA, BPCRTBLD.DAT_POINTER);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRTBLD);
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        IBS.CPY2CLS(SCCGWA, LK_DATA.substring(0, BPCRTBLD.DAT_LEN), BPRTBLD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRTBLD.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
        } else if (BPCRTBLD.FUNC == 'U') {
            B020_UPDATE_RECORD_PROC();
        } else if (BPCRTBLD.FUNC == 'D') {
            B030_DELETE_RECORD_PROC();
        } else if (BPCRTBLD.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
        } else if (BPCRTBLD.FUNC == 'R') {
            B050_READ_UPD_RECORD_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCRTBLD.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTTBLD();
    }
    public void B020_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTTBLD();
    }
    public void B030_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTTBLD();
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTBLD();
    }
    public void B050_READ_UPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTBLD_UPD();
    }
    public void T000_READ_BPTTBLD() throws IOException,SQLException,Exception {
        BPTTBLD_RD = new DBParm();
        BPTTBLD_RD.TableName = "BPTTBLD";
        IBS.READ(SCCGWA, BPRTBLD, BPTTBLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "TBLD (" + IBS.CLS2CPY(SCCGWA, BPRTBLD.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_WRITE_BPTTBLD() throws IOException,SQLException,Exception {
        BPTTBLD_RD = new DBParm();
        BPTTBLD_RD.TableName = "BPTTBLD";
        IBS.WRITE(SCCGWA, BPRTBLD, BPTTBLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "TBLD (" + IBS.CLS2CPY(SCCGWA, BPRTBLD.KEY) + ") DUPLICATE";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READ_BPTTBLD_UPD() throws IOException,SQLException,Exception {
        BPTTBLD_RD = new DBParm();
        BPTTBLD_RD.TableName = "BPTTBLD";
        BPTTBLD_RD.upd = true;
        IBS.READ(SCCGWA, BPRTBLD, BPTTBLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "TBLD (" + IBS.CLS2CPY(SCCGWA, BPRTBLD.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_REWRITE_BPTTBLD() throws IOException,SQLException,Exception {
        BPTTBLD_RD = new DBParm();
        BPTTBLD_RD.TableName = "BPTTBLD";
        IBS.REWRITE(SCCGWA, BPRTBLD, BPTTBLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "TBLD (" + IBS.CLS2CPY(SCCGWA, BPRTBLD.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_DELETE_BPTTBLD() throws IOException,SQLException,Exception {
        BPTTBLD_RD = new DBParm();
        BPTTBLD_RD.TableName = "BPTTBLD";
        IBS.DELETE(SCCGWA, BPRTBLD, BPTTBLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "TBLD (" + IBS.CLS2CPY(SCCGWA, BPRTBLD.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
