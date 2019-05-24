package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class IBOT9010 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCQEINT IBCQEINT = new IBCQEINT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBB9010_AWA_9010 IBB9010_AWA_9010;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBOT9010 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "IBB9010_AWA_9010>");
        IBB9010_AWA_9010 = (IBB9010_AWA_9010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
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
        CEP.TRC(SCCGWA, IBB9010_AWA_9010.AC_NO);
        if (IBB9010_AWA_9010.AC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.IB_AC_NO_M_INPUT);
        }
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQEINT);
        IBCQEINT.FUNC = IBB9010_AWA_9010.FCN;
        IBCQEINT.AC_NO = IBB9010_AWA_9010.AC_NO;
        IBCQEINT.SEQ_NO = IBB9010_AWA_9010.SEQ_NO;
        IBCQEINT.DT = IBB9010_AWA_9010.DATE;
        IBCQEINT.ERATE = IBB9010_AWA_9010.ERATE;
        IBCQEINT.EADV_RATE = IBB9010_AWA_9010.EADV_RAT;
        IBCQEINT.EOVD_RATE = IBB9010_AWA_9010.EOVD_RAT;
        IBCQEINT.DRW_AMT = IBB9010_AWA_9010.DRW_AMT;
        IBCQEINT.CCY = IBB9010_AWA_9010.CCY;
        IBCQEINT.OPEN_BAL = IBB9010_AWA_9010.OPEN_BAL;
        IBCQEINT.CALR_STD = IBB9010_AWA_9010.CALR_STD;
        IBCQEINT.INT_DAYS = IBB9010_AWA_9010.INT_DAYS;
        IBCQEINT.RATE = IBB9010_AWA_9010.RATE;
        S000_CALL_IBZQEINT();
        if (pgmRtn) return;
    }
    public void S000_CALL_IBZQEINT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-ESET-AMT-INQ", IBCQEINT);
        if (IBCQEINT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQEINT.RC);
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
