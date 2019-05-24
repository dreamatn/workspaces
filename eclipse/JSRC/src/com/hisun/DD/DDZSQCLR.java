package com.hisun.DD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.CI.CICACCU;
import com.hisun.CI.CICCUST;
import com.hisun.CI.CICQACAC;
import com.hisun.DC.DCCPACTY;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class DDZSQCLR {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DDX01";
    String WS_ERR_MSG = " ";
    double WS_TX_AMT = 0;
    double WS_DEP_INT = 0;
    double WS_OD_INT = 0;
    double WS_INT_TAX = 0;
    double WS_NRA_TAX = 0;
    double WS_TOT_AMT = 0;
    double WS_TOTAL_AMT = 0;
    char WS_AC_STS = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCOQCLR DDCOQCLR = new DDCOQCLR();
    DDCUPINT DDCUPINT = new DDCUPINT();
    CICACCU CICACCU = new CICACCU();
    CICQACAC CICQACAC = new CICQACAC();
    CICCUST CICCUST = new CICCUST();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DDRCCY DDRCCY = new DDRCCY();
    DDRMST DDRMST = new DDRMST();
    SCCGWA SCCGWA;
    DDCSQCLR DDCSQCLR;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DDCSQCLR DDCSQCLR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSQCLR = DDCSQCLR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSQCLR return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSQCLR.CCY);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B015_CHK_CUS_AC_PROC();
        if (pgmRtn) return;
        B020_GET_DD_AC_NAME_PROC();
        if (pgmRtn) return;
        B030_INQ_CCY_BAL_PROC();
        if (pgmRtn) return;
        B050_INQ_AC_INT_PROC();
        if (pgmRtn) return;
        B060_AC_STS_PROC();
        if (pgmRtn) return;
        B070_OUTPUT_DATA_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSQCLR.DR_CARD);
        CEP.TRC(SCCGWA, DDCSQCLR.AC_NO);
        CEP.TRC(SCCGWA, DDCSQCLR.AC_CNM);
        CEP.TRC(SCCGWA, DDCSQCLR.AC_ENM);
        CEP.TRC(SCCGWA, DDCSQCLR.PSBK_NO);
        CEP.TRC(SCCGWA, DDCSQCLR.CCY);
        CEP.TRC(SCCGWA, DDCSQCLR.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCSQCLR.DRW_PSW);
        if (DDCSQCLR.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSQCLR.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B015_CHK_CUS_AC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XX");
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = DDCSQCLR.AC_NO;
        CICQACAC.DATA.CCY_ACAC = DDCSQCLR.CCY;
        CICQACAC.DATA.CR_FLG = DDCSQCLR.CCY_TYPE;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
    }
    public void B015_GET_FREE_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.FUNC = '3';
        DCCPACTY.INPUT.AC = DDCSQCLR.AC_NO;
        DCCPACTY.INPUT.CCY = DDCSQCLR.CCY;
        DCCPACTY.INPUT.CCY_TYPE = DDCSQCLR.CCY_TYPE;
        S000_CALL_DCZPACTY();
        if (pgmRtn) return;
    }
    public void B020_GET_DD_AC_NAME_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
    }
    public void B030_INQ_CCY_BAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        CEP.TRC(SCCGWA, DDRCCY.CCY);
        CEP.TRC(SCCGWA, DDRCCY.CCY_TYPE);
        T000_READ_DDTCCY();
        if (pgmRtn) return;
        WS_TX_AMT = DDRCCY.CURR_BAL;
        WS_TX_AMT = DDRCCY.CURR_BAL + DDRCCY.CCAL_TOT_BAL;
        WS_TOTAL_AMT = WS_TX_AMT;
        CEP.TRC(SCCGWA, DDRCCY.STS_WORD);
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
            || DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
            || DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
            || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
            WS_TX_AMT = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL;
            if (WS_TX_AMT > 0) {
                WS_TX_AMT = 0;
            }
            CEP.TRC(SCCGWA, WS_TX_AMT);
        }
    }
    public void B050_INQ_AC_INT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUPINT);
        DDCUPINT.AC = DDCSQCLR.AC_NO;
        DDCUPINT.CCY = DDCSQCLR.CCY;
        DDCUPINT.CCY_TYPE = DDCSQCLR.CCY_TYPE;
        DDCUPINT.TX_TYP = 'I';
        CEP.TRC(SCCGWA, DDCUPINT.CCY);
        CEP.TRC(SCCGWA, DDCUPINT.CCY_TYPE);
        S000_CALL_DDZUPINT();
        if (pgmRtn) return;
    }
    public void B060_AC_STS_PROC() throws IOException,SQLException,Exception {
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            WS_AC_STS = DDRMST.AC_STS;
            if (DDRMST.AC_STS == 'M' 
                && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_HAND);
            }
        }
    }
    public void B070_OUTPUT_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOQCLR);
        DDCOQCLR.AC_NO = DDCSQCLR.AC_NO;
        DDCOQCLR.AC_STS = WS_AC_STS;
        DDCOQCLR.AC_CNM = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
        DDCOQCLR.AC_ENM = CICQACAC.O_DATA.O_ACR_DATA.O_AC_ENM;
        DDCOQCLR.CCY = DDCSQCLR.CCY;
        DDCOQCLR.CCY_TYPE = DDCSQCLR.CCY_TYPE;
        DDCOQCLR.DR_CARD = DDCSQCLR.DR_CARD;
        DDCOQCLR.PSBK_NO = DDCSQCLR.PSBK_NO;
        DDCOQCLR.BILL_TYPE = ' ';
        DDCOQCLR.CHQ_NO = "                ";
        DDCOQCLR.PAY_TYPE = ' ';
        DDCOQCLR.DRW_PSW = "                                ";
        DDCOQCLR.ID_TYP = "     ";
        DDCOQCLR.ID_NO = "                                                                      ";
        DDCOQCLR.PRIN = WS_TX_AMT;
        DDCOQCLR.PRIN = WS_TOTAL_AMT;
        CEP.TRC(SCCGWA, DDCOQCLR.PRIN);
        CEP.TRC(SCCGWA, DDCUPINT.NRA_TAX);
