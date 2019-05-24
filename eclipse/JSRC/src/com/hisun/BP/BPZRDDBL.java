package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRDDBL {
    int JIBS_tmp_int;
    DBParm BPTDDBL_RD;
    String K_PGM_NAME = "BPZRDDBL";
    int WS_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRDDBL BPRDDBL = new BPRDDBL();
    SCCGWA SCCGWA;
    BPCRDDBL BPCRDDBL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    String LK_DATA = " ";
    public void MP(SCCGWA SCCGWA, BPCRDDBL BPCRDDBL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRDDBL = BPCRDDBL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZRDDBL return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LK_DATA = IBS.CLS2CPY(SCCGWA, BPCRDDBL.DAT_POINTER);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRDDBL);
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        IBS.CPY2CLS(SCCGWA, LK_DATA.substring(0, BPCRDDBL.DAT_LEN), BPRDDBL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRDDBL.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
        } else if (BPCRDDBL.FUNC == 'U') {
            B020_UPDATE_RECORD_PROC();
        } else if (BPCRDDBL.FUNC == 'D') {
            B030_DELETE_RECORD_PROC();
        } else if (BPCRDDBL.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
        } else if (BPCRDDBL.FUNC == 'R') {
            B050_READ_UPD_RECORD_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCRDDBL.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTDDBL();
    }
    public void B020_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTDDBL();
    }
    public void B030_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTDDBL();
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTDDBL();
    }
    public void B050_READ_UPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTDDBL_UPD();
    }
    public void T000_READ_BPTDDBL() throws IOException,SQLException,Exception {
        BPTDDBL_RD = new DBParm();
        BPTDDBL_RD.TableName = "BPTDDBL";
        IBS.READ(SCCGWA, BPRDDBL, BPTDDBL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "DDBL (" + IBS.CLS2CPY(SCCGWA, BPRDDBL.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_WRITE_BPTDDBL() throws IOException,SQLException,Exception {
        BPTDDBL_RD = new DBParm();
        BPTDDBL_RD.TableName = "BPTDDBL";
        IBS.WRITE(SCCGWA, BPRDDBL, BPTDDBL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "DDBL (" + IBS.CLS2CPY(SCCGWA, BPRDDBL.KEY) + ") DUPLICATE";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READ_BPTDDBL_UPD() throws IOException,SQLException,Exception {
        BPTDDBL_RD = new DBParm();
        BPTDDBL_RD.TableName = "BPTDDBL";
        BPTDDBL_RD.upd = true;
        IBS.READ(SCCGWA, BPRDDBL, BPTDDBL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "DDBL (" + IBS.CLS2CPY(SCCGWA, BPRDDBL.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_REWRITE_BPTDDBL() throws IOException,SQLException,Exception {
        BPTDDBL_RD = new DBParm();
        BPTDDBL_RD.TableName = "BPTDDBL";
        IBS.REWRITE(SCCGWA, BPRDDBL, BPTDDBL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "DDBL (" + IBS.CLS2CPY(SCCGWA, BPRDDBL.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_DELETE_BPTDDBL() throws IOException,SQLException,Exception {
        BPTDDBL_RD = new DBParm();
        BPTDDBL_RD.TableName = "BPTDDBL";
        IBS.DELETE(SCCGWA, BPRDDBL, BPTDDBL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "DDBL (" + IBS.CLS2CPY(SCCGWA, BPRDDBL.KEY) + ") NOT FOUND";
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
