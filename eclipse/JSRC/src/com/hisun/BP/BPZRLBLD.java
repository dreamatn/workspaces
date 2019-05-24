package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRLBLD {
    int JIBS_tmp_int;
    DBParm BPTLBLD_RD;
    String K_PGM_NAME = "BPZRLBLD";
    int WS_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRLBLD BPRLBLD = new BPRLBLD();
    SCCGWA SCCGWA;
    BPCRLBLD BPCRLBLD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    String LK_DATA = " ";
    public void MP(SCCGWA SCCGWA, BPCRLBLD BPCRLBLD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRLBLD = BPCRLBLD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZRLBLD return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LK_DATA = IBS.CLS2CPY(SCCGWA, BPCRLBLD.DAT_POINTER);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRLBLD);
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        IBS.CPY2CLS(SCCGWA, LK_DATA.substring(0, BPCRLBLD.DAT_LEN), BPRLBLD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRLBLD.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
        } else if (BPCRLBLD.FUNC == 'U') {
            B020_UPDATE_RECORD_PROC();
        } else if (BPCRLBLD.FUNC == 'D') {
            B030_DELETE_RECORD_PROC();
        } else if (BPCRLBLD.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
        } else if (BPCRLBLD.FUNC == 'R') {
            B050_READ_UPD_RECORD_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCRLBLD.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTLBLD();
    }
    public void B020_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTLBLD();
    }
    public void B030_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTLBLD();
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTLBLD();
    }
    public void B050_READ_UPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTLBLD_UPD();
    }
    public void T000_READ_BPTLBLD() throws IOException,SQLException,Exception {
        BPTLBLD_RD = new DBParm();
        BPTLBLD_RD.TableName = "BPTLBLD";
        IBS.READ(SCCGWA, BPRLBLD, BPTLBLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LBLD (" + IBS.CLS2CPY(SCCGWA, BPRLBLD.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_WRITE_BPTLBLD() throws IOException,SQLException,Exception {
        BPTLBLD_RD = new DBParm();
        BPTLBLD_RD.TableName = "BPTLBLD";
        IBS.WRITE(SCCGWA, BPRLBLD, BPTLBLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LBLD (" + IBS.CLS2CPY(SCCGWA, BPRLBLD.KEY) + ") DUPLICATE";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READ_BPTLBLD_UPD() throws IOException,SQLException,Exception {
        BPTLBLD_RD = new DBParm();
        BPTLBLD_RD.TableName = "BPTLBLD";
        BPTLBLD_RD.upd = true;
        IBS.READ(SCCGWA, BPRLBLD, BPTLBLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LBLD (" + IBS.CLS2CPY(SCCGWA, BPRLBLD.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_REWRITE_BPTLBLD() throws IOException,SQLException,Exception {
        BPTLBLD_RD = new DBParm();
        BPTLBLD_RD.TableName = "BPTLBLD";
        IBS.REWRITE(SCCGWA, BPRLBLD, BPTLBLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LBLD (" + IBS.CLS2CPY(SCCGWA, BPRLBLD.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_DELETE_BPTLBLD() throws IOException,SQLException,Exception {
        BPTLBLD_RD = new DBParm();
        BPTLBLD_RD.TableName = "BPTLBLD";
        IBS.DELETE(SCCGWA, BPRLBLD, BPTLBLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LBLD (" + IBS.CLS2CPY(SCCGWA, BPRLBLD.KEY) + ") NOT FOUND";
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
