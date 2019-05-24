package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTTXD {
    int JIBS_tmp_int;
    DBParm BPTTTXD_RD;
    String K_PGM_NAME = "BPZRTTXD";
    int WS_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTTXD BPRTTXD = new BPRTTXD();
    SCCGWA SCCGWA;
    BPCRTTXD BPCRTTXD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    String LK_DATA = " ";
    public void MP(SCCGWA SCCGWA, BPCRTTXD BPCRTTXD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTTXD = BPCRTTXD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZRTTXD return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LK_DATA = IBS.CLS2CPY(SCCGWA, BPCRTTXD.DAT_POINTER);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRTTXD);
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        IBS.CPY2CLS(SCCGWA, LK_DATA.substring(0, BPCRTTXD.DAT_LEN), BPRTTXD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRTTXD.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
        } else if (BPCRTTXD.FUNC == 'U') {
            B020_UPDATE_RECORD_PROC();
        } else if (BPCRTTXD.FUNC == 'D') {
            B030_DELETE_RECORD_PROC();
        } else if (BPCRTTXD.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
        } else if (BPCRTTXD.FUNC == 'R') {
            B050_READ_UPD_RECORD_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCRTTXD.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTTTXD();
    }
    public void B020_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTTTXD();
    }
    public void B030_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTTTXD();
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTTXD();
    }
    public void B050_READ_UPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTTXD_UPD();
    }
    public void T000_READ_BPTTTXD() throws IOException,SQLException,Exception {
        BPTTTXD_RD = new DBParm();
        BPTTTXD_RD.TableName = "BPTTTXD";
        IBS.READ(SCCGWA, BPRTTXD, BPTTTXD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "TTXD (" + IBS.CLS2CPY(SCCGWA, BPRTTXD.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_WRITE_BPTTTXD() throws IOException,SQLException,Exception {
        BPTTTXD_RD = new DBParm();
        BPTTTXD_RD.TableName = "BPTTTXD";
        IBS.WRITE(SCCGWA, BPRTTXD, BPTTTXD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "TTXD (" + IBS.CLS2CPY(SCCGWA, BPRTTXD.KEY) + ") DUPLICATE";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READ_BPTTTXD_UPD() throws IOException,SQLException,Exception {
        BPTTTXD_RD = new DBParm();
        BPTTTXD_RD.TableName = "BPTTTXD";
        BPTTTXD_RD.upd = true;
        IBS.READ(SCCGWA, BPRTTXD, BPTTTXD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "TTXD (" + IBS.CLS2CPY(SCCGWA, BPRTTXD.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_REWRITE_BPTTTXD() throws IOException,SQLException,Exception {
        BPTTTXD_RD = new DBParm();
        BPTTTXD_RD.TableName = "BPTTTXD";
        IBS.REWRITE(SCCGWA, BPRTTXD, BPTTTXD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "TTXD (" + IBS.CLS2CPY(SCCGWA, BPRTTXD.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_DELETE_BPTTTXD() throws IOException,SQLException,Exception {
        BPTTTXD_RD = new DBParm();
        BPTTTXD_RD.TableName = "BPTTTXD";
        IBS.DELETE(SCCGWA, BPRTTXD, BPTTTXD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "TTXD (" + IBS.CLS2CPY(SCCGWA, BPRTTXD.KEY) + ") NOT FOUND";
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
