package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class IBZACINS {
    BigDecimal bigD;
    String JIBS_tmp_str[] = new String[10];
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    String K_OUTPUT_FMT = "IBE11";
    String WS_TABLE_NAME = " ";
    double WS_ROUND_INT = 0;
    char WS_TABLE_REC = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCQINFS IBCQINFS = new IBCQINFS();
    IBCOCINS IBCOCINS = new IBCOCINS();
    IBCRODE IBCRODE = new IBCRODE();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBCACINS IBCACINS;
    public void MP(SCCGWA SCCGWA, IBCACINS IBCACINS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCACINS = IBCACINS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "IBZACINS return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_INQ_PROC();
        B030_OUTPUT_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCACINS.PRIM_AC_NO);
        CEP.TRC(SCCGWA, IBCACINS.NOSTR_CD);
        CEP.TRC(SCCGWA, IBCACINS.CCY);
        if (IBCACINS.PRIM_AC_NO.trim().length() == 0 
            && (IBCACINS.NOSTR_CD.trim().length() == 0 
            || IBCACINS.CCY.trim().length() == 0)) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE);
        }
    }
    CEP.TRC(SCCGWA, IBCACINS.SEQ_NO);
    if (IBCACINS.SEQ_NO == 0) {
        CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.SEQ_MUST_INPUT);
    }
    public void B020_INQ_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINFS);
        if (IBCACINS.PRIM_AC_NO.trim().length() > 0) {
            IBCQINFS.PRIM_AC_NO = IBCACINS.PRIM_AC_NO;
        } else {
            IBCQINFS.NOSTR_CD = IBCACINS.NOSTR_CD;
            IBCQINFS.CCY = IBCACINS.CCY;
        }
        IBCQINFS.SEQ_NO = IBCACINS.SEQ_NO;
        S000_CALL_IBZQINFS();
        if (IBCQINFS.POST_CTR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH 
            && IBCACINS.FUNC == 'I' 
            && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != 706660500) {
            B020_01_GET_AC_INFO();
        }
        if (IBCACINS.FUNC != 'I' 
            && IBCACINS.FUNC != 'B' 
            && IBCQINFS.STS == 'C') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_CLOSE);
        }
    }
    public void B020_01_GET_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = IBCQINFS.POST_CTR;
        S000_CALL_BPZPQORG();
        if ((BPCPQORG.VIL_TYP.equalsIgnoreCase("51") 
            && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != 706670500) 
            || (BPCPQORG.VIL_TYP.equalsIgnoreCase("52") 
            && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != 706671500) 
            || (BPCPQORG.VIL_TYP.equalsIgnoreCase("53") 
            && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != 706672500) 
            || (BPCPQORG.VIL_TYP.equalsIgnoreCase("54") 
            && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != 706673500)) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.INQ_NOT_ALLOW);
        }
    }
    public void B030_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCOCINS);
        IBCOCINS.PRIM_AC_NO = IBCQINFS.PRIM_AC_NO;
        IBCOCINS.SEQ_NO = IBCQINFS.SEQ_NO;
        IBCOCINS.POST_CTR = IBCQINFS.POST_CTR;
        IBCOCINS.STS = IBCQINFS.STS;
        IBCOCINS.STSW = IBCQINFS.STSW;
        IBCOCINS.NOSTR_CD = IBCQINFS.NOSTR_CD;
        IBCOCINS.CCY = IBCQINFS.CCY;
        IBCOCINS.CUSTNME = IBCQINFS.CUSTNME;
        IBCOCINS.OIC_NO = IBCQINFS.OIC_NO;
        IBCOCINS.RESP_CD = IBCQINFS.RESP_CD;
        IBCOCINS.SUB_DP = IBCQINFS.SUB_DP;
        IBCOCINS.OWNER_BK = IBCQINFS.OWNER_BK;
        IBCOCINS.OPEN_BR = IBCQINFS.OPEN_BR;
        IBCOCINS.OPEN_DATE = IBCQINFS.OPEN_DATE;
        IBCOCINS.OPEN_TLR = IBCQINFS.OPEN_TLR;
        IBCOCINS.PROD_CD = IBCQINFS.PROD_CD;
        IBCOCINS.CORR_BK_CD = IBCQINFS.CORR_BK_CD;
        IBCOCINS.CORR_AC_NO = IBCQINFS.CORR_AC_NO;
        IBCOCINS.CORR_DEPR_NO = IBCQINFS.CORR_DEPR_NO;
        IBCOCINS.INTS_AC_TYPE = IBCQINFS.INTS_AC_TYPE;
        IBCOCINS.INTS_AC_NO = IBCQINFS.INTS_AC_NO;
        IBCOCINS.FUND_ATTR = IBCQINFS.FUND_ATTR;
        IBCOCINS.AC_NATR = IBCQINFS.AC_NATR;
        IBCOCINS.VALUE_TAX = IBCQINFS.VALUE_TAX;
        if (IBCQINFS.CALR_STD == 360) {
            IBCOCINS.CALR_STD = "01";
        } else if (IBCQINFS.CALR_STD == 365) {
            IBCOCINS.CALR_STD = "02";
        } else if (IBCQINFS.CALR_STD == 366) {
            IBCOCINS.CALR_STD = "03";
        }
        IBCOCINS.RATE = IBCQINFS.RATE;
        IBCOCINS.ADV_RATE = IBCQINFS.ADV_RATE;
        IBCOCINS.OVD_RATE = IBCQINFS.OVD_RATE;
        IBCOCINS.VAL_DATE = IBCQINFS.VAL_DATE;
        IBCOCINS.EXP_DATE = IBCQINFS.EXP_DATE;
        IBCOCINS.PVAL_DATE = IBCQINFS.PVAL_DATE;
        IBCOCINS.RATE_MTH = IBCQINFS.RATE_MTH;
        IBCOCINS.INTS_CYC = IBCQINFS.INTS_CYC;
        IBCOCINS.OPEN_BAL = IBCQINFS.OPEN_BAL;
        IBCOCINS.CURR_BAL = IBCQINFS.CURR_BAL;
        IBCOCINS.LBAL = IBCQINFS.LBAL;
        IBCOCINS.LAST_FI_DATE = IBCQINFS.LAST_FI_DATE;
        WS_ROUND_INT = IBCQINFS.EXP_INT;
        B030_01_ROUND_PROC();
        IBCOCINS.EXP_INT = IBCRODE.INT_AMT;
        WS_ROUND_INT = IBCQINFS.DRW_INT;
        B030_01_ROUND_PROC();
        IBCOCINS.DRW_INT = IBCRODE.INT_AMT;
        WS_ROUND_INT = IBCQINFS.BUD_INT;
        B030_01_ROUND_PROC();
        IBCOCINS.BUD_INT = IBCRODE.INT_AMT;
        IBCOCINS.EXP_INT = IBCQINFS.EXP_INT * 1;
        bigD = new BigDecimal(IBCOCINS.EXP_INT);
        IBCOCINS.EXP_INT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
        IBCOCINS.DRW_INT = IBCQINFS.DRW_INT * 1;
        bigD = new BigDecimal(IBCOCINS.DRW_INT);
        IBCOCINS.DRW_INT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
        IBCOCINS.BUD_INT = IBCQINFS.BUD_INT * 1;
        bigD = new BigDecimal(IBCOCINS.BUD_INT);
        IBCOCINS.BUD_INT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
        IBCOCINS.LSET_DATE = IBCQINFS.LSET_DATE;
        IBCOCINS.CLOS_DATE = IBCQINFS.CLOS_DATE;
        IBCOCINS.AUTH_TLR = IBCQINFS.AUTH_TLR;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = IBCOCINS;
        SCCFMT.DATA_LEN = 729;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B030_01_ROUND_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCRODE);
        IBCRODE.INT_AMT = WS_ROUND_INT;
        IBCRODE.CCY = IBCQINFS.CCY;
        S000_CALL_IBZRODE();
        CEP.TRC(SCCGWA, IBCRODE.INT_AMT);
    }
    public void S000_CALL_IBZQINFS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINFS", IBCQINFS);
        CEP.TRC(SCCGWA, IBCQINFS.RC.RC_CODE);
        if (IBCQINFS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINFS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_IBZRODE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-ROUND-INT", IBCRODE);
        if (IBCRODE.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCRODE.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
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
