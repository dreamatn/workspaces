package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT5200 {
    int JIBS_tmp_int;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCSSUBS LNCSSUBS = new LNCSSUBS();
    LNCSSTBL LNCSSTBL = new LNCSSTBL();
    CICQACRI CICQACRI = new CICQACRI();
    CICCUST CICCUST = new CICCUST();
    SCCGWA SCCGWA;
    LNB5200_AWA_5200 LNB5200_AWA_5200;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT5200 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB5200_AWA_5200>");
        LNB5200_AWA_5200 = (LNB5200_AWA_5200) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        LNCSSUBS.RC.RC_MMO = "LN";
        LNCSSUBS.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_SUBSMULA_MAIN();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSSTBL);
        LNCSSTBL.CI_NO = LNB5200_AWA_5200.CI_NO;
        S000_CALL_LNZSSTBL();
        if (LNB5200_AWA_5200.FUN_CD != 'I' 
            && LNB5200_AWA_5200.FUN_CD != 'D') {
            if (LNB5200_AWA_5200.PAY_AC.trim().length() > 0) {
                IBS.init(SCCGWA, CICQACRI);
                CICQACRI.DATA.AGR_NO = LNB5200_AWA_5200.PAY_AC;
                CICQACRI.FUNC = 'A';
                S000_CALL_CIZQACRI();
            } else {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
                WS_FLD_NO = LNB5200_AWA_5200.PAY_AC_NO;
                S000_ERR_MSG_PROC();
            }
            if (LNB5200_AWA_5200.IA_AC.trim().length() > 0) {
                IBS.init(SCCGWA, CICQACRI);
                CICQACRI.DATA.AGR_NO = LNB5200_AWA_5200.IA_AC;
                CICQACRI.FUNC = 'A';
                S000_CALL_CIZQACRI();
            } else {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT;
                WS_FLD_NO = LNB5200_AWA_5200.IA_AC_NO;
                S000_ERR_MSG_PROC();
            }
            if (LNB5200_AWA_5200.CCY.trim().length() == 0) {
                LNB5200_AWA_5200.CCY = "156";
            } else {
                if (!LNB5200_AWA_5200.CCY.equalsIgnoreCase("156") 
                    && !LNB5200_AWA_5200.CCY.equalsIgnoreCase("840") 
                    && !LNB5200_AWA_5200.CCY.equalsIgnoreCase("344")) {
                    WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_OUT_CTL_CCY;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        if (LNB5200_AWA_5200.CI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = LNB5200_AWA_5200.CI_NO;
            CICCUST.FUNC = 'C';
            S000_CALL_CIZCUST();
        }
    }
    public void B020_SUBSMULA_MAIN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB5200_AWA_5200.PROJ_NO);
        CEP.TRC(SCCGWA, LNB5200_AWA_5200.CI_NO);
        CEP.TRC(SCCGWA, LNB5200_AWA_5200.CI_NM);
        IBS.init(SCCGWA, LNCSSUBS);
        LNCSSUBS.FUNC = LNB5200_AWA_5200.FUN_CD;
        LNCSSUBS.DATA.KEY.PROJ_NO = LNB5200_AWA_5200.PROJ_NO;
        LNCSSUBS.PROJ_NM = LNB5200_AWA_5200.PROJ_NM;
        LNCSSUBS.CI_NO = LNB5200_AWA_5200.CI_NO;
        LNCSSUBS.CI_NM = LNB5200_AWA_5200.CI_NM;
        LNCSSUBS.PAY_AC = LNB5200_AWA_5200.PAY_AC;
        LNCSSUBS.IA_AC = LNB5200_AWA_5200.IA_AC;
        LNCSSUBS.SUBS_MTH = LNB5200_AWA_5200.TYP;
        CEP.TRC(SCCGWA, LNCSSUBS.SUBS_MTH);
        LNCSSUBS.TERM = LNB5200_AWA_5200.TERM;
        LNCSSUBS.ST_TERM = LNB5200_AWA_5200.ST_TERM;
        LNCSSUBS.SUB_SEQ = LNB5200_AWA_5200.SUB_SEQ;
        LNCSSUBS.CCY = LNB5200_AWA_5200.CCY;
        LNCSSUBS.FIXED_AMT = LNB5200_AWA_5200.FIXE_AMT;
        LNCSSUBS.SUBS_PCT = LNB5200_AWA_5200.SUBS_PCT;
        LNCSSUBS.AMT_TYP = LNB5200_AWA_5200.AMT_TYP;
        LNCSSUBS.SUBS_RAT = LNB5200_AWA_5200.SUBS_RAT;
        LNCSSUBS.RMK = LNB5200_AWA_5200.RMK;
        if (LNB5200_AWA_5200.PAGE_ROW == 0) {
            if ("25".trim().length() == 0) LNCSSUBS.PAGE_ROW = 0;
            else LNCSSUBS.PAGE_ROW = Integer.parseInt("25");
        } else {
            if (LNB5200_AWA_5200.PAGE_ROW > 25) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_PAGE_ROW;
                S000_ERR_MSG_PROC();
            } else {
                LNCSSUBS.PAGE_ROW = LNB5200_AWA_5200.PAGE_ROW;
            }
        }
        if (LNB5200_AWA_5200.PAGE_ROW == 0) {
            if ("0".trim().length() == 0) LNCSSUBS.PAGE_NUM = 0;
            else LNCSSUBS.PAGE_NUM = Integer.parseInt("0");
        } else {
            LNCSSUBS.PAGE_NUM = LNB5200_AWA_5200.PAGE_NUM;
        }
        S000_CALL_LNZSSUBS();
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void S000_CALL_LNZSSUBS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-SUBS-MAIN", LNCSSUBS);
        if (LNCSSUBS.RC.RC_CODE != 0) {
            WS_ERR_MSG = "" + LNCSSUBS.RC.RC_CODE;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZSSTBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-CHECK-CI-STS", LNCSSTBL);
        if (LNCSSTBL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSSTBL.RC);
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
