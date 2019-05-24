package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2200 {
    int JIBS_tmp_int;
    String K_OUTPUT_FMT = "BPX01";
    char K_STSW_FLG_Y = '1';
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_P_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    String CPN_BP_P_INQ_ORG = "BP-P-INQ-ORG";
    String CPN_BP_S_CLOSE_BALANCE = "BP-S-CLOSE-BALANCE  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_IDX = 0;
    int WS_BR = 0;
    BPOT2200_WS_FMT_DATA WS_FMT_DATA = new BPOT2200_WS_FMT_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCSCLBL BPCSCLBL = new BPCSCLBL();
    SCCGWA SCCGWA;
    BPB2201_AWA_2201 BPB2201_AWA_2201;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2200 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2201_AWA_2201>");
        BPB2201_AWA_2201 = (BPB2201_AWA_2201) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2201_AWA_2201.VB_FLG);
        CEP.TRC(SCCGWA, BPB2201_AWA_2201.CCY);
        CEP.TRC(SCCGWA, BPB2201_AWA_2201.BAL_FLG);
        B100_CHECK_INPUT();
        B200_BROWSE_BOX_LIB_INFO();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPB2201_AWA_2201.TLR;
        S000_CALL_BPZFTLRQ();
        WS_BR = BPCFTLRQ.INFO.NEW_BR;
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_STSW);
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1));
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1));
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"") 
            && !BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CSHBOX_OR_LIBTLR;
            S000_ERR_MSG_PROC();
        }
        if (!BPB2201_AWA_2201.TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            if (WS_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_SAME_WITH_TLRBR;
                S000_ERR_MSG_PROC();
            }
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_IMPOWER;
            WS_FLD_NO = BPB2201_AWA_2201.TLR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_BROWSE_BOX_LIB_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSCLBL);
        BPCSCLBL.FUN = '0';
        BPCSCLBL.PLBOX_TP = BPB2201_AWA_2201.VB_FLG;
        BPCSCLBL.BRANCH = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCSCLBL.TLR = BPB2201_AWA_2201.TLR;
        CEP.TRC(SCCGWA, BPCSCLBL.PLBOX_TP);
        CEP.TRC(SCCGWA, BPCSCLBL.BRANCH);
        CEP.TRC(SCCGWA, BPCSCLBL.TLR);
        S000_CALL_BPZSCLBL();
    }
    public void S000_CALL_BPZSCLBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_S_CLOSE_BALANCE, BPCSCLBL);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
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
