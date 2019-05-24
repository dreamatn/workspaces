package com.hisun.GD;

import com.hisun.TD.*;
import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.DC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class GDZSINAC {
    DBParm TDTSMST_RD;
    DBParm GDTSTAC_RD;
    DBParm DDTMST_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "GD583";
    String WS_ERR_MSG = " ";
    String WS_AC = " ";
    String WS_CI_NO1 = " ";
    String WS_CI_NO2 = " ";
    String WS_INT_AC = " ";
    String WS_FRM_APP = " ";
    char WS_PLDR_FLG = ' ';
    char WS_SMST_FLG = ' ';
    char WS_IAACR_REC_FLG = ' ';
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    TDRSMST TDRSMST = new TDRSMST();
    DCCUQSAC DCCUQSAC = new DCCUQSAC();
    CICACCU CICACCU = new CICACCU();
    GDCOINAC GDCOINAC = new GDCOINAC();
    DDCIMCCY DDCIMCCY = new DDCIMCCY();
    DDRMST DDRMST = new DDRMST();
    GDRSTAC GDRSTAC = new GDRSTAC();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACAC CICQACAC = new CICQACAC();
    CICSOEC CICSOEC = new CICSOEC();
    SCCGWA SCCGWA;
    GDB5830_AWA_5830 GDB5830_AWA_5830;
    GDCSINAC GDCSINAC;
    public void MP(SCCGWA SCCGWA, GDCSINAC GDCSINAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCSINAC = GDCSINAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GDZSINAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSINAC.AC_NO);
        CEP.TRC(SCCGWA, GDCSINAC.AC_SEQ);
        CEP.TRC(SCCGWA, GDCSINAC.ST_AC);
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B015_CHECK_AC_TYPE();
        if (pgmRtn) return;
        B400_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (GDCSINAC.AC_SEQ != 0) {
            CEP.TRC(SCCGWA, GDCSINAC.AC_SEQ);
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.DATA.AGR_NO = GDCSINAC.AC_NO;
            CICQACAC.DATA.AGR_SEQ = GDCSINAC.AC_SEQ;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            WS_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        } else {
            WS_AC = GDCSINAC.AC_NO;
        }
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = GDCSINAC.ST_AC;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.DATA.ACAC_NO);
        WS_INT_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        T000_READ_DDTMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRMST.AC_STS);
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
        if (DDRMST.AC_TYPE == 'M') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_ST_AC_M_INPUT;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = GDCSINAC.CUT_AC;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.DATA.ACAC_NO);
        WS_INT_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        T000_READ_DDTMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRMST.AC_STS);
        if (DDRMST.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDRMST.AC_TYPE);
        if (DDRMST.AC_TYPE == 'N') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_INTAC_NO_GD_AC;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void B015_CHECK_AC_TYPE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        IBS.init(SCCGWA, DDCIMCCY);
        CICQACAC.DATA.AGR_NO = WS_AC;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        WS_FRM_APP = CICQACRI.O_DATA.O_FRM_APP;
        DDCIMCCY.DATA[1-1].AC = CICQACRI.O_DATA.O_AGR_NO;
        if (WS_FRM_APP.equalsIgnoreCase("DD")) {
            S000_CALL_DDZIMCCY();
            if (pgmRtn) return;
        } else if (WS_FRM_APP.equalsIgnoreCase("TD")) {
            B020_READ_TDTSMST();
            if (pgmRtn) return;
            B030_CHECK_CI_NO();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, WS_FRM_APP);
        }
    }
    public void B020_READ_TDTSMST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = WS_AC;
        T000_READUPDATE_TDTSMST();
        if (pgmRtn) return;
        if (TDRSMST.ACO_STS == 'C') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED2;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        if (TDRSMST.ACO_STS == 'R') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_INVALID;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        if (!TDRSMST.PRDAC_CD.equalsIgnoreCase("033")) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_NOT_GD_AC;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, TDRSMST.CCY);
        IBS.init(SCCGWA, DDCIMCCY);
        DDCIMCCY.DATA[1-1].AC = WS_INT_AC;
        S000_CALL_DDZIMCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCIMCCY.DATA[1-1].AC);
        if (!TDRSMST.CCY.equalsIgnoreCase(DDCIMCCY.DATA[1-1].CCY)) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CCY_NOT_MATCH;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void B030_CHECK_CI_NO() throws IOException,SQLException,Exception {
        T000_READUP_GDTSTAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = WS_AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        WS_CI_NO1 = CICACCU.DATA.CI_NO;
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = WS_INT_AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        WS_CI_NO2 = CICACCU.DATA.CI_NO;
        CEP.TRC(SCCGWA, WS_CI_NO1);
        CEP.TRC(SCCGWA, WS_CI_NO2);
        CEP.TRC(SCCGWA, TDRSMST.OPEN_DR_AC);
        CEP.TRC(SCCGWA, WS_INT_AC);
        if (!WS_CI_NO1.equalsIgnoreCase(WS_CI_NO2)) {
            if ((!TDRSMST.OPEN_DR_AC.equalsIgnoreCase(GDCSINAC.ST_AC)) 
                && (GDCSINAC.ST_AC.trim().length() > 0)) {
                IBS.init(SCCGWA, CICSOEC);
                CICSOEC.DATA.CI_NO = WS_CI_NO2;
                CICSOEC.DATA.READ_ONLY_FLG = 'Y';
                S000_CALL_CIZSOEC();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, CICSOEC.DATA.SPECIAL_CI_NO);
                CEP.TRC(SCCGWA, WS_CI_NO1);
                if (!CICSOEC.DATA.SPECIAL_CI_NO.equalsIgnoreCase(WS_CI_NO1)) {
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CI_NO_NOT_SAME;
                    CEP.ERR(SCCGWA, WS_ERR_MSG);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
        T000_REWRITE_GDTSTAC();
        if (pgmRtn) return;
    }
    public void B400_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCOINAC);
        GDCOINAC.VAL.AC = GDCSINAC.AC_NO;
        GDCOINAC.VAL.AC_SEQ = GDCSINAC.AC_SEQ;
        GDCOINAC.VAL.SUB_AC = WS_AC;
        GDCOINAC.VAL.ST_AC = GDCSINAC.ST_AC;
        SCCFMT.FMTID = K_OUTPUT_FMT;
        CEP.TRC(SCCGWA, "GD583");
        SCCFMT.DATA_PTR = GDCOINAC;
        SCCFMT.DATA_LEN = 102;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READUPDATE_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void T000_REWRITE_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void T000_READUP_GDTSTAC() throws IOException,SQLException,Exception {
        GDRSTAC.INT_AC = GDCSINAC.ST_AC;
        GDTSTAC_RD = new DBParm();
        GDTSTAC_RD.TableName = "GDTSTAC";
        GDTSTAC_RD.upd = true;
        IBS.READ(SCCGWA, GDRSTAC, GDTSTAC_RD);
    }
    public void T000_REWRITE_GDTSTAC() throws IOException,SQLException,Exception {
        GDRSTAC.INT_AC = GDCSINAC.ST_AC;
        GDTSTAC_RD = new DBParm();
        GDTSTAC_RD.TableName = "GDTSTAC";
        IBS.REWRITE(SCCGWA, GDRSTAC, GDTSTAC_RD);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_CIZSOEC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-OPEN-EXP-CI", CICSOEC);
        if (CICSOEC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSOEC.RC);
        }
    }
    public void S000_CALL_DDZIMCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-CCY", DDCIMCCY);
        CEP.TRC(SCCGWA, DDCIMCCY.RC);
        if (DDCIMCCY.RC.RC_CODE != 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CCY_NOT_MATCH;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_DCZUQSAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-INQ-SUB-AC", DCCUQSAC);
        CEP.TRC(SCCGWA, DCCUQSAC.RC.RC_CODE);
        if (DCCUQSAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUQSAC.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        CEP.TRC(SCCGWA, CICACCU.RC);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACAC);
        CEP.TRC(SCCGWA, CICQACAC.RC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
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
