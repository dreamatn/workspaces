package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4930 {
    int JIBS_tmp_int;
    String CPN_TLR_SIGN_ON = "BP-S-TLR-SIGN-ON    ";
    String CPN_RET_TLPE = "BP-S-CHK-VB-TIMES";
    String CPN_RET_FTLRQ = "BP-F-TLR-INF-QUERY";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSTLSO BPCSTLSO = new BPCSTLSO();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCSTLPE BPCSTLPE = new BPCSTLPE();
    SCCGWA SCCGWA;
    BPB4930_AWA_4930 BPB4930_AWA_4930;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4930 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4930_AWA_4930>");
        BPB4930_AWA_4930 = (BPB4930_AWA_4930) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TELLER_SIGN_ON();
        B011_CHK_LAST_TIME();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4930_AWA_4930.TLR);
        CEP.TRC(SCCGWA, BPB4930_AWA_4930.PSW);
        CEP.TRC(SCCGWA, BPB4930_AWA_4930.NEW_PSW);
        CEP.TRC(SCCGWA, BPB4930_AWA_4930.CFM_PSW);
        CEP.TRC(SCCGWA, BPB4930_AWA_4930.TLR_LVL);
        CEP.TRC(SCCGWA, BPB4930_AWA_4930.TX_LVL);
        CEP.TRC(SCCGWA, BPB4930_AWA_4930.AUTH_LVL);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SUP1_ID);
        CEP.TRC(SCCGWA, BPB4930_AWA_4930.DEV_ID);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        if (BPB4930_AWA_4930.TLR.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            WS_FLD_NO = BPB4930_AWA_4930.TLR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B011_CHK_LAST_TIME() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        IBS.init(SCCGWA, BPCSTLPE);
        BPCFTLRQ.INFO.TLR = BPB4930_AWA_4930.TLR;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            BPCSTLPE.TX_DATA.TLR = BPCFTLRQ.INFO.TLR;
            S000_CALL_BPZSTLPE();
        }
    }
    public void B020_TELLER_SIGN_ON() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSTLSO);
        BPCSTLSO.OPT = '0';
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSTLSO();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCSTLSO.TLR = BPB4930_AWA_4930.TLR;
        BPCSTLSO.OLD_PSW = BPB4930_AWA_4930.PSW;
        BPCSTLSO.NEW_PSW = BPB4930_AWA_4930.NEW_PSW;
        BPCSTLSO.CFM_PSW = BPB4930_AWA_4930.CFM_PSW;
        BPCSTLSO.TLR_LVL = BPB4930_AWA_4930.TLR_LVL;
        BPCSTLSO.TX_LVL = BPB4930_AWA_4930.TX_LVL;
        BPCSTLSO.AUTH_LVL = BPB4930_AWA_4930.AUTH_LVL;
    }
    public void S000_CALL_BPZSTLSO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_TLR_SIGN_ON;
        SCCCALL.COMMAREA_PTR = BPCSTLSO;
        SCCCALL.ERR_FLDNO = BPB4930_AWA_4930.TLR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_BPZSTLPE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_RET_TLPE, BPCSTLPE);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_RET_FTLRQ, BPCFTLRQ);
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
