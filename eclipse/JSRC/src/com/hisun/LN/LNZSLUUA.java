package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSLUUA {
    int JIBS_tmp_int;
    LNZSLUUA_WS_DATA_OUTPUT WS_DATA_OUTPUT;
    DBParm LNTCONT_RD;
    DBParm LNTCLNO_RD;
    DBParm LNTAPRD_RD;
    DBParm LNTMGSG_RD;
    brParm LNTMGSG_BR = new brParm();
    brParm LNTSETL_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String SCSSCKDT = "SCSSCKDT";
    LNZSLUUA_WS_VARIABLES WS_VARIABLES = new LNZSLUUA_WS_VARIABLES();
    LNZSLUUA_WS_BROWSE_OUTPUT WS_BROWSE_OUTPUT = new LNZSLUUA_WS_BROWSE_OUTPUT();
    LNCMSG_ERROR_MSG ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGBPA_BP_AREA BP_AREA = new SCCGBPA_BP_AREA();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    LNRMGSG LNRMGSG = new LNRMGSG();
    LNCRMGSG LNCRMGSG = new LNCRMGSG();
    LNRRELA LNRRELA = new LNRRELA();
    CICCUST CICCUST = new CICCUST();
    CICACCU CICACCU = new CICACCU();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    LNRLOAN LNRLOAN = new LNRLOAN();
    LNRICTL LNRICTL = new LNRICTL();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNRCONT LNRCONT = new LNRCONT();
    LNRAPRD LNRAPRD = new LNRAPRD();
    SCCCKDT SCCCKDT = new SCCCKDT();
    LNRCLNO LNRCLNO = new LNRCLNO();
    CICQACRI CICQACRI = new CICQACRI();
    LNRSETL LNRSETL = new LNRSETL();
    LNRMGSG LNRMGSG = new LNRMGSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPNHIS_WS_DB_VARS WS_DB_VARS = new BPCPNHIS_WS_DB_VARS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGAPV SCCGAPV;
    LNCSLUUA LNCSLUUA;
    public void MP(SCCGWA SCCGWA, LNCSLUUA LNCSLUUA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSLUUA = LNCSLUUA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSLUUA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) SC_AREA.APVL_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, LNRLOAN);
        LNCRMGSG.RC.RC_MMO = "LN";
        LNCRMGSG.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_PROC();
        if (pgmRtn) return;
        if (LNCSLUUA.FUNC == 'N') {
            B010_CANCEL_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCSLUUA.FUNC == 'C') {
            B020_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCSLUUA.FUNC == 'U') {
            B030_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCSLUUA.FUNC == 'D') {
            B040_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCSLUUA.FUNC == 'B') {
            B050_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (LNCSLUUA.FUNC == 'I') {
            B050_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + LNCSLUUA.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_PROC() throws IOException,SQLException,Exception {
        if (LNCSLUUA.FUNC == 'C' 
            || LNCSLUUA.FUNC == 'U') {
            IBS.init(SCCGWA, LNRCONT);
            LNRCONT.KEY.CONTRACT_NO = LNCSLUUA.LN_ACNO;
            T000_READ_LNTCONT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNRCONT.BOOK_BR);
            IBS.init(SCCGWA, LNRCLNO);
            LNRCLNO.KEY.CL_DOMI_BR = LNRCONT.BOOK_BR;
            T000_READ_LNTCLNO();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNRCONT.CCY);
            if (!LNRCONT.CCY.equalsIgnoreCase("344")) {
                CEP.ERR(SCCGWA, ERROR_MSG.LN_NOT_ALLOW_CCY);
            }
            CEP.TRC(SCCGWA, LNCSLUUA.CCY);
            if (!LNCSLUUA.CCY.equalsIgnoreCase(LNRCONT.CCY)) {
                CEP.ERR(SCCGWA, ERROR_MSG.LN_NOT_ALLOW_CCY);
            }
            IBS.init(SCCGWA, LNRAPRD);
            LNRAPRD.KEY.CONTRACT_NO = LNCSLUUA.LN_ACNO;
            T000_READ_LNTAPRD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNRAPRD.PAY_MTH);
            CEP.TRC(SCCGWA, LNRAPRD.INST_MTH);
            CEP.TRC(SCCGWA, LNRAPRD.BPAYP_PERD);
            CEP.TRC(SCCGWA, LNRAPRD.PAYP_PERD_UNIT);
            CEP.TRC(SCCGWA, LNRAPRD.PAYP_PERD);
            if (LNRAPRD.PAY_MTH != '4' 
                && LNRAPRD.INST_MTH != '1' 
                && LNRAPRD.BPAYP_PERD != '4' 
                && LNRAPRD.PAYP_PERD_UNIT != 'M' 
                && LNRAPRD.PAYP_PERD != 1) {
                CEP.ERR(SCCGWA, ERROR_MSG.LN_NOT_ALLOW_SIGN);
            }
            CEP.TRC(SCCGWA, LNCSLUUA.VAL_DT);
            if (LNCSLUUA.VAL_DT != 0) {
                IBS.init(SCCGWA, SCCCKDT);
                SCCCKDT.DATE = LNCSLUUA.VAL_DT;
                SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
                SCSSCKDT1.MP(SCCGWA, SCCCKDT);
                if (SCCCKDT.RC != 0) {
                    CEP.ERR(SCCGWA, ERROR_MSG.LN_ERR_DATE);
                }
            } else {
                LNCSLUUA.VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
            }
            CEP.TRC(SCCGWA, LNCSLUUA.DUE_DT);
            if (LNCSLUUA.DUE_DT != 0) {
                IBS.init(SCCGWA, SCCCKDT);
                SCCCKDT.DATE = LNCSLUUA.DUE_DT;
                SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
                SCSSCKDT2.MP(SCCGWA, SCCCKDT);
                if (SCCCKDT.RC != 0) {
                    CEP.ERR(SCCGWA, ERROR_MSG.LN_ERR_DATE);
                }
            } else {
                CEP.ERR(SCCGWA, ERROR_MSG.LN_ERR_MUST_I_MATU_DT);
            }
            CEP.TRC(SCCGWA, LNCSLUUA.P_ACNO);
            CEP.TRC(SCCGWA, LNCSLUUA.ACNO);
            if (LNCSLUUA.P_ACNO.trim().length() == 0 
                || LNCSLUUA.ACNO.trim().length() == 0) {
                CEP.ERR(SCCGWA, ERROR_MSG.LN_ERR_AC_MUST_INPUT);
            }
            CEP.TRC(SCCGWA, LNCSLUUA.P_AC_CS);
            if (LNCSLUUA.P_AC_CS != '1' 
                && LNCSLUUA.P_AC_CS != '2') {
                CEP.ERR(SCCGWA, ERROR_MSG.LN_CCY_TYPE_ERROR);
            }
            CEP.TRC(SCCGWA, LNCSLUUA.P_ACNM);
            CEP.TRC(SCCGWA, LNCSLUUA.ACNM);
            if (LNCSLUUA.P_ACNM.trim().length() == 0 
                || LNCSLUUA.ACNM.trim().length() == 0) {
                CEP.ERR(SCCGWA, ERROR_MSG.LN_ACNAME_ERROR);
            }
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = LNCSLUUA.P_ACNO;
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            IBS.init(SCCGWA, LNRSETL);
            LNRSETL.KEY.CONTRACT_NO = LNCSLUUA.LN_ACNO;
            LNRSETL.KEY.CCY = LNCSLUUA.CCY;
            LNRSETL.KEY.SETTLE_TYPE = '2';
            T000_STARTBR_LNTSETL();
            if (pgmRtn) return;
            T000_READNEXT_LNTSETL();
            if (pgmRtn) return;
            while (WS_VARIABLES.LNTSETL_FOUNG_FLG != 'N' 
                && WS_VARIABLES.P_ACNO_CHECK_FLG != 'Y') {
                CEP.TRC(SCCGWA, LNRSETL.AC);
                CEP.TRC(SCCGWA, LNRSETL.CCY_TYP);
                if (LNRSETL.AC.equalsIgnoreCase(LNCSLUUA.P_ACNO) 
                    && LNRSETL.CCY_TYP == LNCSLUUA.P_AC_CS) {
                    WS_VARIABLES.P_ACNO_CHECK_FLG = 'Y';
                } else {
                    WS_VARIABLES.P_ACNO_CHECK_FLG = 'N';
                }
                T000_READNEXT_LNTSETL();
                if (pgmRtn) return;
            }
            T000_ENDBR_LNTSETL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_VARIABLES.P_ACNO_CHECK_FLG);
            if (WS_VARIABLES.P_ACNO_CHECK_FLG != 'Y') {
                CEP.ERR(SCCGWA, ERROR_MSG.LN_P_AC_ERROR);
            }
            CEP.TRC(SCCGWA, LNCSLUUA.M_FLG);
            if (LNCSLUUA.M_FLG != 'Y' 
                && LNCSLUUA.M_FLG != 'N') {
                CEP.ERR(SCCGWA, ERROR_MSG.LN_UPDATE_AMT_FLG_ERROR);
            }
            CEP.TRC(SCCGWA, LNCSLUUA.TERM_BAL);
            if (LNCSLUUA.TERM_BAL == 0 
                && LNCSLUUA.M_FLG != 'Y') {
                CEP.ERR(SCCGWA, ERROR_MSG.LN_TERM_BAL_MUST_INPUT);
            }
        }
    }
    public void B050_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRMGSG);
        IBS.init(SCCGWA, LNCRMGSG);
        LNCRMGSG.FUNC = 'I';
        LNRMGSG.KEY.SG_SEQNO = LNCSLUUA.SG_SEQNO;
        LNRMGSG.KEY.LN_ACNO = LNCSLUUA.LN_ACNO;
        S000_CALL_LNZRMGSG();
        if (pgmRtn) return;
        if (LNCRMGSG.RETURN_INFO == 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_MGSG_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B300_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CANCEL_RECORD_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_CANCEL_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNRMGSG);
        LNCRMGSG.FUNC = 'R';
        LNRMGSG.KEY.LN_ACNO = LNCSLUUA.LN_ACNO;
        LNRMGSG.KEY.SG_SEQNO = LNCSLUUA.SG_SEQNO;
        S000_CALL_LNZRMGSG();
        if (pgmRtn) return;
        LNCRMGSG.FUNC = 'U';
        LNRMGSG.SG_STS = '1';
        S000_CALL_LNZRMGSG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.FMT_ID = "LNRMGSG";
        BPCPNHIS.INFO.TX_RMK = "CANCEL LNRMGSG";
        BPCPNHIS.INFO.AC = LNCSLUUA.ACNO;
        BPCPNHIS.INFO.FMT_ID_LEN = 108961;
        BPCPNHIS.INFO.NEW_DAT_PT = LNRMGSG;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B020_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        B020_CHECK_ADD_DATA();
        if (pgmRtn) return;
        WS_DB_VARS.MGSG_SEQ += 1;
        LNCSLUUA.SG_SEQNO = WS_DB_VARS.MGSG_SEQ;
        CEP.TRC(SCCGWA, LNCSLUUA.SG_SEQNO);
        IBS.init(SCCGWA, LNRMGSG);
        IBS.init(SCCGWA, LNCRMGSG);
        LNCRMGSG.FUNC = 'A';
        LNRMGSG.RM_STSW = "" + 0;
        JIBS_tmp_int = LNRMGSG.RM_STSW.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNRMGSG.RM_STSW = "0" + LNRMGSG.RM_STSW;
        LNRMGSG.MG_BUSI_KND = LNCSLUUA.BUSI_KND;
        LNRMGSG.CCY = LNCSLUUA.CCY;
        LNRMGSG.KEY.LN_ACNO = LNCSLUUA.LN_ACNO;
        LNRMGSG.KEY.SG_SEQNO = LNCSLUUA.SG_SEQNO;
        LNRMGSG.MG_DCL_TYP = LNCSLUUA.DCL_TYP;
        LNRMGSG.LN_NM = LNCSLUUA.LN_NM;
        LNRMGSG.VAL_DT = LNCSLUUA.VAL_DT;
        LNRMGSG.LN_BAL = LNCSLUUA.BAL;
        LNRMGSG.LN_P_ACNO = LNCSLUUA.P_ACNO;
        LNRMGSG.LN_P_AC_CS = LNCSLUUA.P_AC_CS;
        LNRMGSG.LN_P_ACNM = LNCSLUUA.P_ACNM;
        LNRMGSG.RM_ACNO = LNCSLUUA.ACNO;
        LNRMGSG.RM_ACNM = LNCSLUUA.ACNM;
        LNRMGSG.RM_F_DT = LNCSLUUA.F_DT;
        LNRMGSG.RM_SP_DAY = LNCSLUUA.SP_DAY;
        LNRMGSG.RM_DUE_DT = LNCSLUUA.DUE_DT;
        LNRMGSG.RM_MON = LNCSLUUA.MON;
        LNRMGSG.RM_M_FLG = LNCSLUUA.M_FLG;
        LNRMGSG.RM_STOP_MONTH = LNCSLUUA.STOP_MTH;
        LNRMGSG.RM_TERM_TOP_BAL = LNCSLUUA.TERM_BAL;
        LNRMGSG.RM_ONCE_TOP_BAL = LNCSLUUA.ONCE_BAL;
        LNRMGSG.REMARK = LNCSLUUA.REMARK;
        if (LNRAPRD.GRACE_TYP == '2') {
            if (LNRAPRD.PRIN_DOG < 9) {
                LNRMGSG.RM_TRY_TM = (short) LNRAPRD.PRIN_DOG;
                LNRMGSG.RM_TRY_TM += 1;
            } else {
                LNRMGSG.RM_TRY_TM = 9;
            }
        } else {
            LNRMGSG.RM_TRY_TM = 1;
        }
        LNRMGSG.SG_STS = '0';
        S000_CALL_LNZRMGSG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.FMT_ID = "LNRMGSG";
        BPCPNHIS.INFO.TX_RMK = "ADD LNRMGSG";
        BPCPNHIS.INFO.AC = LNCSLUUA.ACNO;
        BPCPNHIS.INFO.FMT_ID_LEN = 108961;
        BPCPNHIS.INFO.NEW_DAT_PT = LNRMGSG;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        B030_CHECK_UPDATE_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNRMGSG);
        IBS.init(SCCGWA, LNCRMGSG);
        LNCRMGSG.FUNC = 'R';
        LNRMGSG.KEY.LN_ACNO = LNCSLUUA.LN_ACNO;
        LNRMGSG.KEY.SG_SEQNO = LNCSLUUA.SG_SEQNO;
        S000_CALL_LNZRMGSG();
        if (pgmRtn) return;
        WS_VARIABLES.MGSG_CRT_DATE = LNRMGSG.CRT_DATE;
        WS_VARIABLES.MGSG_CRT_TLR = LNRMGSG.CRT_TLR;
        IBS.CLONE(SCCGWA, LNRMGSG, LNRMGSG);
        LNCRMGSG.FUNC = 'U';
        LNRMGSG.MG_BUSI_KND = LNCSLUUA.BUSI_KND;
        LNRMGSG.CCY = LNCSLUUA.CCY;
        LNRMGSG.KEY.LN_ACNO = LNCSLUUA.LN_ACNO;
        LNRMGSG.KEY.SG_SEQNO = LNCSLUUA.SG_SEQNO;
        LNRMGSG.MG_DCL_TYP = LNCSLUUA.DCL_TYP;
        LNRMGSG.LN_NM = LNCSLUUA.LN_NM;
        LNRMGSG.VAL_DT = LNCSLUUA.VAL_DT;
        LNRMGSG.LN_BAL = LNCSLUUA.BAL;
        LNRMGSG.LN_P_ACNO = LNCSLUUA.P_ACNO;
        LNRMGSG.LN_P_AC_CS = LNCSLUUA.P_AC_CS;
        LNRMGSG.LN_P_ACNM = LNCSLUUA.P_ACNM;
        LNRMGSG.RM_ACNO = LNCSLUUA.ACNO;
        LNRMGSG.RM_ACNM = LNCSLUUA.ACNM;
        LNRMGSG.RM_F_DT = LNCSLUUA.F_DT;
        LNRMGSG.RM_SP_DAY = LNCSLUUA.SP_DAY;
        LNRMGSG.RM_DUE_DT = LNCSLUUA.DUE_DT;
        LNRMGSG.RM_MON = LNCSLUUA.MON;
        LNRMGSG.RM_M_FLG = LNCSLUUA.M_FLG;
        LNRMGSG.RM_STOP_MONTH = LNCSLUUA.STOP_MTH;
        LNRMGSG.RM_TERM_TOP_BAL = LNCSLUUA.TERM_BAL;
        LNRMGSG.RM_ONCE_TOP_BAL = LNCSLUUA.ONCE_BAL;
        LNRMGSG.REMARK = LNCSLUUA.REMARK;
        LNRMGSG.RM_TRY_TM = LNCSLUUA.TRY_TM;
        LNRMGSG.SG_STS = '0';
        LNRMGSG.CRT_DATE = WS_VARIABLES.MGSG_CRT_DATE;
        LNRMGSG.CRT_TLR = WS_VARIABLES.MGSG_CRT_TLR;
        S000_CALL_LNZRMGSG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.FMT_ID = "LNRMGSG";
        BPCPNHIS.INFO.TX_RMK = "MODIFY LNRMGSG";
        BPCPNHIS.INFO.AC = LNCSLUUA.ACNO;
        BPCPNHIS.INFO.FMT_ID_LEN = 108961;
        BPCPNHIS.INFO.OLD_DAT_PT = LNRMGSG;
        BPCPNHIS.INFO.NEW_DAT_PT = LNRMGSG;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B040_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        B040_CHECK_DELETE_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNRMGSG);
        IBS.init(SCCGWA, LNCRMGSG);
        LNCRMGSG.FUNC = 'R';
        LNRMGSG.KEY.LN_ACNO = LNCSLUUA.LN_ACNO;
        LNRMGSG.KEY.SG_SEQNO = LNCSLUUA.SG_SEQNO;
        S000_CALL_LNZRMGSG();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, LNRMGSG, LNRMGSG);
        LNCRMGSG.FUNC = 'U';
        LNRMGSG.SG_STS = '2';
        S000_CALL_LNZRMGSG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.FMT_ID = "LNRMGSG";
        BPCPNHIS.INFO.TX_RMK = "DELETE LNRMGSG";
        BPCPNHIS.INFO.AC = LNCSLUUA.ACNO;
        BPCPNHIS.INFO.FMT_ID_LEN = 108961;
        BPCPNHIS.INFO.OLD_DAT_PT = LNRMGSG;
        BPCPNHIS.INFO.NEW_DAT_PT = LNRMGSG;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_BROWSE_OUTPUT.WS_OUT_HEAD);
        if (LNCSLUUA.PAGE_NUM == 0) {
            WS_VARIABLES.WS_DATE.CURR_PAGE = 1;
        } else {
            WS_VARIABLES.WS_DATE.CURR_PAGE = (short) LNCSLUUA.PAGE_NUM;
        }
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_CURR_PAGE = WS_VARIABLES.WS_DATE.CURR_PAGE;
        WS_VARIABLES.WS_DATE.LAST_PAGE = 'N';
        WS_VARIABLES.WS_DATE.PAGE_ROW = (short) LNCSLUUA.PAGE_ROW;
        WS_DATA_OUTPUT = new LNZSLUUA_WS_DATA_OUTPUT();
        WS_BROWSE_OUTPUT.WS_DATA_OUTPUT.add(WS_DATA_OUTPUT);
        WS_VARIABLES.WS_DATE.PAGE_ROW = 10;
        WS_DATA_OUTPUT = new LNZSLUUA_WS_DATA_OUTPUT();
        WS_BROWSE_OUTPUT.WS_DATA_OUTPUT.add(WS_DATA_OUTPUT);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATE.CURR_PAGE);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATE.PAGE_ROW);
        WS_VARIABLES.WS_DATE.NEXT_START_NUM = ( ( WS_VARIABLES.WS_DATE.CURR_PAGE - 1 ) * WS_VARIABLES.WS_DATE.PAGE_ROW ) + 1;
        WS_DB_VARS.START_NUM = WS_VARIABLES.WS_DATE.NEXT_START_NUM;
        CEP.TRC(SCCGWA, WS_DB_VARS.START_NUM);
        B050_CHECK_BROWSE_DATA();
        if (pgmRtn) return;
        if (LNCSLUUA.SG_SEQNO == 0 
            && LNCSLUUA.LN_ACNO.trim().length() == 0) {
            B050_BROWSE_RECORD_BY_ALL();
            if (pgmRtn) return;
        }
        if (LNCSLUUA.SG_SEQNO != 0 
            && LNCSLUUA.LN_ACNO.trim().length() > 0) {
            B050_BROWSE_RECORD_BY_KEY();
            if (pgmRtn) return;
        }
        if (LNCSLUUA.LN_ACNO.trim().length() > 0 
            && LNCSLUUA.SG_SEQNO == 0) {
            B050_BROWSE_RECORD_BY_LN_ACNO();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_CANCEL_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRMGSG);
        IBS.init(SCCGWA, LNCRMGSG);
        LNCRMGSG.FUNC = 'I';
        LNRMGSG.KEY.SG_SEQNO = LNCSLUUA.SG_SEQNO;
        LNRMGSG.KEY.LN_ACNO = LNCSLUUA.LN_ACNO;
        S000_CALL_LNZRMGSG();
        if (pgmRtn) return;
        if (LNCRMGSG.RETURN_INFO == 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_MGSG_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_CHECK_ADD_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRMGSG);
        LNRMGSG.KEY.LN_ACNO = LNCSLUUA.LN_ACNO;
        T000_GROUP_LNTMGSG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_DB_VARS.MGSG_SEQ);
    }
    public void B030_CHECK_UPDATE_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRMGSG);
        IBS.init(SCCGWA, LNCRMGSG);
        LNCRMGSG.FUNC = 'I';
        LNRMGSG.KEY.SG_SEQNO = LNCSLUUA.SG_SEQNO;
        LNRMGSG.KEY.LN_ACNO = LNCSLUUA.LN_ACNO;
        S000_CALL_LNZRMGSG();
        if (pgmRtn) return;
        if (LNCRMGSG.RETURN_INFO == 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_MGSG_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_CHECK_DELETE_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRMGSG);
        IBS.init(SCCGWA, LNRMGSG);
        LNCRMGSG.FUNC = 'I';
        LNRMGSG.KEY.SG_SEQNO = LNCSLUUA.SG_SEQNO;
        LNRMGSG.KEY.LN_ACNO = LNCSLUUA.LN_ACNO;
        S000_CALL_LNZRMGSG();
        if (pgmRtn) return;
        if (LNCRMGSG.RETURN_INFO == 'N') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.LN_ERR_MGSG_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_CHECK_BROWSE_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCSLUUA.LN_ACNO;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_RECORD_BY_ALL() throws IOException,SQLException,Exception {
        T000_STARTBR_PROC_BY_ALL();
        if (pgmRtn) return;
        T000_READNEXT_PROC();
        if (pgmRtn) return;
        if (WS_VARIABLES.REC_MGSG_FLG != 'N') {
            WS_VARIABLES.WS_DATE.IDX = 0;
            WS_VARIABLES.WS_DATE.TOTAL_NUM = 0;
            for (WS_VARIABLES.WS_DATE.IDX = 1; WS_VARIABLES.REC_MGSG_FLG != 'N' 
                && (WS_VARIABLES.WS_DATE.IDX <= WS_VARIABLES.WS_DATE.PAGE_ROW); WS_VARIABLES.WS_DATE.IDX += 1) {
                B300_OUTPUT_MPAG_PROCESS();
                if (pgmRtn) return;
                WS_DB_VARS.START_NUM += 1;
                T000_READNEXT_PROC();
                if (pgmRtn) return;
            }
            if (WS_VARIABLES.REC_MGSG_FLG == 'N') {
                WS_VARIABLES.WS_DATE.TOTAL_PAGE = WS_VARIABLES.WS_DATE.CURR_PAGE;
                WS_VARIABLES.WS_DATE.BAL_CNT = WS_VARIABLES.WS_DATE.IDX - 1;
                WS_VARIABLES.WS_DATE.TOTAL_NUM = ( WS_VARIABLES.WS_DATE.CURR_PAGE - 1 ) * WS_VARIABLES.WS_DATE.PAGE_ROW + WS_VARIABLES.WS_DATE.BAL_CNT;
                WS_VARIABLES.WS_DATE.LAST_PAGE = 'Y';
                WS_VARIABLES.WS_DATE.PAGE_ROW = (short) WS_VARIABLES.WS_DATE.BAL_CNT;
                WS_DATA_OUTPUT = new LNZSLUUA_WS_DATA_OUTPUT();
                WS_BROWSE_OUTPUT.WS_DATA_OUTPUT.add(WS_DATA_OUTPUT);
            } else {
                R000_GROUP_ALL();
                if (pgmRtn) return;
                WS_VARIABLES.WS_DATE.BAL_CNT = WS_VARIABLES.WS_DATE.TOTAL_NUM % WS_VARIABLES.WS_DATE.PAGE_ROW;
                WS_VARIABLES.WS_DATE.TOTAL_PAGE = (short) ((WS_VARIABLES.WS_DATE.TOTAL_NUM - WS_VARIABLES.WS_DATE.BAL_CNT) / WS_VARIABLES.WS_DATE.PAGE_ROW);
                if (WS_VARIABLES.WS_DATE.BAL_CNT != 0) {
                    WS_VARIABLES.WS_DATE.TOTAL_PAGE += 1;
                }
            }
        } else {
            WS_VARIABLES.WS_DATE.TOTAL_PAGE = 1;
            WS_VARIABLES.WS_DATE.TOTAL_NUM = 0;
            WS_VARIABLES.WS_DATE.LAST_PAGE = 'Y';
            WS_VARIABLES.WS_DATE.PAGE_ROW = 0;
            WS_DATA_OUTPUT = new LNZSLUUA_WS_DATA_OUTPUT();
            WS_BROWSE_OUTPUT.WS_DATA_OUTPUT.add(WS_DATA_OUTPUT);
        }
        T000_ENDBR_PROC();
        if (pgmRtn) return;
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_TOTAL_PAGE = WS_VARIABLES.WS_DATE.TOTAL_PAGE;
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_TOTAL_NUM = WS_VARIABLES.WS_DATE.TOTAL_NUM;
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_LAST_PAGE = WS_VARIABLES.WS_DATE.LAST_PAGE;
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_PAGE_ROW = WS_VARIABLES.WS_DATE.PAGE_ROW;
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_CURR_PAGE);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_LAST_PAGE);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_PAGE_ROW);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATE.PAGE_ROW);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "LN542";
        SCCFMT.DATA_PTR = WS_BROWSE_OUTPUT;
        SCCFMT.DATA_LEN = 8989;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B050_BROWSE_RECORD_BY_KEY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRMGSG);
        IBS.init(SCCGWA, LNRMGSG);
        LNRMGSG.KEY.SG_SEQNO = LNCSLUUA.SG_SEQNO;
        LNRMGSG.KEY.LN_ACNO = LNCSLUUA.LN_ACNO;
        LNCRMGSG.FUNC = 'I';
        S000_CALL_LNZRMGSG();
        if (pgmRtn) return;
        WS_VARIABLES.WS_DATE.PAGE_ROW = 1;
        WS_DATA_OUTPUT = new LNZSLUUA_WS_DATA_OUTPUT();
        WS_BROWSE_OUTPUT.WS_DATA_OUTPUT.add(WS_DATA_OUTPUT);
        WS_VARIABLES.WS_DATE.IDX = 1;
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_TOTAL_NUM = 1;
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_TOTAL_PAGE = 1;
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_CURR_PAGE = 1;
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_PAGE_ROW = 1;
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_LAST_PAGE = 'Y';
        B300_OUTPUT_MPAG_PROCESS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "LN542";
        SCCFMT.DATA_PTR = WS_BROWSE_OUTPUT;
        SCCFMT.DATA_LEN = 8989;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B050_BROWSE_RECORD_BY_LN_ACNO() throws IOException,SQLException,Exception {
        LNRMGSG.KEY.LN_ACNO = LNCSLUUA.LN_ACNO;
        T000_STARTBR_PROC_BY_LN_ACNO();
        if (pgmRtn) return;
        T000_READNEXT_PROC();
        if (pgmRtn) return;
        if (WS_VARIABLES.REC_MGSG_FLG != 'N') {
            WS_VARIABLES.WS_DATE.IDX = 0;
            WS_VARIABLES.WS_DATE.TOTAL_NUM = 0;
            for (WS_VARIABLES.WS_DATE.IDX = 1; WS_VARIABLES.REC_MGSG_FLG != 'N' 
                && (WS_VARIABLES.WS_DATE.IDX <= WS_VARIABLES.WS_DATE.PAGE_ROW); WS_VARIABLES.WS_DATE.IDX += 1) {
                B300_OUTPUT_MPAG_PROCESS();
                if (pgmRtn) return;
                WS_DB_VARS.START_NUM += 1;
                T000_READNEXT_PROC();
                if (pgmRtn) return;
            }
            if (WS_VARIABLES.REC_MGSG_FLG == 'N') {
                WS_VARIABLES.WS_DATE.TOTAL_PAGE = WS_VARIABLES.WS_DATE.CURR_PAGE;
                WS_VARIABLES.WS_DATE.BAL_CNT = WS_VARIABLES.WS_DATE.IDX - 1;
                WS_VARIABLES.WS_DATE.TOTAL_NUM = ( WS_VARIABLES.WS_DATE.CURR_PAGE - 1 ) * WS_VARIABLES.WS_DATE.PAGE_ROW + WS_VARIABLES.WS_DATE.BAL_CNT;
                WS_VARIABLES.WS_DATE.LAST_PAGE = 'Y';
                WS_VARIABLES.WS_DATE.PAGE_ROW = (short) WS_VARIABLES.WS_DATE.BAL_CNT;
                WS_DATA_OUTPUT = new LNZSLUUA_WS_DATA_OUTPUT();
                WS_BROWSE_OUTPUT.WS_DATA_OUTPUT.add(WS_DATA_OUTPUT);
            } else {
                R000_GROUP_ACNO();
                if (pgmRtn) return;
                WS_VARIABLES.WS_DATE.BAL_CNT = WS_VARIABLES.WS_DATE.TOTAL_NUM % WS_VARIABLES.WS_DATE.PAGE_ROW;
                WS_VARIABLES.WS_DATE.TOTAL_PAGE = (short) ((WS_VARIABLES.WS_DATE.TOTAL_NUM - WS_VARIABLES.WS_DATE.BAL_CNT) / WS_VARIABLES.WS_DATE.PAGE_ROW);
                if (WS_VARIABLES.WS_DATE.BAL_CNT != 0) {
                    WS_VARIABLES.WS_DATE.TOTAL_PAGE += 1;
                }
            }
        } else {
            WS_VARIABLES.WS_DATE.TOTAL_PAGE = 1;
            WS_VARIABLES.WS_DATE.TOTAL_NUM = 0;
            WS_VARIABLES.WS_DATE.LAST_PAGE = 'Y';
            WS_VARIABLES.WS_DATE.PAGE_ROW = 0;
            WS_DATA_OUTPUT = new LNZSLUUA_WS_DATA_OUTPUT();
            WS_BROWSE_OUTPUT.WS_DATA_OUTPUT.add(WS_DATA_OUTPUT);
        }
        T000_ENDBR_PROC();
        if (pgmRtn) return;
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_TOTAL_PAGE = WS_VARIABLES.WS_DATE.TOTAL_PAGE;
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_TOTAL_NUM = WS_VARIABLES.WS_DATE.TOTAL_NUM;
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_LAST_PAGE = WS_VARIABLES.WS_DATE.LAST_PAGE;
        WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_PAGE_ROW = WS_VARIABLES.WS_DATE.PAGE_ROW;
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_TOTAL_NUM);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_CURR_PAGE);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_LAST_PAGE);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_OUT_HEAD.O_PAGE_ROW);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "LN542";
        SCCFMT.DATA_PTR = WS_BROWSE_OUTPUT;
        SCCFMT.DATA_LEN = 8989;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B300_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        WS_VARIABLES.WS_OUTPUT.BUSI_KND = LNRMGSG.MG_BUSI_KND;
        WS_VARIABLES.WS_OUTPUT.CCY = LNRMGSG.CCY;
        WS_VARIABLES.WS_OUTPUT.LN_ACNO = LNRMGSG.KEY.LN_ACNO;
        WS_VARIABLES.WS_OUTPUT.SG_SEQNO = LNRMGSG.KEY.SG_SEQNO;
        WS_VARIABLES.WS_OUTPUT.DCL_TYP = LNRMGSG.MG_DCL_TYP;
        WS_VARIABLES.WS_OUTPUT.LN_NM = LNRMGSG.LN_NM;
        WS_VARIABLES.WS_OUTPUT.VAL_DT = LNRMGSG.VAL_DT;
        WS_VARIABLES.WS_OUTPUT.BAL = LNRMGSG.LN_BAL;
        WS_VARIABLES.WS_OUTPUT.P_ACNO = LNRMGSG.LN_P_ACNO;
        WS_VARIABLES.WS_OUTPUT.P_AC_CS = LNRMGSG.LN_P_AC_CS;
        WS_VARIABLES.WS_OUTPUT.P_ACNM = LNRMGSG.LN_P_ACNM;
        WS_VARIABLES.WS_OUTPUT.ACNO = LNRMGSG.RM_ACNO;
        WS_VARIABLES.WS_OUTPUT.ACNM = LNRMGSG.RM_ACNM;
        WS_VARIABLES.WS_OUTPUT.F_DT = LNRMGSG.RM_F_DT;
        WS_VARIABLES.WS_OUTPUT.SP_DAY = LNRMGSG.RM_SP_DAY;
        WS_VARIABLES.WS_OUTPUT.DUE_DT = LNRMGSG.RM_DUE_DT;
        WS_VARIABLES.WS_OUTPUT.MON = LNRMGSG.RM_MON;
        WS_VARIABLES.WS_OUTPUT.M_FLG = LNRMGSG.RM_M_FLG;
        WS_VARIABLES.WS_OUTPUT.STOP_MTH = LNRMGSG.RM_STOP_MONTH;
        WS_VARIABLES.WS_OUTPUT.TERM_BAL = LNRMGSG.RM_TERM_TOP_BAL;
        WS_VARIABLES.WS_OUTPUT.ONCE_BAL = LNRMGSG.RM_ONCE_TOP_BAL;
        WS_VARIABLES.WS_OUTPUT.REMARK = LNRMGSG.REMARK;
        WS_VARIABLES.WS_OUTPUT.TRY_TM = LNRMGSG.RM_TRY_TM;
        SCCFMT.FMTID = "LN541";
        SCCFMT.DATA_PTR = WS_VARIABLES.WS_OUTPUT;
        SCCFMT.DATA_LEN = 896;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B300_OUTPUT_MPAG_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_DATA_OUTPUT);
        WS_DATA_OUTPUT.B_BUSI_KND = LNRMGSG.MG_BUSI_KND;
        WS_DATA_OUTPUT.B_CCY = LNRMGSG.CCY;
        WS_DATA_OUTPUT.B_LN_ACNO = LNRMGSG.KEY.LN_ACNO;
        WS_DATA_OUTPUT.B_SG_SEQNO = LNRMGSG.KEY.SG_SEQNO;
        WS_DATA_OUTPUT.B_DCL_TYP = LNRMGSG.MG_DCL_TYP;
        WS_DATA_OUTPUT.B_LN_NM = LNRMGSG.LN_NM;
        WS_DATA_OUTPUT.B_VAL_DT = LNRMGSG.VAL_DT;
        WS_DATA_OUTPUT.B_BAL = LNRMGSG.LN_BAL;
        WS_DATA_OUTPUT.B_P_ACNO = LNRMGSG.LN_P_ACNO;
        WS_DATA_OUTPUT.B_P_AC_CS = LNRMGSG.LN_P_AC_CS;
        WS_DATA_OUTPUT.B_P_ACNM = LNRMGSG.LN_P_ACNM;
        WS_DATA_OUTPUT.B_ACNO = LNRMGSG.RM_ACNO;
        WS_DATA_OUTPUT.B_ACNM = LNRMGSG.RM_ACNM;
        WS_DATA_OUTPUT.B_F_DT = LNRMGSG.RM_F_DT;
        WS_DATA_OUTPUT.B_SP_DAY = LNRMGSG.RM_SP_DAY;
        WS_DATA_OUTPUT.B_DUE_DT = LNRMGSG.RM_DUE_DT;
        WS_DATA_OUTPUT.B_MON = LNRMGSG.RM_MON;
        WS_DATA_OUTPUT.B_M_FLG = LNRMGSG.RM_M_FLG;
        WS_DATA_OUTPUT.B_STOP_MTH = LNRMGSG.RM_STOP_MONTH;
        WS_DATA_OUTPUT.B_TERM_BAL = LNRMGSG.RM_TERM_TOP_BAL;
        WS_DATA_OUTPUT.B_ONCE_BAL = LNRMGSG.RM_ONCE_TOP_BAL;
        WS_DATA_OUTPUT.B_SG_STS = LNRMGSG.SG_STS;
        WS_DATA_OUTPUT.B_REMARK = LNRMGSG.REMARK;
        WS_DATA_OUTPUT.B_TRY_TM = LNRMGSG.RM_TRY_TM;
    }
    public void T000_READ_LNTCONT() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        LNTCONT_RD.where = "CONTRACT_NO = :LNRCONT.KEY.CONTRACT_NO";
        IBS.READ(SCCGWA, LNRCONT, this, LNTCONT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, ERROR_MSG.LN_ERR_CONT_NOTFND);
        }
    }
    public void T000_READ_LNTCLNO() throws IOException,SQLException,Exception {
        LNTCLNO_RD = new DBParm();
        LNTCLNO_RD.TableName = "LNTCLNO";
        LNTCLNO_RD.where = "CL_DOMI_BR = :LNRCLNO.KEY.CL_DOMI_BR";
        LNTCLNO_RD.fst = true;
        IBS.READ(SCCGWA, LNRCLNO, this, LNTCLNO_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, ERROR_MSG.LN_MAINTAIN_ADVANCE);
        }
    }
    public void T000_READ_LNTAPRD() throws IOException,SQLException,Exception {
        LNTAPRD_RD = new DBParm();
        LNTAPRD_RD.TableName = "LNTAPRD";
        LNTAPRD_RD.where = "CONTRACT_NO = :LNRAPRD.KEY.CONTRACT_NO";
        IBS.READ(SCCGWA, LNRAPRD, this, LNTAPRD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, ERROR_MSG.LN_ERR_APRD_NOTFND);
        }
    }
    public void T000_GROUP_LNTMGSG() throws IOException,SQLException,Exception {
        LNTMGSG_RD = new DBParm();
        LNTMGSG_RD.TableName = "LNTMGSG";
        LNTMGSG_RD.set = "WS-MGSG-SEQ=IFNULL(MAX(SG_SEQNO),0)";
        LNTMGSG_RD.where = "LN_ACNO = :LNRMGSG.KEY.LN_ACNO";
        IBS.GROUP(SCCGWA, LNRMGSG, this, LNTMGSG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '3') {
            CEP.ERR(SCCGWA, ERROR_MSG.LN_ERR_OTHER);
        }
    }
    public void R000_GROUP_ALL() throws IOException,SQLException,Exception {
        T000_02_GROUP_REC_PROC();
        if (pgmRtn) return;
        WS_VARIABLES.WS_DATE.TOTAL_NUM = WS_DB_VARS.CNT;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATE.TOTAL_NUM);
    }
    public void R000_GROUP_ACNO() throws IOException,SQLException,Exception {
        T000_02_GROUP_REC_PROC_ACNO();
        if (pgmRtn) return;
        WS_VARIABLES.WS_DATE.TOTAL_NUM = WS_DB_VARS.CNT;
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_DATE.TOTAL_NUM);
    }
    public void T000_STARTBR_PROC_BY_ALL() throws IOException,SQLException,Exception {
        LNTMGSG_BR.rp = new DBParm();
        LNTMGSG_BR.rp.TableName = "LNTMGSG";
        LNTMGSG_BR.rp.order = "SG_SEQNO";
        IBS.STARTBR(SCCGWA, LNRMGSG, LNTMGSG_BR);
    }
    public void T000_STARTBR_PROC_BY_LN_ACNO() throws IOException,SQLException,Exception {
        LNTMGSG_BR.rp = new DBParm();
        LNTMGSG_BR.rp.TableName = "LNTMGSG";
        LNTMGSG_BR.rp.where = "LN_ACNO = :LNRMGSG.KEY.LN_ACNO";
        LNTMGSG_BR.rp.order = "SG_SEQNO";
        IBS.STARTBR(SCCGWA, LNRMGSG, this, LNTMGSG_BR);
    }
    public void T000_02_GROUP_REC_PROC() throws IOException,SQLException,Exception {
        LNTMGSG_RD = new DBParm();
        LNTMGSG_RD.TableName = "LNTMGSG";
        LNTMGSG_RD.set = "WS-CNT=COUNT(*)";
        IBS.GROUP(SCCGWA, LNRMGSG, this, LNTMGSG_RD);
    }
    public void T000_02_GROUP_REC_PROC_ACNO() throws IOException,SQLException,Exception {
        LNTMGSG_RD = new DBParm();
        LNTMGSG_RD.TableName = "LNTMGSG";
        LNTMGSG_RD.where = "LN_ACNO = :LNRMGSG.KEY.LN_ACNO";
        LNTMGSG_RD.set = "WS-CNT=COUNT(*)";
        IBS.GROUP(SCCGWA, LNRMGSG, this, LNTMGSG_RD);
    }
    public void T000_READNEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRMGSG, this, LNTMGSG_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.REC_MGSG_FLG = 'N';
        }
    }
    public void T000_ENDBR_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTMGSG_BR);
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCCONTM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRMGSG() throws IOException,SQLException,Exception {
        LNCRMGSG.REC_PTR = LNRMGSG;
        LNCRMGSG.REC_LEN = 108961;
        CEP.TRC(SCCGWA, LNRMGSG);
        CEP.TRC(SCCGWA, LNCRMGSG);
        IBS.CALLCPN(SCCGWA, "LN-R-MGSG-MAIN", LNCRMGSG);
        if (LNCRMGSG.RETURN_INFO != 'F' 
            && LNCRMGSG.RETURN_INFO != 'E' 
            && LNCRMGSG.RETURN_INFO != 'N') {
            WS_VARIABLES.ERR_MSG = "" + LNCRMGSG.RC.RC_CODE;
            JIBS_tmp_int = WS_VARIABLES.ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_VARIABLES.ERR_MSG = "0" + WS_VARIABLES.ERR_MSG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void T000_STARTBR_LNTSETL() throws IOException,SQLException,Exception {
        LNTSETL_BR.rp = new DBParm();
        LNTSETL_BR.rp.TableName = "LNTSETL";
        LNTSETL_BR.rp.where = "CONTRACT_NO = :LNRSETL.KEY.CONTRACT_NO "
            + "AND CCY = :LNRSETL.KEY.CCY "
            + "AND SETTLE_TYPE = :LNRSETL.KEY.SETTLE_TYPE";
        IBS.STARTBR(SCCGWA, LNRSETL, this, LNTSETL_BR);
    }
    public void T000_READNEXT_LNTSETL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRSETL, this, LNTSETL_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.LNTSETL_FOUNG_FLG = 'N';
        } else {
            WS_VARIABLES.LNTSETL_FOUNG_FLG = 'Y';
        }
    }
    public void T000_ENDBR_LNTSETL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTSETL_BR);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
    } //FROM #ENDIF
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
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
