package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4920 {
    String CPN_F_K_PSW_MAINTAIN = "BP-F-K-PSW-MAINTAIN ";
    String K_OUTPUT_FMT = "BP555";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFKPSW BPCFKPSW = new BPCFKPSW();
    BPCOJNLL BPCOJNLL = new BPCOJNLL();
    SCCGWA SCCGWA;
    BPB4920_AWA_4920 BPB4920_AWA_4920;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4920 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4920_AWA_4920>");
        BPB4920_AWA_4920 = (BPB4920_AWA_4920) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4920_AWA_4920.TLR);
        B010_CHECK_INPUT();
        B020_PASSWORD_MODIFY();
        B030_OUTPUT_DATA();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4920_AWA_4920.TLR);
        CEP.TRC(SCCGWA, BPB4920_AWA_4920.PSW);
        CEP.TRC(SCCGWA, BPB4920_AWA_4920.NEW_PSW);
        CEP.TRC(SCCGWA, BPB4920_AWA_4920.CFM_PSW);
        if (BPB4920_AWA_4920.TLR.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            WS_FLD_NO = BPB4920_AWA_4920.TLR_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB4920_AWA_4920.NEW_PSW.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NEW_PSW_MUST_INPUT;
            WS_FLD_NO = BPB4920_AWA_4920.NEW_PSW_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB4920_AWA_4920.CFM_PSW.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PASSWORD_INPUT_ERROR;
            WS_FLD_NO = BPB4920_AWA_4920.CFM_PSW_NO;
            S000_ERR_MSG_PROC();
        }
        if (!BPB4920_AWA_4920.NEW_PSW.equalsIgnoreCase(BPB4920_AWA_4920.CFM_PSW)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PSWD_NOT_MATCH;
            WS_FLD_NO = BPB4920_AWA_4920.CFM_PSW_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB4920_AWA_4920.NEW_PSW.equalsIgnoreCase(BPB4920_AWA_4920.PSW)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SAME_OLD_PSWD;
            WS_FLD_NO = BPB4920_AWA_4920.NEW_PSW_NO;
            S000_ERR_MSG_PROC();
        }
        if (!BPB4920_AWA_4920.TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_TR_TELLER;
            WS_FLD_NO = BPB4920_AWA_4920.TLR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_PASSWORD_MODIFY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFKPSW);
        BPCFKPSW.OPT = 'M';
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZFKPSW();
    }
    public void B030_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOJNLL);
        IBS.init(SCCGWA, SCCFMT);
        BPCOJNLL.TLR = BPB4920_AWA_4920.TLR;
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOJNLL;
        SCCFMT.DATA_LEN = 68;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCFKPSW.TLR = BPB4920_AWA_4920.TLR;
        BPCFKPSW.OLD_PSW = BPB4920_AWA_4920.PSW;
        BPCFKPSW.NEW_PSW = BPB4920_AWA_4920.NEW_PSW;
        BPCFKPSW.NEW_PSW2 = BPB4920_AWA_4920.CFM_PSW;
    }
    public void S000_CALL_BPZFKPSW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_F_K_PSW_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCFKPSW;
        SCCCALL.ERR_FLDNO = BPB4920_AWA_4920.TLR;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCFKPSW.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFKPSW.RC);
            WS_FLD_NO = BPB4920_AWA_4920.TLR_NO;
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
