package com.hisun.DC;

import com.hisun.DD.*;
import com.hisun.TD.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.DB.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSUHOL {
    DBParm DDTHLD_RD;
    DBParm DDTMST_RD;
    DBParm TDTSMST_RD;
    DBParm DDTCCY_RD;
    DBParm DUPKEY_RD;
    DBParm DDTHLDR_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DC775";
    String K_HIS_REMARKS = "UPDATE HOLD SEQ";
    String WS_ERR_MSG = " ";
    String WS_AC = " ";
    String WS_HLD_NO = " ";
    String WS_OLD_DOWN_HLD_NO = " ";
    String WS_OLD_UP_HLD_NO = " ";
    String WS_NEW_DOWN_HLD_NO = " ";
    String WS_NEW_UP_HLD_NO = " ";
    char WS_HLD_STS = ' ';
    char WS_HLD_TYP = ' ';
    char WS_SPR_TYP = ' ';
    String WS_CCY = " ";
    char WS_CCY_TYP = ' ';
    double WS_HLD_AMT = 0;
    int WS_EFF_DATE = 0;
    int WS_EXP_DATE = 0;
    double WS_ACC_HLD_AMT = 0;
    double WS_CURR_AVL_AMT = 0;
    double WS_CURR_HLD_AMT = 0;
    char WS_CIRCLE_FLG = ' ';
    char WS_AC_HOLD_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DDRHLD DDRHLD = new DDRHLD();
    DDRHLDR DDRHLDR = new DDRHLDR();
    DCCOUHOL DCCOUHOL = new DCCOUHOL();
    SCCBINF SCCBINF = new SCCBINF();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DDRMST DDRMST = new DDRMST();
    TDRSMST TDRSMST = new TDRSMST();
    DDRCCY DDRCCY = new DDRCCY();
    CICQACAC CICQACAC = new CICQACAC();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    DCCSUHOL DCCSUHOL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DCCSUHOL DCCSUHOL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSUHOL = DCCSUHOL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSUHOL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        B020_INQ_HLD_INF_PROC();
        if (pgmRtn) return;
        B025_INQ_ACAC_PROC();
        if (pgmRtn) return;
        B030_UPD_UP_DOWN_HLD_PROC();
        if (pgmRtn) return;
        B030_UPDATE_DDTHLD();
        if (pgmRtn) return;
        B035_UPDATE_CCY_SMST_PROC();
        if (pgmRtn) return;
        B040_WRITE_DDTHLDR();
        if (pgmRtn) return;
        B045_NON_FIN_HIS_PROC();
        if (pgmRtn) return;
        B050_OUTPUT_DATA_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        if (DCCSUHOL.DATA.HLD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLDNO_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSUHOL.DATA.DOWN_HLD_NO.trim().length() == 0 
            && DCCSUHOL.DATA.UP_HLD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLDNO_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_INQ_HLD_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.KEY.HLD_NO = DCCSUHOL.DATA.HLD_NO;
        T000_READ_DDTHLD();
        if (pgmRtn) return;
        WS_AC = DDRHLD.AC;
        WS_HLD_NO = DDRHLD.KEY.HLD_NO;
        WS_EFF_DATE = DDRHLD.EFF_DATE;
        WS_EXP_DATE = DDRHLD.EXP_DATE;
        WS_HLD_AMT = DDRHLD.HLD_AMT;
        WS_HLD_STS = DDRHLD.HLD_STS;
        WS_HLD_TYP = DDRHLD.HLD_TYP;
        WS_SPR_TYP = DDRHLD.SPR_BR_TYP;
        WS_CCY = DDRHLD.CCY;
        WS_CCY_TYP = DDRHLD.CCY_TYPE;
        WS_OLD_DOWN_HLD_NO = DDRHLD.DOWN_HLD_NO;
        WS_OLD_UP_HLD_NO = DDRHLD.UP_HLD_NO;
        if (DDRHLD.HLD_STS == 'C') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_AC_HAS_REL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_NEW_DOWN_HLD_NO = DCCSUHOL.DATA.DOWN_HLD_NO;
        WS_NEW_UP_HLD_NO = DCCSUHOL.DATA.UP_HLD_NO;
        DDRHLD.UP_HLD_NO = WS_NEW_UP_HLD_NO;
        DDRHLD.DOWN_HLD_NO = WS_NEW_DOWN_HLD_NO;
        DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLD.UPDTBL_TIME = SCCGWA.COMM_AREA.TR_TIME;
        DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTHLD();
        if (pgmRtn) return;
        if (WS_OLD_UP_HLD_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "OLD-UP-HLD");
            IBS.init(SCCGWA, DDRHLD);
            DDRHLD.KEY.HLD_NO = WS_OLD_UP_HLD_NO;
            T000_READ_DDTHLD();
            if (pgmRtn) return;
            DDRHLD.DOWN_HLD_NO = WS_OLD_DOWN_HLD_NO;
            T000_REWRITE_DDTHLD();
            if (pgmRtn) return;
        }
        if (WS_OLD_DOWN_HLD_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "OLD-DOWN-HLD");
            IBS.init(SCCGWA, DDRHLD);
            DDRHLD.KEY.HLD_NO = WS_OLD_DOWN_HLD_NO;
            T000_READ_DDTHLD();
            if (pgmRtn) return;
            DDRHLD.UP_HLD_NO = WS_OLD_UP_HLD_NO;
            T000_REWRITE_DDTHLD();
            if (pgmRtn) return;
        }
    }
    public void B025_INQ_ACAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'A';
        CICQACAC.DATA.ACAC_NO = WS_AC;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_STS == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            if (DDRMST.AC_STS == 'C') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = WS_AC;
            T000_READ_TDTSMST();
            if (pgmRtn) return;
            if (TDRSMST.ACO_STS == 'C' 
                || TDRSMST.ACO_STS == 'R' 
                || TDRSMST.ACO_STS == '1' 
                || TDRSMST.ACO_STS == '2') {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_CURR_AVL_AMT = TDRSMST.BAL - TDRSMST.GUAR_BAL;
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.KEY.AC = WS_AC;
            T000_READ_DDTCCY_UPDATE();
            if (pgmRtn) return;
            WS_CURR_AVL_AMT = DDRCCY.CURR_BAL + DDRCCY.CCAL_TOT_BAL - DDRCCY.MARGIN_BAL;
        }
    }
    public void B030_UPD_UP_DOWN_HLD_PROC() throws IOException,SQLException,Exception {
        WS_NEW_DOWN_HLD_NO = DCCSUHOL.DATA.DOWN_HLD_NO;
        WS_NEW_UP_HLD_NO = DCCSUHOL.DATA.UP_HLD_NO;
        CEP.TRC(SCCGWA, WS_NEW_DOWN_HLD_NO);
        CEP.TRC(SCCGWA, WS_NEW_UP_HLD_NO);
        if (WS_NEW_UP_HLD_NO.trim().length() == 0) {
            IBS.init(SCCGWA, DDRHLD);
            DDRHLD.KEY.HLD_NO = WS_NEW_DOWN_HLD_NO;
            T000_READ_DDTHLD();
            if (pgmRtn) return;
            if (DDRHLD.HLD_STS == 'C') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_AC_HAS_REL;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (!DDRHLD.UP_HLD_NO.equalsIgnoreCase(WS_NEW_UP_HLD_NO)) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NEW_U_HLDNO_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (WS_HLD_TYP == '1') {
                WS_AC_HOLD_FLG = 'Y';
                WS_ACC_HLD_AMT = WS_CURR_AVL_AMT;
            } else {
                WS_CURR_HLD_AMT = WS_CURR_HLD_AMT + WS_HLD_AMT;
                if (WS_CURR_AVL_AMT > WS_HLD_AMT) {
                    WS_ACC_HLD_AMT = WS_HLD_AMT;
                } else {
                    WS_ACC_HLD_AMT = WS_CURR_AVL_AMT;
                }
            }
            if (WS_AC_HOLD_FLG == 'Y') {
                DDRHLD.HLD_STS = 'W';
            } else {
                if (DDRHLD.HLD_TYP == '1') {
                    WS_AC_HOLD_FLG = 'Y';
                } else {
                    WS_CURR_HLD_AMT = WS_CURR_HLD_AMT + DDRHLD.HLD_AMT;
                }
            }
            DDRHLD.UP_HLD_NO = WS_HLD_NO;
            T000_REWRITE_DDTHLD();
            if (pgmRtn) return;
            while (WS_CIRCLE_FLG != 'Y') {
                DDRHLD.KEY.HLD_NO = DDRHLD.DOWN_HLD_NO;
                if (DDRHLD.DOWN_HLD_NO.trim().length() == 0) {
                    WS_CIRCLE_FLG = 'Y';
                } else {
                    T000_READ_DDTHLD();
                    if (pgmRtn) return;
                    if (DDRHLD.HLD_STS == 'W') {
                        WS_CIRCLE_FLG = 'Y';
                    } else {
                        if (WS_AC_HOLD_FLG == 'Y') {
                            DDRHLD.HLD_STS = 'W';
                            T000_REWRITE_DDTHLD();
                            if (pgmRtn) return;
                        } else {
                            if (DDRHLD.HLD_TYP == '1') {
                                WS_AC_HOLD_FLG = 'Y';
                            } else {
                                if (WS_CURR_HLD_AMT >= WS_CURR_AVL_AMT) {
                                    DDRHLD.HLD_STS = 'W';
                                    T000_REWRITE_DDTHLD();
                                    if (pgmRtn) return;
                                } else {
                                    WS_CURR_HLD_AMT = WS_CURR_HLD_AMT + DDRHLD.HLD_AMT;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (WS_NEW_UP_HLD_NO.trim().length() > 0) {
            IBS.init(SCCGWA, DDRHLD);
            DDRHLD.KEY.HLD_NO = WS_NEW_UP_HLD_NO;
            T000_READ_DDTHLD();
            if (pgmRtn) return;
            if (DDRHLD.HLD_STS == 'C') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_AC_HAS_REL;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (!DDRHLD.DOWN_HLD_NO.equalsIgnoreCase(WS_NEW_DOWN_HLD_NO)) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NEW_D_HLDNO_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, DDRHLD);
            DDRHLD.AC = WS_AC;
            T000_READ_DDTHLD_FIRST();
            if (pgmRtn) return;
            while (WS_CIRCLE_FLG != 'Y') {
                if (!DDRHLD.KEY.HLD_NO.equalsIgnoreCase(WS_NEW_UP_HLD_NO)) {
                    if (DDRHLD.HLD_STS == 'N') {
                        if (DDRHLD.HLD_TYP == '1') {
                            WS_AC_HOLD_FLG = 'Y';
                        } else {
                            WS_CURR_HLD_AMT = WS_CURR_HLD_AMT + DDRHLD.HLD_AMT;
                        }
                    } else {
                        if (WS_AC_HOLD_FLG != 'Y') {
                            if (WS_CURR_AVL_AMT > WS_CURR_HLD_AMT) {
                                if (DDRHLD.HLD_TYP == '1') {
                                    WS_AC_HOLD_FLG = 'Y';
                                } else {
                                    WS_CURR_HLD_AMT = WS_CURR_HLD_AMT + DDRHLD.HLD_AMT;
                                }
                                DDRHLD.HLD_STS = 'N';
                                T000_REWRITE_DDTHLD();
                                if (pgmRtn) return;
                            }
                        }
                    }
                    DDRHLD.KEY.HLD_NO = DDRHLD.DOWN_HLD_NO;
                    T000_READ_DDTHLD();
                    if (pgmRtn) return;
                } else {
                    WS_CIRCLE_FLG = 'Y';
                }
            }
            DDRHLD.DOWN_HLD_NO = WS_HLD_NO;
            T000_REWRITE_DDTHLD();
            if (pgmRtn) return;
            if (WS_AC_HOLD_FLG == 'Y') {
                WS_ACC_HLD_AMT = 0;
            } else {
                if (DDRHLD.HLD_STS == 'W') {
                    WS_ACC_HLD_AMT = 0;
                } else {
                    if (WS_HLD_TYP == '1') {
                        WS_AC_HOLD_FLG = 'Y';
                        WS_ACC_HLD_AMT = WS_CURR_AVL_AMT - WS_CURR_HLD_AMT;
                        if (WS_ACC_HLD_AMT < 0) {
                            WS_ACC_HLD_AMT = 0;
                        }
                    } else {
                        WS_ACC_HLD_AMT = WS_CURR_AVL_AMT - WS_CURR_HLD_AMT;
                        if (WS_ACC_HLD_AMT < 0) {
                            WS_ACC_HLD_AMT = 0;
                        }
                        if (WS_ACC_HLD_AMT > WS_HLD_AMT) {
                            WS_ACC_HLD_AMT = WS_HLD_AMT;
                        }
                        WS_CURR_HLD_AMT = WS_CURR_HLD_AMT + DDRHLD.HLD_AMT;
                    }
                }
            }
            if (WS_NEW_DOWN_HLD_NO.trim().length() > 0) {
                IBS.init(SCCGWA, DDRHLD);
                DDRHLD.KEY.HLD_NO = WS_NEW_DOWN_HLD_NO;
                T000_READ_DDTHLD();
                if (pgmRtn) return;
                DDRHLD.UP_HLD_NO = DCCSUHOL.DATA.HLD_NO;
                T000_REWRITE_DDTHLD();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, DDRHLD);
            WS_CIRCLE_FLG = ' ';
            DDRHLD.KEY.HLD_NO = WS_NEW_DOWN_HLD_NO;
            while (WS_CIRCLE_FLG != 'Y') {
                if (DDRHLD.KEY.HLD_NO.trim().length() > 0) {
                    T000_READ_DDTHLD();
                    if (pgmRtn) return;
                    if (DDRHLD.HLD_STS == 'W') {
                        if (WS_AC_HOLD_FLG != 'Y') {
                            if (DDRHLD.HLD_TYP == '1') {
                                WS_AC_HOLD_FLG = 'Y';
                                if (WS_CURR_AVL_AMT > WS_CURR_HLD_AMT) {
                                    DDRHLD.HLD_STS = 'N';
                                    T000_REWRITE_DDTHLD();
                                    if (pgmRtn) return;
                                    DDRHLD.KEY.HLD_NO = DDRHLD.DOWN_HLD_NO;
                                }
                            } else {
                                if (WS_CURR_AVL_AMT > WS_CURR_HLD_AMT) {
                                    DDRHLD.HLD_STS = 'N';
                                    T000_REWRITE_DDTHLD();
                                    if (pgmRtn) return;
                                    WS_CURR_HLD_AMT = WS_CURR_HLD_AMT + DDRHLD.HLD_AMT;
                                    DDRHLD.KEY.HLD_NO = DDRHLD.DOWN_HLD_NO;
                                }
                            }
                        } else {
                            WS_CIRCLE_FLG = 'Y';
                        }
                    } else {
                        if (WS_AC_HOLD_FLG == 'Y') {
                            DDRHLD.HLD_STS = 'W';
                            T000_REWRITE_DDTHLD();
                            if (pgmRtn) return;
                            DDRHLD.KEY.HLD_NO = DDRHLD.DOWN_HLD_NO;
                        } else {
                            if (DDRHLD.HLD_TYP == '1') {
                                WS_AC_HOLD_FLG = 'Y';
                                if (WS_CURR_AVL_AMT <= WS_CURR_HLD_AMT) {
                                    DDRHLD.HLD_STS = 'W';
                                    T000_REWRITE_DDTHLD();
                                    if (pgmRtn) return;
                                    DDRHLD.KEY.HLD_NO = DDRHLD.DOWN_HLD_NO;
                                }
                            } else {
                                if (WS_CURR_AVL_AMT <= WS_CURR_HLD_AMT) {
                                    DDRHLD.HLD_STS = 'W';
                                    T000_REWRITE_DDTHLD();
                                    if (pgmRtn) return;
                                    DDRHLD.KEY.HLD_NO = DDRHLD.DOWN_HLD_NO;
                                } else {
                                    WS_CURR_HLD_AMT = WS_CURR_HLD_AMT + DDRHLD.HLD_AMT;
                                    DDRHLD.KEY.HLD_NO = DDRHLD.DOWN_HLD_NO;
                                }
                            }
                        }
                    }
                } else {
                    WS_CIRCLE_FLG = 'Y';
                }
            }
        }
    }
    public void B030_UPDATE_DDTHLD() throws IOException,SQLException,Exception {
        if (WS_ACC_HLD_AMT == 0) {
            IBS.init(SCCGWA, DDRHLD);
            DDRHLD.KEY.HLD_NO = DCCSUHOL.DATA.HLD_NO;
            T000_READ_DDTHLD();
            if (pgmRtn) return;
            DDRHLD.HLD_STS = 'W';
            DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRHLD.UPDTBL_TIME = SCCGWA.COMM_AREA.TR_TIME;
            DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTHLD();
            if (pgmRtn) return;
        }
    }
    public void B035_UPDATE_CCY_SMST_PROC() throws IOException,SQLException,Exception {
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            DDRCCY.HOLD_BAL = WS_CURR_HLD_AMT;
            DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTCCY();
            if (pgmRtn) return;
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            TDRSMST.HBAL = WS_CURR_HLD_AMT;
            TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            TDRSMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
            T000_REWRITE_TDTSMST();
            if (pgmRtn) return;
        }
    }
    public void B040_WRITE_DDTHLDR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLDR);
        DDRHLDR.KEY.HLD_NO = DCCSUHOL.DATA.HLD_NO;
        DDRHLDR.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLDR.KEY.TR_JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        DDRHLDR.AC = WS_AC;
        DDRHLDR.HLD_TYP = 'U';
        DDRHLDR.BEF_TR_AMT = WS_ACC_HLD_AMT;
        DDRHLDR.TR_AMT = WS_HLD_AMT;
        DDRHLDR.DOWN_HLD_NO = WS_NEW_DOWN_HLD_NO;
        DDRHLDR.UP_HLD_NO = WS_NEW_UP_HLD_NO;
        DDRHLDR.CRT_CHNL = SCCGWA.COMM_AREA.REQ_SYSTEM;
        DDRHLDR.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DDRHLDR.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLDR.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRHLDR.AUTH_TL = SCCGWA.COMM_AREA.SUP1_ID;
        DDRHLDR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRHLDR.UPDTBL_TIME = SCCGWA.COMM_AREA.TR_TIME;
        DDRHLDR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DDTHLDR();
        if (pgmRtn) return;
    }
    public void B045_NON_FIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.REF_NO = DCCSUHOL.DATA.HLD_NO;
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.FMT_ID = "DDRHLD";
        BPCPNHIS.INFO.AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        BPCPNHIS.INFO.CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
        BPCPNHIS.INFO.CCY_TYPE = CICQACAC.O_DATA.O_ACAC_DATA.O_CR_FLG;
        BPCPNHIS.INFO.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B050_OUTPUT_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCOUHOL);
        DCCOUHOL.DATA.HLD_NO = DCCSUHOL.DATA.HLD_NO;
        DCCOUHOL.DATA.AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        DCCOUHOL.DATA.HLD_TYP = WS_HLD_TYP;
        DCCOUHOL.DATA.SPR_TYP = WS_SPR_TYP;
        DCCOUHOL.DATA.CCY = WS_CCY;
        DCCOUHOL.DATA.CCY_TYP = WS_CCY_TYP;
        DCCOUHOL.DATA.AMT = WS_HLD_AMT;
        DCCOUHOL.DATA.EFF_DT = WS_EFF_DATE;
        DCCOUHOL.DATA.EXP_DT = WS_EXP_DATE;
        DCCOUHOL.DATA.ACC_HLD_AMT = WS_ACC_HLD_AMT;
        DCCOUHOL.DATA.HLD_STS = DDRHLD.HLD_STS;
        DCCOUHOL.DATA.OLD_DOWN_HLD_NO = WS_OLD_DOWN_HLD_NO;
        DCCOUHOL.DATA.NEW_DOWN_HLD_NO = WS_NEW_DOWN_HLD_NO;
        DCCOUHOL.DATA.OLD_DOWN_HLD_NO = WS_OLD_UP_HLD_NO;
        DCCOUHOL.DATA.NEW_DOWN_HLD_NO = WS_NEW_UP_HLD_NO;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DCCOUHOL;
        SCCFMT.DATA_LEN = 189;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_DDTHLD() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.upd = true;
        IBS.READ(SCCGWA, DDRHLD, DDTHLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTHLD_FIRST() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.where = "AC = :DDRHLD.AC "
            + "AND UP_HLD_NO = ' ' "
            + "AND HLD_STS = 'N'";
        IBS.READ(SCCGWA, DDRHLD, this, DDTHLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.col = "AC_STS_WORD,AC_TYPE";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DD_AC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TD_AC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTCCY_UPDATE() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.col = "AC,CCY,CCY_TYPE,CURR_BAL,HOLD_BAL,CCAL_TOT_BAL";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DD_CCY_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DDTHLD() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.errhdl = true;
        DUPKEY_RD = new DBParm();
        DUPKEY_RD.TableName = "DUPKEY";
        IBS.REWRITE(SCCGWA, DURKEY, DUPKEY_RD);
    }
    public void T000_WRITE_DDTHLDR() throws IOException,SQLException,Exception {
        DDTHLDR_RD = new DBParm();
        DDTHLDR_RD.TableName = "DDTHLDR";
        DDTHLDR_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRHLDR, DDTHLDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLDR_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_REWRITE_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
