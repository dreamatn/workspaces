package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRTTXH {
    int JIBS_tmp_int;
    DBParm BPTTTXH_RD;
    String K_PGM_NAME = "BPZRTTXH";
    int WS_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTTXH BPRTTXH = new BPRTTXH();
    SCCGWA SCCGWA;
    BPCRTTXH BPCRTTXH;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    String LK_DATA = " ";
    public void MP(SCCGWA SCCGWA, BPCRTTXH BPCRTTXH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRTTXH = BPCRTTXH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZRTTXH return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LK_DATA = IBS.CLS2CPY(SCCGWA, BPCRTTXH.DAT_POINTER);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRTTXH);
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        IBS.CPY2CLS(SCCGWA, LK_DATA.substring(0, BPCRTTXH.DAT_LEN), BPRTTXH);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRTTXH.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
        } else if (BPCRTTXH.FUNC == 'U') {
            B020_UPDATE_RECORD_PROC();
        } else if (BPCRTTXH.FUNC == 'D') {
            B030_DELETE_RECORD_PROC();
        } else if (BPCRTTXH.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
        } else if (BPCRTTXH.FUNC == 'R') {
            B050_READ_UPD_RECORD_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCRTTXH.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTTTXH();
    }
    public void B020_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTTTXH();
    }
    public void B030_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTTTXH();
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTTXH();
    }
    public void B050_READ_UPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTTTXH_UPD();
    }
    public void T000_READ_BPTTTXH() throws IOException,SQLException,Exception {
        BPTTTXH_RD = new DBParm();
        BPTTTXH_RD.TableName = "BPTTTXH";
        IBS.READ(SCCGWA, BPRTTXH, BPTTTXH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "TTXH (" + IBS.CLS2CPY(SCCGWA, BPRTTXH.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_WRITE_BPTTTXH() throws IOException,SQLException,Exception {
        BPTTTXH_RD = new DBParm();
        BPTTTXH_RD.TableName = "BPTTTXH";
        IBS.WRITE(SCCGWA, BPRTTXH, BPTTTXH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "TTXH (" + IBS.CLS2CPY(SCCGWA, BPRTTXH.KEY) + ") DUPLICATE";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READ_BPTTTXH_UPD() throws IOException,SQLException,Exception {
        BPTTTXH_RD = new DBParm();
        BPTTTXH_RD.TableName = "BPTTTXH";
        BPTTTXH_RD.upd = true;
        IBS.READ(SCCGWA, BPRTTXH, BPTTTXH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "TTXH (" + IBS.CLS2CPY(SCCGWA, BPRTTXH.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_REWRITE_BPTTTXH() throws IOException,SQLException,Exception {
        BPTTTXH_RD = new DBParm();
        BPTTTXH_RD.TableName = "BPTTTXH";
        IBS.REWRITE(SCCGWA, BPRTTXH, BPTTTXH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "TTXH (" + IBS.CLS2CPY(SCCGWA, BPRTTXH.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_DELETE_BPTTTXH() throws IOException,SQLException,Exception {
        BPTTTXH_RD = new DBParm();
        BPTTTXH_RD.TableName = "BPTTTXH";
        IBS.DELETE(SCCGWA, BPRTTXH, BPTTTXH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "TTXH (" + IBS.CLS2CPY(SCCGWA, BPRTTXH.KEY) + ") NOT FOUND";
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
