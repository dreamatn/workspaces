package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRECGL {
    int JIBS_tmp_int;
    DBParm BPTECGL_RD;
    String K_PGM_NAME = "BPZRECGL";
    int WS_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRECGL BPRECGL = new BPRECGL();
    SCCGWA SCCGWA;
    BPCRECGL BPCRECGL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    String LK_DATA = " ";
    public void MP(SCCGWA SCCGWA, BPCRECGL BPCRECGL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRECGL = BPCRECGL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZRECGL return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LK_DATA = IBS.CLS2CPY(SCCGWA, BPCRECGL.DAT_POINTER);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRECGL);
        if (LK_DATA == null) LK_DATA = "";
        JIBS_tmp_int = LK_DATA.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) LK_DATA += " ";
        IBS.CPY2CLS(SCCGWA, LK_DATA.substring(0, BPCRECGL.DAT_LEN), BPRECGL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRECGL.FUNC == 'A') {
            B010_CREATE_RECORD_PROC();
        } else if (BPCRECGL.FUNC == 'U') {
            B020_UPDATE_RECORD_PROC();
        } else if (BPCRECGL.FUNC == 'D') {
            B030_DELETE_RECORD_PROC();
        } else if (BPCRECGL.FUNC == 'Q') {
            B040_QUERY_RECORD_PROC();
        } else if (BPCRECGL.FUNC == 'R') {
            B050_READ_UPD_RECORD_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCRECGL.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTECGL();
    }
    public void B020_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTECGL();
    }
    public void B030_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTECGL();
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTECGL();
    }
    public void B050_READ_UPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTECGL_UPD();
    }
    public void T000_READ_BPTECGL() throws IOException,SQLException,Exception {
        BPTECGL_RD = new DBParm();
        BPTECGL_RD.TableName = "BPTECGL";
        IBS.READ(SCCGWA, BPRECGL, BPTECGL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "ECGL (" + IBS.CLS2CPY(SCCGWA, BPRECGL.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_WRITE_BPTECGL() throws IOException,SQLException,Exception {
        BPTECGL_RD = new DBParm();
        BPTECGL_RD.TableName = "BPTECGL";
        IBS.WRITE(SCCGWA, BPRECGL, BPTECGL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "ECGL (" + IBS.CLS2CPY(SCCGWA, BPRECGL.KEY) + ") DUPLICATE";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_READ_BPTECGL_UPD() throws IOException,SQLException,Exception {
        BPTECGL_RD = new DBParm();
        BPTECGL_RD.TableName = "BPTECGL";
        BPTECGL_RD.upd = true;
        IBS.READ(SCCGWA, BPRECGL, BPTECGL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "ECGL (" + IBS.CLS2CPY(SCCGWA, BPRECGL.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_REWRITE_BPTECGL() throws IOException,SQLException,Exception {
        BPTECGL_RD = new DBParm();
        BPTECGL_RD.TableName = "BPTECGL";
        IBS.REWRITE(SCCGWA, BPRECGL, BPTECGL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "ECGL (" + IBS.CLS2CPY(SCCGWA, BPRECGL.KEY) + ") NOT FOUND";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void T000_DELETE_BPTECGL() throws IOException,SQLException,Exception {
        BPTECGL_RD = new DBParm();
        BPTECGL_RD.TableName = "BPTECGL";
        IBS.DELETE(SCCGWA, BPRECGL, BPTECGL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "ECGL (" + IBS.CLS2CPY(SCCGWA, BPRECGL.KEY) + ") NOT FOUND";
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
