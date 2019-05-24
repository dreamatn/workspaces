package com.hisun.PS;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class PSZSQRTD {
    DBParm PSTOBLL_RD;
    brParm PSTOBLL_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_EXG_BK_NO = "001";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    int WS_ISS_BR_MSG = 0;
    short WS_NUM = 0;
    short WS_A = 0;
    char WS_READ_TRAN = ' ';
    char WS_TBL_FLAG = ' ';
    double WS_SUM_AMT = 0;
    short WS_PAGE_ROW = 0;
    PSZSQRTD_WS_OUT_DATA WS_OUT_DATA = new PSZSQRTD_WS_OUT_DATA();
    PSZSQRTD_WS_TEMP WS_TEMP = new PSZSQRTD_WS_TEMP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCFMT SCCFMT = new SCCFMT();
    PSROBLL PSROBLL = new PSROBLL();
    PSRIBLL PSRIBLL = new PSRIBLL();
    PSRPBIN PSRPBIN = new PSRPBIN();
    PSREINF PSREINF = new PSREINF();
    int WS_I_EXG_B_DT = 0;
    int WS_I_EXG_E_DT = 0;
    String WS_I_EXG_BK_NO = " ";
    String WS_I_EXG_AREA_NO = " ";
    short WS_I_EXG_TMS = 0;
    int WS_I_ACT_EXG_DT = 0;
    int WS_BIG_ACT_EXG_DT = 0;
    short WS_I_ACT_EXG_TMS = 0;
    short WS_BIG_ACT_EXG_TMS = 0;
    String WS_I_EXG_CCY = " ";
    char WS_I_EXG_DC = ' ';
    String WS_I_OUR_EXG_NO = " ";
    int WS_I_EXG_TX_BR = 0;
    int WS_RCD_SEQ = 0;
    int WS_CNT = 0;
    double WS_AMT = 0;
    String K_OUTPUT_FMT1 = "PS430";
    int K_SCR_ROW_NO = 10;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PSCSQRTD PSCSQRTD;
    public void MP(SCCGWA SCCGWA, PSCSQRTD PSCSQRTD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PSCSQRTD = PSCSQRTD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PSZSQRTD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B200_INPUT_DATA();
        if (pgmRtn) return;
        B310_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (PSCSQRTD.EXG_AREA_NO.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_AREA_NO_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCSQRTD.EXG_B_DT > PSCSQRTD.EXG_E_DT) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_B_E_DT_ER;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, PSCSQRTD.EXG_REPT_DT);
        if (PSCSQRTD.EXG_REPT_DT != 0) {
            if (PSCSQRTD.EXG_B_DT > PSCSQRTD.EXG_REPT_DT) {
                WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_REPT_B_DT_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B200_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSROBLL);
        WS_I_EXG_BK_NO = PSCSQRTD.BK_NO;
        WS_I_EXG_AREA_NO = PSCSQRTD.EXG_AREA_NO;
        WS_I_EXG_TMS = PSCSQRTD.EXG_TMS;
        if (PSCSQRTD.EXG_REPT_DT != 0) {
            WS_I_ACT_EXG_DT = PSCSQRTD.EXG_REPT_DT;
            WS_BIG_ACT_EXG_DT = PSCSQRTD.EXG_REPT_DT;
        } else {
            WS_I_ACT_EXG_DT = 0;
            WS_BIG_ACT_EXG_DT = 99991231;
        }
        CEP.TRC(SCCGWA, PSCSQRTD.EXG_REPT_DT);
        CEP.TRC(SCCGWA, WS_BIG_ACT_EXG_DT);
        if (PSCSQRTD.EXG_REPT_TMS != 0) {
            WS_I_ACT_EXG_TMS = PSCSQRTD.EXG_REPT_TMS;
            WS_BIG_ACT_EXG_TMS = PSCSQRTD.EXG_REPT_TMS;
        } else {
            WS_I_ACT_EXG_TMS = 0;
            WS_BIG_ACT_EXG_TMS = 999;
        }
        CEP.TRC(SCCGWA, PSCSQRTD.EXG_REPT_TMS);
        CEP.TRC(SCCGWA, WS_BIG_ACT_EXG_TMS);
        WS_I_EXG_CCY = PSCSQRTD.EXG_CCY;
        WS_I_EXG_DC = PSCSQRTD.EXG_DC_FLG;
        WS_I_OUR_EXG_NO = PSCSQRTD.OUR_EXG_NO;
        WS_I_EXG_TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, WS_I_EXG_TX_BR);
        WS_I_EXG_B_DT = PSCSQRTD.EXG_B_DT;
        WS_I_EXG_E_DT = PSCSQRTD.EXG_E_DT;
    }
    public void B310_OUTPUT_PROC() throws IOException,SQLException,Exception {
        T000_COUNT_PNTBCC();
        if (pgmRtn) return;
        T000_STARTBR_TRAN_PSTOBLL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LUO");
        CEP.TRC(SCCGWA, PSCSQRTD.PAGE_NUM);
        if (PSCSQRTD.PAGE_NUM == 0) {
            WS_RCD_SEQ = 1;
        } else {
            WS_RCD_SEQ = ( PSCSQRTD.PAGE_NUM - 1 ) * K_SCR_ROW_NO + 1;
        }
        CEP.TRC(SCCGWA, WS_RCD_SEQ);
        T000_READNEXT_FIRST_PSTOBLL();
        if (pgmRtn) return;
        WS_NUM = 0;
        CEP.TRC(SCCGWA, WS_NUM);
        CEP.TRC(SCCGWA, WS_TBL_FLAG);
        CEP.TRC(SCCGWA, WS_NUM);
        CEP.TRC(SCCGWA, WS_READ_TRAN);
        while (WS_NUM < K_SCR_ROW_NO 
            && WS_READ_TRAN != 'N') {
            WS_NUM = (short) (WS_NUM + 1);
            WS_OUT_DATA.WS_DATA[WS_NUM-1].WS_EXG_AREA_NO = PSROBLL.KEY.EXG_AREA_NO;
            WS_OUT_DATA.WS_DATA[WS_NUM-1].WS_OUR_EXG_NO = PSROBLL.OUR_EXG_NO;
            WS_OUT_DATA.WS_DATA[WS_NUM-1].WS_EXG_CCY = PSROBLL.EXG_CCY;
            WS_OUT_DATA.WS_DATA[WS_NUM-1].WS_EXG_REPT_DT = PSROBLL.ACT_EXG_DT;
            WS_OUT_DATA.WS_DATA[WS_NUM-1].WS_EXG_REPT_TMS = PSROBLL.ACT_EXG_TMS;
            WS_OUT_DATA.WS_DATA[WS_NUM-1].WS_EXG_REPT_FLG = PSROBLL.EXG_REPT_FLG;
            WS_OUT_DATA.WS_DATA[WS_NUM-1].WS_EXG_DT = PSROBLL.KEY.EXG_DT;
            WS_OUT_DATA.WS_DATA[WS_NUM-1].WS_EXG_TMS = PSROBLL.KEY.EXG_TMS;
            WS_OUT_DATA.WS_DATA[WS_NUM-1].WS_TX_JRNNO = PSROBLL.KEY.EXG_JRN_NO;
            WS_OUT_DATA.WS_DATA[WS_NUM-1].WS_OUR_ACNO = PSROBLL.OUR_ACNO;
            WS_OUT_DATA.WS_DATA[WS_NUM-1].WS_OUR_ACNM = PSROBLL.OUR_ACNM;
            WS_OUT_DATA.WS_DATA[WS_NUM-1].WS_CASH_ID = PSROBLL.CASH_ID;
            WS_OUT_DATA.WS_DATA[WS_NUM-1].WS_OTH_EXG_NO = PSROBLL.OTH_EXG_NO;
            WS_OUT_DATA.WS_DATA[WS_NUM-1].WS_EXG_DC = PSROBLL.EXG_DC;
            WS_OUT_DATA.WS_DATA[WS_NUM-1].WS_EXG_VOUCH_CD = PSROBLL.EXG_VOUCH_CD;
            WS_OUT_DATA.WS_DATA[WS_NUM-1].WS_EXG_CERT_NO = PSROBLL.EXG_CERT_NO;
            WS_OUT_DATA.WS_DATA[WS_NUM-1].WS_CERT_DT = PSROBLL.CERT_DT;
            WS_OUT_DATA.WS_DATA[WS_NUM-1].WS_ISS_BKNO = PSROBLL.ISS_BKNO;
            WS_OUT_DATA.WS_DATA[WS_NUM-1].WS_EXP_DT = PSROBLL.EXP_DT;
            WS_OUT_DATA.WS_DATA[WS_NUM-1].WS_ISS_AMT = PSROBLL.ISS_AMT;
            WS_OUT_DATA.WS_DATA[WS_NUM-1].WS_EXG_AMT = PSROBLL.EXG_AMT;
            WS_OUT_DATA.WS_DATA[WS_NUM-1].WS_OTH_ACNO = PSROBLL.OTH_ACNO;
            WS_OUT_DATA.WS_DATA[WS_NUM-1].WS_OTH_ACNM = PSROBLL.OTH_ACNM;
            CEP.TRC(SCCGWA, WS_NUM);
            CEP.TRC(SCCGWA, "1234567");
            CEP.TRC(SCCGWA, WS_OUT_DATA.WS_DATA[WS_NUM-1].WS_TX_JRNNO);
            T000_READNEXT_PSTOBLL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_READ_TRAN);
        }
        T000_ENDBR_PSTOBLL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_NUM);
        if (PSCSQRTD.PAGE_NUM == 0) {
            WS_OUT_DATA.WS_CURR_PAGE = 1;
        } else {
            WS_OUT_DATA.WS_CURR_PAGE = (short) PSCSQRTD.PAGE_NUM;
        }
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_CURR_PAGE);
        WS_OUT_DATA.WS_LAST_PAGE = 'N';
        CEP.TRC(SCCGWA, "N");
        if (WS_NUM <= K_SCR_ROW_NO 
            && WS_TBL_FLAG == 'N') {
            WS_OUT_DATA.WS_LAST_PAGE = 'Y';
            CEP.TRC(SCCGWA, "Y");
        }
        WS_PAGE_ROW = WS_NUM;
        WS_OUT_DATA.WS_CURR_PAGE_ROW = WS_NUM;
        CEP.TRC(SCCGWA, WS_PAGE_ROW);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_CURR_PAGE);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_CURR_PAGE_ROW);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT1;
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 7437;
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_COUNT_PNTBCC() throws IOException,SQLException,Exception {
        if ((PSCSQRTD.EXG_B_DT != 0 
                && PSCSQRTD.EXG_E_DT != 0) 
                && PSCSQRTD.EXG_TMS != 0 
                && PSCSQRTD.EXG_DC_FLG != ' ' 
                && PSCSQRTD.EXG_REPT_DT != 0 
                && PSCSQRTD.EXG_REPT_TMS != 0) {
            PSTOBLL_RD = new DBParm();
            PSTOBLL_RD.TableName = "PSTOBLL";
            PSTOBLL_RD.set = "WS-CNT=COUNT(*)";
            PSTOBLL_RD.where = "EXG_BK_NO = :WS_I_EXG_BK_NO "
                + "AND EXG_AREA_NO = :WS_I_EXG_AREA_NO "
                + "AND EXG_DT >= :WS_I_EXG_B_DT "
                + "AND EXG_DT <= :WS_I_EXG_E_DT "
                + "AND EXG_TMS = :WS_I_EXG_TMS "
                + "AND EXG_TX_BR = :WS_I_EXG_TX_BR "
                + "AND EXG_DC = :WS_I_EXG_DC "
                + "AND SHL_EXG_DT = :WS_I_ACT_EXG_DT "
                + "AND SHL_EXG_TMS = :WS_I_ACT_EXG_TMS";
            IBS.GROUP(SCCGWA, PSROBLL, this, PSTOBLL_RD);
        } else if ((PSCSQRTD.EXG_B_DT != 0 
                && PSCSQRTD.EXG_E_DT != 0) 
                && PSCSQRTD.EXG_TMS != 0 
                && PSCSQRTD.EXG_DC_FLG != ' ' 
                && PSCSQRTD.EXG_REPT_DT == 0 
                && PSCSQRTD.EXG_REPT_TMS != 0) {
            PSTOBLL_RD = new DBParm();
            PSTOBLL_RD.TableName = "PSTOBLL";
            PSTOBLL_RD.set = "WS-CNT=COUNT(*)";
            PSTOBLL_RD.where = "EXG_BK_NO = :WS_I_EXG_BK_NO "
                + "AND EXG_AREA_NO = :WS_I_EXG_AREA_NO "
                + "AND EXG_DT >= :WS_I_EXG_B_DT "
                + "AND EXG_DT <= :WS_I_EXG_E_DT "
                + "AND EXG_TMS = :WS_I_EXG_TMS "
                + "AND EXG_TX_BR = :WS_I_EXG_TX_BR "
                + "AND EXG_DC = :WS_I_EXG_DC "
                + "AND SHL_EXG_TMS = :WS_I_ACT_EXG_TMS";
            IBS.GROUP(SCCGWA, PSROBLL, this, PSTOBLL_RD);
        } else if ((PSCSQRTD.EXG_B_DT != 0 
                && PSCSQRTD.EXG_E_DT != 0) 
                && PSCSQRTD.EXG_TMS != 0 
                && PSCSQRTD.EXG_DC_FLG != ' ' 
                && PSCSQRTD.EXG_REPT_DT != 0 
                && PSCSQRTD.EXG_REPT_TMS == 0) {
            PSTOBLL_RD = new DBParm();
            PSTOBLL_RD.TableName = "PSTOBLL";
            PSTOBLL_RD.set = "WS-CNT=COUNT(*)";
            PSTOBLL_RD.where = "EXG_BK_NO = :WS_I_EXG_BK_NO "
                + "AND EXG_AREA_NO = :WS_I_EXG_AREA_NO "
                + "AND EXG_DT >= :WS_I_EXG_B_DT "
                + "AND EXG_DT <= :WS_I_EXG_E_DT "
                + "AND EXG_TMS = :WS_I_EXG_TMS "
                + "AND EXG_TX_BR = :WS_I_EXG_TX_BR "
                + "AND EXG_DC = :WS_I_EXG_DC "
                + "AND SHL_EXG_DT = :WS_I_ACT_EXG_DT";
            IBS.GROUP(SCCGWA, PSROBLL, this, PSTOBLL_RD);
        } else if ((PSCSQRTD.EXG_B_DT != 0 
                && PSCSQRTD.EXG_E_DT != 0) 
                && PSCSQRTD.EXG_TMS != 0 
                && PSCSQRTD.EXG_DC_FLG != ' ' 
                && PSCSQRTD.EXG_REPT_DT == 0 
                && PSCSQRTD.EXG_REPT_TMS == 0) {
            PSTOBLL_RD = new DBParm();
            PSTOBLL_RD.TableName = "PSTOBLL";
            PSTOBLL_RD.set = "WS-CNT=COUNT(*)";
            PSTOBLL_RD.where = "EXG_BK_NO = :WS_I_EXG_BK_NO "
                + "AND EXG_AREA_NO = :WS_I_EXG_AREA_NO "
                + "AND EXG_DT >= :WS_I_EXG_B_DT "
                + "AND EXG_DT <= :WS_I_EXG_E_DT "
                + "AND EXG_TMS = :WS_I_EXG_TMS "
                + "AND EXG_TX_BR = :WS_I_EXG_TX_BR "
                + "AND EXG_DC = :WS_I_EXG_DC";
            IBS.GROUP(SCCGWA, PSROBLL, this, PSTOBLL_RD);
        } else {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_INPUT_DATA_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, "ji shu");
        if (WS_CNT == 0) {
            CEP.ERR(SCCGWA, PSCMSG_ERROR_MSG.PS_NO_RECORD);
        }
        WS_OUT_DATA.WS_TOTAL_NUM = (short) WS_CNT;
        WS_SUM_AMT = WS_AMT;
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_CNT);
        WS_A = (short) (WS_OUT_DATA.WS_TOTAL_NUM % K_SCR_ROW_NO);
        WS_OUT_DATA.WS_TOTAL_PAGE = (short) ((WS_OUT_DATA.WS_TOTAL_NUM - WS_A) / K_SCR_ROW_NO);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_A);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_CURR_PAGE_ROW);
        if (WS_A > 0) {
            WS_OUT_DATA.WS_TOTAL_PAGE = (short) (WS_OUT_DATA.WS_TOTAL_PAGE + 1);
        }
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_TOTAL_PAGE);
        CEP.TRC(SCCGWA, "ZONG YE SHU");
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_TOTAL_NUM);
        CEP.TRC(SCCGWA, "ZONG TIAO SHU");
    }
    public void T000_STARTBR_TRAN_PSTOBLL() throws IOException,SQLException,Exception {
        if ((PSCSQRTD.EXG_B_DT != 0 
                && PSCSQRTD.EXG_E_DT != 0) 
                && PSCSQRTD.EXG_TMS != 0 
                && PSCSQRTD.EXG_DC_FLG != ' ' 
                && PSCSQRTD.EXG_REPT_DT != 0 
                && PSCSQRTD.EXG_REPT_TMS != 0) {
            CEP.TRC(SCCGWA, "11111111");
            CEP.TRC(SCCGWA, WS_I_EXG_BK_NO);
            CEP.TRC(SCCGWA, WS_I_EXG_AREA_NO);
            CEP.TRC(SCCGWA, WS_I_EXG_B_DT);
            CEP.TRC(SCCGWA, WS_I_EXG_E_DT);
            CEP.TRC(SCCGWA, WS_I_EXG_TMS);
            CEP.TRC(SCCGWA, WS_I_EXG_TX_BR);
            WS_TEMP.WS_BROWSE_FLG = 'B';
            PSTOBLL_BR.rp = new DBParm();
            PSTOBLL_BR.rp.TableName = "PSTOBLL";
            PSTOBLL_BR.rp.where = "EXG_BK_NO = :WS_I_EXG_BK_NO "
                + "AND EXG_AREA_NO = :WS_I_EXG_AREA_NO "
                + "AND EXG_DT >= :WS_I_EXG_B_DT "
                + "AND EXG_DT <= :WS_I_EXG_E_DT "
                + "AND EXG_TMS = :WS_I_EXG_TMS "
                + "AND EXG_TX_BR = :WS_I_EXG_TX_BR "
                + "AND EXG_DC = :WS_I_EXG_DC "
                + "AND SHL_EXG_DT = :WS_I_ACT_EXG_DT "
                + "AND SHL_EXG_TMS = :WS_I_ACT_EXG_TMS";
            PSTOBLL_BR.rp.order = "EXG_BK_NO";
            IBS.STARTBR(SCCGWA, PSROBLL, this, PSTOBLL_BR);
        } else if ((PSCSQRTD.EXG_B_DT != 0 
                && PSCSQRTD.EXG_E_DT != 0) 
                && PSCSQRTD.EXG_TMS != 0 
                && PSCSQRTD.EXG_DC_FLG != ' ' 
                && PSCSQRTD.EXG_REPT_DT != 0 
                && PSCSQRTD.EXG_REPT_TMS == 0) {
            CEP.TRC(SCCGWA, "222222222");
            WS_TEMP.WS_BROWSE_FLG = 'A';
            PSTOBLL_BR.rp = new DBParm();
            PSTOBLL_BR.rp.TableName = "PSTOBLL";
            PSTOBLL_BR.rp.where = "EXG_BK_NO = :WS_I_EXG_BK_NO "
                + "AND EXG_AREA_NO = :WS_I_EXG_AREA_NO "
                + "AND EXG_DT >= :WS_I_EXG_B_DT "
                + "AND EXG_DT <= :WS_I_EXG_E_DT "
                + "AND EXG_TMS = :WS_I_EXG_TMS "
                + "AND EXG_TX_BR = :WS_I_EXG_TX_BR "
                + "AND EXG_DC = :WS_I_EXG_DC "
                + "AND SHL_EXG_DT = :WS_I_ACT_EXG_DT";
            PSTOBLL_BR.rp.order = "EXG_BK_NO";
            IBS.STARTBR(SCCGWA, PSROBLL, this, PSTOBLL_BR);
        } else if ((PSCSQRTD.EXG_B_DT != 0 
                && PSCSQRTD.EXG_E_DT != 0) 
                && PSCSQRTD.EXG_TMS != 0 
                && PSCSQRTD.EXG_DC_FLG != ' ' 
                && PSCSQRTD.EXG_REPT_DT == 0 
                && PSCSQRTD.EXG_REPT_TMS != 0) {
            CEP.TRC(SCCGWA, "333333333333333");
            WS_TEMP.WS_BROWSE_FLG = 'N';
            PSTOBLL_BR.rp = new DBParm();
            PSTOBLL_BR.rp.TableName = "PSTOBLL";
            PSTOBLL_BR.rp.where = "EXG_BK_NO = :WS_I_EXG_BK_NO "
                + "AND EXG_AREA_NO = :WS_I_EXG_AREA_NO "
                + "AND EXG_DT >= :WS_I_EXG_B_DT "
                + "AND EXG_DT <= :WS_I_EXG_E_DT "
                + "AND EXG_TMS = :WS_I_EXG_TMS "
                + "AND EXG_TX_BR = :WS_I_EXG_TX_BR "
                + "AND EXG_DC = :WS_I_EXG_DC "
                + "AND SHL_EXG_TMS = :WS_I_ACT_EXG_TMS";
            PSTOBLL_BR.rp.order = "EXG_BK_NO";
            IBS.STARTBR(SCCGWA, PSROBLL, this, PSTOBLL_BR);
        } else if ((PSCSQRTD.EXG_B_DT != 0 
                && PSCSQRTD.EXG_E_DT != 0) 
                && PSCSQRTD.EXG_TMS != 0 
                && PSCSQRTD.EXG_DC_FLG != ' ' 
                && PSCSQRTD.EXG_REPT_DT == 0 
                && PSCSQRTD.EXG_REPT_TMS == 0) {
            CEP.TRC(SCCGWA, "444444444444");
            WS_TEMP.WS_BROWSE_FLG = 'N';
            PSTOBLL_BR.rp = new DBParm();
            PSTOBLL_BR.rp.TableName = "PSTOBLL";
            PSTOBLL_BR.rp.where = "EXG_BK_NO = :WS_I_EXG_BK_NO "
                + "AND EXG_AREA_NO = :WS_I_EXG_AREA_NO "
                + "AND EXG_DT >= :WS_I_EXG_B_DT "
                + "AND EXG_DT <= :WS_I_EXG_E_DT "
                + "AND EXG_TMS = :WS_I_EXG_TMS "
                + "AND EXG_TX_BR = :WS_I_EXG_TX_BR "
                + "AND EXG_DC = :WS_I_EXG_DC";
            PSTOBLL_BR.rp.order = "EXG_BK_NO";
            IBS.STARTBR(SCCGWA, PSROBLL, this, PSTOBLL_BR);
        } else {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_INPUT_DATA_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PSTOBLL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_PSTOBLL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, PSROBLL, this, PSTOBLL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_READ_TRAN = 'Y';
        } else {
            WS_READ_TRAN = 'N';
        }
    }
    public void T000_READNEXT_FIRST_PSTOBLL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, PSROBLL, this, PSTOBLL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_READ_TRAN = 'Y';
        } else {
            WS_READ_TRAN = 'N';
        }
        if (WS_READ_TRAN == 'N') {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_PSTOBLL_NOFUNT_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_PSTOBLL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, PSTOBLL_BR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
