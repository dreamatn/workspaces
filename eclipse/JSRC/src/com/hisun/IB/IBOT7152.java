package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class IBOT7152 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCACSMS IBCACSMS = new IBCACSMS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBB7110_AWA_7110 IBB7110_AWA_7110;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "IBOT7152 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "IBB7110_AWA_7110>");
        IBB7110_AWA_7110 = (IBB7110_AWA_7110) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_SUB_AC_MAINT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBB7110_AWA_7110.PRI_ACNO);
        CEP.TRC(SCCGWA, IBB7110_AWA_7110.NOS_CD);
        CEP.TRC(SCCGWA, IBB7110_AWA_7110.CCY);
        if (IBB7110_AWA_7110.PRI_ACNO.trim().length() == 0 
            && (IBB7110_AWA_7110.NOS_CD.trim().length() == 0 
            || IBB7110_AWA_7110.CCY.trim().length() == 0)) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE);
        }
    }
    public void B020_SUB_AC_MAINT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCACSMS);
        IBCACSMS.NOSTR_CD = IBB7110_AWA_7110.NOS_CD;
        IBCACSMS.PRIM_AC_NO = IBB7110_AWA_7110.PRI_ACNO;
        IBCACSMS.SEQ_NO = IBB7110_AWA_7110.SEQ_NO;
        IBCACSMS.CCY = IBB7110_AWA_7110.CCY;
        IBCACSMS.STS = "" + IBB7110_AWA_7110.AC_STS;
        JIBS_tmp_int = IBCACSMS.STS.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) IBCACSMS.STS = "0" + IBCACSMS.STS;
        IBCACSMS.AUTH_TLR = IBB7110_AWA_7110.AUTH_TLR;
        S000_CALL_IBZACSMS();
    }
    public void S000_CALL_IBZACSMS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-AC-MAINT-SUB", IBCACSMS);
        if (IBCACSMS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCACSMS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
