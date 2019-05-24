package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZURTN {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char LNZURTN_FILLER1 = ' ';
    int WS_I = 0;
    char WS_TYPE = ' ';
    short WS_TERM = 0;
    double WS_P_AMT = 0;
    double WS_I_AMT = 0;
    double WS_O_AMT = 0;
    double WS_L_AMT = 0;
    double WS_F_AMT = 0;
    int WS_LAST_TX_SEQ = 0;
    short WS_P_CUR_TERM = 0;
    short WS_IC_CUR_TERM = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNRLOAN LNRLOAN = new LNRLOAN();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCRATNM LNCRATNM = new LNCRATNM();
    SCCGWA SCCGWA;
    LNCURTN LNCURTN;
    public void MP(SCCGWA SCCGWA, LNCURTN LNCURTN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCURTN = LNCURTN;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZURTN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCURTN.RC.RC_APP = "LN";
        LNCURTN.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B100_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
    }
    public void B100_MAIN_PROC() throws IOException,SQLException,Exception {
        if (LNCURTN.FUNC == 'A') {
            IBS.init(SCCGWA, LNCRATNM);
            B210_PRE_PROCESS();
            if (pgmRtn) return;
            LNCRATNM.FUNC = '0';
            LNCRATNM.REC_DATA.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            LNCRATNM.REC_DATA.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            S000_CALL_LNZRATNM();
            if (pgmRtn) return;
        } else if (LNCURTN.FUNC == 'M') {
            IBS.init(SCCGWA, LNCRATNM);
            LNCRATNM.REC_DATA.KEY.CONTRACT_NO = LNCURTN.REC_DATA.KEY.LN_AC;
            LNCRATNM.REC_DATA.KEY.ACTV_DT = LNCURTN.REC_DATA.KEY.ACTV_DT;
            LNCRATNM.FUNC = '4';
            S000_CALL_LNZRATNM();
            if (pgmRtn) return;
            B210_PRE_PROCESS();
            if (pgmRtn) return;
            LNCRATNM.FUNC = '2';
            S000_CALL_LNZRATNM();
            if (pgmRtn) return;
        } else if (LNCURTN.FUNC == 'D') {
            IBS.init(SCCGWA, LNCRATNM);
            B210_PRE_PROCESS();
            if (pgmRtn) return;
            LNCRATNM.FUNC = '1';
            S000_CALL_LNZRATNM();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCURTN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B210_PRE_PROCESS() throws IOException,SQLException,Exception {
        LNCRATNM.REC_DATA.KEY.CONTRACT_NO = LNCURTN.REC_DATA.KEY.LN_AC;
        LNCRATNM.REC_DATA.KEY.ACTV_DT = LNCURTN.REC_DATA.KEY.ACTV_DT;
        LNCRATNM.REC_DATA.RATE_FLG = LNCURTN.REC_DATA.RATE_FLG;
        LNCRATNM.REC_DATA.FLT_PERD = LNCURTN.FTL_DATA.FLT_PERD;
        LNCRATNM.REC_DATA.FLT_PERD_UNIT = LNCURTN.FTL_DATA.FLT_PERD_UNIT;
        LNCRATNM.REC_DATA.FIRST_FLT_FLG = LNCURTN.REC_DATA.FIRST_FLT_FLG;
        LNCRATNM.REC_DATA.FLT_DAY = LNCURTN.REC_DATA.FLT_DAY;
        LNCRATNM.REC_DATA.FLT_GAP_PERD = LNCURTN.REC_DATA.FLT_GAP_PERD;
        LNCRATNM.REC_DATA.FLT_DTADJ_FLG = LNCURTN.REC_DATA.CHN_TYP;
        LNCRATNM.REC_DATA.FST_FLT_DT = LNCURTN.REC_DATA.FST_FLT_DT;
        LNCRATNM.REC_DATA.NEXT_FLT_DT = LNCURTN.FTL_DATA.NEXT_FLT_DT;
        CEP.TRC(SCCGWA, LNCURTN.REC_DATA.FST_FLT_DT);
        CEP.TRC(SCCGWA, LNCRATNM.REC_DATA.FST_FLT_DT);
        LNCRATNM.REC_DATA.VARIATION_METHOD = LNCURTN.REC_DATA.VARIATION_METHOD;
        LNCRATNM.REC_DATA.INT_RATE_TYPE1 = LNCURTN.REC_DATA.INT_RATE_TYPE1;
        LNCRATNM.REC_DATA.INT_RATE_CLAS1 = LNCURTN.REC_DATA.INT_RATE_CLAS1;
        LNCRATNM.REC_DATA.RATE_VARIATION1 = LNCURTN.REC_DATA.RATE_VARIATION1;
        LNCRATNM.REC_DATA.RATE_PECT1 = LNCURTN.REC_DATA.RATE_PECT1;
        LNCRATNM.REC_DATA.BASE_RATE1 = LNCURTN.REC_DATA.BASE_RATE1;
        LNCRATNM.REC_DATA.COMPARISON_METHOD = LNCURTN.REC_DATA.COMPARISON_METHOD;
        LNCRATNM.REC_DATA.MAX_RATE = LNCURTN.REC_DATA.MAX_RATE;
        LNCRATNM.REC_DATA.MIN_RATE = LNCURTN.REC_DATA.MIN_RATE;
        LNCRATNM.REC_DATA.INT_RATE_TYPE2 = LNCURTN.REC_DATA.INT_RATE_TYPE2;
        LNCRATNM.REC_DATA.INT_RATE_CLAS2 = LNCURTN.REC_DATA.INT_RATE_CLAS2;
        LNCRATNM.REC_DATA.RATE_VARIATION2 = LNCURTN.REC_DATA.RATE_VARIATION2;
        LNCRATNM.REC_DATA.RATE_PECT2 = LNCURTN.REC_DATA.RATE_PECT2;
        LNCRATNM.REC_DATA.BASE_RATE2 = LNCURTN.REC_DATA.BASE_RATE2;
        LNCRATNM.REC_DATA.INT_RATE_TYPE3 = LNCURTN.REC_DATA.INT_RATE_TYPE3;
        LNCRATNM.REC_DATA.INT_RATE_CLAS3 = LNCURTN.REC_DATA.INT_RATE_CLAS3;
        LNCRATNM.REC_DATA.RATE_VARIATION3 = LNCURTN.REC_DATA.RATE_VARIATION3;
        LNCRATNM.REC_DATA.RATE_PECT3 = LNCURTN.REC_DATA.RATE_PECT3;
        LNCRATNM.REC_DATA.BASE_RATE3 = LNCURTN.REC_DATA.BASE_RATE3;
        LNCRATNM.REC_DATA.COST_OF_FUND_RATE = LNCURTN.REC_DATA.COST_OF_FUND_RATE;
        LNCRATNM.REC_DATA.INTEREST_BASE_MEMO = LNCURTN.REC_DATA.INTEREST_BASE_MEMO;
        LNCRATNM.REC_DATA.PREMIUM_RATE_TYPE = LNCURTN.REC_DATA.PREMIUM_RATE_TYPE;
        LNCRATNM.REC_DATA.PREMIUM_RATE_CLAS = LNCURTN.REC_DATA.PREMIUM_RATE_CLAS;
        LNCRATNM.REC_DATA.PREMIUM_RATE = LNCURTN.REC_DATA.PREMIUM_RATE;
        LNCRATNM.REC_DATA.ADD_ON_RATE = LNCURTN.REC_DATA.ADD_ON_RATE;
        LNCRATNM.REC_DATA.ALL_IN_RATE = LNCURTN.REC_DATA.ALL_IN_RATE;
    }
    public void S000_CALL_LNZRATNM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-RATN-MAINT", LNCRATNM);
        if (LNCRATNM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRATNM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCURTN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCURTN.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCURTN=");
            CEP.TRC(SCCGWA, LNCURTN);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
