package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5083 {
    String CPN_S_EXPT = "BP-DEF-EXPT      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String CPN_INQ_PUB_CODE = "BP-P-INQ-PC       ";
    String WS_CCY = " ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPREXRT BPREXRT = new BPREXRT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCEXPT BPCEXPT = new BPCEXPT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB5083_AWA_5083 BPB5083_AWA_5083;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5083 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5083_AWA_5083>");
        BPB5083_AWA_5083 = (BPB5083_AWA_5083) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_INQUIRE_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREXRT);
        IBS.init(SCCGWA, BPCPRMR);
        BPREXRT.KEY.TYP = "EXRT";
        BPREXRT.KEY.CD = BPB5083_AWA_5083.EXR_TYP;
        S000_CALL_BPZPRMR();
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_EXR_TYPE;
            WS_FLD_NO = BPB5083_AWA_5083.EXR_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_CCY = BPB5083_AWA_5083.CCY;
        WS_FLD_NO = BPB5083_AWA_5083.CCY_NO;
        R000_CHECK_CCY();
        CEP.TRC(SCCGWA, BPREXRT.DAT_TXT.BASE_CCY);
        if (BPREXRT.DAT_TXT.BASE_CCY.equalsIgnoreCase(BPB5083_AWA_5083.CCY)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_NOT_DEF_EX_CCY;
            WS_FLD_NO = BPB5083_AWA_5083.CCY_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_INQUIRE_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCEXPT);
        BPCEXPT.FUNC = 'I';
        BPCEXPT.BR = BPB5083_AWA_5083.BR;
        BPCEXPT.EXR_TYP = BPB5083_AWA_5083.EXR_TYP;
        BPCEXPT.CCY = BPB5083_AWA_5083.CCY;
        S000_CALL_BPZEXPT();
    }
    public void S000_CALL_BPZEXPT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_EXPT, BPCEXPT);
        if (BPCEXPT.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCEXPT.RC);
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = BPREXRT;
        IBS.CALLCPN(SCCGWA, CPN_INQ_PUB_PARM, BPCPRMR);
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = WS_CCY;
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
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
