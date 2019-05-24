package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5154 {
    String CPN_S_DEGR = "BP-DEF-EXR-GEN-RULE";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
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
    BPCDEGR BPCDEGR = new BPCDEGR();
    BPRPRMT BPRPRMT = new BPRPRMT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB5150_AWA_5150 BPB5150_AWA_5150;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5154 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5150_AWA_5150>");
        BPB5150_AWA_5150 = (BPB5150_AWA_5150) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        CEP.TRC(SCCGWA, BPB5150_AWA_5150);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_INQ_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_INQ_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCDEGR);
        BPCDEGR.FUNC = 'I';
        BPCDEGR.CCY = BPB5150_AWA_5150.CCY;
        CEP.TRC(SCCGWA, BPB5150_AWA_5150.CCY);
        BPCDEGR.RULE_DATA.DATA[1-1].SEQ = BPB5150_AWA_5150.RULE_DAT[1-1].SEQ;
        CEP.TRC(SCCGWA, "BPOT5154");
        CEP.TRC(SCCGWA, BPB5150_AWA_5150.CCY);
        CEP.TRC(SCCGWA, BPB5150_AWA_5150.RULE_DAT[1-1].SEQ);
        S00_CALL_BPZDEGR();
    }
    public void S00_CALL_BPZDEGR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_DEGR, BPCDEGR);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = BPRPRMT;
        IBS.CALLCPN(SCCGWA, CPN_INQ_PUB_PARM, BPCPRMR);
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = WS_CCY;
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            S000_ERR_MSG_PROC_CONTINUE();
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
