package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5590 {
    String CPN_DD_S_PPBC_PROC = "DD-S-PPBC-PROC";
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSPPBC DDCSPPBC = new DDCSPPBC();
    CICQACRL CICQACRL = new CICQACRL();
    SCCGWA SCCGWA;
    DDB5590_AWA_5590 DDB5590_AWA_5590;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5590 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5590_AWA_5590>");
        DDB5590_AWA_5590 = (DDB5590_AWA_5590) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B015_CHK_IFCARD_PROC();
        B020_PRT_PSBK_COVER_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5590_AWA_5590);
        CEP.TRC(SCCGWA, DDB5590_AWA_5590.SPTPB_AC);
        CEP.TRC(SCCGWA, DDB5590_AWA_5590.PTPB_CNM);
        CEP.TRC(SCCGWA, DDB5590_AWA_5590.PTPB_ENM);
        if (DDB5590_AWA_5590.SPTPB_AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B015_CHK_IFCARD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.DATA.REL_AC_NO = DDB5590_AWA_5590.SPTPB_AC;
        CICQACRL.DATA.AC_REL = "09";
        CICQACRL.FUNC = '4';
        CICQACRL.FUNC = 'I';
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_NO);
        if (CICQACRL.RC.RC_CODE == 0) {
            DDB5590_AWA_5590.SPTPB_AC = CICQACRL.O_DATA.O_AC_NO;
        } else {
        }
    }
    public void B020_PRT_PSBK_COVER_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSPPBC);
        DDCSPPBC.AC = DDB5590_AWA_5590.SPTPB_AC;
        DDCSPPBC.AC_CNAME = DDB5590_AWA_5590.PTPB_CNM;
        DDCSPPBC.AC_ENAME = DDB5590_AWA_5590.PTPB_ENM;
        S000_CALL_DDZSPPBC();
    }
    public void S000_CALL_DDZSPPBC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DD_S_PPBC_PROC, DDCSPPBC);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
