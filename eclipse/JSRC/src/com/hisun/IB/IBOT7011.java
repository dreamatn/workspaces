package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class IBOT7011 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    IBCACINT IBCACINT = new IBCACINT();
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBB7010_AWA_7010 IBB7010_AWA_7010;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBOT7011 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "IBB7010_AWA_7010>");
        IBB7010_AWA_7010 = (IBB7010_AWA_7010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_AC_INQ_PROC();
        if (pgmRtn) return;
    }
    public void EXITT() throws IOException,SQLException,Exception {
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBB7010_AWA_7010.ACNO);
        CEP.TRC(SCCGWA, IBB7010_AWA_7010.NOS_CD);
        CEP.TRC(SCCGWA, IBB7010_AWA_7010.CCY);
        CEP.TRC(SCCGWA, IBB7010_AWA_7010.FUNC);
        if (IBB7010_AWA_7010.ACNO.trim().length() == 0 
            && (IBB7010_AWA_7010.NOS_CD.trim().length() == 0 
            || IBB7010_AWA_7010.CCY.trim().length() == 0)) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE, IBCACINT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_AC_INQ_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCACINT);
        IBCACINT.FUNC = IBB7010_AWA_7010.FUNC;
        IBCACINT.NOSTR_CD = IBB7010_AWA_7010.NOS_CD;
        IBCACINT.CCY = IBB7010_AWA_7010.CCY;
        IBCACINT.AC_NO = IBB7010_AWA_7010.ACNO;
        S000_CALL_IBZACINT();
        if (pgmRtn) return;
    }
    public void S000_CALL_IBZACINT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-AC-INQT", IBCACINT);
        if (IBCACINT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCACINT.RC);
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
