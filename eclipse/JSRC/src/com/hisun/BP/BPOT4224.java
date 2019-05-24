package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4224 {
    String K_CMP_PARM_CODE_MAINT = "BP-MAINT-PARM-CODE";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSMPCD BPCSMPCD = new BPCSMPCD();
    SCCGWA SCCGWA;
    BPB4200_AWA_4200 BPB4200_AWA_4200;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4224 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4200_AWA_4200>");
        BPB4200_AWA_4200 = (BPB4200_AWA_4200) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_MAIN_PROCESS();
    }
    public void B100_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMPCD);
        BPCSMPCD.TYPE = BPB4200_AWA_4200.TYPE;
        BPCSMPCD.TYPE_NAME = BPB4200_AWA_4200.NAME;
        BPCSMPCD.TYPE_CHG_NAME = BPB4200_AWA_4200.CHG_NAME;
        BPCSMPCD.CODE = BPB4200_AWA_4200.CODE;
        BPCSMPCD.INFO.CODE_NAME = BPB4200_AWA_4200.CD_NAME;
        BPCSMPCD.INFO.CODE_NAME_S = BPB4200_AWA_4200.SH_NAME;
        BPCSMPCD.INFO.REMARKS = BPB4200_AWA_4200.CODE_RMK;
        BPCSMPCD.EFF_DATE = BPB4200_AWA_4200.EFF_DATE;
        BPCSMPCD.EXP_DATE = BPB4200_AWA_4200.EXP_DATE;
        BPCSMPCD.INFO.RBASE_TYP = BPB4200_AWA_4200.RBASE_TY;
        BPCSMPCD.OUTPUT_FLG = 'Y';
        BPCSMPCD.FUNC = 'D';
        S000_CALL_BPZSMPCD();
    }
    public void S000_CALL_BPZSMPCD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_PARM_CODE_MAINT;
        SCCCALL.COMMAREA_PTR = BPCSMPCD;
        SCCCALL.ERR_FLDNO = BPB4200_AWA_4200.FUNC_CD_NO;
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
