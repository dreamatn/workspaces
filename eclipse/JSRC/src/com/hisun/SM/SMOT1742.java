package com.hisun.SM;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class SMOT1742 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_CMP_PARM_TYPE_MAINT = "BP-MAINT-PARM-TYPE";
    String K_CMP_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String PGM_SCSSCKDT = "SCSSCKDT";
    SMOT1742_WS_ERR_MSG WS_ERR_MSG = new SMOT1742_WS_ERR_MSG();
    short WS_FLD_NO = 0;
    int WS_DATE = 0;
    short WS_DATE_NO = 0;
    char WS_FUNC_FLG = ' ';
    char WS_TYPE_LVL_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCSMPTY BPCSMPTY = new BPCSMPTY();
    SCCGWA SCCGWA;
    SMB1742_AWA_1742 SMB1742_AWA_1742;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMOT1742 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB1742_AWA_1742>");
        SMB1742_AWA_1742 = (SMB1742_AWA_1742) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MAIN_PROCESS();
        if (pgmRtn) return;
        B300_SET_RETURN_INFO();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SMB1742_AWA_1742.FUNC_CD);
        WS_FUNC_FLG = SMB1742_AWA_1742.FUNC_CD;
        if ((WS_FUNC_FLG != 'B' 
            && WS_FUNC_FLG != 'A')) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUNC_CD_INPUT_ERR, WS_ERR_MSG);
            WS_FLD_NO = SMB1742_AWA_1742.FUNC_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (WS_FUNC_FLG == 'B') {
            if (SMB1742_AWA_1742.TYPE.trim().length() == 0 
                && SMB1742_AWA_1742.TYPE_LVL == ' ' 
                && SMB1742_AWA_1742.UP_TYPE.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_P_T_MUST_INPUT_ONE, WS_ERR_MSG);
                WS_FLD_NO = SMB1742_AWA_1742.TYPE_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (WS_FUNC_FLG == 'A') {
            if (SMB1742_AWA_1742.EFF_DATE > SMB1742_AWA_1742.EXP_DATE) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_EXP_DATE_ERROR, WS_ERR_MSG);
                WS_FLD_NO = SMB1742_AWA_1742.EFF_DATE_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (SMB1742_AWA_1742.TYPE_LVL != ' ') {
            WS_TYPE_LVL_FLG = SMB1742_AWA_1742.TYPE_LVL;
            if ((WS_TYPE_LVL_FLG != 'T' 
                && WS_TYPE_LVL_FLG != 'M' 
                && WS_TYPE_LVL_FLG != 'B' 
                && WS_TYPE_LVL_FLG != 'X')) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TYPE_LVL_ERROR, WS_ERR_MSG);
                WS_FLD_NO = SMB1742_AWA_1742.TYPE_LVL_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, SMB1742_AWA_1742.EFF_DATE);
        CEP.TRC(SCCGWA, SMB1742_AWA_1742.EXP_DATE);
        if (SMB1742_AWA_1742.EFF_DATE != 0) {
            WS_DATE = SMB1742_AWA_1742.EFF_DATE;
            WS_DATE_NO = SMB1742_AWA_1742.EFF_DATE_NO;
            R000_CHECK_DATE();
            if (pgmRtn) return;
        }
        if (SMB1742_AWA_1742.EXP_DATE != 0) {
            WS_DATE = SMB1742_AWA_1742.EXP_DATE;
            WS_DATE_NO = SMB1742_AWA_1742.EXP_DATE_NO;
            R000_CHECK_DATE();
            if (pgmRtn) return;
        }
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, WS_ERR_MSG);
        WS_FLD_NO = 0;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMPTY);
        BPCSMPTY.TYPE = SMB1742_AWA_1742.TYPE;
        BPCSMPTY.INFO.NAME = SMB1742_AWA_1742.NAME;
        BPCSMPTY.INFO.CHG_NAME = SMB1742_AWA_1742.CHG_NAME;
        BPCSMPTY.INFO.LVL = SMB1742_AWA_1742.TYPE_LVL;
        BPCSMPTY.INFO.UP_TYPE = SMB1742_AWA_1742.UP_TYPE;
        BPCSMPTY.INFO.DOWNLOAD_FLG = SMB1742_AWA_1742.DL_FLG;
        BPCSMPTY.INFO.REMARKS = SMB1742_AWA_1742.TYPE_RMK;
        BPCSMPTY.EFF_DATE = SMB1742_AWA_1742.EFF_DATE;
        BPCSMPTY.EXP_DATE = SMB1742_AWA_1742.EXP_DATE;
        BPCSMPTY.OUTPUT_FLG = 'Y';
        CEP.TRC(SCCGWA, BPCSMPTY);
        if (WS_FUNC_FLG == 'A'
            || WS_FUNC_FLG == 'Q'
            || WS_FUNC_FLG == 'U'
            || WS_FUNC_FLG == 'D') {
            BPCSMPTY.FUNC = 'C';
        } else if (WS_FUNC_FLG == 'B') {
            BPCSMPTY.FUNC = 'B';
        } else {
            CEP.TRC(SCCGWA, "WRONG FUNCTION CODE ");
            SCCGWA.RETURN_CODE = 12;
            Z_RET();
            if (pgmRtn) return;
        }
        S000_CALL_BPZSMPTY();
        if (pgmRtn) return;
        if (WS_FUNC_FLG == 'A') {
            if (BPCSMPTY.CHECK_RESULT == 'E') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_TYPE_EXIST, WS_ERR_MSG);
                WS_FLD_NO = SMB1742_AWA_1742.TYPE_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (WS_FUNC_FLG == 'Q' 
            || WS_FUNC_FLG == 'U' 
            || WS_FUNC_FLG == 'D') {
            if (BPCSMPTY.CHECK_RESULT == 'N') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_TYPE_NOTFND, WS_ERR_MSG);
                WS_FLD_NO = SMB1742_AWA_1742.TYPE_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B300_SET_RETURN_INFO() throws IOException,SQLException,Exception {
        if (WS_FUNC_FLG == 'B') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 998;
            SCCSUBS.TR_CODE = 1749;
            S000_SET_SUBS_TRN();
            if (pgmRtn) return;
        } else if (WS_FUNC_FLG == 'Q') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 4212;
            S000_SET_SUBS_TRN();
            if (pgmRtn) return;
        } else if (WS_FUNC_FLG == 'U') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 4213;
            S000_SET_SUBS_TRN();
            if (pgmRtn) return;
        } else if (WS_FUNC_FLG == 'D') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 4214;
            S000_SET_SUBS_TRN();
            if (pgmRtn) return;
        } else if (WS_FUNC_FLG == 'A') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 4215;
            S000_SET_SUBS_TRN();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "WRONG FUNCTION CODE ");
            SCCGWA.RETURN_CODE = 12;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = WS_DATE;
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        CEP.TRC(SCCGWA, SCCCKDT.RC);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG.WS_ERR_AP = "SC";
            WS_ERR_MSG.WS_ERR_CODE = SCCCKDT.RC;
            WS_FLD_NO = WS_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSMPTY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_PARM_TYPE_MAINT;
        SCCCALL.COMMAREA_PTR = BPCSMPTY;
        SCCCALL.ERR_FLDNO = SMB1742_AWA_1742.FUNC_CD_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_SET_SBUS_TRN;
        SCCCALL.COMMAREA_PTR = SCCSUBS;
        SCCCALL.ERR_FLDNO = SMB1742_AWA_1742.FUNC_CD_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
