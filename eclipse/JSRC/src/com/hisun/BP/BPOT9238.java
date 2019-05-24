package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.text.DecimalFormat;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9238 {
    DecimalFormat df;
    char K_ERROR = 'E';
    String WS_EXRT_X = " ";
    BPOT9238_WS_OUT_DATA WS_OUT_DATA = new BPOT9238_WS_OUT_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCPQPDT BPCPQPDT = new BPCPQPDT();
    BPCO702 BPCO702 = new BPCO702();
    SCCGWA SCCGWA;
    BPB9238_AWA_9238 BPB9238_AWA_9238;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "BPOT9238 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB9238_AWA_9238>");
        BPB9238_AWA_9238 = (BPB9238_AWA_9238) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_TRANS_PROCESS();
        B200_OUT_PROCESS();
    }
    public void B100_TRANS_PROCESS() throws IOException,SQLException,Exception {
        df = new DecimalFormat("000000.00000000");
        WS_EXRT_X = df.format(BPB9238_AWA_9238.EX_RATE);
        CEP.TRC(SCCGWA, WS_EXRT_X);
        WS_OUT_DATA.WS_OUT_EXRATE = BPB9238_AWA_9238.EX_RATE;
        CEP.TRC(SCCGWA, BPB9238_AWA_9238.RATE);
        CEP.TRC(SCCGWA, BPB9238_AWA_9238.AMT);
        WS_OUT_DATA.WS_OUT_AMT = BPB9238_AWA_9238.AMT;
        WS_OUT_DATA.WS_OUT_RATE = BPB9238_AWA_9238.RATE;
    }
    public void B200_OUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, WS_OUT_DATA);
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 42;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
