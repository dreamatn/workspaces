package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class AIZSPAI9 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_PARM_TYPE = "PAI09";
    String K_FMT_CD_1 = "AIP09";
    String K_HIS_REMARKS = "AI P TABLE 1 MAINTENANCE";
    String CPN_PARM_BRW_ALL = "BP-R-MBRW-PARM";
    String CPN_CCY_QUERY = "BP-INQUIRE-CCY      ";
    int WS_I = 0;
    int WS_J = 0;
    String WS_MSG_ERR = " ";
    short WS_FLD_NO = 0;
    char WS_BR_FLG = ' ';
    AIZSPAI9_WS_DATA WS_DATA = new AIZSPAI9_WS_DATA();
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
    AIRPAI9 AIRPAI9 = new AIRPAI9();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    AICHPAI9 AICHPAI9 = new AICHPAI9();
    AICPQITM AICPQITM = new AICPQITM();
    AICRCMIB AICRCMIB = new AICRCMIB();
    AICPCMIB AICPCMIB = new AICPCMIB();
    AIRCMIB AIRCMIB = new AIRCMIB();
    AICHPAI9 AICHAI9N = new AICHPAI9();
    AICHPAI9 AICHAI9O = new AICHPAI9();
    BPRPARM BPRPARM = new BPRPARM();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCGWA SCCGWA;
    AICSPAI9 AICSPAI9;
    public void MP(SCCGWA SCCGWA, AICSPAI9 AICSPAI9) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICSPAI9 = AICSPAI9;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZSPAI9 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (AICSPAI9.INFO.FUNC == 'I') {
            B020_QUERY_RECORD_PROC();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSPAI9.INFO.FUNC == 'A') {
            B030_ADD_RECORD_PROC();
            if (pgmRtn) return;
            R000_HISTORY_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSPAI9.INFO.FUNC == 'M') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
            R000_HISTORY_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSPAI9.INFO.FUNC == 'D') {
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
        CEP.TRC(SCCGWA, AICSPAI9);
        CEP.TRC(SCCGWA, AICSPAI9.INFO.FUNC);
        CEP.TRC(SCCGWA, AICSPAI9.DATA.KEY.TYP);
        CEP.TRC(SCCGWA, AICSPAI9.DATA.KEY.REDEFINES13.CODE);
        CEP.TRC(SCCGWA, AICSPAI9.DATA.EFF_DATE);
        CEP.TRC(SCCGWA, AICSPAI9.DATA.EXP_DATE);
        CEP.TRC(SCCGWA, AICSPAI9.DATA.DATA_TXT.BR);
        CEP.TRC(SCCGWA, AICSPAI9.DATA.DATA_TXT.CCY);
        CEP.TRC(SCCGWA, AICSPAI9.DATA.DATA_TXT.ITM1);
        CEP.TRC(SCCGWA, AICSPAI9.DATA.DATA_TXT.SEQ1);
        CEP.TRC(SCCGWA, AICSPAI9.DATA.DATA_TXT.ITM2);
        CEP.TRC(SCCGWA, AICSPAI9.DATA.DATA_TXT.SEQ2);
        if (AICSPAI9.INFO.FUNC == 'A' 
            || AICSPAI9.INFO.FUNC == 'M' 
            || AICSPAI9.INFO.FUNC == 'D' 
            || AICSPAI9.INFO.FUNC == 'I') {
            if (AICSPAI9.DATA.KEY.REDEFINES13.CODE == 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_PAI9_CODE_MUST_INPUT);
            }
            if (!IBS.isNumeric(AICSPAI9.DATA.KEY.REDEFINES13.CODE+"")) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_PAI9_CODE_NOT_NUM);
            }
        }
        if (AICSPAI9.INFO.FUNC == 'A' 
            || AICSPAI9.INFO.FUNC == 'M') {
            if (AICSPAI9.DATA.DATA_TXT.BR == 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_BR_MUST_INPUT);
            } else {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = AICSPAI9.DATA.DATA_TXT.BR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
            }
            if (AICSPAI9.DATA.DATA_TXT.CCY.trim().length() > 0) {
                IBS.init(SCCGWA, BPCQCCY);
                BPCQCCY.DATA.CCY = AICSPAI9.DATA.DATA_TXT.CCY;
                S000_CALL_BPZQCCY();
                if (pgmRtn) return;
            }
            if (AICSPAI9.DATA.DATA_TXT.ITM1.trim().length() == 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_ITM_MUST_INPUT);
            } else {
                IBS.init(SCCGWA, AICPQITM);
                AICPQITM.INPUT_DATA.BOOK_FLG = "BK001";
                AICPQITM.INPUT_DATA.NO = AICSPAI9.DATA.DATA_TXT.ITM1;
                S000_CALL_AIZPQITM();
                if (pgmRtn) return;
            }
            if (AICSPAI9.DATA.DATA_TXT.SEQ1 != 0) {
                IBS.init(SCCGWA, AIRCMIB);
                IBS.init(SCCGWA, AICRCMIB);
                IBS.init(SCCGWA, AICPCMIB);
                AICRCMIB.FUNC = 'F';
                AIRCMIB.KEY.GL_BOOK = "BK001";
                AIRCMIB.KEY.ITM = AICSPAI9.DATA.DATA_TXT.ITM1;
                AIRCMIB.KEY.SEQ = AICSPAI9.DATA.DATA_TXT.SEQ1;
                AIRCMIB.KEY.BR = AICSPAI9.DATA.DATA_TXT.BR;
                AICRCMIB.POINTER = AIRCMIB;
                AICRCMIB.REC_LEN = 407;
                S000_CALL_AIZPCMIB();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, AIRCMIB.STS);
                if (AIRCMIB.STS == ' ') {
                    CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND);
                }
                if (AIRCMIB.STS != 'N') {
                    CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIB_STS_ERRO);
                }
            }
            if (AICSPAI9.DATA.DATA_TXT.ITM2.trim().length() == 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_ITM_MUST_INPUT);
            } else {
                IBS.init(SCCGWA, AICPQITM);
                AICPQITM.INPUT_DATA.BOOK_FLG = "BK001";
                AICPQITM.INPUT_DATA.NO = AICSPAI9.DATA.DATA_TXT.ITM2;
                S000_CALL_AIZPQITM();
                if (pgmRtn) return;
            }
            if (AICSPAI9.DATA.DATA_TXT.SEQ2 != 0) {
                IBS.init(SCCGWA, AIRCMIB);
                IBS.init(SCCGWA, AICRCMIB);
                IBS.init(SCCGWA, AICPCMIB);
                AICRCMIB.FUNC = 'F';
                AIRCMIB.KEY.GL_BOOK = "BK001";
                AIRCMIB.KEY.ITM = AICSPAI9.DATA.DATA_TXT.ITM2;
                AIRCMIB.KEY.SEQ = AICSPAI9.DATA.DATA_TXT.SEQ2;
                AIRCMIB.KEY.BR = AICSPAI9.DATA.DATA_TXT.BR;
                AICRCMIB.POINTER = AIRCMIB;
                AICRCMIB.REC_LEN = 407;
                S000_CALL_AIZPCMIB();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, AIRCMIB.STS);
                if (AIRCMIB.STS == ' ') {
                    CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND);
                }
                if (AIRCMIB.STS != 'N') {
                    CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIB_STS_ERRO);
                }
            }
        }
    }
    public void B020_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAI9);
        AIRPAI9.KEY.TYP = "PAI09";
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        AIRPAI9.KEY.CD = "" + AICSPAI9.DATA.KEY.REDEFINES13.CODE;
        JIBS_tmp_int = AIRPAI9.KEY.CD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) AIRPAI9.KEY.CD = "0" + AIRPAI9.KEY.CD;
        IBS.CPY2CLS(SCCGWA, AIRPAI9.KEY.CD, AIRPAI9.KEY.REDEFINES5);
        BPCPRMM.FUNC = '3';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B030_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAI9);
        AIRPAI9.KEY.TYP = "PAI09";
        BPCPRMM.EFF_DT = AICSPAI9.DATA.EFF_DATE;
        BPCPRMM.EXP_DT = AICSPAI9.DATA.EXP_DATE;
        R000_INPUT_DATA_PROCESS();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '0';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAI9);
        IBS.init(SCCGWA, AICHAI9O);
        IBS.init(SCCGWA, AICHAI9N);
        AIRPAI9.KEY.TYP = "PAI09";
        AIRPAI9.KEY.CD = "" + AICSPAI9.DATA.KEY.REDEFINES13.CODE;
        JIBS_tmp_int = AIRPAI9.KEY.CD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) AIRPAI9.KEY.CD = "0" + AIRPAI9.KEY.CD;
        IBS.CPY2CLS(SCCGWA, AIRPAI9.KEY.CD, AIRPAI9.KEY.REDEFINES5);
        BPCPRMM.EFF_DT = AICSPAI9.DATA.EFF_DATE;
        BPCPRMM.FUNC = '4';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, AICSPAI9.DATA.DATA_TXT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AIRPAI9.DATA_TXT.DATA_INF);
        if (JIBS_tmp_str[1].equalsIgnoreCase(JIBS_tmp_str[0]) 
            && AICSPAI9.DATA.DESC.equalsIgnoreCase(AIRPAI9.DESC) 
            && AICSPAI9.DATA.CDESC.equalsIgnoreCase(AIRPAI9.CDESC) 
            && AICSPAI9.DATA.EXP_DATE == BPCPRMM.EXP_DT) {
            WS_MSG_ERR = AICMSG_ERROR_MSG.AI_UPD_INFO_NOT_CHANGED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        AICHAI9O.KEY.TYP = "PAI09";
        AICHAI9O.KEY.CODE = AIRPAI9.KEY.REDEFINES5.CODE;
        AICHAI9O.DATA.BR = AIRPAI9.DATA_TXT.DATA_INF.BR;
        AICHAI9O.DATA.CCY = AIRPAI9.DATA_TXT.DATA_INF.CCY;
        AICHAI9O.DATA.ITM1 = AIRPAI9.DATA_TXT.DATA_INF.ITM1;
        AICHAI9O.DATA.SEQ1 = AIRPAI9.DATA_TXT.DATA_INF.SEQ1;
        AICHAI9O.DATA.ITM2 = AIRPAI9.DATA_TXT.DATA_INF.ITM2;
        AICHAI9O.DATA.SEQ2 = AIRPAI9.DATA_TXT.DATA_INF.SEQ2;
        AICHAI9N.KEY.TYP = "PAI09";
        AICHAI9N.KEY.CODE = AICSPAI9.DATA.KEY.REDEFINES13.CODE;
        AICHAI9N.DATA.BR = AICSPAI9.DATA.DATA_TXT.BR;
        AICHAI9N.DATA.CCY = AICSPAI9.DATA.DATA_TXT.CCY;
        AICHAI9N.DATA.ITM1 = AICSPAI9.DATA.DATA_TXT.ITM1;
        AICHAI9N.DATA.SEQ1 = AICSPAI9.DATA.DATA_TXT.SEQ1;
        AICHAI9N.DATA.ITM2 = AICSPAI9.DATA.DATA_TXT.ITM2;
        AICHAI9N.DATA.SEQ2 = AICSPAI9.DATA.DATA_TXT.SEQ2;
        AIRPAI9.KEY.TYP = "PAI09";
        R000_INPUT_DATA_PROCESS();
        if (pgmRtn) return;
        BPCPRMM.EFF_DT = AICSPAI9.DATA.EFF_DATE;
        BPCPRMM.EXP_DT = AICSPAI9.DATA.EXP_DATE;
        BPCPRMM.FUNC = '2';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAI9);
        AIRPAI9.KEY.TYP = "PAI09";
        AIRPAI9.KEY.CD = "" + AICSPAI9.DATA.KEY.REDEFINES13.CODE;
        JIBS_tmp_int = AIRPAI9.KEY.CD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) AIRPAI9.KEY.CD = "0" + AIRPAI9.KEY.CD;
        IBS.CPY2CLS(SCCGWA, AIRPAI9.KEY.CD, AIRPAI9.KEY.REDEFINES5);
        BPCPRMM.EFF_DT = AICSPAI9.DATA.EFF_DATE;
        BPCPRMM.FUNC = '4';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        AIRPAI9.KEY.TYP = "PAI09";
        AIRPAI9.KEY.CD = "" + AICSPAI9.DATA.KEY.REDEFINES13.CODE;
        JIBS_tmp_int = AIRPAI9.KEY.CD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) AIRPAI9.KEY.CD = "0" + AIRPAI9.KEY.CD;
        IBS.CPY2CLS(SCCGWA, AIRPAI9.KEY.CD, AIRPAI9.KEY.REDEFINES5);
        BPCPRMM.EFF_DT = AICSPAI9.DATA.EFF_DATE;
        BPCPRMM.FUNC = '1';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void R000_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_DATA);
        WS_DATA.WS_KEY.WS_TYP = "PAI09";
        WS_DATA.WS_KEY.WS_CODE = AIRPAI9.KEY.REDEFINES5.CODE;
        WS_DATA.WS_DESC = AIRPAI9.DESC;
        WS_DATA.WS_CDESC = AIRPAI9.CDESC;
        WS_DATA.WS_EFF_DATE = AICSPAI9.DATA.EFF_DATE;
        WS_DATA.WS_EXP_DATE = AICSPAI9.DATA.EXP_DATE;
        WS_DATA.WS_VAL_LEN = (short) AIRPAI9.VAL_LEN;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AIRPAI9.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_DATA.WS_DATA_TXT);
        CEP.TRC(SCCGWA, AIRPAI9.DATA_TXT);
        CEP.TRC(SCCGWA, WS_DATA.WS_DATA_TXT);
        CEP.TRC(SCCGWA, AIRPAI9);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CD_1;
        SCCFMT.DATA_PTR = WS_DATA;
        SCCFMT.DATA_LEN = 152;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (AICSPAI9.INFO.FUNC == 'A' 
            || AICSPAI9.INFO.FUNC == 'D') {
            R000_01_HISTORY_PROCESS();
            if (pgmRtn) return;
        }
        if (AICSPAI9.INFO.FUNC == 'M') {
            R000_02_HISTORY_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void R000_01_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICHAI9N);
        IBS.init(SCCGWA, AICHAI9O);
        if (AICSPAI9.INFO.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            AICHAI9N.KEY.TYP = "PAI09";
            AICHAI9N.KEY.CODE = AICSPAI9.DATA.KEY.REDEFINES13.CODE;
            AICHAI9N.DATA.BR = AICSPAI9.DATA.DATA_TXT.BR;
            AICHAI9N.DATA.CCY = AICSPAI9.DATA.DATA_TXT.CCY;
            AICHAI9N.DATA.ITM1 = AICSPAI9.DATA.DATA_TXT.ITM1;
            AICHAI9N.DATA.SEQ1 = AICSPAI9.DATA.DATA_TXT.SEQ1;
            AICHAI9N.DATA.ITM2 = AICSPAI9.DATA.DATA_TXT.ITM2;
            AICHAI9N.DATA.SEQ2 = AICSPAI9.DATA.DATA_TXT.SEQ2;
        }
        if (AICSPAI9.INFO.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            AICHAI9O.KEY.TYP = "PAI09";
            AICHAI9O.KEY.CODE = AICSPAI9.DATA.KEY.REDEFINES13.CODE;
            AICHAI9O.DATA.BR = AICSPAI9.DATA.DATA_TXT.BR;
            AICHAI9O.DATA.CCY = AICSPAI9.DATA.DATA_TXT.CCY;
            AICHAI9O.DATA.ITM1 = AICSPAI9.DATA.DATA_TXT.ITM1;
            AICHAI9O.DATA.SEQ1 = AICSPAI9.DATA.DATA_TXT.SEQ1;
            AICHAI9O.DATA.ITM2 = AICSPAI9.DATA.DATA_TXT.ITM2;
            AICHAI9O.DATA.SEQ2 = AICSPAI9.DATA.DATA_TXT.SEQ2;
        }
        BPCPNHIS.INFO.FMT_ID = "AICHPAI9";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.JRNNO);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID_LEN = 50;
        BPCPNHIS.INFO.OLD_DAT_PT = AICHAI9O;
        BPCPNHIS.INFO.NEW_DAT_PT = AICHAI9N;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_02_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.FMT_ID = "AICHPAI9";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID_LEN = 50;
        BPCPNHIS.INFO.OLD_DAT_PT = AICHAI9O;
        BPCPNHIS.INFO.NEW_DAT_PT = AICHAI9N;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_MSG_ERR = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CCY_QUERY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICPQITM);
        CEP.TRC(SCCGWA, AICPQITM.RC.RTNCODE);
        if (AICPQITM.RC.RTNCODE != 0) {
            WS_MSG_ERR = IBS.CLS2CPY(SCCGWA, AICPQITM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, AICPQITM.OUTPUT_DATA.TYPE);
        if (!AICPQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("5")) {
            WS_MSG_ERR = AICMSG_ERROR_MSG.AI_ITM_TYPE_MUST_5;
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
    public void S000_CALL_AIZPCMIB() throws IOException,SQLException,Exception {
        AICPCMIB.POINTER = AIRCMIB;
        AICPCMIB.REC_LEN = 407;
        IBS.CALLCPN(SCCGWA, "AI-P-GET-CMIB", AICPCMIB);
        CEP.TRC(SCCGWA, AICPCMIB.RC.RC_CODE);
        CEP.TRC(SCCGWA, AICPCMIB.RETURN_INFO);
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
        AIRPAI9.VAL_LEN = 63;
        BPCPRMM.DAT_PTR = AIRPAI9;
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_MSG_ERR = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_ALEADY_EXIST) 
                && BPCPRMM.FUNC == '0') {
                WS_MSG_ERR = AICMSG_ERROR_MSG.AI_PAI9_ALREADY_EXIST;
            }
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                WS_MSG_ERR = AICMSG_ERROR_MSG.AI_PAI9_NO_EXIST;
            }
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_INPUT_DATA_PROCESS() throws IOException,SQLException,Exception {
        AIRPAI9.KEY.TYP = "PAI09";
        AIRPAI9.KEY.REDEFINES5.CODE = AICSPAI9.DATA.KEY.REDEFINES13.CODE;
        AIRPAI9.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI9.KEY.REDEFINES5);
        AIRPAI9.DESC = AICSPAI9.DATA.DESC;
        AIRPAI9.CDESC = AICSPAI9.DATA.CDESC;
        AIRPAI9.DATA_TXT.DATA_INF.BR = AICSPAI9.DATA.DATA_TXT.BR;
        AIRPAI9.DATA_TXT.DATA_INF.CCY = AICSPAI9.DATA.DATA_TXT.CCY;
        AIRPAI9.DATA_TXT.DATA_INF.ITM1 = AICSPAI9.DATA.DATA_TXT.ITM1;
        AIRPAI9.DATA_TXT.DATA_INF.SEQ1 = AICSPAI9.DATA.DATA_TXT.SEQ1;
        AIRPAI9.DATA_TXT.DATA_INF.ITM2 = AICSPAI9.DATA.DATA_TXT.ITM2;
        AIRPAI9.DATA_TXT.DATA_INF.SEQ2 = AICSPAI9.DATA.DATA_TXT.SEQ2;
        AIRPAI9.DATA_TXT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        AIRPAI9.DATA_TXT.UPD_DATE = SCCGWA.COMM_AREA.TR_DATE;
        AIRPAI9.DATA_TXT.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        CEP.TRC(SCCGWA, AIRPAI9.KEY.REDEFINES5.CODE);
        CEP.TRC(SCCGWA, AIRPAI9.DESC);
        CEP.TRC(SCCGWA, AIRPAI9.CDESC);
        CEP.TRC(SCCGWA, AICSPAI9.DATA.DATA_TXT.BR);
        CEP.TRC(SCCGWA, AICSPAI9.DATA.DATA_TXT.CCY);
        CEP.TRC(SCCGWA, AICSPAI9.DATA.DATA_TXT.ITM1);
        CEP.TRC(SCCGWA, AICSPAI9.DATA.DATA_TXT.SEQ1);
        CEP.TRC(SCCGWA, AICSPAI9.DATA.DATA_TXT.ITM2);
        CEP.TRC(SCCGWA, AICSPAI9.DATA.DATA_TXT.SEQ2);
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
