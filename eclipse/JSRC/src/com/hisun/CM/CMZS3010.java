package com.hisun.CM;

import com.hisun.SC.*;
import com.hisun.AI.*;
import com.hisun.BP.*;
import com.hisun.DD.*;
import com.hisun.CI.*;
import com.hisun.TC.*;
import com.hisun.DC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CMZS3010 {
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
    String WS_APP = " ";
    short WS_I = 0;
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
    CICCUST CICCUST = new CICCUST();
    BPCFFCAL BPCFFCAL = new BPCFFCAL();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    AICOUMIB AICOUMIB = new AICOUMIB();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    CMCF010 CMCF010 = new CMCF010();
    CICQACRI CICQACRI = new CICQACRI();
    DDCSTRAC DDCSTRAC = new DDCSTRAC();
    TCCTOA TCCTOA = new TCCTOA();
    TCRLOGR TCRLOGR = new TCRLOGR();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPARMC BPCPARMC = new BPCPARMC();
    CICQACAC CICQACAC = new CICQACAC();
    CMCSIQAC CMCSIQAC = new CMCSIQAC();
    BPCPQORG BPCPQORG = new BPCPQORG();
    DCCUBRRC DCCUBRRC = new DCCUBRRC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CMCS3010 CMCS3010;
    public void MP(SCCGWA SCCGWA, CMCS3010 CMCS3010) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCS3010 = CMCS3010;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMZS3010 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CMCF010);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_CI_CHECK();
        if (pgmRtn) return;
        if (CMCS3010.TX_TYP == '1') {
            B160_READ_TCTLOG();
            if (pgmRtn) return;
        } else {
        }
        B300_CHK_CARD();
        if (pgmRtn) return;
        B400_TRN_PROC();
        if (pgmRtn) return;
        B500_INQ_BAL();
        if (pgmRtn) return;
        B700_OUTPUT();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCS3010.BUSI_KND);
        CEP.TRC(SCCGWA, CMCS3010.TX_TYP);
        CEP.TRC(SCCGWA, CMCS3010.CARD_NO);
        CEP.TRC(SCCGWA, CMCS3010.CARD_SEQ);
        CEP.TRC(SCCGWA, CMCS3010.TXN_CCY);
        CEP.TRC(SCCGWA, CMCS3010.TXN_AMT);
        CEP.TRC(SCCGWA, CMCS3010.TXN_RMK);
        CEP.TRC(SCCGWA, CMCS3010.TXN_PSW);
        CEP.TRC(SCCGWA, CMCS3010.TRK2_DAT);
        CEP.TRC(SCCGWA, CMCS3010.TRK3_DAT);
        CEP.TRC(SCCGWA, CMCS3010.CVN);
        CEP.TRC(SCCGWA, CMCS3010.CITY_FLG);
        CEP.TRC(SCCGWA, CMCS3010.ID_FLG);
        CEP.TRC(SCCGWA, CMCS3010.ID_TYP);
        CEP.TRC(SCCGWA, CMCS3010.ID_NO);
        CEP.TRC(SCCGWA, CMCS3010.NM_FLG);
        CEP.TRC(SCCGWA, CMCS3010.CI_NM);
        CEP.TRC(SCCGWA, CMCS3010.TEL_FLG);
        CEP.TRC(SCCGWA, CMCS3010.TEL_NO);
        CEP.TRC(SCCGWA, CMCS3010.SMR);
        CEP.TRC(SCCGWA, CMCS3010.MERCH_CD);
        CEP.TRC(SCCGWA, CMCS3010.MERCH_NM);
        CEP.TRC(SCCGWA, CMCS3010.MERCH_AC);
        if (CMCS3010.BUSI_KND == ' ') {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_KND_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3010.TXN_CCY.trim().length() == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_CCY_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3010.TXN_AMT == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_AMT_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3010.ID_FLG == 'Y' 
            && (CMCS3010.ID_TYP.trim().length() == 0 
            || CMCS3010.ID_NO.trim().length() == 0)) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_ID_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3010.NM_FLG == 'Y' 
            && CMCS3010.CI_NM.trim().length() == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_NM_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3010.TEL_FLG == 'Y' 
            && CMCS3010.TEL_NO.trim().length() == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_TEL_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3010.MERCH_AC.trim().length() == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_MERCHAC_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        CEP.TRC(SCCGWA, CMCS3010.CITY_FLG);
        if (CMCS3010.CITY_FLG == ' ') {
            R000_CHK_CITY_FLG_BY_AREA_CD();
            if (pgmRtn) return;
        } else if (CMCS3010.CITY_FLG == '0'
            || CMCS3010.CITY_FLG == '1'
            || CMCS3010.CITY_FLG == '2'
            || CMCS3010.CITY_FLG == '3'
            || CMCS3010.CITY_FLG == '4'
            || CMCS3010.CITY_FLG == '5') {
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TXN_REGION_INP_ERR);
        }
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPCPARMC);
        BPCPRMR.FUNC = ' ';
        BPCPARMC.KEY.TYP = "PARMC";
        if (BPCPARMC.KEY.CD == null) BPCPARMC.KEY.CD = "";
        JIBS_tmp_int = BPCPARMC.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCPARMC.KEY.CD += " ";
        BPCPARMC.KEY.CD = "MMO" + BPCPARMC.KEY.CD.substring(3);
        if (CMCS3010.TXN_RMK == null) CMCS3010.TXN_RMK = "";
        JIBS_tmp_int = CMCS3010.TXN_RMK.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) CMCS3010.TXN_RMK += " ";
        if (BPCPARMC.KEY.CD == null) BPCPARMC.KEY.CD = "";
        JIBS_tmp_int = BPCPARMC.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPCPARMC.KEY.CD += " ";
        BPCPARMC.KEY.CD = BPCPARMC.KEY.CD.substring(0, 6 - 1) + CMCS3010.TXN_RMK.substring(0, 4) + BPCPARMC.KEY.CD.substring(6 + 9 - 1);
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
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'A';
        CICCUST.DATA.AGR_NO = CMCS3010.CARD_NO;
        R000_CALL_CICCUST();
        if (pgmRtn) return;
        CMCF010.CI_NM = CICCUST.O_DATA.O_CI_NM;
        CMCF010.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
        CMCF010.ID_NO = CICCUST.O_DATA.O_ID_NO;
        CMCF010.TEL_NO = CICCUST.O_DATA.O_TEL_NO;
        if (CMCS3010.TEL_FLG == 'Y' 
            && !CMCS3010.TEL_NO.equalsIgnoreCase(CMCF010.TEL_NO)) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_TELNO_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3010.NM_FLG == 'Y' 
            && !CMCS3010.CI_NM.equalsIgnoreCase(CMCF010.CI_NM)) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_CINM_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3010.ID_FLG == 'Y' 
            && !CMCS3010.ID_TYP.equalsIgnoreCase(CMCF010.ID_TYP)) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_IDTYP_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (CMCS3010.ID_FLG == 'Y' 
            && !CMCS3010.ID_NO.equalsIgnoreCase(CMCF010.ID_NO)) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_IDNO_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = CMCS3010.MERCH_AC;
        R000_CALL_CIZQACRI();
        if (pgmRtn) return;
        WS_APP = CICQACRI.O_DATA.O_FRM_APP;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        if (CMCS3010.MERCH_AC == null) CMCS3010.MERCH_AC = "";
        JIBS_tmp_int = CMCS3010.MERCH_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) CMCS3010.MERCH_AC += " ";
        if (CMCS3010.MERCH_AC.substring(0, 6).trim().length() == 0) BPCPQORG.BR = 0;
        else BPCPQORG.BR = Integer.parseInt(CMCS3010.MERCH_AC.substring(0, 6));
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_PQORG_CHN_NM = BPCPQORG.CHN_NM;
        CEP.TRC(SCCGWA, BPCPQORG.BR);
        CEP.TRC(SCCGWA, BPCPQORG.CHN_ADDR);
        CEP.TRC(SCCGWA, BPCPQORG.LINK_TEL);
        CEP.TRC(SCCGWA, BPCPQORG.CHN_NM);
    }
    public void B160_READ_TCTLOG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TCRLOGR);
        CEP.TRC(SCCGWA, CMCS3010.REQ_JRN);
        TCRLOGR.KEY.REQ_SYS_JRN = CMCS3010.REQ_JRN;
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
            if (TCRLOGR.TR_STS != '1' 
                || TCRLOGR.AC_DATE != SCCGWA.COMM_AREA.AC_DATE) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_JRN_ERR);
            }
    }
    public void B300_CHK_CARD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMCSIQAC);
        CMCSIQAC.I_FUNC = '3';
        CMCSIQAC.I_CUS_AC = CMCS3010.CARD_NO;
        CMCSIQAC.I_CCY = CMCS3010.TXN_CCY;
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
            if (CMCSIQAC.OUT_INF.DC_INF.DC_CD_STSW == null) CMCSIQAC.OUT_INF.DC_INF.DC_CD_STSW = "";
            JIBS_tmp_int = CMCSIQAC.OUT_INF.DC_INF.DC_CD_STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) CMCSIQAC.OUT_INF.DC_INF.DC_CD_STSW += " ";
        } else if (CMCS3010.TXN_PSW.trim().length() > 0 
                && CMCSIQAC.OUT_INF.DC_INF.DC_CD_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_STS9_ERR);
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
    public void B400_TRN_PROC() throws IOException,SQLException,Exception {
        if (((CMCS3010.BUSI_KND == '0' 
                && CMCS3010.TX_TYP == ' ') 
                || (CMCS3010.BUSI_KND == '2' 
                && CMCS3010.TX_TYP == ' '))) {
            R000_CALL_DDZSTRAC_DR();
            if (pgmRtn) return;
        } else if (((CMCS3010.BUSI_KND == '0' 
                && CMCS3010.TX_TYP == '1') 
                || (CMCS3010.BUSI_KND == '2' 
                && CMCS3010.TX_TYP == '1'))
            || CMCS3010.BUSI_KND == '1') {
            R000_CALL_DDZSTRAC_CR();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_ERROR_TR_TYPE_ERROR);
        }
    }
    public void B400_FEE_PROC() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= 3; WS_I += 1) {
            IBS.init(SCCGWA, BPCFFTXI);
            BPCFFTXI.TX_DATA.AUH_FLG = '0';
            BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '4';
            BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = CMCS3010.FEE_DATA[WS_I-1].FEE_AC;
            BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = "156";
            BPCFFTXI.TX_DATA.SVR_CD = CMCS3010.FEE_DATA[WS_I-1].FEE_CD;
            BPCFFTXI.TX_DATA.CARD_PSBK_NO = CMCS3010.FEE_DATA[WS_I-1].FEE_AC;
            BPCFFTXI.TX_DATA.CCY_TYPE = '1';
            BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
            S000_CALL_BPZFFTXI();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPCFFCON);
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_I-1].CHG_AC = CMCS3010.FEE_DATA[WS_I-1].FEE_AC;
            BPCFFCON.FEE_INFO.FEE_CNT = WS_I;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_I-1].FEE_CODE = CMCS3010.FEE_DATA[WS_I-1].FEE_CD;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_I-1].CHG_BAS = CMCS3010.FEE_DATA[WS_I-1].FEE;
            BPCFFCON.FEE_INFO.FEE_INFO1[WS_I-1].CHG_AMT = CMCS3010.FEE_DATA[WS_I-1].FEE - CMCS3010.FEE_DATA[WS_I-1].FEEC;
            S000_CALL_BPZFFCON();
            if (pgmRtn) return;
        }
    }
    public void B500_INQ_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        IBS.init(SCCGWA, DDCIQBAL);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = CMCS3010.CARD_NO;
        R000_INQ_ACAC();
        if (pgmRtn) return;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_AC_REL.trim().length() > 0) {
            DDCIQBAL.DATA.AC = CICQACAC.O_DATA.O_ACR_DATA.O_REL_AC_NO;
        } else {
            DDCIQBAL.DATA.AC = CMCS3010.CARD_NO;
        }
        R000_CALL_DDZIQBAL();
        if (pgmRtn) return;
        CMCF010.AC_BAL = DDCIQBAL.DATA.CURR_BAL;
        CMCF010.AVA_BAL = DDCIQBAL.DATA.AVL_BAL;
        CMCF010.CARD_BR = DDCIQBAL.DATA.OWNER_BR;
    }
    public void B700_OUTPUT() throws IOException,SQLException,Exception {
        CMCF010.TRN_AMT = CMCS3010.TXN_AMT;
        CMCF010.CARD_NO = CMCS3010.CARD_NO;
        CMCF010.TRN_CCY = CMCS3010.TXN_CCY;
        CMCF010.SMR = CMCS3010.SMR;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "CM301";
        SCCFMT.DATA_PTR = CMCF010;
        SCCFMT.DATA_LEN = 579;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_CHK_CITY_FLG_BY_AREA_CD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUBRRC);
        DCCUBRRC.INP_DATA.FUNC = '2';
        DCCUBRRC.INP_DATA.CARD_NO = CMCS3010.CARD_NO;
        DCCUBRRC.INP_DATA.BR_NO = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_DCZUBRRC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CMCS3010.CARD_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, DCCUBRRC.OUT_DATA.O_CITY_FLG);
        CMCS3010.CITY_FLG = DCCUBRRC.OUT_DATA.O_CITY_FLG;
        CEP.TRC(SCCGWA, CMCS3010.CITY_FLG);
    }
    public void S000_CALL_DCZUBRRC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-DCZUBRRC", DCCUBRRC, true);
    }
    public void R000_CALL_CICCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
    }
    public void R000_INQ_ACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC, true);
        CEP.TRC(SCCGWA, CICQACAC.RC.RC_CODE);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void S000_CALL_CMZSIQAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CM-S-INQ-CUS-AC", CMCSIQAC);
    }
    public void R000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void R000_CALL_DDZSTRAC_DR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSTRAC);
        if (CMCS3010.TRK2_DAT.trim().length() > 0 
                && CMCS3010.TXN_PSW.trim().length() > 0) {
            DDCSTRAC.CHK_PSW = 'B';
        } else if (CMCS3010.TRK2_DAT.trim().length() == 0 
                && CMCS3010.TXN_PSW.trim().length() > 0) {
            DDCSTRAC.CHK_PSW = 'P';
            if (CMCS3010.BUSI_KND == '0') {
                CMCS3010.TRK2_DAT = "0000000000000000000000000000000000000000";
            }
            CEP.TRC(SCCGWA, CMCS3010.TRK2_DAT);
        } else if (CMCS3010.TRK2_DAT.trim().length() > 0 
                && CMCS3010.TXN_PSW.trim().length() == 0) {
            DDCSTRAC.CHK_PSW = 'T';
        } else if (CMCS3010.TRK2_DAT.trim().length() == 0 
                && CMCS3010.TXN_PSW.trim().length() == 0) {
            DDCSTRAC.CHK_PSW = 'N';
            if (CMCS3010.BUSI_KND == '0') {
                CMCS3010.TRK2_DAT = "0000000000000000000000000000000000000000";
            }
            CEP.TRC(SCCGWA, CMCS3010.TRK2_DAT);
        } else {
        }
        DDCSTRAC.FR_CARD = CMCS3010.CARD_NO;
        DDCSTRAC.FR_CCY = CMCS3010.TXN_CCY;
        DDCSTRAC.FR_AMT = CMCS3010.TXN_AMT;
        DDCSTRAC.PSWD = CMCS3010.TXN_PSW;
        DDCSTRAC.TRK2_DAT = CMCS3010.TRK2_DAT;
        DDCSTRAC.TRK3_DAT = CMCS3010.TRK3_DAT;
        if (WS_APP.equalsIgnoreCase("DC")) {
            DDCSTRAC.TO_CARD = CMCS3010.MERCH_AC;
        } else {
            DDCSTRAC.TO_AC = CMCS3010.MERCH_AC;
        }
        if (WS_APP.equalsIgnoreCase("DC")) {
            DDCSTRAC.TO_BV_TYPE = '1';
        } else if (WS_APP.equalsIgnoreCase("DD")) {
            DDCSTRAC.TO_BV_TYPE = '2';
        } else if (WS_APP.equalsIgnoreCase("AI")) {
            DDCSTRAC.TO_BV_TYPE = '3';
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_FRM_APP_ERR);
        }
        DDCSTRAC.RLT_AC = CMCS3010.MERCH_CD;
        DDCSTRAC.RLT_AC_NAME = CMCS3010.MERCH_NM;
        DDCSTRAC.TO_CCY = CMCS3010.TXN_CCY;
        DDCSTRAC.TO_AMT = CMCS3010.TXN_AMT;
        DDCSTRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCSTRAC.DR_MMO = CMCS3010.TXN_RMK;
        DDCSTRAC.CR_MMO = CMCS3010.TXN_RMK;
        if (CMCS3010.CITY_FLG == '0'
            || CMCS3010.CITY_FLG == '1') {
            DDCSTRAC.TXN_REGION = '0';
        } else if (CMCS3010.CITY_FLG == '2') {
            DDCSTRAC.TXN_REGION = '1';
        } else if (CMCS3010.CITY_FLG == '3') {
            DDCSTRAC.TXN_REGION = '2';
        } else if (CMCS3010.CITY_FLG == '4') {
            DDCSTRAC.TXN_REGION = '3';
        } else if (CMCS3010.CITY_FLG == '5'        } else {
) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TXN_REGION_INP_ERR);
        }
        DDCSTRAC.TXN_CHNL = SCCGWA.COMM_AREA.CHNL;
        if (CMCS3010.TXN_FLG == '1') {
            DDCSTRAC.TXN_TYPE = "05";
        } else {
            DDCSTRAC.TXN_TYPE = "03";
        }
        DDCSTRAC.IN_OUT_FLG = '1';
        if (CMCS3010.TX_TYP == '1') {
            DDCSTRAC.CANCEL_FLG = 'Y';
        }
        if (CMCS3010.MERCH_AC == null) CMCS3010.MERCH_AC = "";
        JIBS_tmp_int = CMCS3010.MERCH_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) CMCS3010.MERCH_AC += " ";
        DDCSTRAC.RLT_BANK = CMCS3010.MERCH_AC.substring(0, 6);
        DDCSTRAC.RLT_BK_NM = WS_PQORG_CHN_NM;
        S000_CALL_DDZSTRAC();
        if (pgmRtn) return;
    }
    public void R000_CALL_DDZSTRAC_CR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSTRAC);
        if (WS_APP.equalsIgnoreCase("DC")) {
            DDCSTRAC.FR_CARD = CMCS3010.MERCH_AC;
        } else {
            DDCSTRAC.FR_AC = CMCS3010.MERCH_AC;
        }
        if (WS_APP.equalsIgnoreCase("DC")) {
            DDCSTRAC.FR_BV_TYPE = '1';
        } else if (WS_APP.equalsIgnoreCase("DD")) {
            DDCSTRAC.FR_BV_TYPE = '2';
        } else if (WS_APP.equalsIgnoreCase("AI")) {
            DDCSTRAC.FR_BV_TYPE = '3';
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_FRM_APP_ERR);
        }
        DDCSTRAC.FR_CCY = CMCS3010.TXN_CCY;
        DDCSTRAC.FR_AMT = CMCS3010.TXN_AMT;
        DDCSTRAC.TO_AC = CMCS3010.CARD_NO;
        DDCSTRAC.TO_CCY = CMCS3010.TXN_CCY;
        DDCSTRAC.TO_AMT = CMCS3010.TXN_AMT;
        DDCSTRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCSTRAC.TO_AC_CNAME = CMCF010.CI_NM;
        DDCSTRAC.CR_MMO = CMCS3010.TXN_RMK;
        DDCSTRAC.DR_MMO = CMCS3010.TXN_RMK;
        if (CMCS3010.BUSI_KND == '0' 
            || CMCS3010.BUSI_KND == '2') {
            if (CMCS3010.CITY_FLG == '0'
                || CMCS3010.CITY_FLG == '1') {
                DDCSTRAC.TXN_REGION = '0';
            } else if (CMCS3010.CITY_FLG == '2') {
                DDCSTRAC.TXN_REGION = '1';
            } else if (CMCS3010.CITY_FLG == '3') {
                DDCSTRAC.TXN_REGION = '2';
            } else if (CMCS3010.CITY_FLG == '4') {
                DDCSTRAC.TXN_REGION = '3';
            } else if (CMCS3010.CITY_FLG == '5'            } else {
) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TXN_REGION_INP_ERR);
            }
            DDCSTRAC.TXN_CHNL = SCCGWA.COMM_AREA.CHNL;
            DDCSTRAC.TXN_TYPE = "02";
            DDCSTRAC.IN_OUT_FLG = '1';
            DDCSTRAC.SNAME_FLG = 'N';
            DDCSTRAC.DNAME_FLG = 'Y';
            if (CMCS3010.TX_TYP == '1') {
                DDCSTRAC.CANCEL_FLG = 'Y';
            }
        }
        if (CMCS3010.MERCH_AC == null) CMCS3010.MERCH_AC = "";
        JIBS_tmp_int = CMCS3010.MERCH_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) CMCS3010.MERCH_AC += " ";
        DDCSTRAC.RLT_BANK = CMCS3010.MERCH_AC.substring(0, 6);
        DDCSTRAC.RLT_BK_NM = WS_PQORG_CHN_NM;
        S000_CALL_DDZSTRAC();
        if (pgmRtn) return;
    }
    public void R000_CALL_BPCFFCAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-CAL-STD-FEE", BPCFFCAL);
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-TX-INFO", BPCFFTXI);
        CEP.TRC(SCCGWA, BPCFFTXI.RC.RC_CODE);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
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
    public void R000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL);
    }
    public void S000_CALL_DDZSTRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-TR-AC", DDCSTRAC, true);
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
