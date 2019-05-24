package com.hisun.LN;

import com.hisun.SM.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT7050 {
    String JIBS_tmp_str[] = new String[10];
    char K_ERROR = 'E';
    LNOT7050_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNOT7050_WS_TEMP_VARIABLE();
    short WS_I = 0;
    LNCX705 LNCX705 = new LNCX705();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCIRULM LNCIRULM = new LNCIRULM();
    SCCGWA SCCGWA;
    LNB7050_AWA_7050 LNB7050_AWA_7050;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "LNOT7050 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB7050_AWA_7050>");
        LNB7050_AWA_7050 = (LNB7050_AWA_7050) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_CALL_LNZIRULM();
        B300_OUTPUT_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB7050_AWA_7050.FUNC != 'I' 
            && LNB7050_AWA_7050.FUNC != 'A' 
            && LNB7050_AWA_7050.FUNC != 'M' 
            && LNB7050_AWA_7050.FUNC != 'D') {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, LNCIRULM.RC);
            WS_TEMP_VARIABLE.WS_FLD_NO = LNB7050_AWA_7050.FUNC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        R00_CHECK_ERROR();
    }
    public void R00_CHECK_ERROR() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE == 0) {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_CALL_LNZIRULM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCIRULM);
        LNCIRULM.FUNC = LNB7050_AWA_7050.FUNC;
        LNCIRULM.KEY.TYP = LNB7050_AWA_7050.PTYP;
        LNCIRULM.KEY.CD = LNB7050_AWA_7050.CODE;
        LNCIRULM.EFF_DATE = LNB7050_AWA_7050.EFFDATE;
        LNCIRULM.EXP_DATE = LNB7050_AWA_7050.EXPDATE;
        LNCIRULM.DESC = LNB7050_AWA_7050.DESC;
        LNCIRULM.CDESC = LNB7050_AWA_7050.CDESC;
        LNCIRULM.DATA_TXT.VLDT_FLG = LNB7050_AWA_7050.VLDT_FLG;
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            LNCIRULM.DATA_TXT.FROM_CNTR[WS_I-1].FROM_NO = LNB7050_AWA_7050.FRM_CNTR[WS_I-1].FROM_NO;
        }
        LNCIRULM.DATA_TXT.RATE_TYP = LNB7050_AWA_7050.RATE_TYP;
        LNCIRULM.DATA_TXT.INT_FML = LNB7050_AWA_7050.INT_FML;
        LNCIRULM.DATA_TXT.TO_NO = LNB7050_AWA_7050.TO_NO;
        S000_CALL_LNZIRULM();
    }
    public void B300_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCX705);
        IBS.init(SCCGWA, SCCFMT);
        LNCX705.FUNC = LNCIRULM.FUNC;
        LNCX705.TYPE = LNCIRULM.KEY.TYP;
        LNCX705.CODE = LNCIRULM.KEY.CD;
        LNCX705.DESC = LNCIRULM.DESC;
        LNCX705.CDESC = LNCIRULM.CDESC;
        LNCX705.EFF_DATE = LNCIRULM.EFF_DATE;
        CEP.TRC(SCCGWA, LNCIRULM.EFF_DATE);
        CEP.TRC(SCCGWA, LNCX705.EXP_DATE);
        CEP.TRC(SCCGWA, LNB7050_AWA_7050.EXPDATE);
        LNCX705.EXP_DATE = LNCIRULM.EXP_DATE;
        LNCX705.VLDT_FLG = LNCIRULM.DATA_TXT.VLDT_FLG;
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            LNCX705.FROM_CNTR[WS_I-1].FROM_NO = LNCIRULM.DATA_TXT.FROM_CNTR[WS_I-1].FROM_NO;
        }
        LNCX705.RATE_TYP = LNCIRULM.DATA_TXT.RATE_TYP;
        LNCX705.INT_FML = LNCIRULM.DATA_TXT.INT_FML;
        LNCX705.TO_NO = LNCIRULM.DATA_TXT.TO_NO;
        SCCFMT.FMTID = "LN705";
        SCCFMT.DATA_PTR = LNCX705;
        SCCFMT.DATA_LEN = 174;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_LNZIRULM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCIRULM);
        IBS.CALLCPN(SCCGWA, "LN-PRM-IRUL-MAINT", LNCIRULM);
        CEP.TRC(SCCGWA, LNCIRULM);
        if (LNCIRULM.RC.RC_RTNCODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_AP = LNCIRULM.RC.RC_APP;
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_CODE = LNCIRULM.RC.RC_RTNCODE;
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
