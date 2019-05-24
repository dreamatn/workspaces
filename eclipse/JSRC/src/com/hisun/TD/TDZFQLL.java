package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCWA;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDZFQLL {
    String JIBS_tmp_str[] = new String[10];
    brParm TDTRACD_BR = new brParm();
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "TD810";
    String K_AP_MMO = "TD";
    String K_PRDP_TYP = "PRDPR";
    String K_HIS_FMT = "TDCPRDP";
    String K_HIS_RMK = "TD PRODUCT PARM MAINTENANCE";
    String K_SYS_ERR = "SC6001";
    short K_CCY_CNT = 12;
    String WS_MSGID = " ";
    TDZFQLL_CP_PROD_CD CP_PROD_CD = new TDZFQLL_CP_PROD_CD();
    short WS_I = 0;
    int WS_NUM = 0;
    int WS_CNT1 = 0;
    short WS_J = 0;
    short WS_CNT = 0;
    TDZFQLL_WS_CCY_INF[] WS_CCY_INF = new TDZFQLL_WS_CCY_INF[32];
    char WS_RACD_FLG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    TDCQPRD TDCQPRD = new TDCQPRD();
    TDC5000 TDC5000 = new TDC5000();
    TDCPRDP TDCPRDP = new TDCPRDP();
    TDCOFQLL TDCOFQLL = new TDCOFQLL();
    TDRRACD TDRRACD = new TDRRACD();
    BPCCINTI BPCCINTI = new BPCCINTI();
    TDCPROD TDCPROD = new TDCPROD();
    TDCQPMP TDCQPMP = new TDCQPMP();
    SCCGWA SCCGWA;
    SCCCWA SCCCWA;
    TDCIFQLL TDCIFQLL;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public TDZFQLL() {
        for (int i=0;i<32;i++) WS_CCY_INF[i] = new TDZFQLL_WS_CCY_INF();
    }
    public void MP(SCCGWA SCCGWA, TDCIFQLL TDCIFQLL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCIFQLL = TDCIFQLL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZFQLL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        CEP.TRC(SCCGWA, TDCIFQLL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        B200_GET_MSG_PROC();
        if (pgmRtn) return;
        B300_OUT_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCIFQLL.PROD_CODE[1-1].PROD_CD);
        if (TDCIFQLL.PROD_CODE[1-1].PROD_CD.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PRODUCT_MUST_INPUT);
        }
    }
    public void B200_GET_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCIFQLL);
        IBS.init(SCCGWA, TDCOFQLL);
        WS_NUM = 1;
        while (WS_NUM <= 5 
            && TDCIFQLL.PROD_CODE[WS_NUM-1].PROD_CD.trim().length() != 0) {
            CEP.TRC(SCCGWA, WS_NUM);
            CEP.TRC(SCCGWA, TDCIFQLL.PROD_CODE[WS_NUM-1].PROD_CD);
            IBS.init(SCCGWA, TDCQPMP);
            TDCQPMP.FUNC = 'I';
            TDCQPMP.PROD_CD = TDCIFQLL.PROD_CODE[WS_NUM-1].PROD_CD;
            TDCQPMP.DAT_PTR = TDCPROD;
            S000_CALL_TDZQPMP();
            if (pgmRtn) return;
            TDCPRDP.PRDMO_CD = TDCPROD.PRDO_CDM;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.TXN_PRM);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.TXN_PRM);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.INT_PRM);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.INT_PRM);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.EXP_PRM);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.EXP_PRM);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.OTH_PRM);
            CEP.TRC(SCCGWA, TDCPRDP);
            CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM.CCY_INF[1-1].RAT_CD);
            CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM.CCY_INF[1-1].TERM);
            CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM.CCY_INF[1-1].RUL_CD.RUL_CD1);
            CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM.CCY_INF[1-1].RUL_CD.RUL_CD2);
            CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM.CCY_INF[1-1].RUL_CD.RUL_CD3);
            CEP.TRC(SCCGWA, TDCPRDP.OTH_PRM.CCY_INF[1-1].RUL_CD.RUL_CD4);
            TDRRACD.KEY.RUL_CD = TDCPRDP.OTH_PRM.CCY_INF[1-1].RUL_CD.RUL_CD1;
            WS_CNT1 = 1;
            T000_STARTBR_TDTRACD();
            if (pgmRtn) return;
            T000_READNEXT_TDTRACD();
            if (pgmRtn) return;
            while (WS_RACD_FLG != 'N') {
                CEP.TRC(SCCGWA, TDRRACD.PCT_S);
                CEP.TRC(SCCGWA, TDRRACD.BAL);
                CEP.TRC(SCCGWA, TDRRACD.TERM);
                CEP.TRC(SCCGWA, TDRRACD.TERM);
                CEP.TRC(SCCGWA, TDRRACD.PCT_S);
                IBS.init(SCCGWA, BPCCINTI);
                BPCCINTI.FUNC = 'I';
                BPCCINTI.BASE_INFO.TENOR = TDRRACD.TERM;
                BPCCINTI.BASE_INFO.CCY = "156";
                BPCCINTI.BASE_INFO.BASE_TYP = TDCPRDP.OTH_PRM.CCY_INF[1-1].RAT_CD;
                BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
                BPCCINTI.BASE_INFO.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.BASE_TYP);
                CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.CCY);
                CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.TENOR);
                CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.DT);
                CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.BR);
                S000_CALL_BPZCINTI();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.RATE);
                CEP.TRC(SCCGWA, TDRRACD.PCT_S);
                CEP.TRC(SCCGWA, TDRRACD.FLT_RAT);
                if (TDRRACD.PCT_S != 0) {
                    BPCCINTI.BASE_INFO.RATE = BPCCINTI.BASE_INFO.RATE + BPCCINTI.BASE_INFO.RATE * TDRRACD.PCT_S / 100;
                }
                if (TDRRACD.FLT_RAT != 0) {
                    BPCCINTI.BASE_INFO.RATE = BPCCINTI.BASE_INFO.RATE + TDRRACD.FLT_RAT;
                }
                TDCOFQLL.PROD_INF[WS_CNT1-1].MIN_AMT = TDRRACD.BAL;
                TDCOFQLL.PROD_INF[WS_CNT1-1].PROD_CD = TDCIFQLL.PROD_CODE[WS_NUM-1].PROD_CD;
                TDCOFQLL.PROD_INF[WS_CNT1-1].INT_RAT = BPCCINTI.BASE_INFO.RATE;
                CEP.TRC(SCCGWA, TDCOFQLL.PROD_INF[WS_CNT1-1].MIN_AMT);
                CEP.TRC(SCCGWA, TDCOFQLL.PROD_INF[WS_CNT1-1].MAX_AMT);
                CEP.TRC(SCCGWA, TDCOFQLL.PROD_INF[WS_CNT1-1].PROD_CD);
                CEP.TRC(SCCGWA, TDCOFQLL.PROD_INF[WS_CNT1-1].INT_RAT);
                T000_READNEXT_TDTRACD();
                if (pgmRtn) return;
                WS_CNT1 += 1;
            }
            WS_NUM += 1;
        }
    }
    public void T000_READNEXT_TDTRACD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRRACD, this, TDTRACD_BR);
        CEP.TRC(SCCGWA, TDRRACD.KEY.RUL_CD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_RACD_FLG = 'Y';
        } else {
            WS_RACD_FLG = 'N';
        }
    }
    public void T000_STARTBR_TDTRACD() throws IOException,SQLException,Exception {
        TDTRACD_BR.rp = new DBParm();
        TDTRACD_BR.rp.TableName = "TDTRACD";
        TDTRACD_BR.rp.where = "RUL_CD = :TDRRACD.KEY.RUL_CD";
        TDTRACD_BR.rp.order = "NUM";
        IBS.STARTBR(SCCGWA, TDRRACD, this, TDTRACD_BR);
    }
    public void S000_CALL_TDZQPMP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-BASE-PRD-QRY", TDCQPMP);
        CEP.TRC(SCCGWA, TDCQPMP.RC.RC_RTNCODE);
        if (TDCQPMP.RC.RC_RTNCODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, TDCQPMP.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
    }
    public void B300_OUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCOFQLL);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = TDCOFQLL;
        SCCFMT.DATA_LEN = 285;
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM, true);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZCINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-C-INTR-INQ", BPCCINTI, true);
        CEP.TRC(SCCGWA, BPCCINTI.RC);
        if (BPCCINTI.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCCINTI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
