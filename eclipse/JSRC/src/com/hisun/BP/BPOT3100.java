package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3100 {
    String K_OUTPUT_FMT = "BP159";
    String CPN_S_BV_REVOKE = "BP-S-BV-REVOKE   ";
    String CPN_QUERY_TLR = "BP-F-TLR-INF-QUERY  ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    int WS_BVNO_LEN = 0;
    long WS_COMP_BEGNO = 0;
    long WS_COMP_ENDNO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCSBVRV BPCSBVRV = new BPCSBVRV();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB3100_AWA_3100 BPB3100_AWA_3100;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3100 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3100_AWA_3100>");
        BPB3100_AWA_3100 = (BPB3100_AWA_3100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_LINK_BVRV_COMPONENT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCFBVQU.TX_DATA.KEY.CODE = BPB3100_AWA_3100.BV_CODE;
        S000_CALL_BPZFBVQU();
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_LINK_BVRV_COMPONENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBVRV);
        BPCSBVRV.TX_DATA.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCSBVRV.TX_DATA.OUT_TYP = BPB3100_AWA_3100.OUT_TYP;
        BPCSBVRV.TX_DATA.BV_CODE = BPB3100_AWA_3100.BV_CODE;
        BPCSBVRV.TX_DATA.NAME = BPB3100_AWA_3100.BV_NAME;
        BPCSBVRV.TX_DATA.HEAD_NO = BPB3100_AWA_3100.HEAD_NO;
        BPCSBVRV.TX_DATA.BEG_NO = BPB3100_AWA_3100.BEG_NO;
        BPCSBVRV.TX_DATA.END_NO = BPB3100_AWA_3100.END_NO;
        BPCSBVRV.TX_DATA.NUM = BPB3100_AWA_3100.NUM;
        BPCSBVRV.TX_DATA.REMARK = BPB3100_AWA_3100.REMARK;
        S00_CALL_BPZSBVRV();
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S00_CALL_BPZSBVRV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BV_REVOKE, BPCSBVRV);
        if (BPCSBVRV.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSBVRV.RC);
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
