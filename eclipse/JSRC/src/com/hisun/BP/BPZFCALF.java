package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.DD.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFCALF {
    SimpleDateFormat JIBS_sdf;
    Date JIBS_date;
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    BigDecimal bigD;
    String JIBS_NumStr;
    boolean pgmRtn = false;
    String CPN_R_PRMR = "BP-PARM-READ        ";
    String CPN_R_FEE_PARM_MAIN = "BP-F-F-MAINTAIN-PARM";
    String CPN_R_FEE_CAL_FEE = "BP-F-F-CAL-STD-FEE  ";
    String CPN_R_FEE_CAL_IFAV = "BP-F-F-CAL-IFAV     ";
    String CPN_F_COM_FEE_INFO = "BP-F-T-FEE-INFO     ";
    String CPN_EXG_CURRENCY = "BP-EX               ";
    String CPN_Z_CERT_CHARGE = "BP-F-Z-CERT-CHARGE";
    String CPN_R_FEE_BPZPRMM = "BP-PARM-MAINTAIN    ";
    String CPN_S_AMO_CHECK = "BP-P-AMO-CHK        ";
    String K_PARM_TYPE_FBAS = "FBAS ";
    String K_PARM_TYPE_FMSK = "FMSK ";
    String K_FEE_FMT = "BP077";
    String K_PARM_FEETY = "FEETY";
    char K_LOCAL_TYPE = '0';
    String K_LOCAL_CCY = "156";
    String K_ABROAD_CCY = "840";
    double K_MAX_AMT = 99999999999999.99;
    String K_BATCH_CHG_CPN = "BP-BBBBBBBBBBBBBBBBB";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_EMP_RECORD = ' ';
    String WS_CALF_CPN = " ";
    BPZFCALF_WS_CAL_INFO WS_CAL_INFO = new BPZFCALF_WS_CAL_INFO();
    String WS_RGN_CODE = " ";
    double WS_FAV_PCT = 0;
    double WS_TX_AMT = 0;
    String WS_FEE_CCY = " ";
    String WS_CHG_CCY = " ";
    double WS_CMZ_AMT = 0;
    String WS_FMT_TXT = " ";
    String WS_FREE_FMT = " ";
    double WS_TX_EX_RATE = 0;
    int WS_EX_READ_SYS_TIME = 0;
    short WS_FEE_CNT = 0;
    short WS_CNT1 = 0;
    char WS_FEE_EXG_TYP = ' ';
    double WS_UP_AMT = 0;
    double WS_DWN_AMT = 0;
    double WS_MIN_AMT = 0;
    double WS_MAX_AMT = 0;
    String WS_MID_CCY = " ";
    String WS_TS = " ";
    char WS_TBL_FMSK_FLAG = ' ';
    char WS_TBL_FBAS_FLAG = ' ';
    char WS_GET_AGGRAGATE = ' ';
    char WS_FMT_FLG = ' ';
    char WS_DATE_FLG = ' ';
    char WS_PRD_FLG = ' ';
    char WS_BVF_FLG = ' ';
    char WS_BNK_FLG = ' ';
    char WS_CITY_FLG = ' ';
    char WS_CI_TYPE_FLG = ' ';
    char WS_BVF_TYPE_FLG = ' ';
    char WS_URMT_FLG = ' ';
    char WS_DC_FLG = ' ';
    char WS_DEF_FLG = ' ';
    char WS_MATCH_FLG = ' ';
    char WS_CMZ_EX_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCFX BPCFX = new BPCFX();
    BPCFPARM BPCFPARM = new BPCFPARM();
    BPCTIFAV BPCTIFAV = new BPCTIFAV();
    BPCFFCAL BPCFFCAL = new BPCFFCAL();
    BPCPQCMZ BPCPQCMZ = new BPCPQCMZ();
    BPRCMZR BPRCMZR = new BPRCMZR();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    BPRFBAS BPRFBAS = new BPRFBAS();
    BPCTFBAS BPCTFBAS = new BPCTFBAS();
    BPVFMSK BPVFMSK = new BPVFMSK();
    BPVFCOM BPVFCOM = new BPVFCOM();
    BPVFPRD BPVFPRD = new BPVFPRD();
    BPVFCPN BPVFCPN = new BPVFCPN();
    BPVFSVR BPVFSVR = new BPVFSVR();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCBVCHG BPCBVCHG = new BPCBVCHG();
    BPCFEEUC BPCFEEUC = new BPCFEEUC();
    BPCUIFEE BPCUIFEE = new BPCUIFEE();
    CICACCU CICACCU = new CICACCU();
    CICCUST CICCUST = new CICCUST();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DDCIMMST DDCIMMST = new DDCIMMST();
    CICQACRI CICQACRI = new CICQACRI();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCRDAMT BPCRDAMT = new BPCRDAMT();
    SCCGWA SCCGWA;
    BPCTCALF BPCTCALF;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCGCFEE BPCGCFEE;
    BPCGPFEE BPCGPFEE;
    String LK_REC = " ";
    public void MP(SCCGWA SCCGWA, BPCTCALF BPCTCALF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTCALF = BPCTCALF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFCALF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCGCFEE = new BPCGCFEE();
        IBS.init(SCCGWA, BPCGCFEE);
        IBS.CPY2CLS(SCCGWA, GWA_BP_AREA.FEE_AREA.FEE_DATA_PTR, BPCGCFEE);
        BPCGPFEE = (BPCGPFEE) GWA_BP_AREA.FEE_AREA.FEE_PARM_PTR;
        CEP.TRC(SCCGWA, GWA_BP_AREA.FEE_AREA.FEE_PARM_PTR);
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA.CHG_AC_INFO.AC_TYP);
        LK_REC = IBS.CLS2CPY(SCCGWA, BPCTCALF.INPUT_AREA.POINTER);
        IBS.init(SCCGWA, BPRFBAS);
        IBS.init(SCCGWA, BPVFMSK);
        IBS.init(SCCGWA, BPCFPARM);
        IBS.init(SCCGWA, BPCFX);
        WS_URMT_FLG = 'N';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            if (SCCGWA.COMM_AREA.FEE_DATA_IND != 'Y') {
                B020_GET_FEE_CODE_CN();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, "OUTPUT AMT FOR CALLER");
                B050_20_FMT_OUTPUT_INFO_CN();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.FUNC_CODE);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.PROD_CODE);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.TX_AMT);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.TX_CCY);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.TX_AC);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.TX_CI);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.TX_CNT);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.BVF_CODE);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL.BNK_FLG);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL.CITY_FLG);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.URG_RMT_FLG);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL.CI_TYPE_FLG);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL.BVF_TYPE_FLG);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL.DC_FLG);
        if (BPCTCALF.INPUT_AREA.FUNC_CODE == ' ') {
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.TX_AC);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.TX_CI);
    }
    public void B020_GET_FEE_CODE_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPVFSVR);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.SVR_CD);
        if (BPCTCALF.INPUT_AREA.SVR_CD.trim().length() > 0) {
            CEP.TRC(SCCGWA, "AAA");
            BPVFSVR.KEY.SVR_NO = BPCTCALF.INPUT_AREA.SVR_CD;
        } else {
            CEP.TRC(SCCGWA, "BBB");
            BPVFSVR.KEY.SVR_NO = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        }
        CEP.TRC(SCCGWA, BPVFSVR.KEY.SVR_NO);
        IBS.init(SCCGWA, BPCFPARM);
        BPCFPARM.INFO.POINTER = BPVFSVR;
        BPCFPARM.INFO.FUNC = '3';
        BPCFPARM.INFO.TYPE = "FSVR ";
        S000_CALL_BPZFPARM();
        if (pgmRtn) return;
        if (BPCFPARM.RETURN_INFO == 'N') {
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "ZHENGJIE");
        CEP.TRC(SCCGWA, BPRPRMT.DAT_TXT.FILLER);
        CEP.TRC(SCCGWA, BPRPRMT);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.TX_CI);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.TX_AC);
        IBS.init(SCCGWA, CICCUST);
        IBS.init(SCCGWA, CICACCU);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL.CI_TYPE_FLG);
        if (BPCTCALF.INPUT_AREA.ATTR_VAL.CI_TYPE_FLG == ' ') {
            if ((BPCTCALF.INPUT_AREA.TX_CI.trim().length() > 0 
                || BPCTCALF.INPUT_AREA.TX_AC.trim().length() > 0)) {
                if (BPCTCALF.INPUT_AREA.TX_CI.trim().length() > 0) {
                    CEP.TRC(SCCGWA, "GET CI-TYP BY TCALF-TX-CI");
                    CICCUST.DATA.CI_NO = BPCTCALF.INPUT_AREA.TX_CI;
                    CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
                    CICCUST.FUNC = 'C';
                    S000_CALL_CIZCUST();
                    if (pgmRtn) return;
                } else {
                    if (BPCTCALF.INPUT_AREA.TX_AC.trim().length() > 0) {
                        CEP.TRC(SCCGWA, "GET CI-TYP BY TCALF-TX-AC");
                        CICACCU.DATA.AGR_NO = BPCTCALF.INPUT_AREA.TX_AC;
                        S000_CALL_CIZACCU();
                        if (pgmRtn) return;
                    }
                }
            } else {
                if (BPCGPFEE.CUS_DATA.CUS_INFO[1-1].CUS_NO.trim().length() > 0) {
                    CEP.TRC(SCCGWA, "GET CI-TYP BY PFEE-CUS-NO(1)");
                    CICCUST.DATA.CI_NO = BPCGPFEE.CUS_DATA.CUS_INFO[1-1].CUS_NO;
                    CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
                    CICCUST.FUNC = 'C';
                    S000_CALL_CIZCUST();
                    if (pgmRtn) return;
                } else {
                    if (BPCGPFEE.TX_DATA.CHG_AC_INFO.CHG_AC.trim().length() > 0) {
                        CEP.TRC(SCCGWA, "GET CI-TYP BY PFEE-CHG-AC");
                        CICACCU.DATA.AGR_NO = BPCGPFEE.TX_DATA.CHG_AC_INFO.CHG_AC;
                        S000_CALL_CIZACCU();
                        if (pgmRtn) return;
                    } else {
                        CEP.TRC(SCCGWA, "GET CI-TYP BY PFEE-CARD-PSBK-NO");
                        CICACCU.DATA.AGR_NO = BPCGPFEE.TX_DATA3.CARD_PSBK_NO;
                        S000_CALL_CIZACCU();
                        if (pgmRtn) return;
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_TYP);
        CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
        if (BPCTCALF.INPUT_AREA.ATTR_VAL.CI_TYPE_FLG == ' ') {
            if (CICACCU.DATA.CI_TYP != ' ') {
                CEP.TRC(SCCGWA, "GET ACCU-CI-TYP");
                BPCTCALF.INPUT_AREA.ATTR_VAL.CI_TYPE_FLG = CICACCU.DATA.CI_TYP;
                BPCTCALF.INPUT_AREA.FEE_ATTR = IBS.CLS2CPY(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL);
            } else {
                if (CICCUST.O_DATA.O_CI_TYP != ' ') {
                    CEP.TRC(SCCGWA, "GET CUST-O-CI-TYP");
                    BPCTCALF.INPUT_AREA.ATTR_VAL.CI_TYPE_FLG = CICCUST.O_DATA.O_CI_TYP;
                    BPCTCALF.INPUT_AREA.FEE_ATTR = IBS.CLS2CPY(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL);
                }
            }
        }
        CEP.TRC(SCCGWA, BPVFSVR.VAL.MATCH_FLG);
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WS_TS = JIBS_sdf.format(JIBS_date);
        if (BPVFSVR.VAL.MATCH_FLG == 'N') {
            CEP.TRC(SCCGWA, "FSVR MATCH FLAG IN NO");
            B021_FEE_CODE_EXACTLY_MATCH();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "FSVR MATCH FLAG IN YES OR SPACE");
            B022_FEE_CODE_SPACE_MATCH();
            if (pgmRtn) return;
        }
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WS_TS = JIBS_sdf.format(JIBS_date);
    }
    public void B021_FEE_CODE_EXACTLY_MATCH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL.CI_TYPE_FLG);
        WS_MATCH_FLG = 'N';
        for (WS_CNT1 = 1; WS_CNT1 <= 20; WS_CNT1 += 1) {
            CEP.TRC(SCCGWA, WS_CNT1);
            WS_PRD_FLG = 'N';
            WS_BVF_FLG = 'N';
            WS_BNK_FLG = 'N';
            WS_CITY_FLG = 'N';
            WS_CI_TYPE_FLG = 'N';
            WS_BVF_TYPE_FLG = 'N';
            WS_DC_FLG = 'N';
            WS_DEF_FLG = 'N';
            CEP.TRC(SCCGWA, BPVFSVR.VAL.DATA[WS_CNT1-1].FEE_CODE);
            if (BPVFSVR.VAL.DATA[WS_CNT1-1].FEE_CODE.trim().length() > 0) {
                CEP.TRC(SCCGWA, BPVFSVR.VAL.DATA[WS_CNT1-1].PROD_CODE);
                CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.PROD_CODE);
                if (BPVFSVR.VAL.DATA[WS_CNT1-1].PROD_CODE.trim().length() > 0) {
                    if (BPVFSVR.VAL.DATA[WS_CNT1-1].PROD_CODE.equalsIgnoreCase(BPCTCALF.INPUT_AREA.PROD_CODE)) {
                        WS_PRD_FLG = 'Y';
                    }
                } else {
                    WS_PRD_FLG = 'Y';
                }
                CEP.TRC(SCCGWA, BPVFSVR.VAL.DATA[WS_CNT1-1].BVF_CODE);
                CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.BVF_CODE);
                if (BPVFSVR.VAL.DATA[WS_CNT1-1].BVF_CODE.equalsIgnoreCase(BPCTCALF.INPUT_AREA.BVF_CODE)) {
                    WS_BVF_FLG = 'Y';
                }
                CEP.TRC(SCCGWA, BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.BNK_FLG);
                CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL.BNK_FLG);
                if (BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.BNK_FLG != ' ') {
                    if (BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.BNK_FLG == BPCTCALF.INPUT_AREA.ATTR_VAL.BNK_FLG) {
                        WS_BNK_FLG = 'Y';
                    }
                } else {
                    WS_BNK_FLG = 'Y';
                }
                CEP.TRC(SCCGWA, BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.CITY_FLG);
                CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL.CITY_FLG);
                if (BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.CITY_FLG != ' ') {
                    if (BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.CITY_FLG == BPCTCALF.INPUT_AREA.ATTR_VAL.CITY_FLG) {
                        WS_CITY_FLG = 'Y';
                    }
                } else {
                    WS_CITY_FLG = 'Y';
                }
                CEP.TRC(SCCGWA, BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.CI_TYPE_FLG);
                CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL.CI_TYPE_FLG);
                if (BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.CI_TYPE_FLG != ' ') {
                    if (BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.CI_TYPE_FLG == BPCTCALF.INPUT_AREA.ATTR_VAL.CI_TYPE_FLG) {
                        WS_CI_TYPE_FLG = 'Y';
                    }
                } else {
                    WS_CI_TYPE_FLG = 'Y';
                }
                CEP.TRC(SCCGWA, BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.BVF_TYPE_FLG);
                CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL.BVF_TYPE_FLG);
                if (BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.BVF_TYPE_FLG != ' ') {
                    if (BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.BVF_TYPE_FLG == BPCTCALF.INPUT_AREA.ATTR_VAL.BVF_TYPE_FLG) {
                        WS_BVF_TYPE_FLG = 'Y';
                    }
                } else {
                    WS_BVF_TYPE_FLG = 'Y';
                }
                CEP.TRC(SCCGWA, BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.DC_FLG);
                CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL.DC_FLG);
                if (BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.DC_FLG != ' ') {
                    if (BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.DC_FLG == BPCTCALF.INPUT_AREA.ATTR_VAL.DC_FLG) {
                        WS_DC_FLG = 'Y';
                    }
                } else {
                    WS_DC_FLG = 'Y';
                }
                CEP.TRC(SCCGWA, BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.DEF_FLG);
                CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL.DEF_FLG);
                if (BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.DEF_FLG.trim().length() > 0) {
                    if (BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.DEF_FLG.equalsIgnoreCase(BPCTCALF.INPUT_AREA.ATTR_VAL.DEF_FLG)) {
                        WS_DEF_FLG = 'Y';
                    }
                } else {
                    WS_DEF_FLG = 'Y';
                }
                B050_MAIN_PROC_COUNT_FEE_CN();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, WS_MATCH_FLG);
        if (WS_MATCH_FLG == 'N') {
            B022_FEE_CODE_SPACE_MATCH();
            if (pgmRtn) return;
        }
    }
    public void B022_FEE_CODE_SPACE_MATCH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL.CI_TYPE_FLG);
        for (WS_CNT1 = 1; WS_CNT1 <= 20; WS_CNT1 += 1) {
            WS_PRD_FLG = 'N';
            WS_BVF_FLG = 'N';
            WS_BNK_FLG = 'N';
            WS_CITY_FLG = 'N';
            WS_CI_TYPE_FLG = 'N';
            WS_BVF_TYPE_FLG = 'N';
            WS_DC_FLG = 'N';
            WS_DEF_FLG = 'N';
            CEP.TRC(SCCGWA, BPVFSVR.VAL.DATA[WS_CNT1-1].FEE_CODE);
            if (BPVFSVR.VAL.DATA[WS_CNT1-1].FEE_CODE.trim().length() > 0) {
                if (BPVFSVR.VAL.DATA[WS_CNT1-1].PROD_CODE.trim().length() > 0) {
                    CEP.TRC(SCCGWA, BPVFSVR.VAL.DATA[WS_CNT1-1].PROD_CODE);
                    if (BPVFSVR.VAL.DATA[WS_CNT1-1].PROD_CODE.equalsIgnoreCase(BPCTCALF.INPUT_AREA.PROD_CODE)) {
                        WS_PRD_FLG = 'Y';
                    }
                } else {
                    WS_PRD_FLG = 'Y';
                }
                if (BPVFSVR.VAL.DATA[WS_CNT1-1].BVF_CODE.trim().length() > 0) {
                    if (BPVFSVR.VAL.DATA[WS_CNT1-1].BVF_CODE.equalsIgnoreCase(BPCTCALF.INPUT_AREA.BVF_CODE)) {
                        WS_BVF_FLG = 'Y';
                    }
                } else {
                    WS_BVF_FLG = 'Y';
                }
                if (BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.BNK_FLG != ' ') {
                    if (BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.BNK_FLG == BPCTCALF.INPUT_AREA.ATTR_VAL.BNK_FLG) {
                        WS_BNK_FLG = 'Y';
                    }
                } else {
                    WS_BNK_FLG = 'Y';
                }
                if (BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.CITY_FLG != ' ') {
                    if (BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.CITY_FLG == BPCTCALF.INPUT_AREA.ATTR_VAL.CITY_FLG) {
                        WS_CITY_FLG = 'Y';
                    }
                } else {
                    WS_CITY_FLG = 'Y';
                }
                CEP.TRC(SCCGWA, BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.CI_TYPE_FLG);
                CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL.CI_TYPE_FLG);
                if (BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.CI_TYPE_FLG != ' ') {
                    if (BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.CI_TYPE_FLG == BPCTCALF.INPUT_AREA.ATTR_VAL.CI_TYPE_FLG) {
                        WS_CI_TYPE_FLG = 'Y';
                    }
                } else {
                    WS_CI_TYPE_FLG = 'Y';
                }
                CEP.TRC(SCCGWA, BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.BVF_TYPE_FLG);
                CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL.BVF_TYPE_FLG);
                if (BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.BVF_TYPE_FLG != ' ') {
                    if (BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.BVF_TYPE_FLG == BPCTCALF.INPUT_AREA.ATTR_VAL.BVF_TYPE_FLG) {
                        WS_BVF_TYPE_FLG = 'Y';
                    }
                } else {
                    WS_BVF_TYPE_FLG = 'Y';
                }
                CEP.TRC(SCCGWA, BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.DC_FLG);
                CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL.DC_FLG);
                if (BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.DC_FLG != ' ') {
                    if (BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.DC_FLG == BPCTCALF.INPUT_AREA.ATTR_VAL.DC_FLG) {
                        WS_DC_FLG = 'Y';
                    }
                } else {
                    WS_DC_FLG = 'Y';
                }
                CEP.TRC(SCCGWA, BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.DEF_FLG);
                CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.ATTR_VAL.DEF_FLG);
                if (BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.DEF_FLG.trim().length() > 0) {
                    if (BPVFSVR.VAL.DATA[WS_CNT1-1].ATTR_VAL.DEF_FLG.equalsIgnoreCase(BPCTCALF.INPUT_AREA.ATTR_VAL.DEF_FLG)) {
                        WS_DEF_FLG = 'Y';
                    }
                } else {
                    WS_DEF_FLG = 'Y';
                }
                B050_MAIN_PROC_COUNT_FEE_CN();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_GET_MSK_BY_PRD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.PROD_CODE);
        if (BPCTCALF.INPUT_AREA.PROD_CODE.trim().length() > 0) {
            IBS.init(SCCGWA, BPVFMSK);
            IBS.init(SCCGWA, BPCFPARM);
            BPCFPARM.INFO.TYPE = "FMSK ";
            BPCFPARM.INFO.FUNC = '3';
            BPVFMSK.KEY.KEY_TYPE = 'P';
            BPVFMSK.KEY.PRD_CODE = BPCTCALF.INPUT_AREA.PROD_CODE;
            BPVFMSK.VAL.EFF_DT = 0;
            CEP.TRC(SCCGWA, BPVFMSK.KEY.PRD_CODE);
            BPCFPARM.INFO.POINTER = BPVFMSK;
            S000_CALL_BPZFPARM();
            if (pgmRtn) return;
            if (BPCFPARM.RETURN_INFO == 'N') {
                Z_RET();
                if (pgmRtn) return;
            } else {
                WS_CAL_INFO.WS_MSK_INFO_PRD.WS_MSK_PRD_FLG1 = BPVFMSK.VAL.MASK_1;
                WS_CAL_INFO.WS_MSK_INFO_PRD.WS_MSK_PRD_FLG2 = BPVFMSK.VAL.MASK_2;
                WS_CAL_INFO.WS_MSK_INFO_PRD.WS_MSK_PRD_FLG3 = BPVFMSK.VAL.MASK_3;
                WS_CAL_INFO.WS_MSK_INFO_PRD.WS_MSK_PRD_FLG4 = BPVFMSK.VAL.MASK_4;
            }
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PRD_CODE_MUSTINPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_GET_MSK_BY_CPN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.CPN_ID);
        if (!BPCTCALF.INPUT_AREA.CPN_ID.equalsIgnoreCase(K_BATCH_CHG_CPN)) {
            IBS.init(SCCGWA, BPVFCPN);
            IBS.init(SCCGWA, BPCFPARM);
            BPCFPARM.INFO.TYPE = "FCPN ";
            BPCFPARM.INFO.FUNC = '3';
            BPVFCPN.KEY.CPNT_ID = BPCTCALF.INPUT_AREA.CPN_ID;
            BPCFPARM.INFO.POINTER = BPVFCPN;
            S000_CALL_BPZFPARM();
            if (pgmRtn) return;
            if (BPCFPARM.RETURN_INFO == 'N') {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFPARM.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCTCALF.RC);
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFPARM.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
                Z_RET();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, BPVFCPN.VAL.EFF_DATE);
            CEP.TRC(SCCGWA, BPVFCPN.VAL.EXP_DATE);
            if (BPVFCPN.VAL.EFF_DATE > SCCGWA.COMM_AREA.AC_DATE 
                || BPVFCPN.VAL.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FEE_EFF_DATE_ERR, BPCTCALF.RC);
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFF_DATE_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
                Z_RET();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, BPVFMSK);
            IBS.init(SCCGWA, BPCFPARM);
            BPCFPARM.INFO.TYPE = "FMSK ";
            BPCFPARM.INFO.FUNC = '3';
            BPVFMSK.KEY.KEY_TYPE = 'C';
            BPVFMSK.KEY.CPNT_ID = BPCTCALF.INPUT_AREA.CPN_ID;
            BPVFMSK.VAL.EFF_DT = BPVFCPN.VAL.EFF_DATE;
            BPVFMSK.VAL.EXP_DT = BPVFCPN.VAL.EXP_DATE;
            CEP.TRC(SCCGWA, BPVFMSK.VAL.EFF_DT);
            CEP.TRC(SCCGWA, BPVFMSK.VAL.EXP_DT);
            BPCFPARM.INFO.POINTER = BPVFMSK;
            S000_CALL_BPZFPARM();
            if (pgmRtn) return;
            if (BPCFPARM.RETURN_INFO == 'N') {
                Z_RET();
                if (pgmRtn) return;
            } else {
                WS_CAL_INFO.WS_MSK_INFO_CPN.WS_MSK_CPN_FLG1 = BPVFMSK.VAL.MASK_1;
                WS_CAL_INFO.WS_MSK_INFO_CPN.WS_MSK_CPN_FLG2 = BPVFMSK.VAL.MASK_2;
                WS_CAL_INFO.WS_MSK_INFO_CPN.WS_MSK_CPN_FLG3 = BPVFMSK.VAL.MASK_3;
                WS_CAL_INFO.WS_MSK_INFO_CPN.WS_MSK_CPN_FLG4 = BPVFMSK.VAL.MASK_4;
            }
        } else {
            WS_CAL_INFO.WS_MSK_INFO_CPN.WS_MSK_CPN_FLG1 = "11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
            WS_CAL_INFO.WS_MSK_INFO_CPN.WS_MSK_CPN_FLG2 = "11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
            WS_CAL_INFO.WS_MSK_INFO_CPN.WS_MSK_CPN_FLG3 = "11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
            WS_CAL_INFO.WS_MSK_INFO_CPN.WS_MSK_CPN_FLG4 = "11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
        }
    }
    public void B050_MAIN_PROC_COUNT_FEE_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPVFSVR.VAL.MATCH_FLG);
        CEP.TRC(SCCGWA, WS_PRD_FLG);
        CEP.TRC(SCCGWA, WS_BNK_FLG);
        CEP.TRC(SCCGWA, WS_CITY_FLG);
        CEP.TRC(SCCGWA, WS_BVF_FLG);
        CEP.TRC(SCCGWA, WS_CI_TYPE_FLG);
        CEP.TRC(SCCGWA, WS_BVF_TYPE_FLG);
        CEP.TRC(SCCGWA, WS_DC_FLG);
        CEP.TRC(SCCGWA, WS_DEF_FLG);
        if (WS_PRD_FLG == 'Y' 
            && WS_BNK_FLG == 'Y' 
            && WS_CITY_FLG == 'Y' 
            && WS_BVF_FLG == 'Y' 
            && WS_CI_TYPE_FLG == 'Y' 
            && WS_BVF_TYPE_FLG == 'Y' 
            && WS_DC_FLG == 'Y' 
            && WS_DEF_FLG == 'Y') {
            WS_MATCH_FLG = 'Y';
            WS_FEE_CNT = (short) (WS_FEE_CNT + 1);
            B050_01_GET_FEE_BASIC_INFO_CN();
            if (pgmRtn) return;
            B050_05_GET_BASIC_FEE_CN();
            if (pgmRtn) return;
            JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
            JIBS_date = new Date();
            WS_TS = JIBS_sdf.format(JIBS_date);
            B050_50_FEE_CMZ_INFO_CN();
            if (pgmRtn) return;
            JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
            JIBS_date = new Date();
            WS_TS = JIBS_sdf.format(JIBS_date);
            if (BPCFFCAL.DATA.CHG_FLG != 'N') {
                if (BPCPQCMZ.DATE_FLG != 'Y') {
                    B050_03_GET_FEE_COM_INFO_CN();
                    if (pgmRtn) return;
                    B050_10_GET_FAV_INFO_CN();
                    if (pgmRtn) return;
                    B050_15_CAL_FEE_AMT_CN();
                    if (pgmRtn) return;
                } else {
                    CEP.TRC(SCCGWA, BPCPQCMZ.RC);
                    WS_CAL_INFO.WS_F_ITM_CNT += 1;
                    CEP.TRC(SCCGWA, WS_CAL_INFO.WS_F_ITM_CNT);
                    if (BPCPQCMZ.RC.RC_CODE == 0) {
                        B050_50_01_CAL_CMZ_FEE_AMT_CN();
                        if (pgmRtn) return;
                    } else {
                        BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT = BPCFFCAL.DATA.FEE_AMT;
                    }
                }
                if (BPCTCALF.INPUT_AREA.URG_RMT_FLG == 'Y') {
                    B050_15_2_CAL_URGENT_FEE_CN();
                    if (pgmRtn) return;
                }
                B060_EXCHANGE_CURRENCY_CN();
                if (pgmRtn) return;
                R000_GET_RESULT_AMT();
                if (pgmRtn) return;
                B050_20_FMT_OUTPUT_INFO();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.FUNC_CODE);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.FEE_IND);
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.FEE_DATA_IND);
                if (BPCTCALF.INPUT_AREA.FUNC_CODE == 'C' 
                    && SCCGWA.COMM_AREA.FEE_DATA_IND != 'Y') {
                    CEP.TRC(SCCGWA, "113311");
                    B050_25_FMT_FEE_INFO_TO_GWA_CN();
                    if (pgmRtn) return;
                } else {
                    CEP.TRC(SCCGWA, "113312");
                    if (BPCTCALF.INPUT_AREA.FUNC_CODE == 'C' 
                        && SCCGWA.COMM_AREA.FEE_DATA_IND == 'Y') {
                        CEP.TRC(SCCGWA, "113313");
                        B050_30_COMP_FEE_INFO();
                        if (pgmRtn) return;
                    }
                }
                B050_35_FMT_PFEE_INFO_TO_GWA();
                if (pgmRtn) return;
            }
        }
    }
    public void B050_01_GET_FEE_BASIC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFBAS);
        IBS.init(SCCGWA, BPCTFBAS);
        BPCTFBAS.INFO.POINTER = BPRFBAS;
        BPCTFBAS.INFO.REC_LEN = 312;
        BPRFBAS.FEE_NO = WS_CAL_INFO.WS_CNT;
        BPCTFBAS.INFO.FUNC = 'Q';
        CEP.TRC(SCCGWA, WS_CAL_INFO.WS_CNT);
        S000_CALL_BPZTFBAS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRFBAS.KEY.FEE_CODE);
        if (BPCTFBAS.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CODE_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRFBAS.REG_TYPE.equalsIgnoreCase("00") 
            || BPRFBAS.REG_TYPE.trim().length() == 0) {
            WS_RGN_CODE = " ";
        } else {
            if (BPRFBAS.REG_TYPE.equalsIgnoreCase("99")) {
                WS_RGN_CODE = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                JIBS_tmp_int = WS_RGN_CODE.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) WS_RGN_CODE = "0" + WS_RGN_CODE;
            } else {
                R000_GET_REG_CODE_BY_TYPE();
                if (pgmRtn) return;
            }
        }
    }
    public void B050_01_GET_FEE_BASIC_INFO_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFBAS);
        IBS.init(SCCGWA, BPCTFBAS);
        BPCTFBAS.INFO.POINTER = BPRFBAS;
        BPCTFBAS.INFO.REC_LEN = 312;
        BPRFBAS.KEY.FEE_CODE = BPVFSVR.VAL.DATA[WS_CNT1-1].FEE_CODE;
        BPCTFBAS.INFO.FUNC = 'Q';
        CEP.TRC(SCCGWA, BPVFSVR.VAL.DATA[WS_CNT1-1].FEE_CODE);
        CEP.TRC(SCCGWA, BPRFBAS.KEY.FEE_CODE);
        S000_CALL_BPZTFBAS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRFBAS.FEE_NO);
        if (BPCTFBAS.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CODE_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRFBAS.REG_TYPE.equalsIgnoreCase("00") 
            || BPRFBAS.REG_TYPE.trim().length() == 0) {
            WS_RGN_CODE = " ";
        } else {
            if (BPRFBAS.REG_TYPE.equalsIgnoreCase("99")) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
                BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
                if (BPCTCALF.INPUT_AREA.FUNC_CODE == 'C') {
                    if (BPCGPFEE.TX_DATA3.FEE_DPTM != 0) {
                        BPCPQORG.BR = BPCGPFEE.TX_DATA3.FEE_DPTM;
                    }
                }
                CEP.TRC(SCCGWA, BPCPQORG.BR);
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                WS_RGN_CODE = "" + BPCPQORG.BRANCH_BR;
                JIBS_tmp_int = WS_RGN_CODE.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) WS_RGN_CODE = "0" + WS_RGN_CODE;
                CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
            } else {
                R000_GET_REG_CODE_BY_TYPE();
                if (pgmRtn) return;
            }
        }
    }
    public void B050_02_CHECK_EFF_DATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, BPRFBAS.START_DATE);
        CEP.TRC(SCCGWA, BPRFBAS.END_DATE);
        WS_DATE_FLG = 'Y';
        if (BPRFBAS.START_DATE > SCCGWA.COMM_AREA.AC_DATE 
            || BPRFBAS.END_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_DATE_FLG = 'N';
        } else {
            IBS.init(SCCGWA, BPVFPRD);
            IBS.init(SCCGWA, BPCFPARM);
            BPCFPARM.INFO.TYPE = "FPRD ";
            BPVFPRD.KEY.PRD_CODE = BPCTCALF.INPUT_AREA.PROD_CODE;
            BPVFPRD.KEY.FEE_CODE = BPRFBAS.KEY.FEE_CODE;
            CEP.TRC(SCCGWA, BPVFPRD.KEY.PRD_CODE);
            CEP.TRC(SCCGWA, BPVFPRD.KEY.FEE_CODE);
            R000_GET_FPRD_INFO();
            if (pgmRtn) return;
            if (BPCFPARM.RETURN_INFO == 'N') {
                CEP.TRC(SCCGWA, "AAAAA");
                WS_DATE_FLG = 'N';
            } else {
                CEP.TRC(SCCGWA, BPVFPRD.DATE.EFF_DATE);
                CEP.TRC(SCCGWA, BPVFPRD.DATE.EXP_DATE);
                if (BPVFPRD.DATE.EFF_DATE > SCCGWA.COMM_AREA.AC_DATE 
                    || BPVFPRD.DATE.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                    WS_DATE_FLG = 'N';
                }
            }
        }
    }
    public void B050_50_FEE_CMZ_INFO_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQCMZ);
        IBS.init(SCCGWA, CICACCU);
        BPCPQCMZ.CI_NO = BPCTCALF.INPUT_AREA.TX_CI;
        BPCPQCMZ.FEE_CODE = BPVFSVR.VAL.DATA[WS_CNT1-1].FEE_CODE;
        BPCPQCMZ.INQ_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPQCMZ.CMZ_AC = BPCTCALF.INPUT_AREA.TX_AC;
        BPCPQCMZ.CMZ_CARD = BPCTCALF.INPUT_AREA.TX_AC;
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.TX_AC);
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA.CHG_AC_INFO.AC_TYP);
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA3.CARD_PSBK_NO);
        if (BPCTCALF.INPUT_AREA.TX_AC.trim().length() > 0) {
            CICACCU.DATA.AGR_NO = BPCTCALF.INPUT_AREA.TX_AC;
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
            BPCPQCMZ.CI_NO = CICACCU.DATA.CI_NO;
        }
        CEP.TRC(SCCGWA, BPCPQCMZ.CI_NO);
        if (BPCPQCMZ.CI_NO.trim().length() > 0) {
            S000_CALL_BPZPQCMZ();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQCMZ.DATE_FLG);
            CEP.TRC(SCCGWA, BPCPQCMZ.CCY_RULE);
            CEP.TRC(SCCGWA, BPCPQCMZ.CMZ_CCY);
            CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_CCY);
            if (BPCPQCMZ.DATE_FLG == 'Y' 
                && BPCPQCMZ.CCY_RULE == '0' 
                && !BPCPQCMZ.CMZ_CCY.equalsIgnoreCase(BPCFFCAL.DATA.FEE_CCY)) {
                BPCPQCMZ.DATE_FLG = 'N';
            }
            CEP.TRC(SCCGWA, BPCPQCMZ.DATE_FLG);
        }
    }
    public void B050_50_01_CAL_CMZ_FEE_AMT_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQCMZ.CMZ_FLG1);
        if (BPCPQCMZ.CMZ_FLG1 == '0') {
            CEP.TRC(SCCGWA, "111");
            if (BPCPQCMZ.CMZ_FLG2 == '0') {
                S000_CMZ_CCY_FOR_AMT();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, "AMT MODE");
                if (BPCFFCAL.DATA.FEE_AMT > WS_CMZ_AMT) {
                    CEP.TRC(SCCGWA, "LARGE THAN");
                    BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT = BPCFFCAL.DATA.FEE_AMT - WS_CMZ_AMT;
                } else {
                    CEP.TRC(SCCGWA, "LESS THAN");
                    BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT = 0;
                }
            } else {
                BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT = BPCFFCAL.DATA.FEE_AMT - BPCFFCAL.DATA.FEE_AMT * BPCPQCMZ.CMZ_PCN;
                bigD = new BigDecimal(BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT);
                BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            }
        } else {
            CEP.TRC(SCCGWA, "222");
            CEP.TRC(SCCGWA, BPCPQCMZ.CMZ_FLG1);
            CEP.TRC(SCCGWA, BPCPQCMZ.CMZ_CCY);
            CEP.TRC(SCCGWA, BPCPQCMZ.CCY_RULE);
            if (BPCPQCMZ.CMZ_FLG1 == '1') {
                S000_CMZ_CCY_FOR_AMT();
                if (pgmRtn) return;
                BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT = WS_CMZ_AMT;
            }
        }
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT);
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AMT);
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_W_HAS_DIY_CHG_FEE;
        S000_ERR_MSG_PROC();
        if (pgmRtn) return;
    }
    public void S000_CMZ_CCY_FOR_AMT() throws IOException,SQLException,Exception {
        WS_CMZ_AMT = 0;
        CEP.TRC(SCCGWA, BPCPQCMZ.CCY_RULE);
        CEP.TRC(SCCGWA, BPCPQCMZ.CMZ_CCY);
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_CCY);
        if (BPCPQCMZ.CCY_RULE == '0' 
            && BPCPQCMZ.CMZ_CCY.equalsIgnoreCase(BPCFFCAL.DATA.FEE_CCY)) {
            WS_CMZ_AMT = BPCPQCMZ.CMZ_AMT;
        }
        if (BPCPQCMZ.CCY_RULE == '1') {
            if (BPCPQCMZ.CMZ_CCY.equalsIgnoreCase(BPCFFCAL.DATA.FEE_CCY) 
                || BPCPQCMZ.CMZ_AMT == 0) {
                WS_CMZ_AMT = BPCPQCMZ.CMZ_AMT;
            } else {
                WS_TX_AMT = BPCPQCMZ.CMZ_AMT;
                WS_CMZ_EX_FLG = 'Y';
                T000_EXCHANGE_CURRENCY();
                if (pgmRtn) return;
                WS_CMZ_AMT = WS_TX_AMT;
                CEP.TRC(SCCGWA, WS_TX_AMT);
            }
        }
    }
    public void B050_03_GET_FEE_COM_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_FREE_FMT);
        IBS.init(SCCGWA, BPVFCOM);
        IBS.init(SCCGWA, BPCFPARM);
        BPCFPARM.INFO.TYPE = "FCOM ";
        BPVFCOM.KEY.REG_CODE = WS_RGN_CODE;
        BPVFCOM.KEY.CHN_NO = SCCGWA.COMM_AREA.CHNL;
        BPVFCOM.KEY.FREE_FMT = WS_FREE_FMT;
        R000_GET_FCOM_INFO();
        if (pgmRtn) return;
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFCOM);
            BPVFCOM.KEY.REG_CODE = WS_RGN_CODE;
            BPVFCOM.KEY.CHN_NO = " ";
            BPVFCOM.KEY.FREE_FMT = WS_FREE_FMT;
            R000_GET_FCOM_INFO();
            if (pgmRtn) return;
        }
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFCOM);
            BPVFCOM.KEY.REG_CODE = " ";
            BPVFCOM.KEY.CHN_NO = SCCGWA.COMM_AREA.CHNL;
            BPVFCOM.KEY.FREE_FMT = WS_FREE_FMT;
            R000_GET_FCOM_INFO();
            if (pgmRtn) return;
        }
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFCOM);
            BPVFCOM.KEY.REG_CODE = " ";
            BPVFCOM.KEY.CHN_NO = " ";
            BPVFCOM.KEY.FREE_FMT = WS_FREE_FMT;
            R000_GET_FCOM_INFO();
            if (pgmRtn) return;
        }
    }
    public void B050_03_GET_FEE_COM_INFO_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_FREE_FMT);
        IBS.init(SCCGWA, BPVFCOM);
        IBS.init(SCCGWA, BPCFPARM);
        BPCFPARM.INFO.TYPE = "FCOM ";
        BPVFCOM.KEY.FEE_CODE = BPRFBAS.KEY.FEE_CODE;
        BPVFCOM.KEY.REG_CODE = WS_RGN_CODE;
        BPVFCOM.KEY.CHN_NO = SCCGWA.COMM_AREA.CHNL;
        BPVFCOM.KEY.FREE_FMT = WS_FREE_FMT;
        R000_GET_FCOM_INFO();
        if (pgmRtn) return;
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFCOM);
            BPVFCOM.KEY.FEE_CODE = BPVFSVR.VAL.DATA[WS_CNT1-1].FEE_CODE;
            BPVFCOM.KEY.REG_CODE = WS_RGN_CODE;
            BPVFCOM.KEY.CHN_NO = " ";
            BPVFCOM.KEY.FREE_FMT = WS_FREE_FMT;
            R000_GET_FCOM_INFO();
            if (pgmRtn) return;
        }
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFCOM);
            BPVFCOM.KEY.FEE_CODE = BPVFSVR.VAL.DATA[WS_CNT1-1].FEE_CODE;
            BPVFCOM.KEY.REG_CODE = " ";
            BPVFCOM.KEY.CHN_NO = SCCGWA.COMM_AREA.CHNL;
            BPVFCOM.KEY.FREE_FMT = WS_FREE_FMT;
            R000_GET_FCOM_INFO();
            if (pgmRtn) return;
        }
        if (BPCFPARM.RETURN_INFO == 'N') {
            IBS.init(SCCGWA, BPVFCOM);
            BPVFCOM.KEY.FEE_CODE = BPVFSVR.VAL.DATA[WS_CNT1-1].FEE_CODE;
            BPVFCOM.KEY.REG_CODE = " ";
            BPVFCOM.KEY.CHN_NO = " ";
            BPVFCOM.KEY.FREE_FMT = WS_FREE_FMT;
            R000_GET_FCOM_INFO();
            if (pgmRtn) return;
        }
    }
    public void B050_05_GET_BASIC_FEE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFCAL);
        BPCFFCAL.DATA.TX_AC = BPCTCALF.INPUT_AREA.TX_AC;
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.TX_AC);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.OTHER_AC);
        BPCFFCAL.DATA.OTHER_AC = BPCTCALF.INPUT_AREA.OTHER_AC;
        BPCFFCAL.DATA.FEE_CODE = "" + WS_CAL_INFO.WS_CNT;
        JIBS_tmp_int = BPCFFCAL.DATA.FEE_CODE.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) BPCFFCAL.DATA.FEE_CODE = "0" + BPCFFCAL.DATA.FEE_CODE;
        BPCFFCAL.DATA.REG_CODE = WS_RGN_CODE;
        BPCFFCAL.DATA.EXG_TYP = BPRFBAS.EXG_TYP;
        BPCFFCAL.DATA.EXG_GROUP = "MDR";
        BPCFFCAL.DATA.CHNL_NO = " ";
        BPCFFCAL.DATA.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BPCFFCAL.DATA.FREE_FMT = WS_FREE_FMT;
        CEP.TRC(SCCGWA, WS_FREE_FMT);
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FREE_FMT);
        BPCFFCAL.DATA.CNT_CCY = BPCTCALF.INPUT_AREA.TX_CCY;
        BPCFFCAL.DATA.TX_AMT = BPCTCALF.INPUT_AREA.TX_AMT;
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.TX_CNT);
        BPCFFCAL.DATA.TX_CNT = BPCTCALF.INPUT_AREA.TX_CNT;
        BPCFFCAL.DATA.POINT_LEN = BPCTCALF.INPUT_AREA.POINT_LEN;
        BPCFFCAL.DATA.POINTER = BPCTCALF.INPUT_AREA.POINTER;
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA.CHG_AC_INFO.AC_TYP);
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA.CHG_AC_INFO.CHG_CCY);
        if ((BPCGPFEE.TX_DATA.CHG_AC_INFO.AC_TYP != '0' 
            && BPCGPFEE.TX_DATA.CHG_AC_INFO.AC_TYP != '1' 
            && BPCGPFEE.TX_DATA.CHG_AC_INFO.AC_TYP != '2' 
            && BPCGPFEE.TX_DATA.CHG_AC_INFO.AC_TYP != '3') 
            || BPCGPFEE.TX_DATA.CHG_AC_INFO.CHG_CCY.trim().length() == 0) {
            BPCFFCAL.DATA.CHG_CCY = BPCTCALF.INPUT_AREA.TX_CCY;
        } else {
            BPCFFCAL.DATA.CHG_CCY = BPCGPFEE.TX_DATA.CHG_AC_INFO.CHG_CCY;
        }
        CEP.TRC(SCCGWA, "QUERY FEETY");
        CEP.TRC(SCCGWA, BPRFBAS.FEE_TYPE);
        WS_CALF_CPN = "BP-F-F-CAL-STD-FEE";
        CEP.TRC(SCCGWA, WS_CALF_CPN);
        S000_CALL_BPZFFCAL();
        if (pgmRtn) return;
    }
    public void B050_05_GET_BASIC_FEE_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFCAL);
        BPCFFCAL.DATA.TX_AC = BPCTCALF.INPUT_AREA.TX_AC;
        BPCFFCAL.DATA.OTHER_AC = BPCTCALF.INPUT_AREA.OTHER_AC;
        BPCFFCAL.DATA.FEE_CODE = BPRFBAS.KEY.FEE_CODE;
        CEP.TRC(SCCGWA, BPRFBAS.KEY.FEE_CODE);
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_CODE);
        BPCFFCAL.DATA.REG_CODE = WS_RGN_CODE;
        BPCFFCAL.DATA.EXG_TYP = BPRFBAS.EXG_TYP;
        BPCFFCAL.DATA.EXG_GROUP = "MDR";
        BPCFFCAL.DATA.CHNL_NO = " ";
        BPCFFCAL.DATA.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BPCFFCAL.DATA.FREE_FMT = WS_FREE_FMT;
        CEP.TRC(SCCGWA, WS_FREE_FMT);
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FREE_FMT);
        BPCFFCAL.DATA.CNT_CCY = BPCTCALF.INPUT_AREA.TX_CCY;
        BPCFFCAL.DATA.TX_AMT = BPCTCALF.INPUT_AREA.TX_AMT;
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.TX_CNT);
        BPCFFCAL.DATA.TX_CNT = BPCTCALF.INPUT_AREA.TX_CNT;
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA.CHG_AC_INFO.AC_TYP);
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA.CHG_AC_INFO.CHG_CCY);
        if (BPCGPFEE.TX_DATA.CHG_AC_INFO.CHG_CCY.trim().length() == 0) {
            BPCFFCAL.DATA.CHG_CCY = BPCTCALF.INPUT_AREA.TX_CCY;
        } else {
            BPCFFCAL.DATA.CHG_CCY = BPCGPFEE.TX_DATA.CHG_AC_INFO.CHG_CCY;
        }
        WS_CALF_CPN = "BP-F-F-CAL-STD-FEE";
        CEP.TRC(SCCGWA, WS_CALF_CPN);
        S000_CALL_BPZFFCAL_CN();
        if (pgmRtn) return;
    }
    public void B050_10_GET_FAV_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTIFAV);
        BPCTIFAV.INPUT_AREA.FEE_CODE = BPRFBAS.KEY.FEE_CODE;
        BPCTIFAV.INPUT_AREA.REGION_CODE = WS_RGN_CODE;
        BPCTIFAV.INPUT_AREA.CHNL = SCCGWA.COMM_AREA.CHNL;
        BPCTIFAV.INPUT_AREA.FREE_FMT = WS_FREE_FMT;
        CEP.TRC(SCCGWA, BPCTIFAV.INPUT_AREA.FREE_FMT);
        BPCTIFAV.INPUT_AREA.BASIC_FEE = BPCFFCAL.DATA.FEE_AMT;
        BPCTIFAV.INPUT_AREA.FAV_CCY = BPCTCALF.INPUT_AREA.TX_CCY;
        BPCTIFAV.INPUT_AREA.PROD_CODE = BPCTCALF.INPUT_AREA.PROD_CODE;
        BPCTIFAV.INPUT_AREA.TX_AC = BPCTCALF.INPUT_AREA.TX_AC;
        BPCTIFAV.INPUT_AREA.TX_CI = BPCTCALF.INPUT_AREA.TX_CI;
        BPCTIFAV.INPUT_AREA.TX_AMT = BPCTCALF.INPUT_AREA.TX_AMT;
        if (BPCTCALF.INPUT_AREA.OTHER_AC.trim().length() == 0) BPCTIFAV.INPUT_AREA.TX_AMT_OTHER = 0;
        else BPCTIFAV.INPUT_AREA.TX_AMT_OTHER = Double.parseDouble(BPCTCALF.INPUT_AREA.OTHER_AC);
        BPCTIFAV.INPUT_AREA.TX_CNT = BPCTCALF.INPUT_AREA.TX_CNT;
        S000_CALL_BPZFIFAV();
        if (pgmRtn) return;
    }
    public void B050_10_GET_FAV_INFO_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTIFAV);
        BPCTIFAV.INPUT_AREA.FEE_CODE = BPRFBAS.KEY.FEE_CODE;
        BPCTIFAV.INPUT_AREA.REGION_CODE = WS_RGN_CODE;
        BPCTIFAV.INPUT_AREA.CHNL = SCCGWA.COMM_AREA.CHNL;
        BPCTIFAV.INPUT_AREA.FREE_FMT = WS_FREE_FMT;
        CEP.TRC(SCCGWA, BPCTIFAV.INPUT_AREA.FREE_FMT);
        BPCTIFAV.INPUT_AREA.BASIC_FEE = BPCFFCAL.DATA.FEE_AMT;
        BPCTIFAV.INPUT_AREA.FAV_CCY = BPCTCALF.INPUT_AREA.TX_CCY;
        BPCTIFAV.INPUT_AREA.PROD_CODE = BPCTCALF.INPUT_AREA.PROD_CODE;
        BPCTIFAV.INPUT_AREA.TX_AC = BPCTCALF.INPUT_AREA.TX_AC;
        BPCTIFAV.INPUT_AREA.TX_CI = BPCTCALF.INPUT_AREA.TX_CI;
        BPCTIFAV.INPUT_AREA.TX_AMT = BPCTCALF.INPUT_AREA.TX_AMT;
        if (BPCTCALF.INPUT_AREA.OTHER_AC.trim().length() == 0) BPCTIFAV.INPUT_AREA.TX_AMT_OTHER = 0;
        else BPCTIFAV.INPUT_AREA.TX_AMT_OTHER = Double.parseDouble(BPCTCALF.INPUT_AREA.OTHER_AC);
        BPCTIFAV.INPUT_AREA.TX_CNT = BPCTCALF.INPUT_AREA.TX_CNT;
        if (WS_URMT_FLG == 'Y') {
            BPCTIFAV.INPUT_AREA.URG_RMT_FLG = 'Y';
        }
        S000_CALL_BPZFIFAV();
        if (pgmRtn) return;
    }
    public void B050_15_CAL_FEE_AMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCTIFAV.OUTPUT_AREA.FAV_AMT);
        CEP.TRC(SCCGWA, BPCTIFAV.OUTPUT_AREA.FAV_PCT);
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_AMT);
        WS_CAL_INFO.WS_F_ITM_CNT += 1;
        if (BPCTIFAV.OUTPUT_AREA.FAV_PCT != 0) {
            WS_FAV_PCT = BPCTIFAV.OUTPUT_AREA.FAV_PCT / 100;
            WS_FAV_PCT = 1 - WS_FAV_PCT;
            WS_CAL_INFO.WS_FEE_AMT = BPCFFCAL.DATA.FEE_AMT * WS_FAV_PCT;
            BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT = WS_CAL_INFO.WS_FEE_AMT;
        } else {
            WS_CAL_INFO.WS_FEE_AMT = BPCFFCAL.DATA.FEE_AMT;
            BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT = WS_CAL_INFO.WS_FEE_AMT - BPCTIFAV.OUTPUT_AREA.FAV_AMT;
        }
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT);
        if (BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT < 0) {
            BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT = 0;
        }
        CEP.TRC(SCCGWA, "MULTI-BV PRINTING");
        CEP.TRC(SCCGWA, BPRFBAS.FEE_TYPE);
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.TX_CNT);
        if (BPRFBAS.FEE_TYPE.equalsIgnoreCase("002")) {
            if (BPCTCALF.INPUT_AREA.TX_CNT != 0) {
                WS_CAL_INFO.WS_FEE_AMT = WS_CAL_INFO.WS_FEE_AMT * BPCTCALF.INPUT_AREA.TX_CNT;
                BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT * BPCTCALF.INPUT_AREA.TX_CNT;
                BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT = BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT * BPCTCALF.INPUT_AREA.TX_CNT;
            }
        }
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT);
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_CCY);
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.CHG_CCY);
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA.CHG_AC_INFO.CHG_CCY);
        if (BPCFFCAL.DATA.FEE_CCY.equalsIgnoreCase(BPCFFCAL.DATA.CHG_CCY)) {
            BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_BAS = BPCFFCAL.DATA.FEE_AMT;
            BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AMT = BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT;
        } else {
            WS_TX_AMT = BPCFFCAL.DATA.FEE_AMT;
            T000_EXCHANGE_CURRENCY();
            if (pgmRtn) return;
            BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_BAS = WS_TX_AMT;
            WS_TX_AMT = BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT;
            T000_EXCHANGE_CURRENCY();
            if (pgmRtn) return;
            BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AMT = WS_TX_AMT;
            WS_TX_EX_RATE = BPCFX.TRN_RATE;
            WS_EX_READ_SYS_TIME = BPCFX.EFF_TIME;
        }
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_BAS);
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AMT);
    }
    public void B050_15_CAL_FEE_AMT_CN() throws IOException,SQLException,Exception {
        WS_CAL_INFO.WS_F_ITM_CNT += 1;
        BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT = BPCFFCAL.DATA.FEE_AMT;
        CEP.TRC(SCCGWA, BPCTIFAV.OUTPUT_AREA.FAV_PCT);
        CEP.TRC(SCCGWA, BPCTIFAV.OUTPUT_AREA.FAV_AMT);
        if (BPCTIFAV.OUTPUT_AREA.FAV_PCT != 0) {
            if (BPCTIFAV.OUTPUT_AREA.ADJ_TYP != '2') {
                WS_CAL_INFO.WS_FEE_AMT = BPCFFCAL.DATA.FEE_AMT * BPCTIFAV.OUTPUT_AREA.FAV_PCT / 100;
                bigD = new BigDecimal(WS_CAL_INFO.WS_FEE_AMT);
                WS_CAL_INFO.WS_FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            } else {
                WS_CAL_INFO.WS_FEE_AMT = BPCFFCAL.DATA.TX_AMT * BPCTIFAV.OUTPUT_AREA.FAV_PCT / 100;
                bigD = new BigDecimal(WS_CAL_INFO.WS_FEE_AMT);
                WS_CAL_INFO.WS_FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            }
        } else {
            WS_CAL_INFO.WS_FEE_AMT = BPCTIFAV.OUTPUT_AREA.FAV_AMT;
        }
        CEP.TRC(SCCGWA, WS_CAL_INFO.WS_FEE_AMT);
        CEP.TRC(SCCGWA, BPCTIFAV.OUTPUT_AREA.ADJ_TYP);
        if (BPCTIFAV.OUTPUT_AREA.ADJ_TYP == '0') {
            BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT = BPCFFCAL.DATA.FEE_AMT + WS_CAL_INFO.WS_FEE_AMT;
        } else if (BPCTIFAV.OUTPUT_AREA.ADJ_TYP == '1') {
            BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT = BPCFFCAL.DATA.FEE_AMT - WS_CAL_INFO.WS_FEE_AMT;
        } else if (BPCTIFAV.OUTPUT_AREA.ADJ_TYP == '2') {
            BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT = WS_CAL_INFO.WS_FEE_AMT;
        }
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT);
        if (BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT < 0) {
            BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT = 0;
        }
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT);
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AMT);
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_BAS);
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AMT);
    }
    public void B050_15_2_CAL_URGENT_FEE_CN() throws IOException,SQLException,Exception {
        WS_URMT_FLG = 'Y';
        B050_10_GET_FAV_INFO_CN();
        if (pgmRtn) return;
        if (BPCTIFAV.OUTPUT_AREA.FAV_PCT != 0) {
            WS_CAL_INFO.WS_FEE_AMT = BPCFFCAL.DATA.FEE_AMT * BPCTIFAV.OUTPUT_AREA.FAV_PCT / 100;
            bigD = new BigDecimal(WS_CAL_INFO.WS_FEE_AMT);
            WS_CAL_INFO.WS_FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            BPCTIFAV.OUTPUT_AREA.FAV_AMT = WS_CAL_INFO.WS_FEE_AMT;
        }
        if (BPCTIFAV.OUTPUT_AREA.ADJ_TYP == '0') {
            BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT += BPCTIFAV.OUTPUT_AREA.FAV_AMT;
        } else {
            if (BPCTIFAV.OUTPUT_AREA.ADJ_TYP == '1') {
                BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT = BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT - BPCTIFAV.OUTPUT_AREA.FAV_AMT;
            } else {
                if (BPCTIFAV.OUTPUT_AREA.ADJ_TYP == '2') {
                    BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT = BPCTIFAV.OUTPUT_AREA.FAV_AMT;
                }
            }
        }
        if (BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT < 0) {
            BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT = 0;
        }
    }
    public void B050_20_FMT_OUTPUT_INFO() throws IOException,SQLException,Exception {
        BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_CODE = BPRFBAS.KEY.FEE_CODE;
        BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_CCY = BPCFFCAL.DATA.FEE_CCY;
        BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_BAS = BPCFFCAL.DATA.FEE_AMT;
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_AMT);
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_BAS);
        CEP.TRC(SCCGWA, "ZXC");
        if (BPCTCALF.INPUT_AREA.FUNC_CODE == 'C') {
            BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AC_TY = BPCGPFEE.TX_DATA.CHG_AC_INFO.AC_TYP;
            BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AC = BPCGPFEE.TX_DATA.CHG_AC_INFO.CHG_AC;
            BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_CCY = BPCGPFEE.TX_DATA.CHG_AC_INFO.CHG_CCY;
        }
    }
    public void B050_20_FMT_OUTPUT_INFO_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCGCFEE);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_CNT);
        for (WS_CAL_INFO.WS_F_ITM_CNT = 1; WS_CAL_INFO.WS_F_ITM_CNT <= BPCGCFEE.FEE_DATA.FEE_CNT; WS_CAL_INFO.WS_F_ITM_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CAL_INFO.WS_F_ITM_CNT);
            if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].TX_AC.equalsIgnoreCase(BPCGCFEE.FEE_DATA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AC)) {
                CEP.TRC(SCCGWA, "ASD");
                if (BPCGCFEE.FEE_DATA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].TX_AC.equalsIgnoreCase(BPCGPFEE.TX_DATA.CHG_AC_INFO.CHG_AC)) {
                    CEP.TRC(SCCGWA, "SDF");
                } else {
                    CEP.TRC(SCCGWA, "DFG");
                    if (BPCGPFEE.TX_DATA.CHG_AC_INFO.AC_TYP == '0' 
                        || BPCGPFEE.TX_DATA.CHG_AC_INFO.AC_TYP == '3') {
                        CEP.TRC(SCCGWA, "FGH");
                        BPCGCFEE.FEE_DATA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AC = BPCGPFEE.TX_DATA.CHG_AC_INFO.CHG_AC;
                        BPCGCFEE.FEE_DATA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].TX_AC = BPCGPFEE.TX_DATA.CHG_AC_INFO.CHG_AC;
                        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA.CHG_AC_INFO.CHG_AC);
                        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AC);
                        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].TX_AC);
                    } else {
                        CEP.TRC(SCCGWA, "GHJ");
                        if (BPCGPFEE.TX_DATA.CHG_AC_INFO.AC_TYP == '4' 
                            || BPCGPFEE.TX_DATA.CHG_AC_INFO.AC_TYP == '5') {
                            CEP.TRC(SCCGWA, "HJK");
                            BPCGCFEE.FEE_DATA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AC = BPCGPFEE.TX_DATA3.CARD_PSBK_NO;
                            BPCGCFEE.FEE_DATA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].TX_AC = BPCGPFEE.TX_DATA3.CARD_PSBK_NO;
                            CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA3.CARD_PSBK_NO);
                            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AC);
                            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].TX_AC);
                        }
                    }
                }
            } else {
                BPCGCFEE.FEE_DATA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].TX_AC = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AC;
            }
            BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_CODE = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_CODE;
            CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_CODE);
            BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_CCY;
            CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_CCY);
            BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_BAS = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_BAS;
            CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_BAS);
            BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT;
            CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT);
            BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AC_TY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AC_TY;
            CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AC_TY);
            BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AC = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AC;
            CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AC);
            BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_CCY = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_CCY;
            CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_CCY);
            BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_BAS = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_BAS;
            CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_BAS);
            BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AMT;
            CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AMT);
            BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].ADJ_AMT = BPCGCFEE.FEE_DATA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].ADJ_AMT;
            CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].ADJ_AMT);
        }
    }
    public void B050_25_FMT_FEE_INFO_TO_GWA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCGCFEE);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_CNT);
        BPCGCFEE.FEE_DATA.FEE_CNT += 1;
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_CNT);
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].FEE_CODE = BPRFBAS.KEY.FEE_CODE;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].FEE_CCY = BPCFFCAL.DATA.FEE_CCY;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].FEE_BAS = BPCFFCAL.DATA.FEE_AMT;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].FEE_AMT = BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].CHG_CCY = BPCFFCAL.DATA.CHG_CCY;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].CHG_BAS = BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_BAS;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].CHG_AMT = BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AMT;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].ADJ_AMT = BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AMT;
        BPCGCFEE.FEE_DATA.EX_RATE = WS_TX_EX_RATE;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].EXG_TIME = WS_EX_READ_SYS_TIME;
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_AMT);
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.CHG_AMT);
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].DER_AMT = 0;
        JIBS_NumStr = "" + 0;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].DER_AUTH = JIBS_NumStr.charAt(0);
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].EXG_DATE = 0;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].EXG_TIME = 0;
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA.CHG_AC_INFO.AC_TYP);
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].CHG_AC_TY = BPCGPFEE.TX_DATA.CHG_AC_INFO.AC_TYP;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].CHG_AC = BPCGPFEE.TX_DATA.CHG_AC_INFO.CHG_AC;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].PSBK_NO = BPCGPFEE.TX_DATA.CHG_AC_INFO.PSBK_NO;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].RGN_NO = WS_RGN_CODE;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].FREE_FMT = WS_FREE_FMT;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].TX_AC = BPCGPFEE.TX_DATA.CHG_AC_INFO.CHG_AC;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].TX_CCY = BPCTCALF.INPUT_AREA.TX_CCY;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].TX_AMT = BPCTCALF.INPUT_AREA.TX_AMT;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].TX_CNT = BPCTCALF.INPUT_AREA.TX_CNT;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].PROD_CD = BPCTCALF.INPUT_AREA.PROD_CODE;
    }
    public void B050_25_FMT_FEE_INFO_TO_GWA_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCGCFEE);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_CNT);
        BPCGCFEE.FEE_DATA.FEE_CNT += 1;
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_CNT);
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.CHG_CCY);
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].FEE_CODE = BPRFBAS.KEY.FEE_CODE;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].FEE_CCY = BPCFFCAL.DATA.FEE_CCY;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].FEE_BAS = BPCFFCAL.DATA.FEE_AMT;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].FEE_AMT = BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].CHG_CCY = BPCFFCAL.DATA.CHG_CCY;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].CHG_BAS = BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_BAS;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].CHG_AMT = BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AMT;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].ADJ_AMT = BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AMT;
        BPCGCFEE.FEE_DATA.EX_RATE = WS_TX_EX_RATE;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].EXG_TIME = WS_EX_READ_SYS_TIME;
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_AMT);
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.CHG_AMT);
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].DER_AMT = 0;
        JIBS_NumStr = "" + 0;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].DER_AUTH = JIBS_NumStr.charAt(0);
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].EXG_DATE = 0;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].EXG_TIME = 0;
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA.CHG_AC_INFO.AC_TYP);
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].CHG_AC_TY = BPCGPFEE.TX_DATA.CHG_AC_INFO.AC_TYP;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].CHG_AC = BPCGPFEE.TX_DATA.CHG_AC_INFO.CHG_AC;
        if (BPCGPFEE.TX_DATA.CHG_AC_INFO.AC_TYP == '4' 
            || BPCGPFEE.TX_DATA.CHG_AC_INFO.AC_TYP == '5') {
            BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].CHG_AC = BPCGPFEE.TX_DATA3.CARD_PSBK_NO;
        }
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].PSBK_NO = BPCGPFEE.TX_DATA.CHG_AC_INFO.PSBK_NO;
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].RGN_NO);
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].FREE_FMT = WS_FREE_FMT;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].TX_AC = BPCTCALF.INPUT_AREA.TX_AC;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].TX_CCY = BPCTCALF.INPUT_AREA.TX_CCY;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].TX_AMT = BPCTCALF.INPUT_AREA.TX_AMT;
        BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].TX_CNT = BPCTCALF.INPUT_AREA.TX_CNT;
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.PROD_CODE1);
        if (BPCTCALF.INPUT_AREA.PROD_CODE1.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = BPCTCALF.INPUT_AREA.PROD_CODE1;
            S000_CALL_BPZPQPRD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.PROD_CODE1);
            BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].PROD_CD = BPCTCALF.INPUT_AREA.PROD_CODE1;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_PD_CD_MUST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].PROD_CD);
        if (BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].CHG_AC.trim().length() > 0) {
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].CHG_AC);
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].CHG_AC;
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_PROD_CD);
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].PROD_CD);
        }
        if (BPRFBAS.AMO_FLG == '1') {
            BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].AMO_FLG = '1';
            BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].AMO_STDT = BPCTCALF.INPUT_AREA.AMO_STDT_IN;
            BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].AMO_EDDT = BPCTCALF.INPUT_AREA.AMO_EDDT_IN;
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_30_COMP_FEE_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.CHG_CCY);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].CHG_CCY);
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_AMT);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].CHG_BAS);
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.CHG_AMT);
        CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].CHG_AMT);
        if (!BPCFFCAL.DATA.CHG_CCY.equalsIgnoreCase(BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].CHG_CCY) 
            || BPCFFCAL.DATA.FEE_AMT != BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].CHG_BAS 
            || BPCFFCAL.DATA.CHG_AMT != BPCGCFEE.FEE_DATA.FEE_INFO[BPCGCFEE.FEE_DATA.FEE_CNT-1].CHG_AMT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAL_FEE_ITEM_CHANGED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_35_FMT_PFEE_INFO_TO_GWA() throws IOException,SQLException,Exception {
    }
    public void T000_GET_BVF_FREE_FMT_INFO() throws IOException,SQLException,Exception {
        WS_FREE_FMT = " ";
        WS_FMT_FLG = 'N';
        CEP.TRC(SCCGWA, "SHOW POINT-LEN");
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.POINT_LEN);
        if (BPCTCALF.INPUT_AREA.POINT_LEN > 0) {
            LK_REC = IBS.CLS2CPY(SCCGWA, BPCTCALF.INPUT_AREA.POINTER);
            if (LK_REC == null) LK_REC = "";
            JIBS_tmp_int = LK_REC.length();
            for (int i=0;i<10240-JIBS_tmp_int;i++) LK_REC += " ";
            WS_FMT_TXT = LK_REC.substring(0, BPCTCALF.INPUT_AREA.POINT_LEN);
            CEP.TRC(SCCGWA, WS_FMT_TXT);
            IBS.init(SCCGWA, BPCFEEUC);
            IBS.init(SCCGWA, BPCBVCHG);
            BPCBVCHG.INFO.FUNC = '5';
            BPCBVCHG.INFO.OPT = '0';
            BPCBVCHG.INFO.POINTER = BPCFEEUC;
            CEP.TRC(SCCGWA, "STARTBR BPZBVCHG");
            S000_CALL_BPZBVCHG();
            if (pgmRtn) return;
            BPCBVCHG.INFO.FUNC = '5';
            BPCBVCHG.INFO.OPT = '1';
            S000_CALL_BPZBVCHG();
            if (pgmRtn) return;
            while (BPCBVCHG.RETURN_INFO != 'N' 
                && WS_FMT_FLG != 'Y') {
                CEP.TRC(SCCGWA, WS_FMT_TXT);
                CEP.TRC(SCCGWA, BPCFEEUC.TXT_DATA.TXT);
                CEP.TRC(SCCGWA, BPCFEEUC.KEY.FREE_FMT_KEY);
                if (BPCFEEUC.TXT_DATA.TXT.equalsIgnoreCase(WS_FMT_TXT)) {
                    WS_FREE_FMT = BPCFEEUC.KEY.FREE_FMT_KEY;
                    WS_FMT_FLG = 'Y';
                }
                S000_CALL_BPZBVCHG();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCBVCHG.RETURN_INFO);
            }
            BPCBVCHG.INFO.OPT = '2';
            S000_CALL_BPZBVCHG();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_FREE_FMT);
    }
    public void T000_GET_UI_INFO() throws IOException,SQLException,Exception {
        BPCFFCAL.DATA.POINT_LEN = BPCTCALF.INPUT_AREA.POINT_LEN;
        BPCFFCAL.DATA.POINTER = BPCTCALF.INPUT_AREA.POINTER;
        S000_CALL_BPZUICHG();
        if (pgmRtn) return;
        BPCFFCAL.DATA.TX_AC = BPCUIFEE.CHG_AC;
        BPCFFCAL.DATA.CHG_CCY = BPCUIFEE.CHG_CCY;
        BPCFFCAL.DATA.CHG_AMT = BPCUIFEE.CHG_AMT;
    }
    public void B060_EXCHANGE_CURRENCY_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT);
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AMT);
        B061_EXCHANGE_FBAS_CURRENCY_CN();
        if (pgmRtn) return;
        if (BPCPQCMZ.CMZ_FLG1 != '1') {
            if (BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT > WS_MAX_AMT) {
                BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT = WS_MAX_AMT;
            }
            if (BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT < WS_MIN_AMT) {
                BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT = WS_MIN_AMT;
            }
        } else {
            if (BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT < 0) {
                BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT = 0;
            }
        }
        BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_BAS = BPCFFCAL.DATA.FEE_AMT;
        BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AMT = BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT;
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT);
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AMT);
    }
    public void B061_EXCHANGE_FBAS_CURRENCY_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFBAS.EXG_TYP);
        CEP.TRC(SCCGWA, BPRFBAS.UP_AMT);
        CEP.TRC(SCCGWA, BPRFBAS.DWN_AMT);
        WS_FEE_EXG_TYP = BPRFBAS.EXG_TYP;
        WS_UP_AMT = BPRFBAS.UP_AMT;
        WS_MAX_AMT = BPRFBAS.UP_AMT;
        WS_DWN_AMT = BPRFBAS.DWN_AMT;
        WS_MIN_AMT = BPRFBAS.DWN_AMT;
        if (WS_FEE_EXG_TYP == K_LOCAL_TYPE) {
            WS_MID_CCY = K_LOCAL_CCY;
        } else {
            WS_MID_CCY = K_ABROAD_CCY;
        }
        IBS.init(SCCGWA, BPCFX);
        BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCFX.FUNC = '3';
        BPCFX.EXR_TYPE = "MDR";
        if ((WS_DWN_AMT != 0) 
            && (!BPCFFCAL.DATA.CHG_CCY.equalsIgnoreCase(WS_MID_CCY) 
            && !BPCFFCAL.DATA.FEE_CCY.equalsIgnoreCase("156"))) {
            CEP.TRC(SCCGWA, "CHECK FBAS MIN AMT");
            BPCFX.BUY_CCY = BPCFFCAL.DATA.CHG_CCY;
            BPCFX.SELL_CCY = WS_MID_CCY;
            BPCFX.SELL_AMT = WS_DWN_AMT;
            S000_CALL_BPZSFX();
            if (pgmRtn) return;
            WS_MIN_AMT = BPCFX.BUY_AMT;
        }
        if ((WS_UP_AMT != K_MAX_AMT) 
            && (!BPCFFCAL.DATA.CHG_CCY.equalsIgnoreCase(WS_MID_CCY) 
            && !BPCFFCAL.DATA.FEE_CCY.equalsIgnoreCase("156"))) {
            CEP.TRC(SCCGWA, "CHECK FBAS MAX AMT");
            BPCFX.BUY_AMT = 0;
            BPCFX.BUY_CCY = BPCFFCAL.DATA.CHG_CCY;
            BPCFX.SELL_CCY = WS_MID_CCY;
            BPCFX.SELL_AMT = WS_UP_AMT;
            S000_CALL_BPZSFX();
            if (pgmRtn) return;
            WS_MAX_AMT = BPCFX.BUY_AMT;
        }
        CEP.TRC(SCCGWA, WS_MIN_AMT);
        CEP.TRC(SCCGWA, WS_MAX_AMT);
    }
    public void T000_EXCHANGE_CURRENCY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CMZ_EX_FLG);
        if (WS_CMZ_EX_FLG == 'Y') {
            WS_FEE_CCY = BPCPQCMZ.CMZ_CCY;
            WS_CHG_CCY = BPCFFCAL.DATA.FEE_CCY;
        } else {
            WS_FEE_CCY = BPCFFCAL.DATA.FEE_CCY;
            WS_CHG_CCY = BPCFFCAL.DATA.CHG_CCY;
        }
        CEP.TRC(SCCGWA, WS_FEE_CCY);
        CEP.TRC(SCCGWA, WS_CHG_CCY);
        CEP.TRC(SCCGWA, WS_TX_AMT);
        if (WS_TX_AMT != 0) {
            R000_TRANS_BPCEX_IN();
            if (pgmRtn) return;
        }
        WS_CMZ_EX_FLG = ' ';
        WS_TX_AMT = BPCFX.BUY_AMT;
        CEP.TRC(SCCGWA, WS_TX_AMT);
        CEP.TRC(SCCGWA, BPCFX.BUY_AMT);
    }
    public void R000_TRANS_BPCEX_IN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFX);
        BPCFX.FUNC = '3';
        BPCFX.EXR_TYPE = "MDR";
        BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCFX.CI_NO = BPCTCALF.INPUT_AREA.TX_CI;
        CEP.TRC(SCCGWA, WS_TX_AMT);
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_CCY);
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.CHG_CCY);
        CEP.TRC(SCCGWA, BPRFBAS.CHG_TYPE);
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA.CHG_AC_INFO.AC_TYP);
        if (BPCGPFEE.TX_DATA.CHG_AC_INFO.AC_TYP == '1') {
            BPCFX.B_CASH_FLG = 'Y';
        } else {
            BPCFX.B_CASH_FLG = 'N';
        }
        if (BPRFBAS.CHG_TYPE == '1' 
            || WS_CMZ_EX_FLG == 'Y') {
            BPCFX.BUY_AMT = WS_TX_AMT;
            BPCFX.BUY_CCY = WS_FEE_CCY;
            BPCFX.SELL_CCY = WS_CHG_CCY;
            S000_CALL_BPZSFX();
            if (pgmRtn) return;
            WS_TX_AMT = BPCFX.SELL_AMT;
        } else {
            BPCFX.SELL_AMT = WS_TX_AMT;
            BPCFX.SELL_CCY = WS_FEE_CCY;
            BPCFX.BUY_CCY = WS_CHG_CCY;
            S000_CALL_BPZSFX();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCFX.BUY_AMT);
            WS_TX_AMT = BPCFX.BUY_AMT;
        }
        WS_TX_EX_RATE = BPCFX.TRN_RATE;
        WS_EX_READ_SYS_TIME = BPCFX.EFF_TIME;
    }
    public void R000_TRANS_BPCEX_OUT() throws IOException,SQLException,Exception {
        WS_TX_AMT = BPCFX.BUY_AMT;
    }
    public void R000_GET_REG_CODE_BY_TYPE() throws IOException,SQLException,Exception {
    }
    public void R000_GET_FCOM_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFPARM);
        BPCFPARM.RETURN_INFO = 'F';
        BPCFPARM.INFO.FUNC = '3';
        BPCFPARM.INFO.TYPE = "FCOM ";
        BPCFPARM.INFO.POINTER = BPVFCOM;
        S000_CALL_BPZFPARM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CHECK-FCOM");
        CEP.TRC(SCCGWA, BPVFCOM.KEY.FEE_CODE);
    }
    public void R000_GET_FPRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFPARM);
        BPCFPARM.RETURN_INFO = 'F';
        BPCFPARM.INFO.FUNC = '3';
        BPCFPARM.INFO.TYPE = "FPRD ";
        BPCFPARM.INFO.POINTER = BPVFPRD;
        S000_CALL_BPZFPARM();
        if (pgmRtn) return;
    }
    public void R000_GET_RESULT_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRDAMT);
        BPCRDAMT.CCY = BPCFFCAL.DATA.CHG_CCY;
        BPCRDAMT.AMT = BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT;
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT);
        S000_CALL_BPZRDAMT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRDAMT.RESULT_AMT);
        BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT = BPCRDAMT.RESULT_AMT;
        IBS.init(SCCGWA, BPCRDAMT);
        BPCRDAMT.CCY = BPCFFCAL.DATA.CHG_CCY;
        BPCRDAMT.AMT = BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AMT;
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].FEE_AMT);
        S000_CALL_BPZRDAMT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCRDAMT.RESULT_AMT);
        BPCTCALF.OUTPUT_AREA.FEE_INFO[WS_CAL_INFO.WS_F_ITM_CNT-1].CHG_AMT = BPCRDAMT.RESULT_AMT;
    }
    public void S000_CALL_BPZRDAMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-ROUND-AMT", BPCRDAMT);
        if (BPCRDAMT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRDAMT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFX.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZBVCHG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_Z_CERT_CHARGE, BPCBVCHG);
    }
    public void S000_CALL_BPZUICHG() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_BPZFPARM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_FEE_PARM_MAIN, BPCFPARM);
    }
    public void S000_CALL_BPZTFBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_COM_FEE_INFO, BPCTFBAS);
    }
    public void S000_CALL_BPZFFCAL() throws IOException,SQLException,Exception {
        BPCFFCAL.DATA.POINT_LEN = BPCTCALF.INPUT_AREA.POINT_LEN;
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.POINT_LEN);
        IBS.CALLCPN(SCCGWA, WS_CALF_CPN, BPCFFCAL);
    }
    public void S000_CALL_BPZFFCAL_CN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, WS_CALF_CPN, BPCFFCAL);
    }
    public void S000_CALL_BPZFIFAV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_FEE_CAL_IFAV, BPCTIFAV);
        CEP.TRC(SCCGWA, BPCTIFAV.RC);
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_FEE_BPZPRMM, BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC.RC_RTNCODE);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            WS_CALF_CPN = BPRPRMT.DAT_TXT.FILLER;
        } else {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "TYJ713");
        CEP.TRC(SCCGWA, BPCPQPRD.PRDT_CODE);
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        CEP.TRC(SCCGWA, BPCPQPRD.RC.RC_CODE);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = BPRPRMT;
        IBS.CALLCPN(SCCGWA, CPN_R_PRMR, BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (BPCPRMR.FILLER == null) BPCPRMR.FILLER = "";
            JIBS_tmp_int = BPCPRMR.FILLER.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) BPCPRMR.FILLER += " ";
            WS_CALF_CPN = BPCPRMR.FILLER.substring(0, 20);
            if (WS_CALF_CPN.trim().length() == 0) {
                if (BPRPRMT.DAT_TXT.FILLER == null) BPRPRMT.DAT_TXT.FILLER = "";
                JIBS_tmp_int = BPRPRMT.DAT_TXT.FILLER.length();
                for (int i=0;i<9500-JIBS_tmp_int;i++) BPRPRMT.DAT_TXT.FILLER += " ";
                WS_CALF_CPN = BPRPRMT.DAT_TXT.FILLER.substring(0, 20);
            }
        }
    }
    public void S000_CALL_BPZPQCMZ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_AMO_CHECK, BPCPQCMZ);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTCALF.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCTCALF = ");
            CEP.TRC(SCCGWA, BPCTCALF);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
