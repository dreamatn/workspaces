package com.hisun.DD;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZUGCTL {
    DBParm DDTGPMST_RD;
    boolean pgmRtn = false;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DDRGPMST DDRGPMST = new DDRGPMST();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDCUGCTL DDCUGCTL;
    public void MP(SCCGWA SCCGWA, DDCUGCTL DDCUGCTL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUGCTL = DDCUGCTL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZUGCTL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_INQ_CNTL_BAL();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCUGCTL.INPUT.AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_GRP_AC_MST_IN, DDCUGCTL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCUGCTL.INPUT.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_M_INPUT, DDCUGCTL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_INQ_CNTL_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRGPMST);
        DDRGPMST.KEY.AC_NO = DDCUGCTL.INPUT.AC;
        DDRGPMST.KEY.CCY = DDCUGCTL.INPUT.CCY;
        T000_READ_DDTGPMST();
        if (pgmRtn) return;
        if (DDRGPMST.STS == 'D') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_GROUP_REC_EXPIRE, DDCUGCTL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        DDCUGCTL.OUTPUT.CONTROL_BAL = DDRGPMST.CTRL_BAL;
    }
    public void T000_READ_DDTGPMST() throws IOException,SQLException,Exception {
        DDTGPMST_RD = new DBParm();
        DDTGPMST_RD.TableName = "DDTGPMST";
        IBS.READ(SCCGWA, DDRGPMST, DDTGPMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_GRP_AC_NOT_FND, DDCUGCTL.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE DDTGPMST ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTGPMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DDCUGCTL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DDCUGCTL");
            CEP.TRC(SCCGWA, DDCUGCTL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
