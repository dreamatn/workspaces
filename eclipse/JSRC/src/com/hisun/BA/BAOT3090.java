package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BAOT3090 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCFMT SCCFMT = new SCCFMT();
    BACUQSEQ BACUQSEQ = new BACUQSEQ();
    SCCGWA SCCGWA;
    BAB3090_AWA_3090 BAB3090_AWA_3090;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BAOT3090 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BAB3090_AWA_3090>");
        BAB3090_AWA_3090 = (BAB3090_AWA_3090) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        BACUQSEQ.RC.RC_MMO = "BA";
        BACUQSEQ.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_PROC_MAIN();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BAB3090_AWA_3090.DRWR_AC);
        if (BAB3090_AWA_3090.DRWR_AC.trim().length() == 0) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_DRWR_AC_M_INPUT;
            WS_FLD_NO = BAB3090_AWA_3090.DRWR_AC_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_PROC_MAIN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACUQSEQ);
        BACUQSEQ.COMM_DATA.DRWR_AC = BAB3090_AWA_3090.DRWR_AC;
        CEP.TRC(SCCGWA, BACUQSEQ.COMM_DATA.PAGE_ROW);
        CEP.TRC(SCCGWA, BACUQSEQ.COMM_DATA.PAGE_NUM);
        BACUQSEQ.COMM_DATA.PAGE_ROW = BAB3090_AWA_3090.PAGE_ROW;
        BACUQSEQ.COMM_DATA.PAGE_NUM = BAB3090_AWA_3090.PAGE_NUM;
        CEP.TRC(SCCGWA, BACUQSEQ.COMM_DATA.PAGE_ROW);
        CEP.TRC(SCCGWA, BACUQSEQ.COMM_DATA.PAGE_NUM);
        S000_CALL_BAZUQSEQ();
    }
    public void S000_CALL_BAZUQSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-INQ-DBTSEQ", BACUQSEQ);
        if (BACUQSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACUQSEQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
