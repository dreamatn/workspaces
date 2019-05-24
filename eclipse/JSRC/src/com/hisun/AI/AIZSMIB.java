package com.hisun.AI;

import java.util.ArrayList;
import java.util.List;
import com.hisun.BP.*;
import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class AIZSMIB {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    AIZSMIB_WS_RVSNO_DATA WS_RVSNO_DATA;
    DBParm AITMIBA_RD;
    DBParm AITITUS_RD;
    brParm BPTVCHT_BR = new brParm();
    DBParm BPTVCHT_RD;
    DBParm AITMIB_RD;
    boolean pgmRtn = false;
    String TBL_BPTPARM = "BPTPARM";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String TBL_BPTVCHT = "BPTVCHT";
    List<AIZSMIB_WS_BR_DATA> WS_BR_DATA = new ArrayList<AIZSMIB_WS_BR_DATA>();
    List<AIZSMIB_WS_RVSNO_DATA> WS_RVSNO_DATA = new ArrayList<AIZSMIB_WS_RVSNO_DATA>();
    String WS_ERR_MSG = " ";
    short WS_DATA_LEN = 0;
    int WS_I = 0;
    int WS_COUNT = 0;
    int WS_CNT = 0;
    int WS_CNT1 = 0;
    int WS_Z = 0;
    int WS_OLD_OPEN_DT = 0;
    int WS_OLD_CLS_DT = 0;
    int WS_J = 0;
    int WS_TMP_BR = 0;
    int WS_TMP_BBR = 0;
    AIZSMIB_WS_AC_NO WS_AC_NO = new AIZSMIB_WS_AC_NO();
    double WS_DRLT_BAL = 0;
    AIZSMIB_WS_BRW_OUTPUT WS_BRW_OUTPUT = new AIZSMIB_WS_BRW_OUTPUT();
    AIZSMIB_WS_OUTPUT WS_OUTPUT = new AIZSMIB_WS_OUTPUT();
    int WS_GRANT_BR = 0;
    char WS_GRANT = ' ';
    int WS_CNTA = 0;
    int WS_CNTB = 0;
    char WS_SUCC = ' ';
    char WS_LVL = ' ';
    char WS_LVL1 = ' ';
    AIZSMIB_WS_SUPR_GRP WS_SUPR_GRP = new AIZSMIB_WS_SUPR_GRP();
    int WS_BR_I = 0;
    int WS_CAL_DATE = 0;
    int WS_RVS_EXP_DT = 0;
    short WS_RVS_EXP = 0;
    char WS_RVS_UNIT = ' ';
    int WS_LAST_BR = 0;
    int WS_PORLO_BR = 0;
    int WS_SUPR_ORG = 0;
    char WS_COA_FLG = ' ';
    short WS_BOOK_CNT = 0;
    short WS_ACSEQ = 0;
    double WS_OPEN_AMT = 0;
    char WS_GL_BOOK_FLG = ' ';
    char WS_SEQ_FLG = ' ';
    char WS_ITM_NO_FLG = ' ';
    char WS_CCY_FLG = ' ';
    char WS_CHECK_FLG = ' ';
    char WS_PARM_FLG = ' ';
    char WS_BR_OUT_FLG = ' ';
    char WS_AC_OUT_FLG = ' ';
    char WS_BR_ADMIT_FLG = ' ';
    char WS_BR_GRANT_FLG = ' ';
    char WS_BR_FLG = ' ';
    char WS_DB2_REC_STATUS = ' ';
    char WS_REC_STATUS = ' ';
    char WS_MIB_REC_STATUS = ' ';
    char WS_MIBA_FLG = ' ';
    char WS_BBR_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    AICRMIB AICRMIB = new AICRMIB();
    AIRMIBA AIRMIBA = new AIRMIBA();
    AIRMIB AIRMIB = new AIRMIB();
    AICHMIB AICOMIB = new AICHMIB();
    AICHMIB AICNMIB = new AICHMIB();
    AICHMIB AICHMIB = new AICHMIB();
    BPRPARM BPRPARM = new BPRPARM();
    AIRPAI1 AIRPAI1 = new AIRPAI1();
    SCCSUBS SCCSUBS = new SCCSUBS();
    AIRCMIB AIRCMIB = new AIRCMIB();
    AICRCMIB AICRCMIB = new AICRCMIB();
    AICPCMIB AICPCMIB = new AICPCMIB();
    AIRGINF AIRGINF = new AIRGINF();
    AICRGINF AICRGINF = new AICRGINF();
    BPCPORLO BPCPORLO = new BPCPORLO();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCQBKPM BPCQBKPM = new BPCQBKPM();
    BPRVCHT BPRVCHT = new BPRVCHT();
    BPCTVCHT BPCTVCHT = new BPCTVCHT();
    AIRMIBH AIRMIBH = new AIRMIBH();
    AICRMIBH AICRMIBH = new AICRMIBH();
    BPRPORGM BPRPORGM = new BPRPORGM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCFTLCM BPCFTLCM = new BPCFTLCM();
    AIRITUS AIRITUS = new AIRITUS();
    SCCGWA SCCGWA;
    AICSMIB AICSMIB;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, AICSMIB AICSMIB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICSMIB = AICSMIB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZSMIB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (AICSMIB.FUNC == 'B') {
            B020_BROWSE_PROCESS_ALL();
            if (pgmRtn) return;
        } else if (AICSMIB.FUNC == 'Q') {
            B030_QUERY_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
            B200_SET_SUB_TRN();
            if (pgmRtn) return;
        } else if (AICSMIB.FUNC == 'A') {
            B040_ADD_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSMIB.FUNC == 'M') {
            B050_MODIFY_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSMIB.FUNC == 'R') {
            B060_REACTIVE_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSMIB.FUNC == 'H') {
            B070_HOLD_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSMIB.FUNC == 'U') {
            B080_UNHOLD_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSMIB.FUNC == 'S') {
            B090_STOP_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (AICSMIB.FUNC == 'A' 
            || AICSMIB.FUNC == 'M' 
            || AICSMIB.FUNC == 'R') {
            AIRITUS.KEY.BR = AICSMIB.BR;
            AIRITUS.KEY.ITM_NO = AICSMIB.ITM_NO;
            B010_1_GET_COA_FLG();
            if (pgmRtn) return;
            AIRITUS.KEY.COA_FLG = "" + WS_COA_FLG;
            JIBS_tmp_int = AIRITUS.KEY.COA_FLG.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) AIRITUS.KEY.COA_FLG = "0" + AIRITUS.KEY.COA_FLG;
            T000_READ_AITITUS();
            if (pgmRtn) return;
            if (WS_DB2_REC_STATUS == 'N') {
                T000_STARTBR_AITITUS_FIRST();
                if (pgmRtn) return;
                if (WS_DB2_REC_STATUS == 'F') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_BR_CANNOT_USE_ITM;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B020_BROWSE_PROCESS_ALL() throws IOException,SQLException,Exception {
        if (AICSMIB.AC.trim().length() == 0) {
            B020_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            B020_BROWSE_PROCESS_AC();
            if (pgmRtn) return;
        }
    }
    public void B020_BROWSE_PROCESS_AC() throws IOException,SQLException,Exception {
        B010_BR_GRANT_PROC_NCB();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 237;
        SCCMPAG.SCR_ROW_CNT = 20;
        SCCMPAG.SCR_COL_CNT = 5;
        B_MPAG();
        if (pgmRtn) return;
        WS_BBR_FLG = 'N';
        IBS.init(SCCGWA, BPCPQORG);
        if (AICSMIB.AC == null) AICSMIB.AC = "";
        JIBS_tmp_int = AICSMIB.AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) AICSMIB.AC += " ";
        if (AICSMIB.AC.substring(0, 6).trim().length() == 0) BPCPQORG.BR = 0;
        else BPCPQORG.BR = Integer.parseInt(AICSMIB.AC.substring(0, 6));
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_TMP_BBR = BPCPQORG.BBR;
        if (BPCPQORG.ATTR == '3') {
            if (BPCPQORG.BBR != 0) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = WS_TMP_BBR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                if (BPCPQORG.ATTR == '2') {
                    if (AICSMIB.AC == null) AICSMIB.AC = "";
                    JIBS_tmp_int = AICSMIB.AC.length();
                    for (int i=0;i<32-JIBS_tmp_int;i++) AICSMIB.AC += " ";
                    JIBS_tmp_str[0] = "" + BPCPQORG.BBR;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    AICSMIB.AC = JIBS_tmp_str[0] + AICSMIB.AC.substring(6);
                    WS_BBR_FLG = 'Y';
                }
            }
        }
        CEP.TRC(SCCGWA, AICSMIB.AC);
        CEP.TRC(SCCGWA, "TESTAC");
        IBS.CPY2CLS(SCCGWA, AICSMIB.AC, WS_AC_NO);
        AICSMIB.BR = WS_AC_NO.WS_BR;
        AICSMIB.CCY = WS_AC_NO.WS_CCY;
        AICSMIB.ITM_NO = WS_AC_NO.WS_ITM;
        AICSMIB.SEQ = WS_AC_NO.WS_AC_SEQ;
        CEP.TRC(SCCGWA, WS_AC_NO);
        CEP.TRC(SCCGWA, AICSMIB.BR);
        CEP.TRC(SCCGWA, AICSMIB.CCY);
        CEP.TRC(SCCGWA, AICSMIB.ITM_NO);
        CEP.TRC(SCCGWA, AICSMIB.SEQ);
        B030_QUERY_PROCESS();
        if (pgmRtn) return;
        R000_TRANS_DATA_BRW_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_BR_GRANT_PROC_NCB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLCM);
        if (AICSMIB.AC == null) AICSMIB.AC = "";
        JIBS_tmp_int = AICSMIB.AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) AICSMIB.AC += " ";
        if (AICSMIB.AC.substring(0, 6).trim().length() == 0) BPCFTLCM.BR = 0;
        else BPCFTLCM.BR = Integer.parseInt(AICSMIB.AC.substring(0, 6));
        BPCFTLCM.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLCM();
        if (pgmRtn) return;
        if (BPCFTLCM.AUTH_FLG == 'N') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_BR_NO_GRANT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_BR_GRANT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
        WS_BR_FLG = 'N';
        if (AICSMIB.AC == null) AICSMIB.AC = "";
        JIBS_tmp_int = AICSMIB.AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) AICSMIB.AC += " ";
        if (AICSMIB.AC.substring(0, 6).trim().length() == 0) WS_GRANT_BR = 0;
        else WS_GRANT_BR = Integer.parseInt(AICSMIB.AC.substring(0, 6));
        CEP.TRC(SCCGWA, WS_GRANT_BR);
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.LVL);
        if (BPCPORUP.DATA_INFO.LVL == '2' 
            && BPCPORUP.DATA_INFO.BR != WS_GRANT_BR) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_BR_NO_GRANT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCPORUP.DATA_INFO.LVL == '9' 
            || BPCPORUP.DATA_INFO.LVL == '7') {
            WS_BR_FLG = 'Y';
        }
        if (BPCPORUP.DATA_INFO.LVL != '2' 
            && BPCPORUP.DATA_INFO.LVL != '7' 
            && BPCPORUP.DATA_INFO.LVL != '9') {
            IBS.init(SCCGWA, BPCPORLO);
            BPCPORLO.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPORLO.BR = BPCPORUP.DATA_INFO.BR;
            CEP.TRC(SCCGWA, BPCPORLO.BR);
            S000_CALL_BPZPORLO();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPORLO.SUB_NUM);
            CEP.TRC(SCCGWA, BPCPORLO.SUB_NUM);
            if (BPCPORLO.SUB_NUM > 0) {
                WS_GRANT = 'Y';
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPORUP.DATA_INFO.SUPR_GRP[1-1]);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_SUPR_GRP);
                BPCPORLO.BR = WS_SUPR_GRP.WS_SUPR_BR;
                CEP.TRC(SCCGWA, WS_SUPR_GRP.WS_SUPR_BR);
                S000_CALL_BPZPORLO();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPORLO.SUB_NUM);
                if (BPCPORLO.SUB_NUM == 0 
                    && BPCPORUP.DATA_INFO.BR != WS_GRANT_BR) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_BR_NO_GRANT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    WS_GRANT = 'Y';
                }
            }
        }
        if (WS_GRANT == 'Y') {
            CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BR);
            if (WS_GRANT_BR == BPCPORUP.DATA_INFO.BR 
                || WS_GRANT_BR == WS_SUPR_GRP.WS_SUPR_BR) {
                WS_BR_FLG = 'Y';
            }
            WS_CNTA = 1;
            for (WS_CNTA = 1; WS_CNTA <= BPCPORLO.SUB_NUM 
                && WS_BR_FLG != 'Y'; WS_CNTA += 1) {
                CEP.TRC(SCCGWA, WS_CNTA);
                BPCPORLO.BR = BPCPORLO.SUB_BR_DATA[WS_CNTA-1].SUB_BR;
                CEP.TRC(SCCGWA, BPCPORLO.BR);
                if (BPCPORLO.BR == WS_GRANT_BR) {
                    WS_BR_FLG = 'Y';
                }
            }
            if (WS_BR_FLG == 'N' 
                && BPCPORLO.NEXT_CALL_FLG == 'Y') {
                WS_LAST_BR = BPCPORLO.LAST_BR;
                WS_PORLO_BR = BPCPORLO.BR;
                IBS.init(SCCGWA, BPCPORLO);
                BPCPORLO.BNK = SCCGWA.COMM_AREA.TR_BANK;
                BPCPORLO.BR = WS_PORLO_BR;
                BPCPORLO.LAST_BR = WS_LAST_BR;
                S000_CALL_BPZPORLO();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPORLO.SUB_NUM);
                if (BPCPORLO.SUB_NUM != 0) {
                    for (WS_CNTA = 1; WS_CNTA <= BPCPORLO.SUB_NUM 
                        && WS_BR_FLG != 'Y'; WS_CNTA += 1) {
                        CEP.TRC(SCCGWA, WS_CNTA);
                        BPCPORLO.BR = BPCPORLO.SUB_BR_DATA[WS_CNTA-1].SUB_BR;
                        CEP.TRC(SCCGWA, BPCPORLO.BR);
                        if (BPCPORLO.BR == WS_GRANT_BR) {
                            WS_BR_FLG = 'Y';
                        }
                    }
                }
            }
            if (WS_BR_FLG == 'N') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_BR_NO_GRANT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_BPZPORLO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-LOW", BPCPORLO);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRCMIB);
        IBS.init(SCCGWA, AICPCMIB);
        AIRCMIB.KEY.GL_BOOK = AICSMIB.GL_BOOK;
        AIRCMIB.KEY.BR = AICSMIB.BR;
        AIRCMIB.KEY.ITM = AICSMIB.ITM_NO;
        AIRCMIB.KEY.SEQ = AICSMIB.SEQ;
        CEP.TRC(SCCGWA, AIRCMIB);
        S000_CALL_AIZPCMIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AIRCMIB.CHS_NM);
        if (AICPCMIB.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "QUERY CMIB WITH SEQ = 999999");
            IBS.init(SCCGWA, AIRCMIB);
            IBS.init(SCCGWA, AICPCMIB);
            AIRCMIB.KEY.GL_BOOK = AICSMIB.GL_BOOK;
            AIRCMIB.KEY.BR = AICSMIB.BR;
            AIRCMIB.KEY.ITM = AICSMIB.ITM_NO;
            AIRCMIB.KEY.SEQ = 999999;
            CEP.TRC(SCCGWA, AIRCMIB);
            S000_CALL_AIZPCMIB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AIRCMIB.KEY.SEQ);
            CEP.TRC(SCCGWA, AIRCMIB.CHS_NM);
        }
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.WS_OUT_GLBOOK = AIRMIB.KEY.GL_BOOK;
        WS_OUTPUT.WS_OUT_BR = AIRMIB.KEY.BR;
        WS_OUTPUT.WS_OUT_ITM = AIRMIB.KEY.ITM_NO;
        WS_OUTPUT.WS_OUT_SEQ = AIRMIB.KEY.SEQ;
        WS_OUTPUT.WS_OUT_CCY = AIRMIB.KEY.CCY;
        WS_OUTPUT.WS_OUT_ITMNM = AIRMIB.CHS_NM;
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_MODFLG);
        if (AIRCMIB.KEY.SEQ == 999999 
            && AICPCMIB.RETURN_INFO == 'F') {
            WS_OUTPUT.WS_OUT_ITMNM = AIRMIB.CHS_NM;
            WS_OUTPUT.WS_OUT_MODFLG = 'Y';
        }
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_MODFLG);
        WS_OUTPUT.WS_OUT_ACTYP = AIRMIB.AC_TYP;
        WS_OUTPUT.WS_OUT_CCYLMT = AIRMIB.CCY_LMT;
        WS_OUTPUT.WS_OUT_BALDIR = AIRMIB.BAL_DIR;
        WS_OUTPUT.WS_OUT_BALRFLG = AIRMIB.BAL_RFLG;
        WS_OUTPUT.WS_OUT_DTLFLG = AIRMIB.DTL_FLG;
        WS_OUTPUT.WS_OUT_RVSTYP = AIRMIB.RVS_TYP;
        WS_OUTPUT.WS_OUT_RVSKND = AIRMIB.RVS_KND;
        WS_OUTPUT.WS_OUT_RVSEXP = AIRMIB.RVS_EXP;
        WS_OUTPUT.WS_OUT_RVSUNIT = AIRMIB.RVS_UNIT;
        WS_OUTPUT.WS_OUT_ACEXP = AIRMIB.AC_EXP;
        WS_OUTPUT.WS_OUT_MANUAL = AIRMIB.MANUAL_FLG;
        WS_OUTPUT.WS_OUT_AMTDIR = AIRMIB.AMT_DIR;
        WS_OUTPUT.WS_OUT_ONLFLG = AIRMIB.ONL_FLG;
        WS_OUTPUT.WS_OUT_CARDFLG = AIRMIB.CARD_FLG;
        WS_OUTPUT.WS_OUT_HOTFLG = AIRMIB.HOT_FLG;
        WS_OUTPUT.WS_OUT_DRLTBAL = AIRMIB.DRLT_BAL;
        WS_OUTPUT.WS_OUT_CRLTBAL = AIRMIB.CRLT_BAL;
        WS_OUTPUT.WS_OUT_BALCHK = AIRMIB.BAL_CHK;
        WS_OUTPUT.WS_OUT_APP = AIRMIB.APP1;
        WS_OUTPUT.WS_OUT_APP1 = AIRMIB.APP2;
        WS_OUTPUT.WS_OUT_APP2 = AIRMIB.APP3;
        WS_OUTPUT.WS_OUT_APP3 = AIRMIB.APP4;
        WS_OUTPUT.WS_OUT_APP4 = AIRMIB.APP5;
        WS_OUTPUT.WS_OUT_APP5 = AIRMIB.APP6;
        WS_OUTPUT.WS_OUT_APP6 = AIRMIB.APP7;
        WS_OUTPUT.WS_OUT_APP7 = AIRMIB.APP8;
        WS_OUTPUT.WS_OUT_APP8 = AIRMIB.APP9;
        WS_OUTPUT.WS_OUT_APP9 = AIRMIB.APP10;
        WS_OUTPUT.WS_OUT_LBAL = AIRMIB.LBAL;
        WS_OUTPUT.WS_OUT_CBAL = AIRMIB.CBAL;
        WS_OUTPUT.WS_OUT_OPENDT = AIRMIB.OPEN_DATE;
        WS_OUTPUT.WS_OUT_CLOSEDT = AIRMIB.CLS_DATE;
        WS_OUTPUT.WS_OUT_STS = AIRMIB.STS;
        WS_OUTPUT.WS_OUT_ADDR = AIRMIB.ADDR;
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_GLBOOK);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_BR);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_ITM);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_SEQ);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_CCY);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_ITMNM);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_ACTYP);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_CCYLMT);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_BALDIR);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_BALRFLG);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_DTLFLG);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_RVSTYP);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_RVSKND);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_RVSEXP);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_RVSUNIT);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_ACEXP);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_MANUAL);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_AMTDIR);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_ONLFLG);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_CARDFLG);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_HOTFLG);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_DRLTBAL);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_CRLTBAL);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_BALCHK);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_APP);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_APP1);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_APP2);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_APP3);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_APP4);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_APP5);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_APP6);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_APP7);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_APP8);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_APP9);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_STS);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_OUT_ADDR);
        if (!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT") 
            && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = "AI806";
            SCCFMT.DATA_PTR = WS_OUTPUT;
            SCCFMT.DATA_LEN = 333;
            IBS.FMT(SCCGWA, SCCFMT);
        }
    }
    public void B030_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRMIB);
        IBS.init(SCCGWA, AICRMIB);
        AICRMIB.FUNC = 'Q';
        AIRMIB.KEY.ITM_NO = AICSMIB.ITM_NO;
        AIRMIB.KEY.BR = AICSMIB.BR;
        AIRMIB.KEY.SEQ = AICSMIB.SEQ;
        AIRMIB.KEY.CCY = AICSMIB.CCY;
        WS_AC_NO.WS_BR = AICSMIB.BR;
        WS_AC_NO.WS_CCY = AICSMIB.CCY;
        WS_AC_NO.WS_ITM = AICSMIB.ITM_NO;
        WS_AC_NO.WS_AC_SEQ = AICSMIB.SEQ;
        AIRMIB.AC_NO = IBS.CLS2CPY(SCCGWA, WS_AC_NO);
        CEP.TRC(SCCGWA, WS_AC_NO);
        CEP.TRC(SCCGWA, AIRMIB.AC_NO);
        AIRMIB.KEY.GL_BOOK = AICSMIB.GL_BOOK;
        AICRMIB.POINTER = AIRMIB;
        AICRMIB.REC_LEN = 634;
        S000_CALL_AIZRMIB();
        if (pgmRtn) return;
        if (AICRMIB.RETURN_INFO == 'N') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITMIB_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AIRMIB.ONL_FLG == 'N' 
            && ((AIRMIB.KEY.BR == 173001 
            && !AIRMIB.KEY.ITM_NO.equalsIgnoreCase("3142400000") 
            && !AIRMIB.KEY.ITM_NO.equalsIgnoreCase("3141107963") 
            && !AIRMIB.KEY.ITM_NO.equalsIgnoreCase("3980300000") 
            && !AIRMIB.KEY.ITM_NO.equalsIgnoreCase("3141207964")) 
            || AIRMIB.KEY.BR != 173001)) {
            CEP.TRC(SCCGWA, AIRMIB.KEY.BR);
            CEP.TRC(SCCGWA, AIRMIB.ONL_FLG);
            WS_OPEN_AMT = AIRMIB.LBAL;
            B031_BROWSE_BPTVCHT_SUM();
            if (pgmRtn) return;
            AIRMIB.CBAL = WS_OPEN_AMT;
        }
        IBS.init(SCCGWA, AIRMIBA);
        AIRMIBA.KEY.BOOK_FLG = AIRMIB.KEY.GL_BOOK;
        AIRMIBA.KEY.AC = AIRMIB.AC_NO;
        T000_READ_AITMIBA_FIRST();
        if (pgmRtn) return;
        if (WS_MIBA_FLG == 'Y') {
            AIRMIB.LBAL = AIRMIBA.L_BAL;
        }
        CEP.TRC(SCCGWA, AIRMIB.HOT_FLG);
        if (AIRMIB.HOT_FLG == 'H') {
            IBS.init(SCCGWA, AIRMIBH);
            IBS.init(SCCGWA, AICRMIBH);
            AIRMIBH.KEY.GL_BOOK = AIRMIB.KEY.GL_BOOK;
            AIRMIBH.KEY.BR = AIRMIB.KEY.BR;
            AIRMIBH.KEY.CCY = AIRMIB.KEY.CCY;
            AIRMIBH.KEY.ITM_NO = AIRMIB.KEY.ITM_NO;
            AIRMIBH.KEY.SEQ = AIRMIB.KEY.SEQ;
            CEP.TRC(SCCGWA, AIRMIBH.KEY.GL_BOOK);
            CEP.TRC(SCCGWA, AIRMIBH.KEY.BR);
            CEP.TRC(SCCGWA, AIRMIBH.KEY.CCY);
            CEP.TRC(SCCGWA, AIRMIBH.KEY.ITM_NO);
            CEP.TRC(SCCGWA, AIRMIBH.KEY.SEQ);
            AICRMIBH.FUNC = 'S';
            AICRMIBH.POINTER = AIRMIBH;
            AICRMIBH.REC_LEN = 637;
            S000_CALL_AIZRMIBH();
            if (pgmRtn) return;
            if (AICRMIBH.RETURN_INFO == 'F') {
                CEP.TRC(SCCGWA, AICRMIBH.LBAL_SUM);
                CEP.TRC(SCCGWA, AICRMIBH.CBAL_SUM);
                AIRMIB.CBAL = AICRMIBH.CBAL_SUM;
            }
        }
    }
    public void B200_SET_SUB_TRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCSUBS);
        SCCSUBS.AP_CODE = 1;
        if (AICSMIB.FUNC_N == 'M') {
            SCCSUBS.TR_CODE = 5808;
        } else if (AICSMIB.FUNC_N == 'Q') {
            SCCSUBS.TR_CODE = 5809;
        } else if (AICSMIB.FUNC_N == 'S') {
            SCCSUBS.TR_CODE = 5803;
        } else if (AICSMIB.FUNC_N == 'R') {
            SCCSUBS.TR_CODE = 5804;
        } else if (AICSMIB.FUNC_N == 'H') {
            SCCSUBS.TR_CODE = 5805;
        } else if (AICSMIB.FUNC_N == 'U') {
            SCCSUBS.TR_CODE = 5807;
        }
        S000_SET_SUBS_TRN();
        if (pgmRtn) return;
    }
    public void B040_ADD_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICSMIB.SEQ);
        CEP.TRC(SCCGWA, AICSMIB.CCY);
        CEP.TRC(SCCGWA, AICSMIB.ITM_NO);
        CEP.TRC(SCCGWA, AICSMIB.BR);
        CEP.TRC(SCCGWA, AICSMIB.CCY_LMT);
        IBS.init(SCCGWA, AIRMIB);
        IBS.init(SCCGWA, AICRMIB);
        IBS.init(SCCGWA, AICOMIB);
        IBS.init(SCCGWA, AICNMIB);
        AICRMIB.FUNC = 'C';
        R000_TRANS_DATA_TO_MIB();
        if (pgmRtn) return;
        AIRMIB.STS = 'N';
        AIRMIB.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AIRMIB.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AIRMIB.OPEN_TLR = SCCGWA.COMM_AREA.TL_ID;
        AIRMIB.LAST_UPDATE_TLR = SCCGWA.COMM_AREA.TL_ID;
        AIRMIB.LAST_TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        AICRMIB.POINTER = AIRMIB;
        AICRMIB.REC_LEN = 634;
        S000_CALL_AIZRMIB();
        if (pgmRtn) return;
        if (AICRMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICRMIB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        AICNMIB.GL_BOOK = AICSMIB.GL_BOOK;
        AICNMIB.SEQ = AICSMIB.SEQ;
        if (AICSMIB.SEQ == 999999) {
            AICNMIB.SEQ = AIRMIB.KEY.SEQ;
        }
        AICNMIB.CCY = AICSMIB.CCY;
        AICNMIB.ITM_NO = AICSMIB.ITM_NO;
        AICNMIB.BR = AICSMIB.BR;
        AICNMIB.RVS_EXP = AICSMIB.RVS_EXP;
        AICNMIB.DRLT_BAL = AICSMIB.DRLT_BAL;
        AICNMIB.CRLT_BAL = AICSMIB.CRLT_BAL;
        AICNMIB.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICNMIB.LAST_UPDATE_TLR = SCCGWA.COMM_AREA.TL_ID;
        H000_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B050_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICSMIB.SEQ);
        CEP.TRC(SCCGWA, AICSMIB.CCY);
        CEP.TRC(SCCGWA, AICSMIB.ITM_NO);
        CEP.TRC(SCCGWA, AICSMIB.BR);
        CEP.TRC(SCCGWA, AICSMIB.DRLT_BAL);
        CEP.TRC(SCCGWA, AICSMIB.CRLT_BAL);
        CEP.TRC(SCCGWA, AICSMIB.CHK_FLG);
        CEP.TRC(SCCGWA, AICSMIB.CCY_LMT);
        CEP.TRC(SCCGWA, AIRMIB.CCY_LMT);
        R000_READUDP_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICSMIB.CCY_LMT);
        CEP.TRC(SCCGWA, AIRMIB.CCY_LMT);
        if (AIRMIB.STS == 'N') {
            R000_SAVE_OLD_HIS_DATA();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "111");
            if (AICSMIB.RVS_EXP == AIRMIB.RVS_EXP 
                && AICSMIB.DRLT_BAL == AIRMIB.DRLT_BAL 
                && AICSMIB.CRLT_BAL == AIRMIB.CRLT_BAL 
                && AICSMIB.AC_EXP == AIRMIB.AC_EXP 
                && AICSMIB.RVS_UNIT == AIRMIB.RVS_UNIT 
                && AICSMIB.NAME.equalsIgnoreCase(AIRMIB.CHS_NM) 
                && AICSMIB.APP.equalsIgnoreCase(AIRMIB.APP1) 
                && AICSMIB.APP1.equalsIgnoreCase(AIRMIB.APP2) 
                && AICSMIB.APP2.equalsIgnoreCase(AIRMIB.APP3) 
                && AICSMIB.APP3.equalsIgnoreCase(AIRMIB.APP4) 
                && AICSMIB.APP4.equalsIgnoreCase(AIRMIB.APP5) 
                && AICSMIB.APP5.equalsIgnoreCase(AIRMIB.APP6) 
                && AICSMIB.APP6.equalsIgnoreCase(AIRMIB.APP7) 
                && AICSMIB.APP6.equalsIgnoreCase(AIRMIB.APP8) 
                && AICSMIB.APP8.equalsIgnoreCase(AIRMIB.APP9) 
                && AICSMIB.APP9.equalsIgnoreCase(AIRMIB.APP10) 
                && AICSMIB.BAL_RFLG == AIRMIB.BAL_RFLG) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_INFO_NOCHANGE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (AICSMIB.BAL_RFLG == 'N' 
                && AIRMIB.BAL_RFLG == 'Y') {
                if (AIRMIB.BAL_DIR == 'C' 
                    && AIRMIB.CBAL < 0) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_BAL_ERROR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (AIRMIB.BAL_DIR == 'D' 
                    && AIRMIB.CBAL > 0) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_BAL_ERROR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            CEP.TRC(SCCGWA, "123");
            AICNMIB.GL_BOOK = AICSMIB.GL_BOOK;
            AICNMIB.SEQ = AICSMIB.SEQ;
            AICNMIB.CCY = AICSMIB.CCY;
            AICNMIB.ITM_NO = AICSMIB.ITM_NO;
            CEP.TRC(SCCGWA, "1234");
            AICNMIB.BR = AICSMIB.BR;
            CEP.TRC(SCCGWA, "1");
            AICNMIB.RVS_EXP = AICSMIB.RVS_EXP;
            CEP.TRC(SCCGWA, "2");
            CEP.TRC(SCCGWA, AICSMIB.DRLT_BAL);
            AICNMIB.DRLT_BAL = AICSMIB.DRLT_BAL;
            CEP.TRC(SCCGWA, "1122");
            AICNMIB.CRLT_BAL = AICSMIB.CRLT_BAL;
            AICNMIB.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AICNMIB.LAST_UPDATE_TLR = SCCGWA.COMM_AREA.TL_ID;
            CEP.TRC(SCCGWA, "222");
            CEP.TRC(SCCGWA, AIRMIB.KEY.ITM_NO);
            CEP.TRC(SCCGWA, AIRMIB.KEY.CCY);
            CEP.TRC(SCCGWA, AIRMIB.CCY_LMT);
            CEP.TRC(SCCGWA, AIRMIB.KEY.BR);
            CEP.TRC(SCCGWA, AIRMIB.DRLT_BAL);
            CEP.TRC(SCCGWA, AICSMIB.DRLT_BAL);
            CEP.TRC(SCCGWA, AIRMIB.CRLT_BAL);
            CEP.TRC(SCCGWA, AICSMIB.CRLT_BAL);
            CEP.TRC(SCCGWA, AIRMIB.CBAL);
            if (AIRMIB.DRLT_BAL != AICSMIB.DRLT_BAL) {
                WS_DRLT_BAL = AICSMIB.DRLT_BAL * ( -1 );
                CEP.TRC(SCCGWA, WS_DRLT_BAL);
                if (AIRMIB.CBAL < WS_DRLT_BAL) {
                    CEP.TRC(SCCGWA, "111111111111111111");
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_CBAL_BIG_DRBAL;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (AIRMIB.CRLT_BAL != AICSMIB.CRLT_BAL) {
                if (AIRMIB.CBAL > AICSMIB.CRLT_BAL) {
                    CEP.TRC(SCCGWA, "222222222222222222");
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_CBAL_BIG_CRBAL;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            CEP.TRC(SCCGWA, "333");
            CEP.TRC(SCCGWA, AICSMIB.AC_EXP);
            CEP.TRC(SCCGWA, AIRMIB.AC_EXP);
            if (AICSMIB.AC_EXP != AIRMIB.AC_EXP) {
                IBS.init(SCCGWA, AIRGINF);
                IBS.init(SCCGWA, AICRGINF);
                AICRGINF.INFO.FUNC = 'B';
                AICRGINF.INFO.OPT = '6';
                AIRGINF.AC = AIRMIB.AC_NO;
                CEP.TRC(SCCGWA, AIRMIB.AC_NO);
                S000_CALL_AIZRGINF();
                if (pgmRtn) return;
                AICRGINF.INFO.OPT = 'N';
                S000_CALL_AIZRGINF();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, AICRGINF.RETURN_INFO);
                while (AICRGINF.RETURN_INFO != 'N') {
                    CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
                    CEP.TRC(SCCGWA, AICSMIB.AC_EXP);
                    CEP.TRC(SCCGWA, AIRGINF.RVS_EXP);
                    if (AICSMIB.AC_EXP < AIRGINF.RVS_EXP) {
                        WS_CNT1 = WS_CNT1 + 1;
                        WS_RVSNO_DATA = WS_RVSNO_DATA.get(WS_CNT1-1);
                        WS_RVSNO_DATA.WS_REV_NO = AIRGINF.KEY.RVS_NO;
                        WS_RVSNO_DATA.set(WS_CNT1-1, WS_RVSNO_DATA);
                        CEP.TRC(SCCGWA, WS_CNT1);
                        CEP.TRC(SCCGWA, WS_RVSNO_DATA.get(WS_CNT1-1).WS_REV_NO);
                    }
                    AICRGINF.INFO.OPT = 'N';
                    S000_CALL_AIZRGINF();
                    if (pgmRtn) return;
                }
                AICRGINF.INFO.OPT = 'E';
                S000_CALL_AIZRGINF();
                if (pgmRtn) return;
                for (WS_Z = 1; WS_Z <= WS_CNT1; WS_Z += 1) {
                    IBS.init(SCCGWA, AIRGINF);
                    IBS.init(SCCGWA, AICRGINF);
                    AIRGINF.KEY.RVS_NO = WS_RVSNO_DATA.get(WS_Z-1).WS_REV_NO;
                    CEP.TRC(SCCGWA, WS_Z);
                    CEP.TRC(SCCGWA, WS_RVSNO_DATA.get(WS_Z-1).WS_REV_NO);
                    AICRGINF.INFO.FUNC = 'R';
                    S000_CALL_AIZRGINF();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
                    CEP.TRC(SCCGWA, AIRGINF.RVS_EXP);
                    AICRGINF.INFO.FUNC = 'U';
                    AIRGINF.RVS_EXP = AICSMIB.AC_EXP;
                    S000_CALL_AIZRGINF();
                    if (pgmRtn) return;
                }
            }
            if (AICSMIB.RVS_EXP != AIRMIB.RVS_EXP 
                || AICSMIB.RVS_UNIT != AIRMIB.RVS_UNIT) {
                CEP.TRC(SCCGWA, "TESTRVS");
                IBS.init(SCCGWA, AIRGINF);
                IBS.init(SCCGWA, AICRGINF);
                WS_CNT1 = 0;
                WS_Z = 0;
                AICRGINF.INFO.FUNC = 'B';
                AICRGINF.INFO.OPT = '6';
                AIRGINF.AC = AIRMIB.AC_NO;
                CEP.TRC(SCCGWA, AIRMIB.AC_NO);
                S000_CALL_AIZRGINF();
                if (pgmRtn) return;
                AICRGINF.INFO.OPT = 'N';
                S000_CALL_AIZRGINF();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, AICRGINF.RETURN_INFO);
                while (AICRGINF.RETURN_INFO != 'N') {
                    CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
                    CEP.TRC(SCCGWA, AICSMIB.AC_EXP);
                    CEP.TRC(SCCGWA, AIRGINF.RVS_EXP);
                    CEP.TRC(SCCGWA, WS_CNT1);
                    WS_CNT1 = WS_CNT1 + 1;
                    CEP.TRC(SCCGWA, WS_CNT1);
                    WS_RVSNO_DATA = WS_RVSNO_DATA.get(WS_CNT1-1);
                    WS_RVSNO_DATA.WS_REV_NO = AIRGINF.KEY.RVS_NO;
                    WS_RVSNO_DATA.set(WS_CNT1-1, WS_RVSNO_DATA);
                    CEP.TRC(SCCGWA, WS_CNT1);
                    CEP.TRC(SCCGWA, WS_RVSNO_DATA.get(WS_CNT1-1).WS_REV_NO);
                    AICRGINF.INFO.OPT = 'N';
                    S000_CALL_AIZRGINF();
                    if (pgmRtn) return;
                }
                AICRGINF.INFO.OPT = 'E';
                S000_CALL_AIZRGINF();
                if (pgmRtn) return;
                for (WS_Z = 1; WS_Z <= WS_CNT1; WS_Z += 1) {
                    IBS.init(SCCGWA, AIRGINF);
                    IBS.init(SCCGWA, AICRGINF);
                    AIRGINF.KEY.RVS_NO = WS_RVSNO_DATA.get(WS_Z-1).WS_REV_NO;
                    CEP.TRC(SCCGWA, WS_Z);
                    CEP.TRC(SCCGWA, WS_RVSNO_DATA.get(WS_Z-1).WS_REV_NO);
                    AICRGINF.INFO.FUNC = 'R';
                    S000_CALL_AIZRGINF();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, AIRGINF.KEY.RVS_NO);
                    CEP.TRC(SCCGWA, AIRGINF.RVS_EXP);
                    R000_CAL_EXP_DT();
                    if (pgmRtn) return;
                    AICRGINF.INFO.FUNC = 'U';
                    AIRGINF.RVS_EXP = WS_RVS_EXP_DT;
                    S000_CALL_AIZRGINF();
                    if (pgmRtn) return;
                }
            }
            CEP.TRC(SCCGWA, AICSMIB.CCY_LMT);
            CEP.TRC(SCCGWA, AIRMIB.CCY_LMT);
            AICRMIB.FUNC = 'U';
            CEP.TRC(SCCGWA, "QUERY CMIB WITH SEQ = 999999");
            IBS.init(SCCGWA, AIRCMIB);
            IBS.init(SCCGWA, AICPCMIB);
            AIRCMIB.KEY.GL_BOOK = AICSMIB.GL_BOOK;
            AIRCMIB.KEY.BR = AICSMIB.BR;
            AIRCMIB.KEY.ITM = AICSMIB.ITM_NO;
            AIRCMIB.KEY.SEQ = 999999;
            CEP.TRC(SCCGWA, AIRCMIB);
            S000_CALL_AIZPCMIB();
            if (pgmRtn) return;
            if (AICPCMIB.RETURN_INFO == 'F' 
                && AIRCMIB.KEY.SEQ == 999999) {
                CEP.TRC(SCCGWA, AIRCMIB.KEY.SEQ);
                CEP.TRC(SCCGWA, AIRCMIB.CHS_NM);
                AIRMIB.CHS_NM = AICSMIB.NAME;
            }
            AIRMIB.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AIRMIB.LAST_UPDATE_TLR = SCCGWA.COMM_AREA.TL_ID;
            AIRMIB.RVS_EXP = AICSMIB.RVS_EXP;
            AIRMIB.RVS_UNIT = AICSMIB.RVS_UNIT;
            AIRMIB.DRLT_BAL = AICSMIB.DRLT_BAL;
            AIRMIB.CRLT_BAL = AICSMIB.CRLT_BAL;
            AIRMIB.AC_EXP = AICSMIB.AC_EXP;
            AIRMIB.ADDR = AICSMIB.ADDR;
            AIRMIB.APP1 = AICSMIB.APP;
            AIRMIB.APP2 = AICSMIB.APP1;
            AIRMIB.APP3 = AICSMIB.APP2;
            AIRMIB.APP4 = AICSMIB.APP3;
            AIRMIB.APP5 = AICSMIB.APP4;
            AIRMIB.APP6 = AICSMIB.APP5;
            AIRMIB.APP7 = AICSMIB.APP6;
            AIRMIB.APP8 = AICSMIB.APP7;
            AIRMIB.APP9 = AICSMIB.APP8;
            AIRMIB.APP10 = AICSMIB.APP9;
            AIRMIB.CHS_NM = AICSMIB.NAME;
            AIRMIB.BAL_RFLG = AICSMIB.BAL_RFLG;
            AIRMIB.LAST_TX_DT = SCCGWA.COMM_AREA.AC_DATE;
            AICRMIB.POINTER = AIRMIB;
            AICRMIB.REC_LEN = 634;
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
            H000_HISTORY_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_STS_WRONG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B060_REACTIVE_PROCESS() throws IOException,SQLException,Exception {
        R000_READUDP_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICSMIB.ITM_NO);
        CEP.TRC(SCCGWA, AICSMIB.SEQ);
        IBS.init(SCCGWA, AIRCMIB);
        IBS.init(SCCGWA, AICPCMIB);
        AIRCMIB.KEY.GL_BOOK = AICSMIB.GL_BOOK;
        AIRCMIB.KEY.BR = AICSMIB.BR;
        AIRCMIB.KEY.ITM = AICSMIB.ITM_NO;
        AIRCMIB.KEY.SEQ = AICSMIB.SEQ;
        CEP.TRC(SCCGWA, AIRCMIB);
        S000_CALL_AIZPCMIB();
        if (pgmRtn) return;
        if (AICPCMIB.RETURN_INFO == 'F' 
            && AIRCMIB.KEY.BR != 999999 
            && AIRCMIB.STS != 'N') {
            IBS.init(SCCGWA, AICPCMIB);
            IBS.init(SCCGWA, AIRCMIB);
            AIRCMIB.KEY.GL_BOOK = AICSMIB.GL_BOOK;
            AIRCMIB.KEY.BR = 999999;
            AIRCMIB.KEY.ITM = AICSMIB.ITM_NO;
            AIRCMIB.KEY.SEQ = AICSMIB.SEQ;
            S000_CALL_AIZPCMIB();
            if (pgmRtn) return;
            if (AICPCMIB.RETURN_INFO == 'N') {
                IBS.init(SCCGWA, AICPCMIB);
                IBS.init(SCCGWA, AIRCMIB);
                AIRCMIB.KEY.GL_BOOK = AICSMIB.GL_BOOK;
                AIRCMIB.KEY.BR = AICSMIB.BR;
                AIRCMIB.KEY.ITM = AICSMIB.ITM_NO;
                AIRCMIB.KEY.SEQ = AICSMIB.SEQ;
                S000_CALL_AIZPCMIB();
                if (pgmRtn) return;
            }
        }
        if (AICPCMIB.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "QUERY CMIB WITH SEQ = 999999");
            IBS.init(SCCGWA, AIRCMIB);
            IBS.init(SCCGWA, AICPCMIB);
            AIRCMIB.KEY.GL_BOOK = AICSMIB.GL_BOOK;
            AIRCMIB.KEY.BR = AICSMIB.BR;
            AIRCMIB.KEY.ITM = AICSMIB.ITM_NO;
            AIRCMIB.KEY.SEQ = 999999;
            S000_CALL_AIZPCMIB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AIRCMIB.KEY.SEQ);
        }
        CEP.TRC(SCCGWA, AIRCMIB);
        CEP.TRC(SCCGWA, AIRCMIB.STS);
        CEP.TRC(SCCGWA, AIRCMIB.AC_EXP);
        CEP.TRC(SCCGWA, AICSMIB.STS);
        if (AIRCMIB.STS != 'N' 
            && AIRCMIB.STS != 'H') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_CMIB_STS_NOT_NORHLD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R000_READUDP_PROCESS();
        if (pgmRtn) return;
        WS_OLD_OPEN_DT = AIRMIB.OPEN_DATE;
        WS_OLD_CLS_DT = AIRMIB.CLS_DATE;
        CEP.TRC(SCCGWA, WS_OLD_OPEN_DT);
        CEP.TRC(SCCGWA, WS_OLD_CLS_DT);
        CEP.TRC(SCCGWA, AIRMIB.STS);
        if (AIRMIB.STS == 'S' 
            || AIRMIB.STS == 'C') {
            R000_SAVE_OLD_HIS_DATA();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "TESTREACT");
            AICRMIB.FUNC = 'U';
            AIRMIB.STS = 'N';
            AIRMIB.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AIRMIB.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AIRMIB.CLS_DATE = 0;
            AIRMIB.CLS_TLR = " ";
            AIRMIB.LAST_UPDATE_TLR = SCCGWA.COMM_AREA.TL_ID;
            CEP.TRC(SCCGWA, AICSMIB.AC_EXP);
            AIRMIB.AC_EXP = AICSMIB.AC_EXP;
            AICRMIB.POINTER = AIRMIB;
            AICRMIB.REC_LEN = 634;
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
            AICNMIB.STS = AIRMIB.STS;
            R000_SAVE_NEW_HIS_DATA();
            if (pgmRtn) return;
            H000_HISTORY_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_STS_WRONG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B070_HOLD_PROCESS() throws IOException,SQLException,Exception {
        R000_READUDP_PROCESS();
        if (pgmRtn) return;
        if (AIRMIB.ONL_FLG == 'N' 
            && (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT"))) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_ONLFLG_NOTOPR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AIRMIB.STS == 'N') {
            R000_SAVE_OLD_HIS_DATA();
            if (pgmRtn) return;
            AICRMIB.FUNC = 'U';
            AIRMIB.STS = 'H';
            AIRMIB.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AIRMIB.LAST_UPDATE_TLR = SCCGWA.COMM_AREA.TL_ID;
            AICRMIB.POINTER = AIRMIB;
            AICRMIB.REC_LEN = 634;
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
            AICNMIB.STS = AIRMIB.STS;
            R000_SAVE_NEW_HIS_DATA();
            if (pgmRtn) return;
            H000_HISTORY_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_STS_WRONG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B080_UNHOLD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRCMIB);
        IBS.init(SCCGWA, AICPCMIB);
        AIRCMIB.KEY.GL_BOOK = AICSMIB.GL_BOOK;
        AIRCMIB.KEY.BR = AICSMIB.BR;
        AIRCMIB.KEY.ITM = AICSMIB.ITM_NO;
        AIRCMIB.KEY.SEQ = AICSMIB.SEQ;
        S000_CALL_AIZPCMIB();
        if (pgmRtn) return;
        R000_READUDP_PROCESS();
        if (pgmRtn) return;
        if (AIRMIB.STS == 'H') {
            R000_SAVE_OLD_HIS_DATA();
            if (pgmRtn) return;
            AICRMIB.FUNC = 'U';
            AIRMIB.STS = 'N';
            CEP.TRC(SCCGWA, AIRCMIB.STS);
            CEP.TRC(SCCGWA, AIRMIB.AC_EXP);
            CEP.TRC(SCCGWA, AIRMIB.CBAL);
            if (AIRCMIB.STS == 'S' 
                || AIRMIB.AC_EXP <= SCCGWA.COMM_AREA.AC_DATE) {
                if (AIRCMIB.STS == 'S') {
                    AIRMIB.CLN_REN = "THE AITCMIB IS CLOSE";
                } else {
                    AIRMIB.CLN_REN = "THE AITMIB FOR EXP-DATE";
                }
                if (AIRMIB.CBAL == 0) {
                    AIRMIB.STS = 'C';
                } else {
                    if (AIRCMIB.STS == 'S') {
                        AIRMIB.CLN_REN = "THE AITCMIB IS CLOSE";
                    } else {
                        AIRMIB.CLN_REN = "THE AITMIB FOR EXP-DATE";
                    }
                    AIRMIB.STS = 'S';
                }
                AIRMIB.CLS_DATE = SCCGWA.COMM_AREA.AC_DATE;
                AIRMIB.CLS_TLR = SCCGWA.COMM_AREA.TL_ID;
            }
            CEP.TRC(SCCGWA, AIRMIB.STS);
            AIRMIB.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AIRMIB.LAST_UPDATE_TLR = SCCGWA.COMM_AREA.TL_ID;
            AICRMIB.POINTER = AIRMIB;
            AICRMIB.REC_LEN = 634;
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
            AICNMIB.STS = AIRMIB.STS;
            R000_SAVE_NEW_HIS_DATA();
            if (pgmRtn) return;
            H000_HISTORY_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_STS_WRONG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B090_STOP_PROCESS() throws IOException,SQLException,Exception {
        R000_READUDP_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AIRMIB.CBAL);
        if (AIRMIB.ONL_FLG == 'N' 
            && (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT"))) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_ONLFLG_NOTOPR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (AIRMIB.STS == 'N') {
            R000_SAVE_OLD_HIS_DATA();
            if (pgmRtn) return;
            if (AIRMIB.HOT_FLG == 'H') {
                CEP.TRC(SCCGWA, "MIB HOT FLG CHANGE");
                AIRMIB.HOT_FLG = 'N';
                IBS.init(SCCGWA, AIRMIBH);
                IBS.init(SCCGWA, AICRMIBH);
                AIRMIBH.KEY.GL_BOOK = AIRMIB.KEY.GL_BOOK;
                AIRMIBH.KEY.BR = AIRMIB.KEY.BR;
                AIRMIBH.KEY.CCY = AIRMIB.KEY.CCY;
                AIRMIBH.KEY.ITM_NO = AIRMIB.KEY.ITM_NO;
                AIRMIBH.KEY.SEQ = AIRMIB.KEY.SEQ;
                CEP.TRC(SCCGWA, AIRMIBH.KEY.GL_BOOK);
                CEP.TRC(SCCGWA, AIRMIBH.KEY.BR);
                CEP.TRC(SCCGWA, AIRMIBH.KEY.CCY);
                CEP.TRC(SCCGWA, AIRMIBH.KEY.ITM_NO);
                CEP.TRC(SCCGWA, AIRMIBH.KEY.SEQ);
                AICRMIBH.FUNC = 'S';
                AICRMIBH.POINTER = AIRMIBH;
                AICRMIBH.REC_LEN = 637;
                S000_CALL_AIZRMIBH();
                if (pgmRtn) return;
                if (AICRMIBH.RETURN_INFO == 'F') {
                    CEP.TRC(SCCGWA, AICRMIBH.LBAL_SUM);
                    CEP.TRC(SCCGWA, AICRMIBH.CBAL_SUM);
                    AIRMIB.LBAL = AICRMIBH.LBAL_SUM;
                    AIRMIB.CBAL = AICRMIBH.CBAL_SUM;
                    IBS.init(SCCGWA, AIRMIBH);
                    IBS.init(SCCGWA, AICRMIBH);
                    AIRMIBH.KEY.GL_BOOK = AIRMIB.KEY.GL_BOOK;
                    AIRMIBH.KEY.BR = AIRMIB.KEY.BR;
                    AIRMIBH.KEY.CCY = AIRMIB.KEY.CCY;
                    AIRMIBH.KEY.ITM_NO = AIRMIB.KEY.ITM_NO;
                    AIRMIBH.KEY.SEQ = AIRMIB.KEY.SEQ;
                    AICRMIBH.FUNC = 'D';
                    AICRMIBH.POINTER = AIRMIBH;
                    AICRMIBH.REC_LEN = 637;
                    S000_CALL_AIZRMIBH();
                    if (pgmRtn) return;
                }
            }
            AICRMIB.FUNC = 'U';
            if (AIRMIB.CBAL == 0) {
                AIRMIB.STS = 'C';
                AICNMIB.STS = AIRMIB.STS;
                AIRMIB.CLS_DATE = SCCGWA.COMM_AREA.AC_DATE;
                AIRMIB.CLS_TLR = SCCGWA.COMM_AREA.TL_ID;
            } else {
                AIRMIB.STS = 'S';
                IBS.init(SCCGWA, AIRCMIB);
                IBS.init(SCCGWA, AICPCMIB);
                AIRCMIB.KEY.GL_BOOK = AICSMIB.GL_BOOK;
                AIRCMIB.KEY.BR = AICSMIB.BR;
                AIRCMIB.KEY.ITM = AICSMIB.ITM_NO;
                AIRCMIB.KEY.SEQ = AICSMIB.SEQ;
                S000_CALL_AIZPCMIB();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, AIRCMIB.STS);
                if (AIRCMIB.STS == 'S') {
                    AIRMIB.CLN_REN = "THE AITCMIB IS CLOSE";
                }
                if (AIRMIB.AC_EXP <= SCCGWA.COMM_AREA.AC_DATE) {
                    AIRMIB.CLN_REN = "THE AITMIB FOR EXP-DATE";
                }
                AICNMIB.STS = AIRMIB.STS;
            }
            CEP.TRC(SCCGWA, AIRMIB.STS);
            AIRMIB.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AIRMIB.LAST_UPDATE_TLR = SCCGWA.COMM_AREA.TL_ID;
            AIRMIB.CLS_DATE = SCCGWA.COMM_AREA.AC_DATE;
            AIRMIB.CLS_TLR = SCCGWA.COMM_AREA.TL_ID;
            AICRMIB.POINTER = AIRMIB;
            AICRMIB.REC_LEN = 634;
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
            R000_SAVE_NEW_HIS_DATA();
            if (pgmRtn) return;
            H000_HISTORY_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_STS_WRONG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_READUDP_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRMIB);
        IBS.init(SCCGWA, AICRMIB);
        IBS.init(SCCGWA, AICOMIB);
        IBS.init(SCCGWA, AICNMIB);
        AICRMIB.FUNC = 'R';
        CEP.TRC(SCCGWA, AICSMIB.ITM_NO);
        CEP.TRC(SCCGWA, AICSMIB.BR);
        CEP.TRC(SCCGWA, AICSMIB.SEQ);
        CEP.TRC(SCCGWA, AICSMIB.GL_BOOK);
        CEP.TRC(SCCGWA, AICSMIB.CCY);
        AIRMIB.KEY.ITM_NO = AICSMIB.ITM_NO;
        AIRMIB.KEY.BR = AICSMIB.BR;
        AIRMIB.KEY.SEQ = AICSMIB.SEQ;
        AIRMIB.KEY.CCY = AICSMIB.CCY;
        AIRMIB.KEY.GL_BOOK = AICSMIB.GL_BOOK;
        WS_AC_NO.WS_BR = AICSMIB.BR;
        WS_AC_NO.WS_CCY = AICSMIB.CCY;
        WS_AC_NO.WS_ITM = AICSMIB.ITM_NO;
        WS_AC_NO.WS_AC_SEQ = AICSMIB.SEQ;
        AIRMIB.AC_NO = IBS.CLS2CPY(SCCGWA, WS_AC_NO);
        CEP.TRC(SCCGWA, WS_AC_NO);
        AICRMIB.POINTER = AIRMIB;
        AICRMIB.REC_LEN = 634;
        S000_CALL_AIZRMIB();
        if (pgmRtn) return;
        if (AICRMIB.RETURN_INFO == 'N') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITMIB_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_SAVE_OLD_HIS_DATA() throws IOException,SQLException,Exception {
        AICOMIB.GL_BOOK = AIRMIB.KEY.GL_BOOK;
        AICOMIB.STS = AIRMIB.STS;
        AICOMIB.SEQ = AIRMIB.KEY.SEQ;
        AICOMIB.CCY = AIRMIB.KEY.CCY;
        AICOMIB.ITM_NO = AIRMIB.KEY.ITM_NO;
        AICOMIB.BR = AIRMIB.KEY.BR;
        AICOMIB.RVS_EXP = AIRMIB.RVS_EXP;
        AICOMIB.DRLT_BAL = AIRMIB.DRLT_BAL;
        AICOMIB.CRLT_BAL = AIRMIB.CRLT_BAL;
        AICOMIB.UPDTBL_DATE = AIRMIB.UPDTBL_DATE;
        AICOMIB.OPEN_DATE = AIRMIB.OPEN_DATE;
        AICOMIB.CLS_DATE = AIRMIB.CLS_DATE;
    }
    public void R000_SAVE_NEW_HIS_DATA() throws IOException,SQLException,Exception {
        AICNMIB.GL_BOOK = AIRMIB.KEY.GL_BOOK;
        AICNMIB.SEQ = AIRMIB.KEY.SEQ;
        AICNMIB.CCY = AIRMIB.KEY.CCY;
        AICNMIB.ITM_NO = AIRMIB.KEY.ITM_NO;
        AICNMIB.BR = AIRMIB.KEY.BR;
        AICNMIB.RVS_EXP = AIRMIB.RVS_EXP;
        AICNMIB.DRLT_BAL = AIRMIB.DRLT_BAL;
        AICNMIB.CRLT_BAL = AIRMIB.CRLT_BAL;
        AICNMIB.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICNMIB.LAST_UPDATE_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void R000_TRANS_DATA_BRW_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRCMIB);
        IBS.init(SCCGWA, AICPCMIB);
        AIRCMIB.KEY.GL_BOOK = AIRMIB.KEY.GL_BOOK;
        AIRCMIB.KEY.BR = AIRMIB.KEY.BR;
        AIRCMIB.KEY.ITM = AIRMIB.KEY.ITM_NO;
        AIRCMIB.KEY.SEQ = AIRMIB.KEY.SEQ;
        CEP.TRC(SCCGWA, AIRCMIB);
        S000_CALL_AIZPCMIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AIRCMIB.CHS_NM);
        if (AICPCMIB.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "QUERY CMIB WITH SEQ = 999999");
            IBS.init(SCCGWA, AIRCMIB);
            IBS.init(SCCGWA, AICPCMIB);
            AIRCMIB.KEY.GL_BOOK = AIRMIB.KEY.GL_BOOK;
            AIRCMIB.KEY.BR = AIRMIB.KEY.BR;
            AIRCMIB.KEY.ITM = AIRMIB.KEY.ITM_NO;
            AIRCMIB.KEY.SEQ = 999999;
            CEP.TRC(SCCGWA, AIRCMIB);
            S000_CALL_AIZPCMIB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AIRCMIB.KEY.SEQ);
            CEP.TRC(SCCGWA, AIRCMIB.CHS_NM);
        }
        IBS.init(SCCGWA, WS_BRW_OUTPUT);
        WS_BRW_OUTPUT.WS_BRW_OUT_BR = AIRMIB.KEY.BR;
        WS_BRW_OUTPUT.WS_BRW_OUT_ITM = AIRMIB.KEY.ITM_NO;
        WS_BRW_OUTPUT.WS_BRW_OUT_SEQ = AIRMIB.KEY.SEQ;
        WS_BRW_OUTPUT.WS_BRW_OUT_CCY = AIRMIB.KEY.CCY;
        if (AIRMIB.ONL_FLG == 'N' 
            && ((AIRMIB.KEY.BR == 173001 
            && !AIRMIB.KEY.ITM_NO.equalsIgnoreCase("3142400000") 
            && !AIRMIB.KEY.ITM_NO.equalsIgnoreCase("3141107963") 
            && !AIRMIB.KEY.ITM_NO.equalsIgnoreCase("3980300000") 
            && !AIRMIB.KEY.ITM_NO.equalsIgnoreCase("3141207964")) 
            || AIRMIB.KEY.BR != 173001)) {
            CEP.TRC(SCCGWA, "KK22");
            CEP.TRC(SCCGWA, AIRMIB.KEY.BR);
            CEP.TRC(SCCGWA, AIRMIB.ONL_FLG);
            WS_OPEN_AMT = AIRMIB.LBAL;
            B031_BROWSE_BPTVCHT_SUM();
            if (pgmRtn) return;
            WS_BRW_OUTPUT.WS_BRW_OUT_CBAL = WS_OPEN_AMT;
        } else {
            WS_BRW_OUTPUT.WS_BRW_OUT_CBAL = AIRMIB.CBAL;
        }
        WS_BRW_OUTPUT.WS_BRW_OUT_AC_NO = AIRMIB.AC_NO;
        WS_BRW_OUTPUT.WS_BRW_OUT_STS = AIRMIB.STS;
        if (AIRCMIB.KEY.SEQ == 999999 
            && AICPCMIB.RETURN_INFO == 'F') {
            WS_BRW_OUTPUT.WS_BRW_OUT_ITMNM = AIRMIB.CHS_NM;
        }
        WS_BRW_OUTPUT.WS_BRW_OUT_ITMNM = AIRMIB.CHS_NM;
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_OUT_STS);
        CEP.TRC(SCCGWA, "FC");
        CEP.TRC(SCCGWA, WS_BRW_OUTPUT.WS_BRW_OUT_CBAL);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BRW_OUTPUT);
        SCCMPAG.DATA_LEN = 237;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_TO_MIB() throws IOException,SQLException,Exception {
        AIRMIB.KEY.GL_BOOK = AICSMIB.GL_BOOK;
        AIRMIB.KEY.SEQ = AICSMIB.SEQ;
        AIRMIB.KEY.CCY = AICSMIB.CCY;
        AIRMIB.KEY.ITM_NO = AICSMIB.ITM_NO;
        AIRMIB.KEY.BR = AICSMIB.BR;
        WS_AC_NO.WS_BR = AICSMIB.BR;
        WS_AC_NO.WS_CCY = AICSMIB.CCY;
        WS_AC_NO.WS_ITM = AICSMIB.ITM_NO;
        WS_AC_NO.WS_AC_SEQ = AICSMIB.SEQ;
        AIRMIB.AC_NO = IBS.CLS2CPY(SCCGWA, WS_AC_NO);
        CEP.TRC(SCCGWA, AICSMIB.NAME);
        AIRMIB.CHS_NM = AICSMIB.NAME;
        if (AICSMIB.SEQ == 999999) {
            T000_STARTBR_AITMIB_FIRST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_MIB_REC_STATUS);
            CEP.TRC(SCCGWA, AIRMIB.KEY.SEQ);
            WS_AC_NO.WS_AC_SEQ = 0;
            if (WS_MIB_REC_STATUS == 'Y') {
                WS_AC_NO.WS_AC_SEQ = AIRMIB.KEY.SEQ + 1;
            }
            if (WS_MIB_REC_STATUS == 'N') {
                WS_AC_NO.WS_AC_SEQ = WS_AC_NO.WS_AC_SEQ + 1;
            }
            CEP.TRC(SCCGWA, WS_AC_NO.WS_AC_SEQ);
            if (WS_AC_NO.WS_AC_SEQ == 999999) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_EXCEED_MAX_SEQ);
            }
            AIRMIB.KEY.SEQ = WS_AC_NO.WS_AC_SEQ;
            AIRMIB.AC_NO = IBS.CLS2CPY(SCCGWA, WS_AC_NO);
            CEP.TRC(SCCGWA, AIRMIB.KEY.SEQ);
            CEP.TRC(SCCGWA, AIRMIB.AC_NO);
        }
        AIRMIB.AC_TYP = AICSMIB.AC_TYP;
        AIRMIB.CCY_LMT = AICSMIB.CCY_LMT;
        AIRMIB.BAL_DIR = AICSMIB.BAL_DIR;
        AIRMIB.BAL_RFLG = AICSMIB.BAL_RFLG;
        AIRMIB.DTL_FLG = AICSMIB.DTL_FLG;
        AIRMIB.RVS_TYP = AICSMIB.RVS_TYP;
        AIRMIB.RVS_KND = AICSMIB.RVS_KND;
        AIRMIB.RVS_EXP = AICSMIB.RVS_EXP;
        AIRMIB.RVS_UNIT = AICSMIB.RVS_UNIT;
        AIRMIB.AC_EXP = AICSMIB.AC_EXP;
        AIRMIB.MANUAL_FLG = AICSMIB.MANUAL_FLG;
        AIRMIB.AMT_DIR = AICSMIB.AMT_DIR;
        AIRMIB.ONL_FLG = AICSMIB.ONL_FLG;
        AIRMIB.CARD_FLG = AICSMIB.CARD_FLG;
        AIRMIB.HOT_FLG = AICSMIB.HOT_FLG;
        AIRMIB.DRLT_BAL = AICSMIB.DRLT_BAL;
        AIRMIB.CRLT_BAL = AICSMIB.CRLT_BAL;
        AIRMIB.BAL_CHK = AICSMIB.CHK_FLG;
        AIRMIB.APP1 = AICSMIB.APP;
        AIRMIB.APP2 = AICSMIB.APP1;
        AIRMIB.APP3 = AICSMIB.APP2;
        AIRMIB.APP4 = AICSMIB.APP3;
        AIRMIB.APP5 = AICSMIB.APP4;
        AIRMIB.APP6 = AICSMIB.APP5;
        AIRMIB.APP7 = AICSMIB.APP6;
        AIRMIB.APP8 = AICSMIB.APP7;
        AIRMIB.APP9 = AICSMIB.APP8;
        AIRMIB.APP10 = AICSMIB.APP9;
        AIRMIB.ADDR = AICSMIB.ADDR;
        AIRMIB.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AIRMIB.LAST_UPDATE_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void B020_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 237;
        SCCMPAG.SCR_ROW_CNT = 20;
        SCCMPAG.SCR_COL_CNT = 5;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AICRMIB);
        IBS.init(SCCGWA, AIRMIB);
        AICRMIB.FUNC = 'B';
        if (AICSMIB.BR == 999999) {
            while (SCCMPAG.FUNC != 'E') {
                AICRMIB.OPT = 'I';
                if (AICSMIB.ITM_NO.trim().length() == 0) {
                    AICRMIB.OPT = 'C';
                }
                B021_BROWSE_DATA_OTH();
                if (pgmRtn) return;
            }
        } else {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = AICSMIB.BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORG.LVL);
            CEP.TRC(SCCGWA, BPCPQORG.SUPR_BR);
            if (BPCPQORG.LVL == '1') {
                CEP.TRC(SCCGWA, BPCPQORG.BBR);
                WS_TMP_BBR = BPCPQORG.BBR;
                if (BPCPQORG.BBR != 0) {
                    IBS.init(SCCGWA, BPCPQORG);
                    BPCPQORG.BR = WS_TMP_BBR;
                    S000_CALL_BPZPQORG();
                    if (pgmRtn) return;
                    if (BPCPQORG.ATTR == '2') {
                        AIRMIB.KEY.BR = WS_TMP_BBR;
                        AICRMIB.OPT = '3';
                        if (AICSMIB.ITM_NO.trim().length() == 0) {
                            AICRMIB.OPT = 'M';
                        }
                        CEP.TRC(SCCGWA, AICRMIB.OPT);
                        B021_BROWSE_DATA_OTH();
                        if (pgmRtn) return;
                    }
                }
                if (WS_COUNT == 0) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITMIB_NOTFND;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else if (BPCPQORG.LVL == '2') {
                AIRMIB.KEY.BR = AICSMIB.BR;
                AICRMIB.OPT = '3';
                if (AICSMIB.ITM_NO.trim().length() == 0) {
                    AICRMIB.OPT = 'M';
                }
                CEP.TRC(SCCGWA, AICRMIB.OPT);
                B021_BROWSE_DATA_OTH();
                if (pgmRtn) return;
            } else if (BPCPQORG.LVL == '3') {
                AIRMIB.KEY.BR = AICSMIB.BR;
                AICRMIB.OPT = '3';
                if (AICSMIB.ITM_NO.trim().length() == 0) {
                    AICRMIB.OPT = 'M';
                }
                CEP.TRC(SCCGWA, AICRMIB.OPT);
                B021_BROWSE_DATA_OTH();
                if (pgmRtn) return;
            } else if (BPCPQORG.LVL == '9'
                || BPCPQORG.LVL == '8'
                || BPCPQORG.LVL == '7') {
                if (AICSMIB.TX_FLG == 'N' 
                    || AICSMIB.TX_FLG == ' ') {
                    AIRMIB.KEY.BR = 0;
                    AICRMIB.OPT = 'I';
                    if (AICSMIB.ITM_NO.trim().length() == 0) {
                        AICRMIB.OPT = 'C';
                    }
                    CEP.TRC(SCCGWA, AICRMIB.OPT);
                    B021_BROWSE_DATA_OTH();
                    if (pgmRtn) return;
                } else {
                    AIRMIB.KEY.BR = AICSMIB.BR;
                    AICRMIB.OPT = '3';
                    if (AICSMIB.ITM_NO.trim().length() == 0) {
                        AICRMIB.OPT = 'M';
                    }
                    CEP.TRC(SCCGWA, AICRMIB.OPT);
                    B021_BROWSE_DATA_OTH();
                    if (pgmRtn) return;
                }
            } else {
                AIRMIB.KEY.BR = 0;
                AICRMIB.OPT = '3';
                B021_BROWSE_DATA_FH();
                if (pgmRtn) return;
            }
        }
    }
    public void B021_BROWSE_DATA_OTH() throws IOException,SQLException,Exception {
        AIRMIB.KEY.GL_BOOK = AICSMIB.GL_BOOK;
        AIRMIB.KEY.SEQ = AICSMIB.SEQ;
        AIRMIB.KEY.ITM_NO = AICSMIB.ITM_NO;
        AIRMIB.KEY.CCY = AICSMIB.CCY;
        AIRMIB.AC_NO = AICSMIB.AC;
        AIRMIB.CHS_NM = AICSMIB.NAME;
        AICRMIB.POINTER = AIRMIB;
        AICRMIB.REC_LEN = 634;
        S000_CALL_AIZRMIB();
        if (pgmRtn) return;
        AICRMIB.OPT = 'N';
        S000_CALL_AIZRMIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICRMIB.RETURN_INFO);
        if (AICRMIB.RETURN_INFO == 'N') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITMIB_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        while (AICRMIB.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            CEP.TRC(SCCGWA, WS_COUNT);
            IBS.init(SCCGWA, AIRMIBA);
            AIRMIBA.KEY.BOOK_FLG = AIRMIB.KEY.GL_BOOK;
            AIRMIBA.KEY.AC = AIRMIB.AC_NO;
            T000_READ_AITMIBA_FIRST();
            if (pgmRtn) return;
            if (WS_MIBA_FLG == 'Y') {
                AIRMIB.LBAL = AIRMIBA.L_BAL;
            }
            CEP.TRC(SCCGWA, AIRMIB.HOT_FLG);
            if (AIRMIB.HOT_FLG == 'H') {
                IBS.init(SCCGWA, AIRMIBH);
                IBS.init(SCCGWA, AICRMIBH);
                AIRMIBH.KEY.GL_BOOK = AIRMIB.KEY.GL_BOOK;
                AIRMIBH.KEY.BR = AIRMIB.KEY.BR;
                AIRMIBH.KEY.CCY = AIRMIB.KEY.CCY;
                AIRMIBH.KEY.ITM_NO = AIRMIB.KEY.ITM_NO;
                AIRMIBH.KEY.SEQ = AIRMIB.KEY.SEQ;
                CEP.TRC(SCCGWA, AIRMIBH.KEY.GL_BOOK);
                CEP.TRC(SCCGWA, AIRMIBH.KEY.BR);
                CEP.TRC(SCCGWA, AIRMIBH.KEY.CCY);
                CEP.TRC(SCCGWA, AIRMIBH.KEY.ITM_NO);
                CEP.TRC(SCCGWA, AIRMIBH.KEY.SEQ);
                AICRMIBH.FUNC = 'S';
                AICRMIBH.POINTER = AIRMIBH;
                AICRMIBH.REC_LEN = 637;
                S000_CALL_AIZRMIBH();
                if (pgmRtn) return;
                if (AICRMIBH.RETURN_INFO == 'F') {
                    CEP.TRC(SCCGWA, AICRMIBH.LBAL_SUM);
                    CEP.TRC(SCCGWA, AICRMIBH.CBAL_SUM);
                    AIRMIB.CBAL = AICRMIBH.CBAL_SUM;
                }
            }
            R000_TRANS_DATA_BRW_OUTPUT();
            if (pgmRtn) return;
            WS_COUNT = WS_COUNT + 1;
            AICRMIB.OPT = 'N';
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_COUNT);
        if (WS_COUNT == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITMIB_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        AICRMIB.OPT = 'E';
        S000_CALL_AIZRMIB();
        if (pgmRtn) return;
    }
    public void B021_BROWSE_DATA_FH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPORLO);
        BPCPORLO.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPORLO.BR = AICSMIB.BR;
        CEP.TRC(SCCGWA, BPCPORLO.BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
        S000_CALL_BPZPORLO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPORLO.SUB_NUM);
        CEP.TRC(SCCGWA, BPCPORLO);
        if (BPCPORLO.SUB_NUM == 0) {
            IBS.init(SCCGWA, BPCPORLO);
            BPCPORLO.BNK = SCCGWA.COMM_AREA.TR_BANK;
            WS_SUPR_ORG = BPCPQORG.SUPR_BR;
            BPCPORLO.BR = WS_SUPR_ORG;
            CEP.TRC(SCCGWA, BPCPORLO.BR);
            S000_CALL_BPZPORLO();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPORLO.SUB_NUM);
            CEP.TRC(SCCGWA, BPCPORLO);
        }
        CEP.TRC(SCCGWA, "TESTFH");
        for (WS_CNTA = 1; WS_CNTA <= BPCPORLO.SUB_NUM 
            && SCCMPAG.FUNC != 'E'; WS_CNTA += 1) {
            CEP.TRC(SCCGWA, WS_CNTA);
            IBS.init(SCCGWA, AICRMIB);
            IBS.init(SCCGWA, AIRMIB);
            AICRMIB.FUNC = 'B';
            AICRMIB.OPT = '3';
            AIRMIB.KEY.GL_BOOK = AICSMIB.GL_BOOK;
            AIRMIB.KEY.BR = BPCPORLO.SUB_BR_DATA[WS_CNTA-1].SUB_BR;
            AIRMIB.KEY.SEQ = AICSMIB.SEQ;
            AIRMIB.KEY.ITM_NO = AICSMIB.ITM_NO;
            AIRMIB.KEY.CCY = AICSMIB.CCY;
            AIRMIB.CHS_NM = AICSMIB.NAME;
            if (AICSMIB.ITM_NO.trim().length() == 0) {
                AICRMIB.OPT = 'M';
            }
            CEP.TRC(SCCGWA, AIRMIB.KEY.BR);
            AICRMIB.POINTER = AIRMIB;
            AICRMIB.REC_LEN = 634;
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
            AICRMIB.OPT = 'N';
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
            while (AICRMIB.RETURN_INFO != 'N') {
                IBS.init(SCCGWA, AIRMIBA);
                AIRMIBA.KEY.BOOK_FLG = AIRMIB.KEY.GL_BOOK;
                AIRMIBA.KEY.AC = AIRMIB.AC_NO;
                T000_READ_AITMIBA_FIRST();
                if (pgmRtn) return;
                if (WS_MIBA_FLG == 'Y') {
                    AIRMIB.LBAL = AIRMIBA.L_BAL;
                }
                CEP.TRC(SCCGWA, AIRMIB.HOT_FLG);
                if (AIRMIB.HOT_FLG == 'H') {
                    IBS.init(SCCGWA, AIRMIBH);
                    IBS.init(SCCGWA, AICRMIBH);
                    AIRMIBH.KEY.GL_BOOK = AIRMIB.KEY.GL_BOOK;
                    AIRMIBH.KEY.BR = AIRMIB.KEY.BR;
                    AIRMIBH.KEY.CCY = AIRMIB.KEY.CCY;
                    AIRMIBH.KEY.ITM_NO = AIRMIB.KEY.ITM_NO;
                    AIRMIBH.KEY.SEQ = AIRMIB.KEY.SEQ;
                    CEP.TRC(SCCGWA, AIRMIBH.KEY.GL_BOOK);
                    CEP.TRC(SCCGWA, AIRMIBH.KEY.BR);
                    CEP.TRC(SCCGWA, AIRMIBH.KEY.CCY);
                    CEP.TRC(SCCGWA, AIRMIBH.KEY.ITM_NO);
                    CEP.TRC(SCCGWA, AIRMIBH.KEY.SEQ);
                    AICRMIBH.FUNC = 'S';
                    AICRMIBH.POINTER = AIRMIBH;
                    AICRMIBH.REC_LEN = 637;
                    S000_CALL_AIZRMIBH();
                    if (pgmRtn) return;
                    if (AICRMIBH.RETURN_INFO == 'F') {
                        CEP.TRC(SCCGWA, AICRMIBH.LBAL_SUM);
                        CEP.TRC(SCCGWA, AICRMIBH.CBAL_SUM);
                        AIRMIB.CBAL = AICRMIBH.CBAL_SUM;
                    }
                }
                R000_TRANS_DATA_BRW_OUTPUT();
                if (pgmRtn) return;
                WS_COUNT = WS_COUNT + 1;
                AICRMIB.OPT = 'N';
                S000_CALL_AIZRMIB();
                if (pgmRtn) return;
            }
            AICRMIB.OPT = 'E';
            S000_CALL_AIZRMIB();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_COUNT);
        if (WS_COUNT == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITMIB_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CAL_EXP_DT() throws IOException,SQLException,Exception {
        WS_CAL_DATE = AIRGINF.FST_G_DT;
        WS_RVS_UNIT = AICSMIB.RVS_UNIT;
        WS_RVS_EXP = AICSMIB.RVS_EXP;
        CEP.TRC(SCCGWA, WS_CAL_DATE);
        CEP.TRC(SCCGWA, WS_RVS_UNIT);
        CEP.TRC(SCCGWA, WS_RVS_EXP);
        if (WS_CAL_DATE != 0 
            && WS_RVS_UNIT != ' ' 
            && (WS_RVS_EXP != 0 
            && WS_RVS_EXP != 0)) {
            CEP.TRC(SCCGWA, "11");
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = WS_CAL_DATE;
            CEP.TRC(SCCGWA, SCCCLDT.DATE1);
            if (WS_RVS_UNIT == 'D') {
                SCCCLDT.DAYS = WS_RVS_EXP;
                CEP.TRC(SCCGWA, SCCCLDT.DAYS);
            } else {
                SCCCLDT.MTHS = WS_RVS_EXP;
                CEP.TRC(SCCGWA, SCCCLDT.MTHS);
            }
            CEP.TRC(SCCGWA, "22");
            SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
            SCSSCLDT1.MP(SCCGWA, SCCCLDT);
            CEP.TRC(SCCGWA, "33");
            if (SCCCLDT.RC == 0) {
                WS_RVS_EXP_DT = SCCCLDT.DATE2;
            }
        } else {
            if (WS_RVS_UNIT != ' ' 
                && (WS_RVS_EXP == 0 
                || (WS_RVS_EXP == 0))) {
                WS_RVS_EXP_DT = 99991231;
            } else {
                WS_RVS_EXP_DT = 0;
            }
        }
        CEP.TRC(SCCGWA, WS_RVS_EXP_DT);
        if (AIRMIB.AC_EXP < WS_RVS_EXP_DT) {
            WS_RVS_EXP_DT = AIRMIB.AC_EXP;
        }
    }
    public void T000_READ_AITMIBA_FIRST() throws IOException,SQLException,Exception {
        AITMIBA_RD = new DBParm();
        AITMIBA_RD.TableName = "AITMIBA";
        AITMIBA_RD.where = "BOOK_FLG = :AIRMIBA.KEY.BOOK_FLG "
            + "AND AC = :AIRMIBA.KEY.AC";
        AITMIBA_RD.fst = true;
        AITMIBA_RD.order = "AC_DT DESC";
        IBS.READ(SCCGWA, AIRMIBA, this, AITMIBA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MIBA_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MIBA_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITMIBA";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void H000_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (AICSMIB.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.TX_RMK = "ADD AITMIB DEFINITION";
        } else if (AICSMIB.FUNC == 'R') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.TX_RMK = "THE OLD OPEN DATE IS (" + WS_OLD_OPEN_DT + ")" + "THE OLD CLS DATE IS (" + WS_OLD_CLS_DT + ")";
        } else if ((AICSMIB.FUNC == 'M' 
                || AICSMIB.FUNC == 'H' 
                || AICSMIB.FUNC == 'U' 
                || AICSMIB.FUNC == 'S')) {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.TX_RMK = "CHANGE AITMIB DEFINITION";
        } else {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "AICHMIB";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.FMT_ID_LEN = 102;
        BPCPNHIS.INFO.OLD_DAT_PT = AICOMIB;
        BPCPNHIS.INFO.NEW_DAT_PT = AICNMIB;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZFTLCM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-TLR-QRY-BR-CHK", BPCFTLCM);
        if (BPCFTLCM.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCFTLCM.RC.RC_CODE);
        }
    }
    public void S000_CALL_AIZRGINF() throws IOException,SQLException,Exception {
        AICRGINF.INFO.POINTER = AIRGINF;
        AICRGINF.INFO.LEN = 242;
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-GINF", AICRGINF);
        if (AICRGINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICRGINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZRMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-MIB", AICRMIB);
        if (AICRMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICRMIB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZRMIBH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-MIBH", AICRMIBH);
        CEP.TRC(SCCGWA, AICRMIBH.RC);
        CEP.TRC(SCCGWA, AICRMIBH.RC.RC_CODE);
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SET-SUBS-TRANS", SCCSUBS);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPCMIB() throws IOException,SQLException,Exception {
        AICPCMIB.POINTER = AIRCMIB;
        AICPCMIB.REC_LEN = 407;
        IBS.CALLCPN(SCCGWA, "AI-P-GET-CMIB", AICPCMIB);
        CEP.TRC(SCCGWA, AICPCMIB.RC.RC_CODE);
        CEP.TRC(SCCGWA, AICPCMIB.RETURN_INFO);
    }
    public void B010_1_GET_COA_FLG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQBKPM);
        WS_REC_STATUS = 'N';
        BPCQBKPM.FUNC = 'B';
        S000_CALL_BPZQBKPM();
        if (pgmRtn) return;
        for (WS_BOOK_CNT = 1; WS_REC_STATUS != 'Y'; WS_BOOK_CNT += 1) {
            if (BPCQBKPM.DATA[WS_BOOK_CNT-1].BOOK_FLG.equalsIgnoreCase(AICSMIB.GL_BOOK)) {
                WS_COA_FLG = BPCQBKPM.DATA[WS_BOOK_CNT-1].COA_FLG.charAt(0);
                WS_REC_STATUS = 'Y';
            }
        }
    }
    public void S000_CALL_BPZQBKPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-Q-MAINT-AMBKP", BPCQBKPM);
        CEP.TRC(SCCGWA, BPCQBKPM.RC.RC_RTNCODE);
        if (BPCQBKPM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQBKPM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_AITITUS() throws IOException,SQLException,Exception {
        AITITUS_RD = new DBParm();
        AITITUS_RD.TableName = "AITITUS";
        IBS.READ(SCCGWA, AIRITUS, AITITUS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DB2_REC_STATUS = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DB2_REC_STATUS = 'N';
        } else {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_SYS_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_AITITUS_FIRST() throws IOException,SQLException,Exception {
        AITITUS_RD = new DBParm();
        AITITUS_RD.TableName = "AITITUS";
        AITITUS_RD.where = "COA_FLG = :AIRITUS.KEY.COA_FLG "
            + "AND ITM_NO = :AIRITUS.KEY.ITM_NO "
            + "AND BR < > :AIRITUS.KEY.BR";
        AITITUS_RD.fst = true;
        IBS.READ(SCCGWA, AIRITUS, this, AITITUS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DB2_REC_STATUS = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DB2_REC_STATUS = 'N';
        } else {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_SYS_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B031_BROWSE_BPTVCHT_SUM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRVCHT);
        BPRVCHT.BOOK_FLG = AIRMIB.KEY.GL_BOOK;
        BPRVCHT.BR = AIRMIB.KEY.BR;
        BPRVCHT.CCY = AIRMIB.KEY.CCY;
        BPRVCHT.ITM = AIRMIB.KEY.ITM_NO;
        BPRVCHT.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_STARTBR_BPTVCHT();
        if (pgmRtn) return;
        T000_READNEXT_BPTVCHT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCTVCHT.RETURN_INFO);
        while (BPCTVCHT.RETURN_INFO != 'N') {
            if (BPRVCHT.MIB_AC == null) BPRVCHT.MIB_AC = "";
            JIBS_tmp_int = BPRVCHT.MIB_AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) BPRVCHT.MIB_AC += " ";
            if (BPRVCHT.MIB_AC.substring(20 - 1, 20 + 4 - 1).trim().length() == 0) WS_ACSEQ = 0;
            else WS_ACSEQ = Short.parseShort(BPRVCHT.MIB_AC.substring(20 - 1, 20 + 4 - 1));
            CEP.TRC(SCCGWA, BPRVCHT.MIB_AC);
            CEP.TRC(SCCGWA, WS_ACSEQ);
            CEP.TRC(SCCGWA, AIRMIB.KEY.SEQ);
            if (AIRMIB.KEY.SEQ == WS_ACSEQ) {
                if (BPRVCHT.SIGN == 'D') {
                    WS_OPEN_AMT = WS_OPEN_AMT - BPRVCHT.AMT;
                } else {
                    WS_OPEN_AMT = WS_OPEN_AMT + BPRVCHT.AMT;
                }
            }
            T000_READNEXT_BPTVCHT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "111111");
        T000_ENDBR_BPTVCHT();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MBRW-PARM", BPCRMBPM);
        CEP.TRC(SCCGWA, BPCRMBPM.RC.RC_CODE);
    }
    public void T000_STARTBR_BPTVCHT() throws IOException,SQLException,Exception {
        BPTVCHT_BR.rp = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTVCHT_BR.rp.TableName = "BPTVCHT1";
        else BPTVCHT_BR.rp.TableName = "BPTVCHT2";
        BPTVCHT_BR.rp.where = "BR = :BPRVCHT.BR "
            + "AND ITM = :BPRVCHT.ITM "
            + "AND CCY = :BPRVCHT.CCY "
            + "AND BOOK_FLG = :BPRVCHT.BOOK_FLG "
            + "AND AC_DATE = :BPRVCHT.KEY.AC_DATE";
        BPTVCHT_BR.rp.order = "MIB_AC,TR_DATE,SET_NO,SET_SEQ";
        IBS.STARTBR(SCCGWA, BPRVCHT, this, BPTVCHT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_BPTVCHT() throws IOException,SQLException,Exception {
        BPTVCHT_RD = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTVCHT_RD.TableName = "BPTVCHT1";
        else BPTVCHT_RD.TableName = "BPTVCHT2";
        IBS.READ(SCCGWA, BPRVCHT, BPTVCHT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTVCHT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTVCHT.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTVCHT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BPTVCHT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRVCHT, this, BPTVCHT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTVCHT.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "DDDDD");
            BPCTVCHT.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTVCHT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTVCHT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTVCHT_BR);
    }
    public void T000_STARTBR_AITMIB_FIRST() throws IOException,SQLException,Exception {
        AITMIB_RD = new DBParm();
        AITMIB_RD.TableName = "AITMIB";
        AITMIB_RD.col = "SEQ";
        AITMIB_RD.eqWhere = "GL_BOOK,BR,ITM_NO,CCY";
        AITMIB_RD.fst = true;
        AITMIB_RD.order = "AC_NO DESC";
        IBS.READ(SCCGWA, AIRMIB, AITMIB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MIB_REC_STATUS = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MIB_REC_STATUS = 'N';
        } else {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_SYS_ERR);
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
