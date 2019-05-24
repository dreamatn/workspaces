package com.hisun.FS;

import com.hisun.DD.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class FSZVMST {
    DBParm FSTVMST_RD;
    boolean pgmRtn = false;
    String WS_REAL_ACC = " ";
    char WS_TABLE_FLG = ' ';
    char WS_REAL_ACC_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    FSRVMST FSRVMST = new FSRVMST();
    SCCGWA SCCGWA;
    FSCIVMST FSCIVMST;
    public void MP(SCCGWA SCCGWA, FSCIVMST FSCIVMST) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.FSCIVMST = FSCIVMST;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "FSZVMST return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        FSCIVMST.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        FSCIVMST.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_INQ_AC_INF_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, FSCIVMST.DATA.ACC_NO);
        if (FSCIVMST.DATA.ACC_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT, FSCIVMST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_INQ_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSRVMST);
        FSRVMST.KEY.ACC_NO = FSCIVMST.DATA.ACC_NO;
        CEP.TRC(SCCGWA, "START LOOP FETCH FSTVMST");
        T000_READ_FSTVMST();
        if (pgmRtn) return;
        while (WS_TABLE_FLG != 'N' 
            && WS_REAL_ACC_FLG != 'Y') {
            FSCIVMST.DATA.ACC_NO = FSRVMST.UPACC_NO;
            if (FSRVMST.UPACC_VIR_FLAG == '0') {
                WS_REAL_ACC = FSRVMST.UPACC_NO;
                CEP.TRC(SCCGWA, WS_REAL_ACC);
                WS_REAL_ACC_FLG = 'Y';
            }
            FSRVMST.KEY.ACC_NO = FSCIVMST.DATA.ACC_NO;
            T000_READ_FSTVMST();
            if (pgmRtn) return;
        }
        R000_TRANS_DATA_OUT();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_OUT() throws IOException,SQLException,Exception {
        FSCIVMST.O_DATA.O_UPACC_NO = WS_REAL_ACC;
        CEP.TRC(SCCGWA, WS_REAL_ACC);
        CEP.TRC(SCCGWA, FSCIVMST.O_DATA.O_UPACC_NO);
    }
    public void T000_READ_FSTVMST() throws IOException,SQLException,Exception {
        FSTVMST_RD = new DBParm();
        FSTVMST_RD.TableName = "FSTVMST";
        IBS.READ(SCCGWA, FSRVMST, FSTVMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "FSTVMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (FSCIVMST.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "FSCIVMST=");
            CEP.TRC(SCCGWA, FSCIVMST);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
