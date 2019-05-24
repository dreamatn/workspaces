package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTHIST {
    brParm BPTNHIST_BR = new brParm();
    DBParm BPTNHIST_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTHIST";
    String K_TBL_NHIST = "BPTNHIST";
    String WS_ERR_MSG = " ";
    int WS_LENGTH = 0;
    long WS_RCD_SEQ = 0;
    short WS_MAX_JRN_SEQ = 0;
    int WS_STR_DT = 0;
    int WS_END_DT = 0;
    String WS_AC = " ";
    String WS_REF_NO = " ";
    String WS_TX_TOOL = " ";
    String WS_TYP_CD = " ";
    String WS_TX_CD = " ";
    String WS_TX_TLR = " ";
    long WS_JRNNO = 0;
    String WS_CI_NO = " ";
    int WS_TX_BR = 0;
    short WS_TX_DP = 0;
    int WS_STR_TX_DT = 0;
    int WS_END_TX_DT = 0;
    int WS_STR_TX_TM = 0;
    int WS_END_TX_TM = 0;
    int WS_COUNT_NO = 0;
    int WS_AC_SEQ = 0;
    String WS_CCY = " ";
    char WS_CCY_TYPE = ' ';
    char WS_STARTBR_FLAG = ' ';
    char WS_BROWS_COND = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPCTHIST BPCTHIST;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRNHIST BPRNHIST;
    public void MP(SCCGWA SCCGWA, BPCTHIST BPCTHIST) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTHIST = BPCTHIST;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA);
        CEP.TRC(SCCGWA, "BPZTHIST return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRNHIST = (BPRNHIST) BPCTHIST.PTR;
        CEP.TRC(SCCGWA, BPRNHIST.KEY.JRNNO);
        CEP.TRC(SCCGWA, BPRNHIST.KEY.AC_DT);
        CEP.TRC(SCCGWA, BPRNHIST.KEY.JRN_SEQ);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCTHIST.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            if (BPCTHIST.INFO.FUNC == '1') {
                B010_STARTBR_RECORD_PROC_CN();
                if (pgmRtn) return;
            } else if (BPCTHIST.INFO.FUNC == '2') {
                B020_READNEXT_RECORD_PROC_CN();
                if (pgmRtn) return;
            } else if (BPCTHIST.INFO.FUNC == '3') {
                B030_ENDBR_RECORD_PROC();
                if (pgmRtn) return;
            } else if (BPCTHIST.INFO.FUNC == '4') {
                CEP.TRC(SCCGWA, "AAA");
                CEP.TRC(SCCGWA, BPRNHIST.KEY.JRNNO);
                CEP.TRC(SCCGWA, BPRNHIST.KEY.AC_DT);
                CEP.TRC(SCCGWA, BPRNHIST.KEY.JRN_SEQ);
                B040_QUERY_RECORD_PROC();
                if (pgmRtn) return;
            } else if (BPCTHIST.INFO.FUNC == '5') {
                B050_CREAT_RECORD_PROC();
                if (pgmRtn) return;
            } else if (BPCTHIST.INFO.FUNC == '6') {
                B060_DELETE_RECORD_PROC();
                if (pgmRtn) return;
            } else if (BPCTHIST.INFO.FUNC == '7') {
                B070_GROUP_RECORD_PROC();
                if (pgmRtn) return;
            } else if (BPCTHIST.INFO.FUNC == '8') {
                B080_STARTBR_UPD_PROC();
                if (pgmRtn) return;
            } else if (BPCTHIST.INFO.FUNC == '9') {
                B090_UPDATE_RECORD_PROC();
                if (pgmRtn) return;
            } else if (BPCTHIST.INFO.FUNC == 'C') {
                B100_COUNT_RECORD_PROC_CN();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, SCCEXCP);
                SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCTHIST.INFO.FUNC + ")";
                CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
            }
        } else {
            if (BPCTHIST.INFO.FUNC == '1') {
                B010_STARTBR_RECORD_PROC();
                if (pgmRtn) return;
            } else if (BPCTHIST.INFO.FUNC == '2') {
                B020_READNEXT_RECORD_PROC();
                if (pgmRtn) return;
            } else if (BPCTHIST.INFO.FUNC == '3') {
                B030_ENDBR_RECORD_PROC();
                if (pgmRtn) return;
            } else if (BPCTHIST.INFO.FUNC == '4') {
                CEP.TRC(SCCGWA, "BBB");
                CEP.TRC(SCCGWA, BPRNHIST.KEY.JRNNO);
                CEP.TRC(SCCGWA, BPRNHIST.KEY.AC_DT);
                CEP.TRC(SCCGWA, BPRNHIST.KEY.JRN_SEQ);
                B040_QUERY_RECORD_PROC();
                if (pgmRtn) return;
            } else if (BPCTHIST.INFO.FUNC == '5') {
                B050_CREAT_RECORD_PROC();
                if (pgmRtn) return;
            } else if (BPCTHIST.INFO.FUNC == '6') {
                B060_DELETE_RECORD_PROC();
                if (pgmRtn) return;
            } else if (BPCTHIST.INFO.FUNC == '7') {
                B070_GROUP_RECORD_PROC();
                if (pgmRtn) return;
            } else if (BPCTHIST.INFO.FUNC == '8') {
                B080_STARTBR_UPD_PROC();
                if (pgmRtn) return;
            } else if (BPCTHIST.INFO.FUNC == '9') {
                B090_UPDATE_RECORD_PROC();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, SCCEXCP);
                SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCTHIST.INFO.FUNC + ")";
                CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
            }
        }
    }
    public void B005_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCTHIST.INFO.STR_AC_DT == 0) {
            WS_STR_DT = 10101;
        } else {
            WS_STR_DT = BPCTHIST.INFO.STR_AC_DT;
        }
        if (BPCTHIST.INFO.END_AC_DT == 0) {
            WS_END_DT = 99991231;
        } else {
            WS_END_DT = BPCTHIST.INFO.END_AC_DT;
        }
        if (BPCTHIST.INFO.STR_TX_DT == 0) {
            WS_STR_TX_DT = 10101;
        } else {
            WS_STR_TX_DT = BPCTHIST.INFO.STR_TX_DT;
        }
        if (BPCTHIST.INFO.END_TX_DT == 0) {
            WS_END_TX_DT = 99991231;
        } else {
            WS_END_TX_DT = BPCTHIST.INFO.END_TX_DT;
        }
        if (WS_STR_DT > WS_END_DT 
            || WS_STR_TX_DT > WS_END_TX_DT) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_HIS_DATE_ERR, BPCTHIST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCTHIST.INFO.END_TX_TM == 0) {
            WS_END_TX_TM = 235959;
        } else {
            WS_END_TX_TM = BPCTHIST.INFO.END_TX_TM;
        }
        WS_STR_TX_TM = BPCTHIST.INFO.STR_TX_TM;
        if (WS_STR_TX_TM > WS_END_TX_TM) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_STR_END_TIME_ERR, BPCTHIST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_JRNNO = BPCTHIST.INFO.JRNNO;
        WS_AC = BPCTHIST.INFO.AC;
        WS_CI_NO = BPCTHIST.INFO.CI_NO;
        WS_REF_NO = BPCTHIST.INFO.REF_NO;
        WS_TX_TOOL = BPCTHIST.INFO.TX_TOOL;
        WS_TYP_CD = BPCTHIST.INFO.TYP_CD;
        WS_TX_CD = BPCTHIST.INFO.TX_CD;
        WS_TX_TLR = BPCTHIST.INFO.TX_TLR;
        WS_TX_BR = BPCTHIST.INFO.TX_BR;
        WS_TX_DP = BPCTHIST.INFO.TX_DP;
        WS_AC_SEQ = BPCTHIST.AC_SEQ;
        WS_CCY = BPCTHIST.CCY;
        WS_CCY_TYPE = BPCTHIST.CCY_TYPE;
    }
    public void B001_SET_COND_CN() throws IOException,SQLException,Exception {
        WS_BROWS_COND = ' ';
        CEP.TRC(SCCGWA, BPCTHIST.INFO.AC);
        CEP.TRC(SCCGWA, BPCTHIST.INFO.REF_NO);
        CEP.TRC(SCCGWA, BPCTHIST.INFO.JRNNO);
        CEP.TRC(SCCGWA, BPCTHIST.INFO.TX_TLR);
        CEP.TRC(SCCGWA, BPCTHIST.INFO.TX_CD);
        CEP.TRC(SCCGWA, BPCTHIST.INFO.TX_TOOL);
        CEP.TRC(SCCGWA, BPCTHIST.INFO.CI_NO);
        CEP.TRC(SCCGWA, BPCTHIST.INFO.TX_BR);
        if (BPCTHIST.INFO.JRNNO != 0) {
            CEP.TRC(SCCGWA, "BROWSE_BY_JRNNO");
            WS_BROWS_COND = '1';
        } else if (BPCTHIST.INFO.AC.trim().length() > 0 
                && BPCTHIST.INFO.TX_TOOL.trim().length() == 0 
                && BPCTHIST.INFO.TX_CD.trim().length() == 0 
                && BPCTHIST.INFO.TX_BR != 0 
                && BPCTHIST.INFO.TX_TLR.trim().length() > 0) {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_AC");
            WS_BROWS_COND = '3';
        } else if (BPCTHIST.INFO.AC.trim().length() > 0 
                && BPCTHIST.INFO.TX_TOOL.trim().length() == 0 
                && BPCTHIST.INFO.TX_CD.trim().length() == 0 
                && BPCTHIST.INFO.TX_BR == 0 
                && BPCTHIST.INFO.TX_TLR.trim().length() == 0) {
            CEP.TRC(SCCGWA, "BROWSE_AC");
            WS_BROWS_COND = '2';
        } else if (BPCTHIST.INFO.AC.trim().length() == 0 
                && BPCTHIST.INFO.TX_TOOL.trim().length() == 0 
                && BPCTHIST.INFO.TX_CD.trim().length() == 0 
                && BPCTHIST.INFO.TX_BR != 0 
                && BPCTHIST.INFO.TX_TLR.trim().length() > 0) {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR");
            WS_BROWS_COND = '6';
        } else if (BPCTHIST.INFO.AC.trim().length() == 0 
                && BPCTHIST.INFO.TX_TOOL.trim().length() > 0 
                && BPCTHIST.INFO.TX_CD.trim().length() == 0 
                && BPCTHIST.INFO.TX_BR != 0 
                && BPCTHIST.INFO.TX_TLR.trim().length() > 0) {
            CEP.TRC(SCCGWA, "BROWSE_BY_TX_TOOL_BR_TLR");
            WS_BROWS_COND = '5';
        } else if (BPCTHIST.INFO.AC.trim().length() == 0 
                && BPCTHIST.INFO.TX_TOOL.trim().length() > 0 
                && BPCTHIST.INFO.TX_CD.trim().length() == 0 
                && BPCTHIST.INFO.TX_BR == 0 
                && BPCTHIST.INFO.TX_TLR.trim().length() == 0) {
            CEP.TRC(SCCGWA, "BROWSE_BY_TX_TOOL");
            WS_BROWS_COND = '4';
        } else if (BPCTHIST.INFO.AC.trim().length() == 0 
                && BPCTHIST.INFO.TX_TOOL.trim().length() == 0 
                && BPCTHIST.INFO.TX_CD.trim().length() > 0 
                && BPCTHIST.INFO.TX_BR == 0 
                && BPCTHIST.INFO.TX_TLR.trim().length() == 0) {
            CEP.TRC(SCCGWA, "BROWSE_BY_AMT_CD");
            WS_BROWS_COND = '7';
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NONE_COND, BPCTHIST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_STARTBR_RECORD_PROC_CN() throws IOException,SQLException,Exception {
        WS_STARTBR_FLAG = 'N';
        B005_CHECK_INPUT();
        if (pgmRtn) return;
        B001_SET_COND_CN();
        if (pgmRtn) return;
        if (WS_BROWS_COND == '1') {
            T000_STARTBR_JRNNO_CN();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '2') {
            T000_STARTBR_AC_CN();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '3') {
            T000_STARTBR_ACBRTLR_CN();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '6') {
            T000_STARTBR_BRTLR_CN();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '5') {
            T000_STARTBR_TXTOOL_BRTLR_CN();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '4') {
            T000_STARTBR_TXTOOL_CN();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '7') {
            T000_STARTBR_TXCD_CN();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NONE_COND, BPCTHIST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_COUNT_RECORD_PROC_CN() throws IOException,SQLException,Exception {
        WS_STARTBR_FLAG = 'N';
        B005_CHECK_INPUT();
        if (pgmRtn) return;
        B001_SET_COND_CN();
        if (pgmRtn) return;
        WS_COUNT_NO = 0;
        if (WS_BROWS_COND == '1') {
            T000_COUNT_JRNNO_CN();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '2') {
            T000_COUNT_AC_CN();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '3') {
            T000_COUNT_ACBRTLR_CN();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '6') {
            T000_COUNT_BRTLR_CN();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '5') {
            T000_COUNT_TXTOOL_BRTLR_CN();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '4') {
            T000_COUNT_TXTOOL_CN();
            if (pgmRtn) return;
        } else if (WS_BROWS_COND == '7') {
            T000_COUNT_TXCD_CN();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NONE_COND, BPCTHIST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        BPCTHIST.COUNT_NO = WS_COUNT_NO;
    }
    public void T000_STARTBR_JRNNO_CN() throws IOException,SQLException,Exception {
        BPTNHIST_BR.rp = new DBParm();
        BPTNHIST_BR.rp.TableName = "BPTNHIST";
        BPTNHIST_BR.rp.where = "AC_DT = :WS_STR_DT "
            + "AND JRNNO = :WS_JRNNO";
        BPTNHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
        IBS.STARTBR(SCCGWA, BPRNHIST, this, BPTNHIST_BR);
    }
    public void T000_STARTBR_AC_CN() throws IOException,SQLException,Exception {
        if (BPCTHIST.AC_SEQ != 0) {
            BPTNHIST_BR.rp = new DBParm();
            BPTNHIST_BR.rp.TableName = "BPTNHIST";
            BPTNHIST_BR.rp.where = "( AC_DT BETWEEN :WS_STR_DT "
                + "AND :WS_END_DT ) "
                + "AND AC = :WS_AC "
                + "AND AC_SEQ = :WS_AC_SEQ";
            BPTNHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRNHIST, this, BPTNHIST_BR);
        } else {
            if (BPCTHIST.CCY.trim().length() > 0 
                || BPCTHIST.CCY_TYPE != ' ') {
                BPTNHIST_BR.rp = new DBParm();
                BPTNHIST_BR.rp.TableName = "BPTNHIST";
                BPTNHIST_BR.rp.where = "( AC_DT BETWEEN :WS_STR_DT "
                    + "AND :WS_END_DT ) "
                    + "AND AC = :WS_AC "
                    + "AND CCY = :WS_CCY "
                    + "AND CCY_TYPE = :WS_CCY_TYPE";
                BPTNHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRNHIST, this, BPTNHIST_BR);
            } else {
                BPTNHIST_BR.rp = new DBParm();
                BPTNHIST_BR.rp.TableName = "BPTNHIST";
                BPTNHIST_BR.rp.where = "( AC_DT BETWEEN :WS_STR_DT "
                    + "AND :WS_END_DT ) "
                    + "AND AC = :WS_AC";
                BPTNHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRNHIST, this, BPTNHIST_BR);
            }
        }
    }
    public void T000_STARTBR_ACBRTLR_CN() throws IOException,SQLException,Exception {
        BPTNHIST_BR.rp = new DBParm();
        BPTNHIST_BR.rp.TableName = "BPTNHIST";
        BPTNHIST_BR.rp.where = "( AC_DT BETWEEN :WS_STR_DT "
            + "AND :WS_END_DT ) "
            + "AND TX_BR = :WS_TX_BR "
            + "AND TX_TLR = :WS_TX_TLR "
            + "AND AC = :WS_AC";
        BPTNHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
        IBS.STARTBR(SCCGWA, BPRNHIST, this, BPTNHIST_BR);
    }
    public void T000_STARTBR_TXTOOL_CN() throws IOException,SQLException,Exception {
        BPTNHIST_BR.rp = new DBParm();
        BPTNHIST_BR.rp.TableName = "BPTNHIST";
        BPTNHIST_BR.rp.where = "( AC_DT BETWEEN :WS_STR_DT "
            + "AND :WS_END_DT ) "
            + "AND TX_TOOL = :WS_TX_TOOL";
        BPTNHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
        IBS.STARTBR(SCCGWA, BPRNHIST, this, BPTNHIST_BR);
    }
    public void T000_STARTBR_TXTOOL_BRTLR_CN() throws IOException,SQLException,Exception {
        BPTNHIST_BR.rp = new DBParm();
        BPTNHIST_BR.rp.TableName = "BPTNHIST";
        BPTNHIST_BR.rp.where = "( AC_DT BETWEEN :WS_STR_DT "
            + "AND :WS_END_DT ) "
            + "AND TX_BR = :WS_TX_BR "
            + "AND TX_TLR = :WS_TX_TLR "
            + "AND TX_TOOL = :WS_TX_TOOL";
        BPTNHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
        IBS.STARTBR(SCCGWA, BPRNHIST, this, BPTNHIST_BR);
    }
    public void T000_STARTBR_BRTLR_CN() throws IOException,SQLException,Exception {
        BPTNHIST_BR.rp = new DBParm();
        BPTNHIST_BR.rp.TableName = "BPTNHIST";
        BPTNHIST_BR.rp.where = "( AC_DT BETWEEN :WS_STR_DT "
            + "AND :WS_END_DT ) "
            + "AND TX_BR = :WS_TX_BR "
            + "AND TX_TLR = :WS_TX_TLR";
        BPTNHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
        IBS.STARTBR(SCCGWA, BPRNHIST, this, BPTNHIST_BR);
    }
    public void T000_STARTBR_TXCD_CN() throws IOException,SQLException,Exception {
        BPTNHIST_BR.rp = new DBParm();
        BPTNHIST_BR.rp.TableName = "BPTNHIST";
        BPTNHIST_BR.rp.where = "( AC_DT BETWEEN :WS_STR_DT "
            + "AND :WS_END_DT ) "
            + "AND TX_CD = :WS_TX_CD";
        BPTNHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
        IBS.STARTBR(SCCGWA, BPRNHIST, this, BPTNHIST_BR);
    }
    public void T000_COUNT_JRNNO_CN() throws IOException,SQLException,Exception {
        BPTNHIST_RD = new DBParm();
        BPTNHIST_RD.TableName = "BPTNHIST";
        BPTNHIST_RD.set = "WS-COUNT-NO=COUNT(1)";
        BPTNHIST_RD.where = "AC_DT = :WS_STR_DT "
            + "AND JRNNO = :WS_JRNNO";
        IBS.GROUP(SCCGWA, BPRNHIST, this, BPTNHIST_RD);
    }
    public void T000_COUNT_AC_CN() throws IOException,SQLException,Exception {
        BPTNHIST_RD = new DBParm();
        BPTNHIST_RD.TableName = "BPTNHIST";
        BPTNHIST_RD.set = "WS-COUNT-NO=COUNT(1)";
        BPTNHIST_RD.where = "( AC_DT BETWEEN :WS_STR_DT "
            + "AND :WS_END_DT ) "
            + "AND AC = :WS_AC";
        IBS.GROUP(SCCGWA, BPRNHIST, this, BPTNHIST_RD);
    }
    public void T000_COUNT_ACBRTLR_CN() throws IOException,SQLException,Exception {
        BPTNHIST_RD = new DBParm();
        BPTNHIST_RD.TableName = "BPTNHIST";
        BPTNHIST_RD.set = "WS-COUNT-NO=COUNT(1)";
        BPTNHIST_RD.where = "( AC_DT BETWEEN :WS_STR_DT "
            + "AND :WS_END_DT ) "
            + "AND TX_BR = :WS_TX_BR "
            + "AND TX_TLR = :WS_TX_TLR "
            + "AND AC = :WS_AC";
        IBS.GROUP(SCCGWA, BPRNHIST, this, BPTNHIST_RD);
    }
    public void T000_COUNT_TXTOOL_CN() throws IOException,SQLException,Exception {
        BPTNHIST_RD = new DBParm();
        BPTNHIST_RD.TableName = "BPTNHIST";
        BPTNHIST_RD.set = "WS-COUNT-NO=COUNT(1)";
        BPTNHIST_RD.where = "( AC_DT BETWEEN :WS_STR_DT "
            + "AND :WS_END_DT ) "
            + "AND TX_TOOL = :WS_TX_TOOL";
        IBS.GROUP(SCCGWA, BPRNHIST, this, BPTNHIST_RD);
    }
    public void T000_COUNT_TXTOOL_BRTLR_CN() throws IOException,SQLException,Exception {
        BPTNHIST_RD = new DBParm();
        BPTNHIST_RD.TableName = "BPTNHIST";
        BPTNHIST_RD.set = "WS-COUNT-NO=COUNT(1)";
        BPTNHIST_RD.where = "( AC_DT BETWEEN :WS_STR_DT "
            + "AND :WS_END_DT ) "
            + "AND TX_BR = :WS_TX_BR "
            + "AND TX_TLR = :WS_TX_TLR "
            + "AND TX_TOOL = :WS_TX_TOOL";
        IBS.GROUP(SCCGWA, BPRNHIST, this, BPTNHIST_RD);
    }
    public void T000_COUNT_BRTLR_CN() throws IOException,SQLException,Exception {
        BPTNHIST_RD = new DBParm();
        BPTNHIST_RD.TableName = "BPTNHIST";
        BPTNHIST_RD.set = "WS-COUNT-NO=COUNT(1)";
        BPTNHIST_RD.where = "( AC_DT BETWEEN :WS_STR_DT "
            + "AND :WS_END_DT ) "
            + "AND TX_BR = :WS_TX_BR "
            + "AND TX_TLR = :WS_TX_TLR";
        IBS.GROUP(SCCGWA, BPRNHIST, this, BPTNHIST_RD);
    }
    public void T000_COUNT_TXCD_CN() throws IOException,SQLException,Exception {
        BPTNHIST_RD = new DBParm();
        BPTNHIST_RD.TableName = "BPTNHIST";
        BPTNHIST_RD.set = "WS-COUNT-NO=COUNT(1)";
        BPTNHIST_RD.where = "( AC_DT BETWEEN :WS_STR_DT "
            + "AND :WS_END_DT ) "
            + "AND TX_CD = :WS_TX_CD";
        IBS.GROUP(SCCGWA, BPRNHIST, this, BPTNHIST_RD);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        WS_STARTBR_FLAG = 'N';
        B005_CHECK_INPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCTHIST.INFO.JRNNO);
        CEP.TRC(SCCGWA, WS_JRNNO);
        CEP.TRC(SCCGWA, WS_AC);
        CEP.TRC(SCCGWA, WS_CI_NO);
        CEP.TRC(SCCGWA, WS_REF_NO);
        CEP.TRC(SCCGWA, WS_TX_TOOL);
        CEP.TRC(SCCGWA, WS_TYP_CD);
        CEP.TRC(SCCGWA, WS_TX_CD);
        CEP.TRC(SCCGWA, WS_TX_TLR);
        if (BPCTHIST.INFO.AC.trim().length() == 0 
            && BPCTHIST.INFO.TYP_CD.trim().length() == 0 
            && BPCTHIST.INFO.REF_NO.trim().length() == 0 
            && BPCTHIST.INFO.JRNNO == 0 
            && BPCTHIST.INFO.TX_TLR.trim().length() == 0 
            && BPCTHIST.INFO.TX_CD.trim().length() == 0 
            && BPCTHIST.INFO.TX_TOOL.trim().length() == 0 
            && BPCTHIST.INFO.CI_NO.trim().length() == 0 
            && BPCTHIST.INFO.TX_BR == 0 
            && BPCTHIST.INFO.TX_DP == 0) {
            T000_STARTBR_NONE();
            if (pgmRtn) return;
        }
        if (BPCTHIST.INFO.JRNNO != 0) {
            T000_STARTBR_JRN();
            if (pgmRtn) return;
        } else {
            if (BPCTHIST.INFO.AC.trim().length() > 0) {
                T000_STARTBR_AC();
                if (pgmRtn) return;
            } else {
                if (BPCTHIST.INFO.REF_NO.trim().length() > 0) {
                    T000_STARTBR_REF();
                    if (pgmRtn) return;
                } else {
                    if (BPCTHIST.INFO.TX_TOOL.trim().length() > 0) {
                        T000_STARTBR_TXTOOL();
                        if (pgmRtn) return;
                    } else {
                        if (BPCTHIST.INFO.CI_NO.trim().length() > 0) {
                            T000_STARTBR_CINO();
                            if (pgmRtn) return;
                        } else {
                            if (BPCTHIST.INFO.TX_CD.trim().length() > 0) {
                                T000_STARTBR_TXCD();
                                if (pgmRtn) return;
                            } else {
                                if (BPCTHIST.INFO.TX_TLR.trim().length() > 0) {
                                    T000_STARTBR_TLR();
                                    if (pgmRtn) return;
                                } else {
                                    if (BPCTHIST.INFO.TYP_CD.trim().length() > 0) {
                                        T000_STARTBR_TYP();
                                        if (pgmRtn) return;
                                    } else {
                                        if (BPCTHIST.INFO.TX_BR != 0) {
                                            T000_STARTBR_BR();
                                            if (pgmRtn) return;
                                        } else {
                                            if (BPCTHIST.INFO.TX_DP != 0) {
                                                T000_STARTBR_DP();
                                                if (pgmRtn) return;
                                            } else {
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (WS_STARTBR_FLAG == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCTHIST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTNHIST();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTNHIST();
        if (pgmRtn) return;
    }
    public void B040_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRNHIST.KEY.AC_DT);
        CEP.TRC(SCCGWA, BPRNHIST.KEY.JRNNO);
        CEP.TRC(SCCGWA, BPRNHIST.KEY.JRN_SEQ);
        T000_READ_BPTNHIST();
        if (pgmRtn) return;
    }
    public void B050_CREAT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_WRITE_BPTNHIST();
        if (pgmRtn) return;
    }
    public void B060_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTNHIST();
        if (pgmRtn) return;
    }
    public void B070_GROUP_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_GROUP_BPTNHIST();
        if (pgmRtn) return;
        BPCTHIST.MAX_JRN_SEQ = WS_MAX_JRN_SEQ;
    }
    public void B080_STARTBR_UPD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_UPD_BPTNHIST();
        if (pgmRtn) return;
    }
    public void B090_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_REWRITE_BPTNHIST();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_RECORD_PROC_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCTHIST.FETCH_NO);
        if (BPCTHIST.FETCH_NO > 0) {
            WS_RCD_SEQ = BPCTHIST.FETCH_NO;
            T000_FATCH_BPTNHIST_CN();
            if (pgmRtn) return;
        } else {
            T000_READNEXT_BPTNHIST();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_NONE() throws IOException,SQLException,Exception {
        WS_STARTBR_FLAG = 'Y';
        BPTNHIST_BR.rp = new DBParm();
        BPTNHIST_BR.rp.TableName = "BPTNHIST";
        BPTNHIST_BR.rp.where = "( AC_DT BETWEEN :WS_STR_DT "
            + "AND :WS_END_DT ) "
            + "AND ( TX_DT BETWEEN :WS_STR_TX_DT "
            + "AND :WS_END_TX_DT ) "
            + "AND ( TX_TM BETWEEN :WS_STR_TX_TM "
            + "AND :WS_END_TX_TM )";
        BPTNHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
        IBS.STARTBR(SCCGWA, BPRNHIST, this, BPTNHIST_BR);
    }
    public void T000_STARTBR_AC() throws IOException,SQLException,Exception {
        WS_STARTBR_FLAG = 'Y';
        BPTNHIST_BR.rp = new DBParm();
        BPTNHIST_BR.rp.TableName = "BPTNHIST";
        BPTNHIST_BR.rp.where = "( AC_DT BETWEEN :WS_STR_DT "
            + "AND :WS_END_DT ) "
            + "AND AC = :WS_AC "
            + "AND ( TX_TM BETWEEN :WS_STR_TX_TM "
            + "AND :WS_END_TX_TM ) "
            + "AND ( TX_DT BETWEEN :WS_STR_TX_DT "
            + "AND :WS_END_TX_DT )";
        BPTNHIST_BR.rp.order = "AC_DT,AC,TX_TM DESC";
        IBS.STARTBR(SCCGWA, BPRNHIST, this, BPTNHIST_BR);
    }
    public void T000_STARTBR_CINO() throws IOException,SQLException,Exception {
        WS_STARTBR_FLAG = 'Y';
        BPTNHIST_BR.rp = new DBParm();
        BPTNHIST_BR.rp.TableName = "BPTNHIST";
        BPTNHIST_BR.rp.where = "( AC_DT BETWEEN :WS_STR_DT "
            + "AND :WS_END_DT ) "
            + "AND CI_NO = :WS_CI_NO "
            + "AND ( TX_TM BETWEEN :WS_STR_TX_TM "
            + "AND :WS_END_TX_TM ) "
            + "AND ( TX_DT BETWEEN :WS_STR_TX_DT "
            + "AND :WS_END_TX_DT )";
        BPTNHIST_BR.rp.order = "AC_DT,CI_NO,TX_TM DESC";
        IBS.STARTBR(SCCGWA, BPRNHIST, this, BPTNHIST_BR);
    }
    public void T000_STARTBR_DP() throws IOException,SQLException,Exception {
        WS_STARTBR_FLAG = 'Y';
        BPTNHIST_BR.rp = new DBParm();
        BPTNHIST_BR.rp.TableName = "BPTNHIST";
        BPTNHIST_BR.rp.where = "( AC_DT BETWEEN :WS_STR_DT "
            + "AND :WS_END_DT ) "
            + "AND ( TX_DT BETWEEN :WS_STR_TX_DT "
            + "AND :WS_END_TX_DT ) "
            + "AND ( TX_TM BETWEEN :WS_STR_TX_TM "
            + "AND :WS_END_TX_TM ) "
            + "AND TX_DP = :WS_TX_DP";
        BPTNHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
        IBS.STARTBR(SCCGWA, BPRNHIST, this, BPTNHIST_BR);
    }
    public void T000_STARTBR_BR() throws IOException,SQLException,Exception {
        WS_STARTBR_FLAG = 'Y';
        BPTNHIST_BR.rp = new DBParm();
        BPTNHIST_BR.rp.TableName = "BPTNHIST";
        BPTNHIST_BR.rp.where = "( AC_DT BETWEEN :WS_STR_DT "
            + "AND :WS_END_DT ) "
            + "AND ( TX_DT BETWEEN :WS_STR_TX_DT "
            + "AND :WS_END_TX_DT ) "
            + "AND ( TX_TM BETWEEN :WS_STR_TX_TM "
            + "AND :WS_END_TX_TM ) "
            + "AND TX_BR = :WS_TX_BR";
        BPTNHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
        IBS.STARTBR(SCCGWA, BPRNHIST, this, BPTNHIST_BR);
    }
    public void T000_STARTBR_REF() throws IOException,SQLException,Exception {
        WS_STARTBR_FLAG = 'Y';
        BPTNHIST_BR.rp = new DBParm();
        BPTNHIST_BR.rp.TableName = "BPTNHIST";
        BPTNHIST_BR.rp.where = "( AC_DT BETWEEN :WS_STR_DT "
            + "AND :WS_END_DT ) "
            + "AND REF_NO = :WS_REF_NO "
            + "AND ( TX_TM BETWEEN :WS_STR_TX_TM "
            + "AND :WS_END_TX_TM ) "
            + "AND ( TX_DT BETWEEN :WS_STR_TX_DT "
            + "AND :WS_END_TX_DT )";
        BPTNHIST_BR.rp.order = "AC_DT,REF_NO,TX_TM DESC";
        IBS.STARTBR(SCCGWA, BPRNHIST, this, BPTNHIST_BR);
    }
    public void T000_STARTBR_JRN() throws IOException,SQLException,Exception {
        WS_STARTBR_FLAG = 'Y';
        BPTNHIST_BR.rp = new DBParm();
        BPTNHIST_BR.rp.TableName = "BPTNHIST";
        BPTNHIST_BR.rp.where = "( AC_DT BETWEEN :WS_STR_DT "
            + "AND :WS_END_DT ) "
            + "AND JRNNO = :WS_JRNNO "
            + "AND ( TX_DT BETWEEN :WS_STR_TX_DT "
            + "AND :WS_END_TX_DT ) "
            + "AND ( TX_TM BETWEEN :WS_STR_TX_TM "
            + "AND :WS_END_TX_TM )";
        BPTNHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
        IBS.STARTBR(SCCGWA, BPRNHIST, this, BPTNHIST_BR);
    }
    public void T000_STARTBR_TXTOOL() throws IOException,SQLException,Exception {
        WS_STARTBR_FLAG = 'Y';
        BPTNHIST_BR.rp = new DBParm();
        BPTNHIST_BR.rp.TableName = "BPTNHIST";
        BPTNHIST_BR.rp.where = "( AC_DT BETWEEN :WS_STR_DT "
            + "AND :WS_END_DT ) "
            + "AND TX_TOOL = :WS_TX_TOOL "
            + "AND ( TX_TM BETWEEN :WS_STR_TX_TM "
            + "AND :WS_END_TX_TM ) "
            + "AND ( TX_DT BETWEEN :WS_STR_TX_DT "
            + "AND :WS_END_TX_DT )";
        BPTNHIST_BR.rp.order = "AC_DT,TX_TOOL,TX_TM DESC";
        IBS.STARTBR(SCCGWA, BPRNHIST, this, BPTNHIST_BR);
    }
    public void T000_STARTBR_TLR() throws IOException,SQLException,Exception {
        WS_STARTBR_FLAG = 'Y';
        BPTNHIST_BR.rp = new DBParm();
        BPTNHIST_BR.rp.TableName = "BPTNHIST";
        BPTNHIST_BR.rp.where = "( AC_DT BETWEEN :WS_STR_DT "
            + "AND :WS_END_DT ) "
            + "AND TX_TLR = :WS_TX_TLR "
            + "AND ( TX_TM BETWEEN :WS_STR_TX_TM "
            + "AND :WS_END_TX_TM ) "
            + "AND ( TX_DT BETWEEN :WS_STR_TX_DT "
            + "AND :WS_END_TX_DT )";
        BPTNHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
        IBS.STARTBR(SCCGWA, BPRNHIST, this, BPTNHIST_BR);
    }
    public void T000_STARTBR_TXCD() throws IOException,SQLException,Exception {
        WS_STARTBR_FLAG = 'Y';
        BPTNHIST_BR.rp = new DBParm();
        BPTNHIST_BR.rp.TableName = "BPTNHIST";
        BPTNHIST_BR.rp.where = "( AC_DT BETWEEN :WS_STR_DT "
            + "AND :WS_END_DT ) "
            + "AND ( TX_DT BETWEEN :WS_STR_TX_DT "
            + "AND :WS_END_TX_DT ) "
            + "AND ( TX_TM BETWEEN :WS_STR_TX_TM "
            + "AND :WS_END_TX_TM ) "
            + "AND TX_CD = :WS_TX_CD";
        BPTNHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
        IBS.STARTBR(SCCGWA, BPRNHIST, this, BPTNHIST_BR);
    }
    public void T000_STARTBR_TYP() throws IOException,SQLException,Exception {
        WS_STARTBR_FLAG = 'Y';
        BPTNHIST_BR.rp = new DBParm();
        BPTNHIST_BR.rp.TableName = "BPTNHIST";
        BPTNHIST_BR.rp.where = "( AC_DT BETWEEN :WS_STR_DT "
            + "AND :WS_END_DT ) "
            + "AND TX_TYP_CD = :WS_TYP_CD "
            + "AND ( TX_TM BETWEEN :WS_STR_TX_TM "
            + "AND :WS_END_TX_TM ) "
            + "AND ( TX_DT BETWEEN :WS_STR_TX_DT "
            + "AND :WS_END_TX_DT )";
        BPTNHIST_BR.rp.order = "AC_DT,TX_TYP_CD,TX_TM DESC";
        IBS.STARTBR(SCCGWA, BPRNHIST, this, BPTNHIST_BR);
    }
    public void T000_READ_BPTNHIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRNHIST.KEY);
        BPTNHIST_RD = new DBParm();
        BPTNHIST_RD.TableName = "BPTNHIST";
        IBS.READ(SCCGWA, BPRNHIST, BPTNHIST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTHIST.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTHIST.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_NHIST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BPTNHIST() throws IOException,SQLException,Exception {
        BPTNHIST_RD = new DBParm();
        BPTNHIST_RD.TableName = "BPTNHIST";
        IBS.REWRITE(SCCGWA, BPRNHIST, BPTNHIST_RD);
    }
    public void T000_WRITE_BPTNHIST() throws IOException,SQLException,Exception {
        BPTNHIST_RD = new DBParm();
        BPTNHIST_RD.TableName = "BPTNHIST";
        BPTNHIST_RD.errhdl = true;
        IBS.WRITE(SCCGWA, BPRNHIST, BPTNHIST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTHIST.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            BPCTHIST.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_NHIST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_DELETE_BPTNHIST() throws IOException,SQLException,Exception {
    }
    public void T000_GROUP_BPTNHIST() throws IOException,SQLException,Exception {
        BPTNHIST_RD = new DBParm();
        BPTNHIST_RD.TableName = "BPTNHIST";
        BPTNHIST_RD.set = "WS-MAX-JRN-SEQ=IFNULL(MAX(JRN_SEQ),0)";
        BPTNHIST_RD.where = "AC_DT = :BPRNHIST.KEY.AC_DT "
            + "AND JRNNO = :BPRNHIST.KEY.JRNNO";
        IBS.GROUP(SCCGWA, BPRNHIST, this, BPTNHIST_RD);
    }
    public void T000_STARTBR_UPD_BPTNHIST() throws IOException,SQLException,Exception {
        BPTNHIST_BR.rp = new DBParm();
        BPTNHIST_BR.rp.TableName = "BPTNHIST";
        BPTNHIST_BR.rp.upd = true;
        BPTNHIST_BR.rp.where = "AC_DT = :BPRNHIST.KEY.AC_DT "
            + "AND JRNNO = :BPRNHIST.KEY.JRNNO";
        IBS.STARTBR(SCCGWA, BPRNHIST, this, BPTNHIST_BR);
    }
    public void T000_READNEXT_BPTNHIST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRNHIST, this, BPTNHIST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTHIST.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTHIST.RETURN_INFO = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_NHIST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_FATCH_BPTNHIST_CN() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRNHIST, this, BPTNHIST_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTHIST.RETURN_INFO = 'F';
            CEP.TRC(SCCGWA, BPRNHIST.KEY.JRNNO);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTHIST.RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_END_OF_TABLE, BPCTHIST.RC);
            CEP.TRC(SCCGWA, "NOTFND");
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_NHIST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCTHIST.RC);
    }
    public void T000_ENDBR_BPTNHIST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTNHIST_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTHIST.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTHIST = ");
            CEP.TRC(SCCGWA, BPCTHIST);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
