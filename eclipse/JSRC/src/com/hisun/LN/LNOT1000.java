package com.hisun.LN;

import com.hisun.SM.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT1000 {
    String JIBS_tmp_str[] = new String[10];
    char K_ERROR = 'E';
    char K_CHN_FILLER = 0X02;
    String K_MDLPM_CODE = "LN";
    LNOT1000_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNOT1000_WS_TEMP_VARIABLE();
    char WS_FUNC_CHOICE = ' ';
    LNCX755 LNCX755 = new LNCX755();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCMDLPM LNCMDLPM = new LNCMDLPM();
    SCCGWA SCCGWA;
    LNB1000_AWA_1000 LNB1000_AWA_1000;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "LNOT1000 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB1000_AWA_1000>");
        LNB1000_AWA_1000 = (LNB1000_AWA_1000) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_CALL_LNZMDLPM();
        B300_OUTPUT_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_FUNC_CHOICE = LNB1000_AWA_1000.FUNC;
        if ((WS_FUNC_CHOICE != 'I' 
            && WS_FUNC_CHOICE != 'A' 
            && WS_FUNC_CHOICE != 'M' 
            && WS_FUNC_CHOICE != 'D')) {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = LNB1000_AWA_1000.FUNC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        R00_CHECK_ERROR();
    }
    public void B200_CALL_LNZMDLPM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCMDLPM);
        LNCMDLPM.FUNC = LNB1000_AWA_1000.FUNC;
        LNCMDLPM.KEY.TYPE = LNB1000_AWA_1000.PTYP;
        LNCMDLPM.KEY.CODE = LNB1000_AWA_1000.CODE;
        LNCMDLPM.EFF_DATE = LNB1000_AWA_1000.EFFDATE;
        LNCMDLPM.EXP_DATE = LNB1000_AWA_1000.EXPDATE;
        LNCMDLPM.DATA_LEN = LNB1000_AWA_1000.LEN;
        LNCMDLPM.DESC = LNB1000_AWA_1000.DESC;
        LNCMDLPM.CDESC = LNB1000_AWA_1000.CDESC;
        LNCMDLPM.DATA_TXT.INS_FLG = LNB1000_AWA_1000.INS_FLG;
        LNCMDLPM.DATA_TXT.NSI_DAYS = LNB1000_AWA_1000.NSI_DAYS;
        LNCMDLPM.DATA_TXT.DC_DAYS = LNB1000_AWA_1000.DC_DAYS;
        S000_CALL_LNZMDLPM();
    }
    public void B300_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCX755);
        IBS.init(SCCGWA, SCCFMT);
        LNCX755.TYPE = LNCMDLPM.KEY.TYPE;
        LNCX755.CODE = LNCMDLPM.KEY.CODE;
        LNCX755.DESC = LNCMDLPM.DESC;
        LNCX755.CDESC = LNCMDLPM.CDESC;
        LNCX755.EFF_DATE = LNCMDLPM.EFF_DATE;
        LNCX755.EXP_DATE = LNCMDLPM.EXP_DATE;
        LNCX755.INS_FLG = LNCMDLPM.DATA_TXT.INS_FLG;
        LNCX755.NSI_DAYS = LNCMDLPM.DATA_TXT.NSI_DAYS;
        LNCX755.DC_DAYS = LNCMDLPM.DATA_TXT.DC_DAYS;
        CEP.TRC(SCCGWA, LNCMDLPM.EFF_DATE);
        CEP.TRC(SCCGWA, LNCMDLPM.EXP_DATE);
        CEP.TRC(SCCGWA, LNCX755.EFF_DATE);
        CEP.TRC(SCCGWA, LNCX755.EXP_DATE);
        SCCFMT.FMTID = "LN100";
        SCCFMT.DATA_PTR = LNCX755;
        SCCFMT.DATA_LEN = 116;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R00_CHECK_ERROR() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE == 0) {
            CEP.TRC(SCCGWA, "LAST STEP CHECK REJECT.");
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZMDLPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-PRM-MDLP-MAINT", LNCMDLPM);
        CEP.TRC(SCCGWA, LNCMDLPM);
        if (LNCMDLPM.RC.RC_RTNCODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_AP = LNCMDLPM.RC.RC_APP;
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_CODE = LNCMDLPM.RC.RC_RTNCODE;
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
