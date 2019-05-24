package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZQBCCY {
    String CPN_INQ_BRW_CCY = "BP-R-BRW-CCY";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    int WS_CCY_CNT = 0;
    char WS_REC_END_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRBCCY BPRBCCY = new BPRBCCY();
    BPCRBCCY BPCRBCCY = new BPCRBCCY();
    SCCGWA SCCGWA;
    BPCQBCCY BPCQBCCY;
    public void MP(SCCGWA SCCGWA, BPCQBCCY BPCQBCCY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCQBCCY = BPCQBCCY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZQBCCY return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQBCCY.RC);
        IBS.init(SCCGWA, BPCQBCCY.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_BROWSE_OUT_CCY_INFO();
        B300_OUTPUT_INFO();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCQBCCY.CHG_CCY_FLG);
    }
    public void B200_BROWSE_OUT_CCY_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBCCY);
        IBS.init(SCCGWA, BPCRBCCY);
        BPRBCCY.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        BPRBCCY.KEY.CD = "000";
        BPCRBCCY.INFO.OP_FUNC = 'S';
        BPCRBCCY.INFO.POINTER = BPRBCCY;
        BPCRBCCY.INFO.LEN = 391;
        S000_CALL_BPZRBCCY();
        if (BPCRBCCY.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        WS_REC_END_FLG = 'N';
        for (WS_CNT = 1; WS_CNT <= 300 
            && WS_REC_END_FLG != 'Y'; WS_CNT += 1) {
            IBS.init(SCCGWA, BPCRBCCY.RC);
            BPCRBCCY.RETURN_INFO = ' ';
            BPCRBCCY.INFO.OP_FUNC = 'R';
            BPCRBCCY.INFO.POINTER = BPRBCCY;
            BPCRBCCY.INFO.LEN = 391;
            S000_CALL_BPZRBCCY();
            if (BPCRBCCY.RETURN_INFO == 'N') {
                WS_REC_END_FLG = 'Y';
            } else {
                if (BPCQBCCY.CHG_CCY_FLG == ' ') {
                    WS_CCY_CNT += 1;
                    BPCQBCCY.INFO[WS_CCY_CNT-1].INFO_CCY = " ";
                    BPCQBCCY.INFO[WS_CCY_CNT-1].INFO_NO = 0;
                    BPCQBCCY.INFO[WS_CCY_CNT-1].INFO_CCY = BPRBCCY.CCY;
                    if (BPRBCCY.KEY.CD.trim().length() == 0) BPCQBCCY.INFO[WS_CCY_CNT-1].INFO_NO = 0;
                    else BPCQBCCY.INFO[WS_CCY_CNT-1].INFO_NO = Short.parseShort(BPRBCCY.KEY.CD);
                } else {
                    CEP.TRC(SCCGWA, BPRBCCY.CHG_CCY);
                    if (BPRBCCY.CHG_CCY == BPCQBCCY.CHG_CCY_FLG) {
                        WS_CCY_CNT += 1;
                        BPCQBCCY.INFO[WS_CCY_CNT-1].INFO_CCY = " ";
                        BPCQBCCY.INFO[WS_CCY_CNT-1].INFO_NO = 0;
                        BPCQBCCY.INFO[WS_CCY_CNT-1].INFO_CCY = BPRBCCY.CCY;
                        if (BPRBCCY.KEY.CD.trim().length() == 0) BPCQBCCY.INFO[WS_CCY_CNT-1].INFO_NO = 0;
                        else BPCQBCCY.INFO[WS_CCY_CNT-1].INFO_NO = Short.parseShort(BPRBCCY.KEY.CD);
                    }
                }
            }
        }
        BPCQBCCY.CCY_CNT = WS_CCY_CNT;
        IBS.init(SCCGWA, BPCRBCCY.RC);
        BPCRBCCY.RETURN_INFO = ' ';
        BPCRBCCY.INFO.OP_FUNC = 'E';
        BPCRBCCY.INFO.POINTER = BPRBCCY;
        BPCRBCCY.INFO.LEN = 391;
        S000_CALL_BPZRBCCY();
        if (BPCRBCCY.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void B300_OUTPUT_INFO() throws IOException,SQLException,Exception {
        for (WS_CNT = 1; WS_CNT <= BPCQBCCY.CCY_CNT; WS_CNT += 1) {
            CEP.TRC(SCCGWA, BPCQBCCY.INFO[WS_CNT-1].INFO_CCY);
            CEP.TRC(SCCGWA, BPCQBCCY.INFO[WS_CNT-1].INFO_NO);
        }
    }
    public void S000_CALL_BPZRBCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_BRW_CCY, BPCRBCCY);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCQBCCY.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCQBCCY = ");
            CEP.TRC(SCCGWA, BPCQBCCY);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
