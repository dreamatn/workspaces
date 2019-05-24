package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTBLH {
    int JIBS_tmp_int;
    DBParm BPTTBLH_RD;
    String K_PGM_NAME = "BPZRTBLH";
    int WS_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTBLH BPRTBLH = new BPRTBLH();
    SCCGWA SCCGWA;
    BPCRTBLH BPCRTBLH;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    String LK_DATA = " ";
    public void MP(SCCGWA SCCGWA, BPCRTBLH BPCRTBLH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTBLH = BPCRTBLH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZRTBLH return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LK_DATA = IBS.CLS2CPY(SCCGWA, BPCRTBLH.DAT_POINTER);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRTBLH);
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        IBS.CPY2CLS(SCCGWA, LK_DATA.substring(0, BPCRTBLH.DAT_LEN), BPRTBLH);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRTBLH.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
        } else if (BPCRTBLH.FUNC == 'U') {
            B020_UPDATE_RECORD_PROC();
        } else if (BPCRTBLH.FUNC == 'D') {
            B030_DELETE_RECORD_PROC();
        } else if (BPCRTBLH.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
        } else if (BPCRTBLH.FUNC == 'R') {
            B050_READ_UPD_RECORD_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCRTBLH.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTTBLH();
    }
    public void B020_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTTBLH();
    }
    public void B030_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTTBLH();
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTBLH();
    }
    public void B050_READ_UPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTBLH_UPD();
    }
    public void T000_READ_BPTTBLH() throws IOException,SQLException,Exception {
        BPTTBLH_RD = new DBParm();
        BPTTBLH_RD.TableName = "BPTTBLH";
        IBS.READ(SCCGWA, BPRTBLH, BPTTBLH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "TBLH (" + IBS.CLS2CPY(SCCGWA, BPRTBLH.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_WRITE_BPTTBLH() throws IOException,SQLException,Exception {
        BPTTBLH_RD = new DBParm();
        BPTTBLH_RD.TableName = "BPTTBLH";
        IBS.WRITE(SCCGWA, BPRTBLH, BPTTBLH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "TBLH (" + IBS.CLS2CPY(SCCGWA, BPRTBLH.KEY) + ") DUPLICATE";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READ_BPTTBLH_UPD() throws IOException,SQLException,Exception {
        BPTTBLH_RD = new DBParm();
        BPTTBLH_RD.TableName = "BPTTBLH";
        BPTTBLH_RD.upd = true;
        IBS.READ(SCCGWA, BPRTBLH, BPTTBLH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "TBLH (" + IBS.CLS2CPY(SCCGWA, BPRTBLH.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_REWRITE_BPTTBLH() throws IOException,SQLException,Exception {
        BPTTBLH_RD = new DBParm();
        BPTTBLH_RD.TableName = "BPTTBLH";
        IBS.REWRITE(SCCGWA, BPRTBLH, BPTTBLH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "TBLH (" + IBS.CLS2CPY(SCCGWA, BPRTBLH.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_DELETE_BPTTBLH() throws IOException,SQLException,Exception {
        BPTTBLH_RD = new DBParm();
        BPTTBLH_RD.TableName = "BPTTBLH";
        IBS.DELETE(SCCGWA, BPRTBLH, BPTTBLH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "TBLH (" + IBS.CLS2CPY(SCCGWA, BPRTBLH.KEY) + ") NOT FOUND";
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
