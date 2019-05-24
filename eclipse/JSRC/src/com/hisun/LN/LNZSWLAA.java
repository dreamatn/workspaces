package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.BA.*;
import com.hisun.DC.*;
import com.hisun.AI.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSWLAA {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTAGRE_RD;
    String K_AC_TYPE = "13";
    String CPN_CI_CIZCUST = "CI-INQ-CUST";
    String K_GUAR_ADV = "1";
    String K_FACTR_ADV = "2";
    String K_BIL_A_ADV = "3";
    String K_LC_ADV = "4";
    String K_GUAR_0_ADV = "5";
    String K_DISC_ADV = "6";
    String K_SPBT_ADV = "7";
    String K_OTH_ADV = "9";
    String K_DD_AC = "01";
    String K_IA_AC = "02";
    String K_IB_AC = "03";
    String K_DC_AC = "05";
    char K_OUR_BANK = '0';
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_HIS_REMARKS = "ADVANCE TRADE";
    String K_OUTPUT_FMT = "LNT81";
    String K_OUTPUT_FMT_REVS = "LNT82";
    LNZSWLAA_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNZSWLAA_WS_TEMP_VARIABLE();
    int WS_COMP_DAYS = 0;
    int WS_COMP_DATE = 0;
    char WS_BANK_TYPE = ' ';
    String WS_SETL_AC = " ";
    String WS_SETL_AC_TYPE = " ";
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_CMMT_FLG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCOWLAD LNCOWLAD = new LNCOWLAD();
    LNCICIQ LNCICIQ = new LNCICIQ();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    LNCRCMMT LNCRCMMT = new LNCRCMMT();
    LNCPPMQ LNCPPMQ = new LNCPPMQ();
    LNRCMMT LNRCMMT = new LNRCMMT();
    LNCIGECR LNCIGECR = new LNCIGECR();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPQORG BPCPQORG = new BPCPQORG();
    LNCCTLPM LNCCTLPM = new LNCCTLPM();
    CICCUST CICCUST = new CICCUST();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    CICACCU CICACCU = new CICACCU();
    LNCCMTPM LNCCMTPM = new LNCCMTPM();
    CICSSTC CICSSTC = new CICSSTC();
    LNCSWLAD LNCSWLAD = new LNCSWLAD();
    BACUBINF BACUBINF = new BACUBINF();
    BACUPROD BACUPROD = new BACUPROD();
    LNRDISC LNRDISC = new LNRDISC();
    LNRDSBL LNRDSBL = new LNRDSBL();
    LNCRCONT LNCRCONT = new LNCRCONT();
    LNRCONT LNRCONT = new LNRCONT();
    LNRAGRE LNRAGRE = new LNRAGRE();
    BPCCINTI BPCCINTI = new BPCCINTI();
    BARMST1 BARMST1 = new BARMST1();
    BACFMST1 BACFMST1 = new BACFMST1();
    BARTXDL BARTXDL = new BARTXDL();
    BACFTXDL BACFTXDL = new BACFTXDL();
    DCCPACTY DCCPACTY = new DCCPACTY();
    AICPQIA AICPQIA = new AICPQIA();
    BPCSCGWY BPCSCGWY = new BPCSCGWY();
    SCCCLDT SCCCLDT = new SCCCLDT();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACRI CICQACRI = new CICQACRI();
    LNCCONTM LNCCONTM = new LNCCONTM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    SCCGMSG SCCGMSG;
    LNCSWLAA LNCSWLAA;
    public void MP(SCCGWA SCCGWA, LNCSWLAA LNCSWLAA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSWLAA = LNCSWLAA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNZSWLAA return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        LNCSWLAD.RC.RC_APP = "LN";
        LNCSWLAD.RC.RC_RTNCODE = 0;
        CEP.TRC(SCCGWA, GWA_SC_AREA.MULTI_PAGE_AREA.PAGE_IND);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_DATA();
        B030_GET_INFO_PROC();
        B040_DRAWDOWN_PROC();
        B000_FINANCE_HIS();
        CEP.TRC(SCCGWA, GWA_SC_AREA.MULTI_PAGE_AREA.PAGE_IND);
    }
    public void B010_CHECK_DATA() throws IOException,SQLException,Exception {
        if (LNCSWLAA.COMM_DATA.START_DT == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_VAL_DT_M_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        } else {
            if (LNCSWLAA.COMM_DATA.START_DT > SCCGWA.COMM_AREA.AC_DATE) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FWTXN_REJECT, WS_TEMP_VARIABLE.WS_MSGID);
                S000_ERR_MSG_PROC();
            }
            if (LNCSWLAA.COMM_DATA.OCAL_PD != 0 
                && LNCSWLAA.COMM_DATA.OCAL_PDU != ' ') {
                if (LNCSWLAA.COMM_DATA.OCAL_PDU == 'M') {
                    WS_COMP_DAYS = LNCSWLAA.COMM_DATA.OCAL_PD * 30;
                } else {
                    WS_COMP_DAYS = LNCSWLAA.COMM_DATA.OCAL_PD;
                }
                WS_COMP_DAYS = WS_COMP_DAYS * ( -1 );
                CEP.TRC(SCCGWA, WS_COMP_DAYS);
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
                SCCCLDT.DAYS = WS_COMP_DAYS;
                S000_CALL_SCSSCLDT();
                CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                WS_COMP_DATE = SCCCLDT.DATE2;
                if (LNCSWLAA.COMM_DATA.START_DT <= WS_COMP_DATE) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_BACK_VAL_GRAT_OCAL, WS_TEMP_VARIABLE.WS_MSGID);
                    S000_ERR_MSG_PROC();
                }
            }
        }
        CEP.TRC(SCCGWA, LNCSWLAA.COMM_DATA.ADV_TYPE);
        if (LNCSWLAA.COMM_DATA.PROD_TYP.trim().length() == 0 
            && (LNCSWLAA.COMM_DATA.ADV_TYPE.equalsIgnoreCase(K_GUAR_ADV) 
            || LNCSWLAA.COMM_DATA.ADV_TYPE.equalsIgnoreCase(K_FACTR_ADV) 
            || LNCSWLAA.COMM_DATA.ADV_TYPE.equalsIgnoreCase(K_BIL_A_ADV) 
            || LNCSWLAA.COMM_DATA.ADV_TYPE.equalsIgnoreCase(K_SPBT_ADV))) {
            IBS.init(SCCGWA, BPCSCGWY);
            BPCSCGWY.PRDT_CODE = WS_TEMP_VARIABLE.WS_PROD_CD;
            BPCSCGWY.FUNC = 'Q';
            CEP.TRC(SCCGWA, BPCSCGWY.PRDT_CODE);
            S000_CALL_BPZSCGWY();
            if (!LNCSWLAA.COMM_DATA.ADV_TYPE.equalsIgnoreCase(K_BIL_A_ADV)) {
                IBS.init(SCCGWA, LNCCMTPM);
                LNCCMTPM.FUNC = 'I';
                LNCCMTPM.KEY.TYPE = "PRDPR";
                LNCCMTPM.KEY.CODE = BPCSCGWY.PARM_CODE;
                S000_CALL_LNZCMTPM();
                LNCSWLAA.COMM_DATA.PROD_TYP = LNCCMTPM.DATA_TXT.ADV_CODE;
            } else {
                IBS.init(SCCGWA, BACUPROD);
                BACUPROD.FUNC = 'I';
                BACUPROD.KEY.CODE = BPCSCGWY.PARM_CODE;
                BACUPROD.KEY.TYPE = "PRDPR";
                S000_CALL_BAZUPROD();
                LNCSWLAA.COMM_DATA.PROD_TYP = BACUPROD.DATA_TXT.DLY_CODE;
            }
        }
        if (LNCSWLAA.COMM_DATA.PROD_TYP.trim().length() > 0 
            && LNCSWLAA.COMM_DATA.ADV_TYPE.equalsIgnoreCase(K_GUAR_ADV) 
            || LNCSWLAA.COMM_DATA.ADV_TYPE.equalsIgnoreCase(K_FACTR_ADV) 
            || LNCSWLAA.COMM_DATA.ADV_TYPE.equalsIgnoreCase(K_LC_ADV)) {
            IBS.init(SCCGWA, BPCSCGWY);
            BPCSCGWY.PRDT_CODE = LNCSWLAA.COMM_DATA.PROD_TYP;
            BPCSCGWY.FUNC = 'Q';
            CEP.TRC(SCCGWA, BPCSCGWY.PRDT_CODE);
            S000_CALL_BPZSCGWY();
            CEP.TRC(SCCGWA, BPCSCGWY.PARM_CODE);
        }
        IBS.init(SCCGWA, CICACCU);
        IBS.init(SCCGWA, CICQACAC);
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = LNCSWLAA.COMM_DATA.CCY;
        CEP.TRC(SCCGWA, LNCSWLAA.COMM_DATA.CCY);
        S000_CALL_BPZQCCY();
        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_CCY);
        if (LNCSWLAA.COMM_DATA.AMT < 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_M_GREATER_THAN_Z, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = (short) LNCSWLAA.COMM_DATA.AMT;
            S000_ERR_MSG_PROC();
        }
        if (LNCSWLAA.COMM_DATA.CPND_USE == 'Y' 
            && (LNCSWLAA.COMM_DATA.CPND_RATT.trim().length() > 0 
            || LNCSWLAA.COMM_DATA.CPND_IRATE != 0)) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CPND_USE_I, WS_TEMP_VARIABLE.WS_MSGID);
            if (LNCSWLAA.COMM_DATA.CPND_USE == ' ') WS_TEMP_VARIABLE.WS_FLD_NO = 0;
            else WS_TEMP_VARIABLE.WS_FLD_NO = Short.parseShort(""+LNCSWLAA.COMM_DATA.CPND_USE);
            S000_ERR_MSG_PROC();
        }
        if (LNCSWLAA.COMM_DATA.DRAW_ACT.trim().length() == 0 
            || LNCSWLAA.COMM_DATA.DRAW_BK_TP != K_OUR_BANK) {
            WS_BANK_TYPE = LNCSWLAA.COMM_DATA.DRAW_BK_TP;
            WS_SETL_AC = LNCSWLAA.COMM_DATA.DRAW_AC;
            B110_GET_AC_INFO();
            LNCSWLAA.COMM_DATA.DRAW_ACT = WS_SETL_AC_TYPE;
            LNCSWLAA.COMM_DATA.DRAW_AC = WS_SETL_AC;
        }
        if (LNCSWLAA.COMM_DATA.PAY_AC_T.trim().length() == 0 
            || LNCSWLAA.COMM_DATA.PAY_BK_TP != K_OUR_BANK) {
            WS_BANK_TYPE = LNCSWLAA.COMM_DATA.PAY_BK_TP;
            WS_SETL_AC = LNCSWLAA.COMM_DATA.PAY_AC;
            B110_GET_AC_INFO();
            LNCSWLAA.COMM_DATA.PAY_AC_T = WS_SETL_AC_TYPE;
            LNCSWLAA.COMM_DATA.PAY_AC = WS_SETL_AC;
        }
    }
    public void B110_GET_AC_INFO() throws IOException,SQLException,Exception {
        if (WS_BANK_TYPE == K_OUR_BANK) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = WS_SETL_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("IB")) {
                WS_SETL_AC_TYPE = K_IB_AC;
            } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
                WS_SETL_AC_TYPE = K_DD_AC;
            } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
                WS_SETL_AC_TYPE = K_DC_AC;
            } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                WS_SETL_AC_TYPE = K_IA_AC;
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_AC_TYP_NOT_MATCH, WS_TEMP_VARIABLE.WS_MSGID);
                S000_ERR_MSG_PROC();
            }
        } else {
            IBS.init(SCCGWA, AICPQIA);
            AICPQIA.CD.AC_TYP = "3";
            AICPQIA.CD.BUSI_KND = "LNDWSUS";
            AICPQIA.BR = LNCSWLAA.COMM_DATA.DOMI_BR;
            AICPQIA.CCY = LNCSWLAA.COMM_DATA.CCY;
            AICPQIA.SIGN = 'D';
            S000_CALL_AIZPQIA();
            WS_SETL_AC = AICPQIA.AC;
            WS_SETL_AC_TYPE = K_IA_AC;
        }
    }
    public void B030_GET_INFO_PROC() throws IOException,SQLException,Exception {
    }
    public void B040_DRAWDOWN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSWLAD);
        LNCSWLAD.COMM_DATA.DRAWR_NO = LNCSWLAA.COMM_DATA.DRAW_NO;
        LNCSWLAD.COMM_DATA.PAPER_NO = LNCSWLAA.COMM_DATA.CMMT_NO;
        LNCSWLAD.COMM_DATA.CTA_NO = LNCSWLAA.COMM_DATA.CONT_NO;
        LNCSWLAD.COMM_DATA.VRTU_FLG = 'Y';
        LNCSWLAD.COMM_DATA.BOOK_BR = LNCSWLAA.COMM_DATA.DOMI_BR;
        LNCSWLAD.COMM_DATA.DOMI_BR = LNCSWLAA.COMM_DATA.DOMI_BR;
        LNCSWLAD.COMM_DATA.CI_NO = LNCSWLAA.COMM_DATA.CI_NO;
        LNCSWLAD.COMM_DATA.PROD_CD = LNCSWLAA.COMM_DATA.PROD_TYP;
        LNCSWLAD.COMM_DATA.PROD_NM = LNCSWLAA.COMM_DATA.PROD_NM;
        LNCSWLAD.COMM_DATA.ACTV_DT = LNCSWLAA.COMM_DATA.START_DT;
        LNCSWLAD.COMM_DATA.VAL_DT = LNCSWLAA.COMM_DATA.START_DT;
        LNCSWLAD.COMM_DATA.DUE_DT = LNCSWLAA.COMM_DATA.MATU_DT;
        LNCSWLAD.COMM_DATA.CAL_FST_DT = LNCSWLAA.COMM_DATA.MATU_DT;
        LNCSWLAD.COMM_DATA.CCY = LNCSWLAA.COMM_DATA.CCY;
        LNCSWLAD.COMM_DATA.CCY_TYP = LNCSWLAA.COMM_DATA.CCY_TYP;
        LNCSWLAD.COMM_DATA.CONT_AMT = LNCSWLAA.COMM_DATA.AMT;
        LNCSWLAD.COMM_DATA.PRINCIPAL = LNCSWLAD.COMM_DATA.CONT_AMT;
        LNCSWLAD.COMM_DATA.DRAW_BK_TP = LNCSWLAA.COMM_DATA.DRAW_BK_TP;
        CEP.TRC(SCCGWA, LNCSWLAA.COMM_DATA.DRAW_ACT);
        LNCSWLAD.COMM_DATA.DRAW_AC_TYP = LNCSWLAA.COMM_DATA.DRAW_ACT;
        LNCSWLAD.COMM_DATA.DRAW_AC = LNCSWLAA.COMM_DATA.DRAW_AC;
        LNCSWLAD.COMM_DATA.DRAW_AC_NAME = LNCSWLAA.COMM_DATA.DR_AC_NM;
        LNCSWLAD.COMM_DATA.DRAW_SEQ = LNCSWLAA.COMM_DATA.DRAW_SEQ;
        LNCSWLAD.COMM_DATA.PAY_BK_TP = LNCSWLAA.COMM_DATA.PAY_BK_TP;
        LNCSWLAD.COMM_DATA.PAY_AC_TYP = LNCSWLAA.COMM_DATA.PAY_AC_T;
        LNCSWLAD.COMM_DATA.PAY_AC = LNCSWLAA.COMM_DATA.PAY_AC;
        LNCSWLAD.COMM_DATA.PAY_AC_NAME = LNCSWLAA.COMM_DATA.PAY_ACNM;
        LNCSWLAD.COMM_DATA.PEN_RATT = LNCSWLAA.COMM_DATA.PEN_RATT;
        LNCSWLAD.COMM_DATA.PEN_RRAT = LNCSWLAA.COMM_DATA.PEN_RRAT;
        LNCSWLAD.COMM_DATA.PEN_TYPE = LNCSWLAA.COMM_DATA.PEN_TYP;
        LNCSWLAD.COMM_DATA.PEN_RATC = LNCSWLAA.COMM_DATA.PEN_RATC;
        LNCSWLAD.COMM_DATA.PEN_SPR = LNCSWLAA.COMM_DATA.PEN_SPR;
        LNCSWLAD.COMM_DATA.PEN_PCT = LNCSWLAA.COMM_DATA.PEN_PCT;
        LNCSWLAD.COMM_DATA.PEN_IRAT = LNCSWLAA.COMM_DATA.PEN_IRAT;
        LNCSWLAD.COMM_DATA.CPND_USE = LNCSWLAA.COMM_DATA.CPND_USE;
        if (LNCSWLAA.COMM_DATA.INT_MTH == ' ') {
            LNCSWLAD.COMM_DATA.INT_MTH = '0';
        } else {
            LNCSWLAD.COMM_DATA.INT_MTH = LNCSWLAA.COMM_DATA.INT_MTH;
        }
        LNCSWLAD.COMM_DATA.CPND_RATT = LNCSWLAA.COMM_DATA.CPND_RATT;
        LNCSWLAD.COMM_DATA.CPND_RTYPE = LNCSWLAA.COMM_DATA.CPND_RTYPE;
        LNCSWLAD.COMM_DATA.CPND_TYP = LNCSWLAA.COMM_DATA.CPND_TYP;
        LNCSWLAD.COMM_DATA.CPND_RATC = LNCSWLAA.COMM_DATA.CPND_RATC;
        LNCSWLAD.COMM_DATA.CPND_SPR = LNCSWLAA.COMM_DATA.CPND_SPR;
        LNCSWLAD.COMM_DATA.CPND_PCT = LNCSWLAA.COMM_DATA.CPND_PCT;
        LNCSWLAD.COMM_DATA.CPND_IRATE = LNCSWLAA.COMM_DATA.CPND_IRATE;
        LNCSWLAD.COMM_DATA.CUSTM1 = LNCSWLAA.COMM_DATA.CUSTM1;
        LNCSWLAD.COMM_DATA.CUSTM2 = LNCSWLAA.COMM_DATA.CUSTM2;
        LNCSWLAD.COMM_DATA.CUSTM3 = LNCSWLAA.COMM_DATA.CUSTM3;
        LNCSWLAD.COMM_DATA.BAR_CD = LNCSWLAA.COMM_DATA.BAR_CD;
        LNCSWLAD.COMM_DATA.UNIT_CD = LNCSWLAA.COMM_DATA.UNIT_CD;
        LNCSWLAD.COMM_DATA.FLT_MTH = '0';
        LNCSWLAD.COMM_DATA.REPY_MTH = '1';
        LNCSWLAD.COMM_DATA.BREP_MTH = '1';
        LNCSWLAD.COMM_DATA.PYP_CIRC = '1';
        LNCSWLAD.COMM_DATA.PAYI_PERD = '1';
        LNCSWLAD.COMM_DATA.ACCRUAL_TYPE = '0';
        LNCSWLAD.COMM_DATA.AUTO_DFLG = 'N';
        LNCSWLAD.COMM_DATA.REL_CTA_NO = LNCSWLAD.COMM_DATA.CTA_NO;
        LNCSWLAD.COMM_DATA.OCAL_PERD = LNCSWLAA.COMM_DATA.OCAL_PD;
        LNCSWLAD.COMM_DATA.OCAL_PERD_UNIT = LNCSWLAA.COMM_DATA.OCAL_PDU;
        LNCSWLAD.COMM_DATA.INT_DAY_BASIS = "01";
        LNCSWLAD.COMM_DATA.HOLIDAY_METH = 'F';
        LNCSWLAD.COMM_DATA.HOLIDAY_OVER = 'Y';
        LNCSWLAD.COMM_DATA.INT_PER_STRT = LNCSWLAA.COMM_DATA.START_DT;
        LNCSWLAD.COMM_DATA.ADV_TYPE = LNCSWLAA.COMM_DATA.ADV_TYPE;
        LNCSWLAD.COMM_DATA.DRW_AMT = LNCSWLAA.COMM_DATA.DRW_AMT;
        LNCSWLAD.COMM_DATA.TRAN_TYPE = 'D';
        CEP.TRC(SCCGWA, LNCSWLAD.COMM_DATA.CTA_NO);
        S000_CALL_LNZSWLAD();
    }
    public void B040_BA_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            B041_BA_STS_REV();
            B042_BA_MOD_TRANH();
        } else {
            B041_BA_STS_PROC();
        }
    }
    public void B041_BA_STS_REV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BARMST1);
        IBS.init(SCCGWA, BACFMST1);
        if (LNCSWLAA.COMM_DATA.DRAW_NO.trim().length() == 0) BARMST1.KEY.ACCT_CNT = 0;
        else BARMST1.KEY.ACCT_CNT = Short.parseShort(LNCSWLAA.COMM_DATA.DRAW_NO);
        BACFMST1.FUNC = 'R';
        S000_CALL_BAZFMST1();
        BARMST1.AMT_STS = '3';
        BACFMST1.FUNC = 'U';
        S000_CALL_BAZFMST1();
    }
    public void B041_BA_STS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BARMST1);
        IBS.init(SCCGWA, BACFMST1);
        if (LNCSWLAA.COMM_DATA.DRAW_NO.trim().length() == 0) BARMST1.KEY.ACCT_CNT = 0;
        else BARMST1.KEY.ACCT_CNT = Short.parseShort(LNCSWLAA.COMM_DATA.DRAW_NO);
        BACFMST1.FUNC = 'R';
        S000_CALL_BAZFMST1();
        BARMST1.AMT_STS = '4';
        BACFMST1.FUNC = 'U';
        S000_CALL_BAZFMST1();
    }
    public void B042_BA_ADD_TRANH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACFTXDL);
        IBS.init(SCCGWA, BARTXDL);
        BACFTXDL.FUNC = 'A';
        BARTXDL.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARTXDL.KEY.CRE_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BARTXDL.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BARTXDL.PRPH_SYS_CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
        BARTXDL.PRPH_SYS_DT = SCCGWA.COMM_AREA.TR_DATE;
        BARTXDL.PRPH_JRN = "" + SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        JIBS_tmp_int = BARTXDL.PRPH_JRN.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BARTXDL.PRPH_JRN = "0" + BARTXDL.PRPH_JRN;
        BARTXDL.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BARTXDL.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (LNCSWLAA.COMM_DATA.DRAW_NO.trim().length() == 0) BARTXDL.ACCT_CNT = 0;
        else BARTXDL.ACCT_CNT = Short.parseShort(LNCSWLAA.COMM_DATA.DRAW_NO);
        BARTXDL.TX_AC = LNCSWLAA.COMM_DATA.CONT_NO;
        BARTXDL.SUSP_NO = LNCSWLAA.COMM_DATA.DRAW_SEQ;
        BARTXDL.TX_CUR = LNCSWLAA.COMM_DATA.CCY;
        BARTXDL.TX_AMT = LNCSWLAA.COMM_DATA.AMT;
        BARTXDL.BILL_NO = BACUBINF.COMM_DATA.BILL_NO;
        BARTXDL.REC_FLG = '2';
        BARTXDL.REV_FLG = 'N';
        S000_CALL_BAZFTXDL();
    }
    public void B000_FINANCE_HIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.REF_NO = LNCSWLAA.COMM_DATA.CONT_NO;
        BPCPFHIS.DATA.AC = LNCSWLAA.COMM_DATA.CMMT_NO;
        BPCPFHIS.DATA.ACO_AC = LNCSWLAA.COMM_DATA.CONT_NO;
        BPCPFHIS.DATA.CI_NO = LNCSWLAA.COMM_DATA.CI_NO;
        BPCPFHIS.DATA.TX_VAL_DT = LNCSWLAA.COMM_DATA.START_DT;
        BPCPFHIS.DATA.TX_CCY = LNCSWLAA.COMM_DATA.CCY;
        BPCPFHIS.DATA.TX_AMT = LNCSWLAA.COMM_DATA.AMT;
        BPCPFHIS.DATA.PROD_CD = LNCSWLAA.COMM_DATA.PROD_TYP;
        BPCPFHIS.DATA.REMARK = K_HIS_REMARKS;
        BPCPFHIS.DATA.TX_MMO = "12010001";
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPCPFHIS.DATA.FMT_CODE = K_OUTPUT_FMT_REVS;
        } else {
            BPCPFHIS.DATA.FMT_CODE = K_OUTPUT_FMT;
        }
        BPCPFHIS.DATA.FMT_LEN = 1391;
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, LNCSWLAA.COMM_DATA);
        S000_CALL_BPZPFHIS();
    }
    public void B042_BA_MOD_TRANH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACFTXDL);
        IBS.init(SCCGWA, BARTXDL);
        BACFTXDL.FUNC = 'R';
        BARTXDL.KEY.TX_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        BARTXDL.KEY.CRE_JRN = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        if (LNCSWLAA.COMM_DATA.DRAW_NO.trim().length() == 0) BARTXDL.ACCT_CNT = 0;
        else BARTXDL.ACCT_CNT = Short.parseShort(LNCSWLAA.COMM_DATA.DRAW_NO);
        BARTXDL.TX_AC = LNCSWLAA.COMM_DATA.CONT_NO;
        S000_CALL_BAZFTXDL();
        BACFTXDL.FUNC = 'U';
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BARTXDL.REV_FLG = 'R';
        } else {
            BARTXDL.REV_FLG = 'Y';
        }
        BARTXDL.REV_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARTXDL.REV_JRN = SCCGWA.COMM_AREA.JRN_NO;
        S000_CALL_BAZFTXDL();
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        CEP.TRC(SCCGWA, SCCCLDT.RC);
        if (SCCCLDT.RC != 0) {
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_AP = "SC";
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_CODE = SCCCLDT.RC;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BAZUBINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-INQ-BILL-INF", BACUBINF);
        if (BACUBINF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BACUBINF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC, true);
        CEP.TRC(SCCGWA, CICQACAC.RC.RC_CODE);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void S000_CALL_LNZICIQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-CI-INFO", LNCICIQ);
        if (LNCICIQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICIQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZSWLAD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-WLLOAN-ADD2DD", LNCSWLAD);
        if (LNCSWLAD.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSWLAD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZRCMMT() throws IOException,SQLException,Exception {
        LNCRCMMT.REC_PTR = LNRCMMT;
        LNCRCMMT.REC_LEN = 1235;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCMMT", LNCRCMMT);
        if (LNCRCMMT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRCMMT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZPPMQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-PRD-PRM-INQ", LNCPPMQ);
        if (LNCPPMQ.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCPPMQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSCGWY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSCGWY.PRDT_CODE);
        CEP.TRC(SCCGWA, BPCSCGWY.CHANG_WAY);
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-CGWY", BPCSCGWY);
        if (BPCSCGWY.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSCGWY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZCTLPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-PRM-CTLPM-MAINT", LNCCTLPM);
        if (LNCCTLPM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCTLPM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CI_CIZCUST, CICCUST);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_LNZCMTPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-PRM-CMTPM-MAINT", LNCCMTPM);
        if (LNCCMTPM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCMTPM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void S000_CALL_LNZRCONT() throws IOException,SQLException,Exception {
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 416;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRCONT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCONTM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BAZFTXDL() throws IOException,SQLException,Exception {
        BACFTXDL.REC_PTR = BARTXDL;
        BACFTXDL.REC_LEN = 514;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFTXDL", BACFTXDL);
        if (BACFTXDL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BACFTXDL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BAZFMST1() throws IOException,SQLException,Exception {
        BACFMST1.REC_PTR = BARMST1;
        BACFMST1.REC_LEN = 1163;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFMST1", BACFMST1);
        if (BACFMST1.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BACFMST1.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BAZUPROD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-PROD-MAINTAIN", BACUPROD);
        if (BACUPROD.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BACUPROD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZCINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-C-INTR-INQ", BPCCINTI);
        if (BPCCINTI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCINTI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READUPD_AGRE() throws IOException,SQLException,Exception {
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        LNTAGRE_RD.upd = true;
        IBS.READ(SCCGWA, LNRAGRE, LNTAGRE_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_REWRITE_AGRE() throws IOException,SQLException,Exception {
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        IBS.REWRITE(SCCGWA, LNRAGRE, LNTAGRE_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        CEP.TRC(SCCGWA, AICPQIA.AC);
        if (AICPQIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCSWLAA.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCSWLAA=");
            CEP.TRC(SCCGWA, LNCSWLAA);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
