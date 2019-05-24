package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDZBSP10 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    brParm TDTBVT_BR = new brParm();
    DBParm TDTSMST_RD;
    DBParm TDTCMST_RD;
    DBParm TDTBVT_RD;
    DBParm TDTSTS_RD;
    boolean pgmRtn = false;
    String TBL_TDTSMST = "TDTSMST ";
    String TBL_TDTBVT = "TDTBVT  ";
    String TBL_TDTCMST = "TDTCMST ";
    char K_SET_ON = '1';
    char K_SET_OFF = '0';
    String WS_MSGID = " ";
    TDZBSP10_WS_STS_WORD WS_STS_WORD = new TDZBSP10_WS_STS_WORD();
    char WS_STS_TYPE = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCBINF SCCBINF = new SCCBINF();
    TDRSMST TDRSMST = new TDRSMST();
    TDRBVT TDRBVT = new TDRBVT();
    TDRSTS TDRSTS = new TDRSTS();
    TDRCMST TDRCMST = new TDRCMST();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    TDCBSP10 TDCBSP10;
    public void MP(SCCGWA SCCGWA, TDCBSP10 TDCBSP10) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCBSP10 = TDCBSP10;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZBSP10 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        IBS.init(SCCGWA, SCCEXCP);
        IBS.init(SCCGWA, CICQACAC);
    }
    public void B00_MAIN_PROCESS() throws IOException,SQLException,Exception {
        WS_STS_TYPE = TDCBSP10.TYPE;
        if (WS_STS_TYPE == '1') {
            B030_SMST_PROC();
            if (pgmRtn) return;
        } else if (WS_STS_TYPE == '2') {
            B050_BVT_PROC();
            if (pgmRtn) return;
        } else {
        }
        if (TDCBSP10.STS != '1') {
            R000_STS_INF_PROC();
            if (pgmRtn) return;
            if (TDRSTS.KEY.POS == 10) {
            }
        }
    }
    public void B030_SMST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCMST);
        TDRCMST.KEY.AC_NO = TDCBSP10.AC;
        T000_READ_TDTCMST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = TDCBSP10.AC;
        CICQACAC.DATA.AGR_SEQ = TDCBSP10.SEQ;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READUP_TDTSMST();
        if (pgmRtn) return;
        if (TDCBSP10.STS == '1') {
            if (TDRSMST.ACO_STS != '0') {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_STS_NOT_NORMAL;
                S00_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            JIBS_tmp_str[0] = "" + K_SET_ON;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            TDRSMST.STSW = TDRSMST.STSW.substring(0, TDCBSP10.POS - 1) + JIBS_tmp_str[0] + TDRSMST.STSW.substring(TDCBSP10.POS + 1 - 1);
        } else {
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            JIBS_tmp_str[0] = "" + K_SET_OFF;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            TDRSMST.STSW = TDRSMST.STSW.substring(0, TDCBSP10.POS - 1) + JIBS_tmp_str[0] + TDRSMST.STSW.substring(TDCBSP10.POS + 1 - 1);
        }
        TDRSMST.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_TDTSMST();
        if (pgmRtn) return;
    }
    public void B050_BVT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRBVT);
        IBS.init(SCCGWA, TDRCMST);
        TDRCMST.KEY.AC_NO = TDCBSP10.AC;
        T000_READ_TDTCMST();
        if (pgmRtn) return;
        if (TDRCMST.BV_TYP == ' ' 
            || TDRCMST.BV_TYP == '0') {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = TDCBSP10.AC;
            CICQACAC.DATA.AGR_SEQ = TDCBSP10.SEQ;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READUP_TDTSMST();
            if (pgmRtn) return;
            TDRBVT.KEY.AC_NO = TDRSMST.KEY.ACO_AC;
        } else {
            TDRBVT.KEY.AC_NO = TDRCMST.KEY.AC_NO;
        }
        T000_READUP_TDTBVT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDCBSP10.POS);
        CEP.TRC(SCCGWA, TDCBSP10.STS);
        CEP.TRC(SCCGWA, TDRBVT.STSW);
        if (TDCBSP10.STS == '1') {
            if (TDRBVT.STSW == null) TDRBVT.STSW = "";
            JIBS_tmp_int = TDRBVT.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
            JIBS_tmp_str[0] = "" + K_SET_ON;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            TDRBVT.STSW = TDRBVT.STSW.substring(0, TDCBSP10.POS - 1) + JIBS_tmp_str[0] + TDRBVT.STSW.substring(TDCBSP10.POS + 1 - 1);
        } else {
            if (TDRBVT.STSW == null) TDRBVT.STSW = "";
            JIBS_tmp_int = TDRBVT.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
            JIBS_tmp_str[0] = "" + K_SET_OFF;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            TDRBVT.STSW = TDRBVT.STSW.substring(0, TDCBSP10.POS - 1) + JIBS_tmp_str[0] + TDRBVT.STSW.substring(TDCBSP10.POS + 1 - 1);
            if (TDRBVT.STSW == null) TDRBVT.STSW = "";
            JIBS_tmp_int = TDRBVT.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
            if (TDCBSP10.POS == 2 
                && TDRBVT.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
                if (TDRBVT.STSW == null) TDRBVT.STSW = "";
                JIBS_tmp_int = TDRBVT.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
                TDRBVT.STSW = TDRBVT.STSW.substring(0, 7 - 1) + "0" + TDRBVT.STSW.substring(7 + 1 - 1);
                if (TDRBVT.STSW == null) TDRBVT.STSW = "";
                JIBS_tmp_int = TDRBVT.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
                CEP.TRC(SCCGWA, TDRBVT.STSW.substring(7 - 1, 7 + 1 - 1));
            }
        }
        TDRBVT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRBVT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_TDTBVT();
        if (pgmRtn) return;
    }
    public void T000_READNEXT_BVT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRBVT, this, TDTBVT_BR);
    }
    public void T000_STARTBR_BVT() throws IOException,SQLException,Exception {
        TDTBVT_BR.rp = new DBParm();
        TDTBVT_BR.rp.TableName = "TDTBVT";
        TDTBVT_BR.rp.upd = true;
        TDTBVT_BR.rp.where = "AC = :TDRBVT.KEY.AC_NO";
        IBS.STARTBR(SCCGWA, TDRBVT, this, TDTBVT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S00_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_STS_INF_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ);
        IBS.init(SCCGWA, TDRSTS);
        TDRSTS.KEY.AC_NO = TDCBSP10.AC;
        TDRSTS.KEY.TYPE = TDCBSP10.TYPE;
        TDRSTS.KEY.POS = TDCBSP10.POS;
        TDRSTS.KEY.AC_SEQ = TDCBSP10.SEQ;
        T000_READUP_TDTSTS();
        if (pgmRtn) return;
        TDRSTS.STS = '0';
        TDRSTS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRBVT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_TDTSTS();
        if (pgmRtn) return;
    }
    public void T000_READUP_TDTSMST() throws IOException,SQLException,Exception {
        null.upd = true;
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_TDTSMST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_TDTCMST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READUP_TDTBVT() throws IOException,SQLException,Exception {
        null.upd = true;
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_TDTBVT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void T000_REWRITE_TDTBVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.REWRITE(SCCGWA, TDRBVT, TDTBVT_RD);
    }
    public void T000_READUP_TDTSTS() throws IOException,SQLException,Exception {
        TDTSTS_RD = new DBParm();
        TDTSTS_RD.TableName = "TDTSTS";
        TDTSTS_RD.upd = true;
        IBS.READ(SCCGWA, TDRSTS, TDTSTS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void T000_REWRITE_TDTSTS() throws IOException,SQLException,Exception {
        TDTSTS_RD = new DBParm();
        TDTSTS_RD.TableName = "TDTSTS";
        IBS.REWRITE(SCCGWA, TDRSTS, TDTSTS_RD);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S00_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
        Z_RET();
        if (pgmRtn) return;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
