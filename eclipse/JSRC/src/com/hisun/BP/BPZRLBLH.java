package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRLBLH {
    int JIBS_tmp_int;
    DBParm BPTLBLH_RD;
    String K_PGM_NAME = "BPZRLBLH";
    int WS_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRLBLH BPRLBLH = new BPRLBLH();
    SCCGWA SCCGWA;
    BPCRLBLH BPCRLBLH;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    String LK_DATA = " ";
    public void MP(SCCGWA SCCGWA, BPCRLBLH BPCRLBLH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRLBLH = BPCRLBLH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZRLBLH return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LK_DATA = IBS.CLS2CPY(SCCGWA, BPCRLBLH.DAT_POINTER);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRLBLH);
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        IBS.CPY2CLS(SCCGWA, LK_DATA.substring(0, BPCRLBLH.DAT_LEN), BPRLBLH);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRLBLH.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
        } else if (BPCRLBLH.FUNC == 'U') {
            B020_UPDATE_RECORD_PROC();
        } else if (BPCRLBLH.FUNC == 'D') {
            B030_DELETE_RECORD_PROC();
        } else if (BPCRLBLH.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
        } else if (BPCRLBLH.FUNC == 'R') {
            B050_READ_UPD_RECORD_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCRLBLH.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTLBLH();
    }
    public void B020_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTLBLH();
    }
    public void B030_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTLBLH();
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTLBLH();
    }
    public void B050_READ_UPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTLBLH_UPD();
    }
    public void T000_READ_BPTLBLH() throws IOException,SQLException,Exception {
        BPTLBLH_RD = new DBParm();
        BPTLBLH_RD.TableName = "BPTLBLH";
        IBS.READ(SCCGWA, BPRLBLH, BPTLBLH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LBLH (" + IBS.CLS2CPY(SCCGWA, BPRLBLH.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_WRITE_BPTLBLH() throws IOException,SQLException,Exception {
        BPTLBLH_RD = new DBParm();
        BPTLBLH_RD.TableName = "BPTLBLH";
        IBS.WRITE(SCCGWA, BPRLBLH, BPTLBLH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LBLH (" + IBS.CLS2CPY(SCCGWA, BPRLBLH.KEY) + ") DUPLICATE";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READ_BPTLBLH_UPD() throws IOException,SQLException,Exception {
        BPTLBLH_RD = new DBParm();
        BPTLBLH_RD.TableName = "BPTLBLH";
        BPTLBLH_RD.upd = true;
        IBS.READ(SCCGWA, BPRLBLH, BPTLBLH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LBLH (" + IBS.CLS2CPY(SCCGWA, BPRLBLH.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_REWRITE_BPTLBLH() throws IOException,SQLException,Exception {
        BPTLBLH_RD = new DBParm();
        BPTLBLH_RD.TableName = "BPTLBLH";
        IBS.REWRITE(SCCGWA, BPRLBLH, BPTLBLH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LBLH (" + IBS.CLS2CPY(SCCGWA, BPRLBLH.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_DELETE_BPTLBLH() throws IOException,SQLException,Exception {
        BPTLBLH_RD = new DBParm();
        BPTLBLH_RD.TableName = "BPTLBLH";
        IBS.DELETE(SCCGWA, BPRLBLH, BPTLBLH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LBLH (" + IBS.CLS2CPY(SCCGWA, BPRLBLH.KEY) + ") NOT FOUND";
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
