package com.hisun.PY;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class PYOT8240 {
    DBParm PYTSQRV_RD;
    String K_OUTPUT_FMT = "PYZ07";
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    PYCMSG_ERROR_MSG PYCMSG_ERROR_MSG = new PYCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    PYRSQRV PYRSQRV = new PYRSQRV();
    short WS_NUM = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    PYCI8240 PYCI8240;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "PYOT8240 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        PYCI8240 = new PYCI8240();
        IBS.init(SCCGWA, PYCI8240);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, PYCI8240);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        T0000_GROUP_PYTSQRV();
        B210_FMT_OUTPUT();
    }
    public void T0000_GROUP_PYTSQRV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PYRSQRV);
        PYRSQRV.BILL_NO = PYCI8240.BILL_NO;
        PYTSQRV_RD = new DBParm();
        PYTSQRV_RD.TableName = "PYTSQRV";
        PYTSQRV_RD.where = "BILL_NO = :PYRSQRV.BILL_NO";
        PYTSQRV_RD.set = "WS-NUM=COUNT(*)";
        IBS.GROUP(SCCGWA, PYRSQRV, this, PYTSQRV_RD);
    }
    public void B210_FMT_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_NUM;
        SCCFMT.DATA_LEN = 4;
        IBS.FMT(SCCGWA, SCCFMT);
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
