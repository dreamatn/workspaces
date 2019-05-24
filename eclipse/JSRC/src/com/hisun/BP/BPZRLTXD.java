package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRLTXD {
    int JIBS_tmp_int;
    DBParm BPTLTXD_RD;
    String K_PGM_NAME = "BPZRLTXD";
    int WS_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRLTXD BPRLTXD = new BPRLTXD();
    SCCGWA SCCGWA;
    BPCRLTXD BPCRLTXD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    String LK_DATA = " ";
    public void MP(SCCGWA SCCGWA, BPCRLTXD BPCRLTXD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRLTXD = BPCRLTXD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZRLTXD return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LK_DATA = IBS.CLS2CPY(SCCGWA, BPCRLTXD.DAT_POINTER);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRLTXD);
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        IBS.CPY2CLS(SCCGWA, LK_DATA.substring(0, BPCRLTXD.DAT_LEN), BPRLTXD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRLTXD.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
        } else if (BPCRLTXD.FUNC == 'U') {
            B020_UPDATE_RECORD_PROC();
        } else if (BPCRLTXD.FUNC == 'D') {
            B030_DELETE_RECORD_PROC();
        } else if (BPCRLTXD.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
        } else if (BPCRLTXD.FUNC == 'R') {
            B050_READ_UPD_RECORD_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCRLTXD.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTLTXD();
    }
    public void B020_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTLTXD();
    }
    public void B030_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTLTXD();
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTLTXD();
    }
    public void B050_READ_UPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTLTXD_UPD();
    }
    public void T000_READ_BPTLTXD() throws IOException,SQLException,Exception {
        BPTLTXD_RD = new DBParm();
        BPTLTXD_RD.TableName = "BPTLTXD";
        IBS.READ(SCCGWA, BPRLTXD, BPTLTXD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LTXD (" + IBS.CLS2CPY(SCCGWA, BPRLTXD.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_WRITE_BPTLTXD() throws IOException,SQLException,Exception {
        BPTLTXD_RD = new DBParm();
        BPTLTXD_RD.TableName = "BPTLTXD";
        IBS.WRITE(SCCGWA, BPRLTXD, BPTLTXD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LTXD (" + IBS.CLS2CPY(SCCGWA, BPRLTXD.KEY) + ") DUPLICATE";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READ_BPTLTXD_UPD() throws IOException,SQLException,Exception {
        BPTLTXD_RD = new DBParm();
        BPTLTXD_RD.TableName = "BPTLTXD";
        BPTLTXD_RD.upd = true;
        IBS.READ(SCCGWA, BPRLTXD, BPTLTXD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LTXD (" + IBS.CLS2CPY(SCCGWA, BPRLTXD.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_REWRITE_BPTLTXD() throws IOException,SQLException,Exception {
        BPTLTXD_RD = new DBParm();
        BPTLTXD_RD.TableName = "BPTLTXD";
        IBS.REWRITE(SCCGWA, BPRLTXD, BPTLTXD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LTXD (" + IBS.CLS2CPY(SCCGWA, BPRLTXD.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_DELETE_BPTLTXD() throws IOException,SQLException,Exception {
        BPTLTXD_RD = new DBParm();
        BPTLTXD_RD.TableName = "BPTLTXD";
        IBS.DELETE(SCCGWA, BPRLTXD, BPTLTXD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LTXD (" + IBS.CLS2CPY(SCCGWA, BPRLTXD.KEY) + ") NOT FOUND";
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
