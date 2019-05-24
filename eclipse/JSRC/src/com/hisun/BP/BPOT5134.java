package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5134 {
    String K_CMP_IDEV_MAINT = "BP-S-IDEV-MAINT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_I = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSDEVM BPCSDEVM = new BPCSDEVM();
    SCCGWA SCCGWA;
    BPB5130_AWA_5130 BPB5130_AWA_5130;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5134 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5130_AWA_5130>");
        BPB5130_AWA_5130 = (BPB5130_AWA_5130) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_MAIN_PROCESS();
    }
    public void B100_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSDEVM);
        BPCSDEVM.INFO.TYPE = BPB5130_AWA_5130.TYPE;
        BPCSDEVM.INFO.BR = BPB5130_AWA_5130.BR;
        BPCSDEVM.INFO.CCY = BPB5130_AWA_5130.CCY;
        BPCSDEVM.INFO.BASE_TYP = BPB5130_AWA_5130.BASE_TYP;
        BPCSDEVM.INFO.TENOR = BPB5130_AWA_5130.TENOR;
        BPCSDEVM.INFO.FMT = BPB5130_AWA_5130.FMT;
        CEP.TRC(SCCGWA, BPB5130_AWA_5130.TYPE);
        CEP.TRC(SCCGWA, BPB5130_AWA_5130.BR);
        CEP.TRC(SCCGWA, BPB5130_AWA_5130.CCY);
        CEP.TRC(SCCGWA, BPB5130_AWA_5130.BASE_TYP);
        CEP.TRC(SCCGWA, BPB5130_AWA_5130.TENOR);
        CEP.TRC(SCCGWA, BPB5130_AWA_5130.FMT);
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            BPCSDEVM.INFO.DATA[WS_I-1].DEVIA = BPB5130_AWA_5130.DATA[WS_I-1].DEVIA;
            BPCSDEVM.INFO.DATA[WS_I-1].DEVIA_AU = BPB5130_AWA_5130.DATA[WS_I-1].DEVIA_AU;
            BPCSDEVM.CNT = 10;
            CEP.TRC(SCCGWA, BPCSDEVM.CNT);
        }
        BPCSDEVM.INFO.FUNC = 'D';
        BPCSDEVM.OUTPUT_FLG = 'Y';
        S000_CALL_BPZSDEVM();
    }
    public void S000_CALL_BPZSDEVM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_IDEV_MAINT;
        SCCCALL.COMMAREA_PTR = BPCSDEVM;
        SCCCALL.ERR_FLDNO = BPB5130_AWA_5130.FUNC_CD_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
