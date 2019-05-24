package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.DD.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class IBZCKIP {
    String JIBS_tmp_str[] = new String[10];
    DBParm IBTMST_RD;
    DBParm IBTSCASH_RD;
    DBParm IBTTMAIN_RD;
    DBParm IBTTMST_RD;
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    String K_OUTPUT_FMT = "IBG00   ";
    String WS_TABLE_NAME = " ";
    String WS_PROD_CD = " ";
    char WS_TABLE_REC = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCOCKIP IBCOCKIP = new IBCOCKIP();
    CICACCU CICACCU = new CICACCU();
    IBCQINF IBCQINF = new IBCQINF();
    DDCSCINM DDCSCINM = new DDCSCINM();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    DDVMPRD DDVMPRD = new DDVMPRD();
    BPCQCCY BPCQCCY = new BPCQCCY();
    DDCIMMST DDCIMMST = new DDCIMMST();
    DDCSLLCX DDCSLLCX = new DDCSLLCX();
    DDCOLLCX DDCOLLCX = new DDCOLLCX();
    IBRTMST IBRTMST = new IBRTMST();
    IBRMST IBRMST = new IBRMST();
    IBRTMAIN IBRTMAIN = new IBRTMAIN();
    IBRSCASH IBRSCASH = new IBRSCASH();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBCCKIP IBCCKIP;
    public void MP(SCCGWA SCCGWA, IBCCKIP IBCCKIP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCCKIP = IBCCKIP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "IBZCKIP return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (IBCCKIP.FUNC == '1') {
            B020_DD_OPEN_CHECK();
        } else if (IBCCKIP.FUNC == '2') {
            B030_DD_CLOSE_CHECK();
        } else if (IBCCKIP.FUNC == '3') {
            B040_TD_OPEN_CHECK();
        } else if (IBCCKIP.FUNC == '4') {
            B050_TD_MOD_CHECK();
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + IBCCKIP.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCCKIP.FUNC);
        if (IBCCKIP.FUNC == ' ') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT);
        }
    }
    public void B020_DD_OPEN_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRMST);
        if (IBCCKIP.NOSTR_CD.trim().length() > 0 
            && IBCCKIP.CCY.trim().length() > 0) {
            IBRMST.NOSTRO_CODE = IBCCKIP.NOSTR_CD;
            IBRMST.CCY = IBCCKIP.CCY;
            T000_READ_IBTMST1();
            if (WS_TABLE_REC == 'Y') {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.SAME_IB_AC);
            }
        }
        IBS.init(SCCGWA, IBRMST);
        CEP.TRC(SCCGWA, IBCCKIP.CORRAC);
        if (IBCCKIP.CORRAC.trim().length() > 0) {
            IBRMST.CORRAC_NO = IBCCKIP.CORRAC;
            T000_READ_IBTMST_FIRST();
            if (WS_TABLE_REC == 'Y') {
                IBS.init(SCCGWA, CICACCU);
                CICACCU.DATA.AGR_NO = IBRMST.KEY.AC_NO;
                S000_CALL_CIZACCU();
                CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
                CEP.TRC(SCCGWA, IBCCKIP.CI_NO);
                if (CICACCU.DATA.CI_NO.equalsIgnoreCase(IBCCKIP.CI_NO)) {
                    CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.CORRAC_EXIST_S);
                } else {
                    CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.CORRAC_EXIST_D);
                }
            }
            if (IBCCKIP.AC_ATTR == 'L') {
                B021_CHECK_L_CORRAC();
            }
        }
    }
    public void B021_CHECK_L_CORRAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIMMST);
        DDCIMMST.DATA.KEY.AC_NO = IBCCKIP.CORRAC;
        DDCIMMST.TX_TYPE = 'I';
        S000_CALL_DDZIMMST();
        CEP.TRC(SCCGWA, DDCIMMST.DATA.PROD_CODE);
        if (!DDCIMMST.DATA.PROD_CODE.equalsIgnoreCase("6118060000")) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.CORR_NOT_L);
        }
        IBS.init(SCCGWA, DDCSCINM);
        DDCSCINM.INPUT_DATA.CCY = IBCCKIP.CCY;
        DDCSCINM.INPUT_DATA.AC_NO = IBCCKIP.CORRAC;
        S000_CALL_DDZSCINM();
        if (DDCSCINM.OUTPUT_DATA.CCY_INFO[1-1].M_CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_CCY);
        }
        IBCOCKIP.RATE_FLAG = DDCSCINM.OUTPUT_DATA.CCY_INFO[1-1].CCY_CINT_FLG;
        IBCOCKIP.PRV_FLAG = IBCOCKIP.RATE_FLAG;
        CEP.TRC(SCCGWA, IBCOCKIP.RATE_FLAG);
        CEP.TRC(SCCGWA, IBCOCKIP.PRV_FLAG);
        IBS.init(SCCGWA, DDCSLLCX);
        DDCSLLCX.FUNC = 'L';
        CEP.TRC(SCCGWA, DDCSLLCX.FUNC);
        DDCSLLCX.AC = IBCCKIP.CORRAC;
        CEP.TRC(SCCGWA, DDCSLLCX.AC);
        S000_CALL_DDCSLLCX();
        CEP.TRC(SCCGWA, DDCSLLCX.TIR_TYPE);
        CEP.TRC(SCCGWA, DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_FIX_RATE1);
        CEP.TRC(SCCGWA, DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_RBAS1);
        CEP.TRC(SCCGWA, DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_RCD1);
        CEP.TRC(SCCGWA, DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_SPR1);
        CEP.TRC(SCCGWA, DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_SPR1);
        CEP.TRC(SCCGWA, DDCSLLCX.ADP_POST_DT);
        CEP.TRC(SCCGWA, DDCSLLCX.CON_RATE);
        CEP.TRC(SCCGWA, DDCSLLCX.ADP_POST_PERIOD);
        if (DDCSLLCX.TIR_TYPE == 'N') {
            if (DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_FIX_RATE1 != 0) {
                IBCOCKIP.RATE_MTH = '1';
                IBCOCKIP.RATE = DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_FIX_RATE1;
            } else {
                IBCOCKIP.RATE_MTH = '2';
                IBCOCKIP.RATE_TYPE = DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_RBAS1;
                IBCOCKIP.RATE_TERM = DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_RCD1;
                IBCOCKIP.RATE_PCT = DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_SPR_PCT1;
                IBCOCKIP.RATE_SPREAD = DDCSLLCX.TIR_DATA.TIR_INF[1-1].TIR_SPR1;
            }
            if (DDCSLLCX.ADP_POST_PERIOD == 'Y') {
                IBCOCKIP.INTS_CYC = '1';
            } else if (DDCSLLCX.ADP_POST_PERIOD == 'H') {
                IBCOCKIP.INTS_CYC = '2';
            } else if (DDCSLLCX.ADP_POST_PERIOD == 'Q') {
                IBCOCKIP.INTS_CYC = '3';
            } else if (DDCSLLCX.ADP_POST_PERIOD == 'M') {
                IBCOCKIP.INTS_CYC = '4';
            } else if (DDCSLLCX.ADP_POST_PERIOD == 'D') {
                IBCOCKIP.INTS_CYC = '5';
            }
            IBCOCKIP.DEP_POST_DATE1 = (short) DDCSLLCX.ADP_POST_DT;
        }
        CEP.TRC(SCCGWA, DDCSLLCX.OD_CON_RATE);
        IBCOCKIP.OD_RATE = DDCSLLCX.OD_CON_RATE;
        IBCOCKIP.OD_INTS_CYC = DDCSLLCX.OD_INTS_CYC;
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = IBCCKIP.CCY;
        S000_CALL_SCSOCCY();
        IBCOCKIP.CALR_STD = BPCQCCY.DATA.CALR_STD;
        CEP.TRC(SCCGWA, IBCOCKIP.OD_RATE);
        CEP.TRC(SCCGWA, IBCOCKIP.OD_INTS_CYC);
        CEP.TRC(SCCGWA, IBCOCKIP.RATE_MTH);
        CEP.TRC(SCCGWA, IBCOCKIP.PRV_FLAG);
        CEP.TRC(SCCGWA, IBCOCKIP.RATE);
        CEP.TRC(SCCGWA, IBCOCKIP.RATE_TYPE);
        CEP.TRC(SCCGWA, IBCOCKIP.RATE_TERM);
        CEP.TRC(SCCGWA, IBCOCKIP.RATE_SPREAD);
        CEP.TRC(SCCGWA, IBCOCKIP.CALR_STD);
        CEP.TRC(SCCGWA, IBCOCKIP);
        B022_OUTPUT_PROC();
    }
    public void B022_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = IBCOCKIP;
        SCCFMT.DATA_LEN = 84;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B030_DD_CLOSE_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRSCASH);
        CEP.TRC(SCCGWA, IBCCKIP.AC_ATTR);
        if (IBCCKIP.AC_ATTR == 'L') {
            IBRSCASH.AC_NO = IBCCKIP.AC_NO;
            T000_READ_IBTSCASH();
            if (WS_TABLE_REC == 'N') {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.REC_NOTFND);
            }
            if (IBRSCASH.TXN_AMT != 0) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_INUSE);
            }
        }
    }
    public void B040_TD_OPEN_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRTMAIN);
        if (IBCCKIP.NOSTR_CD.trim().length() > 0 
            && IBCCKIP.CCY.trim().length() > 0) {
            IBRTMAIN.NOSTRO_CODE = IBCCKIP.NOSTR_CD;
            IBRTMAIN.CCY = IBCCKIP.CCY;
            T000_READ_IBTTMAIN();
            if (WS_TABLE_REC == 'Y') {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.SAME_IB_AC);
            }
        }
    }
    public void B050_TD_MOD_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRTMST);
        CEP.TRC(SCCGWA, IBCCKIP.AC_NO);
        IBRTMST.PRIM_AC_NO = IBCCKIP.AC_NO;
        CEP.TRC(SCCGWA, IBRTMST.PRIM_AC_NO);
        T000_READ_IBTTMST_FIRST();
        if (WS_TABLE_REC == 'Y') {
            IBCOCKIP.SUBAC_FLAG = 'Y';
        } else {
            IBCOCKIP.SUBAC_FLAG = 'N';
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = IBCOCKIP;
        SCCFMT.DATA_LEN = 84;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
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
    public void S000_CALL_SCSOCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_DDZSCINM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-CI-NAME", DDCSCINM);
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-DDPRD", DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST", DDCIMMST);
        if (DDCIMMST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCIMMST.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_DDCSLLCX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-AC-LLCX", DDCSLLCX);
    }
    public void T000_READ_IBTMST1() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBTMST_RD.where = "NOSTRO_CODE = :IBRMST.NOSTRO_CODE "
            + "AND CCY = :IBRMST.CCY";
        IBS.READ(SCCGWA, IBRMST, this, IBTMST_RD);
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
    public void T000_READ_IBTMST_FIRST() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBTMST_RD.where = "CORRAC_NO = :IBRMST.CORRAC_NO";
        IBTMST_RD.fst = true;
        IBS.READ(SCCGWA, IBRMST, this, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "111");
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "222");
            WS_TABLE_REC = 'N';
        } else {
            CEP.TRC(SCCGWA, "333");
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = WS_TABLE_NAME;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READ_IBTSCASH() throws IOException,SQLException,Exception {
        IBTSCASH_RD = new DBParm();
        IBTSCASH_RD.TableName = "IBTSCASH";
        IBTSCASH_RD.where = "AC_NO = :IBRSCASH.AC_NO";
        IBS.READ(SCCGWA, IBRSCASH, this, IBTSCASH_RD);
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
    public void T000_READ_IBTTMAIN() throws IOException,SQLException,Exception {
        IBTTMAIN_RD = new DBParm();
        IBTTMAIN_RD.TableName = "IBTTMAIN";
        IBTTMAIN_RD.where = "NOSTRO_CODE = :IBRTMAIN.NOSTRO_CODE "
            + "AND CCY = :IBRTMAIN.CCY";
        IBS.READ(SCCGWA, IBRTMAIN, this, IBTTMAIN_RD);
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
    public void T000_READ_IBTTMST_FIRST() throws IOException,SQLException,Exception {
        IBTTMST_RD = new DBParm();
        IBTTMST_RD.TableName = "IBTTMST";
        IBTTMST_RD.where = "PRIM_AC_NO = :IBRTMST.PRIM_AC_NO";
        IBTTMST_RD.fst = true;
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
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
