package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1197 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP080";
    String CPN_GET_DER_LIMIT = "BP-F-S-GET-DER-LIMIT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT1 = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOFDER BPCOFDER = new BPCOFDER();
    SCCGWA SCCGWA;
    BPB1115_AWA_1115 BPB1115_AWA_1115;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1197 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1115_AWA_1115>");
        BPB1115_AWA_1115 = (BPB1115_AWA_1115) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_GET_DER_LIMIT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB1115_AWA_1115.DER_CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FDER_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_GET_DER_LIMIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFDER);
        BPCOFDER.OUTPUT_FMT = K_OUTPUT_FMT;
        CEP.TRC(SCCGWA, BPB1115_AWA_1115.DER_CODE);
        BPCOFDER.INFO.DER_CODE = BPB1115_AWA_1115.DER_CODE;
        for (WS_CNT1 = 1; WS_CNT1 <= 20; WS_CNT1 += 1) {
            CEP.TRC(SCCGWA, BPB1115_AWA_1115.FEE_INFO[WS_CNT1-1].FEE_CODE);
            CEP.TRC(SCCGWA, BPB1115_AWA_1115.FEE_INFO[WS_CNT1-1].CHG_CCY);
            CEP.TRC(SCCGWA, BPB1115_AWA_1115.FEE_INFO[WS_CNT1-1].CHG_AMT);
            if (BPB1115_AWA_1115.FEE_INFO[WS_CNT1-1].FEE_CODE.trim().length() > 0 
                && !BPB1115_AWA_1115.FEE_INFO[WS_CNT1-1].FEE_CODE.equalsIgnoreCase("0") 
                && BPB1115_AWA_1115.FEE_INFO[WS_CNT1-1].FEE_CODE.charAt(0) != 0X00) {
                BPCOFDER.INFO.DATA[WS_CNT1-1].FEE_CODE = BPB1115_AWA_1115.FEE_INFO[WS_CNT1-1].FEE_CODE;
                BPCOFDER.INFO.DATA[WS_CNT1-1].CHG_AMT = BPB1115_AWA_1115.FEE_INFO[WS_CNT1-1].CHG_AMT;
                BPCOFDER.INFO.DATA[WS_CNT1-1].CHG_CCY = BPB1115_AWA_1115.FEE_INFO[WS_CNT1-1].CHG_CCY;
            }
        }
        S000_CALL_BPZFSDER();
    }
    public void S000_CALL_BPZFSDER() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_GET_DER_LIMIT, BPCOFDER);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
