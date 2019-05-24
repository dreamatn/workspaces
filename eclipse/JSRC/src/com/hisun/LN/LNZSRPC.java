package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSRPC {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCAMDB LNCAMDB = new LNCAMDB();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCIPAMT LNCIPAMT = new LNCIPAMT();
    SCCGWA SCCGWA;
    LNCSRPC LNCSRPC;
    public void MP(SCCGWA SCCGWA, LNCSRPC LNCSRPC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSRPC = LNCSRPC;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSRPC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCSRPC.RC.RC_APP = "LN";
        LNCSRPC.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_P_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_I_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_O_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_L_AMT);
        B000_CHECK();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSRPC RETURN...");
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TR_VAL_DTE);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_DTL_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_P_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_I_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_O_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_L_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.HRG_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.APT_REF);
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        if (LNCSRPC.FUNC_CODE != 'I' 
            && LNCSRPC.FUNC_CODE != 'U') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCSRPC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSRPC.COMM_DATA.CTA_NO;
        LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        LNCSRPC.COMM_DATA.TOT_DTL_AMT = LNCSRPC.COMM_DATA.TOT_P_AMT + LNCSRPC.COMM_DATA.TOT_I_AMT + LNCSRPC.COMM_DATA.TOT_O_AMT + LNCSRPC.COMM_DATA.TOT_L_AMT;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1")) {
            LNCSRPC.COMM_DATA.TOT_DTL_AMT += LNCSRPC.COMM_DATA.HRG_AMT;
        }
        if (LNCSRPC.COMM_DATA.TOT_DTL_AMT != LNCSRPC.COMM_DATA.TOT_AMT 
            && LNCSRPC.COMM_DATA.TOT_DTL_AMT != 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AMT_UNMATCH2, LNCSRPC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CUST_AMDB_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CUST_AMDB_PROCESS() throws IOException,SQLException,Exception {
        B110_CUST_REPAY_DETAIL_ENQ();
        if (pgmRtn) return;
        B130_CUST_REPAY_OUTPUT();
        if (pgmRtn) return;
    }
    public void B110_CUST_REPAY_DETAIL_ENQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCAMDB);
        LNCAMDB.COMM_DATA.FUNC_CODE = LNCSRPC.FUNC_CODE;
        LNCAMDB.COMM_DATA.LN_AC = LNCSRPC.COMM_DATA.CTA_NO;
        LNCAMDB.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCAMDB.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCAMDB.COMM_DATA.SUF_NO = "0" + LNCAMDB.COMM_DATA.SUF_NO;
        LNCAMDB.COMM_DATA.MAX_PAY_AMT = LNCSRPC.COMM_DATA.TOT_AMT;
        LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_AMT = LNCSRPC.COMM_DATA.TOT_AMT;
        LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_P_AMT = LNCSRPC.COMM_DATA.TOT_P_AMT;
        LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_I_AMT = LNCSRPC.COMM_DATA.TOT_I_AMT;
        LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_O_AMT = LNCSRPC.COMM_DATA.TOT_O_AMT;
        LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_L_AMT = LNCSRPC.COMM_DATA.TOT_L_AMT;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1")) {
            LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_F_AMT = LNCSRPC.COMM_DATA.HRG_AMT;
        }
        LNCAMDB.COMM_DATA.TR_VAL_DATE = LNCSRPC.COMM_DATA.TR_VAL_DTE;
        LNCAMDB.COMM_DATA.SUBS_FLG = LNCSRPC.COMM_DATA.SUBS_FLG;
        LNCAMDB.COMM_DATA.CUR_TRM = LNCSRPC.COMM_DATA.CUR_TRM;
        S000_CALL_LNZAMDB();
        if (pgmRtn) return;
        if (LNCAMDB.COMM_DATA.TOT_AMTS.TOT_AMT == 0) {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B130_CUST_REPAY_OUTPUT() throws IOException,SQLException,Exception {
        if (LNCSRPC.COMM_DATA.TOT_AMT == 0) {
            LNCSRPC.COMM_DATA.TOT_AMT = LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_AMT;
        }
        LNCSRPC.COMM_DATA.TOT_P_UDAMT = LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_P_UDAMT;
        LNCSRPC.COMM_DATA.TOT_P_AMT = LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_P_AMT;
        LNCSRPC.COMM_DATA.TOT_I_AMT = LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_I_AMT;
        LNCSRPC.COMM_DATA.TOT_O_AMT = LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_O_AMT;
        LNCSRPC.COMM_DATA.TOT_L_AMT = LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_L_AMT;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1")) {
            LNCSRPC.COMM_DATA.HRG_AMT = LNCAMDB.COMM_DATA.TOT_D_AMTS.TOT_D_F_AMT;
        } else {
            LNCSRPC.COMM_DATA.HRG_AMT = LNCAMDB.COMM_DATA.TOT_AMTS.TOT_F_AMT;
        }
        LNCSRPC.COMM_DATA.APT_REF = LNCAMDB.COMM_DATA.APT_REF;
        LNCSRPC.COMM_DATA.TOT_DTL_AMT = LNCSRPC.COMM_DATA.TOT_P_AMT + LNCSRPC.COMM_DATA.TOT_I_AMT + LNCSRPC.COMM_DATA.TOT_O_AMT + LNCSRPC.COMM_DATA.TOT_L_AMT;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1")) {
            LNCSRPC.COMM_DATA.TOT_DTL_AMT += LNCSRPC.COMM_DATA.HRG_AMT;
        }
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_DTL_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_P_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_I_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_O_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_L_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.HRG_AMT);
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1") 
            || LNCICTLM.REC_DATA.CTL_STSW.substring(13 - 1, 13 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, LNCIPAMT);
            LNCIPAMT.CTA_NO = LNCSRPC.COMM_DATA.CTA_NO;
            LNCIPAMT.P_AMT = LNCSRPC.COMM_DATA.TOT_P_AMT;
            LNCIPAMT.I_AMT = LNCSRPC.COMM_DATA.TOT_I_AMT;
            LNCIPAMT.O_AMT = LNCSRPC.COMM_DATA.TOT_O_AMT;
            LNCIPAMT.L_AMT = LNCSRPC.COMM_DATA.TOT_L_AMT;
            LNCIPAMT.F_AMT = LNCSRPC.COMM_DATA.HRG_AMT;
            S000_CALL_LNZIPAMT();
            if (pgmRtn) return;
            for (WS_I = 1; WS_I <= 10; WS_I += 1) {
                LNCSRPC.COMM_DATA.PART_DATA[WS_I-1].SUB_C_NO = LNCIPAMT.PART_DATA[WS_I-1].PART_CTA_NO;
                LNCSRPC.COMM_DATA.PART_DATA[WS_I-1].BBR = LNCIPAMT.PART_DATA[WS_I-1].PART_BR;
                LNCSRPC.COMM_DATA.PART_DATA[WS_I-1].SEQ_NO = LNCIPAMT.PART_DATA[WS_I-1].PART_NO;
                LNCSRPC.COMM_DATA.PART_DATA[WS_I-1].I_O_FLG = LNCIPAMT.INNER_OUT_FLG;
                LNCSRPC.COMM_DATA.PART_DATA[WS_I-1].LCL_BR = LNCIPAMT.PART_DATA[WS_I-1].LOCAL_BANK_FLAG;
                LNCSRPC.COMM_DATA.PART_DATA[WS_I-1].ADJ_FLG = LNCIPAMT.PART_DATA[WS_I-1].ADJ_BANK_FLAG;
                LNCSRPC.COMM_DATA.PART_DATA[WS_I-1].REL_TYP = LNCIPAMT.PART_DATA[WS_I-1].REL_TYPE;
                LNCSRPC.COMM_DATA.PART_DATA[WS_I-1].PY_P_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_P_AMT;
                LNCSRPC.COMM_DATA.PART_DATA[WS_I-1].PY_I_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_I_AMT;
                LNCSRPC.COMM_DATA.PART_DATA[WS_I-1].PY_O_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_O_AMT;
                LNCSRPC.COMM_DATA.PART_DATA[WS_I-1].PY_L_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_L_AMT;
                LNCSRPC.COMM_DATA.PART_DATA[WS_I-1].PY_F_AMT = LNCIPAMT.PART_DATA[WS_I-1].PART_F_AMT;
            }
        }
    }
    public void S000_CALL_LNZAMDB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-AMOUNT-DISB", LNCAMDB);
        if (LNCAMDB.RC.RC_RTNCODE != 0) {
            LNCSRPC.RC.RC_APP = LNCAMDB.RC.RC_APP;
            LNCSRPC.RC.RC_RTNCODE = LNCAMDB.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICTLM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRPC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZIPAMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-INQ-PAR-AMT", LNCIPAMT);
        if (LNCIPAMT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCIPAMT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSRPC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCSRPC.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCSRPC=");
            CEP.TRC(SCCGWA, LNCSRPC);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
