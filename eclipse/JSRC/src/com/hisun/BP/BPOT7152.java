package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.DC.*;
import com.hisun.CI.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT7152 {
    int JIBS_tmp_int;
    BigDecimal bigD;
    brParm BPTFEAG_BR = new brParm();
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
    String CPN_S_AMO_CHECK = "BP-P-AMO-CHK        ";
    String K_PARM_TYPE_FBAS = "FBAS ";
    String K_PARM_TYPE_FMSK = "FMSK ";
    String CPN_EXG_PROC = "BP-EX";
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
    String WS_REF_CCY = " ";
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
    String WS_FEE_CODE = " ";
    int WS_PFEE_PROC_DT = 0;
    char WS_SKPCHG_FLG = ' ';
    char WS_SKPALL_FLG = ' ';
    BPOT7152_WS_FEE_CDS[] WS_FEE_CDS = new BPOT7152_WS_FEE_CDS[20];
    short WS_I = 0;
    char WS_URMT_FLG = ' ';
    char WS_USING_BRANCH_FLAG = ' ';
    char WS_USING_CMZ_FLAG = ' ';
    char WS_FILE_STS = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCFX BPCFX = new BPCFX();
    BPCFFCON BPCFFCON = new BPCFFCON();
    BPCONESE BPCONESE = new BPCONESE();
    BPCPQCMZ BPCPQCMZ = new BPCPQCMZ();
    BPRCMZR BPRCMZR = new BPRCMZR();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCFPARM BPCFPARM = new BPCFPARM();
    BPCTIFAV BPCTIFAV = new BPCTIFAV();
    BPCFFCAL BPCFFCAL = new BPCFFCAL();
    BPRFBAS BPRFBAS = new BPRFBAS();
    BPCTFBAS BPCTFBAS = new BPCTFBAS();
    BPVFMSK BPVFMSK = new BPVFMSK();
    BPVFCOM BPVFCOM = new BPVFCOM();
    BPVFSTD BPVFSTD = new BPVFSTD();
    DCCPACTY DCCPACTY = new DCCPACTY();
    CICACCU CICACCU = new CICACCU();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPRFEAG BPRFEAG = new BPRFEAG();
    CICQACRI CICQACRI = new CICQACRI();
    BPCRDAMT BPCRDAMT = new BPCRDAMT();
    String WS_SGN_CINO = " ";
    String WS_SGN_AC = " ";
    int WS_CLT_BR = 0;
    String WS_FEE_CD = " ";
    char WS_FDT_TYP = ' ';
    char WS_PRC_STS = ' ';
    int WS_PROC_DT = 0;
    String WS_PROD_CD = " ";
    SCCGWA SCCGWA;
    BPCGCFEE BPCGCFEE;
    BPCGPFEE BPCGPFEE;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPB7152_AWA_7152 BPB7152_AWA_7152;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCGCFEE BPCGCFEE;
    public BPOT7152() {
        for (int i=0;i<20;i++) WS_FEE_CDS[i] = new BPOT7152_WS_FEE_CDS();
    }
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT7152 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCONESE);
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB7152_AWA_7152>");
        BPB7152_AWA_7152 = (BPB7152_AWA_7152) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            WS_URMT_FLG = 'N';
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B015_GET_FEE_BASIC_INFO();
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B030_GET_BASIC_FEE_CN();
            B055_01_FEE_CMZ_INFO();
            if (BPCPQCMZ.DATE_FLG != 'Y') {
                B035_GET_FAV_INFO_CN();
                B040_CAL_FEE_AMT_CN();
            } else {
                B055_CAL_CMZ_FEE_AMT_CN();
            }
            B060_EXCHANGE_CURRENCY_CN();
        } else {
            B030_GET_BASIC_FEE();
            B035_GET_FAV_INFO();
            B040_CAL_FEE_AMT();
        }
        B089_COLLECTION_INFO();
        B090_OUTPUT_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB7152_AWA_7152.AC_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = BPB7152_AWA_7152.AC_NO;
            WS_SGN_AC = BPB7152_AWA_7152.AC_NO;
            S000_CALL_CIZACCU();
            CEP.TRC(SCCGWA, BPB7152_AWA_7152.CI_NO);
            CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
            if (BPB7152_AWA_7152.CI_NO.trim().length() > 0) {
                if (!CICACCU.DATA.CI_NO.equalsIgnoreCase(BPB7152_AWA_7152.CI_NO)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_CI_NOT_RELATED;
                    S000_ERR_MSG_PROC();
                }
            }
            WS_SGN_CINO = CICACCU.DATA.CI_NO;
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.FUNC = 'A';
            CICQACRI.DATA.AGR_NO = BPB7152_AWA_7152.AC_NO;
            S000_CALL_CIZQACRI();
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_PROD_CD);
            BPB7152_AWA_7152.PRD_CODE = CICQACRI.O_DATA.O_PROD_CD;
            CEP.TRC(SCCGWA, BPB7152_AWA_7152.PRD_CODE);
        }
    }
    public void B015_GET_FEE_BASIC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFBAS);
        IBS.init(SCCGWA, BPCTFBAS);
        BPCTFBAS.INFO.POINTER = BPRFBAS;
        BPCTFBAS.INFO.REC_LEN = 312;
        BPRFBAS.KEY.FEE_CODE = BPB7152_AWA_7152.FEE_CODE;
        BPCTFBAS.INFO.FUNC = 'Q';
        CEP.TRC(SCCGWA, BPB7152_AWA_7152.FEE_CODE);
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
        WS_FEE_CODE = BPRFBAS.KEY.FEE_CODE;
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
        BPVFCOM.KEY.FEE_CODE = WS_FEE_CODE;
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
        CEP.TRC(SCCGWA, BPB7152_AWA_7152.FEE_AMT);
        CEP.TRC(SCCGWA, BPB7152_AWA_7152.CFEE_CCY);
        if (BPB7152_AWA_7152.FEE_AMT != 0) {
            BPVFSTD.KEY.FEE_CODE = WS_FEE_CODE;
            BPVFSTD.KEY.REG_CODE = " ";
            BPVFSTD.KEY.CHN_NO = " ";
            BPVFSTD.KEY.FREE_FMT = " ";
            BPVFSTD.KEY.REF_CCY = BPB7152_AWA_7152.CFEE_CCY;
            BPCFPARM.RETURN_INFO = 'F';
            BPCFPARM.INFO.FUNC = '3';
            BPCFPARM.INFO.TYPE = "FSTD ";
            BPCFPARM.INFO.POINTER = BPVFSTD;
            S000_CALL_BPZFPARM();
            if (BPCFPARM.RETURN_INFO == 'N') {
                CEP.TRC(SCCGWA, "FFFFFFFFFFFFFFF");
                CEP.TRC(SCCGWA, BPCRBANK.LOC_CCY1);
                BPVFSTD.KEY.FEE_CODE = WS_FEE_CODE;
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
                    CEP.TRC(SCCGWA, BPB7152_AWA_7152.CFEE_CCY);
                    if (!BPVFSTD.VAL.FEE_CCY.equalsIgnoreCase(BPB7152_AWA_7152.CFEE_CCY)) {
                        IBS.init(SCCGWA, BPCFX);
                        BPCFX.FUNC = '3';
                        BPCFX.EXR_TYPE = "MDR";
                        BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                        CEP.TRC(SCCGWA, BPCRBANK.LOC_CCY1);
                        BPCFX.BUY_CCY = BPVFSTD.VAL.FEE_CCY;
                        BPCFX.SELL_AMT = BPB7152_AWA_7152.FEE_AMT;
                        BPCFX.SELL_CCY = BPB7152_AWA_7152.CFEE_CCY;
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
                CEP.TRC(SCCGWA, BPB7152_AWA_7152.FEE_AMT);
                CEP.TRC(SCCGWA, BPVFSTD.VAL.START_AMT);
                if (BPB7152_AWA_7152.FEE_AMT < BPVFSTD.VAL.START_AMT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TX_LESS_START_AMT;
                    S000_ERR_MSG_PROC();
                }
            }
        } else {
        }
        IBS.init(SCCGWA, BPCFFCAL);
        BPCFFCAL.DATA.FEE_CODE = WS_FEE_CODE;
        BPCFFCAL.DATA.CHNL_NO = BPB7152_AWA_7152.CHNL;
        BPCFFCAL.DATA.CNT_CCY = BPB7152_AWA_7152.CFEE_CCY;
        BPCFFCAL.DATA.TX_AMT = BPB7152_AWA_7152.FEE_AMT;
        BPCFFCAL.DATA.TX_CNT = BPB7152_AWA_7152.ACC_CNT;
        BPCFFCAL.DATA.CHG_CCY = BPB7152_AWA_7152.CHG_CCY;
        BPCFFCAL.DATA.FEE_CCY = BPB7152_AWA_7152.CFEE_CCY;
        BPCFFCAL.DATA.EXG_GROUP = "MDR";
        S000_CALL_BPZFFCAL();
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_AMT);
    }
    public void B030_GET_BASIC_FEE_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB7152_AWA_7152.FEE_AMT);
        CEP.TRC(SCCGWA, BPB7152_AWA_7152.CFEE_CCY);
        B030_01_GET_FEE_STD_INFO_CN();
        if (BPB7152_AWA_7152.FEE_AMT != 0) {
            CEP.TRC(SCCGWA, "GGGGGGGGGGGGGGG");
            CEP.TRC(SCCGWA, BPB7152_AWA_7152.CFEE_CCY);
            if (!BPVFSTD.VAL.FEE_CCY.equalsIgnoreCase(BPB7152_AWA_7152.CFEE_CCY)) {
                IBS.init(SCCGWA, BPCFX);
                BPCFX.FUNC = '3';
                BPCFX.EXR_TYPE = "MDR";
                BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                CEP.TRC(SCCGWA, BPCRBANK.LOC_CCY1);
                BPCFX.BUY_CCY = BPVFSTD.VAL.FEE_CCY;
                BPCFX.SELL_AMT = BPB7152_AWA_7152.FEE_AMT;
                BPCFX.SELL_CCY = BPB7152_AWA_7152.CFEE_CCY;
                CEP.TRC(SCCGWA, BPCFX.SELL_CCY);
                CEP.TRC(SCCGWA, BPCFX.BUY_AMT);
                CEP.TRC(SCCGWA, BPVFSTD.VAL.START_AMT);
                S000_CALL_BPZSFX();
                CEP.TRC(SCCGWA, BPCFX.BUY_AMT);
                if (BPCFX.BUY_AMT < BPVFSTD.VAL.START_AMT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TX_LESS_START_AMT;
                    S000_ERR_MSG_PROC();
                }
            } else {
                CEP.TRC(SCCGWA, BPB7152_AWA_7152.FEE_AMT);
                CEP.TRC(SCCGWA, BPVFSTD.VAL.START_AMT);
                if (BPB7152_AWA_7152.FEE_AMT < BPVFSTD.VAL.START_AMT) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TX_LESS_START_AMT;
                    S000_ERR_MSG_PROC();
                }
            }
        } else {
        }
        IBS.init(SCCGWA, BPCFFCAL);
        BPCFFCAL.DATA.FEE_CODE = WS_FEE_CODE;
        BPCFFCAL.DATA.CHNL_NO = BPB7152_AWA_7152.CHNL;
        BPCFFCAL.DATA.CNT_CCY = BPB7152_AWA_7152.CFEE_CCY;
        BPCFFCAL.DATA.TX_AMT = BPB7152_AWA_7152.FEE_AMT;
        BPCFFCAL.DATA.TX_CNT = BPB7152_AWA_7152.ACC_CNT;
        BPCFFCAL.DATA.CHG_CCY = BPB7152_AWA_7152.CHG_CCY;
        BPCFFCAL.DATA.FEE_CCY = BPB7152_AWA_7152.CFEE_CCY;
        BPCFFCAL.DATA.EXG_GROUP = "MDR";
        if (WS_USING_BRANCH_FLAG == 'Y') {
            BPCFFCAL.DATA.REG_CODE = "" + BPCPQORG.BRANCH_BR;
            JIBS_tmp_int = BPCFFCAL.DATA.REG_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCFFCAL.DATA.REG_CODE = "0" + BPCFFCAL.DATA.REG_CODE;
        }
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.REG_CODE);
        S000_CALL_BPZFFCAL();
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_AMT);
    }
    public void B030_01_GET_FEE_STD_INFO_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
        IBS.init(SCCGWA, BPVFSTD);
        IBS.init(SCCGWA, BPCFPARM);
        BPVFSTD.KEY.FEE_CODE = WS_FEE_CODE;
        BPVFSTD.KEY.REG_CODE = "" + BPCPQORG.BRANCH_BR;
        JIBS_tmp_int = BPVFSTD.KEY.REG_CODE.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPVFSTD.KEY.REG_CODE = "0" + BPVFSTD.KEY.REG_CODE;
        BPVFSTD.KEY.CHN_NO = " ";
        BPVFSTD.KEY.FREE_FMT = " ";
        BPVFSTD.KEY.REF_CCY = BPB7152_AWA_7152.CFEE_CCY;
        BPCFPARM.RETURN_INFO = 'F';
        BPCFPARM.INFO.FUNC = '3';
        BPCFPARM.INFO.TYPE = "FSTD ";
        BPCFPARM.INFO.POINTER = BPVFSTD;
        S000_CALL_BPZFPARM();
        if (BPCFPARM.RETURN_INFO == 'F') {
            CEP.TRC(SCCGWA, WS_USING_BRANCH_FLAG);
            WS_USING_BRANCH_FLAG = 'Y';
        }
        if (BPCFPARM.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, BPRFBAS.EXG_TYP);
            if (BPRFBAS.EXG_TYP == '0') {
                WS_REF_CCY = BPCRBANK.LOC_CCY1;
            } else {
                WS_REF_CCY = BPCRBANK.EVA_CCY;
            }
            CEP.TRC(SCCGWA, "FFFFFFFFFFFFFFF");
            CEP.TRC(SCCGWA, BPCRBANK.LOC_CCY1);
            CEP.TRC(SCCGWA, BPCRBANK.EVA_CCY);
            CEP.TRC(SCCGWA, WS_REF_CCY);
            IBS.init(SCCGWA, BPVFSTD);
            IBS.init(SCCGWA, BPCFPARM);
            BPVFSTD.KEY.FEE_CODE = WS_FEE_CODE;
            BPVFSTD.KEY.REG_CODE = "" + BPCPQORG.BRANCH_BR;
            JIBS_tmp_int = BPVFSTD.KEY.REG_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPVFSTD.KEY.REG_CODE = "0" + BPVFSTD.KEY.REG_CODE;
            BPVFSTD.KEY.CHN_NO = " ";
            BPVFSTD.KEY.FREE_FMT = " ";
            BPVFSTD.KEY.REF_CCY = WS_REF_CCY;
            BPCFPARM.RETURN_INFO = 'F';
            BPCFPARM.INFO.FUNC = '3';
            BPCFPARM.INFO.TYPE = "FSTD ";
            BPCFPARM.INFO.POINTER = BPVFSTD;
            S000_CALL_BPZFPARM();
            if (BPCFPARM.RETURN_INFO == 'F') {
                CEP.TRC(SCCGWA, WS_USING_BRANCH_FLAG);
                WS_USING_BRANCH_FLAG = 'Y';
            }
            if (BPCFPARM.RETURN_INFO == 'N') {
                IBS.init(SCCGWA, BPVFSTD);
                IBS.init(SCCGWA, BPCFPARM);
                BPVFSTD.KEY.FEE_CODE = WS_FEE_CODE;
                BPVFSTD.KEY.REG_CODE = " ";
                BPVFSTD.KEY.CHN_NO = " ";
                BPVFSTD.KEY.FREE_FMT = " ";
                BPVFSTD.KEY.REF_CCY = BPB7152_AWA_7152.CFEE_CCY;
                BPCFPARM.RETURN_INFO = 'F';
                BPCFPARM.INFO.FUNC = '3';
                BPCFPARM.INFO.TYPE = "FSTD ";
                BPCFPARM.INFO.POINTER = BPVFSTD;
                S000_CALL_BPZFPARM();
                if (BPCFPARM.RETURN_INFO == 'N') {
                    IBS.init(SCCGWA, BPVFSTD);
                    IBS.init(SCCGWA, BPCFPARM);
                    BPVFSTD.KEY.FEE_CODE = WS_FEE_CODE;
                    BPVFSTD.KEY.REG_CODE = " ";
                    BPVFSTD.KEY.CHN_NO = " ";
                    BPVFSTD.KEY.FREE_FMT = " ";
                    BPVFSTD.KEY.REF_CCY = WS_REF_CCY;
                    BPCFPARM.RETURN_INFO = 'F';
                    BPCFPARM.INFO.FUNC = '3';
                    BPCFPARM.INFO.TYPE = "FSTD ";
                    BPCFPARM.INFO.POINTER = BPVFSTD;
                    S000_CALL_BPZFPARM();
                }
            }
            if (BPCFPARM.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSTD_NOTFND;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B035_GET_FAV_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTIFAV);
        BPCTIFAV.INPUT_AREA.FEE_CODE = BPRFBAS.KEY.FEE_CODE;
        BPCTIFAV.INPUT_AREA.CHNL = SCCGWA.COMM_AREA.CHNL;
        BPCTIFAV.INPUT_AREA.FREE_FMT = " ";
        BPCTIFAV.INPUT_AREA.BASIC_FEE = BPCFFCAL.DATA.FEE_AMT;
        BPCTIFAV.INPUT_AREA.FAV_CCY = BPB7152_AWA_7152.CFEE_CCY;
        BPCTIFAV.INPUT_AREA.PROD_CODE = BPB7152_AWA_7152.PRD_CODE;
        BPCTIFAV.INPUT_AREA.TX_AC = BPB7152_AWA_7152.AC_NO;
        BPCTIFAV.INPUT_AREA.TX_CI = BPB7152_AWA_7152.CI_NO;
        BPCTIFAV.INPUT_AREA.TX_AMT = BPB7152_AWA_7152.FEE_AMT;
        BPCTIFAV.INPUT_AREA.TX_CNT = BPB7152_AWA_7152.ACC_CNT;
        S000_CALL_BPZFIFAV();
        CEP.TRC(SCCGWA, BPCTIFAV.OUTPUT_AREA.FAV_AMT);
    }
    public void B035_GET_FAV_INFO_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTIFAV);
        BPCTIFAV.INPUT_AREA.FEE_CODE = BPRFBAS.KEY.FEE_CODE;
        BPCTIFAV.INPUT_AREA.REGION_CODE = "" + BPCPQORG.BRANCH_BR;
        JIBS_tmp_int = BPCTIFAV.INPUT_AREA.REGION_CODE.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCTIFAV.INPUT_AREA.REGION_CODE = "0" + BPCTIFAV.INPUT_AREA.REGION_CODE;
        BPCTIFAV.INPUT_AREA.CHNL = SCCGWA.COMM_AREA.CHNL;
        BPCTIFAV.INPUT_AREA.FREE_FMT = " ";
        BPCTIFAV.INPUT_AREA.BASIC_FEE = BPCFFCAL.DATA.FEE_AMT;
        BPCTIFAV.INPUT_AREA.FAV_CCY = BPB7152_AWA_7152.CFEE_CCY;
        BPCTIFAV.INPUT_AREA.PROD_CODE = BPB7152_AWA_7152.PRD_CODE;
        BPCTIFAV.INPUT_AREA.TX_AC = BPB7152_AWA_7152.AC_NO;
        BPCTIFAV.INPUT_AREA.TX_CI = BPB7152_AWA_7152.CI_NO;
        BPCTIFAV.INPUT_AREA.TX_AMT = BPB7152_AWA_7152.FEE_AMT;
        BPCTIFAV.INPUT_AREA.TX_CNT = BPB7152_AWA_7152.ACC_CNT;
        if (WS_URMT_FLG == 'Y') {
            BPCTIFAV.INPUT_AREA.URG_RMT_FLG = 'Y';
        }
        S000_CALL_BPZFIFAV();
    }
    public void B040_CAL_FEE_AMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.CHG_CCY);
        BPCONESE.FEEE_CCY = BPCFFCAL.DATA.FEE_CCY;
        CEP.TRC(SCCGWA, "ENTER SAME CCY");
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_AMT);
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.CHG_AMT);
        CEP.TRC(SCCGWA, BPCTIFAV.OUTPUT_AREA.FAV_PCT);
        CEP.TRC(SCCGWA, BPCTIFAV.OUTPUT_AREA.FAV_AMT);
        if (BPCTIFAV.OUTPUT_AREA.FAV_PCT != 0) {
            CEP.TRC(SCCGWA, "11111111");
            WS_FEE_AMT = BPCFFCAL.DATA.FEE_AMT * BPCTIFAV.OUTPUT_AREA.FAV_PCT / 100;
            BPCONESE.FEE_AMT = BPCFFCAL.DATA.FEE_AMT - WS_FEE_AMT;
        } else {
            CEP.TRC(SCCGWA, "22222222");
            BPCONESE.FEE_AMT = BPCFFCAL.DATA.FEE_AMT - BPCTIFAV.OUTPUT_AREA.FAV_AMT;
            CEP.TRC(SCCGWA, "33333333");
        }
        CEP.TRC(SCCGWA, "44444444");
        if (BPCFFCAL.DATA.FEE_CCY.equalsIgnoreCase(BPCFFCAL.DATA.CHG_CCY)) {
            CEP.TRC(SCCGWA, "55555555");
            BPCONESE.CHG_BAS = BPCFFCAL.DATA.FEE_AMT;
            BPCONESE.CHG_AMT = BPCONESE.FEE_AMT;
        } else {
            CEP.TRC(SCCGWA, "66666666");
            if (BPCFFCAL.DATA.FEE_AMT > 0) {
                IBS.init(SCCGWA, BPCFX);
                BPCFX.FUNC = '3';
                BPCFX.EXR_TYPE = "MDR";
                BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                CEP.TRC(SCCGWA, BPRFBAS.CHG_TYPE);
                if (BPRFBAS.CHG_TYPE == '1') {
                    CEP.TRC(SCCGWA, "CCCCCCCC");
                    BPCFX.SELL_CCY = BPB7152_AWA_7152.CHG_CCY;
                    BPCFX.BUY_AMT = BPCFFCAL.DATA.FEE_AMT;
                    BPCFX.BUY_CCY = BPCFFCAL.DATA.FEE_CCY;
                    BPCFX.CI_NO = BPB7152_AWA_7152.CI_NO;
                    S000_CALL_BPZSFX();
                    BPCONESE.CHG_BAS = BPCFX.SELL_AMT;
                } else {
                    CEP.TRC(SCCGWA, "DDDDDDDD");
                    BPCFX.BUY_CCY = BPB7152_AWA_7152.CHG_CCY;
                    BPCFX.SELL_AMT = BPCFFCAL.DATA.FEE_AMT;
                    BPCFX.SELL_CCY = BPCFFCAL.DATA.FEE_CCY;
                    BPCFX.CI_NO = BPB7152_AWA_7152.CI_NO;
                    S000_CALL_BPZSFX();
                    BPCONESE.CHG_BAS = BPCFX.BUY_AMT;
                }
                IBS.init(SCCGWA, BPCFX);
                BPCFX.FUNC = '3';
                BPCFX.EXR_TYPE = "MDR";
                BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                if (BPRFBAS.CHG_TYPE == '1') {
                    CEP.TRC(SCCGWA, "AAAAAAAA");
                    BPCFX.SELL_CCY = BPB7152_AWA_7152.CHG_CCY;
                    BPCFX.BUY_AMT = BPCONESE.FEE_AMT;
                    BPCFX.BUY_CCY = BPCFFCAL.DATA.FEE_CCY;
                    BPCFX.CI_NO = BPB7152_AWA_7152.CI_NO;
                    S000_CALL_BPZSFX();
                    BPCONESE.CHG_AMT = BPCFX.SELL_AMT;
                    BPCONESE.TRN_RATE = BPCFX.TRN_RATE;
                } else {
                    CEP.TRC(SCCGWA, "BBBBBBBB");
                    BPCFX.BUY_CCY = BPB7152_AWA_7152.CHG_CCY;
                    BPCFX.SELL_AMT = BPCONESE.FEE_AMT;
                    BPCFX.SELL_CCY = BPCFFCAL.DATA.FEE_CCY;
                    BPCFX.CI_NO = BPB7152_AWA_7152.CI_NO;
                    S000_CALL_BPZSFX();
                    BPCONESE.CHG_AMT = BPCFX.BUY_AMT;
                    BPCONESE.TRN_RATE = BPCFX.TRN_RATE;
                    CEP.TRC(SCCGWA, BPCONESE.TRN_RATE);
                }
            }
            CEP.TRC(SCCGWA, "77777777");
        }
        CEP.TRC(SCCGWA, BPCONESE.CHG_AMT);
        if (BPCONESE.CHG_AMT < 0) {
            CEP.TRC(SCCGWA, "77777777");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_AMT_LT_LOW_AMT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCONESE.FEE_AMT);
        BPCONESE.FEE_BAS = BPCFFCAL.DATA.FEE_AMT;
    }
    public void B040_CAL_FEE_AMT_CN() throws IOException,SQLException,Exception {
        BPCONESE.FEEE_CCY = BPCFFCAL.DATA.FEE_CCY;
        BPCONESE.FEE_AMT = BPCFFCAL.DATA.FEE_AMT;
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
            BPCONESE.FEE_AMT = BPCFFCAL.DATA.FEE_AMT + WS_FEE_AMT;
        } else if (BPCTIFAV.OUTPUT_AREA.ADJ_TYP == '1') {
            BPCONESE.FEE_AMT = BPCFFCAL.DATA.FEE_AMT - WS_FEE_AMT;
        } else if (BPCTIFAV.OUTPUT_AREA.ADJ_TYP == '2') {
            BPCONESE.FEE_AMT = WS_FEE_AMT;
        } else {
        }
        CEP.TRC(SCCGWA, BPRFBAS.CHG_TYPE);
    }
    public void B055_01_FEE_CMZ_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQCMZ);
        if (BPB7152_AWA_7152.CI_NO.trim().length() == 0) {
            BPCPQCMZ.CI_NO = CICACCU.DATA.CI_NO;
        } else {
            BPCPQCMZ.CI_NO = BPB7152_AWA_7152.CI_NO;
        }
        BPCPQCMZ.FEE_CODE = BPB7152_AWA_7152.FEE_CODE;
        BPCPQCMZ.INQ_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPQCMZ.CMZ_AC = BPB7152_AWA_7152.AC_NO;
        BPCPQCMZ.CMZ_CARD = BPB7152_AWA_7152.AC_NO;
        CEP.TRC(SCCGWA, "ZXC");
        if (BPCPQCMZ.CI_NO.trim().length() > 0) {
            S000_CALL_BPZPQCMZ();
        }
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
            CEP.TRC(SCCGWA, BPCPQCMZ.CMZ_FLG1);
            if (BPCPQCMZ.CMZ_FLG2 == '0') {
                S000_CMZ_CCY_FOR_AMT();
                if (BPCFFCAL.DATA.FEE_AMT > WS_CMZ_AMT) {
                    BPCONESE.FEE_AMT = BPCFFCAL.DATA.FEE_AMT - WS_CMZ_AMT;
                } else {
                    CEP.TRC(SCCGWA, "LESS THAN");
                    BPCONESE.FEE_AMT = 0;
                }
            } else {
                CEP.TRC(SCCGWA, BPCPQCMZ.CMZ_PCN);
                CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_AMT);
                BPCONESE.FEE_AMT = BPCFFCAL.DATA.FEE_AMT - BPCFFCAL.DATA.FEE_AMT * BPCPQCMZ.CMZ_PCN;
                bigD = new BigDecimal(BPCONESE.FEE_AMT);
                BPCONESE.FEE_AMT = bigD.setScale(2, RoundingMode.HALF_UP).doubleValue();
                CEP.TRC(SCCGWA, BPCONESE.FEE_AMT);
            }
        } else {
            CEP.TRC(SCCGWA, "222");
            CEP.TRC(SCCGWA, BPCPQCMZ.CMZ_FLG1);
            CEP.TRC(SCCGWA, BPCPQCMZ.CMZ_CCY);
            CEP.TRC(SCCGWA, BPB7152_AWA_7152.CHG_CCY);
            if (BPCPQCMZ.CMZ_FLG1 == '1') {
                CEP.TRC(SCCGWA, "GET THE PQCMZ-CMZ-AMT!!!");
                S000_CMZ_CCY_FOR_AMT();
                BPCONESE.FEE_AMT = WS_CMZ_AMT;
            }
        }
        CEP.TRC(SCCGWA, BPCONESE.FEE_AMT);
        CEP.TRC(SCCGWA, BPCONESE.CHG_AMT);
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_W_HAS_DIY_CHG_FEE;
        S000_ERR_MSG_PROC();
    }
    public void B089_COLLECTION_INFO() throws IOException,SQLException,Exception {
        WS_SKPCHG_FLG = 'N';
        CEP.TRC(SCCGWA, "QWQWQW");
        CEP.TRC(SCCGWA, WS_SGN_CINO);
        if (WS_SGN_CINO.trim().length() > 0) {
            T000_STARTBR_BPTFEAG_01();
            T000_READNEXT_BPTFEAG();
            while (WS_FILE_STS != 'N' 
                && WS_SKPCHG_FLG != 'Y') {
                WS_PFEE_PROC_DT = SCCGWA.COMM_AREA.AC_DATE;
                CEP.TRC(SCCGWA, WS_PFEE_PROC_DT);
                CEP.TRC(SCCGWA, BPRFEAG.KEY.EFF_DATE);
                CEP.TRC(SCCGWA, BPRFEAG.EXP_DATE);
                CEP.TRC(SCCGWA, BPRFEAG.KEY.CLT_CI_NO);
                CEP.TRC(SCCGWA, BPRFEAG.KEY.CLT_AC);
                CEP.TRC(SCCGWA, WS_SGN_AC);
                if (((WS_SGN_CINO.equalsIgnoreCase(BPRFEAG.KEY.CLT_CI_NO) 
                    && BPRFEAG.KEY.CLT_AC.trim().length() == 0) 
                    || (WS_SGN_AC.equalsIgnoreCase(BPRFEAG.KEY.CLT_AC) 
                    && BPRFEAG.KEY.CLT_AC.trim().length() > 0) 
                    && WS_PFEE_PROC_DT >= BPRFEAG.KEY.EFF_DATE 
                    && WS_PFEE_PROC_DT <= BPRFEAG.EXP_DATE)) {
                    WS_SKPCHG_FLG = 'Y';
                    CEP.TRC(SCCGWA, WS_SKPCHG_FLG);
                    WS_FEE_CDS[1-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE1;
                    WS_FEE_CDS[2-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE2;
                    WS_FEE_CDS[3-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE3;
                    WS_FEE_CDS[4-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE4;
                    WS_FEE_CDS[5-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE5;
                    WS_FEE_CDS[6-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE6;
                    WS_FEE_CDS[7-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE7;
                    WS_FEE_CDS[8-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE8;
                    WS_FEE_CDS[9-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE9;
                    WS_FEE_CDS[10-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE10;
                    WS_FEE_CDS[11-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE11;
                    WS_FEE_CDS[12-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE12;
                    WS_FEE_CDS[13-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE13;
                    WS_FEE_CDS[14-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE14;
                    WS_FEE_CDS[15-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE15;
                    WS_FEE_CDS[16-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE16;
                    WS_FEE_CDS[17-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE17;
                    WS_FEE_CDS[18-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE18;
                    WS_FEE_CDS[19-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE19;
                    WS_FEE_CDS[20-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE20;
                    CEP.TRC(SCCGWA, WS_FEE_CDS[1-1].WS_FEE_CD1);
                    CEP.TRC(SCCGWA, WS_FEE_CDS[2-1].WS_FEE_CD1);
                    CEP.TRC(SCCGWA, WS_FEE_CDS[3-1].WS_FEE_CD1);
                    WS_PFEE_PROC_DT = 0;
                }
                if (WS_SKPCHG_FLG != 'Y') {
                    T000_READNEXT_BPTFEAG();
                    CEP.TRC(SCCGWA, "WANG54");
                }
            }
            T000_ENDBR_BPTFEAG();
            if (WS_SKPCHG_FLG != 'Y') {
                WS_PROD_CD = BPB7152_AWA_7152.PRD_CODE;
                T000_STARTBR_BPTFEAG_02();
                T000_READNEXT_BPTFEAG();
                while (WS_FILE_STS != 'N' 
                    && WS_SKPCHG_FLG != 'Y') {
                    WS_PFEE_PROC_DT = SCCGWA.COMM_AREA.AC_DATE;
                    CEP.TRC(SCCGWA, WS_PFEE_PROC_DT);
                    if (((WS_PROD_CD.equalsIgnoreCase(BPRFEAG.KEY.PRDT_CODE) 
                        && BPRFEAG.KEY.CLT_AC.trim().length() == 0 
                        && BPRFEAG.KEY.CLT_CI_NO.trim().length() == 0)) 
                        && WS_PFEE_PROC_DT >= BPRFEAG.KEY.EFF_DATE 
                        && WS_PFEE_PROC_DT <= BPRFEAG.EXP_DATE) {
                        WS_SKPCHG_FLG = 'Y';
                        CEP.TRC(SCCGWA, WS_SKPCHG_FLG);
                        WS_FEE_CDS[1-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE1;
                        WS_FEE_CDS[2-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE2;
                        WS_FEE_CDS[3-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE3;
                        WS_FEE_CDS[4-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE4;
                        WS_FEE_CDS[5-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE5;
                        WS_FEE_CDS[6-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE6;
                        WS_FEE_CDS[7-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE7;
                        WS_FEE_CDS[8-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE8;
                        WS_FEE_CDS[9-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE9;
                        WS_FEE_CDS[10-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE10;
                        WS_FEE_CDS[11-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE11;
                        WS_FEE_CDS[12-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE12;
                        WS_FEE_CDS[13-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE13;
                        WS_FEE_CDS[14-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE14;
                        WS_FEE_CDS[15-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE15;
                        WS_FEE_CDS[16-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE16;
                        WS_FEE_CDS[17-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE17;
                        WS_FEE_CDS[18-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE18;
                        WS_FEE_CDS[19-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE19;
                        WS_FEE_CDS[20-1].WS_FEE_CD1 = BPRFEAG.FEE_CODE20;
                        CEP.TRC(SCCGWA, WS_FEE_CDS[1-1].WS_FEE_CD1);
                        CEP.TRC(SCCGWA, WS_FEE_CDS[2-1].WS_FEE_CD1);
                        CEP.TRC(SCCGWA, WS_FEE_CDS[3-1].WS_FEE_CD1);
                        WS_PFEE_PROC_DT = 0;
                    }
                    if (WS_SKPCHG_FLG != 'Y') {
                        T000_READNEXT_BPTFEAG();
                    }
                }
                T000_ENDBR_BPTFEAG();
            }
            WS_SKPALL_FLG = 'N';
            if (WS_SKPCHG_FLG == 'Y') {
                CEP.TRC(SCCGWA, BPRFEAG.CLT_RANGE);
                if (BPRFEAG.CLT_RANGE != 'A') {
                    for (WS_I = 1; WS_I <= 20 
                        && WS_FEE_CDS[WS_I-1].WS_FEE_CD1.trim().length() != 0 
                        && WS_SKPALL_FLG != 'Y'; WS_I += 1) {
                        CEP.TRC(SCCGWA, BPB7152_AWA_7152.FEE_CODE);
                        CEP.TRC(SCCGWA, WS_FEE_CDS[WS_I-1].WS_FEE_CD1);
                        CEP.TRC(SCCGWA, "WANG56");
                        if (BPB7152_AWA_7152.FEE_CODE.equalsIgnoreCase(WS_FEE_CDS[WS_I-1].WS_FEE_CD1)) {
                            CEP.TRC(SCCGWA, "WANG57");
                            WS_SKPALL_FLG = 'Y';
                        }
                    }
                }
                if (BPRFEAG.CLT_RANGE == 'A') {
                    CEP.TRC(SCCGWA, "WANG58");
                    WS_SKPALL_FLG = 'Y';
                }
            }
        }
        if (WS_SKPALL_FLG == 'Y') {
            BPCONESE.FEE_CHG_TYPE = 'C';
        } else {
            BPCONESE.FEE_CHG_TYPE = 'A';
        }
    }
    public void B090_OUTPUT_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BBBBBBBBBBB");
        BPCONESE.CHG_AC = BPB7152_AWA_7152.AC_NO;
        R000_GET_RESULT_AMT();
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCONESE;
        SCCFMT.DATA_LEN = 138;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, BPCONESE.FEE_CODE);
        CEP.TRC(SCCGWA, BPCONESE.FEEE_CCY);
        CEP.TRC(SCCGWA, BPCONESE.FEE_BAS);
        CEP.TRC(SCCGWA, BPCONESE.FEE_AMT);
        CEP.TRC(SCCGWA, BPCONESE.CHG_AC);
        CEP.TRC(SCCGWA, BPCONESE.CHG_CCY);
        CEP.TRC(SCCGWA, BPCONESE.CHG_BAS);
        CEP.TRC(SCCGWA, BPCONESE.CHG_AMT);
        CEP.TRC(SCCGWA, BPCONESE.TRN_RATE);
    }
    public void R000_GET_RESULT_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRDAMT);
        BPCRDAMT.CCY = BPCONESE.FEEE_CCY;
        BPCRDAMT.AMT = BPCONESE.FEE_BAS;
        CEP.TRC(SCCGWA, BPCONESE.FEE_BAS);
        S000_CALL_BPZRDAMT();
        CEP.TRC(SCCGWA, BPCRDAMT.RESULT_AMT);
        BPCONESE.FEE_BAS = BPCRDAMT.RESULT_AMT;
        CEP.TRC(SCCGWA, BPCONESE.FEE_BAS);
        IBS.init(SCCGWA, BPCRDAMT);
        BPCRDAMT.CCY = BPCONESE.FEEE_CCY;
        BPCRDAMT.AMT = BPCONESE.FEE_AMT;
        CEP.TRC(SCCGWA, BPCONESE.FEE_AMT);
        S000_CALL_BPZRDAMT();
        CEP.TRC(SCCGWA, BPCRDAMT.RESULT_AMT);
        BPCONESE.FEE_AMT = BPCRDAMT.RESULT_AMT;
        CEP.TRC(SCCGWA, BPCONESE.FEE_AMT);
        IBS.init(SCCGWA, BPCRDAMT);
        BPCRDAMT.CCY = BPCONESE.CHG_CCY;
        BPCRDAMT.AMT = BPCONESE.CHG_BAS;
        CEP.TRC(SCCGWA, BPCONESE.CHG_BAS);
        S000_CALL_BPZRDAMT();
        CEP.TRC(SCCGWA, BPCRDAMT.RESULT_AMT);
        BPCONESE.CHG_BAS = BPCRDAMT.RESULT_AMT;
        CEP.TRC(SCCGWA, BPCONESE.CHG_BAS);
        IBS.init(SCCGWA, BPCRDAMT);
        BPCRDAMT.CCY = BPCONESE.CHG_CCY;
        BPCRDAMT.AMT = BPCONESE.CHG_AMT;
        CEP.TRC(SCCGWA, BPCONESE.CHG_AMT);
        S000_CALL_BPZRDAMT();
        CEP.TRC(SCCGWA, BPCRDAMT.RESULT_AMT);
        BPCONESE.CHG_AMT = BPCRDAMT.RESULT_AMT;
        CEP.TRC(SCCGWA, BPCONESE.CHG_AMT);
    }
    public void B050_CAL_BVF_FEE_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPVFSTD.VAL.FEE_DATA[1-1].AGG_MTH);
        if (BPVFSTD.VAL.FEE_DATA[1-1].AGG_MTH == '2') {
            if (BPB7152_AWA_7152.ACC_CNT != 0) {
                WS_FEE_AMT = WS_FEE_AMT * BPB7152_AWA_7152.ACC_CNT;
                BPCFFCAL.DATA.FEE_AMT = BPCFFCAL.DATA.FEE_AMT * BPB7152_AWA_7152.ACC_CNT;
                BPCONESE.FEE_AMT = BPCONESE.FEE_AMT * BPB7152_AWA_7152.ACC_CNT;
            }
        }
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_AMT);
        CEP.TRC(SCCGWA, BPCONESE.FEE_AMT);
    }
    public void B060_EXCHANGE_CURRENCY_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.FEE_CCY);
        CEP.TRC(SCCGWA, BPCFFCAL.DATA.CHG_CCY);
        BPCONESE.CHG_BAS = BPCFFCAL.DATA.FEE_AMT;
        BPCONESE.CHG_AMT = BPCONESE.FEE_AMT;
        if (BPCONESE.CHG_AMT < 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_AMT_LT_LOW_AMT;
            S000_ERR_MSG_PROC();
        }
        B061_EXCHANGE_FBAS_CURRENCY_CN();
        if (BPCPQCMZ.CMZ_FLG1 != '1') {
            if (BPCONESE.CHG_AMT > WS_MAX_AMT) {
                BPCONESE.CHG_AMT = WS_MAX_AMT;
            }
            if (BPCONESE.CHG_AMT < WS_MIN_AMT) {
                BPCONESE.CHG_AMT = WS_MIN_AMT;
            }
        } else {
            if (BPCONESE.CHG_AMT < 0) {
                BPCONESE.CHG_AMT = 0;
            }
        }
        BPCONESE.FEE_CODE = BPB7152_AWA_7152.FEE_CODE;
        BPCONESE.FEE_BAS = BPCFFCAL.DATA.FEE_AMT;
        BPCONESE.CHG_CCY = BPCFFCAL.DATA.CHG_CCY;
        BPCONESE.FEEE_CCY = BPCFFCAL.DATA.FEE_CCY;
        CEP.TRC(SCCGWA, BPRFBAS.CHG_TYPE);
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
            && (!BPCFFCAL.DATA.CHG_CCY.equalsIgnoreCase(WS_MID_CCY))) {
            CEP.TRC(SCCGWA, "CHECK FBAS MIN AMT");
            BPCFX.BUY_CCY = BPCFFCAL.DATA.CHG_CCY;
            BPCFX.SELL_CCY = WS_MID_CCY;
            BPCFX.SELL_AMT = WS_DWN_AMT;
            S000_CALL_BPZSFX();
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
                R000_TRANS_BPCEX_IN();
                WS_CMZ_AMT = WS_TX_AMT;
                CEP.TRC(SCCGWA, WS_TX_AMT);
            }
        }
    }
    public void R000_TRANS_BPCEX_IN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CMZ SHOULD EXCHNAGE!!!!!");
        CEP.TRC(SCCGWA, WS_FEE_CCY);
        CEP.TRC(SCCGWA, WS_CHG_CCY);
        IBS.init(SCCGWA, BPCFX);
        BPCFX.FUNC = '3';
        BPCFX.EXR_TYPE = "MDR";
        BPCFX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCFX.CI_NO = BPCTCALF.INPUT_AREA.TX_CI;
        CEP.TRC(SCCGWA, WS_TX_AMT);
        CEP.TRC(SCCGWA, BPRFBAS.CHG_TYPE);
        if (BPRFBAS.CHG_TYPE == '1' 
            || WS_USING_CMZ_FLAG == 'Y') {
            BPCFX.BUY_AMT = WS_TX_AMT;
            BPCFX.BUY_CCY = WS_FEE_CCY;
            BPCFX.SELL_CCY = WS_CHG_CCY;
            S000_CALL_BPZSFX();
            WS_TX_AMT = BPCFX.SELL_AMT;
        } else {
            BPCFX.SELL_AMT = WS_TX_AMT;
            BPCFX.SELL_CCY = WS_FEE_CCY;
            BPCFX.BUY_CCY = WS_CHG_CCY;
            S000_CALL_BPZSFX();
            WS_TX_AMT = BPCFX.BUY_AMT;
        }
    }
    public void R000_GET_REG_CODE_BY_TYPE() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_BPZRDAMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-ROUND-AMT", BPCRDAMT);
        if (BPCRDAMT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRDAMT.RC);
            S000_ERR_MSG_PROC();
        }
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
    public void S000_CALL_BPZPQCMZ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_AMO_CHECK, BPCPQCMZ);
    }
    public void S000_CALL_BPZFIFAV() throws IOException,SQLException,Exception {
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
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU         ", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_STARTBR_BPTFEAG_01() throws IOException,SQLException,Exception {
        BPTFEAG_BR.rp = new DBParm();
        BPTFEAG_BR.rp.TableName = "BPTFEAG";
        BPTFEAG_BR.rp.where = "CLT_CI_NO = :WS_SGN_CINO";
        BPTFEAG_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRFEAG, this, BPTFEAG_BR);
    }
    public void T000_READNEXT_BPTFEAG() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFEAG, this, BPTFEAG_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FILE_STS = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FILE_STS = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTFEAG() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFEAG_BR);
    }
    public void T000_STARTBR_BPTFEAG_02() throws IOException,SQLException,Exception {
        BPTFEAG_BR.rp = new DBParm();
        BPTFEAG_BR.rp.TableName = "BPTFEAG";
        BPTFEAG_BR.rp.where = "PRDT_CODE = :WS_PROD_CD";
        BPTFEAG_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, BPRFEAG, this, BPTFEAG_BR);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
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
