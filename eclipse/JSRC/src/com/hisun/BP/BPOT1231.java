package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1231 {
    String JIBS_tmp_str[] = new String[10];
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICACCU CICACCU = new CICACCU();
    BPCPCKPD BPCPCKPD = new BPCPCKPD();
    BPCSFECT BPCSFECT = new BPCSFECT();
    BPCF231 BPCF231 = new BPCF231();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB1231_AWA_1231 BPB1231_AWA_1231;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1231 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1231_AWA_1231>");
        BPB1231_AWA_1231 = (BPB1231_AWA_1231) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_CREATE_FCTR();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1231_AWA_1231.CI_NO);
        CEP.TRC(SCCGWA, BPB1231_AWA_1231.FEE_CODE);
        CEP.TRC(SCCGWA, BPB1231_AWA_1231.BOOK_BR);
        CEP.TRC(SCCGWA, BPB1231_AWA_1231.START_DT);
        CEP.TRC(SCCGWA, BPB1231_AWA_1231.MAT_DT);
        CEP.TRC(SCCGWA, BPB1231_AWA_1231.REL_CTRT);
        CEP.TRC(SCCGWA, BPB1231_AWA_1231.CHG_MTH);
        CEP.TRC(SCCGWA, BPB1231_AWA_1231.CHG_AC);
        CEP.TRC(SCCGWA, BPB1231_AWA_1231.CHG_CCY);
        CEP.TRC(SCCGWA, BPB1231_AWA_1231.CCY_TYPE);
        CEP.TRC(SCCGWA, BPB1231_AWA_1231.CHG_AMT);
        CEP.TRC(SCCGWA, BPB1231_AWA_1231.CHG_FLG);
        CEP.TRC(SCCGWA, BPB1231_AWA_1231.DESC);
        CEP.TRC(SCCGWA, BPB1231_AWA_1231.PRD_CODE);
        CEP.TRC(SCCGWA, BPB1231_AWA_1231.PRD_AC_R);
        if (BPB1231_AWA_1231.PRD_CODE.trim().length() == 0 
            && BPB1231_AWA_1231.PRD_AC_R.trim().length() == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
        }
        if (BPB1231_AWA_1231.PRD_CODE.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = BPB1231_AWA_1231.PRD_CODE;
            S000_CALL_BPZPQPRD();
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
        }
        CEP.TRC(SCCGWA, BPB1231_AWA_1231.BOOK_BR);
        if (BPB1231_AWA_1231.BOOK_BR == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
        }
        if (BPB1231_AWA_1231.RELT_FLG != ' ') {
            if (BPB1231_AWA_1231.RELT_FLG != '0' 
                && BPB1231_AWA_1231.RELT_FLG != '1') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
            }
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_RELT_MUST_INPUT);
        }
        CEP.TRC(SCCGWA, BPB1231_AWA_1231.START_DT);
        CEP.TRC(SCCGWA, BPB1231_AWA_1231.MAT_DT);
        if (BPB1231_AWA_1231.MAT_DT < SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_MATUR_DT_ERR);
        }
        if (BPB1231_AWA_1231.MAT_DT <= BPB1231_AWA_1231.START_DT) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_MATUR_DT_LESS);
        }
    }
    public void B020_CREATE_FCTR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSFECT);
        BPCSFECT.CINO = BPB1231_AWA_1231.CI_NO;
        BPCSFECT.FEE_TYP = BPB1231_AWA_1231.FEE_CODE;
        BPCSFECT.CT_TYP = "FEES";
        BPCSFECT.BOOK_ACCT = BPB1231_AWA_1231.BOOK_BR;
        BPCSFECT.START_DT = BPB1231_AWA_1231.START_DT;
        BPCSFECT.MATURITY_DT = BPB1231_AWA_1231.MAT_DT;
        BPCSFECT.HOL_OVR = 'Y';
        BPCSFECT.HOL_METH = 'N';
        BPCSFECT.CAL_CD1 = "CN";
        BPCSFECT.CAL_CD2 = "CN";
        BPCSFECT.PAY_IND = 'P';
        BPCSFECT.PAY_METH = 'P';
        BPCSFECT.INT_BASIC = "02";
        BPCSFECT.ACU_TYP = 'F';
        BPCSFECT.RLTD_CTNO = BPB1231_AWA_1231.REL_CTRT;
        BPCSFECT.CHG_METH = BPB1231_AWA_1231.CHG_MTH;
        BPCSFECT.CHG_ACNO = BPB1231_AWA_1231.CHG_AC;
        BPCSFECT.CHG_CCY = BPB1231_AWA_1231.CHG_CCY;
        BPCSFECT.CHG_CCY_REAL = BPB1231_AWA_1231.CHG_CCY;
        BPCSFECT.CCY_TYPE = BPB1231_AWA_1231.CCY_TYPE;
        BPCSFECT.CHG_AMT = BPB1231_AWA_1231.CHG_AMT;
        BPCSFECT.CHG_AMT_REAL = BPB1231_AWA_1231.CHG_AMT;
        BPCSFECT.RELT_FLG = BPB1231_AWA_1231.RELT_FLG;
        CEP.TRC(SCCGWA, BPCSFECT.RELT_FLG);
        if (BPCSFECT.PAY_METH == 'P') {
            if (BPCSFECT.RELT_FLG == '1') {
                CEP.TRC(SCCGWA, "FEE CONTRACT STATUS:UNSETTLE");
                BPCSFECT.CT_STS = '0';
            } else {
                if (BPCSFECT.MATURITY_DT != BPCSFECT.START_DT) {
                    CEP.TRC(SCCGWA, "FEE CONTRACT STATUS:SETTLED");
                    BPCSFECT.CT_STS = '1';
                } else {
                    CEP.TRC(SCCGWA, "FEE CONTRACT STATUS:COMPLETE");
                    BPCSFECT.CT_STS = '2';
                }
            }
        } else {
            BPCSFECT.CT_STS = '0';
        }
        BPCSFECT.REMARK = BPB1231_AWA_1231.DESC;
        BPCSFECT.PRDT_TYP = BPB1231_AWA_1231.PRD_CODE;
        if (BPB1231_AWA_1231.PRD_CODE.trim().length() == 0) {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = BPB1231_AWA_1231.PRD_AC_R;
            S000_CALL_CIZACCU();
            BPCSFECT.PRDT_TYP = CICACCU.DATA.PROD_CD;
        } else {
            IBS.init(SCCGWA, BPCPCKPD);
            CEP.TRC(SCCGWA, BPB1231_AWA_1231.PRD_CODE);
            BPCPCKPD.PRDT_CODE = BPB1231_AWA_1231.PRD_CODE;
            BPCPCKPD.CI_NO = BPB1231_AWA_1231.CI_NO;
            S000_CALL_BPZPCKPD();
            BPCSFECT.PRDT_TYP = BPB1231_AWA_1231.PRD_CODE;
        }
        BPCSFECT.FUNC_CODE = 'A';
        S000_CALL_BPZSFECT();
        CEP.TRC(SCCGWA, BPCSFECT.CTNO);
        IBS.init(SCCGWA, BPCF231);
        BPCF231.DATA.CTRT_NO = BPCSFECT.CTNO;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "BP231";
        SCCFMT.DATA_PTR = BPCF231;
        SCCFMT.DATA_LEN = 15;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQPRD.PRDT_CODE);
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
    }
    public void S000_CALL_BPZPCKPD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPCKPD.PRDT_CODE);
        IBS.CALLCPN(SCCGWA, "BP-P-PRDT-COM-CHECK", BPCPCKPD);
        CEP.TRC(SCCGWA, BPCPCKPD.RC.RC_CODE);
        if (BPCPCKPD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPCKPD.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZSFECT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-FECT-MAINT", BPCSFECT, true);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
