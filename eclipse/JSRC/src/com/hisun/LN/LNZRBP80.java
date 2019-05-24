package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRBP80 {
    DBParm LNTBSP80_RD;
    brParm LNTBSP80_BR = new brParm();
    boolean pgmRtn = false;
    String WS_BSPNAME = " ";
    char WS_TBL_FARM_FLAG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNRBSP80 LNRBSP80 = new LNRBSP80();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCRWBSP SCCRWBSP;
    LNRBSP80 LNRBSP80A;
    public void MP(SCCGWA SCCGWA, SCCRWBSP SCCRWBSP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SCCRWBSP = SCCRWBSP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRBP80 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LNRBSP80A = (PSRBSP01) SCCRWBSP.INFO.PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, LNRBSP80);
        IBS.CLONE(SCCGWA, LNRBSP80A, LNRBSP80);
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
        } else if (SCCRWBSP.INFO.FUNC == 'S') {
            B060_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'N') {
            B070_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (SCCRWBSP.INFO.FUNC == 'E') {
            B080_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + SCCRWBSP.INFO.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        IBS.CLONE(SCCGWA, LNRBSP80, LNRBSP80A);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BSP();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BSP_UPD();
        if (pgmRtn) return;
        if (SCCRWBSP.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BSP_NOTFND, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BSP();
        if (pgmRtn) return;
        if (SCCRWBSP.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BSP_NOTFND, SCCRWBSP.RC);
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
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BSP_NOTFND, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B060_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BSP();
        if (pgmRtn) return;
        if (SCCRWBSP.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BSP_NOTFND, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B070_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BSP();
        if (pgmRtn) return;
        if (SCCRWBSP.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BSP_NOTFND, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B080_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BSP();
        if (pgmRtn) return;
        if (SCCRWBSP.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BSP_NOTFND, SCCRWBSP.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BSP() throws IOException,SQLException,Exception {
        LNTBSP80_RD = new DBParm();
        LNTBSP80_RD.TableName = "LNTBSP80";
        IBS.READ(SCCGWA, LNRBSP80, LNTBSP80_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SCCRWBSP.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_WRITE_BSP() throws IOException,SQLException,Exception {
        LNRBSP80.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRBSP80.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRBSP80.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRBSP80.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTBSP80_RD = new DBParm();
        LNTBSP80_RD.TableName = "LNTBSP80";
        LNTBSP80_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRBSP80, LNTBSP80_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            SCCRWBSP.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BSP_UPD() throws IOException,SQLException,Exception {
        LNTBSP80_RD = new DBParm();
        LNTBSP80_RD.TableName = "LNTBSP80";
        LNTBSP80_RD.upd = true;
        IBS.READ(SCCGWA, LNRBSP80, LNTBSP80_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SCCRWBSP.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BSP() throws IOException,SQLException,Exception {
        LNRBSP80.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRBSP80.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTBSP80_RD = new DBParm();
        LNTBSP80_RD.TableName = "LNTBSP80";
        IBS.REWRITE(SCCGWA, LNRBSP80, LNTBSP80_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else {
        }
    }
    public void T000_DELETE_BSP() throws IOException,SQLException,Exception {
        LNTBSP80_RD = new DBParm();
        LNTBSP80_RD.TableName = "LNTBSP80";
        IBS.DELETE(SCCGWA, LNRBSP80, LNTBSP80_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SCCRWBSP.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BSP() throws IOException,SQLException,Exception {
        LNTBSP80_BR.rp = new DBParm();
        LNTBSP80_BR.rp.TableName = "LNTBSP80";
        IBS.STARTBR(SCCGWA, LNRBSP80, LNTBSP80_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SCCRWBSP.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BSP() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRBSP80, this, LNTBSP80_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SCCRWBSP.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BSP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTBSP80_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            SCCRWBSP.RETURN_INFO = 'N';
        } else {
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (SCCRWBSP.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "SCCRWBSP=");
            CEP.TRC(SCCGWA, SCCRWBSP);
            CEP.TRC(SCCGWA, "LNRBSP80=");
            CEP.TRC(SCCGWA, LNRBSP80);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
