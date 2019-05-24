package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4213 {
    String JIBS_tmp_str[] = new String[10];
    String K_CMP_PARM_TYPE_MAINT = "BP-MAINT-PARM-TYPE";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String PGM_SCSSCKDT = "SCSSCKDT";
    BPOT4213_WS_ERR_MSG WS_ERR_MSG = new BPOT4213_WS_ERR_MSG();
    short WS_FLD_NO = 0;
    int WS_DATE = 0;
    short WS_DATE_NO = 0;
    char WS_TYPE_LVL_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCSMPTY BPCSMPTY = new BPCSMPTY();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    BPB4200_AWA_4200 BPB4200_AWA_4200;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4213 return!");
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
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB4200_AWA_4200.EFF_DATE > BPB4200_AWA_4200.EXP_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_EXP_DATE_ERROR, WS_ERR_MSG);
            WS_FLD_NO = BPB4200_AWA_4200.EFF_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
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
        if (BPB4200_AWA_4200.TYPE_LVL == 'T' 
            || BPB4200_AWA_4200.TYPE_LVL == 'M') {
            if (BPB4200_AWA_4200.MAX_LNCD != 0 
                && BPB4200_AWA_4200.MAX_LNDE != 0 
                && BPB4200_AWA_4200.MAX_LNDC != 0 
                && BPB4200_AWA_4200.MAIN_BR1 != 0 
                && BPB4200_AWA_4200.MAIN_BR2 != 0 
                && BPB4200_AWA_4200.MAIN_BR3 != 0 
                && BPB4200_AWA_4200.MAIN_BR4 != 0 
                && BPB4200_AWA_4200.MAIN_BR5 != 0 
                && BPB4200_AWA_4200.REP_FLG != '0') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TYPE_LVL_ERROR, WS_ERR_MSG);
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
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
        CEP.TRC(SCCGWA, BPB4200_AWA_4200.MAX_LNCD);
        CEP.TRC(SCCGWA, BPB4200_AWA_4200.MAX_LNDE);
        CEP.TRC(SCCGWA, BPB4200_AWA_4200.MAX_LNDC);
        CEP.TRC(SCCGWA, BPB4200_AWA_4200.MAIN_BR1);
        CEP.TRC(SCCGWA, BPB4200_AWA_4200.MAIN_BR2);
        CEP.TRC(SCCGWA, BPB4200_AWA_4200.MAIN_BR3);
        CEP.TRC(SCCGWA, BPB4200_AWA_4200.MAIN_BR4);
        CEP.TRC(SCCGWA, BPB4200_AWA_4200.MAIN_BR5);
        if (BPB4200_AWA_4200.MAIN_BR1 != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPB4200_AWA_4200.MAIN_BR1;
            S000_CALL_BPZPQORG();
            if (BPCPQORG.RC.RC_CODE != 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND, WS_ERR_MSG);
                WS_FLD_NO = BPB4200_AWA_4200.MAIN_BR1_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB4200_AWA_4200.MAIN_BR2 != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPB4200_AWA_4200.MAIN_BR2;
            S000_CALL_BPZPQORG();
            if (BPCPQORG.RC.RC_CODE != 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND, WS_ERR_MSG);
                WS_FLD_NO = BPB4200_AWA_4200.MAIN_BR2_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB4200_AWA_4200.MAIN_BR3 != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPB4200_AWA_4200.MAIN_BR3;
            S000_CALL_BPZPQORG();
            if (BPCPQORG.RC.RC_CODE != 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND, WS_ERR_MSG);
                WS_FLD_NO = BPB4200_AWA_4200.MAIN_BR3_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB4200_AWA_4200.MAIN_BR4 != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPB4200_AWA_4200.MAIN_BR4;
            S000_CALL_BPZPQORG();
            if (BPCPQORG.RC.RC_CODE != 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND, WS_ERR_MSG);
                WS_FLD_NO = BPB4200_AWA_4200.MAIN_BR4_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB4200_AWA_4200.MAIN_BR5 != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPB4200_AWA_4200.MAIN_BR5;
            S000_CALL_BPZPQORG();
            if (BPCPQORG.RC.RC_CODE != 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND, WS_ERR_MSG);
                WS_FLD_NO = BPB4200_AWA_4200.MAIN_BR5_NO;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, BPB4200_AWA_4200.REP_FLG);
        if (BPB4200_AWA_4200.TYPE_LVL == 'B' 
            || BPB4200_AWA_4200.TYPE_LVL == 'X') {
            if (BPB4200_AWA_4200.REP_FLG != 'Y' 
                && BPB4200_AWA_4200.REP_FLG != 'N') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_REP_FLAG_MUST_INPUT, WS_ERR_MSG);
                WS_FLD_NO = BPB4200_AWA_4200.REP_FLG_NO;
                S000_ERR_MSG_PROC();
            }
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
        BPCSMPTY.INFO.CODE_MAX_LEN = BPB4200_AWA_4200.MAX_LNCD;
        BPCSMPTY.INFO.DESCE_MAX_LEN = BPB4200_AWA_4200.MAX_LNDE;
        BPCSMPTY.INFO.DESCC_MAX_LEN = BPB4200_AWA_4200.MAX_LNDC;
        BPCSMPTY.INFO.MAINT_BR1 = BPB4200_AWA_4200.MAIN_BR1;
        BPCSMPTY.INFO.MAINT_BR2 = BPB4200_AWA_4200.MAIN_BR2;
        BPCSMPTY.INFO.MAINT_BR3 = BPB4200_AWA_4200.MAIN_BR3;
        BPCSMPTY.INFO.MAINT_BR4 = BPB4200_AWA_4200.MAIN_BR4;
        BPCSMPTY.INFO.MAINT_BR5 = BPB4200_AWA_4200.MAIN_BR5;
        BPCSMPTY.INFO.REP_FLG = BPB4200_AWA_4200.REP_FLG;
        BPCSMPTY.INFO.OPEN_DATE = BPB4200_AWA_4200.OPEN_DT;
        BPCSMPTY.INFO.LST_DATE = BPB4200_AWA_4200.LAST_DT;
        BPCSMPTY.OUTPUT_FLG = 'Y';
        BPCSMPTY.FUNC = 'U';
        S000_CALL_BPZSMPTY();
    }
    public void R000_CHECK_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = WS_DATE;
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        CEP.TRC(SCCGWA, SCCCKDT.RC);
        CEP.TRC(SCCGWA, WS_DATE);
        CEP.TRC(SCCGWA, SCCCKDT.DATE);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG.WS_ERR_AP = "SC";
            WS_ERR_MSG.WS_ERR_CODE = SCCCKDT.RC;
            WS_FLD_NO = WS_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
    }
    public void S000_CALL_BPZSMPTY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_PARM_TYPE_MAINT;
        SCCCALL.COMMAREA_PTR = BPCSMPTY;
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
