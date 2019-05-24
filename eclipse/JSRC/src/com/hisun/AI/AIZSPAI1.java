package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class AIZSPAI1 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_PARM_TYPE = "PAI01";
    String K_FMT_CD_1 = "AIX01";
    String K_HIS_REMARKS = "AI P TABLE 1 MAINTENANCE";
    String CPN_PARM_BRW_ALL = "BP-R-MBRW-PARM";
    int WS_I = 0;
    int WS_J = 0;
    String WS_MSG_ERR = " ";
    short WS_FLD_NO = 0;
    char WS_BR_FLG = ' ';
    AIZSPAI1_WS_DATA WS_DATA = new AIZSPAI1_WS_DATA();
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
    AIRPAI1 AIRPAI1 = new AIRPAI1();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    AICHPAI1 AICHPAI1 = new AICHPAI1();
    AICHPAI1 AICHAI1N = new AICHPAI1();
    AICHPAI1 AICHAI1O = new AICHPAI1();
    BPRPARM BPRPARM = new BPRPARM();
    SCCGWA SCCGWA;
    AICSPAI1 AICSPAI1;
    public void MP(SCCGWA SCCGWA, AICSPAI1 AICSPAI1) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICSPAI1 = AICSPAI1;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZSPAI1 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (AICSPAI1.INFO.FUNC == 'I') {
            B020_QUERY_RECORD_PROC();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSPAI1.INFO.FUNC == 'A') {
            B030_ADD_RECORD_PROC();
            if (pgmRtn) return;
            R000_HISTORY_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSPAI1.INFO.FUNC == 'M') {
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
            R000_HISTORY_PROCESS();
            if (pgmRtn) return;
            R000_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (AICSPAI1.INFO.FUNC == 'D') {
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
        CEP.TRC(SCCGWA, AICSPAI1);
        CEP.TRC(SCCGWA, AICSPAI1.INFO.FUNC);
        CEP.TRC(SCCGWA, AICSPAI1.DATA.KEY.TYP);
        CEP.TRC(SCCGWA, AICSPAI1.DATA.KEY.REDEFINES13.CODE);
        CEP.TRC(SCCGWA, AICSPAI1.DATA.EFF_DATE);
        CEP.TRC(SCCGWA, AICSPAI1.DATA.EXP_DATE);
        CEP.TRC(SCCGWA, AICSPAI1.DATA.DATA_TXT.BR);
        if (AICSPAI1.INFO.FUNC == 'A' 
            || AICSPAI1.INFO.FUNC == 'M' 
            || AICSPAI1.INFO.FUNC == 'D' 
            || AICSPAI1.INFO.FUNC == 'I') {
            if (AICSPAI1.DATA.KEY.REDEFINES13.CODE.trim().length() == 0) {
                WS_MSG_ERR = AICMSG_ERROR_MSG.AI_PAI1_CODE_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (AICSPAI1.INFO.FUNC == 'A' 
            || AICSPAI1.INFO.FUNC == 'M') {
            if (AICSPAI1.DATA.DATA_TXT.BR == 0) {
                WS_MSG_ERR = AICMSG_ERROR_MSG.AI_BR_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = AICSPAI1.DATA.DATA_TXT.BR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAI1);
        AIRPAI1.KEY.TYP = "PAI01";
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        AIRPAI1.KEY.CD = AICSPAI1.DATA.KEY.REDEFINES13.CODE;
        IBS.CPY2CLS(SCCGWA, AIRPAI1.KEY.CD, AIRPAI1.KEY.REDEFINES6);
        BPCPRMM.FUNC = '3';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B025_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRMBPM);
        BPRPARM.KEY.TYPE = "PAI01";
        BPCRMBPM.FUNC = 'S';
        BPCRMBPM.PTR = BPRPARM;
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        BPCRMBPM.FUNC = 'R';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        while (BPCRMBPM.RETURN_INFO != 'L') {
            IBS.CPY2CLS(SCCGWA, BPRPARM.BLOB_VAL, WS_DATA.WS_DATA_TXT);
            CEP.TRC(SCCGWA, BPRPARM.BLOB_VAL);
            CEP.TRC(SCCGWA, WS_DATA.WS_DATA_TXT);
            CEP.TRC(SCCGWA, WS_DATA.WS_DATA_TXT.WS_BR);
            CEP.TRC(SCCGWA, AICSPAI1.DATA.DATA_TXT.BR);
            CEP.TRC(SCCGWA, BPRPARM);
            if (AICSPAI1.DATA.DATA_TXT.BR == WS_DATA.WS_DATA_TXT.WS_BR) {
                WS_MSG_ERR = AICMSG_ERROR_MSG.AI_BR_CAN_NOT_SAME;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            BPCRMBPM.FUNC = 'R';
            S000_CALL_BPZRMBPM();
            if (pgmRtn) return;
        }
        BPCRMBPM.FUNC = 'E';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void B030_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAI1);
        AIRPAI1.KEY.TYP = "PAI01";
        BPCPRMM.EFF_DT = AICSPAI1.DATA.EFF_DATE;
        BPCPRMM.EXP_DT = AICSPAI1.DATA.EXP_DATE;
        R000_INPUT_DATA_PROCESS();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '0';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAI1);
        IBS.init(SCCGWA, AICHAI1O);
        IBS.init(SCCGWA, AICHAI1N);
        AIRPAI1.KEY.TYP = "PAI01";
        AIRPAI1.KEY.CD = AICSPAI1.DATA.KEY.REDEFINES13.CODE;
        IBS.CPY2CLS(SCCGWA, AIRPAI1.KEY.CD, AIRPAI1.KEY.REDEFINES6);
        BPCPRMM.EFF_DT = AICSPAI1.DATA.EFF_DATE;
        BPCPRMM.FUNC = '4';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, AICSPAI1.DATA.DATA_TXT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AIRPAI1.DATA_TXT.DATA_INF);
        if (JIBS_tmp_str[1].equalsIgnoreCase(JIBS_tmp_str[0]) 
            && AICSPAI1.DATA.DESC.equalsIgnoreCase(AIRPAI1.DESC) 
            && AICSPAI1.DATA.CDESC.equalsIgnoreCase(AIRPAI1.CDESC) 
            && AICSPAI1.DATA.EXP_DATE == BPCPRMM.EXP_DT) {
            WS_MSG_ERR = AICMSG_ERROR_MSG.AI_UPD_INFO_NOT_CHANGED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        AICHAI1O.KEY.TYP = "PAI01";
        AICHAI1O.KEY.CODE = AIRPAI1.KEY.REDEFINES6.CODE;
        AICHAI1O.DATA.BR = AIRPAI1.DATA_TXT.DATA_INF.BR;
        AICHAI1N.KEY.TYP = "PAI01";
        AICHAI1N.KEY.CODE = AICSPAI1.DATA.KEY.REDEFINES13.CODE;
        AICHAI1N.DATA.BR = AICSPAI1.DATA.DATA_TXT.BR;
        AIRPAI1.KEY.TYP = "PAI01";
        R000_INPUT_DATA_PROCESS();
        if (pgmRtn) return;
        BPCPRMM.EFF_DT = AICSPAI1.DATA.EFF_DATE;
        BPCPRMM.EXP_DT = AICSPAI1.DATA.EXP_DATE;
        BPCPRMM.FUNC = '2';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAI1);
        AIRPAI1.KEY.TYP = "PAI01";
        AIRPAI1.KEY.CD = AICSPAI1.DATA.KEY.REDEFINES13.CODE;
        IBS.CPY2CLS(SCCGWA, AIRPAI1.KEY.CD, AIRPAI1.KEY.REDEFINES6);
        BPCPRMM.EFF_DT = AICSPAI1.DATA.EFF_DATE;
        BPCPRMM.FUNC = '4';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        AIRPAI1.KEY.TYP = "PAI01";
        AIRPAI1.KEY.CD = AICSPAI1.DATA.KEY.REDEFINES13.CODE;
        IBS.CPY2CLS(SCCGWA, AIRPAI1.KEY.CD, AIRPAI1.KEY.REDEFINES6);
        BPCPRMM.EFF_DT = AICSPAI1.DATA.EFF_DATE;
        BPCPRMM.FUNC = '1';
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void R000_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_DATA);
        WS_DATA.WS_KEY.WS_TYP = "PAI01";
        WS_DATA.WS_KEY.WS_CODE = AIRPAI1.KEY.REDEFINES6.CODE;
        WS_DATA.WS_DESC = AIRPAI1.DESC;
        WS_DATA.WS_CDESC = AIRPAI1.CDESC;
        WS_DATA.WS_EFF_DATE = AICSPAI1.DATA.EFF_DATE;
        WS_DATA.WS_EXP_DATE = AICSPAI1.DATA.EXP_DATE;
        WS_DATA.WS_VAL_LEN = (short) AIRPAI1.VAL_LEN;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AIRPAI1.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_DATA.WS_DATA_TXT);
        CEP.TRC(SCCGWA, AIRPAI1.DATA_TXT);
        CEP.TRC(SCCGWA, WS_DATA.WS_DATA_TXT);
        CEP.TRC(SCCGWA, AIRPAI1);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CD_1;
        SCCFMT.DATA_PTR = WS_DATA;
        SCCFMT.DATA_LEN = 116;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (AICSPAI1.INFO.FUNC == 'A' 
            || AICSPAI1.INFO.FUNC == 'D') {
            R000_01_HISTORY_PROCESS();
            if (pgmRtn) return;
        }
        if (AICSPAI1.INFO.FUNC == 'U') {
            R000_02_HISTORY_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void R000_01_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICHAI1N);
        IBS.init(SCCGWA, AICHAI1O);
        if (AICSPAI1.INFO.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            AICHAI1N.KEY.TYP = "PAI01";
            AICHAI1N.KEY.CODE = AICSPAI1.DATA.KEY.REDEFINES13.CODE;
            AICHAI1N.DATA.BR = AICSPAI1.DATA.DATA_TXT.BR;
        }
        if (AICSPAI1.INFO.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            AICHAI1O.KEY.TYP = "PAI01";
            AICHAI1O.KEY.CODE = AICSPAI1.DATA.KEY.REDEFINES13.CODE;
            AICHAI1O.DATA.BR = AICSPAI1.DATA.DATA_TXT.BR;
        }
        BPCPNHIS.INFO.FMT_ID = "AICHPAI1";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.JRNNO);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID_LEN = 14;
        BPCPNHIS.INFO.OLD_DAT_PT = AICHAI1O;
        BPCPNHIS.INFO.NEW_DAT_PT = AICHAI1N;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_02_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.FMT_ID = "AICHPAI1";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID_LEN = 14;
        BPCPNHIS.INFO.OLD_DAT_PT = AICHAI1O;
        BPCPNHIS.INFO.NEW_DAT_PT = AICHAI1N;
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
        AIRPAI1.VAL_LEN = 28;
        BPCPRMM.DAT_PTR = AIRPAI1;
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_MSG_ERR = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_ALEADY_EXIST) 
                && BPCPRMM.FUNC == '0') {
                WS_MSG_ERR = AICMSG_ERROR_MSG.AI_PAI1_ALREADY_EXIST;
            }
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                WS_MSG_ERR = AICMSG_ERROR_MSG.AI_PAI1_NO_EXIST;
            }
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_BRW_ALL, BPCRMBPM);
        if (BPCRMBPM.RC.RC_CODE != 0) {
            WS_MSG_ERR = IBS.CLS2CPY(SCCGWA, BPCRMBPM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_INPUT_DATA_PROCESS() throws IOException,SQLException,Exception {
        AIRPAI1.KEY.TYP = "PAI01";
        AIRPAI1.KEY.REDEFINES6.CODE = AICSPAI1.DATA.KEY.REDEFINES13.CODE;
        AIRPAI1.KEY.CD = IBS.CLS2CPY(SCCGWA, AIRPAI1.KEY.REDEFINES6);
        AIRPAI1.DESC = AICSPAI1.DATA.DESC;
        AIRPAI1.CDESC = AICSPAI1.DATA.CDESC;
        AIRPAI1.DATA_TXT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        AIRPAI1.DATA_TXT.UPD_DATE = SCCGWA.COMM_AREA.TR_DATE;
        AIRPAI1.DATA_TXT.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        AIRPAI1.DATA_TXT.DATA_INF.BR = AICSPAI1.DATA.DATA_TXT.BR;
        CEP.TRC(SCCGWA, AIRPAI1.KEY.REDEFINES6.CODE);
        CEP.TRC(SCCGWA, AIRPAI1.DESC);
        CEP.TRC(SCCGWA, AIRPAI1.CDESC);
        CEP.TRC(SCCGWA, AICSPAI1.DATA.DATA_TXT.BR);
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
