package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFFXEV {
    int JIBS_tmp_int;
    String CPN_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String CPN_F_S_MAIN_FCOM = "BP-F-S-MAINTAIN-FCOM";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT = 0;
    String WS_PROD_CODE = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCFFXEV BPCFFXEV;
    public void MP(SCCGWA SCCGWA, BPCFFXEV BPCFFXEV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFFXEV = BPCFFXEV;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZFFXEV return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        C010_7_GEN_EVENT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCFFXEV.BUY_CCY.trim().length() == 0 
            || BPCFFXEV.SELL_CCY.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EX_CCY_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (BPCFFXEV.BUY_CCY.equalsIgnoreCase(BPCFFXEV.SELL_CCY)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EX_CCY_SAME;
            S000_ERR_MSG_PROC();
        }
        if (BPCFFXEV.BUY_BR == 0) {
            BPCFFXEV.BUY_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        if (BPCFFXEV.SELL_BR == 0) {
            BPCFFXEV.SELL_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        if (BPCFFXEV.BUY_AMT == 0 
            || BPCFFXEV.SELL_AMT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EX_NOT_INPUT_AMT;
            S000_ERR_MSG_PROC();
        }
        if (BPCFFXEV.REQPARTY == ' ' 
            || BPCFFXEV.REQPARTY == '0') {
            BPCFFXEV.REQPARTY = '2';
        }
        if (BPCFFXEV.REQPARTY != '2' 
            && BPCFFXEV.REQPARTY != '1') {
            CEP.TRC(SCCGWA, "DEVSOS03");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_CUST_BANK;
            S000_ERR_MSG_PROC();
        }
    }
    public void C010_7_GEN_EVENT() throws IOException,SQLException,Exception {
        if (BPCFFXEV.REQPARTY == '2') {
            if (BPCFFXEV.BUY_CCY.equalsIgnoreCase("156")) {
                WS_PROD_CODE = "CMB00173";
            } else {
                if (BPCFFXEV.SELL_CCY.equalsIgnoreCase("156")) {
                    WS_PROD_CODE = "CMB00172";
                }
            }
        }
        if (BPCFFXEV.REQPARTY == '1') {
            if (BPCFFXEV.BUY_CCY.equalsIgnoreCase("156")) {
                WS_PROD_CODE = "RMB00044";
            } else {
                if (BPCFFXEV.SELL_CCY.equalsIgnoreCase("156")) {
                    WS_PROD_CODE = "RMB00043";
                }
            }
        }
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "FX";
        BPCPOEWA.DATA.PROD_CODE = WS_PROD_CODE;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            BPCPOEWA.DATA.EVENT_CODE = "CR";
        } else {
            BPCPOEWA.DATA.EVENT_CODE = "DR";
        }
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCFFXEV.BUY_CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCFFXEV.BUY_AMT;
        BPCPOEWA.DATA.CI_NO = "" + BPCFFXEV.BUY_CINO;
        JIBS_tmp_int = BPCPOEWA.DATA.CI_NO.length();
        for (int i=0;i<9-JIBS_tmp_int;i++) BPCPOEWA.DATA.CI_NO = "0" + BPCPOEWA.DATA.CI_NO;
        BPCPOEWA.DATA.EFF_DAYS = 0;
        BPCPOEWA.DATA.DESC = BPCFFXEV.SELL_CCY;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[1-1].AMT);
        S000_CALL_BPZPOEWA();
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "FX";
        BPCPOEWA.DATA.PROD_CODE = WS_PROD_CODE;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            BPCPOEWA.DATA.EVENT_CODE = "DR";
        } else {
            BPCPOEWA.DATA.EVENT_CODE = "CR";
        }
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCFFXEV.SELL_CCY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCFFXEV.SELL_AMT;
        BPCPOEWA.DATA.CI_NO = "" + BPCFFXEV.SELL_CINO;
        JIBS_tmp_int = BPCPOEWA.DATA.CI_NO.length();
        for (int i=0;i<9-JIBS_tmp_int;i++) BPCPOEWA.DATA.CI_NO = "0" + BPCPOEWA.DATA.CI_NO;
        BPCPOEWA.DATA.EFF_DAYS = 0;
        BPCPOEWA.DATA.DESC = BPCFFXEV.BUY_CCY;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[1-1].AMT);
        S000_CALL_BPZPOEWA();
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFFXEV.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFFXEV = ");
            CEP.TRC(SCCGWA, BPCFFXEV);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
