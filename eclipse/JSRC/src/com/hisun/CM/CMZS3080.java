package com.hisun.CM;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.CI.*;
import com.hisun.DD.*;
import com.hisun.TD.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CMZS3080 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "CM380";
    String WK_PGM_DDZSUHLD = "DD-SVR-DDZSUHLD";
    String K_SYS_ERR = "SC6001";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WK_YL = "YL";
    String WK_SZ = "SZ";
    String WS_ERR_MSG = " ";
    short WS_RC = 0;
    short WS_RC_DISP = 0;
    String WS_HLD_NOTEMP = " ";
    String WS_HLD_DATE = " ";
    String WS_CUS_AC = " ";
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DCCURHLD DCCURHLD = new DCCURHLD();
    SCCBINF SCCBINF = new SCCBINF();
    CICQACAC CICQACAC = new CICQACAC();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    TDCACE TDCACE = new TDCACE();
    DCCUHLD DCCUHLD = new DCCUHLD();
    DCCPFTCK DCCPFTCK = new DCCPFTCK();
    DCCPCDCK DCCPCDCK = new DCCPCDCK();
    DDCSUHLD DDCSUHLD = new DDCSUHLD();
    CMCSIQAC CMCSIQAC = new CMCSIQAC();
    CMCO3080 CMCO3080 = new CMCO3080();
    SCCCLDT SCCCLDT = new SCCCLDT();
    CICQACRL CICQACRL = new CICQACRL();
    CICREGLC CICREGLC = new CICREGLC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CMCS3080 CMCS3080;
    public void MP(SCCGWA SCCGWA, CMCS3080 CMCS3080) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCS3080 = CMCS3080;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMZS3080 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B200_CHK_CARD_STS();
            if (pgmRtn) return;
        }
        if (CMCS3080.TX_TYPE == '1'
            || CMCS3080.TX_TYPE == '3') {
            B300_HOLD_PROC();
            if (pgmRtn) return;
        } else if (CMCS3080.TX_TYPE == '2') {
            B400_RE_HOLD_PROC();
            if (pgmRtn) return;
        }
        if (CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP.equalsIgnoreCase("DC")) {
            B500_CHK_CARD();
            if (pgmRtn) return;
            B510_CHK_CARD_LIMIT();
            if (pgmRtn) return;
        }
        B600_OUTPUT_DATA_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCS3080.CHN_TYP);
        CEP.TRC(SCCGWA, CMCS3080.TX_TYPE);
        CEP.TRC(SCCGWA, CMCS3080.HLD_NO);
        CEP.TRC(SCCGWA, CMCS3080.HLD_CLS);
        CEP.TRC(SCCGWA, CMCS3080.CUS_AC);
        CEP.TRC(SCCGWA, CMCS3080.CCY);
        CEP.TRC(SCCGWA, CMCS3080.CCY_TYP);
        CEP.TRC(SCCGWA, CMCS3080.CHK_FLG);
        CEP.TRC(SCCGWA, CMCS3080.TRK_DAT2);
        CEP.TRC(SCCGWA, CMCS3080.TRK_DAT3);
        CEP.TRC(SCCGWA, CMCS3080.PSWD);
        CEP.TRC(SCCGWA, CMCS3080.HLD_AMT);
        CEP.TRC(SCCGWA, CMCS3080.RMK_CD);
        CEP.TRC(SCCGWA, CMCS3080.RSN);
        CEP.TRC(SCCGWA, CMCS3080.EFF_DT);
        CEP.TRC(SCCGWA, CMCS3080.EXP_DT);
        CEP.TRC(SCCGWA, CMCS3080.RMK);
        CEP.TRC(SCCGWA, CMCS3080.TXN_FLG);
        CEP.TRC(SCCGWA, CMCS3080.TXN_REG);
        CEP.TRC(SCCGWA, CMCS3080.CHK_FLG);
        CEP.TRC(SCCGWA, CMCS3080.TRK_DAT2);
        CEP.TRC(SCCGWA, CMCS3080.TRK_DAT3);
        if (CMCS3080.TX_TYPE != '1' 
            && CMCS3080.TX_TYPE != '2' 
            && CMCS3080.TX_TYPE != '3') {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TX_TYPE_INP_ERR);
        }
        if (CMCS3080.CCY.equalsIgnoreCase("156")) {
            CMCS3080.CCY_TYP = '1';
        }
        CEP.TRC(SCCGWA, CMCS3080.CCY);
        CEP.TRC(SCCGWA, CMCS3080.CCY_TYP);
        if ((!CMCS3080.CCY.equalsIgnoreCase("156")) 
            && (CMCS3080.CCY_TYP == ' ' 
            || CMCS3080.CCY_TYP == 0X00)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CCY_TYP_ERR);
        }
        CEP.TRC(SCCGWA, CMCS3080.CCY);
        CEP.TRC(SCCGWA, CMCS3080.CCY_TYP);
        if (CMCS3080.TX_TYPE == '1' 
            || CMCS3080.TX_TYPE == '3') {
            if (CMCS3080.CUS_AC.trim().length() == 0) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CUS_AC_INP_ERR);
            }
            if (CMCS3080.HLD_AMT == 0) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_HLD_AMT_INP_ERR);
            }
        }
        if (CMCS3080.TX_TYPE == '2') {
            if (CMCS3080.HLD_NO.trim().length() == 0) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_HLD_NO_INP_ERR);
            }
        }
        if (CMCS3080.TXN_FLG == null) CMCS3080.TXN_FLG = "";
        JIBS_tmp_int = CMCS3080.TXN_FLG.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) CMCS3080.TXN_FLG += " ";
        if (CMCS3080.TXN_FLG.substring(0, 1).equalsIgnoreCase("1")) {
            CMCS3080.CHK_FLG = '4';
        }
        if (CMCS3080.TXN_REG == ' ') {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TXN_REGION_INP_ERR);
        }
        if (CMCS3080.EFF_DT == 0 
            && CMCS3080.EXP_DT == 0) {
            CMCS3080.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
            SCCCLDT.DAYS = 30;
            S000_CALL_SCSSCLDT();
            if (pgmRtn) return;
            CMCS3080.EXP_DT = SCCCLDT.DATE2;
        }
        CEP.TRC(SCCGWA, CMCS3080.EFF_DT);
        CEP.TRC(SCCGWA, CMCS3080.EXP_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (CMCS3080.TX_TYPE == '1' 
            || CMCS3080.TX_TYPE == '3') {
            CMCS3080.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        CEP.TRC(SCCGWA, CMCS3080.EFF_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REQ_SYSTEM);
        CEP.TRC(SCCGWA, CMCS3080.HLD_DT);
        if (((SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("070500")) 
            || (SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("070501")) 
            || (SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("070502")) 
            || (SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("070504"))) 
            && (CMCS3080.HLD_DT == 0)) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_HLD_DATE_ERR);
        }
        WS_HLD_DATE = "" + CMCS3080.HLD_DT;
        JIBS_tmp_int = WS_HLD_DATE.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) WS_HLD_DATE = "0" + WS_HLD_DATE;
        if (SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("070504")) {
            WS_HLD_NOTEMP = WK_YL + WS_HLD_DATE + CMCS3080.HLD_NO;
        } else if (SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("070500")
            || SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("070501")
            || SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("070502")) {
            WS_HLD_NOTEMP = WK_SZ + WS_HLD_DATE + CMCS3080.HLD_NO;
        } else {
            WS_HLD_NOTEMP = CMCS3080.HLD_NO;
        }
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        CICQACRL.DATA.AC_NO = CMCS3080.CUS_AC;
        CICQACRL.DATA.AC_REL = "04";
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        WS_CUS_AC = CICQACRL.O_DATA.O_REL_AC_NO;
    }
    public void B500_CHK_CARD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPFTCK);
        DCCPFTCK.VAL.SNAME_TRF_FLG = 'N';
        DCCPFTCK.VAL.DNAME_TRF_FLG = 'Y';
        DCCPFTCK.FUNCTION_CODE = 'L';
        DCCPFTCK.FUNCTION_CODE = 'B';
        DCCPFTCK.VAL.CARD_NO = CMCS3080.CUS_AC;
        DCCPFTCK.VAL.TXN_CCY = CMCS3080.CCY;
        DCCPFTCK.VAL.TXN_AMT = CMCS3080.HLD_AMT;
        if (CMCS3080.TRK_DAT2.trim().length() == 0 
            && CMCS3080.TX_TYPE == '1') {
            CMCS3080.TRK_DAT2 = "0000000000000000000000000000000000000000";
        }
        CEP.TRC(SCCGWA, CMCS3080.TRK_DAT2);
        CEP.TRC(SCCGWA, CMCS3080.TX_TYPE);
        DCCPFTCK.TRK2_DAT = CMCS3080.TRK_DAT2;
        DCCPFTCK.TRK3_DAT = CMCS3080.TRK_DAT3;
        DCCPFTCK.TXN_MMO = CMCS3080.RMK_CD;
        DCCPFTCK.BANK_FLG = '1';
        if (CMCS3080.TXN_REG == '0'
            || CMCS3080.TXN_REG == '1') {
            DCCPFTCK.VAL.REGN_TYP = '0';
        } else if (CMCS3080.TXN_REG == '2') {
            DCCPFTCK.VAL.REGN_TYP = '1';
        } else if (CMCS3080.TXN_REG == '3') {
            DCCPFTCK.VAL.REGN_TYP = '2';
        } else if (CMCS3080.TXN_REG == '4') {
            DCCPFTCK.VAL.REGN_TYP = '3';
        } else if (CMCS3080.TXN_REG == '5'        } else {
) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TXN_REGION_INP_ERR);
        }
        if (CMCS3080.TXN_FLG == null) CMCS3080.TXN_FLG = "";
        JIBS_tmp_int = CMCS3080.TXN_FLG.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) CMCS3080.TXN_FLG += " ";
        if (CMCS3080.TXN_FLG.substring(0, 1).equalsIgnoreCase("1")) {
            DCCPFTCK.VAL.TXN_TYPE = "05";
        } else {
            DCCPFTCK.VAL.TXN_TYPE = "03";
        }
        if (CMCS3080.TXN_FLG == null) CMCS3080.TXN_FLG = "";
        JIBS_tmp_int = CMCS3080.TXN_FLG.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) CMCS3080.TXN_FLG += " ";
        if (CMCS3080.TXN_FLG.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            DCCPFTCK.TXN_CHNL = "10208";
        }
        if (CMCS3080.TX_TYPE == '1'
            || CMCS3080.TX_TYPE == '3') {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                DCCPFTCK.CANCEL_FLG = 'Y';
                DCCPFTCK.CANCEL_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            } else {
                DCCPFTCK.CANCEL_FLG = 'N';
            }
        } else if (CMCS3080.TX_TYPE == '2') {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                DCCPFTCK.CANCEL_FLG = 'Y';
                DCCPFTCK.CANCEL_DATE = DDCSUHLD.CRT_DATE;
            } else {
                DCCPFTCK.CANCEL_FLG = 'N';
            }
        } else {
        }
        CEP.TRC(SCCGWA, DCCPFTCK.VAL.TXN_TYPE);
        R000_CALL_DCZPFTCK();
        if (pgmRtn) return;
    }
    public void B510_CHK_CARD_LIMIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICREGLC);
        CICREGLC.FUNC = '0';
        CEP.TRC(SCCGWA, CICREGLC.FUNC);
        CICREGLC.CUS_AC = CMCS3080.CUS_AC;
        CEP.TRC(SCCGWA, CICREGLC.CUS_AC);
        CICREGLC.CARD_LNK_TYP = CMCSIQAC.OUT_INF.DC_INF.DC_LNK_TYP;
        CEP.TRC(SCCGWA, CICREGLC.CARD_LNK_TYP);
        CICREGLC.ACO_AC = CMCSIQAC.OUT_INF.DC_INF.DC_ACO_NO;
        CICREGLC.DC_FLG = 'D';
        CEP.TRC(SCCGWA, CICREGLC.DC_FLG);
        CICREGLC.CCY = CMCSIQAC.OUT_INF.DC_INF.DC_CCY;
        CICREGLC.CCY_TYP = CMCSIQAC.OUT_INF.DC_INF.DC_CCY_TYP;
        CICREGLC.AMT = CMCS3080.HLD_AMT;
        CEP.TRC(SCCGWA, CICREGLC.CCY);
        CEP.TRC(SCCGWA, CICREGLC.CCY_TYP);
        CEP.TRC(SCCGWA, CICREGLC.AMT);
        CICREGLC.BAL = CMCSIQAC.OUT_INF.DC_INF.DC_CUR_BAL;
        CICREGLC.TRS_FLG = '1';
        CEP.TRC(SCCGWA, CICREGLC.TRS_FLG);
        CEP.TRC(SCCGWA, CMCS3080.TXN_FLG);
        if (CMCS3080.TXN_FLG == null) CMCS3080.TXN_FLG = "";
        JIBS_tmp_int = CMCS3080.TXN_FLG.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) CMCS3080.TXN_FLG += " ";
        if (CMCS3080.TXN_FLG.substring(0, 1).equalsIgnoreCase("1")) {
            CICREGLC.TX_TYP = "05";
        }
        CEP.TRC(SCCGWA, CICREGLC.TX_TYP);
        CICREGLC.OPP_CUS_AC = " ";
        CICREGLC.RLT_CUS_AC = " ";
        CICREGLC.CI_NO = CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_NO;
        CICREGLC.CI_TYP = CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_TYP;
        CEP.TRC(SCCGWA, CICREGLC.CI_NO);
        CEP.TRC(SCCGWA, CICREGLC.CI_TYP);
        CICREGLC.OPP_CI_NO = " ";
        CICREGLC.PROD_CD = CMCSIQAC.OUT_INF.DC_INF.DC_PROD_CD;
        CEP.TRC(SCCGWA, CICREGLC.PROD_CD);
        CICREGLC.CITY_FLG = CMCS3080.TXN_REG;
        CEP.TRC(SCCGWA, CICREGLC.CITY_FLG);
        if (CICREGLC.CITY_FLG == ' ') {
            CICREGLC.CITY_FLG = '0';
        }
        CEP.TRC(SCCGWA, CICREGLC.TX_CHNL_NO);
        CICREGLC.AC_TYP = CMCSIQAC.OUT_INF.DC_INF.DC_AC_TYP.charAt(0);
        CEP.TRC(SCCGWA, CICREGLC.AC_TYP);
        CICREGLC.AC_BR = CMCSIQAC.OUT_INF.DC_INF.DC_ACO_OWN_BR;
        if (CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_STSW == null) CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_STSW = "";
        JIBS_tmp_int = CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_STSW += " ";
        CICREGLC.HS_FLG = CMCSIQAC.OUT_INF.BAS_INF.BAS_CI_STSW.substring(27 - 1, 27 + 1 - 1).charAt(0);
        CEP.TRC(SCCGWA, CICREGLC.HS_FLG);
        CICREGLC.MMO = CMCS3080.RMK_CD;
        CEP.TRC(SCCGWA, CICREGLC.MMO);
        if (CMCS3080.TX_TYPE == '1'
            || CMCS3080.TX_TYPE == '3') {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                CICREGLC.EC_FLG = 'Y';
                CICREGLC.CAN_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            } else {
                CICREGLC.EC_FLG = 'N';
            }
        } else if (CMCS3080.TX_TYPE == '2') {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                CICREGLC.EC_FLG = 'Y';
                CICREGLC.CAN_DATE = DDCSUHLD.CRT_DATE;
            } else {
                CICREGLC.EC_FLG = 'N';
            }
        } else {
        }
        CEP.TRC(SCCGWA, CICREGLC.EC_FLG);
        CEP.TRC(SCCGWA, CICREGLC.CAN_DATE);
        CICREGLC.HLD_NO = CMCS3080.HLD_NO;
        CEP.TRC(SCCGWA, CMCS3080.HLD_NO);
        CEP.TRC(SCCGWA, CICREGLC.HLD_NO);
        S000_CALL_CIZREGLC();
        if (pgmRtn) return;
    }
    public void B200_CHK_CARD_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMCSIQAC);
        CMCSIQAC.I_FUNC = '3';
        CMCSIQAC.I_CUS_AC = CMCS3080.CUS_AC;
        CMCSIQAC.I_CCY = CMCS3080.CCY;
        CMCSIQAC.I_CCY_TYP = CMCS3080.CCY_TYP;
        S000_CALL_CMZSIQAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP);
        if (CMCSIQAC.OUT_INF.BAS_INF.BAS_FRM_APP.equalsIgnoreCase("DC")) {
            B200_01_CHK_CARD_STS();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B200_01_CHK_CARD_STS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCS3080.CUS_AC);
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = CMCS3080.CUS_AC;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUCINF.CARD_STS);
        CEP.TRC(SCCGWA, DCCUCINF.CARD_STSW);
        if (DCCUCINF.CARD_STS == 'C') {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_STS_C_ERR);
        }
        if (DCCUCINF.CARD_STS != 'N') {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_STS_ERR);
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_STS1_ERR);
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_STS2_ERR);
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(14 - 1, 14 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_STS14_ERR);
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_STS8_ERR);
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (CMCS3080.PSWD.trim().length() > 0 
            && DCCUCINF.CARD_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_STS9_ERR);
        }
        CEP.TRC(SCCGWA, DCCUCINF.CARD_NO);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'C';
        CICQACAC.DATA.AGR_NO = DCCUCINF.CARD_NO;
        CICQACAC.DATA.CCY_ACAC = CMCS3080.CCY;
        CICQACAC.DATA.CR_FLG = CMCS3080.CCY_TYP;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_STS);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_CTL);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC);
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDCIQBAL);
            DDCIQBAL.DATA.AC = DCCUCINF.CARD_NO;
            DDCIQBAL.DATA.CCY = CMCS3080.CCY;
            DDCIQBAL.DATA.CCY_TYPE = CMCS3080.CCY_TYP;
            S000_CALL_DDZIQBAL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY_STS);
            CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY_STS_WORD);
            if (DDCIQBAL.DATA.CCY_STS != 'N') {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_ACAC_STS_ERR);
            }
            if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
            JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
            if (DDCIQBAL.DATA.CCY_STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_ACAC_STS8_ERR);
            }
            if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
            JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
            if (DDCIQBAL.DATA.CCY_STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_ACAC_STS9_ERR);
            }
            if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
            JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
            if (DDCIQBAL.DATA.CCY_STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_ACAC_STS16_ERR);
            }
            if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
            JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
            if (DDCIQBAL.DATA.CCY_STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_CARD_ACAC_STS17_ERR);
            }
        }
    }
    public void B300_HOLD_PROC() throws IOException,SQLException,Exception {
        if (CMCS3080.CHK_FLG == '1') {
            IBS.init(SCCGWA, DCCPCDCK);
            DCCPCDCK.FUNC_CODE = 'P';
            DCCPCDCK.CARD_NO = CMCS3080.CUS_AC;
            DCCPCDCK.CARD_PSW = CMCS3080.PSWD;
            S000_CALL_DCZPCDCK();
            if (pgmRtn) return;
        }
        if (CMCS3080.CHK_FLG == '2') {
            IBS.init(SCCGWA, DCCPCDCK);
            DCCPCDCK.FUNC_CODE = 'T';
            DCCPCDCK.CARD_NO = CMCS3080.CUS_AC;
            DCCPCDCK.TRK2_DAT = CMCS3080.TRK_DAT2;
            DCCPCDCK.TRK3_DAT = CMCS3080.TRK_DAT3;
            S000_CALL_DCZPCDCK();
            if (pgmRtn) return;
        }
        if (CMCS3080.CHK_FLG == '3') {
            IBS.init(SCCGWA, DCCPCDCK);
            DCCPCDCK.FUNC_CODE = 'B';
            DCCPCDCK.CARD_NO = CMCS3080.CUS_AC;
            DCCPCDCK.CARD_PSW = CMCS3080.PSWD;
            DCCPCDCK.TRK2_DAT = CMCS3080.TRK_DAT2;
            DCCPCDCK.TRK3_DAT = CMCS3080.TRK_DAT3;
            S000_CALL_DCZPCDCK();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCCUHLD);
        DCCUHLD.DATA.HLD_NO = WS_HLD_NOTEMP;
        if (WS_CUS_AC.trim().length() > 0) {
            DCCUHLD.DATA.AC = WS_CUS_AC;
        } else {
            DCCUHLD.DATA.AC = CMCS3080.CUS_AC;
        }
        DCCUHLD.DATA.HLD_TYP = '2';
        DCCUHLD.DATA.SPR_TYP = '2';
        DCCUHLD.DATA.CCY = CMCS3080.CCY;
        DCCUHLD.DATA.CCY_TYP = CMCS3080.CCY_TYP;
        DCCUHLD.DATA.AMT = CMCS3080.HLD_AMT;
        DCCUHLD.DATA.RSN = CMCS3080.RSN;
        DCCUHLD.DATA.EFF_DT = CMCS3080.EFF_DT;
        DCCUHLD.DATA.EXP_DT = CMCS3080.EXP_DT;
        DCCUHLD.DATA.RMK = CMCS3080.RMK;
        DCCUHLD.DATA.HLD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCCUHLD.DATA.HLD_CLS = CMCS3080.HLD_CLS;
        DCCUHLD.DATA.CHK_OPT = CMCS3080.CHK_FLG;
        DCCUHLD.DATA.TX_TYP_CD = CMCS3080.RMK_CD;
        S000_CALL_DCZUHLD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUHLD.DATA.TX_TYP_CD);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
        CEP.TRC(SCCGWA, DCCUHLD.DATA.CURR_BAL);
        CEP.TRC(SCCGWA, DCCUHLD.DATA.AAMT);
        IBS.init(SCCGWA, CMCO3080);
        CMCO3080.HLD_NO = DCCUHLD.DATA.HLD_NO;
        CMCO3080.CUS_AC = DCCUHLD.DATA.AC;
        CMCO3080.CCY = DCCUHLD.DATA.CCY;
        CMCO3080.CCY_TYP = DCCUHLD.DATA.CCY_TYP;
        CMCO3080.HLD_AMT = DCCUHLD.DATA.AMT;
        CMCO3080.RSN = CMCS3080.RSN;
        CMCO3080.EFF_DT = DCCUHLD.DATA.EFF_DT;
        CMCO3080.EXP_DT = DCCUHLD.DATA.EXP_DT;
        CMCO3080.RMK = CMCS3080.RMK;
        CMCO3080.CURR_BAL = DCCUHLD.DATA.CURR_BAL;
        CMCO3080.AVA_BAL = DCCUHLD.DATA.AAMT;
        CEP.TRC(SCCGWA, CMCO3080.CURR_BAL);
        CEP.TRC(SCCGWA, CMCO3080.AVA_BAL);
    }
    public void B400_RE_HOLD_PROC() throws IOException,SQLException,Exception {
        if (CMCS3080.CHK_FLG == '1') {
            IBS.init(SCCGWA, DCCPCDCK);
            DCCPCDCK.FUNC_CODE = 'P';
            DCCPCDCK.CARD_NO = CMCS3080.CUS_AC;
            DCCPCDCK.CARD_PSW = CMCS3080.PSWD;
            S000_CALL_DCZPCDCK();
            if (pgmRtn) return;
        }
        if (CMCS3080.CHK_FLG == '2') {
            IBS.init(SCCGWA, DCCPCDCK);
            DCCPCDCK.FUNC_CODE = 'T';
            DCCPCDCK.CARD_NO = CMCS3080.CUS_AC;
            DCCPCDCK.TRK2_DAT = CMCS3080.TRK_DAT2;
            DCCPCDCK.TRK3_DAT = CMCS3080.TRK_DAT3;
            S000_CALL_DCZPCDCK();
            if (pgmRtn) return;
        }
        if (CMCS3080.CHK_FLG == '3') {
            IBS.init(SCCGWA, DCCPCDCK);
            DCCPCDCK.FUNC_CODE = 'B';
            DCCPCDCK.CARD_NO = CMCS3080.CUS_AC;
            DCCPCDCK.CARD_PSW = CMCS3080.PSWD;
            DCCPCDCK.TRK2_DAT = CMCS3080.TRK_DAT2;
            DCCPCDCK.TRK3_DAT = CMCS3080.TRK_DAT3;
            S000_CALL_DCZPCDCK();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CMCS3080.HLD_NO);
        IBS.init(SCCGWA, DDCSUHLD);
        DDCSUHLD.KEY.HLD_NO = WS_HLD_NOTEMP;
        S000_CALL_DDZSUHLD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCSUHLD.CRT_DATE);
        IBS.init(SCCGWA, DCCURHLD);
        DCCURHLD.DATA.HLD_NO = WS_HLD_NOTEMP;
        DCCURHLD.DATA.RHLD_TYP = '1';
        DCCURHLD.DATA.RAMT = CMCS3080.HLD_AMT;
        DCCURHLD.DATA.RSN = CMCS3080.RSN;
        DCCURHLD.DATA.RMK = CMCS3080.RMK;
        DCCURHLD.DATA.TX_TYP_CD = CMCS3080.RMK_CD;
        S000_CALL_DCZURHLD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCURHLD.DATA.TX_TYP_CD);
        CEP.TRC(SCCGWA, DCCURHLD.DATA.CURR_BAL);
        CEP.TRC(SCCGWA, DCCURHLD.DATA.AVL_AMT);
        IBS.init(SCCGWA, CMCO3080);
        CMCO3080.HLD_NO = DCCURHLD.DATA.HLD_NO;
        CMCO3080.CUS_AC = DCCURHLD.DATA.AC;
        CMCO3080.CCY = DCCURHLD.DATA.CCY;
        CMCO3080.CCY_TYP = DCCURHLD.DATA.CCY_TYP;
        CMCO3080.HLD_AMT = DCCURHLD.DATA.RAMT;
        CMCO3080.RSN = CMCS3080.RSN;
        CMCO3080.EFF_DT = DCCURHLD.DATA.EFF_DT;
        CMCO3080.EXP_DT = DCCURHLD.DATA.EXP_DT;
        CMCO3080.RMK = CMCS3080.RMK;
        CMCO3080.CURR_BAL = DCCURHLD.DATA.CURR_BAL;
        CMCO3080.AVA_BAL = DCCURHLD.DATA.AVL_AMT;
    }
    public void B600_OUTPUT_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = CMCO3080;
        SCCFMT.DATA_LEN = 360;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_DCZPCDCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-CARD-TRKDAT-CHK", DCCPCDCK, true);
    }
    public void S000_CALL_DDZSUHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSUHLD", DDCSUHLD, true);
    }
    public void S000_CALL_CMZSIQAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CM-S-INQ-CUS-AC", CMCSIQAC, true);
    }
    public void S000_CALL_CIZREGLC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-REG-LC-CTL", CICREGLC);
        if (CICREGLC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICREGLC.RC);
        }
    }
    public void S000_CALL_DCZURHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-RHLD", DCCURHLD);
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL ", CICQACRL, true);
        CEP.TRC(SCCGWA, CICQACRL.RC.RC_CODE);
    }
    public void S000_CALL_DCZUHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-HLD", DCCUHLD);
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
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL, true);
        CEP.TRC(SCCGWA, DDCIQBAL.RC.RC_CODE);
        if (DDCIQBAL.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, DDCIQBAL.RC);
        }
    }
    public void S000_CALL_TDZACE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-AC-ENQ", TDCACE, true);
    }
    public void R000_CALL_DCZPFTCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-FINANCE-TR-CHK", DCCPFTCK, true);
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        WS_RC = 0;
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (WS_RC != 0) {
            SCCCLDT.RC = WS_RC;
        }
        CEP.TRC(SCCGWA, SCCCLDT.RC);
        if (SCCCLDT.RC != 0) {
            IBS.init(SCCGWA, SCCBINF);
            SCCBINF.ERR_TYPE = 'P';
            SCCBINF.ERR_ACTION = 'E';
            WS_RC_DISP = SCCCLDT.RC;
            SCCBINF.ERR_NAME = PGM_SCSSCLDT;
            WS_ERR_MSG = K_SYS_ERR;
            SCCBINF.OTHER_INFO = "CALL-SCSSCLDT ERROR  " + WS_RC_DISP;
            CEP.ERR(SCCGWA, K_SYS_ERR, SCCBINF);
        }
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
