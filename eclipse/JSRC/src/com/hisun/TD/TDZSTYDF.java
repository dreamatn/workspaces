package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.DC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDZSTYDF {
    int JIBS_tmp_int;
    DBParm TDTSMST_RD;
    DBParm TDTAINT_RD;
    DBParm TDTIREV_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    String WS_AC = " ";
    String WS_REMARK = " ";
    TDZSTYDF_REDEFINES5 REDEFINES5 = new TDZSTYDF_REDEFINES5();
    TDZSTYDF_WS_OUTPUT_INFO WS_OUTPUT_INFO = new TDZSTYDF_WS_OUTPUT_INFO();
    int WS_TEMP_VAL_DT = 0;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    TDRSMST TDRSMST = new TDRSMST();
    DCCUQSAC DCCUQSAC = new DCCUQSAC();
    TDCHTYDF TDCNTYDF = new TDCHTYDF();
    TDCHTYDF TDCOTYDF = new TDCHTYDF();
    TDRIREV TDRIREV = new TDRIREV();
    TDRIREV TDROIREV = new TDRIREV();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCCKDT SCCCKDT = new SCCCKDT();
    TDRAINT TDRAINT = new TDRAINT();
    TDCSOYDF TDCSOYDF = new TDCSOYDF();
    CICQACAC CICQACAC = new CICQACAC();
    int WS_IREV_CNT = 0;
    SCCGWA SCCGWA;
    TDCSTYDF TDCSTYDF;
    public void MP(SCCGWA SCCGWA, TDCSTYDF TDCSTYDF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCSTYDF = TDCSTYDF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZSTYDF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCSTYDF.AC_SEQ);
        CEP.TRC(SCCGWA, TDCSTYDF.RATE);
        CEP.TRC(SCCGWA, TDCSTYDF.EXP_DATE);
        B010_CHECK_INPUT_INFO();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
        if (TDCSTYDF.FUNC == '1') {
            B021_INT_ADJ_MAIN();
            if (pgmRtn) return;
        }
        if (TDCSTYDF.FUNC == '2') {
            B022_INT_ADJ_MAIN();
            if (pgmRtn) return;
        }
        if (TDCSTYDF.PRV_RAT != 0 
            || TDCSTYDF.OVE_RAT != 0) {
            IBS.init(SCCGWA, TDRAINT);
            TDRAINT.KEY.ACO_AC = WS_AC;
            T000_READUP_TDTAINT();
            if (pgmRtn) return;
            if (TDCSTYDF.PRV_RAT != 0) {
                TDRAINT.PRV_RAT = TDCSTYDF.PRV_RAT;
            }
            if (TDCSTYDF.OVE_RAT != 0) {
                TDRAINT.OVE_RAT = TDCSTYDF.OVE_RAT;
            }
            TDRAINT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            TDRAINT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            T000_REWRITE_TDTAINT();
            if (pgmRtn) return;
        }
        R000_WRITE_NHIS();
        if (pgmRtn) return;
        B030_OUTPUT_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_INFO() throws IOException,SQLException,Exception {
        if (TDCSTYDF.FUNC != '1' 
            && TDCSTYDF.FUNC != '2') {
            WS_ERR_INFO = " ";
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INVALID_IPT;
            WS_ERR_INFO = "FUNC MUST BE 1 OR 2";
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCSTYDF.MAIN_AC.trim().length() == 0) {
            WS_ERR_INFO = " ";
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCSTYDF.MAIN_AC.trim().length() > 0 
            && TDCSTYDF.AC_SEQ == 0) {
            WS_ERR_INFO = " ";
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_MAIN_AC_SEQ_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCSTYDF.MAIN_AC.trim().length() == 0 
            && TDCSTYDF.AC_SEQ != 0) {
            WS_ERR_INFO = " ";
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_MAIN_AC_SEQ_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCSTYDF.FUNC == '1' 
            && (TDCSTYDF.RATE == 0 
            || TDCSTYDF.VAL_DATE == 0)) {
            WS_ERR_INFO = " ";
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_IPT_RAT_AND_VAL_DT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCSTYDF.FUNC == '2' 
            && TDCSTYDF.EXP_DATE == 0) {
            WS_ERR_INFO = " ";
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INVALID_EXP_DT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCSTYDF.MAIN_AC.trim().length() > 0) {
            REDEFINES5.WS_RMK_AC = TDCSTYDF.MAIN_AC;
            WS_REMARK = IBS.CLS2CPY(SCCGWA, REDEFINES5);
            REDEFINES5.WS_RMK_AC_SEQ = TDCSTYDF.AC_SEQ;
            WS_REMARK = IBS.CLS2CPY(SCCGWA, REDEFINES5);
        } else {
            WS_ERR_INFO = " ";
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_MAIN_AC_SEQ_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        if (TDCSTYDF.MAIN_AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACAC);
            CEP.TRC(SCCGWA, TDCSTYDF.MAIN_AC);
            CEP.TRC(SCCGWA, TDCSTYDF.AC_SEQ);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = TDCSTYDF.MAIN_AC;
            CICQACAC.DATA.AGR_SEQ = TDCSTYDF.AC_SEQ;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
            WS_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        }
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = WS_AC;
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        T000_READUPD_TDTSMST();
        if (pgmRtn) return;
        if (!TDRSMST.PRDAC_CD.equalsIgnoreCase("038")) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_NOT_TYDF;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, TDCNTYDF);
        IBS.init(SCCGWA, TDCOTYDF);
        CEP.TRC(SCCGWA, TDRSMST.EXP_DATE);
        TDCOTYDF.AC = REDEFINES5.WS_RMK_AC;
        TDCOTYDF.AC_SEQ = REDEFINES5.WS_RMK_AC_SEQ;
        TDCOTYDF.EXP_DATE = TDRSMST.EXP_DATE;
        TDCOTYDF.VAL_DATE = TDRSMST.VAL_DATE;
        WS_TEMP_VAL_DT = TDRSMST.VAL_DATE;
        IBS.init(SCCGWA, TDRIREV);
        TDRIREV.KEY.ACO_AC = WS_AC;
        T000_COUNT_TDTIREV_ACNO();
        if (pgmRtn) return;
    }
    public void B021_INT_ADJ_MAIN() throws IOException,SQLException,Exception {
        if (WS_IREV_CNT == 1) {
            B021_INT_ADJ_MAIN_1();
            if (pgmRtn) return;
        } else {
            B021_INT_ADJ_MAIN_2();
            if (pgmRtn) return;
        }
    }
    public void B021_INT_ADJ_MAIN_1() throws IOException,SQLException,Exception {
        if (TDCSTYDF.VAL_DATE != 0 
            && TDCSTYDF.VAL_DATE != TDRSMST.VAL_DATE) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = TDCSTYDF.VAL_DATE;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0 
                || TDCSTYDF.VAL_DATE > SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INVALID_VAL_DT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            TDRSMST.PVAL_DATE = TDCSTYDF.VAL_DATE;
            TDRSMST.VAL_DATE = TDCSTYDF.VAL_DATE;
            TDCNTYDF.VAL_DATE = TDCSTYDF.VAL_DATE;
        }
        if (TDCSTYDF.EXP_DATE != 0 
            && TDCSTYDF.EXP_DATE != TDRSMST.EXP_DATE) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = TDCSTYDF.EXP_DATE;
            SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
            SCSSCKDT2.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INVALID_EXP_DT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            TDRSMST.EXP_DATE = TDCSTYDF.EXP_DATE;
        }
        if (TDRSMST.VAL_DATE >= TDRSMST.EXP_DATE) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INVALID_EXP_DT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSMST.EXP_INT = 0;
        T000_REWRITE_TDTSMST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDRIREV);
        TDRIREV.KEY.ACO_AC = WS_AC;
        TDRIREV.KEY.STR_DATE = WS_TEMP_VAL_DT;
        CEP.TRC(SCCGWA, TDRIREV.KEY.STR_DATE);
        CEP.TRC(SCCGWA, TDRIREV.KEY.ACO_AC);
        T000_READUPD_TDTIREV();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDRIREV.CON_RATE);
        TDCOTYDF.RATE = TDRIREV.CON_RATE;
        IBS.CLONE(SCCGWA, TDRIREV, TDROIREV);
        T000_DELETE_TDTIREV();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDRIREV);
        IBS.CLONE(SCCGWA, TDROIREV, TDRIREV);
        TDRIREV.KEY.STR_DATE = TDRSMST.VAL_DATE;
        TDRIREV.END_DATE = TDRSMST.EXP_DATE;
        TDRIREV.CON_RATE = TDCSTYDF.RATE;
        TDRIREV.PRD_RATE = TDCSTYDF.RATE;
        TDRIREV.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRIREV.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_WRITE_TDTIREV();
        if (pgmRtn) return;
    }
    public void B021_INT_ADJ_MAIN_2() throws IOException,SQLException,Exception {
        if (TDCSTYDF.VAL_DATE != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = TDCSTYDF.VAL_DATE;
            SCSSCKDT SCSSCKDT3 = new SCSSCKDT();
            SCSSCKDT3.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INVALID_VAL_DT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            TDCNTYDF.VAL_DATE = TDCSTYDF.VAL_DATE;
        } else {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INVALID_VAL_DT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, TDRIREV);
        TDRIREV.KEY.ACO_AC = WS_AC;
        CEP.TRC(SCCGWA, TDRIREV.KEY.STR_DATE);
        CEP.TRC(SCCGWA, TDRIREV.KEY.ACO_AC);
        T000_READ_TDTIREV_LAST();
        if (pgmRtn) return;
        if (TDCSTYDF.VAL_DATE != TDRIREV.KEY.STR_DATE) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INVALID_VAL_DT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, TDRIREV);
        TDRIREV.KEY.ACO_AC = WS_AC;
        TDRIREV.KEY.STR_DATE = TDCSTYDF.VAL_DATE;
        CEP.TRC(SCCGWA, TDRIREV.CON_RATE);
        T000_READUPD_TDTIREV();
        if (pgmRtn) return;
        TDCOTYDF.RATE = TDRIREV.CON_RATE;
        TDRIREV.CON_RATE = TDCSTYDF.RATE;
        TDRIREV.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRIREV.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_TDTIREV();
        if (pgmRtn) return;
    }
    public void B022_INT_ADJ_MAIN() throws IOException,SQLException,Exception {
        if (TDCSTYDF.EXP_DATE <= TDRSMST.EXP_DATE) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INVALID_EXP_DT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCSTYDF.EXP_DATE != 0 
            && TDCSTYDF.EXP_DATE != TDRSMST.EXP_DATE) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = TDCSTYDF.EXP_DATE;
            SCSSCKDT SCSSCKDT4 = new SCSSCKDT();
            SCSSCKDT4.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0 
                || TDCSTYDF.EXP_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INVALID_EXP_DT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            TDRSMST.EXP_DATE = TDCSTYDF.EXP_DATE;
        }
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSMST.EXP_INT = 0;
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        TDRSMST.STSW = TDRSMST.STSW.substring(0, 32 - 1) + "1" + TDRSMST.STSW.substring(32 + 1 - 1);
        T000_REWRITE_TDTSMST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDRIREV);
        TDRIREV.KEY.ACO_AC = WS_AC;
        CEP.TRC(SCCGWA, TDRIREV.KEY.ACO_AC);
        T000_READ_TDTIREV();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDRIREV.CON_RATE);
        TDCOTYDF.RATE = TDRIREV.CON_RATE;
        TDCOTYDF.VAL_DATE = TDRIREV.KEY.STR_DATE;
        if (TDRIREV.END_DATE != TDCSTYDF.EXP_DATE) {
            TDCNTYDF.RATE = TDCSTYDF.RATE;
            TDCNTYDF.VAL_DATE = TDRIREV.END_DATE;
            IBS.CLONE(SCCGWA, TDRIREV, TDROIREV);
            TDRIREV.KEY.STR_DATE = TDRIREV.END_DATE;
            TDRIREV.END_DATE = TDRSMST.EXP_DATE;
            TDRIREV.CON_RATE = TDCSTYDF.RATE;
            TDRIREV.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            TDRIREV.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            T000_WRITE_TDTIREV();
            if (pgmRtn) return;
        }
    }
    public void R000_WRITE_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.TX_TYP_CD = "P607";
        BPCPNHIS.INFO.FMT_ID = "0124160";
        BPCPNHIS.INFO.FMT_ID_LEN = 82;
        if (TDCSTYDF.FUNC == '1') {
            REDEFINES5.WS_RMK_DESC = "TYDF COMFIRM";
            WS_REMARK = IBS.CLS2CPY(SCCGWA, REDEFINES5);
        } else {
            REDEFINES5.WS_RMK_DESC = "TYDF CHANGE EXP DT ";
            WS_REMARK = IBS.CLS2CPY(SCCGWA, REDEFINES5);
        }
        BPCPNHIS.INFO.TX_RMK = WS_REMARK;
        R000_TRANS_NTYDF_DATA();
        if (pgmRtn) return;
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.OLD_DAT_PT = TDCOTYDF;
        BPCPNHIS.INFO.NEW_DAT_PT = TDCNTYDF;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_TRANS_NTYDF_DATA() throws IOException,SQLException,Exception {
        TDCNTYDF.AC = REDEFINES5.WS_RMK_AC;
        TDCNTYDF.AC_SEQ = REDEFINES5.WS_RMK_AC_SEQ;
        TDCNTYDF.RATE = TDCSTYDF.RATE;
        TDCNTYDF.EXP_DATE = TDCSTYDF.EXP_DATE;
        TDCNTYDF.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDCNTYDF.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
    }
    public void B030_OUTPUT_INFO() throws IOException,SQLException,Exception {
        TDCSOYDF.DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDCSOYDF.MAIN_AC = TDCSTYDF.MAIN_AC;
        TDCSOYDF.AC_SEQ = TDCSTYDF.AC_SEQ;
        TDCSOYDF.RATE = TDCSTYDF.RATE;
        TDCSOYDF.VAL_DATE = TDCSTYDF.VAL_DATE;
        if (TDCSTYDF.VAL_DATE == 0) {
            TDCSOYDF.VAL_DATE = TDRSMST.VAL_DATE;
        }
        TDCSOYDF.EXP_DATE = TDCSTYDF.EXP_DATE;
        if (TDCSTYDF.EXP_DATE == 0) {
            TDCSOYDF.EXP_DATE = TDRSMST.EXP_DATE;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "TD558";
        SCCFMT.DATA_PTR = TDCSOYDF;
        SCCFMT.DATA_LEN = 74;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_INFO = " ";
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_INFO = " ";
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_INFO = WS_AC;
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_INFO = WS_AC;
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.TYPE = 'D';
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = IBS.CLS2CPY(SCCGWA, TDRSMST.KEY);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTSMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READUP_TDTAINT() throws IOException,SQLException,Exception {
        TDTAINT_RD = new DBParm();
        TDTAINT_RD.TableName = "TDTAINT";
        TDTAINT_RD.upd = true;
        IBS.READ(SCCGWA, TDRAINT, TDTAINT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_INFO = WS_AC;
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_TDTAINT() throws IOException,SQLException,Exception {
        TDTAINT_RD = new DBParm();
        TDTAINT_RD.TableName = "TDTAINT";
        IBS.REWRITE(SCCGWA, TDRAINT, TDTAINT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            WS_ERR_INFO = WS_AC;
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_TDTIREV() throws IOException,SQLException,Exception {
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        TDTIREV_RD.eqWhere = "ACO_AC";
        TDTIREV_RD.where = "STR_DATE = :TDRIREV.KEY.STR_DATE";
        TDTIREV_RD.upd = true;
        IBS.READ(SCCGWA, TDRIREV, this, TDTIREV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_INFO = WS_AC;
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_IREV_NOFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTIREV_LAST() throws IOException,SQLException,Exception {
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        TDTIREV_RD.eqWhere = "ACO_AC";
        TDTIREV_RD.fst = true;
        TDTIREV_RD.order = "STR_DATE DESC";
        IBS.READ(SCCGWA, TDRIREV, TDTIREV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_INFO = WS_AC;
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_IREV_NOFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTIREV() throws IOException,SQLException,Exception {
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        TDTIREV_RD.eqWhere = "ACO_AC";
        TDTIREV_RD.fst = true;
        TDTIREV_RD.order = "STR_DATE DESC";
        IBS.READ(SCCGWA, TDRIREV, TDTIREV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_INFO = WS_AC;
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_IREV_NOFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_TDTIREV() throws IOException,SQLException,Exception {
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        IBS.WRITE(SCCGWA, TDRIREV, TDTIREV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_INFO = WS_AC;
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_IREV_NOFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.TYPE = 'D';
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = IBS.CLS2CPY(SCCGWA, TDRIREV.KEY);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTIREV";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_DELETE_TDTIREV() throws IOException,SQLException,Exception {
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        IBS.DELETE(SCCGWA, TDRIREV, TDTIREV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_INFO = WS_AC;
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_IREV_NOFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.TYPE = 'D';
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = IBS.CLS2CPY(SCCGWA, TDRIREV.KEY);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTIREV";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "DELETE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_TDTIREV() throws IOException,SQLException,Exception {
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        IBS.REWRITE(SCCGWA, TDRIREV, TDTIREV_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.TYPE = 'D';
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = TDRIREV.KEY.ACO_AC;
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTIREV";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_COUNT_TDTIREV_ACNO() throws IOException,SQLException,Exception {
        TDTIREV_RD = new DBParm();
        TDTIREV_RD.TableName = "TDTIREV";
        TDTIREV_RD.set = "WS-IREV-CNT=COUNT(*)";
        TDTIREV_RD.eqWhere = "ACO_AC";
        IBS.GROUP(SCCGWA, TDRIREV, this, TDTIREV_RD);
        CEP.TRC(SCCGWA, WS_IREV_CNT);
    }
    public void S000_CALL_DCZUQSAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-INQ-SUB-AC", DCCUQSAC);
        if (DCCUQSAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUQSAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO);
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
