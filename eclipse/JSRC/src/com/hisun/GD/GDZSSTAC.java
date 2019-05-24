package com.hisun.GD;

import com.hisun.TD.*;
import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.CI.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class GDZSSTAC {
    DBParm GDTSTAC_RD;
    DBParm DDTCCY_RD;
    DBParm TDTSMST_RD;
    DBParm TDTCMST_RD;
    DBParm DDTMST_RD;
    DBParm DCTIAMST_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "GD190";
    String WS_ERR_MSG = " ";
    String WS_AC = " ";
    String WS_CI_NO1 = " ";
    String WS_CI_NO2 = " ";
    String WS_INT_AC = " ";
    String WS_INT_CINO = " ";
    String WS_CUT_CINO = " ";
    String WS_INT_CCY = " ";
    String WS_CUT_CCY = " ";
    String WS_BAIL_AC = " ";
    String WS_BAIL_CINO = " ";
    String WS_BAIL_CCY = " ";
    char WS_STAC_FLG = ' ';
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    TDRSMST TDRSMST = new TDRSMST();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    TDRCMST TDRCMST = new TDRCMST();
    CICACCU CICACCU = new CICACCU();
    GDCOSTAC GDCOSTAC = new GDCOSTAC();
    GDRSTAC GDRSTAC = new GDRSTAC();
    GDCRSTAC GDCRSTAC = new GDCRSTAC();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DCRIAMST DCRIAMST = new DCRIAMST();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACAC CICQACAC = new CICQACAC();
    CICSOEC CICSOEC = new CICSOEC();
    SCCGWA SCCGWA;
    GDB1900_AWA_1900 GDB1900_AWA_1900;
    GDCSSTAC GDCSSTAC;
    public void MP(SCCGWA SCCGWA, GDCSSTAC GDCSSTAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCSSTAC = GDCSSTAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GDZSSTAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCOSTAC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (GDCSSTAC.TYP == '2') {
            B015_CHECK_AC_STS();
            if (pgmRtn) return;
            T000_READ_GDTSTAC();
            if (pgmRtn) return;
            B025_QUERY_GDTSTAC();
            if (pgmRtn) return;
        } else {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B020_CHECK_AC();
            if (pgmRtn) return;
            B030_CHECK_FUNC();
            if (pgmRtn) return;
            B035_UPDATE_POST_AC();
            if (pgmRtn) return;
            B400_OUTPUT_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSSTAC.ST_AC);
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = GDCSSTAC.AC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AGR_NO);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CI_NO);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CCY);
        WS_BAIL_AC = CICQACRI.O_DATA.O_AGR_NO;
        WS_BAIL_CINO = CICQACRI.O_DATA.O_CI_NO;
        WS_BAIL_CCY = CICQACRI.O_DATA.O_CCY;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = GDCSSTAC.AC;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDRMST.AC_STS);
            if (DDRMST.AC_STS == 'C') {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_DD_AC_CLOSED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("TD")) {
                IBS.init(SCCGWA, TDRCMST);
                TDRCMST.KEY.AC_NO = GDCSSTAC.AC;
                T000_READ_TDTCMST();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, TDRCMST.STS);
                if (TDRCMST.STS == '1') {
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_DD_AC_CLOSED;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        if (GDCSSTAC.ST_AC.trim().length() > 0) {
            CEP.TRC(SCCGWA, GDCSSTAC.AC);
            CEP.TRC(SCCGWA, GDCSSTAC.AC_SEQ);
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = GDCSSTAC.ST_AC;
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AGR_NO);
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CI_NO);
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CCY);
            WS_INT_AC = CICQACRI.O_DATA.O_AGR_NO;
            GDCOSTAC.VAL.SUB_AC = CICQACRI.O_DATA.O_AGR_NO;
            WS_INT_CINO = CICQACRI.O_DATA.O_CI_NO;
            WS_INT_CCY = CICQACRI.O_DATA.O_CCY;
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = CICQACRI.O_DATA.O_AGR_NO;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
            if (DDRMST.AC_TYPE == 'N') {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CANNOT_BE_GDAC;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDRMST.AC_STS == 'C') {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_DD_AC_CLOSED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (GDCSSTAC.CUT_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = GDCSSTAC.CUT_AC;
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AGR_NO);
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CI_NO);
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CCY);
            WS_CUT_CINO = CICQACRI.O_DATA.O_CI_NO;
            WS_CUT_CCY = CICQACRI.O_DATA.O_CCY;
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = CICQACRI.O_DATA.O_AGR_NO;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
            if (DDRMST.AC_TYPE == 'N') {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CANNOT_BE_GDAC;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDRMST.AC_STS == 'C') {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_DD_AC_CLOSED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
                Z_RET();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = GDCSSTAC.AC;
        CICQACAC.DATA.AGR_SEQ = GDCSSTAC.AC_SEQ;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            CEP.TRC(SCCGWA, "MAIN-SUB AC");
            if (GDCSSTAC.AC_SEQ == 0) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_SEQ_MST_INPT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            GDCOSTAC.VAL.SUB_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        } else {
            CEP.TRC(SCCGWA, "STD AC");
            WS_AC = GDCSSTAC.AC;
            GDCOSTAC.VAL.SUB_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        }
        CEP.TRC(SCCGWA, WS_INT_CINO);
        CEP.TRC(SCCGWA, WS_CUT_CINO);
        CEP.TRC(SCCGWA, WS_BAIL_CINO);
        if (WS_INT_CINO.trim().length() == 0 
            && WS_CUT_CINO.trim().length() == 0) {
            CEP.ERR(SCCGWA, GDCMSG_ERROR_MSG.GD_CI_NO_NOT_SAME);
        }
        if (!WS_BAIL_CINO.equalsIgnoreCase(WS_INT_CINO) 
            && WS_INT_CINO.trim().length() > 0) {
            IBS.init(SCCGWA, CICSOEC);
            CICSOEC.DATA.CI_NO = WS_INT_CINO;
            CICSOEC.DATA.READ_ONLY_FLG = 'Y';
            IBS.CALLCPN(SCCGWA, "CI-OPEN-EXP-CI", CICSOEC);
            if (!CICSOEC.DATA.SPECIAL_CI_NO.equalsIgnoreCase(WS_BAIL_CINO)) {
                CEP.ERR(SCCGWA, GDCMSG_ERROR_MSG.GD_CI_NO_NOT_SAME);
            }
        }
        if (!WS_BAIL_CINO.equalsIgnoreCase(WS_CUT_CINO) 
            && WS_CUT_CINO.trim().length() > 0) {
            IBS.init(SCCGWA, CICSOEC);
            CICSOEC.DATA.CI_NO = WS_CUT_CINO;
            CICSOEC.DATA.READ_ONLY_FLG = 'Y';
            IBS.CALLCPN(SCCGWA, "CI-OPEN-EXP-CI", CICSOEC);
            if (!CICSOEC.DATA.SPECIAL_CI_NO.equalsIgnoreCase(WS_BAIL_CINO)) {
                CEP.ERR(SCCGWA, GDCMSG_ERROR_MSG.GD_CI_NO_NOT_SAME);
            }
        }
        CEP.TRC(SCCGWA, WS_INT_CCY);
        CEP.TRC(SCCGWA, WS_CUT_CCY);
        CEP.TRC(SCCGWA, WS_BAIL_CCY);
        if (WS_INT_CCY.trim().length() == 0 
            && WS_CUT_CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, GDCMSG_ERROR_MSG.GD_CI_NO_NOT_SAME);
        }
        if (!WS_BAIL_CCY.equalsIgnoreCase(WS_INT_CCY) 
            && WS_INT_CCY.trim().length() > 0) {
            CEP.ERR(SCCGWA, GDCMSG_ERROR_MSG.GD_CCY_NOT_MATCH);
        }
        if (!WS_BAIL_CCY.equalsIgnoreCase(WS_CUT_CCY) 
            && WS_CUT_CCY.trim().length() > 0) {
            CEP.ERR(SCCGWA, GDCMSG_ERROR_MSG.GD_CCY_NOT_MATCH);
        }
    }
    public void B015_CHECK_AC_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = GDCSSTAC.AC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = GDCSSTAC.AC;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDRMST.AC_STS);
            if (DDRMST.AC_STS == 'C') {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_DD_AC_CLOSED;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("TD")) {
                IBS.init(SCCGWA, TDRCMST);
                TDRCMST.KEY.AC_NO = GDCSSTAC.AC;
                T000_READ_TDTCMST();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, TDRCMST.STS);
                if (TDRCMST.STS == '1') {
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_DD_AC_CLOSED;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B020_CHECK_AC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_AC);
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = WS_AC;
            T000_READ_TDTSMST();
            if (pgmRtn) return;
            if (TDRSMST.ACO_STS == '1') {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED2;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
                IBS.init(SCCGWA, DDRMST);
                DDRMST.KEY.CUS_AC = WS_AC;
                T000_READ_DDTMST();
                if (pgmRtn) return;
                if (DDRMST.AC_STS == 'C') {
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_DD_AC_CLOSED;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
        IBS.init(SCCGWA, GDRSTAC);
        GDRSTAC.KEY.AC = GDCSSTAC.AC;
        GDRSTAC.KEY.AC_SEQ = GDCSSTAC.AC_SEQ;
        T000_READUPDATE_GDTSTAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, GDRSTAC.ST_AC);
        GDCOSTAC.VAL.ST_AC = GDRSTAC.ST_AC;
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = WS_AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        WS_CI_NO1 = CICACCU.DATA.CI_NO;
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
        if (CICACCU.DATA.AC_CNM.trim().length() == 0) {
            GDCOSTAC.VAL.CI_NM = CICACCU.DATA.CI_CNM;
        } else {
            GDCOSTAC.VAL.CI_NM = CICACCU.DATA.AC_CNM;
        }
        if (GDRSTAC.ST_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = GDRSTAC.ST_AC;
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
            CEP.TRC(SCCGWA, CICACCU.DATA.CI_CNM);
            if (CICACCU.DATA.AC_CNM.trim().length() == 0) {
                GDCOSTAC.VAL.STCI_NM = CICACCU.DATA.CI_CNM;
            } else {
                GDCOSTAC.VAL.STCI_NM = CICACCU.DATA.AC_CNM;
            }
        }
        if (GDCSSTAC.ST_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = WS_INT_AC;
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
            WS_CI_NO2 = CICACCU.DATA.CI_NO;
            CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
            CEP.TRC(SCCGWA, CICACCU.DATA.CI_CNM);
            if (CICACCU.DATA.AC_CNM.trim().length() == 0) {
                GDCOSTAC.VAL.STCI_NM = CICACCU.DATA.CI_CNM;
            } else {
                GDCOSTAC.VAL.STCI_NM = CICACCU.DATA.AC_CNM;
            }
            if (!WS_CI_NO1.equalsIgnoreCase(WS_CI_NO2)) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_OTH_CI_NEED_AUTH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, WS_CI_NO1);
        CEP.TRC(SCCGWA, WS_CI_NO2);
        CEP.TRC(SCCGWA, GDCSSTAC.TYP);
    }
    public void B025_QUERY_GDTSTAC() throws IOException,SQLException,Exception {
        GDCOSTAC.VAL.AC = GDRSTAC.KEY.AC;
        GDCOSTAC.VAL.AC_SEQ = GDRSTAC.KEY.AC_SEQ;
        GDCOSTAC.VAL.ST_AC = GDRSTAC.ST_AC;
        GDCOSTAC.VAL.CUT_AC = GDRSTAC.INT_AC;
        SCCFMT.FMTID = K_OUTPUT_FMT;
        CEP.TRC(SCCGWA, "GD190");
        SCCFMT.DATA_PTR = GDCOSTAC;
        SCCFMT.DATA_LEN = 893;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B030_CHECK_FUNC() throws IOException,SQLException,Exception {
        if (GDCSSTAC.TYP == '1') {
            if (WS_STAC_FLG == 'N') {
                CEP.TRC(SCCGWA, "NOT FUND");
                if (GDCSSTAC.ST_AC.trim().length() == 0) {
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_NO_REC_CANNOT_DEL;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    IBS.init(SCCGWA, GDRSTAC);
                    GDRSTAC.KEY.AC = GDCSSTAC.AC;
                    GDRSTAC.KEY.AC_SEQ = GDCSSTAC.AC_SEQ;
                    GDRSTAC.ST_AC = GDCSSTAC.ST_AC;
                    GDRSTAC.INT_AC = GDCSSTAC.CUT_AC;
                    GDRSTAC.RMK = K_OUTPUT_FMT;
                    GDRSTAC.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    GDRSTAC.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                    GDRSTAC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    GDRSTAC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    CEP.TRC(SCCGWA, GDRSTAC.ST_AC);
                    GDCOSTAC.VAL.ST_AC = GDRSTAC.ST_AC;
                    GDCOSTAC.VAL.CUT_AC = GDRSTAC.INT_AC;
                    T000_WRITE_REC_PROC();
                    if (pgmRtn) return;
                }
            } else {
                CEP.TRC(SCCGWA, "FUND");
                CEP.TRC(SCCGWA, GDRSTAC.ST_AC);
                if (GDCSSTAC.ST_AC.trim().length() == 0) {
                    IBS.init(SCCGWA, GDRSTAC);
                    GDRSTAC.KEY.AC = GDCSSTAC.AC;
                    GDRSTAC.KEY.AC_SEQ = GDCSSTAC.AC_SEQ;
                    T000_DELETE_REC_PROC();
                    if (pgmRtn) return;
                } else {
                    if (GDCSSTAC.CUT_AC.equalsIgnoreCase(GDRSTAC.INT_AC)) {
                        WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_STAC_REC_EXIST;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    CEP.TRC(SCCGWA, "REWRITE");
                    GDRSTAC.ST_AC = GDCSSTAC.ST_AC;
                    GDRSTAC.INT_AC = GDCSSTAC.CUT_AC;
                    GDRSTAC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    GDRSTAC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    GDCOSTAC.VAL.ST_AC = GDRSTAC.ST_AC;
                    GDCOSTAC.VAL.CUT_AC = GDRSTAC.INT_AC;
                    T000_REWRITE_REC_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B035_UPDATE_POST_AC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSSTAC.AC);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READUP_DDTCCY();
            if (pgmRtn) return;
            DDRCCY.POST_AC = GDCSSTAC.CUT_AC;
            T000_REWRITE_DDTCCY();
            if (pgmRtn) return;
        }
    }
    public void B400_OUTPUT_PROC() throws IOException,SQLException,Exception {
        GDCOSTAC.VAL.AC = GDCSSTAC.AC;
        GDCOSTAC.VAL.AC_SEQ = GDCSSTAC.AC_SEQ;
        SCCFMT.FMTID = K_OUTPUT_FMT;
        CEP.TRC(SCCGWA, "GD190");
        SCCFMT.DATA_PTR = GDCOSTAC;
        SCCFMT.DATA_LEN = 893;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READUPDATE_GDTSTAC() throws IOException,SQLException,Exception {
        GDTSTAC_RD = new DBParm();
        GDTSTAC_RD.TableName = "GDTSTAC";
        GDTSTAC_RD.upd = true;
        IBS.READ(SCCGWA, GDRSTAC, GDTSTAC_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_STAC_FLG = 'N';
        } else {
            WS_STAC_FLG = 'Y';
        }
    }
    public void T000_READ_GDTSTAC() throws IOException,SQLException,Exception {
        GDRSTAC.KEY.AC = GDCSSTAC.AC;
        GDRSTAC.KEY.AC_SEQ = GDCSSTAC.AC_SEQ;
        GDTSTAC_RD = new DBParm();
        GDTSTAC_RD.TableName = "GDTSTAC";
        GDTSTAC_RD.where = "AC = :GDRSTAC.KEY.AC "
            + "AND AC_SEQ = :GDRSTAC.KEY.AC_SEQ";
        IBS.READ(SCCGWA, GDRSTAC, this, GDTSTAC_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_STAC_FLG = 'N';
        } else {
            WS_STAC_FLG = 'Y';
        }
    }
    public void T000_WRITE_REC_PROC() throws IOException,SQLException,Exception {
        GDTSTAC_RD = new DBParm();
        GDTSTAC_RD.TableName = "GDTSTAC";
        GDTSTAC_RD.errhdl = true;
        IBS.WRITE(SCCGWA, GDRSTAC, GDTSTAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_STAC_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        GDTSTAC_RD = new DBParm();
        GDTSTAC_RD.TableName = "GDTSTAC";
        IBS.REWRITE(SCCGWA, GDRSTAC, GDTSTAC_RD);
    }
    public void T000_DELETE_REC_PROC() throws IOException,SQLException,Exception {
        GDTSTAC_RD = new DBParm();
        GDTSTAC_RD.TableName = "GDTSTAC";
        IBS.DELETE(SCCGWA, GDRSTAC, GDTSTAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_STAC_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READUP_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND);
        }
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTIAMST() throws IOException,SQLException,Exception {
        DCTIAMST_RD = new DBParm();
        DCTIAMST_RD.TableName = "DCTIAMST";
        DCTIAMST_RD.where = "VIA_AC = :DCRIAMST.KEY.VIA_AC";
        IBS.READ(SCCGWA, DCRIAMST, this, DCTIAMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDZRSTAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRC-GDZRSTAC", GDCRSTAC);
        CEP.TRC(SCCGWA, GDCRSTAC.RC);
        if (GDCRSTAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, GDCRSTAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        CEP.TRC(SCCGWA, DCCPACTY.RC.RC_CODE);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        CEP.TRC(SCCGWA, CICQACAC.RC.RC_CODE);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
