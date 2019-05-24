package com.hisun.DD;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT8850 {
    String I_VS_INT = "DD-I-VS-INT";
    String OUTPUT_FMT = "DD851";
    char FUNC_BROWSE = '1';
    char FUNC_QUERY = '2';
    short MAX_ROWS = 25;
    int MAX_DT = 99991231;
    int MIN_DT = 00010101;
    DDOT8850_WS_VARIABLES WS_VARIABLES = new DDOT8850_WS_VARIABLES();
    DDOT8850_WS_FMT_VARIABLES WS_FMT_VARIABLES = new DDOT8850_WS_FMT_VARIABLES();
    DDCMSG_ERROR_MSG ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DDCUININ DDCUININ = new DDCUININ();
    SCCGWA SCCGWA;
    DDB5860_AWA_5860 AWA_5860;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT8850 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        AWA_5860 = new DDB5860_AWA_5860();
        IBS.init(SCCGWA, AWA_5860);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWA_AREA_PTR, AWA_5860);
        IBS.init(SCCGWA, WS_VARIABLES);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_INPUT_CHK_PROC();
        B200_ISS_NOTE_PROC();
        B300_FMT_OUTPUT();
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (AWA_5860.VS_AC.trim().length() == 0) {
            WS_VARIABLES.MSG_ID = ERROR_MSG.DD_VAC_MST_IPT;
            S000_ERR_MSG_PROC();
        }
        if (AWA_5860.CCY.trim().length() == 0) {
            WS_VARIABLES.MSG_ID = ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (AWA_5860.CCY_TYP == ' ') {
            WS_VARIABLES.MSG_ID = ERROR_MSG.DD_CCY_TYP_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (AWA_5860.ST_DT == 0) {
            WS_VARIABLES.MSG_ID = ERROR_MSG.DD_ST_DT_MST_IPT;
            S000_ERR_MSG_PROC();
        }
        if (AWA_5860.ED_DT == 0) {
            WS_VARIABLES.MSG_ID = ERROR_MSG.DD_ED_DT_MST_IPT;
            S000_ERR_MSG_PROC();
        }
        if (AWA_5860.ST_DT > AWA_5860.ED_DT) {
            WS_VARIABLES.MSG_ID = ERROR_MSG.DD_STED_ERR;
            S000_ERR_MSG_PROC();
        }
        if (AWA_5860.INT_RAT == 0) {
            WS_VARIABLES.MSG_ID = ERROR_MSG.DD_INT_RAT_INVLD;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_ISS_NOTE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUININ);
        DDCUININ.INPUT.VS_AC = AWA_5860.VS_AC;
        DDCUININ.INPUT.CCY = AWA_5860.CCY;
        DDCUININ.INPUT.CCY_TYP = AWA_5860.CCY_TYP;
        DDCUININ.INPUT.ST_DT = AWA_5860.ST_DT;
        DDCUININ.INPUT.ED_DT = AWA_5860.ED_DT;
        DDCUININ.INPUT.INT_RAT = AWA_5860.INT_RAT;
        CEP.TRC(SCCGWA, DDCUININ);
        S000_CALL_DDZUININ();
    }
    public void B300_FMT_OUTPUT() throws IOException,SQLException,Exception {
        WS_FMT_VARIABLES.FMT_INT = DDCUININ.OUTPUT.INT;
        CEP.TRC(SCCGWA, DDCUININ.OUTPUT.INT);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_FMT_VARIABLES;
        SCCFMT.DATA_LEN = 17;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_DDZUININ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, I_VS_INT, DDCUININ);
        if (DDCUININ.OUTPUT.MSG_ID.RES_RC == 0) {
        } else {
            WS_VARIABLES.MSG_ID = IBS.CLS2CPY(SCCGWA, DDCUININ.OUTPUT.MSG_ID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.MSG_ID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
