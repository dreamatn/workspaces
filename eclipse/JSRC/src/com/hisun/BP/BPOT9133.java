package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;
import com.hisun.LN.*;
import com.hisun.IB.*;
import com.hisun.AI.*;
import com.hisun.DC.*;
import com.hisun.DD.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9133 {
    int JIBS_tmp_int;
    String K_FEE_CODE = "FII01";
    String K_STS_TABLE_APP = "DD";
    String K_CUS_DR_STS_TBL = "0016";
    String K_CUS_CR_STS_TBL = "0016";
    String K_STS_TABLE_DC = "DC";
    String K_DC_DR_STS_TBL = "0002";
    String K_DC_CR_STS_TBL = "0001";
    double K_TOT_PCT = 1;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT = 0;
    short WS_CNT1 = 0;
    short WS_CNT2 = 0;
    char WS_CUS_TYPE = ' ';
    int WS_GLMST1 = 0;
    double WS_TOT_PCT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSFSCH BPCSFSCH = new BPCSFSCH();
    CICCUST CICCUST = new CICCUST();
    BPCOFBAS BPCOUBAS = new BPCOFBAS();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCOCCAL BPCOCCAL = new BPCOCCAL();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    BPCQCCY BPCQCCY = new BPCQCCY();
    LNCSIQIF LNCSIQIF = new LNCSIQIF();
    CICACCU CICACCU = new CICACCU();
    IBCQINF IBCQINF = new IBCQINF();
    AICPQMIB AICPQMIB = new AICPQMIB();
    BPCCNGL BPCICNGL = new BPCCNGL();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    BPCUGLM BPCUGLM = new BPCUGLM();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DDCSCINM DDCSCINM = new DDCSCINM();
    DDCSBCHQ DDCSBCHQ = new DDCSBCHQ();
    DDCIMCCY DDCIMCCY = new DDCIMCCY();
    DDCSQCHQ DDCSQCHQ = new DDCSQCHQ();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    DDCIMMST DDCIMMST = new DDCIMMST();
    DCCPFTCK DCCPFTCK = new DCCPFTCK();
    DCCIMSTR DCCIMSTR = new DCCIMSTR();
    DDCSQPB DDCSQPB = new DDCSQPB();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB9133_AWA_9133 BPB9133_AWA_9133;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9133 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB9133_AWA_9133>");
        BPB9133_AWA_9133 = (BPB9133_AWA_9133) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT_CN();
        } else {
            B010_CHECK_INPUT();
        }
        B020_UPD_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.FEE_STS);
        if (BPB9133_AWA_9133.FEE_STS == '3') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSCH_DELETED;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.CI_NO);
        if (BPB9133_AWA_9133.CI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = BPB9133_AWA_9133.CI_NO;
            CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
            CICCUST.FUNC = 'C';
            S000_CALL_CIZCUST();
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.FEE_TYPE);
        if (BPB9133_AWA_9133.FEE_TYPE.trim().length() > 0) {
            IBS.init(SCCGWA, BPCOUBAS);
            BPCOUBAS.KEY.FEE_CODE = BPB9133_AWA_9133.FEE_TYPE;
            BPCOUBAS.FUNC = 'I';
            S000_CALL_BPZFUBAS();
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.BK_ACCT);
        if (BPB9133_AWA_9133.BK_ACCT != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPB9133_AWA_9133.BK_ACCT;
            S000_CALL_BPZPQORG();
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.STA_DATE);
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.MAT_DATE);
        if (BPB9133_AWA_9133.MAT_DATE <= BPB9133_AWA_9133.STA_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MATUR_DT_LESS;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB9133_AWA_9133.MAT_DATE > 20991231) {
            CEP.TRC(SCCGWA, "AAAAAAAAAAAAAA");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MATUR_DT_ERR;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.REL_SRC);
        if (!BPB9133_AWA_9133.FEE_TYPE.equalsIgnoreCase(K_FEE_CODE)) {
            CEP.TRC(SCCGWA, "CHECK REL-SRC");
            if (BPB9133_AWA_9133.REL_SRC == '0') {
                R100_CHK_LOAN_CTR();
            } else {
            }
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.FIRST_DT);
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.FEE_TYPE);
        if (BPB9133_AWA_9133.FIRST_DT <= BPB9133_AWA_9133.STA_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
            WS_FLD_NO = BPB9133_AWA_9133.FIRST_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.CAL_CD1);
        if (BPB9133_AWA_9133.CAL_CD1.trim().length() > 0) {
            CEP.TRC(SCCGWA, BPB9133_AWA_9133.CAL_CD1);
            IBS.init(SCCGWA, BPCOCCAL);
            BPCOCCAL.CAL_CODE = BPB9133_AWA_9133.CAL_CD1;
            S000_CALL_BPZPCCAL();
            if (BPB9133_AWA_9133.HOL_OVER == 'N') {
                IBS.init(SCCGWA, BPCOCLWD);
                BPCOCLWD.DATE1 = BPB9133_AWA_9133.STA_DATE;
                BPCOCLWD.DATE2 = BPB9133_AWA_9133.MAT_DATE;
                BPCOCLWD.CAL_CODE = BPB9133_AWA_9133.CAL_CD1;
                BPCOCLWD.DAYE_FLG = 'Y';
                BPCOCLWD.WDAYS = 2;
                S000_CALL_BPZPCLWD();
                if (BPCOCLWD.DATE1_FLG == 'H') {
                    CEP.TRC(SCCGWA, BPB9133_AWA_9133.STA_DATE);
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_F_DATE_IS_HOLI;
                    S000_ERR_MSG_PROC();
                }
                if (BPCOCLWD.DATE2_FLG == 'H') {
                    CEP.TRC(SCCGWA, BPB9133_AWA_9133.MAT_DATE);
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_F_DATE_IS_HOLI;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        if (BPB9133_AWA_9133.CAL_CD2.trim().length() > 0) {
            CEP.TRC(SCCGWA, BPB9133_AWA_9133.CAL_CD2);
            IBS.init(SCCGWA, BPCOCCAL);
            BPCOCCAL.CAL_CODE = BPB9133_AWA_9133.CAL_CD2;
            S000_CALL_BPZPCCAL();
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.PORT_CD);
        if (BPB9133_AWA_9133.PORT_CD.trim().length() > 0) {
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.TXN_CCY);
        if (BPB9133_AWA_9133.TXN_CCY.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = BPB9133_AWA_9133.TXN_CCY;
            WS_CNT2 = 1;
            S000_CALL_BPZQCCY();
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.INT_BAS);
        if (BPB9133_AWA_9133.INT_BAS.trim().length() > 0) {
            if (!BPB9133_AWA_9133.INT_BAS.equalsIgnoreCase("01") 
                && !BPB9133_AWA_9133.INT_BAS.equalsIgnoreCase("02") 
                && !BPB9133_AWA_9133.INT_BAS.equalsIgnoreCase("03")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
                WS_FLD_NO = BPB9133_AWA_9133.INT_BAS_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.REF_CCY);
        if (BPB9133_AWA_9133.REF_CCY.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = BPB9133_AWA_9133.REF_CCY;
            WS_CNT2 = 2;
            S000_CALL_BPZQCCY();
        }
        for (WS_CNT1 = 2; WS_CNT1 <= 5; WS_CNT1 += 1) {
            WS_CNT = (short) (WS_CNT1 - 1);
            if (BPB9133_AWA_9133.REF_MTH == '0') {
                CEP.TRC(SCCGWA, BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].REF_AMT);
                if (BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].REF_AMT != 0 
                    && BPB9133_AWA_9133.REF_DATE[WS_CNT-1].REF_AMT == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
                    WS_FLD_NO = BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].REF_AMT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            } else {
                CEP.TRC(SCCGWA, BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].REF_AMT);
                if (BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].REF_PCT != 0 
                    && BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].REF_AMT < BPB9133_AWA_9133.REF_DATE[WS_CNT-1].REF_AMT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
                    WS_FLD_NO = BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].REF_AMT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
        }
        if (BPB9133_AWA_9133.REF_DATE[1-1].REF_AMT != 0 
            && BPB9133_AWA_9133.REF_DATE[2-1].REF_AMT != 0 
            || (BPB9133_AWA_9133.REF_DATE[1-1].REF_AMT < 99999999999999.99 
            && BPB9133_AWA_9133.REF_MTH == '0')) {
            for (WS_CNT1 = 2; WS_CNT1 <= 5 
                && BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].REF_AMT != 0; WS_CNT1 += 1) {
                WS_CNT = (short) (WS_CNT1 - 1);
                CEP.TRC(SCCGWA, BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].REF_AMT);
                if (BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].REF_AMT > BPB9133_AWA_9133.REF_DATE[WS_CNT-1].REF_AMT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_AMT_MUST_INC;
                    WS_FLD_NO = BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].REF_AMT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
            WS_CNT = (short) (WS_CNT1 - 1);
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, BPB9133_AWA_9133.REF_DATE[WS_CNT-1].REF_AMT);
            if (BPB9133_AWA_9133.REF_DATE[1-1].REF_AMT < 99999999999999.99) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_AMT_ERR;
                WS_FLD_NO = BPB9133_AWA_9133.REF_DATE[1-1].REF_AMT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.REF_MTH);
        if (BPB9133_AWA_9133.REF_DATE[1-1].REF_PCT != 0 
            && BPB9133_AWA_9133.REF_DATE[2-1].REF_PCT != 0 
            || (BPB9133_AWA_9133.REF_DATE[1-1].REF_PCT != 100 
            && BPB9133_AWA_9133.REF_MTH == '1')) {
            for (WS_CNT1 = 2; WS_CNT1 <= 5 
                && BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].REF_PCT != 0; WS_CNT1 += 1) {
                WS_CNT = (short) (WS_CNT1 - 1);
                CEP.TRC(SCCGWA, BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].REF_PCT);
                if (BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].REF_PCT < BPB9133_AWA_9133.REF_DATE[WS_CNT-1].REF_PCT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_PCT_MUST_INPUT;
                    WS_FLD_NO = (short) BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].REF_PCT;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
            WS_CNT = (short) (WS_CNT1 - 1);
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, BPB9133_AWA_9133.REF_DATE[WS_CNT-1].REF_PCT);
            if (BPB9133_AWA_9133.REF_DATE[WS_CNT-1].REF_PCT != 100) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LAST_PCT_MUST_100;
                WS_FLD_NO = (short) BPB9133_AWA_9133.REF_DATE[WS_CNT-1].REF_PCT;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.CHG_AMT);
        if (BPB9133_AWA_9133.CHG_AMT == 0) {
            CEP.TRC(SCCGWA, BPB9133_AWA_9133.REF_DATE[1-1].REF_AMT);
            if (BPB9133_AWA_9133.REF_DATE[1-1].REF_AMT == 0 
                && BPB9133_AWA_9133.REF_DATE[1-1].REF_PCT == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_AMT_MUST_INPUT;
                WS_FLD_NO = BPB9133_AWA_9133.REF_DATE[1-1].REF_AMT_NO;
                if (BPB9133_AWA_9133.REF_MTH == '1') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_PCT_MUST_INPUT;
                    WS_FLD_NO = BPB9133_AWA_9133.REF_DATE[1-1].REF_PCT_NO;
                }
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (BPB9133_AWA_9133.TXN_AMT == 0 
                && BPB9133_AWA_9133.AMT_TYP == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_AMT_MUST_INPUT;
                WS_FLD_NO = BPB9133_AWA_9133.CHG_AMT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.REF_DATE[1-1].AGG_MTH);
        for (WS_CNT1 = 2; WS_CNT1 <= 5; WS_CNT1 += 1) {
            WS_CNT = (short) (WS_CNT1 - 1);
            CEP.TRC(SCCGWA, BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].AGG_MTH);
            if (BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].AGG_MTH != ' ' 
                && BPB9133_AWA_9133.REF_DATE[WS_CNT-1].AGG_MTH == ' ') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
                WS_FLD_NO = BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].AGG_MTH_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.CHG_CCY);
        if (BPB9133_AWA_9133.CHG_CCY.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = BPB9133_AWA_9133.CHG_CCY;
            WS_CNT2 = 3;
            S000_CALL_BPZQCCY();
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.CHG_MTH);
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.CHG_AC);
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.NOSTR_CD);
        if (BPB9133_AWA_9133.CHG_MTH != ' ') {
            if (BPB9133_AWA_9133.CHG_MTH == '0') {
                IBS.init(SCCGWA, CICACCU);
                CICACCU.DATA.ENTY_TYP = '1';
                CICACCU.DATA.AGR_NO = BPB9133_AWA_9133.CHG_AC;
                S000_CALL_CIZACCU();
            }
            if (BPB9133_AWA_9133.CHG_MTH == '3') {
                IBS.init(SCCGWA, AICPQMIB);
                AICPQMIB.INPUT_DATA.AC = BPB9133_AWA_9133.CHG_AC;
                S000_CALL_AIZPQMIB();
            }
        }
        if (BPB9133_AWA_9133.FIR_DSCT > 100) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PCNT_ERROR;
            WS_FLD_NO = BPB9133_AWA_9133.FIR_DSCT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, "RRRRRRRRRRRRRRRR");
    }
    public void B010_CHECK_INPUT_CN() throws IOException,SQLException,Exception {
        if (BPB9133_AWA_9133.CHG_MTH == '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CHG_MTH_ERR;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.FEE_STS);
        if (BPB9133_AWA_9133.FEE_STS == '3') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSCH_DELETED;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.CI_NO);
        if (BPB9133_AWA_9133.CI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = BPB9133_AWA_9133.CI_NO;
            CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
            CICCUST.FUNC = 'C';
            S000_CALL_CIZCUST();
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.FEE_TYPE);
        if (BPB9133_AWA_9133.FEE_TYPE.trim().length() > 0) {
            IBS.init(SCCGWA, BPCOUBAS);
            BPCOUBAS.KEY.FEE_CODE = BPB9133_AWA_9133.FEE_TYPE;
            BPCOUBAS.FUNC = 'I';
            S000_CALL_BPZFUBAS();
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.BK_ACCT);
        if (BPB9133_AWA_9133.BK_ACCT != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPB9133_AWA_9133.BK_ACCT;
            S000_CALL_BPZPQORG();
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.STA_DATE);
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.MAT_DATE);
        if (BPB9133_AWA_9133.MAT_DATE <= BPB9133_AWA_9133.STA_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MATUR_DT_LESS;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB9133_AWA_9133.MAT_DATE > 20991231) {
            CEP.TRC(SCCGWA, "AAAAAAAAAAAAAA");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MATUR_DT_ERR;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.REL_SRC);
        if (!BPB9133_AWA_9133.FEE_TYPE.equalsIgnoreCase(K_FEE_CODE)) {
            CEP.TRC(SCCGWA, "CHECK REL-SRC");
            if (BPB9133_AWA_9133.REL_SRC == '0') {
                R100_CHK_LOAN_CTR();
            } else {
            }
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.FIRST_DT);
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.FEE_TYPE);
        if (BPB9133_AWA_9133.FIRST_DT <= BPB9133_AWA_9133.STA_DATE 
            && BPB9133_AWA_9133.UCT_FLG != 'Y') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
            WS_FLD_NO = BPB9133_AWA_9133.FIRST_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.CAL_CD1);
        if (BPB9133_AWA_9133.CAL_CD1.trim().length() > 0) {
            CEP.TRC(SCCGWA, BPB9133_AWA_9133.CAL_CD1);
            IBS.init(SCCGWA, BPCOCCAL);
            BPCOCCAL.CAL_CODE = BPB9133_AWA_9133.CAL_CD1;
            S000_CALL_BPZPCCAL();
            if (BPB9133_AWA_9133.HOL_OVER == 'N') {
                IBS.init(SCCGWA, BPCOCLWD);
                BPCOCLWD.DATE1 = BPB9133_AWA_9133.STA_DATE;
                BPCOCLWD.DATE2 = BPB9133_AWA_9133.MAT_DATE;
                BPCOCLWD.CAL_CODE = BPB9133_AWA_9133.CAL_CD1;
                BPCOCLWD.DAYE_FLG = 'Y';
                BPCOCLWD.WDAYS = 2;
                S000_CALL_BPZPCLWD();
                if (BPCOCLWD.DATE1_FLG == 'H') {
                    CEP.TRC(SCCGWA, BPB9133_AWA_9133.STA_DATE);
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_F_DATE_IS_HOLI;
                    S000_ERR_MSG_PROC();
                }
                if (BPCOCLWD.DATE2_FLG == 'H') {
                    CEP.TRC(SCCGWA, BPB9133_AWA_9133.MAT_DATE);
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_F_DATE_IS_HOLI;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        if (BPB9133_AWA_9133.CAL_CD2.trim().length() > 0) {
            CEP.TRC(SCCGWA, BPB9133_AWA_9133.CAL_CD2);
            IBS.init(SCCGWA, BPCOCCAL);
            BPCOCCAL.CAL_CODE = BPB9133_AWA_9133.CAL_CD2;
            S000_CALL_BPZPCCAL();
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.PORT_CD);
        if (BPB9133_AWA_9133.PORT_CD.trim().length() > 0) {
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.TXN_CCY);
        if (BPB9133_AWA_9133.TXN_CCY.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = BPB9133_AWA_9133.TXN_CCY;
            WS_CNT2 = 1;
            S000_CALL_BPZQCCY();
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.INT_BAS);
        if (BPB9133_AWA_9133.INT_BAS.trim().length() > 0) {
            if (!BPB9133_AWA_9133.INT_BAS.equalsIgnoreCase("01") 
                && !BPB9133_AWA_9133.INT_BAS.equalsIgnoreCase("02") 
                && !BPB9133_AWA_9133.INT_BAS.equalsIgnoreCase("03")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
                WS_FLD_NO = BPB9133_AWA_9133.INT_BAS_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.REF_CCY);
        if (BPB9133_AWA_9133.REF_CCY.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = BPB9133_AWA_9133.REF_CCY;
            WS_CNT2 = 2;
            S000_CALL_BPZQCCY();
        }
        for (WS_CNT1 = 2; WS_CNT1 <= 5; WS_CNT1 += 1) {
            WS_CNT = (short) (WS_CNT1 - 1);
            if (BPB9133_AWA_9133.REF_MTH == '0') {
                CEP.TRC(SCCGWA, BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].REF_AMT);
                if (BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].REF_AMT != 0 
                    && BPB9133_AWA_9133.REF_DATE[WS_CNT-1].REF_AMT == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
                    WS_FLD_NO = BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].REF_AMT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            } else {
                CEP.TRC(SCCGWA, BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].REF_AMT);
                if (BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].REF_PCT != 0 
                    && BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].REF_AMT < BPB9133_AWA_9133.REF_DATE[WS_CNT-1].REF_AMT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
                    WS_FLD_NO = BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].REF_AMT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
        }
        if (BPB9133_AWA_9133.REF_DATE[1-1].REF_AMT != 0 
            && BPB9133_AWA_9133.REF_DATE[2-1].REF_AMT != 0 
            || (BPB9133_AWA_9133.REF_DATE[1-1].REF_AMT < 99999999999999.99 
            && BPB9133_AWA_9133.REF_MTH == '0')) {
            for (WS_CNT1 = 2; WS_CNT1 <= 5 
                && BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].REF_AMT != 0; WS_CNT1 += 1) {
                WS_CNT = (short) (WS_CNT1 - 1);
                CEP.TRC(SCCGWA, "AAA");
                CEP.TRC(SCCGWA, BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].REF_AMT);
                CEP.TRC(SCCGWA, BPB9133_AWA_9133.REF_DATE[WS_CNT-1].REF_AMT);
                if (BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].REF_AMT > BPB9133_AWA_9133.REF_DATE[WS_CNT-1].REF_AMT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_AMT_MUST_INC;
                    WS_FLD_NO = BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].REF_AMT_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
            WS_CNT = (short) (WS_CNT1 - 1);
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, BPB9133_AWA_9133.REF_DATE[WS_CNT-1].REF_AMT);
            if (BPB9133_AWA_9133.REF_DATE[1-1].REF_AMT < 99999999999999.99) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_AMT_ERR;
                WS_FLD_NO = BPB9133_AWA_9133.REF_DATE[1-1].REF_AMT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.REF_MTH);
        if (BPB9133_AWA_9133.REF_DATE[1-1].REF_PCT != 0 
            && BPB9133_AWA_9133.REF_DATE[2-1].REF_PCT != 0 
            || (BPB9133_AWA_9133.REF_DATE[1-1].REF_PCT != 100 
            && BPB9133_AWA_9133.REF_MTH == '1')) {
            for (WS_CNT1 = 2; WS_CNT1 <= 5 
                && BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].REF_PCT != 0; WS_CNT1 += 1) {
                WS_CNT = (short) (WS_CNT1 - 1);
                CEP.TRC(SCCGWA, BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].REF_PCT);
                if (BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].REF_PCT < BPB9133_AWA_9133.REF_DATE[WS_CNT-1].REF_PCT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_PCT_MUST_INPUT;
                    WS_FLD_NO = (short) BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].REF_PCT;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
            WS_CNT = (short) (WS_CNT1 - 1);
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, BPB9133_AWA_9133.REF_DATE[WS_CNT-1].REF_PCT);
            if (BPB9133_AWA_9133.REF_DATE[1-1].REF_PCT != 100) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LAST_PCT_MUST_100;
                WS_FLD_NO = (short) BPB9133_AWA_9133.REF_DATE[1-1].REF_PCT;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.CHG_AMT);
        if (BPB9133_AWA_9133.CHG_AMT == 0) {
            CEP.TRC(SCCGWA, BPB9133_AWA_9133.REF_DATE[1-1].REF_AMT);
            if (BPB9133_AWA_9133.REF_DATE[1-1].REF_AMT == 0 
                && BPB9133_AWA_9133.REF_DATE[1-1].REF_PCT == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_AMT_MUST_INPUT;
                WS_FLD_NO = BPB9133_AWA_9133.REF_DATE[1-1].REF_AMT_NO;
                if (BPB9133_AWA_9133.REF_MTH == '1') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UP_PCT_MUST_INPUT;
                    WS_FLD_NO = BPB9133_AWA_9133.REF_DATE[1-1].REF_PCT_NO;
                }
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (BPB9133_AWA_9133.TXN_AMT == 0 
                && BPB9133_AWA_9133.AMT_TYP == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_AMT_MUST_INPUT;
                WS_FLD_NO = BPB9133_AWA_9133.CHG_AMT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.REF_DATE[1-1].AGG_MTH);
        for (WS_CNT1 = 2; WS_CNT1 <= 5; WS_CNT1 += 1) {
            WS_CNT = (short) (WS_CNT1 - 1);
            CEP.TRC(SCCGWA, BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].AGG_MTH);
            if (BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].AGG_MTH != ' ' 
                && BPB9133_AWA_9133.REF_DATE[WS_CNT-1].AGG_MTH == ' ') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
                WS_FLD_NO = BPB9133_AWA_9133.REF_DATE[WS_CNT1-1].AGG_MTH_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.CHG_CCY);
        if (BPB9133_AWA_9133.CHG_CCY.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = BPB9133_AWA_9133.CHG_CCY;
            WS_CNT2 = 3;
            S000_CALL_BPZQCCY();
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.CHG_MTH);
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.CHG_AC);
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.NOSTR_CD);
        if (BPB9133_AWA_9133.CHG_MTH != ' ') {
            if (BPB9133_AWA_9133.CHG_MTH == '0') {
                IBS.init(SCCGWA, CICACCU);
                CICACCU.DATA.ENTY_TYP = '1';
                CICACCU.DATA.AGR_NO = BPB9133_AWA_9133.CHG_AC;
                S000_CALL_CIZACCU();
            }
            if (BPB9133_AWA_9133.CHG_MTH == '3') {
                IBS.init(SCCGWA, AICPQMIB);
                AICPQMIB.INPUT_DATA.AC = BPB9133_AWA_9133.CHG_AC;
                S000_CALL_AIZPQMIB();
            }
            if (BPB9133_AWA_9133.CHG_MTH == '0') {
                IBS.init(SCCGWA, CICACCU);
                CICACCU.DATA.AGR_NO = BPB9133_AWA_9133.CHG_AC;
                S000_CALL_CIZACCU();
            }
            if (BPB9133_AWA_9133.CHG_MTH == '2') {
                IBS.init(SCCGWA, IBCQINF);
                IBCQINF.INPUT_DATA.NOSTRO_CD = BPB9133_AWA_9133.CHG_AC;
                IBCQINF.INPUT_DATA.CCY = BPB9133_AWA_9133.CHG_CCY;
                S000_CALL_IBZQINF();
            }
            if (BPB9133_AWA_9133.CHG_MTH == '0' 
                || BPB9133_AWA_9133.CHG_MTH == '4' 
                || BPB9133_AWA_9133.CHG_MTH == '5') {
                IBS.init(SCCGWA, CICACCU);
                CICACCU.DATA.ENTY_TYP = '1';
                CICACCU.DATA.AGR_NO = BPB9133_AWA_9133.CHG_AC;
                S000_CALL_CIZACCU();
                IBS.init(SCCGWA, DDCIMMST);
                DDCIMMST.DATA.KEY.AC_NO = BPB9133_AWA_9133.CHG_AC;
                DDCIMMST.TX_TYPE = 'I';
                S000_CALL_DDZIMMST();
                if (DDCIMMST.DATA.AC_STS_WORD == null) DDCIMMST.DATA.AC_STS_WORD = "";
                JIBS_tmp_int = DDCIMMST.DATA.AC_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMMST.DATA.AC_STS_WORD += " ";
                CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_STS_WORD.substring(0, 20));
                if (DDCIMMST.DATA.AC_STS_WORD == null) DDCIMMST.DATA.AC_STS_WORD = "";
                JIBS_tmp_int = DDCIMMST.DATA.AC_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMMST.DATA.AC_STS_WORD += " ";
                CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_STS_WORD.substring(21 - 1, 21 + 20 - 1));
                if (DDCIMMST.DATA.AC_STS_WORD == null) DDCIMMST.DATA.AC_STS_WORD = "";
                JIBS_tmp_int = DDCIMMST.DATA.AC_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMMST.DATA.AC_STS_WORD += " ";
                CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_STS_WORD.substring(42 - 1, 42 + 20 - 1));
                CEP.TRC(SCCGWA, CICACCU.DATA.CI_TYP);
                IBS.init(SCCGWA, BPCFCSTS);
                BPCFCSTS.AP_MMO = K_STS_TABLE_APP;
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CANCEL_IND);
                CEP.TRC(SCCGWA, BPCOUBAS.VAL.FEE_CHG_TYPE);
                if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                    CEP.TRC(SCCGWA, " NOT CANCEL");
                    if (BPCOUBAS.VAL.FEE_CHG_TYPE == '0') {
                        BPCFCSTS.TBL_NO = K_CUS_DR_STS_TBL;
                    } else {
                        if (BPCOUBAS.VAL.FEE_CHG_TYPE == '1') {
                            BPCFCSTS.TBL_NO = K_CUS_CR_STS_TBL;
                        }
                    }
                } else {
                    CEP.TRC(SCCGWA, "CANCEL");
                    if (BPCOUBAS.VAL.FEE_CHG_TYPE == '0') {
                        BPCFCSTS.TBL_NO = K_CUS_CR_STS_TBL;
                    } else {
                        if (BPCOUBAS.VAL.FEE_CHG_TYPE == '1') {
                            BPCFCSTS.TBL_NO = K_CUS_DR_STS_TBL;
                        }
                    }
                }
                if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
                JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
                for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
                if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
                JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
                for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
                BPCFCSTS.STATUS_WORD = CICACCU.DATA.CI_STSW.substring(0, 60) + BPCFCSTS.STATUS_WORD.substring(60);
                if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
                JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
                for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
                if (DDCIMMST.DATA.AC_STS_WORD == null) DDCIMMST.DATA.AC_STS_WORD = "";
                JIBS_tmp_int = DDCIMMST.DATA.AC_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDCIMMST.DATA.AC_STS_WORD += " ";
                BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + DDCIMMST.DATA.AC_STS_WORD + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
            }
            CEP.TRC(SCCGWA, BPB9133_AWA_9133.CHQ_NO);
            if (BPB9133_AWA_9133.CHQ_NO.trim().length() > 0) {
                if (BPB9133_AWA_9133.CHG_MTH == '6' 
                    || BPB9133_AWA_9133.CHG_MTH == '7') {
                } else {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CHQ_TYPE_EER;
                    S000_ERR_MSG_PROC();
                }
                IBS.init(SCCGWA, CICACCU);
                CICACCU.DATA.AGR_NO = BPB9133_AWA_9133.CHG_AC;
                S000_CALL_CIZACCU();
                IBS.init(SCCGWA, DDCSBCHQ);
                DDCSBCHQ.AC_NO = BPB9133_AWA_9133.CHG_AC;
                DDCSBCHQ.CHQ_NO = BPB9133_AWA_9133.CHQ_NO;
                S000_CALL_DDZSBCHQ();
                CEP.TRC(SCCGWA, DDCSBCHQ.ISU_DATE);
                CEP.TRC(SCCGWA, DDCSBCHQ.CI_ID);
                CEP.TRC(SCCGWA, DDCSBCHQ.CHQ_STS);
                CEP.TRC(SCCGWA, DDCSBCHQ.AMT);
                IBS.init(SCCGWA, DDCSQCHQ);
                DDCSQCHQ.AC_NO = BPB9133_AWA_9133.CHG_AC;
                DDCSQCHQ.STR_CHQ_NO = BPB9133_AWA_9133.CHQ_NO;
                DDCSQCHQ.END_CHQ_NO = BPB9133_AWA_9133.CHQ_NO;
                S000_CALL_DDZSQCHQ();
                CEP.TRC(SCCGWA, DDCSQCHQ.CHQ_TYP);
                CEP.TRC(SCCGWA, DDCSQCHQ.CHQ_STS);
                if (DDCSQCHQ.CHQ_STS != '1' 
                    || DDCSQCHQ.CHQ_STS != '6') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CHQ_STS_ERR;
                    S000_ERR_MSG_PROC();
                }
                if ((BPB9133_AWA_9133.CHG_MTH == '6' 
                    && DDCSQCHQ.CHQ_TYP != '2') 
                    || (BPB9133_AWA_9133.CHG_MTH == '7' 
                    && DDCSQCHQ.CHQ_TYP != '3')) {
                    CEP.TRC(SCCGWA, "CHQ TYPE NOT MATCH");
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CHQ_TYPE_EER;
                    S000_ERR_MSG_PROC();
                }
            }
            if (BPB9133_AWA_9133.CHG_MTH == '0' 
                || BPB9133_AWA_9133.CHG_MTH == '4' 
                || BPB9133_AWA_9133.CHG_MTH == '5' 
                || BPB9133_AWA_9133.CHG_MTH == '6' 
                || BPB9133_AWA_9133.CHG_MTH == '7') {
                IBS.init(SCCGWA, DDCIMCCY);
                DDCIMCCY.DATA[1-1].AC = BPB9133_AWA_9133.CHG_AC;
                if ((BPB9133_AWA_9133.CHG_MTH == '0' 
                    || BPB9133_AWA_9133.CHG_MTH == '4' 
                    || BPB9133_AWA_9133.CHG_MTH == '5' 
                    || BPB9133_AWA_9133.CHG_MTH == '6' 
                    || BPB9133_AWA_9133.CHG_MTH == '7')) {
                    DDCIMCCY.DATA[1-1].AC = BPB9133_AWA_9133.CHG_AC;
                }
                DDCIMCCY.DATA[1-1].CCY = BPB9133_AWA_9133.CCY_REAL;
                DDCIMCCY.DATA[1-1].CCY_TYPE = BPB9133_AWA_9133.CCY_TYPE;
            }
            if (BPB9133_AWA_9133.CHG_MTH == '0' 
                || BPB9133_AWA_9133.CHG_MTH == '4' 
                || BPB9133_AWA_9133.CHG_MTH == '5' 
                || BPB9133_AWA_9133.CHG_MTH == '2' 
                || BPB9133_AWA_9133.CHG_MTH == '6' 
                || BPB9133_AWA_9133.CHG_MTH == '7') {
                IBS.init(SCCGWA, CICACCU);
                CICACCU.DATA.ENTY_TYP = '1';
                CICACCU.DATA.AGR_NO = BPB9133_AWA_9133.CHG_AC;
                CICACCU.DATA.AGR_NO = BPB9133_AWA_9133.CHG_AC;
                if (BPB9133_AWA_9133.CHG_MTH == '2') {
                    CICACCU.DATA.AGR_NO = IBCQINF.INPUT_DATA.AC_NO;
                }
                CEP.TRC(SCCGWA, IBCQINF.INPUT_DATA.AC_NO);
                CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.CI_NO);
                S000_CALL_CIZACCU();
                if (!CICACCU.DATA.CI_NO.equalsIgnoreCase(BPB9133_AWA_9133.CI_NO)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_CI_NOT_RELATED;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
        }
        if (BPB9133_AWA_9133.FIR_DSCT > 100) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PCNT_ERROR;
            WS_FLD_NO = BPB9133_AWA_9133.FIR_DSCT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.RES_DATE[1-1].R_PCT_NS);
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.RES_DATE[2-1].R_PCT_NS);
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.RES_DATE[3-1].R_PCT_NS);
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.RES_DATE[4-1].R_PCT_NS);
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.RES_DATE[5-1].R_PCT_NS);
        if (BPB9133_AWA_9133.RES_DATE[1-1].R_PCT_NS != 0 
            || BPB9133_AWA_9133.RES_DATE[2-1].R_PCT_NS != 0 
            || BPB9133_AWA_9133.RES_DATE[3-1].R_PCT_NS != 0 
            || BPB9133_AWA_9133.RES_DATE[4-1].R_PCT_NS != 0 
            || BPB9133_AWA_9133.RES_DATE[5-1].R_PCT_NS != 0) {
            WS_TOT_PCT = BPB9133_AWA_9133.RES_DATE[1-1].R_PCT_NS + BPB9133_AWA_9133.RES_DATE[2-1].R_PCT_NS + BPB9133_AWA_9133.RES_DATE[4-1].R_PCT_NS + BPB9133_AWA_9133.RES_DATE[5-1].R_PCT_NS;
            CEP.TRC(SCCGWA, WS_TOT_PCT);
            CEP.TRC(SCCGWA, K_TOT_PCT);
            if (WS_TOT_PCT != K_TOT_PCT) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PCT_OVER_100;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
    }
    public void R100_CHK_LOAN_CTR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB9133_AWA_9133.RCTRT_NO);
        if (BPB9133_AWA_9133.RCTRT_NO.trim().length() > 0) {
            IBS.init(SCCGWA, LNCSIQIF);
            LNCSIQIF.CONTRACT_NO = BPB9133_AWA_9133.RCTRT_NO;
            S000_CALL_LNZSIQIF();
            if (BPB9133_AWA_9133.CHG_AMT == 0 
                && BPB9133_AWA_9133.AMT_TYP != 0) {
                if (LNCSIQIF.START_DT != 0 
                    && BPB9133_AWA_9133.STA_DATE < LNCSIQIF.START_DT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SCH_STA_DATE_ERR;
                    WS_FLD_NO = BPB9133_AWA_9133.STA_DATE_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
                if (LNCSIQIF.MATU_DT != 0 
                    && BPB9133_AWA_9133.MAT_DATE > LNCSIQIF.MATU_DT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SCH_MAT_DATE_ERR;
                    WS_FLD_NO = BPB9133_AWA_9133.MAT_DATE_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
        }
    }
    public void B020_UPD_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSFSCH);
        BPCSFSCH.FUNC = 'U';
        R00_MOVE_SCH_DATA();
        S00_CALL_BPZSFSCH();
    }
    public void R00_MOVE_SCH_DATA() throws IOException,SQLException,Exception {
        BPCSFSCH.KEY.CTRT_NO = BPB9133_AWA_9133.CTRT_NO;
        BPCSFSCH.INF.CTRT_DESC = BPB9133_AWA_9133.FEE_DESC;
        BPCSFSCH.INF.CI_NO = BPB9133_AWA_9133.CI_NO;
        BPCSFSCH.INF.CTRT_TYPE = "FEES";
        BPCSFSCH.INF.AB_NAME = BPB9133_AWA_9133.AB_NAME;
        BPCSFSCH.INF.FEE_TYPE = BPB9133_AWA_9133.FEE_TYPE;
        BPCSFSCH.INF.BOOK_CENTRE = BPB9133_AWA_9133.BK_ACCT;
        BPCSFSCH.INF.PRD_TYPE = BPB9133_AWA_9133.PROD_TYP;
        BPCSFSCH.INF.START_DATE = BPB9133_AWA_9133.STA_DATE;
        BPCSFSCH.INF.MATURITY_DATE = BPB9133_AWA_9133.MAT_DATE;
        BPCSFSCH.INF.UCT_FLG = BPB9133_AWA_9133.UCT_FLG;
        BPCSFSCH.INF.SETTLE_FREQ = BPB9133_AWA_9133.SET_FREQ;
        BPCSFSCH.INF.FREQ_COUNT = BPB9133_AWA_9133.FREQ_CNT;
        BPCSFSCH.INF.FIRST_CHG_DATE = BPB9133_AWA_9133.FIRST_DT;
        BPCSFSCH.INF.HOLI_OVER = BPB9133_AWA_9133.HOL_OVER;
        BPCSFSCH.INF.CAL_CODE1 = BPB9133_AWA_9133.CAL_CD1;
        BPCSFSCH.INF.HOLI_METHOD = BPB9133_AWA_9133.HOL_METH;
        BPCSFSCH.INF.CAL_CODE2 = BPB9133_AWA_9133.CAL_CD2;
        BPCSFSCH.INF.PAY_IND = BPB9133_AWA_9133.PAY_IND;
        BPCSFSCH.INF.CASHFLOW_IND = BPB9133_AWA_9133.CASH_IND;
        BPCSFSCH.INF.BANK_PORTF = BPB9133_AWA_9133.PORT_CD;
        BPCSFSCH.INF.ACCRUAL_TYPE = BPB9133_AWA_9133.ACCR_TYP;
        BPCSFSCH.INF.PRICE_METHOD = BPB9133_AWA_9133.PRICE_ME;
        BPCSFSCH.INF.TXN_CCY = BPB9133_AWA_9133.TXN_CCY;
        BPCSFSCH.INF.TXN_AMT = BPB9133_AWA_9133.TXN_AMT;
        BPCSFSCH.INF.REL_CTRT_NO = BPB9133_AWA_9133.RCTRT_NO;
        BPCSFSCH.INF.REL_CT_TYPE = BPB9133_AWA_9133.R_CT_TYP;
        BPCSFSCH.INF.REL_CTRT_SRC = BPB9133_AWA_9133.REL_SRC;
        if (BPB9133_AWA_9133.REL_COL.trim().length() == 0) BPCSFSCH.INF.REL_COL_NO = 0;
        else BPCSFSCH.INF.REL_COL_NO = Long.parseLong(BPB9133_AWA_9133.REL_COL);
        BPCSFSCH.INF.REL_LMT_NO = BPB9133_AWA_9133.REL_LMT;
        BPCSFSCH.INF.FIR_DSCT = BPB9133_AWA_9133.FIR_DSCT;
        BPCSFSCH.INF.PROMPT_DAYS = BPB9133_AWA_9133.PR_DAYS;
        BPCSFSCH.INF.AMT_TYPE = BPB9133_AWA_9133.AMT_TYP;
        BPCSFSCH.INF.REL_PD_TYPE = BPB9133_AWA_9133.R_PD_TYP;
        BPCSFSCH.INF.MULTI = BPB9133_AWA_9133.MULTI;
        if (BPB9133_AWA_9133.MULTI == 0) {
            BPCSFSCH.INF.MULTI = 100;
        }
        BPCSFSCH.INF.INT_BAS = BPB9133_AWA_9133.INT_BAS;
        BPCSFSCH.INF.AGGR_TYPE = BPB9133_AWA_9133.AGGR_TYP;
        BPCSFSCH.INF.REF_CCY = BPB9133_AWA_9133.REF_CCY;
        BPCSFSCH.INF.REF_METHOD = BPB9133_AWA_9133.REF_MTH;
        BPCSFSCH.INF.FEE_CCY = BPB9133_AWA_9133.FEE_CCY;
        if (BPB9133_AWA_9133.FEE_CCY.trim().length() == 0) {
            BPCSFSCH.INF.FEE_CCY = BPB9133_AWA_9133.REF_CCY;
        }
        WS_CNT = 0;
        for (WS_CNT = 1; WS_CNT <= 5; WS_CNT += 1) {
            BPCSFSCH.INF.DATE[WS_CNT-1].REF_AMT = BPB9133_AWA_9133.REF_DATE[WS_CNT-1].REF_AMT;
            BPCSFSCH.INF.DATE[WS_CNT-1].REF_PCT = BPB9133_AWA_9133.REF_DATE[WS_CNT-1].REF_PCT;
            BPCSFSCH.INF.DATE[WS_CNT-1].AGG_MTH = BPB9133_AWA_9133.REF_DATE[WS_CNT-1].AGG_MTH;
            BPCSFSCH.INF.DATE[WS_CNT-1].FEE_AMT = BPB9133_AWA_9133.REF_DATE[WS_CNT-1].FEE_AMT;
            BPCSFSCH.INF.DATE[WS_CNT-1].FEE_RATE = BPB9133_AWA_9133.REF_DATE[WS_CNT-1].FEE_RATE;
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, BPCSFSCH.INF.DATE[WS_CNT-1].REF_AMT);
            CEP.TRC(SCCGWA, BPCSFSCH.INF.DATE[WS_CNT-1].REF_PCT);
            CEP.TRC(SCCGWA, BPCSFSCH.INF.DATE[WS_CNT-1].AGG_MTH);
            CEP.TRC(SCCGWA, BPCSFSCH.INF.DATE[WS_CNT-1].FEE_AMT);
            CEP.TRC(SCCGWA, BPCSFSCH.INF.DATE[WS_CNT-1].FEE_RATE);
        }
        BPCSFSCH.INF.AUTO_CHG_FLAG = BPB9133_AWA_9133.AUTO_FLG;
        BPCSFSCH.INF.CHARGE_CCY = BPB9133_AWA_9133.CHG_CCY;
        BPCSFSCH.INF.CHARGE_AMT = BPB9133_AWA_9133.CHG_AMT;
        BPCSFSCH.INF.CHARGE_METHOD = BPB9133_AWA_9133.CHG_MTH;
        BPCSFSCH.INF.CHARGE_AC = BPB9133_AWA_9133.CHG_AC;
        BPCSFSCH.INF.NOSTRO_CD = BPB9133_AWA_9133.NOSTR_CD;
        BPCSFSCH.INF.CHQ_NO = BPB9133_AWA_9133.CHQ_NO;
        BPCSFSCH.INF.GL_MASTER1 = WS_GLMST1;
        BPCSFSCH.INF.GL_MASTER2 = BPB9133_AWA_9133.GL_MST2;
        BPCSFSCH.INF.GL_MASTER3 = BPB9133_AWA_9133.GL_MST3;
        BPCSFSCH.INF.GL_MASTER4 = BPB9133_AWA_9133.GL_MST4;
        for (WS_CNT = 1; WS_CNT <= 5; WS_CNT += 1) {
            BPCSFSCH.INF.DATE1[WS_CNT-1].R_CENT = BPB9133_AWA_9133.RES_DATE[WS_CNT-1].R_CENT;
            BPCSFSCH.INF.DATE1[WS_CNT-1].R_BR = BPB9133_AWA_9133.RES_DATE[WS_CNT-1].R_BR;
            BPCSFSCH.INF.DATE1[WS_CNT-1].R_PCT_NS = BPB9133_AWA_9133.RES_DATE[WS_CNT-1].R_PCT_NS;
        }
        BPCSFSCH.INF.FEE_STATUS = BPB9133_AWA_9133.FEE_STS;
        BPCSFSCH.INF.REMARK = BPB9133_AWA_9133.RMK;
        BPCSFSCH.INF.FCHG_MIN_AMT = BPB9133_AWA_9133.MIN_AMT;
        BPCSFSCH.INF.CHG_CCY_REAL = BPB9133_AWA_9133.CCY_REAL;
        BPCSFSCH.INF.CP_NO = BPB9133_AWA_9133.CP_NO;
        BPCSFSCH.INF.SALE_DT = BPB9133_AWA_9133.SALE_DT;
        BPCSFSCH.INF.CCY_TYPE = BPB9133_AWA_9133.CCY_TYPE;
        CEP.TRC(SCCGWA, BPCSFSCH.INF.CCY_TYPE);
        BPCSFSCH.INF.CREV_NO = BPB9133_AWA_9133.CREV_NO;
        CEP.TRC(SCCGWA, BPCSFSCH.INF.CREV_NO);
    }
    public void S000_CALL_BPZFUBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-U-MAINTAIN-FBAS", BPCOUBAS);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            WS_FLD_NO = BPB9133_AWA_9133.BK_ACCT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        CEP.TRC(SCCGWA, BPCPQPRD.RC);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            WS_FLD_NO = BPB9133_AWA_9133.PROD_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_BPZPCCAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CHK-CAL-CODE", BPCOCCAL);
        CEP.TRC(SCCGWA, BPCOCCAL.RC);
        if (BPCOCCAL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOCCAL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        CEP.TRC(SCCGWA, BPCOCLWD.RC);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOCLWD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        CEP.TRC(SCCGWA, BPCQCCY.RC);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            if (WS_CNT2 == 1) {
                WS_FLD_NO = BPB9133_AWA_9133.TXN_CCY_NO;
            }
            if (WS_CNT2 == 2) {
                WS_FLD_NO = BPB9133_AWA_9133.REF_CCY_NO;
            }
            if (WS_CNT2 == 3) {
                WS_FLD_NO = BPB9133_AWA_9133.CHG_CCY_NO;
            }
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_LNZSIQIF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-INQ-INF", LNCSIQIF, true);
        if (LNCSIQIF.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, LNCSIQIF.RC);
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            WS_FLD_NO = BPB9133_AWA_9133.CHG_AC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
        CEP.TRC(SCCGWA, IBCQINF.RC);
        if (IBCQINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, IBCQINF.RC);
            WS_FLD_NO = BPB9133_AWA_9133.NOSTR_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        WS_CUS_TYPE = CICCUST.O_DATA.O_CI_TYP;
        CEP.TRC(SCCGWA, WS_CUS_TYPE);
    }
    public void R000_GET_GLMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOUBAS.VAL.GL_MASTERNO1);
        CEP.TRC(SCCGWA, BPCOUBAS.VAL.GL_MASTERNO2);
        WS_GLMST1 = BPCOUBAS.VAL.GL_MASTERNO1;
        CEP.TRC(SCCGWA, WS_GLMST1);
        if (BPCOUBAS.VAL.GL_MASTERNO2 != 0 
            && (WS_CUS_TYPE == 'C' 
            || WS_CUS_TYPE == 'F')) {
            WS_GLMST1 = BPCOUBAS.VAL.GL_MASTERNO2;
        }
        CEP.TRC(SCCGWA, WS_GLMST1);
    }
    public void R000_GET_GLMST_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCNGL);
        IBS.init(SCCGWA, BPCICNGL);
        BPCQCNGL.DAT.INPUT.CNTR_TYPE = "FEES";
        if (BPB9133_AWA_9133.BK_ACCT != 0) {
            BPCQCNGL.DAT.INPUT.BR = BPB9133_AWA_9133.BK_ACCT;
        }
        BPCICNGL.PROD_TYPE = BPB9133_AWA_9133.FEE_TYPE;
        BPCQCNGL.DAT.INPUT.OTH_PTR_LEN = 31;
        BPCQCNGL.DAT.INPUT.OTH_PTR = BPCICNGL;
        S000_CALL_BPZQCNGL();
        CEP.TRC(SCCGWA, BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[1-1].MSTNO);
        CEP.TRC(SCCGWA, BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[2-1].MSTNO);
        WS_GLMST1 = BPCQCNGL.DAT.OUTPUT.MSTNO_ARRAY[1-1].MSTNO;
        CEP.TRC(SCCGWA, WS_GLMST1);
        if (WS_GLMST1 != 0) {
            IBS.init(SCCGWA, BPCUGLM);
            BPCUGLM.INFO.FUNC = 'I';
            BPCUGLM.DATA.KEY.TYP = "AMGLM";
            BPCUGLM.DATA.KEY.REDEFINES16.MSTNO = WS_GLMST1;
            BPCUGLM.DATA.KEY.CD = IBS.CLS2CPY(SCCGWA, BPCUGLM.DATA.KEY.REDEFINES16);
            S000_CALL_BPZUGLM();
            CEP.TRC(SCCGWA, BPCUGLM.DATA.DATA_TXT.REL_ITMS[5-1].ITM_NO);
            if (BPCUGLM.DATA.DATA_TXT.REL_ITMS[5-1].ITM_NO.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CTRT_PRD_ITM_ERR;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CTRT_PRD_ITM_ERR;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_BPZQCNGL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-CNGL", BPCQCNGL);
        if (BPCQCNGL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCNGL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZUGLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-MAINT-GLM", BPCUGLM);
        if (BPCUGLM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUGLM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        CEP.TRC(SCCGWA, AICPQMIB.RC);
        if (AICPQMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_TYP_NOT_DEF;
            S000_ERR_MSG_PROC();
        }
    }
    public void S00_CALL_BPZSFSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MGM-FEESCH", BPCSFSCH);
    }
    public void S000_CALL_DDZSCINM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-CI-NAME", DDCSCINM);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            WS_FLD_NO = BPB9133_AWA_9133.CP_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_DDZSBCHQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-BCHQ-PROC", DDCSBCHQ);
    }
    public void S000_CALL_DDZIMCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-CCY", DDCIMCCY);
        CEP.TRC(SCCGWA, DDCIMCCY.RC);
        if (DDCIMCCY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIMCCY.RC);
            S000_ERR_MSG_PROC_CONTINUE();
        }
    }
    public void S000_CALL_DDZSQCHQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-QCHQ-PROC", DDCSQCHQ);
    }
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST", DDCIMMST);
        CEP.TRC(SCCGWA, DDCIMMST.RC.RC_CODE);
    }
    public void S000_CALL_DCZPFTCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-FINANCE-TR-CHK     ", DCCPFTCK);
    }
    public void S000_CALL_DDZSQPB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-Z-DDZSQPB", DDCSQPB);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
