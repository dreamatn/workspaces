package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4873 {
    String CPN_S_RULE = "BP-S-MGM-RULE    ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSRULE BPCSRULE = new BPCSRULE();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB4870_AWA_4870 BPB4870_AWA_4870;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4873 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4870_AWA_4870>");
        BPB4870_AWA_4870 = (BPB4870_AWA_4870) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_ENQ_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_ENQ_REC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4870_AWA_4870.TX_CODE);
        CEP.TRC(SCCGWA, BPB4870_AWA_4870.CHNL_NO);
        CEP.TRC(SCCGWA, BPB4870_AWA_4870.SRV_CODE);
        CEP.TRC(SCCGWA, BPB4870_AWA_4870.RULE_NO);
        IBS.init(SCCGWA, BPCSRULE);
        BPCSRULE.FUNC = 'Q';
        BPCSRULE.KEY.TX_CODE = BPB4870_AWA_4870.TX_CODE;
        BPCSRULE.KEY.CHNL_NO = BPB4870_AWA_4870.CHNL_NO;
        BPCSRULE.KEY.SERV_CODE = BPB4870_AWA_4870.SRV_CODE;
        BPCSRULE.KEY.RULE_NO = BPB4870_AWA_4870.RULE_NO;
        S000_CALL_BPZSRULE();
    }
    public void S000_CALL_BPZSRULE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_RULE, BPCSRULE);
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
