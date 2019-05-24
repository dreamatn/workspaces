package com.hisun.SM;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class SMOT1650 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char K_ERROR = 'E';
    String PGM_SCZMSGB = "SCZMSGB";
    SMOT1650_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT1650_WS_TEMP_VARIABLE();
    String WS_TSQ_REC = " ";
    short WS_I = 0;
    BPCXP15 BPCXP15 = new BPCXP15();
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
    BPRSWT BPRSWT = new BPRSWT();
    SCCGWA SCCGWA;
    SMB1650_AWA_1650 SMB1650_AWA_1650;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMOT1650 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB1650_AWA_1650>");
        SMB1650_AWA_1650 = (SMB1650_AWA_1650) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
        IBS.init(SCCGWA, SCCPRMM);
        IBS.init(SCCGWA, SCRPRMT);
        SCCPRMM.RC.RC_APP = "SM";
        SCCPRMM.DAT_PTR = SCRPRMT;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        if (SMB1650_AWA_1650.FUNC == 'I') {
            B100_READ_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1650_AWA_1650.FUNC == 'A') {
            B100_WRITE_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1650_AWA_1650.FUNC == 'M') {
            B100_READU_PROCESS();
            if (pgmRtn) return;
            B100_REWRITE_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1650_AWA_1650.FUNC == 'D') {
            B100_READ_PROCESS();
            if (pgmRtn) return;
            B100_DELETE_PROCESS();
            if (pgmRtn) return;
        } else {
            Z_RET();
            if (pgmRtn) return;
        }
        B200_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        R00_CHECK_ERROR();
        if (pgmRtn) return;
    }
    public void R00_CHECK_ERROR() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_READ_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '3';
        SCRPRMT.KEY.TYP = SMB1650_AWA_1650.PTYP;
        SCRPRMT.KEY.CD = SMB1650_AWA_1650.CODE;
        SCCPRMM.EFF_DT = SMB1650_AWA_1650.EFFDATE;
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B100_READU_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '4';
        SCRPRMT.KEY.TYP = SMB1650_AWA_1650.PTYP;
        SCRPRMT.KEY.CD = SMB1650_AWA_1650.CODE;
        SCCPRMM.EFF_DT = SMB1650_AWA_1650.EFFDATE;
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B100_REWRITE_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '2';
        SCRPRMT.KEY.TYP = SMB1650_AWA_1650.PTYP;
        SCRPRMT.KEY.CD = SMB1650_AWA_1650.CODE;
        SCCPRMM.EFF_DT = SMB1650_AWA_1650.EFFDATE;
        SCCPRMM.EXP_DT = SMB1650_AWA_1650.EXPDATE;
        SCRPRMT.DESC = SMB1650_AWA_1650.DESC;
        SCRPRMT.CDESC = SMB1650_AWA_1650.CDESC;
        IBS.init(SCCGWA, BPRSWT.DATA_TXT);
        for (WS_I = 1; WS_I <= 3; WS_I += 1) {
            BPRSWT.DATA_TXT.DEST_TR_TBL[WS_I-1].DEST_TR = SMB1650_AWA_1650.TRAN_TAB[WS_I-1].TRCODE;
            BPRSWT.DATA_TXT.DEST_TR_TBL[WS_I-1].TR_FROM = SMB1650_AWA_1650.TRAN_TAB[WS_I-1].TRFROM;
            BPRSWT.DATA_TXT.DEST_TR_TBL[WS_I-1].IPTT_NO = SMB1650_AWA_1650.TRAN_TAB[WS_I-1].IPTNO;
        }
        BPRSWT.DATA_TXT.VCH_IND = SMB1650_AWA_1650.VCH_IND;
        BPRSWT.DATA_TXT.REMARK = SMB1650_AWA_1650.REMARK;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRSWT.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SCRPRMT.DAT_TXT);
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B100_DELETE_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '1';
        SCRPRMT.KEY.TYP = SMB1650_AWA_1650.PTYP;
        SCRPRMT.KEY.CD = SMB1650_AWA_1650.CODE;
        SCCPRMM.EFF_DT = SMB1650_AWA_1650.EFFDATE;
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B100_WRITE_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '0';
        SCRPRMT.KEY.TYP = SMB1650_AWA_1650.PTYP;
        SCRPRMT.KEY.CD = SMB1650_AWA_1650.CODE;
        SCCPRMM.EFF_DT = SMB1650_AWA_1650.EFFDATE;
        SCCPRMM.EXP_DT = SMB1650_AWA_1650.EXPDATE;
        SCRPRMT.DESC = SMB1650_AWA_1650.DESC;
        SCRPRMT.CDESC = SMB1650_AWA_1650.CDESC;
        IBS.init(SCCGWA, BPRSWT.DATA_TXT);
        for (WS_I = 1; WS_I <= 3; WS_I += 1) {
            BPRSWT.DATA_TXT.DEST_TR_TBL[WS_I-1].DEST_TR = SMB1650_AWA_1650.TRAN_TAB[WS_I-1].TRCODE;
            BPRSWT.DATA_TXT.DEST_TR_TBL[WS_I-1].TR_FROM = SMB1650_AWA_1650.TRAN_TAB[WS_I-1].TRFROM;
            BPRSWT.DATA_TXT.DEST_TR_TBL[WS_I-1].IPTT_NO = SMB1650_AWA_1650.TRAN_TAB[WS_I-1].IPTNO;
        }
        BPRSWT.DATA_TXT.VCH_IND = SMB1650_AWA_1650.VCH_IND;
        BPRSWT.DATA_TXT.REMARK = SMB1650_AWA_1650.REMARK;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRSWT.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SCRPRMT.DAT_TXT);
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B200_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCXP15);
        IBS.init(SCCGWA, SCCFMT);
        BPCXP15.FUNC = SMB1650_AWA_1650.FUNC;
        BPCXP15.TYPE = SCRPRMT.KEY.TYP;
        BPCXP15.CODE = SCRPRMT.KEY.CD;
        BPCXP15.EFF_DATE = SCCPRMM.EFF_DT;
        BPCXP15.EXP_DATE = SCCPRMM.EXP_DT;
        BPCXP15.DESC = SCRPRMT.DESC;
        BPCXP15.CDESC = SCRPRMT.CDESC;
        BPCXP15.FLAG = 0X02;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRSWT.DATA_TXT);
        for (WS_I = 1; WS_I <= 3; WS_I += 1) {
            BPCXP15.DATA_TXT.DEST_TR_TBL[WS_I-1].DEST_TR = BPRSWT.DATA_TXT.DEST_TR_TBL[WS_I-1].DEST_TR;
            BPCXP15.DATA_TXT.DEST_TR_TBL[WS_I-1].TR_FROM = BPRSWT.DATA_TXT.DEST_TR_TBL[WS_I-1].TR_FROM;
            BPCXP15.DATA_TXT.DEST_TR_TBL[WS_I-1].IPTT_NO = BPRSWT.DATA_TXT.DEST_TR_TBL[WS_I-1].IPTT_NO;
        }
        BPCXP15.DATA_TXT.VCH_IND = BPRSWT.DATA_TXT.VCH_IND;
        BPCXP15.DATA_TXT.REMARK = BPRSWT.DATA_TXT.REMARK;
        SCCFMT.FMTID = "BPP15";
        SCCFMT.DATA_PTR = BPCXP15;
        SCCFMT.DATA_LEN = 198;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_SCZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-PARM-MAINTAIN", SCCPRMM);
        if (SCCPRMM.RC.RC_RTNCODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_AP = SCCPRMM.RC.RC_APP;
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_CODE = SCCPRMM.RC.RC_RTNCODE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
