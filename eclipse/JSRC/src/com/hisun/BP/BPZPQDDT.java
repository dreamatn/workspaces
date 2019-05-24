package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPQDDT {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_T_MAINT_CLND_INFO = "BP-T-MAINT-CLND-INFO";
    String K_P_CALL_DELE_DATE = "BP-P-CAL-DELE-DATE";
    String K_P_CALL_STOR_DATE = "BP-P-CAL-STOR-DATE";
    short WK_100 = 100;
    int WS_I = 0;
    char WS_OUTPUT_FLG = ' ';
    char WS_STOP_FLG = ' ';
    char WS_FUNC = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCTCLND BPCTCLND = new BPCTCLND();
    BPCMCLNO BPCMCLNO = new BPCMCLNO();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRCLND BPRCLND = new BPRCLND();
    BPCPCDDT BPCPCDDT = new BPCPCDDT();
    BPCPCSDT BPCPCSDT = new BPCPCSDT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCGWA SCCGWA;
    BPCPQDDT BPCPQDDT;
    public void MP(SCCGWA SCCGWA, BPCPQDDT BPCPQDDT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPQDDT = BPCPQDDT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "B000-START ");
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "B000-END   ");
        CEP.TRC(SCCGWA, "BPZPQDDT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQDDT.RC);
        IBS.init(SCCGWA, BPRCLND);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCPQDDT.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B100 START");
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MOVE_INPUT_DATA();
        if (pgmRtn) return;
        B300_BROWSE_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCPQDDT.INPUT.ID.trim().length() == 0 
            || BPCPQDDT.INPUT.ID.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CLEAN_NO_MUST_INPUT, BPCPQDDT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPQDDT.INPUT.AC_DATE == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_DATE_MUST_INPUT, BPCPQDDT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPQDDT.INPUT.LAST_DATE == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_LAST_DATE_MUST_INPUT, BPCPQDDT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_MOVE_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCLND);
        CEP.TRC(SCCGWA, BPCPQDDT.INPUT.ID);
        BPRCLND.KEY.BNK = BPCPQDDT.INPUT.BK;
        BPRCLND.KEY.CLN_ID = BPCPQDDT.INPUT.ID;
        BPRCLND.KEY.CLN_RULE = BPCPQDDT.INPUT.RULE;
    }
    public void B300_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRCLND.KEY.BNK);
        CEP.TRC(SCCGWA, BPRCLND.KEY.CLN_ID);
        CEP.TRC(SCCGWA, BPRCLND.KEY.CLN_RULE);
        IBS.init(SCCGWA, BPCTCLND);
        BPCTCLND.INFO.FUNC = 'B';
        BPCTCLND.INFO.OPT = 'S';
        S000_CALL_BPZTCLND();
        if (pgmRtn) return;
        WS_STOP_FLG = 'N';
        while (WS_STOP_FLG != 'Y' 
            && WS_I <= 100) {
            IBS.init(SCCGWA, BPCTCLND);
            BPCTCLND.INFO.FUNC = 'B';
            BPCTCLND.INFO.OPT = 'N';
            S000_CALL_BPZTCLND();
            if (pgmRtn) return;
            if (BPCTCLND.RETURN_INFO == 'N') {
                CEP.TRC(SCCGWA, " NOT FOUND");
                WS_STOP_FLG = 'Y';
            } else {
                if (BPRCLND.STS == 'Y') {
                    CEP.TRC(SCCGWA, " GO ON !!!");
                    R000_GET_CLEAN_FLAG();
                    if (pgmRtn) return;
                    if (BPCPCDDT.OUTPUT.DELE_FLAG == 'Y') {
                        R000_GET_STOR_DATE();
                        if (pgmRtn) return;
                        R000_OUTPUT_DATA();
                        if (pgmRtn) return;
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, WS_I);
        CEP.TRC(SCCGWA, BPCPQDDT.OUTPUT.CNT);
        IBS.init(SCCGWA, BPCTCLND);
        BPCTCLND.INFO.FUNC = 'B';
        BPCTCLND.INFO.OPT = 'E';
        S000_CALL_BPZTCLND();
        if (pgmRtn) return;
    }
    public void R000_GET_CLEAN_FLAG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPCDDT);
        BPCPCDDT.INPUT.DELE_FRY = BPRCLND.CLN_FRY;
        BPCPCDDT.INPUT.DELE_CYC = BPRCLND.CLN_CYC;
        BPCPCDDT.INPUT.AC_DATE = BPCPQDDT.INPUT.AC_DATE;
        BPCPCDDT.INPUT.LAST_DATE = BPCPQDDT.INPUT.LAST_DATE;
        CEP.TRC(SCCGWA, "CDDT-DELE-FRY : ");
        CEP.TRC(SCCGWA, BPCPCDDT.INPUT.DELE_FRY);
        CEP.TRC(SCCGWA, "CDDT-DELE-CYC : ");
        CEP.TRC(SCCGWA, BPCPCDDT.INPUT.DELE_CYC);
        CEP.TRC(SCCGWA, "CDDT-AC-DATE  : ");
        CEP.TRC(SCCGWA, BPCPCDDT.INPUT.AC_DATE);
        CEP.TRC(SCCGWA, "CDDT-LAST-DATE: ");
        CEP.TRC(SCCGWA, BPCPCDDT.INPUT.LAST_DATE);
        S000_CALL_BPZPCDDT();
        if (pgmRtn) return;
    }
    public void R000_GET_STOR_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPCSDT);
        BPCPCSDT.INPUT.STOR_FRY = BPRCLND.RES_FRY;
        BPCPCSDT.INPUT.STOR_CYC = BPRCLND.RES_CYC;
        BPCPCSDT.INPUT.SPLIT_FLAG = BPRCLND.RES_FLG;
        BPCPCSDT.INPUT.AC_DATE = BPCPQDDT.INPUT.AC_DATE;
        S000_CALL_BPZPCSDT();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_DATA() throws IOException,SQLException,Exception {
        WS_I = WS_I + 1;
        CEP.TRC(SCCGWA, WS_I);
        if (WS_I > WK_100) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CLEAN_TIMES_OVERFLOW, BPCPQDDT.RC);
            WS_STOP_FLG = 'Y';
        } else {
            BPCPQDDT.OUTPUT.RN_DATA[WS_I-1].RN_BK = BPRCLND.KEY.BNK;
            BPCPQDDT.OUTPUT.RN_DATA[WS_I-1].RN_RULE = BPRCLND.KEY.CLN_RULE;
            BPCPQDDT.OUTPUT.RN_DATA[WS_I-1].RN_DATE = BPCPCSDT.OUTPUT.STOR_DATE;
            BPCPQDDT.OUTPUT.CNT = WS_I;
        }
    }
    public void S000_CALL_BPZTCLND() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRCLND.KEY.BNK);
        CEP.TRC(SCCGWA, BPRCLND.KEY.CLN_ID);
        CEP.TRC(SCCGWA, BPRCLND.KEY.CLN_RULE);
        BPCTCLND.INFO.POINTER = BPRCLND;
        BPCTCLND.LEN = 329;
        IBS.CALLCPN(SCCGWA, K_T_MAINT_CLND_INFO, BPCTCLND);
        if (BPCTCLND.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTCLND.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPQDDT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPRCLND.KEY);
    }
    public void S000_CALL_BPZPCDDT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPCDDT.INPUT.DELE_FRY);
        CEP.TRC(SCCGWA, BPCPCDDT.INPUT.DELE_CYC);
        CEP.TRC(SCCGWA, BPCPCDDT.INPUT.AC_DATE);
        CEP.TRC(SCCGWA, BPCPCDDT.INPUT.LAST_DATE);
        IBS.CALLCPN(SCCGWA, K_P_CALL_DELE_DATE, BPCPCDDT);
        CEP.TRC(SCCGWA, "CDDT-RC-MMO   :");
        CEP.TRC(SCCGWA, BPCPCDDT.RC.RC_MMO);
        CEP.TRC(SCCGWA, "CDDT-RC-CODE  :");
        CEP.TRC(SCCGWA, BPCPCDDT.RC.RC_CODE);
        CEP.TRC(SCCGWA, "CDDT-DELE-FLAG:");
        CEP.TRC(SCCGWA, BPCPCDDT.OUTPUT.DELE_FLAG);
        if (BPCPCDDT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPCDDT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPQDDT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPCSDT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPCSDT.INPUT.STOR_FRY);
        CEP.TRC(SCCGWA, BPCPCSDT.INPUT.STOR_CYC);
        CEP.TRC(SCCGWA, BPCPCSDT.INPUT.SPLIT_FLAG);
        CEP.TRC(SCCGWA, BPCPCSDT.INPUT.AC_DATE);
        IBS.CALLCPN(SCCGWA, K_P_CALL_STOR_DATE, BPCPCSDT);
        CEP.TRC(SCCGWA, "CSDT-RC-MMO   :");
        CEP.TRC(SCCGWA, BPCPCSDT.RC.RC_MMO);
        CEP.TRC(SCCGWA, "CSDT-RC-CODE  :");
        CEP.TRC(SCCGWA, BPCPCSDT.RC.RC_CODE);
        CEP.TRC(SCCGWA, "CSDT-STOR-DATE:");
        CEP.TRC(SCCGWA, BPCPCSDT.OUTPUT.STOR_DATE);
        if (BPCPCSDT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPCSDT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPQDDT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPQDDT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPQDDT = ");
            CEP.TRC(SCCGWA, BPCPQDDT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
