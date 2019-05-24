package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT5810 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    AIOT5810_WS_OUTPUT WS_OUTPUT = new AIOT5810_WS_OUTPUT();
    AIOT5810_WS_BUSICODE WS_BUSICODE = new AIOT5810_WS_BUSICODE();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    AICSMIB AICSMIB = new AICSMIB();
    BPCPQORG BPCPQORG = new BPCPQORG();
    AICPQITM AICPQITM = new AICPQITM();
    AICPQIA AICPQIA = new AICPQIA();
    AIRPAI11 AIRPAI11 = new AIRPAI11();
    BPCPRMM BPCPRMM = new BPCPRMM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB5810_AWA_5810 AIB5810_AWA_5810;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT5810 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB5810_AWA_5810>");
        AIB5810_AWA_5810 = (AIB5810_AWA_5810) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BRW_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIB5810_AWA_5810.AC_TYPE);
        if (!AIB5810_AWA_5810.AC_TYPE.equalsIgnoreCase("1") 
            && !AIB5810_AWA_5810.AC_TYPE.equalsIgnoreCase("2")) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, AIB5810_AWA_5810.BUSICODE);
        if (AIB5810_AWA_5810.BUSICODE.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, AIB5810_AWA_5810.CCY);
        if (AIB5810_AWA_5810.CCY.trim().length() == 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (AIB5810_AWA_5810.SIGN == ' ') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_BRW_REC_PROC() throws IOException,SQLException,Exception {
        if (AIB5810_AWA_5810.AC_TYPE.equalsIgnoreCase("2")) {
            IBS.init(SCCGWA, AICPQIA);
            if (AIB5810_AWA_5810.SIGN == 'C') {
                AICPQIA.SIGN = 'C';
            } else {
                AICPQIA.SIGN = 'D';
            }
            AICPQIA.CD.AC_TYP = AIB5810_AWA_5810.AC_TYPE;
            AICPQIA.CD.BUSI_KND = AIB5810_AWA_5810.BUSICODE;
            AICPQIA.CCY = AIB5810_AWA_5810.CCY;
            AICPQIA.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            CEP.TRC(SCCGWA, AICPQIA.CD.AC_TYP);
            CEP.TRC(SCCGWA, AICPQIA.CD.BUSI_KND);
            CEP.TRC(SCCGWA, AICPQIA.CCY);
            CEP.TRC(SCCGWA, AIB5810_AWA_5810.SIGN);
            CEP.TRC(SCCGWA, AICPQIA.SIGN);
            S00_CALL_AIPINQIA();
        } else {
            B020_QUERY_RECORD_PROC();
        }
        R000_DATA_OUTPUT();
    }
    public void S00_CALL_AIPINQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        if (AICPQIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void B020_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, AIRPAI11);
        AIRPAI11.KEY.TYP = "PAI11";
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        WS_BUSICODE.WS_CODE = AIB5810_AWA_5810.BUSICODE;
        WS_BUSICODE.WS_CCY = AIB5810_AWA_5810.CCY;
        AIRPAI11.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_BUSICODE);
        IBS.CPY2CLS(SCCGWA, AIRPAI11.KEY.CD, AIRPAI11.KEY.REDEFINES5);
        BPCPRMM.FUNC = '3';
        S000_CALL_BPZPRMM();
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        AIRPAI11.VAL_LEN = 32;
        BPCPRMM.DAT_PTR = AIRPAI11;
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        if (AIB5810_AWA_5810.AC_TYPE.equalsIgnoreCase("2")) {
            WS_OUTPUT.WS_AC = AICPQIA.AC;
        } else {
            WS_OUTPUT.WS_AC = AIRPAI11.DATA_TXT.IN_AC;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "AI810";
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 32;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
