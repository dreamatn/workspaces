package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT8004 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP085";
    String CPN_RM_FEE_CAL = "BP-F-S-RM-FEE-CAL";
    String CPN_SET_TX_INF = "BP-F-F-TX-INFO";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT1 = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCORTS2 BPCORTS2 = new BPCORTS2();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    SCCGWA SCCGWA;
    BPB8003_AWA_8003 BPB8003_AWA_8003;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCGPFEE BPCGPFEE;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT8004 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB8003_AWA_8003>");
        BPB8003_AWA_8003 = (BPB8003_AWA_8003) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCGPFEE = (BPCGPFEE) GWA_BP_AREA.FEE_AREA.FEE_PARM_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B015_SET_TX_CHG_INFO();
        B020_RM_FEE_CALCULATE();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B015_SET_TX_CHG_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = BPB8003_AWA_8003.CHG_AC;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = BPB8003_AWA_8003.CHG_CCY;
        S000_CALL_BPZFFTXI();
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA.CHG_AC_INFO.AC_TYP);
    }
    public void B020_RM_FEE_CALCULATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCORTS2);
        BPCORTS2.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCORTS2.DATA.REMT_CCY = BPB8003_AWA_8003.REMT_CCY;
        BPCORTS2.DATA.REMT_AMT = BPB8003_AWA_8003.REMT_AMT;
        BPCORTS2.DATA.AC_NO1 = BPB8003_AWA_8003.AC_NO1;
        BPCORTS2.DATA.AC_AMT1 = BPB8003_AWA_8003.AC_AMT1;
        BPCORTS2.DATA.CASH_AMT = BPB8003_AWA_8003.CASH_AMT;
        BPCORTS2.DATA.TRF_AMT = BPB8003_AWA_8003.TRF_AMT;
        BPCORTS2.DATA.CHG_CCY = BPB8003_AWA_8003.CHG_CCY;
        BPCORTS2.DATA.CHG_AC = BPB8003_AWA_8003.CHG_AC;
        BPCORTS2.DATA.C_AC_AMT = BPB8003_AWA_8003.C_AC_AMT;
        BPCORTS2.DATA.C_CS_AMT = BPB8003_AWA_8003.C_CS_AMT;
        BPCORTS2.DATA.C_TR_AMT = BPB8003_AWA_8003.C_TR_AMT;
        BPCORTS2.DATA.CPNT_ID = BPB8003_AWA_8003.CPNT_ID;
        BPCORTS2.DATA.PROD_CD = BPB8003_AWA_8003.PROD_CD;
        BPCORTS2.DATA.DER_CODE = BPB8003_AWA_8003.DER_CODE;
        CEP.TRC(SCCGWA, BPB8003_AWA_8003.CPNT_ID);
        S000_CALL_BPZFRTS2();
    }
    public void S000_CALL_BPZFRTS2() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_RM_FEE_CAL, BPCORTS2);
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_SET_TX_INF, BPCFFTXI);
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
