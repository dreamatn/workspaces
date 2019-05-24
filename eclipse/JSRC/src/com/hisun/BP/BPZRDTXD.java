package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRDTXD {
    int JIBS_tmp_int;
    DBParm BPTDTXD_RD;
    String K_PGM_NAME = "BPZRDTXD";
    int WS_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRDTXD BPRDTXD = new BPRDTXD();
    SCCGWA SCCGWA;
    BPCRDTXD BPCRDTXD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    String LK_DATA = " ";
    public void MP(SCCGWA SCCGWA, BPCRDTXD BPCRDTXD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRDTXD = BPCRDTXD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZRDTXD return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LK_DATA = IBS.CLS2CPY(SCCGWA, BPCRDTXD.DAT_POINTER);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRDTXD);
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        IBS.CPY2CLS(SCCGWA, LK_DATA.substring(0, BPCRDTXD.DAT_LEN), BPRDTXD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRDTXD.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
        } else if (BPCRDTXD.FUNC == 'U') {
            B020_UPDATE_RECORD_PROC();
        } else if (BPCRDTXD.FUNC == 'D') {
            B030_DELETE_RECORD_PROC();
        } else if (BPCRDTXD.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
        } else if (BPCRDTXD.FUNC == 'R') {
            B050_READ_UPD_RECORD_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCRDTXD.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTDTXD();
    }
    public void B020_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTDTXD();
    }
    public void B030_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTDTXD();
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTDTXD();
    }
    public void B050_READ_UPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTDTXD_UPD();
    }
    public void T000_READ_BPTDTXD() throws IOException,SQLException,Exception {
        BPTDTXD_RD = new DBParm();
        BPTDTXD_RD.TableName = "BPTDTXD";
        IBS.READ(SCCGWA, BPRDTXD, BPTDTXD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "DTXD (" + IBS.CLS2CPY(SCCGWA, BPRDTXD.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_WRITE_BPTDTXD() throws IOException,SQLException,Exception {
        BPTDTXD_RD = new DBParm();
        BPTDTXD_RD.TableName = "BPTDTXD";
        IBS.WRITE(SCCGWA, BPRDTXD, BPTDTXD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "DTXD (" + IBS.CLS2CPY(SCCGWA, BPRDTXD.KEY) + ") DUPLICATE";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READ_BPTDTXD_UPD() throws IOException,SQLException,Exception {
        BPTDTXD_RD = new DBParm();
        BPTDTXD_RD.TableName = "BPTDTXD";
        BPTDTXD_RD.upd = true;
        IBS.READ(SCCGWA, BPRDTXD, BPTDTXD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "DTXD (" + IBS.CLS2CPY(SCCGWA, BPRDTXD.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_REWRITE_BPTDTXD() throws IOException,SQLException,Exception {
        BPTDTXD_RD = new DBParm();
        BPTDTXD_RD.TableName = "BPTDTXD";
        IBS.REWRITE(SCCGWA, BPRDTXD, BPTDTXD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "DTXD (" + IBS.CLS2CPY(SCCGWA, BPRDTXD.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_DELETE_BPTDTXD() throws IOException,SQLException,Exception {
        BPTDTXD_RD = new DBParm();
        BPTDTXD_RD.TableName = "BPTDTXD";
        IBS.DELETE(SCCGWA, BPRDTXD, BPTDTXD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "DTXD (" + IBS.CLS2CPY(SCCGWA, BPRDTXD.KEY) + ") NOT FOUND";
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
