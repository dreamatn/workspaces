package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.DC.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1153 {
    int JIBS_tmp_int;
    BigDecimal bigD;
    String K_OUTPUT_FMT = "BP077";
    String CPN_BANK_MAINTAIN = "BP-S-BANK-MAINTAIN  ";
    String CPN_F_F_CAL_FEE = "BP-F-F-CAL-FEE      ";
    String CPN_F_F_TX_INFO = "BP-F-F-TX-INFO      ";
    String CPN_F_F_RM_TX = "BP-RM-CREATE-RECORD ";
    String CPN_F_F_CON_CHG_INFO = "BP-F-F-CON-CHG-INFO ";
    String CPN_R_FEE_PARM_MAIN = "BP-F-F-MAINTAIN-PARM";
    String CPN_R_FEE_CAL_FEE = "BP-F-F-CAL-STD-FEE  ";
    String CPN_R_FEE_CAL_IFAV = "BP-F-F-CAL-IFAV     ";
    String CPN_F_COM_FEE_INFO = "BP-F-T-FEE-INFO     ";
    String CPN_F_CAL_FEE = "BP-F-F-CAL-FEE      ";
    String K_PARM_TYPE_FBAS = "FBAS ";
    String K_PARM_TYPE_FMSK = "FMSK ";
    String CPN_EXG_PROC = "BP-EX";
    String CPN_S_AMO_CHECK = "BP-P-AMO-CHK        ";
    char K_LOCAL_TYPE = '0';
    String K_LOCAL_CCY = "156";
    String K_ABROAD_CCY = "840";
    double K_MAX_AMT = 99999999999999.99;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_ITM = 0;
    double WS_FEE_AMT = 0;
    double WS_CAL_FAV_AMT = 0;
    short WS_CNT = 0;
    String WS_REG_CODE = " ";
    short WS_F_ITM_CNT = 0;
    double WS_FAV_PCT = 0;
    char WS_FEE_EXG_TYP = ' ';
    double WS_UP_AMT = 0;
    double WS_DWN_AMT = 0;
    double WS_MIN_AMT = 0;
    double WS_MAX_AMT = 0;
    String WS_MID_CCY = " ";
    String WS_FEE_CCY = " ";
    String WS_CHG_CCY = " ";
    double WS_CMZ_AMT = 0;
    double WS_TX_AMT = 0;
    char WS_URMT_FLG = ' ';
    char WS_USING_CMZ_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCFX BPCFX = new BPCFX();
    BPCFFCON BPCFFCON = new BPCFFCON();
    BPCONESF BPCONESF = new BPCONESF();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCFPARM BPCFPARM = new BPCFPARM();
    BPCTIFAV BPCTIFAV = new BPCTIFAV();
    BPCFFCAL BPCFFCAL = new BPCFFCAL();
    BPRFBAS BPRFBAS = new BPRFBAS();
    BPCTFBAS BPCTFBAS = new BPCTFBAS();
    BPVFMSK BPVFMSK = new BPVFMSK();
    BPVFCOM BPVFCOM = new BPVFCOM();
    BPVFSTD BPVFSTD = new BPVFSTD();
    BPCPQCMZ BPCPQCMZ = new BPCPQCMZ();
    DCCPACTY DCCPACTY = new DCCPACTY();
    SCCGWA SCCGWA;
    BPCGCFEE BPCGCFEE;
    BPCGPFEE BPCGPFEE;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPB1153_AWA_1153 BPB1153_AWA_1153;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCGCFEE BPCGCFEE;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1153 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCONESF);
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1153_AWA_1153>");
        BPB1153_AWA_1153 = (BPB1153_AWA_1153) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            WS_URMT_FLG = 'N';
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B015_GET_FEE_BASIC_INFO();
        B030_GET_BASIC_FEE();
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B055_01_FEE_CMZ_INFO();
            if (BPCPQCMZ.DATE_FLG != 'Y') {
                B035_GET_FAV_INFO_CN();
                B040_CAL_FEE_AMT_CN();
            } else {
                B055_CAL_CMZ_FEE_AMT_CN();
            }
            B045_CAL_URGENT_FEE_AMT_CN();
            B060_EXCHANGE_CURRENCY_CN();
        } else {
            B035_GET_FAV_INFO();
            B040_CAL_FEE_AMT();
        }
        B090_OUTPUT_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1153_AWA_1153.AC_TYP);
        CEP.TRC(SCCGWA, BPB1153_AWA_1153.AC_NO);
        if (BPB1153_AWA_1153.AC_TYP == '0' 
            && BPB1153_AWA_1153.AC_NO.trim().length() > 0) {
            IBS.init(SCCGWA, DCCPACTY);
            DCCPACTY.INPUT.FUNC = '3';
            DCCPACTY.INPUT.AC = BPB1153_AWA_1153.AC_NO;
            DCCPACTY.INPUT.CCY = BPB1153_AWA_1153.CFEE_CCY;
            DCCPACTY.INPUT.CCY_TYPE = '1';
            S000_CALL_DCZPACTY();
            CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_AC);
            CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.VIA_AC);
            CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.SUB_AC);
            CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_DETL);
            CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_TYPE);
            if (DCCPACTY.OUTPUT.AC_TYPE != 'D') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_CHGTYPE_NOTMATCH;
                S000_ERR_MSG_PROC();
            }
            if (DCCPACTY.OUTPUT.STD_AC.trim().length() > 0) {
                BPB1153_AWA_1153.AC_NO = DCCPACTY.OUTPUT.STD_AC;
            }
        }
    }
    public void B015_GET_FEE_BASIC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFBAS);
        IBS.init(SCCGWA, BPCTFBAS);
        BPCTFBAS.INFO.POINTER = BPRFBAS;
        BPCTFBAS.INFO.REC_LEN = 312;
        BPRFBAS.KEY.FEE_CODE = BPB1153_AWA_1153.FEE_CODE;
        BPCTFBAS.INFO.FUNC = 'Q';
        CEP.TRC(SCCGWA, BPB1153_AWA_1153.FEE_CODE);
        CEP.TRC(SCCGWA, BPRFBAS.KEY);
        CEP.TRC(SCCGWA, BPRFBAS.FEE_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        S000_CALL_BPZTFBAS();
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, BPCTFBAS);
        CEP.TRC(SCCGWA, BPRFBAS);
        if (BPCTFBAS.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "ENTER NOTFND ");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CODE_NOTFND;
            S000_ERR_MSG_PROC();
        }
        if (BPRFBAS.START_DATE > SCCGWA.COMM_AREA.AC_DATE 
            || BPRFBAS.END_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FE_C_PARM_NOTFND;
            S000_ERR_MSG_PROC();
        }
        WS_CNT = BPRFBAS.FEE_NO;
        if (BPRFBAS.REG_TYPE.equalsIgnoreCase("00")) {
            WS_REG_CODE = " ";
        } else {
            if (BPRFBAS.REG_TYPE.equalsIgnoreCase("99")) {
                WS_REG_CODE = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                JIBS_tmp_int = WS_REG_CODE.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) WS_REG_CODE = "0" + WS_REG_CODE;
            } else {
                R000_GET_REG_CODE_BY_TYPE();
            }
        }
    }
    public void B020_GET_FEE_COM_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPVFCOM);
        IBS.init(SCCGWA, BPCFPARM);
        BPCFPARM.INFO.TYPE = "FCOM ";
        BPVFCOM.KEY.FEE_CODE = BPRFBAS.KEY.FEE_CODE;
        BPVFCOM.KEY.CHN_NO = SCCGWA.COMM_AREA.CHNL;
        BPVFCOM.KEY.FREE_FMT = " ";
        BPCFPARM.INFO.POINTER = BPVFCOM;
        BPCFPARM.INFO.FUNC = '3';
        CEP.TRC(SCCGWA, BPVFCOM.KEY);
        S000_CALL_BPZFPARM();
        if (BPCFPARM.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FCOM_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_GET_BASIC_FEE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPVFSTD);
        IBS.init(SCCGWA, BPCFPARM);
        CEP.TRC(SCCGWA, BPB1153_AWA_1153.FEE_AMT);
        if (BPB1153_AWA_1153.FEE_AMT != 0) {
            BPVFSTD.KEY.FEE_CODE = BPRFBAS.KEY.FEE_CODE;
            BPVFSTD.KEY.REG_CODE = " ";
            BPVFSTD.KEY.CHN_NO = " ";
            BPVFSTD.KEY.FREE_FMT = " ";
            BPVFSTD.KEY.REF_CCY = BPB1153_AWA_1153.CFEE_CCY;
            BPCFPARM.RETURN_INFO = 'F';
            BPCFPARM.INFO.FUNC = '3';
            BPCFPARM.INFO.TYPE = "FSTD ";
            BPCFPARM.INFO.POINTER = BPVFSTD;
            S000_CALL_BPZFPARM();
            if (BPCFPARM.RETURN_INFO == 'N') {
                CEP.TRC(SCCGWA, "FFFFFFFFFFFFFFF");
                CEP.TRC(SCCGWA, BPCRBANK.LOC_CCY1);
                BPVFSTD.KEY.FEE_CODE = BPRFBAS.KEY.FEE_CODE;
                BPVFSTD.KEY.REG_CODE = " ";
                BPVFSTD.KEY.CHN_NO = " ";
                BPVFSTD.KEY.FREE_FMT = " ";
                BPVFSTD.KEY.REF_CCY = BPCRBANK.LOC_CCY1;
                BPCFPARM.RETURN_INFO = 'F';
                BPCFPARM.INFO.FUNC = '3';
                BPCFPARM.INFO.TYPE = "FSTD ";
                BPCFPARM.INFO.POINTER = BPVFSTD;
                S000_CALL_BPZFPARM();
                if (BPCFPARM.RETURN_INFO == 'N') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSTD_NOTFND;
                    S000_ERR_MSG_PROC();
                } else {
                    CEP.TRC(SCCGWA, "GGGGGGGGGGGGGGG");
                    CEP.TRC(SCCGWA, BPB1153_AWA_1153.CFEE_CCY);
                    if (!BPVFSTD.VAL.FEE_CCY.equalsIgnoreCase(BPB1153_AWA_1153.CFEE_CCY)) {
                        IBS.init(SCCGWA, BPCFX);
                        BPCFX.EXR_TYPE = BPRFBAS.RATE_GROUP;
                        BPCFX.EXR_TYPE = BPRFBAS.RATE_GROUP;
                        BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                        BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                        CEP.TRC(SCCGWA, BPCRBANK.LOC_CCY1);
                        BPCFX.BUY_CCY = BPVFSTD.VAL.FEE_CCY;
                        CEP.TRC(SCCGWA, BPCFX.BUY_CCY);
                        BPCFX.SELL_AMT = BPB1153_AWA_1153.FEE_AMT;
                        BPCFX.SELL_CCY = BPB1153_AWA_1153.CFEE_CCY;
                        CEP.TRC(SCCGWA, BPB1153_AWA_1153.AC_TYP);
                        if (BPB1153_AWA_1153.AC_TYP == '1') {
                            BPCFX.B_CASH_FLG = 'Y';
                        } else {
                            BPCFX.B_CASH_FLG = 'N';
                        }
                        CEP.TRC(SCCGWA, BPCFX.SELL_CCY);
                        CEP.TRC(SCCGWA, BPCFX.BUY_AMT);
                        CEP.TRC(SCCGWA, BPVFSTD.VAL.START_AMT);
                        S000_CALL_BPZSFX();
                        if (BPCFX.BUY_AMT < BPVFSTD.VAL.START_AMT) {
                            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TX_LESS_START_AMT;
                            S000_ERR_MSG_PROC();
                        }
                    }
                }
            } else {
                CEP.TRC(SCCGWA, BPB1153_AWA_1153.FEE_AMT);
                CEP.TRC(SCCGWA, BPVFSTD.VAL.START_AMT);
                if (BPB1153_AWA_1153.FEE_AMT < BPVFSTD.VAL.START_AMT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TX_LESS_START_AMT;
                    S000_ERR_MSG_PROC();
                }
            }
        } else {
        }
        IBS.init(SCCGWA, BPCFFCAL);
        BPCFFCAL.DATA.FEE_CODE = BPRFBAS.KEY.FEE_CODE;
        BPCFFCAL.DATA.CHNL_NO = BPB1153_AWA_1153.CHNL;
        BPCFFCAL.DATA.CNT_CCY = BPB1153_AWA_1153.CFEE_CCY;
        BPCFFCAL.DATA.TX_AMT = BPB1153_AWA_1153.FEE_AMT;
        BPCFFCAL.DATA.TX_CNT = BPB1153_AWA_1153.ACC_CNT;
        BPCFFCAL.DATA.CHG_CCY = BPB1153_AWA_1153.CHG_CCY;
        BPCFFCAL.DATA.FEE_CCY = BPB1153_AWA_1153.CFEE_CCY;
        BPCFFCAL.DATA.EXG_GROUP = BPRFBAS.RATE_GROUP;
        CEP.TRC(SCCGWA, "1153TEST01");
        BPCFFCAL.DATA.STD_REF_FLG = BPB1153_AWA_1153.REF_FLG;
        BPCFFCAL.DATA.CAL_TYPE = BPB1153_AWA_1153.CAL_TYPE;
        BPCFFCAL.DATA.AGGR_TYPE = BPB1153_AWA_1153.AGG_TYPE;
        CEP.TRC(SCCGWA, "1153TEST03");
        CEP.TRC(SCCGWA, BPB1153_AWA_1153.REF_FLG);
        CEP.TRC(SCCGWA, BPB1153_AWA_1153.CAL_TYPE);
        CEP.TRC(SCCGWA, BPB1153_AWA_1153.AGG_TYPE);
        CEP.TRC(SCCGWA, BPB1153_AWA_1153.REF_DATA[1-1].UP_AMT);
        CEP.TRC(SCCGWA, BPB1153_AWA_1153.REF_DATA[1-1].UP_PCT);
        CEP.TRC(SCCGWA, BPB1153_AWA_1153.REF_DATA[1-1].AGG_MTH);
        CEP.TRC(SCCGWA, BPB1153_AWA_1153.REF_DATA[1-1].RFEE_AMT);
        CEP.TRC(SCCGWA, BPB1153_AWA_1153.REF_DATA[1-1].RFEE_PER);
        BPCFFCAL.DATA.FEE_DATA[1-1].UP_AMT = BPB1153_AWA_1153.REF_DATA[1-1].UP_AMT;
        BPCFFCAL.DATA.FEE_DATA[1-1].UP_CNT = (short) BPB1153_AWA_1153.REF_DATA[1-1].UP_PCT;
        BPCFFCAL.DATA.FEE_DATA[1-1].AGG_MTH = BPB1153_AWA_1153.REF_DATA[1-1].AGG_MTH;
        BPCFFCAL.DATA.FEE_DATA[1-1].RFEE_AMT = BPB1153_AWA_1153.REF_DATA[1-1].RFEE_AMT;
        BPCFFCAL.DATA.FEE_DATA[1-1].RFEE_PER = BPB1153_AWA_1153.REF_DATA[1-1].RFEE_PER;
        CEP.TRC(SCCGWA, "1153TEST02");
        BPCFFCAL.DATA.FEE_DATA[2-1].UP_AMT = BPB1153_AWA_1153.REF_DATA[2-1].UP_AMT;
        BPCFFCAL.DATA.FEE_DATA[2-1].UP_CNT = (short) BPB1153_AWA_1153.REF_DATA[2-1].UP_PCT;
        BPCFFCAL.DATA.FEE_DATA[2-1].AGG_MTH = BPB1153_AWA_1153.REF_DATA[2-1].AGG_MTH;
        BPCFFCAL.DATA.FEE_DATA[2-1].RFEE_AMT = BPB1153_AWA_1153.REF_DATA[2-1].RFEE_AMT;
        BPCFFCAL.DATA.FEE_DATA[2-1].RFEE_PER = BPB1153_AWA_1153.REF_DATA[2-1].RFEE_PER;
        BPCFFCAL.DATA.FEE_DATA[3-1].UP_AMT = BPB1153_AWA_1153.REF_DATA[3-1].UP_AMT;
        BPCFFCAL.DATA.FEE_DATA[3-1].UP_CNT = (short) BPB1153_AWA_1153.REF_DATA[3-1].UP_PCT;
        BPCFFCAL.DATA.FEE_DATA[3-1].AGG_MTH = BPB1153_AWA_1153.REF_DATA[3-1].AGG_MTH;
        BPCFFCAL.DATA.FEE_DATA[3-1].RFEE_AMT = BPB1153_AWA_1153.REF_DATA[3-1].RFEE_AMT;
        BPCFFCAL.DATA.FEE_DATA[3-1].RFEE_PER = BPB1153_AWA_1153.REF_DATA[3-1].RFEE_PER;
        BPCFFCAL.DATA.FEE_DATA[4-1].UP_AMT = BPB1153_AWA_1153.REF_DATA[4-1].UP_AMT;
        BPCFFCAL.DATA.FEE_DATA[4-1].UP_CNT = (short) BPB1153_AWA_1153.REF_DATA[4-1].UP_PCT;
        BPCFFCAL.DATA.FEE_DATA[4-1].AGG_MTH = BPB1153_AWA_1153.REF_DATA[4-1].AGG_MTH;
        BPCFFCAL.DATA.FEE_DATA[4-1].RFEE_AMT = BPB1153_AWA_1153.REF_DATA[4-1].RFEE_AMT;
        BPCFFCAL.DATA.FEE_DATA[4-1].RFEE_PER = BPB1153_AWA_1153.REF_DATA[4-1].RFEE_PER;
        BPCFFCAL.DATA.FEE_DATA[5-1].UP_AMT = BPB1153_AWA_1153.REF_DATA[5-1].UP_AMT;
        BPCFFCAL.DATA.FEE_DATA[5-1].UP_CNT = (short) BPB1153_AWA_1153.REF_DATA[5-1].UP_PCT;
        BPCFFCAL.DATA.FEE_DATA[5-1].AGG_MTH = BPB1153_AWA_1153.REF_DATA[5-1].AGG_MTH;
        BPCFFCAL.DATA.FEE_DATA[5-1].RFEE_AMT = BPB1153_AWA_1153.REF_DATA[5-1].RFEE_AMT;
        BPCFFCAL.DATA.FEE_DATA[5-1].RFEE_PER = BPB1153_AWA_1153.REF_DATA[5-1].RFEE_PER;
        S000_CALL_BPZFFCAL();
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_AMT);
    }
    public void B035_GET_FAV_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTIFAV);
        BPCTIFAV.INPUT_AREA.FEE_CODE = BPRFBAS.KEY.FEE_CODE;
        BPCTIFAV.INPUT_AREA.CHNL = SCCGWA.COMM_AREA.CHNL;
        BPCTIFAV.INPUT_AREA.FREE_FMT = " ";
        BPCTIFAV.INPUT_AREA.BASIC_FEE = BPCFFCAL.DATA.FEE_AMT;
        BPCTIFAV.INPUT_AREA.FAV_CCY = BPB1153_AWA_1153.CFEE_CCY;
        BPCTIFAV.INPUT_AREA.PROD_CODE = BPB1153_AWA_1153.PRD_CODE;
        BPCTIFAV.INPUT_AREA.TX_AC = BPB1153_AWA_1153.AC_NO;
        BPCTIFAV.INPUT_AREA.TX_CI = BPB1153_AWA_1153.CI_NO;
        BPCTIFAV.INPUT_AREA.TX_AMT = BPB1153_AWA_1153.FEE_AMT;
        BPCTIFAV.INPUT_AREA.TX_CNT = BPB1153_AWA_1153.ACC_CNT;
        S000_CALL_BPZPIFAV();
        CEP.TRC(SCCGWA, BPCTIFAV.OUTPUT_AREA.FAV_AMT);
    }
    public void B035_GET_FAV_INFO_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTIFAV);
        BPCTIFAV.INPUT_AREA.FEE_CODE = BPRFBAS.KEY.FEE_CODE;
        BPCTIFAV.INPUT_AREA.CHNL = SCCGWA.COMM_AREA.CHNL;
        BPCTIFAV.INPUT_AREA.FREE_FMT = " ";
        BPCTIFAV.INPUT_AREA.BASIC_FEE = BPCFFCAL.DATA.FEE_AMT;
        BPCTIFAV.INPUT_AREA.FAV_CCY = BPB1153_AWA_1153.CFEE_CCY;
        BPCTIFAV.INPUT_AREA.PROD_CODE = BPB1153_AWA_1153.PRD_CODE;
        BPCTIFAV.INPUT_AREA.TX_AC = BPB1153_AWA_1153.AC_NO;
        BPCTIFAV.INPUT_AREA.TX_CI = BPB1153_AWA_1153.CI_NO;
        BPCTIFAV.INPUT_AREA.TX_AMT = BPB1153_AWA_1153.FEE_AMT;
        BPCTIFAV.INPUT_AREA.TX_CNT = BPB1153_AWA_1153.ACC_CNT;
        if (WS_URMT_FLG == 'Y') {
            BPCTIFAV.INPUT_AREA.URG_RMT_FLG = 'Y';
        }
        S000_CALL_BPZPIFAV();
    }
    public void B040_CAL_FEE_AMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.CHG_CCY);
        BPCONESF.FEEE_CCY = BPCFFCAL.DATA.FEE_CCY;
        CEP.TRC(SCCGWA, "ENTER SAME CCY");
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_AMT);
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.CHG_AMT);
        CEP.TRC(SCCGWA, BPCTIFAV.OUTPUT_AREA.FAV_PCT);
        CEP.TRC(SCCGWA, BPCTIFAV.OUTPUT_AREA.FAV_AMT);
        if (BPCTIFAV.OUTPUT_AREA.FAV_PCT != 0) {
            CEP.TRC(SCCGWA, "11111111");
            WS_FEE_AMT = BPCFFCAL.DATA.FEE_AMT * BPCTIFAV.OUTPUT_AREA.FAV_PCT / 100;
            BPCONESF.FEE_AMT = BPCFFCAL.DATA.FEE_AMT - WS_FEE_AMT;
        } else {
            CEP.TRC(SCCGWA, "22222222");
            BPCONESF.FEE_AMT = BPCFFCAL.DATA.FEE_AMT - BPCTIFAV.OUTPUT_AREA.FAV_AMT;
            CEP.TRC(SCCGWA, "33333333");
        }
        CEP.TRC(SCCGWA, "44444444");
        if (BPCFFCAL.DATA.FEE_CCY.equalsIgnoreCase(BPCFFCAL.DATA.CHG_CCY)) {
            CEP.TRC(SCCGWA, "55555555");
            BPCONESF.CHG_BAS = BPCFFCAL.DATA.FEE_AMT;
            BPCONESF.CHG_AMT = BPCONESF.FEE_AMT;
        } else {
            CEP.TRC(SCCGWA, "66666666");
            if (BPCFFCAL.DATA.FEE_AMT > 0) {
                IBS.init(SCCGWA, BPCFX);
                BPCFX.FUNC = '3';
                BPCFX.EXR_TYPE = BPRFBAS.RATE_GROUP;
                BPCFX.EXR_TYPE = BPRFBAS.RATE_GROUP;
                BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                CEP.TRC(SCCGWA, BPRFBAS.CHG_TYPE);
                CEP.TRC(SCCGWA, BPB1153_AWA_1153.AC_TYP);
                if (BPB1153_AWA_1153.AC_TYP == '1') {
                    BPCFX.B_CASH_FLG = 'Y';
                } else {
                    BPCFX.B_CASH_FLG = 'N';
                }
                if (BPRFBAS.CHG_TYPE == '1') {
                    CEP.TRC(SCCGWA, "CCCCCCCC");
                    BPCFX.SELL_CCY = BPB1153_AWA_1153.CHG_CCY;
                    BPCFX.BUY_AMT = BPCFFCAL.DATA.FEE_AMT;
                    BPCFX.BUY_CCY = BPCFFCAL.DATA.FEE_CCY;
                    BPCFX.CI_NO = BPB1153_AWA_1153.CI_NO;
                    S000_CALL_BPZSFX();
                    BPCONESF.CHG_BAS = BPCFX.SELL_AMT;
                } else {
                    CEP.TRC(SCCGWA, "DDDDDDDD");
                    BPCFX.BUY_CCY = BPB1153_AWA_1153.CHG_CCY;
                    BPCFX.SELL_AMT = BPCFFCAL.DATA.FEE_AMT;
                    BPCFX.SELL_CCY = BPCFFCAL.DATA.FEE_CCY;
                    BPCFX.CI_NO = BPB1153_AWA_1153.CI_NO;
                    S000_CALL_BPZSFX();
                    BPCONESF.CHG_BAS = BPCFX.BUY_AMT;
                }
                IBS.init(SCCGWA, BPCFX);
                BPCFX.FUNC = '3';
                BPCFX.EXR_TYPE = BPRFBAS.RATE_GROUP;
                BPCFX.EXR_TYPE = BPRFBAS.RATE_GROUP;
                BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                CEP.TRC(SCCGWA, BPB1153_AWA_1153.AC_TYP);
                if (BPB1153_AWA_1153.AC_TYP == '1') {
                    BPCFX.B_CASH_FLG = 'Y';
                } else {
                    BPCFX.B_CASH_FLG = 'N';
                }
                if (BPRFBAS.CHG_TYPE == '1') {
                    CEP.TRC(SCCGWA, "AAAAAAAA");
                    BPCFX.SELL_CCY = BPB1153_AWA_1153.CHG_CCY;
                    BPCFX.BUY_AMT = BPCONESF.FEE_AMT;
                    BPCFX.BUY_CCY = BPCFFCAL.DATA.FEE_CCY;
                    BPCFX.CI_NO = BPB1153_AWA_1153.CI_NO;
                    S000_CALL_BPZSFX();
                    BPCONESF.CHG_AMT = BPCFX.SELL_AMT;
                    BPCONESF.TRN_RATE = BPCFX.TRN_RATE;
                } else {
                    CEP.TRC(SCCGWA, "BBBBBBBB");
                    BPCFX.BUY_CCY = BPB1153_AWA_1153.CHG_CCY;
                    BPCFX.SELL_AMT = BPCONESF.FEE_AMT;
                    BPCFX.SELL_CCY = BPCFFCAL.DATA.FEE_CCY;
                    BPCFX.CI_NO = BPB1153_AWA_1153.CI_NO;
                    S000_CALL_BPZSFX();
                    BPCONESF.CHG_AMT = BPCFX.BUY_AMT;
                    BPCONESF.TRN_RATE = BPCFX.TRN_RATE;
                    CEP.TRC(SCCGWA, BPCONESF.TRN_RATE);
                }
                BPCONESF.EXG_DATE = (int) BPCFX.SYS_RATE;
                BPCONESF.EXG_TIME = BPCFX.EFF_TIME;
            }
            CEP.TRC(SCCGWA, "77777777");
        }
        CEP.TRC(SCCGWA, BPCONESF.CHG_AMT);
        if (BPCONESF.CHG_AMT < 0) {
            CEP.TRC(SCCGWA, "77777777");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_AMT_LT_LOW_AMT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCONESF.FEE_AMT);
        BPCONESF.FEE_BAS = BPCFFCAL.DATA.FEE_AMT;
    }
    public void B040_CAL_FEE_AMT_CN() throws IOException,SQLException,Exception {
        BPCONESF.FEEE_CCY = BPCFFCAL.DATA.FEE_CCY;
        BPCONESF.FEE_AMT = BPCFFCAL.DATA.FEE_AMT;
        if (BPCTIFAV.OUTPUT_AREA.FAV_PCT != 0) {
            if (BPCTIFAV.OUTPUT_AREA.ADJ_TYP != '2') {
                WS_FEE_AMT = BPCFFCAL.DATA.FEE_AMT * BPCTIFAV.OUTPUT_AREA.FAV_PCT / 100;
                bigD = new BigDecimal(WS_FEE_AMT);
                WS_FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            } else {
                WS_FEE_AMT = BPCFFCAL.DATA.TX_AMT * BPCTIFAV.OUTPUT_AREA.FAV_PCT / 100;
                bigD = new BigDecimal(WS_FEE_AMT);
                WS_FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
            }
        } else {
            WS_FEE_AMT = BPCTIFAV.OUTPUT_AREA.FAV_AMT;
        }
        if (BPCTIFAV.OUTPUT_AREA.ADJ_TYP == '0') {
            BPCONESF.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + WS_FEE_AMT;
        } else if (BPCTIFAV.OUTPUT_AREA.ADJ_TYP == '1') {
            BPCONESF.FEE_AMT = BPCFFCAL.DATA.FEE_AMT - WS_FEE_AMT;
        } else if (BPCTIFAV.OUTPUT_AREA.ADJ_TYP == '2') {
            BPCONESF.FEE_AMT = WS_FEE_AMT;
        } else {
        }
        BPCFX.FUNC = '3';
        BPCFX.FUNC = '3';
        CEP.TRC(SCCGWA, BPRFBAS.CHG_TYPE);
        CEP.TRC(SCCGWA, BPB1153_AWA_1153.AC_TYP);
    }
    public void B045_CAL_URGENT_FEE_AMT_CN() throws IOException,SQLException,Exception {
        if (BPB1153_AWA_1153.URMT_FLG == 'Y') {
            WS_URMT_FLG = 'Y';
            B035_GET_FAV_INFO_CN();
            if (BPCTIFAV.OUTPUT_AREA.FAV_PCT != 0) {
                if (BPCTIFAV.OUTPUT_AREA.ADJ_TYP != '2') {
                    WS_FEE_AMT = BPCFFCAL.DATA.FEE_AMT * BPCTIFAV.OUTPUT_AREA.FAV_PCT / 100;
                    bigD = new BigDecimal(WS_FEE_AMT);
                    WS_FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                } else {
                    WS_FEE_AMT = BPCFFCAL.DATA.TX_AMT * BPCTIFAV.OUTPUT_AREA.FAV_PCT / 100;
                    bigD = new BigDecimal(WS_FEE_AMT);
                    WS_FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                }
                BPCTIFAV.OUTPUT_AREA.FAV_AMT = WS_FEE_AMT;
            }
            if (BPCTIFAV.OUTPUT_AREA.ADJ_TYP == '0') {
                BPCONESF.FEE_AMT += BPCTIFAV.OUTPUT_AREA.FAV_AMT;
            } else {
                if (BPCTIFAV.OUTPUT_AREA.ADJ_TYP == '1') {
                    BPCONESF.FEE_AMT = BPCONESF.FEE_AMT - BPCTIFAV.OUTPUT_AREA.FAV_AMT;
                } else {
                    if (BPCTIFAV.OUTPUT_AREA.ADJ_TYP == '2') {
                        BPCONESF.FEE_AMT = BPCTIFAV.OUTPUT_AREA.FAV_AMT;
                    }
                }
            }
            BPCFX.FUNC = '3';
        }
    }
    public void B055_01_FEE_CMZ_INFO() throws IOException,SQLException,Exception {
        BPCPQCMZ.CI_NO = BPB1153_AWA_1153.CI_NO;
        BPCPQCMZ.FEE_CODE = BPB1153_AWA_1153.FEE_CODE;
        BPCPQCMZ.INQ_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPQCMZ.CMZ_AC = BPB1153_AWA_1153.AC_NO;
        CEP.TRC(SCCGWA, BPB1153_AWA_1153.AC_TYP);
        CEP.TRC(SCCGWA, BPB1153_AWA_1153.AC_NO);
        CEP.TRC(SCCGWA, BPB1153_AWA_1153.C_P_NO);
        if (BPB1153_AWA_1153.AC_TYP == '4') {
            BPCPQCMZ.CMZ_CARD = BPB1153_AWA_1153.AC_NO;
        }
        S000_CALL_BPZPQCMZ();
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
    public void B055_CAL_CMZ_FEE_AMT_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQCMZ.CMZ_FLG1);
        if (BPCPQCMZ.CMZ_FLG1 == '0') {
            CEP.TRC(SCCGWA, "1111");
            if (BPCPQCMZ.CMZ_FLG2 == '0') {
                S000_CMZ_CCY_FOR_AMT();
                if (BPCFFCAL.DATA.FEE_AMT > WS_CMZ_AMT) {
                    BPCONESF.FEE_AMT = BPCFFCAL.DATA.FEE_AMT - WS_CMZ_AMT;
                } else {
                    CEP.TRC(SCCGWA, "LESS THAN");
                    BPCONESF.FEE_AMT = 0;
                }
            } else {
                CEP.TRC(SCCGWA, BPCPQCMZ.CMZ_PCN);
                CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_AMT);
                BPCONESF.FEE_AMT = BPCFFCAL.DATA.FEE_AMT - BPCFFCAL.DATA.FEE_AMT * BPCPQCMZ.CMZ_PCN / 100;
                bigD = new BigDecimal(BPCONESF.FEE_AMT);
                BPCONESF.FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                CEP.TRC(SCCGWA, BPCONESF.FEE_AMT);
            }
        } else {
            CEP.TRC(SCCGWA, "222");
            if (BPCPQCMZ.CMZ_FLG1 == '1') {
                CEP.TRC(SCCGWA, "GET THE PQCMZ-CMZ-AMT!!!");
                S000_CMZ_CCY_FOR_AMT();
                BPCONESF.FEE_AMT = WS_CMZ_AMT;
            }
        }
        CEP.TRC(SCCGWA, BPCONESF.FEE_AMT);
        CEP.TRC(SCCGWA, BPCONESF.CHG_AMT);
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_W_HAS_DIY_CHG_FEE;
        S000_ERR_MSG_PROC();
    }
    public void B060_EXCHANGE_CURRENCY_CN() throws IOException,SQLException,Exception {
        if (BPCFFCAL.DATA.FEE_CCY.equalsIgnoreCase(BPCFFCAL.DATA.CHG_CCY)) {
            BPCONESF.CHG_BAS = BPCFFCAL.DATA.FEE_AMT;
            BPCONESF.CHG_AMT = BPCONESF.FEE_AMT;
        } else {
            if (BPCFFCAL.DATA.FEE_AMT > 0) {
                IBS.init(SCCGWA, BPCFX);
                BPCFX.FUNC = '3';
                BPCFX.EXR_TYPE = BPRFBAS.RATE_GROUP;
                BPCFX.EXR_TYPE = BPRFBAS.RATE_GROUP;
                BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                if (BPB1153_AWA_1153.AC_TYP == '1') {
                    BPCFX.B_CASH_FLG = 'Y';
                } else {
                    BPCFX.B_CASH_FLG = 'N';
                }
                if (BPRFBAS.CHG_TYPE == '1') {
                    BPCFX.SELL_CCY = BPB1153_AWA_1153.CHG_CCY;
                    BPCFX.BUY_AMT = BPCFFCAL.DATA.FEE_AMT;
                    BPCFX.BUY_CCY = BPCFFCAL.DATA.FEE_CCY;
                    BPCFX.CI_NO = BPB1153_AWA_1153.CI_NO;
                    S000_CALL_BPZSFX();
                    BPCONESF.CHG_BAS = BPCFX.SELL_AMT;
                } else {
                    BPCFX.BUY_CCY = BPB1153_AWA_1153.CHG_CCY;
                    BPCFX.SELL_AMT = BPCFFCAL.DATA.FEE_AMT;
                    BPCFX.SELL_CCY = BPCFFCAL.DATA.FEE_CCY;
                    BPCFX.CI_NO = BPB1153_AWA_1153.CI_NO;
                    S000_CALL_BPZSFX();
                    BPCONESF.CHG_BAS = BPCFX.BUY_AMT;
                }
                IBS.init(SCCGWA, BPCFX);
                BPCFX.FUNC = '3';
                BPCFX.EXR_TYPE = BPRFBAS.RATE_GROUP;
                BPCFX.EXR_TYPE = BPRFBAS.RATE_GROUP;
                BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                if (BPB1153_AWA_1153.AC_TYP == '1') {
                    BPCFX.B_CASH_FLG = 'Y';
                } else {
                    BPCFX.B_CASH_FLG = 'N';
                }
                if (BPRFBAS.CHG_TYPE == '1') {
                    BPCFX.SELL_CCY = BPB1153_AWA_1153.CHG_CCY;
                    BPCFX.BUY_AMT = BPCONESF.FEE_AMT;
                    BPCFX.BUY_CCY = BPCFFCAL.DATA.FEE_CCY;
                    BPCFX.CI_NO = BPB1153_AWA_1153.CI_NO;
                    S000_CALL_BPZSFX();
                    BPCONESF.CHG_AMT = BPCFX.SELL_AMT;
                    BPCONESF.TRN_RATE = BPCFX.TRN_RATE;
                } else {
                    BPCFX.BUY_CCY = BPB1153_AWA_1153.CHG_CCY;
                    BPCFX.SELL_AMT = BPCONESF.FEE_AMT;
                    BPCFX.SELL_CCY = BPCFFCAL.DATA.FEE_CCY;
                    BPCFX.CI_NO = BPB1153_AWA_1153.CI_NO;
                    S000_CALL_BPZSFX();
                    BPCONESF.CHG_AMT = BPCFX.BUY_AMT;
                    BPCONESF.TRN_RATE = BPCFX.TRN_RATE;
                }
                BPCONESF.EXG_DATE = (int) BPCFX.SYS_RATE;
                BPCONESF.EXG_TIME = BPCFX.EFF_TIME;
            }
        }
        if (BPCONESF.CHG_AMT < 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_AMT_LT_LOW_AMT;
            S000_ERR_MSG_PROC();
        }
        B061_EXCHANGE_FBAS_CURRENCY_CN();
        if (BPCONESF.CHG_AMT > WS_MAX_AMT) {
            BPCONESF.CHG_AMT = WS_MAX_AMT;
        }
        if (BPCONESF.CHG_AMT < WS_MIN_AMT) {
            BPCONESF.CHG_AMT = WS_MIN_AMT;
        }
        BPCONESF.FEE_CODE = BPB1153_AWA_1153.FEE_CODE;
        BPCONESF.FEE_BAS = BPCFFCAL.DATA.FEE_AMT;
        BPCONESF.CHG_CCY = BPCFFCAL.DATA.CHG_CCY;
        CEP.TRC(SCCGWA, BPRFBAS.CHG_TYPE);
        CEP.TRC(SCCGWA, BPB1153_AWA_1153.AC_TYP);
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
        BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCFX.FUNC = '3';
        BPCFX.EXR_TYPE = BPRFBAS.RATE_GROUP;
        if ((WS_DWN_AMT != 0) 
            && (!BPCFFCAL.DATA.CHG_CCY.equalsIgnoreCase(WS_MID_CCY))) {
            CEP.TRC(SCCGWA, "CHECK FBAS MIN AMT");
            BPCFX.BUY_CCY = BPCFFCAL.DATA.CHG_CCY;
            BPCFX.SELL_CCY = WS_MID_CCY;
            BPCFX.SELL_AMT = WS_DWN_AMT;
            S000_CALL_BPZSFX();
            CEP.TRC(SCCGWA, BPCFX.TRN_RATE);
            WS_MIN_AMT = BPCFX.BUY_AMT;
        }
        if ((WS_UP_AMT != K_MAX_AMT) 
            && (!BPCFFCAL.DATA.CHG_CCY.equalsIgnoreCase(WS_MID_CCY))) {
            CEP.TRC(SCCGWA, "CHECK FBAS MAX AMT");
            BPCFX.BUY_AMT = 0;
            BPCFX.BUY_CCY = BPCFFCAL.DATA.CHG_CCY;
            BPCFX.SELL_CCY = WS_MID_CCY;
            BPCFX.SELL_AMT = WS_UP_AMT;
            S000_CALL_BPZSFX();
            CEP.TRC(SCCGWA, BPCFX.TRN_RATE);
            WS_MAX_AMT = BPCFX.BUY_AMT;
        }
        CEP.TRC(SCCGWA, WS_MIN_AMT);
        CEP.TRC(SCCGWA, WS_MAX_AMT);
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
                WS_USING_CMZ_FLAG = 'Y';
                WS_TX_AMT = BPCPQCMZ.CMZ_AMT;
                WS_FEE_CCY = BPCPQCMZ.CMZ_CCY;
                CEP.TRC(SCCGWA, WS_FEE_CCY);
                WS_CHG_CCY = BPCFFCAL.DATA.FEE_CCY;
                CEP.TRC(SCCGWA, WS_CHG_CCY);
                R000_TRANS_BPCFX_IN();
                WS_CMZ_AMT = WS_TX_AMT;
                CEP.TRC(SCCGWA, WS_TX_AMT);
            }
        }
    }
    public void R000_TRANS_BPCFX_IN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CMZ SHOULD EXCHNAGE!!!!!");
        CEP.TRC(SCCGWA, WS_FEE_CCY);
        CEP.TRC(SCCGWA, WS_CHG_CCY);
        IBS.init(SCCGWA, BPCFX);
        BPCFX.FUNC = '3';
        BPCFX.EXR_TYPE = BPRFBAS.RATE_GROUP;
        BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCFX.CI_NO = BPCTCALF.INPUT_AREA.TX_CI;
        CEP.TRC(SCCGWA, WS_TX_AMT);
        CEP.TRC(SCCGWA, BPCFX.EXR_TYPE);
        CEP.TRC(SCCGWA, BPRFBAS.CHG_TYPE);
        CEP.TRC(SCCGWA, BPB1153_AWA_1153.AC_TYP);
        if (BPB1153_AWA_1153.AC_TYP == '1') {
            BPCFX.B_CASH_FLG = 'Y';
        } else {
            BPCFX.B_CASH_FLG = 'N';
        }
        if (BPRFBAS.CHG_TYPE == '1' 
            || WS_USING_CMZ_FLAG == 'Y') {
            BPCFX.BUY_AMT = WS_TX_AMT;
            BPCFX.BUY_CCY = WS_FEE_CCY;
            BPCFX.SELL_CCY = WS_CHG_CCY;
            S000_CALL_BPZSFX();
            CEP.TRC(SCCGWA, BPCFX.SELL_AMT);
            CEP.TRC(SCCGWA, BPCFX.TRN_RATE);
            WS_TX_AMT = BPCFX.SELL_AMT;
        } else {
            BPCFX.SELL_AMT = WS_TX_AMT;
            BPCFX.SELL_CCY = WS_FEE_CCY;
            BPCFX.BUY_CCY = WS_CHG_CCY;
            S000_CALL_BPZSFX();
            CEP.TRC(SCCGWA, BPCFX.BUY_AMT);
            CEP.TRC(SCCGWA, BPCFX.TRN_RATE);
            WS_TX_AMT = BPCFX.BUY_AMT;
        }
    }
    public void B090_OUTPUT_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BBBBBBBBBBB");
        BPCONESF.CHG_AC = BPB1153_AWA_1153.AC_NO;
        CEP.TRC(SCCGWA, "BBBBBBBBBBB");
        BPCONESF.TICKET_NO = BPB1153_AWA_1153.TICKET;
        CEP.TRC(SCCGWA, "AAAAAAAAAAA");
        BPCONESF.CHG_AC_TY = BPB1153_AWA_1153.AC_TYP;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCONESF;
        SCCFMT.DATA_LEN = 162;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, BPCONESF.CHG_FLG);
        CEP.TRC(SCCGWA, BPCONESF.FEE_CODE);
        CEP.TRC(SCCGWA, BPCONESF.FEEE_CCY);
        CEP.TRC(SCCGWA, BPCONESF.FEE_BAS);
        CEP.TRC(SCCGWA, BPCONESF.FEE_AMT);
        CEP.TRC(SCCGWA, BPCONESF.CHG_AC_TY);
        CEP.TRC(SCCGWA, BPCONESF.CHG_AC);
        CEP.TRC(SCCGWA, BPCONESF.CHG_CCY);
        CEP.TRC(SCCGWA, BPCONESF.CHG_BAS);
        CEP.TRC(SCCGWA, BPCONESF.CHG_AMT);
        CEP.TRC(SCCGWA, BPCONESF.TRN_RATE);
        CEP.TRC(SCCGWA, BPCONESF.TICKET_NO);
        CEP.TRC(SCCGWA, BPCONESF.EXG_DATE);
        CEP.TRC(SCCGWA, BPCONESF.EXG_TIME);
    }
    public void R000_GET_REG_CODE_BY_TYPE() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_TX_INFO, BPCFFTXI);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFPARM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_FEE_PARM_MAIN, BPCFPARM);
    }
    public void S000_CALL_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFX.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZTFBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_COM_FEE_INFO, BPCTFBAS);
        CEP.TRC(SCCGWA, BPCTFBAS.RC.RC_CODE);
        if (BPCTFBAS.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CODE_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFFCAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_FEE_CAL_FEE, BPCFFCAL);
    }
    public void S000_CALL_BPZPIFAV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_FEE_CAL_IFAV, BPCTIFAV);
    }
    public void S000_CALL_BPZFCALF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_CAL_FEE, BPCTCALF);
    }
    public void S000_CALL_BPZFFCON() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_F_CON_CHG_INFO, BPCFFCON);
        if (BPCFFCON.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFCON.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQCMZ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_AMO_CHECK, BPCPQCMZ);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
