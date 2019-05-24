package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4870 {
    String CPN_S_RULE = "BP-S-MGM-RULE    ";
    String CPN_PARM_READ = "BP-PARM-READ        ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCSRULE BPCSRULE = new BPCSRULE();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCFTRT BPCFTRT = new BPCFTRT();
    SCCGWA SCCGWA;
    BPB4870_AWA_4870 BPB4870_AWA_4870;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "BPOT4870 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4870_AWA_4870>");
        BPB4870_AWA_4870 = (BPB4870_AWA_4870) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_BROWSE_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB4870_AWA_4870.TX_CODE.trim().length() > 0) {
            IBS.init(SCCGWA, BPCFTRT);
            IBS.init(SCCGWA, BPCPRMR);
            BPCFTRT.KEY.TYP = "TRT";
            BPCFTRT.KEY.CD = BPB4870_AWA_4870.TX_CODE;
            S000_CALL_BPZPRMR();
        }
    }
    public void B200_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSRULE);
        BPCSRULE.KEY.TX_CODE = BPB4870_AWA_4870.TX_CODE;
        BPCSRULE.FUNC = '1';
        CEP.TRC(SCCGWA, BPB4870_AWA_4870.TX_CODE);
        S000_CALL_BPZSRULE();
    }
    public void S000_CALL_BPZSRULE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_RULE, BPCSRULE);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = BPCFTRT;
        IBS.CALLCPN(SCCGWA, CPN_PARM_READ, BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            WS_FLD_NO = BPB4870_AWA_4870.TX_CODE_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
