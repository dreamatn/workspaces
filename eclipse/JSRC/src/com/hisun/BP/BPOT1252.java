package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1252 {
    String CPN_F_FEE_HIS_DETAIL = "BP-F-FEE-HIS-DETAIL";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCBINF SCCBINF = new SCCBINF();
    BPCFFEDT BPCFFEDT = new BPCFFEDT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB1250_AWA_1250 BPB1250_AWA_1250;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1252 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1250_AWA_1250>");
        BPB1250_AWA_1250 = (BPB1250_AWA_1250) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_INQ_HIST_DETAIL();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_INQ_HIST_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFEDT);
        BPCFFEDT.FUNC = 'I';
        CEP.TRC(SCCGWA, BPCFFEDT.FUNC);
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZFFEDT();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCFFEDT.TX_DT = BPB1250_AWA_1250.TX_DT;
        BPCFFEDT.JRN_NO = BPB1250_AWA_1250.JRN_NO;
        BPCFFEDT.JRN_SEQ = BPB1250_AWA_1250.JRN_SEQ;
    }
    public void S000_CALL_BPZFFEDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_FEE_HIS_DETAIL, BPCFFEDT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
