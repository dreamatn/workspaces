package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4762 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP466";
    String K_CPN_S_HCHK_MAINTAIN = "BP-S-HCHK-MAINTAIN  ";
    String CPN_BP_I_INQ_CNTY = "BP-I-INQ-CNTY       ";
    String CPN_BP_I_INQ_CITY = "BP-I-INQ-CITY       ";
    String CPN_BP_I_INQ_CNCI = "BP-Q-CNTY-CITY-IFO  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_TMP_CNTY_CD = "    ";
    String WS_TMP_CITY_CD = "      ";
    char WS_CHECK_CD_NO = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSHCHK BPCSHCHK = new BPCSHCHK();
    BPCIQCNT BPCIQCNT = new BPCIQCNT();
    BPCIQCIT BPCIQCIT = new BPCIQCIT();
    BPCQCNCI BPCQCNCI = new BPCQCNCI();
    SCCGWA SCCGWA;
    BPB4760_AWA_4760 BPB4760_AWA_4760;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4762 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4760_AWA_4760>");
        BPB4760_AWA_4760 = (BPB4760_AWA_4760) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_ADD_HCHK_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB4760_AWA_4760.CNTY_CD1.trim().length() == 0 
            && BPB4760_AWA_4760.CITY_CD1.trim().length() > 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CNTY_MUST_INPUT;
            WS_FLD_NO = BPB4760_AWA_4760.CNTY_CD1_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB4760_AWA_4760.CNTY_CD2.trim().length() == 0 
            && BPB4760_AWA_4760.CITY_CD2.trim().length() > 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CNTY_MUST_INPUT;
            WS_FLD_NO = BPB4760_AWA_4760.CNTY_CD2_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB4760_AWA_4760.CNTY_CD3.trim().length() == 0 
            && BPB4760_AWA_4760.CITY_CD3.trim().length() > 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CNTY_MUST_INPUT;
            WS_FLD_NO = BPB4760_AWA_4760.CNTY_CD3_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB4760_AWA_4760.CNTY_CD4.trim().length() == 0 
            && BPB4760_AWA_4760.CITY_CD4.trim().length() > 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CNTY_MUST_INPUT;
            WS_FLD_NO = BPB4760_AWA_4760.CNTY_CD4_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB4760_AWA_4760.CNTY_CD1.trim().length() == 0 
            && BPB4760_AWA_4760.CITY_CD1.trim().length() == 0 
            && BPB4760_AWA_4760.CNTY_CD2.trim().length() == 0 
            && BPB4760_AWA_4760.CITY_CD2.trim().length() == 0) {
        } else {
            if (BPB4760_AWA_4760.CNTY_CD1.equalsIgnoreCase(BPB4760_AWA_4760.CNTY_CD2) 
                && BPB4760_AWA_4760.CITY_CD1.equalsIgnoreCase(BPB4760_AWA_4760.CITY_CD2)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DUP_CODE;
                WS_FLD_NO = BPB4760_AWA_4760.CNTY_CD2_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB4760_AWA_4760.CNTY_CD1.trim().length() == 0 
            && BPB4760_AWA_4760.CITY_CD1.trim().length() == 0 
            && BPB4760_AWA_4760.CNTY_CD3.trim().length() == 0 
            && BPB4760_AWA_4760.CITY_CD3.trim().length() == 0) {
        } else {
            if (BPB4760_AWA_4760.CNTY_CD1.equalsIgnoreCase(BPB4760_AWA_4760.CNTY_CD3) 
                && BPB4760_AWA_4760.CITY_CD1.equalsIgnoreCase(BPB4760_AWA_4760.CITY_CD3)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DUP_CODE;
                WS_FLD_NO = BPB4760_AWA_4760.CNTY_CD3_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB4760_AWA_4760.CNTY_CD1.trim().length() == 0 
            && BPB4760_AWA_4760.CITY_CD1.trim().length() == 0 
            && BPB4760_AWA_4760.CNTY_CD4.trim().length() == 0 
            && BPB4760_AWA_4760.CITY_CD4.trim().length() == 0) {
        } else {
            if (BPB4760_AWA_4760.CNTY_CD1.equalsIgnoreCase(BPB4760_AWA_4760.CNTY_CD4) 
                && BPB4760_AWA_4760.CITY_CD1.equalsIgnoreCase(BPB4760_AWA_4760.CITY_CD4)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DUP_CODE;
                WS_FLD_NO = BPB4760_AWA_4760.CNTY_CD4_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB4760_AWA_4760.CNTY_CD2.trim().length() == 0 
            && BPB4760_AWA_4760.CITY_CD2.trim().length() == 0 
            && BPB4760_AWA_4760.CNTY_CD3.trim().length() == 0 
            && BPB4760_AWA_4760.CITY_CD3.trim().length() == 0) {
        } else {
            if (BPB4760_AWA_4760.CNTY_CD2.equalsIgnoreCase(BPB4760_AWA_4760.CNTY_CD3) 
                && BPB4760_AWA_4760.CITY_CD2.equalsIgnoreCase(BPB4760_AWA_4760.CITY_CD3)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DUP_CODE;
                WS_FLD_NO = BPB4760_AWA_4760.CNTY_CD3_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB4760_AWA_4760.CNTY_CD2.trim().length() == 0 
            && BPB4760_AWA_4760.CITY_CD2.trim().length() == 0 
            && BPB4760_AWA_4760.CNTY_CD4.trim().length() == 0 
            && BPB4760_AWA_4760.CITY_CD4.trim().length() == 0) {
        } else {
            if (BPB4760_AWA_4760.CNTY_CD2.equalsIgnoreCase(BPB4760_AWA_4760.CNTY_CD4) 
                && BPB4760_AWA_4760.CITY_CD2.equalsIgnoreCase(BPB4760_AWA_4760.CITY_CD4)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DUP_CODE;
                WS_FLD_NO = BPB4760_AWA_4760.CNTY_CD4_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB4760_AWA_4760.CNTY_CD3.trim().length() == 0 
            && BPB4760_AWA_4760.CITY_CD3.trim().length() == 0 
            && BPB4760_AWA_4760.CNTY_CD4.trim().length() == 0 
            && BPB4760_AWA_4760.CITY_CD4.trim().length() == 0) {
        } else {
            if (BPB4760_AWA_4760.CNTY_CD3.equalsIgnoreCase(BPB4760_AWA_4760.CNTY_CD4) 
                && BPB4760_AWA_4760.CITY_CD3.equalsIgnoreCase(BPB4760_AWA_4760.CITY_CD4)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DUP_CODE;
                WS_FLD_NO = BPB4760_AWA_4760.CNTY_CD4_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB4760_AWA_4760.CNTY_CD1.trim().length() == 0 
            && BPB4760_AWA_4760.CITY_CD1.trim().length() == 0) {
            if (BPB4760_AWA_4760.CNTY_CD2.trim().length() > 0 
                || BPB4760_AWA_4760.CITY_CD2.trim().length() > 0 
                || BPB4760_AWA_4760.CNTY_CD3.trim().length() > 0 
                || BPB4760_AWA_4760.CITY_CD3.trim().length() > 0 
                || BPB4760_AWA_4760.CNTY_CD4.trim().length() > 0 
                || BPB4760_AWA_4760.CITY_CD4.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_SKIP_ARARY;
                WS_FLD_NO = BPB4760_AWA_4760.CNTY_CD1_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB4760_AWA_4760.CNTY_CD2.trim().length() == 0 
            && BPB4760_AWA_4760.CITY_CD2.trim().length() == 0) {
            if (BPB4760_AWA_4760.CNTY_CD3.trim().length() > 0 
                || BPB4760_AWA_4760.CITY_CD3.trim().length() > 0 
                || BPB4760_AWA_4760.CNTY_CD4.trim().length() > 0 
                || BPB4760_AWA_4760.CITY_CD4.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_SKIP_ARARY;
                WS_FLD_NO = BPB4760_AWA_4760.CNTY_CD2_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB4760_AWA_4760.CNTY_CD3.trim().length() == 0 
            && BPB4760_AWA_4760.CITY_CD3.trim().length() == 0) {
            if (BPB4760_AWA_4760.CNTY_CD4.trim().length() > 0 
                || BPB4760_AWA_4760.CITY_CD4.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_SKIP_ARARY;
                WS_FLD_NO = BPB4760_AWA_4760.CNTY_CD3_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB4760_AWA_4760.CNTY_CD1.trim().length() > 0 
            && BPB4760_AWA_4760.CITY_CD1.trim().length() > 0) {
            WS_TMP_CNTY_CD = BPB4760_AWA_4760.CNTY_CD1;
            WS_TMP_CITY_CD = BPB4760_AWA_4760.CITY_CD1;
            WS_CHECK_CD_NO = '1';
            B010_01_CHK_PARM_VALID();
        }
        if (BPB4760_AWA_4760.CNTY_CD2.trim().length() > 0 
            && BPB4760_AWA_4760.CITY_CD2.trim().length() > 0) {
            WS_TMP_CNTY_CD = BPB4760_AWA_4760.CNTY_CD2;
            WS_TMP_CITY_CD = BPB4760_AWA_4760.CITY_CD2;
            WS_CHECK_CD_NO = '2';
            B010_01_CHK_PARM_VALID();
        }
        if (BPB4760_AWA_4760.CNTY_CD3.trim().length() > 0 
            && BPB4760_AWA_4760.CITY_CD3.trim().length() > 0) {
            WS_TMP_CNTY_CD = BPB4760_AWA_4760.CNTY_CD3;
            WS_TMP_CITY_CD = BPB4760_AWA_4760.CITY_CD3;
            WS_CHECK_CD_NO = '3';
            B010_01_CHK_PARM_VALID();
        }
        if (BPB4760_AWA_4760.CNTY_CD4.trim().length() > 0 
            && BPB4760_AWA_4760.CITY_CD4.trim().length() > 0) {
            WS_TMP_CNTY_CD = BPB4760_AWA_4760.CNTY_CD4;
            WS_TMP_CITY_CD = BPB4760_AWA_4760.CITY_CD4;
            WS_CHECK_CD_NO = '4';
            B010_01_CHK_PARM_VALID();
        }
        if (BPB4760_AWA_4760.RESI_FLG == 'N' 
            && BPB4760_AWA_4760.CUR_FLG == 'N' 
            && BPB4760_AWA_4760.BRAN_FLG == 'N' 
            && BPB4760_AWA_4760.CNTY_CD1.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CHECK_CODE_NULL_DEF;
            WS_FLD_NO = BPB4760_AWA_4760.RESI_FLG_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B010_01_CHK_PARM_VALID() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCNCI);
        BPCQCNCI.INPUT_DAT.CNTY_CD = WS_TMP_CNTY_CD;
        BPCQCNCI.INPUT_DAT.CITY_CD = WS_TMP_CITY_CD;
        S000_CALL_BPZQCNCI();
        CEP.TRC(SCCGWA, WS_TMP_CNTY_CD);
        CEP.TRC(SCCGWA, WS_TMP_CITY_CD);
        CEP.TRC(SCCGWA, BPCQCNCI.RC.RC_CODE);
        if (BPCQCNCI.RC.RC_CODE > 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCNCI.RC);
            if (WS_CHECK_CD_NO == '1') {
                WS_FLD_NO = BPB4760_AWA_4760.CNTY_CD1_NO;
            } else if (WS_CHECK_CD_NO == '2') {
                WS_FLD_NO = BPB4760_AWA_4760.CNTY_CD2_NO;
            } else if (WS_CHECK_CD_NO == '3') {
                WS_FLD_NO = BPB4760_AWA_4760.CNTY_CD3_NO;
            } else if (WS_CHECK_CD_NO == '4') {
                WS_FLD_NO = BPB4760_AWA_4760.CNTY_CD4_NO;
            } else {
            }
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_ADD_HCHK_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSHCHK);
        BPCSHCHK.FUNC = 'A';
        BPCSHCHK.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSHCHK();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCSHCHK.FUNC_CODE = 'A';
        BPCSHCHK.CD = BPB4760_AWA_4760.HCHK_CD;
        BPCSHCHK.DESC = BPB4760_AWA_4760.DESC;
        BPCSHCHK.CDESC = BPB4760_AWA_4760.CDESC;
        BPCSHCHK.RESI_FLG = BPB4760_AWA_4760.RESI_FLG;
        BPCSHCHK.CUR_FLG = BPB4760_AWA_4760.CUR_FLG;
        BPCSHCHK.BRAN_FLG = BPB4760_AWA_4760.BRAN_FLG;
        BPCSHCHK.CNTY_CD1 = BPB4760_AWA_4760.CNTY_CD1;
        BPCSHCHK.CITY_CD1 = BPB4760_AWA_4760.CITY_CD1;
        BPCSHCHK.CNTY_CD2 = BPB4760_AWA_4760.CNTY_CD2;
        BPCSHCHK.CITY_CD2 = BPB4760_AWA_4760.CITY_CD2;
        BPCSHCHK.CNTY_CD3 = BPB4760_AWA_4760.CNTY_CD3;
        BPCSHCHK.CITY_CD3 = BPB4760_AWA_4760.CITY_CD3;
        BPCSHCHK.CNTY_CD4 = BPB4760_AWA_4760.CNTY_CD4;
        BPCSHCHK.CITY_CD4 = BPB4760_AWA_4760.CITY_CD4;
        BPCSHCHK.UPT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCSHCHK.UPT_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void S000_CALL_BPZSHCHK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CPN_S_HCHK_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCSHCHK;
        SCCCALL.ERR_FLDNO = BPB4760_AWA_4760.HCHK_CD_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCSHCHK.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSHCHK.RC);
            WS_FLD_NO = BPB4760_AWA_4760.HCHK_CD_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZQCNCI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_I_INQ_CNCI, BPCQCNCI);
    }
    public void S000_CALL_BPZIQCNT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_I_INQ_CNTY, BPCIQCNT);
    }
    public void S000_CALL_BPZIQCIT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_I_INQ_CITY, BPCIQCIT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
