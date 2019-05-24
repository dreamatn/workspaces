package com.hisun.SM;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class SMOT1640 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char K_ERROR = 'E';
    SMOT1640_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT1640_WS_TEMP_VARIABLE();
    String WS_TSQ_REC = " ";
    BPCXP35 BPCXP35 = new BPCXP35();
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
    BPRCHNTB BPRCHNTB = new BPRCHNTB();
    SCCGWA SCCGWA;
    SMB1640_AWA_1640 SMB1640_AWA_1640;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMOT1640 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB1640_AWA_1640>");
        SMB1640_AWA_1640 = (SMB1640_AWA_1640) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
        IBS.init(SCCGWA, SCCPRMM);
        IBS.init(SCCGWA, SCRPRMT);
        SCCPRMM.RC.RC_APP = "SM";
        SCCPRMM.DAT_PTR = SCRPRMT;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        if (SMB1640_AWA_1640.FUNC == 'I') {
            B100_READ_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1640_AWA_1640.FUNC == 'A') {
            B100_CHECK_INPUT();
            if (pgmRtn) return;
            B100_WRITE_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1640_AWA_1640.FUNC == 'M') {
            B100_CHECK_INPUT();
            if (pgmRtn) return;
            B100_READU_PROCESS();
            if (pgmRtn) return;
            B100_REWRITE_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1640_AWA_1640.FUNC == 'D') {
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
        if (SMB1640_AWA_1640.CHNL_AT != '0' 
            && SMB1640_AWA_1640.CHNL_AT != '1') {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1640_AWA_1640.CHNL_AT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (SMB1640_AWA_1640.STATUS != '0' 
            && SMB1640_AWA_1640.STATUS != '1') {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1640_AWA_1640.STATUS_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (SMB1640_AWA_1640.AU_FLG != '0' 
            && SMB1640_AWA_1640.AU_FLG != '1' 
            && SMB1640_AWA_1640.AU_FLG != '2') {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1640_AWA_1640.AU_FLG_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (SMB1640_AWA_1640.IS_24 != '0' 
            && SMB1640_AWA_1640.IS_24 != '1') {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1640_AWA_1640.IS_24_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
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
        SCRPRMT.KEY.TYP = SMB1640_AWA_1640.PTYP;
        SCRPRMT.KEY.CD = SMB1640_AWA_1640.CODE;
        SCCPRMM.EFF_DT = SMB1640_AWA_1640.EFFDATE;
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B100_READU_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '4';
        SCRPRMT.KEY.TYP = SMB1640_AWA_1640.PTYP;
        SCRPRMT.KEY.CD = SMB1640_AWA_1640.CODE;
        SCCPRMM.EFF_DT = SMB1640_AWA_1640.EFFDATE;
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B100_REWRITE_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '2';
        SCRPRMT.KEY.TYP = SMB1640_AWA_1640.PTYP;
        SCRPRMT.KEY.CD = SMB1640_AWA_1640.CODE;
        SCCPRMM.EFF_DT = SMB1640_AWA_1640.EFFDATE;
        SCCPRMM.EXP_DT = SMB1640_AWA_1640.EXPDATE;
        SCRPRMT.DESC = SMB1640_AWA_1640.DESC;
        SCRPRMT.CDESC = SMB1640_AWA_1640.CDESC;
        IBS.init(SCCGWA, BPRCHNTB.DATA_TXT);
        BPRCHNTB.DATA_TXT.CHNL_NM = SMB1640_AWA_1640.CHNL_NM;
        BPRCHNTB.DATA_TXT.CHNL_AT = SMB1640_AWA_1640.CHNL_AT;
        BPRCHNTB.DATA_TXT.STATUS = SMB1640_AWA_1640.STATUS;
        BPRCHNTB.DATA_TXT.AU_FLG = SMB1640_AWA_1640.AU_FLG;
        BPRCHNTB.DATA_TXT.IS_24 = SMB1640_AWA_1640.IS_24;
        BPRCHNTB.DATA_TXT.CUT_TM = SMB1640_AWA_1640.CUT_TM;
        BPRCHNTB.DATA_TXT.OPN_TM = SMB1640_AWA_1640.OPN_TM;
        BPRCHNTB.DATA_TXT.CLS_TM = SMB1640_AWA_1640.CLS_TM;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRCHNTB.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SCRPRMT.DAT_TXT);
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B100_DELETE_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '1';
        SCRPRMT.KEY.TYP = SMB1640_AWA_1640.PTYP;
        SCRPRMT.KEY.CD = SMB1640_AWA_1640.CODE;
        SCCPRMM.EFF_DT = SMB1640_AWA_1640.EFFDATE;
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B100_WRITE_PROCESS() throws IOException,SQLException,Exception {
        SCCPRMM.FUNC = '0';
        SCRPRMT.KEY.TYP = SMB1640_AWA_1640.PTYP;
        SCRPRMT.KEY.CD = SMB1640_AWA_1640.CODE;
        SCCPRMM.EFF_DT = SMB1640_AWA_1640.EFFDATE;
        SCCPRMM.EXP_DT = SMB1640_AWA_1640.EXPDATE;
        SCRPRMT.DESC = SMB1640_AWA_1640.DESC;
        SCRPRMT.CDESC = SMB1640_AWA_1640.CDESC;
        IBS.init(SCCGWA, BPRCHNTB.DATA_TXT);
        BPRCHNTB.DATA_TXT.CHNL_NM = SMB1640_AWA_1640.CHNL_NM;
        BPRCHNTB.DATA_TXT.CHNL_AT = SMB1640_AWA_1640.CHNL_AT;
        BPRCHNTB.DATA_TXT.STATUS = SMB1640_AWA_1640.STATUS;
        BPRCHNTB.DATA_TXT.AU_FLG = SMB1640_AWA_1640.AU_FLG;
        BPRCHNTB.DATA_TXT.IS_24 = SMB1640_AWA_1640.IS_24;
        BPRCHNTB.DATA_TXT.CUT_TM = SMB1640_AWA_1640.CUT_TM;
        BPRCHNTB.DATA_TXT.OPN_TM = SMB1640_AWA_1640.OPN_TM;
        BPRCHNTB.DATA_TXT.CLS_TM = SMB1640_AWA_1640.CLS_TM;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRCHNTB.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SCRPRMT.DAT_TXT);
        S000_CALL_SCZPRMM();
        if (pgmRtn) return;
    }
    public void B200_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCXP35);
        IBS.init(SCCGWA, SCCFMT);
        BPCXP35.FUNC = SMB1640_AWA_1640.FUNC;
        BPCXP35.TYPE = SCRPRMT.KEY.TYP;
        BPCXP35.CODE = SCRPRMT.KEY.CD;
        BPCXP35.EFF_DATE = SCCPRMM.EFF_DT;
        BPCXP35.EXP_DATE = SCCPRMM.EXP_DT;
        BPCXP35.DESC = SCRPRMT.DESC;
        BPCXP35.CDESC = SCRPRMT.CDESC;
        BPCXP35.FLAG = 0X02;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRCHNTB.DATA_TXT);
        BPCXP35.CHNL_NM = BPRCHNTB.DATA_TXT.CHNL_NM;
        BPCXP35.CFLAG = 0X02;
        BPCXP35.CHNL_AT = BPRCHNTB.DATA_TXT.CHNL_AT;
        BPCXP35.STATUS = BPRCHNTB.DATA_TXT.STATUS;
        BPCXP35.AU_FLG = BPRCHNTB.DATA_TXT.AU_FLG;
        BPCXP35.IS_24 = BPRCHNTB.DATA_TXT.IS_24;
        BPCXP35.CUT_TM = BPRCHNTB.DATA_TXT.CUT_TM;
        BPCXP35.OPN_TM = BPRCHNTB.DATA_TXT.OPN_TM;
        BPCXP35.CLS_TM = BPRCHNTB.DATA_TXT.CLS_TM;
        SCCFMT.FMTID = "BPP35";
        SCCFMT.DATA_PTR = BPCXP35;
        SCCFMT.DATA_LEN = 231;
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
