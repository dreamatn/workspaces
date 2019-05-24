package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUMPRT {
    boolean pgmRtn = false;
    String BP_HIS_REMARKS = "MONTHLY TRANSACTION MAINT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    BPZUMPRT_WS_VAL_DATE WS_VAL_DATE = new BPZUMPRT_WS_VAL_DATE();
    BPZUMPRT_WS_COND_FLG WS_COND_FLG = new BPZUMPRT_WS_COND_FLG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCMMPRT BPCMMPRT = new BPCMMPRT();
    BPRMPRTT BPRMPRTT = new BPRMPRTT();
    BPRMPRTT BPRBPRTT = new BPRMPRTT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPCUMPRT BPCUMPRT;
    public void MP(SCCGWA SCCGWA, BPCUMPRT BPCUMPRT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUMPRT = BPCUMPRT;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, "BPZUMPRT return!");
        Z_RET();
        if (pgmRtn) return;
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRMPRTT);
        CEP.TRC(SCCGWA, BPCUMPRT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_VAL_DATE_SET();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCUMPRT.STUS);
        B030_READ_UPD_PROCESS();
        if (pgmRtn) return;
        if (BPCMMPRT.RETURN_INFO != 'N') {
            B040_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else {
            B050_CREATE_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCUMPRT.KEY.ACCT_CENTRE == 0 
            || BPCUMPRT.KEY.PROD_CODE.trim().length() == 0 
            || BPCUMPRT.KEY.CCY.trim().length() == 0 
            || BPCUMPRT.AMOUNT < 0 
            || BPCUMPRT.KEY.VAL_DATE == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCUMPRT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCUMPRT.KEY.VAL_DATE != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPCUMPRT.KEY.VAL_DATE;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_DATE_ERR, BPCUMPRT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_VAL_DATE_SET() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, BPCUMPRT.KEY.VAL_DATE+"", WS_VAL_DATE);
    }
    public void B030_READ_UPD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCMMPRT);
        IBS.init(SCCGWA, BPRMPRTT);
        IBS.init(SCCGWA, BPRBPRTT);
        BPCMMPRT.INFO.FUNC = 'R';
        BPRMPRTT.KEY.ACCT_CENTRE = BPCUMPRT.KEY.ACCT_CENTRE;
        BPRMPRTT.KEY.PROD_CODE = BPCUMPRT.KEY.PROD_CODE;
        BPRMPRTT.KEY.TOT_CCY = BPCUMPRT.KEY.CCY;
        BPRMPRTT.KEY.TOT_YEAR = WS_VAL_DATE.WS_YEAR;
        BPRMPRTT.KEY.TOT_MON = WS_VAL_DATE.WS_MON;
        BPCMMPRT.INFO.POINTER = BPRMPRTT;
        BPCMMPRT.LEN = 76;
        S000_CALL_BPZMMPRT();
        if (pgmRtn) return;
    }
    public void B040_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.CLONE(SCCGWA, BPRMPRTT, BPRBPRTT);
        IBS.init(SCCGWA, BPCMMPRT);
        IBS.init(SCCGWA, BPRMPRTT);
        BPCMMPRT.INFO.FUNC = 'U';
        BPRMPRTT.KEY.ACCT_CENTRE = BPCUMPRT.KEY.ACCT_CENTRE;
        BPRMPRTT.KEY.PROD_CODE = BPCUMPRT.KEY.PROD_CODE;
        BPRMPRTT.KEY.TOT_CCY = BPCUMPRT.KEY.CCY;
        BPRMPRTT.KEY.TOT_YEAR = WS_VAL_DATE.WS_YEAR;
        BPRMPRTT.KEY.TOT_MON = WS_VAL_DATE.WS_MON;
        if (BPCUMPRT.STUS == 'C') {
            BPRMPRTT.TOT_QTY = BPRBPRTT.TOT_QTY - 1;
            BPRMPRTT.TOT_AMOUNT = BPRBPRTT.TOT_AMOUNT - BPCUMPRT.AMOUNT;
        } else {
            BPRMPRTT.TOT_QTY = BPRBPRTT.TOT_QTY + 1;
            BPRMPRTT.TOT_AMOUNT = BPRBPRTT.TOT_AMOUNT + BPCUMPRT.AMOUNT;
        }
        BPCMMPRT.INFO.POINTER = BPRMPRTT;
        BPCMMPRT.LEN = 76;
        S000_CALL_BPZMMPRT();
        if (pgmRtn) return;
        S000_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B050_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCMMPRT);
        IBS.init(SCCGWA, BPRMPRTT);
        BPCMMPRT.INFO.FUNC = 'A';
        BPRMPRTT.KEY.ACCT_CENTRE = BPCUMPRT.KEY.ACCT_CENTRE;
        BPRMPRTT.KEY.PROD_CODE = BPCUMPRT.KEY.PROD_CODE;
        BPRMPRTT.KEY.TOT_CCY = BPCUMPRT.KEY.CCY;
        BPRMPRTT.KEY.TOT_YEAR = WS_VAL_DATE.WS_YEAR;
        BPRMPRTT.KEY.TOT_MON = WS_VAL_DATE.WS_MON;
        BPRMPRTT.TOT_QTY = 1;
        BPRMPRTT.TOT_AMOUNT = BPCUMPRT.AMOUNT;
        BPCMMPRT.INFO.POINTER = BPRMPRTT;
        BPCMMPRT.LEN = 76;
        S000_CALL_BPZMMPRT();
        if (pgmRtn) return;
        S000_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZMMPRT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-M-MPRT-MAINT", BPCMMPRT);
    }
    public void S000_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (BPCMMPRT.INFO.FUNC == 'A') {
            S000_01_HISTORY_PROCESS();
            if (pgmRtn) return;
        }
        if (BPCMMPRT.INFO.FUNC == 'U') {
            S000_02_HISTORY_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void S000_01_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = "BPRMPRTT";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUMPRT.KEY);
        BPCPNHIS.INFO.REF_NO = JIBS_tmp_str[0];
        BPCPNHIS.INFO.TX_TYP_CD = "BPMPRTT";
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        BPCPNHIS.INFO.TX_RMK = BP_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_02_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.FMT_ID = "BPRMPRTT";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUMPRT.KEY);
        BPCPNHIS.INFO.REF_NO = JIBS_tmp_str[0];
        BPCPNHIS.INFO.TX_TYP_CD = "BPMPRTT";
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        BPCPNHIS.INFO.TX_RMK = BP_HIS_REMARKS;
        BPCPNHIS.INFO.OLD_DAT_PT = BPRBPRTT;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRMPRTT;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCUMPRT.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "BPCUMPRT = ");
            CEP.TRC(SCCGWA, BPCUMPRT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
