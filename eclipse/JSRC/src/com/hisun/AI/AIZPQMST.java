package com.hisun.AI;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZPQMST {
    DBParm AITMST_RD;
    boolean pgmRtn = false;
    String CPN_I_NFIN_M_MST = "DD-I-NFIN-M-MST";
    short WS_AC_IDX = 0;
    short WS_SUB_AC_IDX = 0;
    String WS_DD_AC_NO = " ";
    String WS_TD_AC_NO = " ";
    String WS_PRD_CD = " ";
    AIZPQMST_WS_CONSTANT WS_CONSTANT = new AIZPQMST_WS_CONSTANT();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    AIRMST AIRMST = new AIRMST();
    SCCGWA SCCGWA;
    AICQMST AICQMST;
    public void MP(SCCGWA SCCGWA, AICQMST AICQMST) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICQMST = AICQMST;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZPQMST return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B200_GET_GL_BAL();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (AICQMST.CHANNEL_INFO.BR == 0) {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_BR_MUST_INPUT, AICQMST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (AICQMST.CHANNEL_INFO.ITM_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_COA_NO_MUST_INPUT, AICQMST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (AICQMST.CHANNEL_INFO.CUR.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_CUR_MUST_INPUT, AICQMST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (AICQMST.CHANNEL_INFO.GL_BOOK_FLG.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_BOOK_FLG_MUST_INPUT, AICQMST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_GET_GL_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRMST);
        AIRMST.KEY.GL_BOOK_FLG = AICQMST.CHANNEL_INFO.GL_BOOK_FLG;
        AIRMST.KEY.BR = AICQMST.CHANNEL_INFO.BR;
        AIRMST.KEY.ITM_NO = AICQMST.CHANNEL_INFO.ITM_NO;
        AIRMST.KEY.CCY = AICQMST.CHANNEL_INFO.CUR;
        T000_READ_AITMST();
        if (pgmRtn) return;
        if (WS_CONSTANT.WS_DB2_REC_STATUS == 'N') {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_READ_AITMST_NOTFND, AICQMST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_AITMST() throws IOException,SQLException,Exception {
        AITMST_RD = new DBParm();
        AITMST_RD.TableName = "AITMST";
        IBS.READ(SCCGWA, AIRMST, AITMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CONSTANT.WS_DB2_REC_STATUS = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CONSTANT.WS_DB2_REC_STATUS = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, AICMSG_ERROR_MSG.AI_SYS_ERR, AICQMST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (AICQMST.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "AICQMST = ");
            CEP.TRC(SCCGWA, AICQMST);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
