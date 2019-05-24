package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRLTXH {
    int JIBS_tmp_int;
    DBParm BPTLTXH_RD;
    String K_PGM_NAME = "BPZRLTXH";
    int WS_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRLTXH BPRLTXH = new BPRLTXH();
    SCCGWA SCCGWA;
    BPCRLTXH BPCRLTXH;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    String LK_DATA = " ";
    public void MP(SCCGWA SCCGWA, BPCRLTXH BPCRLTXH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRLTXH = BPCRLTXH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZRLTXH return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LK_DATA = IBS.CLS2CPY(SCCGWA, BPCRLTXH.DAT_POINTER);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRLTXH);
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        IBS.CPY2CLS(SCCGWA, LK_DATA.substring(0, BPCRLTXH.DAT_LEN), BPRLTXH);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRLTXH.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
        } else if (BPCRLTXH.FUNC == 'U') {
            B020_UPDATE_RECORD_PROC();
        } else if (BPCRLTXH.FUNC == 'D') {
            B030_DELETE_RECORD_PROC();
        } else if (BPCRLTXH.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
        } else if (BPCRLTXH.FUNC == 'R') {
            B050_READ_UPD_RECORD_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCRLTXH.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTLTXH();
    }
    public void B020_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTLTXH();
    }
    public void B030_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTLTXH();
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTLTXH();
    }
    public void B050_READ_UPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTLTXH_UPD();
    }
    public void T000_READ_BPTLTXH() throws IOException,SQLException,Exception {
        BPTLTXH_RD = new DBParm();
        BPTLTXH_RD.TableName = "BPTLTXH";
        IBS.READ(SCCGWA, BPRLTXH, BPTLTXH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LTXH (" + IBS.CLS2CPY(SCCGWA, BPRLTXH.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_WRITE_BPTLTXH() throws IOException,SQLException,Exception {
        BPTLTXH_RD = new DBParm();
        BPTLTXH_RD.TableName = "BPTLTXH";
        IBS.WRITE(SCCGWA, BPRLTXH, BPTLTXH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LTXH (" + IBS.CLS2CPY(SCCGWA, BPRLTXH.KEY) + ") DUPLICATE";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READ_BPTLTXH_UPD() throws IOException,SQLException,Exception {
        BPTLTXH_RD = new DBParm();
        BPTLTXH_RD.TableName = "BPTLTXH";
        BPTLTXH_RD.upd = true;
        IBS.READ(SCCGWA, BPRLTXH, BPTLTXH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LTXH (" + IBS.CLS2CPY(SCCGWA, BPRLTXH.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_REWRITE_BPTLTXH() throws IOException,SQLException,Exception {
        BPTLTXH_RD = new DBParm();
        BPTLTXH_RD.TableName = "BPTLTXH";
        IBS.REWRITE(SCCGWA, BPRLTXH, BPTLTXH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LTXH (" + IBS.CLS2CPY(SCCGWA, BPRLTXH.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_DELETE_BPTLTXH() throws IOException,SQLException,Exception {
        BPTLTXH_RD = new DBParm();
        BPTLTXH_RD.TableName = "BPTLTXH";
        IBS.DELETE(SCCGWA, BPRLTXH, BPTLTXH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LTXH (" + IBS.CLS2CPY(SCCGWA, BPRLTXH.KEY) + ") NOT FOUND";
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
