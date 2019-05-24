package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class AIZSPI15 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_PARM_TYPE = "PAI15";
    String K_FMT_CD_1 = "AIP15";
    String K_HIS_REMARKS = "AI P TABLE 1 MAINTENANCE";
    String CPN_PARM_BRW_ALL = "BP-R-MBRW-PARM";
    int WS_I = 0;
    int WS_J = 0;
    String WS_MSG_ERR = " ";
    short WS_FLD_NO = 0;
    char WS_BR_FLG = ' ';
    AIZSPI15_WS_DATA WS_DATA = new AIZSPI15_WS_DATA();
    char WS_FND_FLG = ' ';
    char WS_BR_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCSIC BPCSIC = new BPCSIC();
    AIRPAI15 AIRPAI15 = new AIRPAI15();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    AICHPI15 AICHPI15 = new AICHPI15();
    AICPQITM AICPQITM = new AICPQITM();
    AICRCMIB AICRCMIB = new AICRCMIB();
    AICPCMIB AICPCMIB = new AICPCMIB();
    AIRCMIB AIRCMIB = new AIRCMIB();
    AICHPI15 AICHI15N = new AICHPI15();
    AICHPI15 AICHI15O = new AICHPI15();
    BPRPARM BPRPARM = new BPRPARM();
    SCCGWA SCCGWA;
    AICSPI15 AICSPI15;
    public void MP(SCCGWA SCCGWA, AICSPI15 AICSPI15) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICSPI15 = AICSPI15;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZSPI15 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (AICSPI15.INFO.FUNC == 'I') {
            B020_QUERY_RECORD_PROC();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSPI15.INFO.FUNC == 'A') {
            B030_ADD_RECORD_PROC();
            if (pgmRtn) return;
            R000_HISTORY_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSPI15.INFO.FUNC == 'M') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
            R000_HISTORY_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSPI15.INFO.FUNC == 'D') {
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
            R000_HISTORY_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else {
            WS_MSG_ERR = BPCMSG_ERROR_MSG.BP_FUNC_ERROR;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICSPI15);
        CEP.TRC(SCCGWA, AICSPI15.INFO.FUNC);
        CEP.TRC(SCCGWA, AICSPI15.DATA.KEY.TYP);
        CEP.TRC(SCCGWA, AICSPI15.DATA.KEY.REDEFINES13.CODE);
        CEP.TRC(SCCGWA, AICSPI15.DATA.EFF_DATE);
        CEP.TRC(SCCGWA, AICSPI15.DATA.EXP_DATE);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM1);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM2);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM3);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM4);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM5);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM6);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM7);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM8);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM9);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM10);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM11);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM12);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM13);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM14);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM15);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM16);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM17);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM18);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM19);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM20);
        if (AICSPI15.INFO.FUNC == 'A' 
            || AICSPI15.INFO.FUNC == 'M' 
            || AICSPI15.INFO.FUNC == 'D' 
            || AICSPI15.INFO.FUNC == 'I') {
            if (AICSPI15.DATA.KEY.REDEFINES13.CODE == 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_PAI15_CD_MUST_INPUT);
            }
            if (!IBS.isNumeric(AICSPI15.DATA.KEY.REDEFINES13.CODE+"")) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_PAI15_CODE_NOT_NUM);
            }
        }
        if (AICSPI15.INFO.FUNC == 'A' 
            || AICSPI15.INFO.FUNC == 'M') {
            if (AICSPI15.DATA.DATA_TXT.ITM1.trim().length() > 0) {
                IBS.init(SCCGWA, AICPQITM);
                AICPQITM.INPUT_DATA.BOOK_FLG = "BK001";
                AICPQITM.INPUT_DATA.NO = AICSPI15.DATA.DATA_TXT.ITM1;
                S000_CALL_AIZPQITM();
                if (pgmRtn) return;
            }
            if (AICSPI15.DATA.DATA_TXT.ITM2.trim().length() > 0) {
                IBS.init(SCCGWA, AICPQITM);
                AICPQITM.INPUT_DATA.BOOK_FLG = "BK001";
                AICPQITM.INPUT_DATA.NO = AICSPI15.DATA.DATA_TXT.ITM2;
                S000_CALL_AIZPQITM();
                if (pgmRtn) return;
            }
            if (AICSPI15.DATA.DATA_TXT.ITM3.trim().length() > 0) {
                IBS.init(SCCGWA, AICPQITM);
                AICPQITM.INPUT_DATA.BOOK_FLG = "BK001";
                AICPQITM.INPUT_DATA.NO = AICSPI15.DATA.DATA_TXT.ITM3;
                S000_CALL_AIZPQITM();
                if (pgmRtn) return;
            }
            if (AICSPI15.DATA.DATA_TXT.ITM4.trim().length() > 0) {
                IBS.init(SCCGWA, AICPQITM);
                AICPQITM.INPUT_DATA.BOOK_FLG = "BK001";
                AICPQITM.INPUT_DATA.NO = AICSPI15.DATA.DATA_TXT.ITM4;
                S000_CALL_AIZPQITM();
                if (pgmRtn) return;
            }
            if (AICSPI15.DATA.DATA_TXT.ITM5.trim().length() > 0) {
                IBS.init(SCCGWA, AICPQITM);
                AICPQITM.INPUT_DATA.BOOK_FLG = "BK001";
                AICPQITM.INPUT_DATA.NO = AICSPI15.DATA.DATA_TXT.ITM5;
                S000_CALL_AIZPQITM();
                if (pgmRtn) return;
            }
            if (AICSPI15.DATA.DATA_TXT.ITM6.trim().length() > 0) {
                IBS.init(SCCGWA, AICPQITM);
                AICPQITM.INPUT_DATA.BOOK_FLG = "BK001";
                AICPQITM.INPUT_DATA.NO = AICSPI15.DATA.DATA_TXT.ITM6;
                S000_CALL_AIZPQITM();
                if (pgmRtn) return;
            }
            if (AICSPI15.DATA.DATA_TXT.ITM7.trim().length() > 0) {
                IBS.init(SCCGWA, AICPQITM);
                AICPQITM.INPUT_DATA.BOOK_FLG = "BK001";
                AICPQITM.INPUT_DATA.NO = AICSPI15.DATA.DATA_TXT.ITM7;
                S000_CALL_AIZPQITM();
                if (pgmRtn) return;
            }
            if (AICSPI15.DATA.DATA_TXT.ITM8.trim().length() > 0) {
                IBS.init(SCCGWA, AICPQITM);
                AICPQITM.INPUT_DATA.BOOK_FLG = "BK001";
                AICPQITM.INPUT_DATA.NO = AICSPI15.DATA.DATA_TXT.ITM8;
                S000_CALL_AIZPQITM();
                if (pgmRtn) return;
            }
            if (AICSPI15.DATA.DATA_TXT.ITM9.trim().length() > 0) {
                IBS.init(SCCGWA, AICPQITM);
                AICPQITM.INPUT_DATA.BOOK_FLG = "BK001";
                AICPQITM.INPUT_DATA.NO = AICSPI15.DATA.DATA_TXT.ITM9;
                S000_CALL_AIZPQITM();
                if (pgmRtn) return;
            }
            if (AICSPI15.DATA.DATA_TXT.ITM10.trim().length() > 0) {
                IBS.init(SCCGWA, AICPQITM);
                AICPQITM.INPUT_DATA.BOOK_FLG = "BK001";
                AICPQITM.INPUT_DATA.NO = AICSPI15.DATA.DATA_TXT.ITM10;
                S000_CALL_AIZPQITM();
                if (pgmRtn) return;
            }
            if (AICSPI15.DATA.DATA_TXT.ITM11.trim().length() > 0) {
                IBS.init(SCCGWA, AICPQITM);
                AICPQITM.INPUT_DATA.BOOK_FLG = "BK001";
                AICPQITM.INPUT_DATA.NO = AICSPI15.DATA.DATA_TXT.ITM11;
                S000_CALL_AIZPQITM();
                if (pgmRtn) return;
            }
            if (AICSPI15.DATA.DATA_TXT.ITM12.trim().length() > 0) {
                IBS.init(SCCGWA, AICPQITM);
                AICPQITM.INPUT_DATA.BOOK_FLG = "BK001";
                AICPQITM.INPUT_DATA.NO = AICSPI15.DATA.DATA_TXT.ITM12;
                S000_CALL_AIZPQITM();
                if (pgmRtn) return;
            }
            if (AICSPI15.DATA.DATA_TXT.ITM13.trim().length() > 0) {
                IBS.init(SCCGWA, AICPQITM);
                AICPQITM.INPUT_DATA.BOOK_FLG = "BK001";
                AICPQITM.INPUT_DATA.NO = AICSPI15.DATA.DATA_TXT.ITM13;
                S000_CALL_AIZPQITM();
                if (pgmRtn) return;
            }
            if (AICSPI15.DATA.DATA_TXT.ITM14.trim().length() > 0) {
                IBS.init(SCCGWA, AICPQITM);
                AICPQITM.INPUT_DATA.BOOK_FLG = "BK001";
                AICPQITM.INPUT_DATA.NO = AICSPI15.DATA.DATA_TXT.ITM14;
                S000_CALL_AIZPQITM();
                if (pgmRtn) return;
            }
            if (AICSPI15.DATA.DATA_TXT.ITM15.trim().length() > 0) {
                IBS.init(SCCGWA, AICPQITM);
                AICPQITM.INPUT_DATA.BOOK_FLG = "BK001";
                AICPQITM.INPUT_DATA.NO = AICSPI15.DATA.DATA_TXT.ITM15;
                S000_CALL_AIZPQITM();
                if (pgmRtn) return;
            }
            if (AICSPI15.DATA.DATA_TXT.ITM16.trim().length() > 0) {
                IBS.init(SCCGWA, AICPQITM);
                AICPQITM.INPUT_DATA.BOOK_FLG = "BK001";
                AICPQITM.INPUT_DATA.NO = AICSPI15.DATA.DATA_TXT.ITM16;
                S000_CALL_AIZPQITM();
                if (pgmRtn) return;
            }
            if (AICSPI15.DATA.DATA_TXT.ITM17.trim().length() > 0) {
                IBS.init(SCCGWA, AICPQITM);
                AICPQITM.INPUT_DATA.BOOK_FLG = "BK001";
                AICPQITM.INPUT_DATA.NO = AICSPI15.DATA.DATA_TXT.ITM17;
                S000_CALL_AIZPQITM();
                if (pgmRtn) return;
            }
            if (AICSPI15.DATA.DATA_TXT.ITM18.trim().length() > 0) {
                IBS.init(SCCGWA, AICPQITM);
                AICPQITM.INPUT_DATA.BOOK_FLG = "BK001";
                AICPQITM.INPUT_DATA.NO = AICSPI15.DATA.DATA_TXT.ITM18;
                S000_CALL_AIZPQITM();
                if (pgmRtn) return;
            }
            if (AICSPI15.DATA.DATA_TXT.ITM19.trim().length() > 0) {
                IBS.init(SCCGWA, AICPQITM);
                AICPQITM.INPUT_DATA.BOOK_FLG = "BK001";
                AICPQITM.INPUT_DATA.NO = AICSPI15.DATA.DATA_TXT.ITM19;
                S000_CALL_AIZPQITM();
                if (pgmRtn) return;
            }
            if (AICSPI15.DATA.DATA_TXT.ITM20.trim().length() > 0) {
                IBS.init(SCCGWA, AICPQITM);
                AICPQITM.INPUT_DATA.BOOK_FLG = "BK001";
                AICPQITM.INPUT_DATA.NO = AICSPI15.DATA.DATA_TXT.ITM20;
                S000_CALL_AIZPQITM();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAI15);
        AIRPAI15.KEY.TYP = "PAI15";
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        AIRPAI15.KEY.CD = "" + AICSPI15.DATA.KEY.REDEFINES13.CODE;
        JIBS_tmp_int = AIRPAI15.KEY.CD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) AIRPAI15.KEY.CD = "0" + AIRPAI15.KEY.CD;
        IBS.CPY2CLS(SCCGWA, AIRPAI15.KEY.CD, AIRPAI15.KEY.REDEFINES6);
        BPCPRMM.FUNC = '3';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B030_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAI15);
        AIRPAI15.KEY.TYP = "PAI15";
        BPCPRMM.EFF_DT = AICSPI15.DATA.EFF_DATE;
        BPCPRMM.EXP_DT = AICSPI15.DATA.EXP_DATE;
        R000_INPUT_DATA_PROCESS();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '0';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAI15);
        IBS.init(SCCGWA, AICHI15O);
        IBS.init(SCCGWA, AICHI15N);
        AIRPAI15.KEY.TYP = "PAI15";
        AIRPAI15.KEY.CD = "" + AICSPI15.DATA.KEY.REDEFINES13.CODE;
        JIBS_tmp_int = AIRPAI15.KEY.CD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) AIRPAI15.KEY.CD = "0" + AIRPAI15.KEY.CD;
        IBS.CPY2CLS(SCCGWA, AIRPAI15.KEY.CD, AIRPAI15.KEY.REDEFINES6);
        BPCPRMM.EFF_DT = AICSPI15.DATA.EFF_DATE;
        BPCPRMM.FUNC = '4';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, AICSPI15.DATA.DATA_TXT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AIRPAI15.DATA_TXT.DATA_INF);
        if (JIBS_tmp_str[1].equalsIgnoreCase(JIBS_tmp_str[0]) 
            && AICSPI15.DATA.DESC.equalsIgnoreCase(AIRPAI15.DESC) 
            && AICSPI15.DATA.CDESC.equalsIgnoreCase(AIRPAI15.CDESC) 
            && AICSPI15.DATA.EXP_DATE == BPCPRMM.EXP_DT) {
            WS_MSG_ERR = AICMSG_ERROR_MSG.AI_UPD_INFO_NOT_CHANGED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        AICHI15O.KEY.TYP = "PAI15";
        AICHI15O.KEY.CODE = AIRPAI15.KEY.REDEFINES6.CODE;
        AICHI15O.DATA.ITM1 = AIRPAI15.DATA_TXT.DATA_INF.ITM1;
        AICHI15O.DATA.ITM2 = AIRPAI15.DATA_TXT.DATA_INF.ITM2;
        AICHI15O.DATA.ITM3 = AIRPAI15.DATA_TXT.DATA_INF.ITM3;
        AICHI15O.DATA.ITM4 = AIRPAI15.DATA_TXT.DATA_INF.ITM4;
        AICHI15O.DATA.ITM5 = AIRPAI15.DATA_TXT.DATA_INF.ITM5;
        AICHI15O.DATA.ITM6 = AIRPAI15.DATA_TXT.DATA_INF.ITM6;
        AICHI15O.DATA.ITM7 = AIRPAI15.DATA_TXT.DATA_INF.ITM7;
        AICHI15O.DATA.ITM8 = AIRPAI15.DATA_TXT.DATA_INF.ITM8;
        AICHI15O.DATA.ITM9 = AIRPAI15.DATA_TXT.DATA_INF.ITM9;
        AICHI15O.DATA.ITM10 = AIRPAI15.DATA_TXT.DATA_INF.ITM10;
        AICHI15O.DATA.ITM11 = AIRPAI15.DATA_TXT.DATA_INF.ITM11;
        AICHI15O.DATA.ITM12 = AIRPAI15.DATA_TXT.DATA_INF.ITM12;
        AICHI15O.DATA.ITM13 = AIRPAI15.DATA_TXT.DATA_INF.ITM13;
        AICHI15O.DATA.ITM14 = AIRPAI15.DATA_TXT.DATA_INF.ITM14;
        AICHI15O.DATA.ITM15 = AIRPAI15.DATA_TXT.DATA_INF.ITM15;
        AICHI15O.DATA.ITM16 = AIRPAI15.DATA_TXT.DATA_INF.ITM16;
        AICHI15O.DATA.ITM17 = AIRPAI15.DATA_TXT.DATA_INF.ITM17;
        AICHI15O.DATA.ITM18 = AIRPAI15.DATA_TXT.DATA_INF.ITM18;
        AICHI15O.DATA.ITM19 = AIRPAI15.DATA_TXT.DATA_INF.ITM19;
        AICHI15O.DATA.ITM20 = AIRPAI15.DATA_TXT.DATA_INF.ITM20;
        AICHI15N.KEY.TYP = "PAI15";
        AICHI15N.KEY.CODE = AICSPI15.DATA.KEY.REDEFINES13.CODE;
        AICHI15N.DATA.ITM1 = AICSPI15.DATA.DATA_TXT.ITM1;
        AICHI15N.DATA.ITM2 = AICSPI15.DATA.DATA_TXT.ITM2;
        AICHI15N.DATA.ITM3 = AICSPI15.DATA.DATA_TXT.ITM3;
        AICHI15N.DATA.ITM4 = AICSPI15.DATA.DATA_TXT.ITM4;
        AICHI15N.DATA.ITM5 = AICSPI15.DATA.DATA_TXT.ITM5;
        AICHI15N.DATA.ITM6 = AICSPI15.DATA.DATA_TXT.ITM6;
        AICHI15N.DATA.ITM7 = AICSPI15.DATA.DATA_TXT.ITM7;
        AICHI15N.DATA.ITM8 = AICSPI15.DATA.DATA_TXT.ITM8;
        AICHI15N.DATA.ITM9 = AICSPI15.DATA.DATA_TXT.ITM9;
        AICHI15N.DATA.ITM10 = AICSPI15.DATA.DATA_TXT.ITM10;
        AICHI15N.DATA.ITM11 = AICSPI15.DATA.DATA_TXT.ITM11;
        AICHI15N.DATA.ITM12 = AICSPI15.DATA.DATA_TXT.ITM12;
        AICHI15N.DATA.ITM13 = AICSPI15.DATA.DATA_TXT.ITM13;
        AICHI15N.DATA.ITM14 = AICSPI15.DATA.DATA_TXT.ITM14;
        AICHI15N.DATA.ITM15 = AICSPI15.DATA.DATA_TXT.ITM15;
        AICHI15N.DATA.ITM16 = AICSPI15.DATA.DATA_TXT.ITM16;
        AICHI15N.DATA.ITM17 = AICSPI15.DATA.DATA_TXT.ITM17;
        AICHI15N.DATA.ITM18 = AICSPI15.DATA.DATA_TXT.ITM18;
        AICHI15N.DATA.ITM19 = AICSPI15.DATA.DATA_TXT.ITM19;
        AICHI15N.DATA.ITM20 = AICSPI15.DATA.DATA_TXT.ITM20;
        AIRPAI15.KEY.TYP = "PAI15";
        R000_INPUT_DATA_PROCESS();
        if (pgmRtn) return;
        BPCPRMM.EFF_DT = AICSPI15.DATA.EFF_DATE;
        BPCPRMM.EXP_DT = AICSPI15.DATA.EXP_DATE;
        BPCPRMM.FUNC = '2';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAI15);
        AIRPAI15.KEY.TYP = "PAI15";
        AIRPAI15.KEY.CD = "" + AICSPI15.DATA.KEY.REDEFINES13.CODE;
        JIBS_tmp_int = AIRPAI15.KEY.CD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) AIRPAI15.KEY.CD = "0" + AIRPAI15.KEY.CD;
        IBS.CPY2CLS(SCCGWA, AIRPAI15.KEY.CD, AIRPAI15.KEY.REDEFINES6);
        BPCPRMM.EFF_DT = AICSPI15.DATA.EFF_DATE;
        BPCPRMM.FUNC = '4';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        AIRPAI15.KEY.TYP = "PAI15";
        AIRPAI15.KEY.CD = "" + AICSPI15.DATA.KEY.REDEFINES13.CODE;
        JIBS_tmp_int = AIRPAI15.KEY.CD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) AIRPAI15.KEY.CD = "0" + AIRPAI15.KEY.CD;
        IBS.CPY2CLS(SCCGWA, AIRPAI15.KEY.CD, AIRPAI15.KEY.REDEFINES6);
        BPCPRMM.EFF_DT = AICSPI15.DATA.EFF_DATE;
        BPCPRMM.FUNC = '1';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void R000_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_DATA);
        WS_DATA.WS_KEY.WS_TYP = "PAI15";
        WS_DATA.WS_KEY.WS_CODE = AIRPAI15.KEY.REDEFINES6.CODE;
        WS_DATA.WS_DESC = AIRPAI15.DESC;
        WS_DATA.WS_CDESC = AIRPAI15.CDESC;
        WS_DATA.WS_EFF_DATE = AICSPI15.DATA.EFF_DATE;
        WS_DATA.WS_EXP_DATE = AICSPI15.DATA.EXP_DATE;
        WS_DATA.WS_VAL_LEN = (short) AIRPAI15.VAL_LEN;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AIRPAI15.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_DATA.WS_DATA_TXT);
        CEP.TRC(SCCGWA, AIRPAI15.DATA_TXT);
        CEP.TRC(SCCGWA, WS_DATA.WS_DATA_TXT);
        CEP.TRC(SCCGWA, AIRPAI15);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CD_1;
        SCCFMT.DATA_PTR = WS_DATA;
        SCCFMT.DATA_LEN = 311;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (AICSPI15.INFO.FUNC == 'A' 
            || AICSPI15.INFO.FUNC == 'D') {
            R000_01_HISTORY_PROCESS();
            if (pgmRtn) return;
        }
        if (AICSPI15.INFO.FUNC == 'M') {
            R000_02_HISTORY_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void R000_01_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICHI15N);
        IBS.init(SCCGWA, AICHI15O);
        if (AICSPI15.INFO.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            AICHI15N.KEY.TYP = "PAI15";
            AICHI15N.KEY.CODE = AICSPI15.DATA.KEY.REDEFINES13.CODE;
            AICHI15N.DATA.ITM1 = AICSPI15.DATA.DATA_TXT.ITM1;
            AICHI15N.DATA.ITM2 = AICSPI15.DATA.DATA_TXT.ITM2;
            AICHI15N.DATA.ITM3 = AICSPI15.DATA.DATA_TXT.ITM3;
            AICHI15N.DATA.ITM4 = AICSPI15.DATA.DATA_TXT.ITM4;
            AICHI15N.DATA.ITM5 = AICSPI15.DATA.DATA_TXT.ITM5;
            AICHI15N.DATA.ITM6 = AICSPI15.DATA.DATA_TXT.ITM6;
            AICHI15N.DATA.ITM7 = AICSPI15.DATA.DATA_TXT.ITM7;
            AICHI15N.DATA.ITM8 = AICSPI15.DATA.DATA_TXT.ITM8;
            AICHI15N.DATA.ITM9 = AICSPI15.DATA.DATA_TXT.ITM9;
            AICHI15N.DATA.ITM10 = AICSPI15.DATA.DATA_TXT.ITM10;
            AICHI15N.DATA.ITM11 = AICSPI15.DATA.DATA_TXT.ITM11;
            AICHI15N.DATA.ITM12 = AICSPI15.DATA.DATA_TXT.ITM12;
            AICHI15N.DATA.ITM13 = AICSPI15.DATA.DATA_TXT.ITM13;
            AICHI15N.DATA.ITM14 = AICSPI15.DATA.DATA_TXT.ITM14;
            AICHI15N.DATA.ITM15 = AICSPI15.DATA.DATA_TXT.ITM15;
            AICHI15N.DATA.ITM16 = AICSPI15.DATA.DATA_TXT.ITM16;
            AICHI15N.DATA.ITM17 = AICSPI15.DATA.DATA_TXT.ITM17;
            AICHI15N.DATA.ITM18 = AICSPI15.DATA.DATA_TXT.ITM18;
            AICHI15N.DATA.ITM19 = AICSPI15.DATA.DATA_TXT.ITM19;
            AICHI15N.DATA.ITM20 = AICSPI15.DATA.DATA_TXT.ITM20;
        }
        if (AICSPI15.INFO.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            AICHI15O.KEY.TYP = "PAI15";
            AICHI15O.KEY.CODE = AICSPI15.DATA.KEY.REDEFINES13.CODE;
            AICHI15O.DATA.ITM1 = AICSPI15.DATA.DATA_TXT.ITM1;
            AICHI15O.DATA.ITM2 = AICSPI15.DATA.DATA_TXT.ITM2;
            AICHI15O.DATA.ITM3 = AICSPI15.DATA.DATA_TXT.ITM3;
            AICHI15O.DATA.ITM4 = AICSPI15.DATA.DATA_TXT.ITM4;
            AICHI15O.DATA.ITM5 = AICSPI15.DATA.DATA_TXT.ITM5;
            AICHI15O.DATA.ITM6 = AICSPI15.DATA.DATA_TXT.ITM6;
            AICHI15O.DATA.ITM7 = AICSPI15.DATA.DATA_TXT.ITM7;
            AICHI15O.DATA.ITM8 = AICSPI15.DATA.DATA_TXT.ITM8;
            AICHI15O.DATA.ITM9 = AICSPI15.DATA.DATA_TXT.ITM9;
            AICHI15O.DATA.ITM10 = AICSPI15.DATA.DATA_TXT.ITM10;
            AICHI15O.DATA.ITM11 = AICSPI15.DATA.DATA_TXT.ITM11;
            AICHI15O.DATA.ITM12 = AICSPI15.DATA.DATA_TXT.ITM12;
            AICHI15O.DATA.ITM13 = AICSPI15.DATA.DATA_TXT.ITM13;
            AICHI15O.DATA.ITM14 = AICSPI15.DATA.DATA_TXT.ITM14;
            AICHI15O.DATA.ITM15 = AICSPI15.DATA.DATA_TXT.ITM15;
            AICHI15O.DATA.ITM16 = AICSPI15.DATA.DATA_TXT.ITM16;
            AICHI15O.DATA.ITM17 = AICSPI15.DATA.DATA_TXT.ITM17;
            AICHI15O.DATA.ITM18 = AICSPI15.DATA.DATA_TXT.ITM18;
            AICHI15O.DATA.ITM19 = AICSPI15.DATA.DATA_TXT.ITM19;
            AICHI15O.DATA.ITM20 = AICSPI15.DATA.DATA_TXT.ITM20;
        }
        BPCPNHIS.INFO.FMT_ID = "AICHPI15";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.JRNNO);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID_LEN = 209;
        BPCPNHIS.INFO.OLD_DAT_PT = AICHI15O;
        BPCPNHIS.INFO.NEW_DAT_PT = AICHI15N;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_02_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.FMT_ID = "AICHPI15";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID_LEN = 209;
        BPCPNHIS.INFO.OLD_DAT_PT = AICHI15O;
        BPCPNHIS.INFO.NEW_DAT_PT = AICHI15N;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICPQITM);
        CEP.TRC(SCCGWA, AICPQITM.RC.RTNCODE);
        if (AICPQITM.RC.RTNCODE != 0) {
            WS_MSG_ERR = IBS.CLS2CPY(SCCGWA, AICPQITM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, AICPQITM.OUTPUT_DATA.STS);
        if (AICPQITM.OUTPUT_DATA.STS != 'A') {
            WS_MSG_ERR = AICMSG_ERROR_MSG.AI_COA_NOT_ACTIVE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_MSG_ERR = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        AIRPAI15.VAL_LEN = 222;
        BPCPRMM.DAT_PTR = AIRPAI15;
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_MSG_ERR = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_ALEADY_EXIST) 
                && BPCPRMM.FUNC == '0') {
                WS_MSG_ERR = AICMSG_ERROR_MSG.AI_PAI15_ALREADY_EXIST;
            }
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                WS_MSG_ERR = AICMSG_ERROR_MSG.AI_PAI15_NO_EXIST;
            }
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_INPUT_DATA_PROCESS() throws IOException,SQLException,Exception {
        AIRPAI15.KEY.TYP = "PAI15";
        AIRPAI15.KEY.REDEFINES6.CODE = AICSPI15.DATA.KEY.REDEFINES13.CODE;
        AIRPAI15.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI15.KEY.REDEFINES6);
        AIRPAI15.DESC = AICSPI15.DATA.DESC;
        AIRPAI15.CDESC = AICSPI15.DATA.CDESC;
        AIRPAI15.DATA_TXT.DATA_INF.ITM1 = AICSPI15.DATA.DATA_TXT.ITM1;
        AIRPAI15.DATA_TXT.DATA_INF.ITM2 = AICSPI15.DATA.DATA_TXT.ITM2;
        AIRPAI15.DATA_TXT.DATA_INF.ITM3 = AICSPI15.DATA.DATA_TXT.ITM3;
        AIRPAI15.DATA_TXT.DATA_INF.ITM4 = AICSPI15.DATA.DATA_TXT.ITM4;
        AIRPAI15.DATA_TXT.DATA_INF.ITM5 = AICSPI15.DATA.DATA_TXT.ITM5;
        AIRPAI15.DATA_TXT.DATA_INF.ITM6 = AICSPI15.DATA.DATA_TXT.ITM6;
        AIRPAI15.DATA_TXT.DATA_INF.ITM7 = AICSPI15.DATA.DATA_TXT.ITM7;
        AIRPAI15.DATA_TXT.DATA_INF.ITM8 = AICSPI15.DATA.DATA_TXT.ITM8;
        AIRPAI15.DATA_TXT.DATA_INF.ITM9 = AICSPI15.DATA.DATA_TXT.ITM9;
        AIRPAI15.DATA_TXT.DATA_INF.ITM10 = AICSPI15.DATA.DATA_TXT.ITM10;
        AIRPAI15.DATA_TXT.DATA_INF.ITM11 = AICSPI15.DATA.DATA_TXT.ITM11;
        AIRPAI15.DATA_TXT.DATA_INF.ITM12 = AICSPI15.DATA.DATA_TXT.ITM12;
        AIRPAI15.DATA_TXT.DATA_INF.ITM13 = AICSPI15.DATA.DATA_TXT.ITM13;
        AIRPAI15.DATA_TXT.DATA_INF.ITM14 = AICSPI15.DATA.DATA_TXT.ITM14;
        AIRPAI15.DATA_TXT.DATA_INF.ITM15 = AICSPI15.DATA.DATA_TXT.ITM15;
        AIRPAI15.DATA_TXT.DATA_INF.ITM16 = AICSPI15.DATA.DATA_TXT.ITM16;
        AIRPAI15.DATA_TXT.DATA_INF.ITM17 = AICSPI15.DATA.DATA_TXT.ITM17;
        AIRPAI15.DATA_TXT.DATA_INF.ITM18 = AICSPI15.DATA.DATA_TXT.ITM18;
        AIRPAI15.DATA_TXT.DATA_INF.ITM19 = AICSPI15.DATA.DATA_TXT.ITM19;
        AIRPAI15.DATA_TXT.DATA_INF.ITM20 = AICSPI15.DATA.DATA_TXT.ITM20;
        AIRPAI15.DATA_TXT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        AIRPAI15.DATA_TXT.UPD_DATE = SCCGWA.COMM_AREA.TR_DATE;
        AIRPAI15.DATA_TXT.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        CEP.TRC(SCCGWA, AIRPAI15.KEY.REDEFINES6.CODE);
        CEP.TRC(SCCGWA, AIRPAI15.DESC);
        CEP.TRC(SCCGWA, AIRPAI15.CDESC);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM1);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM2);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM3);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM4);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM5);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM6);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM7);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM8);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM9);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM10);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM11);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM12);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM13);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM14);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM15);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM16);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM17);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM18);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM19);
        CEP.TRC(SCCGWA, AICSPI15.DATA.DATA_TXT.ITM20);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ERR, WS_FLD_NO);
        Z_RET();
        if (pgmRtn) return;
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
