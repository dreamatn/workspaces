package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class IBOT9070 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCCHWAN IBCCHWAN = new IBCCHWAN();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBB9050_AWA_9050 IBB9050_AWA_9050;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBOT9070 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "IBB9050_AWA_9050>");
        IBB9050_AWA_9050 = (IBB9050_AWA_9050) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_TRANS_DATA_PROC();
        if (pgmRtn) return;
        Z_RET();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBB9050_AWA_9050.FCN);
        if (IBB9050_AWA_9050.FCN == ' ') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT);
        }
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCCHWAN);
        IBCCHWAN.FUNC = IBB9050_AWA_9050.FCN;
        IBCCHWAN.EXP_DATE = IBB9050_AWA_9050.EXP_DATE;
        IBCCHWAN.ESET_AMT = IBB9050_AWA_9050.ESET_AMT;
        IBCCHWAN.ASET_AMT = IBB9050_AWA_9050.ASET_AMT;
        IBCCHWAN.CCY = IBB9050_AWA_9050.CCY;
        S000_CALL_IBZCHWAN();
        if (pgmRtn) return;
    }
    public void S000_CALL_IBZCHWAN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-CHECK-WARN", IBCCHWAN);
        if (IBCCHWAN.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCCHWAN.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
