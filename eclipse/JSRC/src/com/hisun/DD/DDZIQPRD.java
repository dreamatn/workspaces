package com.hisun.DD;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZIQPRD {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_CTL_PRM_TYP = "PRDPR";
    String K_RAT_PRM_TYP = "PDRAT";
    int K_BR = 999999999;
    short WS_CNT = 0;
    DDZIQPRD_WS_CCY_RATE_KEY WS_CCY_RATE_KEY = new DDZIQPRD_WS_CCY_RATE_KEY();
    DDZIQPRD_WS_MPRD_KEY WS_MPRD_KEY = new DDZIQPRD_WS_MPRD_KEY();
    char WS_EFF_FLG = ' ';
    char WS_CCY_FOUND_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDVMCCY DDVMCCY = new DDVMCCY();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DDCUPARM DDCUPARM = new DDCUPARM();
    DDCUPRAT DDCUPRAT = new DDCUPRAT();
    SCCGWA SCCGWA;
    DDCIQPRD DDCIQPRD;
    DDVMPRD DDVMPRD;
    DDVMRAT DDVMRAT;
    public void MP(SCCGWA SCCGWA, DDCIQPRD DDCIQPRD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCIQPRD = DDCIQPRD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZIQPRD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        DDVMPRD = (DDVMPRD) DDCIQPRD.DDVMPRD_PTR;
        DDVMRAT = (DDVMRAT) DDCIQPRD.DDVMRAT_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCIQPRD.INPUT_DATA.PROD_CODE);
        CEP.TRC(SCCGWA, DDCIQPRD.INPUT_DATA.CCY);
        if (DDCIQPRD.INPUT_DATA.PROD_CODE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_PROD_CD_M_INPUT, DDCIQPRD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = DDCIQPRD.INPUT_DATA.PROD_CODE;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_PROD_NOT_FND, DDCIQPRD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        DDCIQPRD.OUTPUT_DATA.AC_TYPE = BPCPQPRD.AC_TYPE;
        DDCIQPRD.OUTPUT_DATA.PRDT_MODEL = BPCPQPRD.PRDT_MODEL;
        DDCIQPRD.OUTPUT_DATA.CUS_PER_FLG = BPCPQPRD.CUS_PER_FLG;
        DDCIQPRD.OUTPUT_DATA.CUS_COM_FLG = BPCPQPRD.CUS_COM_FLG;
        DDCIQPRD.OUTPUT_DATA.CUS_FIN_FLG = BPCPQPRD.CUS_FIN_FLG;
        CEP.TRC(SCCGWA, BPCPQPRD.CUS_PER_FLG);
        CEP.TRC(SCCGWA, DDCIQPRD.OUTPUT_DATA.CUS_PER_FLG);
        B030_QUERY_PRD_PROC();
        if (pgmRtn) return;
        if (DDCIQPRD.INPUT_DATA.CCY.trim().length() > 0) {
            B050_CHECK_CCY_PROC();
            if (pgmRtn) return;
            B090_CHK_CCY_RAT_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_QUERY_PRD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDVMPRD);
        IBS.init(SCCGWA, DDCUPARM);
        DDCUPARM.TX_TYPE = 'I';
        DDCUPARM.DATA.KEY.PRDMO_CD = "CAAC";
        DDCUPARM.DATA.KEY.PARM_CODE = BPCPQPRD.PARM_CODE;
        DDCUPARM.DATE.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_DDZUPARM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCUPARM.RC);
        if (DDCUPARM.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DDCUPARM.RC);
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCUPARM.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDVMPRD);
            CEP.TRC(SCCGWA, DDVMPRD);
        }
    }
    public void B050_CHECK_CCY_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CUR_TYPE);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CCY[1-1]);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CCY[2-1]);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CCY[3-1]);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CCY[4-1]);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CCY[5-1]);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CCY[6-1]);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CCY[7-1]);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CCY[8-1]);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CCY[9-1]);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CCY[10-1]);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CCY[11-1]);
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CCY[12-1]);
        if (DDVMPRD.VAL.CUR_TYPE != 'A') {
            for (WS_CNT = 1; WS_CNT <= 20; WS_CNT += 1) {
                if (DDVMPRD.VAL.CCY[WS_CNT-1].equalsIgnoreCase(DDCIQPRD.INPUT_DATA.CCY)) {
                    WS_CCY_FOUND_FLG = 'Y';
                }
            }
            if (WS_CCY_FOUND_FLG != 'Y') {
                IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_PRD_CCY_NOT_DEF, DDCIQPRD.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B090_CHK_CCY_RAT_PROC() throws IOException,SQLException,Exception {
        if (DDCIQPRD.INPUT_DATA.DOM_BR == 0) {
            DDCIQPRD.INPUT_DATA.DOM_BR = K_BR;
        }
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CAL_DINT_METH);
        if (DDVMPRD.VAL.CAL_DINT_METH != '3') {
            CEP.TRC(SCCGWA, "GET PRD RATE");
            WS_CCY_RATE_KEY.WS_PROD_BR = DDCIQPRD.INPUT_DATA.DOM_BR;
            WS_CCY_RATE_KEY.WS_PROD_CODE = BPCPQPRD.PARM_CODE;
            WS_CCY_RATE_KEY.WS_PROD_CCY = DDCIQPRD.INPUT_DATA.CCY;
            R000_INQ_CCY_RATE_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_INQ_CCY_RATE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CCY_RATE_KEY);
        CEP.TRC(SCCGWA, "---BEGIN---");
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
        CEP.TRC(SCCGWA, "----END----");
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
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCIQPRD.RC);
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
        if (DDCIQPRD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DDCIQPRD=");
            CEP.TRC(SCCGWA, DDCIQPRD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
