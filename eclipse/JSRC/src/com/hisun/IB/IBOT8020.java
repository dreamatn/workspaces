package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class IBOT8020 {
    String JIBS_tmp_str[] = new String[10];
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCSQRIT IBCSQRIT = new IBCSQRIT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBB8010_AWA_8010 IBB8010_AWA_8010;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "IBOT8020 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "IBB8010_AWA_8010>");
        IBB8010_AWA_8010 = (IBB8010_AWA_8010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBB8010_AWA_8010.PRI_ACNO);
        CEP.TRC(SCCGWA, IBB8010_AWA_8010.NOS_CD);
        CEP.TRC(SCCGWA, IBB8010_AWA_8010.CCY);
        if (IBB8010_AWA_8010.PRI_ACNO.trim().length() == 0 
            && (IBB8010_AWA_8010.NOS_CD.trim().length() == 0 
            || IBB8010_AWA_8010.CCY.trim().length() == 0)) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE);
        }
        CEP.TRC(SCCGWA, IBB8010_AWA_8010.SEQ_NO);
        if (IBB8010_AWA_8010.SEQ_NO == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.SEQ_MUST_INPUT);
        }
        CEP.TRC(SCCGWA, IBB8010_AWA_8010.STR_DATE);
        CEP.TRC(SCCGWA, IBB8010_AWA_8010.END_DATE);
        if (IBB8010_AWA_8010.STR_DATE == 0 
            || IBB8010_AWA_8010.END_DATE == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT);
        } else {
            if (IBB8010_AWA_8010.STR_DATE > IBB8010_AWA_8010.END_DATE) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.SDATE_GT_EDATE);
            }
        }
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCSQRIT);
        IBCSQRIT.FUNC = IBB8010_AWA_8010.FCN;
        IBCSQRIT.NOSTR_CD = IBB8010_AWA_8010.NOS_CD;
        IBCSQRIT.CCY = IBB8010_AWA_8010.CCY;
        IBCSQRIT.PRIM_AC_NO = IBB8010_AWA_8010.PRI_ACNO;
        IBCSQRIT.SEQ_NO = IBB8010_AWA_8010.SEQ_NO;
        IBCSQRIT.STR_DATE = IBB8010_AWA_8010.STR_DATE;
        IBCSQRIT.END_DATE = IBB8010_AWA_8010.END_DATE;
        S000_CALL_IBZSQRIT();
    }
    public void S000_CALL_IBZSQRIT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-INQS-RATE-HIS", IBCSQRIT);
        if (IBCSQRIT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCSQRIT.RC);
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
