package com.hisun.DD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCFBVQU;
import com.hisun.BP.BPCFCSTS;
import com.hisun.BP.BPCFFTXI;
import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPQORG;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPCTCALF;
import com.hisun.BP.BPCUBUSE;
import com.hisun.BP.BPRPRMT;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICQACAC;
import com.hisun.DC.DCCMSG_ERROR_MSG;
import com.hisun.DC.DCCPACTY;
import com.hisun.DC.DCCUCINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class DDZUSIGN {
    boolean pgmRtn = false;
    String K_STS_TABLE_APP = "DD";
    String K_CHK_STS_TBL_P = "5403";
    String K_CHK_STS_TBL_C = "5404";
    String K_OUTPUT_FMT = "DD807";
    String K_CTL_PRM_TYP = "PDD13";
    String K_HIS_CPB_NM = "DDCUSIGN";
    String K_HIS_RMKS = "CHEQUE ISSUE";
    String CPN_DD_U_SIGN_PROC = "DD-U-SIGN-PROC";
    String CPN_U_BPZFBVQU = "BP-F-BV-PRM-QUERY";
    String WS_ERR_MSG = " ";
    short WS_IDX = 0;
    short WS_ODX = 0;
    short WS_FDX = 0;
    short WS_FDX1 = 0;
    short WS_FDX2 = 0;
    String WS_TS_REC = " ";
    short WS_LEN = 0;
    short WS_RET = 0;
    short WS_RMDR = 0;
    long WS_STR_CHQ_NO_L = 0;
    long WS_END_CHQ_NO_L = 0;
    String WS_STR_CHQ_NO_X = " ";
    String WS_END_CHQ_NO_X = " ";
    long WS_STR_CHQ_NO_3 = 0;
    long WS_END_CHQ_NO_3 = 0;
    int WS_NUM = 0;
    String WS_CCY = " ";
    char WS_CCY_TYPE = ' ';
    String WS_CHQ_BV_CD_L = " ";
    String WS_CHQ_BV_CD_F = " ";
    int WS_CHQ_NUM_L = 0;
    short WS_NUMX = 0;
    short WS_NUMH = 0;
    short WS_NUMN = 0;
    short WS_NUML = 0;
    DDZUSIGN_WS_CHQ_DATA WS_CHQ_DATA = new DDZUSIGN_WS_CHQ_DATA();
    short WS_L = 0;
    String WS_STS = " ";
    double AMT1 = 0;
    double AMT2 = 0;
    double AMT3 = 0;
    double AMT4 = 0;
    int WS_CHQ_NUM_F = 0;
    int WS_CNT = 0;
    int WS_CHQ_CNT = 0;
    int WS_UNUSE_CNT = 0;
    long WS_CHQ_STR_CHQ_NO = 0;
    long WS_CHQ_END_CHQ_NO = 0;
    char WS_CHQ_FLG = ' ';
    char WS_INF_FLG = ' ';
    char WS_CCY_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    DDCOSIGN DDCOSIGN = new DDCOSIGN();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    CICACCU CICACCU = new CICACCU();
    DPCPARMP DPCPARMP = new DPCPARMP();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    DDRPAID DDRPAID = new DDRPAID();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DDCSPCHQ DDCSPCHQ = new DDCSPCHQ();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    CICQACAC CICQACAC = new CICQACAC();
    BPCPQORG BPCPQORG = new BPCPQORG();
    DDRMST DDRMST = new DDRMST();
    DDRCHQ DDRCHQ = new DDRCHQ();
    DDRCCY DDRCCY = new DDRCCY();
    DDRINF DDRINF = new DDRINF();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDCUSIGN DDCUSIGN;
    public void MP(SCCGWA SCCGWA, DDCUSIGN DDCUSIGN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUSIGN = DDCUSIGN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZUSIGN return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUSIGN.AC_NO);
        CEP.TRC(SCCGWA, DDCUSIGN.CCY);
        CEP.TRC(SCCGWA, DDCUSIGN.CCY_TYPE);
        B005_GET_ACAC_INFO();
        if (pgmRtn) return;
        B009_CHECK_UNUSE_CHQ();
        if (pgmRtn) return;
        B010_GET_AC_INF_PROC();
        if (pgmRtn) return;
        B020_CHK_AC_STS();
        if (pgmRtn) return;
        B030_CI_INF_PROC();
        if (pgmRtn) return;
        B040_CHK_STS_TBL_PROC();
        if (pgmRtn) return;
        B050_CHK_PROD_ATTIBUTE_PROC();
        if (pgmRtn) return;
        B070_CHK_COM_AUT_PROC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDCOSIGN);
        CEP.TRC(SCCGWA, DDCUSIGN.ROW_CNT);
        for (WS_IDX = 1; WS_IDX <= DDCUSIGN.ROW_CNT; WS_IDX += 1) {
            IBS.init(SCCGWA, WS_CHQ_DATA);
            WS_STR_CHQ_NO_L = 0;
            WS_END_CHQ_NO_L = 0;
            WS_NUM = 0;
            WS_NUMN = 0;
            WS_NUMH = 0;
            WS_NUMX = 0;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCUSIGN.CHQ_DATA[WS_IDX-1]);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_CHQ_DATA);
            CEP.TRC(SCCGWA, WS_CHQ_DATA.WS_CHQ_BV_CD);
            B072_CHQ_USE_ROWCNT_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_NUMN);
            CEP.TRC(SCCGWA, WS_NUMH);
            if (WS_NUMH == 0) {
                CEP.TRC(SCCGWA, "1");
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_CHQ_DATA.WS_STR_CHQ_NO);
                if (JIBS_tmp_str[0].trim().length() == 0) WS_STR_CHQ_NO_3 = 0;
                else WS_STR_CHQ_NO_3 = Long.parseLong(JIBS_tmp_str[0]);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_CHQ_DATA.WS_END_CHQ_NO);
                if (JIBS_tmp_str[0].trim().length() == 0) WS_END_CHQ_NO_3 = 0;
                else WS_END_CHQ_NO_3 = Long.parseLong(JIBS_tmp_str[0]);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_CHQ_DATA.WS_STR_CHQ_NO);
                if (JIBS_tmp_str[0].trim().length() == 0) WS_STR_CHQ_NO_L = 0;
                else WS_STR_CHQ_NO_L = Long.parseLong(JIBS_tmp_str[0]);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_CHQ_DATA.WS_END_CHQ_NO);
                if (JIBS_tmp_str[0].trim().length() == 0) WS_END_CHQ_NO_L = 0;
                else WS_END_CHQ_NO_L = Long.parseLong(JIBS_tmp_str[0]);
            } else {
                CEP.TRC(SCCGWA, "2");
                WS_STR_CHQ_NO_3 = WS_CHQ_DATA.WS_STR_CHQ_NO.WS_STR_CHQ_NO_9;
                WS_END_CHQ_NO_3 = WS_CHQ_DATA.WS_END_CHQ_NO.WS_END_CHQ_NO_9;
                WS_STR_CHQ_NO_L = WS_CHQ_DATA.WS_STR_CHQ_NO.WS_STR_CHQ_NO_9;
                WS_END_CHQ_NO_L = WS_CHQ_DATA.WS_END_CHQ_NO.WS_END_CHQ_NO_9;
            }
            CEP.TRC(SCCGWA, WS_CHQ_DATA);
            CEP.TRC(SCCGWA, WS_CHQ_DATA.WS_CHQ_NUM);
            CEP.TRC(SCCGWA, WS_CHQ_DATA.WS_STR_CHQ_NO);
            CEP.TRC(SCCGWA, WS_CHQ_DATA.WS_END_CHQ_NO);
            CEP.TRC(SCCGWA, WS_STR_CHQ_NO_3);
            CEP.TRC(SCCGWA, WS_END_CHQ_NO_3);
            CEP.TRC(SCCGWA, WS_STR_CHQ_NO_L);
            CEP.TRC(SCCGWA, WS_END_CHQ_NO_L);
            CEP.TRC(SCCGWA, WS_CHQ_DATA.WS_STR_CHQ_NO.WS_STR_CHQ_NO_9);
            CEP.TRC(SCCGWA, WS_CHQ_DATA.WS_END_CHQ_NO.WS_END_CHQ_NO_9);
            CEP.TRC(SCCGWA, WS_STR_CHQ_NO_X);
            CEP.TRC(SCCGWA, WS_END_CHQ_NO_X);
            B080_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B090_CHQ_USE_ROWCNT_PROC();
            if (pgmRtn) return;
            B100_NFIN_TXN_HIS_PROC();
            if (pgmRtn) return;
            B110_SIG_CHQB_ROWCNT_INF_PROC();
            if (pgmRtn) return;
            WS_CHQ_NUM_F = WS_CHQ_DATA.WS_CHQ_NUM;
            WS_CHQ_BV_CD_F = WS_CHQ_DATA.WS_CHQ_BV_CD;
            B120_CHARGES_ROWCNT_PROC();
            if (pgmRtn) return;
        }
        B130_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B080_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUSIGN.AC_NO);
        CEP.TRC(SCCGWA, DDCUSIGN.CCY);
        CEP.TRC(SCCGWA, DDCUSIGN.CBK_SIZE);
        CEP.TRC(SCCGWA, WS_CHQ_DATA.WS_STR_CHQ_NO);
        CEP.TRC(SCCGWA, WS_CHQ_DATA.WS_END_CHQ_NO);
        if (DDCUSIGN.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCUSIGN.CCY_TYPE == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            R000_CHECK_CCY_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_CHQ_DATA.WS_CHQ_TYP);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_CHQ_DATA.WS_STR_CHQ_NO);
        if (JIBS_tmp_str[0].equalsIgnoreCase("0")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STR_CHQ_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_CHQ_DATA.WS_END_CHQ_NO);
        if (JIBS_tmp_str[0].equalsIgnoreCase("0")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_END_CHQ_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_END_CHQ_NO_3 < WS_STR_CHQ_NO_3) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_END_LESSTHAN_STR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
        CEP.TRC(SCCGWA, WS_CHQ_DATA.WS_CHQ_TYP);
        if (DDRMST.AC_TYPE == 'G' 
            && WS_CHQ_DATA.WS_CHQ_TYP == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NOT_SEL_CHQ;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRMST.AC_TYPE == 'D' 
            && WS_CHQ_DATA.WS_CHQ_TYP == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NOT_SEL_CHQ;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRMST.CASH_FLG);
        if ((DDRMST.CASH_FLG == '2' 
            || DDRMST.CASH_FLG == '4') 
            && WS_CHQ_DATA.WS_CHQ_TYP == '1') {
            CEP.TRC(SCCGWA, DDRMST.CASH_FLG);
            CEP.TRC(SCCGWA, WS_CHQ_DATA.WS_CHQ_TYP);
            CEP.TRC(SCCGWA, "1111111111111111");
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NOT_ALLOW_CHQ;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B005_GET_ACAC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'C';
        CICQACAC.DATA.AGR_NO = DDCUSIGN.AC_NO;
        CICQACAC.DATA.CCY_ACAC = DDCUSIGN.CCY;
        CICQACAC.DATA.CR_FLG = DDCUSIGN.CCY_TYPE;
        CEP.TRC(SCCGWA, CICQACAC.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.DATA.CCY_ACAC);
        CEP.TRC(SCCGWA, CICQACAC.DATA.CR_FLG);
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        T000_READ_DDTCCY();
        if (pgmRtn) return;
    }
    public void B010_GET_AC_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCUSIGN.AC_NO;
        T000_READ_DDTMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRMST.CASH_FLG);
    }
    public void B020_CHK_AC_STS() throws IOException,SQLException,Exception {
        if (DDRMST.AC_STS == 'C' 
            || DDRMST.AC_STS == 'M') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRMST.AC_STS == 'O' 
            || DDRMST.AC_STS == 'V') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INACTIVE_AC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRMST.AC_TYPE == 'G') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_K_NOT_ALLOW_CHQ;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.ATTR);
        CEP.TRC(SCCGWA, BPCPQORG.BBR);
        CEP.TRC(SCCGWA, DDRMST.OWNER_BR);
        if (DDRMST.OWNER_BR != BPCPQORG.BBR) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ERR_TXN_BRANCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCUSIGN.TX_TYP);
    }
    public void B070_CHK_COM_AUT_PROC() throws IOException,SQLException,Exception {
        if (CICACCU.DATA.CI_TYP == '2') {
            if (DDCUSIGN.AUTH_TYP.trim().length() > 0) {
                T000_READ_DDTINF();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_CI_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCUSIGN.AC_NO;
        S000_CALL_CISOACCU();
        if (pgmRtn) return;
        if (CICACCU.DATA.CI_TYP == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CI_TYP_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_CHK_STS_TBL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        BPCFCSTS.AP_MMO = K_STS_TABLE_APP;
        if (CICACCU.DATA.CI_TYP == '1') {
            BPCFCSTS.TBL_NO = K_CHK_STS_TBL_P;
        } else {
            BPCFCSTS.TBL_NO = K_CHK_STS_TBL_C;
        }
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        BPCFCSTS.STATUS_WORD = CICACCU.DATA.CI_STSW + BPCFCSTS.STATUS_WORD.substring(80);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + DDRMST.AC_STS_WORD + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 201 - 1) + DDRCCY.STS_WORD + BPCFCSTS.STATUS_WORD.substring(201 + 99 - 1);
        CEP.TRC(SCCGWA, BPCFCSTS.AP_MMO);
        CEP.TRC(SCCGWA, BPCFCSTS.TBL_NO);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        S000_CALL_BPZFCSTS();
        if (pgmRtn) return;
    }
    public void B050_CHK_PROD_ATTIBUTE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQPRD);
        CEP.TRC(SCCGWA, DDRMST.PROD_CODE);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRMST.PROD_CODE;
        CEP.TRC(SCCGWA, DDCIQPRD.INPUT_DATA.PROD_CODE);
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDVMPRD.VAL.PRD_TOOL_CHQ);
        if (DDVMPRD.VAL.PRD_TOOL_CHQ != 'Y') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.PRD_NOT_ALLOW_CHQ;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B075_PARM_QRY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, DDCSPCHQ);
        CEP.TRC(SCCGWA, K_CTL_PRM_TYP);
        CEP.TRC(SCCGWA, WS_CHQ_DATA.WS_CHQ_BV_CD);
        BPRPRMT.KEY.TYP = K_CTL_PRM_TYP;
        BPRPRMT.KEY.CD = WS_CHQ_DATA.WS_CHQ_BV_CD;
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCSPCHQ.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.TYP);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
        CEP.TRC(SCCGWA, BPRPRMT.DAT_TXT);
        BPCPRMM.FUNC = '3';
        BPCPRMM.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MPRD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
