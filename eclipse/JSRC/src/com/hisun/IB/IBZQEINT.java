package com.hisun.IB;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class IBZQEINT {
    String JIBS_tmp_str[] = new String[10];
    DBParm IBTMST_RD;
    DBParm IBTTMST_RD;
    DBParm IBTINTH_RD;
    DBParm IBTINSH_RD;
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_OUTPUT_FMT = "IBG10   ";
    double WS_INT_AMT1 = 0;
    double WS_INT_AMT2 = 0;
    double WS_INT_AMT3 = 0;
    String WS_TABLE_NAME = " ";
    short WS_COUNT = 0;
    int WS_DATE = 0;
    double WS_RATE = 0;
    double WS_INT_AMT = 0;
    double WS_A_AMT = 0;
    double WS_B_AMT = 0;
    double WS_BUD_INT = 0;
    long WS_CCY_BAL = 0;
    short WS_CALR_STD = 0;
    char WS_TABLE_REC = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    IBCQINFS IBCQINFS = new IBCQINFS();
    IBCOQINT IBCOQINT = new IBCOQINT();
    IBCRODE IBCRODE = new IBCRODE();
    IBRINTH IBRINTH = new IBRINTH();
    IBRINSH IBRINSH = new IBRINSH();
    IBRTMST IBRTMST = new IBRTMST();
    IBRMST IBRMST = new IBRMST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBCQEINT IBCQEINT;
    public void MP(SCCGWA SCCGWA, IBCQEINT IBCQEINT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCQEINT = IBCQEINT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "IBZQEINT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (IBCQEINT.FUNC == '1') {
            B020_DD_INQ_ESET_AMT();
        } else if (IBCQEINT.FUNC == '2') {
            B030_TD_INQ_ESET_AMT();
        } else if (IBCQEINT.FUNC == '3') {
            B040_TD_INQ_EINT();
        } else if (IBCQEINT.FUNC == '4') {
            B050_TD_OPEN_EINT();
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC (" + IBCQEINT.FUNC + ")";
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCQEINT.FUNC);
        CEP.TRC(SCCGWA, IBCQEINT.AC_NO);
        if (IBCQEINT.AC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.IB_AC_NO_M_INPUT);
        }
        CEP.TRC(SCCGWA, IBCQEINT.DT);
        if (IBCQEINT.DT == 0 
            && IBCQEINT.FUNC != '4') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT);
        }
        CEP.TRC(SCCGWA, IBCQEINT.SEQ_NO);
        if ((IBCQEINT.FUNC == '2' 
            || IBCQEINT.FUNC == '3') 
            && IBCQEINT.SEQ_NO == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.SEQ_MUST_INPUT);
        }
        CEP.TRC(SCCGWA, IBCQEINT.ERATE);
        if (IBCQEINT.FUNC == '3' 
            && IBCQEINT.ERATE == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.E_RATE_M);
        }
    }
    public void B020_DD_INQ_ESET_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRMST);
        IBRMST.KEY.AC_NO = IBCQEINT.AC_NO;
        T000_READ_IBTMST();
        if (WS_TABLE_REC == 'N') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_NOEXIST);
        }
        if (IBRMST.AC_STS != 'N') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOT_NORMAL);
        }
        if (IBCQEINT.DT == IBRMST.EFF_DATE 
            || IBCQEINT.DT == IBRMST.L_INTS_DT) {
            WS_INT_AMT = 0;
            WS_BUD_INT = 0;
        }
        if (IBCQEINT.DT < IBRMST.L_INTS_DT) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.BEFORE_INTS);
        }
        if (IBCQEINT.DT > IBRMST.EFF_DATE 
            && IBRMST.L_INTS_DT == 0) {
            if (IBRMST.BUD_INT1 != 0) {
                WS_INT_AMT = IBRMST.BUD_INT1 * -1;
            } else {
                WS_INT_AMT = IBRMST.BUD_INT;
            }
        }
        if (IBCQEINT.DT > IBRMST.EFF_DATE 
            && IBRMST.L_INTS_DT != 0) {
            IBS.init(SCCGWA, IBRINSH);
            IBRINSH.KEY.AC_NO = IBRMST.KEY.AC_NO;
            IBRINSH.KEY.DEAL_DATE = IBCQEINT.DT;
            T000_READ_IBTINSH_FIRST();
            CEP.TRC(SCCGWA, WS_TABLE_REC);
            if (WS_TABLE_REC == 'Y') {
                WS_INT_AMT = 0;
            } else {
                if (IBRMST.BUD_INT1 != 0) {
                    WS_INT_AMT = IBRMST.BUD_INT1 * -1;
                } else {
                    WS_INT_AMT = IBRMST.BUD_INT;
                }
            }
        }
        if (IBCQEINT.DT > IBRMST.EFF_DATE) {
            IBS.init(SCCGWA, IBRINSH);
            IBRINSH.KEY.AC_NO = IBRMST.KEY.AC_NO;
            IBRINSH.KEY.DEAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            T000_READ_IBTINSH();
            CEP.TRC(SCCGWA, WS_TABLE_REC);
            if (WS_TABLE_REC == 'N') {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = IBCQEINT.DT;
                SCCCLDT.DAYS = -1;
                S000_CALL_SCSSCLDT();
                CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                IBS.init(SCCGWA, IBRINTH);
                IBRINTH.KEY.AC_NO = IBRMST.KEY.AC_NO;
                IBRINTH.KEY.INT_DATE = SCCCLDT.DATE2;
                T000_READ_IBTINTH();
                WS_BUD_INT = IBRINTH.BUD_INT;
            } else {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = IBRINSH.INTS_DATE;
                SCCCLDT.DAYS = -1;
                S000_CALL_SCSSCLDT();
                IBS.init(SCCGWA, IBRINTH);
                IBRINTH.KEY.AC_NO = IBRMST.KEY.AC_NO;
                IBRINTH.KEY.INT_DATE = SCCCLDT.DATE2;
                T000_READ_IBTINTH();
                WS_A_AMT = IBRINTH.BUD_INT;
                CEP.TRC(SCCGWA, WS_A_AMT);
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = IBCQEINT.DT;
                SCCCLDT.DAYS = -1;
                S000_CALL_SCSSCLDT();
                CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                IBS.init(SCCGWA, IBRINTH);
                IBRINTH.KEY.AC_NO = IBRMST.KEY.AC_NO;
                IBRINTH.KEY.INT_DATE = SCCCLDT.DATE2;
                T000_READ_IBTINTH();
                WS_B_AMT = IBRINTH.BUD_INT;
                CEP.TRC(SCCGWA, WS_B_AMT);
                WS_BUD_INT = WS_B_AMT - WS_A_AMT;
                CEP.TRC(SCCGWA, WS_BUD_INT);
            }
        }
        R000_OUTPUT_PROC();
    }
    public void B030_TD_INQ_ESET_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINFS);
        IBCQINFS.PRIM_AC_NO = IBCQEINT.AC_NO;
        IBCQINFS.SEQ_NO = IBCQEINT.SEQ_NO;
        S000_CALL_IBZQINFS();
        if (IBCQINFS.STS != 'N') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOT_NORMAL);
        }
        if (IBCQEINT.DT == IBCQINFS.VAL_DATE 
            || IBCQEINT.DT == IBCQINFS.LSET_DATE) {
            CEP.TRC(SCCGWA, "111");
            WS_INT_AMT = 0;
            WS_BUD_INT = 0;
        }
        if (IBCQEINT.DT > IBCQINFS.VAL_DATE 
            && IBCQINFS.LSET_DATE == 0) {
            CEP.TRC(SCCGWA, "222");
            WS_INT_AMT = IBCQINFS.BUD_INT;
        }
        if (IBCQEINT.DT > IBCQINFS.VAL_DATE 
            && IBCQINFS.LSET_DATE != 0) {
            IBS.init(SCCGWA, IBRINSH);
            IBRINSH.KEY.AC_NO = IBCQINFS.AC_NO;
            IBRINSH.KEY.DEAL_DATE = IBCQEINT.DT;
            T000_READ_IBTINSH_FIRST();
            CEP.TRC(SCCGWA, WS_TABLE_REC);
            if (WS_TABLE_REC == 'Y') {
                WS_INT_AMT = 0;
            } else {
                WS_INT_AMT = IBCQINFS.BUD_INT;
            }
        }
        if (IBCQEINT.DT > IBRTMST.VAL_DATE) {
            IBS.init(SCCGWA, IBRINSH);
            IBRINSH.KEY.AC_NO = IBCQINFS.AC_NO;
            IBRINSH.KEY.DEAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            T000_READ_IBTINSH();
            CEP.TRC(SCCGWA, WS_TABLE_REC);
            if (WS_TABLE_REC == 'N') {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = IBCQEINT.DT;
                SCCCLDT.DAYS = -1;
                S000_CALL_SCSSCLDT();
                CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                IBS.init(SCCGWA, IBRINTH);
                IBRINTH.KEY.AC_NO = IBCQINFS.AC_NO;
                IBRINTH.KEY.INT_DATE = SCCCLDT.DATE2;
                T000_READ_IBTINTH();
                WS_BUD_INT = IBRINTH.BUD_INT;
            } else {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = IBRINSH.INTS_DATE;
                SCCCLDT.DAYS = -1;
                S000_CALL_SCSSCLDT();
                CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                IBS.init(SCCGWA, IBRINTH);
                IBRINTH.KEY.AC_NO = IBCQINFS.AC_NO;
                IBRINTH.KEY.INT_DATE = SCCCLDT.DATE2;
                T000_READ_IBTINTH();
                WS_A_AMT = IBRINTH.BUD_INT;
                CEP.TRC(SCCGWA, WS_A_AMT);
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = IBCQEINT.DT;
                SCCCLDT.DAYS = -1;
                S000_CALL_SCSSCLDT();
                IBS.init(SCCGWA, IBRINTH);
                IBRINTH.KEY.AC_NO = IBCQINFS.AC_NO;
                IBRINTH.KEY.INT_DATE = SCCCLDT.DATE2;
                T000_READ_IBTINTH();
                WS_B_AMT = IBRINTH.BUD_INT;
                CEP.TRC(SCCGWA, WS_B_AMT);
                WS_BUD_INT = WS_B_AMT - WS_A_AMT;
                CEP.TRC(SCCGWA, WS_BUD_INT);
            }
        }
        R000_OUTPUT_PROC();
    }
    public void B040_TD_INQ_EINT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINFS);
        IBCQINFS.PRIM_AC_NO = IBCQEINT.AC_NO;
        IBCQINFS.SEQ_NO = IBCQEINT.SEQ_NO;
        S000_CALL_IBZQINFS();
        CEP.TRC(SCCGWA, IBCQEINT.DRW_AMT);
        CEP.TRC(SCCGWA, IBCQINFS.CURR_BAL);
        if (IBCQEINT.DT >= IBCQINFS.EXP_DATE) {
            if (IBCQEINT.DRW_AMT != IBCQINFS.CURR_BAL) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.INVALID_DRW_AMT);
            }
        }
        CEP.TRC(SCCGWA, IBCQINFS.RATE_MTH);
        if (IBCQINFS.RATE_MTH == '1') {
            B040_01_TD_EINT_COMP();
        }
        if (IBCQINFS.RATE_MTH == '2') {
            B040_02_TD_EINT_COMP();
        }
    }
    public void B040_01_TD_EINT_COMP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        if (IBCQEINT.DT < IBCQINFS.EXP_DATE) {
            SCCCLDT.DATE1 = IBCQINFS.VAL_DATE;
            SCCCLDT.DATE2 = IBCQEINT.DT;
            S000_CALL_SCSSCLDT();
            CEP.TRC(SCCGWA, IBCQEINT.EADV_RATE);
            if (IBCQEINT.EADV_RATE == 0) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.E_RATE_M);
            }
            WS_RATE = IBCQEINT.EADV_RATE;
            if (IBCQINFS.CCY.equalsIgnoreCase("156")) {
                WS_CCY_BAL = (long) IBCQEINT.DRW_AMT;
                IBCQEINT.DRW_AMT = WS_CCY_BAL;
            }
            WS_INT_AMT1 = SCCCLDT.DAYS * WS_RATE * IBCQEINT.DRW_AMT / IBCQINFS.CALR_STD / 100;
            WS_INT_AMT = WS_INT_AMT1 - IBCQINFS.DRW_INT * IBCQEINT.DRW_AMT / IBCQINFS.CURR_BAL;
        }
        if (IBCQEINT.DT == IBCQINFS.EXP_DATE) {
            if (IBCQINFS.LSET_DATE != 0) {
                SCCCLDT.DATE1 = IBCQINFS.LSET_DATE;
                SCCCLDT.DATE2 = IBCQEINT.DT;
            } else {
                SCCCLDT.DATE1 = IBCQINFS.VAL_DATE;
                SCCCLDT.DATE2 = IBCQEINT.DT;
            }
            S000_CALL_SCSSCLDT();
            CEP.TRC(SCCGWA, IBCQEINT.ERATE);
            WS_RATE = IBCQEINT.ERATE;
            if (IBCQINFS.CCY.equalsIgnoreCase("156")) {
                WS_CCY_BAL = (long) IBCQEINT.DRW_AMT;
                IBCQEINT.DRW_AMT = WS_CCY_BAL;
            }
            WS_INT_AMT = SCCCLDT.DAYS * WS_RATE * IBCQEINT.DRW_AMT / IBCQINFS.CALR_STD / 100;
        }
        if (IBCQEINT.DT > IBCQINFS.EXP_DATE) {
            if (IBCQINFS.LSET_DATE != 0) {
                SCCCLDT.DATE1 = IBCQINFS.LSET_DATE;
                SCCCLDT.DATE2 = IBCQINFS.EXP_DATE;
            } else {
                SCCCLDT.DATE1 = IBCQINFS.VAL_DATE;
                SCCCLDT.DATE2 = IBCQINFS.EXP_DATE;
            }
            S000_CALL_SCSSCLDT();
            CEP.TRC(SCCGWA, IBCQEINT.ERATE);
            WS_RATE = IBCQEINT.ERATE;
            if (IBCQINFS.CCY.equalsIgnoreCase("156")) {
                WS_CCY_BAL = (long) IBCQEINT.DRW_AMT;
                IBCQEINT.DRW_AMT = WS_CCY_BAL;
            }
            WS_INT_AMT2 = SCCCLDT.DAYS * WS_RATE * IBCQEINT.DRW_AMT / IBCQINFS.CALR_STD / 100;
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = IBCQINFS.EXP_DATE;
            SCCCLDT.DATE2 = IBCQEINT.DT;
            S000_CALL_SCSSCLDT();
            CEP.TRC(SCCGWA, IBCQEINT.EOVD_RATE);
            if (IBCQEINT.EOVD_RATE != 0) {
                WS_RATE = IBCQEINT.EOVD_RATE;
                if (IBCQINFS.CCY.equalsIgnoreCase("156")) {
                    WS_CCY_BAL = (long) IBCQEINT.DRW_AMT;
                    IBCQEINT.DRW_AMT = WS_CCY_BAL;
                }
                WS_INT_AMT3 = SCCCLDT.DAYS * WS_RATE * IBCQEINT.DRW_AMT / IBCQINFS.CALR_STD / 100;
            }
            WS_INT_AMT = WS_INT_AMT2 + WS_INT_AMT3;
        }
        WS_BUD_INT = WS_INT_AMT;
        R000_OUTPUT_PROC();
    }
    public void B040_02_TD_EINT_COMP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        if (IBCQEINT.DT < IBCQINFS.EXP_DATE) {
            SCCCLDT.DATE1 = IBCQINFS.VAL_DATE;
            SCCCLDT.DATE2 = IBCQEINT.DT;
            S000_CALL_SCSSCLDT();
            CEP.TRC(SCCGWA, IBCQEINT.EADV_RATE);
            if (IBCQEINT.EADV_RATE == 0) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.E_RATE_M);
            }
            WS_RATE = IBCQEINT.EADV_RATE;
            if (IBCQINFS.CCY.equalsIgnoreCase("156")) {
                WS_CCY_BAL = (long) IBCQEINT.DRW_AMT;
                IBCQEINT.DRW_AMT = WS_CCY_BAL;
            }
            WS_INT_AMT = SCCCLDT.DAYS * WS_RATE * IBCQEINT.DRW_AMT / IBCQINFS.CALR_STD / 100;
        }
        if (IBCQEINT.DT == IBCQINFS.EXP_DATE) {
            SCCCLDT.DATE1 = IBCQINFS.VAL_DATE;
            SCCCLDT.DATE2 = IBCQEINT.DT;
            S000_CALL_SCSSCLDT();
            CEP.TRC(SCCGWA, IBCQEINT.ERATE);
            WS_RATE = IBCQEINT.ERATE;
            if (IBCQINFS.CCY.equalsIgnoreCase("156")) {
                WS_CCY_BAL = (long) IBCQEINT.DRW_AMT;
                IBCQEINT.DRW_AMT = WS_CCY_BAL;
            }
            WS_INT_AMT = SCCCLDT.DAYS * WS_RATE * IBCQEINT.DRW_AMT / IBCQINFS.CALR_STD / 100;
        }
        if (IBCQEINT.DT > IBCQINFS.EXP_DATE) {
            SCCCLDT.DATE1 = IBCQINFS.VAL_DATE;
            SCCCLDT.DATE2 = IBCQINFS.EXP_DATE;
            S000_CALL_SCSSCLDT();
            CEP.TRC(SCCGWA, IBCQEINT.ERATE);
            WS_RATE = IBCQEINT.ERATE;
            if (IBCQINFS.CCY.equalsIgnoreCase("156")) {
                WS_CCY_BAL = (long) IBCQEINT.DRW_AMT;
                IBCQEINT.DRW_AMT = WS_CCY_BAL;
            }
            WS_INT_AMT2 = SCCCLDT.DAYS * WS_RATE * IBCQEINT.DRW_AMT / IBCQINFS.CALR_STD / 100;
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = IBCQINFS.EXP_DATE;
            SCCCLDT.DATE2 = IBCQEINT.DT;
            S000_CALL_SCSSCLDT();
            CEP.TRC(SCCGWA, IBCQEINT.EOVD_RATE);
            if (IBCQEINT.EOVD_RATE != 0) {
                WS_RATE = IBCQEINT.EOVD_RATE;
                if (IBCQINFS.CCY.equalsIgnoreCase("156")) {
                    WS_CCY_BAL = (long) IBCQEINT.DRW_AMT;
                    IBCQEINT.DRW_AMT = WS_CCY_BAL;
                }
                WS_INT_AMT3 = SCCCLDT.DAYS * WS_RATE * IBCQEINT.DRW_AMT / IBCQINFS.CALR_STD / 100;
            }
            WS_INT_AMT = WS_INT_AMT2 + WS_INT_AMT3;
        }
        WS_BUD_INT = WS_INT_AMT;
        R000_OUTPUT_PROC();
    }
    public void B050_TD_OPEN_EINT() throws IOException,SQLException,Exception {
        if (IBCQEINT.CALR_STD.equalsIgnoreCase("01")) {
            WS_CALR_STD = 360;
        } else if (IBCQEINT.CALR_STD.equalsIgnoreCase("02")) {
            WS_CALR_STD = 365;
        } else if (IBCQEINT.CALR_STD.equalsIgnoreCase("03")) {
            WS_CALR_STD = 366;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC (" + IBCQEINT.CALR_STD + ")";
        }
        if (IBCQEINT.CCY.equalsIgnoreCase("156")) {
            WS_CCY_BAL = (long) IBCQEINT.OPEN_BAL;
            IBCQEINT.OPEN_BAL = WS_CCY_BAL;
        }
        CEP.TRC(SCCGWA, IBCQEINT.OPEN_BAL);
        WS_BUD_INT = IBCQEINT.OPEN_BAL * IBCQEINT.RATE * IBCQEINT.INT_DAYS / WS_CALR_STD / 100;
        CEP.TRC(SCCGWA, WS_BUD_INT);
        R000_OUTPUT_PROC();
    }
    public void R000_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBCQEINT.INT_AMT = WS_INT_AMT;
        IBS.init(SCCGWA, IBCOQINT);
        IBS.init(SCCGWA, IBCRODE);
        IBCRODE.INT_AMT = WS_BUD_INT;
        if (IBCQEINT.FUNC == '1') {
            IBCRODE.CCY = IBRMST.CCY;
        } else {
            if (IBCQEINT.FUNC == '2' 
                || IBCQEINT.FUNC == '3') {
                IBCRODE.CCY = IBCQINFS.CCY;
            } else {
                IBCRODE.CCY = IBCQEINT.CCY;
            }
        }
        S000_CALL_IBZRODE();
        CEP.TRC(SCCGWA, IBCRODE.INT_AMT);
        IBCOQINT.INFO.TOT_INT = IBCRODE.INT_AMT;
        IBCOQINT.INFO.ESET_INT = WS_INT_AMT;
        CEP.TRC(SCCGWA, IBCOQINT.INFO.TOT_INT);
        CEP.TRC(SCCGWA, IBCOQINT.INFO.ESET_INT);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = IBCOQINT;
        SCCFMT.DATA_LEN = 36;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_IBZQINFS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINFS", IBCQINFS);
        if (IBCQINFS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINFS.RC);
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
    public void S000_CALL_IBZRODE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-ROUND-INT", IBCRODE);
        if (IBCRODE.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCRODE.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_READ_IBTMST() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBS.READ(SCCGWA, IBRMST, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = WS_TABLE_NAME;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READ_IBTTMST() throws IOException,SQLException,Exception {
        IBTTMST_RD = new DBParm();
        IBTTMST_RD.TableName = "IBTTMST";
        IBTTMST_RD.where = "PRIM_AC_NO = :IBRTMST.PRIM_AC_NO "
            + "AND SEQ_NO = :IBRTMST.SEQ_NO";
        IBS.READ(SCCGWA, IBRTMST, this, IBTTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = WS_TABLE_NAME;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READ_IBTINTH() throws IOException,SQLException,Exception {
        IBTINTH_RD = new DBParm();
        IBTINTH_RD.TableName = "IBTINTH";
        IBS.READ(SCCGWA, IBRINTH, IBTINTH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "111");
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "222");
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = WS_TABLE_NAME;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READ_IBTINSH_FIRST() throws IOException,SQLException,Exception {
        IBTINSH_RD = new DBParm();
        IBTINSH_RD.TableName = "IBTINSH";
        IBTINSH_RD.where = "AC_NO = :IBRINSH.KEY.AC_NO "
            + "AND DEAL_DATE = :IBRINSH.KEY.DEAL_DATE "
            + "AND REV_FLG < > 'R'";
        IBTINSH_RD.fst = true;
        IBS.READ(SCCGWA, IBRINSH, this, IBTINSH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = WS_TABLE_NAME;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READ_IBTINSH() throws IOException,SQLException,Exception {
        IBTINSH_RD = new DBParm();
        IBTINSH_RD.TableName = "IBTINSH";
        IBTINSH_RD.where = "AC_NO = :IBRINSH.KEY.AC_NO "
            + "AND DEAL_DATE = :IBRINSH.KEY.DEAL_DATE "
            + "AND REV_FLG < > 'R'";
        IBTINSH_RD.fst = true;
        IBTINSH_RD.order = "SEQ DESC";
        IBS.READ(SCCGWA, IBRINSH, this, IBTINSH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = WS_TABLE_NAME;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
