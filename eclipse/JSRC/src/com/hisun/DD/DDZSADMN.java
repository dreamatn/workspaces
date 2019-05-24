package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSADMN {
    DBParm DDTMST_RD;
    int JIBS_tmp_int;
    DBParm DDTADMN_RD;
    DBParm DDTHLD_RD;
    DBParm DDTCCY_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DD620";
    String K_ADMN_REMARK = "CHANGE DDTADMN INFORMATION";
    String WS_ADP_NO = " ";
    String WS_AC_CNM = " ";
    String WS_ADP_TYPE = " ";
    String WS_MSGID = " ";
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    char WS_DDTADMN_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDCOADMN DDCOADMN = new DDCOADMN();
    CICACCU CICACCU = new CICACCU();
    CICQACAC CICQACAC = new CICQACAC();
    DDCIMCYY DDCIMCYY = new DDCIMCYY();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCUINTI BPCUINTI = new BPCUINTI();
    DDRCCY DDRCCY = new DDRCCY();
    DDRMST DDRMST = new DDRMST();
    DDRADMN DDRADMN = new DDRADMN();
    DDRHLD DDRHLD = new DDRHLD();
    int WS_COUNT = 0;
    SCCGWA SCCGWA;
    DDCSADMN DDCSADMN;
    public void MP(SCCGWA SCCGWA, DDCSADMN DDCSADMN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSADMN = DDCSADMN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSADMN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (DDCSADMN.FUNC == '1') {
            B200_ADD_ADMN_REC_PROC();
            if (pgmRtn) return;
        } else if (DDCSADMN.FUNC == '2') {
            B300_MOD_ADMN_REC_PROC();
            if (pgmRtn) return;
        } else if (DDCSADMN.FUNC == '3') {
            B400_STP_ADMN_REC_PROC();
            if (pgmRtn) return;
        } else if (DDCSADMN.FUNC == '4') {
            B500_INQ_ADMN_REC_PROC();
            if (pgmRtn) return;
        } else if (DDCSADMN.FUNC == '5') {
            B600_STOP_ADMN_REC_PROC();
            if (pgmRtn) return;
        } else if (DDCSADMN.FUNC == '6') {
            B700_REUSE_ADMN_REC_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID TX TYPE(" + DDCSADMN.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        B800_NON_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B150_GET_ACAC_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = DDCSADMN.AC;
        CICQACAC.DATA.CCY_ACAC = DDCSADMN.CCY;
        CICQACAC.DATA.CR_FLG = DDCSADMN.CCY_TYP;
        CEP.TRC(SCCGWA, CICQACAC.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.DATA.CCY_ACAC);
        CEP.TRC(SCCGWA, CICQACAC.DATA.CR_FLG);
        CICQACAC.FUNC = 'C';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ACAC_NO_NOT_EXIT);
        }
    }
    public void B160_GET_AC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        IBS.init(SCCGWA, DDCIMCYY);
        DDRMST.KEY.CUS_AC = DDCSADMN.AC;
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.where = "CUS_AC = :DDRMST.KEY.CUS_AC";
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, this, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, DDRMST.CI_TYP);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_MST_REC_NOTFND);
        }
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDCSADMN.AC;
        DDRCCY.CCY = DDCSADMN.CCY;
        DDRCCY.CCY_TYPE = DDCSADMN.CCY_TYP;
        R000_READ_DDTCCY_BY_CUSAC();
        if (pgmRtn) return;
        if (DDCSADMN.FUNC == '1') {
            if (DDRCCY.CURR_BAL < 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_UPT1_RECORD_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, DDCSADMN.STR_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD.substring(42 - 1, 42 + 1 - 1));
        if (DDCSADMN.FUNC == '1' 
            || DDCSADMN.FUNC == '2') {
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(42 - 1, 42 + 1 - 1).equalsIgnoreCase("0")) {
                if (DDCSADMN.STR_DATE == SCCGWA.COMM_AREA.AC_DATE) {
                    if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                    JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                    for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                    DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 42 - 1) + "1" + DDRMST.AC_STS_WORD.substring(42 + 1 - 1);
                    T000_REWRITE_DDTMST();
                    if (pgmRtn) return;
                    DDCIMCYY.DATA.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
                    DDCIMCYY.TX_TYPE = 'S';
                    DDCIMCYY.DATA.SET_FLG = 'O';
                    DDCIMCYY.DATA.STS_CD = "67";
                    S000_CALL_DDZIMCYY();
                    if (pgmRtn) return;
                }
            }
        }
        if (DDCSADMN.FUNC == '3') {
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 42 - 1) + "0" + DDRMST.AC_STS_WORD.substring(42 + 1 - 1);
            T000_REWRITE_DDTMST();
            if (pgmRtn) return;
            DDCIMCYY.DATA.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            DDCIMCYY.TX_TYPE = 'S';
            DDCIMCYY.DATA.SET_FLG = 'F';
            DDCIMCYY.DATA.STS_CD = "67";
            S000_CALL_DDZIMCYY();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDCSADMN.AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
        WS_AC_CNM = CICACCU.DATA.AC_CNM;
    }
    public void R000_CHECK_RATE_TERM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSADMN.LN_RTYP);
        CEP.TRC(SCCGWA, DDCSADMN.LN_RTERM);
        if (DDCSADMN.LN_RTYP.trim().length() > 0 
            && DDCSADMN.LN_RTERM.trim().length() > 0) {
            IBS.init(SCCGWA, BPCUINTI);
            BPCUINTI.CCY = DDCSADMN.CCY;
            BPCUINTI.BASE_TYP = DDCSADMN.LN_RTYP;
            BPCUINTI.TENOR = DDCSADMN.LN_RTERM;
            BPCUINTI.DT = SCCGWA.COMM_AREA.AC_DATE;
            S00_CALL_BPZUINTI();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCSADMN.LN_LRTYP);
        CEP.TRC(SCCGWA, DDCSADMN.LN_LRTRM);
        if (DDCSADMN.LN_LRTYP.trim().length() > 0 
            && DDCSADMN.LN_LRTRM.trim().length() > 0) {
            IBS.init(SCCGWA, BPCUINTI);
            BPCUINTI.CCY = DDCSADMN.CCY;
            BPCUINTI.BASE_TYP = DDCSADMN.LN_LRTYP;
            BPCUINTI.TENOR = DDCSADMN.LN_LRTRM;
            BPCUINTI.DT = SCCGWA.COMM_AREA.AC_DATE;
            S00_CALL_BPZUINTI();
            if (pgmRtn) return;
        }
    }
    public void B200_ADD_ADMN_REC_PROC() throws IOException,SQLException,Exception {
        B150_GET_ACAC_NO();
        if (pgmRtn) return;
        B160_GET_AC_INF();
        if (pgmRtn) return;
        R100_READ_DDTADMN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ADMN_ALR_REC_EXIST);
        }
        IBS.init(SCCGWA, DDRADMN);
        DDRADMN.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRADMN.KEY.ADP_NO = DDCSADMN.ADP_NO;
        DDRADMN.KEY.ADP_TYPE = DDCSADMN.ADP_TYPE;
        if (DDCSADMN.ADP_TYPE.equalsIgnoreCase("08") 
            && DDRMST.CI_TYP != '2') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ADPTYPE_NOSUE_CITYP);
        }
        if (DDCSADMN.ADP_TYPE.equalsIgnoreCase("10") 
            && DDRMST.CI_TYP != '3') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ADPTYPE_NOSUE_CITYP);
        }
        DDRADMN.ADP_STRDATE = DDCSADMN.STR_DATE;
        DDRADMN.ADP_EXPDATE = DDCSADMN.EXP_DATE;
        DDRADMN.ADP_HOLD_DATE = 0;
        DDRADMN.ADP_UNHOLD_DATE = 0;
        CEP.TRC(SCCGWA, DDCSADMN.STR_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (DDCSADMN.STR_DATE == SCCGWA.COMM_AREA.AC_DATE) {
            DDRADMN.ADP_STS = 'O';
        }
        if (DDCSADMN.STR_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            DDRADMN.ADP_STS = 'D';
        }
        R000_CHECK_RATE_TERM();
        if (pgmRtn) return;
        DDRADMN.ADP_STSW = "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        DDRADMN.OD_AMT = DDCSADMN.OD_AMT;
        DDRADMN.FEE_CD = DDCSADMN.FEE_CD;
        DDRADMN.OD_DAYS = DDCSADMN.OD_DAYS;
        DDRADMN.OD_INT_TYP = DDCSADMN.OD_INTYP;
        DDRADMN.OD_INT_RAT_FLT_TYP = DDCSADMN.OD_RFTYP;
        DDRADMN.OD_INT_RAT = DDCSADMN.OD_RATE;
        DDRADMN.OD_INT_RAT_PCT = DDCSADMN.OD_RPCT;
        DDRADMN.OD_INT_RAT_VAR = DDCSADMN.OD_RVAR;
        DDRADMN.LN_INT_RAT_COM_TYP = DDCSADMN.LN_RCTYP;
        DDRADMN.LN_INT_RAT_FLT_TYP = DDCSADMN.LN_RFTYP;
        DDRADMN.LN_INT_RAT = DDCSADMN.LN_RATE;
        DDRADMN.LN_INT_RAT_PCT = DDCSADMN.LN_RPCT;
        DDRADMN.LN_INT_RAT_VAR = DDCSADMN.LN_RVAR;
        DDRADMN.LN_INT_RAT_TYP = DDCSADMN.LN_RTYP;
        DDRADMN.LN_INT_RAT_TERM = DDCSADMN.LN_RTERM;
        DDRADMN.LN_L_USE_FLG = DDCSADMN.LN_LFLG;
        DDRADMN.LN_L_COM_TYP = DDCSADMN.LN_LCTYP;
        DDRADMN.LN_L_FLT_TYP = DDCSADMN.LN_LFTYP;
        DDRADMN.LN_L_INT_RAT = DDCSADMN.LN_LRATE;
        DDRADMN.LN_L_INT_RAT_PCT = DDCSADMN.LN_LRPCT;
        DDRADMN.LN_L_INT_RAT_VAR = DDCSADMN.LN_LRVAR;
        DDRADMN.LN_L_INT_RAT_TYP = DDCSADMN.LN_LRTYP.charAt(0);
        DDRADMN.LN_L_INT_RAT_TERM = DDCSADMN.LN_LRTRM;
        DDRADMN.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRADMN.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRADMN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRADMN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        R000_WRITE_DDTADMN();
        if (pgmRtn) return;
        B210_OUTPUT_DATA_ADD();
        if (pgmRtn) return;
    }
    public void B210_OUTPUT_DATA_ADD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOADMN);
        DDCOADMN.FUNC = DDCSADMN.FUNC;
        DDCOADMN.ADP_NO = DDCSADMN.ADP_NO;
        DDCOADMN.ADP_TYPE = DDCSADMN.ADP_TYPE;
        DDCOADMN.AC = DDCSADMN.AC;
        DDCOADMN.AC_NM = WS_AC_CNM;
        DDCOADMN.CCY = DDCSADMN.CCY;
        DDCOADMN.CCY_TYP = DDCSADMN.CCY_TYP;
        DDCOADMN.STR_DATE = DDCSADMN.STR_DATE;
        DDCOADMN.EXP_DATE = DDCSADMN.EXP_DATE;
        DDCOADMN.OD_AMT = DDCSADMN.OD_AMT;
        DDCOADMN.OD_INTYP = DDCSADMN.OD_INTYP;
        DDCOADMN.OD_RFTYP = DDCSADMN.OD_RFTYP;
        DDCOADMN.OD_RATE = DDCSADMN.OD_RATE;
        DDCOADMN.OD_RPCT = DDCSADMN.OD_RPCT;
        DDCOADMN.OD_RVAR = DDCSADMN.OD_RVAR;
        DDCOADMN.OD_DAYS = DDCSADMN.OD_DAYS;
        DDCOADMN.LN_RCTYP = DDCSADMN.LN_RCTYP;
        DDCOADMN.LN_RFTYP = DDCSADMN.LN_RFTYP;
        DDCOADMN.LN_RATE = DDCSADMN.LN_RATE;
        DDCOADMN.LN_RPCT = DDCSADMN.LN_RPCT;
        DDCOADMN.LN_RVAR = DDCSADMN.LN_RVAR;
        DDCOADMN.LN_RTYP = DDCSADMN.LN_RTYP;
        DDCOADMN.LN_TERM = DDCSADMN.LN_RTERM;
        DDCOADMN.LN_LFLG = DDCSADMN.LN_LFLG;
        DDCOADMN.LN_LCTYP = DDCSADMN.LN_LCTYP;
        DDCOADMN.LN_LFTYP = DDCSADMN.LN_LFTYP;
        DDCOADMN.LN_LRAT = DDCSADMN.LN_LRATE;
        DDCOADMN.LN_LRPCT = DDCSADMN.LN_LRPCT;
        DDCOADMN.LN_LRVAR = DDCSADMN.LN_LRVAR;
        DDCOADMN.LN_LRTYP = DDCSADMN.LN_LRTYP;
        DDCOADMN.LN_LRTRM = DDCSADMN.LN_LRTRM;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOADMN;
        SCCFMT.DATA_LEN = 480;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R100_READ_DDTADMN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRADMN);
        DDRADMN.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, DDRADMN.KEY.AC);
        DDTADMN_RD = new DBParm();
        DDTADMN_RD.TableName = "DDTADMN";
        DDTADMN_RD.where = "AC = :DDRADMN.KEY.AC "
            + "AND ( ADP_STS = 'D' "
            + "OR ADP_STS = 'O' )";
        IBS.READ(SCCGWA, DDRADMN, this, DDTADMN_RD);
    }
    public void T000_READUP_DDTMST() throws IOException,SQLException,Exception {
        DDTADMN_RD = new DBParm();
        DDTADMN_RD.TableName = "DDTADMN";
        DDTADMN_RD.where = "AC = :DDRADMN.KEY.AC "
            + "AND ( ADP_STS = 'D' "
            + "OR ADP_STS = 'O' )";
        IBS.READ(SCCGWA, DDRADMN, this, DDTADMN_RD);
    }
    public void R200_READ_DDTADMN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRADMN);
        DDRADMN.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDTADMN_RD = new DBParm();
        DDTADMN_RD.TableName = "DDTADMN";
        DDTADMN_RD.where = "AC = :DDRADMN.KEY.AC "
            + "AND ADP_STS = 'F'";
        IBS.READ(SCCGWA, DDRADMN, this, DDTADMN_RD);
    }
    public void R000_DELETE_REC_ADMN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRADMN);
        DDRADMN.KEY.ADP_NO = WS_ADP_NO;
        DDRADMN.KEY.ADP_TYPE = WS_ADP_TYPE;
        DDRADMN.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDTADMN_RD = new DBParm();
        DDTADMN_RD.TableName = "DDTADMN";
        DDTADMN_RD.where = "ADP_NO = :DDRADMN.KEY.ADP_NO "
            + "AND ADP_TYPE = :DDRADMN.KEY.ADP_TYPE "
            + "AND AC = :DDRADMN.KEY.AC";
        IBS.DELETE(SCCGWA, DDRADMN, this, DDTADMN_RD);
    }
    public void R000_WRITE_DDTADMN() throws IOException,SQLException,Exception {
        DDTADMN_RD = new DBParm();
        DDTADMN_RD.TableName = "DDTADMN";
        IBS.WRITE(SCCGWA, DDRADMN, DDTADMN_RD);
    }
    public void B300_MOD_ADMN_REC_PROC() throws IOException,SQLException,Exception {
        B150_GET_ACAC_NO();
        if (pgmRtn) return;
        B160_GET_AC_INF();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRADMN);
        DDRADMN.KEY.ADP_NO = DDCSADMN.ADP_NO;
        DDRADMN.KEY.ADP_TYPE = DDCSADMN.ADP_TYPE;
        DDRADMN.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        R000_READUPDATE_DDTADMN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ADMN_REC_NOTFND);
        }
        if (DDCSADMN.OD_RFTYP != DDRADMN.OD_INT_RAT_FLT_TYP 
            || DDCSADMN.OD_RATE != DDRADMN.OD_INT_RAT 
            || DDCSADMN.OD_RPCT != DDRADMN.OD_INT_RAT_PCT 
            || DDCSADMN.OD_RVAR != DDRADMN.OD_INT_RAT_VAR) {
            DDCSADMN.ODRAT_FLG = 'Y';
        }
        DDRADMN.ADP_STRDATE = DDCSADMN.STR_DATE;
        DDRADMN.ADP_EXPDATE = DDCSADMN.EXP_DATE;
        if (DDCSADMN.ODRAT_FLG == 'Y') {
            if (DDRADMN.ADP_STSW == null) DDRADMN.ADP_STSW = "";
            JIBS_tmp_int = DDRADMN.ADP_STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRADMN.ADP_STSW += " ";
            DDRADMN.ADP_STSW = DDRADMN.ADP_STSW.substring(0, 3 - 1) + "1" + DDRADMN.ADP_STSW.substring(3 + 1 - 1);
        }
        CEP.TRC(SCCGWA, DDCSADMN.STR_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (DDCSADMN.STR_DATE == SCCGWA.COMM_AREA.AC_DATE) {
            DDRADMN.ADP_STS = 'O';
        }
        R000_CHECK_RATE_TERM();
        if (pgmRtn) return;
        DDRADMN.OD_AMT = DDCSADMN.OD_AMT;
        DDRADMN.OD_DAYS = DDCSADMN.OD_DAYS;
        DDRADMN.OD_INT_RAT_FLT_TYP = DDCSADMN.OD_RFTYP;
        DDRADMN.OD_INT_RAT = DDCSADMN.OD_RATE;
        DDRADMN.OD_INT_RAT_PCT = DDCSADMN.OD_RPCT;
        DDRADMN.OD_INT_RAT_VAR = DDCSADMN.OD_RVAR;
        DDRADMN.LN_INT_RAT_COM_TYP = DDCSADMN.LN_RCTYP;
        DDRADMN.LN_INT_RAT_FLT_TYP = DDCSADMN.LN_RFTYP;
        DDRADMN.LN_INT_RAT = DDCSADMN.LN_RATE;
        DDRADMN.LN_INT_RAT_PCT = DDCSADMN.LN_RPCT;
        DDRADMN.LN_INT_RAT_VAR = DDCSADMN.LN_RVAR;
        DDRADMN.LN_INT_RAT_TYP = DDCSADMN.LN_RTYP;
        DDRADMN.LN_INT_RAT_TERM = DDCSADMN.LN_RTERM;
        DDRADMN.LN_L_USE_FLG = DDCSADMN.LN_LFLG;
        DDRADMN.LN_L_COM_TYP = DDCSADMN.LN_LCTYP;
        DDRADMN.LN_L_FLT_TYP = DDCSADMN.LN_LFTYP;
        DDRADMN.LN_L_INT_RAT = DDCSADMN.LN_LRATE;
        DDRADMN.LN_L_INT_RAT_PCT = DDCSADMN.LN_LRPCT;
        DDRADMN.LN_L_INT_RAT_VAR = DDCSADMN.LN_LRVAR;
        DDRADMN.LN_L_INT_RAT_TYP = DDCSADMN.LN_LRTYP.charAt(0);
        DDRADMN.LN_L_INT_RAT_TERM = DDCSADMN.LN_LRTRM;
        DDRADMN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRADMN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        R000_REWRITE_DDTADMN();
        if (pgmRtn) return;
        B310_OUTPUT_DATA_MOD();
        if (pgmRtn) return;
    }
    public void B310_OUTPUT_DATA_MOD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOADMN);
        DDCOADMN.FUNC = DDCSADMN.FUNC;
        DDCOADMN.ADP_NO = DDCSADMN.ADP_NO;
        DDCOADMN.ADP_TYPE = DDCSADMN.ADP_TYPE;
        DDCOADMN.AC = DDCSADMN.AC;
        DDCOADMN.AC_NM = WS_AC_CNM;
        DDCOADMN.CCY = DDCSADMN.CCY;
        DDCOADMN.CCY_TYP = DDCSADMN.CCY_TYP;
        DDCOADMN.STR_DATE = DDCSADMN.STR_DATE;
        DDCOADMN.EXP_DATE = DDCSADMN.EXP_DATE;
        DDCOADMN.OD_AMT = DDCSADMN.OD_AMT;
        DDCOADMN.OD_INTYP = DDCSADMN.OD_INTYP;
        DDCOADMN.OD_RFTYP = DDCSADMN.OD_RFTYP;
        DDCOADMN.OD_RATE = DDCSADMN.OD_RATE;
        DDCOADMN.OD_RPCT = DDCSADMN.OD_RPCT;
        DDCOADMN.OD_RVAR = DDCSADMN.OD_RVAR;
        DDCOADMN.OD_DAYS = DDCSADMN.OD_DAYS;
        DDCOADMN.LN_RCTYP = DDCSADMN.LN_RCTYP;
        DDCOADMN.LN_RFTYP = DDCSADMN.LN_RFTYP;
        DDCOADMN.LN_RATE = DDCSADMN.LN_RATE;
        DDCOADMN.LN_RPCT = DDCSADMN.LN_RPCT;
        DDCOADMN.LN_RVAR = DDCSADMN.LN_RVAR;
        DDCOADMN.LN_RTYP = DDCSADMN.LN_RTYP;
        DDCOADMN.LN_TERM = DDCSADMN.LN_RTERM;
        DDCOADMN.LN_LFLG = DDCSADMN.LN_LFLG;
        DDCOADMN.LN_LCTYP = DDCSADMN.LN_LCTYP;
        DDCOADMN.LN_LFTYP = DDCSADMN.LN_LFTYP;
        DDCOADMN.LN_LRAT = DDCSADMN.LN_LRATE;
        DDCOADMN.LN_LRPCT = DDCSADMN.LN_LRPCT;
        DDCOADMN.LN_LRVAR = DDCSADMN.LN_LRVAR;
        DDCOADMN.LN_LRTYP = DDCSADMN.LN_LRTYP;
        DDCOADMN.LN_LRTRM = DDCSADMN.LN_LRTRM;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOADMN;
        SCCFMT.DATA_LEN = 480;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_READUPDATE_DDTADMN() throws IOException,SQLException,Exception {
        DDTADMN_RD = new DBParm();
        DDTADMN_RD.TableName = "DDTADMN";
        DDTADMN_RD.where = "ADP_NO = :DDRADMN.KEY.ADP_NO "
            + "AND ADP_TYPE = :DDRADMN.KEY.ADP_TYPE "
            + "AND AC = :DDRADMN.KEY.AC";
        DDTADMN_RD.upd = true;
        IBS.READ(SCCGWA, DDRADMN, this, DDTADMN_RD);
    }
    public void R000_REWRITE_DDTADMN() throws IOException,SQLException,Exception {
        DDTADMN_RD = new DBParm();
        DDTADMN_RD.TableName = "DDTADMN";
        IBS.REWRITE(SCCGWA, DDRADMN, DDTADMN_RD);
    }
    public void B400_STP_ADMN_REC_PROC() throws IOException,SQLException,Exception {
        B150_GET_ACAC_NO();
        if (pgmRtn) return;
        B160_GET_AC_INF();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRADMN);
        DDRADMN.KEY.ADP_NO = DDCSADMN.ADP_NO;
        DDRADMN.KEY.ADP_TYPE = DDCSADMN.ADP_TYPE;
        DDRADMN.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        R000_READUPDATE_DDTADMN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ADMN_REC_NOTFND);
        }
        DDRADMN.ADP_STS = 'F';
        DDRADMN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRADMN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        R000_REWRITE_DDTADMN();
        if (pgmRtn) return;
        B410_OUTPUT_DATA_STP();
        if (pgmRtn) return;
    }
    public void B410_OUTPUT_DATA_STP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOADMN);
        DDCOADMN.FUNC = DDCSADMN.FUNC;
        DDCOADMN.ADP_NO = DDCSADMN.ADP_NO;
        DDCOADMN.ADP_TYPE = DDCSADMN.ADP_TYPE;
        DDCOADMN.AC = DDCSADMN.AC;
        DDCOADMN.AC_NM = WS_AC_CNM;
        DDCOADMN.CCY = DDCSADMN.CCY;
        DDCOADMN.CCY_TYP = DDCSADMN.CCY_TYP;
        DDCOADMN.STR_DATE = DDRADMN.ADP_STRDATE;
        DDCOADMN.EXP_DATE = DDRADMN.ADP_EXPDATE;
        DDCOADMN.OD_AMT = DDRADMN.OD_AMT;
        DDCOADMN.OD_INTYP = DDRADMN.OD_INT_TYP;
        DDCOADMN.OD_RFTYP = DDRADMN.OD_INT_RAT_FLT_TYP;
        DDCOADMN.OD_RATE = DDRADMN.OD_INT_RAT;
        DDCOADMN.OD_RPCT = DDRADMN.OD_INT_RAT_PCT;
        DDCOADMN.OD_RVAR = DDRADMN.OD_INT_RAT_VAR;
        DDCOADMN.OD_DAYS = DDRADMN.OD_DAYS;
        DDCOADMN.LN_RCTYP = DDRADMN.LN_INT_RAT_COM_TYP;
        DDCOADMN.LN_RFTYP = DDRADMN.LN_INT_RAT_FLT_TYP;
        DDCOADMN.LN_RATE = DDRADMN.LN_INT_RAT;
        DDCOADMN.LN_RPCT = DDRADMN.LN_INT_RAT_PCT;
        DDCOADMN.LN_RVAR = DDRADMN.LN_INT_RAT_VAR;
        DDCOADMN.LN_RTYP = DDRADMN.LN_INT_RAT_TYP;
        DDCOADMN.LN_TERM = DDRADMN.LN_INT_RAT_TERM;
        DDCOADMN.LN_LFLG = DDRADMN.LN_L_USE_FLG;
        DDCOADMN.LN_LCTYP = DDRADMN.LN_L_COM_TYP;
        DDCOADMN.LN_LFTYP = DDRADMN.LN_L_FLT_TYP;
        DDCOADMN.LN_LRAT = DDRADMN.LN_L_INT_RAT;
        DDCOADMN.LN_LRPCT = DDRADMN.LN_L_INT_RAT_PCT;
        DDCOADMN.LN_LRVAR = DDRADMN.LN_L_INT_RAT_VAR;
        DDCOADMN.LN_LRTYP = "" + DDRADMN.LN_L_INT_RAT_TYP;
        JIBS_tmp_int = DDCOADMN.LN_LRTYP.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) DDCOADMN.LN_LRTYP = "0" + DDCOADMN.LN_LRTYP;
        DDCOADMN.LN_LRTRM = DDRADMN.LN_L_INT_RAT_TERM;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOADMN;
        SCCFMT.DATA_LEN = 480;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B500_INQ_ADMN_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRADMN);
        DDRADMN.KEY.ADP_NO = DDCSADMN.ADP_NO;
        R100_READ_DDTADMN_BY_ADPNO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, DDRADMN.KEY.ADP_TYPE);
        CEP.TRC(SCCGWA, DDRADMN.KEY.AC);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ADMN_REC_NOTFND);
        }
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = DDRADMN.KEY.AC;
        R000_READ_DDTCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, DDRCCY.CCY);
        CEP.TRC(SCCGWA, DDRCCY.CCY_TYPE);
        CEP.TRC(SCCGWA, DDRCCY.CUS_AC);
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDRCCY.CUS_AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
        WS_AC_CNM = CICACCU.DATA.AC_CNM;
        IBS.init(SCCGWA, DDCOADMN);
        DDCOADMN.FUNC = DDCSADMN.FUNC;
        DDCOADMN.ADP_NO = DDCSADMN.ADP_NO;
        DDCOADMN.ADP_TYPE = DDRADMN.KEY.ADP_TYPE;
        DDCOADMN.AC = DDRCCY.CUS_AC;
        DDCOADMN.AC_NM = WS_AC_CNM;
        DDCOADMN.CCY = DDRCCY.CCY;
        DDCOADMN.CCY_TYP = DDRCCY.CCY_TYPE;
        DDCOADMN.STR_DATE = DDRADMN.ADP_STRDATE;
        DDCOADMN.EXP_DATE = DDRADMN.ADP_EXPDATE;
        DDCOADMN.OD_AMT = DDRADMN.OD_AMT;
        DDCOADMN.OD_INTYP = DDRADMN.OD_INT_TYP;
        DDCOADMN.OD_RFTYP = DDRADMN.OD_INT_RAT_FLT_TYP;
        DDCOADMN.OD_RATE = DDRADMN.OD_INT_RAT;
        DDCOADMN.OD_RPCT = DDRADMN.OD_INT_RAT_PCT;
        DDCOADMN.OD_RVAR = DDRADMN.OD_INT_RAT_VAR;
        DDCOADMN.OD_DAYS = DDRADMN.OD_DAYS;
        DDCOADMN.LN_RCTYP = DDRADMN.LN_INT_RAT_COM_TYP;
        DDCOADMN.LN_RFTYP = DDRADMN.LN_INT_RAT_FLT_TYP;
        DDCOADMN.LN_RATE = DDRADMN.LN_INT_RAT;
        DDCOADMN.LN_RPCT = DDRADMN.LN_INT_RAT_PCT;
        DDCOADMN.LN_RVAR = DDRADMN.LN_INT_RAT_VAR;
        DDCOADMN.LN_RTYP = DDRADMN.LN_INT_RAT_TYP;
        DDCOADMN.LN_TERM = DDRADMN.LN_INT_RAT_TERM;
        DDCOADMN.LN_LFLG = DDRADMN.LN_L_USE_FLG;
        DDCOADMN.LN_LCTYP = DDRADMN.LN_L_COM_TYP;
        DDCOADMN.LN_LFTYP = DDRADMN.LN_L_FLT_TYP;
        DDCOADMN.LN_LRAT = DDRADMN.LN_L_INT_RAT;
        DDCOADMN.LN_LRPCT = DDRADMN.LN_L_INT_RAT_PCT;
        DDCOADMN.LN_LRVAR = DDRADMN.LN_L_INT_RAT_VAR;
        DDCOADMN.LN_LRTYP = "" + DDRADMN.LN_L_INT_RAT_TYP;
        JIBS_tmp_int = DDCOADMN.LN_LRTYP.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) DDCOADMN.LN_LRTYP = "0" + DDCOADMN.LN_LRTYP;
        DDCOADMN.LN_LRTRM = DDRADMN.LN_L_INT_RAT_TERM;
        DDCOADMN.FEE_CD = DDRADMN.FEE_CD;
        CEP.TRC(SCCGWA, DDCOADMN.FUNC);
        CEP.TRC(SCCGWA, DDCOADMN.ADP_NO);
        CEP.TRC(SCCGWA, DDCOADMN.ADP_TYPE);
        CEP.TRC(SCCGWA, DDCOADMN.AC);
        CEP.TRC(SCCGWA, DDCOADMN.AC_NM);
        CEP.TRC(SCCGWA, DDCOADMN.CCY);
        CEP.TRC(SCCGWA, DDCOADMN.CCY_TYP);
        CEP.TRC(SCCGWA, DDCOADMN.STR_DATE);
        CEP.TRC(SCCGWA, DDCOADMN.EXP_DATE);
        CEP.TRC(SCCGWA, DDCOADMN.OD_AMT);
        CEP.TRC(SCCGWA, DDCOADMN.OD_INTYP);
        CEP.TRC(SCCGWA, DDCOADMN.OD_RFTYP);
        CEP.TRC(SCCGWA, DDCOADMN.OD_RATE);
        CEP.TRC(SCCGWA, DDCOADMN.OD_RPCT);
        CEP.TRC(SCCGWA, DDCOADMN.OD_RVAR);
        CEP.TRC(SCCGWA, DDCOADMN.OD_DAYS);
        CEP.TRC(SCCGWA, DDCOADMN.LN_RCTYP);
        CEP.TRC(SCCGWA, DDCOADMN.LN_RFTYP);
        CEP.TRC(SCCGWA, DDCOADMN.LN_RATE);
        CEP.TRC(SCCGWA, DDCOADMN.LN_RPCT);
        CEP.TRC(SCCGWA, DDCOADMN.LN_RVAR);
        CEP.TRC(SCCGWA, DDCOADMN.LN_RTYP);
        CEP.TRC(SCCGWA, DDCOADMN.LN_TERM);
        CEP.TRC(SCCGWA, DDCOADMN.LN_LFLG);
        CEP.TRC(SCCGWA, DDCOADMN.LN_LCTYP);
        CEP.TRC(SCCGWA, DDCOADMN.LN_LFTYP);
        CEP.TRC(SCCGWA, DDCOADMN.LN_LRAT);
        CEP.TRC(SCCGWA, DDCOADMN.LN_LRPCT);
        CEP.TRC(SCCGWA, DDCOADMN.LN_LRVAR);
        CEP.TRC(SCCGWA, DDCOADMN.LN_LRTYP);
        CEP.TRC(SCCGWA, DDCOADMN.LN_LRTRM);
        CEP.TRC(SCCGWA, DDCOADMN.FEE_CD);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOADMN;
        SCCFMT.DATA_LEN = 480;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B600_STOP_ADMN_REC_PROC() throws IOException,SQLException,Exception {
        B150_GET_ACAC_NO();
        if (pgmRtn) return;
        B160_GET_AC_INF();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRADMN);
        DDRADMN.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRADMN.KEY.ADP_NO = DDCSADMN.ADP_NO;
        DDRADMN.KEY.ADP_TYPE = DDCSADMN.ADP_TYPE;
        T000_READ_UPD_DDTADMN();
        if (pgmRtn) return;
        if (WS_DDTADMN_FLG == 'N') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ADMN_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (DDRADMN.ADP_STS != 'O') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_OD_STS_NOTNORMAL;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            DDRADMN.ADP_STS = 'S';
            DDRADMN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRADMN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            R000_REWRITE_DDTADMN();
            if (pgmRtn) return;
        }
        B410_OUTPUT_DATA_STP();
        if (pgmRtn) return;
    }
    public void B700_REUSE_ADMN_REC_PROC() throws IOException,SQLException,Exception {
        B150_GET_ACAC_NO();
        if (pgmRtn) return;
        B160_GET_AC_INF();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRADMN);
        DDRADMN.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRADMN.KEY.ADP_NO = DDCSADMN.ADP_NO;
        DDRADMN.KEY.ADP_TYPE = DDCSADMN.ADP_TYPE;
        T000_READ_UPD_DDTADMN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRADMN.ADP_STS);
        if (WS_DDTADMN_FLG == 'N') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ADMN_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (DDRADMN.ADP_STS != 'S') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_OD_STS_NOTNORMAL;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDRADMN.ADP_STSW == null) DDRADMN.ADP_STSW = "";
            JIBS_tmp_int = DDRADMN.ADP_STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRADMN.ADP_STSW += " ";
            CEP.TRC(SCCGWA, DDRADMN.ADP_STSW.substring(2 - 1, 2 + 1 - 1));
            if (DDRADMN.ADP_STSW == null) DDRADMN.ADP_STSW = "";
            JIBS_tmp_int = DDRADMN.ADP_STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRADMN.ADP_STSW += " ";
            if (DDRADMN.ADP_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ADVANCE_CASH_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, DDRHLD);
            DDRHLD.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            R000_GROUP_DDTHLD();
            if (pgmRtn) return;
            if (WS_COUNT != 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_HAS_LAW_HLD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            DDRADMN.ADP_STS = 'O';
            DDRADMN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRADMN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            R000_REWRITE_DDTADMN();
            if (pgmRtn) return;
        }
        B410_OUTPUT_DATA_STP();
        if (pgmRtn) return;
    }
    public void B800_NON_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.AC = DDCSADMN.AC;
        BPCPNHIS.INFO.CCY = DDCSADMN.CCY;
        BPCPNHIS.INFO.CCY_TYPE = DDCSADMN.CCY_TYP;
        BPCPNHIS.INFO.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        BPCPNHIS.INFO.TX_TOOL = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        BPCPNHIS.INFO.TX_RMK = K_ADMN_REMARK;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPD_DDTADMN() throws IOException,SQLException,Exception {
        DDTADMN_RD = new DBParm();
        DDTADMN_RD.TableName = "DDTADMN";
        DDTADMN_RD.where = "AC = :DDRADMN.KEY.AC "
            + "AND ADP_NO = :DDRADMN.KEY.ADP_NO "
            + "AND ADP_TYPE = :DDRADMN.KEY.ADP_TYPE";
        DDTADMN_RD.upd = true;
        IBS.READ(SCCGWA, DDRADMN, this, DDTADMN_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTADMN_FLG = 'Y';
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_DDTADMN_FLG = 'N';
            }
        }
    }
    public void R000_GROUP_DDTHLD() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.set = "WS-COUNT=COUNT(*)";
        DDTHLD_RD.where = "AC = :DDRHLD.AC "
            + "AND ( HLD_STS = 'N' "
            + "OR HLD_STS = 'W' ) "
            + "AND SPR_BR_TYP = '1'";
        IBS.GROUP(SCCGWA, DDRHLD, this, DDTHLD_RD);
        CEP.TRC(SCCGWA, WS_COUNT);
    }
    public void R100_READ_DDTADMN_BY_ADPNO() throws IOException,SQLException,Exception {
        DDTADMN_RD = new DBParm();
        DDTADMN_RD.TableName = "DDTADMN";
        DDTADMN_RD.where = "ADP_NO = :DDRADMN.KEY.ADP_NO";
        IBS.READ(SCCGWA, DDRADMN, this, DDTADMN_RD);
    }
    public void R000_READ_DDTADMN() throws IOException,SQLException,Exception {
        DDTADMN_RD = new DBParm();
        DDTADMN_RD.TableName = "DDTADMN";
        IBS.READ(SCCGWA, DDRADMN, DDTADMN_RD);
    }
    public void R000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void R000_READ_DDTCCY_BY_CUSAC() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
    }
    public void R000_READ_DDTMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void T000_REWRITE_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.REWRITE(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void S00_CALL_BPZUINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-INTR-INQ", BPCUINTI);
        CEP.TRC(SCCGWA, BPCUINTI.RC.RC_CODE);
        if (BPCUINTI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUINTI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
    }
    public void S000_CALL_DDZIMCYY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-ZIMCYY-AC", DDCIMCYY);
        CEP.TRC(SCCGWA, DDCIMCYY.RC);
        if (DDCIMCYY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIMCYY.RC);
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
