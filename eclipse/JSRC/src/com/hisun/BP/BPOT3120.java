package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3120 {
    String K_OUTPUT_FMT = "BP161";
    String CPN_S_SOLD_CANCEL = "BP-S-SOLD-CANCEL    ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_TEMP_NUM = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRBCUS BPRBCUS = new BPRBCUS();
    BPCRBCUS BPCRBCUS = new BPCRBCUS();
    BPCRBCUB BPCRBCUB = new BPCRBCUB();
    BPCSBVSC BPCSBVSC = new BPCSBVSC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB3120_AWA_3120 BPB3120_AWA_3120;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3120 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3120_AWA_3120>");
        BPB3120_AWA_3120 = (BPB3120_AWA_3120) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_LINK_BVRV_COMPONENT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_LINK_BVRV_COMPONENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBVSC);
        BPCSBVSC.TX_DATA.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCSBVSC.TX_DATA.AC_NO = BPB3120_AWA_3120.AC_NO;
        BPCSBVSC.TX_DATA.AC_NAME = BPB3120_AWA_3120.AC_NAME;
        BPCSBVSC.TX_DATA.BV_CODE = BPB3120_AWA_3120.BV_CODE;
        BPCSBVSC.TX_DATA.BV_NAME = BPB3120_AWA_3120.BV_NAME;
        BPCSBVSC.TX_DATA.HEAD_NO = BPB3120_AWA_3120.HEAD_NO;
        BPCSBVSC.TX_DATA.BEG_NO = BPB3120_AWA_3120.BEG_NO;
        BPCSBVSC.TX_DATA.END_NO = BPB3120_AWA_3120.END_NO;
        BPCSBVSC.TX_DATA.NUM = BPB3120_AWA_3120.NUM;
        BPCSBVSC.TX_DATA.REASON = BPB3120_AWA_3120.REASON;
        S00_CALL_BPZSBVSC();
    }
    public void S00_CALL_BPZSBVSC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_SOLD_CANCEL, BPCSBVSC);
        if (BPCSBVSC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSBVSC.RC);
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
