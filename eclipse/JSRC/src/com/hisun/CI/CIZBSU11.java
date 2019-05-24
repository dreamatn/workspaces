package com.hisun.CI;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZBSU11 {
    DBParm CITBSP11_RD;
    boolean pgmRtn = false;
    String WS_TEMP_RECORD = " ";
    char WS_TBL_FARM_FLAG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    CIRBSP11 CIRBSP11 = new CIRBSP11();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCRWBSP SCCRWBSP;
    CIRBSP11 CIRLSP11;
    public void MP(SCCGWA SCCGWA, SCCRWBSP SCCRWBSP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SCCRWBSP = SCCRWBSP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZBSU11 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CIRLSP11 = (PSRBSP01) SCCRWBSP.INFO.PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CIRBSP11);
        IBS.CLONE(SCCGWA, CIRLSP11, CIRBSP11);
        SCCRWBSP.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        SCCRWBSP.RC.RC_CODE = 0;
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
        IBS.CLONE(SCCGWA, CIRBSP11, CIRLSP11);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIRBSP11);
        T000_WRITE_BSP();
        if (pgmRtn) return;
        if (SCCRWBSP.RETURN_INFO == 'D') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BSP_REC_EXIST, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BSP_UPD();
        if (pgmRtn) return;
        if (SCCRWBSP.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BSP_REC_NOTFND, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BSP();
        if (pgmRtn) return;
        if (SCCRWBSP.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BSP_REC_NOTFND, SCCRWBSP.RC);
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
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BSP_REC_NOTFND, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BSP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIRBSP11.KEY.AP_TYPE);
        CITBSP11_RD = new DBParm();
        CITBSP11_RD.TableName = "CITBSP11";
        IBS.READ(SCCGWA, CIRBSP11, CITBSP11_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
            CEP.TRC(SCCGWA, "AAA");
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SCCRWBSP.RETURN_INFO = 'N';
            CEP.TRC(SCCGWA, "BBB");
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_SYS_ERROR, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_BSP() throws IOException,SQLException,Exception {
        CITBSP11_RD = new DBParm();
        CITBSP11_RD.TableName = "CITBSP11";
        CITBSP11_RD.errhdl = true;
        IBS.WRITE(SCCGWA, CIRBSP11, CITBSP11_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            SCCRWBSP.RETURN_INFO = 'D';
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_SYS_ERROR, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BSP_UPD() throws IOException,SQLException,Exception {
        CITBSP11_RD = new DBParm();
        CITBSP11_RD.TableName = "CITBSP11";
        CITBSP11_RD.upd = true;
        CITBSP11_RD.col = "STATUS, RT_CODE, OUT_JRN, OUT_VCH_NO, OUT_DATA, TS";
        IBS.READ(SCCGWA, CIRBSP11, CITBSP11_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SCCRWBSP.RETURN_INFO = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_SYS_ERROR, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BSP() throws IOException,SQLException,Exception {
        CITBSP11_RD = new DBParm();
        CITBSP11_RD.TableName = "CITBSP11";
        IBS.REWRITE(SCCGWA, CIRBSP11, CITBSP11_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_SYS_ERROR, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_DELETE_BSP() throws IOException,SQLException,Exception {
        CITBSP11_RD = new DBParm();
        CITBSP11_RD.TableName = "CITBSP11";
        IBS.DELETE(SCCGWA, CIRBSP11, CITBSP11_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SCCRWBSP.RETURN_INFO = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_SYS_ERROR, SCCRWBSP.RC);
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
