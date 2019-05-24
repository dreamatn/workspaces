package com.hisun.DD;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZIPRRE {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_CTL_PRM_TYP = "PRDPR";
    String K_RAT_PRM_TYP = "PDRAT";
    short WS_CNT = 0;
    DDZIPRRE_WS_CCY_RATE_KEY WS_CCY_RATE_KEY = new DDZIPRRE_WS_CCY_RATE_KEY();
    DDZIPRRE_WS_PROD_TIR_INF[] WS_PROD_TIR_INF = new DDZIPRRE_WS_PROD_TIR_INF[5];
    char WS_EFF_FLG = ' ';
    char WS_CCY_FOUND_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    DDCUPARM DDCUPARM = new DDCUPARM();
    DDCITIRR DDCITIRR = new DDCITIRR();
    DDCUPRAT DDCUPRAT = new DDCUPRAT();
    SCCGWA SCCGWA;
    DDCIPRRE DDCIPRRE;
    public DDZIPRRE() {
        for (int i=0;i<5;i++) WS_PROD_TIR_INF[i] = new DDZIPRRE_WS_PROD_TIR_INF();
    }
    public void MP(SCCGWA SCCGWA, DDCIPRRE DDCIPRRE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCIPRRE = DDCIPRRE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZIPRRE return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCIPRRE.PARM_CODE);
        CEP.TRC(SCCGWA, DDCIPRRE.CCY);
        if (DDCIPRRE.PARM_CODE.trim().length() == 0 
            || DDCIPRRE.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_PROD_NOT_FND, DDCIPRRE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B030_QUERY_PRD_PROC();
        if (pgmRtn) return;
        B050_CHECK_CCY_PROC();
        if (pgmRtn) return;
        B070_CHK_CCY_RAT_PROC();
        if (pgmRtn) return;
        B090_CONFIRM_RATE_PROC();
        if (pgmRtn) return;
    }
    public void B030_QUERY_PRD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUPARM);
        IBS.init(SCCGWA, DDVMPRD);
        DDCUPARM.TX_TYPE = 'I';
        DDCUPARM.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        DDCUPARM.DATA.KEY.PRDMO_CD = "CAAC";
        DDCUPARM.DATA.KEY.PARM_CODE = DDCIPRRE.PARM_CODE;
        S000_CALL_DDZUPARM();
        if (pgmRtn) return;
        if (DDCUPARM.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DDCUPARM.RC);
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCUPARM.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDVMPRD);
            CEP.TRC(SCCGWA, DDVMPRD);
        }
    }
    public void B050_CHECK_CCY_PROC() throws IOException,SQLException,Exception {
        if (DDVMPRD.VAL.CUR_TYPE != 'A') {
            for (WS_CNT = 1; WS_CNT <= 20; WS_CNT += 1) {
                if (DDVMPRD.VAL.CCY[WS_CNT-1].equalsIgnoreCase(DDCIPRRE.CCY)) {
                    WS_CCY_FOUND_FLG = 'Y';
                }
            }
            if (WS_CCY_FOUND_FLG != 'Y') {
                IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_PRD_CCY_NOT_DEF, DDCIPRRE.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B070_CHK_CCY_RAT_PROC() throws IOException,SQLException,Exception {
        if (DDVMPRD.VAL.CAL_DINT_METH != '3') {
            CEP.TRC(SCCGWA, "GET PRD RATE");
            WS_CCY_RATE_KEY.WS_PROD_CODE = DDCIPRRE.PARM_CODE;
            WS_CCY_RATE_KEY.WS_PROD_CCY = DDCIPRRE.CCY;
            WS_CCY_RATE_KEY.WS_PROD_CCY_TYPE = DDCIPRRE.CCY_TYPE;
            R000_INQ_CCY_RATE_PROC();
            if (pgmRtn) return;
        }
    }
    public void B090_CONFIRM_RATE_PROC() throws IOException,SQLException,Exception {
        if (DDVMPRD.VAL.CAL_DINT_METH != '3') {
            IBS.init(SCCGWA, DDCITIRR);
            if (DDVMRAT.VAL.TIER[1-1].TAMT != 0 
                || DDVMRAT.VAL.TIER[1-1].TGRP.trim().length() > 0 
                || !DDVMRAT.VAL.TIER[1-1].TCLS.equalsIgnoreCase("0")) {
                for (WS_CNT = 1; WS_CNT <= 5; WS_CNT += 1) {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDVMRAT.VAL.TIER[1-1].TIER_IR[WS_CNT-1]);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCITIRR.RBASE_INFO[WS_CNT-1]);
                }
                DDCITIRR.CCY = DDVMRAT.KEY.CCY;
                DDCITIRR.TYPE = DDVMRAT.VAL.TIER[1-1].SPR_TYPE;
                DDCITIRR.HL_FLAG = DDVMRAT.VAL.TIER[1-1].HL_FLG;
                DDCITIRR.MAX_RATE = DDVMRAT.VAL.TIER[1-1].MAX_RATE;
                DDCITIRR.MIN_RATE = DDVMRAT.VAL.TIER[1-1].MIN_RATE;
                DDCITIRR.FIX_RATE = DDVMRAT.VAL.TIER[1-1].FIX_RATE;
                S000_CALL_DDZITIRR();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DDCITIRR.COMPUTED_RATE);
                DDCIPRRE.RATE = DDCITIRR.COMPUTED_RATE;
            } else {
                DDCIPRRE.RATE = 0;
            }
        }
    }
    public void R000_INQ_CCY_RATE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CCY_RATE_KEY);
        IBS.init(SCCGWA, DDVMRAT);
        IBS.init(SCCGWA, DDCUPRAT);
        DDCUPRAT.TX_TYPE = 'I';
        DDCUPRAT.DATA.KEY.PARM_CODE = WS_CCY_RATE_KEY.WS_PROD_CODE;
        DDCUPRAT.DATA.KEY.CCY = WS_CCY_RATE_KEY.WS_PROD_CCY;
        CEP.TRC(SCCGWA, DDCUPRAT.DATA.KEY.PARM_CODE);
        CEP.TRC(SCCGWA, DDCUPRAT.DATA.KEY.CCY);
        S000_CALL_DDZUPRAT();
        if (pgmRtn) return;
        if (DDCUPRAT.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DDCUPRAT.RC);
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCUPRAT.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDVMRAT);
            CEP.TRC(SCCGWA, DDVMRAT);
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
    }
    public void S000_CALL_DDZUPARM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-MPRD", DDCUPARM);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCIPRRE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZITIRR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-COMP-TIRR-AMT", DDCITIRR);
        if (DDCITIRR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCITIRR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCIPRRE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUPRAT() throws IOException,SQLException,Exception {
        DDZUPRAT DDZUPRAT = new DDZUPRAT();
        DDZUPRAT.MP(SCCGWA, DDCUPRAT);
        if (DDCUPRAT.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DDCUPRAT.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DDCIPRRE.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DDCIPRRE=");
            CEP.TRC(SCCGWA, DDCIPRRE);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
