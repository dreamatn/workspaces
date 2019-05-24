package com.hisun.TD;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCENPSW;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.DP.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDZBVEXC {
    int JIBS_tmp_int;
    brParm TDTPLED_BR = new brParm();
    DBParm TDTPLED_RD;
    DBParm TDTSMST_RD;
    DBParm TDTBVT_RD;
    DBParm TDTYBTP_RD;
    DBParm TDTPBP_RD;
    DBParm TDTSTS_RD;
    DBParm TDTCMST_RD;
    String K_OUTPUT_FMT = "TD546";
    String K_SIMP_ADV_FMT = "TD520";
    String K_PLED_FMT = "TD414";
    String K_INQ_FMT = "TD414";
    int K_REQ_BR = 999999999;
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    String WS_BUSI_NO = " ";
    int WS_AC_SEQ = 0;
    String WS_AC = " ";
    String WS_O_CI_NO = " ";
    String WK_CI_NO_O = " ";
    TDZBVEXC_WS_TABLES_INFO WS_TABLES_INFO = new TDZBVEXC_WS_TABLES_INFO();
    TDZBVEXC_WS_CHECK_INFO WS_CHECK_INFO = new TDZBVEXC_WS_CHECK_INFO();
    TDZBVEXC_WS_CI_INFO WS_CI_INFO = new TDZBVEXC_WS_CI_INFO();
    int WS_VAL_NUM = 0;
    int WS_PROC_NUM = 0;
    String WS_BV_NO = " ";
    short WS_CURR_POS = 0;
    short WS_PART_NUM = 0;
    String WS_BV_CD = " ";
    String WS_BV_CD_I = " ";
    String WS_BV_NO_I = " ";
    TDZBVEXC_WS_PLD_NO WS_PLD_NO = new TDZBVEXC_WS_PLD_NO();
    String WS_CCY_O = " ";
    char WS_CCY_TYPO = ' ';
    char WS_YBT_SIG = ' ';
    char WS_YBT_SIG_I = ' ';
    char WS_SMST_MDY_FLG = ' ';
    short WS_PSBK_SEQ_I = 0;
    short WS_PSBK_SEQ_O = 0;
    int WS_CUR_BVT_POS_I = 0;
    TDZBVEXC_CP_PROD_CD CP_PROD_CD = new TDZBVEXC_CP_PROD_CD();
    String WS_DRAW_MTH_DESC = " ";
    TDZBVEXC_REDEFINES86 REDEFINES86 = new TDZBVEXC_REDEFINES86();
    short WS_C = 0;
    int WS_CNT2 = 0;
    short WS_NUM1 = 0;
    int WS_L_CNT = 0;
    int WS_P_ROW = 0;
    int WS_L_ROW = 0;
    int WS_T_PAGE = 0;
    int WS_P_NUM = 0;
    int WS_STR_NUM = 0;
    int WS_END_NUM = 0;
    TDZBVEXC_WS_FMT WS_FMT = new TDZBVEXC_WS_FMT();
    double WS_AVL_BAL = 0;
    String WS_INQ_AC = " ";
    char WS_INQ_BV_TYP = ' ';
    String WS_INQ_BV_NO = " ";
    String WS_INQ_PLD_NO = " ";
    char WS_STS_FLG = ' ';
    char WS_MSREL_FLG = ' ';
    char WS_TDTPLED_FLAG = ' ';
    TDZBVEXC_WS_PLD_SEQ WS_PLD_SEQ = new TDZBVEXC_WS_PLD_SEQ();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SCCHMPW SCCHMPW = new SCCHMPW();
    SCCFMT SCCFMT = new SCCFMT();
    BPCFQFBV BPCFQFBV = new BPCFQFBV();
    DCCILNKR DCCILNKR = new DCCILNKR();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICMACR CICMACR = new CICMACR();
    CICACCU CICACCU = new CICACCU();
    TDRSMST TDRSMST = new TDRSMST();
    TDRBVT TDRBVT = new TDRBVT();
    TDRSTS TDRSTS = new TDRSTS();
    TDRYBTP TDRYBTP = new TDRYBTP();
    TDRPBP TDRPBP = new TDRPBP();
    TDCPPRTF TDCPPRTF = new TDCPPRTF();
    DPCPARMP DPCPARMP = new DPCPARMP();
    TDCOBVEZ TDCOBVEC = new TDCOBVEZ();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    TDCRPLED TDCRPLED = new TDCRPLED();
    TDCQLDT TDCQLDT = new TDCQLDT();
    TDCORIMP TDCORIMP = new TDCORIMP();
    DCCUCINF DCCUCINF = new DCCUCINF();
    TDCPLDT TDCPLDT = new TDCPLDT();
    TDRIREV TDRIREV = new TDRIREV();
    TDCBVCD TDCBVCD = new TDCBVCD();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    TDCUPARM TDCUPARM = new TDCUPARM();
    TDRPLED TDRPLED = new TDRPLED();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    TDCPRDP TDCPRDP = new TDCPRDP();
    CICCUST CICCUST = new CICCUST();
    CICQACRI CICQACRI = new CICQACRI();
    TDRCMST TDRCMST = new TDRCMST();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    TDCBVEXC TDCBVEXC;
    public void MP(SCCGWA SCCGWA, TDCBVEXC TDCBVEXC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCBVEXC = TDCBVEXC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZBVEXC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCBVEXC.AC_I);
        CEP.TRC(SCCGWA, TDCBVEXC.AC_SEQ_O);
        CEP.TRC(SCCGWA, TDCBVEXC.BV_NO_O);
        CEP.TRC(SCCGWA, TDCBVEXC.BV_TYP_I);
        CEP.TRC(SCCGWA, TDCBVEXC.BV_TYP_O);
        if (TDCBVEXC.CERF_OPT == 'P') {
            if (TDCBVEXC.BV_TYP_O == '7') {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_BV_TYP_ERR;
                S000_ERR_MSG_PROC();
            }
            B100_PLD();
            B250_OUTPUT_PROC();
        } else if (TDCBVEXC.CERF_OPT == 'R') {
            B200_RLS();
        } else {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INVALID_OPT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B100_PLD() throws IOException,SQLException,Exception {
        WS_PLD_SEQ.WS_SEQ1 = 'T';
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = TDCBVEXC.MAIN_AC_I;
        S000_CALL_CIZQACRI();
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = CICQACRI.O_DATA.O_CI_NO;
        S000_CALL_CIZCUST();
        WS_PLD_SEQ.WS_SEQ2 = CICCUST.O_DATA.O_CI_TYP;
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = "ZYSEQ";
        BPCSGSEQ.CODE = "PLD";
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        S000_CALL_BPZSGSEQ();
        WS_PLD_SEQ.WS_SEQ3 = BPCSGSEQ.SEQ;
        B300_WRI_PLEDGE_RIG();
        IBS.init(SCCGWA, TDRSMST);
        IBS.init(SCCGWA, CICQACAC);
        IBS.init(SCCGWA, TDRBVT);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = TDCBVEXC.AC_I;
        CICQACAC.DATA.BV_NO = TDCBVEXC.BV_NO_O;
        CICQACAC.DATA.AGR_SEQ = TDCBVEXC.AC_SEQ_O;
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READU_TDTSMST();
        CEP.TRC(SCCGWA, TDRSMST.AC_NO);
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        R000_CHK_SMST_STS();
        if (TDCBVEXC.BV_TYP_O != '4') {
            TDRBVT.KEY.AC_NO = TDRSMST.KEY.ACO_AC;
            T000_READUP_TDTBVT();
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.TRC(SCCGWA, "222");
                TDRBVT.KEY.AC_NO = TDRSMST.AC_NO;
                T000_READUP_TDTBVT_2();
            }
            R000_CHK_BVT_STS();
        } else {
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = TDCBVEXC.AC_I;
            S000_CALL_DCZUCINF();
            if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
            JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
            if (!DCCUCINF.CARD_STSW.substring(0, 2).equalsIgnoreCase("00")) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_BV_LOSS;
                S000_ERR_MSG_PROC();
            }
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        TDRSMST.STSW = TDRSMST.STSW.substring(0, 5 - 1) + "1" + TDRSMST.STSW.substring(5 + 1 - 1);
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        T000_REWRITE_TDTSMST();
        IBS.init(SCCGWA, TDRPLED);
        TDRPLED.KEY.PLD_NO = TDCBVEXC.PLD_NO;
        TDRPLED.ACO_AC = TDRSMST.KEY.ACO_AC;
        TDRPLED.STS = '1';
        TDRPLED.BV_TYP = '3';
        TDRPLED.BV_NO = TDCBVEXC.BV_NO_O;
        TDRPLED.BOOK_TYP = 'T';
        TDRPLED.BOOK_NO = TDCBVEXC.BOOK_NO;
        TDRPLED.BOOK_TOP = TDCBVEXC.BOOK_TOP;
        TDRPLED.REMARK = TDCBVEXC.REMARK;
        TDRPLED.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRPLED.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRPLED.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRPLED.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRPLED.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRPLED.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        T000_WRITE_TDTPLED();
        IBS.init(SCCGWA, TDRSTS);
        TDRSTS.KEY.AC_NO = TDCBVEXC.AC_I;
        TDRSTS.KEY.TYPE = '3';
        TDRSTS.STR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSTS.KEY.POS = 5;
        TDRSTS.KEY.AC_SEQ = TDCBVEXC.AC_SEQ_O;
        T000_READUP_TDTSTS_R();
        if (WS_STS_FLG == 'Y') {
            TDRSTS.STS = '1';
            T000_REWRITE_TDTSTS();
        } else {
            TDRSTS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            TDRSTS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRSTS.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            TDRSTS.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRSTS.STS = '1';
            TDRSTS.BUSI_NO = TDCBVEXC.PLD_NO;
            TDRSTS.REQ_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            TDRSTS.REMARK = "PLEDGE";
            TDRSTS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            CEP.TRC(SCCGWA, TDRSTS.KEY.AC_NO);
            T000_WRITE_TDTSTS();
        }
        B230_WRI_NFIN_HIS_PLED();
    }
    public void B200_RLS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCBVEXC.PLD_NO);
        IBS.init(SCCGWA, TDRPLED);
        TDRPLED.KEY.PLD_NO = TDCBVEXC.PLD_NO;
        T000_READU_TDTPLED();
        TDRPLED.STS = '0';
        TDRPLED.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRPLED.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRPLED.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        T000_REWRITE_TDTPLED();
        IBS.init(SCCGWA, TDRSTS);
        TDRSTS.KEY.AC_NO = TDCBVEXC.AC_I;
        TDRSTS.KEY.TYPE = '3';
        TDRSTS.KEY.AC_SEQ = TDCBVEXC.AC_SEQ_O;
        TDRSTS.STR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSTS.KEY.POS = 5;
        TDRSTS.STR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_READUP_TDTSTS();
        if (WS_STS_FLG == 'Y') {
            TDRSTS.KEY.TYPE = '3';
            TDRSTS.STS = '0';
            TDRSTS.END_DATE = SCCGWA.COMM_AREA.AC_DATE;
            T000_REWRITE_TDTSTS();
        }
        IBS.init(SCCGWA, TDRSMST);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CEP.TRC(SCCGWA, TDCBVEXC.AC_I);
        CEP.TRC(SCCGWA, TDCBVEXC.AC_SEQ_O);
        CEP.TRC(SCCGWA, TDCBVEXC.BV_NO_O);
        CICQACAC.DATA.AGR_NO = TDCBVEXC.AC_I;
        CICQACAC.DATA.AGR_NO = TDCBVEXC.AC_I;
        CICQACAC.DATA.BV_NO = TDCBVEXC.BV_NO_O;
        CICQACAC.DATA.AGR_SEQ = TDCBVEXC.AC_SEQ_O;
        S000_CALL_CIZQACAC();
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READU_TDTSMST();
        CEP.TRC(SCCGWA, TDRSMST.AC_NO);
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        R000_CHK_SMST_STS();
        if (TDCBVEXC.BV_TYP_O != '4' 
            && TDCBVEXC.BV_TYP_O != ' ') {
            TDRBVT.KEY.AC_NO = TDRSMST.KEY.ACO_AC;
            T000_READUP_TDTBVT();
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.TRC(SCCGWA, "222");
                TDRBVT.KEY.AC_NO = TDRSMST.AC_NO;
                T000_READUP_TDTBVT_2();
            }
            R000_CHK_BVT_STS();
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        TDRSMST.STSW = TDRSMST.STSW.substring(0, 5 - 1) + "0" + TDRSMST.STSW.substring(5 + 1 - 1);
        T000_REWRITE_TDTSMST();
        B230_WRI_NFIN_HIS_PLED();
    }
    public void R000_CHK_SMST_STS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRSMST.HBAL);
        if (TDRSMST.ACO_STS == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED;
            S000_ERR_MSG_PROC();
        }
        if (TDRSMST.ACO_STS == '2') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_RESERVED;
            S000_ERR_MSG_PROC();
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1") 
            || TDRSMST.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_TMP_HLD;
            S000_ERR_MSG_PROC();
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_ALREADY_HLD;
            S000_ERR_MSG_PROC();
        }
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_ALREADY_HLD;
            S000_ERR_MSG_PROC();
        }
        if (TDRSMST.HBAL > 0) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_ALREADY_HLD;
            S000_ERR_MSG_PROC();
        }
        if (TDCBVEXC.CERF_OPT == 'R') {
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("0")) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_PLEDGE;
                S000_ERR_MSG_PROC();
            }
        } else {
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_IMPAWNED;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void R000_CHK_BVT_STS() throws IOException,SQLException,Exception {
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        if ((TDRBVT.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            || TDRBVT.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1"))) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_BV_LOSS;
            S000_ERR_MSG_PROC();
        }
    }
    public void B300_WRI_PLEDGE_RIG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDCBVEXC.AC_I;
        S000_CALL_CIZACCU();
        CEP.TRC(SCCGWA, "F-BUG911");
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_TYP);
        TDCRPLED.FUNC = '0';
        WS_PLD_NO.WS_SIG = 'T';
        if (CICACCU.DATA.CI_TYP != ' ') {
            WS_PLD_NO.WS_CUS_SIG = CICACCU.DATA.CI_TYP;
        } else {
            WS_PLD_NO.WS_CUS_SIG = '1';
        }
        WS_PLD_NO.WS_GTEE_NO = BPCSGSEQ.SEQ;
        TDCPLDT.PLD_NO = IBS.CLS2CPY(SCCGWA, WS_PLD_NO);
        TDCBVEXC.PLD_NO = IBS.CLS2CPY(SCCGWA, WS_PLD_NO);
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void B300_FMT_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCORIMP);
        TDCORIMP.NO = TDCBVEXC.PLD_NO;
        TDCORIMP.REL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDCORIMP.TR_TLR_NO = SCCGWA.COMM_AREA.TL_ID;
        TDCORIMP.AU_TLR_NO = SCCGWA.COMM_AREA.SUP1_ID;
        TDCORIMP.TR_BR_NO = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDCORIMP.FUNC = TDCBVEXC.CERF_OPT;
        TDCORIMP.BV_TYP = TDCBVEXC.BV_TYP_O;
        TDCORIMP.BV_NO = TDCBVEXC.BV_NO_O;
        TDCORIMP.NEW_BVTP = TDCBVEXC.BV_TYP_I;
        TDCORIMP.NEW_BVNO = TDCBVEXC.BV_NO_I;
        TDCORIMP.AC = TDCBVEXC.AC_I;
        TDCORIMP.CCY = TDRSMST.CCY;
        TDCORIMP.AMT = TDRSMST.BAL;
        TDCORIMP.TERM = TDRSMST.TERM;
        TDCORIMP.VAL_DATE = TDRSMST.VAL_DATE;
        TDCORIMP.EXP_DATE = TDRSMST.EXP_DATE;
        TDCORIMP.BOOK_TYP = TDCBVEXC.BOOK_TYP;
        TDCORIMP.BOOK_NO = TDCBVEXC.BOOK_NO;
        TDCORIMP.BOOK_TOP = TDCBVEXC.BOOK_TOP;
        CEP.TRC(SCCGWA, TDCORIMP.BOOK_TOP);
        TDRCMST.KEY.AC_NO = TDCBVEXC.MAIN_AC_I;
        T000_READ_TDCMST();
        TDCORIMP.DRAW_MTH = TDRCMST.DRAW_MTH;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_SIMP_ADV_FMT;
        SCCFMT.DATA_PTR = TDCORIMP;
        SCCFMT.DATA_LEN = 308;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_TDZRPLED() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-R-TDZRPLED", TDCRPLED);
        if (TDCRPLED.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, TDCRPLED.RC);
            S000_ERR_MSG_PROC();
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_STS_FLG = 'Y';
        } else {
            WS_STS_FLG = 'N';
        }
    }
    public void B230_WRI_NFIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.TX_TYP_CD = "P150";
        BPCPNHIS.INFO.AC = TDCBVEXC.AC_O;
        if (TDCBVEXC.MAIN_AC_O.trim().length() > 0) {
            BPCPNHIS.INFO.TX_TOOL = TDCBVEXC.MAIN_AC_O;
        } else {
            if (TDCBVEXC.CARD_NO_O.trim().length() > 0) {
                BPCPNHIS.INFO.TX_TOOL = TDCBVEXC.CARD_NO_O;
            }
        }
        BPCPNHIS.INFO.REF_NO = TDCBVEXC.PLD_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.NEW_DAT_PT = TDCBVEXC;
        BPCPNHIS.INFO.FMT_ID_LEN = 1256;
        BPCPNHIS.INFO.FMT_ID = "TDCBVEXC";
        BPCPNHIS.INFO.REF_NO = TDCBVEXC.PLD_NO;
        BPCPNHIS.INFO.AC_SEQ = TDCBVEXC.AC_SEQ_O;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.AC_SEQ);
        S000_CALL_BPZPNHIS();
    }
    public void B230_WRI_NFIN_HIS_PLED() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.REF_NO = TDCPLDT.PLD_NO;
        BPCPNHIS.INFO.AC = TDCBVEXC.AC_O;
        BPCPNHIS.INFO.AC_SEQ = TDCBVEXC.AC_SEQ_O;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.AC_SEQ);
        if ((TDCBVEXC.BV_TYP_O == '1' 
            && WS_YBT_SIG == 'Y') 
            || (TDCBVEXC.BV_TYP_O == '7' 
            || TDCBVEXC.BV_TYP_O == '8')) {
            BPCPNHIS.INFO.TX_TOOL = TDCBVEXC.MAIN_AC_O;
        }
        if (TDCBVEXC.CERF_OPT == 'P') {
            BPCPNHIS.INFO.TX_TYP_CD = "P150";
        } else {
            BPCPNHIS.INFO.TX_TYP_CD = "P151";
        }
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        S000_CALL_BPZPNHIS();
    }
    public void B250_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCOBVEC);
        TDCOBVEC.BV_CD_O = TDCBVEXC.BV_CD_O;
        TDCOBVEC.BV_NO_O = TDCBVEXC.BV_NO_O;
        CEP.TRC(SCCGWA, TDCBVEXC.BV_CD_O);
        CEP.TRC(SCCGWA, TDCBVEXC.BV_NO_O);
        TDCOBVEC.MAIN_AC_O = TDCBVEXC.MAIN_AC_O;
        TDCOBVEC.AC_SEQ_O = TDCBVEXC.AC_SEQ_O;
        TDCOBVEC.MAIN_AC_O = TDCBVEXC.AC_O;
        TDCOBVEC.DRAW_MTH_O = TDCBVEXC.DRAW_MTH_O;
        if (TDCBVEXC.BV_CD_I.trim().length() > 0) {
            TDCOBVEC.BV_CD_I = TDCBVEXC.BV_CD_I;
        }
        if (TDCBVEXC.BV_NO_I.trim().length() > 0) {
            TDCOBVEC.BV_NO_I = TDCBVEXC.BV_NO_I;
        }
        if (TDCBVEXC.BV_TYP_O == '9' 
            && TDCBVEXC.BV_TYP_I == '7') {
            TDCOBVEC.BV_CD_I = WS_BV_CD_I;
            TDCOBVEC.BV_NO_I = WS_BV_NO_I;
        }
        if (TDCBVEXC.BV_TYP_O == '3' 
            && TDCBVEXC.BV_TYP_I == '7') {
            TDCOBVEC.BV_CD_I = WS_BV_CD_I;
            TDCOBVEC.BV_NO_I = WS_BV_NO_I;
        }
        TDCOBVEC.MAIN_AC_I = TDCBVEXC.MAIN_AC_I;
        TDCOBVEC.AC_SEQ_I = WS_AC_SEQ;
        TDCOBVEC.AC_I = TDCBVEXC.AC_I;
        CEP.TRC(SCCGWA, "F-BUG14");
        CEP.TRC(SCCGWA, TDCBVEXC.AC_NM);
        TDCOBVEC.AC_NM = TDCBVEXC.AC_NM;
        TDCOBVEC.BAL = TDRSMST.BAL;
        TDCOBVEC.TERM = TDRSMST.TERM;
        TDCOBVEC.PLD_NO = TDCBVEXC.PLD_NO;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = TDCOBVEC;
        SCCFMT.DATA_LEN = 446;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_STARTBR_TDTPLED_AC_BV_TYP() throws IOException,SQLException,Exception {
        WS_TDTPLED_FLAG = 'N';
        TDTPLED_BR.rp = new DBParm();
        TDTPLED_BR.rp.TableName = "TDTPLED";
        TDTPLED_BR.rp.where = "MAIN_AC = :WS_INQ_AC "
            + "AND BV_TYP = :WS_INQ_BV_TYP";
        TDTPLED_BR.rp.order = "CRT_DATE, TS";
        IBS.STARTBR(SCCGWA, TDRPLED, this, TDTPLED_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TDTPLED_FLAG = 'Y';
        } else {
            WS_TDTPLED_FLAG = 'N';
        }
    }
    public void T000_STARTBR_TDTPLED_BV_NO() throws IOException,SQLException,Exception {
        WS_TDTPLED_FLAG = 'N';
        TDTPLED_BR.rp = new DBParm();
        TDTPLED_BR.rp.TableName = "TDTPLED";
        TDTPLED_BR.rp.where = "MAIN_AC = :WS_INQ_AC "
            + "AND BV_NO = :WS_INQ_BV_NO";
        TDTPLED_BR.rp.order = "CRT_DATE, TS";
        IBS.STARTBR(SCCGWA, TDRPLED, this, TDTPLED_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TDTPLED_FLAG = 'Y';
        } else {
            WS_TDTPLED_FLAG = 'N';
        }
    }
    public void T000_STARTBR_TDTPLED_AC() throws IOException,SQLException,Exception {
        WS_TDTPLED_FLAG = 'N';
        TDTPLED_BR.rp = new DBParm();
        TDTPLED_BR.rp.TableName = "TDTPLED";
        TDTPLED_BR.rp.where = "MAIN_AC = :WS_INQ_AC";
        TDTPLED_BR.rp.order = "CRT_DATE, TS";
        IBS.STARTBR(SCCGWA, TDRPLED, this, TDTPLED_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TDTPLED_FLAG = 'Y';
        } else {
            WS_TDTPLED_FLAG = 'N';
        }
    }
    public void T000_STARTBR_TDTPLED_AC_BV() throws IOException,SQLException,Exception {
        WS_TDTPLED_FLAG = 'N';
        TDTPLED_BR.rp = new DBParm();
        TDTPLED_BR.rp.TableName = "TDTPLED";
        TDTPLED_BR.rp.where = "MAIN_AC = :WS_INQ_AC "
            + "AND BV_TYP = :WS_INQ_BV_TYP "
            + "AND BV_NO = :WS_INQ_BV_NO";
        TDTPLED_BR.rp.order = "CRT_DATE, TS";
        IBS.STARTBR(SCCGWA, TDRPLED, this, TDTPLED_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TDTPLED_FLAG = 'Y';
        } else {
            WS_TDTPLED_FLAG = 'N';
        }
    }
    public void T000_STARTBR_TDTPLED_PLD_NO() throws IOException,SQLException,Exception {
        WS_TDTPLED_FLAG = 'N';
        TDTPLED_BR.rp = new DBParm();
        TDTPLED_BR.rp.TableName = "TDTPLED";
        TDTPLED_BR.rp.where = "PLD_NO = :WS_INQ_PLD_NO";
        TDTPLED_BR.rp.order = "CRT_DATE, TS";
        IBS.STARTBR(SCCGWA, TDRPLED, this, TDTPLED_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "WS-PLED-FOUND");
            WS_TDTPLED_FLAG = 'Y';
        } else {
            CEP.TRC(SCCGWA, "WS-PLED-NOTFND");
            WS_TDTPLED_FLAG = 'N';
        }
    }
    public void T000_READNEXT_TDTPLED() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRPLED, this, TDTPLED_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "READ-FOUND");
            WS_TDTPLED_FLAG = 'Y';
        } else {
            CEP.TRC(SCCGWA, "READ-NOTFND");
            WS_TDTPLED_FLAG = 'N';
        }
    }
    public void T000_ENDBR_TDTPLED() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTPLED_BR);
    }
    public void T000_READU_TDTPLED() throws IOException,SQLException,Exception {
        TDTPLED_RD = new DBParm();
        TDTPLED_RD.TableName = "TDTPLED";
        TDTPLED_RD.upd = true;
        IBS.READ(SCCGWA, TDRPLED, TDTPLED_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READU_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.eqWhere = "ACO_AC";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_TDTSMST() throws IOException,SQLException,Exception {
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void T000_WRITE_TDTPLED() throws IOException,SQLException,Exception {
        TDRPLED.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRPLED.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRPLED.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTPLED_RD = new DBParm();
        TDTPLED_RD.TableName = "TDTPLED";
        IBS.WRITE(SCCGWA, TDRPLED, TDTPLED_RD);
    }
    public void T000_REWRITE_TDTPLED() throws IOException,SQLException,Exception {
        TDRPLED.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRPLED.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRPLED.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTPLED_RD = new DBParm();
        TDTPLED_RD.TableName = "TDTPLED";
        IBS.REWRITE(SCCGWA, TDRPLED, TDTPLED_RD);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        CEP.TRC(SCCGWA, CICQACAC.RC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_READ_TDTIREV() throws IOException,SQLException,Exception {
    }
    public void T000_READUP_TDTBVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.upd = true;
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
    }
    public void T000_READUP_TDTBVT_2() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.upd = true;
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_UPDATE_TDTBVT() throws IOException,SQLException,Exception {
        TDRBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRBVT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRBVT.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.REWRITE(SCCGWA, TDRBVT, TDTBVT_RD);
    }
    public void T000_WRITE_TDTBVT() throws IOException,SQLException,Exception {
        TDRBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRBVT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRBVT.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.WRITE(SCCGWA, TDRBVT, TDTBVT_RD);
    }
    public void T000_WRITE_TDTYBTP() throws IOException,SQLException,Exception {
        TDRYBTP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRYBTP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRYBTP.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTYBTP_RD = new DBParm();
        TDTYBTP_RD.TableName = "TDTYBTP";
        IBS.WRITE(SCCGWA, TDRYBTP, TDTYBTP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_WRITE_TDTPBP() throws IOException,SQLException,Exception {
        TDTPBP_RD = new DBParm();
        TDTPBP_RD.TableName = "TDTPBP";
        IBS.WRITE(SCCGWA, TDRPBP, TDTPBP_RD);
    }
    public void T000_READ_UPDATE_TDTPBP() throws IOException,SQLException,Exception {
        TDTPBP_RD = new DBParm();
        TDTPBP_RD.TableName = "TDTPBP";
        TDTPBP_RD.upd = true;
        IBS.READ(SCCGWA, TDRPBP, TDTPBP_RD);
    }
    public void T000_UPDATE_TDTPBP() throws IOException,SQLException,Exception {
        TDRPBP.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRPBP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRPBP.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTPBP_RD = new DBParm();
        TDTPBP_RD.TableName = "TDTPBP";
        IBS.REWRITE(SCCGWA, TDRPBP, TDTPBP_RD);
    }
    public void T000_WRITE_TDTSTS() throws IOException,SQLException,Exception {
        TDRSTS.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSTS.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSTS.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTSTS_RD = new DBParm();
        TDTSTS_RD.TableName = "TDTSTS";
        IBS.WRITE(SCCGWA, TDRSTS, TDTSTS_RD);
    }
    public void T000_READ_TDCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
    }
    public void T000_REWRITE_TDTSTS() throws IOException,SQLException,Exception {
        TDRSTS.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSTS.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSTS.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDTSTS_RD = new DBParm();
        TDTSTS_RD.TableName = "TDTSTS";
        IBS.REWRITE(SCCGWA, TDRSTS, TDTSTS_RD);
    }
    public void T000_READUP_TDTSTS() throws IOException,SQLException,Exception {
        TDTSTS_RD = new DBParm();
        TDTSTS_RD.TableName = "TDTSTS";
        TDTSTS_RD.where = "STR_DATE <= :TDRSTS.STR_DATE";
        TDTSTS_RD.upd = true;
        IBS.READ(SCCGWA, TDRSTS, this, TDTSTS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_STS_FLG = 'Y';
        } else {
            WS_STS_FLG = 'N';
        }
    }
    public void T000_READUP_TDTSTS_R() throws IOException,SQLException,Exception {
        TDTSTS_RD = new DBParm();
        TDTSTS_RD.TableName = "TDTSTS";
        TDTSTS_RD.upd = true;
        IBS.READ(SCCGWA, TDRSTS, TDTSTS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_STS_FLG = 'Y';
        } else {
            WS_STS_FLG = 'N';
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
