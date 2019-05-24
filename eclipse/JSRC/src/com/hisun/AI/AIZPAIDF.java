package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class AIZPAIDF {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String K_PARM_TYPE = "PAIDF";
    String K_FMT_CD_1 = "AIP10";
    String K_HIS_REMARKS = "AI P TABLE 1 MAINTENANCE";
    String CPN_PARM_BRW_ALL = "BP-R-MBRW-PARM";
    String CPN_CCY_QUERY = "BP-INQUIRE-CCY      ";
    int WS_I = 0;
    int WS_J = 0;
    String WS_MSG_ERR = " ";
    short WS_FLD_NO = 0;
    char WS_BR_FLG = ' ';
    AIZPAIDF_WS_DATA WS_DATA = new AIZPAIDF_WS_DATA();
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
    AIRPAID AIRPAID = new AIRPAID();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    AICHPAID AICHPAID = new AICHPAID();
    AICPQITM AICPQITM = new AICPQITM();
    AICRCMIB AICRCMIB = new AICRCMIB();
    AICPCMIB AICPCMIB = new AICPCMIB();
    AIRCMIB AIRCMIB = new AIRCMIB();
    AICHPAID AICHAIDN = new AICHPAID();
    AICHPAID AICHAIDO = new AICHPAID();
    BPRPARM BPRPARM = new BPRPARM();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCGWA SCCGWA;
    AICPAIDF AICPAIDF;
    public void MP(SCCGWA SCCGWA, AICPAIDF AICPAIDF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICPAIDF = AICPAIDF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZPAIDF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (AICPAIDF.INFO.FUNC == 'A') {
            B030_ADD_RECORD_PROC();
            if (pgmRtn) return;
            R000_HISTORY_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICPAIDF.INFO.FUNC == 'D') {
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
        CEP.TRC(SCCGWA, AICPAIDF);
        CEP.TRC(SCCGWA, AICPAIDF.INFO.FUNC);
        CEP.TRC(SCCGWA, AICPAIDF.INFO.DATA_TXT.BR);
        CEP.TRC(SCCGWA, AICPAIDF.INFO.DATA_TXT.CCY);
        CEP.TRC(SCCGWA, AICPAIDF.INFO.DATA_TXT.ITM);
        if (AICPAIDF.INFO.FUNC == 'A') {
            if (AICPAIDF.INFO.DATA_TXT.BR == 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_BR_MUST_INPUT);
            } else {
                if (AICPAIDF.INFO.DATA_TXT.BR == 999999999) {
                } else {
                    IBS.init(SCCGWA, BPCPQORG);
                    BPCPQORG.BR = AICPAIDF.INFO.DATA_TXT.BR;
                    S000_CALL_BPZPQORG();
                    if (pgmRtn) return;
                }
            }
            if (AICPAIDF.INFO.DATA_TXT.CCY.trim().length() > 0) {
                IBS.init(SCCGWA, BPCQCCY);
                BPCQCCY.DATA.CCY = AICPAIDF.INFO.DATA_TXT.CCY;
                S000_CALL_BPZQCCY();
                if (pgmRtn) return;
            }
            if (AICPAIDF.INFO.DATA_TXT.ITM.trim().length() == 0) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_ITM_MUST_INPUT);
            } else {
                IBS.init(SCCGWA, AICPQITM);
                AICPQITM.INPUT_DATA.BOOK_FLG = "BK001";
                AICPQITM.INPUT_DATA.NO = AICPAIDF.INFO.DATA_TXT.ITM;
                S000_CALL_AIZPQITM();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAID);
        AIRPAID.KEY.TYP = "PAIDF";
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        if ("99991231".trim().length() == 0) BPCPRMM.EXP_DT = 0;
        else BPCPRMM.EXP_DT = Integer.parseInt("99991231");
        R000_DATA_PROCESS();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '0';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAID);
        AIRPAID.KEY.TYP = "PAIDF";
        R000_DATA_PROCESS();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '4';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        AIRPAID.KEY.TYP = "PAIDF";
        R000_DATA_PROCESS();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '1';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void R000_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_DATA);
        WS_DATA.WS_KEY.WS_TYP = "PAIDF";
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AIRPAID.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_DATA.WS_DATA_TXT);
        CEP.TRC(SCCGWA, AIRPAID.DATA_TXT);
        CEP.TRC(SCCGWA, WS_DATA.WS_DATA_TXT);
        CEP.TRC(SCCGWA, AIRPAID);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CD_1;
        SCCFMT.DATA_PTR = WS_DATA;
        SCCFMT.DATA_LEN = 24;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (AICPAIDF.INFO.FUNC == 'A' 
            || AICPAIDF.INFO.FUNC == 'D') {
            R000_01_HISTORY_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void R000_01_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICHAIDN);
        IBS.init(SCCGWA, AICHAIDO);
        if (AICPAIDF.INFO.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            AICHAIDN.KEY.TYP = "PAIDF";
            AICHAIDN.DATA.BR = AICPAIDF.INFO.DATA_TXT.BR;
            AICHAIDN.DATA.CCY = AICPAIDF.INFO.DATA_TXT.CCY;
            AICHAIDN.DATA.ITM = AICPAIDF.INFO.DATA_TXT.ITM;
        }
        if (AICPAIDF.INFO.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            AICHAIDO.KEY.TYP = "PAIDF";
            AICHAIDO.DATA.BR = AICPAIDF.INFO.DATA_TXT.BR;
            AICHAIDO.DATA.CCY = AICPAIDF.INFO.DATA_TXT.CCY;
            AICHAIDO.DATA.ITM = AICPAIDF.INFO.DATA_TXT.ITM;
        }
        BPCPNHIS.INFO.FMT_ID = "AICHPAID";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.JRNNO);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID_LEN = 64;
        BPCPNHIS.INFO.OLD_DAT_PT = AICHAIDO;
        BPCPNHIS.INFO.NEW_DAT_PT = AICHAIDN;
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
        AIRPAID.VAL_LEN = 41;
        BPCPRMM.DAT_PTR = AIRPAID;
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
    public void R000_DATA_PROCESS() throws IOException,SQLException,Exception {
        AIRPAID.KEY.TYP = "PAIDF";
        AIRPAID.DATA_TXT.DATA_INF.BR = AICPAIDF.INFO.DATA_TXT.BR;
        AIRPAID.DATA_TXT.DATA_INF.CCY = AICPAIDF.INFO.DATA_TXT.CCY;
        AIRPAID.DATA_TXT.DATA_INF.ITM = AICPAIDF.INFO.DATA_TXT.ITM;
        AIRPAID.DATA_TXT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        AIRPAID.DATA_TXT.UPD_DATE = SCCGWA.COMM_AREA.TR_DATE;
        AIRPAID.DATA_TXT.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        AIRPAID.KEY.CD = IBS.CLS2CPY(SCCGWA, AICPAIDF.INFO.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, AIRPAID.KEY.CD, AIRPAID.KEY.REDEFINES6);
        CEP.TRC(SCCGWA, AICPAIDF.INFO.DATA_TXT.BR);
        CEP.TRC(SCCGWA, AICPAIDF.INFO.DATA_TXT.CCY);
        CEP.TRC(SCCGWA, AICPAIDF.INFO.DATA_TXT.ITM);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ERR, WS_FLD_NO);
        Z_RET();
        if (pgmRtn) return;
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        AIRPAID.KEY.CD = "" + WS_FLD_NO;
        JIBS_tmp_int = AIRPAID.KEY.CD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) AIRPAID.KEY.CD = "0" + AIRPAID.KEY.CD;
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
