package com.hisun.TD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPQPRD;
import com.hisun.CI.CICCUST;
import com.hisun.DD.DDCIQBAL;
import com.hisun.DD.DDCMSG_ERROR_MSG;
import com.hisun.DD.DDCUCRAC;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCENPSW;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class TDZSGRP {
    boolean pgmRtn = false;
    String K_IRUL_TYP = "TIRUL";
    String K_OUTPUT_FMT = "TD001";
    String K_OUTPUT_FMT2 = "TD016";
    String K_OUTPUT_FMT3 = "TD017";
    String K_OUTPUT_FMT4 = "TD018";
    String K_AP_MMO = "TD";
    String K_DD_ACCO = "DD";
    String K_DC_ACCO = "DC";
    String K_NORM_CR_STS_TBL = "0001";
    String K_NORM_CR_STS_TBL_P = "0011";
    String K_NORM_CR_STS_TBL_C = "0021";
    String K_NORM_CR_STS_TBL_C_P = "0022";
    String K_CTGM_CR_STS_TBL_C_P = "0025";
    String K_NORM_CR_AMT_TBL = "0001";
    String K_NORM_TR_AMT_TBL = "0003";
    String K_SYS_ERR = "SC6001";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_MSGID = " ";
    String WS_MMO = " ";
    char WS_EC = ' ';
    char WS_PROC_TYP = ' ';
    short WS_I = 0;
    short WS_J = 0;
    short WS_IDX = 0;
    short WS_X = 0;
    short WS_C = 0;
    short WS_D = 0;
    char WS_MSREL_FLG = ' ';
    char WS_NOT_STANDARD_FLG = ' ';
    char WS_HMST_FLAG = ' ';
    char WS_YBT_AC_FLAG = ' ';
    char WS_SPE_FLG = ' ';
    char WS_OCAC_FLAG = ' ';
    char WS_OPP_AC_OSA_FLG = ' ';
    char WS_INT_AC_OSA_FLG = ' ';
    char WS_STL_AC_OSA_FLG = ' ';
    int WS_IAACR_CNT = 0;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCBINF SCCBINF = new SCCBINF();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    TDCUGRP TDCUGRP = new TDCUGRP();
    CICCAGT CICCAGT = new CICCAGT();
    CICCUST CICCUST = new CICCUST();
    DCCUMATP DCCUMATP = new DCCUMATP();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    SCCGWA SCCGWA;
    TDCSGRP TDCSGRP;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, TDCSGRP TDCSGRP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCSGRP = TDCSGRP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZSGRP return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDCUGRP);
        if (TDCSGRP.DRAW_TYP == '0') {
            if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101")) {
                B060_CHK_CI_CONTRACT();
                if (pgmRtn) return;
            } else {
                B070_DEL_CONTRACT();
                if (pgmRtn) return;
            }
        }
        TDCUGRP.AC_NO = TDCSGRP.AC_NO;
        TDCUGRP.PROD_CD = TDCSGRP.PROD_CD;
        TDCUGRP.ID_TYP = TDCSGRP.ID_TYP;
        TDCUGRP.ID_NO = TDCSGRP.ID_NO;
        TDCUGRP.DRAW_MTH = TDCSGRP.DRAW_MTH;
        TDCUGRP.TXN_AMT = TDCSGRP.TXN_AMT;
        TDCUGRP.PSW = TDCSGRP.PSW;
        TDCUGRP.GT_FLG = TDCSGRP.GT_FLG;
        TDCUGRP.OP_AC = TDCSGRP.OP_AC;
        TDCUGRP.DRAW_TYP = TDCSGRP.DRAW_TYP;
        TDCUGRP.AUTO_FLG = TDCSGRP.AUTO_FLG;
        S000_CALL_TDZUGRP();
        if (pgmRtn) return;
        if (TDCSGRP.TXN_AMT > 0) {
            B230_CALL_DD_CR_UNT();
            if (pgmRtn) return;
        }
    }
    public void B070_DEL_CONTRACT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUMATP);
        DCCUMATP.IO_AREA.FUNC_M = '3';
        DCCUMATP.IO_AREA.AGR_NO = TDCSGRP.OP_AC;
        DCCUMATP.IO_AREA.AC_NO = TDCSGRP.OP_AC;
        R000_GET_DD_INFO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQPRD.AC_TYPE);
        if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
        JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY_STS_WORD.substring(53 - 1, 53 + 1 - 1));
        if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
        JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY_STS_WORD.substring(58 - 1, 58 + 1 - 1));
        if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
        JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY_STS_WORD.substring(54 - 1, 54 + 1 - 1));
        if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
        JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY_STS_WORD.substring(59 - 1, 59 + 1 - 1));
        if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("035")) {
            DCCUMATP.IO_AREA.PROD_CDE = "9510000001";
            if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
            JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
            if (DDCIQBAL.DATA.CCY_STS_WORD.substring(53 - 1, 53 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.TRC(SCCGWA, "1");
                DCCUMATP.IO_AREA.PROC_TYP = "I";
                S000_CALL_DCZUMATP();
                if (pgmRtn) return;
            }
            if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
            JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
            if (DDCIQBAL.DATA.CCY_STS_WORD.substring(58 - 1, 58 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.TRC(SCCGWA, "2");
                DCCUMATP.IO_AREA.PROC_TYP = "O";
                S000_CALL_DCZUMATP();
                if (pgmRtn) return;
            }
        } else {
            DCCUMATP.IO_AREA.PROD_CDE = "9510000002";
            if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
            JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
            if (DDCIQBAL.DATA.CCY_STS_WORD.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.TRC(SCCGWA, "11");
                DCCUMATP.IO_AREA.PROC_TYP = "I";
                S000_CALL_DCZUMATP();
                if (pgmRtn) return;
            }
            if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
            JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
            if (DDCIQBAL.DATA.CCY_STS_WORD.substring(59 - 1, 59 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.TRC(SCCGWA, "22");
                DCCUMATP.IO_AREA.PROC_TYP = "O";
                S000_CALL_DCZUMATP();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_GET_DD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQBAL);
        DDCIQBAL.DATA.AC = TDCSGRP.OP_AC;
        DDCIQBAL.DATA.CCY = "156";
        DDCIQBAL.DATA.CCY_TYPE = '1';
        S000_CALL_DDZIQBAL();
        if (pgmRtn) return;
    }
    public void B060_CHK_CI_CONTRACT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'A';
        CICCUST.DATA.AGR_NO = TDCSGRP.AC_NO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NO);
        IBS.init(SCCGWA, CICCAGT);
        CICCAGT.DATA.CI_NO = CICCUST.O_DATA.O_CI_NO;
        CICCAGT.FUNC = 'B';
        CICCAGT.DATA.ENTY_TYP = '3';
        CICCAGT.DATA.AGR_NO = TDCSGRP.AC_NO;
        S000_CALL_CIZCAGT();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (TDCSGRP.DRAW_MTH == '1' 
            && TDCSGRP.PSW.trim().length() == 0) {
            CEP.TRC(SCCGWA, "PASSWORD MUST BE INPUT");
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = TDCSGRP.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        if (!BPCPQPRD.AC_TYPE.equalsIgnoreCase("035") 
            && !BPCPQPRD.AC_TYPE.equalsIgnoreCase("036")) {
            CEP.TRC(SCCGWA, "SHOULD BE 035 OR 036");
            CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
        }
    }
    public void B230_CALL_DD_CR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        CEP.TRC(SCCGWA, TDCUGRP.TXN_AMT_O);
        CEP.TRC(SCCGWA, TDCSGRP.TXN_AMT);
        DDCUCRAC.AC = TDCSGRP.OP_AC;
        DDCUCRAC.CARD_NO = TDCSGRP.OP_AC;
        DDCUCRAC.CCY = "156";
        DDCUCRAC.CCY_TYPE = '1';
        if (TDCUGRP.STSW == null) TDCUGRP.STSW = "";
        JIBS_tmp_int = TDCUGRP.STSW.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) TDCUGRP.STSW += " ";
        CEP.TRC(SCCGWA, TDCUGRP.STSW.substring(5 - 1, 5 + 1 - 1));
        if (TDCUGRP.STSW == null) TDCUGRP.STSW = "";
        JIBS_tmp_int = TDCUGRP.STSW.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) TDCUGRP.STSW += " ";
        if (TDCUGRP.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1") 
            && BPCPQPRD.AC_TYPE.equalsIgnoreCase("036")) {
            DDCUCRAC.TX_AMT = TDCUGRP.TXN_AMT_O;
            CEP.TRC(SCCGWA, "1111");
        } else {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
                && TDCSGRP.DRAW_TYP == '1' 
                && BPCPQPRD.AC_TYPE.equalsIgnoreCase("035")) {
                DDCUCRAC.TX_AMT = TDCSGRP.TXN_AMT;
                CEP.TRC(SCCGWA, "2222");
            } else {
                DDCUCRAC.TX_AMT = TDCUGRP.TXN_AMT_O;
                CEP.TRC(SCCGWA, "33333");
            }
        }
        DDCUCRAC.OTHER_AC = TDCSGRP.AC_NO;
        DDCUCRAC.OTHER_CCY = "156";
        CEP.TRC(SCCGWA, BPCPQPRD.AC_TYPE);
        if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("035")) {
            if (TDCSGRP.DRAW_TYP == '1') {
                DDCUCRAC.TX_MMO = "A004";
            } else {
                DDCUCRAC.TX_MMO = "A004";
            }
        }
        if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("036")) {
            DDCUCRAC.TX_MMO = "A004";
        }
        DDCUCRAC.OTHER_CCY = "156";
        DDCUCRAC.TX_TYPE = 'T';
        DDCUCRAC.BANK_CR_FLG = 'N';
        DDCUCRAC.BV_TYP = '1';
        DDCUCRAC.CHK_LMT_FLG = '4';
        S000_CALL_DDZUCRAC();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZCAGT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CHK-AC-AGT", CICCAGT);
