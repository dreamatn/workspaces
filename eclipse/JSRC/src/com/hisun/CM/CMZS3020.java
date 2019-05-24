package com.hisun.CM;

import com.hisun.SC.*;
import com.hisun.AI.*;
import com.hisun.BP.*;
import com.hisun.DD.*;
import com.hisun.DC.*;
import com.hisun.CI.*;
import com.hisun.TC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CMZS3020 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm TCTLOGR1_RD;
    DBParm TCTLOGR2_RD;
    DBParm TCTLOGR3_RD;
    DBParm TCTLOGR4_RD;
    boolean pgmRtn = false;
    String CPN_R_PRTR_MAINT = "BP-R-PRTR-MAINT";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_REMARKS = "FEE GLRP INFOMATION MAINTAIN";
    String K_CPY_BPRFBAS = "AICHGLRP";
    String WS_ERR_MSG = " ";
    String WS_CI_NM = " ";
    String WS_ID_TYP = " ";
    String WS_ID_NO = " ";
    String WS_TEL_NO = " ";
    short WS_I = 0;
    double WS_AC_BAL = 0;
    double WS_FEE_AMT = 0;
    double WS_AVA_BAL = 0;
    String WS_CI_NO = " ";
    String WS_PQORG_CHN_NM = " ";
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AIRRPT AIRRPT = new AIRRPT();
    AICHGLRP AICNGLRP = new AICHGLRP();
    AICHGLRP AICOGLRP = new AICHGLRP();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    AICRGLRP AICRGLRP = new AICRGLRP();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    BPCFFCON BPCFFCON = new BPCFFCON();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    BPCUABOX BPCUABOX = new BPCUABOX();
    DDCSTRAC DDCSTRAC = new DDCSTRAC();
    DCCUBRRC DCCUBRRC = new DCCUBRRC();
    CICCUST CICCUST = new CICCUST();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    BPCFFCAL BPCFFCAL = new BPCFFCAL();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    CMCF020 CMCF020 = new CMCF020();
    TCCTOA TCCTOA = new TCCTOA();
    TCRLOGR TCRLOGR = new TCRLOGR();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPARMC BPCPARMC = new BPCPARMC();
    BPCTCFEE BPCTCFEE = new BPCTCFEE();
    CICQACAC CICQACAC = new CICQACAC();
    CMCSIQAC CMCSIQAC = new CMCSIQAC();
    CICQACRI CICQACRI = new CICQACRI();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CMCS3020 CMCS3020;
    public void MP(SCCGWA SCCGWA, CMCS3020 CMCS3020) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCS3020 = CMCS3020;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMZS3020 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CMCF020);
        IBS.init(SCCGWA, CICCUST);
        IBS.init(SCCGWA, DDCIQBAL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_CI_CHECK();
        if (pgmRtn) return;
        if (CMCS3020.TX_TYP == '1') {
            B160_READ_TCTLOG();
            if (pgmRtn) return;
        } else {
        }
        B270_CHK_CARD();
        if (pgmRtn) return;
        B300_TRN_PROC();
        if (pgmRtn) return;
        B400_FEE_ROC();
        if (pgmRtn) return;
        B500_INQ_BAL();
        if (pgmRtn) return;
        B600_OUTPUT();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (CMCS3020.BUSI_KND == ' ') {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_KND_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3020.TXN_CCY.trim().length() == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_CCY_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3020.TXN_AMT == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_AMT_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3020.ID_FLG == 'Y' 
            && (CMCS3020.ID_TYP.trim().length() == 0 
            || CMCS3020.ID_NO.trim().length() == 0)) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_ID_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3020.NM_FLG == 'Y' 
            && CMCS3020.CI_NM.trim().length() == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_NM_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3020.TEL_FLG == 'Y' 
            && CMCS3020.TEL_NO.trim().length() == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_TEL_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3020.SLT_NO.trim().length() == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_SLTNO_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3020.SLT_AC.trim().length() == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_SLTAC_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        CEP.TRC(SCCGWA, CMCS3020.STL_DT);
        if (CMCS3020.STL_DT == ' ') {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_SLTDT_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPCPARMC);
        BPCPRMR.FUNC = ' ';
        BPCPARMC.KEY.TYP = "PARMC";
        if (BPCPARMC.KEY.CD == null) BPCPARMC.KEY.CD = "";
        JIBS_tmp_int = BPCPARMC.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCPARMC.KEY.CD += " ";
        BPCPARMC.KEY.CD = "MMO" + BPCPARMC.KEY.CD.substring(3);
        if (CMCS3020.TXN_RMK == null) CMCS3020.TXN_RMK = "";
        JIBS_tmp_int = CMCS3020.TXN_RMK.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) CMCS3020.TXN_RMK += " ";
        if (BPCPARMC.KEY.CD == null) BPCPARMC.KEY.CD = "";
        JIBS_tmp_int = BPCPARMC.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCPARMC.KEY.CD += " ";
        BPCPARMC.KEY.CD = BPCPARMC.KEY.CD.substring(0, 6 - 1) + CMCS3020.TXN_RMK.substring(0, 4) + BPCPARMC.KEY.CD.substring(6 + 9 - 1);
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
    }
    public void B200_CI_CHECK() throws IOException,SQLException,Exception {
        CICCUST.FUNC = 'A';
        CICCUST.DATA.AGR_NO = CMCS3020.CARD_NO;
        R000_CALL_CICCUST();
        if (pgmRtn) return;
        WS_CI_NM = CICCUST.O_DATA.O_CI_NM;
        WS_ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
        WS_ID_NO = CICCUST.O_DATA.O_ID_NO;
        WS_TEL_NO = CICCUST.O_DATA.O_TEL_NO;
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_TEL_NO);
        WS_CI_NO = CICCUST.O_DATA.O_CI_NO;
        if (CMCS3020.TEL_FLG == 'Y' 
            && !CMCS3020.TEL_NO.equalsIgnoreCase(WS_TEL_NO)) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_TELNO_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3020.NM_FLG == 'Y' 
            && !CMCS3020.CI_NM.equalsIgnoreCase(WS_CI_NM)) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_CINM_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3020.ID_FLG == 'Y' 
            && !CMCS3020.ID_TYP.equalsIgnoreCase(WS_ID_TYP)) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_IDTYP_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3020.ID_FLG == 'Y') {
            if (!CMCS3020.ID_TYP.equalsIgnoreCase(WS_ID_TYP)) {
                CEP.TRC(SCCGWA, "IDERR001");
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_IDTYP_ERR);
            }
            CEP.TRC(SCCGWA, WS_ID_NO);
            if (CMCS3020.ID_TYP.equalsIgnoreCase("10100") 
                && !CMCS3020.ID_NO.equalsIgnoreCase(WS_ID_NO)) {
                if (WS_ID_NO == null) WS_ID_NO = "";
                JIBS_tmp_int = WS_ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
                for (WS_I = 1; WS_I <= 70 
                    && (WS_ID_NO.substring(WS_I - 1, WS_I + 1 - 1).trim().length() != 0); WS_I += 1) {
                }
                WS_I -= 1;
                if (WS_ID_NO == null) WS_ID_NO = "";
                JIBS_tmp_int = WS_ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
                if (WS_ID_NO.substring(WS_I - 1, WS_I + 1 - 1).equalsIgnoreCase("X")) {
                    WS_I -= 1;
                }
                if (WS_I < 6) {
                    CEP.TRC(SCCGWA, "IDERR002");
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_IDNO_ERR, WS_ID_NO);
                }
                WS_I -= 5;
                CEP.TRC(SCCGWA, WS_I);
                if (WS_ID_NO == null) WS_ID_NO = "";
                JIBS_tmp_int = WS_ID_NO.length();
                for (int i=0;i<70-JIBS_tmp_int;i++) WS_ID_NO += " ";
                if (!WS_ID_NO.substring(WS_I - 1, WS_I + 6 - 1).equalsIgnoreCase(CMCS3020.ID_NO)) {
                    CEP.TRC(SCCGWA, "IDERR003");
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_IDNO_ERR, WS_ID_NO);
                }
            }
        }
        CEP.TRC(SCCGWA, CMCS3020.SLT_AC);
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        if (CMCS3020.SLT_AC == null) CMCS3020.SLT_AC = "";
        JIBS_tmp_int = CMCS3020.SLT_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) CMCS3020.SLT_AC += " ";
        if (CMCS3020.SLT_AC.substring(0, 6).trim().length() == 0) BPCPQORG.BR = 0;
        else BPCPQORG.BR = Integer.parseInt(CMCS3020.SLT_AC.substring(0, 6));
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_PQORG_CHN_NM = BPCPQORG.CHN_NM;
        CEP.TRC(SCCGWA, BPCPQORG.BR);
        CEP.TRC(SCCGWA, BPCPQORG.CHN_ADDR);
        CEP.TRC(SCCGWA, BPCPQORG.LINK_TEL);
        CEP.TRC(SCCGWA, BPCPQORG.CHN_NM);
        CEP.TRC(SCCGWA, CMCS3020.CITY_FLG);
        CEP.TRC(SCCGWA, CMCS3020.AREA_CD);
        if ((CMCS3020.CITY_FLG == ' ' 
            || CMCS3020.CITY_FLG == 0X00) 
            && (CMCS3020.AREA_CD.trim().length() == 0 
            || CMCS3020.AREA_CD.charAt(0) == 0X00)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AREA_CD_M_INPUT, CMCS3020.AREA_CD);
        }
        if (CMCS3020.CITY_FLG == ' ') {
            R000_CHK_CITY_FLG_BY_AREA_CD();
            if (pgmRtn) return;
        } else if (CMCS3020.CITY_FLG == '0'
            || CMCS3020.CITY_FLG == '1'
            || CMCS3020.CITY_FLG == '2'
            || CMCS3020.CITY_FLG == '3'
            || CMCS3020.CITY_FLG == '4'
            || CMCS3020.CITY_FLG == '5') {
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TXN_REGION_INP_ERR);
        }
        R000_CHECK_CITY_FLG_FOR_DWK();
        if (pgmRtn) return;
    }
    public void B160_READ_TCTLOG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TCRLOGR);
        CEP.TRC(SCCGWA, CMCS3020.REQ_JRN);
        TCRLOGR.KEY.REQ_SYS_JRN = CMCS3020.REQ_JRN;
        TCTLOGR1_RD = new DBParm();
        TCTLOGR1_RD.TableName = "TCTLOGR1";
        TCTLOGR1_RD.where = "REQ_SYS_JRN = :TCRLOGR.KEY.REQ_SYS_JRN";
        IBS.READ(SCCGWA, TCRLOGR, this, TCTLOGR1_RD);
        CEP.TRC(SCCGWA, "111");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            TCTLOGR2_RD = new DBParm();
            TCTLOGR2_RD.TableName = "TCTLOGR2";
            TCTLOGR2_RD.where = "REQ_SYS_JRN = :TCRLOGR.KEY.REQ_SYS_JRN";
            IBS.READ(SCCGWA, TCRLOGR, this, TCTLOGR2_RD);
            CEP.TRC(SCCGWA, "22222");
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                TCTLOGR3_RD = new DBParm();
                TCTLOGR3_RD.TableName = "TCTLOGR3";
                TCTLOGR3_RD.where = "REQ_SYS_JRN = :TCRLOGR.KEY.REQ_SYS_JRN";
                IBS.READ(SCCGWA, TCRLOGR, this, TCTLOGR3_RD);
                CEP.TRC(SCCGWA, "33333");
                if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    TCTLOGR4_RD = new DBParm();
                    TCTLOGR4_RD.TableName = "TCTLOGR4";
                    TCTLOGR4_RD.where = "REQ_SYS_JRN = :TCRLOGR.KEY.REQ_SYS_JRN";
                    IBS.READ(SCCGWA, TCRLOGR, this, TCTLOGR4_RD);
                    CEP.TRC(SCCGWA, "44444");
                    if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                    } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                        CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_JRN_ERR);
                    } else {
                        CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_JRN_ERR);
                    }
                } else {
                    CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_JRN_ERR);
                }
            } else {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_JRN_ERR);
            }
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_JRN_ERR);
        }
        if (TCRLOGR.TR_STS != '1' 
            || TCRLOGR.AC_DATE != SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_JRN_ERR);
        }
    }
    public void B270_CHK_CARD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMCSIQAC);
        CMCSIQAC.I_FUNC = '3';
        CMCSIQAC.I_CUS_AC = CMCS3020.CARD_NO;
        CMCSIQAC.I_CCY = CMCS3020.TXN_CCY;
        S000_CALL_CMZSIQAC();
        if (pgmRtn) return;
        if (CMCSIQAC.OUT_INF.DC_INF.DC_CD_STS != 'N') {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_STS_ERR);
            if (CMCSIQAC.OUT_INF.DC_INF.DC_CD_STSW == null) CMCSIQAC.OUT_INF.DC_INF.DC_CD_STSW = "";
            JIBS_tmp_int = CMCSIQAC.OUT_INF.DC_INF.DC_CD_STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) CMCSIQAC.OUT_INF.DC_INF.DC_CD_STSW += " ";
        } else if (CMCSIQAC.OUT_INF.DC_INF.DC_CD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_STS1_ERR);
            if (CMCSIQAC.OUT_INF.DC_INF.DC_CD_STSW == null) CMCSIQAC.OUT_INF.DC_INF.DC_CD_STSW = "";
            JIBS_tmp_int = CMCSIQAC.OUT_INF.DC_INF.DC_CD_STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) CMCSIQAC.OUT_INF.DC_INF.DC_CD_STSW += " ";
        } else if (CMCSIQAC.OUT_INF.DC_INF.DC_CD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_STS2_ERR);
            if (CMCSIQAC.OUT_INF.DC_INF.DC_CD_STSW == null) CMCSIQAC.OUT_INF.DC_INF.DC_CD_STSW = "";
            JIBS_tmp_int = CMCSIQAC.OUT_INF.DC_INF.DC_CD_STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) CMCSIQAC.OUT_INF.DC_INF.DC_CD_STSW += " ";
        } else if (CMCSIQAC.OUT_INF.DC_INF.DC_CD_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_STS8_ERR);
            if (CMCSIQAC.OUT_INF.DC_INF.DC_ACO_STSW == null) CMCSIQAC.OUT_INF.DC_INF.DC_ACO_STSW = "";
            JIBS_tmp_int = CMCSIQAC.OUT_INF.DC_INF.DC_ACO_STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) CMCSIQAC.OUT_INF.DC_INF.DC_ACO_STSW += " ";
        } else if (CMCSIQAC.OUT_INF.DC_INF.DC_ACO_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_ACO_STS2_ERR);
            if (CMCSIQAC.OUT_INF.DC_INF.DC_ACO_STSW == null) CMCSIQAC.OUT_INF.DC_INF.DC_ACO_STSW = "";
            JIBS_tmp_int = CMCSIQAC.OUT_INF.DC_INF.DC_ACO_STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) CMCSIQAC.OUT_INF.DC_INF.DC_ACO_STSW += " ";
        } else if (CMCSIQAC.OUT_INF.DC_INF.DC_ACO_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_ACO_STS3_ERR);
            if (CMCSIQAC.OUT_INF.DC_INF.DC_ACO_STSW == null) CMCSIQAC.OUT_INF.DC_INF.DC_ACO_STSW = "";
            JIBS_tmp_int = CMCSIQAC.OUT_INF.DC_INF.DC_ACO_STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) CMCSIQAC.OUT_INF.DC_INF.DC_ACO_STSW += " ";
        } else if (CMCSIQAC.OUT_INF.DC_INF.DC_ACO_STSW.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_ACO_STS7_ERR);
            if (CMCSIQAC.OUT_INF.DC_INF.DC_ACO_STSW == null) CMCSIQAC.OUT_INF.DC_INF.DC_ACO_STSW = "";
            JIBS_tmp_int = CMCSIQAC.OUT_INF.DC_INF.DC_ACO_STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) CMCSIQAC.OUT_INF.DC_INF.DC_ACO_STSW += " ";
        } else if (CMCSIQAC.OUT_INF.DC_INF.DC_ACO_STSW.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_ACO_STS8_ERR);
        }
    }
    public void B300_TRN_PROC() throws IOException,SQLException,Exception {
        if (CMCS3020.BUSI_KND == '0'
            && CMCS3020.TX_TYP == ' '
            || CMCS3020.BUSI_KND == '2'
            && CMCS3020.TX_TYP == ' '
            || CMCS3020.BUSI_KND == '1'
            && CMCS3020.TX_TYP == '1') {
            R000_CALL_DDZSTRAC_DR();
            if (pgmRtn) return;
        } else if (CMCS3020.BUSI_KND == '0'
            && CMCS3020.TX_TYP == '1'
            || CMCS3020.BUSI_KND == '2'
            && CMCS3020.TX_TYP == '1'
            || CMCS3020.BUSI_KND == '1'
            && CMCS3020.TX_TYP == ' ') {
            R000_CALL_DDZSTRAC_CR();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TX_TYPE_INP_ERR);
        }
    }
    public void B400_FEE_ROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCS3020.FEE_DATA[1-1].FEE_CD);
        CEP.TRC(SCCGWA, CMCS3020.FEE_DATA[2-1].FEE_CD);
        CEP.TRC(SCCGWA, CMCS3020.FEE_DATA[3-1].FEE_CD);
        CEP.TRC(SCCGWA, CMCS3020.FEE_DATA[1-1].FEE_AC);
        CEP.TRC(SCCGWA, CMCS3020.FEE_DATA[2-1].FEE_AC);
        CEP.TRC(SCCGWA, CMCS3020.FEE_DATA[3-1].FEE_AC);
        for (WS_I = 1; WS_I <= 3 
            && (CMCS3020.FEE_DATA[WS_I-1].FEE_CD.trim().length() != 0 
            && CMCS3020.FEE_DATA[WS_I-1].FEE_CD.charAt(0) != 0X00); WS_I += 1) {
            if (CMCS3020.FEE_DATA[WS_I-1].FEE == 0) {
                IBS.init(SCCGWA, CICQACRI);
                CICQACRI.FUNC = 'A';
                CICQACRI.DATA.AGR_NO = CMCS3020.CARD_NO;
                R000_CALL_CIZQACRI();
                if (pgmRtn) return;
                B410_FEE_CAL_PROC();
                if (pgmRtn) return;
            }
            B420_FEE_CHARGE_PROC();
            if (pgmRtn) return;
        }
    }
    public void B410_FEE_CAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTCFEE);
        BPCTCFEE.INPUT_AREA.CHNL = SCCGWA.COMM_AREA.CHNL;
        BPCTCFEE.INPUT_AREA.PRD_CODE = " ";
        BPCTCFEE.INPUT_AREA.CFEE_CCY = CMCS3020.TXN_CCY;
        BPCTCFEE.INPUT_AREA.CHG_CCY = CMCS3020.TXN_CCY;
        BPCTCFEE.INPUT_AREA.CI_NO = WS_CI_NO;
        BPCTCFEE.INPUT_AREA.BR = CICQACRI.O_DATA.O_OPN_BR;
        BPCTCFEE.INPUT_AREA.FEE_CODE = CMCS3020.FEE_DATA[WS_I-1].FEE_CD;
        BPCTCFEE.INPUT_AREA.AC_NO = CMCS3020.FEE_DATA[WS_I-1].FEE_AC;
        BPCTCFEE.INPUT_AREA.ACC_CNT = 1;
        BPCTCFEE.INPUT_AREA.FEE_AMT = CMCS3020.TXN_AMT;
        S000_CALL_BPZSCFEE();
        if (pgmRtn) return;
        CMCS3020.FEE_DATA[WS_I-1].FEE = BPCTCFEE.OUTPUT_AREA.CHG_AMT;
        CEP.TRC(SCCGWA, BPCTCFEE.OUTPUT_AREA.CHG_AMT);
    }
    public void B420_FEE_CHARGE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '4';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '4';
        BPCFFTXI.TX_DATA.CARD_PSBK_NO = CMCS3020.FEE_DATA[WS_I-1].FEE_AC;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = CMCS3020.TXN_CCY;
        BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCFFCON);
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AC = CMCS3020.FEE_DATA[WS_I-1].FEE_AC;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AC_TY = '4';
        BPCFFCON.FEE_INFO.FEE_CNT = 1;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_CCY = CMCS3020.TXN_CCY;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_CCY = CMCS3020.TXN_CCY;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_CODE = CMCS3020.FEE_DATA[WS_I-1].FEE_CD;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_BAS = CMCS3020.FEE_DATA[WS_I-1].FEE;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AMT = CMCS3020.FEE_DATA[WS_I-1].FEE - CMCS3020.FEE_DATA[WS_I-1].FEEC;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].ADJ_AMT = BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AMT;
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AMT);
        S000_CALL_BPZFFCON();
        if (pgmRtn) return;
        WS_FEE_AMT = BPCFFCON.FEE_INFO.FEE_INFO1[1-1].ADJ_AMT;
        CEP.TRC(SCCGWA, BPCFFCON.FEE_INFO.FEE_INFO1[1-1].ADJ_AMT);
    }
    public void B500_INQ_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        IBS.init(SCCGWA, DDCIQBAL);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = CMCS3020.CARD_NO;
        R000_INQ_ACAC();
        if (pgmRtn) return;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_AC_REL.trim().length() > 0) {
            DDCIQBAL.DATA.AC = CICQACAC.O_DATA.O_ACR_DATA.O_REL_AC_NO;
        } else {
            DDCIQBAL.DATA.AC = CMCS3020.CARD_NO;
        }
        R000_CALL_DDZIQBAL();
        if (pgmRtn) return;
        CMCF020.AC_BAL = DDCIQBAL.DATA.CURR_BAL;
        CMCF020.AVA_BAL = DDCIQBAL.DATA.AVL_BAL;
        CMCF020.CARD_BR = DDCIQBAL.DATA.OWNER_BR;
    }
    public void B600_OUTPUT() throws IOException,SQLException,Exception {
        CMCF020.TRN_AMT = CMCS3020.TXN_AMT;
        CMCF020.CARD_NO = CMCS3020.CARD_NO;
        CMCF020.TRN_CCY = CMCS3020.TXN_CCY;
        CMCF020.SMR = CMCS3020.NATIVE;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "CM302";
        SCCFMT.DATA_PTR = CMCF020;
        SCCFMT.DATA_LEN = 561;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_CALL_CICCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
    }
    public void R000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI, true);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void R000_INQ_ACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC, true);
        CEP.TRC(SCCGWA, CICQACAC.RC.RC_CODE);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void R000_CHK_CITY_FLG_BY_AREA_CD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUBRRC);
        DCCUBRRC.INP_DATA.FUNC = '0';
        DCCUBRRC.INP_DATA.CARD_NO = CMCS3020.CARD_NO;
        S000_CALL_DCZUBRRC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CMCS3020.CARD_NO);
        CEP.TRC(SCCGWA, DCCUBRRC.OUT_DATA.O_AREA_NO);
        CEP.TRC(SCCGWA, CMCS3020.AREA_CD);
        if (DCCUBRRC.OUT_DATA.O_AREA_NO == CMCS3020.AREA_CD) {
            CMCS3020.CITY_FLG = '0';
        } else {
            CMCS3020.CITY_FLG = '1';
        }
        CEP.TRC(SCCGWA, CMCS3020.CITY_FLG);
    }
    public void R000_CHECK_CITY_FLG_FOR_DWK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = CMCS3020.CARD_NO;
        R000_CALL_CIZQACRI();
        if (pgmRtn) return;
        if (CICQACRI.O_DATA.O_CI_TYP == '2') {
            if (CMCS3020.CITY_FLG == '0'
                || CMCS3020.CITY_FLG == '1') {
            } else if (CMCS3020.CITY_FLG == '2'
                || CMCS3020.CITY_FLG == '3'
                || CMCS3020.CITY_FLG == '4'
                || CMCS3020.CITY_FLG == '5') {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_DWK_NOT_ALLOWED_FORG);
            } else {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TXN_REGION_INP_ERR);
            }
        }
    }
    public void S000_CALL_CMZSIQAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CM-S-INQ-CUS-AC", CMCSIQAC);
    }
    public void R000_CALL_DDZSTRAC_DR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSTRAC);
        if (CMCS3020.TRK2_DAT.trim().length() > 0 
                && CMCS3020.TXN_PSW.trim().length() > 0) {
            DDCSTRAC.CHK_PSW = 'B';
        } else if (CMCS3020.TRK2_DAT.trim().length() == 0 
                && CMCS3020.TXN_PSW.trim().length() > 0) {
            DDCSTRAC.CHK_PSW = 'P';
            if (CMCS3020.BUSI_KND == '0') {
                CMCS3020.TRK2_DAT = "0000000000000000000000000000000000000000";
            }
            CEP.TRC(SCCGWA, CMCS3020.TRK2_DAT);
        } else if (CMCS3020.TRK2_DAT.trim().length() > 0 
                && CMCS3020.TXN_PSW.trim().length() == 0) {
            DDCSTRAC.CHK_PSW = 'T';
        } else if (CMCS3020.TRK2_DAT.trim().length() == 0 
                && CMCS3020.TXN_PSW.trim().length() == 0) {
            DDCSTRAC.CHK_PSW = 'N';
            if (CMCS3020.BUSI_KND == '0') {
                CMCS3020.TRK2_DAT = "0000000000000000000000000000000000000000";
            }
            CEP.TRC(SCCGWA, CMCS3020.TRK2_DAT);
        } else {
        }
        if (CMCS3020.TXN_FLG == '1' 
            || CMCS3020.TXN_FLG == '2') {
            DDCSTRAC.CHK_PSW_FLG = 'N';
        }
        DDCSTRAC.FR_CARD = CMCS3020.CARD_NO;
        DDCSTRAC.FR_CCY = CMCS3020.TXN_CCY;
        DDCSTRAC.FR_AMT = CMCS3020.TXN_AMT;
        DDCSTRAC.PSWD = CMCS3020.TXN_PSW;
        DDCSTRAC.TRK2_DAT = CMCS3020.TRK2_DAT;
        DDCSTRAC.TRK3_DAT = CMCS3020.TRK3_DAT;
        DDCSTRAC.TO_AC = CMCS3020.SLT_AC;
        DDCSTRAC.RLT_AC = CMCS3020.RLT_AC;
        DDCSTRAC.RLT_AC_NAME = CMCS3020.RLT_NM;
        DDCSTRAC.TO_BV_TYPE = '3';
        DDCSTRAC.TO_CCY = CMCS3020.TXN_CCY;
        DDCSTRAC.TO_AMT = CMCS3020.TXN_AMT;
        DDCSTRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCSTRAC.DR_MMO = CMCS3020.TXN_RMK;
        DDCSTRAC.CR_MMO = CMCS3020.TXN_RMK;
        DDCSTRAC.TX_RMK = CMCS3020.NATIVE;
        DDCSTRAC.REMARKS = CMCS3020.MERCH_NM;
        if (CMCS3020.CITY_FLG == '0'
            || CMCS3020.CITY_FLG == '1') {
            DDCSTRAC.TXN_REGION = '0';
        } else if (CMCS3020.CITY_FLG == '2') {
            DDCSTRAC.TXN_REGION = '1';
        } else if (CMCS3020.CITY_FLG == '3') {
            DDCSTRAC.TXN_REGION = '2';
        } else if (CMCS3020.CITY_FLG == '4') {
            DDCSTRAC.TXN_REGION = '3';
        } else if (CMCS3020.CITY_FLG == '5'        } else {
) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TXN_REGION_INP_ERR);
        }
        DDCSTRAC.TXN_CHNL = SCCGWA.COMM_AREA.CHNL;
        if (CMCS3020.TXN_FLG == '1') {
            DDCSTRAC.TXN_TYPE = "05";
        } else {
            DDCSTRAC.TXN_TYPE = "03";
        }
        DDCSTRAC.IN_OUT_FLG = '2';
        if (CMCS3020.TX_TYP == '1') {
            DDCSTRAC.CANCEL_FLG = 'Y';
        }
        if (CMCS3020.SLT_AC == null) CMCS3020.SLT_AC = "";
        JIBS_tmp_int = CMCS3020.SLT_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) CMCS3020.SLT_AC += " ";
        DDCSTRAC.RLT_BANK = CMCS3020.SLT_AC.substring(0, 6);
        DDCSTRAC.RLT_BK_NM = WS_PQORG_CHN_NM;
        S000_CALL_DDZSTRAC();
        if (pgmRtn) return;
    }
    public void R000_CALL_DDZSTRAC_CR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSTRAC);
        DDCSTRAC.FR_AC = CMCS3020.SLT_AC;
        DDCSTRAC.FR_BV_TYPE = '3';
        DDCSTRAC.FR_CCY = CMCS3020.TXN_CCY;
        DDCSTRAC.FR_AMT = CMCS3020.TXN_AMT;
        DDCSTRAC.TO_CARD = CMCS3020.CARD_NO;
        DDCSTRAC.RLT_AC = CMCS3020.RLT_AC;
        DDCSTRAC.RLT_AC_NAME = CMCS3020.RLT_NM;
        DDCSTRAC.TO_CCY = CMCS3020.TXN_CCY;
        DDCSTRAC.TO_AMT = CMCS3020.TXN_AMT;
        DDCSTRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCSTRAC.CR_MMO = CMCS3020.TXN_RMK;
        DDCSTRAC.DR_MMO = CMCS3020.TXN_RMK;
        DDCSTRAC.TX_RMK = CMCS3020.NATIVE;
        DDCSTRAC.REMARKS = CMCS3020.MERCH_NM;
        if (CMCS3020.BUSI_KND == '0' 
            || CMCS3020.BUSI_KND == '2') {
            if (CMCS3020.CITY_FLG == '0'
                || CMCS3020.CITY_FLG == '1') {
                DDCSTRAC.TXN_REGION = '0';
            } else if (CMCS3020.CITY_FLG == '2') {
                DDCSTRAC.TXN_REGION = '1';
            } else if (CMCS3020.CITY_FLG == '3') {
                DDCSTRAC.TXN_REGION = '2';
            } else if (CMCS3020.CITY_FLG == '4') {
                DDCSTRAC.TXN_REGION = '3';
            } else if (CMCS3020.CITY_FLG == '5'            } else {
) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TXN_REGION_INP_ERR);
            }
            DDCSTRAC.TXN_CHNL = SCCGWA.COMM_AREA.CHNL;
            DDCSTRAC.TXN_TYPE = "02";
            DDCSTRAC.IN_OUT_FLG = '2';
            DDCSTRAC.SNAME_FLG = 'N';
            DDCSTRAC.DNAME_FLG = 'Y';
            if (CMCS3020.TX_TYP == '1') {
                DDCSTRAC.CANCEL_FLG = 'Y';
            }
        }
        if (CMCS3020.SLT_AC == null) CMCS3020.SLT_AC = "";
        JIBS_tmp_int = CMCS3020.SLT_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) CMCS3020.SLT_AC += " ";
        DDCSTRAC.RLT_BANK = CMCS3020.SLT_AC.substring(0, 6);
        DDCSTRAC.RLT_BK_NM = WS_PQORG_CHN_NM;
        S000_CALL_DDZSTRAC();
        if (pgmRtn) return;
    }
    public void R000_CALL_BPCUABOX() throws IOException,SQLException,Exception {
        BPCUSBOX.CCY = "156";
        BPCUSBOX.AMT = CMCS3020.TXN_AMT;
        IBS.CALLCPN(SCCGWA, "BP-U-SUB-CBOX", BPCUSBOX);
    }
    public void R000_CALL_BPCUSBOX() throws IOException,SQLException,Exception {
        BPCUSBOX.CCY = "156";
        BPCUSBOX.AMT = CMCS3020.TXN_AMT;
        IBS.CALLCPN(SCCGWA, "BP-U-ADD-CBOX", BPCUABOX);
    }
    public void S000_CALL_BPZSCFEE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-F-C-FEE", BPCTCFEE);
        CEP.TRC(SCCGWA, BPCTCFEE.RC.RC_CODE);
        if (BPCTCFEE.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTCFEE.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_BPZFFCON() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-CON-CHG-INFO", BPCFFCON);
        CEP.TRC(SCCGWA, BPCFFCON.RC.RC_CODE);
        if (BPCFFCON.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFCON.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-TX-INFO", BPCFFTXI);
        CEP.TRC(SCCGWA, BPCFFTXI.RC.RC_CODE);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void R000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL);
    }
    public void S000_CALL_DDZSTRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-TR-AC", DDCSTRAC, true);
    }
    public void S000_CALL_DCZUBRRC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-DCZUBRRC", DCCUBRRC, true);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG, true);
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
