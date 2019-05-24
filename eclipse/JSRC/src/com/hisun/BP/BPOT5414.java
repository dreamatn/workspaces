package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5414 {
    int JIBS_tmp_int;
    String K_CMP_CAL_MAINTAIN = "BP-S-IRPD-MAINT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSIPOM BPCSIPOM = new BPCSIPOM();
    SCCGWA SCCGWA;
    BPB5410_AWA_5410 BPB5410_AWA_5410;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5414 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5410_AWA_5410>");
        BPB5410_AWA_5410 = (BPB5410_AWA_5410) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_MAIN_PROCESS();
    }
    public void B100_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSIPOM);
        BPCSIPOM.CCY = BPB5410_AWA_5410.CCY;
        BPCSIPOM.B_TYPE = BPB5410_AWA_5410.B_TYPE;
        BPCSIPOM.TENOR = "" + BPB5410_AWA_5410.TENOR;
        JIBS_tmp_int = BPCSIPOM.TENOR.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) BPCSIPOM.TENOR = "0" + BPCSIPOM.TENOR;
        BPCSIPOM.EFF_FLG = BPB5410_AWA_5410.EFF_FLG;
        BPCSIPOM.DEL_FLG = BPB5410_AWA_5410.DEL_FLG;
        BPCSIPOM.FUNC = 'D';
        BPCSIPOM.OUTPUT_FLG = 'Y';
        S000_CALL_BPZSIPOM();
    }
    public void S000_CALL_BPZSIPOM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_CAL_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCSIPOM;
        SCCCALL.ERR_FLDNO = BPB5410_AWA_5410.FUNC_CD_NO;
        IBS.CALL(SCCGWA, SCCCALL);
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
