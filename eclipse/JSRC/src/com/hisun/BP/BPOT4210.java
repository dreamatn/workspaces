package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4210 {
    String JIBS_tmp_str[] = new String[10];
    String K_CMP_PARM_TYPE_MAINT = "BP-MAINT-PARM-TYPE";
    String K_CMP_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String PGM_SCSSCKDT = "SCSSCKDT";
    BPOT4210_WS_ERR_MSG WS_ERR_MSG = new BPOT4210_WS_ERR_MSG();
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
    BPB4200_AWA_4200 BPB4200_AWA_4200;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4210 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4200_AWA_4200>");
        BPB4200_AWA_4200 = (BPB4200_AWA_4200) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_MAIN_PROCESS();
        B300_SET_RETURN_INFO();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4200_AWA_4200.FUNC_CD);
        WS_FUNC_FLG = BPB4200_AWA_4200.FUNC_CD;
        if ((WS_FUNC_FLG != 'B' 
            && WS_FUNC_FLG != 'A')) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUNC_CD_INPUT_ERR, WS_ERR_MSG);
            WS_FLD_NO = BPB4200_AWA_4200.FUNC_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (WS_FUNC_FLG == 'B') {
            if (BPB4200_AWA_4200.TYPE.trim().length() == 0 
                && BPB4200_AWA_4200.TYPE_LVL == ' ' 
                && BPB4200_AWA_4200.UP_TYPE.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_P_T_MUST_INPUT_ONE, WS_ERR_MSG);
                WS_FLD_NO = BPB4200_AWA_4200.TYPE_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        if (WS_FUNC_FLG == 'A') {
            if (BPB4200_AWA_4200.EFF_DATE > BPB4200_AWA_4200.EXP_DATE) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_EXP_DATE_ERROR, WS_ERR_MSG);
                WS_FLD_NO = BPB4200_AWA_4200.EFF_DATE_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        if (BPB4200_AWA_4200.TYPE_LVL != ' ') {
            WS_TYPE_LVL_FLG = BPB4200_AWA_4200.TYPE_LVL;
            if ((WS_TYPE_LVL_FLG != 'T' 
                && WS_TYPE_LVL_FLG != 'M' 
                && WS_TYPE_LVL_FLG != 'B' 
                && WS_TYPE_LVL_FLG != 'X')) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TYPE_LVL_ERROR, WS_ERR_MSG);
                WS_FLD_NO = BPB4200_AWA_4200.TYPE_LVL_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        CEP.TRC(SCCGWA, BPB4200_AWA_4200.EFF_DATE);
        CEP.TRC(SCCGWA, BPB4200_AWA_4200.EXP_DATE);
        if (BPB4200_AWA_4200.EFF_DATE != 0) {
            WS_DATE = BPB4200_AWA_4200.EFF_DATE;
            WS_DATE_NO = BPB4200_AWA_4200.EFF_DATE_NO;
            R000_CHECK_DATE();
        }
        if (BPB4200_AWA_4200.EXP_DATE != 0) {
            WS_DATE = BPB4200_AWA_4200.EXP_DATE;
            WS_DATE_NO = BPB4200_AWA_4200.EXP_DATE_NO;
            R000_CHECK_DATE();
        }
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, WS_ERR_MSG);
        WS_FLD_NO = 0;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMPTY);
        BPCSMPTY.TYPE = BPB4200_AWA_4200.TYPE;
        BPCSMPTY.INFO.NAME = BPB4200_AWA_4200.NAME;
        BPCSMPTY.INFO.CHG_NAME = BPB4200_AWA_4200.CHG_NAME;
        BPCSMPTY.INFO.LVL = BPB4200_AWA_4200.TYPE_LVL;
        BPCSMPTY.INFO.UP_TYPE = BPB4200_AWA_4200.UP_TYPE;
        BPCSMPTY.INFO.DOWNLOAD_FLG = BPB4200_AWA_4200.DL_FLG;
        BPCSMPTY.INFO.REMARKS = BPB4200_AWA_4200.TYPE_RMK;
        BPCSMPTY.EFF_DATE = BPB4200_AWA_4200.EFF_DATE;
        BPCSMPTY.EXP_DATE = BPB4200_AWA_4200.EXP_DATE;
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
        }
        S000_CALL_BPZSMPTY();
        if (WS_FUNC_FLG == 'A') {
            if (BPCSMPTY.CHECK_RESULT == 'E') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_TYPE_EXIST, WS_ERR_MSG);
                WS_FLD_NO = BPB4200_AWA_4200.TYPE_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (WS_FUNC_FLG == 'Q' 
            || WS_FUNC_FLG == 'U' 
            || WS_FUNC_FLG == 'D') {
            if (BPCSMPTY.CHECK_RESULT == 'N') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_TYPE_NOTFND, WS_ERR_MSG);
                WS_FLD_NO = BPB4200_AWA_4200.TYPE_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B300_SET_RETURN_INFO() throws IOException,SQLException,Exception {
        if (WS_FUNC_FLG == 'B') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 4211;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'Q') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 4212;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'U') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 4213;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'D') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 4214;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'A') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 4215;
            S000_SET_SUBS_TRN();
        } else {
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
        }
    }
    public void S000_CALL_BPZSMPTY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_PARM_TYPE_MAINT;
        SCCCALL.COMMAREA_PTR = BPCSMPTY;
        SCCCALL.ERR_FLDNO = BPB4200_AWA_4200.FUNC_CD_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_SET_SBUS_TRN;
        SCCCALL.COMMAREA_PTR = SCCSUBS;
        SCCCALL.ERR_FLDNO = BPB4200_AWA_4200.FUNC_CD_NO;
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
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
