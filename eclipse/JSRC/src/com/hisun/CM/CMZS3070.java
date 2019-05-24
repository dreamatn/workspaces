package com.hisun.CM;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.DD.*;
import com.hisun.CI.*;
import com.hisun.TD.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CMZS3070 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    int WS_AC_BR = 0;
    String WS_AC_NM = " ";
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DCCURHLD DCCURHLD = new DCCURHLD();
    SCCBINF SCCBINF = new SCCBINF();
    DCCUHLD DCCUHLD = new DCCUHLD();
    DDCSTRAC DDCSTRAC = new DDCSTRAC();
    CICQACAC CICQACAC = new CICQACAC();
    DCCUCINF DCCUCINF = new DCCUCINF();
    TDCACE TDCACE = new TDCACE();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    CMCSIQAC CMCSIQAC = new CMCSIQAC();
    DCCPFTCK DCCPFTCK = new DCCPFTCK();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPARMC BPCPARMC = new BPCPARMC();
    DCCPCDCK DCCPCDCK = new DCCPCDCK();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CMCS3070 CMCS3070;
    public void MP(SCCGWA SCCGWA, CMCS3070 CMCS3070) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCS3070 = CMCS3070;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMZS3070 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_RHLD_DEDUCT_HOLD_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCS3070.HLD_NO);
        if (CMCS3070.HLD_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_HLD_NO_INP_ERR);
        }
        CEP.TRC(SCCGWA, CMCS3070.HLD_AMT);
        if (CMCS3070.HLD_AMT == 0) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_HLD_AMT_INP_ERR);
        }
        CEP.TRC(SCCGWA, CMCS3070.AMT);
        if (CMCS3070.AMT == 0) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TXN_AMT_ERR);
        }
        CEP.TRC(SCCGWA, CMCS3070.TX_TYP);
        if (CMCS3070.TX_TYP != '0' 
            && CMCS3070.TX_TYP != '1') {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TX_TYPE_INP_ERR);
        }
        if (CMCS3070.RMK_CD.trim().length() == 0) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_RMK_CD_ERR);
        }
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPCPARMC);
        BPCPRMR.FUNC = ' ';
        BPCPARMC.KEY.TYP = "PARMC";
        if (BPCPARMC.KEY.CD == null) BPCPARMC.KEY.CD = "";
        JIBS_tmp_int = BPCPARMC.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCPARMC.KEY.CD += " ";
        BPCPARMC.KEY.CD = "MMO" + BPCPARMC.KEY.CD.substring(3);
        if (CMCS3070.RMK_CD == null) CMCS3070.RMK_CD = "";
        JIBS_tmp_int = CMCS3070.RMK_CD.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) CMCS3070.RMK_CD += " ";
        if (BPCPARMC.KEY.CD == null) BPCPARMC.KEY.CD = "";
        JIBS_tmp_int = BPCPARMC.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCPARMC.KEY.CD += " ";
        BPCPARMC.KEY.CD = BPCPARMC.KEY.CD.substring(0, 6 - 1) + CMCS3070.RMK_CD.substring(0, 4) + BPCPARMC.KEY.CD.substring(6 + 9 - 1);
        BPCPRMR.DAT_PTR = BPCPARMC;
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC.RC_RTNCODE);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase("BP0180")) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_MMO_NOT_EXIST);
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
            }
        }
        CEP.TRC(SCCGWA, CMCS3070.CHK_FLG);
        JIBS_tmp_str[0] = "" + CMCS3070.CHK_FLG;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 1).equalsIgnoreCase("1") 
            && (CMCS3070.PSW.trim().length() == 0 
            || CMCS3070.PSW.charAt(0) == 0X00)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_PSW_ERR);
        }
        JIBS_tmp_str[0] = "" + CMCS3070.CHK_FLG;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 1).equalsIgnoreCase("2") 
            && ((CMCS3070.TRK_DATA2.trim().length() == 0 
            || CMCS3070.TRK_DATA2.charAt(0) == 0X00) 
            && (CMCS3070.TRK_DATA3.trim().length() == 0 
            || CMCS3070.TRK_DATA3.charAt(0) == 0X00))) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TRK_ERR);
        }
        JIBS_tmp_str[0] = "" + CMCS3070.CHK_FLG;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 1).equalsIgnoreCase("3") 
            && (CMCS3070.PSW.trim().length() == 0 
            || CMCS3070.PSW.charAt(0) == 0X00) 
            && ((CMCS3070.TRK_DATA2.trim().length() == 0 
            || CMCS3070.TRK_DATA2.charAt(0) == 0X00) 
            && (CMCS3070.TRK_DATA3.trim().length() == 0 
            || CMCS3070.TRK_DATA3.charAt(0) == 0X00))) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TRK_ERR);
        }
    }
    public void B200_RHLD_DEDUCT_HOLD_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (CMCS3070.TX_TYP == '0') {
                B200_01_URHLD_PROC();
                if (pgmRtn) return;
                B200_02_DD_STRAC_D_PROC();
                if (pgmRtn) return;
            } else if (CMCS3070.TX_TYP == '1') {
                B200_03_DD_STRAC_C_PROC();
                if (pgmRtn) return;
                B200_04_HOLD_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (CMCS3070.TX_TYP == '0') {
                B200_02_DD_STRAC_D_PROC();
                if (pgmRtn) return;
                B200_01_URHLD_PROC();
                if (pgmRtn) return;
            } else if (CMCS3070.TX_TYP == '1') {
                B200_04_HOLD_PROC();
                if (pgmRtn) return;
                B200_03_DD_STRAC_C_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B200_01_URHLD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCS3070.RMK_CD);
        IBS.init(SCCGWA, DCCURHLD);
        DCCURHLD.DATA.HLD_NO = CMCS3070.HLD_NO;
        DCCURHLD.DATA.RMK = CMCS3070.REMARK;
        DCCURHLD.DATA.CHG_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCCURHLD.DATA.RHLD_TYP = '1';
        DCCURHLD.DATA.TX_TYP_CD = CMCS3070.RMK_CD;
        S000_CALL_DCZURHLD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCURHLD.DATA.TX_TYP_CD);
    }
    public void B200_02_DD_STRAC_D_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCS3070.AC);
        CEP.TRC(SCCGWA, CMCS3070.TX_TYP);
        CEP.TRC(SCCGWA, CMCS3070.HLD_NO);
        CEP.TRC(SCCGWA, CMCS3070.CCY);
        CEP.TRC(SCCGWA, CMCS3070.CCY_TYP);
        CEP.TRC(SCCGWA, CMCS3070.PSW);
        CEP.TRC(SCCGWA, CMCS3070.TRK_DATA2);
        CEP.TRC(SCCGWA, CMCS3070.TRK_DATA3);
        CEP.TRC(SCCGWA, CMCS3070.AMT);
        CEP.TRC(SCCGWA, CMCS3070.HLD_AMT);
        CEP.TRC(SCCGWA, CMCS3070.OP_AC);
        CEP.TRC(SCCGWA, CMCS3070.OP_NM);
        CEP.TRC(SCCGWA, CMCS3070.OP_BR);
        CEP.TRC(SCCGWA, CMCS3070.OP_BRNM);
        CEP.TRC(SCCGWA, CMCS3070.RMK_CD);
        CEP.TRC(SCCGWA, CMCS3070.REMARK);
        IBS.init(SCCGWA, DDCSTRAC);
        IBS.init(SCCGWA, CMCSIQAC);
        CMCSIQAC.I_FUNC = '3';
        CMCSIQAC.I_CUS_AC = CMCS3070.AC;
        CMCSIQAC.I_CCY = CMCS3070.CCY;
        CMCSIQAC.I_CCY_TYP = CMCS3070.CCY_TYP;
        S000_CALL_CMZSIQAC();
        if (pgmRtn) return;
        WS_AC_BR = CMCSIQAC.OUT_INF.DD_INF.DD_ACO_OWN_BR;
        WS_AC_NM = CMCSIQAC.OUT_INF.BAS_INF.BAS_CPN_NM;
        CEP.TRC(SCCGWA, CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP);
        if (CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP.equalsIgnoreCase("DD")) {
            DDCSTRAC.FR_AC = CMCS3070.AC;
            DDCSTRAC.FR_BV_TYPE = '2';
        }
        if (CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP.equalsIgnoreCase("DC")) {
            DDCSTRAC.FR_CARD = CMCS3070.AC;
            DDCSTRAC.FR_BV_TYPE = '1';
        }
        IBS.init(SCCGWA, CMCSIQAC);
        CMCSIQAC.I_FUNC = '1';
        CMCSIQAC.I_CUS_AC = CMCS3070.OP_AC;
        CMCSIQAC.I_CCY = CMCS3070.CCY;
        CMCSIQAC.I_CCY_TYP = CMCS3070.CCY_TYP;
        S000_CALL_CMZSIQAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP);
        if (CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP.equalsIgnoreCase("DC")) {
            DDCSTRAC.TO_BV_TYPE = '1';
        } else if (CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP.equalsIgnoreCase("DD")) {
            DDCSTRAC.TO_BV_TYPE = '2';
        } else if (CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP.equalsIgnoreCase("AI")) {
            DDCSTRAC.TO_BV_TYPE = '3';
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_FRM_APP_ERR);
        }
        DDCSTRAC.FR_CCY = CMCS3070.CCY;
        DDCSTRAC.TO_CCY = CMCS3070.CCY;
        DDCSTRAC.FR_CCY_TYPE = CMCS3070.CCY_TYP;
        DDCSTRAC.PSWD = CMCS3070.PSW;
        DDCSTRAC.TRK2_DAT = CMCS3070.TRK_DATA2;
        DDCSTRAC.TRK3_DAT = CMCS3070.TRK_DATA3;
        DDCSTRAC.FR_AMT = CMCS3070.AMT;
        DDCSTRAC.TO_AC = CMCS3070.OP_AC;
        DDCSTRAC.RLT_AC = CMCS3070.OP_AC;
        DDCSTRAC.RLT_AC_NAME = CMCS3070.OP_NM;
        DDCSTRAC.RLT_BANK = "" + CMCS3070.OP_BR;
        JIBS_tmp_int = DDCSTRAC.RLT_BANK.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) DDCSTRAC.RLT_BANK = "0" + DDCSTRAC.RLT_BANK;
        DDCSTRAC.RLT_BK_NM = CMCS3070.OP_BRNM;
        DDCSTRAC.CR_MMO = CMCS3070.RMK_CD;
        DDCSTRAC.DR_MMO = CMCS3070.RMK_CD;
        DDCSTRAC.REMARKS = CMCS3070.REMARK;
        CEP.TRC(SCCGWA, DDCSTRAC.DR_MMO);
        CEP.TRC(SCCGWA, DDCSTRAC.CR_MMO);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_BV_TYPE);
        CEP.TRC(SCCGWA, DDCSTRAC.TRK2_DAT);
        CEP.TRC(SCCGWA, DDCSTRAC.TRK3_DAT);
        CEP.TRC(SCCGWA, DDCSTRAC.PSWD);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_AMT);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_AC);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_AC_CNAME);
        CEP.TRC(SCCGWA, CMCS3070.CHK_FLG);
        CEP.TRC(SCCGWA, DDCSTRAC.RLT_AC);
        CEP.TRC(SCCGWA, DDCSTRAC.RLT_AC_NAME);
        CEP.TRC(SCCGWA, DDCSTRAC.RLT_BANK);
        CEP.TRC(SCCGWA, DDCSTRAC.RLT_BK_NM);
        if (CMCS3070.CHK_FLG == '1') {
            DDCSTRAC.CHK_PSW = 'P';
        } else if (CMCS3070.CHK_FLG == '2') {
            DDCSTRAC.CHK_PSW = 'T';
        } else if (CMCS3070.CHK_FLG == '3') {
            DDCSTRAC.CHK_PSW = 'B';
        } else {
            DDCSTRAC.CHK_PSW_FLG = 'N';
        }
        S000_CALL_DDZSTRAC();
        if (pgmRtn) return;
    }
    public void B200_03_DD_STRAC_C_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCS3070.AC);
        CEP.TRC(SCCGWA, CMCS3070.TX_TYP);
        CEP.TRC(SCCGWA, CMCS3070.HLD_NO);
        CEP.TRC(SCCGWA, CMCS3070.CCY);
        CEP.TRC(SCCGWA, CMCS3070.CCY_TYP);
        CEP.TRC(SCCGWA, CMCS3070.CHK_FLG);
        CEP.TRC(SCCGWA, CMCS3070.TRK_DATA2);
        CEP.TRC(SCCGWA, CMCS3070.TRK_DATA3);
        CEP.TRC(SCCGWA, CMCS3070.PSW);
        CEP.TRC(SCCGWA, CMCS3070.AMT);
        CEP.TRC(SCCGWA, CMCS3070.HLD_AMT);
        CEP.TRC(SCCGWA, CMCS3070.OP_AC);
        CEP.TRC(SCCGWA, CMCS3070.OP_NM);
        CEP.TRC(SCCGWA, CMCS3070.OP_BR);
        CEP.TRC(SCCGWA, CMCS3070.OP_BRNM);
        CEP.TRC(SCCGWA, CMCS3070.RMK_CD);
        CEP.TRC(SCCGWA, CMCS3070.REMARK);
        IBS.init(SCCGWA, DDCSTRAC);
        IBS.init(SCCGWA, CMCSIQAC);
        CMCSIQAC.I_FUNC = '3';
        CMCSIQAC.I_CUS_AC = CMCS3070.AC;
        CMCSIQAC.I_CCY = CMCS3070.CCY;
        CMCSIQAC.I_CCY_TYP = CMCS3070.CCY_TYP;
        S000_CALL_CMZSIQAC();
        if (pgmRtn) return;
        WS_AC_BR = CMCSIQAC.OUT_INF.DD_INF.DD_ACO_OWN_BR;
        WS_AC_NM = CMCSIQAC.OUT_INF.BAS_INF.BAS_CPN_NM;
        CEP.TRC(SCCGWA, CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP);
        if (CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP.equalsIgnoreCase("DD")) {
            DDCSTRAC.TO_AC = CMCS3070.AC;
            DDCSTRAC.TO_BV_TYPE = '2';
        }
        if (CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP.equalsIgnoreCase("DC")) {
            DDCSTRAC.TO_CARD = CMCS3070.AC;
            DDCSTRAC.TO_BV_TYPE = '1';
        }
        IBS.init(SCCGWA, CMCSIQAC);
        CMCSIQAC.I_FUNC = '1';
        CMCSIQAC.I_CUS_AC = CMCS3070.OP_AC;
        CMCSIQAC.I_CCY = CMCS3070.CCY;
        CMCSIQAC.I_CCY_TYP = CMCS3070.CCY_TYP;
        S000_CALL_CMZSIQAC();
        if (pgmRtn) return;
        if (CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP.equalsIgnoreCase("DC")) {
            DDCSTRAC.FR_BV_TYPE = '1';
        } else if (CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP.equalsIgnoreCase("DD")) {
            DDCSTRAC.FR_BV_TYPE = '2';
        } else if (CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP.equalsIgnoreCase("AI")) {
            DDCSTRAC.FR_BV_TYPE = '3';
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_FRM_APP_ERR);
        }
        DDCSTRAC.FR_CCY = CMCS3070.CCY;
        DDCSTRAC.TO_CCY = CMCS3070.CCY;
        DDCSTRAC.TO_CCY_TYPE = CMCS3070.CCY_TYP;
        DDCSTRAC.PSWD = CMCS3070.PSW;
        DDCSTRAC.TRK2_DAT = CMCS3070.TRK_DATA2;
        DDCSTRAC.TRK3_DAT = CMCS3070.TRK_DATA3;
        DDCSTRAC.FR_AMT = CMCS3070.AMT;
        DDCSTRAC.FR_AC = CMCS3070.OP_AC;
        DDCSTRAC.RLT_AC = CMCS3070.AC;
        DDCSTRAC.RLT_AC_NAME = CMCS3070.OP_NM;
        DDCSTRAC.RLT_BANK = "" + CMCS3070.OP_BR;
        JIBS_tmp_int = DDCSTRAC.RLT_BANK.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) DDCSTRAC.RLT_BANK = "0" + DDCSTRAC.RLT_BANK;
        DDCSTRAC.RLT_BK_NM = CMCS3070.OP_BRNM;
        DDCSTRAC.DR_MMO = CMCS3070.RMK_CD;
        DDCSTRAC.CR_MMO = CMCS3070.RMK_CD;
        DDCSTRAC.REMARKS = CMCS3070.REMARK;
        CEP.TRC(SCCGWA, DDCSTRAC.CR_MMO);
        CEP.TRC(SCCGWA, DDCSTRAC.DR_MMO);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_BV_TYPE);
        CEP.TRC(SCCGWA, DDCSTRAC.PSWD);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_AMT);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_AC);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_AC_CNAME);
        CEP.TRC(SCCGWA, DDCSTRAC.RLT_AC);
        CEP.TRC(SCCGWA, DDCSTRAC.RLT_AC_NAME);
        CEP.TRC(SCCGWA, DDCSTRAC.RLT_BANK);
        CEP.TRC(SCCGWA, DDCSTRAC.RLT_BK_NM);
        if (CMCS3070.CHK_FLG == '1') {
            DDCSTRAC.CHK_PSW = 'P';
        } else if (CMCS3070.CHK_FLG == '2') {
            DDCSTRAC.CHK_PSW = 'T';
        } else if (CMCS3070.CHK_FLG == '3') {
            DDCSTRAC.CHK_PSW = 'B';
        } else {
            DDCSTRAC.CHK_PSW_FLG = 'N';
        }
        S000_CALL_DDZSTRAC();
        if (pgmRtn) return;
    }
    public void B200_04_HOLD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCS3070.AC);
        CEP.TRC(SCCGWA, CMCS3070.TX_TYP);
        CEP.TRC(SCCGWA, CMCS3070.HLD_NO);
        CEP.TRC(SCCGWA, CMCS3070.CCY);
        CEP.TRC(SCCGWA, CMCS3070.CCY_TYP);
        CEP.TRC(SCCGWA, CMCS3070.CHK_FLG);
        CEP.TRC(SCCGWA, CMCS3070.TRK_DATA2);
        CEP.TRC(SCCGWA, CMCS3070.TRK_DATA3);
        CEP.TRC(SCCGWA, CMCS3070.PSW);
        CEP.TRC(SCCGWA, CMCS3070.AMT);
        CEP.TRC(SCCGWA, CMCS3070.OP_AC);
        CEP.TRC(SCCGWA, CMCS3070.OP_NM);
        CEP.TRC(SCCGWA, CMCS3070.OP_BR);
        CEP.TRC(SCCGWA, CMCS3070.OP_BRNM);
        CEP.TRC(SCCGWA, CMCS3070.RMK_CD);
        CEP.TRC(SCCGWA, CMCS3070.REMARK);
        IBS.init(SCCGWA, DCCUHLD);
        DCCUHLD.DATA.HLD_NO = CMCS3070.HLD_NO;
        DCCUHLD.DATA.AC = CMCS3070.AC;
        DCCUHLD.DATA.HLD_TYP = '2';
        DCCUHLD.DATA.PRE_SIC_FLG = 'Y';
        DCCUHLD.DATA.SPR_TYP = '2';
        DCCUHLD.DATA.CCY = CMCS3070.CCY;
        DCCUHLD.DATA.CCY_TYP = CMCS3070.CCY_TYP;
        DCCUHLD.DATA.AMT = CMCS3070.AMT;
        DCCUHLD.DATA.RMK = CMCS3070.REMARK;
        DCCUHLD.DATA.HLD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCCUHLD.DATA.TX_TYP_CD = CMCS3070.RMK_CD;
        S000_CALL_DCZUWHLD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUHLD.DATA.TX_TYP_CD);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC, true);
        CEP.TRC(SCCGWA, CICQACAC.RC.RC_CODE);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF ", DCCUCINF, true);
        CEP.TRC(SCCGWA, DCCUCINF.RC.RC_CODE);
        if (DCCUCINF.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DCCUCINF.RC);
        }
    }
    public void S000_CALL_DCZUWHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-DCZUWHLD", DCCUHLD);
    }
    public void S000_CALL_DCZURHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-RHLD", DCCURHLD, true);
    }
    public void S000_CALL_DDZSTRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-TR-AC", DDCSTRAC, true);
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL, true);
    }
    public void S000_CALL_CMZSIQAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CM-S-INQ-CUS-AC", CMCSIQAC, true);
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
