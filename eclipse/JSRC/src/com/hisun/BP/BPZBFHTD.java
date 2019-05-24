package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZBFHTD {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTFHIS_RD;
    brParm BPTFHIS_BR = new brParm();
    boolean pgmRtn = false;
    String PROD_CD_01 = "RDP00002";
    String PROD_CD_02 = "RDP00495";
    String WS_ERR_MSG = " ";
    int WS_LENGTH = 0;
    long WS_RCD_SEQ = 0;
    char WS_PROD_CD_FLG = ' ';
    short WS_MAX_JRN_SEQ = 0;
    int WS_STR_DATE = 0;
    int WS_END_DATE = 0;
    int WS_STR_TR_DATE = 0;
    int WS_END_TR_DATE = 0;
    int WS_STR_TR_TM = 0;
    int WS_END_TR_TM = 0;
    double WS_STR_AMT = 0;
    double WS_END_AMT = 0;
    String WS_AC = " ";
    String WS_TX_CCY = " ";
    String WS_REF_NO = " ";
    long WS_JRNNO = 0;
    String WS_TX_TOOL = " ";
    String WS_TX_TELR = " ";
    String WS_TX_CD = " ";
    String WS_CI_NO = " ";
    int WS_TX_BR = 0;
    short WS_TX_DP = 0;
    char WS_TX_DC = ' ';
    String WS_MAKER_ID = " ";
    String WS_CHECKER_ID = " ";
    int WS_COUNT_NO = 0;
    String WS_PROD_CD = " ";
    String WS_TX_MMO = " ";
    String WS_PROD_CD_01 = " ";
    String WS_PROD_CD_02 = " ";
    char WS_BRW_FHIS_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    BPRFHIS BPRFHIS = new BPRFHIS();
    SCCGWA SCCGWA;
    BPCBFHTD BPCBFHTD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCRCWAT SCRCWA;
    String LK_REC = " ";
    public void MP(SCCGWA SCCGWA, BPCBFHTD BPCBFHTD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCBFHTD = BPCBFHTD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZBFHTD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        LK_REC = IBS.CLS2CPY(SCCGWA, BPCBFHTD.INPUT.REC_PT);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCRCWA = (SCRCWAT) GWA_SC_AREA.CWA_AREA_PTR;
        IBS.init(SCCGWA, BPRFHIS);
        WS_LENGTH = 690;
        CEP.TRC(SCCGWA, WS_LENGTH);
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.REC_LEN);
        if (WS_LENGTH == BPCBFHTD.INPUT.REC_LEN) {
            CEP.TRC(SCCGWA, "WS-LENGTH = BFHTD-REC-LEN");
        }
        if (LK_REC == null) LK_REC = "";
        JIBS_tmp_int = LK_REC.length();
        for (int i=0;i<10240-JIBS_tmp_int;i++) LK_REC += " ";
        JIBS_tmp_str[0] = LK_REC.substring(0, BPCBFHTD.INPUT.REC_LEN);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRFHIS);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCBFHTD.OUTPUT.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_IN_USE);
        if (BPCBFHTD.INPUT.FUNC != '4' 
            && BPCBFHTD.INPUT.FUNC != '5') {
            B005_CHECK_INPUT();
            if (pgmRtn) return;
        }
        if (BPCBFHTD.INPUT.FUNC == '1') {
            B010_STARTBR_PROC();
            if (pgmRtn) return;
        } else if (BPCBFHTD.INPUT.FUNC == '2') {
            B020_READNEXT_PROC();
            if (pgmRtn) return;
        } else if (BPCBFHTD.INPUT.FUNC == '3') {
            B030_ENDBR_PROC();
            if (pgmRtn) return;
        } else if (BPCBFHTD.INPUT.FUNC == '4') {
            B040_GROUPMAX_PROC();
            if (pgmRtn) return;
        } else if (BPCBFHTD.INPUT.FUNC == '5') {
            B050_READ_PROC();
            if (pgmRtn) return;
        } else if (BPCBFHTD.INPUT.FUNC == '6') {
        } else if (BPCBFHTD.INPUT.FUNC == '7') {
            B010_STARTBR_STATEMENT_PROC();
            if (pgmRtn) return;
        } else if (BPCBFHTD.INPUT.FUNC == '8') {
            B080_COUNT_PROC();
            if (pgmRtn) return;
        } else if (BPCBFHTD.INPUT.FUNC == '9') {
            B090_FIRST_READNEXT_CN();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUNC_ERROR, BPCBFHTD.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "BPZBFHTD INVALID FUNC(" + BPCBFHTD.INPUT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.REC_LEN);
        WS_LENGTH = 10240;
        CEP.TRC(SCCGWA, WS_LENGTH);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRFHIS);
        LK_REC = JIBS_tmp_str[0];
    }
    public void B005_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_STR_DATE = BPCBFHTD.INPUT.STR_AC_DT;
        CEP.TRC(SCCGWA, WS_STR_DATE);
        WS_END_DATE = BPCBFHTD.INPUT.END_AC_DT;
        CEP.TRC(SCCGWA, WS_END_DATE);
        WS_STR_TR_DATE = BPCBFHTD.INPUT.STR_TR_DT;
        CEP.TRC(SCCGWA, WS_STR_TR_DATE);
        WS_END_TR_DATE = BPCBFHTD.INPUT.END_TR_DT;
        CEP.TRC(SCCGWA, WS_END_TR_DATE);
        WS_END_AMT = BPCBFHTD.INPUT.END_AMT;
        CEP.TRC(SCCGWA, WS_END_AMT);
        WS_STR_AMT = BPCBFHTD.INPUT.STR_AMT;
        CEP.TRC(SCCGWA, WS_STR_AMT);
        WS_END_TR_TM = BPCBFHTD.INPUT.END_TR_TM;
        CEP.TRC(SCCGWA, WS_END_TR_TM);
        WS_STR_TR_TM = BPCBFHTD.INPUT.STR_TR_TM;
        CEP.TRC(SCCGWA, WS_STR_TR_TM);
        if (WS_STR_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_STR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (WS_END_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            WS_END_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (WS_STR_TR_TM > WS_END_TR_TM) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_STR_END_TIME_ERR, BPCBFHTD.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCBFHTD.INPUT.PROD_CD.equalsIgnoreCase("99999999")) {
            WS_PROD_CD_FLG = 'Y';
            WS_PROD_CD_01 = PROD_CD_01;
            WS_PROD_CD_02 = PROD_CD_02;
        } else {
            WS_PROD_CD_FLG = 'N';
        }
        WS_AC = BPCBFHTD.INPUT.AC;
        WS_TX_CCY = BPCBFHTD.INPUT.TX_CCY;
        WS_REF_NO = BPCBFHTD.INPUT.REF_NO;
        WS_JRNNO = BPCBFHTD.INPUT.JRNNO;
        WS_TX_TOOL = BPCBFHTD.INPUT.TX_TOOL;
        WS_TX_TELR = BPCBFHTD.INPUT.TX_TLR;
        WS_TX_CD = BPCBFHTD.INPUT.TX_CD;
        WS_CI_NO = BPCBFHTD.INPUT.CI_NO;
        WS_TX_BR = BPCBFHTD.INPUT.TX_BR;
        WS_TX_DP = BPCBFHTD.INPUT.TX_DP;
        WS_TX_DC = BPCBFHTD.INPUT.DC_FLG;
        WS_MAKER_ID = BPCBFHTD.INPUT.MAKER_ID;
        WS_CHECKER_ID = BPCBFHTD.INPUT.CHECKER_ID;
        WS_PROD_CD = BPCBFHTD.INPUT.PROD_CD;
        WS_TX_MMO = BPCBFHTD.INPUT.TX_MMO;
        CEP.TRC(SCCGWA, WS_JRNNO);
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.AC);
        CEP.TRC(SCCGWA, WS_AC);
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.TX_CCY);
        CEP.TRC(SCCGWA, WS_TX_CCY);
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.REF_NO);
        CEP.TRC(SCCGWA, WS_REF_NO);
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.JRNNO);
        CEP.TRC(SCCGWA, WS_JRNNO);
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.TX_TOOL);
        CEP.TRC(SCCGWA, WS_TX_TOOL);
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.TX_TLR);
        CEP.TRC(SCCGWA, WS_TX_TELR);
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.TX_CD);
        CEP.TRC(SCCGWA, WS_TX_CD);
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.CI_NO);
        CEP.TRC(SCCGWA, WS_CI_NO);
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.TX_BR);
        CEP.TRC(SCCGWA, WS_TX_BR);
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.TX_DP);
        CEP.TRC(SCCGWA, WS_TX_DP);
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.DC_FLG);
        CEP.TRC(SCCGWA, WS_MAKER_ID);
        CEP.TRC(SCCGWA, WS_CHECKER_ID);
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.SORT_FLG);
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.PROD_CD);
        CEP.TRC(SCCGWA, WS_STR_DATE);
        CEP.TRC(SCCGWA, WS_END_DATE);
    }
    public void B080_COUNT_PROC() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'N';
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.AC);
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.TX_CCY);
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.REF_NO);
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.JRNNO);
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.TX_TLR);
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.TX_CD);
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.TX_TOOL);
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.CI_NO);
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.DC_FLG);
        if (BPCBFHTD.INPUT.BROWS_COND == '2') {
            T000_COUNT_AC();
            if (pgmRtn) return;
        } else if (BPCBFHTD.INPUT.BROWS_COND == '3') {
            T000_COUNT_ACBRTLR();
            if (pgmRtn) return;
        } else if (BPCBFHTD.INPUT.BROWS_COND == '4') {
            T000_COUNT_TOOL_BRTLR();
            if (pgmRtn) return;
        } else if (BPCBFHTD.INPUT.BROWS_COND == '5') {
            T000_COUNT_BRTLR();
            if (pgmRtn) return;
        } else if (BPCBFHTD.INPUT.BROWS_COND == '1') {
            T000_COUNT_JRN();
            if (pgmRtn) return;
        } else if (BPCBFHTD.INPUT.BROWS_COND == '7') {
            T000_COUNT_AC_PROD();
            if (pgmRtn) return;
        } else if (BPCBFHTD.INPUT.BROWS_COND == '8') {
            T000_COUNT_TOOL_PROD();
            if (pgmRtn) return;
        } else if (BPCBFHTD.INPUT.BROWS_COND == '9') {
            T000_COUNT_AC_JUMP();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NONE_COND, BPCBFHTD.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "BPZBFHTD INVALID FUNC(" + BPCBFHTD.INPUT.BROWS_COND + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        CEP.TRC(SCCGWA, WS_COUNT_NO);
        if (WS_BRW_FHIS_FLAG == 'N') {
            CEP.TRC(SCCGWA, "00000000");
            BPCBFHTD.OUTPUT.COUNT_NO = 0;
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCBFHTD.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            BPCBFHTD.OUTPUT.COUNT_NO = WS_COUNT_NO;
        }
        CEP.TRC(SCCGWA, BPCBFHTD.OUTPUT.COUNT_NO);
    }
    public void B010_STARTBR_PROC() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'N';
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.AC);
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.TX_CCY);
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.REF_NO);
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.JRNNO);
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.TX_TLR);
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.TX_CD);
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.TX_TOOL);
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.CI_NO);
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.DC_FLG);
        if (BPCBFHTD.INPUT.SORT_FLG != 'Y') {
            BPCBFHTD.INPUT.SORT_FLG = 'N';
        }
        if (BPCBFHTD.INPUT.BROWS_COND == '1') {
            T000_STARTBR_JRN();
            if (pgmRtn) return;
        } else if (BPCBFHTD.INPUT.BROWS_COND == '3') {
            T000_STARTBR_ACBRTLR();
            if (pgmRtn) return;
        } else if (BPCBFHTD.INPUT.BROWS_COND == '4') {
            T000_STARTBR_TOOL_BRTLR();
            if (pgmRtn) return;
        } else if (BPCBFHTD.INPUT.BROWS_COND == '5') {
            T000_STARTBR_BRTLR();
            if (pgmRtn) return;
        } else if (BPCBFHTD.INPUT.BROWS_COND == '2') {
            T000_STARTBR_AC();
            if (pgmRtn) return;
        } else if (BPCBFHTD.INPUT.BROWS_COND == '7') {
            T000_STARTBR_AC_PROD();
            if (pgmRtn) return;
        } else if (BPCBFHTD.INPUT.BROWS_COND == '8') {
            T000_STARTBR_TOOL_PROD();
            if (pgmRtn) return;
        } else if (BPCBFHTD.INPUT.BROWS_COND == '9') {
            T000_STARTBR_AC_JUMP();
            if (pgmRtn) return;
        } else if (BPCBFHTD.INPUT.BROWS_COND == 'A') {
            T000_STARTBR_JRN_ALL();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NONE_COND, BPCBFHTD.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "BPZBFHTD INVALID FUNC(" + BPCBFHTD.INPUT.BROWS_COND + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        if (WS_BRW_FHIS_FLAG == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCBFHTD.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_COUNT_AC() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        CEP.TRC(SCCGWA, "COUNT_BY_AC");
        if (WS_TX_CCY.trim().length() == 0) {
            null.set = "WS-COUNT-NO=COUNT(1)";
            BPTFHIS_RD = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
            else BPTFHIS_RD.TableName = "BPTFHIS2";
            BPTFHIS_RD.where = "AC = :WS_AC "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND ( ' ' = :WS_TX_DC "
                + "OR DRCRFLG = :WS_TX_DC ) "
                + "AND PRINT_FLG = 'Y'";
            IBS.GROUP(SCCGWA, BPRFHIS, this, BPTFHIS_RD);
        } else {
            null.set = "WS-COUNT-NO=COUNT(1)";
            BPTFHIS_RD = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
            else BPTFHIS_RD.TableName = "BPTFHIS2";
            BPTFHIS_RD.where = "AC = :WS_AC "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND ( ' ' = :WS_TX_DC "
                + "OR DRCRFLG = :WS_TX_DC ) "
                + "AND PRINT_FLG = 'Y'";
            IBS.GROUP(SCCGWA, BPRFHIS, this, BPTFHIS_RD);
        }
    }
    public void T000_COUNT_BR_TOD() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        if (WS_TX_CCY.trim().length() == 0) {
            null.set = "WS-COUNT-NO=COUNT(1)";
            BPTFHIS_RD = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
            else BPTFHIS_RD.TableName = "BPTFHIS2";
            BPTFHIS_RD.where = "AC_DT = :WS_STR_DATE "
                + "AND TX_BR = :WS_TX_BR "
                + "AND ( ' ' = :WS_TX_DC "
                + "OR DRCRFLG = :WS_TX_DC ) "
                + "AND PRINT_FLG = 'Y'";
            IBS.GROUP(SCCGWA, BPRFHIS, this, BPTFHIS_RD);
        } else {
            null.set = "WS-COUNT-NO=COUNT(1)";
            BPTFHIS_RD = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
            else BPTFHIS_RD.TableName = "BPTFHIS2";
            BPTFHIS_RD.where = "AC_DT = :WS_STR_DATE "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND ( ' ' = :WS_TX_DC "
                + "OR DRCRFLG = :WS_TX_DC ) "
                + "AND PRINT_FLG = 'Y'";
            IBS.GROUP(SCCGWA, BPRFHIS, this, BPTFHIS_RD);
        }
    }
    public void T000_COUNT_JRN() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        CEP.TRC(SCCGWA, WS_JRNNO);
        null.set = "WS-COUNT-NO=COUNT(1)";
        BPTFHIS_RD = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
        else BPTFHIS_RD.TableName = "BPTFHIS2";
        BPTFHIS_RD.where = "JRNNO = :WS_JRNNO "
            + "AND AC_DT = :WS_STR_DATE "
            + "AND PRINT_FLG = 'Y'";
        IBS.GROUP(SCCGWA, BPRFHIS, this, BPTFHIS_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_COUNT_AC_PROD() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        CEP.TRC(SCCGWA, WS_AC);
        CEP.TRC(SCCGWA, WS_PROD_CD);
        if (WS_TX_CCY.trim().length() == 0) {
            if (WS_PROD_CD_FLG != 'Y') {
                null.set = "WS-COUNT-NO=COUNT(1)";
                BPTFHIS_RD = new DBParm();
                if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
                else BPTFHIS_RD.TableName = "BPTFHIS2";
                BPTFHIS_RD.where = "AC = :WS_AC "
                    + "AND PROD_CD = :WS_PROD_CD "
                    + "AND AC_DT = :WS_STR_DATE "
                    + "AND PRINT_FLG = 'Y'";
                IBS.GROUP(SCCGWA, BPRFHIS, this, BPTFHIS_RD);
            } else {
                null.set = "WS-COUNT-NO=COUNT(1)";
                BPTFHIS_RD = new DBParm();
                if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
                else BPTFHIS_RD.TableName = "BPTFHIS2";
                BPTFHIS_RD.where = "AC = :WS_AC "
                    + "AND ( PROD_CD = :WS_PROD_CD_01 "
                    + "OR PROD_CD = :WS_PROD_CD_02 ) "
                    + "AND AC_DT = :WS_STR_DATE "
                    + "AND PRINT_FLG = 'Y'";
                IBS.GROUP(SCCGWA, BPRFHIS, this, BPTFHIS_RD);
            }
        } else {
            if (WS_PROD_CD_FLG != 'Y') {
                null.set = "WS-COUNT-NO=COUNT(1)";
                BPTFHIS_RD = new DBParm();
                if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
                else BPTFHIS_RD.TableName = "BPTFHIS2";
                BPTFHIS_RD.where = "AC = :WS_AC "
                    + "AND TX_CCY = :WS_TX_CCY "
                    + "AND PROD_CD = :WS_PROD_CD "
                    + "AND AC_DT = :WS_STR_DATE "
                    + "AND PRINT_FLG = 'Y'";
                IBS.GROUP(SCCGWA, BPRFHIS, this, BPTFHIS_RD);
            } else {
                null.set = "WS-COUNT-NO=COUNT(1)";
                BPTFHIS_RD = new DBParm();
                if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
                else BPTFHIS_RD.TableName = "BPTFHIS2";
                BPTFHIS_RD.where = "AC = :WS_AC "
                    + "AND TX_CCY = :WS_TX_CCY "
                    + "AND ( PROD_CD = :WS_PROD_CD_01 "
                    + "OR PROD_CD = :WS_PROD_CD_02 ) "
                    + "AND AC_DT = :WS_STR_DATE "
                    + "AND PRINT_FLG = 'Y'";
                IBS.GROUP(SCCGWA, BPRFHIS, this, BPTFHIS_RD);
            }
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_COUNT_TOOL_PROD() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        CEP.TRC(SCCGWA, WS_TX_TOOL);
        CEP.TRC(SCCGWA, WS_PROD_CD);
        if (WS_TX_CCY.trim().length() == 0) {
            if (WS_PROD_CD_FLG != 'Y') {
                null.set = "WS-COUNT-NO=COUNT(1)";
                BPTFHIS_RD = new DBParm();
                if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
                else BPTFHIS_RD.TableName = "BPTFHIS2";
                BPTFHIS_RD.where = "TX_TOOL = :WS_TX_TOOL "
                    + "AND PROD_CD = :WS_PROD_CD "
                    + "AND AC_DT = :WS_STR_DATE "
                    + "AND PRINT_FLG = 'Y'";
                IBS.GROUP(SCCGWA, BPRFHIS, this, BPTFHIS_RD);
            } else {
                null.set = "WS-COUNT-NO=COUNT(1)";
                BPTFHIS_RD = new DBParm();
                if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
                else BPTFHIS_RD.TableName = "BPTFHIS2";
                BPTFHIS_RD.where = "TX_TOOL = :WS_TX_TOOL "
                    + "AND ( PROD_CD = :WS_PROD_CD_01 "
                    + "OR PROD_CD = :WS_PROD_CD_02 ) "
                    + "AND AC_DT = :WS_STR_DATE "
                    + "AND PRINT_FLG = 'Y'";
                IBS.GROUP(SCCGWA, BPRFHIS, this, BPTFHIS_RD);
            }
        } else {
            if (WS_PROD_CD_FLG != 'Y') {
                null.set = "WS-COUNT-NO=COUNT(1)";
                BPTFHIS_RD = new DBParm();
                if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
                else BPTFHIS_RD.TableName = "BPTFHIS2";
                BPTFHIS_RD.where = "TX_TOOL = :WS_TX_TOOL "
                    + "AND TX_CCY = :WS_TX_CCY "
                    + "AND PROD_CD = :WS_PROD_CD "
                    + "AND AC_DT = :WS_STR_DATE "
                    + "AND PRINT_FLG = 'Y'";
                IBS.GROUP(SCCGWA, BPRFHIS, this, BPTFHIS_RD);
            } else {
                null.set = "WS-COUNT-NO=COUNT(1)";
                BPTFHIS_RD = new DBParm();
                if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
                else BPTFHIS_RD.TableName = "BPTFHIS2";
                BPTFHIS_RD.where = "TX_TOOL = :WS_TX_TOOL "
                    + "AND TX_CCY = :WS_TX_CCY "
                    + "AND ( PROD_CD = :WS_PROD_CD_01 "
                    + "OR PROD_CD = :WS_PROD_CD_02 ) "
                    + "AND AC_DT = :WS_STR_DATE "
                    + "AND PRINT_FLG = 'Y'";
                IBS.GROUP(SCCGWA, BPRFHIS, this, BPTFHIS_RD);
            }
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_COUNT_TLR_TOD() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        null.set = "WS-COUNT-NO=COUNT(1)";
        BPTFHIS_RD = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
        else BPTFHIS_RD.TableName = "BPTFHIS2";
        BPTFHIS_RD.where = "AC_DT = :WS_STR_DATE "
            + "AND TX_TLR = :WS_TX_TELR "
            + "AND ( ' ' = :WS_TX_DC "
            + "OR DRCRFLG = :WS_TX_DC ) "
            + "AND PRINT_FLG = 'Y'";
        IBS.GROUP(SCCGWA, BPRFHIS, this, BPTFHIS_RD);
    }
    public void T000_COUNT_TLR_BR_TOD() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        null.set = "WS-COUNT-NO=COUNT(1)";
        BPTFHIS_RD = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
        else BPTFHIS_RD.TableName = "BPTFHIS2";
        BPTFHIS_RD.where = "AC_DT = :WS_STR_DATE "
            + "AND TX_TLR = :WS_TX_TELR "
            + "AND TX_BR = :WS_TX_BR "
            + "AND ( ' ' = :WS_TX_DC "
            + "OR DRCRFLG = :WS_TX_DC ) "
            + "AND PRINT_FLG = 'Y'";
        IBS.GROUP(SCCGWA, BPRFHIS, this, BPTFHIS_RD);
    }
    public void T000_COUNT_ACBRTLR() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_AC ");
        null.set = "WS-COUNT-NO=COUNT(1)";
        BPTFHIS_RD = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
        else BPTFHIS_RD.TableName = "BPTFHIS2";
        BPTFHIS_RD.where = "AC = :WS_AC "
            + "AND TX_BR = :WS_TX_BR "
            + "AND TX_TLR = :WS_TX_TELR "
            + "AND AC_DT = :WS_STR_DATE "
            + "AND ( ' ' = :WS_TX_CCY "
            + "OR TX_CCY = :WS_TX_CCY ) "
            + "AND ( ' ' = :WS_TX_DC "
            + "OR DRCRFLG = :WS_TX_DC ) "
            + "AND PRINT_FLG = 'Y'";
        IBS.GROUP(SCCGWA, BPRFHIS, this, BPTFHIS_RD);
    }
    public void T000_COUNT_TOOL_BRTLR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TX_BR);
        CEP.TRC(SCCGWA, WS_TX_TELR);
        CEP.TRC(SCCGWA, WS_TX_CCY);
        CEP.TRC(SCCGWA, WS_TX_CCY);
        CEP.TRC(SCCGWA, WS_TX_TOOL);
        WS_BRW_FHIS_FLAG = 'Y';
        if (WS_TX_BR != 0 
                && WS_TX_TELR.trim().length() > 0 
                && WS_TX_CCY.trim().length() == 0) {
            CEP.TRC(SCCGWA, "COUNT_BY_TOOL_BR_TLR");
            null.set = "WS-COUNT-NO=COUNT(1)";
            BPTFHIS_RD = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
            else BPTFHIS_RD.TableName = "BPTFHIS2";
            BPTFHIS_RD.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND ( ' ' = :WS_TX_DC "
                + "OR DRCRFLG = :WS_TX_DC ) "
                + "AND PRINT_FLG = 'Y'";
            IBS.GROUP(SCCGWA, BPRFHIS, this, BPTFHIS_RD);
        } else if (WS_TX_BR == 0 
                && WS_TX_TELR.trim().length() == 0 
                && WS_TX_CCY.trim().length() == 0) {
            CEP.TRC(SCCGWA, "COUNT_BY_TOOL");
            null.set = "WS-COUNT-NO=COUNT(1)";
            BPTFHIS_RD = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
            else BPTFHIS_RD.TableName = "BPTFHIS2";
            BPTFHIS_RD.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND ( ' ' = :WS_TX_DC "
                + "OR DRCRFLG = :WS_TX_DC ) "
                + "AND PRINT_FLG = 'Y'";
            IBS.GROUP(SCCGWA, BPRFHIS, this, BPTFHIS_RD);
        } else if (WS_TX_BR != 0 
                && BPCBFHTD.INPUT.TX_TLR.trim().length() > 0 
                && WS_TX_CCY.trim().length() > 0) {
            CEP.TRC(SCCGWA, "COUNT_BY_TOOL_BR_TLR_CCY");
            null.set = "WS-COUNT-NO=COUNT(1)";
            BPTFHIS_RD = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
            else BPTFHIS_RD.TableName = "BPTFHIS2";
            BPTFHIS_RD.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND ( ' ' = :WS_TX_DC "
                + "OR DRCRFLG = :WS_TX_DC ) "
                + "AND PRINT_FLG = 'Y'";
            IBS.GROUP(SCCGWA, BPRFHIS, this, BPTFHIS_RD);
        } else if (WS_TX_BR == 0 
                && WS_TX_TELR.trim().length() == 0 
                && WS_TX_CCY.trim().length() > 0) {
            CEP.TRC(SCCGWA, "COUNT_BY_TOOL_CCY");
            null.set = "WS-COUNT-NO=COUNT(1)";
            BPTFHIS_RD = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
            else BPTFHIS_RD.TableName = "BPTFHIS2";
            BPTFHIS_RD.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND ( ' ' = :WS_TX_DC "
                + "OR DRCRFLG = :WS_TX_DC ) "
                + "AND PRINT_FLG = 'Y'";
            IBS.GROUP(SCCGWA, BPRFHIS, this, BPTFHIS_RD);
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NONE_COND, BPCBFHTD.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_COUNT_BRTLR() throws IOException,SQLException,Exception {
        if (BPCBFHTD.INPUT.TX_TLR.trim().length() > 0 
            && BPCBFHTD.INPUT.TX_BR != 0) {
            T000_COUNT_TLR_BR_TOD();
            if (pgmRtn) return;
        } else {
            if (BPCBFHTD.INPUT.TX_TLR.trim().length() > 0) {
                T000_COUNT_TLR_TOD();
                if (pgmRtn) return;
            } else {
                T000_COUNT_BR_TOD();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_COUNT_AC_JUMP() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        CEP.TRC(SCCGWA, "COUNT_BY_AC");
        if (WS_TX_CCY.trim().length() == 0) {
            null.set = "WS-COUNT-NO=COUNT(1)";
            BPTFHIS_RD = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
            else BPTFHIS_RD.TableName = "BPTFHIS2";
            BPTFHIS_RD.where = "AC = :WS_AC "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND TX_MMO NOT IN ( 'X15' , 'X16' , 'GTU' , 'GTV' ) "
                + "AND ( ' ' = :WS_TX_DC "
                + "OR DRCRFLG = :WS_TX_DC ) "
                + "AND PRINT_FLG = 'Y'";
            IBS.GROUP(SCCGWA, BPRFHIS, this, BPTFHIS_RD);
        } else {
            null.set = "WS-COUNT-NO=COUNT(1)";
            BPTFHIS_RD = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
            else BPTFHIS_RD.TableName = "BPTFHIS2";
            BPTFHIS_RD.where = "AC = :WS_AC "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND TX_MMO NOT IN ( 'X15' , 'X16' , 'GTU' , 'GTV' ) "
                + "AND ( ' ' = :WS_TX_DC "
                + "OR DRCRFLG = :WS_TX_DC ) "
                + "AND PRINT_FLG = 'Y'";
            IBS.GROUP(SCCGWA, BPRFHIS, this, BPTFHIS_RD);
        }
    }
    public void T000_STARTBR_AC_JUMP() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        CEP.TRC(SCCGWA, "BROWSE_BY_AC");
        if (BPCBFHTD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC != ' ') {
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "AC = :WS_AC "
                + "AND TX_MMO NOT IN ( 'X15' , 'X16' , 'GTU' , 'GTV' ) "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (BPCBFHTD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC != ' ') {
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "AC = :WS_AC "
                + "AND TX_MMO NOT IN ( 'X15' , 'X16' , 'GTU' , 'GTV' ) "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (BPCBFHTD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC != ' ') {
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "AC = :WS_AC "
                + "AND TX_MMO NOT IN ( 'X15' , 'X16' , 'GTU' , 'GTV' ) "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (BPCBFHTD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC != ' ') {
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "AC = :WS_AC "
                + "AND TX_MMO NOT IN ( 'X15' , 'X16' , 'GTU' , 'GTV' ) "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (BPCBFHTD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC == ' ') {
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "AC = :WS_AC "
                + "AND TX_MMO NOT IN ( 'X15' , 'X16' , 'GTU' , 'GTV' ) "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (BPCBFHTD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC == ' ') {
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "AC = :WS_AC "
                + "AND TX_MMO NOT IN ( 'X15' , 'X16' , 'GTU' , 'GTV' ) "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (BPCBFHTD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC == ' ') {
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "AC = :WS_AC "
                + "AND TX_MMO NOT IN ( 'X15' , 'X16' , 'GTU' , 'GTV' ) "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (BPCBFHTD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC == ' ') {
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "AC = :WS_AC "
                + "AND TX_MMO NOT IN ( 'X15' , 'X16' , 'GTU' , 'GTV' ) "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NONE_COND, BPCBFHTD.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_AC() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        CEP.TRC(SCCGWA, "BROWSE_BY_AC");
        if (BPCBFHTD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC != ' ') {
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "AC = :WS_AC "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (BPCBFHTD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC != ' ') {
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "AC = :WS_AC "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (BPCBFHTD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC != ' ') {
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "AC = :WS_AC "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (BPCBFHTD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC != ' ') {
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "AC = :WS_AC "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (BPCBFHTD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC == ' ') {
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "AC = :WS_AC "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (BPCBFHTD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC == ' ') {
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "AC = :WS_AC "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (BPCBFHTD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC == ' ') {
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "AC = :WS_AC "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (BPCBFHTD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC == ' ') {
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "AC = :WS_AC "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NONE_COND, BPCBFHTD.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_JRN() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        CEP.TRC(SCCGWA, WS_JRNNO);
        if (BPCBFHTD.INPUT.SORT_FLG == 'Y') {
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "JRNNO = :WS_JRNNO "
                + "AND AC_DT = :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else {
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "JRNNO = :WS_JRNNO "
                + "AND AC_DT = :WS_END_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_STARTBR_JRN_ALL() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        CEP.TRC(SCCGWA, WS_JRNNO);
        if (BPCBFHTD.INPUT.SORT_FLG == 'Y') {
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "JRNNO = :WS_JRNNO "
                + "AND AC_DT = :WS_END_DATE";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else {
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "JRNNO = :WS_JRNNO "
                + "AND AC_DT = :WS_END_DATE";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_STARTBR_AC_PROD() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        CEP.TRC(SCCGWA, WS_AC);
        CEP.TRC(SCCGWA, WS_PROD_CD);
        if (BPCBFHTD.INPUT.SORT_FLG == 'Y') {
            if (WS_TX_CCY.trim().length() == 0) {
                if (WS_PROD_CD_FLG != 'Y') {
                    BPTFHIS_BR.rp = new DBParm();
                    if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
                    else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
                    BPTFHIS_BR.rp.where = "AC = :WS_AC "
                        + "AND PROD_CD = :WS_PROD_CD "
                        + "AND AC_DT = :WS_END_DATE "
                        + "AND PRINT_FLG = 'Y'";
                    BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
                    IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
                } else {
                    BPTFHIS_BR.rp = new DBParm();
                    if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
                    else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
                    BPTFHIS_BR.rp.where = "AC = :WS_AC "
                        + "AND ( PROD_CD = :WS_PROD_CD_01 "
                        + "OR PROD_CD = :WS_PROD_CD_02 ) "
                        + "AND AC_DT = :WS_END_DATE "
                        + "AND PRINT_FLG = 'Y'";
                    BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
                    IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
                }
            } else {
                if (WS_PROD_CD_FLG != 'Y') {
                    BPTFHIS_BR.rp = new DBParm();
                    if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
                    else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
                    BPTFHIS_BR.rp.where = "AC = :WS_AC "
                        + "AND TX_CCY = :WS_TX_CCY "
                        + "AND PROD_CD = :WS_PROD_CD "
                        + "AND AC_DT = :WS_END_DATE "
                        + "AND PRINT_FLG = 'Y'";
                    BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
                    IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
                } else {
                    BPTFHIS_BR.rp = new DBParm();
                    if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
                    else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
                    BPTFHIS_BR.rp.where = "AC = :WS_AC "
                        + "AND TX_CCY = :WS_TX_CCY "
                        + "AND ( PROD_CD = :WS_PROD_CD_01 "
                        + "OR PROD_CD = :WS_PROD_CD_02 ) "
                        + "AND AC_DT = :WS_END_DATE "
                        + "AND PRINT_FLG = 'Y'";
                    BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
                    IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
                }
            }
        } else {
            if (WS_TX_CCY.trim().length() == 0) {
                if (WS_PROD_CD_FLG != 'Y') {
                    BPTFHIS_BR.rp = new DBParm();
                    if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
                    else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
                    BPTFHIS_BR.rp.where = "AC = :WS_AC "
                        + "AND PROD_CD = :WS_PROD_CD "
                        + "AND AC_DT = :WS_END_DATE "
                        + "AND PRINT_FLG = 'Y'";
                    BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
                } else {
                    BPTFHIS_BR.rp = new DBParm();
                    if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
                    else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
                    BPTFHIS_BR.rp.where = "AC = :WS_AC "
                        + "AND ( PROD_CD = :WS_PROD_CD_01 "
                        + "OR PROD_CD = :WS_PROD_CD_02 ) "
                        + "AND AC_DT = :WS_END_DATE "
                        + "AND PRINT_FLG = 'Y'";
                    BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
                }
            } else {
                if (WS_PROD_CD_FLG != 'Y') {
                    BPTFHIS_BR.rp = new DBParm();
                    if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
                    else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
                    BPTFHIS_BR.rp.where = "AC = :WS_AC "
                        + "AND TX_CCY = :WS_TX_CCY "
                        + "AND PROD_CD = :WS_PROD_CD "
                        + "AND AC_DT = :WS_END_DATE "
                        + "AND PRINT_FLG = 'Y'";
                    BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
                } else {
                    BPTFHIS_BR.rp = new DBParm();
                    if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
                    else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
                    BPTFHIS_BR.rp.where = "AC = :WS_AC "
                        + "AND TX_CCY = :WS_TX_CCY "
                        + "AND ( PROD_CD = :WS_PROD_CD_01 "
                        + "OR PROD_CD = :WS_PROD_CD_02 ) "
                        + "AND AC_DT = :WS_END_DATE "
                        + "AND PRINT_FLG = 'Y'";
                    BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                    IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
                }
            }
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_STARTBR_TOOL_PROD() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        CEP.TRC(SCCGWA, WS_TX_TOOL);
        CEP.TRC(SCCGWA, WS_PROD_CD);
        if (BPCBFHTD.INPUT.SORT_FLG == 'Y') {
            if (WS_PROD_CD_FLG != 'Y') {
                BPTFHIS_BR.rp = new DBParm();
                if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
                else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
                BPTFHIS_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                    + "AND PROD_CD = :WS_PROD_CD "
                    + "AND AC_DT = :WS_END_DATE "
                    + "AND PRINT_FLG = 'Y'";
                BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
                IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
            } else {
                BPTFHIS_BR.rp = new DBParm();
                if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
                else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
                BPTFHIS_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                    + "AND ( PROD_CD = :WS_PROD_CD_01 "
                    + "OR PROD_CD = :WS_PROD_CD_02 ) "
                    + "AND AC_DT = :WS_END_DATE "
                    + "AND PRINT_FLG = 'Y'";
                BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
                IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
            }
        } else {
            if (WS_PROD_CD_FLG != 'Y') {
                BPTFHIS_BR.rp = new DBParm();
                if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
                else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
                BPTFHIS_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                    + "AND PROD_CD = :WS_PROD_CD "
                    + "AND AC_DT = :WS_END_DATE "
                    + "AND PRINT_FLG = 'Y'";
                BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
            } else {
                BPTFHIS_BR.rp = new DBParm();
                if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
                else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
                BPTFHIS_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                    + "AND ( PROD_CD = :WS_PROD_CD_01 "
                    + "OR PROD_CD = :WS_PROD_CD_02 ) "
                    + "AND AC_DT = :WS_END_DATE "
                    + "AND PRINT_FLG = 'Y'";
                BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
            }
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_STARTBR_TLR_BR_TOD() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        if (BPCBFHTD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC != ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_CCY_DC DESC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (BPCBFHTD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC != ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_CCY_DC ASC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (BPCBFHTD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC != ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_CCY_DC DESC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (BPCBFHTD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC != ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_CCY_DC ASC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (BPCBFHTD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC == ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_CCY DESC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (BPCBFHTD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC == ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_CCY ASC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (BPCBFHTD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC == ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_CCY DESC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (BPCBFHTD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC == ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR ASC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NONE_COND, BPCBFHTD.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_ACBRTLR() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        if (BPCBFHTD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC != ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_AC_CCY_DC DESC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "AC = :WS_AC "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (BPCBFHTD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC != ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_AC_CCY_DC ASC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "AC = :WS_AC "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (BPCBFHTD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC != ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_AC_CCY_DC DESC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "AC = :WS_AC "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (BPCBFHTD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC != ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_AC_CCY_DC ASC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "AC = :WS_AC "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (BPCBFHTD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC == ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_AC_CCY DESC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "AC = :WS_AC "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (BPCBFHTD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC == ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_AC_CCY ASC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "AC = :WS_AC "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (BPCBFHTD.INPUT.SORT_FLG == 'Y' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC == ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_AC DESC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "AC = :WS_AC "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (BPCBFHTD.INPUT.SORT_FLG == 'N' 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC == ' ') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_AC ASC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "AC = :WS_AC "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NONE_COND, BPCBFHTD.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_TOOL_BRTLR() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        if (WS_TX_BR != 0 
                && WS_TX_TELR.trim().length() > 0 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC != ' ' 
                && BPCBFHTD.INPUT.SORT_FLG == 'Y') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_TOOL_CCY_DC DESC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (WS_TX_BR != 0 
                && WS_TX_TELR.trim().length() > 0 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC != ' ' 
                && BPCBFHTD.INPUT.SORT_FLG == 'N') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_TOOL_CCY_DC ASC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (WS_TX_BR != 0 
                && WS_TX_TELR.trim().length() > 0 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC == ' ' 
                && BPCBFHTD.INPUT.SORT_FLG == 'Y') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_TOOL_CCY DESC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (WS_TX_BR != 0 
                && WS_TX_TELR.trim().length() > 0 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC == ' ' 
                && BPCBFHTD.INPUT.SORT_FLG == 'N') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_TOOL_CCY ASC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (WS_TX_BR != 0 
                && WS_TX_TELR.trim().length() > 0 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC != ' ' 
                && BPCBFHTD.INPUT.SORT_FLG == 'Y') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_TOOL_DC DESC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (WS_TX_BR != 0 
                && WS_TX_TELR.trim().length() > 0 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC != ' ' 
                && BPCBFHTD.INPUT.SORT_FLG == 'N') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_TOOL_DC ASC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (WS_TX_BR != 0 
                && WS_TX_TELR.trim().length() > 0 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC == ' ' 
                && BPCBFHTD.INPUT.SORT_FLG == 'Y') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_TOOL DESC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (WS_TX_BR != 0 
                && WS_TX_TELR.trim().length() > 0 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC == ' ' 
                && BPCBFHTD.INPUT.SORT_FLG == 'N') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_TOOL ASC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (WS_TX_BR == 0 
                && WS_TX_TELR.trim().length() == 0 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC != ' ' 
                && BPCBFHTD.INPUT.SORT_FLG == 'Y') {
            CEP.TRC(SCCGWA, "BROWSE_BY_TOOL_DC_CCY DESC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (WS_TX_BR == 0 
                && WS_TX_TELR.trim().length() == 0 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC != ' ' 
                && BPCBFHTD.INPUT.SORT_FLG == 'N') {
            CEP.TRC(SCCGWA, "BROWSE_BY_TOOL_DC_CCY ASC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (WS_TX_BR == 0 
                && WS_TX_TELR.trim().length() == 0 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC != ' ' 
                && BPCBFHTD.INPUT.SORT_FLG == 'Y') {
            CEP.TRC(SCCGWA, "BROWSE_BY_TOOL_DC DESC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (WS_TX_BR == 0 
                && WS_TX_TELR.trim().length() == 0 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC != ' ' 
                && BPCBFHTD.INPUT.SORT_FLG == 'N') {
            CEP.TRC(SCCGWA, "BROWSE_BY_TOOL_DC ASC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND DRCRFLG = :WS_TX_DC "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (WS_TX_BR == 0 
                && WS_TX_TELR.trim().length() == 0 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC == ' ' 
                && BPCBFHTD.INPUT.SORT_FLG == 'Y') {
            CEP.TRC(SCCGWA, "BROWSE_BY_TOOL_CCY DESC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (WS_TX_BR == 0 
                && WS_TX_TELR.trim().length() == 0 
                && WS_TX_CCY.trim().length() > 0 
                && WS_TX_DC == ' ' 
                && BPCBFHTD.INPUT.SORT_FLG == 'N') {
            CEP.TRC(SCCGWA, "BROWSE_BY_TOOL_CCY ASC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND TX_CCY = :WS_TX_CCY "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (WS_TX_BR == 0 
                && WS_TX_TELR.trim().length() == 0 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC == ' ' 
                && BPCBFHTD.INPUT.SORT_FLG == 'Y') {
            CEP.TRC(SCCGWA, "BROWSE_BY_TOOL DESC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else if (WS_TX_BR == 0 
                && WS_TX_TELR.trim().length() == 0 
                && WS_TX_CCY.trim().length() == 0 
                && WS_TX_DC == ' ' 
                && BPCBFHTD.INPUT.SORT_FLG == 'N') {
            CEP.TRC(SCCGWA, "BROWSE_BY_TOOL ASC");
            BPTFHIS_BR.rp = new DBParm();
            if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
            else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS_BR.rp.where = "TX_TOOL = :WS_TX_TOOL "
                + "AND AC_DT = :WS_STR_DATE "
                + "AND PRINT_FLG = 'Y'";
            BPTFHIS_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NONE_COND, BPCBFHTD.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BRTLR() throws IOException,SQLException,Exception {
        T000_STARTBR_TLR_BR_TOD();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTFHIS_CN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRFHIS.KEY.AC_DT);
        CEP.TRC(SCCGWA, BPRFHIS.KEY.JRNNO);
        CEP.TRC(SCCGWA, BPRFHIS.KEY.JRN_SEQ);
        CEP.TRC(SCCGWA, BPRFHIS.KEY.AC);
        CEP.TRC(SCCGWA, BPRFHIS.KEY.AC_DT);
        CEP.TRC(SCCGWA, BPRFHIS.KEY.JRNNO);
        CEP.TRC(SCCGWA, BPRFHIS.KEY.JRN_SEQ);
        CEP.TRC(SCCGWA, BPRFHIS.KEY.AC);
    }
    public void B090_FIRST_READNEXT_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCBFHTD.INPUT.FETCH_NO);
        if (BPCBFHTD.INPUT.FETCH_NO > 0) {
            WS_RCD_SEQ = BPCBFHTD.INPUT.FETCH_NO;
            T000_READNEXT_FIRST_BPTFHIS();
            if (pgmRtn) return;
        } else {
            T000_READNEXT_BPTFHIS_CN();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPRFHIS.KEY.AC_DT);
        CEP.TRC(SCCGWA, BPRFHIS.KEY.JRNNO);
        CEP.TRC(SCCGWA, BPRFHIS.KEY.JRN_SEQ);
        CEP.TRC(SCCGWA, BPRFHIS.KEY.AC);
        CEP.TRC(SCCGWA, BPRFHIS.KEY.AC_DT);
        CEP.TRC(SCCGWA, BPRFHIS.KEY.JRNNO);
        CEP.TRC(SCCGWA, BPRFHIS.KEY.JRN_SEQ);
        CEP.TRC(SCCGWA, BPRFHIS.KEY.AC);
    }
    public void B030_ENDBR_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTFHIS();
        if (pgmRtn) return;
    }
    public void B050_READ_PROC() throws IOException,SQLException,Exception {
        T000_READ_BPTFHIS();
        if (pgmRtn) return;
    }
    public void B010_STARTBR_STATEMENT_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_FOR_STATEMENT();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_FOR_STATEMENT() throws IOException,SQLException,Exception {
        BPTFHIS_BR.rp = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_BR.rp.TableName = "BPTFHIS1";
        else BPTFHIS_BR.rp.TableName = "BPTFHIS2";
        BPTFHIS_BR.rp.where = "AC = :WS_AC "
            + "AND TX_CCY = :WS_TX_CCY "
            + "AND AC_DT = :WS_STR_DATE";
        BPTFHIS_BR.rp.order = "AC,JRNNO,JRN_SEQ";
        IBS.STARTBR(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
    }
    public void B040_GROUPMAX_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFHIS);
        T000_GROUPMAX_BPTFHIS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_MAX_JRN_SEQ);
        BPCBFHTD.OUTPUT.MAX_JRN_SEQ = WS_MAX_JRN_SEQ;
        CEP.TRC(SCCGWA, BPRFHIS);
    }
    public void T000_READNEXT_FIRST_BPTFHIS() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        CEP.TRC(SCCGWA, WS_RCD_SEQ);
        IBS.READNEXT(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        CEP.TRC(SCCGWA, BPRFHIS);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_END_OF_TABLE, BPCBFHTD.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
        }
    }
    public void T000_READNEXT_BPTFHIS_CN() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        CEP.TRC(SCCGWA, BPRFHIS);
        IBS.READNEXT(SCCGWA, BPRFHIS, this, BPTFHIS_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_END_OF_TABLE, BPCBFHTD.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
        }
    }
    public void T000_ENDBR_BPTFHIS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFHIS_BR);
    }
    public void T000_READ_BPTFHIS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFHIS.KEY.AC_DT);
        CEP.TRC(SCCGWA, BPRFHIS.KEY.JRNNO);
        CEP.TRC(SCCGWA, BPRFHIS.KEY.JRN_SEQ);
        BPTFHIS_RD = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
        else BPTFHIS_RD.TableName = "BPTFHIS2";
        BPTFHIS_RD.where = "AC_DT = :BPRFHIS.KEY.AC_DT "
            + "AND JRNNO = :BPRFHIS.KEY.JRNNO "
            + "AND JRN_SEQ = :BPRFHIS.KEY.JRN_SEQ";
        BPTFHIS_RD.fst = true;
        IBS.READ(SCCGWA, BPRFHIS, this, BPTFHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_REC_NOTFND, BPCBFHTD.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_GROUPMAX_BPTFHIS() throws IOException,SQLException,Exception {
        BPTFHIS_RD = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
        else BPTFHIS_RD.TableName = "BPTFHIS2";
        BPTFHIS_RD.set = "WS-MAX-JRN-SEQ=IFNULL(MAX(JRN_SEQ),0)";
        BPTFHIS_RD.where = "AC_DT = :BPRFHIS.KEY.AC_DT "
            + "AND JRNNO = :BPRFHIS.KEY.JRNNO";
        IBS.GROUP(SCCGWA, BPRFHIS, this, BPTFHIS_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCBFHTD.OUTPUT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCBFHTD = ");
            CEP.TRC(SCCGWA, BPCBFHTD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
