package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.DC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDZPPRT {
    int JIBS_tmp_int;
    DBParm TDTSMST_RD;
    DBParm TDTBVT_RD;
    String JIBS_tmp_str[] = new String[10];
    String WS_ERR_MSG = " ";
    char WS_BV_TYP = ' ';
    String WS_CHECK_AC = " ";
    char WS_CHECK_AC_STS = ' ';
    String WS_CHECK_AC_STSW = " ";
    char WS_YBT_AC_FLAG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCBINF SCCBINF = new SCCBINF();
    DCCUIQMC DCCUIQMC = new DCCUIQMC();
    DCCIMSTR DCCIMSTR = new DCCIMSTR();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    TDRSMST TDRSMST = new TDRSMST();
    TDRBVT TDRBVT = new TDRBVT();
    TDCPPRTF TDCPPRTF = new TDCPPRTF();
    TDCBVCHG TDCBVCHG = new TDCBVCHG();
    CICACCU CICACCU = new CICACCU();
    CICQACAC CICQACAC = new CICQACAC();
    TDRCMST TDRCMST = new TDRCMST();
    SCCGWA SCCGWA;
    TDCPPRT TDCPPRT;
    public void MP(SCCGWA SCCGWA, TDCPPRT TDCPPRT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCPPRT = TDCPPRT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZPPRT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCPPRT.BV_CD);
        CEP.TRC(SCCGWA, TDCPPRT.BV_NO);
        CEP.TRC(SCCGWA, TDCPPRT.AC_NO);
        CEP.TRC(SCCGWA, TDCPPRT.BV_TYP);
        CEP.TRC(SCCGWA, TDCPPRT.PRT_OPT);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = TDCPPRT.AC_NO;
        CICQACAC.DATA.BV_NO = TDCPPRT.BV_NO;
        CICQACAC.FUNC = 'R';
        S000_CALL_CIZQACAC();
        B100_READ_CHK_PROC();
        R000_GET_CI_INFO();
        if ((TDCPPRT.PRT_OPT == '1' 
                || TDCPPRT.PRT_OPT == '3' 
                || TDCPPRT.PRT_OPT == ' ' 
                || TDCPPRT.PRT_OPT == '9')) {
            B210_REPRINT_PB();
        } else if (TDCPPRT.PRT_OPT == '2') {
            B210_BVCHG_REPRT();
        } else if (TDCPPRT.PRT_OPT == '4') {
            B210_PRT_TITTLE();
        } else {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INVALID_OPT;
            S000_ERR_MSG_PROC();
        }
        B220_WRITE_NFIN_HIS();
    }
    public void B100_READ_CHK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCPPRT.BV_TYP);
        if (TDCPPRT.BV_TYP == '1' 
            || TDCPPRT.BV_TYP == ' ') {
            B110_GET_BV_TYP_UNT();
            if (TDRBVT.STSW == null) TDRBVT.STSW = "";
            JIBS_tmp_int = TDRBVT.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
            if (TDRBVT.STSW.substring(0, 1).equalsIgnoreCase("1") 
                && TDCPPRT.BV_TYP == '1') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_OLD_BV_ERR, SCCBINF);
            }
        }
        IBS.init(SCCGWA, TDRCMST);
        TDRCMST.KEY.AC_NO = TDCPPRT.AC_NO;
        WS_CHECK_AC_STS = TDRCMST.STS;
        WS_CHECK_AC_STSW = TDRCMST.STSW;
        WS_CHECK_AC = TDRCMST.KEY.AC_NO;
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        if (TDRBVT.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            || TDRBVT.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_PB_LOS_N_ALLOW;
            S000_ERR_MSG_PROC();
        }
        if (WS_CHECK_AC_STS == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED;
            S000_ERR_MSG_PROC();
        }
        if (WS_CHECK_AC_STS == '2') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_RESERVED;
            S000_ERR_MSG_PROC();
        }
        if (WS_CHECK_AC_STSW == null) WS_CHECK_AC_STSW = "";
        JIBS_tmp_int = WS_CHECK_AC_STSW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_CHECK_AC_STSW += " ";
        if (WS_CHECK_AC_STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_ORAL_REG_LOS;
            S000_ERR_MSG_PROC();
        }
        if (WS_CHECK_AC_STSW == null) WS_CHECK_AC_STSW = "";
        JIBS_tmp_int = WS_CHECK_AC_STSW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_CHECK_AC_STSW += " ";
        if (WS_CHECK_AC_STSW.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NORMAL_REG_LOS;
            S000_ERR_MSG_PROC();
        }
        if (TDCPPRT.BV_NO.trim().length() > 0 
            && !(TDCPPRT.BV_TYP == '1' 
            || TDCPPRT.BV_TYP == ' ')) {
            IBS.init(SCCGWA, TDRBVT);
            TDRBVT.KEY.AC_NO = WS_CHECK_AC;
            T000_READ_TDTBVT();
            if (!TDCPPRT.BV_NO.equalsIgnoreCase(TDRBVT.BV_NO)) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_BV_NO_NOT_MATCH;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B210_BVCHG_REPRT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCBVCHG);
        TDCBVCHG.CHG_TYP = '3';
        TDCBVCHG.BV_CD = TDCPPRT.BV_CD;
        TDCBVCHG.BV_TYP = TDCPPRT.BV_TYP;
        TDCBVCHG.BV_NO = TDCPPRT.BV_NO;
        TDCBVCHG.AC_NO = TDCPPRT.AC_NO;
        TDCBVCHG.BV_NO_NEW = TDCPPRT.NEW_BVNO;
        S000_CALL_TDZBVCHG();
    }
    public void B110_GET_BV_TYP_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRBVT);
        TDRBVT.KEY.AC_NO = TDCPPRT.AC_NO;
        T000_READ_BVT1();
        CEP.TRC(SCCGWA, TDCPPRT.AC_NO);
        CEP.TRC(SCCGWA, TDRBVT.BV_CD);
        CEP.TRC(SCCGWA, TDRBVT.BV_NO);
    }
    public void B210_REPRINT_PB() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCPPRT.PRT_OPT);
        CEP.TRC(SCCGWA, TDCPPRTF.F_LINE);
        IBS.init(SCCGWA, TDCPPRTF);
        if (TDCPPRT.PRT_OPT == '1' 
            || TDCPPRT.PRT_OPT == ' ') {
            TDCPPRTF.OPT = '1';
        } else {
            TDCPPRTF.OPT = '5';
            TDCPPRTF.F_LINE = TDCPPRT.F_LINE;
            if (TDCPPRT.BV_TYP == '1') {
                TDCPPRTF.F_LINE = (short) (( TDCPPRTF.F_LINE - 1 ) * 2 + 1);
            }
            TDCPPRTF.RP_LINES = TDCPPRT.T_LINES;
            TDCPPRTF.RP_OPT = TDCPPRT.REPRT_OPT;
            CEP.TRC(SCCGWA, TDCPPRT.T_LINES);
        }
        TDCPPRTF.AC = TDCPPRT.AC_NO;
        if (TDCPPRT.BV_TYP == '1') {
            TDCPPRTF.PRDAC_CD = "020";
        } else {
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.AC_NO = TDCPPRT.AC_NO;
            T000_READ_TDTSMST();
            TDCPPRTF.PRDAC_CD = TDRSMST.PRDAC_CD;
            TDCPPRTF.ACO_AC = TDRSMST.KEY.ACO_AC;
        }
        TDCPPRTF.BV_CD = TDCPPRT.BV_CD;
        TDCPPRTF.BV_TYP = TDCPPRT.BV_TYP;
        TDCPPRTF.BV_NO = TDCPPRT.BV_NO;
        TDCPPRTF.AC_NAME = CICACCU.DATA.AC_CNM;
        CEP.TRC(SCCGWA, TDCPPRT.BV_CD);
        CEP.TRC(SCCGWA, TDCPPRT.BV_TYP);
        CEP.TRC(SCCGWA, TDCPPRT.BV_NO);
        CEP.TRC(SCCGWA, TDCPPRTF.OPT);
        CEP.TRC(SCCGWA, TDCPPRTF.BV_CD);
        CEP.TRC(SCCGWA, TDCPPRTF.BV_TYP);
        CEP.TRC(SCCGWA, TDCPPRTF.BV_NO);
        S000_CALL_TDZPPRTF();
    }
    public void B210_PRT_TITTLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCPPRTF);
        TDCPPRTF.OPT = '6';
        TDCPPRTF.AC = TDCPPRT.AC_NO;
        TDCPPRTF.BV_CD = TDCPPRT.BV_CD;
        TDCPPRTF.BV_TYP = TDCPPRT.BV_TYP;
        TDCPPRTF.BV_NO = TDCPPRT.BV_NO;
        TDCPPRTF.AC_NAME = CICACCU.DATA.AC_CNM;
        CEP.TRC(SCCGWA, TDCPPRTF.AC);
        CEP.TRC(SCCGWA, TDCPPRT.BV_CD);
        CEP.TRC(SCCGWA, TDCPPRT.BV_TYP);
        CEP.TRC(SCCGWA, TDCPPRT.BV_NO);
        S000_CALL_TDZPPRTF();
    }
    public void B220_WRITE_NFIN_HIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.TX_TYP_CD = "P103";
        BPCPNHIS.INFO.AC = TDCPPRT.AC_NO;
        BPCPNHIS.INFO.AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZPNHIS();
    }
    public void S000_CALL_TDZBVCHG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-BV-CHG", TDCBVCHG);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_TDZPPRTF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCPPRT.AC_NO);
        CEP.TRC(SCCGWA, TDCPPRTF.AC);
        IBS.CALLCPN(SCCGWA, "TD-PSBK-PRTF", TDCPPRTF);
        if (TDCPPRTF.RC_MSG.RC != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, TDCPPRTF.RC_MSG);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_READ_TDTMMST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCIMSTR);
        DCCIMSTR.KEY.VIA_AC = TDCPPRT.AC_NO;
        S000_CALL_DCZIMSTR();
        CEP.TRC(SCCGWA, DCCIMSTR.DATA.PROC_NUM);
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO";
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_TDTBVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCCUIQMC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-INQ-VA-AC", DCCUIQMC);
    }
    public void T000_READ_BVT1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            T000_READ_BVT_LAST();
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
                S000_ERR_MSG_PROC();
            } else {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_PSBK_N_MATCH;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void T000_READ_BVT_LAST() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.where = "AC_NO = :TDRBVT.KEY.AC_NO";
        IBS.READ(SCCGWA, TDRBVT, this, TDTBVT_RD);
    }
    public void S000_CALL_DCZIMSTR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-INQ-IAMST", DCCIMSTR);
        if (DCCIMSTR.RC.RC_CODE != 0) {
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, DCCIMSTR.RC);
            if (TDCPPRTF.BV_TYP == '1' 
                && JIBS_tmp_str[1].equalsIgnoreCase("DC2031")) {
                WS_YBT_AC_FLAG = 'N';
            } else {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCIMSTR.RC);
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void R000_GET_CI_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDCPPRT.AC_NO;
        S000_CALL_CIZACCU();
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_ENM);
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
