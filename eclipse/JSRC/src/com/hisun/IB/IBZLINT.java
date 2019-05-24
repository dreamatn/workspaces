package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.DD.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class IBZLINT {
    BigDecimal bigD;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm IBTSCASH_RD;
    DBParm IBTMST_RD;
    DBParm IBTINRH_RD;
    DBParm IBTINTH_RD;
    DBParm IBTINSH_RD;
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    char K_INT_ACCRU = '1';
    char K_INT_SETTL = '2';
    String K_IBTMST = "IBTMST";
    String K_IBTBALF = "IBTBALF";
    String K_IBTINRH = "IBTINRH";
    String K_IBTINTH = "IBTINTH";
    String K_IBTINSH = "IBTINSH";
    String K_IBTSCASH = "IBTSCASH";
    String K_IB_PROD_MODEL = "IBDD";
    String K_OUTPUT_FMT = "IBT07";
    String PGM_SCSSCLDT = "SCSSCLDT";
    int WS_SEQ = 0;
    double WS_INT = 0;
    int WS_VALUE_DATE = 0;
    double WS_BUD_INT = 0;
    double WS_INTS_AMT = 0;
    int WS_DATE = 0;
    char WS_TABLE_REC = ' ';
    char WS_RATE_CHANGE = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    DDCSCINM DDCSCINM = new DDCSCINM();
    SCCCLDT SCCCLDT = new SCCCLDT();
    IBCQINF IBCQINF = new IBCQINF();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCBWEVT BPCBWEVT = new BPCBWEVT();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    IBCUBAL IBCUBAL = new IBCUBAL();
    IBCINFHI IBCINFHI = new IBCINFHI();
    DDCSLLCX DDCSLLCX = new DDCSLLCX();
    IBRMST IBRMST = new IBRMST();
    IBRINRH IBRINRH = new IBRINRH();
    IBRINTH IBRINTH = new IBRINTH();
    IBRINSH IBRINSH = new IBRINSH();
    IBRSCASH IBRSCASH = new IBRSCASH();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    SCCBATH SCCBATH;
    IBCLINT IBCLINT;
    public void MP(SCCGWA SCCGWA, IBCLINT IBCLINT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCLINT = IBCLINT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZLINT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
        CEP.TRC(SCCGWA, SCCBATH.JPRM.AC_DATE);
        IBS.init(SCCGWA, SCCEXCP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_GET_IB_AC();
        if (pgmRtn) return;
        B030_GET_AC_INFO_UPD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, IBCLINT.FUNC);
        if (IBCLINT.FUNC == K_INT_ACCRU) {
            B040_UPDATE_IBTMST();
            if (pgmRtn) return;
            B070_WRITE_INTH();
            if (pgmRtn) return;
            B080_PROC_VOUCHER();
            if (pgmRtn) return;
        } else if (IBCLINT.FUNC == K_INT_SETTL) {
            B050_UPDATE_IBTMST();
            if (pgmRtn) return;
            B090_VOUCHER_HIST();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + IBCLINT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCLINT.FUNC);
        CEP.TRC(SCCGWA, IBCLINT.AC_DATE);
        CEP.TRC(SCCGWA, IBCLINT.VOSTRO_AC);
        CEP.TRC(SCCGWA, IBCLINT.BAL);
        CEP.TRC(SCCGWA, IBCLINT.BUD_INT);
        CEP.TRC(SCCGWA, IBCLINT.L_BUD_INT);
        CEP.TRC(SCCGWA, IBCLINT.OD_BUD_INT);
        CEP.TRC(SCCGWA, IBCLINT.OD_L_BUD_INT);
        CEP.TRC(SCCGWA, IBCLINT.INTS_AMT);
        CEP.TRC(SCCGWA, IBCLINT.OD_INTS_AMT);
        CEP.TRC(SCCGWA, IBCLINT.RATE);
        if (IBCLINT.FUNC == ' ') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.FCN_MUST_INPUT, IBCLINT.RC);
            Z_RET();
            if (pgmRtn) return;
        } else if (IBCLINT.FUNC == '1') {
            if (IBCLINT.RATE == 0) {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.INPUT, IBCLINT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if ((IBCLINT.BAL >= 0 
                && IBCLINT.BUD_INT == 0) 
                || (IBCLINT.BAL < 0 
                && IBCLINT.OD_BUD_INT == 0)) {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.INPUT, IBCLINT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else if (IBCLINT.FUNC == '2') {
            if ((IBCLINT.BAL >= 0 
                && IBCLINT.INTS_AMT == 0)) {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.INPUT, IBCLINT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
        }
        if (SCCGWA.COMM_AREA.AC_DATE == 0 
            || IBCLINT.VOSTRO_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT, IBCLINT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_IB_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRSCASH);
        IBRSCASH.VOSTRO_AC = IBCLINT.VOSTRO_AC;
        T000_READ_SCASH_FIRST();
        if (pgmRtn) return;
        if (WS_TABLE_REC == 'N') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.NOTFND_SCASH, IBCLINT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_GET_AC_INFO_UPD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRMST);
        IBRMST.KEY.AC_NO = IBRSCASH.AC_NO;
        T000_READ_IBTMST_UPD();
        if (pgmRtn) return;
        if (WS_TABLE_REC == 'N') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.AC_NOEXIST, IBCLINT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (IBRMST.AC_STS == 'C') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.NOT_NORMAL, IBCLINT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B030_01_CHECK_DATE();
        if (pgmRtn) return;
    }
    public void B030_01_CHECK_DATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBRMST.CCY);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, IBRMST.EFF_DATE);
        if (IBCLINT.FUNC == '2') {
            if (SCCGWA.COMM_AREA.AC_DATE < IBRMST.EFF_DATE) {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.NOT_TH_EFF_DATE, IBCLINT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, IBRMST.L_INTS_DT);
            if ((IBCLINT.BAL >= 0 
                && SCCGWA.COMM_AREA.AC_DATE <= IBRMST.L_INTS_DT) 
                || (IBCLINT.BAL < 0 
                && SCCGWA.COMM_AREA.AC_DATE <= IBRMST.OD_L_INTS_DT)) {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.INP_DT_GE_LASTDT, IBCLINT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B040_UPDATE_IBTMST() throws IOException,SQLException,Exception {
        if (IBCLINT.BUD_INT != 0) {
            IBRMST.L_BUD_INT = IBRMST.BUD_INT;
            IBRMST.BUD_INT = IBCLINT.BUD_INT;
        }
        IBRMST.OD_BUD_INT = IBCLINT.OD_BUD_INT;
        IBRMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        IBRMST.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_IBTMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, IBCLINT.BAL);
        CEP.TRC(SCCGWA, IBCLINT.RATE);
        CEP.TRC(SCCGWA, IBRMST.OD_RATE);
        CEP.TRC(SCCGWA, IBRMST.CCY);
        T000_READ_IBTINRH_FIRST();
        if (pgmRtn) return;
        B040_01_GET_RATE_INFO();
        if (pgmRtn) return;
        if (DDCSLLCX.CON_RATE != IBRINRH.INT_RATE 
            || DDCSLLCX.OD_CON_RATE != IBRINRH.OD_RATE 
            || !DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_RBAS1.equalsIgnoreCase(IBRINRH.RATE_TYPE) 
            || !DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_RCD1.equalsIgnoreCase(IBRINRH.RATE_TERM) 
            || DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_SPR_PCT1 != IBRINRH.RATE_PCT 
            || DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_SPR1 != IBRINRH.RATE_SPREAD) {
            B060_WRITE_INRH();
            if (pgmRtn) return;
        }
    }
    public void B040_01_GET_RATE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSLLCX);
        DDCSLLCX.FUNC = 'L';
        DDCSLLCX.AC = IBRSCASH.VOSTRO_AC;
        S000_CALL_DDCSLLCX();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, IBRMST.CCY);
    }
    public void B050_UPDATE_IBTMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBRMST.LAST_FI_DATE);
        CEP.TRC(SCCGWA, IBCLINT.INTS_AMT);
        CEP.TRC(SCCGWA, IBCLINT.OD_INTS_AMT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, SCCBATH.JPRM.NEXT_AC_DATB);
        if (SCCBATH.JPRM.NEXT_AC_DATB > IBRMST.LAST_FI_DATE) {
            B050_01_UPDATE();
            if (pgmRtn) return;
        } else if (SCCBATH.JPRM.NEXT_AC_DATB == IBRMST.LAST_FI_DATE) {
            B050_02_UPDATE();
            if (pgmRtn) return;
        } else {
        }
        CEP.TRC(SCCGWA, IBRMST.L_INTS_DT);
        CEP.TRC(SCCGWA, IBRMST.OD_L_INTS_DT);
        IBRMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        IBRMST.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_IBTMST();
        if (pgmRtn) return;
    }
    public void B050_01_UPDATE() throws IOException,SQLException,Exception {
        if (IBCLINT.INTS_AMT != 0) {
            IBRMST.L_VALUE_BAL = IBRMST.VALUE_BAL;
            IBRMST.VALUE_BAL = IBRMST.VALUE_BAL + IBCLINT.INTS_AMT;
            CEP.TRC(SCCGWA, "33333");
            CEP.TRC(SCCGWA, IBRMST.VALUE_BAL);
            IBRMST.L_INTS_DT = SCCGWA.COMM_AREA.AC_DATE;
            IBRMST.LAST_FI_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (IBCLINT.OD_INTS_AMT != 0) {
            IBRMST.VALUE_BAL = IBRMST.VALUE_BAL - IBCLINT.OD_INTS_AMT;
            CEP.TRC(SCCGWA, "44444");
            CEP.TRC(SCCGWA, IBRMST.VALUE_BAL);
            IBRMST.OD_BUD_INT = 0;
            IBRMST.OD_L_INTS_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
    }
    public void B050_02_UPDATE() throws IOException,SQLException,Exception {
        if (IBCLINT.INTS_AMT != 0) {
            IBRMST.VALUE_BAL = IBRMST.VALUE_BAL + IBCLINT.INTS_AMT;
            IBRMST.BUD_INT = IBRMST.BUD_INT - IBCLINT.INTS_AMT;
            CEP.TRC(SCCGWA, "55555");
            CEP.TRC(SCCGWA, IBRMST.L_VALUE_BAL);
            CEP.TRC(SCCGWA, IBRMST.VALUE_BAL);
            CEP.TRC(SCCGWA, IBRMST.BUD_INT);
            IBRMST.L_INTS_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (IBCLINT.OD_INTS_AMT != 0) {
            IBRMST.L_VALUE_BAL = IBRMST.L_VALUE_BAL - IBCLINT.OD_INTS_AMT;
            IBRMST.VALUE_BAL = IBRMST.VALUE_BAL - IBCLINT.OD_INTS_AMT;
            IBRMST.OD_BUD_INT = IBCLINT.OD_INTS_AMT - IBRMST.OD_BUD_INT;
            CEP.TRC(SCCGWA, "66666");
            CEP.TRC(SCCGWA, IBRMST.L_VALUE_BAL);
            CEP.TRC(SCCGWA, IBRMST.VALUE_BAL);
            CEP.TRC(SCCGWA, IBRMST.BUD_INT);
            IBRMST.OD_L_INTS_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
    }
    public void B060_WRITE_INRH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "333");
        IBS.init(SCCGWA, IBRINRH);
        IBRINRH.KEY.AC_NO = IBRMST.KEY.AC_NO;
        if (SCCGWA.COMM_AREA.AC_DATE >= DDCSLLCX.ADP_DATE) {
            IBRINRH.KEY.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            IBRINRH.KEY.VALUE_DATE = DDCSLLCX.ADP_DATE;
        }
        T000_READ_IBTINRH_UPD();
        if (pgmRtn) return;
        if (WS_TABLE_REC == 'N') {
            B060_01_GET_RATE_INFO();
            if (pgmRtn) return;
            IBRINRH.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBRINRH.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            IBRINRH.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBRINRH.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
            IBRINRH.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_WRITE_IBTINRH();
            if (pgmRtn) return;
        } else {
            B060_01_GET_RATE_INFO();
            if (pgmRtn) return;
            IBRINRH.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBRINRH.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
            IBRINRH.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_IBTINRH();
            if (pgmRtn) return;
        }
    }
    public void B060_01_GET_RATE_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBRMST.CCY);
        if (DDCSLLCX.TIR_TYPE == 'N') {
            if (DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_FIX_RATE1 != 0) {
                IBRINRH.RATE_MTH = '1';
                IBRINRH.INT_RATE = DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_FIX_RATE1;
            } else {
                IBRINRH.RATE_MTH = '2';
                IBRINRH.RATE_TYPE = DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_RBAS1;
                IBRINRH.RATE_TERM = DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_RCD1;
                IBRINRH.RATE_PCT = DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_SPR_PCT1;
                IBRINRH.RATE_SPREAD = DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_SPR1;
            }
            IBRINRH.INT_RATE = DDCSLLCX.CON_RATE;
        } else {
            IBRINRH.RATE_MTH = ' ';
            IBRINRH.RATE_TYPE = " ";
            IBRINRH.RATE_TERM = " ";
            IBRINRH.RATE_PCT = 0;
            IBRINRH.RATE_SPREAD = 0;
            IBRINRH.INT_RATE = 0;
        }
        IBRINRH.OD_RATE = DDCSLLCX.OD_CON_RATE;
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = IBRMST.CCY;
        CEP.TRC(SCCGWA, IBRMST.CCY);
        CEP.TRC(SCCGWA, BPCQCCY.DATA.CCY);
        S000_CALL_SCSOCCY();
        if (pgmRtn) return;
        if (BPCQCCY.DATA.CALR_STD.equalsIgnoreCase("01")) {
            IBRINRH.CALR_STD = 360;
        } else if (BPCQCCY.DATA.CALR_STD.equalsIgnoreCase("02")) {
            IBRINRH.CALR_STD = 365;
        } else if (BPCQCCY.DATA.CALR_STD.equalsIgnoreCase("03")) {
            IBRINRH.CALR_STD = 366;
        } else {
        }
        CEP.TRC(SCCGWA, IBRINRH.CALR_STD);
        IBS.init(SCCGWA, DDCSCINM);
        DDCSCINM.INPUT_DATA.CCY = IBRMST.CCY;
        DDCSCINM.INPUT_DATA.AC_NO = IBRSCASH.VOSTRO_AC;
        S000_CALL_DDZSCINM();
        if (pgmRtn) return;
        IBRINRH.RATE_FLAG = DDCSCINM.OUTPUT_DATA.CCY_INFO[1-1].CCY_CINT_FLG;
        IBRINRH.PRV_FLAG = IBRINRH.RATE_FLAG;
    }
    public void B070_WRITE_INTH() throws IOException,SQLException,Exception {
        if (IBCLINT.BAL >= 0) {
            WS_INT = IBCLINT.BUD_INT - IBCLINT.L_BUD_INT;
        } else {
            WS_INT = IBCLINT.OD_BUD_INT;
        }
        CEP.TRC(SCCGWA, WS_INT);
        IBS.init(SCCGWA, IBRINTH);
        IBRINTH.KEY.AC_NO = IBRMST.KEY.AC_NO;
        IBRINTH.KEY.INT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRINTH.END_BAL = IBCLINT.BAL;
        CEP.TRC(SCCGWA, "111");
        if (IBCLINT.BAL >= 0) {
            IBRINTH.INT = WS_INT;
            IBRINTH.BUD_INT = IBCLINT.BUD_INT;
        } else {
            IBRINTH.OD_INT = WS_INT * -1;
            IBRINTH.OD_BUD_INT = IBCLINT.OD_BUD_INT * -1;
            IBRINTH.INT = IBRMST.BUD_INT - IBRMST.L_BUD_INT;
            IBRINTH.BUD_INT = IBRMST.BUD_INT;
        }
        CEP.TRC(SCCGWA, IBRINTH.INT);
        CEP.TRC(SCCGWA, IBRINTH.OD_INT);
        CEP.TRC(SCCGWA, IBRINTH.BUD_INT);
        CEP.TRC(SCCGWA, IBRINTH.OD_BUD_INT);
        IBRINTH.INT_RATE = IBCLINT.RATE;
        IBRINTH.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRINTH.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBRINTH.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRINTH.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        IBRINTH.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, "222");
        T000_WRITE_IBTINTH();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "333");
    }
    public void B080_PROC_VOUCHER() throws IOException,SQLException,Exception {
        if (WS_INT != 0) {
            IBS.init(SCCGWA, BPCBWEVT);
            BPCBWEVT.INFO.AP_MMO = "IB";
            BPCBWEVT.INFO.TR_DATE = SCCGWA.COMM_AREA.TR_DATE;
            BPCBWEVT.INFO.TR_TIME = SCCGWA.COMM_AREA.TR_TIME;
            BPCBWEVT.INFO.TR_BK = SCCGWA.COMM_AREA.TR_BANK;
            BPCBWEVT.INFO.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.VCH_NO, BPCBWEVT.INFO.SET_NO);
            BPCBWEVT.INFO.EVENT.CNTR_TYPE = "IBDD";
            BPCBWEVT.INFO.EVENT.EVENT_CODE = "DY";
            BPCBWEVT.INFO.EVENT.PROD_CODE = IBRMST.PROD_CD;
            BPCBWEVT.INFO.SET_NO.BFVCH_CD = "IB04";
            BPCBWEVT.INFO.EVENT.BR_OLD = IBRMST.POST_CTR;
            BPCBWEVT.INFO.EVENT.BR_NEW = IBRMST.POST_CTR;
            BPCBWEVT.INFO.EVENT.EVENT_CCY[01-1].CCY = IBRMST.CCY;
            if (IBCLINT.BAL >= 0) {
                BPCBWEVT.INFO.EVENT.EVENT_AMT[04-1].AMT = IBCLINT.BUD_INT * 1;
                bigD = new BigDecimal(BPCBWEVT.INFO.EVENT.EVENT_AMT[04-1].AMT);
                BPCBWEVT.INFO.EVENT.EVENT_AMT[04-1].AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
                BPCBWEVT.INFO.EVENT.EVENT_AMT[02-1].AMT = IBCLINT.L_BUD_INT * 1;
                bigD = new BigDecimal(BPCBWEVT.INFO.EVENT.EVENT_AMT[02-1].AMT);
                BPCBWEVT.INFO.EVENT.EVENT_AMT[02-1].AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
            } else {
                BPCBWEVT.INFO.EVENT.EVENT_AMT[23-1].AMT = IBCLINT.OD_BUD_INT * 1;
                bigD = new BigDecimal(BPCBWEVT.INFO.EVENT.EVENT_AMT[23-1].AMT);
                BPCBWEVT.INFO.EVENT.EVENT_AMT[23-1].AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
                BPCBWEVT.INFO.EVENT.EVENT_AMT[22-1].AMT = IBCLINT.OD_L_BUD_INT * 1;
                bigD = new BigDecimal(BPCBWEVT.INFO.EVENT.EVENT_AMT[22-1].AMT);
                BPCBWEVT.INFO.EVENT.EVENT_AMT[22-1].AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
            }
            BPCBWEVT.INFO.EVENT.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCBWEVT.INFO.EVENT.AC_NO = IBRMST.ACO_AC;
            BPCBWEVT.INFO.EVENT.DESC = "IBDD-L AC DY";
            BPCBWEVT.INFO.EVENT.POST_NARR = "IBDD-L AC DY";
            S000_CALL_BPZBWEVT();
            if (pgmRtn) return;
        }
    }
    public void B090_VOUCHER_HIST() throws IOException,SQLException,Exception {
        if (IBCLINT.INTS_AMT != 0) {
            if (SCCGWA.COMM_AREA.AC_DATE > IBRMST.L_INTS_DT) {
                WS_VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                WS_VALUE_DATE = IBRMST.L_INTS_DT;
            }
            WS_INTS_AMT = IBCLINT.INTS_AMT;
            WS_BUD_INT = IBCLINT.INTS_AMT;
            CEP.TRC(SCCGWA, WS_BUD_INT);
            B090_01_WRITE_BALF();
            if (pgmRtn) return;
            B090_02_PROC_VOUCHER();
            if (pgmRtn) return;
            B090_03_WRITE_HIST();
            if (pgmRtn) return;
            B090_04_WRITE_INSH();
            if (pgmRtn) return;
        }
        if (IBCLINT.OD_INTS_AMT != 0) {
            if (SCCGWA.COMM_AREA.AC_DATE > IBRMST.OD_L_INTS_DT) {
                WS_VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            } else {
                WS_VALUE_DATE = IBRMST.OD_L_INTS_DT;
            }
            WS_INTS_AMT = IBCLINT.OD_INTS_AMT;
            WS_BUD_INT = IBCLINT.OD_INTS_AMT;
            CEP.TRC(SCCGWA, WS_BUD_INT);
            B090_01_WRITE_BALF();
            if (pgmRtn) return;
            B090_02_PROC_VOUCHER();
            if (pgmRtn) return;
            B090_03_WRITE_HIST();
            if (pgmRtn) return;
            B090_04_WRITE_INSH();
            if (pgmRtn) return;
        }
    }
    public void B090_01_WRITE_BALF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCUBAL);
        IBCUBAL.AC_NO = IBRMST.KEY.AC_NO;
        IBCUBAL.VALUE_DATE = WS_VALUE_DATE;
        IBCUBAL.AMT = WS_INTS_AMT;
        if (IBCLINT.INTS_AMT != 0) {
            IBCUBAL.SIGN = 'D';
        }
        if (IBCLINT.OD_INTS_AMT != 0) {
            IBCUBAL.SIGN = 'C';
        }
        IBCUBAL.FUNC = '1';
        S000_CALL_IBZUBAL();
        if (pgmRtn) return;
    }
    public void B090_02_PROC_VOUCHER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCBWEVT);
        BPCBWEVT.INFO.AP_MMO = "IB";
        BPCBWEVT.INFO.TR_DATE = SCCGWA.COMM_AREA.TR_DATE;
        BPCBWEVT.INFO.TR_TIME = SCCGWA.COMM_AREA.TR_TIME;
        BPCBWEVT.INFO.TR_BK = SCCGWA.COMM_AREA.TR_BANK;
        BPCBWEVT.INFO.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.VCH_NO, BPCBWEVT.INFO.SET_NO);
        BPCBWEVT.INFO.EVENT.CNTR_TYPE = "IBDD";
        BPCBWEVT.INFO.EVENT.EVENT_CODE = "IS";
        BPCBWEVT.INFO.EVENT.PROD_CODE = IBRMST.PROD_CD;
        BPCBWEVT.INFO.EVENT.BR_OLD = IBRMST.POST_CTR;
        BPCBWEVT.INFO.EVENT.BR_NEW = IBRMST.POST_CTR;
        BPCBWEVT.INFO.EVENT.EVENT_CCY[01-1].CCY = IBRMST.CCY;
        if (IBCLINT.INTS_AMT != 0) {
            BPCBWEVT.INFO.EVENT.EVENT_AMT[04-1].AMT = WS_INTS_AMT * 1;
            bigD = new BigDecimal(BPCBWEVT.INFO.EVENT.EVENT_AMT[04-1].AMT);
            BPCBWEVT.INFO.EVENT.EVENT_AMT[04-1].AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
            BPCBWEVT.INFO.EVENT.EVENT_AMT[02-1].AMT = WS_BUD_INT * 1;
            bigD = new BigDecimal(BPCBWEVT.INFO.EVENT.EVENT_AMT[02-1].AMT);
            BPCBWEVT.INFO.EVENT.EVENT_AMT[02-1].AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
            BPCBWEVT.INFO.EVENT.EFF_DAYS = 1;
            BPCBWEVT.INFO.SET_NO.BFVCH_CD = "IB05";
        } else if (IBCLINT.OD_INTS_AMT != 0) {
            BPCBWEVT.INFO.EVENT.EVENT_AMT[22-1].AMT = WS_INTS_AMT * 1;
            bigD = new BigDecimal(BPCBWEVT.INFO.EVENT.EVENT_AMT[22-1].AMT);
            BPCBWEVT.INFO.EVENT.EVENT_AMT[22-1].AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
            BPCBWEVT.INFO.EVENT.EVENT_AMT[23-1].AMT = WS_BUD_INT * 1;
            bigD = new BigDecimal(BPCBWEVT.INFO.EVENT.EVENT_AMT[23-1].AMT);
            BPCBWEVT.INFO.EVENT.EVENT_AMT[23-1].AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
            BPCBWEVT.INFO.EVENT.EFF_DAYS = 0;
            BPCBWEVT.INFO.SET_NO.BFVCH_CD = "IB06";
        } else {
        }
        BPCBWEVT.INFO.EVENT.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCBWEVT.INFO.EVENT.AC_NO = IBRMST.ACO_AC;
        BPCBWEVT.INFO.EVENT.DESC = "IBDD-L AC IS";
        BPCBWEVT.INFO.EVENT.POST_NARR = "IBDD-L AC IS";
        S000_CALL_BPZBWEVT();
        if (pgmRtn) return;
    }
    public void B090_03_WRITE_HIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCINFHI);
        IBCINFHI.AC_NO = IBRMST.KEY.AC_NO;
        IBCINFHI.SETT_DT = SCCGWA.COMM_AREA.AC_DATE;
        IBCINFHI.ESET_AMT = WS_BUD_INT;
        IBCINFHI.ASET_AMT = WS_INTS_AMT;
        IBCINFHI.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBCINFHI.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.JRNNO = IBCLINT.JRNNO;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.AC = IBRMST.KEY.AC_NO;
        BPCPFHIS.DATA.ACO_AC = IBRMST.ACO_AC;
        B090_03_01_GET_AC_INFO();
        if (pgmRtn) return;
        BPCPFHIS.DATA.TX_CCY = IBRMST.CCY;
        BPCPFHIS.DATA.TX_CCY_TYP = '1';
        BPCPFHIS.DATA.TX_TYPE = 'T';
        if (IBCLINT.INTS_AMT != 0) {
            BPCPFHIS.DATA.TX_AMT = WS_INTS_AMT;
        }
        if (IBCLINT.OD_INTS_AMT != 0) {
            BPCPFHIS.DATA.TX_AMT = WS_INTS_AMT * -1;
        }
        BPCPFHIS.DATA.TX_VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.PROD_CD = IBRMST.PROD_CD;
        BPCPFHIS.DATA.PRDMO_CD = K_IB_PROD_MODEL;
        BPCPFHIS.DATA.TX_MMO = "S109";
        BPCPFHIS.DATA.CI_NO = IBCQINF.OUTPUT_DATA.CI_NO;
        BPCPFHIS.DATA.RLT_AC = IBRMST.KEY.AC_NO;
        BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
        if (SCCGWA.COMM_AREA.REVERSAL_IND == 'Y') {
            BPCPFHIS.DATA.TX_REV_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        BPCPFHIS.DATA.FMT_ID = "IBCINFHI";
        BPCPFHIS.DATA.FMT_CODE = K_OUTPUT_FMT;
        BPCPFHIS.DATA.FMT_LEN = 143;
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, IBCINFHI);
        S000_CALL_BPZPFHIS();
        if (pgmRtn) return;
    }
    public void B090_03_01_GET_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINF);
        IBCQINF.INPUT_DATA.AC_NO = IBRMST.KEY.AC_NO;
        S000_CALL_IBZQINF();
        if (pgmRtn) return;
        BPCPFHIS.DATA.RLT_BANK = "" + IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
        JIBS_tmp_int = BPCPFHIS.DATA.RLT_BANK.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCPFHIS.DATA.RLT_BANK = "0" + BPCPFHIS.DATA.RLT_BANK;
        BPCPFHIS.DATA.RLT_AC_NAME = IBCQINF.OUTPUT_DATA.AC_CHN_NAME;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        BPCPFHIS.DATA.RLT_BK_NM = BPCPQORG.CHN_NM;
    }
    public void B090_04_WRITE_INSH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRINSH);
        IBRINSH.KEY.AC_NO = IBRMST.KEY.AC_NO;
        IBRINSH.KEY.DEAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRINSH.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        T000_READ_IBTINSH_FIRST();
        if (pgmRtn) return;
        WS_SEQ = WS_SEQ + 1;
        IBRINSH.KEY.SEQ = WS_SEQ;
        IBRINSH.INTS_DATE = SCCGWA.COMM_AREA.AC_DATE;
        if (IBCLINT.INTS_AMT > 0) {
            IBRINSH.ESET_AMT = WS_BUD_INT;
            IBRINSH.ASET_AMT = WS_INTS_AMT;
        }
        if (IBCLINT.OD_INTS_AMT > 0) {
            IBRINSH.ESET_AMT = WS_BUD_INT * -1;
            IBRINSH.ASET_AMT = WS_INTS_AMT * -1;
        }
        CEP.TRC(SCCGWA, IBRINSH.ESET_AMT);
        CEP.TRC(SCCGWA, IBRINSH.ASET_AMT);
        IBRINSH.SETT_AC_NO = IBRMST.KEY.AC_NO;
        IBRINSH.SETT_AC_TYPE = 'N';
        IBRINSH.SETT_TYPE = '1';
        IBRINSH.REV_FLG = 'N';
        IBRINSH.AUTH_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBRINSH.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRINSH.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBRINSH.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRINSH.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        IBRINSH.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_IBTINSH();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZBWEVT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BAT-EVENT", BPCBWEVT);
        if (BPCBWEVT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCBWEVT.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_IBZUBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-UPD-BAL", IBCUBAL);
        if (IBCUBAL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCUBAL.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
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
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
        if (IBCQINF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINF.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            CEP.ERR(SCCGWA, SCCCLDT.RC);
        }
    }
    public void S000_CALL_DDZSCINM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-CI-NAME", DDCSCINM);
    }
    public void S000_CALL_SCSOCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_SCZGJRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "SC-GET-JOURNAL-NO";
        SCCCALL.COMMAREA_PTR = null;
        SCCCALL.CONTINUE_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_DDCSLLCX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-AC-LLCX", DDCSLLCX);
    }
    public void T000_READ_SCASH_FIRST() throws IOException,SQLException,Exception {
        IBTSCASH_RD = new DBParm();
        IBTSCASH_RD.TableName = "IBTSCASH";
        IBTSCASH_RD.where = "VOSTRO_AC = :IBRSCASH.VOSTRO_AC";
        IBTSCASH_RD.fst = true;
        IBS.READ(SCCGWA, IBRSCASH, this, IBTSCASH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTSCASH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTMST_UPD() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBTMST_RD.upd = true;
        IBS.READ(SCCGWA, IBRMST, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTMST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_IBTMST() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBS.REWRITE(SCCGWA, IBRMST, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTMST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTINRH_UPD() throws IOException,SQLException,Exception {
        IBTINRH_RD = new DBParm();
        IBTINRH_RD.TableName = "IBTINRH";
        IBTINRH_RD.upd = true;
        IBS.READ(SCCGWA, IBRINRH, IBTINRH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTINRH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTINRH_FIRST() throws IOException,SQLException,Exception {
        IBTINRH_RD = new DBParm();
        IBTINRH_RD.TableName = "IBTINRH";
        IBTINRH_RD.where = "AC_NO = :IBRMST.KEY.AC_NO";
        IBTINRH_RD.fst = true;
        IBTINRH_RD.order = "VALUE_DATE DESC";
        IBS.READ(SCCGWA, IBRINRH, this, IBTINRH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTINRH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_IBTINRH() throws IOException,SQLException,Exception {
        IBTINRH_RD = new DBParm();
        IBTINRH_RD.TableName = "IBTINRH";
        IBS.WRITE(SCCGWA, IBRINRH, IBTINRH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.REC_DUPKEY);
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTINRH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_IBTINRH() throws IOException,SQLException,Exception {
        IBTINRH_RD = new DBParm();
        IBTINRH_RD.TableName = "IBTINRH";
        IBS.REWRITE(SCCGWA, IBRINRH, IBTINRH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTINRH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_IBTINTH() throws IOException,SQLException,Exception {
        IBTINTH_RD = new DBParm();
        IBTINTH_RD.TableName = "IBTINTH";
        IBS.WRITE(SCCGWA, IBRINTH, IBTINTH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "WRITE");
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.REC_DUPKEY);
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTINTH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTINSH_FIRST() throws IOException,SQLException,Exception {
        IBTINSH_RD = new DBParm();
        IBTINSH_RD.TableName = "IBTINSH";
        IBTINSH_RD.where = "AC_NO = :IBRINSH.KEY.AC_NO "
            + "AND DEAL_DATE = :IBRINSH.KEY.DEAL_DATE";
        IBTINSH_RD.fst = true;
        IBTINSH_RD.order = "SEQ DESC";
        IBS.READ(SCCGWA, IBRINSH, this, IBTINSH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SEQ = IBRINSH.KEY.SEQ + 1;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_SEQ = WS_SEQ + 1;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTINSH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_IBTINSH() throws IOException,SQLException,Exception {
        IBTINSH_RD = new DBParm();
        IBTINSH_RD.TableName = "IBTINSH";
        IBS.WRITE(SCCGWA, IBRINSH, IBTINSH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.REC_DUPKEY);
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTINSH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
