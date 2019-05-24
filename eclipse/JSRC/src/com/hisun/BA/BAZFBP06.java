package com.hisun.BA;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BAZFBP06 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm BATBSP06_RD;
    boolean pgmRtn = false;
    String WS_BSPNAME = " ";
    char WS_TBL_FARM_FLAG = ' ';
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BARBSP06 BARBSP06 = new BARBSP06();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCRWBSP SCCRWBSP;
    String LK_REC = " ";
    public void MP(SCCGWA SCCGWA, SCCRWBSP SCCRWBSP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SCCRWBSP = SCCRWBSP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BAZFBP06 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LK_REC = IBS.CLS2CPY(SCCGWA, SCCRWBSP.INFO.PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BARBSP06);
        if (LK_REC == null) LK_REC = "";
        JIBS_tmp_int = LK_REC.length();
        for (int i=0;i<10240-JIBS_tmp_int;i++) LK_REC += " ";
        JIBS_tmp_str[0] = LK_REC.substring(0, SCCRWBSP.INFO.LEN);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BARBSP06);
        SCCRWBSP.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        SCCRWBSP.RC.RC_CODE = 0;
        SCCRWBSP.RETURN_INFO = 'F';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCRWBSP.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'Q') {
            B030_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + SCCRWBSP.INFO.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BARBSP06);
        LK_REC = JIBS_tmp_str[0];
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BSP();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BSP_UPD();
        if (pgmRtn) return;
        if (SCCRWBSP.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_BSP_NOTFND, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BSP();
        if (pgmRtn) return;
        if (SCCRWBSP.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_BSP_NOTFND, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BSP();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BSP();
        if (pgmRtn) return;
        if (SCCRWBSP.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_BSP_NOTFND, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BSP() throws IOException,SQLException,Exception {
        BATBSP06_RD = new DBParm();
        BATBSP06_RD.TableName = "BATBSP06";
        IBS.READ(SCCGWA, BARBSP06, BATBSP06_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SCCRWBSP.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BSP() throws IOException,SQLException,Exception {
        BATBSP06_RD = new DBParm();
        BATBSP06_RD.TableName = "BATBSP06";
        BATBSP06_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BARBSP06, BATBSP06_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            SCCRWBSP.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BSP_UPD() throws IOException,SQLException,Exception {
        BATBSP06_RD = new DBParm();
        BATBSP06_RD.TableName = "BATBSP06";
        BATBSP06_RD.upd = true;
        IBS.READ(SCCGWA, BARBSP06, BATBSP06_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SCCRWBSP.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BSP() throws IOException,SQLException,Exception {
        BATBSP06_RD = new DBParm();
        BATBSP06_RD.TableName = "BATBSP06";
        IBS.REWRITE(SCCGWA, BARBSP06, BATBSP06_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else {
        }
    }
    public void T000_DELETE_BSP() throws IOException,SQLException,Exception {
        BATBSP06_RD = new DBParm();
        BATBSP06_RD.TableName = "BATBSP06";
        IBS.DELETE(SCCGWA, BARBSP06, BATBSP06_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SCCRWBSP.RETURN_INFO = 'N';
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
