package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class LNZRATG {
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTRCVD_RD;
    int JIBS_tmp_int;
    BigDecimal bigD;
    DBParm LNTRATH_RD;
    brParm LNTRATH_BR = new brParm();
    DBParm LNTRATN_RD;
    brParm LNTRATN_BR = new brParm();
    brParm LNTRATL_BR = new brParm();
    boolean pgmRtn = false;
    String SCSSCLDT = "SCSSCLDT";
    char UNIT_D = 'D';
    char UNIT_M = 'M';
    char UNIT_W = 'W';
    char UNIT_B = 'B';
    char UNIT_Q = 'Q';
    char UNIT_H = 'H';
    char UNIT_Y = 'Y';
    LNZRATG_WS_VARIABLES WS_VARIABLES = new LNZRATG_WS_VARIABLES();
    LNZRATG_WS_PRIME_CODE WS_PRIME_CODE = new LNZRATG_WS_PRIME_CODE();
    LNZRATG_WS_COND_FLG WS_COND_FLG = new LNZRATG_WS_COND_FLG();
    LNRRCVD LNRRCVD = new LNRRCVD();
    LNRRATN LNRRATN = new LNRRATN();
    LNRRATL LNRRATL = new LNRRATL();
    LNRRATH LNRRATH = new LNRRATH();
    LNCMSG_ERROR_MSG ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCPPMQ LNCPPMQ = new LNCPPMQ();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCRATNM LNCRATNM = new LNCRATNM();
    LNCRATLM LNCRATLM = new LNCRATLM();
    LNCRATHM LNCRATHM = new LNCRATHM();
    BPCCINTI BPCCINTI = new BPCCINTI();
    BPCUINTI BPCUINTI = new BPCUINTI();
    SCCMSG SCCMSG = new SCCMSG();
    SCRJPRM SCRJPRM = new SCRJPRM();
    SCCGWA SCCGWA;
    LNCRATG LNCRATG;
    SCCGSCA_SC_AREA SC_AREA;
    SCRCWAT SCRCWAT;
    SCCGBPA_BP_AREA BP_AREA;
    SCCBATH SCCBATH;
    SCCGMSG SCCGMSG;
    public void MP(SCCGWA SCCGWA, LNCRATG LNCRATG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCRATG = LNCRATG;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZRATG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_COND_FLG);
        IBS.init(SCCGWA, BPCUINTI);
        IBS.init(SCCGWA, BPCCINTI);
        LNCRATG.RC.RC_APP = "LN";
        LNCRATG.RC.RC_RTNCODE = 0;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCRCWAT = (SCRCWAT) SC_AREA.CWA_AREA_PTR;
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
            WS_VARIABLES.GWA_AC_DATE = SCCBATH.JPRM.NEXT_AC_DATB;
        } else {
            WS_VARIABLES.GWA_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        CEP.TRC(SCCGWA, SCRCWAT.AC_DATE_AREA[2-1].NEXT_AC_DATE);
        CEP.TRC(SCCGWA, LNCRATG.COMM_DATA.FUNC_CODE);
        CEP.TRC(SCCGWA, LNCRATG.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCRATG.COMM_DATA.RATE_TYPE);
        CEP.TRC(SCCGWA, LNCRATG.COMM_DATA.VAL_DATE);
        CEP.TRC(SCCGWA, WS_VARIABLES.GWA_AC_DATE);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B100_DEL_OLD_RATH();
        if (pgmRtn) return;
        B200_GET_LOAN_INFO();
        if (pgmRtn) return;
        if (LNCRATG.COMM_DATA.FUNC_CODE != 'D') {
            B300_MAIN_PROC();
            if (pgmRtn) return;
        }
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "ATG-ERR");
        CEP.TRC(SCCGWA, LNCRATG.COMM_DATA.FUNC_CODE);
        if (LNCRATG.COMM_DATA.FUNC_CODE != 'I' 
            && LNCRATG.COMM_DATA.FUNC_CODE != 'U' 
            && LNCRATG.COMM_DATA.FUNC_CODE != 'D') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_FUNC_CODE, LNCRATG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCRATG.COMM_DATA.RATE_TYPE != 'N' 
            && LNCRATG.COMM_DATA.RATE_TYPE != 'O' 
            && LNCRATG.COMM_DATA.RATE_TYPE != 'L' 
            && LNCRATG.COMM_DATA.RATE_TYPE != 'P') {
            CEP.TRC(SCCGWA, "TYPE-ERR");
            CEP.TRC(SCCGWA, LNCRATG.COMM_DATA.RATE_TYPE);
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_FUNC_CODE, LNCRATG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCRATG.COMM_DATA.LN_AC.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_MUST_INPUT, LNCRATG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_GET_LOAN_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCRATG.COMM_DATA.LN_AC;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        WS_PRIME_CODE.RAT_CCY = LNCCONTM.REC_DATA.CCY;
        IBS.init(SCCGWA, LNCPPMQ);
        LNCPPMQ.KEY.REQ_INSTRUCT.REQ_MODULE_PARM = 'N';
        LNCPPMQ.KEY.REQ_INSTRUCT.REQ_COMMIT_PARM = 'N';
        LNCPPMQ.KEY.REQ_INSTRUCT.REQ_CONTRACT_PARM = 'Y';
        LNCPPMQ.KEY.KEY_METH = 'C';
        LNCPPMQ.KEY.KEY_VALUE.LEVEL = 'D';
        LNCPPMQ.KEY.KEY_VALUE.CONTRACT_NO = LNCCONTM.REC_DATA.KEY.CONTRACT_NO;
        S000_CALL_LNZPPMQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCPPMQ.DATA_CONT_SPC.CONT_PAY_MTH);
        CEP.TRC(SCCGWA, LNCPPMQ.DATA_CONT_SPC.CONT_INST_MTH);
        B110_GET_ICTL_INFO();
        if (pgmRtn) return;
        B110_GET_OLDRATE_INFO();
        if (pgmRtn) return;
        if ((LNCRATG.COMM_DATA.VAL_DATE < LNCCONTM.REC_DATA.START_DATE) 
            && (LNCRATG.COMM_DATA.FUNC_CODE != 'D')) {
            LNCRATG.COMM_DATA.VAL_DATE = LNCCONTM.REC_DATA.START_DATE;
        }
    }
    public void B110_GET_ICTL_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCRATG.COMM_DATA.LN_AC;
        if (LNCRATG.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCRATG.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B110_GET_OLDRATE_INFO() throws IOException,SQLException,Exception {
        WS_COND_FLG.FOUND_FLG = 'N';
        WS_COND_FLG.RATH_FND_FLG = 'Y';
        IBS.init(SCCGWA, LNRRATH);
        LNRRATH.KEY.CONTRACT_NO = LNCRATG.COMM_DATA.LN_AC;
        if (LNCRATG.COMM_DATA.SUF_NO.trim().length() == 0) LNRRATH.KEY.SUB_CTA_NO = 0;
        else LNRRATH.KEY.SUB_CTA_NO = Integer.parseInt(LNCRATG.COMM_DATA.SUF_NO);
        LNRRATH.KEY.RATE_TYP = LNCRATG.COMM_DATA.RATE_TYPE;
        LNRRATH.KEY.RAT_CHG_DT = LNCRATG.COMM_DATA.VAL_DATE;
        T000_GET_LAST_RATH();
        if (pgmRtn) return;
        LNCRATG.COMM_DATA.OLD_RATE = LNRRATH.INT_RAT;
        LNCRATG.COMM_DATA.OLD_DATE = LNRRATH.KEY.RAT_CHG_DT;
        if (LNRRATH.KEY.RATE_TYP == 'N') {
            WS_VARIABLES.CUR_RAT = LNRRATH.INT_RAT;
            WS_VARIABLES.CUR_RAT_DT = LNRRATH.KEY.RAT_CHG_DT;
        }
        if (LNRRATH.KEY.RATE_TYP == 'O') {
            WS_VARIABLES.CUR_O_RAT = LNRRATH.INT_RAT;
            WS_VARIABLES.CUR_RAT = LNRRATH.INT_RAT;
            WS_VARIABLES.CUR_O_RAT_DT = LNRRATH.KEY.RAT_CHG_DT;
            WS_VARIABLES.CUR_RAT_DT = LNRRATH.KEY.RAT_CHG_DT;
        }
        if (LNRRATH.KEY.RATE_TYP == 'L') {
            WS_VARIABLES.CUR_L_RAT = LNRRATH.INT_RAT;
            WS_VARIABLES.CUR_RAT = LNRRATH.INT_RAT;
            WS_VARIABLES.CUR_L_RAT_DT = LNRRATH.KEY.RAT_CHG_DT;
            WS_VARIABLES.CUR_RAT_DT = LNRRATH.KEY.RAT_CHG_DT;
        }
        if (LNRRATH.KEY.RATE_TYP == 'P') {
            WS_VARIABLES.CUR_P_RAT = LNRRATH.INT_RAT;
            WS_VARIABLES.CUR_RAT = LNRRATH.INT_RAT;
            WS_VARIABLES.CUR_P_RAT_DT = LNRRATH.KEY.RAT_CHG_DT;
            WS_VARIABLES.CUR_RAT_DT = LNRRATH.KEY.RAT_CHG_DT;
        }
        WS_VARIABLES.BASE_RATE = LNRRATH.BASE_RATE;
        if (WS_COND_FLG.FOUND_FLG == 'N') {
            WS_VARIABLES.CUR_RAT = 9999.99999999;
            WS_VARIABLES.CUR_O_RAT = 9999.99999999;
            WS_VARIABLES.CUR_L_RAT = 9999.99999999;
            WS_VARIABLES.CUR_P_RAT = 9999.99999999;
            WS_VARIABLES.BASE_RATE = 0;
            WS_COND_FLG.RATH_FND_FLG = 'N';
        }
    }
    public void B100_DEL_OLD_RATH() throws IOException,SQLException,Exception {
        if (LNCRATG.COMM_DATA.FUNC_CODE == 'U' 
            || LNCRATG.COMM_DATA.FUNC_CODE == 'D') {
            B210_DEL_RATE_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B210_DEL_RATE_PROCESS() throws IOException,SQLException,Exception {
        WS_COND_FLG.FOUND_FLG = 'N';
        WS_VARIABLES.RATE_TYPE = LNCRATG.COMM_DATA.RATE_TYPE;
        WS_VARIABLES.RAT_CHG_DT = LNCRATG.COMM_DATA.VAL_DATE;
        T00_STARTBR_LNTRATH();
        if (pgmRtn) return;
        T00_READNEXT_LNTRATH();
        if (pgmRtn) return;
        while (WS_COND_FLG.FOUND_FLG != 'N') {
            if ((LNRRATH.KEY.RAT_CHG_DT != LNCRATG.COMM_DATA.VAL_DATE 
                && LNCRATG.COMM_DATA.FUNC_CODE == 'U') 
                || LNCRATG.COMM_DATA.FUNC_CODE == 'D') {
                B220_DEL_RATH_PROCESS();
                if (pgmRtn) return;
                if (LNCRATG.COMM_DATA.RATE_TYPE == 'N') {
                    B230_DELETE_A_O_RATE();
                    if (pgmRtn) return;
                }
            }
            T00_READNEXT_LNTRATH();
            if (pgmRtn) return;
        }
        T00_ENDBR_LNTRATH();
        if (pgmRtn) return;
    }
    public void B220_DEL_RATH_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRATHM);
        LNCRATHM.FUNC = '4';
        LNCRATHM.REC_DATA.KEY.CONTRACT_NO = LNRRATH.KEY.CONTRACT_NO;
        LNCRATHM.REC_DATA.KEY.SUB_CTA_NO = LNRRATH.KEY.SUB_CTA_NO;
        LNCRATHM.REC_DATA.KEY.RATE_TYP = LNRRATH.KEY.RATE_TYP;
        LNCRATHM.REC_DATA.KEY.RAT_CHG_DT = LNRRATH.KEY.RAT_CHG_DT;
        S000_CALL_LNZRATHM();
        if (pgmRtn) return;
        LNCRATHM.FUNC = '1';
        S000_CALL_LNZRATHM();
        if (pgmRtn) return;
    }
    public void B300_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_VARIABLES.VAL_DATE = LNCRATG.COMM_DATA.VAL_DATE;
        if (LNCRATG.COMM_DATA.RATE_TYPE == 'N') {
            B310_GEN_NORM_RATE();
            if (pgmRtn) return;
        } else {
            B320_GEN_OVRD_RATE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCRATG.COMM_DATA.FUNC_CODE);
        if (LNCRATG.COMM_DATA.FUNC_CODE == 'U') {
            B500_ICTL_UPD_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B310_GEN_NORM_RATE() throws IOException,SQLException,Exception {
        B300_GET_RATN_ACTVDT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCRATNM);
        CEP.TRC(SCCGWA, LNRRATN);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA);
        CEP.TRC(SCCGWA, LNRRATN);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA);
        CEP.TRC(SCCGWA, LNRRATN.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRRATN.KEY.ACTV_DT);
        CEP.TRC(SCCGWA, LNRRATN.RATE_FLG);
        CEP.TRC(SCCGWA, LNRRATN.FLT_PERD);
        CEP.TRC(SCCGWA, LNRRATN.FLT_PERD_UNIT);
        CEP.TRC(SCCGWA, LNRRATN.FIRST_FLT_FLG);
        CEP.TRC(SCCGWA, LNRRATN.FLT_DAY);
        CEP.TRC(SCCGWA, LNRRATN.FLT_GAP_PERD);
        CEP.TRC(SCCGWA, LNRRATN.FLT_DTADJ_FLG);
        CEP.TRC(SCCGWA, LNRRATN.FST_FLT_DT);
        CEP.TRC(SCCGWA, LNRRATN.NEXT_FLT_DT);
        CEP.TRC(SCCGWA, LNRRATN.VARIATION_METHOD);
        CEP.TRC(SCCGWA, LNRRATN.COMPARISON_METHOD);
        CEP.TRC(SCCGWA, LNRRATN.MAX_RATE);
        CEP.TRC(SCCGWA, LNRRATN.MIN_RATE);
        CEP.TRC(SCCGWA, LNRRATN.INT_RATE_TYPE1);
        CEP.TRC(SCCGWA, LNRRATN.INT_RATE_CLAS1);
        CEP.TRC(SCCGWA, LNRRATN.RATE_VARIATION1);
        CEP.TRC(SCCGWA, LNRRATN.RATE_PECT1);
        CEP.TRC(SCCGWA, LNRRATN.INT_RATE_TYPE2);
        CEP.TRC(SCCGWA, LNRRATN.INT_RATE_CLAS2);
        CEP.TRC(SCCGWA, LNRRATN.RATE_VARIATION2);
        CEP.TRC(SCCGWA, LNRRATN.RATE_PECT2);
        CEP.TRC(SCCGWA, LNRRATN.INT_RATE_TYPE3);
        CEP.TRC(SCCGWA, LNRRATN.INT_RATE_CLAS3);
        CEP.TRC(SCCGWA, LNRRATN.RATE_VARIATION3);
        CEP.TRC(SCCGWA, LNRRATN.RATE_PECT3);
        CEP.TRC(SCCGWA, LNRRATN.COST_OF_FUND_RATE);
        CEP.TRC(SCCGWA, LNRRATN.INTEREST_BASE_MEMO);
        CEP.TRC(SCCGWA, LNRRATN.PREMIUM_RATE_TYPE);
        CEP.TRC(SCCGWA, LNRRATN.PREMIUM_RATE_CLAS);
        CEP.TRC(SCCGWA, LNRRATN.PREMIUM_RATE);
        CEP.TRC(SCCGWA, LNRRATN.ADD_ON_RATE);
        CEP.TRC(SCCGWA, LNRRATN.ALL_IN_RATE);
        CEP.TRC(SCCGWA, LNRRATN.CRT_DATE);
        CEP.TRC(SCCGWA, LNRRATN.CRT_TLR);
        CEP.TRC(SCCGWA, LNRRATN.UPDTBL_DATE);
        CEP.TRC(SCCGWA, LNRRATN.UPDTBL_TLR);
        CEP.TRC(SCCGWA, LNRRATN.SUP1);
        CEP.TRC(SCCGWA, LNRRATN.SUP2);
        CEP.TRC(SCCGWA, LNRRATN.TS);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNRRATN);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCRATNM.REC_DATA);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA);
        CEP.TRC(SCCGWA, LNRRATN);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.KEY.ACTV_DT);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.RATE_FLG);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.FLT_PERD);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.FLT_PERD_UNIT);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.FIRST_FLT_FLG);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.FLT_DAY);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.FLT_GAP_PERD);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.FLT_DTADJ_FLG);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.FST_FLT_DT);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.NEXT_FLT_DT);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.VARIATION_METHOD);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.COMPARISON_METHOD);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.MAX_RATE);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.MIN_RATE);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.INT_RATE_TYPE1);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.INT_RATE_CLAS1);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.RATE_VARIATION1);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.RATE_PECT1);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.INT_RATE_TYPE2);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.INT_RATE_CLAS2);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.RATE_VARIATION2);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.RATE_PECT2);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.INT_RATE_TYPE3);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.INT_RATE_CLAS3);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.RATE_VARIATION3);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.RATE_PECT3);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.COST_OF_FUND_RATE);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.INTEREST_BASE_MEMO);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.PREMIUM_RATE_TYPE);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.PREMIUM_RATE_CLAS);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.PREMIUM_RATE);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.ADD_ON_RATE);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.ALL_IN_RATE);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.CRT_DATE);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.CRT_TLR);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.UPDTBL_DATE);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.UPDTBL_TLR);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.SUP1);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.SUP2);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.TS);
        WS_VARIABLES.ACTV_DT = LNCRATNM.REC_DATA.KEY.ACTV_DT;
        WS_COND_FLG.RATE_FLT_FLG = LNCRATNM.REC_DATA.RATE_FLG;
        WS_COND_FLG.RATE_CPAR_MTH = LNCRATNM.REC_DATA.COMPARISON_METHOD;
        WS_VARIABLES.NXT_VAL_DATE = LNCRATG.COMM_DATA.VAL_DATE;
        WS_VARIABLES.RATH_VAL_DATE = LNCRATNM.REC_DATA.NEXT_FLT_DT;
        if (WS_COND_FLG.RATE_FLT_FLG == '0') {
            B311_GEN_NORM_FIX_RATE();
            if (pgmRtn) return;
        } else {
            B312_GEN_NORM_VAR_RATE();
            if (pgmRtn) return;
        }
        if (LNCRATG.COMM_DATA.FUNC_CODE == 'U') {
            IBS.init(SCCGWA, LNCRATNM);
            LNCRATNM.REC_DATA.KEY.CONTRACT_NO = LNCRATG.COMM_DATA.LN_AC;
            LNCRATNM.REC_DATA.KEY.ACTV_DT = WS_VARIABLES.ACTV_DT;
            LNCRATNM.FUNC = '4';
            S000_CALL_LNZRATNM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "TESTTTTTT");
            CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.FST_FLT_DT);
            CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.NEXT_FLT_DT);
            CEP.TRC(SCCGWA, "ESTTTTTT");
            LNCRATNM.FUNC = '2';
            LNCRATNM.REC_DATA.NEXT_FLT_DT = WS_VARIABLES.NXT_VAR_RTDT;
            CEP.TRC(SCCGWA, WS_VARIABLES.NXT_VAR_RTDT);
            CEP.TRC(SCCGWA, "20181217");
            CEP.TRC(SCCGWA, LNCRATG.COMM_DATA.LN_AC);
            LNCRATNM.REC_DATA.ALL_IN_RATE = LNCRATG.COMM_DATA.RATE;
            LNCRATNM.REC_DATA.KEY.ACTV_DT = WS_PRIME_CODE.RAT_N_L_DT;
            S000_CALL_LNZRATNM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.FST_FLT_DT);
            CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.NEXT_FLT_DT);
            CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.KEY.ACTV_DT);
            CEP.TRC(SCCGWA, "201812134");
        }
    }
    public void B311_GEN_NORM_FIX_RATE() throws IOException,SQLException,Exception {
        if (LNCRATG.COMM_DATA.VAL_DATE == LNCRATNM.REC_DATA.KEY.ACTV_DT) {
            B3S1_GEN_NORM_ACTV_RATE();
            if (pgmRtn) return;
        } else {
            LNCRATG.COMM_DATA.RATE = LNCRATNM.REC_DATA.ALL_IN_RATE;
        }
        LNCRATG.COMM_DATA.STYLE_SEQ = 0;
        WS_VARIABLES.NXT_VAL_DATE = 0;
        CEP.TRC(SCCGWA, "9999-5");
    }
    public void B312_GEN_NORM_VAR_RATE() throws IOException,SQLException,Exception {
        if (LNCRATG.COMM_DATA.FUNC_CODE == 'I') {
            B3S1_GEN_NORM_ACTV_RATE();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, WS_VARIABLES.II);
            while (WS_VARIABLES.NXT_VAL_DATE <= WS_VARIABLES.GWA_AC_DATE 
                && WS_COND_FLG.RATE_FLT_FLG != '0' 
                && WS_VARIABLES.II <= 9990) {
                WS_VARIABLES.II += 1;
                CEP.TRC(SCCGWA, WS_VARIABLES.NXT_VAL_DATE);
                CEP.TRC(SCCGWA, WS_VARIABLES.GWA_AC_DATE);
                CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.MAT_DATE);
                CEP.TRC(SCCGWA, LNCRATG.COMM_DATA.VAL_DATE);
                CEP.TRC(SCCGWA, WS_VARIABLES.VAL_DATE);
                B3S1_GEN_NORM_ACTV_RATE();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_VARIABLES.NXT_VAR_RTDT);
            CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.NEXT_FLT_DT);
            if (WS_COND_FLG.RATE_FLT_FLG == '1' 
                && WS_VARIABLES.NXT_VAR_RTDT > LNCRATNM.REC_DATA.NEXT_FLT_DT) {
                B3S1_UPD_PERD_NXT_VAR_RTDT();
                if (pgmRtn) return;
            }
        }
    }
    public void B3S1_UPD_PERD_NXT_VAR_RTDT() throws IOException,SQLException,Exception {
    }
    public void B3S1_GEN_NORM_ACTV_RATE() throws IOException,SQLException,Exception {
        if (WS_COND_FLG.RATE_FLT_FLG == '0' 
            && LNCRATNM.REC_DATA.INT_RATE_TYPE1.trim().length() == 0 
            && LNCRATNM.REC_DATA.INT_RATE_TYPE2.trim().length() == 0 
            && LNCRATNM.REC_DATA.INT_RATE_TYPE3.trim().length() == 0 
            && LNCRATNM.REC_DATA.INT_RATE_TYPE4.trim().length() == 0 
            && LNCRATNM.REC_DATA.INT_RATE_TYPE5.trim().length() == 0) {
            LNCRATG.COMM_DATA.RATE = LNCRATNM.REC_DATA.COST_OF_FUND_RATE;
        } else {
            B3S2_GEN_NORM_RATE();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNCRATG.COMM_DATA.RATE);
            CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.COST_OF_FUND_RATE);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            if ((JIBS_tmp_str[0].equalsIgnoreCase("0134070") 
                || JIBS_tmp_str[0].equalsIgnoreCase("0134072") 
                || JIBS_tmp_str[0].equalsIgnoreCase("0134079") 
                || JIBS_tmp_str[0].equalsIgnoreCase("0139801") 
                || JIBS_tmp_str[0].equalsIgnoreCase("0139809"))) {
                LNCRATNM.REC_DATA.COST_OF_FUND_RATE = LNCRATG.COMM_DATA.RATE;
            }
            if (WS_VARIABLES.VAL_DATE == LNCRATNM.REC_DATA.KEY.ACTV_DT) {
                if (LNCRATNM.REC_DATA.COST_OF_FUND_RATE == 0) {
                    LNCRATNM.REC_DATA.COST_OF_FUND_RATE = LNCRATG.COMM_DATA.RATE;
                } else {
                    if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT") 
                        || SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
                        LNCRATNM.REC_DATA.COST_OF_FUND_RATE = LNCRATG.COMM_DATA.RATE;
                    } else {
                        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
                        if (LNCRATG.COMM_DATA.RATE != LNCRATNM.REC_DATA.COST_OF_FUND_RATE 
                            && (!JIBS_tmp_str[1].equalsIgnoreCase("0134070") 
                            && !JIBS_tmp_str[1].equalsIgnoreCase("0134072") 
                            && !JIBS_tmp_str[1].equalsIgnoreCase("0134079") 
                            && !JIBS_tmp_str[1].equalsIgnoreCase("0139801") 
                            && !JIBS_tmp_str[1].equalsIgnoreCase("0139809"))) {
                            CEP.TRC(SCCGWA, "TMPDEV-LNZRATG1");
                            CEP.TRC(SCCGWA, LNCRATG.COMM_DATA.RATE);
                            CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.COST_OF_FUND_RATE);
                            CEP.TRC(SCCGWA, LNCRATG.COMM_DATA.RATE);
                            CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.COST_OF_FUND_RATE);
                            CEP.TRC(SCCGWA, "ERR RATG-RATE");
                            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_NOTEQ_COST_RAT, LNCRATG.RC);
                            Z_RET();
                            if (pgmRtn) return;
                        }
                    }
                }
            }
        }
        WS_VARIABLES.COST_RATE = LNCRATG.COMM_DATA.RATE;
        LNCRATG.COMM_DATA.RATE = WS_VARIABLES.COST_RATE + LNCRATNM.REC_DATA.PREMIUM_RATE + LNCRATNM.REC_DATA.ADD_ON_RATE;
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT") 
            && LNCRATG.COMM_DATA.RATE < 0) {
            LNCRATG.COMM_DATA.RATE = 0;
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.VAL_DATE);
        CEP.TRC(SCCGWA, LNCRATG.COMM_DATA.RATE);
        CEP.TRC(SCCGWA, WS_VARIABLES.COST_RATE);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.PREMIUM_RATE);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.ADD_ON_RATE);
        CEP.TRC(SCCGWA, WS_VARIABLES.VAL_DATE);
        CEP.TRC(SCCGWA, LNCRATG.COMM_DATA.RATE);
        CEP.TRC(SCCGWA, WS_VARIABLES.COST_RATE);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.PREMIUM_RATE);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.ADD_ON_RATE);
        if (WS_VARIABLES.VAL_DATE == LNCRATNM.REC_DATA.KEY.ACTV_DT) {
            if (LNCRATNM.REC_DATA.ALL_IN_RATE == 0) {
                LNCRATNM.REC_DATA.ALL_IN_RATE = LNCRATG.COMM_DATA.RATE;
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
                if ((JIBS_tmp_str[0].equalsIgnoreCase("0134070") 
                    || JIBS_tmp_str[0].equalsIgnoreCase("0134072") 
                    || JIBS_tmp_str[0].equalsIgnoreCase("0134079") 
                    || JIBS_tmp_str[0].equalsIgnoreCase("0139801") 
                    || JIBS_tmp_str[0].equalsIgnoreCase("0139809"))) {
                    CEP.TRC(SCCGWA, "EXNT-BSP-BAT ------------------");
                    LNCRATNM.REC_DATA.ALL_IN_RATE = LNCRATG.COMM_DATA.RATE;
                }
                if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT") 
                    || SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
                    LNCRATNM.REC_DATA.ALL_IN_RATE = LNCRATG.COMM_DATA.RATE;
                } else {
                    if (LNCRATG.COMM_DATA.RATE > LNCRATNM.REC_DATA.ALL_IN_RATE) {
                        WS_VARIABLES.DIFF_RATE = LNCRATG.COMM_DATA.RATE - LNCRATNM.REC_DATA.ALL_IN_RATE;
                    } else {
                        WS_VARIABLES.DIFF_RATE = LNCRATNM.REC_DATA.ALL_IN_RATE - LNCRATG.COMM_DATA.RATE;
                    }
                    JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
                    if (WS_VARIABLES.DIFF_RATE > .000001 
                        && (!JIBS_tmp_str[1].equalsIgnoreCase("0134070") 
                        && !JIBS_tmp_str[1].equalsIgnoreCase("0134072") 
                        && !JIBS_tmp_str[1].equalsIgnoreCase("0134079") 
                        && !JIBS_tmp_str[1].equalsIgnoreCase("0139801") 
                        && !JIBS_tmp_str[1].equalsIgnoreCase("0139809"))) {
                        CEP.TRC(SCCGWA, LNCRATG.COMM_DATA.RATE);
                        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.ALL_IN_RATE);
                        CEP.TRC(SCCGWA, LNCRATG.COMM_DATA.RATE);
                        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.ALL_IN_RATE);
                        CEP.TRC(SCCGWA, "TMPDEV-LNZRATG2");
                        IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_NOTEQ_ALLRATE, LNCRATG.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, "987654-------");
        CEP.TRC(SCCGWA, LNCRATG.COMM_DATA.FUNC_CODE);
        if (LNCRATG.COMM_DATA.FUNC_CODE == 'U') {
            CEP.TRC(SCCGWA, "123456-------");
            if (LNCPPMQ.DATA_TXT.PROD_MOD == 'R' 
                && WS_VARIABLES.VAL_DATE != LNRRATN.KEY.ACTV_DT) {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = LNCCONTM.REC_DATA.START_DATE;
                SCCCLDT.MTHS = 12;
                R00_CAL_DATE();
                if (pgmRtn) return;
            }
            if (LNCPPMQ.DATA_TXT.PROD_MOD == 'R' 
                && WS_VARIABLES.VAL_DATE != LNRRATN.KEY.ACTV_DT 
                && LNCCONTM.REC_DATA.ORI_MAT_DATE <= SCCCLDT.DATE2 
                && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT") 
                && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            } else {
                B310_UPDATE_HIS_RATE();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.NXT_VAL_DATE);
        WS_VARIABLES.VAL_DATE = WS_VARIABLES.NXT_VAL_DATE;
    }
    public void B3S2_GEN_NORM_RATE() throws IOException,SQLException,Exception {
        WS_VARIABLES.RATE = 0;
        WS_VARIABLES.RATE1 = 0;
        WS_VARIABLES.RT_TYPE = LNCRATNM.REC_DATA.INT_RATE_TYPE1;
        WS_VARIABLES.RT_CLASS = LNCRATNM.REC_DATA.INT_RATE_CLAS1;
        WS_VARIABLES.VAL_DATE = WS_VARIABLES.NXT_VAL_DATE;
        WS_VARIABLES.SPREAD_PNT = LNCRATNM.REC_DATA.RATE_VARIATION1;
        WS_VARIABLES.SPREAD_PCT = LNCRATNM.REC_DATA.RATE_PECT1;
        WS_VARIABLES.VARIATION_METHOD = LNCRATNM.REC_DATA.VARIATION_METHOD;
        CEP.TRC(SCCGWA, "DF111");
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.BASE_RATE1);
        WS_VARIABLES.RAT_BASE_RATE = LNCRATNM.REC_DATA.BASE_RATE1;
        WS_VARIABLES.RAT_ACTV_DT = LNCRATNM.REC_DATA.KEY.ACTV_DT;
        R000_GET_TYPE_RATE();
        if (pgmRtn) return;
        WS_VARIABLES.RATE1 = WS_VARIABLES.RATE;
        LNCRATG.COMM_DATA.RATE = WS_VARIABLES.RATE;
        CEP.TRC(SCCGWA, LNCRATG.COMM_DATA.RATE);
        LNCRATG.COMM_DATA.STYLE_SEQ = 1;
        CEP.TRC(SCCGWA, "RRRRRR");
        CEP.TRC(SCCGWA, WS_VARIABLES.NXT_VAL_DATE);
        if (LNCRATG.COMM_DATA.FUNC_CODE != 'I') {
            CEP.TRC(SCCGWA, "1234567-------");
            B3S3_GEN_NXT_RTDAT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "RRRRRR");
        CEP.TRC(SCCGWA, WS_VARIABLES.NXT_VAL_DATE);
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.N_DT);
        CEP.TRC(SCCGWA, WS_VARIABLES.VAL_DATE);
        CEP.TRC(SCCGWA, WS_VARIABLES.GWA_AC_DATE);
    }
    public void B3S3_GEN_NXT_RTDAT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_COND_FLG.RATE_FLT_FLG);
        if (WS_COND_FLG.RATE_FLT_FLG == '0') {
            CEP.TRC(SCCGWA, "9999-4");
            WS_VARIABLES.NXT_VAL_DATE = 0;
            WS_VARIABLES.NXT_VAR_RTDT = 0;
        } else if (WS_COND_FLG.RATE_FLT_FLG == '1') {
            B3S3_1_GEN_NXT_RTDAT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "RATE-PERIOD");
            CEP.TRC(SCCGWA, WS_VARIABLES.NXT_VAR_RTDT);
        } else if (WS_COND_FLG.RATE_FLT_FLG == '2') {
            B3S3_2_GEN_NXT_RTDAT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "RATE-DAILY");
            CEP.TRC(SCCGWA, WS_VARIABLES.NXT_VAR_RTDT);
        } else if (WS_COND_FLG.RATE_FLT_FLG == '3') {
            CEP.TRC(SCCGWA, "RATE-INTPERD");
            CEP.TRC(SCCGWA, WS_VARIABLES.NXT_VAR_RTDT);
            B3S3_3_GEN_NXT_RTDAT();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_RATTYP_INVAILD, LNCRATG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B3S3_1_GEN_NXT_RTDAT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.NEXT_FLT_DT);
        WS_VARIABLES.FLT_PERD = LNCRATNM.REC_DATA.FLT_PERD;
        WS_VARIABLES.FLT_PERD_UNIT = LNCRATNM.REC_DATA.FLT_PERD_UNIT;
        B3S3_1_CMP_NXT_RTDAT();
        if (pgmRtn) return;
        if (LNCRATNM.REC_DATA.FLT_PERD_UNIT == 'M' 
            || LNCRATNM.REC_DATA.FLT_PERD_UNIT == 'Q' 
            || LNCRATNM.REC_DATA.FLT_PERD_UNIT == 'H' 
            || LNCRATNM.REC_DATA.FLT_PERD_UNIT == 'Y') {
            BS07_MOD_NXT_PYDAT();
            if (pgmRtn) return;
        }
        if (WS_VARIABLES.VAL_DATE == LNCRATNM.REC_DATA.KEY.ACTV_DT) {
            CEP.TRC(SCCGWA, WS_VARIABLES.VAL_DATE);
            CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.KEY.ACTV_DT);
            WS_VARIABLES.NXT_VAL_DATE = LNCRATNM.REC_DATA.FST_FLT_DT;
            WS_VARIABLES.NXT_VAR_RTDT = LNCRATNM.REC_DATA.FST_FLT_DT;
        }
    }
    public void B3S3_1_CMP_NXT_RTDAT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.FLT_PERD);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.FLT_PERD_UNIT);
        CEP.TRC(SCCGWA, WS_VARIABLES.NXT_VAL_DATE);
        if (WS_VARIABLES.FLT_PERD == 0 
            || WS_VARIABLES.FLT_PERD_UNIT == ' ') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_RFLT_MTH_MISS, LNCRATG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_VARIABLES.NXT_VAL_DATE;
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.FLT_PERD_UNIT);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.FLT_PERD);
        if (WS_VARIABLES.FLT_PERD_UNIT == UNIT_D) {
            SCCCLDT.DAYS = WS_VARIABLES.FLT_PERD;
        } else if (WS_VARIABLES.FLT_PERD_UNIT == UNIT_M) {
            SCCCLDT.MTHS = WS_VARIABLES.FLT_PERD;
        } else if (WS_VARIABLES.FLT_PERD_UNIT == UNIT_W) {
            SCCCLDT.DAYS = 7;
        } else if (WS_VARIABLES.FLT_PERD_UNIT == UNIT_B) {
            SCCCLDT.DAYS = 14;
        } else if (WS_VARIABLES.FLT_PERD_UNIT == UNIT_Q) {
            SCCCLDT.MTHS = 3;
        } else if (WS_VARIABLES.FLT_PERD_UNIT == UNIT_H) {
            SCCCLDT.MTHS = 6;
        } else if (WS_VARIABLES.FLT_PERD_UNIT == UNIT_Y) {
            SCCCLDT.MTHS = 12;
        } else {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_RFLT_MTH_MISS, LNCRATG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCCLDT.DATE1);
        if (WS_VARIABLES.FLT_PERD != 0) {
            R00_CAL_DATE();
            if (pgmRtn) return;
        } else {
            SCCCLDT.DATE2 = LNCCONTM.REC_DATA.MAT_DATE;
        }
        CEP.TRC(SCCGWA, "CHG-DT");
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        WS_VARIABLES.NXT_VAL_DATE = SCCCLDT.DATE2;
        WS_VARIABLES.NXT_VAR_RTDT = SCCCLDT.DATE2;
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
    }
    public void BS07_MOD_NXT_PYDAT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.FLT_DAY);
        WS_VARIABLES.YYYYMMDD = SCCCLDT.DATE2;
        IBS.CPY2CLS(SCCGWA, WS_VARIABLES.YYYYMMDD+"", WS_VARIABLES.REDEFINES56);
        if (LNCRATNM.REC_DATA.FLT_DAY < 29) {
            if (LNCRATNM.REC_DATA.FLT_DAY > 0) {
                WS_VARIABLES.REDEFINES56.DD = LNCRATNM.REC_DATA.FLT_DAY;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES56);
                WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
            }
        } else {
            WS_VARIABLES.YYYY_1 = WS_VARIABLES.REDEFINES56.YYYY / 4;
            WS_VARIABLES.YYYY_2 = (short) WS_VARIABLES.YYYY_1;
            if (LNCRATNM.REC_DATA.FLT_DAY == 29) {
                if (WS_VARIABLES.REDEFINES56.MM == 2) {
                    if (WS_VARIABLES.YYYY_1 == WS_VARIABLES.YYYY_2) {
                        WS_VARIABLES.REDEFINES56.DD = 29;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES56);
                        WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                    } else {
                        WS_VARIABLES.REDEFINES56.DD = 28;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES56);
                        WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                    }
                } else {
                    WS_VARIABLES.REDEFINES56.DD = LNCRATNM.REC_DATA.FLT_DAY;
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES56);
                    WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                }
            }
            if (LNCRATNM.REC_DATA.FLT_DAY == 30) {
                if (WS_VARIABLES.REDEFINES56.MM == 2) {
                    if (WS_VARIABLES.YYYY_1 == WS_VARIABLES.YYYY_2) {
                        WS_VARIABLES.REDEFINES56.DD = 29;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES56);
                        WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                    } else {
                        WS_VARIABLES.REDEFINES56.DD = 28;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES56);
                        WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                    }
                } else {
                    WS_VARIABLES.REDEFINES56.DD = 30;
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES56);
                    WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                }
            }
            if (LNCRATNM.REC_DATA.FLT_DAY == 31) {
                if (WS_VARIABLES.REDEFINES56.MM == 2) {
                    if (WS_VARIABLES.YYYY_1 == WS_VARIABLES.YYYY_2) {
                        WS_VARIABLES.REDEFINES56.DD = 29;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES56);
                        WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                    } else {
                        WS_VARIABLES.REDEFINES56.DD = 28;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES56);
                        WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                    }
                } else {
                    if (WS_VARIABLES.REDEFINES56.MM == 4 
                        || WS_VARIABLES.REDEFINES56.MM == 6 
                        || WS_VARIABLES.REDEFINES56.MM == 9 
                        || WS_VARIABLES.REDEFINES56.MM == 11) {
                        WS_VARIABLES.REDEFINES56.DD = 30;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES56);
                        WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                    } else {
                        WS_VARIABLES.REDEFINES56.DD = 31;
                        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.REDEFINES56);
                        WS_VARIABLES.YYYYMMDD = Integer.parseInt(JIBS_tmp_str[0]);
                    }
                }
            }
        }
        SCCCLDT.DATE2 = WS_VARIABLES.YYYYMMDD;
        WS_VARIABLES.NXT_VAL_DATE = WS_VARIABLES.YYYYMMDD;
        WS_VARIABLES.NXT_VAR_RTDT = WS_VARIABLES.YYYYMMDD;
        CEP.TRC(SCCGWA, "CHG-DT");
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
    }
    public void B3S3_2_GEN_NXT_RTDAT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "9999-1");
        CEP.TRC(SCCGWA, WS_VARIABLES.VAL_DATE);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.NEXT_FLT_DT);
        WS_VARIABLES.FLT_PERD = 1;
        WS_VARIABLES.FLT_PERD_UNIT = 'D';
        B3S3_1_CMP_NXT_RTDAT();
        if (pgmRtn) return;
    }
    public void B3S3_3_GEN_NXT_RTDAT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CAL_DUE_DT);
        IBS.init(SCCGWA, LNRRCVD);
        LNRRCVD.KEY.CONTRACT_NO = LNCRATG.COMM_DATA.LN_AC;
        LNRRCVD.VAL_DT = WS_VARIABLES.NXT_VAL_DATE;
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        LNTRCVD_RD.eqWhere = "CONTRACT_NO";
        LNTRCVD_RD.where = "VAL_DT <= :LNRRCVD.VAL_DT "
            + "AND DUE_DT > :LNRRCVD.VAL_DT "
            + "AND AMT_TYP < > 'P'";
        LNTRCVD_RD.fst = true;
        LNTRCVD_RD.order = "VAL_DT";
        IBS.READ(SCCGWA, LNRRCVD, this, LNTRCVD_RD);
        WS_VARIABLES.NXT_VAL_DATE = LNRRCVD.DUE_DT;
        WS_VARIABLES.NXT_VAR_RTDT = LNRRCVD.DUE_DT;
        if (WS_VARIABLES.NXT_VAL_DATE <= WS_VARIABLES.VAL_DATE) {
            WS_VARIABLES.NXT_VAL_DATE = LNCICTLM.REC_DATA.IC_CAL_DUE_DT;
            WS_VARIABLES.NXT_VAR_RTDT = LNCICTLM.REC_DATA.IC_CAL_DUE_DT;
            if (LNCICTLM.REC_DATA.IC_CAL_DUE_DT <= WS_VARIABLES.GWA_AC_DATE) {
                WS_VARIABLES.FLT_PERD = LNCPPMQ.DATA_CONT_SPC.CONT_CAL_PERD;
                WS_VARIABLES.FLT_PERD_UNIT = LNCPPMQ.DATA_CONT_SPC.CONT_CAL_UNIT;
                while (WS_VARIABLES.NXT_VAL_DATE <= WS_VARIABLES.VAL_DATE) {
                    B3S3_1_CMP_NXT_RTDAT();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B320_GEN_OVRD_RATE() throws IOException,SQLException,Exception {
        WS_VARIABLES.O_R_ACTV_DT = LNCRATG.COMM_DATA.VAL_DATE;
        B300_GET_RATL_ACTVDT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCRATLM);
        LNCRATLM.REC_DATA.KEY.CONTRACT_NO = LNCRATG.COMM_DATA.LN_AC;
        LNCRATLM.REC_DATA.KEY.OVD_KND = LNCRATG.COMM_DATA.RATE_TYPE;
        LNCRATLM.REC_DATA.KEY.ACTV_DT = WS_VARIABLES.LAST_ACTV_DT;
        CEP.TRC(SCCGWA, LNCRATG.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCRATG.COMM_DATA.RATE_TYPE);
        CEP.TRC(SCCGWA, LNCRATG.COMM_DATA.VAL_DATE);
        CEP.TRC(SCCGWA, LNCRATLM.REC_DATA.KEY.ACTV_DT);
        if (LNCRATG.COMM_DATA.FUNC_CODE == 'U') {
            LNCRATLM.FUNC = '4';
        } else {
            LNCRATLM.FUNC = '3';
        }
        S000_CALL_LNZRATLM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCRATLM.REC_DATA.INT_CHRG_MTH);
        if ((LNCRATLM.REC_DATA.INT_CHRG_MTH != 'F' 
            && LNCRATLM.REC_DATA.INT_CHRG_MTH != 'T' 
            && LNCRATLM.REC_DATA.INT_CHRG_MTH != 'A')) {
            CEP.TRC(SCCGWA, "MTH-ERR");
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_PRAT_FLG, LNCRATG.RC);
            WS_PRIME_CODE.ERR_INFO = "" + LNCRATG.COMM_DATA.RATE_TYPE;
            JIBS_tmp_int = WS_PRIME_CODE.ERR_INFO.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) WS_PRIME_CODE.ERR_INFO = "0" + WS_PRIME_CODE.ERR_INFO;
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_PRAT_FLG, WS_PRIME_CODE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        WS_VARIABLES.NXT_VAL_DATE = LNCRATG.COMM_DATA.VAL_DATE;
        while (WS_VARIABLES.NXT_VAL_DATE != 0) {
            WS_VARIABLES.VAL_DATE = WS_VARIABLES.NXT_VAL_DATE;
            B330_GEN_OVRD_RATE();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_VARIABLES.NXT_VAL_DATE);
        }
        CEP.TRC(SCCGWA, "111111111111");
        if (LNCRATG.COMM_DATA.FUNC_CODE == 'U') {
            LNCRATLM.FUNC = '2';
            if (LNCRATLM.REC_DATA.KEY.OVD_KND == 'O') {
                LNCRATLM.REC_DATA.EFF_RAT = LNCRATG.COMM_DATA.RATE;
                LNCRATLM.REC_DATA.KEY.ACTV_DT = WS_PRIME_CODE.RAT_N_L_DT;
                CEP.TRC(SCCGWA, LNCRATLM.REC_DATA.KEY.ACTV_DT);
                CEP.TRC(SCCGWA, WS_VARIABLES.VAL_DATE);
                CEP.TRC(SCCGWA, "201812131");
            }
            if (LNCRATLM.REC_DATA.KEY.OVD_KND == 'L') {
                LNCRATLM.REC_DATA.EFF_RAT = LNCRATG.COMM_DATA.RATE;
                LNCRATLM.REC_DATA.KEY.ACTV_DT = WS_PRIME_CODE.RAT_N_L_DT;
                CEP.TRC(SCCGWA, LNCRATLM.REC_DATA.KEY.ACTV_DT);
                CEP.TRC(SCCGWA, WS_VARIABLES.VAL_DATE);
                CEP.TRC(SCCGWA, "201812132");
            }
            S000_CALL_LNZRATLM();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "22222222222");
    }
    public void B330_GEN_OVRD_RATE() throws IOException,SQLException,Exception {
        if (LNCRATLM.REC_DATA.INT_CHRG_MTH == 'F') {
            WS_VARIABLES.RATE = LNCRATLM.REC_DATA.EFF_RAT;
            WS_VARIABLES.NXT_VAL_DATE = 0;
        } else {
            B331_GEN_OVRD_RATE();
            if (pgmRtn) return;
        }
        LNCRATG.COMM_DATA.RATE = WS_VARIABLES.RATE;
        LNCRATLM.REC_DATA.EFF_RAT = LNCRATG.COMM_DATA.RATE;
        if (LNCRATG.COMM_DATA.FUNC_CODE == 'U') {
            B310_UPDATE_HIS_RATE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "B310-FINISH");
    }
    public void B331_GEN_OVRD_RATE() throws IOException,SQLException,Exception {
        WS_VARIABLES.RATE = 0;
        WS_VARIABLES.RT_TYPE = LNCRATLM.REC_DATA.RATE_TYPE;
        WS_VARIABLES.RT_CLASS = LNCRATLM.REC_DATA.RATE_CLAS;
        WS_VARIABLES.VAL_DATE = LNCRATG.COMM_DATA.VAL_DATE;
        WS_VARIABLES.SPREAD_PNT = LNCRATLM.REC_DATA.DIF_IRAT_PNT;
        WS_VARIABLES.SPREAD_PCT = LNCRATLM.REC_DATA.DIF_IRAT_PER;
        WS_VARIABLES.VARIATION_METHOD = LNCRATLM.REC_DATA.VARIATION_METHOD;
        WS_VARIABLES.RAT_ACTV_DT = LNCRATLM.REC_DATA.KEY.ACTV_DT;
        if (LNCRATLM.REC_DATA.INT_CHRG_MTH == 'A') {
            CEP.TRC(SCCGWA, "DF222");
            CEP.TRC(SCCGWA, LNCRATLM.REC_DATA.BASE_RATE);
            WS_VARIABLES.RAT_BASE_RATE = LNCRATLM.REC_DATA.BASE_RATE;
            R000_GET_TYPE_RATE();
            if (pgmRtn) return;
            WS_VARIABLES.NXT_VAL_DATE = 0;
            CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.OWN_RATE);
            CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.N_DT);
        }
        CEP.TRC(SCCGWA, LNCRATLM.REC_DATA.INT_CHRG_MTH);
        if (LNCRATLM.REC_DATA.INT_CHRG_MTH == 'P') {
            WS_VARIABLES.RT_CODE = IBS.CLS2CPY(SCCGWA, WS_PRIME_CODE);
            WS_VARIABLES.NXT_VAL_DATE = 0;
            CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.OWN_RATE);
            CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.N_DT);
        }
        if (LNCRATLM.REC_DATA.INT_CHRG_MTH == 'S') {
            CEP.TRC(SCCGWA, "A-GET-NOR-RATE");
            B322_GET_NOR_RATE();
            if (pgmRtn) return;
            WS_VARIABLES.NXT_VAL_DATE = 0;
            WS_VARIABLES.SPREAD_PNT = LNCRATLM.REC_DATA.DIF_IRAT_PNT;
            WS_VARIABLES.SPREAD_PCT = LNCRATLM.REC_DATA.DIF_IRAT_PER;
            CEP.TRC(SCCGWA, WS_VARIABLES.VARIATION_METHOD);
            CEP.TRC(SCCGWA, WS_VARIABLES.RATE);
            if (WS_VARIABLES.VARIATION_METHOD == '1') {
                CEP.TRC(SCCGWA, WS_VARIABLES.SPREAD_PNT);
                WS_VARIABLES.RATE = WS_VARIABLES.RATE + WS_VARIABLES.SPREAD_PNT;
            }
            if (WS_VARIABLES.VARIATION_METHOD == '2') {
                CEP.TRC(SCCGWA, WS_VARIABLES.SPREAD_PCT);
                WS_VARIABLES.RATE = WS_VARIABLES.RATE * ( 1 + WS_VARIABLES.SPREAD_PCT / 100 );
                bigD = new BigDecimal(WS_VARIABLES.RATE);
                WS_VARIABLES.RATE = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            }
            if (WS_VARIABLES.VARIATION_METHOD == '3') {
                CEP.TRC(SCCGWA, WS_VARIABLES.SPREAD_PNT);
                CEP.TRC(SCCGWA, WS_VARIABLES.SPREAD_PCT);
                WS_VARIABLES.RATE = WS_VARIABLES.RATE * ( 1 + WS_VARIABLES.SPREAD_PCT / 100 ) + WS_VARIABLES.SPREAD_PNT;
                bigD = new BigDecimal(WS_VARIABLES.RATE);
                WS_VARIABLES.RATE = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
            }
            if (WS_VARIABLES.VARIATION_METHOD == '4') {
                CEP.TRC(SCCGWA, WS_VARIABLES.SPREAD_PNT);
                CEP.TRC(SCCGWA, WS_VARIABLES.SPREAD_PCT);
                WS_VARIABLES.RATE = ( WS_VARIABLES.RATE + WS_VARIABLES.SPREAD_PNT ) * ( 1 + WS_VARIABLES.SPREAD_PCT / 100 );
                bigD = new BigDecimal(WS_VARIABLES.RATE);
                WS_VARIABLES.RATE = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            }
        }
    }
    public void B322_GET_NOR_RATE() throws IOException,SQLException,Exception {
        WS_COND_FLG.FOUND_FLG = 'N';
        WS_VARIABLES.RATE_TYPE = 'N';
        WS_VARIABLES.RAT_CHG_DT = 0;
        T00_STARTBR_LNTRATH();
        if (pgmRtn) return;
        T00_READNEXT_LNTRATH();
        if (pgmRtn) return;
        while (WS_COND_FLG.FOUND_FLG != 'N' 
            && LNRRATH.KEY.RAT_CHG_DT <= WS_VARIABLES.VAL_DATE) {
            WS_VARIABLES.RATE = LNRRATH.INT_RAT;
            WS_VARIABLES.LST_CHG_DT = LNRRATH.KEY.RAT_CHG_DT;
            WS_VARIABLES.CINTI_RATE = LNRRATH.BASE_RATE;
            T00_READNEXT_LNTRATH();
            if (pgmRtn) return;
        }
        if (WS_COND_FLG.FOUND_FLG == 'N') {
            WS_VARIABLES.NXT_CHG_DT = 0;
        } else {
            WS_VARIABLES.NXT_CHG_DT = LNRRATH.KEY.RAT_CHG_DT;
        }
        T00_ENDBR_LNTRATH();
        if (pgmRtn) return;
    }
    public void B300_GET_RATN_ACTVDT() throws IOException,SQLException,Exception {
        WS_COND_FLG.FOUND_FLG = 'N';
        T000_GET_LAST_ACTV_DT();
        if (pgmRtn) return;
        WS_VARIABLES.LAST_ACTV_DT = LNRRATN.KEY.ACTV_DT;
        CEP.TRC(SCCGWA, LNRRATN.KEY.ACTV_DT);
    }
    public void B300_GET_RATL_ACTVDT() throws IOException,SQLException,Exception {
        WS_COND_FLG.FOUND_FLG = 'N';
        WS_VARIABLES.RATE_TYPE2 = LNCRATG.COMM_DATA.RATE_TYPE;
        T00_STARTBR_LNTRATL();
        if (pgmRtn) return;
        T00_READNEXT_LNTRATL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRRATL.KEY.ACTV_DT);
        WS_VARIABLES.LAST_ACTV_DT = LNRRATL.KEY.ACTV_DT;
        T00_ENDBR_LNTRATL();
        if (pgmRtn) return;
    }
    public void B410_GET_RATL_ACTVDT() throws IOException,SQLException,Exception {
        WS_COND_FLG.FOUND_FLG = 'N';
        T00_STARTBR_LNTRATL();
        if (pgmRtn) return;
        T00_READNEXT_LNTRATL_1();
        if (pgmRtn) return;
        WS_VARIABLES.LAST_ACTV_DT = LNRRATL.KEY.ACTV_DT;
        T00_ENDBR_LNTRATL();
        if (pgmRtn) return;
    }
    public void R000_GET_CODE_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUINTI);
        if (WS_VARIABLES.RT_CODE.trim().length() > 0) {
            BPCUINTI.BASE_TYP = WS_VARIABLES.RT_TYPE;
            BPCUINTI.TENOR = WS_VARIABLES.RT_CLASS;
            BPCUINTI.CCY = WS_PRIME_CODE.RAT_CCY;
            BPCUINTI.DT = WS_VARIABLES.VAL_DATE;
            if (BPCUINTI.DT > WS_VARIABLES.GWA_AC_DATE) {
                BPCUINTI.DT = WS_VARIABLES.GWA_AC_DATE;
            }
            S000_CALL_BPZUINTI();
            if (pgmRtn) return;
        }
        WS_VARIABLES.RATE = BPCUINTI.OWN_RATE;
    }
    public void R000_GET_TYPE_RATE() throws IOException,SQLException,Exception {
        WS_VARIABLES.RATE = 0;
        if (WS_VARIABLES.RT_TYPE.trim().length() > 0) {
            IBS.init(SCCGWA, BPCCINTI);
            if ((LNCRATG.COMM_DATA.RATE_TYPE == 'N' 
                || LNCRATG.COMM_DATA.RATE_TYPE == 'O' 
                || LNCRATG.COMM_DATA.RATE_TYPE == 'L') 
                && LNCRATNM.REC_DATA.KEY.ACTV_DT < WS_VARIABLES.VAL_DATE 
                && ((LNCRATNM.REC_DATA.RATE_FLG == '1' 
                && LNCRATNM.REC_DATA.FLT_DAY <= 31 
                && LNCRATNM.REC_DATA.FLT_DAY >= 1 
                && LNCRATNM.REC_DATA.FIRST_FLT_FLG == '1') 
                || (LNCRATNM.REC_DATA.RATE_FLG == '2' 
                && LNCRATNM.REC_DATA.FIRST_FLT_FLG == '1'))) {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = WS_VARIABLES.VAL_DATE;
                SCCCLDT.DAYS = -1;
                R00_CAL_DATE();
                if (pgmRtn) return;
                BPCCINTI.BASE_INFO.DT = SCCCLDT.DATE2;
            } else {
                BPCCINTI.BASE_INFO.DT = WS_VARIABLES.VAL_DATE;
            }
            CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.DT);
            BPCCINTI.BASE_INFO.CCY = LNCCONTM.REC_DATA.CCY;
            BPCCINTI.BASE_INFO.BASE_TYP = WS_VARIABLES.RT_TYPE;
            BPCCINTI.BASE_INFO.TENOR = WS_VARIABLES.RT_CLASS;
            BPCCINTI.FUNC = 'I';
            if (WS_VARIABLES.VAL_DATE != WS_VARIABLES.RAT_ACTV_DT 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT") 
                || SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
                S000_CALL_BPZCINTI();
                if (pgmRtn) return;
            } else {
                BPCCINTI.BASE_INFO.RATE = WS_VARIABLES.RAT_BASE_RATE;
            }
            CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.RATE);
            WS_VARIABLES.RATE = BPCCINTI.BASE_INFO.RATE;
            WS_VARIABLES.CINTI_RATE = BPCCINTI.BASE_INFO.RATE;
        }
        CEP.TRC(SCCGWA, "XCXCX");
        CEP.TRC(SCCGWA, WS_VARIABLES.NXT_VAL_DATE);
        CEP.TRC(SCCGWA, WS_VARIABLES.VARIATION_METHOD);
        CEP.TRC(SCCGWA, WS_VARIABLES.RATE);
        if (WS_VARIABLES.VARIATION_METHOD == '1') {
            CEP.TRC(SCCGWA, WS_VARIABLES.SPREAD_PNT);
            WS_VARIABLES.RATE = WS_VARIABLES.RATE + WS_VARIABLES.SPREAD_PNT;
        }
        if (WS_VARIABLES.VARIATION_METHOD == '2') {
            CEP.TRC(SCCGWA, WS_VARIABLES.SPREAD_PCT);
            WS_VARIABLES.RATE = WS_VARIABLES.RATE * ( 1 + WS_VARIABLES.SPREAD_PCT / 100 );
            bigD = new BigDecimal(WS_VARIABLES.RATE);
            WS_VARIABLES.RATE = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
        }
        if (WS_VARIABLES.VARIATION_METHOD == '3') {
            CEP.TRC(SCCGWA, WS_VARIABLES.SPREAD_PNT);
            CEP.TRC(SCCGWA, WS_VARIABLES.SPREAD_PCT);
            WS_VARIABLES.RATE = WS_VARIABLES.RATE * ( 1 + WS_VARIABLES.SPREAD_PCT / 100 ) + WS_VARIABLES.SPREAD_PNT;
            bigD = new BigDecimal(WS_VARIABLES.RATE);
            WS_VARIABLES.RATE = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
        }
        if (WS_VARIABLES.VARIATION_METHOD == '4') {
            CEP.TRC(SCCGWA, WS_VARIABLES.SPREAD_PNT);
            CEP.TRC(SCCGWA, WS_VARIABLES.SPREAD_PCT);
            WS_VARIABLES.RATE = ( WS_VARIABLES.RATE + WS_VARIABLES.SPREAD_PNT ) * ( 1 + WS_VARIABLES.SPREAD_PCT / 100 );
            bigD = new BigDecimal(WS_VARIABLES.RATE);
            WS_VARIABLES.RATE = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
        }
        CEP.TRC(SCCGWA, LNCRATG.COMM_DATA.RATE_TYPE);
    }
    public void R100_MAXMIN_RATE_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "2");
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.MAX_RATE);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.MIN_RATE);
        if (LNCRATNM.REC_DATA.MAX_RATE == 0 
            && LNCRATNM.REC_DATA.MIN_RATE == 0) {
            CEP.TRC(SCCGWA, "11111");
        } else {
            CEP.TRC(SCCGWA, WS_VARIABLES.RATE);
            if ((WS_VARIABLES.RATE > LNCRATNM.REC_DATA.MAX_RATE) 
                || (WS_VARIABLES.RATE < LNCRATNM.REC_DATA.MIN_RATE)) {
                CEP.TRC(SCCGWA, WS_VARIABLES.RATE);
                CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.MIN_RATE);
                CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.MAX_RATE);
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_OUT_MAXMIN_RATE, LNCRATG.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void R100_GEN_CPAR_RATE() throws IOException,SQLException,Exception {
        WS_VARIABLES.RATE = 0;
        WS_VARIABLES.RATE2 = 0;
        WS_VARIABLES.RT_TYPE = LNCRATNM.REC_DATA.INT_RATE_TYPE2;
        WS_VARIABLES.RT_CLASS = LNCRATNM.REC_DATA.INT_RATE_CLAS2;
        WS_VARIABLES.SPREAD_PNT = LNCRATNM.REC_DATA.RATE_VARIATION2;
        WS_VARIABLES.SPREAD_PCT = LNCRATNM.REC_DATA.RATE_PECT2;
        WS_VARIABLES.VARIATION_METHOD = LNCRATNM.REC_DATA.VARIATION_METHOD;
        CEP.TRC(SCCGWA, "333");
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.BASE_RATE2);
        WS_VARIABLES.RAT_BASE_RATE = LNCRATNM.REC_DATA.BASE_RATE2;
        WS_VARIABLES.RAT_ACTV_DT = LNCRATNM.REC_DATA.KEY.ACTV_DT;
        R000_GET_TYPE_RATE();
        if (pgmRtn) return;
        WS_VARIABLES.RATE2 = WS_VARIABLES.RATE;
        CEP.TRC(SCCGWA, "2222222");
        if (WS_VARIABLES.RATE2 != 0) {
            if (WS_COND_FLG.RATE_CPAR_MTH == 'H') {
                if (WS_VARIABLES.RATE2 > LNCRATG.COMM_DATA.RATE) {
                    LNCRATG.COMM_DATA.RATE = WS_VARIABLES.RATE2;
                    LNCRATG.COMM_DATA.STYLE_SEQ = 2;
                }
            }
            if (WS_COND_FLG.RATE_CPAR_MTH == 'L') {
                if (WS_VARIABLES.RATE2 < LNCRATG.COMM_DATA.RATE) {
                    LNCRATG.COMM_DATA.RATE = WS_VARIABLES.RATE2;
                    LNCRATG.COMM_DATA.STYLE_SEQ = 2;
                }
            }
        }
        WS_VARIABLES.RATE = 0;
        WS_VARIABLES.RATE3 = 0;
        WS_VARIABLES.RT_TYPE = LNCRATNM.REC_DATA.INT_RATE_TYPE3;
        CEP.TRC(SCCGWA, WS_VARIABLES.RT_TYPE);
        WS_VARIABLES.RT_CLASS = LNCRATNM.REC_DATA.INT_RATE_CLAS3;
        WS_VARIABLES.SPREAD_PNT = LNCRATNM.REC_DATA.RATE_VARIATION3;
        WS_VARIABLES.SPREAD_PCT = LNCRATNM.REC_DATA.RATE_PECT3;
        WS_VARIABLES.VARIATION_METHOD = LNCRATNM.REC_DATA.VARIATION_METHOD;
        CEP.TRC(SCCGWA, "DF444");
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.BASE_RATE3);
        WS_VARIABLES.RAT_BASE_RATE = LNCRATNM.REC_DATA.BASE_RATE3;
        WS_VARIABLES.RAT_ACTV_DT = LNCRATNM.REC_DATA.KEY.ACTV_DT;
        R000_GET_TYPE_RATE();
        if (pgmRtn) return;
        WS_VARIABLES.RATE3 = WS_VARIABLES.RATE;
        if (WS_COND_FLG.RATE_FLT_FLG == '2') {
            if ((BPCCINTI.BASE_INFO.N_DT != 0) 
                && (BPCCINTI.BASE_INFO.N_DT < WS_VARIABLES.NXT_VAL_DATE)) {
                WS_VARIABLES.NXT_VAL_DATE = BPCCINTI.BASE_INFO.N_DT;
                WS_VARIABLES.NXT_VAR_RTDT = BPCCINTI.BASE_INFO.N_DT;
            }
        }
        if (WS_VARIABLES.RATE3 != 0) {
            if (WS_COND_FLG.RATE_CPAR_MTH == 'H') {
                if (WS_VARIABLES.RATE3 > LNCRATG.COMM_DATA.RATE) {
                    LNCRATG.COMM_DATA.RATE = WS_VARIABLES.RATE3;
                    LNCRATG.COMM_DATA.STYLE_SEQ = 3;
                }
            }
            if (WS_COND_FLG.RATE_CPAR_MTH == 'L') {
                if (WS_VARIABLES.RATE3 < LNCRATG.COMM_DATA.RATE) {
                    LNCRATG.COMM_DATA.RATE = WS_VARIABLES.RATE3;
                    LNCRATG.COMM_DATA.STYLE_SEQ = 3;
                }
            }
        }
        if (WS_COND_FLG.RATE_CPAR_MTH == 'V') {
            WS_VARIABLES.RAT_CNT = 0;
            if (WS_VARIABLES.RATE1 != 0) {
                WS_VARIABLES.RAT_CNT += 1;
            }
            if (WS_VARIABLES.RATE2 != 0) {
                WS_VARIABLES.RAT_CNT += 1;
            }
            if (WS_VARIABLES.RATE3 != 0) {
                WS_VARIABLES.RAT_CNT += 1;
            }
            if (WS_VARIABLES.RAT_CNT == 0) {
                WS_VARIABLES.RAT_CNT = 1;
            }
            LNCRATG.COMM_DATA.RATE = ( WS_VARIABLES.RATE1 + WS_VARIABLES.RATE2 + WS_VARIABLES.RATE3 ) / WS_VARIABLES.RAT_CNT;
            bigD = new BigDecimal(LNCRATG.COMM_DATA.RATE);
            LNCRATG.COMM_DATA.RATE = bigD.setScale(0, RoundingMode.HALF_UP).doubleValue();
            LNCRATG.COMM_DATA.STYLE_SEQ = 9;
        }
        if (LNCRATG.COMM_DATA.RATE_TYPE == 'N') {
            R100_MAXMIN_RATE_CHECK();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.NXT_VAL_DATE);
    }
    public void B310_UPDATE_HIS_RATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCRATG.COMM_DATA.RATE);
        CEP.TRC(SCCGWA, WS_VARIABLES.CUR_RAT);
        CEP.TRC(SCCGWA, LNCRATG.COMM_DATA.RATE);
        CEP.TRC(SCCGWA, WS_VARIABLES.CUR_RAT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (LNCRATG.COMM_DATA.RATE != WS_VARIABLES.CUR_RAT 
            || JIBS_tmp_str[1].equalsIgnoreCase("139122") 
            || WS_COND_FLG.RATH_FND_FLG == 'N') {
            B310_UPDATE_RATE_HIST();
            if (pgmRtn) return;
            WS_COND_FLG.RATH_FND_FLG = 'Y';
        }
        WS_VARIABLES.CUR_RAT = LNCRATG.COMM_DATA.RATE;
    }
    public void B310_UPDATE_RATE_HIST() throws IOException,SQLException,Exception {
        if (LNCRATG.COMM_DATA.RATE < 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_RATE_LT_ZERO, LNCRATG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B311_ADD_RATE_HIST();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRATHM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase("LN1013")) {
            B312_UPD_RATE_HIST();
            if (pgmRtn) return;
        }
    }
    public void B311_ADD_RATE_HIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCRATG.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCRATG.COMM_DATA.SUF_NO);
        CEP.TRC(SCCGWA, LNCRATG.COMM_DATA.RATE_TYPE);
        CEP.TRC(SCCGWA, WS_VARIABLES.VAL_DATE);
        CEP.TRC(SCCGWA, LNCRATG.COMM_DATA.RATE);
        CEP.TRC(SCCGWA, WS_VARIABLES.CINTI_RATE);
        CEP.TRC(SCCGWA, WS_VARIABLES.BASE_RATE);
        WS_PRIME_CODE.RAT_N_L_DT = WS_VARIABLES.VAL_DATE;
        IBS.init(SCCGWA, LNCRATHM);
        LNCRATHM.FUNC = '0';
        LNCRATHM.REC_DATA.RATE_KIND = 'Y';
        LNCRATHM.REC_DATA.KEY.CONTRACT_NO = LNCRATG.COMM_DATA.LN_AC;
        if (LNCRATG.COMM_DATA.SUF_NO.trim().length() == 0) LNCRATHM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCRATHM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCRATG.COMM_DATA.SUF_NO);
        LNCRATHM.REC_DATA.KEY.RATE_TYP = LNCRATG.COMM_DATA.RATE_TYPE;
        LNCRATHM.REC_DATA.KEY.RAT_CHG_DT = WS_VARIABLES.VAL_DATE;
        WS_VARIABLES.CUR_RAT_DT = WS_VARIABLES.VAL_DATE;
        LNCRATHM.REC_DATA.INT_RAT = LNCRATG.COMM_DATA.RATE;
        WS_VARIABLES.CUR_RAT = LNCRATG.COMM_DATA.RATE;
        LNCRATHM.REC_DATA.RATE_FROM_SEQ = LNCRATG.COMM_DATA.STYLE_SEQ;
        LNCRATHM.REC_DATA.BASE_RATE = WS_VARIABLES.CINTI_RATE;
        if (WS_VARIABLES.CINTI_RATE != 0) {
            if (WS_VARIABLES.CINTI_RATE != WS_VARIABLES.BASE_RATE) {
                LNCRATHM.REC_DATA.BASE_RATE_FLG = 'Y';
                WS_VARIABLES.BASE_RATE_FLG = 'Y';
            } else {
                LNCRATHM.REC_DATA.BASE_RATE_FLG = 'N';
                WS_VARIABLES.BASE_RATE_FLG = 'N';
            }
        }
        S000_CALL_LNZRATHM();
        if (pgmRtn) return;
    }
    public void B312_UPD_RATE_HIST() throws IOException,SQLException,Exception {
        WS_VARIABLES.BASE_TYP = LNCRATG.COMM_DATA.RATE_TYPE;
        R00_GET_WS_BASE_RATE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCRATG.COMM_DATA.RATE);
        CEP.TRC(SCCGWA, WS_VARIABLES.CINTI_RATE);
        CEP.TRC(SCCGWA, WS_VARIABLES.BASE_RATE);
        IBS.init(SCCGWA, LNCRATHM);
        LNCRATHM.FUNC = '4';
        LNCRATHM.REC_DATA.KEY.CONTRACT_NO = LNCRATG.COMM_DATA.LN_AC;
        if (LNCRATG.COMM_DATA.SUF_NO.trim().length() == 0) LNCRATHM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCRATHM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCRATG.COMM_DATA.SUF_NO);
        LNCRATHM.REC_DATA.KEY.RATE_TYP = LNCRATG.COMM_DATA.RATE_TYPE;
        LNCRATHM.REC_DATA.KEY.RAT_CHG_DT = WS_VARIABLES.VAL_DATE;
        S000_CALL_LNZRATHM();
        if (pgmRtn) return;
        LNCRATHM.FUNC = '2';
        LNCRATHM.REC_DATA.BASE_RATE = WS_VARIABLES.CINTI_RATE;
        if (WS_VARIABLES.BASE_RATE != WS_VARIABLES.CINTI_RATE) {
            if (WS_VARIABLES.CINTI_RATE != 0) {
                LNCRATHM.REC_DATA.BASE_RATE_FLG = 'Y';
                WS_VARIABLES.BASE_RATE_FLG = 'Y';
            }
        } else {
            LNCRATHM.REC_DATA.BASE_RATE_FLG = 'N';
            WS_VARIABLES.BASE_RATE_FLG = 'N';
        }
        LNCRATHM.REC_DATA.INT_RAT = LNCRATG.COMM_DATA.RATE;
        LNCRATHM.REC_DATA.RATE_FROM_SEQ = LNCRATG.COMM_DATA.STYLE_SEQ;
        S000_CALL_LNZRATHM();
        if (pgmRtn) return;
    }
    public void B500_ICTL_UPD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRATH);
        LNRRATH.KEY.CONTRACT_NO = LNCRATG.COMM_DATA.LN_AC;
        if (LNCRATG.COMM_DATA.SUF_NO.trim().length() == 0) LNRRATH.KEY.SUB_CTA_NO = 0;
        else LNRRATH.KEY.SUB_CTA_NO = Integer.parseInt(LNCRATG.COMM_DATA.SUF_NO);
        LNRRATH.KEY.RATE_TYP = LNCRATG.COMM_DATA.RATE_TYPE;
        LNRRATH.KEY.RAT_CHG_DT = 99990101;
        T000_GET_LAST_RATH();
        if (pgmRtn) return;
        if (LNCRATG.COMM_DATA.RATE_TYPE == 'N') {
            WS_VARIABLES.TMP_N_RAT = LNRRATH.INT_RAT;
            WS_VARIABLES.TMP_RAT_DT = LNRRATH.KEY.RAT_CHG_DT;
            LNRRATH.KEY.RATE_TYP = 'O';
            T000_GET_LAST_RATH();
            if (pgmRtn) return;
            WS_VARIABLES.TMP_O_RAT = LNRRATH.INT_RAT;
            LNRRATH.KEY.RATE_TYP = 'L';
            T000_GET_LAST_RATH();
            if (pgmRtn) return;
            WS_VARIABLES.TMP_L_RAT = LNRRATH.INT_RAT;
            LNRRATH.KEY.RATE_TYP = 'P';
            T000_GET_LAST_RATH();
            if (pgmRtn) return;
            WS_VARIABLES.TMP_P_RAT = LNRRATH.INT_RAT;
        }
        B520_ICTL_UPD_PROCESS();
        if (pgmRtn) return;
    }
    public void B510_GET_LAST_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRATH);
        LNRRATH.KEY.CONTRACT_NO = LNCRATG.COMM_DATA.LN_AC;
        if (LNCRATG.COMM_DATA.SUF_NO.trim().length() == 0) LNRRATH.KEY.SUB_CTA_NO = 0;
        else LNRRATH.KEY.SUB_CTA_NO = Integer.parseInt(LNCRATG.COMM_DATA.SUF_NO);
        LNRRATH.KEY.RATE_TYP = LNCRATG.COMM_DATA.RATE_TYPE;
        CEP.TRC(SCCGWA, "9999-2");
        LNRRATH.KEY.RAT_CHG_DT = 99990101;
        T10_STARTBR_LNTRATH();
        if (pgmRtn) return;
        T10_READNEXT_LNTRATH();
        if (pgmRtn) return;
        T10_ENDBR_LNTRATH();
        if (pgmRtn) return;
    }
    public void B520_ICTL_UPD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCRATG.COMM_DATA.LN_AC;
        if (LNCRATG.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCRATG.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRRATH.INT_RAT);
        CEP.TRC(SCCGWA, LNRRATH.KEY.RAT_CHG_DT);
        CEP.TRC(SCCGWA, WS_VARIABLES.RATL_INT_CHRG_MTH);
        CEP.TRC(SCCGWA, WS_VARIABLES.RATL_INT_CHRG_MTH);
        CEP.TRC(SCCGWA, WS_VARIABLES.CUR_O_RAT);
        CEP.TRC(SCCGWA, WS_VARIABLES.CUR_O_RAT_DT);
        CEP.TRC(SCCGWA, WS_VARIABLES.RATL_INT_CHRG_MTH_O);
        CEP.TRC(SCCGWA, WS_VARIABLES.RATL_INT_CHRG_MTH_L);
        CEP.TRC(SCCGWA, WS_VARIABLES.RATL_INT_CHRG_MTH_P);
        LNCICTLM.FUNC = '2';
        if (LNCRATG.COMM_DATA.RATE_TYPE == 'N') {
            LNCICTLM.REC_DATA.CUR_RAT = WS_VARIABLES.TMP_N_RAT;
            LNCICTLM.REC_DATA.CUR_RAT_DT = WS_VARIABLES.TMP_RAT_DT;
            if (WS_VARIABLES.RATL_INT_CHRG_MTH_O == 'A') {
                LNCICTLM.REC_DATA.CUR_PO_RAT = WS_VARIABLES.TMP_O_RAT;
                LNCICTLM.REC_DATA.CUR_PO_RAT_DT = WS_VARIABLES.TMP_RAT_DT;
            }
            if (WS_VARIABLES.RATL_INT_CHRG_MTH_L == 'A') {
                LNCICTLM.REC_DATA.CUR_IO_RAT = WS_VARIABLES.TMP_L_RAT;
                LNCICTLM.REC_DATA.CUR_IO_RAT_DT = WS_VARIABLES.TMP_RAT_DT;
            }
            if (WS_VARIABLES.RATL_INT_CHRG_MTH_P == 'A') {
                LNCICTLM.REC_DATA.CUR_FO_RAT = WS_VARIABLES.TMP_P_RAT;
                LNCICTLM.REC_DATA.CUR_FO_RAT_DT = WS_VARIABLES.TMP_RAT_DT;
            }
        }
        if (LNCRATG.COMM_DATA.RATE_TYPE == 'O') {
            LNCICTLM.REC_DATA.CUR_PO_RAT = LNRRATH.INT_RAT;
            LNCICTLM.REC_DATA.CUR_PO_RAT_DT = LNRRATH.KEY.RAT_CHG_DT;
        }
        if (LNCRATG.COMM_DATA.RATE_TYPE == 'L') {
            LNCICTLM.REC_DATA.CUR_IO_RAT = LNRRATH.INT_RAT;
            LNCICTLM.REC_DATA.CUR_IO_RAT_DT = LNRRATH.KEY.RAT_CHG_DT;
        }
        if (LNCRATG.COMM_DATA.RATE_TYPE == 'P') {
            LNCICTLM.REC_DATA.CUR_FO_RAT = LNRRATH.INT_RAT;
            LNCICTLM.REC_DATA.CUR_FO_RAT_DT = LNRRATH.KEY.RAT_CHG_DT;
        }
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B230_DELETE_A_O_RATE() throws IOException,SQLException,Exception {
        WS_VARIABLES.O_R_ACTV_DT = LNRRATH.KEY.RAT_CHG_DT;
        WS_VARIABLES.RATE_TYPE2 = 'O';
        B410_GET_RATL_ACTVDT();
        if (pgmRtn) return;
        WS_VARIABLES.RATL_INT_CHRG_MTH_O = LNRRATL.INT_CHRG_MTH;
        if (LNRRATL.INT_CHRG_MTH == 'A') {
            B230_DEL_A_O_RATE_HIST();
            if (pgmRtn) return;
        }
        WS_VARIABLES.O_R_ACTV_DT = LNRRATH.KEY.RAT_CHG_DT;
        WS_VARIABLES.RATE_TYPE2 = 'L';
        WS_VARIABLES.RATL_INT_CHRG_MTH_L = LNRRATL.INT_CHRG_MTH;
        B410_GET_RATL_ACTVDT();
        if (pgmRtn) return;
        if (LNRRATL.INT_CHRG_MTH == 'A') {
            B230_DEL_A_O_RATE_HIST();
            if (pgmRtn) return;
        }
        WS_VARIABLES.O_R_ACTV_DT = LNRRATH.KEY.RAT_CHG_DT;
        WS_VARIABLES.RATE_TYPE2 = 'P';
        B410_GET_RATL_ACTVDT();
        if (pgmRtn) return;
        WS_VARIABLES.RATL_INT_CHRG_MTH_P = LNRRATL.INT_CHRG_MTH;
        if (LNRRATL.INT_CHRG_MTH == 'A') {
            B230_DEL_A_O_RATE_HIST();
            if (pgmRtn) return;
        }
    }
    public void B230_DEL_A_O_RATE_HIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRATHM);
        LNCRATHM.FUNC = '3';
        LNCRATHM.REC_DATA.KEY.CONTRACT_NO = LNCRATG.COMM_DATA.LN_AC;
        if (LNCRATG.COMM_DATA.SUF_NO.trim().length() == 0) LNCRATHM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCRATHM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCRATG.COMM_DATA.SUF_NO);
        LNCRATHM.REC_DATA.KEY.RATE_TYP = LNRRATL.KEY.OVD_KND;
        LNCRATHM.REC_DATA.KEY.RAT_CHG_DT = LNRRATH.KEY.RAT_CHG_DT;
        CEP.TRC(SCCGWA, LNCRATHM.REC_DATA.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNCRATHM.REC_DATA.KEY.SUB_CTA_NO);
        CEP.TRC(SCCGWA, LNCRATHM.REC_DATA.KEY.RATE_TYP);
        CEP.TRC(SCCGWA, LNCRATHM.REC_DATA.KEY.RAT_CHG_DT);
        S000_CALL_LNZRATHM();
        if (pgmRtn) return;
        if (LNCRATHM.RC.RC_RTNCODE == 0) {
            B231_DEL_A_O_RATE_HIST();
            if (pgmRtn) return;
        }
    }
    public void B231_DEL_A_O_RATE_HIST() throws IOException,SQLException,Exception {
        LNCRATHM.FUNC = '4';
        S000_CALL_LNZRATHM();
        if (pgmRtn) return;
        LNCRATHM.FUNC = '1';
        S000_CALL_LNZRATHM();
        if (pgmRtn) return;
    }
    public void B410_UPD_A_O_RATE_HIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRATHM);
        LNCRATHM.FUNC = '3';
        LNCRATHM.REC_DATA.KEY.CONTRACT_NO = LNCRATG.COMM_DATA.LN_AC;
        if (LNCRATG.COMM_DATA.SUF_NO.trim().length() == 0) LNCRATHM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCRATHM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCRATG.COMM_DATA.SUF_NO);
        LNCRATHM.REC_DATA.KEY.RATE_TYP = LNRRATL.KEY.OVD_KND;
        LNCRATHM.REC_DATA.KEY.RAT_CHG_DT = WS_VARIABLES.VAL_DATE;
        S000_CALL_LNZRATHM();
        if (pgmRtn) return;
        R000_GET_WS_RATG_RATE();
        if (pgmRtn) return;
        if (LNCRATHM.RC.RC_RTNCODE == 0) {
            CEP.TRC(SCCGWA, "RECORD THE EXIST LNTRATH------");
            CEP.TRC(SCCGWA, LNCRATHM.REC_DATA.KEY.RAT_CHG_DT);
            B411_UPD_A_O_RATE_HIST();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "RECORD NOT EXIST LNTRATH------");
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRATHM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(ERROR_MSG.LN_ERR_RATH_NOTFND)) {
                B412_ADD_A_O_RATE_HIST();
                if (pgmRtn) return;
            }
        }
        WS_VARIABLES.CUR_O_RAT = LNCRATHM.REC_DATA.INT_RAT;
        WS_VARIABLES.CUR_O_RAT_DT = LNCRATHM.REC_DATA.KEY.RAT_CHG_DT;
    }
    public void B411_UPD_A_O_RATE_HIST() throws IOException,SQLException,Exception {
        WS_VARIABLES.BASE_TYP = LNRRATL.KEY.OVD_KND;
        R00_GET_WS_BASE_RATE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_VARIABLES.CINTI_RATE);
        CEP.TRC(SCCGWA, WS_VARIABLES.RATG_RATE);
        CEP.TRC(SCCGWA, WS_VARIABLES.BASE_RATE_FLG);
        LNCRATHM.FUNC = '4';
        S000_CALL_LNZRATHM();
        if (pgmRtn) return;
        LNCRATHM.FUNC = '2';
        LNCRATHM.REC_DATA.INT_RAT = WS_VARIABLES.RATG_RATE;
        LNCRATHM.REC_DATA.BASE_RATE = WS_VARIABLES.CINTI_RATE;
        LNCRATHM.REC_DATA.BASE_RATE_FLG = WS_VARIABLES.BASE_RATE_FLG;
        S000_CALL_LNZRATHM();
        if (pgmRtn) return;
    }
    public void B412_ADD_A_O_RATE_HIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_VARIABLES.CINTI_RATE);
        CEP.TRC(SCCGWA, WS_VARIABLES.RATG_RATE);
        CEP.TRC(SCCGWA, WS_VARIABLES.BASE_RATE_FLG);
        LNCRATHM.FUNC = '0';
        LNCRATHM.REC_DATA.INT_RAT = WS_VARIABLES.RATG_RATE;
        LNCRATHM.REC_DATA.BASE_RATE = WS_VARIABLES.CINTI_RATE;
        LNCRATHM.REC_DATA.BASE_RATE_FLG = WS_VARIABLES.BASE_RATE_FLG;
        S000_CALL_LNZRATHM();
        if (pgmRtn) return;
    }
    public void R000_GET_WS_RATG_RATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCRATG.COMM_DATA.RATE);
        CEP.TRC(SCCGWA, LNRRATL.DIF_IRAT_PER);
        CEP.TRC(SCCGWA, LNRRATL.DIF_IRAT_PNT);
        WS_VARIABLES.RATG_RATE = LNCRATG.COMM_DATA.RATE;
        CEP.TRC(SCCGWA, "TRC PRIN RATL-VARIATION-METHOD");
        CEP.TRC(SCCGWA, LNRRATL.VARIATION_METHOD);
        if (LNRRATL.VARIATION_METHOD == '0') {
            if (LNRRATL.DIF_IRAT_PER != 0 
                || LNRRATL.DIF_IRAT_PNT != 0) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_E_NO_IN_PEN_PER_PNT, LNCRATG.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else if (LNRRATL.VARIATION_METHOD == '1') {
            WS_VARIABLES.RATG_RATE = WS_VARIABLES.RATG_RATE + LNRRATL.DIF_IRAT_PNT;
            if (LNRRATL.DIF_IRAT_PER != 0) {
                LNRRATL.DIF_IRAT_PER = 0;
            }
        } else if (LNRRATL.VARIATION_METHOD == '2') {
            WS_VARIABLES.RATG_RATE = WS_VARIABLES.RATG_RATE * ( 1 + LNRRATL.DIF_IRAT_PER / 100 );
            bigD = new BigDecimal(WS_VARIABLES.RATG_RATE);
            WS_VARIABLES.RATG_RATE = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            if (LNRRATL.DIF_IRAT_PNT != 0) {
                LNRRATL.DIF_IRAT_PNT = 0;
            }
        } else if (LNRRATL.VARIATION_METHOD == '3') {
            if (LNRRATL.DIF_IRAT_PER == 0 
                || LNRRATL.DIF_IRAT_PNT == 0) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_E_M_IN_PER_PNT, LNCRATG.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                WS_VARIABLES.RATG_RATE = WS_VARIABLES.RATG_RATE * ( 1 + LNRRATL.DIF_IRAT_PER / 100 ) + LNRRATL.DIF_IRAT_PNT;
                bigD = new BigDecimal(WS_VARIABLES.RATG_RATE);
                WS_VARIABLES.RATG_RATE = bigD.setScale(8, RoundingMode.HALF_UP).doubleValue();
            }
        } else if (LNRRATL.VARIATION_METHOD == '4') {
            if (LNRRATL.DIF_IRAT_PER == 0 
                || LNRRATL.DIF_IRAT_PNT == 0) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_E_M_IN_PER_PNT, LNCRATG.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                WS_VARIABLES.RATG_RATE = ( WS_VARIABLES.RATG_RATE + LNRRATL.DIF_IRAT_PNT ) * ( 1 + LNRRATL.DIF_IRAT_PER / 100 );
                bigD = new BigDecimal(WS_VARIABLES.RATG_RATE);
                WS_VARIABLES.RATG_RATE = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            }
        } else {
            LNRRATL.VARIATION_METHOD = '0';
        }
        CEP.TRC(SCCGWA, "END-PNTPER");
        CEP.TRC(SCCGWA, WS_VARIABLES.RATG_RATE);
    }
    public void R00_GET_WS_BASE_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRATH);
        LNRRATH.KEY.CONTRACT_NO = LNCRATG.COMM_DATA.LN_AC;
        if (LNCRATG.COMM_DATA.SUF_NO.trim().length() == 0) LNRRATH.KEY.SUB_CTA_NO = 0;
        else LNRRATH.KEY.SUB_CTA_NO = Integer.parseInt(LNCRATG.COMM_DATA.SUF_NO);
        LNRRATH.KEY.RATE_TYP = WS_VARIABLES.BASE_TYP;
        LNRRATH.KEY.RAT_CHG_DT = LNCRATG.COMM_DATA.VAL_DATE;
        CEP.TRC(SCCGWA, LNRRATH.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRRATH.KEY.RATE_TYP);
        CEP.TRC(SCCGWA, LNRRATH.KEY.RAT_CHG_DT);
        LNTRATH_RD = new DBParm();
        LNTRATH_RD.TableName = "LNTRATH";
        LNTRATH_RD.where = "CONTRACT_NO = :LNRRATH.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRRATH.KEY.SUB_CTA_NO "
            + "AND RATE_TYP = :LNRRATH.KEY.RATE_TYP "
            + "AND RAT_CHG_DT < :LNRRATH.KEY.RAT_CHG_DT";
        LNTRATH_RD.fst = true;
        LNTRATH_RD.order = "RAT_CHG_DT DESC";
        IBS.READ(SCCGWA, LNRRATH, this, LNTRATH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, LNRRATH.KEY.RAT_CHG_DT);
        CEP.TRC(SCCGWA, LNRRATH.BASE_RATE);
        WS_VARIABLES.BASE_RATE = LNRRATH.BASE_RATE;
    }
    public void T000_GET_LAST_RATH() throws IOException,SQLException,Exception {
        LNTRATH_RD = new DBParm();
        LNTRATH_RD.TableName = "LNTRATH";
        LNTRATH_RD.where = "CONTRACT_NO = :LNRRATH.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRRATH.KEY.SUB_CTA_NO "
            + "AND RATE_TYP = :LNRRATH.KEY.RATE_TYP "
            + "AND RAT_CHG_DT <= :LNRRATH.KEY.RAT_CHG_DT";
        LNTRATH_RD.fst = true;
        LNTRATH_RD.order = "RAT_CHG_DT DESC";
        IBS.READ(SCCGWA, LNRRATH, this, LNTRATH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "FOUND IT ------------");
            WS_COND_FLG.FOUND_FLG = 'Y';
        } else {
            CEP.TRC(SCCGWA, "GWA-DBIO-NURMAL NOT TRUE-----");
            WS_COND_FLG.FOUND_FLG = 'N';
            if (LNCRATG.COMM_DATA.RATE_TYPE == 'N') {
                LNRRATH.INT_RAT = LNCICTLM.REC_DATA.CUR_RAT;
                LNRRATH.KEY.RAT_CHG_DT = LNCICTLM.REC_DATA.CUR_RAT_DT;
            }
            if (LNCRATG.COMM_DATA.RATE_TYPE == 'O') {
                LNRRATH.INT_RAT = LNCICTLM.REC_DATA.CUR_PO_RAT;
                LNRRATH.KEY.RAT_CHG_DT = LNCICTLM.REC_DATA.CUR_PO_RAT_DT;
            }
            if (LNCRATG.COMM_DATA.RATE_TYPE == 'L') {
                LNRRATH.INT_RAT = LNCICTLM.REC_DATA.CUR_IO_RAT;
                LNRRATH.KEY.RAT_CHG_DT = LNCICTLM.REC_DATA.CUR_IO_RAT_DT;
            }
            if (LNCRATG.COMM_DATA.RATE_TYPE == 'P') {
                LNRRATH.INT_RAT = LNCICTLM.REC_DATA.CUR_FO_RAT;
                LNRRATH.KEY.RAT_CHG_DT = LNCICTLM.REC_DATA.CUR_FO_RAT_DT;
            }
        }
    }
    public void T10_STARTBR_LNTRATH() throws IOException,SQLException,Exception {
        LNTRATH_BR.rp = new DBParm();
        LNTRATH_BR.rp.TableName = "LNTRATH";
        LNTRATH_BR.rp.where = "CONTRACT_NO = :LNRRATH.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRRATH.KEY.SUB_CTA_NO "
            + "AND RATE_TYP = :LNRRATH.KEY.RATE_TYP "
            + "AND RAT_CHG_DT <= :LNRRATH.KEY.RAT_CHG_DT";
        LNTRATH_BR.rp.order = "RAT_CHG_DT DESC";
        IBS.STARTBR(SCCGWA, LNRRATH, this, LNTRATH_BR);
    }
    public void T10_READNEXT_LNTRATH() throws IOException,SQLException,Exception {
        WS_COND_FLG.FOUND_FLG = ' ';
        IBS.READNEXT(SCCGWA, LNRRATH, this, LNTRATH_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "FOUND IT ------------");
            WS_COND_FLG.FOUND_FLG = 'Y';
        } else {
            CEP.TRC(SCCGWA, "GWA-DBIO-NURMAL NOT TRUE-----");
            WS_COND_FLG.FOUND_FLG = 'N';
            if (LNCRATG.COMM_DATA.RATE_TYPE == 'N') {
                LNRRATH.INT_RAT = LNCICTLM.REC_DATA.CUR_RAT;
                LNRRATH.KEY.RAT_CHG_DT = LNCICTLM.REC_DATA.CUR_RAT_DT;
            }
            if (LNCRATG.COMM_DATA.RATE_TYPE == 'O') {
                LNRRATH.INT_RAT = LNCICTLM.REC_DATA.CUR_PO_RAT;
                LNRRATH.KEY.RAT_CHG_DT = LNCICTLM.REC_DATA.CUR_PO_RAT_DT;
            }
            if (LNCRATG.COMM_DATA.RATE_TYPE == 'L') {
                LNRRATH.INT_RAT = LNCICTLM.REC_DATA.CUR_IO_RAT;
                LNRRATH.KEY.RAT_CHG_DT = LNCICTLM.REC_DATA.CUR_IO_RAT_DT;
            }
            if (LNCRATG.COMM_DATA.RATE_TYPE == 'P') {
                LNRRATH.INT_RAT = LNCICTLM.REC_DATA.CUR_FO_RAT;
                LNRRATH.KEY.RAT_CHG_DT = LNCICTLM.REC_DATA.CUR_FO_RAT_DT;
            }
        }
    }
    public void T10_ENDBR_LNTRATH() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTRATH_BR);
    }
    public void T00_STARTBR_LNTRATH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRATH);
        LNRRATH.KEY.CONTRACT_NO = LNCRATG.COMM_DATA.LN_AC;
        if (LNCRATG.COMM_DATA.SUF_NO.trim().length() == 0) LNRRATH.KEY.SUB_CTA_NO = 0;
        else LNRRATH.KEY.SUB_CTA_NO = Integer.parseInt(LNCRATG.COMM_DATA.SUF_NO);
        LNRRATH.KEY.RATE_TYP = WS_VARIABLES.RATE_TYPE;
        LNRRATH.KEY.RAT_CHG_DT = WS_VARIABLES.RAT_CHG_DT;
        LNTRATH_BR.rp = new DBParm();
        LNTRATH_BR.rp.TableName = "LNTRATH";
        LNTRATH_BR.rp.where = "CONTRACT_NO = :LNRRATH.KEY.CONTRACT_NO "
            + "AND SUB_CTA_NO = :LNRRATH.KEY.SUB_CTA_NO "
            + "AND RATE_TYP = :LNRRATH.KEY.RATE_TYP "
            + "AND RAT_CHG_DT >= :LNRRATH.KEY.RAT_CHG_DT";
        LNTRATH_BR.rp.order = "RAT_CHG_DT";
        IBS.STARTBR(SCCGWA, LNRRATH, this, LNTRATH_BR);
    }
    public void T00_READNEXT_LNTRATH() throws IOException,SQLException,Exception {
        WS_COND_FLG.FOUND_FLG = ' ';
        IBS.READNEXT(SCCGWA, LNRRATH, this, LNTRATH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.FOUND_FLG = 'Y';
        } else {
            WS_COND_FLG.FOUND_FLG = 'N';
        }
    }
    public void T00_ENDBR_LNTRATH() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTRATH_BR);
    }
    public void T00_READ_LNTRATN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRATN);
        LNRRATN.KEY.CONTRACT_NO = LNCRATG.COMM_DATA.LN_AC;
        LNRRATN.KEY.ACTV_DT = LNCRATG.COMM_DATA.VAL_DATE;
        LNTRATN_RD = new DBParm();
        LNTRATN_RD.TableName = "LNTRATN";
        LNTRATN_RD.where = "CONTRACT_NO = :LNRRATN.KEY.CONTRACT_NO "
            + "AND ACTV_DT <= :LNRRATN.KEY.ACTV_DT";
        LNTRATN_RD.fst = true;
        LNTRATN_RD.order = "ACTV_DT DESC";
        IBS.READ(SCCGWA, LNRRATN, this, LNTRATN_RD);
    }
    public void T000_GET_LAST_ACTV_DT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRATN);
        LNRRATN.KEY.CONTRACT_NO = LNCRATG.COMM_DATA.LN_AC;
        LNRRATN.KEY.ACTV_DT = LNCRATG.COMM_DATA.VAL_DATE;
        LNTRATN_RD = new DBParm();
        LNTRATN_RD.TableName = "LNTRATN";
        LNTRATN_RD.where = "CONTRACT_NO = :LNRRATN.KEY.CONTRACT_NO "
            + "AND ACTV_DT <= :LNRRATN.KEY.ACTV_DT";
        LNTRATN_RD.fst = true;
        LNTRATN_RD.order = "ACTV_DT DESC";
        IBS.READ(SCCGWA, LNRRATN, this, LNTRATN_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.FOUND_FLG = 'Y';
        } else {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_RATN_NOTFND, LNCRATG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T00_STARTBR_LNTRATN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRATN);
        LNRRATN.KEY.CONTRACT_NO = LNCRATG.COMM_DATA.LN_AC;
        LNRRATN.KEY.ACTV_DT = LNCRATG.COMM_DATA.VAL_DATE;
        LNTRATN_BR.rp = new DBParm();
        LNTRATN_BR.rp.TableName = "LNTRATN";
        LNTRATN_BR.rp.where = "CONTRACT_NO = :LNRRATN.KEY.CONTRACT_NO "
            + "AND ACTV_DT <= :LNRRATN.KEY.ACTV_DT";
        LNTRATN_BR.rp.order = "ACTV_DT DESC";
        IBS.STARTBR(SCCGWA, LNRRATN, this, LNTRATN_BR);
    }
    public void T00_READNEXT_LNTRATN() throws IOException,SQLException,Exception {
        WS_COND_FLG.FOUND_FLG = ' ';
        IBS.READNEXT(SCCGWA, LNRRATN, this, LNTRATN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.FOUND_FLG = 'Y';
        } else {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_RATN_NOTFND, LNCRATG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T00_ENDBR_LNTRATN() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTRATN_BR);
    }
    public void T00_STARTBR_LNTRATL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRATL);
        LNRRATL.KEY.CONTRACT_NO = LNCRATG.COMM_DATA.LN_AC;
        LNRRATL.KEY.OVD_KND = WS_VARIABLES.RATE_TYPE2;
        LNRRATL.KEY.ACTV_DT = WS_VARIABLES.O_R_ACTV_DT;
        CEP.TRC(SCCGWA, LNRRATL.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNCRATG.COMM_DATA.RATE_TYPE);
        CEP.TRC(SCCGWA, LNRRATL.KEY.ACTV_DT);
        LNTRATL_BR.rp = new DBParm();
        LNTRATL_BR.rp.TableName = "LNTRATL";
        LNTRATL_BR.rp.where = "CONTRACT_NO = :LNRRATL.KEY.CONTRACT_NO "
            + "AND OVD_KND = :LNRRATL.KEY.OVD_KND "
            + "AND ACTV_DT <= :LNRRATL.KEY.ACTV_DT";
        LNTRATL_BR.rp.order = "ACTV_DT DESC";
        IBS.STARTBR(SCCGWA, LNRRATL, this, LNTRATL_BR);
    }
    public void T00_READNEXT_LNTRATL() throws IOException,SQLException,Exception {
        WS_COND_FLG.FOUND_FLG = ' ';
        IBS.READNEXT(SCCGWA, LNRRATL, this, LNTRATL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.FOUND_FLG = 'Y';
        } else {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_RATL_NOTFND, LNCRATG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNRRATL.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRRATL.KEY.OVD_KND);
        CEP.TRC(SCCGWA, LNRRATL.KEY.ACTV_DT);
    }
    public void T00_READNEXT_LNTRATL_1() throws IOException,SQLException,Exception {
        WS_COND_FLG.FOUND_FLG = ' ';
        IBS.READNEXT(SCCGWA, LNRRATL, this, LNTRATL_BR);
    }
    public void T00_ENDBR_LNTRATL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTRATL_BR);
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCONTM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCRATG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPPMQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-PRD-PRM-INQ", LNCPPMQ);
        if (LNCPPMQ.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCPPMQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCRATG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            LNCRATG.RC.RC_APP = LNCICTLM.RC.RC_APP;
            LNCRATG.RC.RC_RTNCODE = LNCICTLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRATNM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-RATN-MAINT", LNCRATNM);
        if (LNCRATNM.RC.RC_RTNCODE != 0) {
            LNCRATG.RC.RC_APP = LNCRATNM.RC.RC_APP;
            LNCRATG.RC.RC_RTNCODE = LNCRATNM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRATLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-RATL-MAINT", LNCRATLM);
        if (LNCRATLM.RC.RC_RTNCODE != 0) {
            LNCRATG.RC.RC_APP = LNCRATLM.RC.RC_APP;
            LNCRATG.RC.RC_RTNCODE = LNCRATLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRATHM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-RATH-MAINT", LNCRATHM);
    }
    public void S000_CALL_BPZUINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-INTR-INQ", BPCUINTI);
        if (BPCUINTI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUINTI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCRATG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZCINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-C-INTR-INQ", BPCCINTI);
        if (BPCCINTI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCINTI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCRATG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R00_CAL_DATE() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            LNCRATG.RC.RC_APP = "SC";
            LNCRATG.RC.RC_RTNCODE = SCCCLDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_RATN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRATN);
        LNRRATN.KEY.CONTRACT_NO = LNCRATG.COMM_DATA.LN_AC;
        LNRRATN.KEY.ACTV_DT = LNCRATG.COMM_DATA.VAL_DATE;
        CEP.TRC(SCCGWA, LNRRATN.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRRATN.KEY.ACTV_DT);
        LNTRATN_RD = new DBParm();
        LNTRATN_RD.TableName = "LNTRATN";
        LNTRATN_RD.where = "CONTRACT_NO = :LNRRATN.KEY.CONTRACT_NO "
            + "AND ACTV_DT <= :LNRRATN.KEY.ACTV_DT";
        LNTRATN_RD.fst = true;
        LNTRATN_RD.order = "ACTV_DT DESC";
        IBS.READ(SCCGWA, LNRRATN, this, LNTRATN_RD);
        CEP.TRC(SCCGWA, LNRRATN.KEY.ACTV_DT);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_RATN_NOTFND, LNCRATG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_PRIME_CODE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_PRIME_CODE.ERR_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCRATG.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCRATG=");
            CEP.TRC(SCCGWA, LNCRATG);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
