package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4701 {
    String K_CMP_CHK_CAL_CODE = "BP-P-CHK-CAL-CODE";
    String K_FMT_CD = "BP452";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT4701_WS_FMT_DATA WS_FMT_DATA = new BPOT4701_WS_FMT_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOCCAL BPCOCCAL = new BPCOCCAL();
    SCCGWA SCCGWA;
    BPB4700_AWA_4700 BPB4700_AWA_4700;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4701 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4700_AWA_4700>");
        BPB4700_AWA_4700 = (BPB4700_AWA_4700) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_MAIN_PROCESS();
        B200_SET_RETURN_INFO();
    }
    public void B100_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCCAL);
        BPCOCCAL.CAL_CODE = BPB4700_AWA_4700.CALCD;
        S000_CALL_BPZPCCAL();
    }
    public void B200_SET_RETURN_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_FMT_DATA);
        WS_FMT_DATA.WS_FMT_CAL = BPB4700_AWA_4700.CALCD;
        WS_FMT_DATA.WS_FMT_CAL_NAME = BPCOCCAL.CAL_NAME;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CD;
        SCCFMT.DATA_PTR = WS_FMT_DATA;
        SCCFMT.DATA_LEN = 64;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPCCAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_CHK_CAL_CODE;
        SCCCALL.COMMAREA_PTR = BPCOCCAL;
        SCCCALL.ERR_FLDNO = BPB4700_AWA_4700.CALCD_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCOCCAL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOCCAL.RC);
            WS_FLD_NO = BPB4700_AWA_4700.CALCD_NO;
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
