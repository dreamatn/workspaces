package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZROCAC {
    DBParm BPTOCAC_RD;
    boolean pgmRtn = false;
    String K_TBL_OCAC = "BPTOCAC ";
    String WS_TEMP_RECORD = " ";
    char WS_TBL_OCAC_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    BPROCAC BPROCAC = new BPROCAC();
    SCCGWA SCCGWA;
    BPCROCAC BPCROCAC;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPROCAC BPROCACL;
    public void MP(SCCGWA SCCGWA, BPCROCAC BPCROCAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCROCAC = BPCROCAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZROCAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPROCACL = (BPROCAC) BPCROCAC.INFO.POINTER;
        IBS.init(SCCGWA, BPCROCAC.RC);
        IBS.init(SCCGWA, BPROCAC);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCROCAC.RC);
        IBS.CLONE(SCCGWA, BPROCACL, BPROCAC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPROCAC.KEY.AC);
        if (BPCROCAC.INFO.FUNC == 'C' 
            || BPCROCAC.INFO.FUNC == 'R') {
            if (BPROCAC.KEY.AC.trim().length() == 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
            }
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCROCAC.INFO.FUNC == 'C') {
            B010_CREATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCROCAC.INFO.FUNC == 'R') {
            B020_READUPD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCROCAC.INFO.FUNC == 'U') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCROCAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPROCAC, BPROCACL);
    }
    public void B010_CREATE_RECORD_PROC() throws IOException,SQLException,Exception {
        if (BPROCAC.BR == 0) {
            BPROCAC.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        T000_WRITE_BPTOCAC();
        if (pgmRtn) return;
    }
    public void B020_READUPD_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTOCAC_UPD();
        if (pgmRtn) return;
        if (WS_TBL_OCAC_FLAG == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_OCAC_NOTFND, BPCROCAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTOCAC();
        if (pgmRtn) return;
    }
    public void T000_WRITE_BPTOCAC() throws IOException,SQLException,Exception {
        BPTOCAC_RD = new DBParm();
        BPTOCAC_RD.TableName = "BPTOCAC";
        BPTOCAC_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPROCAC, BPTOCAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCROCAC.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCROCAC.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_OCAC;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTOCAC_UPD() throws IOException,SQLException,Exception {
        BPTOCAC_RD = new DBParm();
        BPTOCAC_RD.TableName = "BPTOCAC";
        BPTOCAC_RD.upd = true;
        IBS.READ(SCCGWA, BPROCAC, BPTOCAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_OCAC_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_OCAC_FLAG = 'D';
            BPCROCAC.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_REWRITE_BPTOCAC() throws IOException,SQLException,Exception {
        BPTOCAC_RD = new DBParm();
        BPTOCAC_RD.TableName = "BPTOCAC";
        IBS.REWRITE(SCCGWA, BPROCAC, BPTOCAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCROCAC.RETURN_INFO = 'F';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_OCAC;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCROCAC.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCROCAC = ");
            CEP.TRC(SCCGWA, BPCROCAC);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
