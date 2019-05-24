package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRDTXH {
    int JIBS_tmp_int;
    DBParm BPTDTXH_RD;
    String K_PGM_NAME = "BPZRDTXH";
    int WS_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRDTXH BPRDTXH = new BPRDTXH();
    SCCGWA SCCGWA;
    BPCRDTXH BPCRDTXH;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    String LK_DATA = " ";
    public void MP(SCCGWA SCCGWA, BPCRDTXH BPCRDTXH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRDTXH = BPCRDTXH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZRDTXH return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LK_DATA = IBS.CLS2CPY(SCCGWA, BPCRDTXH.DAT_POINTER);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRDTXH);
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        IBS.CPY2CLS(SCCGWA, LK_DATA.substring(0, BPCRDTXH.DAT_LEN), BPRDTXH);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRDTXH.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
        } else if (BPCRDTXH.FUNC == 'U') {
            B020_UPDATE_RECORD_PROC();
        } else if (BPCRDTXH.FUNC == 'D') {
            B030_DELETE_RECORD_PROC();
        } else if (BPCRDTXH.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
        } else if (BPCRDTXH.FUNC == 'R') {
            B050_READ_UPD_RECORD_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCRDTXH.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTDTXH();
    }
    public void B020_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTDTXH();
    }
    public void B030_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTDTXH();
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTDTXH();
    }
    public void B050_READ_UPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTDTXH_UPD();
    }
    public void T000_READ_BPTDTXH() throws IOException,SQLException,Exception {
        BPTDTXH_RD = new DBParm();
        BPTDTXH_RD.TableName = "BPTDTXH";
        IBS.READ(SCCGWA, BPRDTXH, BPTDTXH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "DTXH (" + IBS.CLS2CPY(SCCGWA, BPRDTXH.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_WRITE_BPTDTXH() throws IOException,SQLException,Exception {
        BPTDTXH_RD = new DBParm();
        BPTDTXH_RD.TableName = "BPTDTXH";
        IBS.WRITE(SCCGWA, BPRDTXH, BPTDTXH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "DTXH (" + IBS.CLS2CPY(SCCGWA, BPRDTXH.KEY) + ") DUPLICATE";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READ_BPTDTXH_UPD() throws IOException,SQLException,Exception {
        BPTDTXH_RD = new DBParm();
        BPTDTXH_RD.TableName = "BPTDTXH";
        BPTDTXH_RD.upd = true;
        IBS.READ(SCCGWA, BPRDTXH, BPTDTXH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "DTXH (" + IBS.CLS2CPY(SCCGWA, BPRDTXH.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_REWRITE_BPTDTXH() throws IOException,SQLException,Exception {
        BPTDTXH_RD = new DBParm();
        BPTDTXH_RD.TableName = "BPTDTXH";
        IBS.REWRITE(SCCGWA, BPRDTXH, BPTDTXH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "DTXH (" + IBS.CLS2CPY(SCCGWA, BPRDTXH.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_DELETE_BPTDTXH() throws IOException,SQLException,Exception {
        BPTDTXH_RD = new DBParm();
        BPTDTXH_RD.TableName = "BPTDTXH";
        IBS.DELETE(SCCGWA, BPRDTXH, BPTDTXH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "DTXH (" + IBS.CLS2CPY(SCCGWA, BPRDTXH.KEY) + ") NOT FOUND";
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
