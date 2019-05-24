package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4225 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    String K_CMP_PARM_CODE_MAINT = "BP-MAINT-PARM-CODE";
    String K_CMP_BP_P_INQ_PC = "BP-P-INQ-PC       ";
    String PGM_SCSSCKDT = "SCSSCKDT";
    BPOT4225_WS_ERR_MSG WS_ERR_MSG = new BPOT4225_WS_ERR_MSG();
    short WS_FLD_NO = 0;
    int WS_DATE = 0;
    short WS_DATE_NO = 0;
    short WS_LEN_I = 0;
    short WS_LEN_J = 0;
    short WS_LEN_K = 0;
    char WS_BR_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCSMPCD BPCSMPCD = new BPCSMPCD();
    SCCGWA SCCGWA;
    BPB4200_AWA_4200 BPB4200_AWA_4200;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4225 return!");
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
        B101_CHECK_DESC_PROCESS();
        B200_MAIN_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQPCD);
        CEP.TRC(SCCGWA, BPB4200_AWA_4200.TYPE);
        CEP.TRC(SCCGWA, BPB4200_AWA_4200.CODE);
        BPCOQPCD.INPUT_DATA.TYPE = BPB4200_AWA_4200.TYPE;
        R000_CALL_BPZPQPCD();
        CEP.TRC(SCCGWA, BPCOQPCD.RC);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            S000_ERR_MSG_PROC_CONTINUE();
        }
        for (WS_LEN_J = 1; WS_LEN_J <= 10; WS_LEN_J += 1) {
            if (BPB4200_AWA_4200.CODE == null) BPB4200_AWA_4200.CODE = "";
            JIBS_tmp_int = BPB4200_AWA_4200.CODE.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) BPB4200_AWA_4200.CODE += " ";
            if (BPB4200_AWA_4200.CODE.substring(WS_LEN_J - 1, WS_LEN_J + 1 - 1).trim().length() > 0) {
                WS_LEN_K = WS_LEN_J;
                if (BPB4200_AWA_4200.CODE == null) BPB4200_AWA_4200.CODE = "";
                JIBS_tmp_int = BPB4200_AWA_4200.CODE.length();
                for (int i=0;i<10-JIBS_tmp_int;i++) BPB4200_AWA_4200.CODE += " ";
                CEP.TRC(SCCGWA, BPB4200_AWA_4200.CODE.substring(WS_LEN_J - 1, WS_LEN_J + 1 - 1));
                CEP.TRC(SCCGWA, WS_LEN_K);
            }
        }
        CEP.TRC(SCCGWA, BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_CODE_MAX_LEN);
        if (BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_CODE_MAX_LEN != 0) {
            if (BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_CODE_MAX_LEN < WS_LEN_K) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CODE_LENGTH_BIGGER, WS_ERR_MSG);
                WS_FLD_NO = BPB4200_AWA_4200.CODE_NO;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, BPB4200_AWA_4200.SH_NAME);
        WS_LEN_J = 0;
        WS_LEN_K = 0;
        for (WS_LEN_J = 1; WS_LEN_J <= 60; WS_LEN_J += 1) {
            if (BPB4200_AWA_4200.SH_NAME == null) BPB4200_AWA_4200.SH_NAME = "";
            JIBS_tmp_int = BPB4200_AWA_4200.SH_NAME.length();
            for (int i=0;i<120-JIBS_tmp_int;i++) BPB4200_AWA_4200.SH_NAME += " ";
            if (BPB4200_AWA_4200.SH_NAME.substring(WS_LEN_J - 1, WS_LEN_J + 1 - 1).trim().length() > 0) {
                WS_LEN_K = WS_LEN_J;
            }
        }
        CEP.TRC(SCCGWA, BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_DESCC_MAX_LEN);
        if (BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_DESCC_MAX_LEN != 0) {
            if (BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_DESCC_MAX_LEN < WS_LEN_K) {
                CEP.TRC(SCCGWA, BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_DESCC_MAX_LEN);
                CEP.TRC(SCCGWA, WS_LEN_K);
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CNAME_LENGTH_BIGGER, WS_ERR_MSG);
                WS_FLD_NO = BPB4200_AWA_4200.SH_NAME_NO;
                CEP.TRC(SCCGWA, "DESCC");
                S000_ERR_MSG_PROC();
            }
        }
        WS_LEN_J = 0;
        WS_LEN_K = 0;
        for (WS_LEN_J = 1; WS_LEN_J <= 60; WS_LEN_J += 1) {
            if (BPB4200_AWA_4200.CD_NAME == null) BPB4200_AWA_4200.CD_NAME = "";
            JIBS_tmp_int = BPB4200_AWA_4200.CD_NAME.length();
            for (int i=0;i<120-JIBS_tmp_int;i++) BPB4200_AWA_4200.CD_NAME += " ";
            if (BPB4200_AWA_4200.CD_NAME.substring(WS_LEN_J - 1, WS_LEN_J + 1 - 1).trim().length() > 0) {
                WS_LEN_K = WS_LEN_J;
            }
        }
        if (BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_DESCE_MAX_LEN != 0) {
            if (BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_DESCE_MAX_LEN < WS_LEN_K) {
                CEP.TRC(SCCGWA, BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_DESCE_MAX_LEN);
                CEP.TRC(SCCGWA, WS_LEN_K);
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ENAME_LENGTH_BIGGER, WS_ERR_MSG);
                WS_FLD_NO = BPB4200_AWA_4200.CD_NAME_NO;
                CEP.TRC(SCCGWA, "DESCE");
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_MAINT_BR1 != 0 
            || BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_MAINT_BR2 != 0 
            || BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_MAINT_BR3 != 0 
            || BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_MAINT_BR4 != 0 
            || BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_MAINT_BR5 != 0) {
            WS_BR_FLG = 'O';
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        if (BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_MAINT_BR1 != 0) {
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_MAINT_BR1) {
                WS_BR_FLG = 'I';
            }
        }
        if (BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_MAINT_BR2 != 0) {
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_MAINT_BR2) {
                WS_BR_FLG = 'I';
            }
        }
        if (BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_MAINT_BR3 != 0) {
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_MAINT_BR3) {
                WS_BR_FLG = 'I';
            }
        }
        if (BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_MAINT_BR4 != 0) {
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_MAINT_BR4) {
                WS_BR_FLG = 'I';
            }
        }
        if (BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_MAINT_BR4 != 0) {
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == BPCOQPCD.OUTPUT_DATA.TYPE_INFO.TYPE_MAINT_BR5) {
                WS_BR_FLG = 'I';
            }
        }
        if (BPB4200_AWA_4200.EFF_DATE > BPB4200_AWA_4200.EXP_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_EXP_DATE_ERROR, WS_ERR_MSG);
            WS_FLD_NO = BPB4200_AWA_4200.EFF_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
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
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, WS_ERR_MSG);
        WS_FLD_NO = 0;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B101_CHECK_DESC_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMPCD);
        BPCSMPCD.TYPE = BPB4200_AWA_4200.TYPE;
        BPCSMPCD.TYPE_NAME = BPB4200_AWA_4200.NAME;
        BPCSMPCD.TYPE_CHG_NAME = BPB4200_AWA_4200.CHG_NAME;
        BPCSMPCD.CODE = BPB4200_AWA_4200.CODE;
        BPCSMPCD.INFO.CODE_NAME = BPB4200_AWA_4200.CD_NAME;
        BPCSMPCD.INFO.CODE_NAME_S = BPB4200_AWA_4200.SH_NAME;
        BPCSMPCD.EFF_DATE = BPB4200_AWA_4200.EFF_DATE;
        BPCSMPCD.EXP_DATE = BPB4200_AWA_4200.EXP_DATE;
        BPCSMPCD.OUTPUT_FLG = 'N';
        BPCSMPCD.FUNC = 'I';
        S000_CALL_BPZSMPCD();
        if (BPCSMPCD.CHECK_RESULT == 'E') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_SAME_DESCRIPTION, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMPCD);
        BPCSMPCD.TYPE = BPB4200_AWA_4200.TYPE;
        BPCSMPCD.TYPE_NAME = BPB4200_AWA_4200.NAME;
        BPCSMPCD.TYPE_CHG_NAME = BPB4200_AWA_4200.CHG_NAME;
        BPCSMPCD.CODE = BPB4200_AWA_4200.CODE;
        BPCSMPCD.INFO.CODE_NAME = BPB4200_AWA_4200.CD_NAME;
        BPCSMPCD.INFO.CODE_NAME_S = BPB4200_AWA_4200.SH_NAME;
        BPCSMPCD.INFO.REMARKS = BPB4200_AWA_4200.CODE_RMK;
        BPCSMPCD.EFF_DATE = BPB4200_AWA_4200.EFF_DATE;
        BPCSMPCD.EXP_DATE = BPB4200_AWA_4200.EXP_DATE;
        CEP.TRC(SCCGWA, BPCSMPCD.EXP_DATE);
        BPCSMPCD.INFO.RBASE_TYP = BPB4200_AWA_4200.RBASE_TY;
        CEP.TRC(SCCGWA, BPCSMPCD.INFO.RBASE_TYP);
        BPCSMPCD.OUTPUT_FLG = 'Y';
        BPCSMPCD.FUNC = 'A';
        S000_CALL_BPZSMPCD();
    }
    public void R000_CHECK_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = WS_DATE;
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG.WS_ERR_AP = "SC";
            WS_ERR_MSG.WS_ERR_CODE = SCCCKDT.RC;
            WS_FLD_NO = WS_DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_BPZSMPCD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_PARM_CODE_MAINT;
        SCCCALL.COMMAREA_PTR = BPCSMPCD;
        SCCCALL.ERR_FLDNO = BPB4200_AWA_4200.FUNC_CD_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void R000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_BP_P_INQ_PC, BPCOQPCD);
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
