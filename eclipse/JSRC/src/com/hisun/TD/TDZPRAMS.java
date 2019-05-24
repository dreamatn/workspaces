package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDZPRAMS {
    int JIBS_tmp_int;
    DBParm TDTLMT_RD;
    String JIBS_tmp_str[] = new String[10];
    DBParm TDTOTHE_RD;
    DBParm TDTRATE_RD;
    String K_AP_MMO = "TD";
    String K_PRDP_TYP = "PRDPR";
    String K_HIS_FMT = "TDCPRDP";
    String K_TD_QH_SEQ = "QHSEQ";
    String K_TD_HD_SEQ = "HDSEQ";
    String K_HIS_RMK = "TD PRODUCT PARM MAINTENANCE";
    String K_SYS_ERR = "SC6001";
    String K_OUTPUT_FMT = "TD543";
    String WS_MSGID = " ";
    char WS_TABLE_FLG = ' ';
    char WS_TABLE_DEL = ' ';
    char WS_CHECK_FND = ' ';
    TDZPRAMS_CP_PROD_CD CP_PROD_CD = new TDZPRAMS_CP_PROD_CD();
    char WS_HD_FLG = ' ';
    char WS_ONTIM_FLG = ' ';
    int WS_SHX_DT = 0;
    int WS_SHI_DT = 0;
    TDZPRAMS_WS_HUODONG_NO WS_HUODONG_NO = new TDZPRAMS_WS_HUODONG_NO();
    TDZPRAMS_WS_QIHAO_NO WS_QIHAO_NO = new TDZPRAMS_WS_QIHAO_NO();
    String WS_AC_TYPE = " ";
    int WS_TERM_CQ_1 = 0;
    int WS_TERM_FX_2 = 0;
    int WS_OTHE_CNT = 0;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCBINF SCCBINF = new SCCBINF();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    TDCQPRD TDCQPRD = new TDCQPRD();
    TDCPRDP TDCPRDP = new TDCPRDP();
    TDCPRDP TDCPRDPO = new TDCPRDP();
    TDROTHE TDROTHE = new TDROTHE();
    TDCORAMA TDCORAMA = new TDCORAMA();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    CICMCGRP CICMCGRP = new CICMCGRP();
    BPCPQORG BPCPQORG = new BPCPQORG();
    TDRRATE TDRRATE = new TDRRATE();
    TDRLMT TDRLMT = new TDRLMT();
    TDCLMBP TDCLMBP = new TDCLMBP();
    TDCQPMP TDCQPMP = new TDCQPMP();
    TDCPROD TDCPROD = new TDCPROD();
    SCCGWA SCCGWA;
    TDCPRAM TDCPRAM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, TDCPRAM TDCPRAM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCPRAM = TDCPRAM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZPRAMS return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, TDROTHE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCPRAM.FUNC);
        if (TDCPRAM.FUNC == 'A') {
            B030_ADD_CHECK();
            B030_ADD_PROCESS();
        } else if (TDCPRAM.FUNC == 'M') {
            B040_MODIFY_CHECK();
            B040_MODIFY_PROCESS();
        } else if (TDCPRAM.FUNC == 'D') {
            B050_DELETE_CHECK();
            B050_DELETE_PROCESS();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + TDCPRAM.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B030_ADD_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCPRAM.PROD_CD);
        CEP.TRC(SCCGWA, TDCPRAM.CQ_TERM);
        CEP.TRC(SCCGWA, TDCPRAM.HD_FLG);
        CEP.TRC(SCCGWA, TDCPRAM.FX_TERM);
        if (TDCPRAM.PROD_CD.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PRODUCT_MUST_INPUT);
        }
        if (TDCPRAM.CQ_TERM.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_TERM_M_IPT);
        }
        if (TDCPRAM.HD_FLG == ' ') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_IPT);
        }
        if (TDCPRAM.CQ_TERM == null) TDCPRAM.CQ_TERM = "";
        JIBS_tmp_int = TDCPRAM.CQ_TERM.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) TDCPRAM.CQ_TERM += " ";
        if (TDCPRAM.FX_TERM == null) TDCPRAM.FX_TERM = "";
        JIBS_tmp_int = TDCPRAM.FX_TERM.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) TDCPRAM.FX_TERM += " ";
        if (TDCPRAM.CQ_TERM.substring(0, 1).equalsIgnoreCase(TDCPRAM.FX_TERM.substring(0, 1))) {
            if (TDCPRAM.CQ_TERM == null) TDCPRAM.CQ_TERM = "";
            JIBS_tmp_int = TDCPRAM.CQ_TERM.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCPRAM.CQ_TERM += " ";
            if (TDCPRAM.FX_TERM == null) TDCPRAM.FX_TERM = "";
            JIBS_tmp_int = TDCPRAM.FX_TERM.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCPRAM.FX_TERM += " ";
            if (TDCPRAM.CQ_TERM.substring(2 - 1, 2 + 3 - 1).compareTo(TDCPRAM.FX_TERM.substring(2 - 1, 2 + 3 - 1)) < 0) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_FX_TERM_ERR);
            }
            CEP.TRC(SCCGWA, "11111111111111");
        } else {
            CEP.TRC(SCCGWA, "22222222222222");
            if (TDCPRAM.CQ_TERM == null) TDCPRAM.CQ_TERM = "";
            JIBS_tmp_int = TDCPRAM.CQ_TERM.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCPRAM.CQ_TERM += " ";
            if (TDCPRAM.CQ_TERM.substring(2 - 1, 2 + 3 - 1).trim().length() == 0) WS_TERM_CQ_1 = 0;
            else WS_TERM_CQ_1 = Integer.parseInt(TDCPRAM.CQ_TERM.substring(2 - 1, 2 + 3 - 1));
            if (TDCPRAM.FX_TERM == null) TDCPRAM.FX_TERM = "";
            JIBS_tmp_int = TDCPRAM.FX_TERM.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCPRAM.FX_TERM += " ";
            if (TDCPRAM.FX_TERM.substring(2 - 1, 2 + 3 - 1).trim().length() == 0) WS_TERM_FX_2 = 0;
            else WS_TERM_FX_2 = Integer.parseInt(TDCPRAM.FX_TERM.substring(2 - 1, 2 + 3 - 1));
            if (TDCPRAM.CQ_TERM == null) TDCPRAM.CQ_TERM = "";
            JIBS_tmp_int = TDCPRAM.CQ_TERM.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCPRAM.CQ_TERM += " ";
            if (TDCPRAM.CQ_TERM.substring(0, 1).equalsIgnoreCase("M")) {
                WS_TERM_CQ_1 = WS_TERM_CQ_1 * 30;
            }
            if (TDCPRAM.FX_TERM == null) TDCPRAM.FX_TERM = "";
            JIBS_tmp_int = TDCPRAM.FX_TERM.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCPRAM.FX_TERM += " ";
            if (TDCPRAM.FX_TERM.substring(0, 1).equalsIgnoreCase("M")) {
                WS_TERM_FX_2 = WS_TERM_FX_2 * 30;
            }
            if (TDCPRAM.CQ_TERM == null) TDCPRAM.CQ_TERM = "";
            JIBS_tmp_int = TDCPRAM.CQ_TERM.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCPRAM.CQ_TERM += " ";
            if (TDCPRAM.CQ_TERM.substring(0, 1).equalsIgnoreCase("Y")) {
                WS_TERM_CQ_1 = WS_TERM_CQ_1 * 360;
            }
            if (TDCPRAM.FX_TERM == null) TDCPRAM.FX_TERM = "";
            JIBS_tmp_int = TDCPRAM.FX_TERM.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCPRAM.FX_TERM += " ";
            if (TDCPRAM.FX_TERM.substring(0, 1).equalsIgnoreCase("Y")) {
                WS_TERM_FX_2 = WS_TERM_FX_2 * 360;
            }
            if (WS_TERM_CQ_1 < WS_TERM_FX_2) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_FX_TERM_ERR);
            }
        }
        if (TDCPRAM.HD_FLG == '1') {
            B011_CHECK_HD_FLG1();
            B013_CHECK_HD_FLG();
        } else {
            B012_CHECK_HD_FLG0();
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = TDCPRAM.BR;
        S000_CALL_BPZPQORG();
        if (TDCPRAM.RUL_CD.trim().length() > 0) {
            IBS.init(SCCGWA, TDRRATE);
            CEP.TRC(SCCGWA, "***************");
            CEP.TRC(SCCGWA, TDCPRAM.RUL_CD);
            TDRRATE.KEY.RUL_CD = TDCPRAM.RUL_CD;
            CEP.TRC(SCCGWA, TDRRATE.KEY.RUL_CD);
            T000_READ_TDTRATE();
        }
    }
    public void B040_MODIFY_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDROTHE);
        TDROTHE.KEY.ACTI_NO = TDCPRAM.ACTI_NO;
        T000_READ_TDTOTHE_UPD();
        if (WS_TABLE_FLG == 'N') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ACTI_NO_NOT_EXIST);
        } else {
            WS_HD_FLG = TDROTHE.ACTI_FLG;
        }
        if (WS_HD_FLG == '1') {
            if (TDCPRAM.SHI_DT < SCCGWA.COMM_AREA.AC_DATE) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_EXPDT_LESS_ACDT);
            }
            if (TDCPRAM.SHI_DT > TDCPRAM.SDT 
                && TDCPRAM.SDT != 0) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_EXPDT_LESS_ACDT);
            }
            B030_A_M_CHECK();
            B011_CHECK_HD_FLG1();
        } else {
            if (TDCPRAM.SHI_DT < SCCGWA.COMM_AREA.AC_DATE) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_EXPDT_LESS_ACDT);
            }
            if (TDCPRAM.SHI_DT > TDCPRAM.SDT 
                && TDCPRAM.SDT != 0) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_EXPDT_LESS_ACDT);
            }
            if (TDROTHE.STR_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
                if (!TDCPRAM.CQ_TERM.equalsIgnoreCase(TDROTHE.TERM) 
                    || !TDCPRAM.CCY.equalsIgnoreCase(TDROTHE.CCY) 
                    || !TDCPRAM.RUL_CD.equalsIgnoreCase(TDROTHE.RUL_CD)) {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ONLY_MOD_SHIX_DT);
                }
            } else {
                B012_CHECK_HD_FLG0();
            }
        }
        if (TDCPRAM.CQ_TERM.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_TERM_M_IPT);
        }
        if (TDCPRAM.CQ_TERM == null) TDCPRAM.CQ_TERM = "";
        JIBS_tmp_int = TDCPRAM.CQ_TERM.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) TDCPRAM.CQ_TERM += " ";
        if (TDCPRAM.FX_TERM == null) TDCPRAM.FX_TERM = "";
        JIBS_tmp_int = TDCPRAM.FX_TERM.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) TDCPRAM.FX_TERM += " ";
        if (TDCPRAM.CQ_TERM.substring(0, 1).equalsIgnoreCase(TDCPRAM.FX_TERM.substring(0, 1))) {
            if (TDCPRAM.CQ_TERM == null) TDCPRAM.CQ_TERM = "";
            JIBS_tmp_int = TDCPRAM.CQ_TERM.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCPRAM.CQ_TERM += " ";
            if (TDCPRAM.FX_TERM == null) TDCPRAM.FX_TERM = "";
            JIBS_tmp_int = TDCPRAM.FX_TERM.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCPRAM.FX_TERM += " ";
            if (TDCPRAM.CQ_TERM.substring(2 - 1, 2 + 3 - 1).compareTo(TDCPRAM.FX_TERM.substring(2 - 1, 2 + 3 - 1)) < 0) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_FX_TERM_ERR);
            }
            CEP.TRC(SCCGWA, "33333333333333");
        } else {
            CEP.TRC(SCCGWA, "44444444444444");
            if (TDCPRAM.CQ_TERM == null) TDCPRAM.CQ_TERM = "";
            JIBS_tmp_int = TDCPRAM.CQ_TERM.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCPRAM.CQ_TERM += " ";
            if (TDCPRAM.CQ_TERM.substring(2 - 1, 2 + 3 - 1).trim().length() == 0) WS_TERM_CQ_1 = 0;
            else WS_TERM_CQ_1 = Integer.parseInt(TDCPRAM.CQ_TERM.substring(2 - 1, 2 + 3 - 1));
            if (TDCPRAM.FX_TERM == null) TDCPRAM.FX_TERM = "";
            JIBS_tmp_int = TDCPRAM.FX_TERM.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCPRAM.FX_TERM += " ";
            if (TDCPRAM.FX_TERM.substring(2 - 1, 2 + 3 - 1).trim().length() == 0) WS_TERM_FX_2 = 0;
            else WS_TERM_FX_2 = Integer.parseInt(TDCPRAM.FX_TERM.substring(2 - 1, 2 + 3 - 1));
            if (TDCPRAM.CQ_TERM == null) TDCPRAM.CQ_TERM = "";
            JIBS_tmp_int = TDCPRAM.CQ_TERM.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCPRAM.CQ_TERM += " ";
            if (TDCPRAM.CQ_TERM.substring(0, 1).equalsIgnoreCase("M")) {
                WS_TERM_CQ_1 = WS_TERM_CQ_1 * 30;
            }
            if (TDCPRAM.FX_TERM == null) TDCPRAM.FX_TERM = "";
            JIBS_tmp_int = TDCPRAM.FX_TERM.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCPRAM.FX_TERM += " ";
            if (TDCPRAM.FX_TERM.substring(0, 1).equalsIgnoreCase("M")) {
                WS_TERM_FX_2 = WS_TERM_FX_2 * 30;
            }
            if (TDCPRAM.CQ_TERM == null) TDCPRAM.CQ_TERM = "";
            JIBS_tmp_int = TDCPRAM.CQ_TERM.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCPRAM.CQ_TERM += " ";
            if (TDCPRAM.CQ_TERM.substring(0, 1).equalsIgnoreCase("Y")) {
                WS_TERM_CQ_1 = WS_TERM_CQ_1 * 360;
            }
            if (TDCPRAM.FX_TERM == null) TDCPRAM.FX_TERM = "";
            JIBS_tmp_int = TDCPRAM.FX_TERM.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) TDCPRAM.FX_TERM += " ";
            if (TDCPRAM.FX_TERM.substring(0, 1).equalsIgnoreCase("Y")) {
                WS_TERM_FX_2 = WS_TERM_FX_2 * 360;
            }
            if (WS_TERM_CQ_1 < WS_TERM_FX_2) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_FX_TERM_ERR);
            }
        }
    }
    public void B050_DELETE_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRLMT);
        TDRLMT.KEY.ACTI_NO = TDCPRAM.ACTI_NO;
        T000_READ_TDTLMT();
        if (TDRLMT.AGN_USE_BAL != 0 
            || TDRLMT.UNAGN_USE_BAL != 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PROD_BOUGHT);
        } else {
        }
        IBS.init(SCCGWA, TDROTHE);
        TDROTHE.KEY.ACTI_NO = TDCPRAM.ACTI_NO;
        T000_READ_TDTOTHE_UPD();
        if (WS_TABLE_FLG == 'N') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ACTI_NO_NOT_EXIST);
        } else {
            WS_HD_FLG = TDROTHE.ACTI_FLG;
            WS_SHX_DT = TDROTHE.STR_DATE;
            WS_SHI_DT = TDROTHE.END_DATE;
        }
        if (WS_HD_FLG == '1') {
        } else {
            if (WS_SHX_DT <= SCCGWA.COMM_AREA.AC_DATE 
                && WS_SHI_DT > SCCGWA.COMM_AREA.AC_DATE) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BAL_INUSE_FORBID);
            }
        }
    }
    public void T000_READ_TDTLMT() throws IOException,SQLException,Exception {
        TDTLMT_RD = new DBParm();
        TDTLMT_RD.TableName = "TDTLMT";
        TDTLMT_RD.where = "ACTI_NO = :TDRLMT.KEY.ACTI_NO "
            + "AND LM_CNT = 1";
        IBS.READ(SCCGWA, TDRLMT, this, TDTLMT_RD);
    }
    public void B011_CHECK_HD_FLG1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCPRAM.ZX_FLG);
        if (TDCPRAM.ZX_FLG == ' ') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_IPT);
        }
        CEP.TRC(SCCGWA, TDCPRAM.ZQ_TYP);
        if (TDCPRAM.ZQ_TYP == ' ') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_IPT);
        }
        CEP.TRC(SCCGWA, TDCPRAM.TQ_TYP);
        if (TDCPRAM.TQ_TYP == ' ') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_IPT);
        }
        CEP.TRC(SCCGWA, TDCPRAM.YQ_TYP);
        if (TDCPRAM.YQ_TYP == ' ') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_IPT);
        }
        CEP.TRC(SCCGWA, TDCPRAM.SY_TYP);
        if (TDCPRAM.SY_TYP == ' ') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_IPT);
        }
        CEP.TRC(SCCGWA, TDCPRAM.CHNL_NO);
        if (TDCPRAM.CHNL_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_IPT);
        }
        CEP.TRC(SCCGWA, TDCPRAM.MD_TYP);
        if (TDCPRAM.MD_TYP == ' ') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_IPT);
        }
        CEP.TRC(SCCGWA, TDCPRAM.ZX_FLG);
        CEP.TRC(SCCGWA, TDCPRAM.PCT_S);
        if (TDCPRAM.ZX_FLG == '1' 
            && TDCPRAM.PCT_S == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_IPT);
        }
        CEP.TRC(SCCGWA, TDCPRAM.FD_RAT);
        if (TDCPRAM.ZX_FLG == '2' 
            && TDCPRAM.FD_RAT == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_IPT);
        }
        CEP.TRC(SCCGWA, TDCPRAM.RUL_CD);
        if (TDCPRAM.ZX_FLG == '3' 
            && TDCPRAM.RUL_CD.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_IPT);
        }
        CEP.TRC(SCCGWA, TDCPRAM.HY_RAT);
        if (TDCPRAM.ZX_FLG == '4' 
            && TDCPRAM.HY_RAT == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_IPT);
        }
    }
    public void B012_CHECK_HD_FLG0() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCPRAM.BR);
        CEP.TRC(SCCGWA, TDCPRAM.CCY);
        CEP.TRC(SCCGWA, TDCPRAM.RUL_CD);
        CEP.TRC(SCCGWA, TDCPRAM.TQ_RAT);
        if (TDCPRAM.SHI_DT < SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_EXPDT_LESS_ACDT);
        }
        if (TDCPRAM.SHX_DT >= TDCPRAM.SHI_DT) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_EXPDT_LESS_EFFDT);
        }
        if (TDCPRAM.BR == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_IPT);
        }
        if (TDCPRAM.CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_IPT);
        }
        if (TDCPRAM.RUL_CD.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_IPT);
        }
    }
    public void B013_CHECK_HD_FLG() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCPRAM.TQ_TYP);
        CEP.TRC(SCCGWA, TDCPRAM.TQ_RAT);
        if (TDCPRAM.TQ_TYP == '5' 
            && TDCPRAM.TQ_RAT == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.D_TQRAT_MUST_INPUT);
        }
        CEP.TRC(SCCGWA, TDCPRAM.YQ_TYP);
        CEP.TRC(SCCGWA, TDCPRAM.YQ_RAT);
        if (TDCPRAM.YQ_TYP == '5' 
            && TDCPRAM.YQ_RAT == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.D_YQRAT_MUST_INPUT);
        }
        CEP.TRC(SCCGWA, TDCPRAM.ZQ_TYP);
        CEP.TRC(SCCGWA, TDCPRAM.FX_TERM);
        if (TDCPRAM.ZQ_TYP == '1' 
            && TDCPRAM.FX_TERM.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_IPT);
        }
    }
    public void B090_CHECK_AQFSBZ() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCPRAM.PROD_CD);
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = TDCPRAM.PROD_CD;
        S000_CALL_BPZPQPRD();
        IBS.init(SCCGWA, TDCQPMP);
        IBS.init(SCCGWA, TDCPRDP);
        TDCQPMP.FUNC = 'I';
        TDCQPMP.PROD_CD = BPCPQPRD.PARM_CODE;
        TDCQPMP.DAT_PTR = TDCPROD;
        S000_CALL_TDZQPMP();
        TDCPRDP.PRDMO_CD = TDCPROD.PRDO_CDM;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.TXN_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.TXN_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.INT_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.INT_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.EXP_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.EXP_PRM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCPROD.PROD_DATA.OTH_PRM);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP.OTH_PRM);
        WS_ONTIM_FLG = TDCPRDP.EXP_PRM.ONTM_FLG;
    }
    public void B030_ADD_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCPRAM.PROD_CD);
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = TDCPRAM.PROD_CD;
        S000_CALL_BPZPQPRD();
        IBS.init(SCCGWA, TDCQPMP);
        IBS.init(SCCGWA, TDCPRDP);
        TDCQPMP.FUNC = 'I';
        TDCQPMP.PROD_CD = BPCPQPRD.PARM_CODE;
        TDCQPMP.DAT_PTR = TDCPROD;
        S000_CALL_TDZQPMP();
        CEP.TRC(SCCGWA, TDCPRAM.HD_FLG);
        B030_A_M_CHECK();
        IBS.init(SCCGWA, BPCSGSEQ);
        if (TDCPRAM.HD_FLG == '1') {
            BPCSGSEQ.TYPE = K_TD_QH_SEQ;
            BPCSGSEQ.CODE = "TDQHSEQ";
        } else {
            if (TDCPRAM.HD_FLG == '0') {
                BPCSGSEQ.TYPE = K_TD_HD_SEQ;
                BPCSGSEQ.CODE = "TDHDSEQ";
            }
        }
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        S000_CALL_BPZSGSEQ();
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        if (TDCPRAM.HD_FLG == '1') {
            WS_QIHAO_NO.WS_QIHAO_DT = SCCGWA.COMM_AREA.AC_DATE;
            B030_01_GET_TYP_PROC();
            CEP.TRC(SCCGWA, BPCPQPRD.CUS_PER_FLG);
            CEP.TRC(SCCGWA, BPCPQPRD.CUS_COM_FLG);
            CEP.TRC(SCCGWA, BPCPQPRD.CUS_FIN_FLG);
            if (WS_AC_TYPE.equalsIgnoreCase("021")) {
                if (BPCPQPRD.CUS_PER_FLG == '0') {
                    WS_QIHAO_NO.WS_DUIXIANG = '1';
                }
                if (BPCPQPRD.CUS_COM_FLG == '0' 
                    || BPCPQPRD.CUS_FIN_FLG == '0') {
                    WS_QIHAO_NO.WS_DUIXIANG = '2';
                }
            }
            if (WS_AC_TYPE.equalsIgnoreCase("037")) {
                WS_QIHAO_NO.WS_DUIXIANG = '3';
            }
            WS_QIHAO_NO.WS_CUNQI = TDCPRAM.CQ_TERM;
            CEP.TRC(SCCGWA, "-----------QIHAO---------");
            CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
            JIBS_tmp_str[0] = "" + BPCSGSEQ.SEQ;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(9 - 1, 9 + 7 - 1).trim().length() == 0) WS_QIHAO_NO.WS_QHSX_NO = 0;
            else WS_QIHAO_NO.WS_QHSX_NO = Integer.parseInt(JIBS_tmp_str[0].substring(9 - 1, 9 + 7 - 1));
            CEP.TRC(SCCGWA, WS_QIHAO_NO.WS_QHSX_NO);
            TDCPRAM.ACTI_NO = IBS.CLS2CPY(SCCGWA, WS_QIHAO_NO);
            if (WS_AC_TYPE.equalsIgnoreCase("037")) {
                if (TDCPRAM.GRPS_NO == null) TDCPRAM.GRPS_NO = "";
                JIBS_tmp_int = TDCPRAM.GRPS_NO.length();
                for (int i=0;i<9-JIBS_tmp_int;i++) TDCPRAM.GRPS_NO += " ";
                TDCPRAM.GRPS_NO = "Y" + TDCPRAM.GRPS_NO.substring(1);
                JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (TDCPRAM.GRPS_NO == null) TDCPRAM.GRPS_NO = "";
                JIBS_tmp_int = TDCPRAM.GRPS_NO.length();
                for (int i=0;i<9-JIBS_tmp_int;i++) TDCPRAM.GRPS_NO += " ";
                TDCPRAM.GRPS_NO = TDCPRAM.GRPS_NO.substring(0, 2 - 1) + JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1) + TDCPRAM.GRPS_NO.substring(2 + 2 - 1);
                if (TDCPRAM.CQ_TERM == null) TDCPRAM.CQ_TERM = "";
                JIBS_tmp_int = TDCPRAM.CQ_TERM.length();
                for (int i=0;i<4-JIBS_tmp_int;i++) TDCPRAM.CQ_TERM += " ";
                if (TDCPRAM.GRPS_NO == null) TDCPRAM.GRPS_NO = "";
                JIBS_tmp_int = TDCPRAM.GRPS_NO.length();
                for (int i=0;i<9-JIBS_tmp_int;i++) TDCPRAM.GRPS_NO += " ";
                TDCPRAM.GRPS_NO = TDCPRAM.GRPS_NO.substring(0, 4 - 1) + TDCPRAM.CQ_TERM.substring(0, 1) + TDCPRAM.GRPS_NO.substring(4 + 1 - 1);
                if (TDCPRAM.CQ_TERM == null) TDCPRAM.CQ_TERM = "";
                JIBS_tmp_int = TDCPRAM.CQ_TERM.length();
                for (int i=0;i<4-JIBS_tmp_int;i++) TDCPRAM.CQ_TERM += " ";
                if (TDCPRAM.GRPS_NO == null) TDCPRAM.GRPS_NO = "";
                JIBS_tmp_int = TDCPRAM.GRPS_NO.length();
                for (int i=0;i<9-JIBS_tmp_int;i++) TDCPRAM.GRPS_NO += " ";
                TDCPRAM.GRPS_NO = TDCPRAM.GRPS_NO.substring(0, 5 - 1) + TDCPRAM.CQ_TERM.substring(3 - 1, 3 + 2 - 1) + TDCPRAM.GRPS_NO.substring(5 + 2 - 1);
                JIBS_tmp_str[0] = "" + BPCSGSEQ.SEQ;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (TDCPRAM.GRPS_NO == null) TDCPRAM.GRPS_NO = "";
                JIBS_tmp_int = TDCPRAM.GRPS_NO.length();
                for (int i=0;i<9-JIBS_tmp_int;i++) TDCPRAM.GRPS_NO += " ";
                TDCPRAM.GRPS_NO = TDCPRAM.GRPS_NO.substring(0, 7 - 1) + JIBS_tmp_str[0].substring(13 - 1, 13 + 3 - 1) + TDCPRAM.GRPS_NO.substring(7 + 3 - 1);
                B023_GET_GRPS_NO();
            }
        }
        if (TDCPRAM.HD_FLG == '0') {
            WS_HUODONG_NO.WS_HUODONG_DT = SCCGWA.COMM_AREA.AC_DATE;
            JIBS_tmp_str[0] = "" + BPCSGSEQ.SEQ;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(8 - 1, 8 + 8 - 1).trim().length() == 0) WS_HUODONG_NO.WS_HDSX_NO = 0;
            else WS_HUODONG_NO.WS_HDSX_NO = Integer.parseInt(JIBS_tmp_str[0].substring(8 - 1, 8 + 8 - 1));
            TDCPRAM.ACTI_NO = IBS.CLS2CPY(SCCGWA, WS_HUODONG_NO);
            CEP.TRC(SCCGWA, WS_HUODONG_NO.WS_HDSX_NO);
        }
        B030_WRT_TDTOTHE_MOVE();
        T000_WRITE_TDTOTHE();
        B030_OUTPUT_INF();
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = TDCORAMA;
        SCCFMT.DATA_LEN = 332;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B023_GET_GRPS_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMCGRP);
        CICMCGRP.FUNC = 'A';
        CICMCGRP.INPUT_DATA.GRPS_NO = TDCPRAM.GRPS_NO;
        if (WS_AC_TYPE.equalsIgnoreCase("037")) {
            CICMCGRP.INPUT_DATA.GRPS_TYPE = "JGC";
        } else {
            CICMCGRP.INPUT_DATA.GRPS_TYPE = "DEC";
        }
        CICMCGRP.INPUT_DATA.NAME = TDCPRAM.ACTI_NAME;
        CICMCGRP.INPUT_DATA.EFF_DATE = TDCPRAM.SHX_DT;
        CICMCGRP.INPUT_DATA.EXP_DATE = TDCPRAM.DDT;
        CICMCGRP.INPUT_DATA.FLG = '0';
        S000_CALL_CIZMCGRP();
    }
    public void B040_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        B030_WRT_TDTOTHE_MOVE();
        T000_REWRITE_TDTOTHE();
        if (TDROTHE.ACTI_FLG == '0' 
            && TDROTHE.SUC_FLG != '1') {
            IBS.init(SCCGWA, TDCLMBP);
            TDCLMBP.ACTI_NO = TDROTHE.KEY.ACTI_NO;
            TDCLMBP.PROD_CD = TDROTHE.PROD_CD;
            S000_CALL_TDZLMBP();
        }
        B030_OUTPUT_INF();
        TDCORAMA.FUNC = 'M';
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = TDCORAMA;
        SCCFMT.DATA_LEN = 332;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B050_DELETE_PROCESS() throws IOException,SQLException,Exception {
        B030_WRT_TDTOTHE_MOVE();
        T000_REWRITE_TDTOTHE();
        B030_OUTPUT_INF();
        TDCORAMA.FUNC = 'D';
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = TDCORAMA;
        SCCFMT.DATA_LEN = 332;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B030_A_M_CHECK() throws IOException,SQLException,Exception {
        B090_CHECK_AQFSBZ();
        WS_ONTIM_FLG = '0';
        CEP.TRC(SCCGWA, WS_ONTIM_FLG);
        if (WS_ONTIM_FLG != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_IPT);
        }
        CEP.TRC(SCCGWA, TDCPRAM.CHNL_NO);
        if (TDCPRAM.CHNL_NO == null) TDCPRAM.CHNL_NO = "";
        JIBS_tmp_int = TDCPRAM.CHNL_NO.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) TDCPRAM.CHNL_NO += " ";
        if (TDCPRAM.CHNL_NO == null) TDCPRAM.CHNL_NO = "";
        JIBS_tmp_int = TDCPRAM.CHNL_NO.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) TDCPRAM.CHNL_NO += " ";
        if (TDCPRAM.CHNL_NO == null) TDCPRAM.CHNL_NO = "";
        JIBS_tmp_int = TDCPRAM.CHNL_NO.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) TDCPRAM.CHNL_NO += " ";
        if (!TDCPRAM.CHNL_NO.substring(0, 1).equalsIgnoreCase("1") 
            && !TDCPRAM.CHNL_NO.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            && !TDCPRAM.CHNL_NO.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INVALID_IPT);
        }
    }
    public void B030_01_GET_TYP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = TDCPRAM.PROD_CD;
        S000_CALL_BPZPQPRD();
        WS_AC_TYPE = BPCPQPRD.AC_TYPE;
        CEP.TRC(SCCGWA, WS_AC_TYPE);
        CEP.TRC(SCCGWA, BPCPQPRD.CUS_PER_FLG);
        CEP.TRC(SCCGWA, BPCPQPRD.CUS_COM_FLG);
        CEP.TRC(SCCGWA, BPCPQPRD.CUS_FIN_FLG);
    }
    public void B030_WRT_TDTOTHE_MOVE() throws IOException,SQLException,Exception {
        TDROTHE.KEY.ACTI_NO = TDCPRAM.ACTI_NO;
        TDROTHE.PROD_CD = TDCPRAM.PROD_CD;
        TDROTHE.ACTI_DESC = TDCPRAM.ACTI_NAME;
        TDROTHE.ACTI_FLG = TDCPRAM.HD_FLG;
        TDROTHE.BR = 43999;
        TDROTHE.STR_DATE = TDCPRAM.SHX_DT;
        if (TDCPRAM.FUNC == 'D') {
            TDROTHE.END_DATE = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            TDROTHE.END_DATE = TDCPRAM.SHI_DT;
        }
        TDROTHE.SDT = TDCPRAM.SDT;
        TDROTHE.DDT = TDCPRAM.DDT;
        TDROTHE.MIN_BAL = TDCPRAM.MIN_BAL;
        TDROTHE.MAX_BAL = TDCPRAM.MAX_BAL;
        TDROTHE.CCY = TDCPRAM.CCY;
        TDROTHE.TERM = TDCPRAM.CQ_TERM;
        TDROTHE.MIN_RAT = TDCPRAM.MIN_RAT;
        TDROTHE.MAX_RAT = TDCPRAM.MAX_RAT;
        TDROTHE.RAT_TYP = TDCPRAM.ZX_FLG;
        TDROTHE.PCT_S = TDCPRAM.PCT_S;
        TDROTHE.FLT_RAT = TDCPRAM.FD_RAT;
        TDROTHE.RUL_CD = TDCPRAM.RUL_CD;
        TDROTHE.CONT_RAT = TDCPRAM.HY_RAT;
        TDROTHE.AFF_TYP = TDCPRAM.YX_TYP;
        TDROTHE.INT_PERD = TDCPRAM.ZQ_TYP;
        TDROTHE.CD_PERD = TDCPRAM.FX_TERM;
        TDROTHE.TRAN_TYP = TDCPRAM.ZR_TYP;
        TDROTHE.REDE_TYP = TDCPRAM.SH_TYP;
        TDROTHE.PLED_TYP = TDCPRAM.ZY_TYP;
        TDROTHE.EARLY_TYP = TDCPRAM.TQ_TYP;
        TDROTHE.PRV_RAT = TDCPRAM.TQ_RAT;
        TDROTHE.DOCU_NO = TDCPRAM.WF_NO;
        TDROTHE.LATE_TYP = TDCPRAM.YQ_TYP;
        TDROTHE.OVE_RAT = TDCPRAM.YQ_RAT;
        TDROTHE.RES_TYP = TDCPRAM.SY_TYP;
        TDROTHE.DUE_RAT = TDCPRAM.SY_RAT;
        TDROTHE.CHNL_NO = TDCPRAM.CHNL_NO;
        TDROTHE.SET_FLG = TDCPRAM.CL_FLG;
        TDROTHE.LST_TYP = TDCPRAM.MD_TYP;
        TDROTHE.LSTDT_TYP = TDCPRAM.MDQX_TYP;
        TDROTHE.HER_BAL = TDCPRAM.HER_BAL;
        TDROTHE.ADD_BAL = TDCPRAM.ADD_BAL;
        TDROTHE.ADD_AMT = TDCPRAM.ADD_BAL;
        TDROTHE.PART_NUM = TDCPRAM.PART_NUM;
        TDROTHE.LST_CTL = TDCPRAM.LST_CTL;
        TDROTHE.RMK = TDCPRAM.RMK;
        CEP.TRC(SCCGWA, TDCPRAM.ADD_BAL);
        CEP.TRC(SCCGWA, TDCPRAM.GRPS_NO);
        CEP.TRC(SCCGWA, TDROTHE.GRPS_NO);
        if (TDCPRAM.GRPS_NO.trim().length() > 0) {
            TDROTHE.GRPS_NO = TDCPRAM.GRPS_NO;
        }
        CEP.TRC(SCCGWA, "2222");
        CEP.TRC(SCCGWA, TDROTHE.GRPS_NO);
        TDROTHE.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDROTHE.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        if (TDROTHE.ACTI_FLG == '0') {
            TDROTHE.SUC_FLG = '1';
        }
    }
    public void B030_OUTPUT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCORAMA);
        TDCORAMA.PROD_CD = TDROTHE.PROD_CD;
        TDCORAMA.ACTI_NO = TDROTHE.KEY.ACTI_NO;
        TDCORAMA.ACTI_NAME = TDROTHE.ACTI_DESC;
        TDCORAMA.CCY = TDROTHE.CCY;
        TDCORAMA.HD_FLG = TDROTHE.ACTI_FLG;
        TDCORAMA.BR = TDROTHE.BR;
        TDCORAMA.SHX_DT = TDROTHE.STR_DATE;
        TDCORAMA.SHI_DT = TDROTHE.END_DATE;
        TDCORAMA.SDT = TDROTHE.SDT;
        TDCORAMA.DDT = TDROTHE.DDT;
        TDCORAMA.MIN_BAL = TDROTHE.MIN_BAL;
        TDCORAMA.MAX_BAL = TDROTHE.MAX_BAL;
        TDCORAMA.MIN_RAT = TDROTHE.MIN_RAT;
        TDCORAMA.MAX_RAT = TDROTHE.MAX_RAT;
        TDCORAMA.XZ_FLG = TDROTHE.RAT_TYP;
        TDCORAMA.PCT_S = TDROTHE.PCT_S;
        TDCORAMA.FD_RAT = TDROTHE.FLT_RAT;
        TDCORAMA.RUL_CD = TDROTHE.RUL_CD;
        TDCORAMA.HY_RAT = TDROTHE.CONT_RAT;
        TDCORAMA.YX_TYP = TDROTHE.AFF_TYP;
        TDCORAMA.ZQ_TYP = TDROTHE.INT_PERD;
        TDCORAMA.FX_TERM = TDROTHE.CD_PERD.charAt(0);
        TDCORAMA.ZR_TYP = TDROTHE.TRAN_TYP;
        TDCORAMA.SH_TYP = TDROTHE.REDE_TYP;
        TDCORAMA.ZY_TYP = TDROTHE.PLED_TYP;
        TDCORAMA.TQ_TYP = TDROTHE.EARLY_TYP;
        TDCORAMA.TQ_RAT = TDROTHE.PRV_RAT;
        TDCORAMA.WF_NO = TDROTHE.DOCU_NO;
        TDCORAMA.YQ_TYP = TDROTHE.LATE_TYP;
        TDCORAMA.YQ_RAT = TDROTHE.OVE_RAT;
        TDCORAMA.SY_TYP = TDROTHE.RES_TYP;
        TDCORAMA.SY_RAT = TDROTHE.DUE_RAT;
        TDCORAMA.CHNL_NO = TDROTHE.CHNL_NO;
        TDCORAMA.MD_TYP = TDROTHE.LST_TYP;
        TDCORAMA.MDQX_TYP = TDROTHE.LSTDT_TYP;
        TDCORAMA.CL_FLG = TDROTHE.SET_FLG;
        TDCORAMA.CQ_TERM = TDROTHE.TERM;
        TDCORAMA.GRPS_NO = TDROTHE.GRPS_NO;
        CEP.TRC(SCCGWA, TDCORAMA.PROD_CD);
        CEP.TRC(SCCGWA, TDCORAMA.ACTI_NO);
    }
    public void T000_COUNT_TDTOTHE() throws IOException,SQLException,Exception {
        TDTOTHE_RD = new DBParm();
        TDTOTHE_RD.TableName = "TDTOTHE";
        TDTOTHE_RD.set = "WS-OTHE-CNT=COUNT(*)";
        TDTOTHE_RD.where = "ACTI_FLG = '0'";
        IBS.GROUP(SCCGWA, TDROTHE, this, TDTOTHE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "2");
            WS_TABLE_FLG = 'Y';
        } else {
            CEP.TRC(SCCGWA, "4");
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTOTHE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READ_TDTOTHE() throws IOException,SQLException,Exception {
        TDTOTHE_RD = new DBParm();
        TDTOTHE_RD.TableName = "TDTOTHE";
        IBS.READ(SCCGWA, TDROTHE, TDTOTHE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTOTHE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READ_TDTRATE() throws IOException,SQLException,Exception {
        TDTRATE_RD = new DBParm();
        TDTRATE_RD.TableName = "TDTRATE";
        IBS.READ(SCCGWA, TDRRATE, TDTRATE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ACTI_I_ERR);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTOTHE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READ_TDTOTHE_UPD() throws IOException,SQLException,Exception {
        TDTOTHE_RD = new DBParm();
        TDTOTHE_RD.TableName = "TDTOTHE";
        TDTOTHE_RD.upd = true;
        IBS.READ(SCCGWA, TDROTHE, TDTOTHE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTOTHE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_READ_TDTOTHE_CHK() throws IOException,SQLException,Exception {
        TDTOTHE_RD = new DBParm();
        TDTOTHE_RD.TableName = "TDTOTHE";
        TDTOTHE_RD.where = "BR = :TDROTHE.BR "
            + "AND CCY = :TDROTHE.CCY "
            + "AND TERM = :TDROTHE.TERM "
            + "AND PROD_CD = :TDROTHE.PROD_CD";
        TDTOTHE_RD.fst = true;
        IBS.READ(SCCGWA, TDROTHE, this, TDTOTHE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CHECK_FND = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CHECK_FND = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTOTHE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_WRITE_TDTOTHE() throws IOException,SQLException,Exception {
        TDTOTHE_RD = new DBParm();
        TDTOTHE_RD.TableName = "TDTOTHE";
        IBS.WRITE(SCCGWA, TDROTHE, TDTOTHE_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "111111111");
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_REC_EXIST);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTOTHE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
        }
    }
    public void T000_REWRITE_TDTOTHE() throws IOException,SQLException,Exception {
        TDTOTHE_RD = new DBParm();
        TDTOTHE_RD.TableName = "TDTOTHE";
        IBS.REWRITE(SCCGWA, TDROTHE, TDTOTHE_RD);
    }
    public void T000_DELETE_TDTOTHE() throws IOException,SQLException,Exception {
        TDTOTHE_RD = new DBParm();
        TDTOTHE_RD.TableName = "TDTOTHE";
        TDTOTHE_RD.where = "ACTI_NO = :TDROTHE.KEY.ACTI_NO";
        IBS.DELETE(SCCGWA, TDROTHE, this, TDTOTHE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_DEL = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_DEL = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTOTHE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "DELETE";
            B_DB_EXCP();
        }
    }
    public void S000_CALL_TDZLMBP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-ACTI-LMT-EQU", TDCLMBP);
    }
    public void S000_CALL_CIZMCGRP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZMCGRP", CICMCGRP);
        CEP.TRC(SCCGWA, CICMCGRP.RC);
        if (CICMCGRP.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICMCGRP.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_TDZQPMP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-BASE-PRD-QRY", TDCQPMP);
        CEP.TRC(SCCGWA, TDCQPMP.RC.RC_RTNCODE);
        if (TDCQPMP.RC.RC_RTNCODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, TDCQPMP.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S00_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
