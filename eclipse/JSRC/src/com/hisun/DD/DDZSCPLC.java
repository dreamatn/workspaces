package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.CI.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.AI.*;
import com.hisun.IB.*;
import com.hisun.TD.*;
import com.hisun.LN.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSCPLC {
    DBParm DDTCCY_RD;
    int JIBS_tmp_int;
    brParm DDTCCY_BR = new brParm();
    DBParm DDTMST_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DD846";
    String K_DDEG4 = "DDEG4";
    String K_DDEG5 = "DDEG5";
    String WS_ERR_MSG = " ";
    char WS_TMP_ACATTR = ' ';
    double WS_DEP_INT = 0;
    double WS_INT_TAX = 0;
    double WS_OD_INT = 0;
    double WS_DEP_INT_END = 0;
    double WS_SPINT_PRIN = 0;
    char WS_CI_TYPE = ' ';
    String WS_CI_NO = " ";
    char WS_AC_TYPE = ' ';
    char WS_RETURN_INFO = ' ';
    double WS_FEE_AMT = 0;
    short WS_IDX = 0;
    double WS_CURR_BAL = 0;
    double WS_BAL_TOT = 0;
    char WS_ACCU_FLG = ' ';
    DDZSCPLC_WS_DATA_OUT1 WS_DATA_OUT1 = new DDZSCPLC_WS_DATA_OUT1();
    DDZSCPLC_WS_DATA_OUT2 WS_DATA_OUT2 = new DDZSCPLC_WS_DATA_OUT2();
    char WS_DDTCCY_FLG = ' ';
    char WS_CLOSE_DDTCCY_FLG = ' ';
    char WS_READ_STS_FLAG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDCOCPLC DDCOCPLC = new DDCOCPLC();
    DDCSPINT DDCSPINT = new DDCSPINT();
    DDCSCCLC DDCSCCLC = new DDCSCCLC();
    DDCSTRAC DDCSTRAC = new DDCSTRAC();
    DDCSCSHW DDCSCSHW = new DDCSCSHW();
    CICQACAC CICQACAC = new CICQACAC();
    AICUUPIA AICUUPIA = new AICUUPIA();
    AICPQIA AICPQIA = new AICPQIA();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    IBCCASH IBCCASH = new IBCCASH();
    DDCUZFMM DDCUZFMM = new DDCUZFMM();
    TDCUCACK TDCUCACK = new TDCUCACK();
    CICSACAC CICSACAC = new CICSACAC();
    LNCSETLM LNCSETLM = new LNCSETLM();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    CICQACAC CICFACAC = new CICQACAC();
    DDRCCY DDRCCY = new DDRCCY();
    DDRMST DDRMST = new DDRMST();
    String WS_IAACR_VIA_AC = " ";
    SCCGWA SCCGWA;
    DDCSCPLC DDCSCPLC;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCGPFEE BPCGPFEE;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCGCFEE BPCGCFEE;
    public void MP(SCCGWA SCCGWA, DDCSCPLC DDCSCPLC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSCPLC = DDCSCPLC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSCPLC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCGPFEE = (BPCGPFEE) GWA_BP_AREA.FEE_AREA.FEE_PARM_PTR;
        BPCGCFEE = new BPCGCFEE();
        IBS.init(SCCGWA, BPCGCFEE);
        IBS.CPY2CLS(SCCGWA, GWA_BP_AREA.FEE_AREA.FEE_DATA_PTR, BPCGCFEE);
        BPCGCFEE = new BPCGCFEE();
        IBS.init(SCCGWA, BPCGCFEE);
        IBS.CPY2CLS(SCCGWA, GWA_BP_AREA.FEE_AREA.FEE_DATA_PTR, BPCGCFEE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B050_DDTCCY_OTHER_AC_CHECK();
        if (pgmRtn) return;
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && WS_CURR_BAL > 0) {
            B015_CHECK_PAY_PSWD();
            if (pgmRtn) return;
        }
        B020_CAL_INT_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCSCPLC.TO_BV_AM);
        if (DDCSCPLC.TO_BV_AM == '0') {
            B030_CASH_DRAW_PROC();
            if (pgmRtn) return;
        } else {
            if (DDRCCY.CURR_BAL != 0) {
                if (DDCSCPLC.TO_BV_AM == '2') {
                    B035_TR_OUTAC_PROC();
                    if (pgmRtn) return;
                } else {
                    B030_TR_AMT_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        if (WS_CLOSE_DDTCCY_FLG == 'Y') {
            B060_ONLY_CLOSE_DDTCCY();
            if (pgmRtn) return;
        } else {
            B055_OTHER_DDTCCY_CLOSE();
            if (pgmRtn) return;
            B040_AC_CLOSE_PROC();
            if (pgmRtn) return;
        }
        if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            B100_DATA_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSCPLC.FR_BV_AM);
        CEP.TRC(SCCGWA, DDCSCPLC.FR_BV_TYPE);
        CEP.TRC(SCCGWA, DDCSCPLC.FR_CARD);
        CEP.TRC(SCCGWA, DDCSCPLC.FR_AC);
        CEP.TRC(SCCGWA, DDCSCPLC.FR_PSBK);
        CEP.TRC(SCCGWA, DDCSCPLC.CHQ_TYPE);
        CEP.TRC(SCCGWA, DDCSCPLC.CHQ_NO);
        CEP.TRC(SCCGWA, DDCSCPLC.CHQ_ISS_DATE);
        CEP.TRC(SCCGWA, DDCSCPLC.FR_AC_CNAME);
        CEP.TRC(SCCGWA, DDCSCPLC.FR_AC_ENAME);
        CEP.TRC(SCCGWA, DDCSCPLC.FR_CCY);
        CEP.TRC(SCCGWA, DDCSCPLC.FR_CCY_TYPE);
        CEP.TRC(SCCGWA, DDCSCPLC.FR_AMT);
        CEP.TRC(SCCGWA, DDCSCPLC.TO_BV_AM);
        CEP.TRC(SCCGWA, DDCSCPLC.TO_BV_TYPE);
        CEP.TRC(SCCGWA, DDCSCPLC.TO_CARD);
        CEP.TRC(SCCGWA, DDCSCPLC.TO_AC);
        CEP.TRC(SCCGWA, DDCSCPLC.FR_CREV_NO);
        CEP.TRC(SCCGWA, DDCSCPLC.TO_CREV_NO);
        CEP.TRC(SCCGWA, DDCSCPLC.TO_CCY);
        CEP.TRC(SCCGWA, DDCSCPLC.TO_CCY_TYPE);
        CEP.TRC(SCCGWA, DDCSCPLC.TO_AC_CNAME);
        CEP.TRC(SCCGWA, DDCSCPLC.TO_AC_ENAME);
        CEP.TRC(SCCGWA, DDCSCPLC.TO_AMT);
        CEP.TRC(SCCGWA, DDCSCPLC.EX_RATE);
        CEP.TRC(SCCGWA, DDCSCPLC.TICK_NO);
        CEP.TRC(SCCGWA, DDCSCPLC.EX_TIME);
        CEP.TRC(SCCGWA, DDCSCPLC.VAL_DATE);
        CEP.TRC(SCCGWA, DDCSCPLC.TX_RMK);
        CEP.TRC(SCCGWA, DDCSCPLC.REMARKS);
        CEP.TRC(SCCGWA, DDCSCPLC.PAY_TYPE);
        CEP.TRC(SCCGWA, DDCSCPLC.PSWD);
        CEP.TRC(SCCGWA, DDCSCPLC.PAY_PSWD);
        CEP.TRC(SCCGWA, DDCSCPLC.CHK_PSW);
        CEP.TRC(SCCGWA, DDCSCPLC.TRK2_DAT);
        CEP.TRC(SCCGWA, DDCSCPLC.TRK3_DAT);
        B005_POST_AC_CHK();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = DDCSCPLC.FR_AC;
        CICQACAC.DATA.CCY_ACAC = DDCSCPLC.FR_CCY;
        CICQACAC.DATA.CR_FLG = DDCSCPLC.FR_CCY_TYPE;
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
        if (!CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_MUST_DD_AC);
        }
        IBS.init(SCCGWA, LNCSETLM);
        LNCSETLM.FUNC = 'I';
        LNCSETLM.DD_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        LNCSETLM.CCY = DDCSCPLC.FR_CCY;
        LNCSETLM.CCY_TYP = DDCSCPLC.FR_CCY_TYPE;
        CEP.TRC(SCCGWA, LNCSETLM.DD_AC);
        CEP.TRC(SCCGWA, LNCSETLM.CCY);
        CEP.TRC(SCCGWA, LNCSETLM.CCY_TYP);
        S000_CALL_LNZSETLM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCSETLM.CON_FLG);
        if (LNCSETLM.CON_FLG == 'N') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_HAVE_LN_CONT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND);
        }
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        WS_CURR_BAL = DDRCCY.CURR_BAL;
        if (DDCSCPLC.FR_AMT != DDRCCY.CURR_BAL 
            && DDCSCPLC.FR_AMT != 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AMT_INVALID);
        }
        if (WS_CLOSE_DDTCCY_FLG != 'Y') {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(61 - 1, 61 + 1 - 1).equalsIgnoreCase("1")) {
                B005_IBAC_PROC();
                if (pgmRtn) return;
            }
            B006_TD_CONTRACT();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSCPLC.FR_AC;
        T000_READ_DDTMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRMST.OPEN_DP);
        if (DDRMST.OPEN_DP != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH 
            && DDRMST.CROS_DR_FLG == '2') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TXN_BR_NOT_OPEN_BR);
        }
    }
    public void B005_POST_AC_CHK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.POST_AC = DDCSCPLC.FR_AC;
        T000_STARTBR_DDTCCY();
        if (pgmRtn) return;
        T000_READNEXT_DDTCCY();
        if (pgmRtn) return;
        while (WS_READ_STS_FLAG != 'Y') {
            T000_READNEXT_DDTCCY();
            if (pgmRtn) return;
        }
        T000_ENDBR_DDTCCY();
        if (pgmRtn) return;
    }
    public void B005_IBAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCCASH);
        IBCCASH.FUNC = 'Q';
        IBCCASH.VOSTRO_AC = DDRCCY.CUS_AC;
        S000_CALL_IBZCASH();
        if (pgmRtn) return;
        if (IBCCASH.TOT_AMT != 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_IBAC_ACCAMT);
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_LHCF_CLOSE;
        S000_ERR_MSG_PROC();
        if (pgmRtn) return;
    }
    public void B006_TD_CONTRACT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCUCACK);
        TDCUCACK.AC_NO = DDCSCPLC.FR_AC;
        S000_CALL_TDZUCACK();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDCUCACK.PI_FLG);
        CEP.TRC(SCCGWA, TDCUCACK.GK_FLG);
        CEP.TRC(SCCGWA, TDCUCACK.ZC_FLG);
        CEP.TRC(SCCGWA, TDCUCACK.FB_FLG);
        CEP.TRC(SCCGWA, TDCUCACK.FOUND_FLG);
        if (TDCUCACK.PI_FLG == 'Y') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_IS_FX_ACCT);
        }
        if (TDCUCACK.GK_FLG == 'Y') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_IS_GK_ACCT);
        }
        if (TDCUCACK.ZC_FLG == 'Y') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_IS_ZC_ACCT);
        }
        if (TDCUCACK.FB_FLG == 'Y') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_IS_FB_ACCT);
        }
        if (TDCUCACK.FOUND_FLG == 'Y') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_HAVE_TD_CONT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B015_CHECK_PAY_PSWD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUZFMM);
        DDCUZFMM.STD_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        DDCUZFMM.INPUT.CHQ_TYP = '8';
        DDCUZFMM.INPUT.CHQ_ISSU_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUZFMM.INPUT.CHQ_NO = "0000000000000000";
        DDCUZFMM.INPUT.AMT = WS_CURR_BAL;
        DDCUZFMM.INPUT.CHQ_PSW = DDCSCPLC.PAY_PSWD;
        CEP.TRC(SCCGWA, DDCUZFMM.STD_AC);
        CEP.TRC(SCCGWA, DDCUZFMM.INPUT.CHQ_ISSU_DATE);
        CEP.TRC(SCCGWA, DDCUZFMM.INPUT.CHQ_NO);
        CEP.TRC(SCCGWA, DDCUZFMM.INPUT.AMT);
        CEP.TRC(SCCGWA, DDCUDRAC.PAY_PSWD);
        S000_CALL_DDZUZFMM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCUZFMM.OUTPUT_DATE.CHK_RESULT);
        if (DDCUZFMM.OUTPUT_DATE.CHK_RESULT == '2') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CHQ_PAY_PSW_ERR);
        }
        if (DDCUZFMM.OUTPUT_DATE.CHK_RESULT == '4') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CHQ_PAY_PSW_INPUT);
        }
    }
    public void B020_CAL_INT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSPINT);
        DDCSPINT.AC = DDCSCPLC.FR_AC;
        DDCSPINT.CCY = DDCSCPLC.FR_CCY;
        DDCSPINT.CCY_TYPE = DDCSCPLC.FR_CCY_TYPE;
        DDCSPINT.AC_CNM = DDCSCPLC.FR_AC_CNAME;
        DDCSPINT.AC_ENM = DDCSCPLC.FR_AC_ENAME;
        DDCSPINT.PSBK_NO = DDCSCPLC.FR_PSBK;
        DDCSPINT.CHQ_TYPE = DDCSCPLC.CHQ_TYPE;
        DDCSPINT.CHQ_NO = DDCSCPLC.CHQ_NO;
        DDCSPINT.PSWD = DDCSCPLC.PSWD;
        DDCSPINT.RMK = DDCSCPLC.REMARKS;
        DDCSPINT.NARRATIVE = DDCSCPLC.TX_RMK;
        S000_CALL_DDZSPINT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.col = "CURR_BAL,HOLD_BAL,OWNER_BRDP,STS_WORD";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
        WS_BAL_TOT = DDRCCY.CURR_BAL;
        WS_DEP_INT = WS_BAL_TOT - WS_CURR_BAL;
    }
    public void B030_CASH_DRAW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSCSHW);
        DDCSCSHW.BV_TYP = DDCSCPLC.FR_BV_TYPE;
        DDCSCSHW.AC = DDCSCPLC.FR_AC;
        DDCSCSHW.CCY = DDCSCPLC.FR_CCY;
        DDCSCSHW.CCY_TYPE = DDCSCPLC.FR_CCY_TYPE;
        DDCSCSHW.PSBK_NO = DDCSCPLC.FR_PSBK;
        DDCSCSHW.CHQ_TYPE = DDCSCPLC.CHQ_TYPE;
        DDCSCSHW.CHQ_NO = DDCSCPLC.CHQ_NO;
        DDCSCSHW.CHQ_ISS_DATE = DDCSCPLC.CHQ_ISS_DATE;
        DDCSCSHW.CASH_AMT = DDRCCY.CURR_BAL;
        DDCSCSHW.TX_RMK = DDCSCPLC.TX_RMK;
        DDCSCSHW.REMARKS = DDCSCPLC.REMARKS;
        DDCSCSHW.PSWD = DDCSCPLC.PSWD;
        DDCSCSHW.CHQ_PSWD = DDCSCPLC.PAY_PSWD;
        DDCSCSHW.PAY_TYPE = DDCSCPLC.PAY_TYPE;
        DDCSCSHW.CHK_PSW = DDCSCPLC.CHK_PSW;
        DDCSCSHW.TRK_DATE2 = DDCSCPLC.TRK2_DAT;
        DDCSCSHW.TRK_DATE3 = DDCSCPLC.TRK2_DAT;
        DDCSCSHW.PSBK_SEQ = DDCSCPLC.FR_PSBK_SEQ;
        DDCSCSHW.CREV_NO = DDCSCPLC.FR_CREV_NO;
        CEP.TRC(SCCGWA, DDCSCSHW.CHK_PSW);
        CEP.TRC(SCCGWA, DDCSCSHW.TRK_DATE2);
        CEP.TRC(SCCGWA, DDCSCSHW.TRK_DATE3);
        S000_CALL_DDZSCSHW();
        if (pgmRtn) return;
    }
    public void B030_TR_AMT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSTRAC);
        DDCSTRAC.FR_BV_AM = DDCSCPLC.FR_BV_AM;
        DDCSTRAC.FR_BV_TYPE = DDCSCPLC.FR_BV_TYPE;
        DDCSTRAC.FR_CARD = DDCSCPLC.FR_CARD;
        DDCSTRAC.FR_AC = DDCSCPLC.FR_AC;
        DDCSTRAC.FR_PSBK = DDCSCPLC.FR_PSBK;
        DDCSTRAC.CHQ_TYPE = DDCSCPLC.CHQ_TYPE;
        DDCSTRAC.CHQ_NO = DDCSCPLC.CHQ_NO;
        DDCSTRAC.CHQ_ISS_DATE = DDCSCPLC.CHQ_ISS_DATE;
        DDCSTRAC.FR_AC_CNAME = DDCSCPLC.FR_AC_CNAME;
        DDCSTRAC.FR_AC_ENAME = DDCSCPLC.FR_AC_ENAME;
        DDCSTRAC.FR_CCY = DDCSCPLC.FR_CCY;
        DDCSTRAC.FR_CCY_TYPE = DDCSCPLC.FR_CCY_TYPE;
        DDCSTRAC.FR_AMT = DDRCCY.CURR_BAL;
        DDCSTRAC.TO_BV_AM = DDCSCPLC.TO_BV_AM;
        DDCSTRAC.TO_BV_TYPE = DDCSCPLC.TO_BV_TYPE;
        DDCSTRAC.TO_CARD = DDCSCPLC.TO_CARD;
        DDCSTRAC.TO_AC = DDCSCPLC.TO_AC;
        DDCSTRAC.FR_CREV_NO = DDCSCPLC.FR_CREV_NO;
        DDCSTRAC.TO_CREV_NO = DDCSCPLC.TO_CREV_NO;
        DDCSTRAC.TO_CCY = DDCSCPLC.TO_CCY;
        DDCSTRAC.TO_CCY_TYPE = DDCSCPLC.TO_CCY_TYPE;
        DDCSTRAC.TO_AC_CNAME = DDCSCPLC.TO_AC_CNAME;
        DDCSTRAC.TO_AC_ENAME = DDCSCPLC.TO_AC_ENAME;
        DDCSTRAC.TO_AMT = DDRCCY.CURR_BAL;
        DDCSTRAC.EX_RATE = DDCSCPLC.EX_RATE;
        DDCSTRAC.TICK_NO = DDCSCPLC.TICK_NO;
        DDCSTRAC.EX_TIME = DDCSCPLC.EX_TIME;
        DDCSTRAC.VAL_DATE = DDCSCPLC.VAL_DATE;
        DDCSTRAC.TX_RMK = DDCSCPLC.TX_RMK;
        DDCSTRAC.REMARKS = DDCSCPLC.REMARKS;
        DDCSTRAC.PAY_TYPE = DDCSCPLC.PAY_TYPE;
        DDCSTRAC.PSWD = DDCSCPLC.PSWD;
        DDCSTRAC.PAY_PSWD = DDCSCPLC.PAY_PSWD;
        DDCSTRAC.CHK_PSW = 'P';
        DDCSTRAC.FR_PSBK_SEQ = DDCSCPLC.FR_PSBK_SEQ;
        DDCSTRAC.TO_PSBK_SEQ = DDCSCPLC.TO_PSBK_SEQ;
        DDCSTRAC.CHK_PSW = DDCSCPLC.CHK_PSW;
        DDCSTRAC.TRK2_DAT = DDCSCPLC.TRK2_DAT;
        DDCSTRAC.TRK3_DAT = DDCSCPLC.TRK3_DAT;
        S000_CALL_DDZSTRAC();
        if (pgmRtn) return;
    }
    public void B035_TR_OUTAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQIA);
        AICPQIA.CD.AC_TYP = "3";
        AICPQIA.CD.BUSI_KND = "DDKH";
        AICPQIA.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICPQIA.CCY = DDCSCPLC.FR_CCY;
        AICPQIA.SIGN = 'C';
        S000_CALL_AIZPQIA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICPQIA.AC);
        CEP.TRC(SCCGWA, AICPQIA.CHS_NM);
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = AICPQIA.AC;
        AICUUPIA.DATA.RVS_SEQ = 0;
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.AMT = DDRCCY.CURR_BAL;
        AICUUPIA.DATA.CCY = DDCSCPLC.FR_CCY;
        AICUUPIA.DATA.PAY_MAN = DDCSCPLC.FR_AC_CNAME;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.POST_NARR = " ";
        AICUUPIA.DATA.EVENT_CODE = "CR";
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, AICUUPIA.DATA.RVS_NO);
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.AC = DDCSCPLC.FR_AC;
        DDCUDRAC.CCY = DDCSCPLC.FR_CCY;
        DDCUDRAC.TX_AMT = DDRCCY.CURR_BAL;
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.OTHER_AC = AICPQIA.AC;
        DDCUDRAC.OTHER_CCY = DDCSCPLC.FR_CCY;
        DDCUDRAC.OTHER_AMT = DDRCCY.CURR_BAL;
        DDCUDRAC.CHK_PSW_FLG = 'N';
        DDCUDRAC.TX_MMO = "C004";
        S000_CALL_DDZUDRAC();
        if (pgmRtn) return;
    }
    public void B050_DDTCCY_OTHER_AC_CHECK() throws IOException,SQLException,Exception {
        WS_DDTCCY_FLG = 'F';
        WS_CLOSE_DDTCCY_FLG = 'N';
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDCSCPLC.FR_AC;
        T000_STARTBR_DDTCCY_CUS_AC();
        if (pgmRtn) return;
        T000_READNEXT_DDTCCY_CUS_AC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CFX111");
        CEP.TRC(SCCGWA, WS_DDTCCY_FLG);
        CEP.TRC(SCCGWA, WS_CLOSE_DDTCCY_FLG);
        while ((WS_DDTCCY_FLG != 'N' 
            && WS_CLOSE_DDTCCY_FLG != 'Y')) {
            CEP.TRC(SCCGWA, DDRCCY.CUS_AC);
            CEP.TRC(SCCGWA, DDRCCY.CCY);
            CEP.TRC(SCCGWA, DDRCCY.STS);
            CEP.TRC(SCCGWA, DDRCCY.CCY_TYPE);
            if ((DDRCCY.CCY.equalsIgnoreCase(DDCSCPLC.FR_CCY)) 
                && (DDRCCY.CCY_TYPE == DDCSCPLC.FR_CCY_TYPE)) {
            } else {
                if (DDRCCY.STS != 'C') {
                    WS_CLOSE_DDTCCY_FLG = 'Y';
                }
            }
            T000_READNEXT_DDTCCY_CUS_AC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "CFX222");
        CEP.TRC(SCCGWA, WS_DDTCCY_FLG);
        CEP.TRC(SCCGWA, WS_CLOSE_DDTCCY_FLG);
        T000_ENDBR_DDTCCY();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = DDCSCPLC.FR_AC;
        CICQACAC.FUNC = 'R';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CICQACAC, CICFACAC);
        CEP.TRC(SCCGWA, CICFACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        CEP.TRC(SCCGWA, CICFACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC);
        CEP.TRC(SCCGWA, CICFACAC.O_DATA.O_ACAC_DATA.O_CR_FLG);
        if (DDCSCPLC.FR_CCY.equalsIgnoreCase(CICFACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC) 
            && DDCSCPLC.FR_CCY_TYPE == CICFACAC.O_DATA.O_ACAC_DATA.O_CR_FLG 
            && WS_CLOSE_DDTCCY_FLG == 'Y') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DEFAULT_AC_NOT_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B060_ONLY_CLOSE_DDTCCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDCSCPLC.FR_AC;
        DDRCCY.CCY = DDCSCPLC.FR_CCY;
        DDRCCY.CCY_TYPE = DDCSCPLC.FR_CCY_TYPE;
        T000_READUP_DDTCCY();
        if (pgmRtn) return;
        DDRCCY.STS = 'C';
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 11 - 1) + "1" + DDRCCY.STS_WORD.substring(11 + 1 - 1);
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTCCY();
        if (pgmRtn) return;
    }
    public void B055_OTHER_DDTCCY_CLOSE() throws IOException,SQLException,Exception {
        WS_DDTCCY_FLG = 'F';
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDCSCPLC.FR_AC;
        T000_STARTBR_DDTCCY_CUS_AC();
        if (pgmRtn) return;
        T000_READNEXT_DDTCCY_CUS_AC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CFX333");
        CEP.TRC(SCCGWA, WS_DDTCCY_FLG);
        while (WS_DDTCCY_FLG != 'N') {
            CEP.TRC(SCCGWA, DDRCCY.CUS_AC);
            CEP.TRC(SCCGWA, DDRCCY.CCY);
            CEP.TRC(SCCGWA, DDRCCY.STS);
            CEP.TRC(SCCGWA, DDRCCY.CCY_TYPE);
            if (DDRCCY.STS == 'C') {
                IBS.init(SCCGWA, CICSACAC);
                CICSACAC.FUNC = 'D';
                CICSACAC.DATA.ACAC_NO = DDRCCY.KEY.AC;
                S000_CALL_CIZSACAC();
                if (pgmRtn) return;
                IBS.init(SCCGWA, BPCSOCAC);
                BPCSOCAC.FUNC = 'U';
                BPCSOCAC.AC = DDCSCPLC.FR_AC;
                BPCSOCAC.ACO_AC = DDRCCY.KEY.AC;
                BPCSOCAC.CLOSE_AC_STS = DDRCCY.STS;
                BPCSOCAC.LOSS_INT = WS_DEP_INT;
                BPCSOCAC.LOSS_AMT = WS_BAL_TOT;
                BPCSOCAC.LOSS_TYP = '2';
                BPCSOCAC.STS = 'C';
                BPCSOCAC.CLOSE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                BPCSOCAC.CLOSE_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCSOCAC.CLOSE_REASON = DDCSCPLC.TX_RMK;
                S000_CALL_BPZSOCAC();
                if (pgmRtn) return;
            }
            T000_READNEXT_DDTCCY_CUS_AC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "CFX444");
        CEP.TRC(SCCGWA, WS_DDTCCY_FLG);
        T000_ENDBR_DDTCCY();
        if (pgmRtn) return;
    }
    public void B040_AC_CLOSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSCCLC);
        DDCSCCLC.FUNC = 'M';
        DDCSCCLC.CARD_NO = DDCSCPLC.FR_CARD;
        DDCSCCLC.AC = DDCSCPLC.FR_AC;
        DDCSCCLC.CCY = DDCSCPLC.FR_CCY;
        DDCSCCLC.CCY_TYPE = DDCSCPLC.FR_CCY_TYPE;
        DDCSCCLC.PSBK_NO = DDCSCPLC.FR_PSBK;
        DDCSCCLC.RMK = DDCSCPLC.REMARKS;
        DDCSCCLC.NRTV = DDCSCPLC.TX_RMK;
        DDCSCCLC.CLOSE_INT = WS_DEP_INT;
        DDCSCCLC.CLOSE_AMT = WS_BAL_TOT;
        CEP.TRC(SCCGWA, DDCSCCLC.FUNC);
        CEP.TRC(SCCGWA, DDCSCCLC.CARD_NO);
        CEP.TRC(SCCGWA, DDCSCCLC.AC);
        CEP.TRC(SCCGWA, DDCSCCLC.CCY);
        CEP.TRC(SCCGWA, DDCSCCLC.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCSCCLC.PSBK_NO);
        CEP.TRC(SCCGWA, DDCSCCLC.RMK);
        CEP.TRC(SCCGWA, DDCSCCLC.NRTV);
        S000_CALL_DDZSCCLC();
        if (pgmRtn) return;
    }
    public void B100_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOCPLC);
        DDCOCPLC.FR_BV_AMT = DDCSCPLC.FR_BV_AM;
        DDCOCPLC.FR_BV_TYPE = DDCSCPLC.FR_BV_TYPE;
        DDCOCPLC.FR_AC = DDCSCPLC.FR_AC;
        DDCOCPLC.ENG_NM1 = DDCSCPLC.FR_AC_ENAME;
        DDCOCPLC.CHN_NM1 = DDCSCPLC.FR_AC_CNAME;
        DDCOCPLC.TO_BV_AMT = DDCSCPLC.TO_BV_AM;
        DDCOCPLC.TO_BV_TYPE = DDCSCPLC.TO_BV_TYPE;
        if (DDCSCPLC.TO_BV_AM == '2') {
            DDCOCPLC.TO_AC = AICPQIA.AC;
            DDCOCPLC.TO_AC_NAME = AICPQIA.CHS_NM;
        } else {
            DDCOCPLC.TO_AC = DDCSCPLC.TO_AC;
        }
        DDCOCPLC.ENG_NM2 = DDCSCPLC.TO_AC_ENAME;
        DDCOCPLC.CHN_NM2 = DDCSCPLC.TO_AC_CNAME;
        DDCOCPLC.FR_CCY = DDCSCPLC.FR_CCY;
        DDCOCPLC.FR_CCY_TYPE = DDCSCPLC.FR_CCY_TYPE;
        DDCOCPLC.TO_CCY = DDCSCPLC.TO_CCY;
        DDCOCPLC.TO_CCY_TYPE = DDCSCPLC.TO_CCY_TYPE;
        DDCOCPLC.FR_AMT = WS_BAL_TOT;
        DDCOCPLC.TO_AMT = WS_BAL_TOT;
        DDCOCPLC.CHQ_TYPE = DDCSCPLC.CHQ_TYPE;
        DDCOCPLC.ISS_DATE = DDCSCPLC.CHQ_ISS_DATE;
        if (!DDCSCPLC.CHQ_NO.equalsIgnoreCase("0")) {
            DDCOCPLC.CHQ_NO = DDCSCPLC.CHQ_NO;
        }
        DDCOCPLC.FR_PSBK = DDCSCPLC.FR_PSBK;
        DDCOCPLC.FR_CARD = DDCSCPLC.FR_CARD;
        DDCOCPLC.FR_CREV = DDCSCPLC.FR_CREV_NO;
        if (DDCSCPLC.TO_BV_AM == '2') {
            DDCOCPLC.TO_CREV = AICUUPIA.DATA.RVS_NO;
        } else {
            DDCOCPLC.TO_CREV = DDCSCPLC.TO_CREV_NO;
        }
        DDCOCPLC.TO_CARD = DDCSCPLC.TO_CARD;
        DDCOCPLC.VAL_DATE = DDCSCPLC.VAL_DATE;
        DDCOCPLC.TX_REF = DDCSCPLC.TX_RMK;
        DDCOCPLC.TICKET_NO = DDCSCPLC.TICK_NO;
        DDCOCPLC.EX_RATE = DDCSCPLC.EX_RATE;
        DDCOCPLC.EX_TM = DDCSCPLC.EX_TIME;
        DDCOCPLC.RMKS = DDCSCPLC.REMARKS;
        DDCOCPLC.PAY_TYPE = DDCSCPLC.PAY_TYPE;
        DDCOCPLC.TX_MMO = "102";
        CEP.TRC(SCCGWA, WS_BAL_TOT);
        CEP.TRC(SCCGWA, DDCOCPLC.FR_AMT);
        CEP.TRC(SCCGWA, DDCOCPLC.TO_AMT);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOCPLC;
        SCCFMT.DATA_LEN = 1776;
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_STARTBR_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_BR.rp = new DBParm();
        DDTCCY_BR.rp.TableName = "DDTCCY";
        DDTCCY_BR.rp.eqWhere = "POST_AC";
        IBS.STARTBR(SCCGWA, DDRCCY, DDTCCY_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_READ_STS_FLAG = 'Y';
        }
    }
    public void T000_STARTBR_DDTCCY_CUS_AC() throws IOException,SQLException,Exception {
        DDTCCY_BR.rp = new DBParm();
        DDTCCY_BR.rp.TableName = "DDTCCY";
        DDTCCY_BR.rp.eqWhere = "CUS_AC";
        IBS.STARTBR(SCCGWA, DDRCCY, DDTCCY_BR);
    }
    public void T000_READNEXT_DDTCCY_CUS_AC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCCY, this, DDTCCY_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_DDTCCY_FLG = 'N';
        }
    }
    public void T000_READNEXT_DDTCCY() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCCY, this, DDTCCY_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_READ_STS_FLAG = 'Y';
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, DDRCCY.POST_AC);
        CEP.TRC(SCCGWA, DDRCCY.CUS_AC);
        CEP.TRC(SCCGWA, DDRCCY.CINT_FLG);
        CEP.TRC(SCCGWA, DDRCCY.STS);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0' 
            && (!DDRCCY.POST_AC.equalsIgnoreCase(DDRCCY.CUS_AC)) 
            && DDRCCY.CINT_FLG == 'Y' 
            && DDRCCY.STS != 'C' 
            && (DDRCCY.CCY.equalsIgnoreCase(DDCSCPLC.FR_CCY) 
            && DDRCCY.CCY_TYPE == DDCSCPLC.FR_CCY_TYPE 
            && WS_CLOSE_DDTCCY_FLG == 'Y')) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_IS_POST_AC);
        }
    }
    public void T000_ENDBR_DDTCCY() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCCY_BR);
    }
    public void T000_READUP_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        DDTCCY_RD.fst = true;
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        if (AICPQIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZCASH() throws IOException,SQLException,Exception {
        IBZCASH IBZCASH = new IBZCASH();
        IBZCASH.MP(SCCGWA, IBCCASH);
        CEP.TRC(SCCGWA, IBCCASH.RC.RC_CODE);
        if (IBCCASH.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, IBCCASH.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_TDZUCACK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-CLOSE-AC-CHK", TDCUCACK);
    }
    public void S000_CALL_DDZSCSHW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSCSHW", DDCSCSHW);
    }
    public void S000_CALL_DDZSPINT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-POST-INT", DDCSPINT);
    }
    public void S000_CALL_DDZSTRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-TR-AC", DDCSTRAC);
    }
    public void S000_CALL_DDZSCCLC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-CLS-C-AC", DDCSCCLC);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void S000_CALL_DDZUZFMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DDZUZFMM", DDCUZFMM);
    }
    public void S000_CALL_CIZSACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACAC", CICSACAC);
        if (CICSACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSACAC.RC);
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND);
        }
    }
    public void S000_CALL_LNZSETLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-M-SETLM", LNCSETLM);
        if (LNCSETLM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSETLM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-OCAC-INFO", BPCSOCAC);
        if (BPCSOCAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCSOCAC.RC);
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
