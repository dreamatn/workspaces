package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT8110 {
    int JIBS_tmp_int;
    DBParm LNTSETL_RD;
    brParm LNTTRAN_BR = new brParm();
    String FMT_ID = "LN811";
    LNOT8110_WS_VARIABLES WS_VARIABLES = new LNOT8110_WS_VARIABLES();
    LNOT8110_WS_PAGE_INFO WS_PAGE_INFO = new LNOT8110_WS_PAGE_INFO();
    LNOT8110_WS_OUT_RECODE WS_OUT_RECODE = new LNOT8110_WS_OUT_RECODE();
    LNOT8110_WS_DB_VARS WS_DB_VARS = new LNOT8110_WS_DB_VARS();
    LNCMSG_ERROR_MSG ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNRTRAN LNRTRAN = new LNRTRAN();
    CICCUST CICCUST = new CICCUST();
    BPCPQORG BPCPQORG = new BPCPQORG();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNCRAGRE LNCRAGRE = new LNCRAGRE();
    LNRSETL LNRSETL = new LNRSETL();
    LNRCONT LNRCONT = new LNRCONT();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNRRATN LNRRATN = new LNRRATN();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    LNCRCVDM LNCRCVDM = new LNCRCVDM();
    LNRRCVD LNRRCVD = new LNRRCVD();
    LNCSCKPD LNCSCKPD = new LNCSCKPD();
    SCCGWA SCCGWA;
    LNB8110_AWA_8110 AWA_8110;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT8110 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        AWA_8110 = new LNB8110_AWA_8110();
        IBS.init(SCCGWA, AWA_8110);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWA_AREA_PTR, AWA_8110);
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_PAGE_INFO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_MAIN_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AWA_8110.BR);
        if (AWA_8110.BR == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (AWA_8110.DT_START == 0) {
            WS_DB_VARS.STR_DT = 0;
        } else {
            WS_DB_VARS.STR_DT = AWA_8110.DT_START;
        }
        if (AWA_8110.DT_END == 0) {
            WS_DB_VARS.END_DT = 99999999;
        } else {
            WS_DB_VARS.END_DT = AWA_8110.DT_END;
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_GET_PAGE_INFO();
        B000_GET_PAY_INFO();
        B000_GET_HEAD_INFO();
        B000_OUTPUT();
    }
    public void B000_GET_PAGE_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AWA_8110.PAGE_ROW);
        if (AWA_8110.PAGE_ROW == 0) {
            WS_PAGE_INFO.PAGE_ROW = 10;
        } else {
            if (AWA_8110.PAGE_ROW > 10) {
                WS_PAGE_INFO.PAGE_ROW = 10;
            } else {
                WS_PAGE_INFO.PAGE_ROW = AWA_8110.PAGE_ROW;
            }
        }
        CEP.TRC(SCCGWA, WS_PAGE_INFO.PAGE_ROW);
        if (AWA_8110.PAGE_NUM == 0) {
            WS_PAGE_INFO.CURR_PAGE = 1;
        } else {
            WS_PAGE_INFO.CURR_PAGE = (short) AWA_8110.PAGE_NUM;
        }
        WS_PAGE_INFO.TOTAL_NUM = 0;
        WS_PAGE_INFO.PAGE_IDX = 0;
        WS_PAGE_INFO.NEXT_START_NUM = ( ( WS_PAGE_INFO.CURR_PAGE - 1 ) * WS_PAGE_INFO.PAGE_ROW ) + 1;
        CEP.TRC(SCCGWA, WS_PAGE_INFO.NEXT_START_NUM);
    }
    public void B000_GET_PAY_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        WS_VARIABLES.CNT = 0;
        BPCPQORG.BR = AWA_8110.BR;
        S000_CALL_BPZPQORG();
        IBS.init(SCCGWA, LNRTRAN);
        if (AWA_8110.LOAN_AC.trim().length() > 0) {
            T000_STRBR_TRAN_1();
        } else {
            T000_STRBR_TRAN_2();
        }
        T000_NEXT_TRAN();
        CEP.TRC(SCCGWA, WS_PAGE_INFO.PAGE_IDX);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.PAGE_ROW);
        CEP.TRC(SCCGWA, "TEST 0426");
        while (WS_PAGE_INFO.PAGE_IDX <= WS_PAGE_INFO.PAGE_ROW 
            && WS_VARIABLES.TRAN_FLG != 'N' 
            && WS_VARIABLES.CNT <= 1000) {
            WS_PAGE_INFO.TOTAL_NUM += 1;
            CEP.TRC(SCCGWA, WS_PAGE_INFO.TOTAL_NUM);
            if (WS_PAGE_INFO.PAGE_IDX < WS_PAGE_INFO.PAGE_ROW 
                && WS_PAGE_INFO.TOTAL_NUM >= WS_PAGE_INFO.NEXT_START_NUM) {
                WS_PAGE_INFO.PAGE_IDX += 1;
                IBS.init(SCCGWA, WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1]);
                R000_GET_D_CNM();
                CEP.TRC(SCCGWA, WS_PAGE_INFO.PAGE_IDX);
                IBS.init(SCCGWA, LNRAGRE);
                IBS.init(SCCGWA, LNCRAGRE);
                LNCRAGRE.FUNC = 'I';
                LNRAGRE.KEY.CONTRACT_NO = LNRTRAN.KEY.CONTRACT_NO;
                S000_CALL_LNZRAGRE();
                IBS.init(SCCGWA, CICCUST);
                CICCUST.FUNC = 'A';
                CICCUST.DATA.AGR_NO = LNRAGRE.PAPER_NO;
                S000_CALL_CIZCUST();
                WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_TR_DATE = LNRTRAN.KEY.TR_DATE;
                WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_JRN_NO = LNRTRAN.KEY.TR_JRN_NO;
                WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_CONTRACT_NO = LNRTRAN.KEY.CONTRACT_NO;
                WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_END_DT = LNRTRAN.DUE_DT;
                WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_PAY_P = LNRTRAN.P_AMT;
                WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_PAY_I = LNRTRAN.I_AMT;
                WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_PAY_O = LNRTRAN.O_AMT;
                WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_PAY_L = LNRTRAN.L_AMT;
                WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_REC_P = LNRTRAN.P_REC_AMT;
                if (LNRTRAN.TR_CODE == 4100) {
                    WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_REC_I = LNRTRAN.I_AMT;
                } else {
                    WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_REC_I = LNRTRAN.I_REC_AMT;
                }
                WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_REC_O = LNRTRAN.O_REC_AMT;
                WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_REC_L = LNRTRAN.L_REC_AMT;
                WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_BAL = LNRTRAN.OS_BAL;
                R000_GEN_PROD_INFO();
                LNCSCKPD.FUNC = '0';
                LNCSCKPD.PROD_CD = LNRCONT.PROD_CD;
                S000_CALL_LNZSCKPD();
                WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_END_DT = LNRCONT.MAT_DATE;
                WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_PAY_TYP = LNRTRAN.AC_TYP1;
                WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_ALL_AMT = WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_PAY_I + WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_PAY_O + WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_PAY_L + WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_PAY_P;
                WS_VARIABLES.ADD2 = WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_REC_I + WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_REC_O + WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_REC_L + WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_REC_P;
                WS_VARIABLES.ADD1 = WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_PAY_I + WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_PAY_O + WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_PAY_L + WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_PAY_P;
                if (WS_VARIABLES.ADD2 <= WS_VARIABLES.ADD1) {
                    WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_NR_INT = 0;
                } else {
                    WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_NR_INT = WS_VARIABLES.ADD2 - WS_VARIABLES.ADD1;
                }
                CEP.TRC(SCCGWA, LNRTRAN.UPDTBL_TLR);
                if (LNRTRAN.UPDTBL_TLR == null) LNRTRAN.UPDTBL_TLR = "";
                JIBS_tmp_int = LNRTRAN.UPDTBL_TLR.length();
                for (int i=0;i<8-JIBS_tmp_int;i++) LNRTRAN.UPDTBL_TLR += " ";
                if (LNRTRAN.UPDTBL_TLR.substring(0, 1).equalsIgnoreCase("B")) {
                    WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_CHNL_FLG = "B";
                } else {
                    WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_CHNL_FLG = "O";
                }
                WS_OUT_RECODE.WS_OUT_DATA[WS_PAGE_INFO.PAGE_IDX-1].O_DUE_DT = LNRTRAN.DUE_DT;
            }
            T000_NEXT_TRAN();
        }
        T000_ENDBR_PROC();
    }
    public void R000_GET_D_CNM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSETL);
        LNRSETL.KEY.CONTRACT_NO = LNRTRAN.KEY.CONTRACT_NO;
        CEP.TRC(SCCGWA, LNRSETL.KEY.CONTRACT_NO);
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        LNTSETL_RD.where = "CONTRACT_NO = :LNRSETL.KEY.CONTRACT_NO "
            + "AND CI_TYPE = 'C' "
            + "AND SETTLE_TYPE = '4'";
        LNTSETL_RD.fst = true;
        IBS.READ(SCCGWA, LNRSETL, this, LNTSETL_RD);
        CEP.TRC(SCCGWA, LNRSETL.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRSETL.AC);
        if (LNRSETL.AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'A';
            CICCUST.DATA.AGR_NO = LNRSETL.AC;
            S000_CALL_CIZCUST();
        }
    }
    public void B000_GET_HEAD_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_PAGE_INFO.PAGE_ROW);
        WS_PAGE_INFO.BAL_CNT = WS_PAGE_INFO.TOTAL_NUM % WS_PAGE_INFO.PAGE_ROW;
        WS_PAGE_INFO.TOTAL_PAGE = (short) ((WS_PAGE_INFO.TOTAL_NUM - WS_PAGE_INFO.BAL_CNT) / WS_PAGE_INFO.PAGE_ROW);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.BAL_CNT);
        if (WS_PAGE_INFO.BAL_CNT != 0) {
            WS_PAGE_INFO.TOTAL_PAGE += 1;
        }
        if (WS_PAGE_INFO.TOTAL_PAGE <= WS_PAGE_INFO.CURR_PAGE) {
            WS_PAGE_INFO.LAST_PAGE = 'Y';
        } else {
            WS_PAGE_INFO.LAST_PAGE = 'N';
        }
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW = WS_PAGE_INFO.PAGE_IDX;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_PAGE = WS_PAGE_INFO.TOTAL_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_TOTAL_NUM = WS_PAGE_INFO.TOTAL_NUM;
        WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE = WS_PAGE_INFO.CURR_PAGE;
        WS_OUT_RECODE.WS_OUT_HEAD.O_LAST_PAGE = WS_PAGE_INFO.LAST_PAGE;
        CEP.TRC(SCCGWA, WS_PAGE_INFO.TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.PAGE_ROW);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.CURR_PAGE);
        CEP.TRC(SCCGWA, WS_PAGE_INFO.LAST_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_OUT_HEAD.O_CURR_PAGE_ROW);
    }
    public void B000_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = FMT_ID;
        SCCFMT.DATA_PTR = WS_OUT_RECODE;
        SCCFMT.DATA_LEN = 2539;
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_GEN_PROD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCONT);
        IBS.init(SCCGWA, LNRCONT);
        LNCRCONT.FUNC = 'I';
        LNRCONT.KEY.CONTRACT_NO = LNRTRAN.KEY.CONTRACT_NO;
        S000_CALL_LNZRCONT();
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST, true);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void S000_CALL_LNZRAGRE() throws IOException,SQLException,Exception {
        LNCRAGRE.REC_PTR = LNRAGRE;
        LNCRAGRE.REC_LEN = 491;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTAGRE", LNCRAGRE);
        if (LNCRAGRE.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRAGRE.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZSCKPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-CHK-PROD", LNCSCKPD);
    }
    public void S000_CALL_LNZRCONT() throws IOException,SQLException,Exception {
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 749;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRCONT.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_STRBR_TRAN_1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRTRAN.KEY.CONTRACT_NO);
        LNRTRAN.KEY.CONTRACT_NO = AWA_8110.LOAN_AC;
        LNRTRAN.TR_BR = AWA_8110.BR;
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.eqWhere = "CONTRACT_NO";
        LNTTRAN_BR.rp.where = "TR_BR = :LNRTRAN.TR_BR "
            + "AND ( TR_CODE = 4000 "
            + "OR TR_CODE = 4100 "
            + "OR TR_CODE = 4500 "
            + "OR TR_CODE = 4600 "
            + "OR TR_CODE = 4700 ) "
            + "AND TRAN_STS = 'N' "
            + "AND TXN_TYP = 'T' "
            + "AND ( TR_DATE BETWEEN :WS_DB_VARS.STR_DT "
            + "AND :WS_DB_VARS.END_DT )";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T000_STRBR_TRAN_2() throws IOException,SQLException,Exception {
        LNRTRAN.TR_BR = AWA_8110.BR;
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.where = "TR_BR = :LNRTRAN.TR_BR "
            + "AND ( TR_CODE = 4000 "
            + "OR TR_CODE = 4100 "
            + "OR TR_CODE = 4500 "
            + "OR TR_CODE = 4600 "
            + "OR TR_CODE = 4700 ) "
            + "AND TRAN_STS = 'N' "
            + "AND TXN_TYP = 'T' "
            + "AND ( TR_DATE BETWEEN :WS_DB_VARS.STR_DT "
            + "AND :WS_DB_VARS.END_DT )";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T000_NEXT_TRAN() throws IOException,SQLException,Exception {
        WS_VARIABLES.CNT = WS_VARIABLES.CNT + 1;
        IBS.READNEXT(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VARIABLES.TRAN_FLG = 'Y';
        } else {
            WS_VARIABLES.TRAN_FLG = 'N';
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTTRAN_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
