package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3270 {
    int JIBS_tmp_int;
    String K_OUTPUT_FMT = "BP179";
    String CPN_S_BL_DESTROY = "BP-S-BL-DESTROY  ";
    String CPN_QUERY_TLR = "BP-F-TLR-INF-QUERY  ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    int WS_BVNO_LEN = 0;
    long WS_COMP_BEGNO = 0;
    long WS_COMP_ENDNO = 0;
    char WS_OUT_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCSBLDE BPCSBLDE = new BPCSBLDE();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB3270_AWA_3270 BPB3270_AWA_3270;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3270 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3270_AWA_3270>");
        BPB3270_AWA_3270 = (BPB3270_AWA_3270) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_LINK_COMPONENT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BVLT_TLR;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCFBVQU.TX_DATA.KEY.CODE = BPB3270_AWA_3270.BV_CODE;
        BPCFBVQU.TX_DATA.TYPE = '1';
        S000_CALL_BPZFBVQU();
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_LINK_COMPONENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBLDE);
        BPCSBLDE.TX_DATA.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCSBLDE.TX_DATA.OUT_TYP = BPB3270_AWA_3270.OUT_TYP;
        BPCSBLDE.TX_DATA.BV_CODE = BPB3270_AWA_3270.BV_CODE;
        BPCSBLDE.TX_DATA.BV_NAME = BPB3270_AWA_3270.BV_NAME;
        BPCSBLDE.TX_DATA.CCY = BPB3270_AWA_3270.CCY;
        BPCSBLDE.TX_DATA.VALUE = BPB3270_AWA_3270.PVAL;
        BPCSBLDE.TX_DATA.HEAD_NO = BPB3270_AWA_3270.HEAD_NO;
        BPCSBLDE.TX_DATA.BEG_NO = BPB3270_AWA_3270.BEG_NO;
        BPCSBLDE.TX_DATA.END_NO = BPB3270_AWA_3270.END_NO;
        BPCSBLDE.TX_DATA.NUM = BPB3270_AWA_3270.NUM;
        BPCSBLDE.TX_DATA.AMT = BPB3270_AWA_3270.AMT;
        S00_CALL_BPZSBLDE();
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_QUERY_TLR, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S00_CALL_BPZSBLDE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BL_DESTROY, BPCSBLDE);
        if (BPCSBLDE.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSBLDE.RC);
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
