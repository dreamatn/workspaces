package com.hisun.SM;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class SMOT1367 {
    String K_OUTPUT_FMT = "SMX01";
    String CPN_DCHK_MAINTAIN = "SM-S_DCHK_MAINTAIN  ";
    String CPN_DDIC_MAINTAIN = "SM-S_DDIC_MAINTAIN  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SMCOCHKM SMCOCHKM = new SMCOCHKM();
    SMCODICM SMCODICM = new SMCODICM();
    SCCGWA SCCGWA;
    SMB1350_AWA_1350 SMB1350_AWA_1350;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "SMOT1367 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB1350_AWA_1350>");
        SMB1350_AWA_1350 = (SMB1350_AWA_1350) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_MODIFY_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SMCODICM);
        SMCODICM.FUNC = 'I';
        SMCODICM.NAME = SMB1350_AWA_1350.NAME;
        S000_CALL_SMZDDICM();
        if (SMCODICM.DEC_PNT != ' ' 
            && SMCODICM.DEC_PNT != '0') {
            WS_ERR_MSG = SMCMSG_ERROR_MSG.SM_DDIC_HAVE_DEC;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_MODIFY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SMCOCHKM);
        SMCOCHKM.FUNC = 'U';
        SMCOCHKM.SUB_FUNC = SMB1350_AWA_1350.SUB_FUNC;
        SMCOCHKM.OUTPUT_FMT = K_OUTPUT_FMT;
        SMCOCHKM.NAME = SMB1350_AWA_1350.NAME;
        SMCOCHKM.FMTNO = SMB1350_AWA_1350.FMTNO;
        SMCOCHKM.OPT = SMB1350_AWA_1350.OPT;
        SMCOCHKM.REMARK = SMB1350_AWA_1350.REMARK;
        SMCOCHKM.DATA.DATA_LEN = 8400;
        SMCOCHKM.REDEFINES22.REDEFINES25.ITM_TEXT[1-1].ITM_NO = SMB1350_AWA_1350.ITM_NO;
        SMCOCHKM.REDEFINES22.DATA_TEXT1 = IBS.CLS2CPY(SCCGWA, SMCOCHKM.REDEFINES22.REDEFINES25);
        SMCOCHKM.REDEFINES22.REDEFINES25.ITM_TEXT[1-1].ITM_TYPE = SMB1350_AWA_1350.ITM_TYPE;
        SMCOCHKM.REDEFINES22.DATA_TEXT1 = IBS.CLS2CPY(SCCGWA, SMCOCHKM.REDEFINES22.REDEFINES25);
        SMCOCHKM.REDEFINES22.REDEFINES25.ITM_TEXT[1-1].ITM_LTH = SMB1350_AWA_1350.ITM_LTH;
        SMCOCHKM.REDEFINES22.DATA_TEXT1 = IBS.CLS2CPY(SCCGWA, SMCOCHKM.REDEFINES22.REDEFINES25);
        SMCOCHKM.REDEFINES22.REDEFINES25.ITM_TEXT[1-1].ITM_DATA = SMB1350_AWA_1350.ITM_DATA;
        SMCOCHKM.REDEFINES22.DATA_TEXT1 = IBS.CLS2CPY(SCCGWA, SMCOCHKM.REDEFINES22.REDEFINES25);
        SMCOCHKM.REDEFINES22.REDEFINES25.ITM_TEXT[1-1].ITM_ABBR = SMB1350_AWA_1350.ITM_ABBR;
        SMCOCHKM.REDEFINES22.DATA_TEXT1 = IBS.CLS2CPY(SCCGWA, SMCOCHKM.REDEFINES22.REDEFINES25);
        SMCOCHKM.REDEFINES22.REDEFINES25.ITM_TEXT[1-1].ITM_RMK = SMB1350_AWA_1350.ITM_RMK;
        SMCOCHKM.REDEFINES22.DATA_TEXT1 = IBS.CLS2CPY(SCCGWA, SMCOCHKM.REDEFINES22.REDEFINES25);
        SMCOCHKM.REDEFINES22.REDEFINES25.ITM_TEXT[1-1].ITM_CRMK = SMB1350_AWA_1350.ITM_CRMK;
        SMCOCHKM.REDEFINES22.DATA_TEXT1 = IBS.CLS2CPY(SCCGWA, SMCOCHKM.REDEFINES22.REDEFINES25);
        SMCOCHKM.UPD_DATE = SCCGWA.COMM_AREA.TR_DATE;
        SMCOCHKM.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_SMZDCHKM();
    }
    public void S000_CALL_SMZDDICM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DDIC_MAINTAIN, SMCODICM, true);
        if (SMCODICM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, SMCODICM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_SMZDCHKM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCHK_MAINTAIN, SMCOCHKM);
        if (SMCOCHKM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, SMCOCHKM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}