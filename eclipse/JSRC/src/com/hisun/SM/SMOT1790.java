package com.hisun.SM;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class SMOT1790 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    SMOT1790_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT1790_WS_TEMP_VARIABLE();
    String WS_TSQ_REC = " ";
    BPCXP28 BPCXP28 = new BPCXP28();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCPRMM SCCPRMM = new SCCPRMM();
    SCRPRMT SCRPRMT = new SCRPRMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPRTRT BPRTRT = new BPRTRT();
    SCCGWA SCCGWA;
    SMB1790_AWA_1790 SMB1790_AWA_1790;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "SMOT1790 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB1790_AWA_1790>");
        SMB1790_AWA_1790 = (SMB1790_AWA_1790) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
        IBS.init(SCCGWA, SCCPRMM);
        IBS.init(SCCGWA, SCRPRMT);
        SCCPRMM.RC.RC_APP = "SM";
        SCCPRMM.DAT_PTR = SCRPRMT;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_TRANS_DATA();
        B100_OUTPUT_PROCESS();
    }
    public void B100_TRANS_DATA() throws IOException,SQLException,Exception {
        SCRPRMT.KEY.TYP = SMB1790_AWA_1790.PTYP;
        SCRPRMT.KEY.CD = SMB1790_AWA_1790.CODE;
        SCCPRMM.EFF_DT = SMB1790_AWA_1790.EFFDATE;
        SCCPRMM.FUNC = '3';
        S000_CALL_SCZPRMM();
        SCRPRMT.DESC = SMB1790_AWA_1790.DESC;
        SCRPRMT.CDESC = SMB1790_AWA_1790.CDESC;
        IBS.init(SCCGWA, BPRTRT.DATA_TXT);
    }
    public void B100_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCXP28);
        IBS.init(SCCGWA, SCCFMT);
        BPCXP28.FUNC = SMB1790_AWA_1790.FUNC;
        BPCXP28.TYPE = SCRPRMT.KEY.TYP;
        BPCXP28.CODE = SCRPRMT.KEY.CD;
        BPCXP28.EFF_DATE = SCCPRMM.EFF_DT;
        BPCXP28.EXP_DATE = SCCPRMM.EXP_DT;
        CEP.TRC(SCCGWA, BPCXP28.EXP_DATE);
        BPCXP28.DESC = SCRPRMT.DESC;
        BPCXP28.CDESC = SCRPRMT.CDESC;
        BPCXP28.FLAG = 0X02;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRTRT.DATA_TXT);
        BPCXP28.TR_MMO = BPRTRT.DATA_TXT.TR_MMO;
        BPCXP28.STUS = BPRTRT.DATA_TXT.STUS;
        BPCXP28.SYS_LVL = BPRTRT.DATA_TXT.SYS_LVL;
        BPCXP28.RUN_MODE = BPRTRT.DATA_TXT.RUN_MODE;
        BPCXP28.LOG_IND = BPRTRT.DATA_TXT.LOG_IND;
        BPCXP28.REEN_IND = BPRTRT.DATA_TXT.REEN_IND;
        BPCXP28.CLS = BPRTRT.DATA_TXT.CLS;
        BPCXP28.SELF_GRNT = BPRTRT.DATA_TXT.SELF_GRNT;
        BPCXP28.AUTH_LVL = BPRTRT.DATA_TXT.AUTH_LVL;
        BPCXP28.APVL_IND = BPRTRT.DATA_TXT.APVL_IND;
        BPCXP28.OWN_SYS_OPT = BPRTRT.DATA_TXT.OWN_SYS_IND;
        BPCXP28.HIS_REC_IND = BPRTRT.DATA_TXT.HIS_REC_IND;
        if (BPRTRT.DATA_TXT.ATTR_WORD == null) BPRTRT.DATA_TXT.ATTR_WORD = "";
        JIBS_tmp_int = BPRTRT.DATA_TXT.ATTR_WORD.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) BPRTRT.DATA_TXT.ATTR_WORD += " ";
        if (BPRTRT.DATA_TXT.ATTR_WORD.substring(0, 1).equalsIgnoreCase("1")) {
            BPCXP28.FINT_IND = 'Y';
        } else {
            BPCXP28.FINT_IND = 'N';
        }
        if (BPRTRT.DATA_TXT.ATTR_WORD == null) BPRTRT.DATA_TXT.ATTR_WORD = "";
        JIBS_tmp_int = BPRTRT.DATA_TXT.ATTR_WORD.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) BPRTRT.DATA_TXT.ATTR_WORD += " ";
        if (BPRTRT.DATA_TXT.ATTR_WORD.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            BPCXP28.REVT_IND = 'Y';
        } else {
            BPCXP28.REVT_IND = 'N';
        }
        BPCXP28.SUBS_TX_CODE = BPRTRT.DATA_TXT.SUBS_TX_CODE;
        BPCXP28.AWA_IND = BPRTRT.DATA_TXT.AWA_IND;
        BPCXP28.INP_FMT = BPRTRT.DATA_TXT.INP_FMT;
        BPCXP28.OUTP_FMT = BPRTRT.DATA_TXT.OUTP_FMT;
        BPCXP28.OUTP_CNTL = BPRTRT.DATA_TXT.OUTP_CNTL;
        BPCXP28.SPEC_CNTL = BPRTRT.DATA_TXT.SPEC_CNTL;
        BPCXP28.WAIT_BSP1 = BPRTRT.DATA_TXT.WAIT_BSP.WAIT_BSP1;
        BPCXP28.WAIT_BSP2 = BPRTRT.DATA_TXT.WAIT_BSP.WAIT_BSP2;
        BPCXP28.WAIT_BSP3 = BPRTRT.DATA_TXT.WAIT_BSP.WAIT_BSP3;
        BPCXP28.PGM_CNT = BPRTRT.DATA_TXT.PGM_CNT;
        for (WS_TEMP_VARIABLE.WS_COUNT = 1; WS_TEMP_VARIABLE.WS_COUNT <= 5; WS_TEMP_VARIABLE.WS_COUNT += 1) {
            BPCXP28.FILE_TBL[WS_TEMP_VARIABLE.WS_COUNT-1].PGM_NAME = BPRTRT.DATA_TXT.PGM[WS_TEMP_VARIABLE.WS_COUNT-1].PGM_NAME;
            BPCXP28.FILE_TBL[WS_TEMP_VARIABLE.WS_COUNT-1].PGM_SCHE = BPRTRT.DATA_TXT.PGM[WS_TEMP_VARIABLE.WS_COUNT-1].PGM_SCHE;
            BPCXP28.FILE_TBL[WS_TEMP_VARIABLE.WS_COUNT-1].PGM_CTL = BPRTRT.DATA_TXT.PGM[WS_TEMP_VARIABLE.WS_COUNT-1].PGM_CTL;
            BPCXP28.FILE_TBL[WS_TEMP_VARIABLE.WS_COUNT-1].PGM_RMK = BPRTRT.DATA_TXT.PGM[WS_TEMP_VARIABLE.WS_COUNT-1].PGM_RMK;
        }
        SCCFMT.FMTID = "BPX01";
        SCCFMT.DATA_PTR = BPCXP28;
        SCCFMT.DATA_LEN = 458;
        IBS.FMT(SCCGWA, SCCFMT);
        WS_TSQ_REC = IBS.CLS2CPY(SCCGWA, BPCXP28);
    }
    public void S000_CALL_SCZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-PARM-MAINTAIN", SCCPRMM);
        if (SCCPRMM.RC.RC_RTNCODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_AP = SCCPRMM.RC.RC_APP;
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_CODE = SCCPRMM.RC.RC_RTNCODE;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}