package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT8100 {
    int JIBS_tmp_int;
    DBParm LNTSETL_RD;
    DBParm LNTCONT_RD;
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTAGRE_RD;
    DBParm LNTTRAN_RD;
    brParm LNTTRAN_BR = new brParm();
    String FMT_ID = "LN810";
    LNOT8100_WS_VARIABLES WS_VARIABLES = new LNOT8100_WS_VARIABLES();
    LNOT8100_WS_OUT_RECODE WS_OUT_RECODE = new LNOT8100_WS_OUT_RECODE();
    LNOT8100_WS_COND_FLAG WS_COND_FLAG = new LNOT8100_WS_COND_FLAG();
    LNCMSG_ERROR_MSG ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    LNRTRAN LNRTRAN = new LNRTRAN();
    CICCUST CICCUST = new CICCUST();
    BPCPQORG BPCPQORG = new BPCPQORG();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNRSETL LNRSETL = new LNRSETL();
    LNRCONT LNRCONT = new LNRCONT();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACRI_WS_DB_VARS WS_DB_VARS = new CICQACRI_WS_DB_VARS();
    SCCGWA SCCGWA;
    LNB8100_AWA_8100 AWA_8100;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT8100 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        AWA_8100 = new LNB8100_AWA_8100();
        IBS.init(SCCGWA, AWA_8100);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWA_AREA_PTR, AWA_8100);
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_MAIN_PROCESS();
        B300_OUTPUT_WRITE();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (AWA_8100.BR == 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_MUST_INPUT, WS_VARIABLES.WS_TEMP_VARIABLE.WS_MSGID);
            WS_VARIABLES.FLD_NO = AWA_8100.BR_NO;
            S000_ERR_MSG_PROC();
        }
        if (AWA_8100.DT_START != 0) {
            WS_DB_VARS.DT_START = AWA_8100.DT_START;
        } else {
            WS_DB_VARS.DT_START = 0;
        }
        if (AWA_8100.DT_END != 0) {
            WS_DB_VARS.DT_END = AWA_8100.DT_END;
        } else {
            WS_DB_VARS.DT_END = 99999999;
        }
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_RECODE.WS_O_HEAD);
        WS_VARIABLES.CNT = 0;
        if (AWA_8100.PAGE_NUM == 0) {
            WS_VARIABLES.CURR_PAGE = 1;
        } else {
            WS_VARIABLES.CURR_PAGE = (short) AWA_8100.PAGE_NUM;
        }
        WS_OUT_RECODE.WS_O_HEAD.O_CURR_PAGE = WS_VARIABLES.CURR_PAGE;
        WS_VARIABLES.LAST_PAGE = 'N';
        if (AWA_8100.PAGE_ROW == 0) {
            WS_VARIABLES.PAGE_ROW = 10;
        } else {
            if (AWA_8100.PAGE_ROW > 10) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_PAGE_ROW, WS_VARIABLES.WS_TEMP_VARIABLE.WS_MSGID);
                WS_VARIABLES.FLD_NO = AWA_8100.PAGE_ROW_NO;
                S000_ERR_MSG_PROC();
            } else {
                WS_VARIABLES.PAGE_ROW = AWA_8100.PAGE_ROW;
            }
        }
        WS_VARIABLES.NEXT_START_NUM = ( ( WS_VARIABLES.CURR_PAGE - 1 ) * WS_VARIABLES.PAGE_ROW ) + 1;
        CEP.TRC(SCCGWA, WS_VARIABLES.CURR_PAGE);
        CEP.TRC(SCCGWA, WS_VARIABLES.PAGE_ROW);
        CEP.TRC(SCCGWA, WS_VARIABLES.NEXT_START_NUM);
        WS_VARIABLES.IDX = 1;
        WS_VARIABLES.TOTAL_NUM = 0;
        CEP.TRC(SCCGWA, AWA_8100.LOAN_AC);
        CEP.TRC(SCCGWA, AWA_8100.BR);
        if (AWA_8100.LOAN_AC.trim().length() > 0) {
            T001_CHECK_GROUP_LNTTRAN();
            T001_STARTBR_LNTTRAN_BY_CONT();
        } else {
            T002_CHECK_GROUP_LNTTRAN();
            T002_STARTBR_LNTTRAN_BY_BR();
        }
        R000_GET_DRAW_BR();
        if (WS_VARIABLES.TOTAL_NUM == 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_TRAN_NOTFND, WS_VARIABLES.WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.IDX);
        CEP.TRC(SCCGWA, AWA_8100.LOAN_AC);
        CEP.TRC(SCCGWA, AWA_8100.BR);
        CEP.TRC(SCCGWA, LNRTRAN.TR_BR);
        WS_DB_VARS.START_NUM = WS_VARIABLES.NEXT_START_NUM;
        T000_READNEXT_LNTTRAN();
        while (WS_VARIABLES.IDX <= WS_VARIABLES.PAGE_ROW 
            && WS_VARIABLES.CNT <= 1000 
            && WS_COND_FLAG.FOUND_FLG != 'N') {
            CEP.TRC(SCCGWA, LNRTRAN.KEY.CONTRACT_NO);
            IBS.init(SCCGWA, WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.IDX-1]);
            WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.IDX-1].O_TR_DATE = LNRTRAN.KEY.TR_DATE;
            WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.IDX-1].O_JRN_NO = LNRTRAN.KEY.TR_JRN_NO;
            WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.IDX-1].O_DRAW_BK = LNRTRAN.TR_BR;
            WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.IDX-1].O_CONTRACT_NO = LNRTRAN.KEY.CONTRACT_NO;
            WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.IDX-1].O_DRAW_AMT = LNRTRAN.P_AMT;
            WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.IDX-1].O_RATE = LNRTRAN.ALL_IN_RATE;
            CEP.TRC(SCCGWA, LNRTRAN.UPDTBL_TLR);
            if (LNRTRAN.UPDTBL_TLR == null) LNRTRAN.UPDTBL_TLR = "";
            JIBS_tmp_int = LNRTRAN.UPDTBL_TLR.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) LNRTRAN.UPDTBL_TLR += " ";
            if (LNRTRAN.UPDTBL_TLR.substring(0, 1).equalsIgnoreCase("B")) {
                WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.IDX-1].O_CHNL_FLG = 'B';
            } else {
                WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.IDX-1].O_CHNL_FLG = 'O';
            }
            T000_READ_LNTCONT();
            WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.IDX-1].O_PROD_CD = LNRCONT.PROD_CD;
            WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.IDX-1].O_DRAW_CCY = LNRCONT.CCY;
            WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.IDX-1].O_DUE_DT = LNRCONT.MAT_DATE;
            R000_GET_DRAW_BR();
            WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.IDX-1].O_DRAW_BK_CNM = BPCPQORG.CHN_NM;
            T000_READ_LNTAGRE();
            WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.IDX-1].O_DRAW_SEQ = LNRAGRE.DRAW_NO;
            WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.IDX-1].O_CONT_NO = LNRAGRE.PAPER_NO;
            WS_VARIABLES.CI_ACNO = LNRAGRE.PAPER_NO;
            CEP.TRC(SCCGWA, WS_VARIABLES.CI_ACNO);
            R000_GET_CI_INF();
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NM);
            WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.IDX-1].O_CI_CNM = CICCUST.O_DATA.O_CI_NM;
            if (CICCUST.O_DATA.O_CI_TYP == '1') {
                WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.IDX-1].O_COM_FLG = '1';
            }
            if (CICCUST.O_DATA.O_CI_TYP == '2') {
                WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.IDX-1].O_COM_FLG = '0';
            }
            T000_READ_LNTSETL();
            if (LNRSETL.AC.trim().length() > 0) {
                WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.IDX-1].O_DRAW_AC = LNRSETL.AC;
                WS_VARIABLES.CI_ACNO = WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.IDX-1].O_DRAW_AC;
                S000_CALL_CIZQACRI();
                WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.IDX-1].O_DRAW_AC_BK = CICQACRI.O_DATA.O_OPN_BR;
                WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.IDX-1].O_DRAW_BK1 = CICQACRI.O_DATA.O_OPN_BR;
            }
            IBS.init(SCCGWA, BPCPQORG);
            CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.IDX-1].O_DRAW_AC_BK);
            BPCPQORG.BR = WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.IDX-1].O_DRAW_AC_BK;
            S000_CALL_BPZPQORG();
            WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.IDX-1].O_DRAW_AC_BKNM = BPCPQORG.CHN_NM;
            R000_GET_CHG_CNM();
            WS_VARIABLES.IDX += 1;
            WS_VARIABLES.NEXT_START_NUM += 1;
            WS_DB_VARS.START_NUM = WS_VARIABLES.NEXT_START_NUM;
            T000_READNEXT_LNTTRAN();
        }
        T000_ENDBR_LNTTRAN();
        WS_VARIABLES.BAL_CNT = WS_VARIABLES.TOTAL_NUM % WS_VARIABLES.PAGE_ROW;
        WS_VARIABLES.TOTAL_PAGE = (short) ((WS_VARIABLES.TOTAL_NUM - WS_VARIABLES.BAL_CNT) / WS_VARIABLES.PAGE_ROW);
        CEP.TRC(SCCGWA, WS_VARIABLES.TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_VARIABLES.BAL_CNT);
        if (WS_VARIABLES.BAL_CNT != 0) {
            WS_VARIABLES.TOTAL_PAGE += 1;
        }
        if (AWA_8100.PAGE_NUM >= WS_VARIABLES.TOTAL_PAGE) {
            WS_VARIABLES.LAST_PAGE = 'Y';
        } else {
            WS_VARIABLES.LAST_PAGE = 'N';
        }
        WS_OUT_RECODE.WS_O_HEAD.O_TOTAL_PAGE = WS_VARIABLES.TOTAL_PAGE;
        WS_OUT_RECODE.WS_O_HEAD.O_TOTAL_NUM = WS_VARIABLES.TOTAL_NUM;
        WS_OUT_RECODE.WS_O_HEAD.O_LAST_PAGE = WS_VARIABLES.LAST_PAGE;
        WS_OUT_RECODE.WS_O_HEAD.O_CURR_PAGE_ROW = (short) (WS_VARIABLES.IDX - 1);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_O_HEAD.O_TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_O_HEAD.O_TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_O_HEAD.O_CURR_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_RECODE.WS_O_HEAD.O_CURR_PAGE_ROW);
    }
    public void B300_OUTPUT_WRITE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = FMT_ID;
        SCCFMT.DATA_PTR = WS_OUT_RECODE;
        SCCFMT.DATA_LEN = 9679;
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_GET_CI_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_VARIABLES.CI_ACNO);
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'A';
        CICCUST.DATA.AGR_NO = WS_VARIABLES.CI_ACNO;
        S000_CALL_CIZCUST();
    }
    public void R000_GET_CHG_CNM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSETL);
        LNRSETL.KEY.CONTRACT_NO = LNRCONT.KEY.CONTRACT_NO;
        CEP.TRC(SCCGWA, LNRSETL.KEY.CONTRACT_NO);
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        LNTSETL_RD.where = "CONTRACT_NO = :LNRSETL.KEY.CONTRACT_NO "
            + "AND CI_TYPE = 'C' "
            + "AND SETTLE_TYPE = '4'";
        LNTSETL_RD.fst = true;
        IBS.READ(SCCGWA, LNRSETL, this, LNTSETL_RD);
        CEP.TRC(SCCGWA, LNRSETL.AC);
        if (LNRSETL.AC.trim().length() > 0) {
            WS_VARIABLES.CI_ACNO = LNRSETL.AC;
            R000_GET_CI_INF();
            WS_OUT_RECODE.WS_O_DATA[WS_VARIABLES.IDX-1].O_CHG_CI_CNM = CICCUST.O_DATA.O_CI_NM;
        }
    }
    public void T000_READ_LNTCONT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNRTRAN.KEY.CONTRACT_NO;
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
    }
    public void R000_GET_DRAW_BR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        CEP.TRC(SCCGWA, LNRTRAN.TR_BR);
        BPCPQORG.BR = LNRTRAN.TR_BR;
        S000_CALL_BPZPQORG();
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_TEMP_VARIABLE.WS_MSGID);
            WS_VARIABLES.FLD_NO = AWA_8100.BR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_LNTAGRE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAGRE);
        LNRAGRE.KEY.CONTRACT_NO = LNRTRAN.KEY.CONTRACT_NO;
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        IBS.READ(SCCGWA, LNRAGRE, LNTAGRE_RD);
    }
    public void T000_READ_LNTSETL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSETL);
        LNRSETL.KEY.CONTRACT_NO = LNRTRAN.KEY.CONTRACT_NO;
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        LNTSETL_RD.where = "CONTRACT_NO = :LNRSETL.KEY.CONTRACT_NO "
            + "AND SETTLE_TYPE = '1'";
        LNTSETL_RD.fst = true;
        IBS.READ(SCCGWA, LNRSETL, this, LNTSETL_RD);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_VARIABLES.CI_ACNO;
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI, true);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST, true);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
    }
    public void T001_CHECK_GROUP_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRTRAN);
        LNRTRAN.KEY.CONTRACT_NO = AWA_8100.LOAN_AC;
        LNRTRAN.TR_BR = AWA_8100.BR;
        LNRTRAN.KEY.SUB_CTA_NO = 0;
        LNTTRAN_RD = new DBParm();
        LNTTRAN_RD.TableName = "LNTTRAN";
        LNTTRAN_RD.set = "WS-CNT2=COUNT(*)";
        LNTTRAN_RD.where = "CONTRACT_NO = :LNRTRAN.KEY.CONTRACT_NO "
            + "AND TR_BR = :LNRTRAN.TR_BR "
            + "AND SUB_CTA_NO = :LNRTRAN.KEY.SUB_CTA_NO "
            + "AND TRAN_STS = 'N' "
            + "AND TR_CODE IN ( 2000 , 2100 , 2200 , 2300 ) "
            + "AND ( TR_DATE BETWEEN :WS_DB_VARS.DT_START "
            + "AND :WS_DB_VARS.DT_END )";
        IBS.GROUP(SCCGWA, LNRTRAN, this, LNTTRAN_RD);
        CEP.TRC(SCCGWA, WS_DB_VARS.CNT2);
        CEP.TRC(SCCGWA, "T001-CHECK-GROUP-LNTTRAN");
        WS_VARIABLES.TOTAL_NUM = WS_DB_VARS.CNT2;
    }
    public void T002_CHECK_GROUP_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRTRAN);
        LNRTRAN.TR_BR = AWA_8100.BR;
        LNRTRAN.KEY.SUB_CTA_NO = 0;
        LNTTRAN_RD = new DBParm();
        LNTTRAN_RD.TableName = "LNTTRAN";
        LNTTRAN_RD.set = "WS-CNT2=COUNT(*)";
        LNTTRAN_RD.where = "TR_BR = :LNRTRAN.TR_BR "
            + "AND SUB_CTA_NO = :LNRTRAN.KEY.SUB_CTA_NO "
            + "AND TRAN_STS = 'N' "
            + "AND TR_CODE IN ( 2000 , 2100 , 2200 , 2300 ) "
            + "AND ( TR_DATE BETWEEN :WS_DB_VARS.DT_START "
            + "AND :WS_DB_VARS.DT_END )";
        IBS.GROUP(SCCGWA, LNRTRAN, this, LNTTRAN_RD);
        CEP.TRC(SCCGWA, WS_DB_VARS.CNT2);
        CEP.TRC(SCCGWA, "T002-CHECK-GROUP-LNTTRAN");
        WS_VARIABLES.TOTAL_NUM = WS_DB_VARS.CNT2;
    }
    public void T001_STARTBR_LNTTRAN_BY_CONT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRTRAN);
        LNRTRAN.KEY.CONTRACT_NO = AWA_8100.LOAN_AC;
        LNRTRAN.TR_BR = AWA_8100.BR;
        LNRTRAN.KEY.SUB_CTA_NO = 0;
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.eqWhere = "CONTRACT_NO";
        LNTTRAN_BR.rp.where = "TR_BR = :LNRTRAN.TR_BR "
            + "AND SUB_CTA_NO = :LNRTRAN.KEY.SUB_CTA_NO "
            + "AND TRAN_STS = 'N' "
            + "AND TR_CODE IN ( 2000 , 2100 , 2200 , 2300 ) "
            + "AND ( TR_DATE BETWEEN :WS_DB_VARS.DT_START "
            + "AND :WS_DB_VARS.DT_END )";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T002_STARTBR_LNTTRAN_BY_BR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRTRAN);
        LNRTRAN.TR_BR = AWA_8100.BR;
        LNRTRAN.KEY.SUB_CTA_NO = 0;
        LNTTRAN_BR.rp = new DBParm();
        LNTTRAN_BR.rp.TableName = "LNTTRAN";
        LNTTRAN_BR.rp.where = "TR_BR = :LNRTRAN.TR_BR "
            + "AND SUB_CTA_NO = :LNRTRAN.KEY.SUB_CTA_NO "
            + "AND TRAN_STS = 'N' "
            + "AND TR_CODE IN ( 2000 , 2100 , 2200 , 2300 ) "
            + "AND ( TR_DATE BETWEEN :WS_DB_VARS.DT_START "
            + "AND :WS_DB_VARS.DT_END )";
        IBS.STARTBR(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
    }
    public void T000_READNEXT_LNTTRAN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_VARIABLES.CNT);
        WS_VARIABLES.CNT = WS_VARIABLES.CNT + 1;
        IBS.READNEXT(SCCGWA, LNRTRAN, this, LNTTRAN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLAG.FOUND_FLG = 'Y';
        } else {
            WS_COND_FLAG.FOUND_FLG = 'N';
        }
    }
    public void T000_ENDBR_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTTRAN_BR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
