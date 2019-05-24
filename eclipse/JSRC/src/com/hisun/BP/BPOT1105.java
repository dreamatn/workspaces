package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1105 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT1 = "BPZ01";
    String K_OUTPUT_FMT2 = "BP051";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT1 = 0;
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOMFEE BPCOMFEE = new BPCOMFEE();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCOFBAS BPCOFBAS = new BPCOFBAS();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPB1116_AWA_1116 BPB1116_AWA_1116;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1105 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1116_AWA_1116>");
        BPB1116_AWA_1116 = (BPB1116_AWA_1116) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B015_SET_NXT_TXN();
        B020_QUERY_BAS_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B015_SET_NXT_TXN() throws IOException,SQLException,Exception {
    }
    public void B020_QUERY_BAS_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.FEE_CD);
        CEP.TRC(SCCGWA, BPB1116_AWA_1116.FUNC);
        if (BPB1116_AWA_1116.FUNC != 'O') {
            IBS.init(SCCGWA, BPCOMFEE);
            BPCOMFEE.FEE_CODE = BPB1116_AWA_1116.FEE_CD;
            BPCOMFEE.FUNC = BPB1116_AWA_1116.FUNC;
            CEP.TRC(SCCGWA, BPCOMFEE.FEE_CODE);
            S000_CALL_BPZSMFEE();
        } else {
            IBS.init(SCCGWA, BPCOFBAS);
            BPCOFBAS.FUNC = 'I';
            BPCOFBAS.OUTPUT_FMT = K_OUTPUT_FMT2;
            BPCOFBAS.KEY.FEE_CODE = BPB1116_AWA_1116.FEE_CD;
            S000_CALL_BPZFSBAS();
        }
    }
    public void S000_CALL_BPZSMFEE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "BP-F-MAIN-FEE";
        SCCCALL.COMMAREA_PTR = BPCOMFEE;
        SCCCALL.ERR_FLDNO = BPB1116_AWA_1116.FEE_CD_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_BPZFSBAS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "BP-F-S-MAINTAIN-FBAS";
        SCCCALL.COMMAREA_PTR = BPCOFBAS;
        SCCCALL.ERR_FLDNO = BPB1116_AWA_1116.FEE_CD;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "SC-SET-SUBS-TRANS";
        SCCCALL.COMMAREA_PTR = SCCSUBS;
        SCCCALL.ERR_FLDNO = BPB1116_AWA_1116.FUNC_NO;
        IBS.CALL(SCCGWA, SCCCALL);
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
