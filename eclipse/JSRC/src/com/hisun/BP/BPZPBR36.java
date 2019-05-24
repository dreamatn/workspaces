package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.CI.CICACCU;
import com.hisun.CI.CICQACAC;
import com.hisun.CI.CICQACRI;
import com.hisun.CI.CICQCIAC;
import com.hisun.DC.DCCPACTY;
import com.hisun.DC.DCCUSPAC;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BPZPBR36 {
    boolean pgmRtn = false;
    String K_TBL_ORGE = "BPTORGI ";
    BPZPBR36_WS_PARM_DATA1 WS_PARM_DATA1 = new BPZPBR36_WS_PARM_DATA1();
    BPZPBR36_WS_PARM_DATA2 WS_PARM_DATA2 = new BPZPBR36_WS_PARM_DATA2();
    BPZPBR36_WS_PARM_DATA3 WS_PARM_DATA3 = new BPZPBR36_WS_PARM_DATA3();
    BPZPBR36_WS_PARM_DATA4 WS_PARM_DATA4 = new BPZPBR36_WS_PARM_DATA4();
    BPZPBR36_WS_PARM_DATA5 WS_PARM_DATA5 = new BPZPBR36_WS_PARM_DATA5();
    int WS_BRAN_BR_NEW = 0;
    int WS_BRAN_BR_OLD = 0;
    int WS_I = 0;
    int WS_J = 0;
    String WS_VIL_TYP_NEW = " ";
    String WS_VIL_TYP_OLD = " ";
    String WS_AREA_CD_NEW = " ";
    String WS_AREA_CD_OLD = " ";
    char WS_ATTR_OLD = ' ';
    char WS_ATTR_NEW = ' ';
    int WS_SUPR_BR_OLD = 0;
    int WS_SUPR_BR_NEW = 0;
    String WS_FX_BUSI = " ";
    String WS_ACO_AC = " ";
    String WS_ACO_AC_T = " ";
    String WS_CCY = " ";
    String WS_CCY_ACAC = " ";
    int WS_OPN_BR = 0;
    String WS_APP_ACO = " ";
    String WS_APP_ACAC = " ";
    int WS_BBR_NEW = 0;
    int WS_BBR_OLD = 0;
    char WS_ENTY_TYP = ' ';
    char WS_INT_BR_FLG_OLD = ' ';
    char WS_INT_BR_FLG_NEW = ' ';
    char WS_ORGI_FLG = ' ';
    char WS_FLG = ' ';
    char WS_ORGE_FLG = ' ';
    char WS_CALL_TYP_FLG = ' ';
    char WS_OCAC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRORGI BPRORGI = new BPRORGI();
    BPRORGL BPRORGL = new BPRORGL();
    BPRORGE BPRORGE = new BPRORGE();
    CICQACRI CICQACRI = new CICQACRI();
    BPROCAC BPROCAC = new BPROCAC();
    SCCFMT SCCFMT = new SCCFMT();
    BPCRORGI BPCRORGI = new BPCRORGI();
    BPCOORGI BPCOORGI = new BPCOORGI();
    BPCPQORG BPCPQORG = new BPCPQORG();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DCCUSPAC DCCUSPAC = new DCCUSPAC();
    DDCUQCAC DDCUQCAC = new DDCUQCAC();
    DCCUIQMC DCCUIQMC = new DCCUIQMC();
    SCCBSP SCCBSP = new SCCBSP();
    SCCBSPS SCCBSPS = new SCCBSPS();
    CICACCU CICACCU = new CICACCU();
    BPCRORGL BPCRORGL = new BPCRORGL();
    CICQACAC CICQACAC = new CICQACAC();
    CICQCIAC CICQCIAC = new CICQCIAC();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    TDCPBR36 TDCPTD36 = new TDCPBR36();
    IBCCHBR IBCCHBR = new IBCCHBR();
    DDCUMORG DDCUMORG = new DDCUMORG();
    DCCUORG DCCUORG = new DCCUORG();
    DCCUBRIQ DCCUBRIQ = new DCCUBRIQ();
    LNCSIQIF LNCSIQIF = new LNCSIQIF();
    LNCUCORG LNCUCORG = new LNCUCORG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPBR36 BPCPBR36;
    public void MP(SCCGWA SCCGWA, BPCPBR36 BPCPBR36) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPBR36 = BPCPBR36;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPBR36 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_LIST();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
        Z_RET();
        if (pgmRtn) return;
    }
    public void B010_CHECK_LIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPBR36.TX_DATE);
        CEP.TRC(SCCGWA, BPCPBR36.TX_JRN);
        CEP.TRC(SCCGWA, BPCPBR36.TX_SEQ);
        CEP.TRC(SCCGWA, BPCPBR36.AC_DT);
        CEP.TRC(SCCGWA, BPCPBR36.AC_ORGI_TYP);
        CEP.TRC(SCCGWA, BPCPBR36.TX_TOOL);
        CEP.TRC(SCCGWA, BPCPBR36.ACO_AC);
        CEP.TRC(SCCGWA, BPCPBR36.INCO_OLD_BR);
        CEP.TRC(SCCGWA, BPCPBR36.INCO_NEW_BR);
        B010_CHECK_LIST1();
        if (pgmRtn) return;
        B010_CHECK_LIST2();
        if (pgmRtn) return;
    }
    public void B010_CHECK_LIST1() throws IOException,SQLException,Exception {
        if (BPCPBR36.INCO_OLD_BR == BPCPBR36.INCO_NEW_BR) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_OLD_NEW_BR_SAME);
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = BPCPBR36.INCO_NEW_BR;
        CEP.TRC(SCCGWA, BPCPBR36.INCO_NEW_BR);
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_BRAN_BR_NEW = BPCPQORG.BRANCH_BR;
        WS_VIL_TYP_NEW = BPCPQORG.VIL_TYP;
        WS_FX_BUSI = BPCPQORG.FX_BUSI;
        WS_ATTR_NEW = BPCPQORG.ATTR;
        WS_SUPR_BR_NEW = BPCPQORG.SUPR_BR;
        WS_BBR_NEW = BPCPQORG.BBR;
        WS_INT_BR_FLG_NEW = BPCPQORG.INT_BR_FLG;
        if (BPCPQORG.ATTR == '0' 
            || BPCPQORG.ATTR == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NEW_BR_ATTR_ACCOUNT);
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = BPCPBR36.INCO_OLD_BR;
        CEP.TRC(SCCGWA, BPCPBR36.INCO_OLD_BR);
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_BRAN_BR_OLD = BPCPQORG.BRANCH_BR;
        WS_VIL_TYP_OLD = BPCPQORG.VIL_TYP;
        WS_ATTR_OLD = BPCPQORG.ATTR;
        WS_SUPR_BR_OLD = BPCPQORG.SUPR_BR;
        WS_BBR_OLD = BPCPQORG.BBR;
        WS_INT_BR_FLG_OLD = BPCPQORG.INT_BR_FLG;
        CEP.TRC(SCCGWA, WS_VIL_TYP_NEW);
        CEP.TRC(SCCGWA, WS_VIL_TYP_OLD);
        if (!WS_VIL_TYP_NEW.equalsIgnoreCase(WS_VIL_TYP_OLD)) {
        }
        if (WS_INT_BR_FLG_OLD != WS_INT_BR_FLG_NEW) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INT_SAME);
        }
    }
    public void B010_CHECK_LIST2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = BPCPBR36.TX_TOOL;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        if (BPCPBR36.AC_ORGI_TYP == '2') {
            if ((BPCPBR36.CCY.trim().length() == 0 
                || BPCPBR36.CCY_TYPE == ' ') 
                && BPCPBR36.SEQ == ' ') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
            }
        }
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = BPCPBR36.TX_TOOL;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_OPN_BR);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        CEP.TRC(SCCGWA, BPCPBR36.AC_ORGI_TYP);
        CEP.TRC(SCCGWA, BPCPBR36.INCO_OLD_BR);
        if (BPCPBR36.AC_ORGI_TYP == '1' 
            && BPCPBR36.INCO_OLD_BR != CICQACRI.O_DATA.O_OPN_BR) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR1);
        }
        IBS.init(SCCGWA, DCCPACTY);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase("CI3011")) {
            IBS.init(SCCGWA, CICACCU);
            DCCPACTY.INPUT.AC = BPCPBR36.TX_TOOL;
            S000_CALL_DCZPACTY();
            if (pgmRtn) return;
            if (DCCPACTY.OUTPUT.AC_TYPE == 'K') {
                CICACCU.DATA.AGR_NO = DCCPACTY.OUTPUT.N_CARD_NO;
            } else if (DCCPACTY.OUTPUT.AC_STD_FLG == '0' 
                    && DCCPACTY.OUTPUT.AC_DETL.equalsIgnoreCase("TD")) {
                IBS.init(SCCGWA, DCCUSPAC);
                DCCUSPAC.FUNC.AC = DCCPACTY.INPUT.AC;
                S000_CALL_DCZUSPAC();
                if (pgmRtn) return;
                CICACCU.DATA.AGR_NO = DCCUSPAC.OUTPUT.STD_AC;
            } else if (DCCPACTY.OUTPUT.AC_STD_FLG == '0' 
                    && !DCCPACTY.OUTPUT.AC_DETL.equalsIgnoreCase("TD")) {
                CICACCU.DATA.AGR_NO = DCCPACTY.OUTPUT.STD_AC;
            } else {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_NOT_EXIST);
            }
            CEP.TRC(SCCGWA, CICACCU.DATA.AGR_NO);
            if (CICACCU.DATA.AGR_NO.trim().length() > 0) {
                S000_CALL_CIZACCU_SECOND();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, CICACCU.DATA.STS);
        CEP.TRC(SCCGWA, BPCPBR36.TX_TOOL);
        if (CICACCU.DATA.STS == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_CLOSE);
        }
        if (BPCPBR36.AC_ORGI_TYP == '2') {
            B020_GET_ACO_NO();
            if (pgmRtn) return;
            B030_GET_ACO_NO_Q();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPBR36.AC_ORGI_TYP);
            CEP.TRC(SCCGWA, WS_ACO_AC);
            CEP.TRC(SCCGWA, WS_ACO_AC_T);
            CEP.TRC(SCCGWA, WS_CCY);
            CEP.TRC(SCCGWA, WS_OPN_BR);
            CEP.TRC(SCCGWA, BPCPBR36.INCO_OLD_BR);
            CEP.TRC(SCCGWA, WS_FX_BUSI);
            if (WS_OPN_BR != BPCPBR36.INCO_OLD_BR) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR3);
            }
            if (!WS_CCY.equalsIgnoreCase("156") 
                && WS_FX_BUSI.equalsIgnoreCase("00")) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR4);
            }
            if (WS_ACO_AC.equalsIgnoreCase(WS_ACO_AC_T)) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR7);
            }
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CNTRCT_TYP);
            if (CICQACRI.O_DATA.O_CNTRCT_TYP.equalsIgnoreCase("IBTD") 
                || CICQACRI.O_DATA.O_CNTRCT_TYP.equalsIgnoreCase("021") 
                || CICQACRI.O_DATA.O_CNTRCT_TYP.equalsIgnoreCase("031") 
                || CICQACRI.O_DATA.O_CNTRCT_TYP.equalsIgnoreCase("035") 
                || CICQACRI.O_DATA.O_CNTRCT_TYP.equalsIgnoreCase("036") 
                || CICQACRI.O_DATA.O_CNTRCT_TYP.equalsIgnoreCase("044") 
                || CICQACRI.O_DATA.O_CNTRCT_TYP.equalsIgnoreCase("045")) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR8);
            }
        }
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("LN")) {
            if (BPCPBR36.AC_ORGI_TYP == '1') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR16);
            } else {
                S000_CALL_LNZSIQIF();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, LNCSIQIF.STS);
                if (LNCSIQIF.STS == 'M' 
                    || LNCSIQIF.STS == 'D' 
                    || LNCSIQIF.STS == 'C') {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR17);
                }
            }
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPBR36.AC_ORGI_TYP);
        CEP.TRC(SCCGWA, BPCPBR36.TX_DATE);
        CEP.TRC(SCCGWA, BPCPBR36.TX_JRN);
        CEP.TRC(SCCGWA, BPCPBR36.TX_SEQ);
        CEP.TRC(SCCGWA, BPCPBR36.AC_DT);
        CEP.TRC(SCCGWA, BPCPBR36.AC_ORGI_TYP);
        CEP.TRC(SCCGWA, BPCPBR36.TX_TOOL);
        CEP.TRC(SCCGWA, BPCPBR36.ACO_AC);
        CEP.TRC(SCCGWA, BPCPBR36.INCO_OLD_BR);
        CEP.TRC(SCCGWA, BPCPBR36.INCO_NEW_BR);
        CEP.TRC(SCCGWA, WS_ACO_AC);
        if (BPCPBR36.AC_ORGI_TYP == '2') {
            if (BPCPBR36.ACO_AC.trim().length() == 0) {
                BPCPBR36.ACO_AC = WS_ACO_AC;
            }
            WS_CALL_TYP_FLG = '2';
            B021_GET_AC_APP();
            if (pgmRtn) return;
            B040_CALL_APP();
            if (pgmRtn) return;
            B050_CREATE_BPTORGE();
            if (pgmRtn) return;
            B053_UPDATE_BPTOCAC();
            if (pgmRtn) return;
        } else {
            B022_GET_ACO_AC();
            if (pgmRtn) return;
        }
    }
    public void B021_GET_AC_APP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CEP.TRC(SCCGWA, WS_J);
        CEP.TRC(SCCGWA, WS_CALL_TYP_FLG);
        CEP.TRC(SCCGWA, BPCPBR36.TX_TOOL);
        if (WS_CALL_TYP_FLG == '2') {
            CICQACAC.FUNC = 'A';
            if (BPCPBR36.AC_ORGI_TYP == '2') {
                CEP.TRC(SCCGWA, "ACOAC");
                CICQACAC.DATA.ACAC_NO = BPCPBR36.ACO_AC;
            } else {
                CEP.TRC(SCCGWA, "AC-ACOAC");
                CEP.TRC(SCCGWA, CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_J-1].ENTY_NO);
                CICQACAC.DATA.ACAC_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_J-1].ENTY_NO;
            }
            CICQACAC.DATA.AGR_NO = BPCPBR36.TX_TOOL;
        } else {
            CEP.TRC(SCCGWA, "AC");
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = BPCPBR36.TX_TOOL;
        }
        CEP.TRC(SCCGWA, CICQACAC.DATA.ACAC_NO);
        CEP.TRC(SCCGWA, CICQACAC.DATA.AGR_NO);
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_ENTY_TYP);
        WS_APP_ACO = CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR;
        WS_APP_ACAC = CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC;
        WS_ENTY_TYP = CICQACAC.O_DATA.O_ACR_DATA.O_ENTY_TYP;
    }
    public void B022_GET_ACO_AC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPBR36.TX_TOOL);
        CEP.TRC(SCCGWA, BPCPBR36.INCO_OLD_BR);
        IBS.init(SCCGWA, CICQCIAC);
        WS_FLG = ' ';
        CICQCIAC.FUNC = '3';
        CICQCIAC.DATA.AGR_NO = BPCPBR36.TX_TOOL;
        CICQCIAC.DATA.OPEN_BR = BPCPBR36.INCO_OLD_BR;
        S000_CALL_CIZQCIAC();
        if (pgmRtn) return;
        for (WS_I = 1; WS_I <= 20 
            && WS_FLG != 'Y' 
            && CICQCIAC.RC.RC_CODE == 0; WS_I += 1) {
            for (WS_J = 1; WS_J <= 100 
                && CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_J-1].ENTY_NO.trim().length() != 0; WS_J += 1) {
                CEP.TRC(SCCGWA, WS_J);
                CEP.TRC(SCCGWA, CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_J-1].ENTY_NO);
                CEP.TRC(SCCGWA, CICQCIAC.DATA.ACR_AREA.ENTY_INF[1-1].ENTY_NO);
                CEP.TRC(SCCGWA, CICQCIAC.DATA.ACR_AREA.ENTY_INF[2-1].ENTY_NO);
                CEP.TRC(SCCGWA, CICQCIAC.DATA.ACR_AREA.ENTY_INF[3-1].ENTY_NO);
                CEP.TRC(SCCGWA, CICQCIAC.DATA.ACR_AREA.ENTY_INF[4-1].ENTY_NO);
                CEP.TRC(SCCGWA, CICQCIAC.DATA.ACR_AREA.ENTY_INF[5-1].ENTY_NO);
                CEP.TRC(SCCGWA, CICQCIAC.DATA.ACR_AREA.ENTY_INF[6-1].ENTY_NO);
                CEP.TRC(SCCGWA, CICQCIAC.DATA.ACR_AREA.ENTY_INF[7-1].ENTY_NO);
                CEP.TRC(SCCGWA, CICQCIAC.DATA.ACR_AREA.ENTY_INF[8-1].ENTY_NO);
                CEP.TRC(SCCGWA, CICQCIAC.DATA.ACR_AREA.ENTY_INF[9-1].ENTY_NO);
                CEP.TRC(SCCGWA, CICQCIAC.DATA.ACR_AREA.ENTY_INF[10-1].ENTY_NO);
                IBS.init(SCCGWA, CICQACAC);
                CICQACAC.FUNC = 'A';
                CICQACAC.DATA.ACAC_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_J-1].ENTY_NO;
                CEP.TRC(SCCGWA, CICQACAC.DATA.ACAC_NO);
                CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_CTL);
                S000_CALL_CIZQACAC();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC);
                WS_CCY_ACAC = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
                CEP.TRC(SCCGWA, WS_CCY_ACAC);
                WS_CALL_TYP_FLG = '2';
                if (CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_CTL == null) CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_CTL = "";
                JIBS_tmp_int = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_CTL.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_CTL += " ";
                if (!WS_CCY_ACAC.equalsIgnoreCase("156") 
                    && WS_FX_BUSI.equalsIgnoreCase("00") 
                    && CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_CTL.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CHEB_ERROR14);
                }
                if (!WS_CCY_ACAC.equalsIgnoreCase("156") 
                    && WS_FX_BUSI.equalsIgnoreCase("00")) {
                } else {
                    B021_GET_AC_APP();
                    if (pgmRtn) return;
                    B040_CALL_APP();
                    if (pgmRtn) return;
                    B050_CREATE_BPTORGE();
                    if (pgmRtn) return;
                    B053_UPDATE_BPTOCAC();
                    if (pgmRtn) return;
                }
            }
            CEP.TRC(SCCGWA, CICQCIAC.DATA.LAST_FLG);
            if (CICQCIAC.DATA.LAST_FLG == 'Y') {
                WS_FLG = 'Y';
            }
            CEP.TRC(SCCGWA, WS_FLG);
            if (WS_FLG != 'Y') {
                IBS.init(SCCGWA, CICQCIAC);
                CICQCIAC.FUNC = '3';
                CICQCIAC.DATA.LAST_ENTY_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[100-1].ENTY_NO;
                CICQCIAC.DATA.LAST_ENTY_TYP = CICQCIAC.DATA.ACR_AREA.ENTY_INF[100-1].ENTY_TYP;
                CICQCIAC.DATA.OPEN_BR = BPCPBR36.INCO_OLD_BR;
                S000_CALL_CIZQCIAC();
                if (pgmRtn) return;
            }
        }
        WS_CALL_TYP_FLG = '1';
        B021_GET_AC_APP();
        if (pgmRtn) return;
        B040_CALL_APP();
        if (pgmRtn) return;
        B050_CREATE_BPTORGE();
        if (pgmRtn) return;
        B053_UPDATE_BPTOCAC();
        if (pgmRtn) return;
    }
    public void B031_DATA_TRANS() throws IOException,SQLException,Exception {
    }
    public void B032_DATA_TRANS() throws IOException,SQLException,Exception {
    }
    public void B040_CALL_APP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CALL_TYP_FLG);
        CEP.TRC(SCCGWA, WS_APP_ACO);
        CEP.TRC(SCCGWA, WS_APP_ACAC);
        if (WS_CALL_TYP_FLG == '1') {
            if (WS_APP_ACO.equalsIgnoreCase("DC")) {
                B041_CALL_DC();
                if (pgmRtn) return;
            } else if (WS_APP_ACO.equalsIgnoreCase("DD")) {
                B042_CALL_DD();
                if (pgmRtn) return;
            } else if (WS_APP_ACO.equalsIgnoreCase("TD")) {
                B043_CALL_TD();
                if (pgmRtn) return;
            } else if (WS_APP_ACO.equalsIgnoreCase("IB")) {
                B044_CALL_IB();
                if (pgmRtn) return;
            } else if (WS_APP_ACO.equalsIgnoreCase("LN")) {
                B045_CALL_LN();
                if (pgmRtn) return;
            }
        } else {
            if (WS_APP_ACAC.equalsIgnoreCase("DD")) {
                B042_CALL_DD();
                if (pgmRtn) return;
            } else if (WS_APP_ACAC.equalsIgnoreCase("TD")) {
                B043_CALL_TD();
                if (pgmRtn) return;
            } else if (WS_APP_ACAC.equalsIgnoreCase("IB")) {
                B044_CALL_IB();
                if (pgmRtn) return;
            } else if (WS_APP_ACO.equalsIgnoreCase("LN")) {
                B045_CALL_LN();
                if (pgmRtn) return;
            }
        }
    }
    public void B041_CALL_DC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CALL DC");
        CEP.TRC(SCCGWA, WS_CALL_TYP_FLG);
        IBS.init(SCCGWA, DCCUORG);
        DCCUORG.CARD_NO = BPCPBR36.TX_TOOL;
        DCCUORG.BR_OLD = BPCPBR36.INCO_OLD_BR;
        DCCUORG.BR_NEW = BPCPBR36.INCO_NEW_BR;
        S000_CALL_DCZUORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUORG.CARD_NO);
        CEP.TRC(SCCGWA, DCCUORG.BR_OLD);
        CEP.TRC(SCCGWA, DCCUORG.BR_NEW);
    }
    public void B042_CALL_DD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CALL DD");
        CEP.TRC(SCCGWA, WS_CALL_TYP_FLG);
        IBS.init(SCCGWA, DDCUMORG);
        DDCUMORG.DD_AC = BPCPBR36.TX_TOOL;
        if (WS_CALL_TYP_FLG == '2') {
            DDCUMORG.MIG_TYP = '2';
            if (BPCPBR36.AC_ORGI_TYP == '2') {
                DDCUMORG.DD_AC = BPCPBR36.ACO_AC;
            } else {
                CEP.TRC(SCCGWA, WS_J);
                DDCUMORG.DD_AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_J-1].ENTY_NO;
            }
        } else {
            DDCUMORG.MIG_TYP = '1';
        }
        if (WS_ENTY_TYP == '4') {
            DDCUMORG.VS_AC_FLG = 'Y';
        }
        DDCUMORG.BR_OLD = BPCPBR36.INCO_OLD_BR;
        DDCUMORG.BR_NEW = BPCPBR36.INCO_NEW_BR;
        S000_CALL_DDZUMORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCUMORG.MIG_TYP);
        CEP.TRC(SCCGWA, DDCUMORG.DD_AC);
        CEP.TRC(SCCGWA, DDCUMORG.BR_OLD);
        CEP.TRC(SCCGWA, DDCUMORG.BR_NEW);
    }
    public void B043_CALL_TD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CALL TD");
        CEP.TRC(SCCGWA, WS_CALL_TYP_FLG);
