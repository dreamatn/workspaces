package com.hisun.PS;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class PSZCSSTS {
    DBParm PSTBSP02_RD;
    boolean pgmRtn = false;
    String WS_TEMP_RECORD = " ";
    char WS_TBL_FARM_FLAG = ' ';
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    PSCEXDEL PSCEXDEL = new PSCEXDEL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    PSRBSP02 PSRBSP02 = new PSRBSP02();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCRWBSP SCCRWBSP;
    PSRBSP02 PSRBSP02A;
    public void MP(SCCGWA SCCGWA, SCCRWBSP SCCRWBSP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SCCRWBSP = SCCRWBSP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PSZCSSTS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        PSRBSP02A = (PSRBSP01) SCCRWBSP.INFO.PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CEP.TRC(SCCGWA, "111");
        IBS.init(SCCGWA, PSRBSP02);
        CEP.TRC(SCCGWA, "222");
        IBS.CLONE(SCCGWA, PSRBSP02A, PSRBSP02);
        SCCRWBSP.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        SCCRWBSP.RC.RC_CODE = 0;
        CEP.TRC(SCCGWA, "333");
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
        IBS.CLONE(SCCGWA, PSRBSP02, PSRBSP02A);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, PSRBSP02);
        T000_WRITE_BSP();
        if (pgmRtn) return;
        if (SCCRWBSP.RETURN_INFO == 'D') {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_BSP_NOTFND, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BSP_UPD();
        if (pgmRtn) return;
        if (SCCRWBSP.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_BSP_NOTFND, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BSP();
        if (pgmRtn) return;
        if (SCCRWBSP.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_BSP_NOTFND, SCCRWBSP.RC);
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
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_BSP_NOTFND, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BSP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, PSRBSP02.KEY.AP_TYPE);
        CEP.TRC(SCCGWA, PSRBSP02.KEY.AP_BATNO);
        CEP.TRC(SCCGWA, PSRBSP02.KEY.BAT_NO);
        PSTBSP02_RD = new DBParm();
        PSTBSP02_RD.TableName = "PSTBSP02";
        IBS.READ(SCCGWA, PSRBSP02, PSTBSP02_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
            CEP.TRC(SCCGWA, "AAA");
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SCCRWBSP.RETURN_INFO = 'N';
            CEP.TRC(SCCGWA, "BBB");
        } else {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_SYS_ERROR, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_BSP() throws IOException,SQLException,Exception {
        PSTBSP02_RD = new DBParm();
        PSTBSP02_RD.TableName = "PSTBSP02";
        PSTBSP02_RD.errhdl = true;
        IBS.WRITE(SCCGWA, PSRBSP02, PSTBSP02_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            SCCRWBSP.RETURN_INFO = 'D';
        } else {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_SYS_ERROR, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BSP_UPD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, PSRBSP02.KEY.AP_TYPE);
        CEP.TRC(SCCGWA, PSRBSP02.KEY.AP_BATNO);
        CEP.TRC(SCCGWA, PSRBSP02.KEY.BAT_NO);
        PSTBSP02_RD = new DBParm();
        PSTBSP02_RD.TableName = "PSTBSP02";
        PSTBSP02_RD.upd = true;
        PSTBSP02_RD.col = "STATUS, RT_CODE, OUT_JRN, OUT_VCH_NO, OUT_DATA, TS";
        IBS.READ(SCCGWA, PSRBSP02, PSTBSP02_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SCCRWBSP.RETURN_INFO = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_SYS_ERROR, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BSP() throws IOException,SQLException,Exception {
        PSTBSP02_RD = new DBParm();
        PSTBSP02_RD.TableName = "PSTBSP02";
        IBS.REWRITE(SCCGWA, PSRBSP02, PSTBSP02_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_SYS_ERROR, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_DELETE_BSP() throws IOException,SQLException,Exception {
        PSTBSP02_RD = new DBParm();
        PSTBSP02_RD.TableName = "PSTBSP02";
        IBS.DELETE(SCCGWA, PSRBSP02, PSTBSP02_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SCCRWBSP.RETURN_INFO = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, PSCMSG_ERROR_MSG.PS_SYS_ERROR, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
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
