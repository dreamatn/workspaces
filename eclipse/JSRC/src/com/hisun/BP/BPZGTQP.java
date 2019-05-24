package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZGTQP {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZGTQP";
    String K_FWD_TENOR = "FWDT ";
    String CPN_INQ_PUB_CODE = "BP-P-INQ-PC       ";
    String CPN_R_ERGR_M = "BP-R-ERGR-M       ";
    String CPN_R_ERGR_B = "BP-R-ERGR-B       ";
    String CPN_R_EXRM = "BP-R-EXRD-M       ";
    String CPN_R_TQPH_B = "BP-R-TQPH-B      ";
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_R_TQP = "BP-R-TQP-M       ";
    String CPN_R_TQP_B = "BP-R-TQP-B        ";
    String CPN_R_TQPH = "BP-R-TQPH-M      ";
    String CPN_R_EXRD = "BP-R-EXRD-B       ";
    int K_MAX_DATE = 99991231;
    int K_MAX_TIME = 235959;
    int K_MAX_CNT = 20;
    BPZGTQP_OCCURS1[] OCCURS1 = new BPZGTQP_OCCURS1[20];
    double WS_RATE = 0;
    String WS_TENOR_CHAR = " ";
    int WS_TENOR_NUM = 0;
    int WS_TENOR_NUM1 = 0;
    int WS_TENOR_NUM2 = 0;
    int WS_TENOR_NUMO = 0;
    int WS_TENOR_NUMN = 0;
    int WS_TENOR_NUMC = 0;
    int WS_T = 0;
    int WS_S = 0;
    int WS_SS = 0;
    int WS_J = 0;
    int WS_K = 0;
    int WS_I = 0;
    int WS_TENOR_IND = 0;
    int WS_TENOR_IND1 = 0;
    String WS_ERR_MSG = " ";
    int WS_EFF_DT = 0;
    int WS_EXP_DT = 0;
    short WS_CNT = 0;
    short WS_LEN = 0;
    BPZGTQP_WS_DEC_POINT WS_DEC_POINT = new BPZGTQP_WS_DEC_POINT();
    char WS_FOUND_FLG = ' ';
    char WS_FOUND_M_FLAG = ' ';
    char WS_FOUND_L_FLAG = ' ';
    char WS_FOUND_H_FLAG = ' ';
    char WS_ALL_COMP_FLAG = ' ';
    short WS_COMP_CNT = 0;
    char WS_FIND_LAST_FLAG = ' ';
    char WS_RATE_UPD_FLAG = ' ';
    BPZGTQP_WS_DATE_AND_TIME WS_DATE_AND_TIME = new BPZGTQP_WS_DATE_AND_TIME();
    BPZGTQP_WS_ORIG WS_ORIG = new BPZGTQP_WS_ORIG();
    BPZGTQP_WS_RESULT WS_RESULT = new BPZGTQP_WS_RESULT();
    BPZGTQP_WS_COMPUTE_PARM WS_COMPUTE_PARM = new BPZGTQP_WS_COMPUTE_PARM();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCBINF SCCBINF = new SCCBINF();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPRPRMT BPRPRMT = new BPRPRMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCRTQPH BPCRTQPH = new BPCRTQPH();
    BPCTERGM BPCTERGM = new BPCTERGM();
    BPCRERGR BPCRERGR = new BPCRERGR();
    BPRTQPH BPRTQPH = new BPRTQPH();
    BPRTQPH BPRTQPHO = new BPRTQPH();
    BPRERGR BPRERGR = new BPRERGR();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPREXRT BPREXRT = new BPREXRT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPREXRD BPREXRD = new BPREXRD();
    BPCTEXRM BPCTEXRM = new BPCTEXRM();
    BPCREXRS BPCREXRS = new BPCREXRS();
    BPCTTQPM BPCTTQPM = new BPCTTQPM();
    BPCRTQPS BPCRTQPS = new BPCRTQPS();
    BPCTQPHM BPCTQPHM = new BPCTQPHM();
    BPRTQP BPRTQP = new BPRTQP();
    BPRTQP BPRTQPO = new BPRTQP();
    SCCGWA SCCGWA;
    BPCGTQP BPCGTQP;
    public BPZGTQP() {
        for (int i=0;i<20;i++) OCCURS1[i] = new BPZGTQP_OCCURS1();
    }
    public void MP(SCCGWA SCCGWA, BPCGTQP BPCGTQP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCGTQP = BPCGTQP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZGTQP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_UPDATE_REL_RATE();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_UPDATE_REL_RATE() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= K_MAX_CNT 
            && BPCGTQP.MKT_RAT_INFO[WS_I-1].EXR_TYP.trim().length() != 0; WS_I += 1) {
            B021_UPD_ARRAY_RATE();
            if (pgmRtn) return;
        }
    }
    public void B021_UPD_ARRAY_RATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BROWER CCY RULE SEQ");
        IBS.init(SCCGWA, BPRERGR);
        IBS.init(SCCGWA, BPCRERGR);
        BPRERGR.KEY.CCY = BPCGTQP.MKT_RAT_INFO[WS_I-1].CCY;
        BPRERGR.KEY.SQN = 0;
        BPCRERGR.INFO.FUNC = '1';
        S000_CALL_BPZTERGR();
        if (pgmRtn) return;
        BPCRERGR.INFO.FUNC = 'R';
        S000_CALL_BPZTERGR();
        if (pgmRtn) return;
        while (BPCRERGR.INFO.RTN_INFO != 'N') {
            CEP.TRC(SCCGWA, BPRERGR.ITP_IND);
            if (BPRERGR.ITP_IND == 'Y') {
                R000_COMPUTE_INPT_RATE();
                if (pgmRtn) return;
            } else {
                WS_RATE_UPD_FLAG = 'Y';
                CEP.TRC(SCCGWA, "GET ORI RATE");
                IBS.init(SCCGWA, BPRTQP);
                IBS.init(SCCGWA, BPCTTQPM);
                BPRTQP.KEY.EXR_TYP = BPRERGR.BASE_EXR_TYP;
                BPRTQP.KEY.FWD_TENOR = BPRERGR.BASE_FWD_TENOR;
                BPRTQP.KEY.CCY = BPRERGR.BASE_CCY;
                BPRTQP.KEY.EFF_DT = BPCGTQP.EFF_DATE;
                BPRTQP.KEY.EFF_TM = BPCGTQP.EFF_TIME;
                CEP.TRC(SCCGWA, BPRTQP.KEY);
                BPCTTQPM.INFO.FUNC = 'Q';
                BPCTTQPM.QUERY_OPTION.DBL_CHK_FLG = '1';
                S000_CALL_BPZTTQPM();
                if (pgmRtn) return;
                if (BPCTTQPM.RETURN_INFO == 'F') {
                    CEP.TRC(SCCGWA, "COMPUTE REF RATE");
                    WS_ORIG.WS_ORIG_BUY = BPRTQP.FX_BUY;
                    WS_ORIG.WS_ORIG_SELL = BPRTQP.FX_SELL;
                    WS_ORIG.WS_ORIG_MID = BPRTQP.LOC_MID;
                    WS_COMPUTE_PARM.WS_COM_SPRD_METH = BPRERGR.SPRD_METH;
                    WS_COMPUTE_PARM.WS_COM_CMP_BASE = BPRERGR.CMP_BASE;
                    WS_COMPUTE_PARM.WS_COM_CMP_FLG = BPRERGR.CMP_FLG;
                    WS_COMPUTE_PARM.WS_COM_AMEND_SP = BPRERGR.AMEND_SP;
                    R000_COMPUTE_REF_RATE();
                    if (pgmRtn) return;
                } else {
                    WS_RATE_UPD_FLAG = 'N';
                }
            }
            CEP.TRC(SCCGWA, WS_RATE_UPD_FLAG);
            R000_GET_POINT_PROCESS();
            if (pgmRtn) return;
            if (WS_RATE_UPD_FLAG == 'Y') {
                CEP.TRC(SCCGWA, "UPDATE EX RATE");
                B003_04_UPD_EX_RATE();
                if (pgmRtn) return;
            }
            BPCRERGR.INFO.FUNC = 'R';
            S000_CALL_BPZTERGR();
            if (pgmRtn) return;
        }
        BPCRERGR.INFO.FUNC = 'E';
        S000_CALL_BPZTERGR();
        if (pgmRtn) return;
    }
    public void B003_04_UPD_EX_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTQP);
        IBS.init(SCCGWA, BPCTTQPM);
        BPRTQP.KEY.EXR_TYP = BPRERGR.EXR_TYP;
        BPRTQP.KEY.FWD_TENOR = BPRERGR.FWD_TENOR;
        BPRTQP.KEY.CCY = BPRERGR.KEY.CCY;
        BPRTQP.KEY.EFF_DT = BPCGTQP.EFF_DATE;
        BPRTQP.KEY.EFF_TM = BPCGTQP.EFF_TIME;
        CEP.TRC(SCCGWA, BPRTQP.KEY);
        BPCTTQPM.INFO.FUNC = 'R';
        BPCTTQPM.QUERY_OPTION.DBL_CHK_FLG = '1';
        S000_CALL_BPZTTQPM();
        if (pgmRtn) return;
        if (BPCTTQPM.RETURN_INFO == 'F') {
            if (BPRERGR.ITP_IND == 'Y') {
                BPRTQP.FX_BUY = WS_RESULT.WS_RESULT_BUY;
                BPRTQP.FX_BUY_ORG = WS_RESULT.WS_RESULT_BUY;
                BPRTQP.FX_SELL = WS_RESULT.WS_RESULT_SELL;
                BPRTQP.FX_SELL_ORG = WS_RESULT.WS_RESULT_SELL;
                BPRTQP.CS_BUY = WS_RESULT.WS_RESULT_BUY;
                BPRTQP.CS_BUY_ORG = WS_RESULT.WS_RESULT_BUY;
                BPRTQP.CS_SELL = WS_RESULT.WS_RESULT_SELL;
                BPRTQP.CS_SELL_ORG = WS_RESULT.WS_RESULT_SELL;
                BPRTQP.LOC_MID = WS_RESULT.WS_RESULT_MID;
            } else {
                if (BPRERGR.SPRD_METH == '1') {
                    BPRTQP.FX_BUY = WS_RESULT.WS_RESULT_BUY;
                    BPRTQP.FX_BUY_ORG = WS_RESULT.WS_RESULT_BUY;
                    BPRTQP.CS_BUY = WS_RESULT.WS_RESULT_BUY;
                    BPRTQP.CS_BUY_ORG = WS_RESULT.WS_RESULT_BUY;
                } else if (BPRERGR.SPRD_METH == '2') {
                    BPRTQP.FX_SELL = WS_RESULT.WS_RESULT_SELL;
                    BPRTQP.FX_SELL_ORG = WS_RESULT.WS_RESULT_SELL;
                    BPRTQP.CS_SELL = WS_RESULT.WS_RESULT_SELL;
                    BPRTQP.CS_SELL_ORG = WS_RESULT.WS_RESULT_SELL;
                } else if (BPRERGR.SPRD_METH == '3') {
                    BPRTQP.LOC_MID = WS_RESULT.WS_RESULT_MID;
                }
            }
            BPRTQP.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRTQP.UPD_TM = SCCGWA.COMM_AREA.TR_TIME;
            BPRTQP.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRTQP.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCTTQPM.INFO.FUNC = 'U';
            S000_CALL_BPZTTQPM();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "UPDATE BPTTQP");
            IBS.init(SCCGWA, BPRTQP);
            IBS.init(SCCGWA, BPCTTQPM);
            BPRTQP.KEY.EXR_TYP = BPRERGR.EXR_TYP;
            BPRTQP.KEY.FWD_TENOR = BPRERGR.FWD_TENOR;
            BPRTQP.KEY.CCY = BPRERGR.KEY.CCY;
            BPRTQP.KEY.EFF_DT = BPCGTQP.EFF_DATE;
            BPRTQP.KEY.EFF_TM = BPCGTQP.EFF_TIME;
            BPRTQP.LOC_MID = WS_RESULT.WS_RESULT_MID;
            BPRTQP.CS_BUY = WS_RESULT.WS_RESULT_BUY;
            BPRTQP.CS_BUY_ORG = WS_RESULT.WS_RESULT_BUY;
            BPRTQP.CS_SELL = WS_RESULT.WS_RESULT_SELL;
            BPRTQP.CS_SELL_ORG = WS_RESULT.WS_RESULT_SELL;
            BPRTQP.FX_BUY = WS_RESULT.WS_RESULT_BUY;
            BPRTQP.FX_BUY_ORG = WS_RESULT.WS_RESULT_BUY;
            BPRTQP.FX_SELL = WS_RESULT.WS_RESULT_SELL;
            BPRTQP.FX_SELL_ORG = WS_RESULT.WS_RESULT_SELL;
            BPRTQP.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRTQP.UPD_TM = SCCGWA.COMM_AREA.TR_TIME;
            BPRTQP.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRTQP.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCTTQPM.INFO.FUNC = 'C';
            S000_CALL_BPZTTQPM();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "UPDATE BPTTQPH");
        CEP.TRC(SCCGWA, "FIND THE LAST & NEXT HISTORY RECORD");
        IBS.init(SCCGWA, BPRTQPH);
        IBS.init(SCCGWA, BPCRTQPH);
        BPRTQPH.KEY.EXR_TYP = BPRERGR.EXR_TYP;
        BPRTQPH.KEY.FWD_TENOR = BPRERGR.FWD_TENOR;
        BPRTQPH.KEY.CCY = BPRERGR.KEY.CCY;
        BPCRTQPH.STR_DATE = K_MAX_DATE;
        BPCRTQPH.STR_TIME = K_MAX_TIME;
        CEP.TRC(SCCGWA, BPCRTQPH.STR_DATE);
        CEP.TRC(SCCGWA, BPCRTQPH.STR_TIME);
        BPCRTQPH.INFO.FUNC = '6';
        S000_CALL_BPZTTQPH();
        if (pgmRtn) return;
        BPCRTQPH.INFO.FUNC = 'R';
        S000_CALL_BPZTTQPH();
        if (pgmRtn) return;
        WS_DATE_AND_TIME.WS_NEXT_EFF_DT = K_MAX_DATE;
        WS_DATE_AND_TIME.WS_NEXT_EFF_TM = K_MAX_TIME;
        WS_DATE_AND_TIME.WS_LAST_EXP_DT = BPCGTQP.EFF_DATE;
        WS_DATE_AND_TIME.WS_LAST_EXP_TM = BPCGTQP.EFF_TIME;
        WS_FIND_LAST_FLAG = 'N';
        while (BPCRTQPH.INFO.RTN_INFO != 'N' 
            && WS_FIND_LAST_FLAG != 'Y') {
            if (BPRTQPH.EFF_DT > BPCGTQP.EFF_DATE 
                || (BPRTQPH.EFF_DT == BPCGTQP.EFF_DATE 
                && BPRTQPH.EFF_TM > BPCGTQP.EFF_TIME)) {
                WS_DATE_AND_TIME.WS_NEXT_EFF_DT = BPRTQPH.EFF_DT;
                WS_DATE_AND_TIME.WS_NEXT_EFF_TM = BPRTQPH.EFF_TM;
            } else {
                WS_DATE_AND_TIME.WS_LAST_EXP_DT = BPRTQPH.KEY.EXP_DT;
                WS_DATE_AND_TIME.WS_LAST_EXP_TM = BPRTQPH.KEY.EXP_TM;
                WS_FIND_LAST_FLAG = 'Y';
            }
            BPCRTQPH.INFO.FUNC = 'R';
            S000_CALL_BPZTTQPH();
            if (pgmRtn) return;
        }
        BPCRTQPH.INFO.FUNC = 'E';
        S000_CALL_BPZTTQPH();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRTQPH);
        IBS.init(SCCGWA, BPCTQPHM);
        BPRTQPH.KEY.EXP_DT = WS_DATE_AND_TIME.WS_LAST_EXP_DT;
        BPRTQPH.KEY.EXP_TM = WS_DATE_AND_TIME.WS_LAST_EXP_TM;
        BPRTQPH.KEY.EXR_TYP = BPRERGR.EXR_TYP;
        BPRTQPH.KEY.FWD_TENOR = BPRERGR.FWD_TENOR;
        BPRTQPH.KEY.CCY = BPRERGR.KEY.CCY;
        BPCTQPHM.INFO.FUNC = 'R';
        S000_CALL_BPZTQPHM();
        if (pgmRtn) return;
        if (BPCTQPHM.RETURN_INFO == 'F') {
            IBS.CLONE(SCCGWA, BPRTQPH, BPRTQPHO);
            BPCTQPHM.INFO.FUNC = 'D';
            S000_CALL_BPZTQPHM();
            if (pgmRtn) return;
            if ((BPCGTQP.EFF_DATE != BPRTQPHO.EFF_DT) 
                || (BPCGTQP.EFF_TIME != BPRTQPHO.EFF_TM)) {
                IBS.CLONE(SCCGWA, BPRTQPHO, BPRTQPH);
                BPRTQPH.KEY.EXP_DT = BPCGTQP.EFF_DATE;
                BPRTQPH.KEY.EXP_TM = BPCGTQP.EFF_TIME;
                BPRTQPH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                BPRTQPH.UPD_TM = SCCGWA.COMM_AREA.TR_TIME;
                BPRTQPH.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPRTQPH.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCTQPHM.INFO.FUNC = 'C';
                S000_CALL_BPZTQPHM();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, BPRTQPH);
        IBS.init(SCCGWA, BPCTQPHM);
        BPRTQPH.KEY.EXP_DT = WS_DATE_AND_TIME.WS_NEXT_EFF_DT;
        BPRTQPH.KEY.EXP_TM = WS_DATE_AND_TIME.WS_NEXT_EFF_TM;
        BPRTQPH.KEY.EXR_TYP = BPRERGR.EXR_TYP;
        BPRTQPH.KEY.FWD_TENOR = BPRERGR.FWD_TENOR;
        BPRTQPH.KEY.CCY = BPRERGR.KEY.CCY;
        BPRTQPH.EFF_DT = BPCGTQP.EFF_DATE;
        BPRTQPH.EFF_TM = BPCGTQP.EFF_TIME;
        BPRTQPH.LOC_MID = BPRTQP.LOC_MID;
        BPRTQPH.CS_BUY = BPRTQP.FX_BUY;
        BPRTQPH.CS_BUY_ORG = BPRTQP.FX_BUY;
        BPRTQPH.CS_SELL = BPRTQP.FX_SELL;
        BPRTQPH.CS_SELL_ORG = BPRTQP.FX_SELL;
        BPRTQPH.FX_BUY = BPRTQP.FX_BUY;
        BPRTQPH.FX_BUY_ORG = BPRTQP.FX_BUY;
        BPRTQPH.FX_SELL = BPRTQP.FX_SELL;
        BPRTQPH.FX_SELL_ORG = BPRTQP.FX_SELL;
        BPRTQPH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRTQPH.UPD_TM = SCCGWA.COMM_AREA.TR_TIME;
        BPRTQPH.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRTQPH.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCTQPHM.INFO.FUNC = 'C';
        S000_CALL_BPZTQPHM();
        if (pgmRtn) return;
    }
    public void R000_COMPUTE_INPT_RATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "SEARCH BEGIN AND END RATE");
        IBS.init(SCCGWA, BPRTQPH);
        IBS.init(SCCGWA, BPCRTQPH);
        BPRTQPH.KEY.EXR_TYP = BPRERGR.EXR_TYP;
        BPRTQPH.KEY.CCY = BPRERGR.KEY.CCY;
        BPRTQPH.KEY.FWD_TENOR = "%%%";
        BPRTQPH.EFF_DT = BPCGTQP.EFF_DATE;
        BPRTQPH.EFF_TM = BPCGTQP.EFF_TIME;
        BPCRTQPH.STR_DATE = BPCGTQP.EFF_DATE;
        BPCRTQPH.STR_TIME = BPCGTQP.EFF_TIME;
        CEP.TRC(SCCGWA, BPRTQPH.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.CCY);
        CEP.TRC(SCCGWA, BPRTQPH.EFF_DT);
        CEP.TRC(SCCGWA, BPRTQPH.EFF_TM);
        CEP.TRC(SCCGWA, BPCRTQPH.STR_DATE);
        CEP.TRC(SCCGWA, BPCRTQPH.STR_TIME);
        BPCRTQPH.INFO.FUNC = '9';
        S000_CALL_BPZTTQPH();
        if (pgmRtn) return;
        BPCRTQPH.INFO.FUNC = 'R';
        S000_CALL_BPZTTQPH();
        if (pgmRtn) return;
        WS_COMPUTE_PARM.WS_COM_TENOR_L = BPRERGR.FWD_TENOR;
        WS_COMPUTE_PARM.WS_COM_TENOR_H = BPRERGR.FWD_TENOR;
        CEP.TRC(SCCGWA, WS_COMPUTE_PARM.WS_COM_TENOR_L);
        CEP.TRC(SCCGWA, WS_COMPUTE_PARM.WS_COM_TENOR_H);
        WS_FIND_LAST_FLAG = 'N';
        WS_RATE_UPD_FLAG = 'Y';
        CEP.TRC(SCCGWA, BPRTQPH.KEY.FWD_TENOR);
        CEP.TRC(SCCGWA, BPRERGR.FWD_TENOR);
        while (BPCRTQPH.INFO.RTN_INFO != 'N' 
            && WS_FIND_LAST_FLAG != 'Y') {
            if (BPRTQPH.KEY.FWD_TENOR.compareTo(BPRERGR.FWD_TENOR) > 0) {
                WS_COMPUTE_PARM.WS_COM_TENOR_H = BPRTQPH.KEY.FWD_TENOR;
                WS_ORIG.WS_ORIG_BUY2 = BPRTQPH.FX_BUY;
                WS_ORIG.WS_ORIG_SELL2 = BPRTQPH.FX_SELL;
                WS_ORIG.WS_ORIG_MID2 = BPRTQPH.LOC_MID;
                CEP.TRC(SCCGWA, WS_COMPUTE_PARM.WS_COM_TENOR_H);
                CEP.TRC(SCCGWA, WS_ORIG.WS_ORIG_BUY2);
                CEP.TRC(SCCGWA, WS_ORIG.WS_ORIG_SELL2);
                CEP.TRC(SCCGWA, WS_ORIG.WS_ORIG_MID2);
                WS_FIND_LAST_FLAG = 'Y';
            }
            CEP.TRC(SCCGWA, BPRERGR.FWD_TENOR);
            CEP.TRC(SCCGWA, BPRTQPH.KEY.FWD_TENOR);
            if (BPRTQPH.KEY.FWD_TENOR.equalsIgnoreCase(BPRERGR.FWD_TENOR)) {
                WS_RATE_UPD_FLAG = 'N';
            }
            if (BPRTQPH.KEY.FWD_TENOR.compareTo(BPRERGR.FWD_TENOR) < 0) {
                WS_COMPUTE_PARM.WS_COM_TENOR_L = BPRTQPH.KEY.FWD_TENOR;
                WS_ORIG.WS_ORIG_BUY = BPRTQPH.FX_BUY;
                WS_ORIG.WS_ORIG_SELL = BPRTQPH.FX_SELL;
                WS_ORIG.WS_ORIG_MID = BPRTQPH.LOC_MID;
                CEP.TRC(SCCGWA, WS_COMPUTE_PARM.WS_COM_TENOR_L);
                CEP.TRC(SCCGWA, WS_ORIG.WS_ORIG_BUY);
                CEP.TRC(SCCGWA, WS_ORIG.WS_ORIG_SELL);
                CEP.TRC(SCCGWA, WS_ORIG.WS_ORIG_MID);
            }
            BPCRTQPH.INFO.FUNC = 'R';
            S000_CALL_BPZTTQPH();
            if (pgmRtn) return;
        }
        BPCRTQPH.INFO.FUNC = 'E';
        S000_CALL_BPZTTQPH();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_COMPUTE_PARM.WS_COM_TENOR_L);
        CEP.TRC(SCCGWA, WS_COMPUTE_PARM.WS_COM_TENOR_H);
        CEP.TRC(SCCGWA, BPRERGR.FWD_TENOR);
        if (WS_COMPUTE_PARM.WS_COM_TENOR_L.equalsIgnoreCase(BPRERGR.FWD_TENOR) 
            || WS_COMPUTE_PARM.WS_COM_TENOR_H.equalsIgnoreCase(BPRERGR.FWD_TENOR)) {
            WS_RATE_UPD_FLAG = 'N';
        }
        CEP.TRC(SCCGWA, "COMPUTE TENOR ORDER");
        CEP.TRC(SCCGWA, WS_RATE_UPD_FLAG);
        if (WS_RATE_UPD_FLAG == 'Y') {
            IBS.init(SCCGWA, BPREXRD);
            IBS.init(SCCGWA, BPCREXRS);
            BPREXRD.KEY.EXR_TYP = BPRERGR.EXR_TYP;
            BPREXRD.KEY.FWD_TENOR = "%%%";
            BPREXRD.KEY.CCY = BPRERGR.KEY.CCY;
            CEP.TRC(SCCGWA, BPREXRD.KEY.EXR_TYP);
            CEP.TRC(SCCGWA, BPREXRD.KEY.FWD_TENOR);
            CEP.TRC(SCCGWA, BPREXRD.KEY.CCY);
            BPCREXRS.INFO.FUNC = '2';
            S000_CALL_BPZTEXRS();
            if (pgmRtn) return;
            BPCREXRS.INFO.FUNC = 'R';
            S000_CALL_BPZTEXRS();
            if (pgmRtn) return;
            WS_COMPUTE_PARM.WS_COM_ORDER_L = 0;
            WS_COMPUTE_PARM.WS_COM_ORDER_M = 0;
            WS_COMPUTE_PARM.WS_COM_ORDER_H = 0;
            WS_FIND_LAST_FLAG = 'N';
            WS_FOUND_M_FLAG = 'N';
            WS_FOUND_L_FLAG = 'N';
            WS_FOUND_H_FLAG = 'N';
            while (BPCREXRS.INFO.RTN_INFO != 'N' 
                && WS_FIND_LAST_FLAG != 'Y') {
                CEP.TRC(SCCGWA, BPREXRD.KEY.FWD_TENOR);
                CEP.TRC(SCCGWA, WS_COMPUTE_PARM.WS_COM_TENOR_L);
                if (BPREXRD.KEY.FWD_TENOR.compareTo(WS_COMPUTE_PARM.WS_COM_TENOR_L) <= 0) {
                    WS_COMPUTE_PARM.WS_COM_ORDER_L += 1;
                    if (BPREXRD.KEY.FWD_TENOR.equalsIgnoreCase(WS_COMPUTE_PARM.WS_COM_TENOR_L)) {
                        WS_FOUND_L_FLAG = 'F';
                    }
                }
                CEP.TRC(SCCGWA, BPREXRD.KEY.FWD_TENOR);
                CEP.TRC(SCCGWA, BPRERGR.FWD_TENOR);
                if (BPREXRD.KEY.FWD_TENOR.compareTo(BPRERGR.FWD_TENOR) <= 0) {
                    WS_COMPUTE_PARM.WS_COM_ORDER_M += 1;
                    if (BPREXRD.KEY.FWD_TENOR.equalsIgnoreCase(BPRERGR.FWD_TENOR)) {
                        WS_FOUND_M_FLAG = 'F';
                    }
                }
                CEP.TRC(SCCGWA, BPREXRD.KEY.FWD_TENOR);
                CEP.TRC(SCCGWA, WS_COMPUTE_PARM.WS_COM_TENOR_H);
                if (BPREXRD.KEY.FWD_TENOR.compareTo(WS_COMPUTE_PARM.WS_COM_TENOR_H) <= 0) {
                    WS_COMPUTE_PARM.WS_COM_ORDER_H += 1;
                    if (BPREXRD.KEY.FWD_TENOR.equalsIgnoreCase(WS_COMPUTE_PARM.WS_COM_TENOR_H)) {
                        WS_FIND_LAST_FLAG = 'Y';
                        WS_FOUND_H_FLAG = 'F';
                    }
                }
                BPCREXRS.INFO.FUNC = 'R';
                S000_CALL_BPZTEXRS();
                if (pgmRtn) return;
            }
            BPCREXRS.INFO.FUNC = 'E';
            S000_CALL_BPZTEXRS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_COMPUTE_PARM.WS_COM_ORDER_L);
            CEP.TRC(SCCGWA, WS_COMPUTE_PARM.WS_COM_ORDER_M);
            CEP.TRC(SCCGWA, WS_COMPUTE_PARM.WS_COM_ORDER_M);
            CEP.TRC(SCCGWA, WS_COMPUTE_PARM.WS_COM_ORDER_H);
            CEP.TRC(SCCGWA, WS_FOUND_M_FLAG);
            CEP.TRC(SCCGWA, WS_FOUND_H_FLAG);
            CEP.TRC(SCCGWA, WS_FOUND_L_FLAG);
            if (WS_FOUND_M_FLAG == 'N' 
                || WS_FOUND_L_FLAG == 'N' 
                || WS_FOUND_H_FLAG == 'N' 
                || (WS_COMPUTE_PARM.WS_COM_ORDER_L == WS_COMPUTE_PARM.WS_COM_ORDER_H) 
                || (WS_COMPUTE_PARM.WS_COM_ORDER_L == WS_COMPUTE_PARM.WS_COM_ORDER_M) 
                || (WS_COMPUTE_PARM.WS_COM_ORDER_M == WS_COMPUTE_PARM.WS_COM_ORDER_H)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EX_RATE_UNDEFINE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_TENOR_NUMO = WS_COMPUTE_PARM.WS_COM_ORDER_H - WS_COMPUTE_PARM.WS_COM_ORDER_L;
            CEP.TRC(SCCGWA, WS_TENOR_NUMO);
            CEP.TRC(SCCGWA, WS_COMPUTE_PARM.WS_COM_ORDER_H);
            CEP.TRC(SCCGWA, WS_COMPUTE_PARM.WS_COM_ORDER_L);
            CEP.TRC(SCCGWA, WS_TENOR_NUMO);
            WS_TENOR_NUMC = WS_COMPUTE_PARM.WS_COM_ORDER_M - WS_COMPUTE_PARM.WS_COM_ORDER_L;
            CEP.TRC(SCCGWA, WS_TENOR_NUMC);
            CEP.TRC(SCCGWA, WS_COMPUTE_PARM.WS_COM_ORDER_M);
            CEP.TRC(SCCGWA, WS_COMPUTE_PARM.WS_COM_ORDER_L);
            WS_RESULT.WS_RESULT_BUY = WS_ORIG.WS_ORIG_BUY + ( WS_ORIG.WS_ORIG_BUY2 - WS_ORIG.WS_ORIG_BUY ) * WS_TENOR_NUMC / WS_TENOR_NUMO;
            CEP.TRC(SCCGWA, WS_RESULT.WS_RESULT_BUY);
            CEP.TRC(SCCGWA, WS_ORIG.WS_ORIG_BUY);
            CEP.TRC(SCCGWA, WS_ORIG.WS_ORIG_BUY2);
            CEP.TRC(SCCGWA, WS_ORIG.WS_ORIG_BUY);
            CEP.TRC(SCCGWA, WS_TENOR_NUMC);
            CEP.TRC(SCCGWA, WS_TENOR_NUMO);
            WS_RESULT.WS_RESULT_SELL = WS_ORIG.WS_ORIG_SELL + ( WS_ORIG.WS_ORIG_SELL2 - WS_ORIG.WS_ORIG_SELL ) * WS_TENOR_NUMC / WS_TENOR_NUMO;
            CEP.TRC(SCCGWA, WS_RESULT.WS_RESULT_SELL);
            CEP.TRC(SCCGWA, WS_ORIG.WS_ORIG_SELL);
            CEP.TRC(SCCGWA, WS_ORIG.WS_ORIG_SELL2);
            CEP.TRC(SCCGWA, WS_ORIG.WS_ORIG_SELL);
            CEP.TRC(SCCGWA, WS_TENOR_NUMC);
            CEP.TRC(SCCGWA, WS_TENOR_NUMO);
            WS_RESULT.WS_RESULT_MID = WS_ORIG.WS_ORIG_MID + ( WS_ORIG.WS_ORIG_MID2 - WS_ORIG.WS_ORIG_MID ) * WS_TENOR_NUMC / WS_TENOR_NUMO;
            CEP.TRC(SCCGWA, WS_RESULT.WS_RESULT_MID);
            CEP.TRC(SCCGWA, WS_ORIG.WS_ORIG_MID);
            CEP.TRC(SCCGWA, WS_ORIG.WS_ORIG_MID2);
            CEP.TRC(SCCGWA, WS_ORIG.WS_ORIG_MID);
            CEP.TRC(SCCGWA, WS_TENOR_NUMC);
            CEP.TRC(SCCGWA, WS_TENOR_NUMO);
            CEP.TRC(SCCGWA, BPZGTQP_WS3);
        }
    }
    public void R000_COMPUTE_REF_RATE() throws IOException,SQLException,Exception {
        if (WS_COMPUTE_PARM.WS_COM_CMP_BASE.equalsIgnoreCase("01")) {
            WS_ORIG.WS_ORIG_RATE = WS_ORIG.WS_ORIG_MID;
        } else if (WS_COMPUTE_PARM.WS_COM_CMP_BASE.equalsIgnoreCase("02")) {
            WS_ORIG.WS_ORIG_RATE = WS_ORIG.WS_ORIG_BUY;
        } else if (WS_COMPUTE_PARM.WS_COM_CMP_BASE.equalsIgnoreCase("03")) {
            WS_ORIG.WS_ORIG_RATE = WS_ORIG.WS_ORIG_SELL;
        }
        if (WS_COMPUTE_PARM.WS_COM_CMP_FLG == '0') {
            WS_ORIG.WS_ORIG_RATE = WS_ORIG.WS_ORIG_RATE * ( 1 + WS_COMPUTE_PARM.WS_COM_AMEND_SP / 100 );
        } else if (WS_COMPUTE_PARM.WS_COM_CMP_FLG == '1') {
            WS_ORIG.WS_ORIG_RATE = WS_ORIG.WS_ORIG_RATE + WS_COMPUTE_PARM.WS_COM_AMEND_SP;
        }
        CEP.TRC(SCCGWA, BPCGTQP.MKT_RAT_INFO[WS_I-1].BUY_RAT);
        if (WS_COMPUTE_PARM.WS_COM_SPRD_METH == '1') {
            WS_RESULT.WS_RESULT_BUY = WS_ORIG.WS_ORIG_RATE;
        }
        CEP.TRC(SCCGWA, BPCGTQP.MKT_RAT_INFO[WS_I-1].SELL_RAT);
        if (WS_COMPUTE_PARM.WS_COM_SPRD_METH == '2') {
            WS_RESULT.WS_RESULT_SELL = WS_ORIG.WS_ORIG_RATE;
        }
        if (WS_COMPUTE_PARM.WS_COM_SPRD_METH == '3') {
            WS_RESULT.WS_RESULT_MID = WS_ORIG.WS_ORIG_RATE;
        }
        CEP.TRC(SCCGWA, BPCGTQP.MKT_RAT_INFO[WS_I-1].EXR_TYP);
        CEP.TRC(SCCGWA, BPCGTQP.MKT_RAT_INFO[WS_I-1].TENOR);
        CEP.TRC(SCCGWA, BPCGTQP.MKT_RAT_INFO[WS_I-1].CCY);
        CEP.TRC(SCCGWA, BPRERGR.EXR_TYP);
        CEP.TRC(SCCGWA, BPRERGR.FWD_TENOR);
        CEP.TRC(SCCGWA, BPRERGR.KEY.CCY);
        if (BPCGTQP.MKT_RAT_INFO[WS_I-1].EXR_TYP.equalsIgnoreCase(BPRERGR.EXR_TYP) 
            && BPCGTQP.MKT_RAT_INFO[WS_I-1].TENOR.equalsIgnoreCase(BPRERGR.FWD_TENOR) 
            && BPCGTQP.MKT_RAT_INFO[WS_I-1].CCY.equalsIgnoreCase(BPRERGR.KEY.CCY)) {
            if (WS_COMPUTE_PARM.WS_COM_SPRD_METH == '1' 
                && BPCGTQP.MKT_RAT_INFO[WS_I-1].BUY_RAT != 0) {
                WS_RESULT.WS_RESULT_BUY = BPCGTQP.MKT_RAT_INFO[WS_I-1].BUY_RAT;
            }
            if (WS_COMPUTE_PARM.WS_COM_SPRD_METH == '2' 
                && BPCGTQP.MKT_RAT_INFO[WS_I-1].SELL_RAT != 0) {
                WS_RESULT.WS_RESULT_SELL = BPCGTQP.MKT_RAT_INFO[WS_I-1].SELL_RAT;
            }
            if (WS_COMPUTE_PARM.WS_COM_SPRD_METH == '3' 
                && BPCGTQP.MKT_RAT_INFO[WS_I-1].MID_RAT != 0) {
                WS_RESULT.WS_RESULT_MID = BPCGTQP.MKT_RAT_INFO[WS_I-1].MID_RAT;
            }
        }
        CEP.TRC(SCCGWA, WS_RESULT.WS_RESULT_BUY);
        CEP.TRC(SCCGWA, WS_RESULT.WS_RESULT_SELL);
        CEP.TRC(SCCGWA, WS_RESULT.WS_RESULT_MID);
    }
    public void R000_GET_POINT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREXRD);
        IBS.init(SCCGWA, BPCTEXRM);
        BPREXRD.KEY.EXR_TYP = BPCGTQP.MKT_RAT_INFO[WS_I-1].EXR_TYP;
        BPREXRD.KEY.FWD_TENOR = BPCGTQP.MKT_RAT_INFO[WS_I-1].TENOR;
        BPREXRD.KEY.CCY = BPCGTQP.MKT_RAT_INFO[WS_I-1].CCY;
        BPREXRD.KEY.CORR_CCY = BPCGTQP.MKT_RAT_INFO[WS_I-1].CORR_CCY;
        BPCTEXRM.INFO.FUNC = 'Q';
        S000_CALL_BPZTEXRM();
        if (pgmRtn) return;
        if (BPCTEXRM.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EX_RATE_UNDEFINE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPREXRD.EXR_PNT);
        CEP.TRC(SCCGWA, BPREXRD.EXR_RND);
        if (BPREXRD.EXR_PNT == '0') {
            WS_DEC_POINT.WS_DEC_MREM = WS_RESULT.WS_RESULT_MID % 1;
            WS_DEC_POINT.WS_DEC_MP0 = (int) ((WS_RESULT.WS_RESULT_MID - WS_DEC_POINT.WS_DEC_MREM) / 1);
            WS_DEC_POINT.WS_DEC_BREM = WS_RESULT.WS_RESULT_BUY % 1;
            WS_DEC_POINT.WS_DEC_BP0 = (int) ((WS_RESULT.WS_RESULT_BUY - WS_DEC_POINT.WS_DEC_BREM) / 1);
            WS_DEC_POINT.WS_DEC_SREM = WS_RESULT.WS_RESULT_SELL % 1;
            WS_DEC_POINT.WS_DEC_SP0 = (int) ((WS_RESULT.WS_RESULT_SELL - WS_DEC_POINT.WS_DEC_SREM) / 1);
            if (BPREXRD.EXR_RND == '1') {
                WS_DEC_POINT.WS_DEC_MP0 += 1;
                WS_DEC_POINT.WS_DEC_BP0 += 1;
                WS_DEC_POINT.WS_DEC_SP0 += 1;
            }
            if (BPREXRD.EXR_RND == '2') {
                if (WS_DEC_POINT.WS_DEC_MREM >= .5) {
                    WS_DEC_POINT.WS_DEC_MP0 += 1;
                }
                if (WS_DEC_POINT.WS_DEC_BREM >= .5) {
                    WS_DEC_POINT.WS_DEC_BP0 += 1;
                }
                if (WS_DEC_POINT.WS_DEC_SREM >= .5) {
                    WS_DEC_POINT.WS_DEC_SP0 += 1;
                }
            }
            WS_RESULT.WS_RESULT_MID = (double)WS_DEC_POINT.WS_DEC_MP0;
            WS_RESULT.WS_RESULT_BUY = (double)WS_DEC_POINT.WS_DEC_BP0;
            WS_RESULT.WS_RESULT_SELL = (double)WS_DEC_POINT.WS_DEC_SP0;
        } else if (BPREXRD.EXR_PNT == '1') {
            WS_DEC_POINT.WS_DEC_MREM = WS_RESULT.WS_RESULT_MID % 1;
            WS_DEC_POINT.WS_DEC_MP1 = (WS_RESULT.WS_RESULT_MID - WS_DEC_POINT.WS_DEC_MREM) / 1;
            WS_DEC_POINT.WS_DEC_BREM = WS_RESULT.WS_RESULT_BUY % 1;
            WS_DEC_POINT.WS_DEC_BP1 = (WS_RESULT.WS_RESULT_BUY - WS_DEC_POINT.WS_DEC_BREM) / 1;
            WS_DEC_POINT.WS_DEC_SREM = WS_RESULT.WS_RESULT_SELL % 1;
            WS_DEC_POINT.WS_DEC_SP1 = (WS_RESULT.WS_RESULT_SELL - WS_DEC_POINT.WS_DEC_SREM) / 1;
            if (BPREXRD.EXR_RND == '1') {
                WS_DEC_POINT.WS_DEC_MP1 += .1;
                WS_DEC_POINT.WS_DEC_BP1 += .1;
                WS_DEC_POINT.WS_DEC_SP1 += .1;
            }
            if (BPREXRD.EXR_RND == '2') {
                if (WS_DEC_POINT.WS_DEC_MREM >= .05) {
                    WS_DEC_POINT.WS_DEC_MP1 += .1;
                }
                if (WS_DEC_POINT.WS_DEC_BREM >= .05) {
                    WS_DEC_POINT.WS_DEC_BP1 += .1;
                }
                if (WS_DEC_POINT.WS_DEC_SREM >= .05) {
                    WS_DEC_POINT.WS_DEC_SP1 += .1;
                }
            }
            WS_RESULT.WS_RESULT_MID = WS_DEC_POINT.WS_DEC_MP1;
            WS_RESULT.WS_RESULT_BUY = WS_DEC_POINT.WS_DEC_BP1;
            WS_RESULT.WS_RESULT_SELL = WS_DEC_POINT.WS_DEC_SP1;
        } else if (BPREXRD.EXR_PNT == '2') {
            WS_DEC_POINT.WS_DEC_MREM = WS_RESULT.WS_RESULT_MID % 1;
            WS_DEC_POINT.WS_DEC_MP2 = (WS_RESULT.WS_RESULT_MID - WS_DEC_POINT.WS_DEC_MREM) / 1;
            WS_DEC_POINT.WS_DEC_BREM = WS_RESULT.WS_RESULT_BUY % 1;
            WS_DEC_POINT.WS_DEC_BP2 = (WS_RESULT.WS_RESULT_BUY - WS_DEC_POINT.WS_DEC_BREM) / 1;
            WS_DEC_POINT.WS_DEC_SREM = WS_RESULT.WS_RESULT_SELL % 1;
            WS_DEC_POINT.WS_DEC_SP2 = (WS_RESULT.WS_RESULT_SELL - WS_DEC_POINT.WS_DEC_SREM) / 1;
            if (BPREXRD.EXR_RND == '1') {
                WS_DEC_POINT.WS_DEC_MP2 += .01;
                WS_DEC_POINT.WS_DEC_BP2 += .01;
                WS_DEC_POINT.WS_DEC_SP2 += .01;
            }
            if (BPREXRD.EXR_RND == '2') {
                if (WS_DEC_POINT.WS_DEC_MREM >= .005) {
                    WS_DEC_POINT.WS_DEC_MP2 += .01;
                }
                if (WS_DEC_POINT.WS_DEC_BREM >= .005) {
                    WS_DEC_POINT.WS_DEC_BP2 += .01;
                }
                if (WS_DEC_POINT.WS_DEC_SREM >= .005) {
                    WS_DEC_POINT.WS_DEC_SP2 += .01;
                }
            }
            WS_RESULT.WS_RESULT_MID = WS_DEC_POINT.WS_DEC_MP2;
            WS_RESULT.WS_RESULT_BUY = WS_DEC_POINT.WS_DEC_BP2;
            WS_RESULT.WS_RESULT_SELL = WS_DEC_POINT.WS_DEC_SP2;
        } else if (BPREXRD.EXR_PNT == '3') {
            WS_DEC_POINT.WS_DEC_MREM = WS_RESULT.WS_RESULT_MID % 1;
            WS_DEC_POINT.WS_DEC_MP3 = (WS_RESULT.WS_RESULT_MID - WS_DEC_POINT.WS_DEC_MREM) / 1;
            WS_DEC_POINT.WS_DEC_BREM = WS_RESULT.WS_RESULT_BUY % 1;
            WS_DEC_POINT.WS_DEC_BP3 = (WS_RESULT.WS_RESULT_BUY - WS_DEC_POINT.WS_DEC_BREM) / 1;
            WS_DEC_POINT.WS_DEC_SREM = WS_RESULT.WS_RESULT_SELL % 1;
            WS_DEC_POINT.WS_DEC_SP3 = (WS_RESULT.WS_RESULT_SELL - WS_DEC_POINT.WS_DEC_SREM) / 1;
            if (BPREXRD.EXR_RND == '1') {
                WS_DEC_POINT.WS_DEC_MP3 += .001;
                WS_DEC_POINT.WS_DEC_BP3 += .001;
                WS_DEC_POINT.WS_DEC_SP3 += .001;
            }
            if (BPREXRD.EXR_RND == '2') {
                if (WS_DEC_POINT.WS_DEC_MREM >= .0005) {
                    WS_DEC_POINT.WS_DEC_MP3 += .001;
                }
                if (WS_DEC_POINT.WS_DEC_BREM >= .0005) {
                    WS_DEC_POINT.WS_DEC_BP3 += .001;
                }
                if (WS_DEC_POINT.WS_DEC_SREM >= .0005) {
                    WS_DEC_POINT.WS_DEC_SP3 += .001;
                }
            }
            WS_RESULT.WS_RESULT_MID = WS_DEC_POINT.WS_DEC_MP3;
            WS_RESULT.WS_RESULT_BUY = WS_DEC_POINT.WS_DEC_BP3;
            WS_RESULT.WS_RESULT_SELL = WS_DEC_POINT.WS_DEC_SP3;
        } else if (BPREXRD.EXR_PNT == '4') {
            WS_DEC_POINT.WS_DEC_MREM = WS_RESULT.WS_RESULT_MID % 1;
            WS_DEC_POINT.WS_DEC_MP4 = (WS_RESULT.WS_RESULT_MID - WS_DEC_POINT.WS_DEC_MREM) / 1;
            WS_DEC_POINT.WS_DEC_BREM = WS_RESULT.WS_RESULT_BUY % 1;
            WS_DEC_POINT.WS_DEC_BP4 = (WS_RESULT.WS_RESULT_BUY - WS_DEC_POINT.WS_DEC_BREM) / 1;
            WS_DEC_POINT.WS_DEC_SREM = WS_RESULT.WS_RESULT_SELL % 1;
            WS_DEC_POINT.WS_DEC_SP4 = (WS_RESULT.WS_RESULT_SELL - WS_DEC_POINT.WS_DEC_SREM) / 1;
            if (BPREXRD.EXR_RND == '1') {
                WS_DEC_POINT.WS_DEC_MP4 += .0001;
                WS_DEC_POINT.WS_DEC_BP4 += .0001;
                WS_DEC_POINT.WS_DEC_SP4 += .0001;
            }
            if (BPREXRD.EXR_RND == '2') {
                if (WS_DEC_POINT.WS_DEC_MREM >= .00005) {
                    WS_DEC_POINT.WS_DEC_MP4 += .0001;
                }
                if (WS_DEC_POINT.WS_DEC_BREM >= .00005) {
                    WS_DEC_POINT.WS_DEC_BP4 += .0001;
                }
                if (WS_DEC_POINT.WS_DEC_SREM >= .00005) {
                    WS_DEC_POINT.WS_DEC_SP4 += .0001;
                }
            }
            WS_RESULT.WS_RESULT_MID = WS_DEC_POINT.WS_DEC_MP4;
            WS_RESULT.WS_RESULT_BUY = WS_DEC_POINT.WS_DEC_BP4;
            WS_RESULT.WS_RESULT_SELL = WS_DEC_POINT.WS_DEC_SP4;
        } else if (BPREXRD.EXR_PNT == '5') {
            WS_DEC_POINT.WS_DEC_MREM = WS_RESULT.WS_RESULT_MID % 1;
            WS_DEC_POINT.WS_DEC_MP5 = (WS_RESULT.WS_RESULT_MID - WS_DEC_POINT.WS_DEC_MREM) / 1;
            WS_DEC_POINT.WS_DEC_BREM = WS_RESULT.WS_RESULT_BUY % 1;
            WS_DEC_POINT.WS_DEC_BP5 = (WS_RESULT.WS_RESULT_BUY - WS_DEC_POINT.WS_DEC_BREM) / 1;
            WS_DEC_POINT.WS_DEC_SREM = WS_RESULT.WS_RESULT_SELL % 1;
            WS_DEC_POINT.WS_DEC_SP5 = (WS_RESULT.WS_RESULT_SELL - WS_DEC_POINT.WS_DEC_SREM) / 1;
            if (BPREXRD.EXR_RND == '1') {
                WS_DEC_POINT.WS_DEC_MP5 += .00001;
                WS_DEC_POINT.WS_DEC_BP5 += .00001;
                WS_DEC_POINT.WS_DEC_SP5 += .00001;
            }
            if (BPREXRD.EXR_RND == '2') {
                if (WS_DEC_POINT.WS_DEC_MREM >= .000005) {
                    WS_DEC_POINT.WS_DEC_MP5 += .00001;
                }
                if (WS_DEC_POINT.WS_DEC_BREM >= .000005) {
                    WS_DEC_POINT.WS_DEC_BP5 += .00001;
                }
                if (WS_DEC_POINT.WS_DEC_SREM >= .000005) {
                    WS_DEC_POINT.WS_DEC_SP5 += .00001;
                }
            }
            WS_RESULT.WS_RESULT_MID = WS_DEC_POINT.WS_DEC_MP5;
            WS_RESULT.WS_RESULT_BUY = WS_DEC_POINT.WS_DEC_BP5;
            WS_RESULT.WS_RESULT_SELL = WS_DEC_POINT.WS_DEC_SP5;
        } else if (BPREXRD.EXR_PNT == '6') {
            WS_DEC_POINT.WS_DEC_MREM = WS_RESULT.WS_RESULT_MID % 1;
            WS_DEC_POINT.WS_DEC_MP6 = (WS_RESULT.WS_RESULT_MID - WS_DEC_POINT.WS_DEC_MREM) / 1;
            WS_DEC_POINT.WS_DEC_BREM = WS_RESULT.WS_RESULT_BUY % 1;
            WS_DEC_POINT.WS_DEC_BP6 = (WS_RESULT.WS_RESULT_BUY - WS_DEC_POINT.WS_DEC_BREM) / 1;
            WS_DEC_POINT.WS_DEC_SREM = WS_RESULT.WS_RESULT_SELL % 1;
            WS_DEC_POINT.WS_DEC_SP6 = (WS_RESULT.WS_RESULT_SELL - WS_DEC_POINT.WS_DEC_SREM) / 1;
            if (BPREXRD.EXR_RND == '1') {
                WS_DEC_POINT.WS_DEC_MP6 += .000001;
                WS_DEC_POINT.WS_DEC_BP6 += .000001;
                WS_DEC_POINT.WS_DEC_SP6 += .000001;
            }
            if (BPREXRD.EXR_RND == '2') {
                if (WS_DEC_POINT.WS_DEC_MREM >= .0000005) {
                    WS_DEC_POINT.WS_DEC_MP6 += .000001;
                }
                if (WS_DEC_POINT.WS_DEC_BREM >= .0000005) {
                    WS_DEC_POINT.WS_DEC_BP6 += .000001;
                }
                if (WS_DEC_POINT.WS_DEC_SREM >= .0000005) {
                    WS_DEC_POINT.WS_DEC_SP6 += .000001;
                }
            }
            WS_RESULT.WS_RESULT_MID = WS_DEC_POINT.WS_DEC_MP6;
            WS_RESULT.WS_RESULT_BUY = WS_DEC_POINT.WS_DEC_BP6;
            WS_RESULT.WS_RESULT_SELL = WS_DEC_POINT.WS_DEC_SP6;
        } else if (BPREXRD.EXR_PNT == '7') {
            WS_DEC_POINT.WS_DEC_MREM = WS_RESULT.WS_RESULT_MID % 1;
            WS_DEC_POINT.WS_DEC_MP7 = (WS_RESULT.WS_RESULT_MID - WS_DEC_POINT.WS_DEC_MREM) / 1;
            WS_DEC_POINT.WS_DEC_BREM = WS_RESULT.WS_RESULT_BUY % 1;
            WS_DEC_POINT.WS_DEC_BP7 = (WS_RESULT.WS_RESULT_BUY - WS_DEC_POINT.WS_DEC_BREM) / 1;
            WS_DEC_POINT.WS_DEC_SREM = WS_RESULT.WS_RESULT_SELL % 1;
            WS_DEC_POINT.WS_DEC_SP7 = (WS_RESULT.WS_RESULT_SELL - WS_DEC_POINT.WS_DEC_SREM) / 1;
            if (BPREXRD.EXR_RND == '1') {
                WS_DEC_POINT.WS_DEC_MP7 += .0000001;
                WS_DEC_POINT.WS_DEC_BP7 += .0000001;
                WS_DEC_POINT.WS_DEC_SP7 += .0000001;
            }
            if (BPREXRD.EXR_RND == '2') {
                if (WS_DEC_POINT.WS_DEC_MREM >= .00000005) {
                    WS_DEC_POINT.WS_DEC_MP7 += .0000001;
                }
                if (WS_DEC_POINT.WS_DEC_BREM >= .00000005) {
                    WS_DEC_POINT.WS_DEC_BP7 += .0000001;
                }
                if (WS_DEC_POINT.WS_DEC_SREM >= .00000005) {
                    WS_DEC_POINT.WS_DEC_SP7 += .0000001;
                }
            }
            WS_RESULT.WS_RESULT_MID = WS_DEC_POINT.WS_DEC_MP7;
            WS_RESULT.WS_RESULT_BUY = WS_DEC_POINT.WS_DEC_BP7;
            WS_RESULT.WS_RESULT_SELL = WS_DEC_POINT.WS_DEC_SP7;
        } else if (BPREXRD.EXR_PNT == '8'
            || BPREXRD.EXR_PNT == '9') {
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_DATA_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_RESULT.WS_RESULT_MID);
        CEP.TRC(SCCGWA, WS_RESULT.WS_RESULT_BUY);
        CEP.TRC(SCCGWA, WS_RESULT.WS_RESULT_SELL);
    }
    public void R000_CHECK_FWD_TENOR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_FWD_TENOR;
        BPCOQPCD.INPUT_DATA.CODE = BPCGTQP.MKT_RAT_INFO[1-1].TENOR;
        IBS.CALLCPN(SCCGWA, CPN_INQ_PUB_CODE, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTERGM() throws IOException,SQLException,Exception {
        BPCTERGM.INFO.POINTER = BPRERGR;
        BPCTERGM.INFO.LEN = 122;
        IBS.CALLCPN(SCCGWA, CPN_R_ERGR_M, BPCTERGM);
        if (BPCTERGM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTERGM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTERGR() throws IOException,SQLException,Exception {
        BPCRERGR.INFO.POINTER = BPRERGR;
        BPCRERGR.INFO.LEN = 122;
        IBS.CALLCPN(SCCGWA, CPN_R_ERGR_B, BPCRERGR);
        if (BPCRERGR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRERGR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTEXRM() throws IOException,SQLException,Exception {
        BPCTEXRM.POINTER = BPREXRD;
        BPCTEXRM.LEN = 259;
        IBS.CALLCPN(SCCGWA, "BP-R-EXRD-M ", BPCTEXRM);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ        ", BPCPRMR);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            IBS.init(SCCGWA, SCCBINF);
            SCCBINF.ERR_TYPE = 'P';
            SCCBINF.ERR_ACTION = 'E';
            SCCBINF.ERR_NAME = "BPZPRMR ";
            SCCBINF.OTHER_INFO = "CALL BPZPRMR ERROR!";
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTTQPH() throws IOException,SQLException,Exception {
        BPCRTQPH.INFO.POINTER = BPRTQPH;
        BPCRTQPH.INFO.LEN = 412;
        IBS.CALLCPN(SCCGWA, CPN_R_TQPH_B, BPCRTQPH);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.FWD_TENOR);
        CEP.TRC(SCCGWA, BPCRTQPH.RC);
        CEP.TRC(SCCGWA, BPCRTQPH.INFO.RTN_INFO);
        if (BPCRTQPH.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTQPH.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTEXRS() throws IOException,SQLException,Exception {
        BPCREXRS.INFO.POINTER = BPREXRD;
        BPCREXRS.INFO.LEN = 259;
        IBS.CALLCPN(SCCGWA, CPN_R_EXRD, BPCREXRS);
    }
    public void S000_CALL_BPZTTQPM() throws IOException,SQLException,Exception {
        BPCTTQPM.POINTER = BPRTQP;
        BPCTTQPM.LEN = 401;
        IBS.CALLCPN(SCCGWA, CPN_R_TQP, BPCTTQPM);
    }
    public void S000_CALL_BPZRTQPS() throws IOException,SQLException,Exception {
        BPCRTQPS.INFO.POINTER = BPRTQP;
        BPCRTQPS.INFO.LEN = 401;
        IBS.CALLCPN(SCCGWA, CPN_R_TQP_B, BPCRTQPS);
    }
    public void S000_CALL_BPZTQPHM() throws IOException,SQLException,Exception {
        BPCTQPHM.INFO.POINTER = BPRTQPH;
        BPCTQPHM.INFO.LEN = 412;
        IBS.CALLCPN(SCCGWA, CPN_R_TQPH, BPCTQPHM);
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCGTQP.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCGTQP = ");
            CEP.TRC(SCCGWA, BPCGTQP);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
